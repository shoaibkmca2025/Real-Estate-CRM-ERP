package com.bcs.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="company_payment_details")
public class PaymentDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_id")
	private int paymentId;
	
	/*@Column(name="user_id")
	private int userId;*/
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="payment_type")
	private int paymentType;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="end_date")
	private String endDate;
	
	@Column(name="paid_date")
	private String paidDate;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="discount")
	private double discount;
	
	@Column(name="gst")
	private double gst;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	@Column(name="send_invoice_date")
	private String sendInvoiceDate;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private String paymentTypeName;
	
	@Transient
	private String userName;
	
	@Transient
	private String userMobile;
	
	@Transient
	private String companyName;
	
	@Transient
	private String projectName;
	
	@Transient
	private String companyEmail;
	
	@Transient
	private String mobile;
	
	@Transient
	private String userEmail;
	
	@Transient
	private String website;
	
	@Transient
	private String address;
	
	@Transient
	private String landline;
	
	@Transient
	private int editFlag;
	
	@Transient
	private Date startDate1;
	
	@Transient
	private Date endDate1;
	
	@Transient
	private Date paidDate1;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

/*	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public String getStartDate() {
		if(startDate == null){
			return "";
		}
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		if(endDate == null){
			return "";
		}
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getSendInvoiceDate() {
		if(sendInvoiceDate == null){
			return "";
		}
		return sendInvoiceDate;
	}

	public void setSendInvoiceDate(String sendInvoiceDate) {
		this.sendInvoiceDate = sendInvoiceDate;
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

	public String getPaymentTypeName() {
		if(paymentTypeName == null){
			return "";
		}
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}

	public String getUserName() {
		if(userName == null){
			return "";
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		if(userMobile == null){
			return "";
		}
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCompanyEmail() {
		if(companyEmail == null){
			return "";
		}
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getMobile() {
		if(mobile == null){
			return "";
		}
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserEmail() {
		if(userEmail == null){
			return "";
		}
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getWebsite() {
		if(website == null){
			return "";
		}
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	public String getLandline() {
		if(landline == null){
			return "";
		}
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}
	
	public int getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
	}

	public Date getStartDate1() {
		return startDate1;
	}

	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}

	public Date getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}

	public Date getPaidDate1() {
		return paidDate1;
	}

	public void setPaidDate1(Date paidDate1) {
		this.paidDate1 = paidDate1;
	}

	public PaymentDetails(){
	}

	public PaymentDetails(int paymentId, int projectId, int paymentType, String startDate, String endDate, String paidDate, double amount,
			double discount,double gst, double totalAmount, String sendInvoiceDate, String createdDatetime, String updatedDatetime) {
		super();
		this.paymentId = paymentId;
		//this.userId = userId;
		this.projectId = projectId;
		this.paymentType = paymentType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.paidDate = paidDate;
		this.amount = amount;
		this.discount = discount;
		this.gst = gst;
		this.totalAmount = totalAmount;
		this.sendInvoiceDate = sendInvoiceDate;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	@Override
	public String toString() {
		return "PaymentDetails [paymentId=" + paymentId + ",  projectId=" + projectId + ",  paymentType=" + paymentType
				+ ", startDate=" + startDate + ", endDate=" + endDate + ",paidDate=" + paidDate + ", amount=" + amount + ", discount=" + discount 
				+ ", gst=" + gst + ", totalAmount=" + totalAmount + ", sendInvoiceDate=" + sendInvoiceDate + ", createdDatetime=" + createdDatetime
				+ ", updatedDatetime=" + updatedDatetime + "]";
	}
	
	//userId=" + userId + ",
	
}
