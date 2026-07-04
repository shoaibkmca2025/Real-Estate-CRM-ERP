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
@Table(name="disbursement")
public class Disbursement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="disbursement_id")
	private int disbursementId;
	
	@Column(name="client_id")
	private int clientId;
	
	@Column(name="percentage_value")
	private double percentageValue;
	
	@Column(name="disbursement_amount")
	private double disbursementAmount;	
	
	@Column(name="gst_amount")
	private double gstAmount;
	
	@Column(name="prev_remaining_amount")
	private double prevRemainingAmount;	
	
	@Column(name="total_amount")
	private double totalAmount;	
	
	@Column(name="paid_amount")
	private double paidAmount;
	
	@Column(name="remaining_amount")
	private double remainingAmount;
	
	@Column(name="send_pdf_date")
	private String sendPdfDate;	
		
	@Column(name="demand_letter_pdf_path")
	private String demandLetterPdfPath;	
	
	@Column(name="disbursement_followup_date")
	private String disbursementFollowupDate;

	@Column(name="project_disbursement_id")
	private int projectDisbursementId;
	
	@Column(name="paid_date")
	private String paidDate;
	
	@Column(name="disbursement_description")
	private String disbursementDescription;	
			
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;	
	
	@Transient
	private String disbursementTitle;
	
	@Transient
	private int paymentType;
	
	@Transient
	private String completionDate;
		
	@Transient
	private int projectId;
	
	@Transient
	private int companyId;
	
	@Transient
	private String clientEmail;
	
	@Transient
	private String projectName;
	
	@Transient
	private List<String> disbursementIdList;
	
	@Transient
	private double totalPartialAmount;	
	
	@Transient
	private double partialGrossAmount;	
	
	@Transient
	private double partialGstAmount;	
	
	@Transient
	private List<DisbursementPartialPayments> partialPaymentsList;
	
	@Transient
	private int partialPaymentId;
	
	@Transient
	private double prevPaidAmount;
	
	@Transient
	private double gstPercentage;
	
	@Transient
	private String recipientEmailsCc;
	
	@Transient
	private String recipientEmailsBcc;
	
	@Transient
	private Date date;
	
	@Transient
	private int userType;
	
	@Transient
	private Date followupDateAsDate;
	
	public Disbursement(){
		
	}

	public Disbursement(int disbursementId, int clientId, double percentageValue, double disbursementAmount,
			double gstAmount, double prevRemainingAmount, double totalAmount, double paidAmount, double remainingAmount,
			String sendPdfDate, String demandLetterPdfPath, String disbursementFollowupDate, int projectDisbursementId,
			String paidDate, String disbursementDescription, int userId, String createdDatetime,
			String updatedDatetime) {
		super();
		this.disbursementId = disbursementId;
		this.clientId = clientId;
		this.percentageValue = percentageValue;
		this.disbursementAmount = disbursementAmount;
		this.gstAmount = gstAmount;
		this.prevRemainingAmount = prevRemainingAmount;
		this.totalAmount = totalAmount;
		this.paidAmount = paidAmount;
		this.remainingAmount = remainingAmount;
		this.sendPdfDate = sendPdfDate;
		this.demandLetterPdfPath = demandLetterPdfPath;
		this.disbursementFollowupDate = disbursementFollowupDate;
		this.projectDisbursementId = projectDisbursementId;
		this.paidDate = paidDate;
		this.disbursementDescription = disbursementDescription;
		this.userId = userId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	public int getDisbursementId() {
		return disbursementId;
	}

	public void setDisbursementId(int disbursementId) {
		this.disbursementId = disbursementId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public double getPercentageValue() {
		return percentageValue;
	}

	public void setPercentageValue(double percentageValue) {
		this.percentageValue = percentageValue;
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

	public double getPrevRemainingAmount() {
		return prevRemainingAmount;
	}

	public void setPrevRemainingAmount(double prevRemainingAmount) {
		this.prevRemainingAmount = prevRemainingAmount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public double getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
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

	public String getDemandLetterPdfPath() {
		if(demandLetterPdfPath == null){
			return "";
		}
		return demandLetterPdfPath;
	}

	public void setDemandLetterPdfPath(String demandLetterPdfPath) {
		this.demandLetterPdfPath = demandLetterPdfPath;
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

	public int getProjectDisbursementId() {
		return projectDisbursementId;
	}

	public void setProjectDisbursementId(int projectDisbursementId) {
		this.projectDisbursementId = projectDisbursementId;
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

	public String getDisbursementDescription() {
		if(disbursementDescription == null){
			return "";
		}
		return disbursementDescription;
	}

	public void setDisbursementDescription(String disbursementDescription) {
		this.disbursementDescription = disbursementDescription;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getDisbursementTitle() {
		if(disbursementTitle == null){
			return "";
		}
		return disbursementTitle;
	}

	public void setDisbursementTitle(String disbursementTitle) {
		this.disbursementTitle = disbursementTitle;
	}

	public int getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompletionDate() {
		if(completionDate == null){
			return "";
		}
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getClientEmail() {
		if(clientEmail == null){
			return "";
		}
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
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

	public List<String> getDisbursementIdList() {
		return disbursementIdList;
	}

	public void setDisbursementIdList(List<String> disbursementIdList) {
		this.disbursementIdList = disbursementIdList;
	}
	
	public double getTotalPartialAmount() {
		return totalPartialAmount;
	}

	public void setTotalPartialAmount(double totalPartialAmount) {
		this.totalPartialAmount = totalPartialAmount;
	}

	public double getPartialGrossAmount() {
		return partialGrossAmount;
	}

	public void setPartialGrossAmount(double partialGrossAmount) {
		this.partialGrossAmount = partialGrossAmount;
	}

	public double getPartialGstAmount() {
		return partialGstAmount;
	}

	public void setPartialGstAmount(double partialGstAmount) {
		this.partialGstAmount = partialGstAmount;
	}

	public List<DisbursementPartialPayments> getPartialPaymentsList() {
		return partialPaymentsList;
	}

	public void setPartialPaymentsList(List<DisbursementPartialPayments> partialPaymentsList) {
		this.partialPaymentsList = partialPaymentsList;
	}

	public int getPartialPaymentId() {
		return partialPaymentId;
	}

	public void setPartialPaymentId(int partialPaymentId) {
		this.partialPaymentId = partialPaymentId;
	}

	public double getPrevPaidAmount() {
		return prevPaidAmount;
	}

	public void setPrevPaidAmount(double prevPaidAmount) {
		this.prevPaidAmount = prevPaidAmount;
	}

	public double getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(double gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	

	public String getRecipientEmailsCc() {
		return recipientEmailsCc;
	}

	public void setRecipientEmailsCc(String recipientEmailsCc) {
		this.recipientEmailsCc = recipientEmailsCc;
	}

	public String getRecipientEmailsBcc() {
		return recipientEmailsBcc;
	}

	public void setRecipientEmailsBcc(String recipientEmailsBcc) {
		this.recipientEmailsBcc = recipientEmailsBcc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getFollowupDateAsDate() {
		return followupDateAsDate;
	}

	public void setFollowupDateAsDate(Date followupDateAsDate) {
		this.followupDateAsDate = followupDateAsDate;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Disbursement [disbursementId=" + disbursementId + ", clientId=" + clientId + ", percentageValue="
				+ percentageValue + ", disbursementAmount=" + disbursementAmount + ", gstAmount=" + gstAmount
				+ ", prevRemainingAmount=" + prevRemainingAmount + ", totalAmount=" + totalAmount + ", paidAmount="
				+ paidAmount + ", remainingAmount=" + remainingAmount + ", sendPdfDate=" + sendPdfDate
				+ ", demandLetterPdfPath=" + demandLetterPdfPath + ", disbursementFollowupDate="
				+ disbursementFollowupDate + ", projectDisbursementId=" + projectDisbursementId + ", paidDate="
				+ paidDate + ", disbursementDescription=" + disbursementDescription + ", userId=" + userId
				+ ", createdDatetime=" + createdDatetime + ", updatedDatetime="
				+ updatedDatetime + "]";
	}	
	
	
}
