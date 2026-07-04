package com.bcs.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="login_details")
public class LoginDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="login_details_id")
	private int loginDetailsId;
	
	@Column(name="login_id")
	private int loginId;
	
	@Column(name="login_datetime")
	private String loginDateTime;
	
	@Column(name="latitude")
	private String latitude;
	
	@Column(name="longitude")
	private String longitude;
	
	@Column(name="location")
	private String userLocation;
	
	@Column(name="device_name")
	private String userDeviceName;
	
	@Column(name="ip_address")
	private String userIpAddress;
	
	@Column(name="created_datetime")
	private String createdDateTime;
	
	@Column(name="update_datetime")
	private String updatedDateTime;
	
	@Transient
	private String loggedInUserName;
	
	@Transient
	private String loggedInUserEmail;
	
	@Transient
	private List<LoginDetails> loginDataList;
	
	
	public LoginDetails(){
		
	}

	public LoginDetails(int loginDetailsId, int loginId, String loginDateTime, String latitude, String longitude,
			String userLocation, String userDeviceName, String userIpAddress, String createdDateTime, String updatedDateTime, 
			String loggedInUserName, String loggedInUserEmail, List<LoginDetails> loginDataList) {
		super();
		this.loginDetailsId = loginDetailsId;
		this.loginId = loginId;
		this.loginDateTime = loginDateTime;
		this.latitude = latitude;
		this.longitude = longitude;
		this.userLocation = userLocation;
		this.userDeviceName = userDeviceName;
		this.userIpAddress = userIpAddress;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;
		this.loggedInUserName = loggedInUserName;
		this.loggedInUserEmail = loggedInUserEmail;
		this.loginDataList = loginDataList;
		
	}

	public int getLoginDetailsId() {
		return loginDetailsId;
	}

	public void setLoginDetailsid(int loginDetailsId) {
		this.loginDetailsId = loginDetailsId;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getLoginDateTime() {
		return loginDateTime;
	}

	public void setLoginDateTime(String loginDateTime) {
		this.loginDateTime = loginDateTime;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserDeviceName() {
		return userDeviceName;
	}

	public void setUserDeviceName(String userDeviceName) {
		this.userDeviceName = userDeviceName;
	}

	public String getUserIpAddress() {
		return userIpAddress;
	}

	public void setUserIpAddress(String userIpAddress) {
		this.userIpAddress = userIpAddress;
	}
	
	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String getLoggedInUserName() {
		return loggedInUserName;
	}

	public void setLoggedInUserName(String loggedInUserName) {
		this.loggedInUserName = loggedInUserName;
	}
	
	

	public String getLoggedInUserEmail() {
		return loggedInUserEmail;
	}

	public void setLoggedInUserEmail(String loggedInUserEmail) {
		this.loggedInUserEmail = loggedInUserEmail;
	}
	
	

	public List<LoginDetails> getLoginDataList() {
		return loginDataList;
	}

	public void setLoginDataList(List<LoginDetails> loginDataList) {
		this.loginDataList = loginDataList;
	}

	@Override
	public String toString() {
		return "LoginDetails [loginDetailsId=" + loginDetailsId + ", loginId=" + loginId + ", loginDateTime="
				+ loginDateTime + ", latitude=" + latitude + ", longitude=" + longitude + ", userLocation="
				+ userLocation + ", userDeviceName=" + userDeviceName + ", userIpAddress=" + userIpAddress
				+ ", createdDateTime=" + createdDateTime + ", updatedDateTime=" + updatedDateTime
				+ ", loggedInUserName=" + loggedInUserName + ", loggedInUserEmail=" + loggedInUserEmail
				+ ", loginDataList=" + loginDataList + "]";
	}
}
