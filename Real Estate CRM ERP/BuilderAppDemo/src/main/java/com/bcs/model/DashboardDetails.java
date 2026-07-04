package com.bcs.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Transient;

public class DashboardDetails {

	@Transient
	private List<DashboardProjectDetails> dashboardProjectDetails;
	
	@Transient
	private ArrayList<String> barChartLabels;
	
	@Transient
	private ArrayList<String> barChartSeries;
	
	@SuppressWarnings("rawtypes")
	@Transient
	private ArrayList<ArrayList> barChartData;
	
	@Transient
	private ArrayList<String> lineChartLabels;
	
	@Transient
	private ArrayList<String> lineChartSeries;
	
	@SuppressWarnings("rawtypes")
	@Transient
	private ArrayList<ArrayList> lineChartData;
	
	@Transient
	private ArrayList<String> pieChartLabels;
	
	@Transient
	private ArrayList<String> pieChartEnquiryData;
	
	@Transient
	private ArrayList<String> pieChartClientData;
	
	@Transient
	private ArrayList<String> donutChartLabels;
	
	@Transient
	private ArrayList<Integer> donutChartEnquiryData;
	
	@Transient
	private ArrayList<String> donutChartAreaCounts;
	
	@Transient
	private ArrayList<String> donutChartAreaDetails;	
	
	@Transient
	private Object jsonObject;
	
	public Object getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(Object jsonObject) {
		this.jsonObject = jsonObject;
	}

	public DashboardDetails(){
		
	}
	
	public ArrayList<String> getBarChartLabels() {
		return barChartLabels;
	}

	public void setBarChartLabels(ArrayList<String> barChartLabels) {
		this.barChartLabels = barChartLabels;
	}

	public ArrayList<String> getBarChartSeries() {
		return barChartSeries;
	}

	public void setBarChartSeries(ArrayList<String> barChartSeries) {
		this.barChartSeries = barChartSeries;
	}

	public ArrayList<ArrayList> getBarChartData() {
		return barChartData;
	}

	public void setBarChartData(ArrayList<ArrayList> barChartData) {
		this.barChartData = barChartData;
	}

	public ArrayList<String> getLineChartLabels() {
		return lineChartLabels;
	}

	public void setLineChartLabels(ArrayList<String> lineChartLabels) {
		this.lineChartLabels = lineChartLabels;
	}

	public ArrayList<String> getLineChartSeries() {
		return lineChartSeries;
	}

	public void setLineChartSeries(ArrayList<String> lineChartSeries) {
		this.lineChartSeries = lineChartSeries;
	}

	public ArrayList<ArrayList> getLineChartData() {
		return lineChartData;
	}

	public void setLineChartData(ArrayList<ArrayList> lineChartData) {
		this.lineChartData = lineChartData;
	}

	public ArrayList<String> getPieChartLabels() {
		return pieChartLabels;
	}

	public void setPieChartLabels(ArrayList<String> pieChartLabels) {
		this.pieChartLabels = pieChartLabels;
	}

	public ArrayList<String> getPieChartEnquiryData() {
		return pieChartEnquiryData;
	}

	public void setPieChartEnquiryData(ArrayList<String> pieChartEnquiryData) {
		this.pieChartEnquiryData = pieChartEnquiryData;
	}

	public ArrayList<String> getPieChartClientData() {
		return pieChartClientData;
	}

	public void setPieChartClientData(ArrayList<String> pieChartClientData) {
		this.pieChartClientData = pieChartClientData;
	}

	public ArrayList<String> getDonutChartLabels() {
		return donutChartLabels;
	}

	public void setDonutChartLabels(ArrayList<String> donutChartLabels) {
		this.donutChartLabels = donutChartLabels;
	}

	public ArrayList<Integer> getDonutChartEnquiryData() {
		return donutChartEnquiryData;
	}

	public void setDonutChartEnquiryData(ArrayList<Integer> donutChartEnquiryData) {
		this.donutChartEnquiryData = donutChartEnquiryData;
	}

	public ArrayList<String> getDonutChartAreaCounts() {
		return donutChartAreaCounts;
	}

	public void setDonutChartAreaCounts(ArrayList<String> donutChartAreaCounts) {
		this.donutChartAreaCounts = donutChartAreaCounts;
	}

	public ArrayList<String> getDonutChartAreaDetails() {
		return donutChartAreaDetails;
	}

	public void setDonutChartAreaDetails(ArrayList<String> donutChartAreaDetails) {
		this.donutChartAreaDetails = donutChartAreaDetails;
	}

	public List<DashboardProjectDetails> getDashboardProjectDetails() {
		return dashboardProjectDetails;
	}

	public void setDashboardProjectDetails(List<DashboardProjectDetails> dashboardProjectDetails) {
		this.dashboardProjectDetails = dashboardProjectDetails;
	}

}
