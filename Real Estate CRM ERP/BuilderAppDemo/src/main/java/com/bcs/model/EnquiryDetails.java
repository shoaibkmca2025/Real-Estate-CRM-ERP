package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="enquiry_details")
public class EnquiryDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="enquiry_id")
	private int enquiryId;	

	@Column(name="first_name")
	private String firstName;
		
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	@Column(name="landline_no")
	private String landlineNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="city")
	private String city;
	
	@Column(name="address")
	private String address;
	
	@Column(name="occupation")
	private String occupation;
	
	@Column(name="company")
	private String company;
	
	@Column(name="sales_person")
	private int salesPerson;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="property_type")
	private int propertyType;
	
	@Column(name="property")
	private int property;
	
	@Column(name="property_area")
	private double propertyArea;
	
	@Column(name="budget_min")
	private int budgetMin;
	
	@Column(name="budget_max")
	private int budgetMax;
	
	@Column(name="reference")
	private int reference;
	
	@Column(name="reference_name")
	private String referenceName;
	
	@Column(name="followup_date")
	private String followupDate;
	
	@Column(name="followup_status")
	private int followupStatus;
	
	@Column(name="user_id")
	private int userId;	

	@Column(name="is_remove")
	private int isRemove;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Column(name="remark")
	private String remark;
	
	@Column(name="remark_date")
	private String remarkDate;
	
	@Transient
	private String projectName;
	
	@Transient
	private String propertyTypeDescr;
	
	@Transient
	private String propertyName;
	
	@Transient
	private String referenceDescr;
	
	@Transient
	private String followupDescr;
	
	@Transient
	private String followupFlag;
		
	@Transient
	private int projectTranId;
	
	@Transient
	private int companyId;	
	
	@Transient
	private int userType;	

	public EnquiryDetails(){
		
	}

	public EnquiryDetails(int enquiryId, String firstName, String lastName, String mobileNo, String landlineNo,
			String email, String city, String address, String occupation, String company, int salesPerson,
			int projectId, int propertyType, int property, double propertyArea, int budgetMin, int budgetMax,
			int reference, String referenceName, int isRemove, String createdDatetime, String updatedDatetime, String projectName,
			String propertyTypeDescr, String propertyName, String referenceDescr, String followupDescr,
			String followupDate,int followupStatus,int projectTranId) {
		super();
		this.enquiryId = enquiryId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.landlineNo = landlineNo;
		this.email = email;
		this.city = city;
		this.address = address;
		this.occupation = occupation;
		this.company = company;
		this.salesPerson = salesPerson;
		this.projectId = projectId;
		this.propertyType = propertyType;
		this.property = property;
		this.propertyArea = propertyArea;
		this.budgetMin = budgetMin;
		this.budgetMax = budgetMax;
		this.reference = reference;
		this.referenceName = referenceName;
		this.isRemove = isRemove;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.projectName = projectName;
		this.propertyTypeDescr = propertyTypeDescr;
		this.propertyName = propertyName;
		this.referenceDescr = referenceDescr;
		this.followupDescr = followupDescr;
		this.followupDate = followupDate;
		this.followupStatus = followupStatus;
		this.projectTranId = projectTranId;
	}

	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
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

	public String getLandlineNo() {
		if(landlineNo == null){
			return "";
		}
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
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

	public String getCity() {
		if(city == null){
			return "";
		}
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getOccupation() {
		if(occupation == null){
			return "";
		}
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCompany() {
		if(company == null){
			return "";
		}
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(int salesPerson) {
		this.salesPerson = salesPerson;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}

	public int getProperty() {
		return property;
	}

	public void setProperty(int property) {
		this.property = property;
	}

	public double getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(double propertyArea) {
		this.propertyArea = propertyArea;
	}

	public int getBudgetMin() {
		return budgetMin;
	}

	public void setBudgetMin(int budgetMin) {
		this.budgetMin = budgetMin;
	}

	public int getBudgetMax() {
		return budgetMax;
	}

	public void setBudgetMax(int budgetMax) {
		this.budgetMax = budgetMax;
	}

	public int getReference() {
		return reference;
	}

	public String getReferenceName() {
		if(referenceName == null){
			return "";
		}
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
	}

	public int getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
	}

	public void setReference(int reference) {
		this.reference = reference;
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
	
	public String getRemark() {
		if(remark == null){
			return "";
		}
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemarkDate() {
		return remarkDate;
	}

	public void setRemarkDate(String remarkDate) {
		this.remarkDate = remarkDate;
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

	public String getPropertyTypeDescr() {
		if(propertyTypeDescr == null){
			return "";
		}
		return propertyTypeDescr;
	}

	public void setPropertyTypeDescr(String propertyTypeDescr) {
		this.propertyTypeDescr = propertyTypeDescr;
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

	public String getReferenceDescr() {
		if(referenceDescr == null){
			return "";
		}
		return referenceDescr;
	}

	public void setReferenceDescr(String referenceDescr) {
		this.referenceDescr = referenceDescr;
	}

	public String getFollowupDescr() {
		if(followupDescr == null){
			return "";
		}
		return followupDescr;
	}

	public void setFollowupDescr(String followupDescr) {
		this.followupDescr = followupDescr;
	}

	public String getFollowupDate() {
		if(followupDate == null){
			return "";
		}
		return followupDate;
	}

	public void setFollowupDate(String followupDate) {
		this.followupDate = followupDate;
	}

	public int getFollowupStatus() {
		return followupStatus;
	}

	public void setFollowupStatus(int followupStatus) {
		this.followupStatus = followupStatus;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFollowupFlag() {
		if(followupFlag == null){
			return "";
		}
		return followupFlag;
	}

	public void setFollowupFlag(String followupFlag) {
		this.followupFlag = followupFlag;
	}

	public int getProjectTranId() {
		return projectTranId;
	}

	public void setProjectTranId(int projectTranId) {
		this.projectTranId = projectTranId;
	}
	
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "EnquiryDetails [enquiryId=" + enquiryId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNo=" + mobileNo + ", landlineNo=" + landlineNo + ", email=" + email + ", city=" + city
				+ ", address=" + address + ", occupation=" + occupation + ", company=" + company + ", salesPerson="
				+ salesPerson + ", projectId=" + projectId + ", propertyType=" + propertyType + ", property=" + property
				+ ", propertyArea=" + propertyArea + ", budgetMin=" + budgetMin + ", budgetMax=" + budgetMax
				+ ", reference=" + reference + ", referenceName=" + referenceName + ", followupDate=" + followupDate
				+ ", followupStatus=" + followupStatus + ", isRemove=" + isRemove + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + ", projectName=" + projectName
				+ ", propertyTypeDescr=" + propertyTypeDescr + ", propertyName=" + propertyName + ", referenceDescr="
				+ referenceDescr + ", followupDescr=" + followupDescr  
				+ ", followupFlag=" + followupFlag + ", projectTranId=" + projectTranId ;
	}

}