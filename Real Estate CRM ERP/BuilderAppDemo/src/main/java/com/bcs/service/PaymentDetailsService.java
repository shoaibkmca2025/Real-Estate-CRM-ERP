package com.bcs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.PaymentDetails;

@Component
public interface PaymentDetailsService {
	
	void addPaymentDetails(PaymentDetails paymentDetails) throws ParseException;

	public List<PaymentDetails> getAllPaymentDetails();

	PaymentDetails getPaymentDetailsByPaymentId(int paymentId);

	void updatePaymentDetailsByPaymentId(PaymentDetails paymentDetails) throws ParseException;

	public List<PaymentDetails> getAllPaymentNotifications();
	
	public List<String> getTodaysEndDateProjectIdList();

	//public List<PaymentDetails> getPaymentDetailsByUserId(int userId);
	public List<PaymentDetails> getPaymentDetailsByProjectId(int projectId);
	
	public String createInvoiceLetterPdf(int paymentId,int companyId);

	void updateSendInvoiceDate(PaymentDetails paymentDetails);

}
