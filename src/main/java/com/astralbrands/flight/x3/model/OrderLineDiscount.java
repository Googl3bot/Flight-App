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
public class OrderLineDiscount{
    @JsonProperty("OrderLineID") 
    private int orderLineID;
    @JsonProperty("DiscountRuleID") 
    private int discountRuleID;
    @JsonProperty("DiscountRuleTypeID") 
    private int discountRuleTypeID;
    @JsonProperty("Discount") 
    private double discount;
    @JsonProperty("OverrideDiscount") 
    private Object overrideDiscount;
    @JsonProperty("LineDiscount") 
    private double lineDiscount;
    @JsonProperty("PromotionalRewardGroupRewardID") 
    private int promotionalRewardGroupRewardID;
    @JsonProperty("PartyHostRewardGroupRewardID") 
    private Object partyHostRewardGroupRewardID;
    @JsonProperty("CouponID") 
    private int couponID;
    @JsonProperty("CouponCode") 
    private String couponCode;
    @JsonProperty("ModificationState") 
    private int modificationState;
}

