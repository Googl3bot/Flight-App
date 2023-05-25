package com.astralbrands.flight.x3.model;

import com.opencsv.bean.CsvBindByName;

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
public class Order {
	
	@CsvBindByName(column = "Sales Site")
	private String salesSite;
	
	@CsvBindByName(column = "Order Type")
	private String orderType;
	
	@CsvBindByName(column = "Order Number")
	private String orderNumber;
	
	@CsvBindByName(column = "Sold To")
	private String soldTo;
	
	@CsvBindByName(column = "Order Date")
	private String orderDate;
	
	@CsvBindByName(column = "Shipment ID")
	private String shipentId;
	
	@CsvBindByName(column = "Shipping Site")
	private String shippingSite;
	
	@CsvBindByName(column = "Currency")
	private String currency;
	
	@CsvBindByName(column = "Project")
	private String project;
	
	@CsvBindByName(column = "Header Text")
	private String headerText;
	
	@CsvBindByName(column = "Footer Text")
	private String footerText;
	
	@CsvBindByName(column = "BillFirstName")
	private String billingFirstName;
	
	@CsvBindByName(column = "BillLastName")
	private String billLastName;
	
	@CsvBindByName(column = "BillAddr1")
	private String billAddr1;
	
	@CsvBindByName(column = "BillAddr2")
	private String billAddr2;
	
	@CsvBindByName(column = "BillPostalCode")
	private String billPostalCode;
	
	@CsvBindByName(column = "BillCity")
	private String billCity;
	
	@CsvBindByName(column = "BillState")
	private String billState;
	
	@CsvBindByName(column = "ShipFirstName")
	private String shipFirstName;
	
	@CsvBindByName(column = "ShipLastName")
	private String shipLastName;
	
	@CsvBindByName(column = "ShipAddr1")
	private String shipAddr1;
	
	@CsvBindByName(column = "ShipAddr2")
	private String shipAddr2;
	
	@CsvBindByName(column = "Postal")
	private String postal;
	
	@CsvBindByName(column = "City")
	private String city;
	
	@CsvBindByName(column = "State")
	private String state;
	
	@CsvBindByName(column = "Shipping")
	private String shipping;
	
	@CsvBindByName(column = "Discount")
	private String discount;
	
	@CsvBindByName(column = "Tax")
	private String tax;
	
	@CsvBindByName(column = "Delivery Method")
	private String deliveryMethod;
	
	@CsvBindByName(column = "Carrier")
	private String carrier;
	
	@CsvBindByName(column = "Product")
	private String product;
	
	@CsvBindByName(column = "Product Name")
	private String productName;
	
	@CsvBindByName(column = "Sales Unit")
	private String salesUnit;
	
	@CsvBindByName(column = "Quantity Ordered")
	private String quantity;
	
	@CsvBindByName(column = "Gross Price")
	private String grossPrice;
	
	@CsvBindByName(column = "Discount Charge 1")
	private String discountCharge1;
	
	@CsvBindByName(column = "Discount Charge 2")
	private String discountCharge2;
	
	@CsvBindByName(column = "Discount Charge 3")
	private String discountCharge3;
	
	@CsvBindByName(column = "Line Text")
	private String lineText;

	
/*
	"","","","","","","","",
	"","","","","","","","","","","",
	"","","","","","Discount Charge 3",
	"","Line Text"*/
}
