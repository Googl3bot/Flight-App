package com.astralbrands.flight.x3.service;

import com.astralbrands.flight.x3.controller.FlightPortalController;
import com.astralbrands.flight.x3.handler.FlightWebPageDAO;
import com.astralbrands.flight.x3.model.EmailData;
import com.astralbrands.flight.x3.model.Orders;
import com.astralbrands.flight.x3.model.TodaysOrders;
import com.astralbrands.flight.x3.model.TrackingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightWebPageService {

//    @Autowired
//    FlightPortalController flightPortalController;
    @Autowired
    FlightWebPageDAO flightWebPageDAO;
    @Value("${x3.tracking.query}")
    String trackingQuery;

    public Map<Integer, String> mapList;

    public StringBuilder orderList = new StringBuilder();

    public Map<Integer, String> backUp;

    public StringBuilder flightOrderList = new StringBuilder();

    public StringBuilder todaysOrderList = new StringBuilder();

    public List<String> flightList = new ArrayList<>();
    public StringBuilder flightWebList_2;
    public StringBuilder setWebList2(StringBuilder list) {
        return this.flightWebList_2 = new StringBuilder(list);
    }
    public StringBuilder getWebList2() {
        return this.flightWebList_2;
    }
//    public void emailWebOrders(String webList) {
//        EmailData emailData
//    }



//    public List<Orders> getOrders(String date) throws Exception {
//        try {
//            ResultSet resultSet = Optional.ofNullable(flightWebPageDAO.getDateQuery(date)).orElse(null);
//            List<Orders> orders = Optional.ofNullable(getOrderDetails(resultSet)).orElse(null);
//            return orders;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

//    public List<TodaysOrders> getTodaysOrders() throws Exception{
//        String newLine = "\r\n\r\n";
//        try {
//            ResultSet resultSet = Optional.ofNullable(flightWebPageDAO.runQueryFlight(trackingQuery.replace("#currDate", getCurrentDate()))).orElse(null);
//            return Optional.ofNullable(getTodaysOrderDetails(resultSet)).orElse(null);
//        } catch (Exception e) {
//            System.err.println("************************--ERROR-GET-TODAY'S-ORDERS----ORDER-DETAILS--*****************************" + "\r\n");
//            System.err.println(e.getMessage() + newLine);
//            System.err.println(Arrays.toString(e.getStackTrace()) + newLine);
//            e.printStackTrace();
//            System.out.println(newLine);
//            throw new RuntimeException(e.getCause().toString());
//        }
//    }

//    private Map<Integer, String> todaysFlightList(Map<Integer, String> list) {
//        Map<Integer, String> todaysList = list;
//        mapList.putAll(list);
//
//    }

    public void setTodaysFlightList(Map<Integer, String> orders) {
       StringBuilder sb = new StringBuilder();
       try {
           if(!orders.isEmpty()) {
               this.mapList.putAll(orders);
           }
           if(!mapList.isEmpty()) {
               for(Map.Entry<Integer, String> entry : mapList.entrySet()) {
                   sb.append(entry.getValue().toString());
//                   return sb.toString();
               }
           } else {
               sb.append("------------------****-NO-DATA-****-------------------");
           }
//           return sb.toString();
       } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException(sb.toString() + "\n\r" + e.getMessage());
       }
    }

    private String goin = "";

    public String getTodaysFlightOrders(Map<Integer, String> list) {
//        List<TodaysOrders> todaysFlightOrdersList = new ArrayList<>();
        StringBuilder orders = new StringBuilder();
//        goin.lines().forEach();
        StringBuilder todayOrders = new StringBuilder();
        StringJoiner stringJoiner = new StringJoiner("-|-");
        orders.append("-----------------------------COLUMN NAMES FOR HEADER LINES------------------------------" + "\r\n");
        orders.append("Flag-|-" + "Sales-Site-|-" + "Order-Type-|-" + "Order-Number-|-" + "Warehouse-Name-|-" + "Order-Date-|-" + "Shipment-ID-|-" + "Shipment-Site-|-" + "Currency-|-" + "Name-|-" + "Billing-Address-|-" + "Shipping-Fee-|-" + "Discount-Amount-|-" + "Tax-Fee-|-" + "Payment-Type-|" + "\r\n");
        orders.append("-----------------------------COLUMN NAMES FOR PRODUCT LINES------------------------------" + "\r\n");
        orders.append("FLAG-|-" + "SKU-ID-|-" + "BUILDABLE-SKU-ID-|-" + "PRODUCT-NAME-|-" + "PRODUCT-TYPE-|-" + "QUANTITY-|-" + "PRODUCT-PRICE-|-" + "\r\n\r\n\r\n");
        try {
            if (list != null) {
                for (Map.Entry<Integer, String> entry : list.entrySet()) {
                    orderList.append(entry.getValue());
                    backUp.put(entry.getKey(), entry.getValue());
                }
                if (backUp.isEmpty()) {
                    backUp.putAll(list);
                }
                int i = 0;
                if (!backUp.isEmpty()) {
                    try {
                        //--GET-ALL-ORDER-ENTRIES-INSIDE-IFILE-&-BUILD-NEW-TABLE-(WEBPAGE)--\\
                        flightOrderList = new StringBuilder(backUp.entrySet().toString());
                        //--COMPILE-STRING-LITERALS-INTO-PATTERN-OBJECTS--\\
                        Pattern pattern = Pattern.compile("(~{1,6})");

                        //--MATCHER-OBJECTS-MATCH-PATTERN-OBJECTS-TO-NEWLIST-ENTRIES--\\
                        Matcher matcher = pattern.matcher(flightOrderList);



                        //--REPLACE-ALL-PATTERN-OBJECTS-USING-MATCHERS-FOR-ALL-ENTRIES-NEW-FLIGHT-LIST--\\
                        while (matcher.find()) {
                            flightOrderList = new StringBuilder(matcher.replaceAll("-|-"));
                        }

                        System.err.println("----------------------------------------FIRST-FLIGHT-ORDERS-LIST-COPY---------------------------------------");
                    } catch (Exception e) {
                        System.err.println("************************--ERROR-REPLACING-ENTRIES-FOR-NEW-MAP-(' -|- ')--*****************************" + "\r\n");
                        System.err.println(e.getMessage() + "\r\n\r\n");
                        System.err.println(Arrays.toString(e.getStackTrace()) + "\r\n\r\n");
                        e.printStackTrace();
                    }
                    System.err.println("------------------------------------------SECOND-FLIGHT-ORDERS-LIST-COPY-----------------------------------------------");
                    //----IF-FIRST-TRY-BLOCK-FAILS-POPULATE-ORDER-LIST--DIFFERENT-LOGIC----\\
                    if ((flightOrderList.toString().contains("~~") || flightOrderList.toString().contains("~~~") || flightOrderList.toString().contains("~~~~~")
                            || flightOrderList.toString().contains("~~~~~~")) && flightOrderList.length() <= 0) {
                        todayOrders.append(flightOrderList.toString().replaceAll("(~+)", "-|-"));
                    }
                }
            }
        } catch(Exception e) {
             e.printStackTrace();
             throw new RuntimeException("SOMETHING WENT WRONG ---- NO DATA -->" + e.getMessage());
        }
        if(flightOrderList.toString().contains("~") || flightOrderList.toString().contains("~~") || flightOrderList.toString().contains("~~~")
                || flightOrderList.toString().contains("~~~~~")) {
            return todayOrders.toString();
        } else {
            return flightOrderList.toString();
        }
    }

    private TodaysOrders getOrderDetails(String[] result) throws Exception {
        TodaysOrders order = new TodaysOrders();
        if (result != null) {
//            List<Orders> ordersList = new ArrayList<Orders>();
            for (int i = 0; i <= result.length; i++) {
                order.setFlag(result[0]);
                order.setSkuId(result[1]);
                order.setBuildableSkuId(result[2]);
                order.setProductName(result[3]);
                order.setProductType(result[4]);
                order.setQuantity(result[5]);
                order.setProductPrice(result[6]);
            }
            return order;
        }
        return null;
    }


    private TodaysOrders getTodaysOrderDetails(String[] result) throws Exception {
        TodaysOrders todaysOrders = new TodaysOrders();
        if(result != null) {
            for (int b = 0; b <= result.length; b++) {

                todaysOrders.setFlag(result[0]);
                todaysOrders.setSalesSite(result[1]);
                todaysOrders.setOrderType(result[2]);
                todaysOrders.setOrderNumber(result[3]);
                todaysOrders.setWarehouseName(result[4]);
                todaysOrders.setOrderDate(result[5]);
                todaysOrders.setShipmentId(result[6]);
                todaysOrders.setShippingSite(result[7]);
                todaysOrders.setCurrency(result[8]);
                todaysOrders.setBillingAddress(result[9]);
                todaysOrders.setShippingAddress(result[10]);
                todaysOrders.setShippingFee(result[11]);
                todaysOrders.setDiscountPrice(result[12]);
                todaysOrders.setTaxFee(result[13]);
                todaysOrders.setPaymentType(result[14]);
                todaysOrders.setPaymentType(result[15]);
            }
        }
        return todaysOrders;
    }

    public String getTrackingCount(int exception, int success, int unprocessed) {
        StringJoiner stringJoiner = new StringJoiner("~");
        stringJoiner.add(String.valueOf(exception));
        stringJoiner.add(String.valueOf(success));
        stringJoiner.add(String.valueOf(unprocessed));

        return stringJoiner.toString();
    }


    /*
     *  --REMOVED this loop  "extractOrders()" ->
     *    for(int i = 0; i < flightList.toString().length(); i++) {
     *           stringBuilder.append(flightList.charAt(i));
     *
     *       }
     */

    public String extractOrders(StringBuilder flightList) {
        TodaysOrders todaysOrders = new TodaysOrders();
//        String[] array = flightList.toString().split();
        if(!flightList.toString().isEmpty()) {
            this.todaysOrderList = new StringBuilder(flightList.toString().replaceAll("(~{1,6})", "-|-"));

            if(this.todaysOrderList.length() > 0) {
                return this.todaysOrderList.toString();
            }
        } else {
            this.todaysOrderList.append("EMPTY FLIGHT LIST");
        }
        return this.todaysOrderList.toString();
    }

    /*
     *
     * //TODO
     *    Convert line by line
     */
    public String tableOfOrders(String orderList) throws Exception {
        StringBuilder listB = new StringBuilder();
        List<TodaysOrders> ordersList = new ArrayList<TodaysOrders>();
        String listA = orderList.replaceAll("(~{1,6})", "-");
//        Pattern pattern = Pattern.compile(".\\R");
//        Stream<String> lines = orderList.lines().forEach();
        String[] listArray = listA.split("\\r?\\n");
        for(String line : listArray) {
           String[] chars = line.split("-");
            if(line.startsWith("E")) {
                ordersList.add(getTodaysOrderDetails(chars));
            }
            else if (line.startsWith("L")) {
                ordersList.add(getOrderDetails(chars));
            }
        }
//        for(String line : listA.lines().collect(Collectors.toList())) {
//            if(line.startsWith("E")) {
//
//            }
//        }

        return "Temporary //TODO";
    }

