package com.bcs.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.CompanyProfileDao;
import com.bcs.dao.PaymentDetailsDao;
import com.bcs.dao.UtilityDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.CompanyProfile;
import com.bcs.model.PaymentDetails;
import com.bcs.model.Settings;
import com.bcs.utility.DateTimeUtil;

@Service
public class CompanyProfileServiceImpl implements CompanyProfileService{

	@Autowired
	CompanyProfileDao companyProfileDao;
	
	@Autowired
	UtilityDao utilityDao;
	
	@Autowired
	PaymentDetailsDao paymentDao;
	
	@Autowired  
	private ActivityDao activityDao;

	@Override
	public void addCompanyDetails(CompanyProfile companyDetails) throws ParseException {
		String dateTime = DateTimeUtil.getSysDateTime();
		companyDetails.setCreatedDatetime(dateTime); 
		companyDetails.setUpdatedDatetime(dateTime);
		
		String startDate = null;
		String endDate = null;
		Date date,date1;
		
			Calendar cal = Calendar.getInstance();
			//SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			cal.setTime(companyDetails.getResultDate());
			cal.add(Calendar.DATE, 31); // add 31 days to start date
			date1 = cal.getTime();
			
			/*DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); 	 ************************************* 
			 
			 Date date3 = df.parse(companyDetails.getStartDate());
			 DateFormat outputFormatter1 = new SimpleDateFormat("dd-MM-yyyy"); 
			 String output3 = outputFormatter1.format(date3); //					
			 System.out.println("*******************date"+output3);					 ************************************* 
			 	*/
			date = companyDetails.getResultDate();
			startDate = dateFormat.format(date);
			endDate = dateFormat.format(date1);
		
				boolean isAdded = companyProfileDao.addCompanyDetails(companyDetails);
		
				int recentCompId = companyDetails.getCompanyId();
		
		if(isAdded==true){
			Settings settings = new Settings();
			settings.setSendersEmail("noreply@ysmsoftware.com");
			settings.setFollowupNotificationDuration(8);
			settings.setPaymentDuedateDuration(8);
			settings.setPaymentNotificationDuration(6);
			settings.setCreatedDatetime(dateTime);
			settings.setUpdatedDatetime(dateTime);
			settings.setCompanyId(recentCompId);
			utilityDao.addSettings(settings);
			
			PaymentDetails payDetails = new PaymentDetails();
			//payDetails.setUserId();
			payDetails.setPaymentType(1);
			payDetails.setStartDate(startDate);
			payDetails.setEndDate(endDate);
			payDetails.setPaidDate(startDate);
			payDetails.setAmount(0);
			payDetails.setDiscount(0);
			payDetails.setTotalAmount(0);
			payDetails.setCompanyId(recentCompId);
			payDetails.setCreatedDatetime(dateTime);
			payDetails.setUpdatedDatetime(dateTime);
			paymentDao.addPaymentDetails(payDetails);
		}		
	}

	@Override
	public CompanyProfile getCompanyDetailsByUserId(int userId) {
		// TODO Auto-generated method stub
		return companyProfileDao.getCompanyDetailsByUserId(userId);
	}

	@Override
	public List<CompanyProfile> getAllCompanyDetails() {
		List<CompanyProfile> companyList = companyProfileDao.getAllCompanyDetails();
		return companyList;
	}

	@Override
	public CompanyProfile getCompanyDetailsByCompanyId(int companyId) {
		return companyProfileDao.getCompanyDetailsByCompanyId(companyId);
	}

	@Override
	public void updateCompanyByCompanyId(CompanyProfile companyProfile) {
		String dateTime = DateTimeUtil.getSysDateTime(); 
		companyProfile.setUpdatedDatetime(dateTime);
		
		companyProfileDao.updateCompanyByCompanyId(companyProfile);
	}
	
	@Override
	public String updateCompanyProfileDetailsByCompanyId(CompanyProfile companyProfile) {
		 String dateTime = DateTimeUtil.getSysDateTime();
		 companyProfile.setUpdatedDatetime(dateTime);
		 String LogoAttachment = null;
		 
		LogoAttachment =companyProfileDao.updateCompanyProfileDetailsByCompanyId(companyProfile);
		 
		if(LogoAttachment!=null || LogoAttachment!=""){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(0); 
			 activityLog.setActivityDescription("CompanyId"+companyProfile.getCompanyId()+" Details Updated");
			 activityLog.setProjectTranId(0);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return LogoAttachment;
	}
}
