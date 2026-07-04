package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.model.PaymentDetails;

@Component
public interface PaymentDetailsDao {
	
	boolean addPaymentDetails(PaymentDetails paymentDetails);

	public List<PaymentDetails> getAllPaymentDetails();

	PaymentDetails getPaymentDetailsByPaymentId(int paymentId);

	void updatePaymentDetailsByPaymentId(PaymentDetails paymentDetails);

	public List<PaymentDetails> getAllPaymentNotifications();

	public List<String> getTodaysEndDateProjectIdList();

	//public List<PaymentDetails> getPaymentDetailsByUserId(int userId);
	public List<PaymentDetails> getPaymentDetailsByProjectId(int projectId);

	void updateSendInvoiceDate(PaymentDetails paymentDetails);
	
}
