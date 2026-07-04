package com.bcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.SMSDao;
import com.bcs.model.SMS;
import com.bcs.model.SMSCreditDetails;
import com.bcs.utility.DateTimeUtil;

@Service
public class SMSServiceImpl implements SMSService {
	
	@Autowired
	SMSDao smsdao;

	@Override
	public boolean sendSms(SMS sms) {
		
			String dateTime = DateTimeUtil.getSysDateTime();
			sms.setCreatedDatetime(dateTime);
			sms.setUpdatedDatetime(dateTime);
			sms.setSmsSendTime(dateTime);
			return smsdao.sendSms(sms);
		
	}

	@Override
	public List<SMS> getAllSMS(int userId, String date) {
		
		return smsdao.getAllSMS(userId, date);
	}

	@Override
	public String getAllMobileNosByType(int userId, int type, int projectId) {
		
		return smsdao.getAllMobileNosByType(userId, type, projectId);
	}

	@Override
	public boolean scheduleSms(SMS sms) {
		String dateTime = DateTimeUtil.getSysDateTime();
		sms.setCreatedDatetime(dateTime);
		sms.setUpdatedDatetime(dateTime);
		return smsdao.scheduleSms(sms);
	}

	@Override
	public List<SMS> getAllScheduleSMSDetails() {
		
		return smsdao.getAllScheduleSMSDetails();
	}

	@Override
	public boolean updateSendTimeAndSendStatusByScheduler(SMS sms, int failedMessageCost) {
		
		String dateTime = DateTimeUtil.getSysDateTime();
		sms.setUpdatedDatetime(dateTime);
		return smsdao.updateSendTimeAndSendStatusByScheduler(sms, failedMessageCost);
	}

	@Override
	public List<SMS> getAllScheduledSMSListByCompanyId(int companyId) {
		
		return smsdao.getAllScheduledSMSListByCompanyId(companyId);
	}

	@Override
	public boolean addSmsCreditDetails(SMSCreditDetails smsCreditDetails) {
		String dateTime = DateTimeUtil.getSysDateTime();
		String date = DateTimeUtil.getSysDate();
		smsCreditDetails.setUpdatedDatetime(dateTime);
		//smsCreditDetails.setAvailableCredits(smsCreditDetails.getTotalCredits());
		smsCreditDetails.setInsertionDate(date);
		return smsdao.addSmsCreditDetails(smsCreditDetails);
	}

	@Override
	public List<SMSCreditDetails> getAllSmsCreditDetailsByCompanyId(int companyId) {
		
		return smsdao.getAllSmsCreditDetailsByCompanyId(companyId);
	}

	@Override
	public SMSCreditDetails getAvailableSMSCreditsByCompanyId(int companyId) {
		
		return smsdao.getAvailableSMSCreditsByCompanyId(companyId);
	}

}
