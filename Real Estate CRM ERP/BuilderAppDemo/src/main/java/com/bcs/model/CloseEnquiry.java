package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="close_enquiry")
public class CloseEnquiry {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="closed_enquiry_id")
	private int closedEnquiryId;
	
	@Column(name="reason")
	private String reason;
	
	@Column(name="enquiry_id")
	private int enquiryId;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private String fullName;
	
	@Transient
	private String mobileNo;
	
	@Transient
	private String projectName;
	
	@Transient
	private String email;
	
	@Transient
	private int projectId;
	
	@Transient
	private int userType;
	
	public CloseEnquiry(){
		
	}

	public CloseEnquiry(int closedEnquiryId, String reason, int enquiryId, String createdDatetime,
			String updatedDatetime, int projectId) {
		super();
		this.closedEnquiryId = closedEnquiryId;
		this.reason = reason;
		this.enquiryId = enquiryId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.projectId = projectId;
	}

	public int getClosedEnquiryId() {
		return closedEnquiryId;
	}

	public void setClosedEnquiryId(int closedEnquiryId) {
		this.closedEnquiryId = closedEnquiryId;
	}

	public String getReason() {
		if(reason == null){
			return "";
		}
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
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

	public String getFullName() {
		if(fullName == null){
			return "";
		}
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getProjectName() {
		if(projectName == null){
			return "";
		}
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getEmail() {
		if(email == null){
			return "";
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "CloseEnquiry [closedEnquiryId=" + closedEnquiryId + ", reason=" + reason + ", enquiryId=" + enquiryId
				+ ", createdDatetime=" + createdDatetime + ", updatedDatetime=" + updatedDatetime + ", projectId=" + projectId +  "]";
	}
	
}
