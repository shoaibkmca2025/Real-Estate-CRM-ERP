package com.bcs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.SMS;
import com.bcs.model.SMSCreditDetails;

@Component
public interface SMSService {
	
	public boolean sendSms(SMS sms);
	
	public List<SMS> getAllSMS(int userId, String date);
	
	public String getAllMobileNosByType(int userId, int type, int projectId);
	
	public boolean scheduleSms(SMS sms);
	
	public List<SMS> getAllScheduleSMSDetails();
	
	public boolean updateSendTimeAndSendStatusByScheduler(SMS sms, int failedMessageCost);
	
	public List<SMS> getAllScheduledSMSListByCompanyId(int comapnyId);
	
	public boolean addSmsCreditDetails(SMSCreditDetails smsCreditDetails);
	
	public List<SMSCreditDetails> getAllSmsCreditDetailsByCompanyId(int smsCreditDetails);
	
	public SMSCreditDetails getAvailableSMSCreditsByCompanyId(int companyId);

}
