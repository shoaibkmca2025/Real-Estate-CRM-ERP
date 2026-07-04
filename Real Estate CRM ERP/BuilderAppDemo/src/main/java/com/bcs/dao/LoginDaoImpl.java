package com.bcs.dao;


import java.util.List;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.LoginDetails;
import com.bcs.model.User;
import com.bcs.utility.DateTimeUtil;

/**
 * Hibernate 6 note: the legacy {@code org.hibernate.Criteria} / {@code criterion}
 * API was removed, so every former Criteria query here was rewritten as the
 * equivalent HQL. Behaviour (filters, projections, results) is unchanged.
 */
@Repository
public class LoginDaoImpl implements LoginDao{

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }

   //To check mobile exists or not
    @Override
	public boolean checkEmail(String userEmail){
   	Session session = null;
   	Transaction tx  = null;
	    boolean result=false;
	    try {
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Long count = session.createQuery(
					"select count(u.userId) from User u where u.userEmail = :userEmail", Long.class)
					.setParameter("userEmail", userEmail)
					.uniqueResult();

	        if(count != null && count > 0) result = true;
	        tx.commit();
	    }catch(Exception e){
	        e.printStackTrace();
	    }finally{
	    	session.close();
	    }
	    return result;
	}

  //To check mobile exists or not
  		@Override
  		public boolean checkMobile(String userMobile) {
  			Session session = null;
  			Transaction tx = null;
  			boolean result = false;
  		    try {
  		    	session = sessionFactory.openSession();
  				tx = session.beginTransaction();

  				Long count = session.createQuery(
  						"select count(u.userId) from User u where u.userMobile = :userMobile", Long.class)
  						.setParameter("userMobile", userMobile)
  						.uniqueResult();
  		        tx.commit();

  		        if(count != null && count > 0) result = true;
  		    }catch(Exception e){
  		        e.printStackTrace();
  		    }finally {
  		    	session.close();
  		    }
  		    return result;
  		}

  		@Override
		public int checkEmailOrMobile(String inputstr){

	   	 int result = 0;
	   	 	if(Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(inputstr).matches()){
	       	 result = 2;

	        } else if(inputstr.chars().allMatch(Character::isDigit) && inputstr.trim().length() == 10){

	        	 result = 1;
	        }
	        return result;
	   }

		@Override
		public String getPasswordByEmailOrMobile(String userName) {
			Session session = null;
			Transaction tx = null;
			String userpassword = null;
			try{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				userpassword = session.createQuery(
						"select u.userPassword from User u where u.userEmail = :userName", String.class)
						.setParameter("userName", userName)
						.uniqueResult();
				tx.commit();
			 }catch(Exception e){
			      e.printStackTrace();
			 }finally {
			      session.close();
			 }
			return userpassword;
		}

		@Override
		public int getUserIdByEmailAndPassword(String username, String password) throws Exception {
			 Session session = null;
			 Transaction tx = null;
			 int userId = 0;
			    try{
			    	 session = sessionFactory.openSession();
					  tx = session.beginTransaction();

					  Integer id = session.createQuery(
							  "select u.userId from User u where u.userEmail = :username and u.userPassword = :password", Integer.class)
							  .setParameter("username", username)
							  .setParameter("password", password)
							  .uniqueResult();
					  if(id != null) userId = id;
					  tx.commit();
				}catch(Exception e){
				      e.printStackTrace();
				}finally {
				      session.close();
				}
			  return userId;
		}

		@Override
		public User getUserById(int userId) throws Exception {
			Session session = null;
			Transaction tx = null;
			User user = null;
			try{
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();

					String resultQuery =  " select u.userId as userId, u.userName as userName, u.userEmail as userEmail, u.userMobile as userMobile, u.userPassword as userPassword, "
										+ " u.userType as userType, u.userStatus as userStatus , u.companyId as companyId "
										+ " from User as u where u.isRemove = 0 and u.userId = :userId";
					Query<?> query = session.createQuery(resultQuery).setParameter("userId", userId);

					user = (User) query.setTupleTransformer(Transformers.aliasToBean(User.class)).uniqueResult();

				tx.commit();
			}catch(Exception e){
			      e.printStackTrace();
			}finally {
			      session.close();
			}
			return user;
		}

		@Override
		public String getPasswordByUserId(int userId) {
			Session session = null;
			Transaction tx = null;
			String userpassword = null;
			try{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				  userpassword = session.createQuery(
						  "select u.userPassword from User u where u.userId = :userId", String.class)
						  .setParameter("userId", userId)
						  .uniqueResult();
				  tx.commit();
			 }catch(Exception e){
			      e.printStackTrace();
			 }finally {
			      session.close();
			 }
			return userpassword;
		}

		@Override
		public boolean changeUserPassword(User user) {
			Session session = null;
			Transaction tx = null;
			boolean isRecordUpdated = false;
			try{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

					String hqlUpdate = "update User set userPassword = :userPassword, updatedDatetime = :updatedDatetime "+
										"where userId = :userId";

					session.createMutationQuery(hqlUpdate)
							.setParameter("userPassword", user.getNewPassword())
							.setParameter("updatedDatetime",DateTimeUtil.getSysDateTime() )
							.setParameter("userId", user.getUserId())
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
		public void addLoginDetails(LoginDetails loginDetails) {
			Session session = null;
			Transaction tx = null;
			try{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();

				session.persist(loginDetails);

				}catch(Exception ex){
					ex.printStackTrace();
					 if(tx!= null){
						  tx.rollback();
				    }
				}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		}


		@Override
		public List<LoginDetails> getLoginReportsBySelectedDate(String createdDateTime) {
			Session session = null;
			Transaction tx  = null;
			List<LoginDetails> loginDetailsList=null;

			try{
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();

				String resultQuery = " select ld.loginDetailsId as loginDetailsId, ld.loginId as loginId, u.userName as loggedInUserName, u.userEmail as loggedInUserEmail, ld.loginDateTime as loginDateTime, "
						+ " ld.userLocation as userLocation, ld.userDeviceName as userDeviceName, ld.userIpAddress as userIpAddress, "
						+ " ld.createdDateTime as createdDateTime, ld.updatedDateTime as updatedDateTime "
						+ " from LoginDetails as ld, User as u "
						+ " where u.userId = ld.loginId "
						+ " and str(ld.createdDateTime) like :createdDateTime GROUP BY u.userName, u.userEmail ORDER BY ld.loginDetailsId DESC ";

				Query<?> query = session.createQuery(resultQuery)
							  .setParameter("createdDateTime", createdDateTime+"%");

				loginDetailsList = (List<LoginDetails>) query.setTupleTransformer(Transformers.aliasToBean(LoginDetails.class)).list();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
			return loginDetailsList;
		}

			@Override
			public List<LoginDetails> getLoginDataByIdAndDate(int loginId, String createdDateTime) {
				Session session = null;
				Transaction tx  = null;
				List<LoginDetails> loginDetailsList1=null;

				try{
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();

					String resultQuery = " select ld.loginId as loginId, ld.loginDateTime as loginDateTime, "
						+ " ld.userLocation as userLocation, ld.userDeviceName as userDeviceName, ld.userIpAddress as userIpAddress, "
						+ " ld.createdDateTime as createdDateTime, ld.updatedDateTime as updatedDateTime "
						+ " from LoginDetails as ld, User as u "
						+ " where u.userId = ld.loginId and ld.loginId =:loginId "
						+ " and str(ld.createdDateTime) like :createdDateTime ";

				Query<?> query = session.createQuery(resultQuery)
							  .setParameter("loginId", loginId)
							  .setParameter("createdDateTime", createdDateTime+"%");

					loginDetailsList1 = (List<LoginDetails>) query.setTupleTransformer(Transformers.aliasToBean(LoginDetails.class)).list();
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
				return loginDetailsList1;
			}

			@Override
			public int getuserTypeByEmailOrMobile(String userName) {
				Session session = null;
				Transaction tx = null;
				int userType = 0;
				try{
						session = sessionFactory.openSession();
						tx = session.beginTransaction();

						Integer type;
						 if(userName.contains("@")){
							 type = session.createQuery(
									 "select u.userType from User u where u.userEmail = :userName", Integer.class)
									 .setParameter("userName", userName)
									 .uniqueResult();
						  }else{
							 type = session.createQuery(
									 "select u.userType from User u where u.userMobile = :userName", Integer.class)
									 .setParameter("userName", userName)
									 .uniqueResult();
						  }
						 if(type != null) userType = type;
						tx.commit();
				}catch(Exception e){
			           e.printStackTrace();
			    }finally {
			    	   session.close();
			    }
				return userType;
			}

			@Override
			public int getUserIdByEmailOrMobile(String userEmailOrMobile, int flag)throws Exception {
				Session session = null;
				Transaction tx = null;
				int userId = 0;
				try{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();

					  Integer id = null;
					  if(flag == 2) {
						  id = session.createQuery(
								  "select u.userId from User u where u.userEmail = :val", Integer.class)
								  .setParameter("val", userEmailOrMobile).uniqueResult();
					  } else if(flag == 1) {
						  id = session.createQuery(
								  "select u.userId from User u where u.userMobileno = :val", Integer.class)
								  .setParameter("val", userEmailOrMobile).uniqueResult();
					  }
					if(id != null)  {
					  userId = id;
					}
					tx.commit();
				}catch(Exception e){
				      e.printStackTrace();
				}finally {
				      session.close();
				}
			return userId;
		}

			@Override
			public void updateRandomPassword(String userPassword, int userId) throws Exception {
				Session session = null;
				Transaction tx = null;
				try{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();

					String hqlUpdate = "update User set userPassword = :userPassword, updatedDatetime = :updatedDatetime where userId = :userId";
					session.createMutationQuery(hqlUpdate)
							.setParameter("userPassword",userPassword)
							.setParameter("updatedDatetime",DateTimeUtil.getSysDateTime())
							.setParameter("userId", userId)
							.executeUpdate();

					  tx.commit();
				}catch(Exception e){
				      e.printStackTrace();
				}finally {
				      session.close();
				}
			}
}
