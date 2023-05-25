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
public class OrderTaxTotal{
    @JsonProperty("OrderID") 
    private int orderID;
    @JsonProperty("CountryTaxID") 
    private Object countryTaxID;
    @JsonProperty("ProvinceTaxID") 
    private int provinceTaxID;
    @JsonProperty("TaxName") 
    private String taxName;
    @JsonProperty("Tax") 
    private double tax;
    @JsonProperty("Taxable") 
    private double taxable;
}

