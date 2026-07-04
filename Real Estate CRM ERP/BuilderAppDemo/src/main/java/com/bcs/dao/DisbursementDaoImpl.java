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

import com.bcs.model.Client;
import com.bcs.model.ClientDemandLetterDetails;
import com.bcs.model.Disbursement;
import com.bcs.model.DisbursementPartialPayments;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.ProjectDisbursement;
import com.bcs.utility.DateTimeUtil;

@SuppressWarnings("unchecked")
@Repository
public class DisbursementDaoImpl extends JdbcDaoSupport implements DisbursementDao{

	 private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }
	 
	 @Autowired
     public void setDs(DataSource dataSource) {
         setDataSource(dataSource);
     }
	 
	@Override
	public List<ProjectDisbursement> getProjectDisbursementListByProjectId(int projectId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<ProjectDisbursement> disbursementDetailsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = " select d.projectDisbursementId as projectDisbursementId, d.disbursementTitle as disbursementTitle, d.disbursementDescription as disbursementDescription, "+						
						 " d.disbursementPercentage as disbursementPercentage, d.completionDate as completionDate from ProjectDisbursement as d, ProjectDetails as p where d.projectId = p.projectTranId and p.projectTranId = :projectId";
			
			Query query     = session.createQuery(hql).setParameter("projectId", projectId);
			disbursementDetailsList = query.setTupleTransformer(Transformers.aliasToBean(ProjectDisbursement.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		
		return disbursementDetailsList;
	}


	@Override
	public boolean updateProjectDisbursementDetails(ProjectDetails project) {
	
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();	
			
			int projectTranId = project.getProjectTranId();
			
			String hql = "Delete ProjectDisbursement where projectId = :projectId";				
			session.createQuery(hql).setParameter("projectId", projectTranId)
			.executeUpdate();
			
			List<ProjectDisbursement> disbursementList =  project.getDisbursementList();
			
			for(int i=0; i < disbursementList.size(); i++){
				
				ProjectDisbursement disbursement  = disbursementList.get(i);
				
				disbursement.setCreatedDatetime(project.getCreatedDatetime());
				disbursement.setUpdatedDatetime(project.getUpdatedDatetime());
				disbursement.setProjectId(projectTranId);
				disbursement.setUserId(project.getUserId());
				session.save(disbursement);
			}
				
			tx.commit();
			isRecordUpdated = true;
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
	
	@Override
	public boolean payDisbursementAmountByDisbursementId(Disbursement disbursement) {
		
		Session session        = null;  
		Transaction tx         = null;
		boolean isRecordUpdated = false;
		try{
			
			session = sessionFactory.openSession();  
			tx      = session.beginTransaction();  
			
				String dateTime = DateTimeUtil.getSysDateTime();			
				int paymentType = disbursement.getPaymentType();
				
				/*if(paymentType == 1){
					
						String query = "update disbursement set paid_date = ?, disbursement_description = ?, paid_amount = ?, remaining_amount = ?, user_id = ?, is_view_pdf = ?, updated_datetime = ?  where disbursement_id = ? ";
				        List<Object[]> inputList = new ArrayList<Object[]>();			        
				        int isViewPdf = 0;
				        double paidAmount = 0.0;
				       
				        for(int i = 0; i < disbursementIdlist.size(); i++){		
				        	
				        	if(disbursement.getDisbursementId() == Integer.parseInt(disbursementIdlist.get(i))){		        		
				        		isViewPdf = 1;
				        		paidAmount = disbursement.getPaidAmount();
				        		
				        	}else{
				        		isViewPdf  = 0;
				        		paidAmount = 0.0;
				        	}		        	
				            Object[] tmp = new Object[]{disbursement.getPaidDate(),disbursement.getDisbursementDescription(), paidAmount ,  0.0 ,disbursement.getUserId(),isViewPdf, dateTime, Integer.parseInt(disbursementIdlist.get(i))};
				            inputList.add(tmp);
				        }
					    getJdbcTemplate().batchUpdate(query, inputList);
				}*/
				
				if(paymentType == 2 || disbursement.getPrevPaidAmount() > 0){
					
					DisbursementPartialPayments partialPayments = new DisbursementPartialPayments();
					
					partialPayments.setPartialPaymentDescription(disbursement.getDisbursementDescription());
					partialPayments.setPartialPaymentPaidDate(disbursement.getPaidDate());
					
					double totalPartialAmount = disbursement.getTotalPartialAmount();					
					double partialGstAmount   = (disbursement.getGstPercentage()/100)*totalPartialAmount;
					double partialGrossAmount = totalPartialAmount-partialGstAmount;
					
					partialPayments.setPartialGrossAmount(partialGrossAmount);
					partialPayments.setPartialGstAmount(partialGstAmount);
					partialPayments.setTotalPartialAmount(disbursement.getTotalPartialAmount());					
					partialPayments.setDisbursementId(disbursement.getDisbursementId());
					partialPayments.setCreatedDatetime(dateTime);
					partialPayments.setUpdatedDatetime(dateTime);
					
					session.save(partialPayments);
					
				}	
				
				String hql 	= " update Disbursement set remainingAmount = :remainingAmount, paidAmount = :paidAmount, paidDate = :paidDate,";
				
					if(paymentType == 2 && disbursement.getRemainingAmount() > 0){						
					   hql +=  "disbursementFollowupDate = :disbursementFollowupDate, ";						
					}
				  	   hql +=  " disbursementDescription = :disbursementDescription , updatedDatetime = :updatedDatetime, "
				  				+ " userId = :userId  where disbursementId = :disbursementId";
			
				Query query =   session.createQuery(hql)
					       .setParameter("remainingAmount", disbursement.getRemainingAmount())
					       .setParameter("paidAmount", disbursement.getPaidAmount())
					       .setParameter("paidDate", disbursement.getPaidDate())
					       .setParameter("disbursementDescription", disbursement.getDisbursementDescription())
					       .setParameter("updatedDatetime", dateTime)
					       .setParameter("userId",disbursement.getUserId())
					       .setParameter("disbursementId",disbursement.getDisbursementId());
		    
			    if(paymentType == 2 && disbursement.getRemainingAmount() > 0){ 
			    		query.setParameter("disbursementFollowupDate", disbursement.getDisbursementFollowupDate());
			    }
			    
		        query.executeUpdate();
			    isRecordUpdated = true;
			    
			    Query query1 =   session.createQuery("select d.paidDate from Disbursement as d where d.clientId = :clientId order by d.disbursementId DESC")
			    				    	.setParameter("clientId",disbursement.getClientId()).setMaxResults(1);
				
			    String paidDate = (String) query1.uniqueResult();
			    
			    if(paidDate != null){			    	
			    
				    	String hql1  = "update Client set isPaid = :isPaid, updatedDatetime = :updatedDatetime where clientId = :clientId";
				
			    		session.createQuery(hql1)
					    	   .setParameter("clientId",disbursement.getClientId())
					    	   .setParameter("isPaid",1)
					    	   .setParameter("updatedDatetime", dateTime).executeUpdate();
			    }
			    
			
			/*String dateTime = DateTimeUtil.getSysDateTime();
			String hql      = " update Disbursement set paidDate = :paidDate, disbursementDescription = :disbursementDescription, "+
							  " updatedDatetime = :updatedDatetime where disbursementId = :disbursementId";
			
		       session.createQuery(hql)
		       
		       .setParameter("disbursementId",disbursement.getDisbursementId())
		       .setParameter("paidDate", disbursement.getPaidDate())
		       .setParameter("disbursementDescription", disbursement.getDisbursementDescription())
		       .setParameter("updatedDatetime", dateTime)
			   .executeUpdate();*/
			    
			    tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
			if(tx!= null){
				 tx.rollback();
		    }	
		}finally{
			session.close();
		}
		return isRecordUpdated;
	}

	@Override
	public boolean updateCompletionDateByProjectDisbursementId(ProjectDisbursement projectDisbursement) {
		
		Session session        = null;  
		Transaction tx         = null;
		boolean isRecordUpdated = false;
		
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			String dateTime 	=  DateTimeUtil.getSysDateTime();			
			String hqlUpdate    =  " update ProjectDisbursement set completionDate = :completionDate, userId = :userId, updatedDatetime = :updatedDatetime where projectDisbursementId = :projectDisbursementId";
			
		        session.createQuery(hqlUpdate).setParameter("projectDisbursementId",projectDisbursement.getProjectDisbursementId())
										      .setParameter("completionDate", projectDisbursement.getCompletionDate())
										      .setParameter("userId",projectDisbursement.getUserId())
										      .setParameter("updatedDatetime", dateTime)
											  .executeUpdate();
		        
		       /* Query query  = session.createNativeQuery("CALL getAllClientDetailsByDisbursementId(:projectDisbursementId)")
						 	   .setParameter("projectDisbursementId", projectDisbursementId);
		        
		        disbursementDetailsList = query.setTupleTransformer(Transformers.aliasToBean(EnquiryDetails.class)).list();
		       */
		        
		        tx.commit(); 
		        isRecordUpdated = true;
		}catch(Exception ex){
			 ex.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
	  		session.close();
		}
		return isRecordUpdated;
	}


	@Override
	public List<Client> getPaymentDueDateNotifications(int userId, int userType, int paymentNotifDuration, int companyId, int projectId) {
		
		Session session        = null;  
		Transaction tx         = null;
		List<Client> clientList = null;
		try{
			
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
			
		        Query query  = session.createNativeQuery("CALL proc_getPaymentDueDateNotifications(:userId,:userType,:paymentNotifDuration,:companyId,:projectId)")
		        			          .setParameter("userId", userId)
		        			          .setParameter("userType", userType)
		        			          .setParameter("paymentNotifDuration", paymentNotifDuration)
		        			          .setParameter("companyId", companyId)
		                              .setParameter("projectId", projectId);
		        
		        clientList = query.setTupleTransformer(Transformers.aliasToBean(Client.class)).list();
		        
		        tx.commit();     
		}catch(Exception ex){
			 ex.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
	  		session.close();
		}
		
		return clientList;
	}


	@Override
	public ClientDemandLetterDetails getClientDisbursementDetailsByClientId(int clientId, int disbursementId) {
		
		Session session = null;  
		Transaction tx = null;
		ClientDemandLetterDetails clientDetails= null;
		
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
				
									
				Query query = session.createNativeQuery("CALL proc_getClientDisbursementDetailsByClientId(:clientId,:disbursementId)")
							 .setParameter("clientId", clientId).setParameter("disbursementId", disbursementId)
							 .setTupleTransformer(Transformers.aliasToBean(ClientDemandLetterDetails.class));
				
				clientDetails = (ClientDemandLetterDetails) query.uniqueResult();
				
				
			tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		    }	
		}finally {
		      session.close();
		}	
		return clientDetails;
	}


	@Override
	public boolean updatePdfSendDate(Disbursement disbursement) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			
			 session = sessionFactory.openSession();
			 tx = session.beginTransaction();
			 
			 String dateTime = DateTimeUtil.getSysDateTime();
			 
			 List<String> disbursementIdList = disbursement.getDisbursementIdList();
			
		     double prevRemAmount          = 0.0;
		     
		     if(disbursementIdList.size() > 1){
		    	 
		    	 String sqlQuery = " select SUM(d.remainingAmount) from Disbursement d, ProjectDisbursement pd"
		    					 + " where pd.projectDisbursementId = d.projectDisbursementId "
		    					 + " and pd.completionDate IS NOT NULL "
		    					 + " and d.disbursementId != :disbursementId and d.clientId = :clientId";
		    	 
		    	   Query query =  session.createQuery(sqlQuery).setParameter("disbursementId", disbursement.getDisbursementId())
		    			 		.setParameter("clientId", disbursement.getClientId());
		    
		    	    if(query.uniqueResult() != null){
				    	prevRemAmount =  (double) query.uniqueResult();				    	
				    }else{
				    	prevRemAmount = 0.0;
				    }
		    	    
		    	    String sqlQuery1 = "update disbursement set remaining_amount = ?, updated_datetime = ?  where disbursement_id = ? ";
			        List<Object[]> inputList = new ArrayList<Object[]>();	
			        
		    	    for(int i = 0; i < disbursementIdList.size(); i++){
			        	
			        	if(disbursement.getDisbursementId() != Integer.parseInt(disbursementIdList.get(i))){
			        		
			        		Object[] tmp = new Object[]{0.0, dateTime, Integer.parseInt(disbursementIdList.get(i))};
			        		inputList.add(tmp);
			        	}
			         }
				     getJdbcTemplate().batchUpdate(sqlQuery1, inputList);
		     }
		     
		     	double totalRemainingAmount = prevRemAmount + disbursement.getTotalAmount();
		  
			 	String hql  = " update Disbursement set prevRemainingAmount = :prevRemainingAmount, totalAmount = :totalAmount, remainingAmount = :remainingAmount, "
			 				+ " sendPdfDate = :sendPdfDate, demandLetterPdfPath = :demandLetterPdfPath, userId = :userId ,  updatedDatetime = :updatedDatetime "
			 				+ " where disbursementId = :disbursementId";
			
		        session.createQuery(hql)
		        	   .setParameter("prevRemainingAmount", prevRemAmount)
				       .setParameter("totalAmount", totalRemainingAmount)
				       .setParameter("remainingAmount", totalRemainingAmount)
				       .setParameter("sendPdfDate", disbursement.getSendPdfDate())
				       .setParameter("demandLetterPdfPath", disbursement.getDemandLetterPdfPath())
				       .setParameter("totalAmount", prevRemAmount + disbursement.getTotalAmount())
				       .setParameter("updatedDatetime", dateTime)
				       .setParameter("userId",disbursement.getUserId())
				       .setParameter("disbursementId",disbursement.getDisbursementId())
					   .executeUpdate();
		   		 
		       isRecordUpdated = true;
		       
		     tx.commit(); 
		    
		}catch(Exception ex){
			
			 if(tx!= null){
				  tx.rollback();
		     }	
			 ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		return isRecordUpdated;
	}

	@Override
	public List<Client> getPendingDemandLetterNotifications(int userId, int userType, int companyId, int projectId) {
		
		Session session        = null;  
		Transaction tx         = null;
		List<Client> clientList = null;
		try{
			
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
				
				/*String sql   =   " select cl.client_tran_id as clientTranId, ed.first_name as firstName, ed.last_name as lastName, ed.mobile_no as mobileNo, "
								+" p.project_name as projectName, p.project_tran_id as projectId, w.wing_name as wingName,cl.wing_id as wingId, "
								+" cl.floor_number as floorNumber, cl.flat_number as flatNumber "
								+" from project_disbursement pd, disbursement d, client cl, enquiry_details ed, Wing w, project_details p "
								+" where pd.project_disbursement_id = d.project_disbursement_id "
								+" and d.client_id      = cl.client_tran_id "
								+" and p.project_tran_id = cl.project_id "
								+" and w.wing_tran_id    = cl.wing_id "
								+" and ed.enquiry_id     = cl.enquiry_id "
								+" and pd.completion_date Is NOT NULL "
								+" and cl.is_paid = 0 "
								+" and d.send_pdf_date IS NULL "
								+" and (Case When :userType = 2 Then p.company_id = :companyId  Else p.subuser_id IN (:userId)  End) "
								+" group by cl.client_tran_id ";
			*/
				


		        Query query  =  session.createNativeQuery("CALL proc_getPendingDemandLetterNotifications(:companyId,:userId,:userType,:projectId)")
		        					.setParameter("companyId", companyId)
		        					.setParameter("userId", userId)
		        					.setParameter("userType", userType)
		        					.setParameter("projectId", projectId);
		        
		        clientList = query.setTupleTransformer(Transformers.aliasToBean(Client.class)).list();
		        
		        tx.commit();     
		}catch(Exception ex){
			 ex.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
	  		session.close();
		}
		
		return clientList;
	}
	

	/* QUERY : getDisbursementDetailsListByClientId 
	 * String hql    =  " select d.disbursementId as disbursementId, d.clientId as clientId, d.propertyTypeId as propertyTypeId, pd.disbursementTitle as disbursementTitle,"
		+ " d.disbursementDescription as disbursementDescription, d.percentageValue as percentageValue, d.disbursementAmount as disbursementAmount,  "
		+ " d.paidDate as paidDate, pd.completionDate as completionDate, d.gstAmount as gstAmount, d.sendPdfDate as sendPdfDate, "
		+ " d.demandLetterPdfPath as demandLetterPdfPath, d.totalAmount as totalAmount, d.isViewPdf as isViewPdf, d.disbursementFollowupDate "
		+ " as disbursementFollowupDate, d.remainingAmount as remainingAmount "
		+ " from Disbursement d, ProjectDisbursement as pd, Client as cl "
		+ " where pd.projectDisbursementId = d.projectDisbursementId and d.clientId = cl.clientTranId and cl.clientTranId =:clientId ";
*/

	@Override
	public List<Disbursement> getDisbursementDetailsListByClientId(int clientTranId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<Disbursement> disbursementDetailsList = null;
		try{
			
			  session = sessionFactory.openSession();
			  tx      = session.beginTransaction();
			
			  Query query = session.createNativeQuery("CALL proc_getDisbursmentListByClientId(:clientTranId)")
					 			   .setParameter("clientTranId", clientTranId);
			
			  disbursementDetailsList = query.setTupleTransformer(Transformers.aliasToBean(Disbursement.class)).list(); 
			 
			  Disbursement prevDisbursement = null ;
			  List<DisbursementPartialPayments> partialPaymentsList = new ArrayList<DisbursementPartialPayments>();
			  int k = 0;
			  
			  int length = disbursementDetailsList.size();
			  
			  for (int i=0, j=0; i < disbursementDetailsList.size() ; i++,j++){
				
				  if(disbursementDetailsList.get(i).getDisbursementTitle() != ""){
					  
					  if( partialPaymentsList.size() > 0){
						  
						  prevDisbursement.setPartialPaymentsList(partialPaymentsList);
						  partialPaymentsList = new ArrayList<DisbursementPartialPayments>();
						  k =0;
					  }
					  prevDisbursement = disbursementDetailsList.get(i);
					  
				  }else if(disbursementDetailsList.get(i).getDisbursementTitle() == ""){
					  
					  Disbursement disb  = disbursementDetailsList.get(i);
					  int disbursementId = disb.getDisbursementId();
					
					  if(prevDisbursement.getDisbursementId() == disbursementId){
						 
						  DisbursementPartialPayments partialPayment = new DisbursementPartialPayments();
						  
						  partialPayment.setDisbursementId(disbursementId);
						  partialPayment.setPartialPaymentDescription(disb.getDisbursementDescription());
						  partialPayment.setPartialGrossAmount(disb.getPartialGrossAmount());
						  partialPayment.setPartialGstAmount(disb.getPartialGstAmount());
						  partialPayment.setTotalPartialAmount(disb.getPaidAmount());
						  partialPayment.setPartialPaymentPaidDate(disb.getPaidDate());
						  
						  partialPaymentsList.add(k,partialPayment);
						  k++;
						  disbursementDetailsList.remove(i);
						  i--;
					  }
				  }
				 
				  if(prevDisbursement.getDisbursementTitle() != "" && j == length-1 && partialPaymentsList.size() > 0){
					 
					  prevDisbursement.setPartialPaymentsList(partialPaymentsList);
				  }
			  }
			 
			tx.commit();
		}catch(Exception ex){
			 if(tx!= null){
				  tx.rollback();
		      }	
			ex.printStackTrace();
			
		}finally{
			session.close();
		}
		
		return disbursementDetailsList;
	}


	@Override
	public void updateDisbursementFollowupDate(Disbursement disbursement) {
		
		Session session = null;  
		Transaction tx  = null; 
		try{
			session = sessionFactory.openSession(); 
		    tx      = session.beginTransaction(); 
			
	        String hql  =   " update Disbursement set  disbursementFollowupDate = :disbursementFollowupDate "
	        			  + " where disbursementId = :disbursementId";
	        
	        session.createQuery(hql)
	        
	        .setParameter("disbursementId",disbursement.getDisbursementId())
	        .setParameter("disbursementFollowupDate", disbursement.getDisbursementFollowupDate())
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
	public Disbursement getDisbursementFirstFollowupDetails(int clientId) {
	
			Session session = null;  
			Transaction tx  = null; 
			Disbursement  disbFirstFollowupDetails = null;
			try{
				session = sessionFactory.openSession(); 
			    tx      = session.beginTransaction(); 
			    
			    String sqlQuery = " SELECT Sum(sub.remaining_amount) as remainingAmount, sum(sub.percentage_value) as percentageValue, "
			    				+ "	sub.disbursement_title as disbursementTitle , sub.client_id as clientId "
			    				+ "	FROM ("
			    				+ " SELECT d.gst_amount, pd.`disbursement_title`, d.`client_id` , "
			    				+ " d.`paid_date` , d.`remaining_amount`, d.`percentage_value` FROM  `project_disbursement` pd, `disbursement` d "
			    				+ " WHERE pd.`project_disbursement_id` = d.`project_disbursement_id` "
			    				+ " AND pd.`completion_date` IS NOT NULL AND d.`client_id` = :clientId "
			    				+ " ORDER BY pd.`project_disbursement_id` DESC ) as sub "
			    				+ " GROUP BY sub.client_id ";
				
			    Query query = session.createNativeQuery(sqlQuery)
			 			   .setParameter("clientId", clientId);
	
			    disbFirstFollowupDetails = (Disbursement) query.setTupleTransformer(Transformers.aliasToBean(Disbursement.class)).uniqueResult(); 
		       
		       tx.commit();  
			}catch(Exception e){	       
		        e.printStackTrace();
		    }finally{
		    	 if(session.isOpen())
		     	 session.close();	     		
		    }
			return disbFirstFollowupDetails;
		
	}
	
	@Override
	public List<Client> getTodaysReceivedPaymentClientsList(int userId, int userType, int companyId, int projectId) {
		Session session        = null;  
		Transaction tx         = null;
		List<Client> clientList = null;
		try{			
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  		
				
				String sql = "select cl.client_tran_id as clientTranId, p.project_name as projectName, cl.owner_name as ownerName, ed.mobile_no as mobileNo, p.project_tran_id as projectId,"
						+ " cl.flat_number as flatNumber, cl.floor_number as floorNumber, w.wing_name as wingName, d.disbursement_id as disbursementId, d.updated_datetime as updatedDatetime, "
						+ " case when dpp.total_partial_amount IS NULL THEN d.paid_amount else SUM(dpp.total_partial_amount) end as totalPaidAmount "
						+ " from project_details as p, wing as w, client as cl, enquiry_details as ed, disbursement as d "
						+ " LEFT JOIN disbursement_partial_payments as dpp "						
						+ " on d.disbursement_id = dpp.disbursement_id "
						+ " and STR_TO_DATE(dpp.updated_datetime, '%d/%m/%Y') = CURDATE() "
						+ " where p.project_tran_id  = cl.project_id "
						+ " and cl.client_tran_id    = d.client_id "
						+ " and ed.enquiry_id        = cl.enquiry_id "
						+ " and w.wing_tran_id       = cl.wing_id "
						+ " and d.send_pdf_date IS NOT NULL "
						+ " and d.paid_date IS NOT NULL " 
						+ " and STR_TO_DATE(d.updated_datetime, '%d/%m/%Y') = CURDATE() "
						+ " and Case When :projectId != 0 Then p.project_tran_id = :projectId else true end "
						+ " and (Case When :userType = 2 Then p.company_id = :companyId Else FIND_IN_SET(:userId, p.subuser_id )  End) "
						+ " Group By dpp.disbursement_id ";

		        Query query  =  session.createNativeQuery(sql)
		        			          .setParameter("userId", userId)
		        			          .setParameter("userType", userType)
		        			          .setParameter("companyId", companyId)
		        			          .setParameter("projectId", projectId);
		        
		        clientList = query.setTupleTransformer(Transformers.aliasToBean(Client.class)).list();
		        
		        tx.commit();     
		}catch(Exception ex){
			 ex.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
	  		session.close();
		}
		
		return clientList;
	}

	@Override
	public List<PaidDisbursementDetails> getAllPaidDisbursementDetailsByClientId(int clientId) {
		

		Session session = null;  
		Transaction tx  = null;
		List<PaidDisbursementDetails>  paidDisbursementDetails = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();			
			
		String sql =  " SELECT cl.client_tran_id as clientTranId , cl.owner_name as ownerName, p.property_name as propertyName,"
					+ " ed.mobile_no as mobileNo, cl.property_area as propertyArea,"
					+ " case when dpp.total_partial_amount IS NULL THEN d.paid_amount else dpp.total_partial_amount end as paidAmount,"
					+ " case when dpp.total_partial_amount IS NULL THEN d.paid_date else dpp.partial_payment_paid_date end as paidDate"				
					+ "	From client as cl , enquiry_details as ed, property as p, disbursement as d"
					+ " LEFT JOIN disbursement_partial_payments as dpp on dpp.disbursement_id   = d.disbursement_id"
					+ "	WHERE cl.client_tran_id  = d.client_id "
					+ " and ed.enquiry_id        = cl.enquiry_id "
					+ " and d.paid_amount != 0"
					+ " and ed.property          = p.property_id"
					+ " and cl.client_tran_id    = :clientId";
					/*+ " UNION"
					+ " SELECT cl.client_tran_id as clientTranId, cl.owner_name as ownerName, p.property_name as propertyName, ed.mobile_no as mobileNo,"
					+ " cl.property_area as propertyArea, op.amount as amount, op.paid_date as paidDate"
					+ " From client as cl , enquiry_details as ed, property as p, other_payments as op"
					+ " WHERE cl.client_tran_id = op.client_id"						
					+ " and ed.enquiry_id       = cl.enquiry_id" 
					+ " and ed.property         = p.property_id"
					+ " and op.client_id        = :clientId";*/
			
	    Query query             =  session.createNativeQuery(sql).setParameter("clientId", clientId);
	    paidDisbursementDetails = query.setTupleTransformer(Transformers.aliasToBean(PaidDisbursementDetails.class)).list();
	   
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		
		return paidDisbursementDetails;
}

	// created for add details in demand letter mail header section 
	@Override
	public Client getPropertyDetails(int clientTranId) {
		Session session = null;  
		Transaction tx = null; 
		Client propertyDetails = null;
		try{

			session = sessionFactory.openSession();
			tx      = session.beginTransaction();		
			
			
				String resultQuery =  " select w.wingName as wingName, cl.flatNumber as flatNumber, c.companyName as company, pd.projectName as projectName "
									+ " from CompanyProfile c, Wing w, ProjectDetails pd, Client cl "
									+ " where c.companyId = pd.companyId and pd.projectTranId = cl.projectId "
									+ " and w.wingTranId = cl.wingId and cl.clientTranId =:clientTranId";						  
				Query query = session.createQuery(resultQuery).setParameter("clientTranId", clientTranId);
				
				propertyDetails = (Client) query.setTupleTransformer(Transformers.aliasToBean(Client.class)).uniqueResult();
				         
			tx.commit();
		}catch(Exception e){	        
		      e.printStackTrace();
		}finally {				 
		      session.close();
		}	
		return propertyDetails;	
	}

	
}
