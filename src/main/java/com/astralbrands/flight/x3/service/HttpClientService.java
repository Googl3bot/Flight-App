//package com.astralbrands.flight.x3.service;
//
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Base64;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class HttpClientService {
//
//	@Value("${sag.x3.userName}")
//	private String userName;
//
//	@Value("${sag.x3.userPassword}")
//	private String password;
//
//	@Value("${sag.x3.pool}")
//	private String pool;
//
//	public String call(String url, String requestStr) {
//		HttpClient client = HttpClient.newHttpClient();
//		//System.out.println("Request to API is : "+requestStr);
//		 HttpResponse<String> response=  null;
//			try {
//				response = client.send(getRequest(requestStr, url),
//				        HttpResponse.BodyHandlers.ofString());
//
//		        //System.out.println(response.body());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//	        return response != null ? response.body() : null;
//	}
//
//	private HttpRequest getRequest(String requestStr, String url){
//		return  HttpRequest.newBuilder().uri(URI.create(url))
//				.POST(HttpRequest.BodyPublishers.ofString(requestStr)).header("Content-Type", "text/html")
//				.header("soapAction", url).header("Authorization", "Basic "+getEncodePass()).build();
//	}
//
//	private String getEncodePass() {
//		return new String(Base64.getEncoder().encode(getUserNamePwdBytes()));
//	}
//
//	private byte[] getUserNamePwdBytes() {
//		return (userName+":"+password).getBytes();
//	}
//
//}
