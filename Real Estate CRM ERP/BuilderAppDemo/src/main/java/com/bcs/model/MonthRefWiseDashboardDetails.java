package com.bcs.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity
public class MonthRefWiseDashboardDetails {

	@Id
	private int Id;
	
	@Transient
	private String projectName;

	@Transient
	private String monthName;
	
	@Transient
	private String totalVisit;
	
	@Transient
	private String areaCount;
	
	@Transient
	private String areaDetails;
	
	@Transient
	private ArrayList<String> totalVisitLabels;
	
	@Transient
	private ArrayList<String> totalVisitSeries;
	
	@SuppressWarnings("rawtypes")
	@Transient
	private ArrayList<ArrayList> totalVisitData;
	
	@Transient
	private String reference;
	
	@Transient
	private String totalClient;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getTotalVisit() {
		return totalVisit;
	}

	public void setTotalVisit(String totalVisit) {
		this.totalVisit = totalVisit;
	}

	public ArrayList<String> getTotalVisitLabels() {
		return totalVisitLabels;
	}

	public void setTotalVisitLabels(ArrayList<String> totalVisitLabels) {
		this.totalVisitLabels = totalVisitLabels;
	}

	public ArrayList<String> getTotalVisitSeries() {
		return totalVisitSeries;
	}

	public void setTotalVisitSeries(ArrayList<String> totalVisitSeries) {
		this.totalVisitSeries = totalVisitSeries;
	}

	public ArrayList<ArrayList> getTotalVisitData() {
		return totalVisitData;
	}

	public void setTotalVisitData(ArrayList<ArrayList> totalVisitData) {
		this.totalVisitData = totalVisitData;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getTotalClient() {
		return totalClient;
	}

	public void setTotalClient(String totalClient) {
		this.totalClient = totalClient;
	}

	public String getAreaCount() {
		return areaCount;
	}

	public void setAreaCount(String areaCount) {
		this.areaCount = areaCount;
	}

	public String getAreaDetails() {
		return areaDetails;
	}

	public void setAreaDetails(String areaDetails) {
		this.areaDetails = areaDetails;
	}

	@Override
	public String toString() {
		return "MonthWiseTotalVisits [Id=" + Id + ", projectName=" + projectName + ", monthName=" + monthName
				+ ", totalVisit=" + totalVisit + ", totalVisitLabels=" + totalVisitLabels + ", totalVisitSeries="
				+ totalVisitSeries + ", totalVisitData=" + totalVisitData + "]";
	}
	
}
