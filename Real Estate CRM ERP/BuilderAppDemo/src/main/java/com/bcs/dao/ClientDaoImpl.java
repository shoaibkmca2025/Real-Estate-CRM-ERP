package com.bcs.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.Client;
import com.bcs.model.ClientDocuments;
import com.bcs.model.Disbursement;
import com.bcs.model.DocumentType;
import com.bcs.model.Documents;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.model.ProjectDisbursement;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;

@Repository
public class ClientDaoImpl implements ClientDao{

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addClient(Client client) {
		
		Session session        = null;  
		Transaction tx         = null;
		boolean isRecordAdded = false;
		try{
			
			session = sessionFactory.openSession();  
			tx      = session.beginTransaction();  
			
		       session.save(client);
		       
		       int bookingStatus  = client.getBookingStatus();
		       int followupStatus = 0;
		       if(bookingStatus == 1){
		    	   followupStatus = 5;
		       }else{
		    	   followupStatus = 2;
		       }
		       
		       String hqlUpdate  = " update EnquiryDetails set followupStatus = :followupStatus where enquiryId = :enquiryId";
		          
		        session.createQuery(hqlUpdate)		       
				       .setParameter("enquiryId",client.getClientEnquiryId())
				       .setParameter("followupStatus", followupStatus)
					   .executeUpdate();
		       
		        if(bookingStatus == 1){
		        	
				       int recentClientTranId = client.getClientTranId();
				       int projectId          = client.getProjectId();
				       
				       String hql  = " select pd.projectDisbursementId as projectDisbursementId, pd.disbursementPercentage as disbursementPercentage from ProjectDisbursement pd where projectId = :projectId";
				          
				       List<ProjectDisbursement> disbursementList =  session.createQuery(hql).setParameter("projectId", projectId).setTupleTransformer(Transformers.aliasToBean(ProjectDisbursement.class)).list();
				       
					    	   for(int i = 0; i < disbursementList.size(); i++){
					    		  
					    		   int projectDisbursementId     = disbursementList.get(i).getProjectDisbursementId();
					    		   double disbursementPercentage = disbursementList.get(i).getDisbursementPercentage();
					    		   double gstPercentage			 = client.getGstPercentage();
					    		   
					    		   double disbursementAmount = (client.getAgreementAmount()/100) * Double.valueOf(disbursementPercentage);
					    		  // double roundOffAmount     = Math.round(disbursementAmount);
					    		   double roundOffAmount     = disbursementAmount;
					    		   
					    		   double gstAmount          = (roundOffAmount/100) * Double.valueOf(gstPercentage);
					    		   double roundOffGstAmount  = gstAmount;
					    		  // double roundOffGstAmount  = Math.round(gstAmount);
					    		   
					    		  // double totalAmount        =  Math.round(roundOffAmount + roundOffGstAmount);
					    		   double totalAmount        =  roundOffAmount + roundOffGstAmount;
					    		   
					    		   Disbursement disbursement = new Disbursement();
					    		   disbursement.setClientId(recentClientTranId);
					    		   disbursement.setPercentageValue(disbursementPercentage);
					    		   disbursement.setDisbursementAmount(roundOffAmount);
					    		   disbursement.setGstAmount(roundOffGstAmount);
					    		   disbursement.setTotalAmount(totalAmount);
					    		   disbursement.setRemainingAmount(totalAmount);
					    		   disbursement.setCreatedDatetime(client.getCreatedDatetime());
					    		   disbursement.setUpdatedDatetime(client.getUpdatedDatetime());
					    		   disbursement.setProjectDisbursementId(projectDisbursementId);
					    		   disbursement.setUserId(client.getUserId());
					    		   session.save(disbursement);
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
	public List<Client> getAllClientsByProjectId(int projectId) {
		
		Session session        = null;  
		Transaction tx         = null;
		List<Client> clientList = null;
		try{
			
			session = sessionFactory.openSession();
			tx  = session.beginTransaction();
			
			 String resultQuery = " select cl.clientId as clientId, cl.clientTranId as clientTranId, cl.ownerName as ownerName, pd.projectName as projectName, ed.enquiryId as clientEnquiryId,"+
					 			  " w.wingName as wingName, pt.propertyTypeDescr as propertyTypeDescr, p.propertyName as propertyName, cl.propertyArea as propertyArea, cl.createdDatetime as createdDatetime,"+
					 			  " cl.floorNumber as floorNumber, cl.flatNumber as flatNumber, ed.mobileNo as mobileNo, cl.bookingStatus as bookingStatus "+ 
					 			  " from ProjectDetails as pd, Wing as w, Client as cl, PropertyType as pt,  Property as p, EnquiryDetails as ed"+
					 			  " where pd.projectTranId = cl.projectId "+
					 			  " and ed.enquiryId = cl.clientEnquiryId "+
					 			  " and w.wingTranId = cl.wingId "+
					 			  " and cl.propertyTypeId = pt.propertyTypeId "+
					 			  " and p.propertyId = cl.propertyId "+
					 			  " and pd.projectTranId = :projectId order by cl.clientId desc ";
					
			 Query query = session.createQuery(resultQuery).setParameter("projectId", projectId);
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
	public Client getClientDetailsById(int clientId) {
		
		Session session        = null;  
		Transaction tx         = null;
		Client clientDetails = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			
			 
			  Query query      = session.createNativeQuery("CALL proc_getClientDetailsById(:clientId)").setParameter("clientId", clientId);
			 
			  clientDetails   = (Client) query.setTupleTransformer(Transformers.aliasToBean(Client.class)).uniqueResult();  
			     
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return clientDetails;
	}

	@Override
	public Client getBookedFlatDetails(int wingId, int flatNumber, int floorNumber, String floorName) {
				
		Session session          = null;  
		Transaction tx           = null;
		Client bookedFlatDetails = null;
		
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
					 
			 Query query  =  session.createNativeQuery("CALL proc_getBookedFlatDetails(:wingId,:floorName,:floorNumber,:flatNumber)")
					 		.setParameter("wingId", wingId)					 		
					 		.setParameter("floorName", floorName)
					 		.setParameter("floorNumber", floorNumber)				
					 		.setParameter("flatNumber", flatNumber);			
			 
			 bookedFlatDetails  =  (Client) query.setTupleTransformer(Transformers.aliasToBean(Client.class)).uniqueResult();  
			   
			 tx.commit();
			 
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return bookedFlatDetails;
	
	}

	/*@Override
	public boolean checkFlatNumberExistOrNot(int wingId,int flatNumber) {
		
		Session session = null;  
		Transaction tx  = null; 
	    boolean result=false;
	    try {
	    	
	    	session = sessionFactory.openSession(); 
	 		tx      = session.beginTransaction(); 
			Long count = session.createQuery(
					"select count(*) from Client c where c.wingId = :wingId and c.flatNumber = :flatNumber", Long.class)
					.setParameter("wingId", wingId)
					.setParameter("flatNumber", flatNumber)
					.uniqueResult();

	        if(count != null && count > 0) result = true;
	        
	            tx.commit();
	      
	    }catch(Exception e){	       
	        e.printStackTrace();
	    }finally{
	    	 if(session.isOpen())
	    	 session.close();
	    }
	    return result;
	}*/
	
	@Override
	public String getClientNameByFlatNumberAndWingId(int wingId,int flatNumber, int floorNum, String floorName) {
		
		Session session   = null;  
		Transaction tx    = null; 
	    String clientName = null;
	    try {
	    	
		    	session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
				
				String hql =   " select cl.ownerName as ownerName from Client as cl " 
							 + " where cl.wingId = :wingId and cl.flatNumber = :flatNumber "
							 + " and cl.floorNumber =:floorNumber and cl.floorName = :floorName and cl.bookingStatus != 3";							  
						
				Query query = session.createQuery(hql).setParameter("wingId", wingId).setParameter("flatNumber", flatNumber)
									 .setParameter("floorNumber", floorNum).setParameter("floorName", floorName);
									
				clientName = (String) query.uniqueResult();
	      
	    }catch(Exception e){	       
	        e.printStackTrace();
	        if(tx!= null){
				  tx.rollback();
		    }	
	    }finally{
	    	 tx.commit();
	    	 if(session.isOpen())
	    	 session.close();
	    	 
	    }
	    return clientName;
	}
	
	@Override
	public String getUnbookedPropertyType(int wingId,int flatNumber, int floorNum, String floorName) {
		
		Session session   = null;  
		Transaction tx    = null; 
	    String propertyName = null;
	    try {
		    	session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
				
				String hql =    " select p.propertyName as propertyName from Flats as f, WingDetails as wd, Property as p where wd.wingDetailsId = f.wingDetailsId "
							  + " and p.propertyId = wd.propertyId and wd.wingId = :wingId and  f.flatNumber = :flatNumber and f.floorNumber =:floorNumber "
							  + " and c.floorName = :floorName";							  
						
				Query query = session.createQuery(hql).setParameter("wingId", wingId).setParameter("flatNumber", flatNumber)
									 .setParameter("floorNumber", floorNum).setParameter("floorName", floorName);
									
				propertyName = (String) query.uniqueResult();
			   
	          
	      
	    }catch(Exception e){	       
	        e.printStackTrace();
	        if(tx!= null){
				  tx.rollback();
		    }	
	    }finally{
	    	  tx.commit();
	    	 if(session.isOpen())
	    	 session.close();
	    }
	    return propertyName;
	}
	
	@Override
	public int getRecentClientTranId() {
		
		int clientTranId 	= 0;
		Session session     = null;  
		Transaction tx      = null;
		try{
			
			session = sessionFactory.openSession();  
			tx 		= session.beginTransaction(); 
			
			  List<Integer> maxIdList = session.createQuery(
					  "select c.clientId from Client c order by c.clientId desc", Integer.class)
					  .setMaxResults(1)
					  .list();

		     if(maxIdList.isEmpty()){
		    	 clientTranId = 1;
		     }else{
		    	 clientTranId = maxIdList.get(0);
		    	 clientTranId = clientTranId+1;
		     }
		     
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return clientTranId;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateClientDetailsByClientTranId(Client client) {
		
		Session session = null;
		Transaction tx  = null;
		boolean isRecordUpdated = false;
		try{
			
			session = sessionFactory.openSession();
			tx		= session.beginTransaction();
			
			String hqlQry =   " Update Client set ownerName = :ownerName, dateOfBirth = :dateOfBirth, panNumber = :panNumber, aadharNumber = :aadharNumber, "
							+ " rate = :rate, infrastructureAmount = :infrastructureAmount, agreementAmount = :agreementAmount, gstPercentage = :gstPercentage, ";
				//if(client.getBookingStatus() == 1 ){
				   hqlQry   += " registrationDate = :registrationDate, registrationNo = :registrationNo, ";
				//}
				   hqlQry   += " bookingStatus = :bookingStatus where clientTranId = :clientTranId ";
			
			Query query = session.createQuery(hqlQry)
					 			  .setParameter("ownerName", client.getOwnerName())
					 			  .setParameter("dateOfBirth", client.getDateOfBirth())
								  .setParameter("panNumber", client.getPanNumber())
								  .setParameter("aadharNumber", client.getAadharNumber())
								  .setParameter("rate", client.getRate())
								  .setParameter("infrastructureAmount", client.getInfrastructureAmount())
								  .setParameter("agreementAmount", client.getAgreementAmount())
								  .setParameter("gstPercentage",client.getGstPercentage())
								  .setParameter("bookingStatus", client.getBookingStatus())					
								  .setParameter("clientTranId", client.getClientTranId())
							      .setParameter("registrationNo", client.getRegistrationNo());
			
								  //if(client.getBookingStatus() == 1 ){
									  query.setParameter("registrationDate",client.getRegistrationDate() );
								 // }
								  
								  			
					int result = query.executeUpdate();			 
			 		if(result > 0 ){
			 			isRecordUpdated = true;
			 		}
			 
			        if(result > 0  && client.getBookingStatus() == 1){
			        	
					       int recentClientTranId = client.getClientTranId();
					       int projectId          = client.getProjectId();
					       
					       String hql  = " select pd.projectDisbursementId as projectDisbursementId, pd.disbursementPercentage as disbursementPercentage from ProjectDisbursement pd where projectId = :projectId";
					          
					       List<ProjectDisbursement> disbursementList =  session.createQuery(hql).setParameter("projectId", projectId).setTupleTransformer(Transformers.aliasToBean(ProjectDisbursement.class)).list();
					       
						    	   for(int i = 0; i < disbursementList.size(); i++){
						    		  
						    		   int projectDisbursementId     = disbursementList.get(i).getProjectDisbursementId();
						    		   double disbursementPercentage = disbursementList.get(i).getDisbursementPercentage();
						    		   double gstPercentage			 = client.getGstPercentage();
						    		   
						    		   double disbursementAmount = (client.getAgreementAmount()/100) * Double.valueOf(disbursementPercentage);
						    		  // double roundOffAmount     = Math.round(disbursementAmount);
						    		   double roundOffAmount     = disbursementAmount;
						    		   
						    		   double gstAmount          = (roundOffAmount/100) * Double.valueOf(gstPercentage);
						    		   double roundOffGstAmount  = gstAmount;
						    		  // double roundOffGstAmount  = Math.round(gstAmount);
						    		   
						    		  // double totalAmount        =  Math.round(roundOffAmount + roundOffGstAmount);
						    		   double totalAmount        =  roundOffAmount + roundOffGstAmount;
						    		   
						    		   Disbursement disbursement = new Disbursement();
						    		   String dateTime = DateTimeUtil.getSysDateTime();
						    		   
						    		   disbursement.setClientId(recentClientTranId);
						    		   disbursement.setPercentageValue(disbursementPercentage);
						    		   disbursement.setDisbursementAmount(roundOffAmount);
						    		   disbursement.setGstAmount(roundOffGstAmount);
						    		   disbursement.setTotalAmount(totalAmount);
						    		   disbursement.setRemainingAmount(totalAmount);
						    		   disbursement.setCreatedDatetime(dateTime);
						    		   disbursement.setUpdatedDatetime(dateTime);
						    		   disbursement.setProjectDisbursementId(projectDisbursementId);
						    		   disbursement.setUserId(client.getUserId());
						    		   session.save(disbursement);
						    	   }
			  }
			  
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return isRecordUpdated;
	}

	@Override
	public boolean cancelPropertyBooking(Client client) {

		Session session   = null;
		Transaction tx    = null;
		boolean isUpdated = false;
		try{
			
			session = sessionFactory.openSession();
			tx		= session.beginTransaction();
			
			 String dateTime = DateTimeUtil.getSysDateTime();
			 
			String hqlQry =  " Update Client set bookingStatus = :bookingStatus, remark = :remark, updatedDatetime = :updatedDatetime "
						   + " where clientTranId = :clientTranId ";
			
			 int result =  session.createQuery(hqlQry)
					 			  .setParameter("bookingStatus", 3)
					 			  .setParameter("updatedDatetime", dateTime)
					 			  .setParameter("remark", client.getRemark())	
								  .setParameter("clientTranId", client.getClientTranId())									 
								  .executeUpdate();	
			 
			/* String hqlQry1 =  " Update enquiry_details set followup_status = :followupStatus "
					 		+ "  where enquiry_id = (select cl.enquiry_id from client where cl.client_tran_id = :clientTranId)";
		
			 int result1 =  session.createNativeQuery(hqlQry1)
				 			  .setParameter("followupStatus", 1)
							  .setParameter("clientTranId", client.getClientTranId())									 
							  .executeUpdate();	*/	
		 
			 
			 if(result > 0 ){
				 isUpdated = true;
			 }
			 tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return isUpdated;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaidDisbursementDetails> getClientsDisbursementRemaingAmountByProjectId(int projectId) {
		Session session        = null;  
		Transaction tx         = null;
		List<PaidDisbursementDetails> clientList = null;
		try{			
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  		
				
				String sql = "select c.client_tran_id as clientTranId, c.agreement_Amount as agreementAmount, c.owner_name as ownerName, ed.mobile_no as mobileNo, c.flat_number as flatNumber, "
						+ " w.wing_name as wingName, sum(d.paid_amount)as paidAmount, sum(d.disbursement_amount) as disbursementAmount, "
						+ " sum(d.gst_amount) as gstAmount, sum(d.remaining_amount) as remainingAmount "
						+ " from client as c, enquiry_details as ed, disbursement as d, wing as w, project_details as pd "					
						+ " WHERE pd.project_tran_id = c.project_id "
						+ " and ed.enquiry_id        = c.enquiry_id "						
						+ " and w.wing_tran_id       = c.wing_id "
						+ " and d.client_id          = c.client_tran_id"
						+ " and pd.project_tran_id   = :projectId" 					
						+ " and Case When :projectId != 0 Then pd.project_tran_id = :projectId else true end "						
						+ " Group By c.client_id";
					
		        Query query  =  session.createNativeQuery(sql).setParameter("projectId", projectId);
		        			
		        clientList = query.setTupleTransformer(Transformers.aliasToBean(PaidDisbursementDetails.class)).list();
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
	public PaidDisbursementDetails getAllDisbursmentDetailsByClient(int clientId) {
		Session session        = null;  
		Transaction tx         = null;
		PaidDisbursementDetails paidDisbursementDetails = null;
		try{			
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  		
				
				String sql = "select c.client_tran_id as clientTranId, c.agreement_amount as agreementAmount, c.owner_name as ownerName, ed.mobile_no as mobileNo, c.flat_number as flatNumber, "
						+ " w.wing_name as wingName, sum(d.paid_amount)as paidAmount, sum(d.disbursement_amount) as disbursementAmount,  sum(total_amount) as totalAmount, "
						+ " sum(d.gst_amount) as gstAmount, sum(d.remaining_amount) as remainingAmount,  pd.project_name as projectName"
						+ " from client as c, enquiry_details as ed, disbursement as d, wing as w, project_details as pd "					
						+ " WHERE pd.project_tran_id = c.project_id "
						+ " and ed.enquiry_id        = c.enquiry_id "						
						+ " and w.wing_tran_id       = c.wing_id "
						+ " and d.client_id          = c.client_tran_id"
						+ " and c.client_tran_id    = :clientId"
						+ " Group By c.client_id";
					
		        Query query  =  session.createNativeQuery(sql).setParameter("clientId", clientId);
		        
		        paidDisbursementDetails  = (PaidDisbursementDetails) query.setTupleTransformer(Transformers.aliasToBean(PaidDisbursementDetails.class)).uniqueResult();  
		        tx.commit();     
		    		}catch(Exception ex){
		    			 ex.printStackTrace();
		    	          if(tx!= null){
		    				  tx.rollback();
		    		      }	
		    		}finally{
		    	  		session.close();
		    		}
		    		
		    		return paidDisbursementDetails;
		    	}
	
	@Override
	public boolean addClientDocuments(ClientDocuments document) {

		Session session = null;  
		Transaction tx  = null;
		String fileName  = null;
		String extension  = null;
		boolean isRecordAdded = false;
		String pdfName = null;
		String filePath = null;
		   
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
					 Date date  = new Date();				
					
					 MultipartFile file = document.getDocumentFile();
					 
					 if (file.getOriginalFilename().indexOf(".") >= 0) {
						  fileName = file.getOriginalFilename().substring(0, (file.getOriginalFilename()).lastIndexOf("."));
						  extension = file.getOriginalFilename().substring((file.getOriginalFilename()).lastIndexOf("."),(file.getOriginalFilename()).length());
						} 
					String originalFilename = (fileName.replaceAll("[^a-zA-Z0-9_]", "_")).replaceAll("_+", "_")+extension;
					 
					String documentName   = date.getTime()+"_"+originalFilename;
					
					filePath = document.getProjectName()+"\\"+document.getWingName()+"\\"+document.getFloorName()+"\\"+document.getFlatNumber();
					 
					File theDir = new File(ConstantsUtil.FILE_SAVE_LOCATION +filePath); 
					
					if (!theDir.exists()){ 							
						 theDir.mkdirs();
					}
					
					 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(theDir, documentName)));

		             outputStream.write(file.getBytes());
		             outputStream.flush();
		             outputStream.close();
										
					 document.setDocumentPath(filePath+"\\"+documentName);
					
					/*int docTypeId = document.getDocumentTypeId();
					
					if(docTypeId == -1){
						
						Criteria criteria1 =	session.createCriteria(DocumentType.class).add(Restrictions.eq("documentType", document.getDocumentType()))
												.setProjection(Projections.property("documentTypeId"));			
						
				        if(criteria1.list().size() == 0){
				        	
				        	DocumentType docType  = new DocumentType();
							docType.setDocumentType(document.getDocumentType());
							session.save(docType);	*/					

							/*Criteria criteria = session.createCriteria(DocumentType.class)
												.setProjection(Projections.property("documentTypeId"))
												 .addOrder(Order.desc("documentTypeId"));			  
								docTypeId     = (int) criteria.setMaxResults(1).uniqueResult();	*/
							/*docTypeId = docType.getDocumentTypeId();
				        }else{
				        	docTypeId = (int) criteria1.uniqueResult();
				        	
				        }
							document.setDocumentTypeId(docTypeId);
					}*/
					session.save(document);	
					
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ClientDocuments> getClientDocumentsListByClientId(int clientId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<ClientDocuments> documentsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = "select d.documentId as documentId, d.documentPath as documentPath, d.documentTitle as documentTitle "+
						 "from ClientDocuments as d where d.clientId = :clientId";
			
			Query query     = session.createQuery(hql).setParameter("clientId", clientId);
			documentsList = query.setTupleTransformer(Transformers.aliasToBean(ClientDocuments.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		
		return documentsList;
	}
	
	@Override
	public String getDocumentNameByDocumentId(int documentId){
		
		Session session 	= null;  
		Transaction tx 	    = null;
		String documentType = null;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					Query query = session.createQuery("select d.documentPath as documentPath from ClientDocuments d where d.documentId =:documentId");
								  query.setParameter("documentId", documentId);
					
				    documentType = (String) query.uniqueResult();
				    
					tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }	
		}finally{
			
			session.close();
		}
		return documentType;
	}
	
	@Override
	public boolean deleteClientDocumentById(int documentId) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordDeleted = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
					int result = session.createQuery("Delete ClientDocuments where documentId = :documentId").setParameter("documentId", documentId)
							.executeUpdate();
					
					if(result > 0){
						isRecordDeleted = true;
					}	
					
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
}
