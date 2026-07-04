package com.bcs.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.DashboardDao;
import com.bcs.model.DashboardDetails;
import com.bcs.model.DashboardProjectDetails;
import com.bcs.model.MonthRefWiseDashboardDetails;



@Service
public class DashboardServiceImpl implements DashboardService{
	
	@Autowired
	private DashboardDao dashboardDao;
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public MonthRefWiseDashboardDetails getMonthwiseTotalEnquiries(int companyId, int userId, int userType, int projectStatus) {
		
		List<MonthRefWiseDashboardDetails> monthwiseList = dashboardDao.getMonthwiseTotalEnquiries(companyId, userId, userType, projectStatus);
		MonthRefWiseDashboardDetails monthwiseTotalVisitsData = new MonthRefWiseDashboardDetails();

		ArrayList<String> monthArray = new ArrayList<String>();
		ArrayList<String> projectNameArray = new ArrayList<String>();
		ArrayList totalVisitData = new ArrayList();
		
		if(monthwiseList.size() > 0){
			
			String[] arrayElements = monthwiseList.get(0).getMonthName().split(",");
			monthArray = new ArrayList<String>(Arrays.asList(arrayElements));
			monthwiseTotalVisitsData.setTotalVisitLabels(monthArray);
		}
		for(int i = 0; i < monthwiseList.size(); i++){
			
			projectNameArray.add(monthwiseList.get(i).getProjectName());
			
			ArrayList<String> totalVisitsArray = new ArrayList<String>();
			String[] dataArray = monthwiseList.get(i).getTotalVisit().split(",");
			totalVisitsArray = new ArrayList<String>(Arrays.asList(dataArray));
			totalVisitData.add(totalVisitsArray);
		}
		
		monthwiseTotalVisitsData.setTotalVisitSeries(projectNameArray);
		monthwiseTotalVisitsData.setTotalVisitData(totalVisitData);
		
		return monthwiseTotalVisitsData;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DashboardDetails getAllDashboardDetails(int companyId,  int userId, int userType, int projectStatus) {
		
		 DashboardDetails dashboardDetails = new DashboardDetails();
		 
		 List<DashboardProjectDetails> dashboardProjectDetails = dashboardDao.getProjectDetailsCountOnDashboard(companyId,userId,userType, projectStatus);
		 dashboardDetails.setDashboardProjectDetails(dashboardProjectDetails);

		 MonthRefWiseDashboardDetails  monthWiseData = getMonthwiseTotalEnquiries(companyId,userId,userType, projectStatus);
		 
		 dashboardDetails.setLineChartLabels(monthWiseData.getTotalVisitLabels());
		 dashboardDetails.setLineChartSeries(monthWiseData.getTotalVisitSeries());
		 dashboardDetails.setLineChartData(monthWiseData.getTotalVisitData());
		 
		 
		 ArrayList<String> projectNameArray 	= new ArrayList<String>();
		 ArrayList<Integer> totalUnitsArray      = new ArrayList<Integer>();
		 ArrayList<Integer> totalBookedPropArray = new ArrayList<Integer>();
		 ArrayList<Integer> totalVisitsArray     = new ArrayList<Integer>();
		
		 ArrayList lineChartData     = new ArrayList();
		 
		 for(int i = 0; i < dashboardProjectDetails.size(); i++){
			 
			 projectNameArray.add(dashboardProjectDetails.get(i).getProjectName());
			 totalUnitsArray.add(dashboardProjectDetails.get(i).getTotalUnits().intValue());
			 totalBookedPropArray.add(dashboardProjectDetails.get(i).getTotalBooked().intValue());
			 totalVisitsArray.add(dashboardProjectDetails.get(i).getTotalEnquiry().intValue());
			
		 }
		 lineChartData.add(totalUnitsArray);
		 lineChartData.add(totalBookedPropArray);
		 lineChartData.add(totalVisitsArray);
		 
		 
		 dashboardDetails.setBarChartLabels(projectNameArray);
		 dashboardDetails.setBarChartData(lineChartData);
		 dashboardDetails.setDonutChartLabels(projectNameArray);
		 dashboardDetails.setDonutChartEnquiryData(totalVisitsArray);
		
		 
		 MonthRefWiseDashboardDetails  refWiseData = dashboardDao.getReferenceWiseTotalEnquiryAndClient(companyId, userId, userType, projectStatus);
		 
		 String[] refArray = refWiseData.getReference().split(",");
		 ArrayList<String> pieChartLabels = new ArrayList<String>(Arrays.asList(refArray));
		 
		 String[] totalVisitArr = refWiseData.getTotalVisit().split(",");
		 ArrayList<String> pieChartEnquiryData = new ArrayList<String>(Arrays.asList(totalVisitArr));
		 
		 String[] totalClientArr = refWiseData.getTotalClient().split(",");
		 ArrayList<String> pieChartClientData = new ArrayList<String>(Arrays.asList(totalClientArr));
		 
		 dashboardDetails.setPieChartLabels(pieChartLabels);
		 dashboardDetails.setPieChartEnquiryData(pieChartEnquiryData);
		 dashboardDetails.setPieChartClientData(pieChartClientData);
		 
		return dashboardDetails;
	}

	public List<DashboardProjectDetails> getMonthwiseCountDetailsByProjectId(int projectId) {
		
		 List<DashboardProjectDetails>  monthwiseList = dashboardDao.getMonthwiseCountDetailsByProjectId(projectId);
		 /*int totalBooked = 0;
		
		 for(int i=0; i <monthwiseList.size();i++){
			 
			 int totalUnits =  monthwiseList.get(i).getTotalUnits().intValue();
			 totalBooked += monthwiseList.get(i).getTotalBooked().intValue();
			 int unbooked = totalUnits - totalBooked;
			 
			 System.out.println("unbooked: "+unbooked);
			 
			 BigDecimal totalUnbooked = new BigDecimal(unbooked);
			 monthwiseList.get(i).setTotalUnbooked(totalUnbooked);
			 
		 }
		 
		 System.out.println("monthwiseList: "+monthwiseList);*/
		 return monthwiseList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public DashboardDetails getProjectDashboardDetails(int projectId) {
		
		DashboardDetails projectDashboardDetails = new DashboardDetails();
		
		List<DashboardProjectDetails>  monthwiseList = dashboardDao.getMonthwiseCountDetailsByProjectId(projectId);
		 int totalBooked = 0;
		 ArrayList<String> lineChartLabels 	= new ArrayList<String>();
		 ArrayList<Integer> totalClosedArray      = new ArrayList<Integer>();
		 ArrayList<Integer> totalBookedPropArray = new ArrayList<Integer>();
		 ArrayList<Integer> totalVisitsArray     = new ArrayList<Integer>();
		 
		 for(int i=0; i <monthwiseList.size();i++){
			 
			 int totalUnits =  monthwiseList.get(i).getTotalUnits().intValue();
			 totalBooked += monthwiseList.get(i).getTotalBooked().intValue();
			 int unbooked = totalUnits - totalBooked;
			
			 BigDecimal totalUnbooked = new BigDecimal(unbooked);
			 monthwiseList.get(i).setTotalUnbooked(totalUnbooked);
			 
			 lineChartLabels.add(monthwiseList.get(i).getMonthName());
			 totalBookedPropArray.add(monthwiseList.get(i).getTotalBooked().intValue());
			 totalVisitsArray.add(monthwiseList.get(i).getTotalEnquiry().intValue());
			 totalClosedArray.add(monthwiseList.get(i).getClosedEnquiry().intValue());
			 
		 }
		 ArrayList lineChartData     = new ArrayList();
		 
		 lineChartData.add(totalBookedPropArray);
		 lineChartData.add(totalVisitsArray);
		 lineChartData.add(totalClosedArray);
		 
		 MonthRefWiseDashboardDetails  refWiseDataByProjectId = dashboardDao.getReferenceWiseDetailsByProjectId(projectId);
		 
		 String[] projRefArray = refWiseDataByProjectId.getReference().split(",");
		 ArrayList<String> pieChartLabelsOfProject = new ArrayList<String>(Arrays.asList(projRefArray));
		 
		 String[] projTotalVisitArr = refWiseDataByProjectId.getTotalVisit().split(",");
		 ArrayList<String> pieChartEnquiryDataOfProject = new ArrayList<String>(Arrays.asList(projTotalVisitArr));
		 
		 String[] projTotalClientArr = refWiseDataByProjectId.getTotalClient().split(",");
		 ArrayList<String> pieChartClientDataOfProject = new ArrayList<String>(Arrays.asList(projTotalClientArr));
		 
		 String[] projTotalAreaCountsArr = refWiseDataByProjectId.getAreaCount().split(",");
		 ArrayList<String> donutChartTotalAreaCount= new ArrayList<String>(Arrays.asList(projTotalAreaCountsArr));
		 
		 String[] projAreaDetailsArr = refWiseDataByProjectId.getAreaDetails().split(",");
		 ArrayList<String> donutChartProjectAreaDetails = new ArrayList<String>(Arrays.asList(projAreaDetailsArr));
		 
		 projectDashboardDetails.setPieChartLabels(pieChartLabelsOfProject);
		 projectDashboardDetails.setPieChartEnquiryData(pieChartEnquiryDataOfProject);
		 projectDashboardDetails.setPieChartClientData(pieChartClientDataOfProject);
		 projectDashboardDetails.setDonutChartAreaCounts(donutChartTotalAreaCount);
		 projectDashboardDetails.setDonutChartAreaDetails(donutChartProjectAreaDetails);
		 
		projectDashboardDetails.setDashboardProjectDetails(monthwiseList);
		projectDashboardDetails.setLineChartData(lineChartData);
		projectDashboardDetails.setLineChartLabels(lineChartLabels);
		
		return projectDashboardDetails;

	}
}
