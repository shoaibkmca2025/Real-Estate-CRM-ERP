package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="custom_events")
public class CustomEvent {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="custom_event_id")
	private int customEventId;
	
	@Column(name="event_name")
	private String eventName;
	
	@Column(name="event_date")
	private String eventDate;
	
	@Column(name="sms_id")
	private int smsId;
	
	@Column(name="is_remove")
	private int isRemove;
	
	public CustomEvent(){
		
	}

	public CustomEvent(int customEventId, String eventName, String eventDate, int smsId, int isRemove) {
		super();
		this.customEventId = customEventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.smsId = smsId;
		this.isRemove = isRemove;
	}

	public int getCustomEventId() {
		return customEventId;
	}

	public void setCustomEventId(int customEventId) {
		this.customEventId = customEventId;
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

	

	public int getSmsId() {
		return smsId;
	}

	public void setSmsId(int smsId) {
		this.smsId = smsId;
	}

	public int getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
	}

	@Override
	public String toString() {
		return "CustomEvent [customEventId=" + customEventId + ", eventName=" + eventName + ", eventDate=" + eventDate
				+ ", smsId=" + smsId + ", isRemove=" + isRemove + "]";
	}
	

}
