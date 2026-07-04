package com.bcs.dao;

import java.util.List;
import org.springframework.stereotype.Component;


import com.bcs.model.CloseEnquiry;
import com.bcs.model.EnquiryDetails;
import com.bcs.model.WingDetails;

@Component
public interface EnquiryDao {
	
	public boolean addEnquiryDetails(EnquiryDetails enquiryDetails);
	
	public List<EnquiryDetails> getAllEnquiryDetails();
	
	public EnquiryDetails getEnquiryDetailsById(int id);
	
	public boolean updateEnquiryDetails(EnquiryDetails enquiryDetails);
	
	public List<WingDetails> getPropertyAreaByPropertyId(int projectId,int propertyTypeId, int propertyId);

	boolean deleteEnquiryDetails(Integer enquiryId);
	
	public List<EnquiryDetails> getEnquiryDetailsByProjectId(int projectId);

	public int getRecentEnquiryId();

	public void updateFollowupDate(EnquiryDetails enquiryDetails);
	
	public boolean addClosedEnquiry(CloseEnquiry closeEnquiry);
	
	public List<CloseEnquiry> getAllClosedEnquiryByProjectId(int projectId, int followupStatus, int companyId);

//	public List<WingDetails> getPropertyUnitByProjectIdAndPropertyTypeId(int projectId, int propertyTypeId);

	List<EnquiryDetails> getAllEnquiryAndClientListByProjectId(int projectId);
	
	public boolean addRemark(EnquiryDetails enquiryDetails);
	

}
