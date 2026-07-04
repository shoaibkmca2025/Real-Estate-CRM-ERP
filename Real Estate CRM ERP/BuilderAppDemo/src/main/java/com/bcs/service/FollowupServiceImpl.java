package com.bcs.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.EnquiryDao;
import com.bcs.dao.FollowupDao;
import com.bcs.model.EnquiryDetails;
import com.bcs.model.FollowupDetails;
import com.bcs.utility.DateTimeUtil;

@Service
public class FollowupServiceImpl implements FollowupService {
	
	@Autowired
	FollowupDao fdao;
	
	@Autowired
	EnquiryDao edao;
	
	@Override
	public List<EnquiryDetails> getAllFollowupDetails(int userId,int userType, int companyId, int projectId) {
		// TODO Auto-generated method stub
		return fdao.getAllFollowupDetails(userId,userType, companyId, projectId);
	}

	@Override
	public void addFollowupDetails(FollowupDetails followupDetails) throws ParseException {
		String NextFollowupDate="";			
		 String dateTime = DateTimeUtil.getSysDateTime();			    	  
		 followupDetails.setUpdatedDatetime(dateTime);	
		 followupDetails.setCreatedDatetime(dateTime);	
		 
		 fdao.addFollowupDetails(followupDetails);
		 
		 EnquiryDetails enquiryDetails=new EnquiryDetails();
		 
		// String NextFollowupDate=followupDetails.getNextFollowupDate(); SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		 //SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		 Date date;
		 date = followupDetails.getResultDate();
		 NextFollowupDate = output.format(date);
			
		 enquiryDetails.setFollowupDate(NextFollowupDate);
		 enquiryDetails.setEnquiryId(followupDetails.getEnquiryId());
		 enquiryDetails.setFollowupStatus(1);
		 enquiryDetails.setUpdatedDatetime(dateTime);
		 
		 edao.updateFollowupDate(enquiryDetails);
		 
	}

	@Override
	public List<FollowupDetails> getFollowupDetailsByEnquiryId(int enquiryTranId) {
		// TODO Auto-generated method stub

		return fdao.getFollowupDetailsByEnquiryId(enquiryTranId);
		
		
	}


	@Override
	public List<EnquiryDetails> getFollowupDetailsByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return fdao.getFollowupDetailsByProjectId(projectId);
	}

	/*@Override
	public List<ProjectDetails> getFollowupCountDetails(int userId, int userType) {
		
		List<ProjectDetails> projectDetails = fdao.getFollowupCountDetails(userId, userType);
		
		for(int i = 0; i < projectDetails.size() ; i++){
			
			if(projectDetails.get(i).getProjectStatus() == 1){
				projectDetails.get(i).setProjectStatusName("Upcoming");
			}else if(projectDetails.get(i).getProjectStatus() == 2){
				projectDetails.get(i).setProjectStatusName("In Progress");
			}else if(projectDetails.get(i).getProjectStatus() == 3){
				projectDetails.get(i).setProjectStatusName("Completed");
			}
		}
		return projectDetails;
		
	}*/

}
