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
public class Province{
    @JsonProperty("ProvinceID") 
    private int provinceID;
    @JsonProperty("CountryID") 
    private int countryID;
    @JsonProperty("CountryName") 
    private String countryName;
    @JsonProperty("Name") 
    private String name;
    @JsonProperty("Abbreviation") 
    private String abbreviation;
}

