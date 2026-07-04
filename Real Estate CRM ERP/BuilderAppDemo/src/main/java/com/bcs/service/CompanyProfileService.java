package com.bcs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.CompanyProfile;

@Component
public interface CompanyProfileService {

	void addCompanyDetails(CompanyProfile companyDetails) throws ParseException;

	CompanyProfile getCompanyDetailsByUserId(int userId);

	public List<CompanyProfile> getAllCompanyDetails();

	CompanyProfile getCompanyDetailsByCompanyId(int companyId);

	void updateCompanyByCompanyId(CompanyProfile companyProfile);
	
	public String updateCompanyProfileDetailsByCompanyId(CompanyProfile companyProfile);
}
