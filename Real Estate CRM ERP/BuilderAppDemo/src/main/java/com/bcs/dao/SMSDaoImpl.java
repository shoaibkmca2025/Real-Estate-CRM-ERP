package com.bcs.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.SendSMS;
import com.bcs.model.CustomEvent;
import com.bcs.model.MobileNumbers;
import com.bcs.model.SMS;
import com.bcs.model.SMSCreditDetails;


@Repository
public class SMSDaoImpl implements SMSDao {

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean sendSms(SMS sms) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		int recordCount = 0;
		try{			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					String[] mobileNoArray = sms.getMobileNos().split(",");
					String response = SendSMS.sendSms(sms.getMobileNos(), sms.getSmsText());
					JSONParser parser = new JSONParser();
					JSONObject json = (JSONObject) parser.parse(response);
					
					System.out.println("response: "+response);
					System.out.println("cost: "+json.get("cost"));
					
					List<Object> obj =  (List<Object>) json.get("warnings");
					for(int i=0 ;i< obj.size(); i++){
						
						JSONObject row = (JSONObject) obj.get(i);
						String mobileNo = row.get("numbers").toString().substring(2, 12);
						
						List list = new ArrayList(Arrays.asList(mobileNoArray));
						list.remove(mobileNo);
						
						mobileNoArray = (String[]) list.toArray(new String[list.size()]);
						
					}
					
					if(json.get("status").equals("success") && mobileNoArray.length > 0){
						
						String sqlUpdate = " UPDATE sms_credit_details scd, (SELECT available_credits, sms_credit_id, total_sent "
										 + " FROM sms_credit_details where company_id = :companyId order by sms_credit_id desc limit 1) temp "
										 + " SET scd.available_credits = temp.available_credits - :totalCost, scd.total_sent = temp.total_sent + :totalCost,"
										 + " updated_datetime = :updatedDatetime "
										 + " WHERE scd.company_id = :companyId and scd.sms_credit_id = temp.sms_credit_id";
						
						session.createNativeQuery(sqlUpdate)
						.setParameter("totalCost", (int) json.get("cost"))
						.setParameter("updatedDatetime", sms.getCreatedDatetime())
						.setParameter("companyId", sms.getCompanyId())
						.executeUpdate();
						
						session.save(sms);
						
						for(int i=0; i<mobileNoArray.length; i++){
							
							MobileNumbers mb = new MobileNumbers();
							mb.setMobileNumber(mobileNoArray[i]);
							mb.setSmsId(sms.getSmsId());
							mb.setSendStatus(1);
							mb.setCreatedDatetime(sms.getCreatedDatetime());							
							session.save(mb);
							
							recordCount++;
							if(recordCount % 20 == 0 ){
								
								session.flush();
								session.clear();
							}
						}
						
						tx.commit();
						isRecordAdded = true;
					}	
					
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
	public List<SMS> getAllSMS(int companyId, String date) {
		
		Session session = null;  
		Transaction tx  = null;
		List<SMS> smsList = null;
		try{
		
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				 String resultQuery =  	" select s.sms_text as smsText, s.sms_send_time as smsSendTime, mb.mobile_number as mobileNos "
						 			  + " from sms s, mobilenumbers mb where s.sms_id = mb.sms_id and s.company_id = :companyId "
						 			  + " and mb.send_status = 1 and s.sms_send_time like :date order by s.sms_id desc";				  	 
		
				Query query = session.createNativeQuery(resultQuery).setParameter("companyId", companyId).setParameter("date", date+"%");
				smsList 	= query.setTupleTransformer(Transformers.aliasToBean(SMS.class)).list();  
				
				tx.commit();
				
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }
	        
		}finally{
			
			session.close();
		}
		return smsList;
	}

