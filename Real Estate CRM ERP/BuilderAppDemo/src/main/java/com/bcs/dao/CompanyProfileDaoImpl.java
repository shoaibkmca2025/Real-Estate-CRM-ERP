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

import com.bcs.model.CompanyProfile;
import com.bcs.utility.ConstantsUtil;

@Repository
public class CompanyProfileDaoImpl implements CompanyProfileDao{

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); }

	@Override
	public boolean addCompanyDetails(CompanyProfile companyDetails) {
		Session session = null;  
		Transaction tx = null;
		boolean isAdded = false;
		try{
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			session.save(companyDetails);		
			isAdded = true;
			}catch(Exception ex){
				
				ex.printStackTrace();
				 if(tx!= null){
					  tx.rollback();
			    }				
			}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return isAdded;	
		
	}

	@Override
	public CompanyProfile getCompanyDetailsByUserId(int userId) {
		Session session = null;
		Transaction tx  = null;
		CompanyProfile companyProfile		= null;
		
		try {
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();		
			
				String resultQuery =  " select  c.companyId as companyId, c.companyName as companyName, c.companyEmail as companyEmail, c.website as website, "
									+ "	c.mobile as mobile, c.landline as landline, c.address as address, c.logoPath as logoPath, c.isUpdated as isUpdated, "
									+ " c.marketedBy as marketedBy, c.marketedByWebsite as marketedByWebsite from User as u, CompanyProfile as c "
									+ " where c.companyId = u.companyId and u.userId = :userId"; 
												  
				Query query = session.createQuery(resultQuery).setParameter("userId", userId);
				
				companyProfile = (CompanyProfile) query.setTupleTransformer(Transformers.aliasToBean(CompanyProfile.class)).uniqueResult();
				         
			tx.commit();
		}catch(Exception e){	        
		      e.printStackTrace();
		}finally {				 
		      session.close();
		}
		return companyProfile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyProfile> getAllCompanyDetails() {
		Session session = null;  
		Transaction tx  = null;
		List<CompanyProfile> companyList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery =  "select companyId as companyId, companyName as companyName,companyEmail as companyEmail, website as website, "+ 
				" mobile as mobile, landline as landline, address as address, logoPath as logoPath, marketedBy as marketedBy, "+ 
				" marketedByWebsite as marketedByWebsite from CompanyProfile";
		
		Query query = session.createQuery(resultQuery);
		companyList = query.setTupleTransformer(Transformers.aliasToBean(CompanyProfile.class)).list();  
		
		tx.commit();
		session.close();
		return companyList;
	}

	@Override
	public CompanyProfile getCompanyDetailsByCompanyId(int companyId) {
		Session session = null;  
		Transaction tx = null; 
		CompanyProfile companyProfile = null;
		try{

			session = sessionFactory.openSession();
			tx      = session.beginTransaction();		
			
			
				String resultQuery = "select companyId as companyId, companyName as  companyName, companyEmail as companyEmail, website as website, mobile as mobile, "
						+ " landline as landline, address as address,marketedBy as marketedBy, marketedByWebsite as marketedByWebsite, "
						+ " isUpdated as isUpdated from CompanyProfile where companyId = :companyId";						  
				Query query = session.createQuery(resultQuery).setParameter("companyId", companyId);
				
				companyProfile = (CompanyProfile) query.setTupleTransformer(Transformers.aliasToBean(CompanyProfile.class)).uniqueResult();
				         
			tx.commit();
		}catch(Exception e){	        
		      e.printStackTrace();
		}finally {				 
		      session.close();
		}	
		return companyProfile;	
	}

	@Override
	public void updateCompanyByCompanyId(CompanyProfile companyProfile) {
		Session session = null;  
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update CompanyProfile set companyName = :companyName, companyEmail =:companyEmail, website = :website, mobile =:mobile, "+ 
								   "landline = :landline, address = :address, marketedBy =:marketedBy, marketedByWebsite =:marketedByWebsite, isUpdated =:isUpdated, "+ 
								   "updatedDatetime = :updatedDatetime where companyId = :companyId";
				
				session.createQuery(hqlUpdate)
						.setParameter("companyName", companyProfile.getCompanyName())
						.setParameter("companyEmail", companyProfile.getCompanyEmail())
						.setParameter("website", companyProfile.getWebsite())
						.setParameter("mobile", companyProfile.getMobile())
						.setParameter("landline", companyProfile.getLandline())
						.setParameter("address", companyProfile.getAddress())
						.setParameter("marketedBy", companyProfile.getMarketedBy())
						.setParameter("marketedByWebsite", companyProfile.getMarketedByWebsite())
						.setParameter("isUpdated", companyProfile.getIsUpdated())
						.setParameter("updatedDatetime", companyProfile.getUpdatedDatetime())
						.setParameter("companyId", companyProfile.getCompanyId())
						.executeUpdate();
				
			
			tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		    }	
		}finally {
		      session.close();
		}
	}
	

	@Override
	public String updateCompanyProfileDetailsByCompanyId(CompanyProfile companyProfile) {

		Session session = null;  
		Transaction tx = null;
		//boolean isRecordUpdated = false;
		String documentName   = null;
		
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//String logoPath = getCompanyDetailsByUserId(companyProfile.getUserId()).getLogoPath();
			
				if(companyProfile.getLogoAttachment() != null){
									
				    MultipartFile file = companyProfile.getLogoAttachment();	
				   
					if(file.getOriginalFilename() != null && file.getOriginalFilename() != ""){
						
						Date date     = new Date();								 	 
					 	documentName  = date.getTime()+"_"+file.getOriginalFilename().replace(" ", "_");
					 	
					 	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
			                        new File(ConstantsUtil.FILE_SAVE_LOCATION, documentName)));
	
					 	outputStream.write(file.getBytes());
					 	outputStream.flush();
					 	outputStream.close();
					 	
					 	if(companyProfile.getLogoPath() != "" ){
					 		
					 		File file1          =  new File(ConstantsUtil.FILE_SAVE_LOCATION+companyProfile.getLogoPath());		
							file1.delete();
					 	}
					}
				}else if(companyProfile.getLogoPath() != ""){						
						
						documentName = companyProfile.getLogoPath();	
				}
			
				String hqlUpdate = "update CompanyProfile set companyName = :companyName,companyEmail =:companyEmail, website = :website, "
						+ " mobile =:mobile, landline = :landline, address = :address, logoPath = :logoPath, isUpdated  =:isUpdated,  "
						+ " updatedDatetime =:updatedDatetime where companyId = :companyId";
				
				session.createQuery(hqlUpdate)
						.setParameter("companyName", companyProfile.getCompanyName())
						.setParameter("companyEmail", companyProfile.getCompanyEmail())
						.setParameter("website", companyProfile.getWebsite())
						.setParameter("mobile", companyProfile.getMobile())
						.setParameter("landline", companyProfile.getLandline())
						.setParameter("address", companyProfile.getAddress())
						.setParameter("logoPath", documentName)
						.setParameter("isUpdated", 1)
						.setParameter("updatedDatetime", companyProfile.getUpdatedDatetime())
						.setParameter("companyId", companyProfile.getCompanyId())
						
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
	}
}
