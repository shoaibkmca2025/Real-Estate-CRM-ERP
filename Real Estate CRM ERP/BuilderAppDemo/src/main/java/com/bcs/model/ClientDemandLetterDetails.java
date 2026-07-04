package com.bcs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class ClientDemandLetterDetails {
	
	@Id
	private int Id;
	
	@Transient
	private String ownerName;
	
	@Transient
	private String mobileNo;
	
	@Transient
	private String landlineNo;
	
	@Transient
	private String email;
	
	@Transient
	private String city;
	
	@Transient
	private String address;
	
	@Transient
	private String projectName;
	
	@Transient
	private String projectAddress;
	
	@Transient
	private String wingName;
	
	@Transient
	private int floorNumber;
	
	@Transient
	private String floorName;
	
	@Transient
	private int flatNumber;
	
	@Transient
	private double agreementAmount;
	
	@Transient
	private double totalPercentage;
	
	@Transient
	private double gstPercentage;	
	
	@Transient
	private double totalPaidAmount;
	
	@Transient
	private double prevRemainingAmount;
	
	@Transient
	private double outStandingAmount;
	
	@Transient
	private double gstAmount;	
	
	@Transient
	private String accountName;
	
	@Transient
	private String accountNumber;
	
	@Transient
	private String bankName;
	
	@Transient
	private String branchName;
	
	@Transient
	private String ifscCode;
	
	@Transient
	private String disbursementTitle;
	
	@Transient
	private String companyName;
	
	@Transient
	private String companyLogo;
	
	@Transient
	private String letterHeadName;
	
	@Transient
	private String companyAddress;
	
	public ClientDemandLetterDetails(){
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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

	public String getMobileNo() {
		if(mobileNo == null){
			return "";
		}
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getLandlineNo() {
		if(landlineNo == null){
			return "";
		}
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getEmail() {
		if(email == null){
			return "";
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		if(city == null){
			return "";
		}
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		if(address == null){
			return "";
		}
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getProjectAddress() {
		if(projectAddress == null){
			return "";
		}
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
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

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}

	

	public double getAgreementAmount() {
		return agreementAmount;
	}

	public void setAgreementAmount(double agreementAmount) {
		this.agreementAmount = agreementAmount;
	}

	public double getTotalPercentage() {
		return totalPercentage;
	}

	public void setTotalPercentage(double totalPercentage) {
		this.totalPercentage = totalPercentage;
	}

	public double getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(double gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	public double getTotalPaidAmount() {
		return totalPaidAmount;
	}

	public void setTotalPaidAmount(double totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public double getPrevRemainingAmount() {
		return prevRemainingAmount;
	}

	public void setPrevRemainingAmount(double prevRemainingAmount) {
		this.prevRemainingAmount = prevRemainingAmount;
	}

	public double getOutStandingAmount() {
		return outStandingAmount;
	}

	public void setOutStandingAmount(double outStandingAmount) {
		this.outStandingAmount = outStandingAmount;
	}

	public double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public String getAccountName() {
		if(accountName == null){
			return "";
		}
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		if(accountNumber == null){
			return "";
		}
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		if(bankName == null){
			return "";
		}
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		if(branchName == null){
			return "";
		}
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIfscCode() {
		if(ifscCode == null){
			return "";
		}
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getDisbursementTitle() {
		if(disbursementTitle == null){
			return "";
		}
		return disbursementTitle;
	}

	public void setDisbursementTitle(String disbursementTitle) {
		this.disbursementTitle = disbursementTitle;
	}

	public String getCompanyName() {
		if(companyName == null){
			return "";
		}
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyLogo() {
		if(companyLogo == null){
			return "";
		}
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getLetterHeadName() {
		if(letterHeadName == null){
			return "";
		}
		return letterHeadName;
	}

	public void setLetterHeadName(String letterHeadName) {
		this.letterHeadName = letterHeadName;
	}

	public String getCompanyAddress() {
		if(companyAddress == null){
			return "";
		}
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}	
}
