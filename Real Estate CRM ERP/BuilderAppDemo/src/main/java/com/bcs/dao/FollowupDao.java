package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.EnquiryDetails;
import com.bcs.model.FollowupDetails;

@Component
public interface FollowupDao {
	
	public List<EnquiryDetails> getAllFollowupDetails(int userId, int userType, int companyId, int projectId);

	public void addFollowupDetails(FollowupDetails followupDetails);

	public List<FollowupDetails> getFollowupDetailsByEnquiryId(int enquiryId);

	public List<EnquiryDetails> getFollowupDetailsByProjectId(int projectId);
	
}
