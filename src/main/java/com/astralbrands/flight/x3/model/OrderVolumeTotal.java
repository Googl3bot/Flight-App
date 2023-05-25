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
public class OrderVolumeTotal{
    @JsonProperty("OrderID") 
    private int orderID;
    @JsonProperty("VolumeTypeID") 
    private int volumeTypeID;
    @JsonProperty("VolumeTypeDisplayOrder") 
    private int volumeTypeDisplayOrder;
    @JsonProperty("VolumeTypeName") 
    private String volumeTypeName;
    @JsonProperty("VolumeTypeAbbreviation") 
    private String volumeTypeAbbreviation;
    @JsonProperty("TotalVolume") 
    private double totalVolume;
    @JsonProperty("TotalDiscounts") 
    private double totalDiscounts;
    @JsonProperty("TotalRefunds") 
    private double totalRefunds;
}

