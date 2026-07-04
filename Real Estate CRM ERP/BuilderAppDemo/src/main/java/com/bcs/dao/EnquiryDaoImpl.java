package com.bcs.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.CloseEnquiry;
import com.bcs.model.EnquiryDetails;
import com.bcs.model.WingDetails;
import com.bcs.utility.DateTimeUtil;


@Repository
public class EnquiryDaoImpl implements EnquiryDao {

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	
	//Session session = null;  
	//Transaction tx = null;	
	
	@Override
	public boolean addEnquiryDetails(EnquiryDetails enquiryDetails) {
		
		Session session = null;  
		Transaction tx = null;	
		boolean isRecordAdded = false;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			session.save(enquiryDetails);		
			isRecordAdded = true;
		}catch(Exception ex){
			
			ex.printStackTrace();
			 if(tx!= null){
				  tx.rollback();
		    }				
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return isRecordAdded;			
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EnquiryDetails> getAllEnquiryDetails() {
			
		 Session session = sessionFactory.openSession();
		 Transaction tx  = session.beginTransaction();
		
		 String resultQuery = " select ed.enquiryId as enquiryId, ed.firstName as firstName, ed.lastName as lastName, ed.mobileNo as mobileNo, ed.email as email, "
				+ " ed.landlineNo as landlineNo, ed.city as city, ed.address as address, ed.occupation as occupation, ed.company as company, "
				+ " ed.budgetMin as budgetMin, ed.budgetMax as budgetMax, pd.projectName as projectName, ed.propertyArea as propertyArea, "
				+ " r.referenceTypeDescr as referenceDescr, pt.propertyTypeDescr as propertyTypeDescr, p.propertyName as propertyName  "
				+ " from EnquiryDetails as ed, ProjectDetails as pd, Reference as r, PropertyType as pt, Property as p "
				+ " where ed.projectId  = pd.projectId "
				+ " and ed.propertyType = pt.propertyTypeId "
				+ " and ed.property     = p.propertyId "
				+ " and ed.reference    = r.referenceId "
				+ " and ed.isRemove    = 0 ";
				
		 Query query = session.createQuery(resultQuery);
		 List<EnquiryDetails> enquiryDetails = query.setTupleTransformer(Transformers.aliasToBean(EnquiryDetails.class)).list();  
		
		 tx.commit();
		 session.close();
		
		 return enquiryDetails;		
	}

	@Override
	public EnquiryDetails getEnquiryDetailsById(int id) {
		
		Session session        = null;  
		Transaction tx         = null;
		EnquiryDetails enquiryDetails = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			
			String resultQuery =  " select ed.enquiryId as enquiryId, ed.firstName as firstName, ed.lastName as lastName, ed.mobileNo as mobileNo, ed.email as email, ed.remark as remark, "
								+ " ed.landlineNo as landlineNo, ed.city as city, ed.address as address, ed.occupation as occupation, ed.company as company, ed.budgetMin as budgetMin, "
								+ " ed.budgetMax as budgetMax, pd.projectTranId as ProjectId, pd.projectName as projectName, ed.propertyArea as propertyArea, r.referenceId as reference, "
								+ " r.referenceTypeDescr as referenceDescr, pt.propertyTypeId as propertyType, pt.propertyTypeDescr as propertyTypeDescr, p.propertyId as property, "
								+ " p.propertyName as propertyName, ed.referenceName as referenceName from EnquiryDetails as ed, ProjectDetails as pd, Reference as r, PropertyType as pt, Property as p "
								+ " where ed.projectId  = pd.projectTranId "
								+ " and ed.propertyType = pt.propertyTypeId "
								+ " and ed.property     = p.propertyId "
								+ " and ed.reference    = r.referenceId "
								+ " and ed.enquiryId    = :enquiryId ";
			 
			  Query query     = session.createQuery(resultQuery).setParameter("enquiryId", id);
			 
			  enquiryDetails  = (EnquiryDetails) query.setTupleTransformer(Transformers.aliasToBean(EnquiryDetails.class)).uniqueResult();  
			     
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			session.close();
		}
		return enquiryDetails;
	}

