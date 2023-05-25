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
public class OrderLine{
    @JsonProperty("OrderID") 
    private int orderID;
    @JsonProperty("OrderLineID") 
    private int orderLineID;
    @JsonProperty("OrderLineStatusName") 
    private String orderLineStatusName;
    @JsonProperty("ProductID") 
    private int productID;
    @JsonProperty("SKU") 
    private String sKU;
    @JsonProperty("DisplayName") 
    private String displayName;
    @JsonProperty("ProductVariationOptionsDelimited") 
    private Object productVariationOptionsDelimited;
    @JsonProperty("WarehouseName") 
    private String warehouseName;
    @JsonProperty("WarehouseBinDisplayID") 
    private String warehouseBinDisplayID;
    @JsonProperty("Quantity") 
    private int quantity;
    @JsonProperty("OverrideQuantity") 
    private int overrideQuantity;
    @JsonProperty("Price") 
    private double price;
    @JsonProperty("OverridePrice") 
    private Object overridePrice;
    @JsonProperty("Tax") 
    private double tax;
    @JsonProperty("OverrideTax") 
    private Object overrideTax;
    @JsonProperty("ShippingPrice") 
    private double shippingPrice;
    @JsonProperty("HandlingPrice") 
    private double handlingPrice;
    @JsonProperty("Discounts") 
    private double discounts;
    @JsonProperty("SequenceNumber") 
    private int sequenceNumber;
    @JsonProperty("HasPersonalizations") 
    private boolean hasPersonalizations;
    @JsonProperty("IsBuildable") 
    private boolean isBuildable;
    @JsonProperty("BuildableKitOrderLineID") 
    private Object buildableKitOrderLineID;
    @JsonProperty("LinePrice") 
    private double linePrice;
    @JsonProperty("LineTax") 
    private double lineTax;
    @JsonProperty("Subtotal") 
    private double subtotal;
    @JsonProperty("LineTotal") 
    private double lineTotal;
    @JsonProperty("CurrencyTypeID") 
    private int currencyTypeID;
    @JsonProperty("CurrencySymbol") 
    private String currencySymbol;
    @JsonProperty("OrderLineStatusID") 
    private int orderLineStatusID;
    @JsonProperty("ProductBusinessUnitID") 
    private Object productBusinessUnitID;
    @JsonProperty("TrackingNumberLinks") 
    private ArrayList<Object> trackingNumberLinks;
    @JsonProperty("TrackingNumbers") 
    private ArrayList<Object> trackingNumbers;
    @JsonProperty("OrderLineVolumeTotals") 
    private ArrayList<OrderLineVolumeTotal> orderLineVolumeTotals;
    @JsonProperty("OrderLineShipments") 
    private ArrayList<OrderLineShipment> orderLineShipments;
    @JsonProperty("OrderLineDiscounts") 
    private ArrayList<OrderLineDiscount> orderLineDiscounts;
    @JsonProperty("GiftCards") 
    private ArrayList<Object> giftCards;
    @JsonProperty("TaxInDisplayPrice") 
    private boolean taxInDisplayPrice;
}

