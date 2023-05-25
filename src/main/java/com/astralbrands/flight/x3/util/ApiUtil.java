package com.astralbrands.flight.x3.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class ApiUtil {

	// Input body for SearchCriteria URl
	private static final String SEARCH_CRITERIA_REQUEST = "{\"SearchResultTitle\":null,\"SearchOperators\":[{\"Key\":\"Is\",\"Value\":\"Is\"},{\"Key\":\"Is In\",\"Value\":\"Is In\"},{\"Key\":\"Is Not In\",\"Value\":\"Is Not In\"},{\"Key\":\"Contains\",\"Value\":\"Contains\"},{\"Key\":\"Does Not Contain\",\"Value\":\"Does Not Contain\"},{\"Key\":\"Starts With\",\"Value\":\"Starts With\"},{\"Key\":\"Less Than\",\"Value\":\"Less Than\"},{\"Key\":\"Greater Than\",\"Value\":\"Greater Than\"},{\"Key\":\"Between\",\"Value\":\"Between\"}],\"SearchFields\":null,\"DefaultSearchFields\":[{\"FieldID\":295,\"DisplayName\":\"Business Unit\",\"FieldName\":\"BusinessUnitID\",\"FieldType\":\"dropdown\",\"FieldGroupID\":14,\"DefaultSearchOperator\":\"Is In\",\"SearchValue\":null,\"SearchValues\":[#searchValues],\"SearchSelectValue\":null,\"SearchWhereValue\":null,\"SearchValueBegin\":null,\"SearchValueEnd\":null,\"SearchDropdownOptionsName\":\"businessUnitOptions\",\"SearchDropdownOptions\":null,\"SearchDefaultCriterion\":true,\"PartialView\":null,\"SearchGroup\":1,\"SearchShowInResults\":false,\"Required\":false},{\"FieldID\":294,\"DisplayName\":\"Date Created\",\"FieldName\":\"DateCreated\",\"FieldType\":\"datetime\",\"FieldGroupID\":14,\"DefaultSearchOperator\":\"Between\",\"SearchValue\":null,\"SearchValues\":null,\"SearchSelectValue\":null,\"SearchWhereValue\":null,\"SearchValueBegin\":\"#searchValueStartDate\",\"SearchValueEnd\":\"#searchValueEndDate\",\"SearchDropdownOptionsName\":null,\"SearchDropdownOptions\":null,\"SearchDefaultCriterion\":true,\"PartialView\":null,\"SearchGroup\":1,\"SearchShowInResults\":false,\"Required\":false},{\"FieldID\":298,\"DisplayName\":\"Order Date\",\"FieldName\":\"OrderDate\",\"FieldType\":\"datetime\",\"FieldGroupID\":14,\"DefaultSearchOperator\":\"Between\",\"SearchValue\":null,\"SearchValues\":null,\"SearchSelectValue\":null,\"SearchWhereValue\":null,\"SearchValueBegin\":null,\"SearchValueEnd\":null,\"SearchDropdownOptionsName\":null,\"SearchDropdownOptions\":null,\"SearchDefaultCriterion\":true,\"PartialView\":null,\"SearchGroup\":1,\"SearchShowInResults\":false,\"Required\":false},{\"FieldID\":296,\"DisplayName\":\"Run ID\",\"FieldName\":\"ShippingFileRunID\",\"FieldType\":\"int32\",\"FieldGroupID\":14,\"DefaultSearchOperator\":\"Is\",\"SearchValue\":null,\"SearchValues\":null,\"SearchSelectValue\":null,\"SearchWhereValue\":null,\"SearchValueBegin\":null,\"SearchValueEnd\":null,\"SearchDropdownOptionsName\":null,\"SearchDropdownOptions\":null,\"SearchDefaultCriterion\":true,\"PartialView\":null,\"SearchGroup\":1,\"SearchShowInResults\":false,\"Required\":false}],\"OrSearchFields\":[],\"SearchEntityType\":8,\"ContextKey\":null,\"ContextIncludeSearch\":true,\"OffsetHours\":6}\r\n";
	// Input body for GetDataExportWithFilters URL to get Order Display Id's
	// associated with RunId's
	private static final String GET_DATA_EXPORT_WITH_FILTER_REQUEST = "{\r\n"
			+ "  \"Name\": \"ShippingFileRunOrder\",\r\n" + "  \"Mode\": \"1\",\r\n" + "  \"filters\": [\r\n" + "{\r\n"
			+ "\"DisplayName\": \"Shipping File Run ID\",\r\n" + "\"FieldName\": \"ShippingFileRunID\",\r\n"
			+ "\"SchemaName\": \"reports\",\r\n" + "\"ObjectName\": \"ShippingFileRunOrder\",\r\n"
			+ "\"DataExportFilterType\": 1,\r\n" + "\"BeginDateValue\": \"#beginDateValue\",\r\n"
			+ "\"EndDateValue\": \"#endDateValue\",\r\n" + "\"DisplayIDs\": [\r\n" + "\"string\"\r\n" + "],\r\n"
			+ "\"RunID\": #runId\r\n" + "}\r\n" + "],\r\n" + "\"CompProcessRunID\": 0\r\n" + "}";

	public static String getSearchCriteriaRequest(String searchValues) {
		String request = SEARCH_CRITERIA_REQUEST.replace("#searchValues", searchValues);
		String currentDate = getCurrentDate();
		request = request.replace("#searchValueStartDate", currentDate);
		request = request.replace("#searchValueEndDate", currentDate);
		return request;
	}
	
	public static String getWareHouseIdSearchReq() {
		String request = FileUtils.getWareHouseIdSearchReq();
		String currentDate = getCurrentDate();
		request = request.replace("#searchValueStartDate", currentDate);
		request = request.replace("#searchValueEndDate", currentDate);
		return request;
		//2022-11-15
	}
	
	public static String getWareHouseIdSearchReqByRunId(String runId) {
		String request = FileUtils.getWareHouseIdSearchReqByRunId();
		request = request.replace("#RUNID#", runId);   //  <----REPLACE FOR MANUAL String num = "39560";
		return request;
	}
	
	public static String getCurrentDateYYYYMMDD() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		System.out.println(LocalDate.now().format(formatter));
		return LocalDate.now().format(formatter);
	}

	public static String getDataExportFilterRequest(Integer runId) {
		String request = GET_DATA_EXPORT_WITH_FILTER_REQUEST.replace("#runId", runId + "");
		String currentDate = getCurrentDate();
		request = request.replace("#beginDateValue", currentDate);
		request = request.replace("#endDateValue", currentDate);
		return request;
	}

	public static String getCurrentDate() {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY,5);
//		calendar.set(Calendar.DAY_OF_MONTH, -1);
		String date = inputFormat.format(calendar.getTime());
		System.out.println("current date : " + date);
		return date;
	}

	public static String getSpecificDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY,5);

		//----CHANGE '27' TO THE DAY OF THE CURRENT MONTH YOU NEED TO PROCESS----\\
		calendar.set(Calendar.DAY_OF_MONTH, 27);

//		calendar.set(Calendar.DAY_OF_MONTH, -1);
		String date = format.format(calendar.getTime());
		System.out.println("Specified date : " + date);
		return date;
	}

}
