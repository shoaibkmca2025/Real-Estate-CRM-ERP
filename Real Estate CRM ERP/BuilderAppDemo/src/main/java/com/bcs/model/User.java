package com.bcs.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_mobile")
	private String userMobile;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_type")
	private int userType;
	
	@Column(name="user_status")
	private int userStatus;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="is_remove")
	private int isRemove;
	
	@Column(name="token_name")
	private String tokenName;	
	
	@Column(name="user_device_id")
	private String userDeviceId;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
		
	@Transient
	private int isUpdated;
	
	@Transient
	private String companyName;
	
	@Transient
	private String companyEmail;
	
	@Transient
	private String companyMobile;
	
	@Transient
	private String website;
	
	@Transient
	private String landline;
	
	@Transient
	private String address;
	
	@Transient
	private String logoPath;
	
	@Transient
	private String newPassword;
	
	@Transient
	private String newEmailOrMobile;
	
	@Transient
	private String userTypeName;
	
	@Transient
	public MultipartFile logoAttachment;
	
	@Transient
	private String startDate;
	
	@Transient
	private String marketedBy;	
	
	@Transient
	private String marketedByWebsite;
	
	
	
	@Transient
	private List<String> userIdList;

	public User(){
		
	}

	public User(int userId, String userName, String userEmail, String userMobile, String userPassword, int userType,
			int userStatus, int companyId, int isRemove, String createdDatetime, String updatedDatetime, int isUpdated,
			String companyName, String website, String landline, String address, String logoPath, String newPassword,
			String newEmailOrMobile, String userTypeName, MultipartFile logoAttachment, String startDate,
			List<String> userIdList) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userMobile = userMobile;
		this.userPassword = userPassword;
		this.userType = userType;
		this.userStatus = userStatus;
		this.companyId = companyId;
		this.isRemove = isRemove;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.isUpdated = isUpdated;
		this.companyName = companyName;
		this.website = website;
		this.landline = landline;
		this.address = address;
		this.logoPath = logoPath;
		this.newPassword = newPassword;
		this.newEmailOrMobile = newEmailOrMobile;
		this.userTypeName = userTypeName;
		this.logoAttachment = logoAttachment;
		this.startDate = startDate;
		this.userIdList = userIdList;
	}
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		if(userName == null){
			return "";
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		if(userEmail == null){
			return "";
		}
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserMobile() {
		if(userMobile == null){
			return "";
		}
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPassword() {
		if(userPassword == null){
			return "";
		}
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
	}

	public int getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(int isUpdated) {
		this.isUpdated = isUpdated;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

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

	
	public String getCompanyName() {
		if(companyName == null){
			return "";
		}
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyEmail() {
		if(companyEmail == null){
			return "";
		}
		return companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	public String getCompanyMobile() {
		if(companyMobile == null){
			return "";
		}
		return companyMobile;
	}

	public void setCompanyMobile(String companyMobile) {
		this.companyMobile = companyMobile;
	}

	public String getWebsite() {
		if(website == null){
			return "";
		}
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLandline() {
		if(landline == null){
			return "";
		}
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getAddress() {
		if(address == null){
			return "";
		}
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogoPath() {
		if(logoPath == null){
			return "";
		}
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
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
	
	public String getNewPassword() {
		if(newPassword == null){
			return "";
		}
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewEmailOrMobile() {
		if(newEmailOrMobile == null){
			return "";
		}
		return newEmailOrMobile;
	}

	public void setNewEmailOrMobile(String newEmailOrMobile) {
		this.newEmailOrMobile = newEmailOrMobile;
	}

	public String getUserTypeName() {
		if(userTypeName == null){
			return "";
		}
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public MultipartFile getLogoAttachment() {
		return logoAttachment;
	}

	public void setLogoAttachment(MultipartFile logoAttachment) {
		this.logoAttachment = logoAttachment;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
	
	public String getStartDate() {
		if(startDate == null){
			return "";
		}
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getMarketedBy() {
		if(marketedBy == null){
			return "";
		}
		return marketedBy;
	}

	public void setMarketedBy(String marketedBy) {
		this.marketedBy = marketedBy;
	}

	public String getMarketedByWebsite() {
		if(marketedByWebsite == null){
			return "";
		}
		return marketedByWebsite;
	}

	public void setMarketedByWebsite(String marketedByWebsite) {
		this.marketedByWebsite = marketedByWebsite;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public String getUserDeviceId() {
		return userDeviceId;
	}

	public void setUserDeviceId(String userDeviceId) {
		this.userDeviceId = userDeviceId;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userMobile="
				+ userMobile + ", userPassword=" + userPassword + ", userType=" + userType + ", userStatus="
				+ userStatus + ", companyId=" + companyId + ", isRemove=" + isRemove + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + ", isUpdated=" + isUpdated
				+ ", companyName=" + companyName + ", website=" + website + ", landline=" + landline + ", address="
				+ address + ", logoPath=" + logoPath + ", newPassword=" + newPassword + ", newEmailOrMobile="
				+ newEmailOrMobile + ", userTypeName=" + userTypeName + ", logoAttachment=" + logoAttachment
				+ ", startDate=" + startDate + ", userIdList=" + userIdList + "]";
	}


}