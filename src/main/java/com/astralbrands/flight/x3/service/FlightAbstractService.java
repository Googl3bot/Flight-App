package com.astralbrands.flight.x3.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.astralbrands.flight.x3.model.SearchCriteriaResponse;
import com.astralbrands.flight.x3.model.SearchCriteriaResult;
import com.astralbrands.flight.x3.util.ApiUtil;

public abstract class FlightAbstractService extends RestApiCallService {

	protected Map<Integer, String> searchWareHouseId(String accessToken, String runId, String searchCriteriaUrl) {
		Map<Integer, String> runIds = new HashMap<>();
		String request = runId == null ? ApiUtil.getWareHouseIdSearchReq()
				: ApiUtil.getWareHouseIdSearchReqByRunId(runId);
		SearchCriteriaResponse criteriaResult = call(searchCriteriaUrl, HttpMethod.POST, request,
				SearchCriteriaResponse.class, getHeader(accessToken));

		if (criteriaResult != null && criteriaResult.getResults() != null) {
			for (SearchCriteriaResult res : criteriaResult.getResults()) {
				if (res.getOrderCount() > 0) {
					runIds.put(res.getShippingFileRunID(), res.getWarehouses());
				}
			}
		}
		return runIds;
	}

	protected HttpHeaders getHeader(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