	@Override
	public boolean updateEnquiryDetails(EnquiryDetails enquiryDetails) {
		Session session = null;  
		Transaction tx  = null; 
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession(); 
		    tx      = session.beginTransaction(); 
			
	        String hql  = " update EnquiryDetails set firstName = :firstName, lastName = :lastName,"
	        			+ " mobileNo = :mobileNo, landlineNo = :landlineNo, email = :email, city = :city, "
	        			+ " address = :address, occupation = :occupation, company = :company,projectId = :projectId, "
	        			+ " propertyType = :propertyType,property = :property, propertyArea = :propertyArea, budgetMin = :budgetMin, budgetMax = :budgetMax, "
	        			+ " reference = :reference, referenceName = :referenceName , userId = :userId where enquiryId = :enquiryId";
	        
	        session.createQuery(hql)
	        
	        .setParameter("enquiryId",enquiryDetails.getEnquiryId())
	        .setParameter("firstName",enquiryDetails.getFirstName())
	        .setParameter("lastName",enquiryDetails.getLastName())
	        .setParameter("mobileNo",enquiryDetails.getMobileNo())
	        .setParameter("landlineNo",enquiryDetails.getLandlineNo())
	        .setParameter("email",enquiryDetails.getEmail())
	        .setParameter("city",enquiryDetails.getCity())
	        .setParameter("address",enquiryDetails.getAddress())
	        .setParameter("occupation",enquiryDetails.getOccupation())
	        .setParameter("company",enquiryDetails.getCompany())
	        
	        .setParameter("projectId",enquiryDetails.getProjectId())
	        .setParameter("propertyType",enquiryDetails.getPropertyType())
	        .setParameter("property",enquiryDetails.getProperty())
	        .setParameter("propertyArea",enquiryDetails.getPropertyArea())
	        .setParameter("budgetMin",enquiryDetails.getBudgetMin())
	        .setParameter("budgetMax",enquiryDetails.getBudgetMax())
	        .setParameter("reference",enquiryDetails.getReference())
	        .setParameter("referenceName",enquiryDetails.getReferenceName())
	        .setParameter("userId",enquiryDetails.getUserId())
			.executeUpdate();
	      
	       tx.commit();  
	       isRecordUpdated = true;
		}catch(Exception e){	       
	        e.printStackTrace();
	    }finally{
	    	 if(session.isOpen())
	     	 session.close();	     		
	    }
		return isRecordUpdated;
		
	}

	@Override
	public boolean deleteEnquiryDetails(Integer enquiryId) {
		Session session = null;  
		Transaction tx = null;
		boolean isRecordDeleted = false;
		try{
			 session = sessionFactory.openSession();  
			 tx = session.beginTransaction(); 
			  
			String hqlUpdate = "update EnquiryDetails set isRemove = 1 where enquiryId = :enquiryId";
			session.createQuery(hqlUpdate)
					.setParameter("enquiryId", enquiryId)
					.executeUpdate();
				
			tx.commit();
			isRecordDeleted = true;
		}catch(Exception e){	        
	          e.printStackTrace();	
		}finally {
		      session.close();
		}
		return isRecordDeleted;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<WingDetails> getPropertyAreaByPropertyId(int projectId,int propertyTypeId, int propertyId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<WingDetails> propertyareaList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			String resultQuery = null;

			resultQuery = " select distinct wd.propertyArea as propertyArea from WingDetails as wd, ProjectDetails as p, Wing as w "+
					  	  " where p.projectTranId = w.projectId and w.wingTranId = wd.wingId and p.projectTranId = :projectId and wd.propertyTypeId = :propertyTypeId and "+
					      " wd.propertyId = :propertyId ";			
				
			Query query = session.createQuery(resultQuery).
						  setParameter("projectId", projectId).
						  setParameter("propertyTypeId", propertyTypeId).
						  setParameter("propertyId", propertyId);
			
			propertyareaList = query.setTupleTransformer(Transformers.aliasToBean(WingDetails.class)).list();  
		
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return propertyareaList;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnquiryDetails> getEnquiryDetailsByProjectId(int projectId) {
		
		Session session = sessionFactory.openSession();
		 Transaction tx  = session.beginTransaction();
		
		 String resultQuery = " select ed.enquiryId as enquiryId, ed.firstName as firstName, ed.lastName as lastName, ed.mobileNo as mobileNo, ed.remark as remark, "
							+ " ed.email as email, ed.budgetMax as budgetMax, p.propertyName as propertyName, ed.createdDatetime as createdDatetime"				
							+ " from EnquiryDetails as ed, ProjectDetails as pd, Reference as r, PropertyType as pt, Property as p "
							+ " where ed.projectId  = pd.projectTranId "
							+ " and ed.propertyType = pt.propertyTypeId "
							+ " and ed.property     = p.propertyId "
							+ " and ed.reference    = r.referenceId "
							+ " and ed.isRemove     = 0 and ed.followupStatus = 1"
							+ " and ed.projectId    = :projectId order by ed.enquiryId desc ";
				
		 Query query = session.createQuery(resultQuery).setParameter("projectId", projectId);
		 List<EnquiryDetails> enquiryDetails = query.setTupleTransformer(Transformers.aliasToBean(EnquiryDetails.class)).list();  
		
		 tx.commit();
		 session.close();
		
		 return enquiryDetails;
		
    }

	@Override
	public int getRecentEnquiryId() {
		
		    int enquiryId = 0;
		    
			Session session = sessionFactory.openSession();  
			Transaction tx = session.beginTransaction(); 
			
			List<Integer> maxIdList = session.createQuery(
					"select e.enquiryId from EnquiryDetails e order by e.enquiryId desc", Integer.class)
					.setMaxResults(1)
					.list();

		     if(maxIdList.isEmpty()){
		    	 enquiryId = 1;

		      }else{
		    	 enquiryId = maxIdList.get(0);
		     }
		     tx.commit();
		     session.close();

		return enquiryId;
	}

	@Override
	public void updateFollowupDate(EnquiryDetails enquiryDetails) {
		Session session = null;  
		Transaction tx  = null; 
		try{
			session = sessionFactory.openSession(); 
		    tx      = session.beginTransaction(); 
			
	        String hql  = " update EnquiryDetails set  followupDate = :followupDate, followupStatus = :followupStatus,"
	        			  + " updatedDatetime = :updatedDatetime where enquiryId = :enquiryId";
	        
	        session.createQuery(hql)
	        
	        .setParameter("enquiryId",enquiryDetails.getEnquiryId())
	        .setParameter("followupDate", enquiryDetails.getFollowupDate())
	        .setParameter("followupStatus", enquiryDetails.getFollowupStatus())
			.setParameter("updatedDatetime",enquiryDetails.getUpdatedDatetime())
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
	public boolean addClosedEnquiry(CloseEnquiry closeEnquiry) {
		
		Session session = null;  
		Transaction tx = null;	
		boolean isRecordAdded = false;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			String hql  = " update EnquiryDetails set followupStatus = :followupStatus, updatedDatetime = :updatedDatetime "
						+ " where enquiryId = :enquiryId";
       			         
		       session.createQuery(hql)
		       
		       .setParameter("enquiryId",closeEnquiry.getEnquiryId())
		       .setParameter("updatedDatetime", DateTimeUtil.getSysDateTime())
		       .setParameter("followupStatus", 3)
			   .executeUpdate();
			
			session.save(closeEnquiry);		
			isRecordAdded = true;
			}catch(Exception ex){
				
				ex.printStackTrace();
				 if(tx!= null){
					  tx.rollback();
			    }				
			}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return isRecordAdded;			
		
	}

	

	@SuppressWarnings("unchecked")
	@Override
	public List<CloseEnquiry> getAllClosedEnquiryByProjectId(int projectId, int followupStatus, int companyId) {
		
		Session session = null;  
		Transaction tx = null;	
		List<CloseEnquiry> closedEnquiry = null;
		try{
			
			session = sessionFactory.openSession();
			tx  = session.beginTransaction();
			 /*String resultQuery = "select ce.closedEnquiryId as closedEnquiryId, ce.reason as reason, concat(ed.firstName,' ', ed.lastName) as fullName, ed.enquiryId as enquiryId,"+
					 			  "ed.mobileNo as mobileNo, pd.projectName as projectName, ed.email as email, ce.createdDatetime as createdDatetime from CloseEnquiry ce, EnquiryDetails ed, "+
					 			  "ProjectDetails pd where pd.projectTranId = ed.projectId and ce.enquiryId = ed.enquiryId and pd.projectTranId = :projectId order by ce.closedEnquiryId desc";*/
			
			
			String resultQuery =  " select concat(ed.first_name,' ', ed.last_name) as fullName, ed.mobile_no as mobileNo, "
								+ " pd.project_name as projectName, ed.email as email, ce.reason as reason, ed.enquiry_id as enquiryId, "
								+ " (case when :followupStatus = 3 then ce.created_datetime ELSE ed.created_datetime END) as createdDatetime "
								+ " from project_details pd, enquiry_details ed "
								+ " LEFT JOIN "
								+ " close_enquiry ce ON ce.enquiry_id = ed.enquiry_id "
								+ " where pd.project_tran_id = ed.project_id "
								+ " and ed.is_remove = 0 "
								+ " and case when :followupStatus = 3 then ed.followup_status = 3 Else true END"
								//+ " and case when :projectId > 0 then pd.project_tran_id = :projectId Else true END"
								+ " and case when :projectId > 0 then pd.project_tran_id = :projectId Else pd.company_id = :companyId and pd.is_approved = 1 END"
								+ " order by (case when :followupStatus = 3 then ce.closed_enquiry_id ELSE ed.enquiry_id END) desc ";
					
					
			 Query query = session.createNativeQuery(resultQuery)
					 .setParameter("projectId", projectId)
					 .setParameter("followupStatus", followupStatus)
					 .setParameter("companyId", companyId);
			 closedEnquiry = query.setTupleTransformer(Transformers.aliasToBean(CloseEnquiry.class)).list();  
			
			 tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			 if(tx!= null){
				  tx.rollback();
		    }	
		}finally{
			
			session.close();
		}
		 return closedEnquiry;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<WingDetails> getPropertyUnitByProjectIdAndPropertyTypeId(int projectId, int propertyTypeId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<WingDetails> propertyList = null;
		try{
		
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			String resultQuery = null;
			
			resultQuery = " select distinct p.propertyName as propertyName, wd.propertyTypeId as propertyTypeId, wd.propertyId as propertyId from WingDetails as wd, ProjectDetails as pd, Wing as w, Property as p"+
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
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EnquiryDetails> getAllEnquiryAndClientListByProjectId(int projectId) {
		
		Session session = sessionFactory.openSession();
		Transaction tx  = session.beginTransaction();
		
		String resultQuery = " select ed.enquiryId as enquiryId, ed.firstName as firstName, ed.lastName as lastName "
							+ " from EnquiryDetails as ed, ProjectDetails as pd "
							+ " where ed.projectId  = pd.projectTranId "
							+ " and ed.isRemove     = 0 and ed.followupStatus != 3"
							+ " and pd.projectTranId = :projectId order by ed.followupStatus asc";
				
		 Query query = session.createQuery(resultQuery).setParameter("projectId", projectId);
		 List<EnquiryDetails> enquiryDetails = query.setTupleTransformer(Transformers.aliasToBean(EnquiryDetails.class)).list();  
		
		 tx.commit();
		 session.close();
		
		 return enquiryDetails;
		
    }

	@Override
	public boolean addRemark(EnquiryDetails enquiryDetails) {
		
		Session session = null;  
		Transaction tx  = null; 
		boolean isRecordUpdated  = false;
		
		try{
			session = sessionFactory.openSession(); 
		    tx      = session.beginTransaction(); 
			
	        String hql  = " update EnquiryDetails set  remark = :remark, remarkDate = :remarkDate "
	        			  + " where enquiryId = :enquiryId";
	        
	        Query query =  session.createQuery(hql)
			        .setParameter("enquiryId",enquiryDetails.getEnquiryId())
			        .setParameter("remarkDate", enquiryDetails.getRemarkDate())
			        .setParameter("remark", enquiryDetails.getRemark());
					
	         
	         int result = query.executeUpdate();			 
		 		if(result > 0 ){
		 			isRecordUpdated = true;
		 		}
	       
	       tx.commit();  
		}catch(Exception e){	       
	        e.printStackTrace();
	    }finally{
	    	 if(session.isOpen())
	     	 session.close();	     		
	    }
		return isRecordUpdated;
	}
}