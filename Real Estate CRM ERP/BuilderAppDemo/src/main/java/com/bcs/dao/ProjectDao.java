package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.CountDetails;
import com.bcs.model.MonthRefWiseDashboardDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.Wing;

@Component
public interface ProjectDao {

	public boolean addAllDetails(ProjectDetails project, int projectTranId, int wingTranId);

	public int getRecentProjectId();

	public int getRecentWingId();
	
	public List<ProjectDetails> getAllProjectDetails(int userType, int Id, int projectStatus);
	
	public ProjectDetails getProjectDetailsById(int projectId);
	
	public boolean updateProjectBasicDetails(ProjectDetails project);
	
    public List<ProjectDetails> getAllProjectsByStatus(int projectStatus,int userType, int id);

    public boolean deleteProjectDetails(int projectId);
    
    public boolean updateProjectStatus(int projectId, int projectStatus,int userId);
    
    public List<Wing> getProjectStructureByProjectId(int projectId);

	public List<Wing> getBookingDetailsByWingTranId(int wingTranId);

	//public List<ProjectDetails> getAllProjectDetailsByUserType(int userId);

	//public List<ProjectDetails> getAllProjectsByStatusAndUserType(int projectStatus, int userId);

	public boolean allocateProjectsToUsers(ProjectDetails projectDetails);

	public List<ProjectDetails> getAllUsersByProjectId(int projectId);
	
	public CountDetails getAllCountDetails(int userId, int userType, int projectStatus, int projectTranId, int companyId);

	public boolean updateProjectApprovedStatus(int projectId, int isApproved);

	public List<ProjectDetails> getAllUnapprovedProjectDetails();

	public boolean updateProjectStatusByScheduledTasks(List<String> projectIdsList);

	public int checkIsAnyPropertyBookedByProjectId(int projectId);

	List<ProjectDetails> getInprogressAndCompletedProjectList(int userType, int userId);
	
	//public List<DashboardDetails> getDashboardDetails(int projectTranId);
	
}