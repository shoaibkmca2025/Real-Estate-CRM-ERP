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
@Table(name="wing_details")
public class WingDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wing_details_id")
	private int wingDetailsId;
	
	@Column(name="floor_number")
	private int floorNumber;
	
	@Column(name="floor_name")
	private String floorName;
	
	@Column(name="property_type_id")
	private int propertyTypeId;		
	
	@Column(name="property_id")
	private int propertyId;
	
	@Column(name="property_area")
	private double propertyArea;
	
	@Column(name="no_of_flats")
	private int noOfFlats;	
	
	@Column(name="wing_id")
	private int wingId;
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="is_remove")
	private int isRemove;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private String propertyName;
	
	@Transient
	private int projectId;
	
	@Transient
	private String propertyTypeDescr;	
	
	@Transient
	private String wingName;
	
	@Transient
	private int flatNumber;	
	
	@Transient
	private List<EnquiryDetails> enquiryList;
	
	public WingDetails(){
		
	}	

	public WingDetails(int wingDetailsId, int floorNumber, int propertyTypeId, int propertyId, double propertyArea,
			int noOfFlats, int wingId, int userId, int isRemove, String createdDatetime, String updatedDatetime,
			String propertyName, int projectId, String propertyTypeDescr, String wingName, int flatNumber,
			List<EnquiryDetails> enquiryList) {
		super();
		this.wingDetailsId = wingDetailsId;
		this.floorNumber = floorNumber;
		this.propertyTypeId = propertyTypeId;
		this.propertyId = propertyId;
		this.propertyArea = propertyArea;
		this.noOfFlats = noOfFlats;
		this.wingId = wingId;
		this.userId = userId;
		this.isRemove = isRemove;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.propertyName = propertyName;
		this.projectId = projectId;
		this.propertyTypeDescr = propertyTypeDescr;
		this.wingName = wingName;
		this.flatNumber = flatNumber;
		this.enquiryList = enquiryList;
	}

	public int getWingDetailsId() {
		return wingDetailsId;
	}

	public void setWingDetailsId(int wingDetailsId) {
		this.wingDetailsId = wingDetailsId;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}	

	public double getPropertyArea() {
		return propertyArea;
	}

	public void setPropertyArea(double propertyArea) {
		this.propertyArea = propertyArea;
	}

	public int getNoOfFlats() {
		return noOfFlats;
	}

	public void setNoOfFlats(int noOfFlats) {
		this.noOfFlats = noOfFlats;
	}

	public int getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(int propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		this.wingId = wingId;
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
	
	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getWingName() {
		if(wingName == null){
			return "";
		}
		return wingName;
	}

	public void setWingName(String wingName) {
		this.wingName = wingName;
	}
	
	public List<EnquiryDetails> getEnquiryList() {
		return enquiryList;
	}

	public void setEnquiryList(List<EnquiryDetails> enquiryList) {
		this.enquiryList = enquiryList;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}

	@Override
	public String toString() {
		return "WingDetails [wingDetailsId=" + wingDetailsId + ", floorNumber=" + floorNumber + ", propertyTypeId="
				+ propertyTypeId + ", propertyId=" + propertyId + ", propertyArea=" + propertyArea + ", noOfFlats="
				+ noOfFlats + ", wingId=" + wingId + ", userId=" + userId + ", isRemove=" + isRemove
				+ ", createdDatetime=" + createdDatetime + ", updatedDatetime=" + updatedDatetime + ", propertyName="
				+ propertyName + ", projectId=" + projectId + ", propertyTypeDescr=" + propertyTypeDescr + ", wingName="
				+ wingName + ", flatNumber=" + flatNumber + ", enquiryList=" + enquiryList + "]";
	}

	
}
