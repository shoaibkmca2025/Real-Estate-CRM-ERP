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
import com.bcs.model.EnquiryDetails;
import com.bcs.model.PaymentDetails;

@Repository
public class PaymentDetailsDaoImpl implements PaymentDetailsDao{
	
	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }

	@Override
	public boolean addPaymentDetails(PaymentDetails paymentDetails) {
		// TODO Auto-generated method stub
		Session session = null;  
		Transaction tx = null;
		boolean isAdded = false;
		try{
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			session.save(paymentDetails);		
			isAdded = true;
			}catch(Exception ex){
				
				ex.printStackTrace();
				 if(tx!= null){
					  tx.rollback();
			    }				
			}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return isAdded;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentDetails> getAllPaymentDetails() {
		
			
		Session session = null;  
		Transaction tx  = null;
		List<PaymentDetails> paymentList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery = "select pd.paymentId as paymentId, pd.companyId as companyId, c.companyName as companyName,c.landline as landline, "+
					  "pd.paymentType as paymentType, pd.amount as amount, pd.startDate as startDate, pd.endDate as endDate, pd.paidDate as paidDate, "+ 
					  "pd.discount as discount,pd.gst as gst, pd.totalAmount as totalAmount from PaymentDetails as pd,CompanyProfile as c "+
					   "where pd.companyId = c.companyId ";	
		
		Query query = session.createQuery(resultQuery);
		paymentList = query.setTupleTransformer(Transformers.aliasToBean(PaymentDetails.class)).list();  
		
		tx.commit();
		session.close();
		return paymentList;
	}

	@Override
	public PaymentDetails getPaymentDetailsByPaymentId(int paymentId) {
		Session session = null;  
		Transaction tx = null; 
		PaymentDetails paymentDetails = null;
		try{

			session = sessionFactory.openSession();
			tx      = session.beginTransaction();		
			
			String resultQuery =  " select pd.paymentId as paymentId, pd.companyId as companyId, pd.projectId as projectId, p.projectName as projectName, pd.paymentType as paymentType, pd.startDate as startDate, pd.endDate as endDate, "
							+ " pd.amount as amount, pd.paidDate as paidDate, pd.discount as discount, pd.gst as gst, pd.totalAmount as totalAmount, pd.sendInvoiceDate as sendInvoiceDate "
							+ " from PaymentDetails as pd, ProjectDetails as p"
							+ " where pd.projectId  = p.projectTranId "
							+ " and pd.paymentId   = :paymentId ";
		 
		  Query query     = session.createQuery(resultQuery).setParameter("paymentId", paymentId);
		 
		  paymentDetails  = (PaymentDetails) query.setTupleTransformer(Transformers.aliasToBean(PaymentDetails.class)).uniqueResult();  
				/*Criteria criteria = session.createCriteria(PaymentDetails.class)
						           .add(Restrictions.eq("paymentId", paymentId));						  
				paymentDetails  =  (PaymentDetails) criteria.uniqueResult();	*/			        
				         
			tx.commit();
		}catch(Exception e){	        
		      e.printStackTrace();
		}finally {				 
		      session.close();
		}	
		return paymentDetails;	
	}

	@Override
	public void updatePaymentDetailsByPaymentId(PaymentDetails paymentDetails) {
		Session session = null;  
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update PaymentDetails set paymentType = :paymentType, amount = :amount,startDate = :startDate,endDate = :endDate, "+ 
								   "paidDate = :paidDate, discount = :discount,gst = :gst, totalAmount = :totalAmount , updatedDatetime = :updatedDatetime where paymentId = :paymentId";
				
				session.createQuery(hqlUpdate)
						//.setParameter("userId", paymentDetails.getUserId())
						.setParameter("paymentType", paymentDetails.getPaymentType())
						.setParameter("amount", paymentDetails.getAmount())
						.setParameter("startDate", paymentDetails.getStartDate())
						.setParameter("endDate", paymentDetails.getEndDate())
						.setParameter("paidDate", paymentDetails.getPaidDate())
						.setParameter("discount", paymentDetails.getDiscount())
						.setParameter("gst", paymentDetails.getGst())
						.setParameter("totalAmount", paymentDetails.getTotalAmount())
						.setParameter("paymentId", paymentDetails.getPaymentId())
						.setParameter("updatedDatetime", paymentDetails.getUpdatedDatetime())
						.executeUpdate();
				
			
			tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		    }	
		}finally {
		      session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentDetails> getAllPaymentNotifications() {
		Session session = null;  
		Transaction tx  = null;
		List<PaymentDetails> paymentNotificationsList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		
		 /*String sqlQuery = 	"select * from(select tbl.payment_id as paymentId, tbl.company_id as companyId, tbl.company_name as companyName, "+
		 			"tbl.landline as landline, tbl.payment_type as paymentType,tbl.start_date as startDate, tbl.end_date as endDate "+ 
		 			"from (select pd.payment_id, pd.company_id, cp.company_name, cp.landline,pd.payment_type, pd.start_date, pd.end_date "+
		 			"from company_payment_details as pd,company_profile as cp where pd.company_id = cp.company_id order by pd.payment_id desc ) as tbl "+ 
		 			"group by tbl.company_id ) as tbl1 where STR_TO_DATE(tbl1.endDate , '%d/%m/%Y')<= DATE_ADD(CURRENT_DATE(), INTERVAL 8 DAY)";*/	
		
		String sqlQuery = "select pd.payment_id as paymentId, pd.company_id as companyId, pd.project_id as projectId, p.project_name as projectName, cp.company_name as companyName, cp.landline as landline, "
				+ " pd.payment_type as paymentType, pd.start_date as startDate, pd.end_date as endDate from company_profile as cp, company_payment_details as pd, project_details as p, "
				+ "(select Max(pd.payment_id) as paymentid from company_payment_details as pd group by pd.project_id ) as tbl "
				+ "where pd.company_id = cp.company_id and tbl.paymentid = pd.payment_id and pd.project_id = p.project_tran_id  "
				+ "and STR_TO_DATE(pd.end_date , '%d/%m/%Y')<= DATE_ADD(CURRENT_DATE(), INTERVAL 8 DAY)";

		Query query = session.createNativeQuery(sqlQuery);
		paymentNotificationsList = query.setTupleTransformer(Transformers.aliasToBean(PaymentDetails.class)).list();  
		
		tx.commit();
		session.close();
		return paymentNotificationsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTodaysEndDateProjectIdList() {
		Session session = null;  
		Transaction tx  = null;
		List<String> projectList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		
		/*String sqlQuery = 	"select pd.payment_id as paymentId, pd.user_id as userId, u.user_name as userName,u.user_mobile as userMobile, "+
	 			"pd.payment_type as paymentType, pd.amount as amount, pd.start_date as startDate, pd.end_date as endDate, "+ 
	 			"pd.discount as discount, pd.total_amount as totalAmount from company_payment_details as pd,user as u "+
	 			"where pd.user_id = u.user_id "+ 
	 			"and  STR_TO_DATE(pd.end_date , '%d/%m/%Y' ) = CURRENT_DATE()";	*/
		
		String sqlQuery = "select project_id as projectId from company_payment_details "+
	 					" where STR_TO_DATE(end_date , '%d/%m/%Y' ) = CURRENT_DATE()";	

		Query query = session.createNativeQuery(sqlQuery);
		projectList = query.list();   // setResultTransformer(Transformers.aliasToBean(PaymentDetails.class)).list();  
		
		tx.commit();
		session.close();
		return projectList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentDetails> getPaymentDetailsByProjectId(int projectId) {
		Session session = null;  
		Transaction tx  = null;
		List<PaymentDetails> paymentList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		
		String sqlQuery = "select pd.payment_id as paymentId, pd.company_id as companyId, pd.project_id as projectId, p.project_name as projectName, c.company_name as companyName,c.company_email as companyEmail, c.landline as landline, "+
					  "c.mobile as mobile, c.website as website, c.address as address,pd.payment_type as paymentType, pd.amount as amount, "+ 
					  "pd.start_date as startDate, pd.end_date as endDate, pd.paid_date as paidDate, pd.discount as discount, pd.gst as gst, pd.total_amount as totalAmount, "+ 
					  "Case When STR_TO_DATE(pd.start_date , '%d/%m/%Y') >= DATE_SUB(CURRENT_DATE(), INTERVAL 15 DAY) Then 1 Else 0 End  as editFlag, pd.send_invoice_date as sendInvoiceDate "+ 
					  "from company_payment_details as pd,company_profile as c,project_details as p where pd.company_id = c.company_id and pd.project_id = p.project_tran_id and pd.project_id = :projectId order by pd.payment_id desc";	
		
		Query query = session.createNativeQuery(sqlQuery).setParameter("projectId", projectId);
		paymentList = query.setTupleTransformer(Transformers.aliasToBean(PaymentDetails.class)).list();  
		
		tx.commit();
		session.close();
		return paymentList;
	}

	@Override
	public void updateSendInvoiceDate(PaymentDetails paymentDetails) {
		Session session = null;  
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update PaymentDetails set sendInvoiceDate = :sendInvoiceDate , updatedDatetime = :updatedDatetime"
						+ " where paymentId = :paymentId";
				
				session.createQuery(hqlUpdate)
						
						.setParameter("sendInvoiceDate", paymentDetails.getUpdatedDatetime())
						.setParameter("paymentId", paymentDetails.getPaymentId())
						.setParameter("updatedDatetime", paymentDetails.getUpdatedDatetime())
						.executeUpdate();
				
			
			tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		    }	
		}finally {
		      session.close();
		}
	}



}
