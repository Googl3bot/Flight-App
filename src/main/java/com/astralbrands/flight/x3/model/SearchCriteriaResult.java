package com.astralbrands.flight.x3.model;

import java.util.Date;

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
public class SearchCriteriaResult{
    @JsonProperty("ShippingFileRunID") 
    private int shippingFileRunID;
    @JsonProperty("DateCreated") 
    private Date dateCreated;
    @JsonProperty("Warehouses") 
    private String warehouses;
    @JsonProperty("CreatedBy") 
    private String createdBy;
    @JsonProperty("OrderCount") 
    private int orderCount;
    @JsonProperty("TotalOrderValue") 
    private double totalOrderValue;
    @JsonProperty("CurrencyTypeID") 
    private int currencyTypeID;
    @JsonProperty("Countries") 
    private String countries;
    @JsonProperty("Consolidation") 
    private String consolidation;
    @JsonProperty("DatePrinted") 
    private Object datePrinted;
    @JsonProperty("CountCurrencyTypes") 
    private int countCurrencyTypes;
    @JsonProperty("BusinessUnitName") 
    private String businessUnitName;
    @JsonProperty("ShowBusinessUnits") 
    private boolean showBusinessUnits;
    @JsonProperty("RunStatusID") 
    private Object runStatusID;
    @JsonProperty("RunStatusName") 
    private String runStatusName;
}

