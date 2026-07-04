package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="disbursement_partial_payments")
public class DisbursementPartialPayments {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="partial_payment_id")
	private int partialPaymentId;
	
	@Column(name="partial_payment_description")
	private String partialPaymentDescription;
	
	@Column(name="partial_payment_paid_date")
	private String partialPaymentPaidDate;
	
	@Column(name="disbursement_id")
	private int disbursementId;
	
	@Column(name="partial_gross_amount")
	private double partialGrossAmount;
	
	@Column(name="partial_gst_amount")
	private double partialGstAmount;
	
	@Column(name="total_partial_amount")
	private double totalPartialAmount;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	public DisbursementPartialPayments(){
		
	}
	
	public DisbursementPartialPayments(int partialPaymentId, String partialPaymentDescription,
			String partialPaymentPaidDate, int disbursementId, double partialGrossAmount, double partialGstAmount,
			double totalPartialAmount, String createdDatetime, String updatedDatetime) {
		super();
		this.partialPaymentId = partialPaymentId;
		this.partialPaymentDescription = partialPaymentDescription;
		this.partialPaymentPaidDate = partialPaymentPaidDate;
		this.disbursementId = disbursementId;
		this.partialGrossAmount = partialGrossAmount;
		this.partialGstAmount = partialGstAmount;
		this.totalPartialAmount = totalPartialAmount;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	public int getPartialPaymentId() {
		return partialPaymentId;
	}

	public void setPartialPaymentId(int partialPaymentId) {
		this.partialPaymentId = partialPaymentId;
	}

	public String getPartialPaymentDescription() {
		if(partialPaymentDescription == null){
			return "";
		}
		return partialPaymentDescription;
	}

	public void setPartialPaymentDescription(String partialPaymentDescription) {
		this.partialPaymentDescription = partialPaymentDescription;
	}

	public String getPartialPaymentPaidDate() {
		if(partialPaymentPaidDate == null){
			return "";
		}
		return partialPaymentPaidDate;
	}

	public void setPartialPaymentPaidDate(String partialPaymentPaidDate) {
		this.partialPaymentPaidDate = partialPaymentPaidDate;
	}

	public int getDisbursementId() {
		return disbursementId;
	}

	public void setDisbursementId(int disbursementId) {
		this.disbursementId = disbursementId;
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

	public double getTotalPartialAmount() {
		return totalPartialAmount;
	}

	public void setTotalPartialAmount(double totalPartialAmount) {
		this.totalPartialAmount = totalPartialAmount;
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

	@Override
	public String toString() {
		return "DisbursementPartialPayments [partialPaymentId=" + partialPaymentId + ", partialPaymentDescription="
				+ partialPaymentDescription + ", partialPaymentPaidDate=" + partialPaymentPaidDate + ", disbursementId="
				+ disbursementId + ", partialGrossAmount=" + partialGrossAmount + ", partialGstAmount="
				+ partialGstAmount + ", totalPartialAmount=" + totalPartialAmount + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + "]";
	}

	
}
