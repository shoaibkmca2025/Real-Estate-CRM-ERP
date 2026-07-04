package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.model.PaymentDetails;
import com.bcs.model.PaymentFollowup;
@Component
public interface PaymentFollowupDao {

	public boolean addPaymentFollowupDetails(PaymentFollowup paymentFollowup);

	List<PaymentFollowup> getPaymentFollowupDetailsByDisbursementId(int disbursementId);
	
	List<Client> getTotalAndTodaysFollowup(int userId, int userType, int companyId, int projectId);
	
	public PaymentFollowup getClientDetailsByDisbursementId(int disbursementId);
	
}
