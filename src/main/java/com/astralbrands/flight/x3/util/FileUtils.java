package com.astralbrands.flight.x3.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {
	private static String wareHouseIdSearchReq;
	private static String wareHouseIdSearchReqByRunId;
	private static final String WAREHOUSEID_SEARCH_REQ_FILE = "wareHouseIdSearchReq.json";
	private static final String WAREHOUSEID_SEARCH_BY_ORDERID_REQ_FILE = "wareHouseIdSearchReqByRunId.json";
	static {
		wareHouseIdSearchReq = readFile(WAREHOUSEID_SEARCH_REQ_FILE);
		wareHouseIdSearchReqByRunId = readFile(WAREHOUSEID_SEARCH_BY_ORDERID_REQ_FILE);
	}

	public static String readFile(String fileName) {
		StringBuilder strBuilder = new StringBuilder();
		URL url = FileUtils.class.getClassLoader().getResource(fileName);
		try {
			File file = new File(url.toURI());
			try (Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {
				stream.forEach(strBuilder::append);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return strBuilder.toString();
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return "";

	}

	public static String getWareHouseIdSearchReq() {
		if (wareHouseIdSearchReq == null) {
			wareHouseIdSearchReq = readFile(WAREHOUSEID_SEARCH_REQ_FILE);
		}
		return wareHouseIdSearchReq;
	}
	
	public static String getWareHouseIdSearchReqByRunId() {
		if (wareHouseIdSearchReqByRunId == null) {
			wareHouseIdSearchReqByRunId = readFile(WAREHOUSEID_SEARCH_BY_ORDERID_REQ_FILE);
		}
		return wareHouseIdSearchReqByRunId;
	}

}
