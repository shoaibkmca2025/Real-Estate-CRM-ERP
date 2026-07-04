package com.bcs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.OtherPayments;

@Component
public interface OtherPaymentsService {

	public boolean addOtherPayments(OtherPayments otherPayment) throws ParseException;
	
	public List<OtherPayments> getOtherPaymentsListByClientId(int clientId);
	
	public OtherPayments getOtherPaymentsById(int otherPaymentId);
	
	public boolean updateOtherPayments(OtherPayments otherPayment) throws ParseException;
	
	public boolean deleteOtherPayments(int otherPaymentId);

}
