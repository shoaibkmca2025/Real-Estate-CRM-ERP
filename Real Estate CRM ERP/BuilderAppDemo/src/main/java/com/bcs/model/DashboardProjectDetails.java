package com.bcs.model;


import java.math.BigDecimal;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class DashboardProjectDetails {

	@Id
	private int Id;
	
	@Transient
	private Long projectId;
	
	@Transient
	private String projectName;
	
	@Transient
	private String monthName;
	
	@Transient
	private BigDecimal totalUnits;
	
	@Transient
	private Long totalBooked;
	
	@Transient
	private BigDecimal totalUnbooked;
	
	@Transient
	private Long totalEnquiry;
	
	@Transient
	private Long closedEnquiry;
	
	@Transient
	private BigDecimal response;
	
	@Transient
	private double projectCompletion;
	
	
	
	
	public DashboardProjectDetails(){
	}	

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public BigDecimal getTotalUnits() {
		return totalUnits;
	}

	public void setTotalUnits(BigDecimal totalUnits) {
		this.totalUnits = totalUnits;
	}

	public Long getTotalBooked() {
		return totalBooked;
	}

	public void setTotalBooked(Long totalBooked) {
		this.totalBooked = totalBooked;
	}

	public BigDecimal getTotalUnbooked() {
		return totalUnbooked;
	}

	public void setTotalUnbooked(BigDecimal totalUnbooked) {
		this.totalUnbooked = totalUnbooked;
	}

	public Long getTotalEnquiry() {
		return totalEnquiry;
	}

	public void setTotalEnquiry(Long totalEnquiry) {
		this.totalEnquiry = totalEnquiry;
	}

	public Long getClosedEnquiry() {
		return closedEnquiry;
	}

	public void setClosedEnquiry(Long closedEnquiry) {
		this.closedEnquiry = closedEnquiry;
	}

	public BigDecimal getResponse() {
		return response;
	}

	public void setResponse(BigDecimal response) {
		this.response = response;
	}

	public double getProjectCompletion() {
		return projectCompletion;
	}

	public void setProjectCompletion(double projectCompletion) {
		this.projectCompletion = projectCompletion;
	}

	

}

