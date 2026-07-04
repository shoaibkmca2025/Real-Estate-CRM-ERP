package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="settings")
public class Settings {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="settings_id")
	private int settingsId;
	
	@Column(name="sender_email")
	private String sendersEmail;
	
	@Column(name="followup_notification_duration")
	private int followupNotificationDuration;	
	
	@Column(name="payment_notification_duration")
	private int paymentNotificationDuration;	
	
	@Column(name="payment_duedate_duration")
	private int paymentDuedateDuration;	
	
/*	@Column(name="builder_id")
	private int builderId;	*/
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private int userId;
	
	public Settings(){
		
	}

	public Settings(int settingsId, String sendersEmail, int followupNotificationDuration,
			int paymentNotificationDuration, int paymentDuedateDuration, int builderId, int companyId, String createdDatetime,
			String updatedDatetime) {
		super();
		this.settingsId = settingsId;
		this.sendersEmail = sendersEmail;
		this.followupNotificationDuration = followupNotificationDuration;
		this.paymentNotificationDuration = paymentNotificationDuration;
		this.paymentDuedateDuration = paymentDuedateDuration;
		//this.builderId = builderId;
		this.companyId = companyId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	public int getSettingsId() {
		return settingsId;
	}

	public void setSettingsId(int settingsId) {
		this.settingsId = settingsId;
	}	

	public String getSendersEmail() {
		if(sendersEmail == null){
			return "";
		}
		return sendersEmail;
	}

	public void setSendersEmail(String sendersEmail) {
		this.sendersEmail = sendersEmail;
	}

	public int getFollowupNotificationDuration() {
		return followupNotificationDuration;
	}

	public void setFollowupNotificationDuration(int followupNotificationDuration) {
		this.followupNotificationDuration = followupNotificationDuration;
	}

	public int getPaymentNotificationDuration() {
		return paymentNotificationDuration;
	}

	public void setPaymentNotificationDuration(int paymentNotificationDuration) {
		this.paymentNotificationDuration = paymentNotificationDuration;
	}

	public int getPaymentDuedateDuration() {
		return paymentDuedateDuration;
	}

	public void setPaymentDuedateDuration(int paymentDuedateDuration) {
		this.paymentDuedateDuration = paymentDuedateDuration;
	}

/*	public int getBuilderId() {
		return builderId;
	}

	public void setBuilderId(int builderId) {
		this.builderId = builderId;
	}*/

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Settings [settingsId=" + settingsId + ", sendersEmail=" + sendersEmail + ", followupNotificationDuration="
				+ followupNotificationDuration + ", paymentNotificationDuration=" + paymentNotificationDuration
				+ ", paymentDuedateDuration=" + paymentDuedateDuration + ",  companyId=" + companyId
				+ ", createdDatetime=" + createdDatetime + ", updatedDatetime=" + updatedDatetime + "]";
	}

	//builderId=" + builderId + ",
	
}
