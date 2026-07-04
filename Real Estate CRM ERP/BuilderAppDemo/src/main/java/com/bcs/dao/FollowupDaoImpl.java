package com.bcs.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.EnquiryDetails;
import com.bcs.model.FollowupDetails;

@Repository
public class FollowupDaoImpl implements FollowupDao{
	
	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }

	@SuppressWarnings("unchecked")
	public List<EnquiryDetails> getAllFollowupDetails(int userId, int userType, int companyId, int projectId) {
		
		 Session session = sessionFactory.openSession();
		 Transaction tx  = session.beginTransaction(); 
		 
					Query query  = session.createNativeQuery("CALL proc_getAllFollowupDetails(:userId,:userType,:companyId,:projectId)")
							.setParameter("userId", userId)
							.setParameter("userType", userType)
							.setParameter("companyId", companyId)
					        .setParameter("projectId", projectId);
							       
		 List<EnquiryDetails> enquiryDetails = query.setTupleTransformer(Transformers.aliasToBean(EnquiryDetails.class)).list();  
		
		 tx.commit();
		 session.close();
		
		 return enquiryDetails;	
	}
	
	@Override
	public void addFollowupDetails(FollowupDetails followupDetails) {
		
		Session session = null;  
		Transaction tx = null;
		
		session = sessionFactory.openSession();  
		tx = session.beginTransaction();  
		
		session.save(followupDetails);		
		
		tx.commit();
		session.close();		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<FollowupDetails> getFollowupDetailsByEnquiryId(int enquiryId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<FollowupDetails> followupDetailsList=null;

		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			String resultQuery = null;
			
			
			resultQuery = " SELECT * FROM (select fd.followup_descr as followupDescription, ed.enquiry_id as enquiryId, "
						+ " ed.followup_date as nextFollowupDate, fd.created_datetime as createdDatetime, ed.first_name as firstName, "
						+ " ed.last_name as lastName, ed.mobile_no as mobileNo, pd.project_name as projectName, "
						+ " ed.property_area as propertyArea, p.property_name as propertyName, ed.project_id as projectId, 0 as flag "
						+ " from followup_details as fd, enquiry_details as ed , project_details as pd , property as p  "
						+ " where ed.enquiry_id=fd.enquiry_id and fd.enquiry_id = :enquiryId "
						+ " and ed.property   = p.property_id "
						+ " and ed.project_id = pd.project_tran_id "
						+ " and fd.is_remove  = 0 "
						+ " UNION "
						+ " select ed.remark as followupDescription, ed.enquiry_id as enquiryId, ed.followup_date as nextFollowupDate, "
						+ " ed.remark_date as createdDatetime, ed.first_name as firstName, ed.last_name as lastName, ed.mobile_no as mobileNo, "
						+ " pd.project_name as projectName, ed.property_area as propertyArea, p.property_name as propertyName, "				
						+ " ed.project_id as projectId, 1 as flag "
						+ " from enquiry_details as ed , project_details as pd , property as p "
						+ " where ed.enquiry_id = :enquiryId "
						+ " and ed.property   = p.property_id "
						+ " and ed.project_id  = pd.project_tran_id "
						+ " and ed.remarK IS NOT NULL "
						+ " ) as tbl ORDER BY STR_TO_DATE(tbl.createdDatetime , '%d/%m/%Y') desc ";

			
			/*resultQuery = " select fd.followupDescription as followupDescription, ed.enquiryId as enquiryId, ed.followupDate as nextFollowupDate,"
					+ " fd.createdDatetime as createdDatetime,ed.firstName as firstName, ed.lastName as lastName, ed.mobileNo as mobileNo,"
					+ " pd.projectName as projectName, ed.propertyArea as propertyArea, p.propertyName as propertyName,ed.projectId as projectId"
					+ " from FollowupDetails as fd, EnquiryDetails as ed , ProjectDetails as pd , Property as p  "
					+ " where ed.enquiryId=fd.enquiryId and fd.enquiryId = :enquiryId "
					+ " and ed.property   = p.propertyId "
					+ " and ed.projectId  = pd.projectTranId "
					+ " and fd.isRemove   = 0 and ed.followupStatus = 1 ORDER BY fd.followupId DESC ";*/			

			
			Query query = session.createNativeQuery(resultQuery).
						  setParameter("enquiryId", enquiryId);
			
			followupDetailsList = query.setTupleTransformer(Transformers.aliasToBean(FollowupDetails.class)).list();  
		
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
	public List<EnquiryDetails> getFollowupDetailsByProjectId(int projectId) {
		 Session session = sessionFactory.openSession();
		 Transaction tx  = session.beginTransaction(); 
		 
		 Query query  = session.createNativeQuery("CALL proc_getFollowupDetailsByProjectId(:projectId)")
				 .setParameter("projectId", projectId);
					
		 List<EnquiryDetails> enquiryDetails = query.setTupleTransformer(Transformers.aliasToBean(EnquiryDetails.class)).list();  
		
		 tx.commit();
		 session.close();
		
		 return enquiryDetails;	
	}


}
