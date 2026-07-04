package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Flats")
public class Flats {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="flat_id")
	private int flatId;	

	@Column(name="flat_number")
	private int flatNumber;
	
	@Column(name="floor_number")
	private int floorNumber;	
	
	@Column(name="floor_name")
	private String floorName;	

	@Column(name="wing_id")
	private int wingId;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="wing_details_id")
	private int wingDetailsId;
		
	public Flats(){
		
	}

	public Flats(int flatId, int flatNumber, int floorNumber, int wingId, int projectId, int wingDetailsId) {
		super();
		this.flatId = flatId;
		this.flatNumber = flatNumber;
		this.floorNumber = floorNumber;
		this.wingId = wingId;
		this.projectId = projectId;
		this.wingDetailsId = wingDetailsId;
	}

	public int getFlatId() {
		return flatId;
	}

	public void setFlatId(int flatId) {
		this.flatId = flatId;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
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

	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		this.wingId = wingId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getWingDetailsId() {
		return wingDetailsId;
	}

	public void setWingDetailsId(int wingDetailsId) {
		this.wingDetailsId = wingDetailsId;
	}

	@Override
	public String toString() {
		return "Flats [flatId=" + flatId + ", flatNumber=" + flatNumber + ", floorNumber=" + floorNumber + ", wingId="
				+ wingId + ", projectId=" + projectId + ", wingDetailsId=" + wingDetailsId + "]";
	}
	
}
