package com.astralbrands.flight.x3.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    /*----------OBJECT -> 'Orders' -> FIELDS--------------*/

    private String date;

    private String orderNumber;

    private String companyPrefix;

    private String customerName;

    private String customerOrderNumber;

    private String trackingNumber;

    private String deliveryNumber;

    private String shipDate;

    /*-------------SETTER METHODS-----------------*/

    public void setDate(String date) {this.date = date;}
    public void setOrderNumber(int orderNumber) {this.orderNumber = getString(orderNumber);}

    public void setCompanyPrefix(String companyPrefix) {this.companyPrefix = companyPrefix;}

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public void setCustomerOrderNumber(String custOrderNumber) {this.customerOrderNumber = custOrderNumber;}

    public void setTrackingNumber(String trackNumber) {this.trackingNumber = trackNumber;}

    public void setDeliveryNumber(String delivNumber) {this.deliveryNumber = delivNumber;}

    public void setShipDate(String shDate) {this.shipDate = shDate;}




    /*-------------GETTER FUNCTIONS-----------------*/

    public String getDate() {return this.date;}

    public String getOrderNumber() {return this.orderNumber;}

    public String getCompanyPrefix() {return this.companyPrefix;}

    public String getCustomerName() {return this.customerName;}

    public String getCustomerOrderNumber() {return this.customerOrderNumber;}

    public String getTrackingNumber() {return this.trackingNumber;}

    public String getDeliveryNumber() {return this.deliveryNumber;}

    public String getShipDate() {return this.shipDate;}


    /*
           Formats an SQL query to retrieve a brands' information
    */
    public String orderDetailsString() {
        return "OrderLine [orderNumber=" + orderNumber + ", companyPrefix=" + companyPrefix + ", customerName=" + customerName + ", customerOrderNumber=" + customerOrderNumber + "\n\r"
                + ", trackingNumber=" + trackingNumber + ", deliveryNumber=" + deliveryNumber + ", trackingNumber=" + trackingNumber + ", shipDate=" + shipDate +"]";
    }

    public static String getString(Object value) {
        return value != null ? value.toString() : "";
    }
}
