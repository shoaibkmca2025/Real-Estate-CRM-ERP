package com.bcs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.OtherPaymentsDao;
import com.bcs.model.OtherPayments;
import com.bcs.utility.DateTimeUtil;

@Service
public class OtherPaymentsServiceImpl implements OtherPaymentsService{

	@Autowired
	private OtherPaymentsDao otherPaymentsDao;
	
	final static Logger logger = LoggerFactory.getLogger(OtherPaymentsServiceImpl.class);	
	
	@Override
	public boolean addOtherPayments(OtherPayments otherPayment) throws ParseException {
		
		 //SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		 
		// DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		// Date date = formatter.parse(otherPayment.getDate().toString());
		// Date date = sdf.parse(otherPayment.getPaidDate());
		// System.out.println("Disbursement date in date format: "+date);
		 if(otherPayment.getDate() != null){
			 otherPayment.setPaidDate(output.format(otherPayment.getDate()));
		 }
		 if(otherPayment.getDueDate1() != null){
			 otherPayment.setDueDate(output.format(otherPayment.getDueDate1()));
		 }
		 if(otherPayment.getChequeDate1() != null){
			 otherPayment.setChequeDate(output.format(otherPayment.getChequeDate1()));
		 }

		String dateTime = DateTimeUtil.getSysDateTime();
		otherPayment.setCreatedDatetime(dateTime);
		otherPayment.setUpdatedDatetime(dateTime);
		
		return otherPaymentsDao.addOtherPayments(otherPayment);
	}

	@Override
	public List<OtherPayments> getOtherPaymentsListByClientId(int clientId) {
	
		return otherPaymentsDao.getOtherPaymentsListByClientId(clientId);
	}

	@Override
	public OtherPayments getOtherPaymentsById(int otherPaymentId) {
		
		return otherPaymentsDao.getOtherPaymentsById(otherPaymentId);
	}

	@Override
	public boolean updateOtherPayments(OtherPayments otherPayment) throws ParseException {
		
		// SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		 
		 //Date date = sdf.parse(otherPayment.getPaidDate());
		 if(otherPayment.getDate() != null){
			 otherPayment.setPaidDate(output.format(otherPayment.getDate()));
		 }
		 if(otherPayment.getDueDate1() != null){
			 otherPayment.setDueDate(output.format(otherPayment.getDueDate1()));
		 }
		 if(otherPayment.getChequeDate1() != null){
			 otherPayment.setChequeDate(output.format(otherPayment.getChequeDate1()));
		 }		 
		 
		String dateTime = DateTimeUtil.getSysDateTime();		
		otherPayment.setUpdatedDatetime(dateTime);
		
		return otherPaymentsDao.updateOtherPayments(otherPayment);
	}

	@Override
	public boolean deleteOtherPayments(int otherPaymentId) {
		
		return otherPaymentsDao.deleteOtherPayments(otherPaymentId);
		
	}
	

}
