package com.astralbrands.flight.x3.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.astralbrands.flight.x3.model.EmailAttachment;
import com.astralbrands.flight.x3.model.EmailData;
import com.astralbrands.flight.x3.model.Group;
import com.astralbrands.flight.x3.model.GroupField;
import com.astralbrands.flight.x3.util.ApiUtil;
import com.astralbrands.flight.x3.util.AppConstants;
import com.astralbrands.flight.x3.util.FileUtils;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


@Service
@Component
public class SageX3Service implements AppConstants {

	public static final String UPLOAD_IFILE_TO_X3_REQ = "upload-ifile-to-x3-request.xml";
	public static final String GET_X3_INTEGRATION_STATUS = "get-x3-integration-status-request.xml";
	private static final Set<String> INTEGRATION_SUCCESS_STATUS = new HashSet<>();
	
	Logger log = LoggerFactory.getLogger(SageX3Service.class);

	static {
		INTEGRATION_SUCCESS_STATUS.add("Finished");
		INTEGRATION_SUCCESS_STATUS.add("FINISHED");
		INTEGRATION_SUCCESS_STATUS.add("ERROR");
		INTEGRATION_SUCCESS_STATUS.add("Error");
	}

	@Value("${sag.x3.url}")
	private String sageX3Url;

	@Value("${sag.x3.api.retry.count}")
	private int retryCount;

	@Value("${sag.x3.api.retry.delay}")
	private int retryDelay;

	@Value("${sag.network.localPath}")
	private String sagLocalPath;

	@Autowired
	private MailingService mailingService;

//	@Autowired
//	HttpClientService httpService;

//	@Autowired
//	private NetworkFileService networkFileService;

//	public boolean uploadIfileToX3(String iFile, String importType) {
////		String res1 = iFile.replace("|END", NEW_LINE);
////		res1 = res1.replace(LINE_SEPARATOR, NEW_LINE);
//		// System.out.println("IFile sent in email is : "+res1.replace(LINE_SEPARATOR,
//		// NEW_LINE));
//		String filePath = networkFileService.upload(iFile);
//		System.out.println("File Name : " + filePath);
//		String result = httpService.call(sageX3Url, getUploadIfileToX3Req(filePath, importType));
//		System.out.println("Result from SOAP API is : " + result);
//
//		String traNumber = getTraNumber(result);
//		System.out.println("Reqnum is : " + traNumber);
//		if (traNumber == null) {
//			EmailAttachment attachment = EmailAttachment.builder().attachmentData(iFile).fileName("IFILE")
//					.fileType(TXT_FILE_EXTENSION).mimetype("text/plain").build();
//			EmailData emailData = EmailData.builder().subject("X3 Import Failure")
//					.emailBody("Failed to upload iflie to X3").attachments(List.of(attachment)).build();
//			log.info("***************************First api call failed email sent");
//			mailingService.sendEmailWithAttachment(emailData);
//			return false;
//		} else {
//			String traFileMsg = networkFileService.readFile(traNumber);
//			EmailAttachment attachment = EmailAttachment.builder().attachmentData(traFileMsg)
//					.fileName("ifileUploaderLog").fileType(".log").mimetype("text/plain").build();
//			EmailData emailData;
//			boolean isSuccess = false;
//			if (traFileMsg.contains("error")) {
//				EmailAttachment ifile = EmailAttachment.builder().attachmentData(iFile)
//						.fileName(importType + ApiUtil.getCurrentDateYYYYMMDD()).fileType(TXT_FILE_EXTENSION).mimetype("text/plain")
//						.build();
//				emailData = EmailData.builder().subject("X3 Import Failure").emailBody("Failed to upload iflie to X3")
//						.subject("Failed to upload iflie to X3!!").attachments(List.of(ifile, attachment)).build();
//				isSuccess = false;
//			} else {
//				emailData = EmailData.builder().subject("X3 Import Failure")
//						.emailBody("iFile uploaded successfully to x3").subject("iFile uploaded successfully to x3!!")
//						.attachments(List.of(attachment)).build();
//				isSuccess = true;
//			}
//			System.out.println("***************************First api call failed email sent");
//			mailingService.sendEmailWithAttachment(emailData);
//			return isSuccess;
//		}
//	}

