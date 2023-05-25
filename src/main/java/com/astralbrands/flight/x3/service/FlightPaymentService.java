package com.astralbrands.flight.x3.service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.astralbrands.flight.x3.model.OrderDetails;
import com.astralbrands.flight.x3.model.OrderShipmentID;
import com.astralbrands.flight.x3.model.SearchCriteriaResponse;
import com.astralbrands.flight.x3.model.SearchCriteriaResult;
import com.astralbrands.flight.x3.util.ApiUtil;
import com.astralbrands.flight.x3.util.AppConstants;

@Service
public class FlightPaymentService extends FlightAbstractService implements AppConstants {

	Logger log = LoggerFactory.getLogger(FlightPaymentService.class);

	private static final Map<String, String> SOLD_TO_MAP = new HashMap<>(5);
	private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	static {
		SOLD_TO_MAP.put("US Direct to Corporate", "49999988"); // Warehouse ID from Flight 48
		SOLD_TO_MAP.put("US Corp Home Office Warehouse", "49999999"); // Warehouse ID from Flight 18
		SOLD_TO_MAP.put("Corp Warehouse CAD", "49999997"); // Warehouse ID from Flight 863
		SOLD_TO_MAP.put("Canada Direct to Corporate", "49999997"); // Warehouse ID from Flight 60
	}

	@Autowired
	private AccessTokenService restApiCallService;
	
	@Autowired
	private SageX3Service sageX3Service;

	@Value("${flight.search.criteria.url}")
	private String searchCriteriaUrl;

	@Value("${flight.search.criteria.values}")
	private String searchValues;

	@Value("${flight.getOrderShipmentsUrl}")
	private String orderShipmentsUrl;

	@Value("${flight.orders.detailsUrl}")
	private String orderDetailsUrl;

	public String uploadPaymentDetailsToX3() {
		return uploadPaymentDetailsToX3(null);
	}

