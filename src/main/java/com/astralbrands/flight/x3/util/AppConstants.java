package com.astralbrands.flight.x3.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public interface AppConstants {

//	@Value("${x3.tracking.query}")
//	public final static String X3_TRACKING_QUERY = null;

	public final static String ORDER_DISPLAY_ID = "orderDisplayId";
	public final static String SHIPPING_RUN_ID = "shippingFileRunId";
	public final static String EIGHT = "8";
	public final static String NINE = "9";
	public final static String BGO = "BGO";
	public final static String NEW_LINE = "\r\n";
	public final static String US = "US";
	public final static String IFILE_DATA_SEPERATOR = "~";
	public static final String EMPTRY_STR = "";
	public static final String SPACE = " ";
	public static final String COMMA = ",";
	public static final String SEMI_COMMA = ";";
	public static final String LINE_SEPARATOR = "|";
	public static final String FORWARD_SLASH = "/";
	public static final String END_OF_FILE = "END";
	public static final String USD = "USD";
	public static final String CAD = "CAD";
	public static final String US_SITE = "ALOUS";

	//**********----CHANGED ALCUS -> ALCCA FOR CANADA----***********
	public static final String CA_SHIPPING_SITE = "ALCUS"; // ****--CHANGED TO ->  " ALCCA " --*****
	public static final String TXT_FILE_EXTENSION = ".txt";
	
}
