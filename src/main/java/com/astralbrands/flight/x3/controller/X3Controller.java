package com.astralbrands.flight.x3.controller;

import javax.ws.rs.QueryParam;

import com.astralbrands.flight.x3.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.astralbrands.flight.x3.handler.FlightServiceHandler;

@RestController
public class X3Controller {

	private static final Logger log = LoggerFactory.getLogger(X3Controller.class);

	@Autowired
	private FlightServiceHandler flightServiceHandler;

	@Autowired
	private FlightPaymentService flightPayments;

	@Autowired
	private ScheduledTaskService scheduledTaskService;

	@Autowired
	private TrackingService trackingService;

//	public ResponseEntity<String> autoIntegration(String orderId) {
//		scheduledTaskService.updateOrderToX3(orderId);
//		return ResponseEntity.ok().build();
//	}

	@GetMapping("/health")
	public String healthCheck() {
		return "Flight order integration service is up and running!!";
	}

	@PostMapping(value = "/flightOrdersIntegration")
	public ResponseEntity<String> IntegrateFlightOrders(@QueryParam("orderId") String orderId) {
		flightServiceHandler.handle(orderId);
//		flightServiceHandler.handleWebOrderList(orderId);
		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/getTrackingRecords")
	public ResponseEntity<String> exportX3ToCsv() {
		return ResponseEntity.ok(trackingService.importX3ToFlight());
	}

	@PostMapping(value = "/getPaymentUpdate")
	public ResponseEntity<String> integratePayments() {
		flightPayments.uploadPaymentDetailsToX3();
		 return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/trackingRecordList")
	public ResponseEntity<String> trackingList() {
		flightServiceHandler.handleTracking();

		return ResponseEntity.ok().build();
	}


}