	public String uploadPaymentDetailsToX3(String runId) {

		Instant start = Instant.now();

		Map<String, String> orderDisplayIds = new HashMap<>();
		Map<String, OrderDetails> orderDetails = new HashMap<>();
		Map<Integer, String> runIds = new HashMap<>();
		StringBuilder sb = new StringBuilder(); // search
		String wareHouseName = "";
		try {
			String accessToken = restApiCallService.getAccessToken();
			//runId = "39604";
			runIds = searchWareHouseId(accessToken, runId, searchCriteriaUrl); // To store all the
			// available runIds and Warehouse names
			//runIds.put(36416,"US Corp Home Office Warehouse");
			for (Map.Entry<Integer, String> entry : runIds.entrySet()) {
				orderDisplayIds.putAll(getOrderShipmentIds(entry.getKey(), accessToken, sb));
			}
			orderDetails.putAll(processDisplayIds(orderDisplayIds, accessToken, sb));

			for (Map.Entry<String, OrderDetails> entry : orderDetails.entrySet()) {
				System.out.println("Order details for : " + entry.getValue().getDisplayID());
				String paymentTerms = "";
				String transactionId = "";
				if (!entry.getValue().getOrderPayments().isEmpty()) {
					if (entry.getValue().getOrderPayments().get(0).getAmount() > 0) {
						for (int i = 0; i < entry.getValue().getTransactions().size(); i++) {
							if (entry.getValue().getTransactions().get(i).getPaymentTypeIdentifier() != null
									&& !entry.getValue().getTransactions().get(i).getPaymentTypeIdentifier()
											.equals(EMPTRY_STR)) {
								paymentTerms = entry.getValue().getTransactions().get(i).getPaymentTypeIdentifier()
										.toString();
								transactionId = entry.getValue().getTransactions().get(i).getTransactionID();
							}
						}
						if (paymentTerms != null && !paymentTerms.equals(EMPTRY_STR)) {
							wareHouseName = entry.getValue().getOrderLines().get(0).getWarehouseName();
							sb.append(getHeader(entry.getValue(), wareHouseName, transactionId));
							sb.append(NEW_LINE);// for manual imports
							//sb.append(LINE_SEPARATOR);//for auto imports
							sb.append(getOrderLine(entry.getValue(), wareHouseName));
							sb.append(NEW_LINE);// for manual imports
							//sb.append(LINE_SEPARATOR);//for auto imports
						}
					}
				}
			}
			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toSeconds();
			// runIds = null;
			log.info(" Total api execution times in Seconds :" + timeElapsed);
		} catch (Exception e) {
			log.error("Exception while calling service " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("Final Payment import file : " + sb.toString());
		log.info("Import process is done");
        return sb.toString();
	}

	private String getOrderLine(OrderDetails orderDetails, String wareHouseName) {
		boolean isUsSite = isUSSite(wareHouseName);
		StringJoiner sj = new StringJoiner(IFILE_DATA_SEPERATOR);
		sj.add("D");
		sj.add("PAYRC");
		sj.add("AST");
		sj.add("11000");
		sj.add(SOLD_TO_MAP.get(wareHouseName));
		sj.add("CCCLI");
		sj.add(orderDetails.getDisplayID());
		sj.add(EMPTRY_STR);
		sj.add(SOLD_TO_MAP.get(wareHouseName));
		sj.add(getDateInYYYYMMDD(orderDetails.getOrderPayments().get(0).getPaymentDate()));
		sj.add(getSalesSite(isUsSite));
		sj.add(getCurrency(isUsSite));
		sj.add(String.valueOf(orderDetails.getOrderPayments().get(0).getAmount()));
		sj.add("NT");
		sj.add("0");

		return sj.toString();
	}

	private String getHeader(OrderDetails orderDetails, String wareHouseName, String transactionId) {
		boolean isUsSite = isUSSite(wareHouseName);
		StringJoiner sj = new StringJoiner(IFILE_DATA_SEPERATOR);
		sj.add("P");
		sj.add(EMPTRY_STR);
		sj.add(transactionId);
		sj.add("XX1XO");
		sj.add(EMPTRY_STR);
		sj.add(SOLD_TO_MAP.get(wareHouseName));
		sj.add("AST");
		sj.add("11000");
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		sj.add(getSalesSite(isUsSite));
		sj.add(getBankAccount(isUsSite));
		sj.add("2"); // need to confirm this one
		sj.add(getCurrency(isUsSite));
		sj.add(String.valueOf(orderDetails.getOrderPayments().get(0).getAmount()));
		sj.add(getDateInYYYYMMDD(orderDetails.getOrderDate()));

		return sj.toString();
	}

	private String getBankAccount(boolean isUsSite) {
		if (isUsSite) {
			return "1006";
		} else {
			return "1012";
		}
	}

	public OrderDetails orderDetails(String orderDisplayId, String accessToken) {

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(orderDetailsUrl)
				.queryParam(ORDER_DISPLAY_ID, "{orderDisplayId}").encode().toUriString();
		Map<String, String> params = new HashMap<>();
		params.put(ORDER_DISPLAY_ID, orderDisplayId);
		OrderDetails orderDetails = call(urlTemplate, HttpMethod.GET, null, OrderDetails.class, getHeader(accessToken),
				params);
		params = null;
		log.info("Received order details for display Id : " + orderDetails.getDisplayID()
				+ " with order lines of size :" + orderDetails.getOrderLines().size());

		return orderDetails;
	}

	public Map<String, String> getOrderShipmentIds(Integer runId, String accessToken, StringBuilder sb) {

		Map<String, String> orderDisplayIds = new HashMap<>();
		try {
			String urlTemplate = UriComponentsBuilder.fromHttpUrl(orderShipmentsUrl)
					.queryParam(SHIPPING_RUN_ID, "{shippingFileRunId}").encode().toUriString();
			Map<String, String> params = new HashMap<>();
			params.put(SHIPPING_RUN_ID, String.valueOf(runId));
			OrderShipmentID[] response = call(urlTemplate, HttpMethod.GET, null, OrderShipmentID[].class,
					getHeader(accessToken), params);
			params = null;
			if (response != null) {
				return Stream.of(response).collect(
						Collectors.toMap(OrderShipmentID::getOrderDisplayID, OrderShipmentID::getShipmentDisplayID));
			}
			log.info("Map is : " + Map.of());
		} catch (Exception ex) {
			sb.append(runId).append(IFILE_DATA_SEPERATOR);
		}
		log.info("Shipments and display Id's map is : " + Map.of());
		return Map.of();
	}

	private Map<String, OrderDetails> processDisplayIds(Map<String, String> displayIds, String tokenId,
			StringBuilder sb) {
		Map<String, OrderDetails> displayIdOrders = new HashMap<>();
		for (Entry<String, String> displayId : displayIds.entrySet()) {
			if (displayId.getKey() != null) {
				try {
					log.info("Getting into order details for display Id : " + displayId.getKey());
					displayIdOrders.put(displayId.getValue(), orderDetails(displayId.getKey(), tokenId));
				} catch (Exception ex) {
					sb.append(displayId).append(IFILE_DATA_SEPERATOR);
				}
			}
		}
		log.info("Processed map display Id's : " + displayIdOrders.keySet());
		return displayIdOrders;
	}

	// Duplicated
	private boolean isUSSite(String wareHouseName) {
		return wareHouseName.contains(US);
	}

	// Duplicated
	private String getSalesSite(boolean isUsSite) {
		if (isUsSite) {
			return US_SITE;
		} else {
			return "ALCCA";
		}
	}

	// Duplicated
	private String getCurrency(boolean isUsSite) {
		if (isUsSite) {
			return USD;
		} else {
			return CAD;
		}
	}

	// Duplicated
	public String getDateInYYYYMMDD(Date date) {
		return inputFormat.format(date);
	}

}
