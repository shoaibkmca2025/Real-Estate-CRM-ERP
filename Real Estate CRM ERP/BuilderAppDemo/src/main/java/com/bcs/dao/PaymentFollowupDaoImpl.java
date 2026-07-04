package com.bcs.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.Client;
import com.bcs.model.PaymentFollowup;

@Repository
public class PaymentFollowupDaoImpl implements PaymentFollowupDao{
	
	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }

	@Override
	public boolean addPaymentFollowupDetails(PaymentFollowup paymentFollowup) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordAdded = false;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			session.save(paymentFollowup);		
			isRecordAdded = true;
			tx.commit();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return isRecordAdded;
				
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentFollowup> getPaymentFollowupDetailsByDisbursementId(int disbursementId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<PaymentFollowup> followupDetailsList=null;

		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			/*resultQuery = " select pf.paymentFollowupDescription as paymentFollowupDescription, d.disbursementId as disbursementId,"
						+ " d.disbursementFollowupDate as disbursementFollowupDate, pf.createdDatetime as createdDatetime"
						+ " from PaymentFollowup as pf, Disbursement d "
						+ " where pf.disbursementId = d.disbursementId "
						+ " and pf.disbursementId = :disbursementId"
						+ " and pf.isRemove  = 0 ORDER BY pf.paymentFollowupId DESC ";	*/		

		   String sqlQuery =  " SELECT tbl.disbursementId, tbl.totalPartialAmount, tbl.paymentFollowupDescription, tbl.disbursementFollowupDate,"
		   					+ " tbl.createdDatetime, tbl.followupFlag FROM "
				   			+ " (select d.disbursement_id as disbursementId, 0.0 as totalPartialAmount, pf.payment_followup_descr as paymentFollowupDescription,"
		   					+ " d.disbursement_followup_date as disbursementFollowupDate, pf.created_datetime as createdDatetime, '0' as followupFlag, "
		   					+ " pf.created_datetime as datetime from payment_followup as pf, disbursement d "
		   					+ " where pf.disbursement_id = d.disbursement_id "
		   					+ " and pf.is_remove  = 0 and pf.disbursement_id = :disbursementId"
		   					+ " UNION "
		   					+ " select dpp.disbursement_id as disbursementId, dpp.total_partial_amount as totalPartialAmount, dpp.partial_payment_description as "
		   					+ " paymentFollowupDescription, d.disbursement_followup_date as disbursementFollowupDate, "
		   					+ " dpp.partial_payment_paid_date as createdDatetime, '1' as followupFlag, dpp.created_datetime as datetime"
		   					+ " from disbursement_partial_payments as dpp, disbursement d"
		   					+ " where dpp.disbursement_id = d.disbursement_id and dpp.disbursement_id = :disbursementId ) as tbl"
		   					+ " ORDER BY STR_TO_DATE(tbl.datetime, '%d/%m/%Y %h:%i:%s %p') DESC";
			
			Query query = session.createNativeQuery(sqlQuery).setParameter("disbursementId", disbursementId);
			
			followupDetailsList = query.setTupleTransformer(Transformers.aliasToBean(PaymentFollowup.class)).list();  
		
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		
		return followupDetailsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getTotalAndTodaysFollowup(int userId, int userType, int companyId, int projectId) {
		
		Session session        = null;  
		Transaction tx         = null;
		List<Client> clientList = null;
		try{
			
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
			
		        Query query  = session.createNativeQuery("CALL proc_paymentTotalAndTodaysFollowupDetails(:userId,:userType,:companyId,:projectId)")
		        			          .setParameter("userId", userId)
		        			          .setParameter("userType", userType)		        			         
		        			          .setParameter("companyId", companyId)
		                              .setParameter("projectId", projectId);
		        
		        clientList = query.setTupleTransformer(Transformers.aliasToBean(Client.class)).list();
		        
		        tx.commit();     
		}catch(Exception ex){
			 ex.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
	  		session.close();
		}
		
		return clientList;
	}
	
	@Override
	public PaymentFollowup getClientDetailsByDisbursementId(int disbursementId) {
	
		Session session        = null;  
		Transaction tx         = null;
		PaymentFollowup paymentFollowup = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String resultQuery  =   " select d.disbursement_id as disbursementId, c.owner_name as clientName, ed.mobile_no as mobileNo, p.project_Name as projectName"
									+ " FROM client c, enquiry_details ed, project_details as p, disbursement as d"
									+ " where c.client_tran_id = d.client_id"
									+ " and p.project_tran_id = c.project_id"
									+ " and d.disbursement_id = :disbursementId"
									+ " and c.enquiry_id = ed.enquiry_id";									
			 
			  Query query       =  session.createNativeQuery(resultQuery).setParameter("disbursementId", disbursementId);
			 
			  paymentFollowup   = (PaymentFollowup) query.setTupleTransformer(Transformers.aliasToBean(PaymentFollowup.class)).uniqueResult();  
			     
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return paymentFollowup;
	}
}
