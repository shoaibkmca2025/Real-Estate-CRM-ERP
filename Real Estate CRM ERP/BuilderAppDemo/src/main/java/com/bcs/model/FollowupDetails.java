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
@Table(name="followup_details")
public class FollowupDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="followup_id")
	private int followupId;	

	@Column(name="followup_descr")
	private String followupDescription;
		
	@Column(name="enquiry_id")
	private int enquiryId;
	
/*	@Column(name="followup_date")
	private String nextFollowupDate;*/
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="is_remove")
	private int isRemove;

	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private String nextFollowupDate;
	
	@Transient
	private String firstName;
	
	@Transient
	private String lastName;
	
	@Transient
	private String mobileNo;

	@Transient
	private String projectName;
	
	@Transient
	private double propertyArea;
	
	@Transient
	private String propertyName;
	
	@Transient
	private int projectId;
	
	@Transient
	private int userType;
	
	@Transient
	private Date resultDate;
	
	@Transient
	private Long flag;
	
	public FollowupDetails(){	
	}

	public FollowupDetails(int followupId, String followupDescription, int enquiryId, String nextFollowupDate, int isRemove,
			String createdDatetime, String updatedDatetime) {
		super();
		this.followupId = followupId;
		this.followupDescription = followupDescription;
		this.enquiryId = enquiryId;
		this.isRemove = isRemove;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	public int getFollowupId() {
		return followupId;
	}

	public void setFollowupId(int followupId) {
		this.followupId = followupId;
	}

	public String getFollowupDescription() {
		if(followupDescription == null){
			return "";
		}
		return followupDescription;
	}

	public void setFollowupDescription(String followupDescription) {
		this.followupDescription = followupDescription;
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
	
	public String getNextFollowupDate() {
		if(nextFollowupDate == null){
			return "";
		}
		return nextFollowupDate;
	}

	public void setNextFollowupDate(String nextFollowupDate) {
		this.nextFollowupDate = nextFollowupDate;
	}
	
	public String getFirstName() {
		if(firstName == null){
			return "";
		}
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		if(lastName == null){
			return "";
		}
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public double getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(double propertyArea) {
		this.propertyArea = propertyArea;
	}

	public String getPropertyName() {
		if(propertyName == null){
			return "";
		}
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
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

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public Long getFlag() {
		return flag;
	}

	public void setFlag(Long flag) {
		this.flag = flag;
	}
	
	
}
