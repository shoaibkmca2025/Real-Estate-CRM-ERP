package com.bcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.UtilityDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.DocumentType;
import com.bcs.model.Property;
import com.bcs.model.PropertyType;
import com.bcs.model.Reference;
import com.bcs.model.Settings;
import com.bcs.model.WingDetails;
import com.bcs.utility.DateTimeUtil;

@Service
public class UtilityServiceImpl implements UtilityService {

	@Autowired
	UtilityDao utilityDao;
	
	@Autowired  
	private ActivityDao activityDao;
	
	@Override
	public List<Reference> getAllReferences() {
		
		return utilityDao.getAllReferences();
	}

	@Override
	public List<PropertyType> getAllPropertyType() {
		
		return utilityDao.getAllPropertyType();
	}
	
	@Override
	public List<Property> getPropertyByPropertyType(int propertyTypeId) {
		
		return utilityDao.getPropertyByPropertyType(propertyTypeId);
	}
	
	@Override
	public List<WingDetails> getPropertyByPropertyTypeAndProjectId(int projectId, int propertyTypeId){
		return utilityDao.getPropertyByPropertyTypeAndProjectId(projectId, propertyTypeId);
	}

	@Override
	public List<DocumentType> getAllDocumentType() {
	
		return utilityDao.getAllDocumentType();
	}

	@Override
	public List<Property> getAllProperty() {
		
		return utilityDao.getAllProperty();
	}

	@Override
	public boolean updateSettings(Settings settings) {
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		 boolean isRecordUpdated = false;
		 settings.setUpdatedDatetime(dateTime);
		 
		 isRecordUpdated = utilityDao.updateSettings(settings);
		 
		 if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(settings.getUserId()); 
			 activityLog.setActivityDescription("Settings of CompanyId["+settings.getCompanyId()+"] Updated");
			 activityLog.setProjectTranId(0);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		  
		return isRecordUpdated;
	}

	@Override
	public Settings getSettingsByCompanyId(int companyId) {
	
		return utilityDao.getSettingsByCompanyId(companyId);
	}

	@Override
	public boolean addSettings(Settings settings) {
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		 
		 boolean isRecordUpdated = false;
		 
		 settings.setCreatedDatetime(dateTime);
		 settings.setUpdatedDatetime(dateTime);
		
		 isRecordUpdated = utilityDao.addSettings(settings);
		 
		 if(isRecordUpdated==true){
			 
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(settings.getUserId()); 
			 activityLog.setActivityDescription("Settings of CompanyId["+settings.getCompanyId()+"] added");
			 activityLog.setProjectTranId(0);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		  
		return isRecordUpdated;
	}

	
	
	@SuppressWarnings("rawtypes")
	@Override
	public int getRecentId(Class objclass, String pid) {
		
		return utilityDao.getRecentId(objclass, pid);
	}
	

}
