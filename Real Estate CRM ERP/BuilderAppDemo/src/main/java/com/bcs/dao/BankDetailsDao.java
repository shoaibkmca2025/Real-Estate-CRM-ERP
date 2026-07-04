package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.BankDetails;

@Component
public interface BankDetailsDao {
	
	public List<BankDetails> getBankDetailsListById(int projectId);
	
	public boolean updateBankDetails(BankDetails bankdetails);
	
	public boolean addBankDetails(BankDetails bankdetails);

	public boolean deleteBankDetails(int bankId);
	
	public BankDetails getBankDetailsById(int BankId);

	String getAttachmentByBankId(int bankId);
	
	public List<BankDetails> getDisbursementBankDetailsListById(int projectDisbursementId);

}
