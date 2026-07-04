package com.bcs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.BankDetails;

@Component
public interface BankDetailsService {

	public List<BankDetails> getBankDetailsListById(int projectId);
	
	public void updateBankDetails(BankDetails bankdetails);
	
	public void addBankDetails(BankDetails bankdetails);
	
	public boolean deleteBankDetails(int bankId, int userId, int projectId);
	
	public BankDetails getBankDetailsById(int BankId);
	
	String getAttachmentByBankId(int bankId);
	
	public List<BankDetails> getDisbursementBankDetailsListById(int projectDisbursementId);
}
