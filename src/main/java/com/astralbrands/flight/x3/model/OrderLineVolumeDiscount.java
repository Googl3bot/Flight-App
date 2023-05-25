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
public class OrderLineVolumeDiscount{
    @JsonProperty("OrderLineID") 
    private int orderLineID;
    @JsonProperty("VolumeTypeID") 
    private int volumeTypeID;
    @JsonProperty("DiscountRuleID") 
    private int discountRuleID;
    @JsonProperty("Discount") 
    private double discount;
    @JsonProperty("OverrideDiscount") 
    private Object overrideDiscount;
    @JsonProperty("LineDiscount") 
    private double lineDiscount;
    @JsonProperty("ModificationState") 
    private int modificationState;
}

