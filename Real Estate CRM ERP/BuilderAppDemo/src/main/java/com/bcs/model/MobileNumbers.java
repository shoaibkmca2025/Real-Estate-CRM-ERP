package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mobilenumbers")
public class MobileNumbers {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="mobilenumber_id")
	private int mobileNumberId;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="sms_id")
	private int smsId;
	
	@Column(name="send_status")
	private int sendStatus;
		
	@Column(name="custom_event_id")
	private int customEventId;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	public MobileNumbers(){
		
	}

	public MobileNumbers(int mobileNumberId, String mobileNumber, int smsId, String createdDatetime) {
		super();
		this.mobileNumberId = mobileNumberId;
		this.mobileNumber = mobileNumber;
		this.smsId = smsId;
		this.createdDatetime = createdDatetime;
	}

	public int getMobileNumberId() {
		return mobileNumberId;
	}

	public void setMobileNumberId(int mobileNumberId) {
		this.mobileNumberId = mobileNumberId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getSmsId() {
		return smsId;
	}

	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}

	public int getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(int sendStatus) {
		this.sendStatus = sendStatus;
	}

	public int getCustomEventId() {
		return customEventId;
	}

	public void setCustomEventId(int customEventId) {
		this.customEventId = customEventId;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	@Override
	public String toString() {
		return "MobileNumbers [mobileNumberId=" + mobileNumberId + ", mobileNumber=" + mobileNumber + ", smsId=" + smsId
				+ ", createdDatetime=" + createdDatetime + "]";
	}
	
}
