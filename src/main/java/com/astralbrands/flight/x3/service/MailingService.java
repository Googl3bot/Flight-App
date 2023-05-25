package com.astralbrands.flight.x3.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.annotation.PostConstruct;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.astralbrands.flight.x3.model.EmailAttachment;
import com.astralbrands.flight.x3.model.EmailData;
import com.astralbrands.flight.x3.util.AppConstants;

@Component
public class MailingService implements AppConstants {
	Logger log = LoggerFactory.getLogger(MailingService.class);

	@Value("${smtp.host}")
	private String host;
	@Value("${smtp.port}")
	private String port;
	@Value("${smtp.username}")
	private String userName;
	@Value("${smtp.password}")
	private String password;
	@Value("${smtp.from}")
	private String from;
	@Value("${smtp.to}")
	private String toList;
	
	JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

	@PostConstruct
	public void init() {
		javaMailSender.setHost(host);
		javaMailSender.setPort(587);

		javaMailSender.setUsername(userName);
		javaMailSender.setPassword(password);

		Properties props = javaMailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtpClient.EnableSsl", "false");
		props.put("mail.debug", "true");
	}

	public void sendMailWithAttachment(EmailData emailData, String emailBody) {

		log.info("sending .........");

		MimeMessage message = javaMailSender.createMimeMessage();
		try {

			if (toList != null && toList.length() > 0 && toList.contains(SEMI_COMMA)) {
				String[] toAdd = toList.split(SEMI_COMMA);
				for (String to : toAdd) {
					message.addRecipient(RecipientType.TO, new InternetAddress(to));
				}
			} else {
				message.addRecipient(RecipientType.TO, new InternetAddress(toList));
			}

//			String jacobEmail = "jschirm@astralbrands.com";
//			message.addRecipient(RecipientType.TO, new InternetAddress(jacobEmail));

			message.setFrom(from);
			message.setSubject(emailData.getSubject());
			BodyPart msgBody = new MimeBodyPart();

			msgBody.setContent(emailBody, "text/html");

			Multipart multiPart = new MimeMultipart();
			MimeBodyPart attachFilePart = new MimeBodyPart();
			attachFilePart.setDataHandler(new DataHandler(
					new ByteArrayDataSource(emailData.getAttachments().toString().replaceAll(IFILE_DATA_SEPERATOR, COMMA).getBytes(), "text/csv")));
			attachFilePart.setFileName(emailData.getAttachments().toString() + ".csv");

			MimeBodyPart iFileBody = new MimeBodyPart();
			iFileBody.setDataHandler(new DataHandler(new ByteArrayDataSource(emailData.getAttachments().toString().getBytes(), "text/plain")));
			iFileBody.setFileName(emailData.getAttachments().toString() + ".txt");
			multiPart.addBodyPart(iFileBody);
			multiPart.addBodyPart(attachFilePart);
			multiPart.addBodyPart(msgBody);
			message.setContent(multiPart);

		} catch (Exception e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
		System.out.println("email send with subject : " + emailData.getSubject().toString());

	}

	public void sendEmailTrackingAttachment(EmailData emailData) {

		log.info("sending .........");

		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			message.addRecipient(RecipientType.TO, new InternetAddress("jschirm@astralbrands.com"));
			message.setFrom(from);
			message.setSubject("**********----TRACKING INFO----***********");
			BodyPart msgBody = new MimeBodyPart();
			msgBody.setText(emailData.getEmailBody());
			msgBody.setContent(emailData.getEmailBody(), "text/html");

			Multipart multiPart = new MimeMultipart();

			for (EmailAttachment attachment : emailData.getAttachments()) {
				MimeBodyPart iFileBody = new MimeBodyPart();
				iFileBody.setDataHandler(new DataHandler(
						new ByteArrayDataSource(attachment.getAttachmentData().getBytes(), attachment.getMimetype())));
				iFileBody.setFileName(attachment.getFileName() + attachment.getFileType());
				multiPart.addBodyPart(iFileBody);
			}
			multiPart.addBodyPart(msgBody);
			message.setContent(multiPart);

		} catch (

				Exception e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
//		System.out.println("email send with subject : " + subject);
	}

	public void sendEmailWithAttachment(EmailData emailData) {

		log.info("sending .........");

		MimeMessage message = javaMailSender.createMimeMessage();
		try {

			if (toList != null && toList.length() > 0 && toList.contains(SEMI_COMMA)) {
				String[] toAdd = toList.split(SEMI_COMMA);
				for (String to : toAdd) {
					message.addRecipient(RecipientType.TO, new InternetAddress(to));
				}
			} else {
				message.addRecipient(RecipientType.TO, new InternetAddress(toList));
			}

			message.setFrom(from);
			message.setSubject(emailData.getSubject());
			BodyPart msgBody = new MimeBodyPart();
			msgBody.setText(emailData.getEmailBody());
			msgBody.setContent(emailData.getEmailBody(), "text/html");

			Multipart multiPart = new MimeMultipart();

			for (EmailAttachment attachment : emailData.getAttachments()) {
				MimeBodyPart iFileBody = new MimeBodyPart();
				iFileBody.setDataHandler(new DataHandler(
						new ByteArrayDataSource(attachment.getAttachmentData().getBytes(), attachment.getMimetype())));
				iFileBody.setFileName(attachment.getFileName() + attachment.getFileType());
				multiPart.addBodyPart(iFileBody);
			}
			multiPart.addBodyPart(msgBody);
			message.setContent(multiPart);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
		 javaMailSender.send(message);
//		System.out.println("email send with subject : " + subject);
	}

	public void sendMail(EmailData emailData) {

		MimeMessage msg = javaMailSender.createMimeMessage();
		Multipart multiPart = new MimeMultipart();
		try {
			msg.setFrom(from);
			BodyPart msgBody = new MimeBodyPart();
			msgBody.setText(emailData.getEmailBody());
			msgBody.setContent(emailData.getEmailBody(), "text/html");
			msg.addRecipient(RecipientType.TO, new InternetAddress("jschirm@astralbrands.com"));
			for (EmailAttachment attachment : emailData.getAttachments()) {
				MimeBodyPart iFileBody = new MimeBodyPart();
				iFileBody.setDataHandler(new DataHandler(
						new ByteArrayDataSource(attachment.getAttachmentData().getBytes(), attachment.getMimetype())));
				iFileBody.setFileName(attachment.getFileName() + attachment.getFileType());
				multiPart.addBodyPart(iFileBody);
			}
			msg.setSubject("---------FLIGHT-WEB-ORDERS-TEST----------");
			multiPart.addBodyPart(msgBody);
			msg.setContent(multiPart);
			javaMailSender.send(msg);
			System.err.println("***********************----FLIGHT-WEB-EMAIL-TEST----********************************" + "\n\r"
								+ "**********************----SUCCESSFUL!!!!----***************************************");
		} catch(Exception e) {
			e.printStackTrace();
			System.err.println("******************--ERROR-SENDING-WEB-EMAIL--******************* \n\r" + e.getMessage());
		}
	}

}