	private String getTraNumber(String response) {
		// <FLD NAME="O_REQNUM" TYPE="Integer">1302038</FLD>
		Group result = getGroup(response);
		System.out.println("result string :" + result);
		return getFieldValue(result, "O_TRA");
	}

	private String getFieldValue(Group result, String field) {
		if (result != null) {
			for (GroupField fld : result.getFld()) {
				if (fld.getName().equals(field)) {
					return fld.getValue();
				}
			}
		}
		return null;
	}

	private Group getGroup(String resultRes) {
		String resultStr = getResultString(resultRes);
		XmlMapper mapper = new XmlMapper();
		try {
			Group result = mapper.readValue(resultStr.getBytes(), Group.class);
			return result;
		} catch (Exception e) {
			System.err.println("Error msg response : " + e.getMessage());
		}
		return null;
	}

	private void waitToProcessIFile(int sec) {
		try {
			wait(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	public synchronized boolean checkIntegrationStatus(String reqNumber, String iFile) {
//		String x3IntegReq = getX3IntegrationStatusReq(reqNumber);
//		waitToProcessIFile(retryDelay);
//		int retryCounter = retryCount;
//		String status = null;
//		while (retryCounter >= 0) {
//			String result = httpService.call(sageX3Url, x3IntegReq);
//			System.out.println("Result from integration status is : " + result.toString());
//			Group group = getGroup(result);
//			if (group == null) {
//				retryCounter--;
//				waitToProcessIFile(retryDelay);
//			}
//			status = getFieldValue(group, "O_MESREQ");
//			result = null;
//			System.out.println("Status message receied : " + status);
//			if (INTEGRATION_SUCCESS_STATUS.contains(status)) {
//				String message = getFieldValue(group, "O_FILETRA");
//				if ("ERROR".equals(status.toUpperCase())) {
//					EmailAttachment attachment = EmailAttachment.builder().attachmentData(message)
//							.fileName("log_detail").fileType("log").mimetype("text/plain").build();
//					EmailAttachment attachmentIFile = EmailAttachment.builder().attachmentData(iFile).fileName("IFILE")
//							.mimetype("text/plain").fileType(TXT_FILE_EXTENSION).build();
//					EmailData emailData = EmailData.builder().subject("Flight import errored out")
//							.emailBody("Please find the error log and IFile attached.")
//							.attachments(List.of(attachment, attachmentIFile)).build();
//					System.out.println("Integration status message received : " + message);
//					mailingService.sendEmailWithAttachment(emailData);
//				} else if ("FINISHED".equals(status.toUpperCase())) {
//					// save the error log into text file and send it through email.
//					EmailAttachment attachmentIFile = EmailAttachment.builder().attachmentData(iFile).fileName("IFILE")
//							.mimetype("text/plain").fileType(TXT_FILE_EXTENSION).build();
//					EmailData emailData = EmailData.builder().subject("Flight import was successfull")
//							.emailBody("Please find the imported IFile attached.").attachments(List.of(attachmentIFile))
//							.build();
//					mailingService.sendEmailWithAttachment(emailData);
//					return true;
//				}
//				break;
//
//			} else {
//				retryCounter--;
//				waitToProcessIFile(retryDelay);
//			}
//
//		}
//		return false;
//
//	}

	private String getResultString(String response) {
		if (response == null || !response.contains("<RESULT>")) {
			return "";
		}
		return response.substring(response.indexOf("<RESULT>") + 8, response.indexOf("</RESULT>"));
	}

	private String getUploadIfileToX3Req(String iFile, String importType) {
		String fileName = sagLocalPath + FORWARD_SLASH + iFile;
		fileName = fileName.replaceAll(FORWARD_SLASH, "\\\\\\\\");
		String template = FileUtils.readFile(UPLOAD_IFILE_TO_X3_REQ);
		template = template.replace("#I_FILE#", fileName);
		if (importType.equals("orders")) {
			template = template.replace("#TEMPLATE_NAME#", "YNSOHF");
		} else {
			template = template.replace("#TEMPLATE_NAME#", "ZPAYSHOP");
		}
		System.out.println("Template is : " + template);
		return template;
	}

	private String getX3IntegrationStatusReq(String reqNumber) {
		String template = FileUtils.readFile(GET_X3_INTEGRATION_STATUS);
		template = template.replace("#REQNUM#", reqNumber);
		return template;
	}

}
