package com.bcs.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="project_disbursement")
public class ProjectDisbursement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_disbursement_id")
	private int projectDisbursementId;
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="disbursement_title")
	private String disbursementTitle;
	
	@Column(name="disbursement_description")
	private String disbursementDescription;
	
	@Column(name="disbursement_percentage")
	private double disbursementPercentage;
	
	@Column(name="completion_date")
	private String completionDate;
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	private Date dateOfCompletion;
	
	public ProjectDisbursement(){
		
	}
	
	public ProjectDisbursement(int projectDisbursementId, int projectId, String disbursementTitle,
			String disbursementDescription, double disbursementPercentage, String createdDatetime,
			String updatedDatetime) {
		super();
		this.projectDisbursementId = projectDisbursementId;
		this.projectId = projectId;
		this.disbursementTitle = disbursementTitle;
		this.disbursementDescription = disbursementDescription;
		this.disbursementPercentage = disbursementPercentage;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
	}


	public int getProjectDisbursementId() {
		return projectDisbursementId;
	}

	public void setProjectDisbursementId(int projectDisbursementId) {
		this.projectDisbursementId = projectDisbursementId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getDisbursementTitle() {
		if(disbursementTitle == null){
			return "";
		}
		return disbursementTitle;
	}

	public void setDisbursementTitle(String disbursementTitle) {
		this.disbursementTitle = disbursementTitle;
	}

	public String getDisbursementDescription() {
		if(disbursementDescription == null){
			return "";
		}
		return disbursementDescription;
	}

	public void setDisbursementDescription(String disbursementDescription) {
		this.disbursementDescription = disbursementDescription;
	}

	public double getDisbursementPercentage() {
		return disbursementPercentage;
	}

	public void setDisbursementPercentage(double disbursementPercentage) {
		this.disbursementPercentage = disbursementPercentage;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getCompletionDate() {
		if(completionDate == null){
			return "";
		}
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
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

	

	public Date getDateOfCompletion() {
		return dateOfCompletion;
	}

	public void setDateOfCompletion(Date dateOfCompletion) {
		this.dateOfCompletion = dateOfCompletion;
	}

	@Override
	public String toString() {
		return "ProjectDisbursement [projectDisbursementId=" + projectDisbursementId + ", projectId=" + projectId
				+ ", disbursementTitle=" + disbursementTitle + ", disbursementDescription=" + disbursementDescription
				+ ", disbursementPercentage=" + disbursementPercentage + "]";
	}
}
