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
import com.bcs.model.Wing;
import com.bcs.model.WingDetails;

@Repository
public class WingDaoImpl implements WingDao {

	 private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<Wing> getWingListByProjectId(int projectId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<Wing> WingList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = "select w.wingId as wingId, w.wingName as wingName, w.noOfFloors as noOfFloors, w.totalFlats as totalFlats, "+
						 "w.wingTranId as wingTranId from Wing as w, ProjectDetails as p where w.projectId = p.projectTranId and p.projectTranId = :projectId";
			
			Query query = session.createQuery(hql).setParameter("projectId", projectId);
			WingList = query.setTupleTransformer(Transformers.aliasToBean(Wing.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		return WingList;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<WingDetails> getWingDetailsByProjectId(int projectId) {
	
		Session session = null;  
		Transaction tx  = null;
		List<WingDetails> wingDetailsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = "select wd.wingDetailsId as wingDetailsId, wd.floorNumber as floorNumber, pr.propertyName as propertyName, wd.propertyId as propertyId, wd.propertyArea as propertyArea, wd.noOfFlats as noOfFlats, "+
						 "pt.propertyTypeDescr as propertyTypeDescr, pt.propertyTypeId as propertyTypeId, w.wingTranId as wingId from WingDetails as wd, Wing as w, ProjectDetails as p, PropertyType as pt, "+
						 "Property as pr where pr.propertyId = wd.propertyId and pt.propertyTypeId = pr.propertyTypeId and wd.wingId = w.wingTranId and "+
						 "w.projectId = p.projectTranId and p.projectTranId = :projectId";
			
			
			Query query = session.createQuery(hql).setParameter("projectId", projectId);
			wingDetailsList = query.setTupleTransformer(Transformers.aliasToBean(WingDetails.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		return wingDetailsList;
	}
	
	@Override
	public Wing getWingById(int wingId) {
		
			Session session = null;  
			Transaction tx  = null;
			Wing wing = null;
			try{
				
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				String hql = "select w.wingTranId as wingTranId, w.wingName as wingName, w.noOfFloors as noOfFloors, w.totalFlats as totalFlats, "+
							 "w.isManualFloors as isManualFloors from Wing as w where w.wingTranId = :wingId";
				
				Query query = session.createQuery(hql).setParameter("wingId", wingId);
				wing        = (Wing) query.setTupleTransformer(Transformers.aliasToBean(Wing.class)).uniqueResult();
				
				tx.commit();
			}catch(Exception ex){
				
				ex.printStackTrace();
				
			}finally{
				
				session.close();
			}
			return wing;
	}
	
	@Override
	public boolean updateWing(Wing wing) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update Wing set wingName = :wingName, noOfFloors = :noOfFloors, totalFlats = :totalFlats, "+ 
								   "userId = :userId, updatedDatetime = :updatedDatetime where wingTranId = :wingTranId";
				
				session.createQuery(hqlUpdate)
						.setParameter("wingName", wing.getWingName())
						.setParameter("noOfFloors", wing.getNoOfFloors())
						.setParameter("totalFlats", wing.getTotalFlats())
						.setParameter("updatedDatetime", wing.getUpdatedDatetime())
						.setParameter("userId", wing.getUserId())
						.setParameter("wingTranId", wing.getWingTranId())
						.executeUpdate();
				
				String hql = "Delete WingDetails where wingId = :wingId";				
				session.createQuery(hql).setParameter("wingId", wing.getWingTranId())
				.executeUpdate();
				
				List<FloorDetails> floordetailsList =  wing.getFloorList();
				for(int i=0; i < floordetailsList.size(); i++){
					
					FloorDetails floorDetails = floordetailsList.get(i);
					System.out.println(floorDetails);
					if(floorDetails.getFloorId() == 0){
						
						floorDetails.setWingId(wing.getWingTranId());
						floorDetails.setCreatedDatetime(wing.getUpdatedDatetime());
						floorDetails.setUpdatedDatetime(wing.getUpdatedDatetime());
						floorDetails.setUserId(wing.getUserId());
						session.save(floorDetails);	
					}
				}
				
				
				List<WingDetails> wingdetailsList =  wing.getWingdetailsList();
				
				for(int i=0; i < wingdetailsList.size(); i++){
						
					WingDetails wdetails = wingdetailsList.get(i);
					
					wdetails.setWingId(wing.getWingTranId());
					wdetails.setCreatedDatetime(wing.getUpdatedDatetime());
					wdetails.setUpdatedDatetime(wing.getUpdatedDatetime());
					wdetails.setUserId(wing.getUserId());
					session.save(wdetails);					
				}
				
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
	public boolean addWing(Wing wing) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		
		int recordCount = 0;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
					session.save(wing);
					/*Query query = session.createQuery(
					        "select count(*) from Wing w where w.projectId = :projectId");
					query.setParameter("projectId", wing.getProjectId());
				
					Long count = (Long)query.uniqueResult();
					
					 query = session.createQuery( "update ProjectDetails set  noOfWings = :noOfWings where projectTranId = :projectId");
					 query.setParameter("projectId", wing.getProjectId())
					 .setParameter("noOfWings", count.intValue()).executeUpdate();*/
					 
					 List<FloorDetails> floordetailsList =  wing.getFloorList();
						
					 for(int i=0; i < floordetailsList.size(); i++){
						
						 FloorDetails floordetails = floordetailsList.get(i);
						 floordetails.setWingId(wing.getWingTranId());
						 floordetails.setUserId(wing.getUserId());
						 floordetails.setCreatedDatetime(wing.getCreatedDatetime());
						 floordetails.setUpdatedDatetime(wing.getUpdatedDatetime());
						 
						 session.save(floordetails);
							
						 recordCount ++;
						 if(recordCount % 30 == 0 ){
								session.flush();
								session.clear();
						}
					 }
					 
					 List<WingDetails> wingdetailsList =  wing.getWingdetailsList();
						
						for(int i=0; i < wingdetailsList.size(); i++){
								
							WingDetails wdetails = wingdetailsList.get(i);							
							wdetails.setWingId(wing.getWingTranId());
							wdetails.setCreatedDatetime(wing.getCreatedDatetime());
							wdetails.setUpdatedDatetime(wing.getUpdatedDatetime());
							wdetails.setUserId(wing.getUserId());
							
							session.save(wdetails);
							
							recordCount ++;
							recordCount += wdetails.getNoOfFlats();
							
							if(recordCount % 30 == 0 ){
								
								session.flush();
								session.clear();
							}
							
						}
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

	@Override
	public boolean deleteWing(int wingId, int projectId) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordDeleted = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
					session.createQuery("Delete Wing where wingTranId = :wingTranId").setParameter("wingTranId", wingId).executeUpdate();
					
					String hql = "Delete WingDetails where wingId = :wingId";
					session.createQuery(hql).setParameter("wingId", wingId).executeUpdate();
					
					Query query = session.createQuery(
					        "select count(*) from Wing w where w.projectId = :projectId");
					query.setParameter("projectId", projectId);
				
					Long count = (Long)query.uniqueResult();
					
					query = session.createQuery( "update ProjectDetails set  noOfWings = :noOfWings where projectTranId = :projectId");
					query.setParameter("projectId", projectId)
					.setParameter("noOfWings", count.intValue()).executeUpdate();
					
					tx.commit();
					isRecordDeleted = true;
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }	
		}finally{
			
			session.close();
		}
		return isRecordDeleted;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WingDetails> getAllWingDetailsList() {
		
		Session session = null;  
		Transaction tx  = null;
		List<WingDetails> wingDetailsList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery = "select wingDetailsId as wingDetailsId, propertyId as propertyId, propertyArea as propertyArea, propertyTypeId as propertyTypeId, "+
					  "wingId as wingId from WingDetails where isRemove = 0";			

		
		Query query = session.createQuery(resultQuery);
		wingDetailsList = query.setTupleTransformer(Transformers.aliasToBean(WingDetails.class)).list();  
		
		tx.commit();
		session.close();
		return wingDetailsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Wing> getAllWing() {
		
		Session session = null;  
		Transaction tx  = null;
		List<Wing> wingList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery = "select wingId as wingId, wingTranId as wingTranId, projectId as projectId from Wing where isRemove = 0";			

		
		Query query = session.createQuery(resultQuery);
		wingList = query.setTupleTransformer(Transformers.aliasToBean(Wing.class)).list();  
		
		tx.commit();
		session.close();
		return wingList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<WingDetails> getAllWingDetailsByWingId(int wingId) {
		Session session = null;  
		Transaction tx  = null;
		List<WingDetails> wingDetailsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql =  " select wd.wingDetailsId as wingDetailsId,  wd.floorNumber as floorNumber, pr.propertyName as propertyName,"
						+ " wd.propertyId as propertyId, wd.propertyArea as propertyArea, wd.noOfFlats as noOfFlats, wd.floorName as "
						+ " floorName, pt.propertyTypeDescr as propertyTypeDescr, pt.propertyTypeId as propertyTypeId, w.wingTranId as wingId "
						+ " from WingDetails as wd, Wing as w, PropertyType as pt, Property as pr "
						+ " where pr.propertyId = wd.propertyId and pt.propertyTypeId = pr.propertyTypeId  "
						+ " and wd.wingId = w.wingTranId and w.wingTranId = :wingId";
			
			
			Query query = session.createQuery(hql)				
			         .setParameter("wingId", wingId);
			wingDetailsList = query.setTupleTransformer(Transformers.aliasToBean(WingDetails.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		return wingDetailsList;
	}
	
	@Override
	public WingDetails getUnbookedPropertyDetails(int wingId, int flatNumber, int floorNumber,  String floorName){
			
		Session session = null;  
		Transaction tx  = null;
		WingDetails wingDetails = null;
		
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql =   " select wd.propertyTypeId as propertyTypeId, pt.propertyTypeDescr as propertyTypeDescr, wd.propertyId as propertyId, p.propertyName as propertyName, "
						 + " wd.floorNumber as floorNumber, wd.propertyArea as propertyArea, w.wingName as wingName, w.wingTranId as wingId, f.flatNumber as flatNumber, wd.floorName as floorName "
						 + " from Flats as f, WingDetails as wd, Wing as w, PropertyType as pt, Property as p "
					     + " where f.wingDetailsId = wd.wingDetailsId and f.wingId = wd.wingId and pt.propertyTypeId = wd.propertyTypeId and p.propertyId = wd.propertyId and wd.wingId = w.wingTranId "
						 + " and wd.wingId = :wingId and f.flatNumber = :flatNumber and f.floorNumber = :floorNumber and f.floorName = :floorName ";
			
			Query query = session.createQuery(hql).setParameter("wingId", wingId)
												  .setParameter("flatNumber", flatNumber)
												  .setParameter("floorNumber", floorNumber)
												  .setParameter("floorName", floorName);
												  
			wingDetails = (WingDetails) query.setTupleTransformer(Transformers.aliasToBean(WingDetails.class)).uniqueResult();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		return wingDetails;
	}


	
}
