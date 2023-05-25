package com.astralbrands.flight.x3.model;

import java.util.ArrayList;

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
public class OrderLineVolumeTotal{
    @JsonProperty("OrderLineID") 
    private int orderLineID;
    @JsonProperty("VolumeTypeID") 
    private int volumeTypeID;
    @JsonProperty("DisplayOrder") 
    private int displayOrder;
    @JsonProperty("Name") 
    private String name;
    @JsonProperty("Abbreviation") 
    private String abbreviation;
    @JsonProperty("Volume") 
    private double volume;
    @JsonProperty("OverrideVolume") 
    private Object overrideVolume;
    @JsonProperty("LineVolume") 
    private double lineVolume;
    @JsonProperty("Discounts") 
    private double discounts;
    @JsonProperty("Refunds") 
    private double refunds;
    @JsonProperty("OrderLineVolumeDiscounts") 
    private ArrayList<OrderLineVolumeDiscount> orderLineVolumeDiscounts;
}

