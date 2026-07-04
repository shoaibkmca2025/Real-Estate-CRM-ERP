package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sales_person")
public class SalesPerson {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sales_person_id")
	private int salesPersonId;	

	@Column(name="sales_person_name")
	private String salesPersonName;
	
	@Column(name="sales_person_email")
	private String salesPersonEmail;	
	
	@Column(name="sales_person_address")
	private String salesPersonAddress;	
	
	@Column(name="sales_person_mobile")
	private String salesPersonMobile;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="created_datetime")
	private String createdDateTime;
	
	@Column(name="updated_datetime")
	private String updatedDateTime;
	
	@Column(name="is_disabled")
	private int isDisabled;

	public int getSalesPersonId() {
		return salesPersonId;
	}

	public void setSalesPersonId(int salesPersonId) {
		this.salesPersonId = salesPersonId;
	}

	public String getSalesPersonName() {
		if(salesPersonName == null){
			return "";
		}
		return salesPersonName;
	}

	public void setSalesPersonName(String salesPersonName) {
		this.salesPersonName = salesPersonName;
	}

	public String getSalesPersonEmail() {
		if(salesPersonEmail == null){
			return "";
		}
		return salesPersonEmail;
	}

	public void setSalesPersonEmail(String salesPersonEmail) {
		this.salesPersonEmail = salesPersonEmail;
	}

	public String getSalesPersonAddress() {
		if(salesPersonAddress == null){
			return "";
		}
		return salesPersonAddress;
	}

	public void setSalesPersonAddress(String salesPersonAddress) {
		this.salesPersonAddress = salesPersonAddress;
	}

	public String getSalesPersonMobile() {
		if(salesPersonMobile == null){
			return "";
		}
		return salesPersonMobile;
	}

	public void setSalesPersonMobile(String salesPersonMobile) {
		this.salesPersonMobile = salesPersonMobile;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getCreatedDateTime() {
		if(createdDateTime == null){
			return "";
		}
		return createdDateTime;
	}

	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedDateTime() {
		if(updatedDateTime == null){
			return "";
		}
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public int getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(int isDisabled) {
		this.isDisabled = isDisabled;
	}

	public SalesPerson() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalesPerson(int salesPersonId, String salesPersonName, String salesPersonEmail, String salesPersonAddress,
			String salesPersonMobile, int projectId, String createdDateTime, String updatedDateTime, int isDisabled) {
		super();
		this.salesPersonId = salesPersonId;
		this.salesPersonName = salesPersonName;
		this.salesPersonEmail = salesPersonEmail;
		this.salesPersonAddress = salesPersonAddress;
		this.salesPersonMobile = salesPersonMobile;
		this.projectId = projectId;
		this.createdDateTime = createdDateTime;
		this.updatedDateTime = updatedDateTime;;
		this.isDisabled = isDisabled;
	}
	
	

}
