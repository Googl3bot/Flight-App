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
public class BillingAddress{
    @JsonProperty("AddressID") 
    private int addressID;
    @JsonProperty("FirstName") 
    private Object firstName;
    @JsonProperty("LastName") 
    private Object lastName;
    @JsonProperty("LastName2") 
    private Object lastName2;
    @JsonProperty("Company") 
    private Object company;
    @JsonProperty("NickName") 
    private Object nickName;
    @JsonProperty("City") 
    private String city;
    @JsonProperty("DisplayName") 
    private Object displayName;
    @JsonProperty("CountryID") 
    private int countryID;
    @JsonProperty("CountryName") 
    private String countryName;
    @JsonProperty("CountryTwoLetterISO") 
    private Object countryTwoLetterISO;
    @JsonProperty("PhoneNumberFormat") 
    private Object phoneNumberFormat;
    @JsonProperty("ProvinceID") 
    private int provinceID;
    @JsonProperty("County") 
    private Object county;
    @JsonProperty("PostalCode") 
    private String postalCode;
    @JsonProperty("ProvinceAbbreviation") 
    private String provinceAbbreviation;
    @JsonProperty("ProvinceName") 
    private Object provinceName;
    @JsonProperty("Street1") 
    private String street1;
    @JsonProperty("Street2") 
    private String street2;
    @JsonProperty("Street3") 
    private String street3;
    @JsonProperty("Street4") 
    private String street4;
    @JsonProperty("Region") 
    private Object region;
    @JsonProperty("DeliveryOffice") 
    private Object deliveryOffice;
    @JsonProperty("Residential") 
    private boolean residential;
    @JsonProperty("IsValid") 
    private Object isValid;
    @JsonProperty("Primary") 
    private boolean primary;
    @JsonProperty("Mailing") 
    private boolean mailing;
    @JsonProperty("IsNexus") 
    private boolean isNexus;
    @JsonProperty("PhoneNumber") 
    private Object phoneNumber;
}