//    public String fieldDelimiterEliminator(StringBuilder flightList) {
//        StringBuilder stringBuilder = new StringBuilder();
//        StringBuilder flightOrderList = new StringBuilder(flightList);
//        String flight = "";
//
//        String index = "~~~~~~";
//        String index_2 = "~~~~~";
//        String index_3 = "~~";
//
//        if(flightList.toString().contains("~~~~~~") || flightList.toString().contains("~~~~~") || flightList.toString().contains("~~")) {
////            flightOrderList = stringBuilder.toString();
//            while(flightList.length() > 0) {
//                while(flightOrderList.toString().contains("~~~~~~") || flightOrderList.toString().contains("~~~~~") || flightOrderList.toString().contains("~~")) {
//                    Pattern pattern = Pattern.compile("~~~~~~");
//                    Pattern pattern2 = Pattern.compile("~~~~~");
//                    Pattern pattern3 = Pattern.compile("~~");
//
//                    Matcher matcher =
//
//                    int index1 = flightOrderList.toString().indexOf("~~~~~~");
//                    int index2 = flightOrderList.toString().indexOf("~~~~~");
//                    int index3 = flightOrderList.toString().indexOf("~~");
//                    flightOrderList.deleteCharAt(index1);
//                    flightOrderList.setCharAt(index1, '~');
//                    flightOrderList.deleteCharAt(index2);
//                    flightOrderList.setCharAt(index2, '~');
//                    flightOrderList.deleteCharAt(index3);
//                    flightOrderList.setCharAt(index3, '~');
//
//                    flight = flightOrderList.toString();
//                }
//            }
//
//            if(flight.contains("~~~~~~") || flight.contains("~~~~~") || flight.contains("~~")) {
//                try {
//                    switch ("/~~~~~~ (?:~~~~~) (?:~~)/gm") {
//                        case "~~~~~~":
//                            flight.replaceAll(Pattern.quote("~~~~~~"),"~");
//                        case "~~~~~":
//                            flight.replaceAll(Pattern.quote("~~~~~"),"~");
//                        case "~~":
//                            flight.replaceAll(Pattern.quote("~~"),"~");
//
//                    }
//                    Pattern.compile("~~~~~~").matcher("~~~~~~").replaceAll("~");
//
//                }
//
//
//            }
//
//        } else {
//                System.err.println("--------------******--WRONG NUMBER OF '~' IN FUNCTION--******----------------");
//        }
//        flight = stringBuilder.append(flightOrderList).toString();
//        if(flightOrderList.toString().isEmpty()) {
//
//        }
//    }
    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(LocalDate.now().format(formatter));
        return LocalDate.now().format(formatter);
    }
}
