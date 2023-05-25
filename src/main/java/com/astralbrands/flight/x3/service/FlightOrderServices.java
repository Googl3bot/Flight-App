package com.astralbrands.flight.x3.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.astralbrands.flight.x3.util.ApiUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.astralbrands.flight.x3.model.BillingAddress;
import com.astralbrands.flight.x3.model.EmailAttachment;
import com.astralbrands.flight.x3.model.EmailData;
import com.astralbrands.flight.x3.model.OrderDetails;
import com.astralbrands.flight.x3.model.OrderExportResponse;
import com.astralbrands.flight.x3.model.Transaction;
import com.astralbrands.flight.x3.util.AppConstants;

@Service
public class FlightOrderServices extends FlightAbstractService implements AppConstants {

	/*
	 * @Autowired private RestTemplate restTemplate;
	 */

	Logger log = LoggerFactory.getLogger(FlightOrderServices.class);

	private static final Map<String, String> SOLD_TO_MAP = new HashMap<>(5);
	private static final Map<String, String> COUNTRY_TO_CD_MAP = new HashMap<>(5);
	private SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	private DecimalFormat df = new DecimalFormat("0.00");

	static {
		SOLD_TO_MAP.put("US Direct to Corporate", "49999988"); // Warehouse ID from Flight 48
		SOLD_TO_MAP.put("US Corp Home Office Warehouse", "49999999"); // Warehouse ID from Flight 18
		SOLD_TO_MAP.put("Corp Warehouse CAD", "49999997"); // Warehouse ID from Flight 863
		SOLD_TO_MAP.put("Canada Direct to Corporate", "49999997"); // Warehouse ID from Flight 60
		SOLD_TO_MAP.put("Canada Direct to Corporate, Corp Warehouse CAD", "49999997");
		SOLD_TO_MAP.put("US Corp Home Office Warehouse, US Direct to Corporate", "49999999");
		COUNTRY_TO_CD_MAP.put("United States", US);
		COUNTRY_TO_CD_MAP.put("USA", US);
		COUNTRY_TO_CD_MAP.put("Canada", "CA");
		COUNTRY_TO_CD_MAP.put("CAN", "CA");
	}

	@Autowired
	private AccessTokenService restApiCallService;
	
	@Autowired
	private MailingService mailingService;

	@Autowired
	private FlightWebPageService flightWebPageService;

	@Autowired
	private SageX3Service sageX3Service;

	@Value("${flight.getDisplayIdUrl}")
	private String getDisplayIdUrl;

	@Value("${flight.search.criteria.url}")
	private String searchCriteriaUrl;

	@Value("${flight.getOrderShipmentsUrl}")
	private String orderShipmentsUrl;

	@Value("${flight.search.criteria.values}")
	private String searchValues;

	@Value("${flight.orders.detailsUrl}")
	private String orderDetailsUrl;

	@Value("${flight.orders.exportDetails}")
	private String exportDetailsUrl;

	public String webResults;

	public String email = "jschirm@astralbrands.com";

	public String uploadOrderDetailsToX3() {
		return uploadOrderDetailsToX3(null);
	}

