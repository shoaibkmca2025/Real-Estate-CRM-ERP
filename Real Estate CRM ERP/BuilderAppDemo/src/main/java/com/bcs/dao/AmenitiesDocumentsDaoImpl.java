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

import com.bcs.model.Amenities;
import com.bcs.model.DocumentType;
import com.bcs.model.Documents;
import com.bcs.utility.ConstantsUtil;

@Repository
public class AmenitiesDocumentsDaoImpl implements AmenitiesDocumentsDao {

	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Amenities> getAmenitiesById(int projectId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<Amenities> amenitiesList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = " select am.amenityId as amenityId, am.aminities as aminities from Amenities as am, "+
						 " ProjectDetails as p where am.projectId = p.projectTranId and p.projectTranId = :projectId ";
			
			Query query     = session.createQuery(hql).setParameter("projectId", projectId);
			amenitiesList = query.setTupleTransformer(Transformers.aliasToBean(Amenities.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}		
		return amenitiesList;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Documents> getDocumentsById(int projectId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<Documents> documentsList = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();
			
			String hql = "select d.documentId as documentId, dt.documentType as documentType, d.documentPath as documentPath, d.documentTitle as documentTitle "+
						 "from Documents as d, ProjectDetails as p, DocumentType as dt where dt.documentTypeId = d.documentTypeId and "+
						 "d.projectId = p.projectTranId and p.projectTranId = :projectId";
			
			Query query     = session.createQuery(hql).setParameter("projectId", projectId);
			documentsList = query.setTupleTransformer(Transformers.aliasToBean(Documents.class)).list();
			
			tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		
		return documentsList;
	}

	@Override
	public boolean addDocument(Documents document) {

		Session session = null;  
		Transaction tx  = null;
		String fileName  = null;
		String extension  = null;
		boolean isRecordAdded = false;
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
					 
					 
					 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(ConstantsUtil.FILE_SAVE_LOCATION, documentName)));

		             outputStream.write(file.getBytes());
		             outputStream.flush();
		             outputStream.close();
										
					 document.setDocumentPath(documentName);
					
					int docTypeId = document.getDocumentTypeId();
					
					if(docTypeId == -1){
						
						List<Integer> docTypeIds = session.createQuery(
								"select d.documentTypeId from DocumentType d where d.documentType = :documentType", Integer.class)
								.setParameter("documentType", document.getDocumentType())
								.list();

				        if(docTypeIds.isEmpty()){

				        	DocumentType docType  = new DocumentType();
							docType.setDocumentType(document.getDocumentType());
							session.save(docType);
							docTypeId = docType.getDocumentTypeId();
				        }else{
				        	docTypeId = docTypeIds.get(0);

				        }
							document.setDocumentTypeId(docTypeId);
					}
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
	
	/*@Override
	public void addDocument(Documents document) {

		Session session = null;  
		Transaction tx  = null;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
					int docTypeId = document.getDocumentTypeId();
					
					Query query = session.createQuery("select dt.documentType from DocumentType dt where dt.documentTypeId =:documentTypeId");
								  query.setParameter("documentTypeId", docTypeId);
					
				    String documentType = (String) query.uniqueResult();
					
					String Path           = ConstantsUtil.FILE_LOCATION +document.getDocumentPath();
					File file             = new File(Path);
					String encodeImage    = CommonUtil.encodeFileToBase64Binary(file);
					String documentPath   = documentType+"_"+document.getDocumentPath();
					CommonUtil.decodeFileToBase64Binary(encodeImage, documentPath);
					
					document.setDocumentPath(documentPath);
			
					session.save(document);					
					tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }	
		}finally{
			
			session.close();
		}
		
	}*/

	@Override
	public boolean addAmenities(Amenities amenity) {

		Session session = null;  
		Transaction tx  = null;
		boolean isRecordAdded = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					session.save(amenity);
					
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
	public boolean deleteAmenities(int amenityId) {
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordDeleted = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
					int result = session.createQuery("Delete Amenities where amenityId = :amenityId").setParameter("amenityId", amenityId)
							.executeUpdate();
					
					if(result > 0){
						isRecordDeleted = true;
					}				
					
					tx.commit();
					
		}catch(Exception ex){
			ex.printStackTrace();
	    }finally{			
			session.close();
		}
		return isRecordDeleted;
		
	}

	@Override
	public boolean deleteDocument(int documentId) {
		
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordDeleted = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					
					int result = session.createQuery("Delete Documents where documentId = :documentId").setParameter("documentId", documentId)
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
	
	@Override
	public String getDocumentNameByDocumentId(int documentId){
		
		Session session 	= null;  
		Transaction tx 	    = null;
		String documentType = null;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
			
					Query query = session.createQuery("select d.documentPath as documentPath from Documents d where d.documentId =:documentId");
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
	public boolean updateAmenities(Amenities amenity) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update Amenities set aminities = :aminities, userId = :userId, "+ 
								   "updatedDatetime = :updatedDatetime where amenityId = :amenityId";
				
				session.createQuery(hqlUpdate)
						.setParameter("aminities", amenity.getAminities())
						.setParameter("updatedDatetime", amenity.getUpdatedDatetime())
						.setParameter("userId", amenity.getUserId())
						.setParameter("amenityId", amenity.getAmenityId())
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
	
	//To check Document exists or not
    @Override
	public boolean checkDocument(String docName){
	   
   	Session session = null;  
   	Transaction tx  = null; 
	    boolean result=false;
	    try {
	    	session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Long count = session.createQuery(
					"select count(*) from Documents d where d.documentPath = :documentPath", Long.class)
					.setParameter("documentPath", docName)
					.uniqueResult();

	        tx.commit();
	        if(count != null && count > 0) result = true;
	        
	    }catch(Exception e){	       
	        e.printStackTrace();
	    }finally{
	    	session.close();
	    }
	    return result;
	}

	@Override
	public boolean addDocument(Documents documents, MultipartFile file) {
	
		Session session = null;  
		Transaction tx  = null;
		boolean result  = false;
		try{
			
					session = sessionFactory.openSession();
					tx      = session.beginTransaction();
					String fileName  = null;
					String extension  = null;
					 Date date  = new Date();	
					 
					 if (file.getOriginalFilename().indexOf(".") >= 0) {
						  fileName = file.getOriginalFilename().substring(0, (file.getOriginalFilename()).lastIndexOf("."));
						  extension = file.getOriginalFilename().substring((file.getOriginalFilename()).lastIndexOf("."),(file.getOriginalFilename()).length());
						} 
					String originalFilename = (fileName.replaceAll("[^a-zA-Z0-9_]", "_")).replaceAll("_+", "_")+extension;
					
					 String documentName   = date.getTime()+"_"+originalFilename;
					 				
					 BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(ConstantsUtil.FILE_SAVE_LOCATION, documentName)));

		             outputStream.write(file.getBytes());
		             outputStream.flush();
		             outputStream.close();
										
					documents.setDocumentPath(documentName);
					
					int docTypeId = documents.getDocumentTypeId();
					
					if(docTypeId == -1){
						
						DocumentType docType  = new DocumentType();
						docType.setDocumentType(documents.getDocumentType());
						session.save(docType);						

						List<Integer> recentTypeIds = session.createQuery(
								"select d.documentTypeId from DocumentType d order by d.documentTypeId desc", Integer.class)
								.setMaxResults(1)
								.list();
							docTypeId = recentTypeIds.isEmpty() ? 0 : recentTypeIds.get(0);
					
							documents.setDocumentTypeId(docTypeId);
						
					}
			
					
					session.save(documents);	
					
					result = true;
					tx.commit();
		}catch(Exception ex){
			
			ex.printStackTrace();
	        if(tx!= null){
			     tx.rollback();
		    }	
		}finally{
			
			session.close();
		}
		return result;
	}
	
	
}
