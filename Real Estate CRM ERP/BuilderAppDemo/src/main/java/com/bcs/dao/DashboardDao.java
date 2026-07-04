package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.DashboardProjectDetails;
import com.bcs.model.MonthRefWiseDashboardDetails;

@Component
public interface DashboardDao {
	
	public List<DashboardProjectDetails> getProjectDetailsCountOnDashboard(int companyId, int userType, int userId, int projectStatus);
	
	public List<MonthRefWiseDashboardDetails> getMonthwiseTotalEnquiries(int companyId, int userId, int userType, int projectStatus);

	public MonthRefWiseDashboardDetails getReferenceWiseTotalEnquiryAndClient(int companyId, int userId, int userType, int projectStatus);
	
	public List<DashboardProjectDetails> getMonthwiseCountDetailsByProjectId(int projectId);
	
	public MonthRefWiseDashboardDetails getReferenceWiseDetailsByProjectId(int projectId);


}
