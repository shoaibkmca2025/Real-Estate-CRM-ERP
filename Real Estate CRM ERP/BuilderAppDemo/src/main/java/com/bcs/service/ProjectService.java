package com.bcs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.CountDetails;
import com.bcs.model.MonthRefWiseDashboardDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.Wing;

@Component
public interface ProjectService {
	
	public boolean addAllDetails(ProjectDetails project, int projectTranId, int wingTranId) throws ParseException;
	
	public int getRecentProjectId();

	public int getRecentWingId();
	
	public List<ProjectDetails> getAllProjectDetails(int userType, int Id, int projectStatus);
	
	public ProjectDetails getProjectDetailsById(int projectId);
	
	public boolean updateProjectBasicDetails(ProjectDetails project) throws ParseException;
	
	//public ProjectDetails deleteProjectdetailse(int projectId);	
	
	public List<ProjectDetails> getAllProjectsByStatus(int projectStatus, int userType, int Id);

	public boolean deleteProjectDetails(int projectId, int userId);
	
	public boolean updateProjectStatus(int projectId, int projectStatus, int userId);
	
	public List<Wing> getProjectStructureByProjectId(int projectId);

	public List<Wing> getBookingDetailsByWingTranId(int wingTranId);

	//public List<ProjectDetails> getAllProjectDetailsByUserType(int userId);

	//public List<ProjectDetails> getAllProjectsByStatusAndUserType(int projectStatus, int userId);

	public void allocateProjectsToUsers(ProjectDetails projectDetails);

	public List<ProjectDetails> getAllUsersByProjectId(int projectId);

	public String generateProjectDetailsPdf(ProjectDetails projectDetails);

	public ProjectDetails getProjectDetailsByProjectTransId(int projectId);
	
	public CountDetails getAllCountDetails(int userId, int userType, int projectStatus, int projectTranId, int companyId);

	public boolean updateProjectApprovedStatus(int projectId, int isApproved);

	public List<ProjectDetails> getAllUnapprovedProjectDetails();
	
	public int checkIsAnyPropertyBookedByProjectId(int projectId);
	
	public boolean updateProjectStatusByScheduledTasks(List<String> projectIdsList);
	
	List<ProjectDetails> getInprogressAndCompletedProjectList(int userType, int userId);
	
	
	
}