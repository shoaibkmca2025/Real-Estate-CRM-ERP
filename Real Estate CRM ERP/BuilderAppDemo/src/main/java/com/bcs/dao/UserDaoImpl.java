package com.bcs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.bcs.model.User;
import com.bcs.utility.DateTimeUtil;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao{

	 private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	 
	 @Autowired
     public void setDs(DataSource dataSource) {
         setDataSource(dataSource);
     }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserDetails() {
		
		Session session = null;  
		Transaction tx  = null;
		List<User> userList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;

		resultQuery =  " select u.userId as userId, u.userName as userName, u.userEmail as userEmail,u.userMobile as userMobile, u.userType as userType,"
				  	  +" u.userStatus as userStatus, u.userPassword as userPassword, c.companyId as companyId, c.companyName as companyName, "
				      +" c.logoPath as logoPath from User as u, CompanyProfile as c where u.isRemove = 0 and c.companyId = u.companyId order by c.companyId";

		Query query = session.createQuery(resultQuery);
		userList 	= query.setTupleTransformer(Transformers.aliasToBean(User.class)).list();  
		
		tx.commit();
		session.close();
		return userList;
	}

	@Override
	public boolean addUser(User user) {
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					session.save(user);
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

	@Override
	public boolean updateUser(User user) {

		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update User set userName = :userName, userEmail = :userEmail, userMobile = :userMobile, "+ //builderId = :builderId,
								   "userType = :userType, userPassword = :userPassword, updatedDatetime = :updatedDatetime where userId = :userId";
				
				int result = session.createQuery(hqlUpdate)
									.setParameter("userName", user.getUserName())
									.setParameter("userEmail", user.getUserEmail())
									.setParameter("userMobile", user.getUserMobile())
									//.setParameter("builderId", user.getBuilderId())
									.setParameter("userType", user.getUserType())
									.setParameter("userPassword", user.getUserPassword())
									.setParameter("updatedDatetime", user.getUpdatedDatetime())
									.setParameter("userId", user.getUserId())
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
	public boolean updateUserStatus(int userId, int userStatus) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdateUser = "update User set userStatus = :userStatus, updatedDatetime = :updatedDatetime "+
										  "where userId = :userId";	
				
				int result = session.createQuery(hqlUpdateUser)
								    .setParameter("userStatus", userStatus)
								    .setParameter("updatedDatetime", DateTimeUtil.getSysDateTime())
								    .setParameter("userId", userId)
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
		}finally{
		      session.close();
		}
		return isRecordUpdated;
	}

/*	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserDetailsByBuilderId(int builderId) {
		Session session = null;  
		Transaction tx  = null;
		List<User> userDetailsList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery =  " select u.userId as userId, u.userName as userName, u.userEmail as userEmail, u.userMobile as userMobile, u.userPassword as userPassword, "
					  +" u.userStatus as userStatus, u.userType as userType, c.logoPath as logoPath from User as u,CompanyProfile as c "
					  +" where u.isRemove = 0 and u.builderId =:builderId";	
		
		Query query = session.createQuery(resultQuery)
				.setParameter("builderId", builderId);
		userDetailsList = query.setTupleTransformer(Transformers.aliasToBean(User.class)).list();  
		
		tx.commit();
		session.close();
		return userDetailsList;
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserDetailsByCompanyId(int companyId) {
		Session session = null;  
		Transaction tx  = null;
		List<User> userDetailsList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery =  " select u.userId as userId, u.userName as userName, u.userEmail as userEmail, u.userMobile as userMobile, u.userPassword as userPassword, "
					  +" u.userStatus as userStatus, u.userType as userType, u.companyId as companyId, c.logoPath as logoPath from User as u, CompanyProfile as c "
					  +" where u.isRemove = 0 and u.companyId =:companyId and u.companyId=c.companyId order by u.userType";	
		
		Query query = session.createQuery(resultQuery)
				.setParameter("companyId", companyId);
		userDetailsList = query.setTupleTransformer(Transformers.aliasToBean(User.class)).list();  
		
		tx.commit();
		session.close();
		return userDetailsList;
	}

/*	@Override
	public void updateAllSubusersStatus(int builderId, int userStatus) {

		Session session = null;  
		Transaction tx  = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdateUser = "update User set userStatus = :userStatus, updatedDatetime = :updatedDatetime "+
										  "where builderId = :builderId";	
				
				session.createQuery(hqlUpdateUser)
				   .setParameter("userStatus", userStatus)
				   .setParameter("updatedDatetime", DateTimeUtil.getSysDateTime())
				   .setParameter("builderId", builderId)
				   .executeUpdate();
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
		      session.close();
		}
		
	}*/

/*	@Override
	public String updateUserProfileDetailsByUserId(User user) {
		Session session = null;  
		Transaction tx = null;
		//boolean isRecordUpdated = false;
		String documentName   = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
				if(user.getLogoAttachment() != null){
					
				    MultipartFile file = user.getLogoAttachment();	
				    
					if(file.getOriginalFilename() != null && file.getOriginalFilename() != ""){
						
						Date date     = new Date();								 	 
					 	documentName  = date.getTime()+"_"+file.getOriginalFilename();
					 	
					 	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
			                        new File(ConstantsUtil.FILE_SAVE_LOCATION, documentName)));
	
					 	outputStream.write(file.getBytes());
					 	outputStream.flush();
					 	outputStream.close();
					}
				}else if(user.getLogoPath() != null){
					
					documentName=user.getLogoPath();					
					
				}
			
				
				String hqlUpdate = "update User set userName = :userName, company = :company, website = :website,userMobile = :userMobile,"
						+ "landline = :landline, userEmail = :userEmail, address = :address, logo_path = :logo_path, isUpdated = :isUpdated,"
						+ "updatedDatetime = :updatedDatetime where userId = :userId";
				
				session.createQuery(hqlUpdate)
						.setParameter("userName", user.getUserName())
						.setParameter("companyId", user.getCompanyId())
						.setParameter("website", user.getWebsite())
						.setParameter("userMobile", user.getUserMobile())
						.setParameter("landline", user.getLandline())
						.setParameter("userEmail", user.getUserEmail())
						.setParameter("address", user.getAddress())
						.setParameter("logo_path", documentName)
						.setParameter("isUpdated", 1)
						.setParameter("updatedDatetime", user.getUpdatedDatetime())
						.setParameter("userId", user.getUserId())
						.executeUpdate();
				
			
			tx.commit();
			//isRecordUpdated = true;
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		    }	
		}finally {
		      session.close();
		}		
		return documentName;
	}*/
	
	public int getRecentUserId() {
		
		Session session = null;  
		Transaction tx  = null;	
		int userId      = 0;
		try{
			
			session = sessionFactory.openSession();  
			tx      = session.beginTransaction(); 
			
			  List<Integer> maxIdList = session.createQuery(
					  "select u.userId from User u order by u.userId desc", Integer.class)
					  .setMaxResults(1)
					  .list();
		    	 if(!maxIdList.isEmpty()) userId = maxIdList.get(0);
		    	 
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return userId;
	}

/*	@Override
	public void updateAllSubusersDetailsByBuilderId(int builderId,User user) {
		Session session = null;  
		Transaction tx  = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdateUser = "update User set company = :company, website = :website, landline = :landline, address = :address, "+
										  "logo_path = :logo_path, updatedDatetime = :updatedDatetime where builderId = :builderId";	
				
				session.createQuery(hqlUpdateUser)
					.setParameter("company", user.getCompanyId())
					.setParameter("website", user.getWebsite())
					.setParameter("landline", user.getLandline())
					.setParameter("address", user.getAddress())
					.setParameter("logo_path", user.getLogoPath())
					.setParameter("updatedDatetime", DateTimeUtil.getSysDateTime())
					.setParameter("builderId", builderId)
					.executeUpdate();
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
		      session.close();
		}
		
	}*/

	@Override
	public boolean updateUserStatusByScheduledTasks(List<String> companyIdsList) {
		Session session        = null;  
		Transaction tx         = null;
		boolean isUpdate       = false;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			//List<String> userIdList = userIdList
			
			String query = "update user set user_status = 0, updated_datetime =? where company_id = ? ";
	        List<Object[]> inputList = new ArrayList<Object[]>();			        
	        
	        for(int i = 0; i < companyIdsList.size(); i++){
	        	
	            Object[] tmp = new Object[]{ DateTimeUtil.getSysDateTime(), companyIdsList.get(i)};
	            inputList.add(tmp);
	        }
	        
		    getJdbcTemplate().batchUpdate(query, inputList);
		    isUpdate       = true;
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return isUpdate;
	}


	@Override
	public void updateAllUsersStatusAfterAddPayment(int companyId) {
		Session session = null;  
		Transaction tx  = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdateUser = "update User set userStatus = :userStatus, updatedDatetime = :updatedDatetime "+
										  "where companyId = :companyId";	
				
				session.createQuery(hqlUpdateUser)
				   .setParameter("userStatus", 1)
				   .setParameter("updatedDatetime", DateTimeUtil.getSysDateTime())
				   .setParameter("companyId", companyId)
				   .executeUpdate();
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
		      session.close();
		}
	}

	@Override
	public boolean updateUserProfileDetailsByUserId(User user) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update User set userName = :userName, userEmail = :userEmail, userMobile = :userMobile, "+ 
								   "updatedDatetime = :updatedDatetime where userId = :userId";
				
				int result = session.createQuery(hqlUpdate)
									.setParameter("userName", user.getUserName())
									.setParameter("userEmail", user.getUserEmail())
									.setParameter("userMobile", user.getUserMobile())
									.setParameter("updatedDatetime", user.getUpdatedDatetime())
									.setParameter("userId", user.getUserId())
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllAdminList()  {
		Session session = null;  
		Transaction tx  = null;
		List<User> userDetailsList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery =  " select userId as userId, userName as userName, tokenName as tokenName, userDeviceId as userDeviceId "				     
					  +" from User"
					  +" where userType = 2";	
		
		Query query = session.createQuery(resultQuery);				
		userDetailsList =  query.setTupleTransformer(Transformers.aliasToBean(User.class)).list();  
		
		tx.commit();
		session.close();
		return userDetailsList;
	}
	
	@Override
	public boolean updateUserTokenAndDeviceId(User user) {
		Session session = null;  
		Transaction tx  = null;
		boolean isUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdateUser = " update User set userDeviceId = :userDeviceId, tokenName = :tokenName "+
									   " where userId = :userId";	
				
				session.createQuery(hqlUpdateUser)
				.setParameter("userId",user.getUserId())
		        .setParameter("userDeviceId",user.getUserDeviceId())
		        .setParameter("tokenName",user.getTokenName())		       
				.executeUpdate();
		      
		       tx.commit();  
		       isUpdated = true;
			}catch(Exception e){	       
		        e.printStackTrace();
		    }finally{
		    	 if(session.isOpen())
		     	 session.close();	     		
		    }
			return isUpdated;
	}		
	
}
