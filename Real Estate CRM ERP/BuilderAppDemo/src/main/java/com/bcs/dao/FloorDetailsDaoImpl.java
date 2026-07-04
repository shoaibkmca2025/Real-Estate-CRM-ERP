package com.bcs.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.FloorDetails;

@Repository
public class FloorDetailsDaoImpl implements FloorDetailsDao{

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }
	
	@Override
	public boolean addFloorDetails(FloorDetails floordetails) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					session.save(floordetails);
					
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FloorDetails> getFloorDetailsByWingId(int wingId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<FloorDetails> floorDetailsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql =  " select fd.floorId as floorId, fd.floorNumber as floorNumber,  fd.floorName as floorName "
						+ " from FloorDetails as fd, Wing as w "
						+ " where fd.wingId = w.wingTranId and w.wingTranId = :wingId";
			
			Query query = session.createQuery(hql)				
			         	 .setParameter("wingId", wingId);
			
			floorDetailsList = query.setTupleTransformer(Transformers.aliasToBean(FloorDetails.class)).list();			
			tx.commit();
			
		}catch(Exception ex){			
			ex.printStackTrace();			
		}finally{
			
			session.close();
		}
		return floorDetailsList;
	}

}
