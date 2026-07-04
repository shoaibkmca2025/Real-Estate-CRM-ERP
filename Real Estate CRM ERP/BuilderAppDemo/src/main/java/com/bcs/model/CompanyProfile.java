package com.bcs.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="company_profile")
public class CompanyProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="company_name")
	private String companyName;
	
	@Column(name="website")
	private String website;
	
	@Column(name="company_email")
	private String companyEmail;
	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="landline")
	private String landline;
	
	@Column(name="address")
	private String address;
	
	@Column(name="logo_path")
	private String logoPath;
	
	@Column(name="marketed_by")
	private String marketedBy;
	
	@Column(name="marketed_by_website")
	private String marketedByWebsite;
	
	@Column(name="is_updated")
	private int isUpdated;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	public MultipartFile logoAttachment;
	
	@Transient
	private String startDate;
	
	@Transient
	private int userId;
	
	@Transient
	private Date resultDate;
	
	public CompanyProfile() {
		
	}

	public CompanyProfile(int companyId, String companyName, String mobile, String companyEmail, String website, String landline, String address,
			String logoPath, String marketedBy, String marketedByWebsite, int isUpdated, String createdDatetime, String updatedDatetime) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.mobile = mobile;
		this.companyEmail = companyEmail;
		this.website = website;
		this.landline = landline;
		this.address = address;
		this.logoPath = logoPath;
		this.marketedBy = marketedBy;
		this.marketedByWebsite = marketedByWebsite;
		this.isUpdated = isUpdated;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
	

	public String getWebsite() {
		if(website == null){
			return "";
		}
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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

	public String getMobile() {
		if(mobile == null){
			return "";
		}
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getMarketedBy() {
		return marketedBy;
	}

	public void setMarketedBy(String marketedBy) {
		this.marketedBy = marketedBy;
	}

	public String getMarketedByWebsite() {
		return marketedByWebsite;
	}

	public void setMarketedByWebsite(String marketedByWebsite) {
		this.marketedByWebsite = marketedByWebsite;
	}

	public int getIsUpdated() {
		return isUpdated;
	}

	public void setIsUpdated(int isUpdated) {
		this.isUpdated = isUpdated;
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

	public MultipartFile getLogoAttachment() {
		return logoAttachment;
	}

	public void setLogoAttachment(MultipartFile logoAttachment) {
		this.logoAttachment = logoAttachment;
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	
	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	@Override
	public String toString() {
		return "CompanyProfile [companyId=" + companyId + ", companyName=" + companyName + ", companyEmail=" + companyEmail + ", mobile=" + mobile + ", website=" + website 
				+ ", landline=" + landline + ", address=" + address + ", logoPath=" + logoPath + ", marketedBy=" + marketedBy + ", marketedByWebsite=" + marketedByWebsite
				+ ", isUpdated=" + isUpdated + ", createdDatetime=" + createdDatetime + ", updatedDatetime=" + updatedDatetime + "]";
	}

}
