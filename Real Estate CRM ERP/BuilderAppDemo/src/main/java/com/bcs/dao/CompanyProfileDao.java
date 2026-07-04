package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.CompanyProfile;
import com.bcs.model.User;

@Component
public interface CompanyProfileDao {

	boolean addCompanyDetails(CompanyProfile companyDetails);

	CompanyProfile getCompanyDetailsByUserId(int userId);

	public List<CompanyProfile> getAllCompanyDetails();

	CompanyProfile getCompanyDetailsByCompanyId(int companyId);

	void updateCompanyByCompanyId(CompanyProfile companyProfile);

	public String updateCompanyProfileDetailsByCompanyId(CompanyProfile companyProfile);

}
