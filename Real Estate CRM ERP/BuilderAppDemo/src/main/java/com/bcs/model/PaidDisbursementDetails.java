package com.bcs.model;

import java.util.List;

import jakarta.persistence.Transient;

public class PaidDisbursementDetails {
	
	
	@Transient
	private int clientTranId;
	
	@Transient
	private String ownerName;	
	
	@Transient
	private String propertyName;
	
	@Transient
	private double propertyArea;
	
	@Transient
	private String mobileNo;
	
	@Transient
	private String paidDate;
	
	@Transient
	private double paidAmount;	
	
	@Transient
	private String wingName;
	
	@Transient
	private int flatNumber;
	
	@Transient
	private double disbursementAmount;	
	
	@Transient
	private double gstAmount;	
	
	@Transient
	private double remainingAmount;	
	
	@Transient
	private double agreementAmount;
	
	@Transient
	private double totalAmount;
	
	@Transient
	private String projectName;
	
	@Transient
	private List<OtherPayments> otherPaymentList;

public PaidDisbursementDetails(){
	
}

public int getClientTranId() {
	return clientTranId;
}

public void setClientTranId(int clientTranId) {
	this.clientTranId = clientTranId;
}

public String getOwnerName() {
	if(ownerName == null){
		return "";
	}
	return ownerName;
}

public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
}

public String getPropertyName() {
	if(propertyName == null){
		return "";
	}
	return propertyName;
}

public void setPropertyName(String propertyName) {
	this.propertyName = propertyName;
}

public double getPropertyArea() {
	return propertyArea;
}

public void setPropertyArea(double propertyArea) {
	this.propertyArea = propertyArea;
}

public String getMobileNo() {
	if(mobileNo == null){
		return "";
	}
	return mobileNo;
}

public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}

public String getPaidDate() {
	if(paidDate == null){
		return "";
	}
	return paidDate;
}

public void setPaidDate(String paidDate) {
	this.paidDate = paidDate;
}

public double getPaidAmount() {
	return paidAmount;
}

public void setPaidAmount(double paidAmount) {
	this.paidAmount = paidAmount;
}

public String getWingName() {
	if(wingName == null){
		return "";
	}
	return wingName;
}

public void setWingName(String wingName) {
	this.wingName = wingName;
}

public int getFlatNumber() {
	return flatNumber;
}

public void setFlatNumber(int flatNumber) {
	this.flatNumber = flatNumber;
}

public double getDisbursementAmount() {
	return disbursementAmount;
}

public void setDisbursementAmount(double disbursementAmount) {
	this.disbursementAmount = disbursementAmount;
}

public double getGstAmount() {
	return gstAmount;
}

public void setGstAmount(double gstAmount) {
	this.gstAmount = gstAmount;
}

public double getRemainingAmount() {
	return remainingAmount;
}

public void setRemainingAmount(double remainingAmount) {
	this.remainingAmount = remainingAmount;
}

public double getAgreementAmount() {
	return agreementAmount;
}

public void setAgreementAmount(double agreementAmount) {
	this.agreementAmount = agreementAmount;
}

public double getTotalAmount() {
	return totalAmount;
}

public void setTotalAmount(double totalAmount) {
	this.totalAmount = totalAmount;
}

public String getProjectName() {
	if(projectName == null){
		return "";
	}
	return projectName;
}

public void setProjectName(String projectName) {
	this.projectName = projectName;
}

public List<OtherPayments> getOtherPaymentList() {
	return otherPaymentList;
}

public void setOtherPaymentList(List<OtherPayments> otherPaymentList) {
	this.otherPaymentList = otherPaymentList;
}

@Override
public String toString() {
	return "PaidDisbursementDetails [clientTranId=" + clientTranId + ", ownerName=" + ownerName + ", propertyName="
			+ propertyName + ", propertyArea=" + propertyArea + ", mobileNo=" + mobileNo + ", paidDate=" + paidDate
			+ ", paidAmount=" + paidAmount + ", wingName=" + wingName + ", flatNumber=" + flatNumber
			+ ", disbursementAmount=" + disbursementAmount + ", gstAmount=" + gstAmount + ", remainingAmount="
			+ remainingAmount + ", agreementAmount=" + agreementAmount + ", totalAmount=" + totalAmount
			+ ", projectName=" + projectName + ", otherPaymentList=" + otherPaymentList + "]";
}


}
