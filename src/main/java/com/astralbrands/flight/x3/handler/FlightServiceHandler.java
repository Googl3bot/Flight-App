package com.astralbrands.flight.x3.handler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.astralbrands.flight.x3.model.TodaysOrders;
import com.astralbrands.flight.x3.model.Tracking;
import com.astralbrands.flight.x3.service.*;
import com.astralbrands.flight.x3.util.TrackingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.astralbrands.flight.x3.model.EmailAttachment;
import com.astralbrands.flight.x3.model.EmailData;
import com.astralbrands.flight.x3.util.ApiUtil;
import com.astralbrands.flight.x3.util.TrackingUtil;
import com.astralbrands.flight.x3.util.AppConstants;
import com.sun.mail.imap.AppendUID;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FlightServiceHandler {

	@Autowired
	FlightOrderServices flightOrderServices;

	@Autowired
	FlightWebPageService flightWebPageService;

	@Autowired
	FlightPaymentService flightPaymentService;

	@Autowired
	TrackingService trackingService;

	@Autowired
	TrackingUtil trackingUtil;

	@Autowired
	MailingService mailingService;

	public List<Object> dailyOrderList;



//	@Autowired
//	FlightWebPageService flightWebPageService;

//	@Value("${export.flight.payments}")
//	public static String exportPaymentPath;
//	// EXPORT PATH for the FLIGHT OPEN ORDERS
//	@Value("${export.flight.orders}")
//	public static String exportOrdersPath;
//	// EXPORT PATH for TRACKING #'s for the ORDERS
//	@Value("${export.flight.tracking}")
//	public static String exportTrackingPath;
	//// ONE I-FILE IN DIRECTORY GETS OVERWRITTEN EVERYDAY
////	@Value("${ifile.payments}")
////	public static String payIFile;
////	// ONE I-FILE IN DIRECTORY GETS OVERWRITTEN EVERYDAY
////	@Value("${ifile.orders}")
////	public static String ordersIFile;
//	// ONE I-FILE IN DIRECTORY GETS OVERWRITTEN EVERYDAY
//	@Value("${ifile.tracking}")
//	public static String trackIFile;
//	// In case the first path doesn't work
//	@Value("${export.orders.path2}")
//	public static String ordersPath2;
//	// In case first payments path doesn't work
//	@Value("${export.payments.path2}")
//	public static String payPath2;
//	// In case first TRACKING path doesn't work
//	@Value("${export.tracking.path2}")
//	public static String trackingPath2;

	/**
	 * Execute flow with runid from REST API
	 * 
	 * @param orderId
	 */
	public void handle(String orderId) {
		StringBuilder ifile = new StringBuilder(flightOrderServices.uploadOrderDetailsToX3(orderId));
		String orderList = flightWebPageService.extractOrders(ifile);
//		 TrackingUtil.setDailyOrders(ifile);
		log.info("Order details are integrated to X3 with status " + ifile + " for orderId :" + orderId);
		if (ifile.length() > 1) {
			String paymentIFile = flightPaymentService.uploadPaymentDetailsToX3(orderId);
			List<Object> lisdt = Arrays.stream(trackingService.importX3ToFlight().lines().toArray()).collect(Collectors.toList());

		//-----------GET-TRACKING-RESPONSE-DETAILS-THEN-EMAIL---------------\\
//			String trackingResponse = trackingUtil.getTrackingInfo();  ----PRINTS-EXCEPTION-COUNT
			String trackingResponseTwo = trackingUtil.getTrackingInfoTwo();
			String trackingResponseThree = trackingUtil.getResponseDetails();

//			EmailAttachment trackingAttachmentOne = EmailAttachment.builder().attachmentData(trackingResponse)
//					.fileName("Flight_trackRespOne_" + ApiUtil.getCurrentDateYYYYMMDD())
//					.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
			EmailAttachment trackingAttachmentTwo = EmailAttachment.builder().attachmentData(trackingResponseTwo)
					.fileName("Flight_trackRespTwo_" + ApiUtil.getCurrentDateYYYYMMDD())
					.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
			EmailAttachment trackingAttachmentThree = EmailAttachment.builder().attachmentData(trackingResponseThree)
					.fileName("Flight_trackRespThree_" + ApiUtil.getCurrentDateYYYYMMDD())
					.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();


			EmailAttachment attachment = EmailAttachment.builder().attachmentData(ifile.toString())
					.fileName("Flight_Order_" + ApiUtil.getCurrentDateYYYYMMDD())
					.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
			EmailAttachment attachment1 = EmailAttachment.builder().attachmentData(paymentIFile.toString())
					.fileName("Flight_payments_" + ApiUtil.getCurrentDateYYYYMMDD())
					.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
			EmailAttachment attachment2 = EmailAttachment.builder().attachmentData(lisdt.toString())
					.fileName("Flight_tracking_" + ApiUtil.getCurrentDateYYYYMMDD())
					.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
			//----attachment2--REMOVED TO PREVENT DOUBLE IMPORT OF TRACKING TO X3
//			EmailAttachment attachmen3t2 = EmailAttachment.builder().attachmentData(trackingIFile.toString())
//					.fileName("Flight_Tracking_" + ApiUtil.getCurrentDateYYYYMMDD())
//					.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
			String emailBody = "<html> Hi Team, <br>" + "<br><b style='color:black;'>FLIGHT IMPORT FILE :</b><br>"
					+ "<br><i style='color:black;'>"+attachment.getFileName().toString()+".txt</i><br>"
					+ "<br><i style='color:black;'>"+attachment1.getFileName().toString()+".txt</i><br>"
//					+ "<br><i style='color:black;'>"+attachment2.getFileName().toString()+".txt</i><br>"
//					+ "<br><i style='color:black;'>"+attachment3.getFileName().toString()+".txt</i><br>"
					  + "<br> Regards,<br>" + "<br>Jacob Schirm </html>";  //attachment3
			String emailBodyTwo = "<html> Hi Team, <br>" + "<br><b style='color:black;'>FLIGHT TRACKING FILE :</b><br>"
					+ "<br><i style='color:black;'>"+attachment2.getFileName().toString()+".txt</i><br>"
//					+ "<br><i style='color:black;'>"+trackingAttachmentOne.getFileName().toString()+".txt</i><br>"
					+ "<br><i style='color:black;'>"+trackingAttachmentTwo.getFileName().toString()+".txt</i><br>"
					+ "<br><i style='color:black;'>"+trackingAttachmentThree.getFileName().toString()+".txt</i><br><br>"
					+ "<br> Regards,<br>" + "<br>Jacob Schirm </html>";
			EmailData emailData = EmailData.builder().subject("******--FLIGHT IMPORT FILES--******")
					.emailBody(emailBody)
					//----attachment2--REMOVED TO PREVENT DOUBLE IMPORT OF TRACKING TO X3
					.attachments(List.of(attachment, attachment1)).build(); //attachment3)).build();    attachment2
			EmailData emailData1 = EmailData.builder().subject("*************--FLIGHT-TRACKING-INFO--*************")
							.emailBody(emailBodyTwo).attachments(List.of(attachment2,
							trackingAttachmentTwo, trackingAttachmentThree)).build();
			EmailData emailData2 = EmailData.builder().subject("*************--FLIGHT-TRACKING-INFO--*************")
					.emailBody(emailBodyTwo).attachments(List.of(attachment2)).build();
			log.info("***************************First api call failed email sent");
			//----SENDS-THE-TRACKING-INFO-&-RESPONSE-BY-EMAIL----
			mailingService.sendEmailWithAttachment(emailData);
		    //----SENDS-THE-TRACKING-INFO-&-RESPONSE-BY-EMAIL----
//			mailingService.sendEmailTrackingAttachment(emailData1);


			//--EmailData emailData = EmailData.builder().subject("*******--Import file for FLIGHT orders--******")
			//					.emailBody("Please find the import file attached for flight orders : \n\r" + attachment.getFileName().toString() +
			//							"\n\r" + attachment1.getFileName().toString() + "\n\r" + attachment2.getFileName().toString() +
			//							"\n\r" + attachment3.getFileName().toString() + "\n\r" +
			//							"\n\r\n\r Regards, \n\r" + "Jacob Schirm")
			//					.attachments(List.of(attachment, attachment1, attachment2, attachment3)).build();--\\

			// TRY & IMPLEMENT A LOCAL DIRECTORY PATH FOR THE OUTPUT FILES
//			Path ordersPath = Paths.get(ordersIFile);
//			Path paymentsPath = Paths.get(payIFile);
////			Path trackingPath = Paths.get(trackIFile);
//			// DIFFERENT LOCAL FILE PATH IN CASE OF EXCEPTION
////			Path orders = Paths.get("C:/Users/jschirm/payweb-csv/FLIGHT/FLIGHT_ORDERS/");
////			Path payments = Paths.get("");
//			try {
//				Files.writeString(ordersPath, ordersIFile);
//				Files.writeString(paymentsPath, payIFile);
////				Files.writeString(trackingPath, trackIFile);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}		// IN CASE FILE PATHS DON'T WORK

	/**
	 * Execute flow with runid from REST API
	 *
	 * @param orderId
	 */
	public void handleWebOrderList(String orderId) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
//			List<TodaysOrders> todaysOrders = flightWebPageService.getTodaysOrders();
//			for(TodaysOrders orders : todaysOrders) {
//				if(orders.getFlag().equals("E")) {
//					stringBuilder.append(orders.getFlag());
//					stringBuilder.append(orders.getSalesSite());
//					stringBuilder.append(orders.getOrderType());
//					stringBuilder.append(orders.getOrderNumber());
//					stringBuilder.append(orders.getWarehouseName());
//					stringBuilder.append(orders.getShipmentId());
//					stringBuilder.append(orders.getShippingSite());
//					stringBuilder.append(orders.getCurrency());
//					stringBuilder.append(orders.getCustomerName());
//					stringBuilder.append(orders.getBillingAddress());
//					stringBuilder.append(orders.getShippingAddress());
//					stringBuilder.append(orders.getShippingFee());
//					stringBuilder.append(orders.getDiscountPrice());
//					stringBuilder.append(orders.getTaxFee());
//					stringBuilder.append(orders.getPaymentType());
//				} else if(orders.getFlag().equals("L")) {
//					stringBuilder.append(orders.getFlag());
//					stringBuilder.append(orders.getSkuId());
//					stringBuilder.append(orders.getBuildableSkuId());
//					stringBuilder.append(orders.getProductName());
//					stringBuilder.append(orders.getProductType());
//					stringBuilder.append(orders.getQuantity());
//					stringBuilder.append(orders.getProductPrice());
//				} else {
//					stringBuilder.append("----------------****--MODIFY-HANDLER-FUNCTION--****----------------");

//				flightWebPageService.getWebList(stringBuilder.toString());

				StringBuilder orders = new StringBuilder(); //----TrackingUtil.getDailyOrders()
				String ordersString = orders.toString();

				EmailAttachment emailAttachment = EmailAttachment.builder().attachmentData(stringBuilder.toString())
						.fileName("Flight-Orders-WEB-" + ApiUtil.getCurrentDateYYYYMMDD())
						.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
				EmailAttachment emailAttachment2 = EmailAttachment.builder().attachmentData(stringBuilder.toString())
						.fileName("Flight-Orders-WEB-CSV-" + ApiUtil.getCurrentDateYYYYMMDD())
						.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/csv").build();
				String body = "<html> Hi Team, <br>" + "<br><b style='color:black;'>FLIGHT IMPORT FILE :</b><br>"
						+ "<br><i style='color:black;'>"+emailAttachment.getFileName().toString()+".txt</i><br>"
						+ "<br><i style='color:black;'>"+emailAttachment2.getFileName().toString()+".txt</i><br>"
//					+ "<br><i style='color:black;'>"+attachment2.getFileName().toString()+".txt</i><br>"
//					+ "<br><i style='color:black;'>"+attachment3.getFileName().toString()+".txt</i><br>"
						+ "<br> Regards,<br>" + "<br>Jacob Schirm </html>";
				EmailData emailData2 = EmailData.builder().subject("---------**-Flight WEB Order List-**---------")
						.emailBody(body).attachments(List.of(emailAttachment, emailAttachment2)).build();
				System.err.println("---------------TODAY'S FLIGHT WEB ORDERS SENT----------------");

				log.info("***************************First api call failed email sent");
				mailingService.sendMail(emailData2);

			} catch (Exception e) {
			e.printStackTrace();
			System.err.println("PROJECT STRUCTURE PROBLEM" + e.getMessage());
		}
	}

	public void handleTracking() {
		List<Tracking> trackFile = new ArrayList<>(trackingService.getTrackingDataInCsv());
		ListIterator<Tracking> listIterator = trackFile.listIterator();
		StringBuilder stringBuilder = new StringBuilder();
//			String trackingIFile = trackingService.importX3ToFlight();
		while(listIterator.hasNext()) {
			stringBuilder.append(listIterator.next().toString());
		}
		StringJoiner sb = new StringJoiner("~");
		sb.add(stringBuilder);

		EmailAttachment attachment = EmailAttachment.builder().attachmentData(sb.toString())
				.fileName("Flight_TrackingList_" + ApiUtil.getCurrentDateYYYYMMDD())
				.fileType(AppConstants.TXT_FILE_EXTENSION).mimetype("text/plain").build();
		String emailBody = "<html> Hi Team, <br>" + "<br><b style='color:black;'>FLIGHT TRACKING LIST:</b><br>" +
				  "<br><i style='color:black;'>"+attachment.getFileName().toString()+".txt</i><br>" +
				  "<br> Regards,<br>" + "<br>Jacob Schirm </html>";
		EmailData emailData = EmailData.builder().subject("******--FLIGHT TRACKING RECORDS FILE--******")
				.emailBody(emailBody)
				//----attachment2--REMOVED TO PREVENT DOUBLE IMPORT OF TRACKING TO X3
				.attachments(List.of(attachment)).build();
		log.info("***************************First api call failed email sent");
		mailingService.sendEmailWithAttachment(emailData);
	}

	/**
	 * Execute the flow without runid from scheduler
	 */
	public void handle() {
		handle(null);
	}

}
