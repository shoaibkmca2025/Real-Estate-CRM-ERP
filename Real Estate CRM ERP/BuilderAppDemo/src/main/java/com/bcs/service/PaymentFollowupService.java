package com.bcs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.model.PaymentFollowup;

@Component
public interface PaymentFollowupService {
	
	public boolean addPaymentFollowupDetails(PaymentFollowup paymentFollowup) throws ParseException;
	
	List<PaymentFollowup> getPaymentFollowupDetailsByDisbursementId(int disbursementId);
	
	List<Client> getTotalAndTodaysFollowup(int userId, int userType, int companyId, int projectId);
	
	public PaymentFollowup getClientDetailsByDisbursementId(int disbursementId);
}
