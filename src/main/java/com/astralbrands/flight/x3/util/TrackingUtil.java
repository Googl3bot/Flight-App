package com.astralbrands.flight.x3.util;

import org.springframework.stereotype.Component;

@Component
public class TrackingUtil {

    private static StringBuilder dailyOrders;

    private static String dailyFlightRun;
    private String trackingInfo;
    private static String trackingResponse;
    private static String trackingResponseTwo;

    private static String trackingResponse2ndCall;

    public static String getTrackingResponse2ndCall() {
        return trackingResponse2ndCall;
    }

    public static void setTrackingResponse2ndCall(String trackingResponse2ndCall) {
        TrackingUtil.trackingResponse2ndCall = trackingResponse2ndCall;
    }

    public static String getDailyFlightRun() {
        return TrackingUtil.dailyFlightRun;
    }

    public static void setDailyFlightRun(String dailyFlightRun) {
        TrackingUtil.dailyFlightRun = dailyFlightRun;
    }

    public static String getTrackingResponse() {
        return TrackingUtil.trackingResponse;
    }

    public static void setTrackingResponse(String trackingResponse) {
        TrackingUtil.trackingResponse = trackingResponse;
    }

    public StringBuilder getDailyOrders() {
        return TrackingUtil.dailyOrders;
    }

    public static void setDailyOrders(StringBuilder orders) {
          TrackingUtil.dailyOrders = orders;
    }

    public String getResponse(String trackingResponse) {
        this.trackingInfo = trackingResponse;
        return this.trackingInfo;
    }
    public String getResponseDetails() { return this.trackingInfo; }
    public static void setTrackingInfo(String trackInfo) {  TrackingUtil.trackingResponse = trackInfo;}

    public static String getTrackingInfo() { return TrackingUtil.trackingResponse;}

    public static void setTrackingInfoTwo(String trackInfo) {  TrackingUtil.trackingResponseTwo = trackInfo;}

    public String getTrackingInfoTwo() { return TrackingUtil.trackingResponseTwo;}
}
