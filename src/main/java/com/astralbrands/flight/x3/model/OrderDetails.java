package com.astralbrands.flight.x3.model;

import java.util.ArrayList;
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
public class OrderDetails {
	@JsonProperty("OrderID")
	private int orderID;
	@JsonProperty("DisplayID")
	private String displayID;
	@JsonProperty("OrderDate")
	private Date orderDate;
	@JsonProperty("CommissionDate")
	private Date commissionDate;
	@JsonProperty("PostedDate")
	private Date postedDate;
	@JsonProperty("OrderStatusID")
	private int orderStatusID;
	@JsonProperty("OrderPaymentStatusID")
	private int orderPaymentStatusID;
	@JsonProperty("PersonID")
	private int personID;
	@JsonProperty("OrderTotal")
	private double orderTotal;
	@JsonProperty("Subtotal")
	private double subtotal;
	@JsonProperty("Locked")
	private boolean locked;
	@JsonProperty("ShipHold")
	private boolean shipHold;
	@JsonProperty("Commissionable")
	private boolean commissionable;
	@JsonProperty("ShoppingCartID")
	private int shoppingCartID;
	@JsonProperty("ShippingMethodID")
	private int shippingMethodID;
	@JsonProperty("LegalEntityPaymentMethodID")
	private int legalEntityPaymentMethodID;
	@JsonProperty("ShippingMethodPrice")
	private double shippingMethodPrice;
	@JsonProperty("ShippingOverridePrice")
	private Object shippingOverridePrice;
	@JsonProperty("ShipmentCount")
	private int shipmentCount;
	@JsonProperty("HandlingMethodID")
	private Object handlingMethodID;
	@JsonProperty("HandlingMethodPrice")
	private double handlingMethodPrice;
	@JsonProperty("HandlingOverridePrice")
	private Object handlingOverridePrice;
	@JsonProperty("TaxTotal")
	private double taxTotal;
	@JsonProperty("DiscountTotal")
	private double discountTotal;
	@JsonProperty("ConsultantPrice")
	private double consultantPrice;
	@JsonProperty("OrderOwnerDisplayName")
	private String orderOwnerDisplayName;
	@JsonProperty("OrderOwnerDisplayID")
	private String orderOwnerDisplayID;
	@JsonProperty("OrderOwnerPersonTypeName")
	private String orderOwnerPersonTypeName;
	@JsonProperty("OrderOwnerPersonTypeID")
	private int orderOwnerPersonTypeID;
	@JsonProperty("CommissionOwnerDisplayName")
	private String commissionOwnerDisplayName;
	@JsonProperty("CommissionOwnerDisplayID")
	private String commissionOwnerDisplayID;
	@JsonProperty("CommissionOwnerPersonTypeName")
	private String commissionOwnerPersonTypeName;
	@JsonProperty("CommissionOwnerPersonTypeID")
	private int commissionOwnerPersonTypeID;
	@JsonProperty("AddressCount")
	private int addressCount;
	@JsonProperty("ShoppingCartName")
	private String shoppingCartName;
	@JsonProperty("ShippingMethodName")
	private String shippingMethodName;
	@JsonProperty("OrderStatusName")
	private String orderStatusName;
	@JsonProperty("OrderPaymentStatusName")
	private String orderPaymentStatusName;
	@JsonProperty("TaxID")
	private Object taxID;
	@JsonProperty("EventDisplayID")
	private String eventDisplayID;
	@JsonProperty("EventOwnerDisplayID")
	private String eventOwnerDisplayID;
	@JsonProperty("SubscriptionIDs")
	private Object subscriptionIDs;
	@JsonProperty("SubscriptionRunDate")
	private Object subscriptionRunDate;
	@JsonProperty("BusinessUnitID")
	private int businessUnitID;
	@JsonProperty("TaxInDisplayPrice")
	private boolean taxInDisplayPrice;
	@JsonProperty("AllowUpdateOrder")
	private boolean allowUpdateOrder;
	@JsonProperty("AllowChangeParty")
	private boolean allowChangeParty;
	@JsonProperty("AllowChangeCommissionDate")
	private boolean allowChangeCommissionDate;
	@JsonProperty("AllowChangeCommissionOwner")
	private boolean allowChangeCommissionOwner;
	@JsonProperty("AllowChangeShipHoldStatus")
	private boolean allowChangeShipHoldStatus;
	@JsonProperty("HandlingMethodEnabled")
	private boolean handlingMethodEnabled;
	@JsonProperty("IsHostOrder")
	private boolean isHostOrder;
	@JsonProperty("OrderAddress")
	private OrderAddress orderAddress;
	@JsonProperty("OrderOwnerLink")
	private String orderOwnerLink;
	@JsonProperty("CommissionOwnerLink")
	private String commissionOwnerLink;
	@JsonProperty("GrandTotal")
	private double grandTotal;
	@JsonProperty("BalanceDueAfterPayments")
	private double balanceDueAfterPayments;
	@JsonProperty("BalanceDueAfterPaymentsRefundsCreditMemos")
	private double balanceDueAfterPaymentsRefundsCreditMemos;
	@JsonProperty("TotalPayments")
	private double totalPayments;
	@JsonProperty("TotalRefunds")
	private double totalRefunds;
	@JsonProperty("TotalCreditMemos")
	private double totalCreditMemos;
	@JsonProperty("ShippingPrice")
	private double shippingPrice;
	@JsonProperty("HandlingPrice")
	private double handlingPrice;
	@JsonProperty("CurrencyCulture")
	private String currencyCulture;
	@JsonProperty("CurrencyTypeID")
	private int currencyTypeID;
	@JsonProperty("CurrencySymbol")
	private String currencySymbol;
	@JsonProperty("OrderVolumeTotals")
	private ArrayList<OrderVolumeTotal> orderVolumeTotals;
	@JsonProperty("OrderLines")
	private ArrayList<OrderLine> orderLines;
	@JsonProperty("OrderNotes")
	private ArrayList<Object> orderNotes;
	@JsonProperty("OrderPayments")
	private ArrayList<OrderPayment> orderPayments;
	@JsonProperty("Transactions")
	private ArrayList<Transaction> transactions;
	@JsonProperty("OrderTaxTotals")
	private ArrayList<OrderTaxTotal> orderTaxTotals;
}
