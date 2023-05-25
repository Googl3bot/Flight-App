package com.astralbrands.flight.x3.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderExportResponse{
 @JsonProperty("OrderNumber") 
 public String orderNumber;
 @JsonProperty("OrderDate") 
 public Date orderDate;
 @JsonProperty("ShipmentId") 
 public String shipmentId;
 @JsonProperty("ShipCountry") 
 public String shipCountry;
 @JsonProperty("ShipFirstName") 
 public String shipFirstName;
 @JsonProperty("ShipLastName") 
 public String shipLastName;
 @JsonProperty("ShipAddr1") 
 public String shipAddr1;
 @JsonProperty("ShipAddr2") 
 public String shipAddr2;
 @JsonProperty("ShipPostalCode") 
 public String shipPostalCode;
 @JsonProperty("ShipCity") 
 public String shipCity;
 @JsonProperty("ShipState") 
 public String shipState;
 @JsonProperty("Shipping") 
 public double shipping;
 @JsonProperty("Discount") 
 public double discount;
 @JsonProperty("Tax") 
 public double tax;
 @JsonProperty("Product") 
 public String product;
 @JsonProperty("ProductName") 
 public String productName;
 @JsonProperty("KitBuildableParentSKU") 
 public String kitBuildableParentSKU;
 @JsonProperty("KitBuildableIndex") 
 public String kitBuildableIndex;
 @JsonProperty("Quantity") 
 public int quantity;
 @JsonProperty("GrossPrice") 
 public double grossPrice;
}


