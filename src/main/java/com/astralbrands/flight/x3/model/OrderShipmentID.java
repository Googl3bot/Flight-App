package com.astralbrands.flight.x3.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderShipmentID{
    @JsonProperty("ShipmentDisplayID") 
    public String shipmentDisplayID;
    @JsonProperty("OrderDisplayID") 
    public String orderDisplayID;
}