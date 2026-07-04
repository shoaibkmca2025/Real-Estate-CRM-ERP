package com.bcs.service;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.DisbursementDao;
import com.bcs.dao.PaymentFollowupDao;
import com.bcs.model.Client;
import com.bcs.model.Disbursement;
import com.bcs.model.PaymentFollowup;
import com.bcs.utility.DateTimeUtil;

@Service
public class PaymentFollowupServiceImpl implements PaymentFollowupService{

	@Autowired  
	PaymentFollowupDao paymentFollowupDao;
	
	@Autowired
	DisbursementDao disbursementDao;
	
	@Override
	public boolean addPaymentFollowupDetails(PaymentFollowup paymentFollowup) throws ParseException {
		
		 String dateTime = DateTimeUtil.getSysDateTime();			    	  
		 paymentFollowup.setUpdatedDatetime(dateTime);	
		 paymentFollowup.setCreatedDatetime(dateTime);	
		 boolean isRecordAdded = paymentFollowupDao.addPaymentFollowupDetails(paymentFollowup);
		
		 if(isRecordAdded){
			 
			 //SimpleDateFormat sdf  = new SimpleDateFormat("M/dd/yyyy");
			 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");		 
			
			 //Date date = sdf.parse(paymentFollowup.getDisbursementFollowupDate());
			 
			 String paymentFollowupDate = dateFormat.format(paymentFollowup.getPaymentFollowupDate());
				
		 	 Disbursement disbursement = new Disbursement();
		 	
		 	 disbursement.setDisbursementId(paymentFollowup.getDisbursementId());
			 disbursement.setDisbursementFollowupDate(paymentFollowupDate);
			 disbursement.setUpdatedDatetime(dateTime);
			
			 disbursementDao.updateDisbursementFollowupDate(disbursement); 
		 }
			
		 return isRecordAdded;
		 
	}

	@Override
	public List<PaymentFollowup> getPaymentFollowupDetailsByDisbursementId(int disbursementId) {
		
		List<PaymentFollowup> paymentFollowupList = paymentFollowupDao.getPaymentFollowupDetailsByDisbursementId(disbursementId);
		Format format     = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		
		for(int i = 0; i < paymentFollowupList.size(); i++){			
			
			PaymentFollowup paymentFollowup = paymentFollowupList.get(i);
		
			if(paymentFollowup.getFollowupFlag().equals("1")){
				
				String description = "Rs."+format.format(paymentFollowup.getTotalPartialAmount()).substring(1)+" - "+paymentFollowup.getPaymentFollowupDescription();
				paymentFollowupList.get(i).setPaymentFollowupDescription(description);
				
			}
		}
		return paymentFollowupList;
	}

	@Override
	public List<Client> getTotalAndTodaysFollowup(int userId, int userType, int companyId, int projectId) {
		
		return paymentFollowupDao.getTotalAndTodaysFollowup(userId, userType, companyId, projectId);
	}
	

	@Override
	public PaymentFollowup getClientDetailsByDisbursementId(int disbursementId) {
		
		PaymentFollowup paymentFollowup = paymentFollowupDao.getClientDetailsByDisbursementId(disbursementId); 
		
		paymentFollowup.setPaymentFollowupList(getPaymentFollowupDetailsByDisbursementId(disbursementId));	
		
		return paymentFollowup;
	}

}
