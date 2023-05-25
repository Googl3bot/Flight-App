//package com.astralbrands.flight.x3.service;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.astralbrands.flight.x3.util.ApiUtil;
//import com.astralbrands.flight.x3.util.AppConstants;
//
//import jcifs.smb.NtlmPasswordAuthentication;
//import jcifs.smb.SmbFile;
//import jcifs.smb.SmbFileInputStream;
//import jcifs.smb.SmbFileOutputStream;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class NetworkFileService implements AppConstants {
//
//	@Value("${sag.network.file.fileName}")
//	private String fileName;
//
//	@Value("${sag.network.file.domain}")
//	private String networkDomain;
//
//	@Value("${sag.network.file.uploadLocation}")
//	private String uploadLocation;
//
//	@Value("${sag.network.file.trlocation}")
//	private String traLocation;
//
//	@Value("${sag.network.file.userName}")
//	private String userName;
//
//	@Value("${sag.network.file.password}")
//	private String password;
//
//	private NtlmPasswordAuthentication ntlmAuthentication;
//
//
//	public String upload(String fileData) {
//		String fName = fileName + ApiUtil.getCurrentDateYYYYMMDD() + TXT_FILE_EXTENSION;
//		String path = uploadLocation + FORWARD_SLASH + fName;
//		try {
//			SmbFile sFile = new SmbFile(path, getAuthentication());
//			SmbFileOutputStream sfos = new SmbFileOutputStream(sFile);
//			sfos.write(fileData.getBytes());
//			sfos.close();
//		} catch (IOException e) {
//			log.error("Error while uploading ifile to network drive");
//		}
//		return fName;
//
//	}
//
//	public String readFile(String fileName) {
//		String path = traLocation + FORWARD_SLASH + fileName + ".tra";
//		StringBuilder sb = new StringBuilder();
//		try {
//			SmbFile sFile = new SmbFile(path, getAuthentication());
//			try (BufferedReader reader = new BufferedReader(new InputStreamReader(new SmbFileInputStream(sFile)))) {
//				String line = reader.readLine();
//				while (line != null) {
//					line = reader.readLine();
//					sb.append(line);
//				}
//			}
//
//		} catch (IOException e) {
//			log.error("Error while uploading ifile to network drive");
//		}
//		return sb.toString();
//
//	}
//
//	private NtlmPasswordAuthentication getAuthentication() {
//		if (ntlmAuthentication == null) {
//			ntlmAuthentication = new NtlmPasswordAuthentication(networkDomain, userName, password);
//		}
//		return ntlmAuthentication;
//	}
//
//}
