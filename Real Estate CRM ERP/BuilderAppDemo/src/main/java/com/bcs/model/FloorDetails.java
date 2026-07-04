package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="floor_details")
public class FloorDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="floor_id")
	private int floorId;
	
	@Column(name="floorNumber")
	private int floorNumber;
	
	@Column(name="floor_name")
	private String floorName;
	
	@Column(name="wing_id")
	private int wingId;	
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	

	public FloorDetails() {
		
	}


	public FloorDetails(int floorId, int floorNumber, String floorName, int wingId, int userId, String createdDatetime,
			String updatedDatetime) {
		super();
		this.floorId = floorId;
		this.floorNumber = floorNumber;
		this.floorName = floorName;
		this.wingId = wingId;
		this.userId = userId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}

	public int getFloorId() {
		return floorId;
	}

	public void setFloorId(int floorId) {
		this.floorId = floorId;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public String getFloorName() {
		if(floorName == null){
			return "";
		}	
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
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

	@Override
	public String toString() {
		return "FloorDetails [floorId=" + floorId + ", floorNumber=" + floorNumber + ", floorName=" + floorName
				+ ", wingId=" + wingId + ", userId=" + userId + ", createdDatetime=" + createdDatetime
				+ ", updatedDatetime=" + updatedDatetime + "]";
	}
	
	
	

}
