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
@Table(name="wing")
public class Wing {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="wing_id")
	private int wingId;
	
	@Column(name="wing_tran_id")
	private int wingTranId;
	
	@Column(name="wing_name")
	private String wingName;
	
	@Column(name="no_of_floors")
	private int noOfFloors;
	
	@Column(name="total_flats")
	private int totalFlats;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="is_manual_floors")
	private int isManualFloors;	
	
	@Column(name="is_remove")
	private int isRemove;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private List<String> wingdetails;
	
	@Transient
	private List<WingDetails> wingdetailsList;
	
	@Transient
	private List<FloorDetails> floorList;
	
	@Transient
	private String flatNumbers;
	
	@Transient
	private int floorNumber;
	
	@Transient
	private String floorName;
	
	@Transient
	private String bookedFlatNumbers;
	
	@Transient
	private String registeredFlatNumbers;
	
	public Wing(){
		
	}
	public Wing(int wingId, int wingTranId, String wingName, int noOfFloors, int totalFlats, int projectId,
			String createdDatetime, String updatedDatetime, int isRemove, List<String> wingdetails) {
		super();
		this.wingId = wingId;
		this.wingTranId = wingTranId;
		this.wingName = wingName;
		this.noOfFloors = noOfFloors;
		this.totalFlats = totalFlats;
		this.projectId = projectId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.isRemove = isRemove;
		this.wingdetails = wingdetails;
	}

	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		this.wingId = wingId;
	}

	public int getWingTranId() {
		return wingTranId;
	}

	public void setWingTranId(int wingTranId) {
		this.wingTranId = wingTranId;
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

	public int getNoOfFloors() {
		return noOfFloors;
	}

	public void setNoOfFloors(int noOfFloors) {
		this.noOfFloors = noOfFloors;
	}

	public int getTotalFlats() {
		return totalFlats;
	}

	public void setTotalFlats(int totalFlats) {
		this.totalFlats = totalFlats;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getIsManualFloors() {
		return isManualFloors;
	}
	public void setIsManualFloors(int isManualFloors) {
		this.isManualFloors = isManualFloors;
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

	
	public List<String> getWingdetails() {
		return wingdetails;
	}

	public void setWingdetails(List<String> wingdetails) {
		this.wingdetails = wingdetails;
	}

	public String getFlatNumbers() {
		if(flatNumbers == null){
			return "";
		}
		return flatNumbers;
	}
	
	public void setFlatNumbers(String flatNumbers) {
		this.flatNumbers = flatNumbers;
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
	public String getBookedFlatNumbers() {
		if(bookedFlatNumbers == null){
			return "";
		}
		return bookedFlatNumbers;
	}
	
	public void setBookedFlatNumbers(String bookedFlatNumbers) {
		this.bookedFlatNumbers = bookedFlatNumbers;
	}
	
	public String getRegisteredFlatNumbers() {
		if(registeredFlatNumbers == null){
			return "";
		}
		return registeredFlatNumbers;
	}
	
	public void setRegisteredFlatNumbers(String registeredFlatNumbers) {
		this.registeredFlatNumbers = registeredFlatNumbers;
	}
	
	public List<WingDetails> getWingdetailsList() {
		return wingdetailsList;
	}
	
	public void setWingdetailsList(List<WingDetails> wingdetailsList) {
		this.wingdetailsList = wingdetailsList;
	}
	
	public List<FloorDetails> getFloorList() {
		return floorList;
	}
	public void setFloorList(List<FloorDetails> floorList) {
		this.floorList = floorList;
	}
	@Override
	public String toString() {
		return "Wing [wingId=" + wingId + ", wingName=" + wingName + ", noOfFloors=" + noOfFloors + ", totalFlats="
				+ totalFlats + ", projectId=" + projectId + ", createdDatetime=" + createdDatetime
				+ ", updatedDatetime=" + updatedDatetime+ ", wingdetailsList=" + wingdetailsList + "]";
	}

}
