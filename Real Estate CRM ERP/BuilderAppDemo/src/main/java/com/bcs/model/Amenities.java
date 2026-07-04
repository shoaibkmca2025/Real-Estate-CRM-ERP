package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="aminities")
public class Amenities {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="amenity_id")
	private int amenityId;
	
	@Column(name="aminities")
	private String aminities;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="is_remove")
	private int isRemove;	
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	
	public Amenities(){
		
	}
	
	public Amenities(int amenityId, String aminities, int projectId, String createdDatetime, String updatedDatetime,
			int isRemove) {
		super();
		this.amenityId = amenityId;
		this.aminities = aminities;
		this.projectId = projectId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.isRemove = isRemove;
	}

	public int getAmenityId() {
		
		return amenityId;
	}

	public void setAmenityId(int amenityId) {
		this.amenityId = amenityId;
	}
	
	public String getAminities() {		
		if(aminities == null){
			return "";
		}
		return aminities;
	}

	public void setAminities(String aminities) {
		
		this.aminities = aminities;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	public int getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
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
		return "Amenities [amenityId=" + amenityId + ", aminities=" + aminities + ", projectId=" + projectId
				+ ", createdDatetime=" + createdDatetime + ", updatedDatetime=" + updatedDatetime + "]";
	}
	
}
