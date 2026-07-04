package com.bcs.service;

import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bcs.model.CloseEnquiry;
import com.bcs.model.EnquiryDetails;
import com.bcs.model.WingDetails;

@Component
public interface EnquiryService {
	
	public boolean addEnquirydetails(EnquiryDetails enquiryDetails);
	
	public List<EnquiryDetails> getAllEnquiryDetails();
	
	public EnquiryDetails getEnquiryDetailsById(int id);
	
	public boolean updateEnquiryDetails(EnquiryDetails enquiryDetails);
	
	public boolean deleteEnquiryDetails(Integer enquiryId, int userId, int projectId);
	
	public List<WingDetails> getPropertyAreaByPropertyId(int projectId,int propertyTypeId, int propertyId);
	
	public List<EnquiryDetails> getEnquiryDetailsByProjectId(int projectId);

	public int getRecentEnquiryId();
	
	public boolean addClosedEnquiry(CloseEnquiry closeEnquiry);
	
	public List<CloseEnquiry> getAllClosedEnquiryByProjectId(int projectId, int followupStatus, int companyId);

	/*public List<WingDetails> getPropertyUnitByProjectIdAndPropertyTypeId(int projectId, int propertyTypeId);*/
	
	List<EnquiryDetails> getAllEnquiryAndClientListByProjectId(int projectId);

	public void genarateClosedEnquiryExcelReport(int projectId, int followupStatus, int companyId, HttpServletResponse response);
	
	public boolean addRemark(EnquiryDetails enquiryDetails);
	
	
}
