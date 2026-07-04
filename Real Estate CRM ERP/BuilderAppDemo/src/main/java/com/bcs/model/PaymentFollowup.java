package com.bcs.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="payment_followup")
public class PaymentFollowup {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="payment_followup_id")
	private int paymentFollowupId;
	
	@Column(name="payment_followup_descr")
	private String paymentFollowupDescription;
	
	@Column(name="disbursement_id")
	private int disbursementId;
	
	@Column(name="is_remove")
	private int isRemove;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private String disbursementFollowupDate;
	
	@Transient
	private String followupFlag;
	
	@Transient
	private double totalPartialAmount;
	
	@Transient
	private Date paymentFollowupDate;
	
	@Transient
	private int userType;
	
	@Transient
	private String clientName;
	
	@Transient
	private String projectName;
	
	@Transient
	private String mobileNo;
	
	@Transient
	private List<PaymentFollowup> paymentFollowupList;
	
	
	public PaymentFollowup(){
		
	}
	
	public PaymentFollowup(int paymentFollowupId, String paymentFollowupDescription, int disbursementId,
			String createdDatetime, String updatedDatetime) {
		super();
		this.paymentFollowupId = paymentFollowupId;
		this.paymentFollowupDescription = paymentFollowupDescription;
		this.disbursementId = disbursementId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	public int getPaymentFollowupId() {
		return paymentFollowupId;
	}

	public void setPaymentFollowupId(int paymentFollowupId) {
		this.paymentFollowupId = paymentFollowupId;
	}

	public String getPaymentFollowupDescription() {
		if(paymentFollowupDescription == null){
			return "";
		}
		return paymentFollowupDescription;
	}

	public void setPaymentFollowupDescription(String paymentFollowupDescription) {
		this.paymentFollowupDescription = paymentFollowupDescription;
	}

	public int getDisbursementId() {
		return disbursementId;
	}

	public void setDisbursementId(int disbursementId) {
		this.disbursementId = disbursementId;
	}

	public int getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
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

	public String getDisbursementFollowupDate() {
		if(disbursementFollowupDate == null){
			return "";
		}
		return disbursementFollowupDate;
	}

	public void setDisbursementFollowupDate(String disbursementFollowupDate) {
		this.disbursementFollowupDate = disbursementFollowupDate;
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

	public double getTotalPartialAmount() {
		return totalPartialAmount;
	}

	public void setTotalPartialAmount(double totalPartialAmount) {
		this.totalPartialAmount = totalPartialAmount;
	}

	public Date getPaymentFollowupDate() {
		return paymentFollowupDate;
	}

	public void setPaymentFollowupDate(Date paymentFollowupDate) {
		this.paymentFollowupDate = paymentFollowupDate;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
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

	public String getProjectName() {
		if(projectName == null){
			return "";
		}
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public List<PaymentFollowup> getPaymentFollowupList() {
		return paymentFollowupList;
	}

	public void setPaymentFollowupList(List<PaymentFollowup> paymentFollowupList) {
		this.paymentFollowupList = paymentFollowupList;
	}

	@Override
	public String toString() {
		return "PaymentFollowup [paymentFollowupId=" + paymentFollowupId + ", paymentFollowupDescription="
				+ paymentFollowupDescription + ", disbursementId=" + disbursementId + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + "]";
	}
	

}