	public String uploadOrderDetailsToX3(String orderId) {

		Instant start = Instant.now();
		Map<Integer, String> exportOrders = new HashMap<>();
		// To get the access token by invoking an API
		StringBuilder iFile = new StringBuilder(); // search ----For the Flight WebPage
		StringBuilder webFile2 = new StringBuilder();
		StringBuilder webFile3 = new StringBuilder();
		Map<Integer, String> flightWebMap = new HashMap<>();

		int c = 0;

		try {
			String accessToken = restApiCallService.getAccessToken();
			log.info("token >> " + accessToken);
			//orderId = "40703";
			Map<Integer, String> runIds = searchWareHouseId(accessToken, orderId, searchCriteriaUrl);    // To store all
			// th// available runIds and Warehouse names
			for (Map.Entry<Integer, String> entry : runIds.entrySet()) {
				getExportData(entry.getKey(), entry.getValue(), accessToken, exportOrders);
			}

			} catch (Exception m) {
				System.err.println("***********--EXCEPTION-FLIGHT-ORDER-SERVICE--***********" + "\n\r\n\r" + m.getMessage() +"\n\r\n\r");
				m.printStackTrace();
		}

//			c = webFile3.toString().trim().length();
////			f = s3.toString().trim().length();
//
//			EmailData emailData = EmailData.builder().build();
//
//			List<EmailAttachment> emailAttachmentList = new ArrayList<>();
//
//
//			if (c > 0) {
//
//					EmailAttachment attachment2 = EmailAttachment.builder().attachmentData(webFile3.toString())
//							.fileName("Flight-WEB-TestFile-WebFile-3-" + ApiUtil.getCurrentDateYYYYMMDD())
//							.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
//
//					emailAttachmentList.add(attachment2);
//
//			}
//				emailData.setAttachments(emailAttachmentList);
//				int g = emailAttachmentList.size();
//				int h = 0;
//				String j = "";
//				String l = "";
//			String k = "";
//			String y = "";
//			String t = "";
//			String w = "";
//				String emailBody = "<html> Hi Team, <br><br>" + "<b style='color:black;'>FLIGHT TRACKING FILE :</b><br><br>"
//						+ "<br><i style='color:black;'>"+ "#email1" + ".txt</i><br>"
//						+ "<br><i style='color:black;'>"+"#email2"+".txt</i><br>"
//						+ "<br><i style='color:black;'>"+"#email3"+".txt</i><br>"
////						+ "<br><i style='color:black;'>"+"#email4"+".txt</i><br>"
////						+ "<br><i style='color:black;'>"+"#email5"+".txt</i><br>"
////						+ "<br><i style='color:black;'>"+"#email6"+".txt</i><br>"
//						+ "<br> Regards,<br><br>" + "<br>Jacob Schirm </html>";  //attachment3

//				j = emailAttachmentList.get(0).getFileName();
//				emailBody.replace("#email1", j);
//			l = emailAttachmentList.get(1).getFileName();
//			emailBody.replace("#email2", l);
//			k = emailAttachmentList.get(2).getFileName();
//			emailBody.replace("#email3", k);
//			y = emailAttachmentList.get(3).getFileName();
//			emailBody.replace("#email4", y);
//			t = emailAttachmentList.get(4).getFileName();
//			emailBody.replace("#email5", t);
//			w = emailAttachmentList.get(5).getFileName();
//			emailBody.replace("#email6", w);

//			emailData = EmailData.builder().subject("******--FLIGHT TEST FILES--******")
//						.emailBody(emailBody)
//						//----attachment2--REMOVED TO PREVENT DOUBLE IMPORT OF TRACKING TO X3
//						.attachments(emailAttachmentList).build();
//
//				mailingService.sendMail(emailData);



			Instant finish = Instant.now();
			long timeElapsed = Duration.between(start, finish).toSeconds();
			log.info(" Total api execution times in Seconds :" + timeElapsed);
//			flightWebPageService.setTodaysFlightList(exportOrders);
		for (Map.Entry<Integer, String> entry : exportOrders.entrySet()) {
			iFile.append(entry.getValue());
		}

//		flightWebPageService.flightOrderList = flightWebPageService.getTodaysFlightOrders(exportOrders);
		System.out.println("i file data :" + iFile.toString());
		System.out.println("End of file");

//		flightWebPageService.setWebList2(iFile);
		flightWebPageService.setWebList2(iFile);
		return  iFile.toString();
	}

	private void getExportData(int runId, String wareHouseName, String tokenId, Map<Integer, String> orders) {

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(exportDetailsUrl)
				.queryParam("shippingFileRunID", "{shippingFileRunID}")
				.queryParam("fileExportConfigurationID", "{fileExportConfigurationID}").queryParam("asJSON", "{asJSON}")
				.encode().toUriString();

		Map<String, String> params = new HashMap<>();
		params.put("shippingFileRunID", runId + EMPTRY_STR);
		params.put("fileExportConfigurationID", "7");
		params.put("asJSON", "true");
		StringBuilder iFileData = new StringBuilder();
		OrderExportResponse[] response = call(urlTemplate, HttpMethod.GET, null, OrderExportResponse[].class,
				getHeader(tokenId), params);
		Map<String, List<OrderExportResponse>> orderNumberMap = Stream.of(response)
				.collect(Collectors.groupingBy(OrderExportResponse::getOrderNumber));
		for (Map.Entry<String, List<OrderExportResponse>> entry : orderNumberMap.entrySet()) {
			Map<String, String> taxOrders = getOrderLines(entry.getValue());
			Map<String, Object> paymentTerms = getPaymentTerms(entry.getKey(), tokenId);
			System.out.println("Payment terms found for order display id "+entry.getKey()+" is : "+paymentTerms.get("ADDRESS"));
			String header = getHeader(entry.getValue().get(0), wareHouseName, paymentTerms.get("ADDRESS"));
			iFileData.append(header).append(IFILE_DATA_SEPERATOR).append(taxOrders.get("shipingPrice"))
					.append(IFILE_DATA_SEPERATOR).append(taxOrders.get("discountPrice")).append(IFILE_DATA_SEPERATOR)
					.append(wareHouseName.contains(US) ? taxOrders.get("taxPrice") : EMPTRY_STR)
					.append(IFILE_DATA_SEPERATOR)
					.append(!wareHouseName.contains(US) ? taxOrders.get("taxPrice") : EMPTRY_STR)
					.append(IFILE_DATA_SEPERATOR).append(getDeliveryMode(wareHouseName)).append(IFILE_DATA_SEPERATOR)
					.append(getCarrier(wareHouseName)).append(IFILE_DATA_SEPERATOR)
					.append(paymentTerms.get("PAYMENT_TERM")).append(NEW_LINE)// append(LINE_SEPARATOR)//.append(NEW_LINE)
					.append(taxOrders.get("orderLine"));
		}
		orders.put(runId, iFileData.toString());
//		flightWebPageService.mapList.putAll(orders);
	}

