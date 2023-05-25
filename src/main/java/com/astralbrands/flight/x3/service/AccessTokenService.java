package com.astralbrands.flight.x3.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.astralbrands.flight.x3.model.AccessTokenResponse;

@Service
public class AccessTokenService extends RestApiCallService {

	/*
	 * @Autowired private RestTe.mplate restTemplate;
	 */

	Logger log = LoggerFactory.getLogger(AccessTokenService.class);
	
	@Value("${token.access.user}")
	private String userId;

	@Value("${token.access.password}")
	private String password;

	@Value("${token.access.url}")
	private String tokenUrl;

	private String accesToken;

	public String getAccessToken() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> multMap = new LinkedMultiValueMap<>();
		multMap.add("grant_type", "password");
		multMap.add("username", userId);
		multMap.add("password", password);
		AccessTokenResponse response = call(tokenUrl, HttpMethod.POST, multMap, AccessTokenResponse.class, headers);
		log.info(" response from token api :" + response);
		if (response != null) {
			return response.getAccess_token();
		}
		return null;
	}

}
