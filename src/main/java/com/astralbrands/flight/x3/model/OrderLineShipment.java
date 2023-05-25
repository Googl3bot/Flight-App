package com.astralbrands.flight.x3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineShipment{
    @JsonProperty("OrderLineID") 
    private int orderLineID;
    @JsonProperty("ShipmentID") 
    private int shipmentID;
    @JsonProperty("TrackingNumber") 
    private String trackingNumber;
}