	private Map<String, Object> getPaymentTerms(String orderDisplayId, String tokenId) {
		String urlTemplate = UriComponentsBuilder.fromHttpUrl(orderDetailsUrl)
				.queryParam(ORDER_DISPLAY_ID, "{orderDisplayId}").encode().toUriString();
		Map<String, String> params = new HashMap<>();
		params.put(ORDER_DISPLAY_ID, orderDisplayId);
		OrderDetails orderDetails = call(urlTemplate, HttpMethod.GET, null, OrderDetails.class, getHeader(tokenId),
				params);

		params = null;
		String paymentTerms = EMPTRY_STR;
		Map<String, Object> paymentTermsMap = new HashMap<String, Object>();
		if (orderDetails == null) {
			return paymentTermsMap;
		}
		BillingAddress billingAddress = null;
		try {
			for (Transaction transaction : orderDetails.getTransactions()) {
				if (transaction.getPaymentTypeIdentifier() != null
						&& !transaction.getPaymentTypeIdentifier().equals(EMPTRY_STR)) {
					paymentTerms = transaction.getPaymentTypeIdentifier().toString();
				}
				if (billingAddress == null && transaction.getBillingAddress() != null) {
					billingAddress = transaction.getBillingAddress();
					billingAddress.setFirstName(transaction.getNameOnCard());
					billingAddress.setLastName(EMPTRY_STR);
				}
			}
		} catch (Exception e) {
			paymentTerms = "WEB";
		}
		if (paymentTerms.equalsIgnoreCase("CC"))
			paymentTerms = "CREDITCARD";
		else if (paymentTerms.equalsIgnoreCase("paypal")) {
			paymentTerms = "PAYPAL";
		} else if (paymentTerms.equals(EMPTRY_STR)) {
			paymentTerms = "WEB";
		}
		paymentTermsMap.put("PAYMENT_TERM", paymentTerms);
		paymentTermsMap.put("ADDRESS", billingAddress);

		return paymentTermsMap;
	}


	private String getCarrier(String wareHouseName) {
		if (isUSSite(wareHouseName)) {
			return "USPS";
		} else {
			return "CPOST";
		}
	}

	private String getDeliveryMode(String wareHouseName) {
		if (isUSSite(wareHouseName)) {
			return "USPSP";
		} else {
			return "CPEPA";
		}
	}

	private Map<String, String> getOrderLines(List<OrderExportResponse> orderExportResponse) {

		StringBuilder sb = new StringBuilder();
		Map<String, String> taxOrders = new HashMap<>();
		updateGrossPriceForKits(orderExportResponse);
		double shippingPrice = 0.0;
		double discount = 0.0;
		double tax = 0.0;

		for (OrderExportResponse order : orderExportResponse) {
			shippingPrice = shippingPrice + order.getShipping();
			discount = discount + order.getDiscount();
			if (tax == 0.0) {
				tax = tax + order.getTax();
			}
			sb.append(getLines(order));
			sb.append(NEW_LINE); 
		}

		taxOrders.put("shipingPrice", String.valueOf(df.format(shippingPrice)));
		taxOrders.put("discountPrice", String.valueOf(df.format(discount)));
		taxOrders.put("taxPrice", String.valueOf(df.format(tax)));

		taxOrders.put("orderLine", sb.toString());
		return taxOrders;
	}

