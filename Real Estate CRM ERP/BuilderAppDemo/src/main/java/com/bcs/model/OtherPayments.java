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
@Table(name="other_payments")
public class OtherPayments {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="other_payment_id")
	private int otherPaymentId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="paid_date")
	private String paidDate;
	
	@Column(name="due_date")
	private String dueDate;
	
	@Column(name="cheque_number")
	private String chequeNumber;
	
	@Column(name="cheque_date")
	private String chequeDate;
	
	@Column(name="client_id")
	private int clientId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient 
	private Date date;
	
	@Transient 
	private Date chequeDate1;
	
	@Transient 
	private Date dueDate1;
	
	
	public OtherPayments(){
		
	}

	public OtherPayments(int otherPaymentId, String title, double amount, String paidDate, int clientId, int userId,
			String createdDatetime, String updatedDatetime) {
		super();
		this.otherPaymentId = otherPaymentId;
		this.title    = title;
		this.amount   = amount;
		this.paidDate = paidDate;
		this.clientId = clientId;
		this.userId   = userId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	public int getOtherPaymentId() {
		return otherPaymentId;
	}

	public void setOtherPaymentId(int otherPaymentId) {
		this.otherPaymentId = otherPaymentId;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public String getChequeNumber() {
		if(chequeNumber == null){
			return "";
		}
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public String getChequeDate() {
		if(chequeDate == null){
			return "";
		}
		return chequeDate;
	}

	public void setChequeDate(String chequeDate) {
		this.chequeDate = chequeDate;
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

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getChequeDate1() {
		return chequeDate1;
	}

	public void setChequeDate1(Date chequeDate1) {
		this.chequeDate1 = chequeDate1;
	}

	public Date getDueDate1() {
		return dueDate1;
	}

	public void setDueDate1(Date dueDate1) {
		this.dueDate1 = dueDate1;
	}

	@Override
	public String toString() {
		return "OtherPayments [otherPaymentId=" + otherPaymentId + ", title=" + title + ", amount=" + amount
				+ ", paidDate=" + paidDate + ", clientId=" + clientId + ", userId=" + userId + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + "]";
	}


}
