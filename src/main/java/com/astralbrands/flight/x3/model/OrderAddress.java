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
public class OrderAddress{
    @JsonProperty("AddressID") 
    private int addressID;
    @JsonProperty("FirstName") 
    private String firstName;
    @JsonProperty("LastName") 
    private String lastName;
    @JsonProperty("LastName2") 
    private Object lastName2;
    @JsonProperty("Company") 
    private Object company;
    @JsonProperty("NickName") 
    private String nickName;
    @JsonProperty("Street1") 
    private String street1;
    @JsonProperty("Street2") 
    private Object street2;
    @JsonProperty("Street3") 
    private Object street3;
    @JsonProperty("Street4") 
    private Object street4;
    @JsonProperty("City") 
    private String city;
    @JsonProperty("ProvinceID") 
    private int provinceID;
    @JsonProperty("PhoneNumber") 
    private Object phoneNumber;
    @JsonProperty("Province") 
    private Province province;
    @JsonProperty("PostalCode") 
    private String postalCode;
}

