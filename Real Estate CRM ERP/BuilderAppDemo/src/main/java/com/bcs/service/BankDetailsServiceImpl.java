package com.bcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.BankDetailsDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.BankDetails;
import com.bcs.utility.DateTimeUtil;

@Service
public class BankDetailsServiceImpl implements BankDetailsService{

	@Autowired
	private BankDetailsDao bankdetailsdao;
	
	@Autowired  
	private ActivityDao activityDao;
	
	@Override
	public List<BankDetails> getBankDetailsListById(int projectId) {
		
		return bankdetailsdao.getBankDetailsListById(projectId);
	}

	@Override
	public void updateBankDetails(BankDetails bankdetails) {
		
		String dateTime = DateTimeUtil.getSysDateTime();
		boolean isRecordUpdated = false;
		bankdetails.setUpdatedDatetime(dateTime);
		
		isRecordUpdated = bankdetailsdao.updateBankDetails(bankdetails);	

		if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(bankdetails.getUserId()); 
			 activityLog.setActivityDescription("BankId "+bankdetails.getBankId()+" Updated");
			 activityLog.setProjectTranId(bankdetails.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
	}

	@Override
	public void addBankDetails(BankDetails bankdetails) {
		
		String dateTime = DateTimeUtil.getSysDateTime();
		boolean isRecordAdded = false;
		bankdetails.setCreatedDatetime(dateTime);
		bankdetails.setUpdatedDatetime(dateTime);
		isRecordAdded = bankdetailsdao.addBankDetails(bankdetails);
		
		if(isRecordAdded==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(bankdetails.getUserId()); 
			 activityLog.setActivityDescription("BankId "+bankdetails.getBankId()+" added");
			 activityLog.setProjectTranId(bankdetails.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
	}

	@Override
	public boolean deleteBankDetails(int bankId, int userId, int projectId) {
		
		boolean isRecordDeleted = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordDeleted = bankdetailsdao.deleteBankDetails(bankId);

		if(isRecordDeleted==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("BankId "+bankId+" Deleted");
			 activityLog.setProjectTranId(projectId);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordDeleted;
	}

	@Override
	public BankDetails getBankDetailsById(int BankId) {
		
		return bankdetailsdao.getBankDetailsById(BankId);
	}

	@Override
	public String getAttachmentByBankId(int bankId) {
	
		return bankdetailsdao.getAttachmentByBankId(bankId);
	}

	@Override
	public List<BankDetails> getDisbursementBankDetailsListById(int projectDisbursementId) {
		
		return bankdetailsdao.getDisbursementBankDetailsListById(projectDisbursementId);
	}
	
	
}
