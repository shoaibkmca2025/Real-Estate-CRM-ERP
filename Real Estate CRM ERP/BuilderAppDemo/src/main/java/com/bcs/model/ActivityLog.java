package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="activity_log")
public class ActivityLog {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="activity_id")
	private int activityId;
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="activity_description")
	private String activityDescription;
	
	@Column(name="activity_datetime")
	private String activityDatetime;
	
	@Column(name="project_tran_id")
	private int projectTranId;
	
	public ActivityLog(){
		
	}

	public ActivityLog(int activityId, int userId, String activityDescription, String activityDatetime, int projectTranId) {
		super();
		this.activityId = activityId;
		this.userId = userId;
		this.activityDescription = activityDescription;
		this.activityDatetime = activityDatetime;
		this.projectTranId = projectTranId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProjectTranId() {
		return projectTranId;
	}

	public void setProjectTranId(int projectTranId) {
		this.projectTranId = projectTranId;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public String getActivityDatetime() {
		return activityDatetime;
	}

	public void setActivityDatetime(String activityDatetime) {
		this.activityDatetime = activityDatetime;
	}

	@Override
	public String toString() {
		return "ActivityLog [activityId=" + activityId + ", userId=" + userId + ", projectTranId=" + projectTranId + ", activityDescription="
				+ activityDescription + ", activityDatetime=" + activityDatetime + "]";
	}

}
