package com.astralbrands.flight.x3.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.astralbrands.flight.x3.model.OrderDetails;

@Service
public class OrderIntegrationServiceImpl implements OrderIntegrationService{
	
	@Autowired
	private FlightOrderServices flightServices;
	
//	@Autowired
//	private NetworkFileService iFileService;
	
	
	/*
	 * @Override public String integrateOrders() { String fileData = null; //Stores
	 * Order Details for each OrderID flightServices.getOrderDetails();
	 * 
	 * return fileData; }
	 */

}
