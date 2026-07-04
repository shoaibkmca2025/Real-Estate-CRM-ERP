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

import com.bcs.model.BankDetails;
import com.bcs.utility.ConstantsUtil;

@Repository
public class BankDetailsDaoImpl implements BankDetailsDao{

	 private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	 
	@SuppressWarnings("unchecked")
	@Override
	public List<BankDetails> getBankDetailsListById(int projectId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<BankDetails> bankdetailsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = " select b.bankId as bankId, b.bankName as bankName, b.branchName as branchName, b.contactPerson as contactPerson, b.contactNo as contactNo, b.iFSCCode "+
						 " as iFSCCode, b.accountType as accountType, b.accountNumber as accountNumber, b.attachment as attachment, b.bankType as bankType, b.contactNo "+
						 " as contactNo, b.accountName as accountName from BankDetails as b, ProjectDetails as p where b.projectId = p.projectTranId and p.projectTranId = :projectId";
			
			Query query     = session.createQuery(hql).setParameter("projectId", projectId);
			bankdetailsList = query.setTupleTransformer(Transformers.aliasToBean(BankDetails.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		
		return bankdetailsList;
	}


	@Override
	public boolean updateBankDetails(BankDetails bankdetails) {
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				int bankType = bankdetails.getBankType();
				String documentName   = null;
				
				if(bankType == 1){
					
					String hqlUpdate =  " update BankDetails set bankName = :bankName, branchName = :branchName, contactPerson = :contactPerson, contactNo = :contactNo, "+									
										" userId = :userId, updatedDatetime = :updatedDatetime where bankId = :bankId";
					
					 session.createQuery(hqlUpdate)
							.setParameter("bankName", bankdetails.getBankName())
							.setParameter("branchName", bankdetails.getBranchName())
							.setParameter("contactPerson", bankdetails.getContactPerson())
							.setParameter("contactNo", bankdetails.getContactNo())
							.setParameter("userId", bankdetails.getUserId())
							.setParameter("updatedDatetime", bankdetails.getUpdatedDatetime())
							.setParameter("bankId", bankdetails.getBankId())
							.executeUpdate();
		 
				}else{
					
						String previousAttachment = bankdetails.getAttachment();
						if(bankdetails.getBankAttachment() != null ){
								String fileName = null;
								String extension = null;
							    MultipartFile file = bankdetails.getBankAttachment();	
							    
							    if (file.getOriginalFilename().indexOf(".") >= 0) {
									  fileName = file.getOriginalFilename().substring(0, (file.getOriginalFilename()).lastIndexOf("."));
									  extension = file.getOriginalFilename().substring((file.getOriginalFilename()).lastIndexOf("."),(file.getOriginalFilename()).length());
									} 
								String originalFilename = (fileName.replaceAll("[^a-zA-Z0-9_]", "_")).replaceAll("_+", "_")+extension;
							    
								if(bankType == 2 && originalFilename != null && originalFilename != ""){
									
									Date date     = new Date();								 	 
								 	documentName  = date.getTime()+"_"+originalFilename;
								 	
								 	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
						                        new File(ConstantsUtil.FILE_SAVE_LOCATION, documentName)));
				
								 	outputStream.write(file.getBytes());
								 	outputStream.flush();
								 	outputStream.close();
								 	
								 	if(previousAttachment != null && previousAttachment != ""){
								 		
								 		File file1          =  new File(ConstantsUtil.FILE_SAVE_LOCATION+previousAttachment);		
										file1.delete();
								 	}
								}
						}else if(previousAttachment != null && previousAttachment != ""){
							
							documentName = previousAttachment;
						}
						 	
						String hqlUpdate =  " update BankDetails set bankName = :bankName, branchName = :branchName, accountName = :accountName, iFSCCode = :iFSCCode, accountType = :accountType, "+ 
											" accountNumber = :accountNumber, attachment = :attachment, userId = :userId, updatedDatetime = :updatedDatetime where bankId = :bankId "; 							
			
						 session.createQuery(hqlUpdate)		
								.setParameter("bankName", bankdetails.getBankName())
								.setParameter("branchName", bankdetails.getBranchName())
								.setParameter("accountName", bankdetails.getAccountName())
								.setParameter("iFSCCode", bankdetails.getiFSCCode())
								.setParameter("accountType", bankdetails.getAccountType())
								.setParameter("accountNumber", bankdetails.getAccountNumber())
								.setParameter("attachment", documentName)
								.setParameter("userId", bankdetails.getUserId())
								.setParameter("updatedDatetime", bankdetails.getUpdatedDatetime())
								.setParameter("bankId", bankdetails.getBankId())
								.executeUpdate();
					   
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
	public boolean addBankDetails(BankDetails bankdetails) {
			
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
					int bankType = bankdetails.getBankType();
					String documentName   = null;
					MultipartFile file = bankdetails.getBankAttachment();
					
					if(file  != null){	
						String fileName = null;
						String extension = null;
						if (file.getOriginalFilename().indexOf(".") >= 0) {
							  fileName = file.getOriginalFilename().substring(0, (file.getOriginalFilename()).lastIndexOf("."));
							  extension = file.getOriginalFilename().substring((file.getOriginalFilename()).lastIndexOf("."),(file.getOriginalFilename()).length());
							} 
						String originalFilename = (fileName.replaceAll("[^a-zA-Z0-9_]", "_")).replaceAll("_+", "_")+extension;
						
						 if(bankType == 2 && originalFilename != null && originalFilename != "" ){
					        	
					    	    Date date  = new Date();
							 					 
							    documentName       = date.getTime()+"_"+originalFilename;
							 	
							 	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
					                        new File(ConstantsUtil.FILE_SAVE_LOCATION, documentName)));
					
							 	outputStream.write(file.getBytes());
							 	outputStream.flush();
							 	outputStream.close();
							 	bankdetails.setAttachment(documentName);	
					    	   
					        }
					}
					
					session.save(bankdetails);
					
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
	public boolean deleteBankDetails(int bankId) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordDeleted = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
					session.createQuery("Delete BankDetails where bankId = :bankId").setParameter("bankId", bankId)
							.executeUpdate();
					
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

	@Override
	public BankDetails getBankDetailsById(int bankId) {
		
		Session session = null;  
		Transaction tx  = null;
		BankDetails bankdetails = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = " select b.bankId as bankId, b.bankName as bankName, b.branchName as branchName, b.contactPerson as contactPerson, b.contactNo as contactNo, p.projectTranId as "+
						 " projectId, b.iFSCCode as iFSCCode, b.accountType as accountType, b.accountNumber as accountNumber, b.attachment as attachment, b.bankType as bankType, "+
					     " b.accountName as accountName from BankDetails as b, ProjectDetails as p where b.projectId = p.projectTranId and b.bankId = :bankId ";
			
			Query query = session.createQuery(hql).setParameter("bankId", bankId);
			bankdetails        = (BankDetails) query.setTupleTransformer(Transformers.aliasToBean(BankDetails.class)).uniqueResult();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		return bankdetails;

	}
	
	@Override
	public String getAttachmentByBankId(int bankId){
		
		Session session 	= null;  
		Transaction tx 	    = null;
		String docName = null;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					Query query = session.createQuery("select b.attachment as attachment from BankDetails b where b.bankId =:bankId");
								  query.setParameter("bankId", bankId);
					
								  docName = (String) query.uniqueResult();
				    
					tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }	
		}finally{
			
			session.close();
		}
		return docName;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<BankDetails> getDisbursementBankDetailsListById(int projectDisbursementId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<BankDetails> disbursementBankdetailsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			 
	        String hql  = " select b.bankName as bankName, b.branchName as branchName, b.iFSCCode as iFSCCode, b.accountNumber as accountNumber, "+
	        			  " b.accountName as accountName from BankDetails b where b.bankType = 2 and b.projectId = "+ 
	        			  " (select pd.projectId from ProjectDisbursement pd where pd.projectDisbursementId = :projectDisbursementId)";
	        
			Query query     = session.createQuery(hql).setParameter("projectDisbursementId", projectDisbursementId);
			disbursementBankdetailsList = query.setTupleTransformer(Transformers.aliasToBean(BankDetails.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		
		return disbursementBankdetailsList;
	}
	
}