	// To allocate kit price equally to components
	private void updateGrossPriceForKits(List<OrderExportResponse> orderExportResponse) {
		System.out.println("Updating entries for order display Id : " + orderExportResponse.get(0).getOrderNumber());
		int len = orderExportResponse.size();
		int parentIndex = 0;
		for (int i = 0; i < len;) {
			double price = 0.0;
			if (orderExportResponse.get(i).getKitBuildableParentSKU() == null) {
				price = orderExportResponse.get(i).getGrossPrice();
				parentIndex = i;
				i++;
			}
			int count = 0;
			int eleCount = 0;
			while (i < len && Integer.valueOf(orderExportResponse.get(i).getKitBuildableIndex()) > 0) {
				if (orderExportResponse.get(i).getGrossPrice() == 0.0) {
					count++;
				}
				i++;
				eleCount++;
			}
			DecimalFormat kf = new DecimalFormat();
			kf.setMaximumFractionDigits(2);
			double divPrice;
			if (count > 0) {
				divPrice = price / count;
				divPrice = Double.parseDouble(kf.format(divPrice));
				boolean isFirstElement = true;
				for (int j = i - eleCount; j <= i; j++) {
					if (isFirstElement && j != count && j < len && orderExportResponse.get(j).getGrossPrice() == 0.0) {
						double remainingAmnt = Double.valueOf(kf.format(price - (divPrice * (count - 1)))); // ;
						orderExportResponse.get(j).setGrossPrice(remainingAmnt);
						isFirstElement = false;
					} else if (j < len && orderExportResponse.get(j).getGrossPrice() == 0.0) {
						orderExportResponse.get(j).setGrossPrice(divPrice);
					}
				}
				orderExportResponse.get(parentIndex).setGrossPrice(0);
			}

		}
	}

	// if - statement to check for ' BGO ' Kits with Parent SKU of BGO/BOGO
	//
	// if(order.getKitBuildableParentSKU() != null) {
	//			parentSku = order.getKitBuildableParentSKU();
	//		} else {
	//			parentSku = null;
	//		}
	//
	// if (parentSku != null && (parentSku.startsWith(EIGHT) && parentSku.contains("BGO"))) {
	//			parentSku = "09739";
	//			sj.add(parentSku);
	//		} else {
	//			sj.add(updateSku(skuId));
	//		}
	private String getLines(OrderExportResponse order) {
		StringJoiner sj = new StringJoiner(IFILE_DATA_SEPERATOR);

		String skuId = order.getProduct();
		String parentSku = "";
		String skuId2 = skuId;
//		parentSku = order.getKitBuildableParentSKU();


//		skuId2 = updateSku(skuId);
//		if(skuId2.matches("^[a-zA-Z]*$")) {
//			skuId2.replaceAll("^[a-zA-Z]*$", "");
//		else if(skuId.contains("BGO") || skuId.contains("bgo")) {
//			sj.add(skuId + SPACE + order.getProductName());
//		}
//		}

//		skuId2 = updateSku(skuId2);
//		if (skuId2.matches("[a-zA-Z]")) {
//			skuId2 = skuId2.replaceAll("[a-zA-Z]", "");
//		} else {
//			skuId2 = skuId2;
//		}
		sj.add("L");
		sj.add(updateSku(skuId2));
		if (order.getKitBuildableParentSKU() != null) {
			sj.add(order.getKitBuildableParentSKU() + SPACE + order.getProductName());
		} else if (checkSKU(skuId)) {
			sj.add(skuId + SPACE + order.getProductName());
		} else {
			sj.add(order.getProductName());
		}
		sj.add("EA");
		sj.add(String.valueOf(order.getQuantity()));
		sj.add(String.valueOf(order.getGrossPrice()));
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		return sj.toString();
	}

	private String getHeader(OrderExportResponse orderExportResponse, String wareHouseName, Object billingAdd) {
		StringJoiner sj = new StringJoiner(IFILE_DATA_SEPERATOR);
		boolean isUsSite = isUSSite(wareHouseName);
		sj.add("E");
		sj.add(getSalesSite(isUsSite));//
		sj.add(getOrderType(isUsSite));
		sj.add(orderExportResponse.getOrderNumber());
		if(orderExportResponse.getOrderNumber() == null) {
			sj.add("WH-NAME-CHANGED");
		} else {
			sj.add(SOLD_TO_MAP.get(wareHouseName));
		}
		sj.add(getDateInYYYYMMDD(orderExportResponse.getOrderDate()));
		sj.add(orderExportResponse.getShipmentId());
		sj.add(getShippingSite(isUsSite));// Shipping site
		sj.add(getCurrency(isUsSite));
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		sj.add(EMPTRY_STR);
		//System.out.println("header billid ADD  Obj :" + billingAdd);
		String biilingAddStr = billingAdd != null ? getAddress((BillingAddress) billingAdd)
				: getAddress(orderExportResponse);
		//System.out.println("header billid ADD :" + biilingAddStr);
		sj.add(biilingAddStr); // Billing
								// address
		sj.add(getAddress(orderExportResponse)); // Shipping order Address
		return sj.toString();

	}

