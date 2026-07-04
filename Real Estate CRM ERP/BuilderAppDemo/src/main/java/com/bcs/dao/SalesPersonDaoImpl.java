package com.bcs.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.SalesPerson;
import com.bcs.utility.DateTimeUtil;

@Repository
public class SalesPersonDaoImpl implements SalesPersonDao {

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }
	
	@Override
	public void addSalesPerson(SalesPerson salesPerson) {		
		
		  Session session = null;  
		  Transaction tx  = null; 
		  try{ 
			  session = sessionFactory.openSession();  
			  tx      = session.beginTransaction();  
			  session.save(salesPerson);  
			  tx.commit(); 
					 				  
		  }catch(Exception e){	        
			   e.printStackTrace();
		  }finally {
			    	if(session.isOpen())
			    	session.close();
		   }
						
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesPerson> getAllSalesPerson() {
		
		Session session = null;  
		Transaction tx  = null;
		List<SalesPerson> salesPersonList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery = "select salesPersonId as salesPersonId, salesPersonName as salesPersonName, salesPersonEmail as salesPersonEmail, salesPersonAddress as salesPersonAddress, salesPersonMobile as salesPersonMobile, projectId as projectId from SalesPerson where isDisabled = 0";			
		
		Query query = session.createQuery(resultQuery);
		salesPersonList = query.setTupleTransformer(Transformers.aliasToBean(SalesPerson.class)).list();  
		
		tx.commit();
		session.close();
		return salesPersonList;
	}

	@Override
	public SalesPerson getSalesPersonById(int id) {
		
		Session session = null;  
		Transaction tx  = null; 
		SalesPerson salesPerson = null;
		try{
			session  = sessionFactory.openSession(); 
			 tx      = session.beginTransaction();
			
			 String hql = "select salesPersonId as salesPersonId, salesPersonName as salesPersonName, salesPersonEmail as salesPersonEmail, salesPersonAddress as salesPersonAddress, salesPersonMobile as salesPersonMobile, projectId as projectId from SalesPerson where salesPersonId =:salesPersonId";
		 			 
			 Query query  = session.createQuery(hql)
						   .setParameter("salesPersonId", id);
			 salesPerson = (SalesPerson) query.setTupleTransformer(Transformers.aliasToBean(SalesPerson.class)).uniqueResult();		
			 
			 tx.commit(); 
		}catch(Exception e){	       
	        e.printStackTrace();
	    }finally{
	    	 if(session.isOpen())
	     	 session.close();	     		
	    }			  
	     return salesPerson;		
	}

	@Override
	public void updateSalesPerson(SalesPerson salesPerson) {
		
		Session session = null;  
		Transaction tx  = null; 
		try{
			session = sessionFactory.openSession(); 
		    tx      = session.beginTransaction(); 
			
	        String hql  = " update SalesPerson set salesPersonName = :salesPersonName, salesPersonEmail = :salesPersonEmail, salesPersonAddress = :salesPersonAddress,"+
	        			  " salesPersonMobile =:salesPersonMobile, projectId =:projectId, updatedDateTime = :updatedDateTime where salesPersonId = :salesPersonId";
	        session.createQuery(hql)
			.setParameter("salesPersonId",salesPerson.getSalesPersonId())
			.setParameter("salesPersonName",salesPerson.getSalesPersonName())
			.setParameter("salesPersonEmail", salesPerson.getSalesPersonEmail())
			.setParameter("salesPersonAddress",salesPerson.getSalesPersonAddress())
			.setParameter("salesPersonMobile",salesPerson.getSalesPersonMobile())
			.setParameter("projectId",salesPerson.getProjectId())
			.setParameter("updatedDateTime",salesPerson.getUpdatedDateTime())
			.executeUpdate();
	      
	       tx.commit();  
		}catch(Exception e){	       
	        e.printStackTrace();
	    }finally{
	    	 if(session.isOpen())
	     	 session.close();	     		
	    }
		 
	}

	@Override
	public void deleteSalesPerson(int id) {
		
		Session session = null;  
		Transaction tx  = null; 
		try{
			session = sessionFactory.openSession(); 
		    tx      = session.beginTransaction(); 
			
	        String hql  = " update SalesPerson set isDisabled = :isDisabled, updatedDateTime = :updatedDateTime where salesPersonId = :salesPersonId";
	        session.createQuery(hql)
			.setParameter("salesPersonId",id)
			.setParameter("isDisabled",1)			
			.setParameter("updatedDateTime",DateTimeUtil.getSysDateTime())
			.executeUpdate();
	      
	       tx.commit();  
		}catch(Exception e){	       
	        e.printStackTrace();
	    }finally{
	    	 if(session.isOpen())
	     	 session.close();	     		
	    }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SalesPerson> getSalesPersonByProjectId(int projectId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<SalesPerson> salesPersonList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery = "select salesPersonId as salesPersonId, salesPersonName as salesPersonName from SalesPerson where isDisabled = 0 and projectId =:projectId";			
		
		Query query = session.createQuery(resultQuery)
				      .setParameter("projectId",projectId);
		salesPersonList = query.setTupleTransformer(Transformers.aliasToBean(SalesPerson.class)).list();  
		
		tx.commit();
		session.close();
		return salesPersonList;
	}
}
