package com.astralbrands.flight.x3.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodaysOrders {

        /*----------OBJECT -> 'Orders' -> HEADER FIELDS--------------*/

        private String flag;

        private String salesSite;

        private String orderType;

        private String orderNumber;

        private String warehouseName;

        private String orderDate;

        private String shipmentId;

        private String shippingSite;

        private String currency;

        private String customerName;

        private String billingAddress;

        private String shippingAddress;

        private String shippingFee;

        private String discountPrice;

        private String taxFee;

        private String paymentType;


        /*-------------PRODUCT ORDER LINE FIELDS-------------*/

        private String skuId;

        private String buildableSkuId;

        private String productName;

        private String productType;

        private String quantity;

        private String productPrice;

        /*-------------SETTER METHODS-----------------*/

        //public void setOrderType(String orderSite) {this.orderType = orderSite;}
        public void setFlag(String header) {this.flag = header;}

        public void setSalesSite(String salesSite1) {this.salesSite = salesSite1;}

        public void setOrderType(String orderSite) {this.orderType = orderSite;}

        public void setOrderNumber(String ordNumber) {this.orderNumber = ordNumber;}

        public void setWarehouseName(String wareHname) {this.warehouseName = wareHname;}

        public void setOrderDate(String date) {this.orderDate = date;}

        public void setShipmentId(String shipId) {this.shipmentId = shipId;}

        public void setShippingSite(String shipSite) {this.shippingSite = shipSite;}

        public void setCurrency(String currencyType) {this.currency = currencyType;}

        public void setCustomerName(String custName) {this.customerName = custName;}

        public void setBillingAddress(String billAddress) {this.billingAddress = billAddress;}

        public void setShippingAddress(String shipAddress) {this.shippingAddress = shipAddress;}

        public void setShippingFee(String shipFee) {this.shippingFee = shipFee;}

        public void setDiscountPrice(String discountTotal) {this.discountPrice = discountTotal;}

        public void setTaxFee(String taxPrice) {this.taxFee = taxPrice;}

        public void setPaymentType(String payment) {this.paymentType = payment;}

        /*---------PRODUCT LINE SETTER METHODS-------------*/

        public void setSkuId(String id) {this.skuId = id;}

        public void setBuildableSkuId(String sku) {this.buildableSkuId = sku;}

        public void setProductName(String prodName) {this.productName = prodName;}

        public void setProductType(String prodType) {this.productType = prodType;}

        public void setQuantity(String qty) {this.quantity = qty;}

        public void setProductPrice(String prodPrice) {this.productPrice = prodPrice;}


        /*-------------GETTER FUNCTIONS-----------------*/

        public String getFlag() {return flag;}

        public String getSalesSite() {return salesSite;}

        public String getOrderType() {return orderType;}

        public String getOrderNumber() {return orderNumber;}

        public String getWarehouseName() {return warehouseName;}

        public String getOrderDate() {return orderDate;}

        public String getShipmentId() {return shipmentId;}

        public String getShippingSite() {return shippingSite;}

        public String getCurrency() {return currency;}

        public String getCustomerName() {return customerName;}

        public String getBillingAddress() {return billingAddress;}

        public String getShippingAddress() {return shippingAddress;}

        public String getShippingFee() {return shippingFee;}

        public String getDiscountPrice() {return discountPrice;}

        public String getTaxFee() {return taxFee;}

        public String getPaymentType() {return paymentType;}


        /*---------PRODUCT LINE SETTER METHODS-------------*/

    public String getSkuId() {return skuId;}

    public String getBuildableSkuId() {
        return buildableSkuId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getProductPrice() {
        return productPrice;
    }

    /*
                                               Formats an SQL query to retrieve a brands' information
                                        */
//        public String orderDetailsString(String orderDetails) {
//            String headerLine = "";
//            String productLine = "";
//            if(orderDetails.startsWith("E")) {
//
//            }
//
//        }

        public static String getString(Object value) {
            return value != null ? value.toString() : "";
        }

}
