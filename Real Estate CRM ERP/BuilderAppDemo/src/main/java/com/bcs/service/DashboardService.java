package com.bcs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.DashboardDetails;
import com.bcs.model.DashboardProjectDetails;
import com.bcs.model.MonthRefWiseDashboardDetails;

@Component
public interface DashboardService {

	//public List<DashboardProjectDetails> getProjectDetailsCountOnDashboard(int companyId, int userType, int userId);

	public MonthRefWiseDashboardDetails getMonthwiseTotalEnquiries(int companyId, int userId, int userType, int projectStatus);
	
	public DashboardDetails getAllDashboardDetails(int companyId, int userId, int userType, int projectStatus);

	public List<DashboardProjectDetails> getMonthwiseCountDetailsByProjectId(int projectId);

	public DashboardDetails getProjectDashboardDetails(int projectId);

	
}
