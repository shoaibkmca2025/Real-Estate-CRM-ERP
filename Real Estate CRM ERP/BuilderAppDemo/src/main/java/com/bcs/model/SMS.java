package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="sms")
public class SMS {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sms_id")
	private int smsId;
	
	@Column(name="sms_text")
	private String smsText;
	
	@Column(name="sms_send_time")
	private String smsSendTime;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private String mobileNos;
	
	@Transient
	private boolean isScheduledSMS;
	
	@Transient
	private String eventName;
	
	@Transient
	private String eventDate;
	
	@Transient
	private String date;
	
	
	@Transient
	private int messageCost;
	
	public SMS(){
		
	}

	public SMS(int smsId, String smsText, String smsSendTime, int companyId) {
		super();
		this.smsId = smsId;
		this.smsText = smsText;
		this.smsSendTime = smsSendTime;
		this.companyId = companyId;
	}

	public int getSmsId() {
		return smsId;
	}

	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}

	public String getSmsText() {
		return smsText;
	}

	public void setSmsText(String smsText) {
		this.smsText = smsText;
	}

	public String getSmsSendTime() {
		return smsSendTime;
	}

	public void setSmsSendTime(String smsSendTime) {
		this.smsSendTime = smsSendTime;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public String getMobileNos() {
		return mobileNos;
	}

	public void setMobileNos(String mobileNos) {
		this.mobileNos = mobileNos;
	}

	public boolean getIsScheduledSMS() {
		return isScheduledSMS;
	}

	public void setScheduledSMS(boolean isScheduledSMS) {
		this.isScheduledSMS = isScheduledSMS;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getMessageCost() {
		return messageCost;
	}

	public void setMessageCost(int messageCost) {
		this.messageCost = messageCost;
	}

	@Override
	public String toString() {
		return "SMS [smsId=" + smsId + ", smsText=" + smsText + ", smsSendTime=" + smsSendTime + ", companyId=" + companyId
				+ ", mobileNos=" + mobileNos + "]";
	}
}
