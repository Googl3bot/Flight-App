package com.astralbrands.flight.x3.controller;

import com.astralbrands.flight.x3.config.AppConfig;
import com.astralbrands.flight.x3.handler.FlightWebPageDAO;
import com.astralbrands.flight.x3.model.Orders;
import com.astralbrands.flight.x3.model.TodaysOrders;
//import com.astralbrands.flight.x3.service.FlightWebPageService;
//import com.astralbrands.flight.x3.service.FlightWebPageService;
import com.astralbrands.flight.x3.service.FlightWebPageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FlightPortalController {

    Logger log = LoggerFactory.getLogger(FlightPortalController.class);

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    AppConfig appConfig;
//    @Autowired
//    FlightWebPageService flightWebPageService;

    @Autowired
    FlightWebPageDAO flightWebPageDAO;

    @Autowired
    FlightWebPageService flightWebPageService;

    private Connection x3Connection;

    private List<Orders> ordersList;
    private StringBuilder todayOrders;
    private List<TodaysOrders> todaysOrdersList;

    @Autowired
    @Qualifier("x3DataSource")
    private DataSource x3DataSource;



    @GetMapping(value = "/")
    public ModelAndView homePage() {
        log.info("home page");
        String date = getCurrentDate();
        ModelAndView map = new ModelAndView("index");
        return displayHomePage(map, date);
    }

    private ModelAndView displayHomePage(ModelAndView map, String date) {
        Model model;
        try {
            String message;
            todayOrders = flightWebPageService.getWebList2();
            log.info("list : " + ordersList);
//            log.info(": " + brands);
            map.addObject("orderDate", date);
            map.addObject("orders", todayOrders);
            map.addObject("tracking", "");
            map.addObject("orderList", ordersList);
//            map.addObject("CUSTOMER_NAME", );
//            map.addObject("CUSTOMER_ORDER_NUMBER", );
//            map.addObject("TRACKING_NUMBER", );
//            map.addObject("DELIVERY_NUMBER", );
//            map.addObject("SHIP_DATE", );
        } catch (Exception e) {
            log.error("error while loading data from db : "+e.getMessage());
            map.addObject("orders", new ArrayList());
            map.addObject("orderList", new ArrayList());
            map.addObject("message", "Error while loading data from data base");
        }
        return map;
    }

//    @RequestMapping(value = {"/date"}, method = RequestMethod.GET)
//    public ModelAndView displayTodayOrders(@RequestParam("date") String date) {
//        log.info("---------------DISPLAYING DATA FOR ORDER DATE : " + date + "---------------------------------");
//        ResultSet resultSet = flightWebPageDAO.getDateQuery(date);
//
//    }

//    @RequestMapping(value = {"/today"}, method = RequestMethod.GET)
//    public ModelAndView displayTodayOrders() {
//        log.info("---------------DISPLAYING DATA FOR ORDER DATE : " + getCurrentDate() + "---------------------------------");
//        ResultSet resultSet = flightWebPageDAO.getDateQuery("20230220");
//        todayOrders = flightWebPageService.getWebList2();
//        ModelAndView modelAndView = new ModelAndView("flight-home-page");
//        return displayHomePage(modelAndView, getCurrentDate());
//    }


//    private Orders getOrderDetails(String query) throws Exception {
//        List<TodaysOrders> todaysOrdersList;
//        if (result != null) {
//            Orders orders = new Orders();
//            while (result.next()) {
//
//                orders.setDate(getCurrentDate());
//                orders.setOrderNumber(result.getString("ORDER_NUMBER"));
//                orders.setCompanyPrefix(result.getString("COMPANY"));
//                orders.setCustomerName(result.getString("CUSTOMER_NAME"));
//                orders.setCustomerOrderNumber(result.getString("CUSTOMER_ORDER_NUMBER"));
//                orders.setTrackingNumber(result.getString("TRACKING_NUMBER"));
//                orders.setDeliveryNumber(result.getString("DELIVERY_NUMBER"));
//                orders.setShipDate(result.getString("SHIP_DATE"));
//
//            }
//            return orders;
//        }
//        return null;
//    }

//    public List<Orders>
//
    private String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(LocalDate.now().format(formatter));
        return LocalDate.now().format(formatter);
    }


}