	private String getAddress(OrderExportResponse orderExportResponse) {
		StringJoiner sj = new StringJoiner(IFILE_DATA_SEPERATOR);
		sj.add(orderExportResponse.getShipFirstName()+" "+orderExportResponse.getShipLastName());
		sj.add(EMPTRY_STR);
		sj.add(getStrValue(orderExportResponse.getShipAddr1()));
		sj.add(getStrValue(orderExportResponse.getShipAddr2()));
		sj.add(getStrValue(orderExportResponse.getShipPostalCode()));
		sj.add(getStrValue(orderExportResponse.getShipCity()));
		sj.add(getStrValue(orderExportResponse.getShipState()));
		sj.add(getStrValue(orderExportResponse.getShipCountry()));
		return sj.toString();
	}

	private String getAddress(BillingAddress billingAddress) {
		StringJoiner sj = new StringJoiner(IFILE_DATA_SEPERATOR);
		sj.add(getStrValue(billingAddress.getFirstName()));
		sj.add(getStrValue(billingAddress.getLastName()));
		sj.add(getStrValue(billingAddress.getStreet1()));
		sj.add(getStrValue(billingAddress.getStreet2()));
		sj.add(getStrValue(billingAddress.getPostalCode()));
		sj.add(getStrValue(billingAddress.getCity()));
		sj.add(getStrValue(billingAddress.getProvinceAbbreviation()));
		sj.add(getCountryCode(billingAddress.getCountryName()));
		return sj.toString();
	}

	private String getStrValue(Object value) {
		return value != null ? value.toString() : EMPTRY_STR;
	}

	public String getDateInYYYYMMDD(Date date) {
		return inputFormat.format(date);
	}

	private String getCurrency(boolean isUsSite) {
		if (isUsSite) {
			return USD;
		} else {
			return CAD;
		}
	}

	private String getCountryCode(Object countryName) {
		return countryName != null ? COUNTRY_TO_CD_MAP.get(countryName.toString()) : EMPTRY_STR;
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
	private String getShippingSite(boolean isUsSite) {
		if (isUsSite) {
			return US_SITE;
		} else {
			return CA_SHIPPING_SITE;
		}
	}

	private String getOrderType(boolean isUsSite) {
		if (isUsSite) {
			return "AWEB";
		} else {
			return "AWEBC";
		}
	}

	// To get runIds for Bussiness Units: Aloette Corporate and US Direct to Deposit
	// from Flight
	/*
	 * public Map<Integer, String> searchWareHouseId(String accessToken, String
	 * runId) { Map<Integer, String> runIds = new HashMap<>(); String request =
	 * runId == null ? ApiUtil.getWareHouseIdSearchReq() :
	 * ApiUtil.getWareHouseIdSearchReqByRunId(runId); SearchCriteriaResponse
	 * criteriaResult = call(searchCriteriaUrl, HttpMethod.POST, request,
	 * SearchCriteriaResponse.class, getHeader(accessToken));
	 * 
	 * if (criteriaResult != null && criteriaResult.getResults() != null) { for
	 * (SearchCriteriaResult res : criteriaResult.getResults()) { if
	 * (res.getOrderCount() > 0) { runIds.put(res.getShippingFileRunID(),
	 * res.getWarehouses()); } } } return runIds; }
	 */

	private double getTwoDigitPrice(double price) {
		return Double.valueOf(df.format(price));
	}

	private boolean checkSKU(String skuId) {
		return (!skuId.startsWith(EIGHT)   || skuId.matches("^[a-zA-Z0-9]*$"));
	}

	private String updateSku(String skuId) {

		// || ((skuId.startsWith(EIGHT) && skuId.matches("^[a-zA-Z0-9]*$")))

		if ((!skuId.startsWith(EIGHT) && !skuId.startsWith(NINE))) {
			 return "09739";
		} else if(skuId.startsWith(EIGHT) || skuId.startsWith(NINE) ) {                 //|| skuId.matches("^[a-zA-Z]*$")
			return skuId.replaceAll("[a-zA-Z]", "");
		} else {
			return skuId;
		}
	}

	//		} else if (skuId.startsWith(EIGHT) || skuId.startsWith(NINE)) {
//				return skuId.replaceAll("^[a-zA-Z]*$", "");
//		}

	// Duplicated
	/*
	 * private HttpHeaders getHeader(String token) { HttpHeaders headers = new
	 * HttpHeaders(); headers.setBearerAuth(token);
	 * headers.setContentType(MediaType.APPLICATION_JSON); return headers; }
	 */

}
