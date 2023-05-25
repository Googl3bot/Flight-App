package com.astralbrands.flight.x3.service;

import java.util.List;
import java.util.Map;

import com.astralbrands.flight.x3.handler.FlightServiceHandler;
import com.astralbrands.flight.x3.model.EmailAttachment;
import com.astralbrands.flight.x3.model.EmailData;
import com.astralbrands.flight.x3.util.ApiUtil;
import com.astralbrands.flight.x3.util.AppConstants;
import com.astralbrands.flight.x3.util.TrackingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public abstract class RestApiCallService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private FlightServiceHandler flightServiceHandler;

	@Autowired
	private MailingService mailingService;

	@Autowired
	private TrackingUtil trackingUtil;

	public <T> T call(String url, HttpMethod method, Object request, Class<T> response, HttpHeaders headers) throws RestClientException {
		HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
		System.out.println("input"+requestEntity);
		ResponseEntity<T> result = restTemplate.exchange(url, method, requestEntity, response);

		StringBuilder sb = new StringBuilder();
		sb.append("-----------------Rest API Call Response------------------" + "\n\r\n\r");
		sb.append("STATUS CODE  OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCode()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("REASON PHRASE  OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCode().getReasonPhrase()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("STATUS-CODE-VALUE  OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCodeValue()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("HASH CODE OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.hashCode()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("PRINTING THE ACTUAL 'RESULT' OBJECT --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");;
		sb.append("PRINTING THE ACTUAL 'RESPONSE' OBJECT --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(response).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("PRINTING THE 'SERIES' FOR STATUS-CODE OBJECT --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCode().series()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("PRINTING 'RESULT' OBJECT GET-BODY METHOD --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getBody()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");

		TrackingUtil.setTrackingResponse(sb.toString());
//		TrackingUtil.setDailyOrders(sb);

		System.out.println("Response from API: "+result);
		return result.getBody();
	}

	public <T> T call(String url, HttpMethod method, Object request, Class<T> response, HttpHeaders headers,
			Map<String, String> params) throws RestClientException {
		HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
		ResponseEntity<T> result = restTemplate.exchange(url, method, requestEntity, response, params);

		StringBuilder sb = new StringBuilder();
		sb.append("STATUS CODE  OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCode()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("REASON PHRASE  OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCode().getReasonPhrase()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("STATUS-CODE-VALUE  OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCodeValue()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("HASH CODE OF CALL FUNCTION -->  \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.hashCode()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("PRINTING THE ACTUAL 'RESULT' OBJECT --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");;
		sb.append("PRINTING THE ACTUAL 'RESPONSE' OBJECT --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(response).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("PRINTING THE 'SERIES' FOR STATUS-CODE OBJECT --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getStatusCode().series()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");
		sb.append("PRINTING 'RESULT' OBJECT GET-BODY METHOD --> \n\r").append("--------------------------------------------------" + "\n\r\n\r").append(result.getBody()).append("\n\r\n\r").append("**************************************************").append("\n\r\n\r");

		TrackingUtil.setTrackingResponse2ndCall(sb.toString());
		return result.getBody();
	}

}
