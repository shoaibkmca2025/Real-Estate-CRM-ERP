package com.bcs.service;


import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.EnquiryDetails;
import com.bcs.model.FollowupDetails;

@Component
public interface FollowupService {

	public List<EnquiryDetails> getAllFollowupDetails(int userId, int userType, int companyId, int projectId);

	public void addFollowupDetails(FollowupDetails followupDetails) throws ParseException;

	public List<FollowupDetails> getFollowupDetailsByEnquiryId(int enquiryTranId);

	public List<EnquiryDetails> getFollowupDetailsByProjectId(int projectId);

}
