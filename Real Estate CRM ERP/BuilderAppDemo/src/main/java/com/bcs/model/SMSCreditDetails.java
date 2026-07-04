package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sms_credit_details")
public class SMSCreditDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sms_credit_id")
	private int smsCreditId;
	
	@Column(name="total_credits")
	private int totalCredits;
	
	@Column(name="total_sent")
	private int totalSent;
	
	@Column(name="available_credits")
	private int availableCredits;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="insertion_date")
	private String insertionDate;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	public SMSCreditDetails(){
		
	}

	public SMSCreditDetails(int smsCreditId, int totalCredits, int totalSent, int availableCredits, int companyId,
			String insertionDate, String updatedDatetime) {
		super();
		this.smsCreditId = smsCreditId;
		this.totalCredits = totalCredits;
		this.totalSent = totalSent;
		this.availableCredits = availableCredits;
		this.companyId = companyId;
		this.insertionDate = insertionDate;
		this.updatedDatetime = updatedDatetime;
	}

	public int getSmsCreditId() {
		return smsCreditId;
	}

	public void setSmsCreditId(int smsCreditId) {
		this.smsCreditId = smsCreditId;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public int getTotalSent() {
		return totalSent;
	}

	public void setTotalSent(int totalSent) {
		this.totalSent = totalSent;
	}

	public int getAvailableCredits() {
		return availableCredits;
	}

	public void setAvailableCredits(int availableCredits) {
		this.availableCredits = availableCredits;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getInsertionDate() {
		return insertionDate;
	}

	public void setInsertionDate(String insertionDate) {
		this.insertionDate = insertionDate;
	}

	public String getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	@Override
	public String toString() {
		return "SMSCountDetails [smsCreditId=" + smsCreditId + ", totalCredits=" + totalCredits + ", totalSent="
				+ totalSent + ", availableCredits=" + availableCredits + ", companyId=" + companyId + ", insertionDate="
				+ insertionDate + ", updatedDatetime=" + updatedDatetime + "]";
	}
	
	
	
}
