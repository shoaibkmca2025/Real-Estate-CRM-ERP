package com.bcs.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcs.model.OtherPayments;

@Component
public class OtherPaymentsDaoImpl implements OtherPaymentsDao{

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	
	@Override
	public boolean addOtherPayments(OtherPayments otherPayment) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					session.save(otherPayment);
					isRecordAdded = true;
					tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }	
		}finally{
			
			session.close();
		}
		return isRecordAdded;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OtherPayments> getOtherPaymentsListByClientId(int clientId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<OtherPayments> OtherPaymentsList = null;
		
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = " select op.otherPaymentId as otherPaymentId, op.title as title, op.amount as amount, op.paidDate as paidDate, op.dueDate as dueDate, "+
						 " op.chequeNumber as chequeNumber, op.chequeDate as chequeDate, op.clientId as clientId from OtherPayments as op, Client as cl "+
						 " where op.clientId = cl.clientTranId and op.clientId = :clientId ";
			
			Query query     = session.createQuery(hql).setParameter("clientId", clientId);
			OtherPaymentsList = query.setTupleTransformer(Transformers.aliasToBean(OtherPayments.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}		
		return OtherPaymentsList;
	}

	@Override
	public OtherPayments getOtherPaymentsById(int otherPaymentId) {
		
			Session session = null;  
			Transaction tx  = null;
			OtherPayments otherPayments = null;
			try{
				
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				String hql = " select op.otherPaymentId as otherPaymentId, op.title as title, op.amount as amount, op.paidDate as paidDate, "
							+" op.dueDate as dueDate, op.chequeNumber as chequeNumber, op.chequeDate as chequeDate, "
						 	+" op.clientId as clientId from OtherPayments as op where op.otherPaymentId = :otherPaymentId";
				
				Query query = session.createQuery(hql).setParameter("otherPaymentId", otherPaymentId);
				otherPayments        = (OtherPayments) query.setTupleTransformer(Transformers.aliasToBean(OtherPayments.class)).uniqueResult();
				
				tx.commit();
			}catch(Exception ex){
				
				ex.printStackTrace();
				
			}finally{
				
				session.close();
			}
			return otherPayments;
	}

	@Override
	public boolean updateOtherPayments(OtherPayments otherPayment) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = " update OtherPayments set title = :title, amount = :amount, paidDate = :paidDate, dueDate = :dueDate,"
								 + " chequeNumber = :chequeNumber, chequeDate = :chequeDate, " 
								 + " updatedDatetime = :updatedDatetime where otherPaymentId = :otherPaymentId";
				
				int result = session.createQuery(hqlUpdate)
									.setParameter("title", otherPayment.getTitle())
									.setParameter("amount", otherPayment.getAmount())
									.setParameter("paidDate", otherPayment.getPaidDate())
									.setParameter("dueDate", otherPayment.getDueDate())
									.setParameter("chequeNumber", otherPayment.getChequeNumber())
									.setParameter("chequeDate", otherPayment.getChequeDate())
									.setParameter("updatedDatetime", otherPayment.getUpdatedDatetime())
									.setParameter("otherPaymentId", otherPayment.getOtherPaymentId())
									.executeUpdate();
				 if(result > 0){
					 
					 isRecordUpdated = true;
				 }
			
			tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally {
		      session.close();
		}
		return isRecordUpdated;
	}

	@Override
	public boolean deleteOtherPayments(int otherPaymentId) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordDeleted = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
				    int result  =  session.createQuery("Delete OtherPayments where otherPaymentId = :otherPaymentId")
						   .setParameter("otherPaymentId", otherPaymentId)
						   .executeUpdate();
				    
				    if(result > 0){
				    	isRecordDeleted = true;
				    }
					
					tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();	
			
		}finally{
			
			session.close();
		}
		return isRecordDeleted;
	}
	

}
