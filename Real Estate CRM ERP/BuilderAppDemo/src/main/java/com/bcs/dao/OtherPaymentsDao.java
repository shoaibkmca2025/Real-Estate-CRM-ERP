package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.OtherPayments;

@Component
public interface OtherPaymentsDao {

	public boolean addOtherPayments(OtherPayments otherPayment);
	
	public List<OtherPayments> getOtherPaymentsListByClientId(int clientId);
	
	public OtherPayments getOtherPaymentsById(int otherPaymentId);
	
	public boolean updateOtherPayments(OtherPayments otherPayment);
	
	public boolean deleteOtherPayments(int otherPaymentId);

}
