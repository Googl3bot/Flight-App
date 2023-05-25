package com.astralbrands.flight.x3.util;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.astralbrands.flight.x3.model.FlightOrderLine;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class FlightOrderLineCache {

	private static Map<String, Set<String>> flightOrderLines = new HashMap<>();
	private static FlightOrderLineCache INSTANCE = new FlightOrderLineCache();

	static {
		loadFlightOrderConfiguration();
	}

	public static FlightOrderLineCache getInstance() {
		return INSTANCE;
	}

	public static void loadFlightOrderConfiguration() {

		InputStream in = FlightOrderLineCache.class.getClassLoader().getResourceAsStream("FlightOrderLine.xml");
		try {
			if (in == null) {
				System.out.println(" Getting null input Stream for config-data.xml");
				return;
			}
			XmlMapper mapper = new XmlMapper();
			List<FlightOrderLine> config = mapper.readValue(in, new TypeReference<List<FlightOrderLine>>() {
			});
			System.out.println("configs :" + config);
			for (FlightOrderLine flightOrderLine : config) {
				Set<String> items = flightOrderLines.get(flightOrderLine.getSkuId());
				if (items != null) {
					items.addAll(flightOrderLine.getItems());
				} else {
					items = new HashSet<String>(flightOrderLine.getItems());
					flightOrderLines.put(flightOrderLine.getSkuId(), items);
				}
			}
			System.out.println("order line Map based on SKU id : " + flightOrderLines);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error while reading config-data.xml file ");
			throw new RuntimeException("Error while loading config data .." + e.getMessage());

		}

	}

	public static int getChildOrderCount(String skuid) {
		return flightOrderLines.get(skuid) != null ? flightOrderLines.get(skuid).size() : 0;
	}

	public static Set<String> getChildOrders(String skuId) {
		return flightOrderLines.get(skuId);
	}
	
	public static void main(String[] args) {
	}

}
