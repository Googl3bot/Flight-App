package com.astralbrands.flight.x3.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.astralbrands.flight.x3.handler.FlightServiceHandler;

import javax.ws.rs.QueryParam;

@Component
public class ScheduledTaskService {

	@Autowired
	private FlightServiceHandler flightServiceHandler;

	@Autowired
	private TrackingService trackingService;

	private static String orderId;

	@Scheduled(cron = "0 31 9 * * MON-FRI")    //----@QueryParam("orderId")String orderId
	public void updateOrderToX3() {
		System.out.println("updating order from flight to X3 :" + new Date());
		flightServiceHandler.handle();
	}

//	@Scheduled(cron = "45 25 20 * * MON-FRI")
//	public void updateTrackingRecord() {
//		 System.out.println("Get tracking record from X3 and publish to Flight system :" + new Date());
//		 trackingService.importX3ToFlight();
//	}

//	@Scheduled(cron = "0 31 9 * * MON-FRI")
//	public void getTrackingList() {
//		System.out.println("Get Tracking Record List from X3 and CREATE .csv file:" + new Date());
//		flightServiceHandler.handleTracking();
//	}

}
