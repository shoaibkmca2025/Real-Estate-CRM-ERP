package com.bcs.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcs.model.DashboardDetails;
import com.bcs.model.Status;
import com.bcs.service.DashboardService;

@Controller
public class DashboardController {
	
final static Logger logger = LoggerFactory.getLogger(DashboardController.class);	
	
	@Autowired
	DashboardService dashboardService;
	
	@RequestMapping(value = "/getProjectDashboardDetails/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getProjectDetailsCountOnDashboard(@PathVariable("projectId") int projectId)throws Exception {		
		
		 try{
			 	DashboardDetails projectDashboardDetails = dashboardService.getProjectDashboardDetails(projectId);
			 	return new Status(200, projectDashboardDetails);
		 
		 }catch(Exception ex){  
			 	ex.printStackTrace(); 
			 	return new Status(500, ex.toString());
		 }
		
	}
	
	@RequestMapping(value = "/getAllDashboadDetails/{companyId}/{userType}/{userId}/{projectStatus}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllDashboadDetails(@PathVariable("companyId") int companyId,@PathVariable("userType") int userType,
			@PathVariable("userId") int userId, @PathVariable("projectStatus") int projectStatus)throws Exception {		
		
		 try{			 
			   // String lineChartDetails = dashboardService.getLineChartData(ConstantsUtil.LINE_CHART_URL, companyId, userType, userId);					    
			   
			 	DashboardDetails dashboardDetails = dashboardService.getAllDashboardDetails(companyId, userId, userType, projectStatus);
			 	return new Status(200, dashboardDetails);//,  lineChartDetails

		 
		 }catch(Exception ex){  
			 	ex.printStackTrace(); 
			 	return new Status(500, ex.toString());
		 }
		
	}

}