	@Override
	public String getAllMobileNosByType(int userId, int type, int projectId) {
		Session session = null;  
		Transaction tx  = null;
		String mobileNos = null;
		try{
		
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				 String resultQuery =  	  " select GROUP_CONCAT(ed.mobile_no) from enquiry_details ed, project_details pd  "
						 				+ " where ed.project_id = pd.project_tran_id and pd.is_remove = 0 and pd.is_approved = 1 "
						 				+ " and ed.user_id = :userId and ed.is_remove = 0"
						 				+ " and case when :type != 0 then ed.followup_status = :type else true end "
						 				+ " and case when :projectId != 0 then ed.project_id = :projectId else true end ";
						 			   	 
		
				Query query = session.createNativeQuery(resultQuery).setParameter("userId", userId)
									 .setParameter("type", type).setParameter("projectId", projectId);
				
				mobileNos 	= (String) query.uniqueResult();  
				
				tx.commit();
				
		}catch(Exception ex){			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }
	        
		}finally{
			
			session.close();
		}
		return mobileNos;
	}

	@Override
	public boolean scheduleSms(SMS sms) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		int recordCount = 0;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
						String sqlUpdate = " UPDATE sms_credit_details scd, (SELECT available_credits, sms_credit_id, total_sent "
										 + " FROM sms_credit_details where company_id = :companyId order by sms_credit_id desc limit 1) temp "
										 + " SET scd.available_credits = temp.available_credits - :totalCost, scd.total_sent = temp.total_sent + :totalCost,"
										 + " updated_datetime = :updatedDatetime "
										 + " WHERE scd.company_id = :companyId and scd.sms_credit_id = temp.sms_credit_id";
						
						session.createNativeQuery(sqlUpdate)
						.setParameter("totalCost", sms.getMessageCost())
						.setParameter("updatedDatetime", sms.getCreatedDatetime())
						.setParameter("companyId", sms.getCompanyId())
						.executeUpdate();
				
						session.save(sms);
						
						CustomEvent ce = new CustomEvent();
						
						ce.setEventDate(sms.getEventDate());
						ce.setEventName(sms.getEventName());
						ce.setSmsId(sms.getSmsId());
						session.save(ce);
						
						String[] mobileNoList = sms.getMobileNos().split(",");
						
						for(int i=0; i<mobileNoList.length; i++){
							
							MobileNumbers mb = new MobileNumbers();
							mb.setMobileNumber(mobileNoList[i]);
							mb.setSmsId(sms.getSmsId());
							mb.setCreatedDatetime(sms.getCreatedDatetime());	
							mb.setCustomEventId(ce.getCustomEventId());
							session.save(mb);
							
							recordCount++;
							if(recordCount % 20 == 0 ){
								
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SMS> getAllScheduleSMSDetails() {
		Session session = null;  
		Transaction tx  = null;
		List<SMS> list = null;
		try{
		
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				String resultQuery =  	  " select s.sms_id as smsId, s.company_id as companyId, s.sms_text as smsText, "
				 						+ " GROUP_CONCAT(mn.mobile_number) as mobileNos from mobilenumbers mn, custom_events ce, sms s  "
						 				+ " where ce.custom_event_id = mn.custom_event_id and mn.sms_id = s.sms_id "
						 				+ " and mn.send_status = 0 and STR_TO_DATE(ce.event_date , '%d/%m/%Y') = curdate() "
						 				+ " group by mn.sms_id";
						 			   	 
		
				Query query = session.createNativeQuery(resultQuery);
				
				list 	= query.setTupleTransformer(Transformers.aliasToBean(SMS.class)).list(); 
				
				tx.commit();
				
		}catch(Exception ex){			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }
	        
		}finally{
			
			session.close();
		}
		return list;
	}

	@Override
	public boolean updateSendTimeAndSendStatusByScheduler(SMS sms, int failedMessageCost) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			String[] mobileNoArray = sms.getMobileNos().split(",");
			
			if(mobileNoArray.length > 0){
				
				String hqlUpdate =  " update SMS set smsSendTime = :smsSendTime, "+ 
						   			" updatedDatetime = :updatedDatetime where smsId = :smsId";
		
				session.createQuery(hqlUpdate)
						.setParameter("smsSendTime", sms.getUpdatedDatetime())
						.setParameter("updatedDatetime", sms.getUpdatedDatetime())
						.setParameter("smsId", sms.getSmsId())
						.executeUpdate();
				System.out.println(sms.getMobileNos()+" *** "+sms.getSmsId());
				String hqlUpdate1 = " update mobilenumbers set send_status = :sendStatus "+ 
						   			" where sms_id = :smsId and FIND_IN_SET(mobile_number, :mobileNos)";
		
				session.createNativeQuery(hqlUpdate1)
						.setParameter("sendStatus", 1)
						.setParameter("mobileNos", sms.getMobileNos())
						.setParameter("smsId", sms.getSmsId())
						.executeUpdate();
				
				String sqlUpdate = " UPDATE sms_credit_details scd, (SELECT available_credits, sms_credit_id, total_sent "
								 + " FROM sms_credit_details where company_id = :companyId order by sms_credit_id desc limit 1) temp "
								 + " SET scd.available_credits = temp.available_credits + :failedMessageCost, scd.updated_datetime =:updatedDatetime, "
								 + " scd.total_sent = case when temp.total_sent != 0 then temp.total_sent - :failedMessageCost else scd.total_sent end "
								 + " WHERE scd.company_id = :companyId and scd.sms_credit_id = temp.sms_credit_id ";

				session.createNativeQuery(sqlUpdate)
				.setParameter("failedMessageCost", failedMessageCost)
				.setParameter("updatedDatetime", sms.getUpdatedDatetime())
				.setParameter("companyId", sms.getCompanyId())
				.executeUpdate();
				 
				tx.commit();
				isRecordUpdated = true;
			}
					
				
			
			
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
	public List<SMS> getAllScheduledSMSListByCompanyId(int companyId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<SMS> list = null;
		try{
		
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				 String resultQuery =  	  " select s.sms_id as smsId, s.sms_text as smsText, GROUP_CONCAT(mn.mobile_number) as mobileNos,"
				 						+ " ce.event_name as eventName, ce.event_date as eventDate "
				 						+ " from mobilenumbers mn, custom_events ce, sms s  "
						 				+ " where ce.custom_event_id = mn.custom_event_id and mn.sms_id = s.sms_id "
						 				+ " and mn.send_status = 0 and STR_TO_DATE(ce.event_date , '%d/%m/%Y') > curdate() "
						 				+ " and s.company_id = :companyId"
						 				+ " group by mn.sms_id";
						 			   	 
		
				Query query = session.createNativeQuery(resultQuery).setParameter("companyId", companyId);
				
				list 	= query.setTupleTransformer(Transformers.aliasToBean(SMS.class)).list(); 
				
				tx.commit();
				
		}catch(Exception ex){			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }
	        
		}finally{
			
			session.close();
		}
		return list;
	}

	@Override
	public boolean addSmsCreditDetails(SMSCreditDetails smsCreditDetails) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					session.save(smsCreditDetails);
					
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
	public List<SMSCreditDetails> getAllSmsCreditDetailsByCompanyId(int companyId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<SMSCreditDetails> smsCountList = null;
		try{
		
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				 String resultQuery =  	" select scd.smsCreditId as smsCreditId, scd.totalCredits as totalCredits, "
				 					  + " scd.totalSent as totalSent, scd.availableCredits as availableCredits, "
				 					  + " scd.insertionDate as insertionDate from SMSCreditDetails scd "
						 			  + " where scd.companyId = :companyId ";				  	 
		
				Query query = session.createQuery(resultQuery).setParameter("companyId", companyId);
				smsCountList 	= query.setTupleTransformer(Transformers.aliasToBean(SMSCreditDetails.class)).list();  
				
				tx.commit();
				
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }
	        
		}finally{
			
			session.close();
		}
		return smsCountList;
	}

	@Override
	public SMSCreditDetails getAvailableSMSCreditsByCompanyId(int companyId) {
		
		Session session = null;  
		Transaction tx  = null;
		SMSCreditDetails availableCreditDetails = null;
		try{
		
				session = sessionFactory.openSession();
				tx      = session.beginTransaction();
				
				 String resultQuery =  	" select scd.smsCreditId as smsCreditId, scd.availableCredits as availableCredits "
				 					  + " from SMSCreditDetails scd "
						 			  + " where scd.companyId = :companyId order by scd.smsCreditId desc ";				  	 
		
				Query query = session.createQuery(resultQuery).setParameter("companyId", companyId)
									 .setTupleTransformer(Transformers.aliasToBean(SMSCreditDetails.class)).setMaxResults(1);
				availableCreditDetails 	= (SMSCreditDetails) query.uniqueResult();
				
				tx.commit();
				
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }
	        
		}finally{
			
			session.close();
		}
		return availableCreditDetails;
	}

}
