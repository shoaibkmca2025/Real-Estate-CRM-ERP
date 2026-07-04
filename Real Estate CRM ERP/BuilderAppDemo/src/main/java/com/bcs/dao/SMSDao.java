package com.bcs.dao;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.SMS;
import com.bcs.model.SMSCreditDetails;

@Component
public interface SMSDao {

	public boolean sendSms(SMS sms);
	
	public List<SMS> getAllSMS(int companyId, String date);
	
	public String getAllMobileNosByType(int userId, int type, int projectId );
	
	public boolean scheduleSms(SMS sms);
	
	public List<SMS> getAllScheduleSMSDetails();
	
	public boolean updateSendTimeAndSendStatusByScheduler(SMS sms, int failedMessageCost);
	
	public List<SMS> getAllScheduledSMSListByCompanyId(int companyId);
	
	public boolean addSmsCreditDetails(SMSCreditDetails smsCreditDetails);
	
	public List<SMSCreditDetails> getAllSmsCreditDetailsByCompanyId(int companyId);
	
	public SMSCreditDetails getAvailableSMSCreditsByCompanyId(int companyId);
}
