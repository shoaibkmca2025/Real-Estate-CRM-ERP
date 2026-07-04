package com.bcs.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.DashboardProjectDetails;
import com.bcs.model.MonthRefWiseDashboardDetails;


@Repository
public class DashboardDaoImpl implements DashboardDao{
	
	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }

	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardProjectDetails> getProjectDetailsCountOnDashboard(int companyId, int userId,int userType, int projectStatus) {
		 Session session = sessionFactory.openSession();
		 Transaction tx  = session.beginTransaction(); 
		 
					Query query  = session.createNativeQuery("CALL proc_getAllDashboardDetails(:companyId,:userType,:userId,:projectStatus)")
							.setParameter("companyId", companyId)
							.setParameter("userType", userType)
							.setParameter("userId", userId)
							.setParameter("projectStatus", projectStatus);
							       
		 List<DashboardProjectDetails> dashboardList = query.setTupleTransformer(Transformers.aliasToBean(DashboardProjectDetails.class)).list();  
		
		 tx.commit();
		 session.close();
		
		 return dashboardList;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MonthRefWiseDashboardDetails> getMonthwiseTotalEnquiries(int companyId, int userId, int userType, int projectStatus) {
		List<MonthRefWiseDashboardDetails> dashboardDetails = null;
		Session session = null;  
		Transaction tx = null;
		try{
			
				session = sessionFactory.openSession();
				tx = session.beginTransaction();	
			
				Query query = session.createNativeQuery("CALL proc_getMonthwiseTotalEnquiries(:userId,:userType,:companyId,:projectStatus)")
									 .setParameter("userId", userId)
									 .setParameter("userType", userType)
									 .setParameter("companyId", companyId)
									 .setParameter("projectStatus", projectStatus);
				
				dashboardDetails = query.setTupleTransformer(Transformers.aliasToBean(MonthRefWiseDashboardDetails.class)).list();   
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	         
		}finally{
		      session.close();
		}
		
		return dashboardDetails;
	}

	@Override
	public MonthRefWiseDashboardDetails getReferenceWiseTotalEnquiryAndClient(int companyId, int userId, int userType, int projectStatus) {

		Session session = null;  
		Transaction tx = null;
		MonthRefWiseDashboardDetails referenceWiseDetails  = null;
		try{
			
			 session = sessionFactory.openSession();
			 tx  	 = session.beginTransaction(); 
			 
			 Query query  = session.createNativeQuery("CALL proc_getReferencewiseDashboardDetails(:companyId,:userType,:userId,:projectStatus)")
					 			   .setParameter("companyId", companyId)
					 			   .setParameter("userType", userType)
					 			   .setParameter("userId", userId)
			 					   .setParameter("projectStatus", projectStatus);
								       
			 referenceWiseDetails = (MonthRefWiseDashboardDetails) query.setTupleTransformer(Transformers.aliasToBean(MonthRefWiseDashboardDetails.class)).uniqueResult();  
			
			 tx.commit();
			 
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			 session.close();
			
		}
		return referenceWiseDetails;	
	}
	
	@Override
	public MonthRefWiseDashboardDetails getReferenceWiseDetailsByProjectId(int projectId) {

		Session session = null;  
		Transaction tx = null;
		MonthRefWiseDashboardDetails refWiseDetails  = null;
		try{
			
			 session = sessionFactory.openSession();
			 tx  	 = session.beginTransaction(); 
			 
			 Query query  = session.createNativeQuery("CALL proc_getReferencewiseDashboardDetailsByProjectId(:projectId)")
					 			   .setParameter("projectId", projectId);
								       
			 refWiseDetails = (MonthRefWiseDashboardDetails) query.setTupleTransformer(Transformers.aliasToBean(MonthRefWiseDashboardDetails.class)).uniqueResult();  
			
			 tx.commit();
			 
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			 session.close();
			
		}
		return refWiseDetails;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DashboardProjectDetails> getMonthwiseCountDetailsByProjectId(int projectId) {
		
		List<DashboardProjectDetails> monthwiseDetails = null;
		Session session = null;  
		Transaction tx = null;
		try{
			
				session = sessionFactory.openSession();
				tx = session.beginTransaction();	
			
				Query query = session.createNativeQuery("CALL proc_getMonthwiseProjectDashboardDetails(:projectId)")
									 .setParameter("projectId", projectId);		
				
				monthwiseDetails = query.setTupleTransformer(Transformers.aliasToBean(DashboardProjectDetails.class)).list();   
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	         
		}finally{
		      session.close();
		}
		
		return monthwiseDetails;
	}

}
