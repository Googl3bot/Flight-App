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
public class Transaction{
	
    @JsonProperty("Date") 
    private Date date;
    @JsonProperty("Type") 
    private String type;
    @JsonProperty("CheckNumber") 
    private Object checkNumber;
    @JsonProperty("NameOnCheck") 
    private Object nameOnCheck;
    @JsonProperty("BankName") 
    private Object bankName;
    @JsonProperty("EcheckAccountType") 
    private Object echeckAccountType;
    @JsonProperty("NameOnAccount") 
    private Object nameOnAccount;
    @JsonProperty("BarcodeNumber") 
    private Object barcodeNumber;
    @JsonProperty("BarcodeUrl") 
    private Object barcodeUrl;
    @JsonProperty("DueDate") 
    private Date dueDate;
    @JsonProperty("FirstName") 
    private Object firstName;
    @JsonProperty("LastName") 
    private Object lastName;
    @JsonProperty("Email") 
    private Object email;
    @JsonProperty("ExternalPaymentNote") 
    private Object externalPaymentNote;
    @JsonProperty("LineVolumeRefunds") 
    private Object lineVolumeRefunds;
    @JsonProperty("BillingAddress") 
    private BillingAddress billingAddress;
    @JsonProperty("CardType") 
    private String cardType;
    @JsonProperty("Last4Digits") 
    private String last4Digits;
    @JsonProperty("ExpirationDate") 
    private String expirationDate;
    @JsonProperty("NameOnCard") 
    private String nameOnCard;
    @JsonProperty("ProcessTime") 
    private String processTime;
    @JsonProperty("Accepted") 
    private String accepted;
    @JsonProperty("Memo") 
    private Object memo;
    @JsonProperty("PaymentID") 
    private int paymentID;
    @JsonProperty("DisplayID") 
    private String displayID;
    @JsonProperty("PersonID") 
    private int personID;
    @JsonProperty("ReceiptDate") 
    private Object receiptDate;
    @JsonProperty("CaptureDate") 
    private Date captureDate;
    @JsonProperty("VoidDate") 
    private Object voidDate;
    @JsonProperty("Amount") 
    private double amount;
    @JsonProperty("AmountAppliedToOrder") 
    private double amountAppliedToOrder;
    @JsonProperty("Comment") 
    private Object comment;
    @JsonProperty("PaymentDate") 
    private Date paymentDate;
    @JsonProperty("TransactionID") 
    private String transactionID;
    @JsonProperty("Origination") 
    private String origination;
    @JsonProperty("ReferenceNo") 
    private String referenceNo;
    @JsonProperty("CreditMemoAmount") 
    private double creditMemoAmount;
    @JsonProperty("RefundedAmount") 
    private double refundedAmount;
    @JsonProperty("UnappliedAmount") 
    private double unappliedAmount;
    @JsonProperty("PaymentMethodID") 
    private int paymentMethodID;
    @JsonProperty("Detail1") 
    private String detail1;
    @JsonProperty("PaymentType") 
    private String paymentType;
    @JsonProperty("PaymentTypeIdentifier") 
    private String paymentTypeIdentifier;
    @JsonProperty("Voidable") 
    private boolean voidable;
    @JsonProperty("HasDetails") 
    private boolean hasDetails;
    @JsonProperty("TransactionType") 
    private String transactionType;
    @JsonProperty("Username") 
    private String username;
    @JsonProperty("Gateway") 
    private String gateway;
    @JsonProperty("Status") 
    private String status;
}

