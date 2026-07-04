package com.bcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.EnquiryDao;
import com.bcs.dao.FloorDetailsDao;
import com.bcs.dao.WingDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.Wing;
import com.bcs.model.WingDetails;
import com.bcs.utility.DateTimeUtil;

@Service
public class WingServiceImpl implements WingService{

	@Autowired
	private WingDao wingdao;
	
	@Autowired
	private ProjectService projectservice;	
	
	@Autowired
	private EnquiryDao enquiryDao;
	
	@Autowired  
	private ActivityDao activityDao;
	
	@Autowired  
	private FloorDetailsDao floorDetailsDao;
	
	@Override
	public List<Wing> getWingListByProjectId(int projectId) {
		
		return wingdao.getWingListByProjectId(projectId);
	}
	
	@Override
	public List<WingDetails> getWingDetailsByProjectId(int projectId) {
		
		return wingdao.getWingDetailsByProjectId(projectId);
	}

	@Override
	public Wing getWingById(int wingId) {
		
		 Wing wing = wingdao.getWingById(wingId);
		 wing.setWingdetailsList(wingdao.getAllWingDetailsByWingId(wingId));
		 wing.setFloorList(floorDetailsDao.getFloorDetailsByWingId(wingId));
		 
		return wing;
	}

	@Override
	public boolean updateWing(Wing wing) {
		boolean isRecordUpdated = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		wing.setUpdatedDatetime(dateTime);
		
		isRecordUpdated =wingdao.updateWing(wing);
		
		if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(wing.getUserId()); 
			 activityLog.setActivityDescription("WingId "+wing.getWingTranId()+" Updated");
			 activityLog.setActivityDatetime(dateTime);
			 activityLog.setProjectTranId(wing.getProjectId());
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordUpdated;
	}

	@Override
	public boolean addWing(Wing wing) {
		
		String dateTime = DateTimeUtil.getSysDateTime();
		wing.setUpdatedDatetime(dateTime);
		boolean isRecordAdded = false;
		wing.setWingTranId(projectservice.getRecentWingId());
		wing.setCreatedDatetime(dateTime);
		
		isRecordAdded =wingdao.addWing(wing);		
		
		if(isRecordAdded==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(wing.getUserId()); 
			 activityLog.setActivityDescription("WingId "+wing.getWingTranId()+" Added");
			 activityLog.setActivityDatetime(dateTime);
			 activityLog.setProjectTranId(wing.getProjectId());
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordAdded;
	}

	@Override
	public boolean deleteWing(int wingId, int projectId, int userId) {
		
		boolean isRecordDeleted = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordDeleted =wingdao.deleteWing(wingId, projectId);	
		
		if(isRecordDeleted==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("WingId "+wingId+" Deleted");
			 activityLog.setActivityDatetime(dateTime);
			 activityLog.setProjectTranId(projectId);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordDeleted;
	}

	@Override
	public List<WingDetails> getAllWingDetailsList() {
		
		return wingdao.getAllWingDetailsList();
	}

	@Override
	public List<Wing> getAllWing() {
		
		return wingdao.getAllWing();
	}

	@Override
	public List<WingDetails> getAllWingDetailsByWingId(int wingId) {
		
		return wingdao.getAllWingDetailsByWingId(wingId);
	}
	
	@Override
	public WingDetails getUnbookedPropertyDetails(int wingId, int flatNumber, int projectId, int floorNumber, String floorName) {
		
		 WingDetails wingDetails  = wingdao.getUnbookedPropertyDetails(wingId, flatNumber,floorNumber, floorName);
		 
		 wingDetails.setEnquiryList(enquiryDao.getAllEnquiryAndClientListByProjectId(projectId));
		
		return wingDetails;
	}

}
