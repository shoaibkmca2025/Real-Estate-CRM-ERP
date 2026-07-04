package com.bcs.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.DocumentType;
import com.bcs.model.Property;
import com.bcs.model.PropertyType;
import com.bcs.model.Reference;
import com.bcs.model.Settings;
import com.bcs.model.WingDetails;


@Repository
public class UtilityDaoImpl implements UtilityDao {

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Reference> getAllReferences() {		
		
		Session session = sessionFactory.openSession();
		Transaction tx  = session.beginTransaction();
		
		String resultQuery = "from Reference";			
		
		List<Reference> referenceList = session.createQuery(resultQuery).list();
		
		tx.commit();
		session.close();
		
		return referenceList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PropertyType> getAllPropertyType() {
		
		Session session = sessionFactory.openSession();
		Transaction tx  = session.beginTransaction();
		
		String resultQuery = "from PropertyType";			
		
		List<PropertyType> propertyTypeList = session.createQuery(resultQuery).list();
		
		tx.commit();
		session.close();
		
		return propertyTypeList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Property> getPropertyByPropertyType(int propertyTypeId) {
		Session session = null;  
		Transaction tx  = null;
		List<Property> propertyList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			String resultQuery = null;
			
			resultQuery = "select propertyId as propertyId, propertyName as propertyName from Property where propertyTypeId = :propertyTypeId ";			
			
			Query query = session.createQuery(resultQuery).setParameter("propertyTypeId", propertyTypeId);
			propertyList = query.setTupleTransformer(Transformers.aliasToBean(Property.class)).list();  
		
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return propertyList;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WingDetails> getPropertyByPropertyTypeAndProjectId(int projectId, int propertyTypeId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<WingDetails> propertyList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			String resultQuery = null;
			
			resultQuery = " select distinct p.propertyName as propertyName, wd.propertyTypeId as propertyTypeId, wd.propertyId as "+
						  " propertyId from WingDetails as wd, ProjectDetails as pd, Wing as w, Property as p"+
						  " where pd.projectTranId = w.projectId and w.wingTranId = wd.wingId and wd.propertyId = p.propertyId "+
					      " and pd.projectTranId = :projectId and wd.propertyTypeId = :propertyTypeId order by propertyId asc";	
			
			
			Query query = session.createQuery(resultQuery).
						  setParameter("projectId", projectId).
						  setParameter("propertyTypeId", propertyTypeId);
			
			propertyList = query.setTupleTransformer(Transformers.aliasToBean(WingDetails.class)).list(); 
		
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return propertyList;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DocumentType> getAllDocumentType() {
	
		Session session = sessionFactory.openSession();
		Transaction tx  = session.beginTransaction();
		
		String resultQuery = "from DocumentType";
		
		List<DocumentType> documentTypeList = session.createQuery(resultQuery).list();
		
		tx.commit();
		session.close();
		
		return documentTypeList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Property> getAllProperty() {
		
		Session session = sessionFactory.openSession();
		Transaction tx  = session.beginTransaction();
		
		String resultQuery = "from Property";			
		
		List<Property> propertyList = session.createQuery(resultQuery).list();
		
		tx.commit();
		session.close();
		
		return propertyList;
	}

	@Override
	public boolean updateSettings(Settings settings) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = " update Settings set sendersEmail = :sendersEmail, followupNotificationDuration = :followupNotificationDuration, "+ 
								   " paymentNotificationDuration = :paymentNotificationDuration, paymentDuedateDuration = :paymentDuedateDuration, "+
								   " updatedDatetime = :updatedDatetime where companyId = :companyId";
				
				session.createQuery(hqlUpdate)
						.setParameter("sendersEmail", settings.getSendersEmail())
						.setParameter("followupNotificationDuration", settings.getFollowupNotificationDuration())
						.setParameter("paymentNotificationDuration", settings.getPaymentNotificationDuration())
						.setParameter("paymentDuedateDuration", settings.getPaymentDuedateDuration())
						.setParameter("updatedDatetime", settings.getUpdatedDatetime())						
						.setParameter("companyId", settings.getCompanyId())
						.executeUpdate();
				
			
			tx.commit();
			isRecordUpdated = true;
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
	public Settings getSettingsByCompanyId(int companyId) {
		Session session = null;  
		Transaction tx  = null;
		Settings settings = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			settings = session.createQuery(
					"from Settings s where s.companyId = :companyId", Settings.class)
					.setParameter("companyId", companyId)
					.uniqueResult();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}		
		return settings;
		
	}

	@Override
	public boolean addSettings(Settings settings) {

		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					session.save(settings);
					
					tx.commit();
					isRecordAdded = true;
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

	  @SuppressWarnings("rawtypes")
		@Override
		public int getRecentId(Class objclass, String pid) {
	    	 Session session = null;  
	    	 Transaction tx = null;
	    	  int id = 0;
			 try{
					 session = sessionFactory.openSession();  
					 tx = session.beginTransaction(); 
					  		     
				     String recentIdHql = "select e." + pid + " from " + objclass.getSimpleName()
				    		  + " e order by e." + pid + " desc";
				     List<Integer> maxIdList = session.createQuery(recentIdHql, Integer.class)
				    		  .setMaxResults(1)
				    		  .list();
				     if(!maxIdList.isEmpty()) id = maxIdList.get(0);
				     
					  tx.commit();
			 }catch(Exception e){	        
		         e.printStackTrace();
		     }finally {
		    	 if(session.isOpen())
		    	session.close();
		    }		
			return id;	
		}
}