package com.bcs.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="client")
public class Client {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="client_id")
	private int clientId;
	
	@Column(name="owner_name")
	private String ownerName;
		
	@Column(name="date_of_birth")
	private String dateOfBirth;
	
	@Column(name="pan_number")
	private String panNumber;
	
	@Column(name="aadhar_number")
	private String aadharNumber;
	
	@Column(name="enquiry_id")
	private int clientEnquiryId;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="wing_id")
	private int wingId;
	
	@Column(name="property_type_id")
	private int propertyTypeId;
	
	@Column(name="property_id")
	private int propertyId;
	
	@Column(name="property_area")
	private double propertyArea;
	
	@Column(name="floor_number")
	private int floorNumber;
	
	@Column(name="floor_name")
	private String floorName;
	
	@Column(name="flat_number")
	private int flatNumber;	
	
	@Column(name="rate")
	private double rate;
	
	@Column(name="infrastructure_amount")
	private double infrastructureAmount;
	
	@Column(name="agreement_amount")
	private double agreementAmount;
	
	@Column(name="gst_percentage")
	private double gstPercentage;
	
	@Column(name="client_tran_id")
	private int clientTranId;
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="is_paid")
	private int isPaid;	
	
	@Column(name="booking_status")
	private int bookingStatus;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Column(name="registration_no")
	private String registrationNo;
	
	@Column(name="registration_date")
	private String registrationDate;
	
	@Column(name="booking_date")
	private String bookingDate;
	
	@Column(name="remark")
	private String remark;
	
	@Transient
	private String clientName;	
	
	@Transient
	private String email;
	
	@Transient
	private String mobileNo;
	
	@Transient
	private String landlineNo;
	
	@Transient
	private String city;
	
	@Transient
	private String address;
	
	@Transient
	private String occupation;
	
	@Transient
	private String company;
	
	@Transient
	private int budgetMax;
	
	@Transient
	private String reference;
	
	@Transient
	private String referenceName;
	
	@Transient
	private String projectName;
	
	@Transient
	private String projectAddress;
	
	@Transient
	private String propertyTypeDescr;
	
	@Transient
	private String propertyName;
	
	@Transient
	private String wingName;
	
	@Transient
	private String dueDate;
	
	@Transient
	private double enqPropertyArea;
	
	@Transient
	private String enqPropertyTypeName;
	
	@Transient
	private String enqPropertyName;	
	
	@Transient
	private List<Disbursement> disbursementList;
	
	@Transient
	private List<OtherPayments> otherPaymentList;
	
	@Transient
	private double remainingAmount;
	
	@Transient
	private double totalPaidAmount;
	
	@Transient
	private int disbursementId;
	
	@Transient
	private String followupFlag;
	
	@Transient
	private Date birthDate;
	
	@Transient
	private Date regDate;
	
	@Transient
	private Date bookingDate1;
	
	@Transient
	private double bookingAmount;
	
	@Transient
	private String chequeNo;
	
	@Transient
	private Date chequeDate;
	
	@Transient
	private String chequeDateString;
	
	@Transient
	private String title;
	
	@Transient
	private String sendPdfDate;
	
	@Transient
	private int userType;
	
	
	public Client(){
		
	}

	public Client(int clientId, String dateOfBirth, String panNumber, String aadharNumber, int clientEnquiryId,
			int projectId, int wingId, int propertyTypeId, int propertyId, double propertyArea, int floorNumber,
			int flatNumber, double agreementAmount, double gstPercentage, int clientTranId, int userId, int isPaid,
			String createdDatetime, String updatedDatetime, String clientName, String projectName,
			String propertyTypeDescr, String propertyName, String wingName) {
		super();
		this.clientId = clientId;
		this.dateOfBirth = dateOfBirth;
		this.panNumber = panNumber;
		this.aadharNumber = aadharNumber;
		this.clientEnquiryId = clientEnquiryId;
		this.projectId = projectId;
		this.wingId = wingId;
		this.propertyTypeId = propertyTypeId;
		this.propertyId = propertyId;
		this.propertyArea = propertyArea;
		this.floorNumber = floorNumber;
		this.flatNumber = flatNumber;
		this.agreementAmount = agreementAmount;
		this.gstPercentage = gstPercentage;
		this.clientTranId = clientTranId;
		this.userId = userId;
		this.isPaid = isPaid;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.clientName = clientName;
		this.projectName = projectName;
		this.propertyTypeDescr = propertyTypeDescr;
		this.propertyName = propertyName;
		this.wingName = wingName;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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

	public String getDateOfBirth() {
		if(dateOfBirth == null){
			return "";
		}
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPanNumber() {
		if(panNumber == null){
			return "";
		}
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharNumber() {
		if(aadharNumber == null){
			return "";
		}
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public int getClientEnquiryId() {
		return clientEnquiryId;
	}

	public void setClientEnquiryId(int clientEnquiryId) {
		this.clientEnquiryId = clientEnquiryId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		this.wingId = wingId;
	}

	public int getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(int propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public double getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(double propertyArea) {
		this.propertyArea = propertyArea;
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

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	
	public double getInfrastructureAmount() {
		return infrastructureAmount;
	}

	public void setInfrastructureAmount(double infrastructureAmount) {
		this.infrastructureAmount = infrastructureAmount;
	}

	public double getAgreementAmount() {
		return agreementAmount;
	}

	public void setAgreementAmount(double agreementAmount) {
		this.agreementAmount = agreementAmount;
	}

	public double getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(double gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	public int getClientTranId() {
		return clientTranId;
	}

	public void setClientTranId(int clientTranId) {
		this.clientTranId = clientTranId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}

	public int getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getCreatedDatetime() {
		if(createdDatetime == null){
			return "";
		}
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedDatetime() {
		if(updatedDatetime == null){
			return "";
		}
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public String getRegistrationNo() {
		if(registrationNo == null){
			return "";
		}
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getRegistrationDate() {
		if(registrationDate == null){
			return "";
		}
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getBookingDate() {
		if(bookingDate == null){
			return "";
		}
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClientName() {
		if(clientName == null){
			return "";
		}
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
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

	public String getOccupation() {
		if(occupation == null){
			return "";
		}
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCompany() {
		if(company == null){
			return "";
		}
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getBudgetMax() {
		return budgetMax;
	}

	public void setBudgetMax(int budgetMax) {
		this.budgetMax = budgetMax;
	}

	public String getReference() {
		if(reference == null){
			return "";
		}
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getReferenceName() {
		if(referenceName == null){
			return "";
		}
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
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

	public String getPropertyTypeDescr() {
		if(propertyTypeDescr == null){
			return "";
		}
		return propertyTypeDescr;
	}

	public void setPropertyTypeDescr(String propertyTypeDescr) {
		this.propertyTypeDescr = propertyTypeDescr;
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

	public String getWingName() {
		if(wingName == null){
			return "";
		}
		return wingName;
	}

	public void setWingName(String wingName) {
		this.wingName = wingName;
	}

	public String getDueDate() {
		if(dueDate == null){
			return "";
		}
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public double getEnqPropertyArea() {
		return enqPropertyArea;
	}

	public void setEnqPropertyArea(double enqPropertyArea) {
		this.enqPropertyArea = enqPropertyArea;
	}

	public String getEnqPropertyTypeName() {
		if(enqPropertyTypeName == null){
			return "";
		}
		return enqPropertyTypeName;
	}

	public void setEnqPropertyTypeName(String enqPropertyTypeName) {
		this.enqPropertyTypeName = enqPropertyTypeName;
	}

	public String getEnqPropertyName() {
		if(enqPropertyName == null){
			return "";
		}
		return enqPropertyName;
	}

	public void setEnqPropertyName(String enqPropertyName) {
		this.enqPropertyName = enqPropertyName;
	}

	public List<Disbursement> getDisbursementList() {	
		return disbursementList;
	}

	public void setDisbursementList(List<Disbursement> disbursementList) {
		this.disbursementList = disbursementList;
	}

	public List<OtherPayments> getOtherPaymentList() {		
		return otherPaymentList;
	}

	public void setOtherPaymentList(List<OtherPayments> otherPaymentList) {
		this.otherPaymentList = otherPaymentList;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	
	public double getTotalPaidAmount() {
		return totalPaidAmount;
	}

	public void setTotalPaidAmount(double totalPaidAmount) {
		this.totalPaidAmount = totalPaidAmount;
	}

	public int getDisbursementId() {
		return disbursementId;
	}

	public void setDisbursementId(int disbursementId) {
		this.disbursementId = disbursementId;
	}


	public String getFollowupFlag() {
		if(followupFlag == null){
			return "";
		}
		return followupFlag;
	}

	public void setFollowupFlag(String followupFlag) {
		this.followupFlag = followupFlag;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getBookingDate1() {			
		return bookingDate1;
	}

	public void setBookingDate1(Date bookingDate1) {
		this.bookingDate1 = bookingDate1;
	}

	public double getBookingAmount() {
		return bookingAmount;
	}

	public void setBookingAmount(double bookingAmount) {
		this.bookingAmount = bookingAmount;
	}

	public String getChequeNo() {
		if(chequeNo == null){
			return "";
		}
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Date getChequeDate() {		
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public String getChequeDateString() {
		if(chequeDateString == null){
			return "";
		}
		return chequeDateString;
	}

	public void setChequeDateString(String chequeDateString) {
		this.chequeDateString = chequeDateString;
	}

	public String getTitle() {
		if(title == null){
			return "";
		}
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSendPdfDate() {
		if(sendPdfDate == null){
			return "";
		}
		return sendPdfDate;
	}

	public void setSendPdfDate(String sendPdfDate) {
		this.sendPdfDate = sendPdfDate;
	}


	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", dateOfBirth=" + dateOfBirth + ", panNumber=" + panNumber
				+ ", aadharNumber=" + aadharNumber + ", clientEnquiryId=" + clientEnquiryId + ", projectId=" + projectId
				+ ", wingId=" + wingId + ", propertyTypeId=" + propertyTypeId + ", propertyId=" + propertyId
				+ ", propertyArea=" + propertyArea + ", floorNumber=" + floorNumber + ", flatNumber=" + flatNumber
				+ ", agreementAmount=" + agreementAmount + ", gstPercentage=" + gstPercentage + ", clientTranId="
				+ clientTranId + ", userId=" + userId + ", isPaid=" + isPaid + ", createdDatetime=" + createdDatetime
				+ ", updatedDatetime=" + updatedDatetime + ", clientName=" + clientName + ", projectName=" + projectName
				+ ", propertyTypeDescr=" + propertyTypeDescr + ", propertyName=" + propertyName + ", wingName="
				+ wingName + "]";
	}
	
}
