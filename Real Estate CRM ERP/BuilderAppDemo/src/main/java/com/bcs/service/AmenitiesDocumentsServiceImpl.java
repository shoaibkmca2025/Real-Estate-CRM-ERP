package com.bcs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.AmenitiesDocumentsDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.Amenities;
import com.bcs.model.Documents;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;

@Service
public class AmenitiesDocumentsServiceImpl implements AmenitiesDocumentsService{

	@Autowired
	private AmenitiesDocumentsDao amenitiesDocumentsDao;
	
	@Autowired  
	private ActivityDao activityDao;

	@Override
	public List<Amenities> getAmenitiesById(int projectId) {
		
		return amenitiesDocumentsDao.getAmenitiesById(projectId);
	}

	@Override
	public List<Documents> getDocumentsById(int projectId) {
		
		return amenitiesDocumentsDao.getDocumentsById(projectId);
	}

	@Override
	public boolean addDocument(Documents document) {
		
		  String dateTime = DateTimeUtil.getSysDateTime();
		  boolean isRecordAdded = false;
		  document.setUpdatedDatetime(dateTime);
		  document.setCreatedDatetime(dateTime);
				
		  isRecordAdded = amenitiesDocumentsDao.addDocument(document);
		  
		  if(isRecordAdded==true){
				 ActivityLog activityLog = new ActivityLog();
				 
				 activityLog.setUserId(document.getUserId()); 
				 activityLog.setActivityDescription("DocumentId "+document.getDocumentId()+" Added");
				 activityLog.setProjectTranId(document.getProjectId());
				 activityLog.setActivityDatetime(dateTime);
				 activityDao.addActivityDetails(activityLog);
			}
		return isRecordAdded;
	}

	@Override
	public boolean addAmenities(Amenities amenity) {
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		 boolean isRecordAdded = false;
		 amenity.setCreatedDatetime(dateTime); 
		 amenity.setUpdatedDatetime(dateTime);
		 
		 isRecordAdded = amenitiesDocumentsDao.addAmenities(amenity);
		 
		 if(isRecordAdded==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(amenity.getUserId()); 
			 activityLog.setActivityDescription("AmenityId "+amenity.getAmenityId()+" added");
			 activityLog.setProjectTranId(amenity.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		 return isRecordAdded;
	}

	@Override
	public boolean deleteAmenities(int amenityId, int userId, int projectId) {
		boolean isRecordDeleted = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordDeleted = amenitiesDocumentsDao.deleteAmenities(amenityId);
		
		if(isRecordDeleted==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("AmenityId "+amenityId+" Deleted");
			 activityLog.setProjectTranId(projectId);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordDeleted;
	}

	@Override
	public boolean deleteDocument(int documentId, int userId, int projectId) {
		boolean isRecordDeleted = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordDeleted = amenitiesDocumentsDao.deleteDocument(documentId);
		
		String documentName    =  amenitiesDocumentsDao.getDocumentNameByDocumentId(documentId);
		File file              =  new File(ConstantsUtil.FILE_SAVE_LOCATION+documentName);		
		file.delete();
		
		if(isRecordDeleted==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("DocumentId "+documentId+" Deleted");
			 activityLog.setProjectTranId(projectId);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordDeleted;
	}

	@Override
	public String getDocumentNameByDocumentId(int documentId) {
		
		return amenitiesDocumentsDao.getDocumentNameByDocumentId(documentId);
	}

	@Override
	public boolean updateAmenities(Amenities amenity) {
		boolean isRecordUpdated = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		 amenity.setUpdatedDatetime(dateTime);
		 
		 isRecordUpdated = amenitiesDocumentsDao.updateAmenities(amenity);
		
		if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(amenity.getUserId()); 
			 activityLog.setActivityDescription("AmenityId "+amenity.getAmenityId()+" Updated");
			 activityLog.setProjectTranId(amenity.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordUpdated;
	}

	@Override
	public boolean checkDocument(String docName) {
		
		return amenitiesDocumentsDao.checkDocument(docName);
	}

	@Override
	public boolean addDocument(Documents documents, MultipartFile file) {
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		  documents.setUpdatedDatetime(dateTime);
		  documents.setCreatedDatetime(dateTime);
				
		return amenitiesDocumentsDao.addDocument(documents, file);
	}

	@Override
	public void downloadDocument(String documentName, HttpServletResponse response) throws FileNotFoundException {
		
		try{
			
			String fullPath         =  ConstantsUtil.FILE_SAVE_LOCATION+documentName;
			
			File downloadFile           = new File(fullPath);		
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			 //ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			//ServletContext context = request.getServletContext();
			//String mimeType        = servletContext.getMimeType(fullPath);
			//response.setContentType(mimeType);
		    
		    //response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + downloadFile.getName() +"\""));
		   // response.setContentLength((int)downloadFile.length());
		    ServletOutputStream out = response.getOutputStream();
		   
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	        	out.write(buffer, 0, bytesRead);
	        }
			
			out.flush();	 
	        inputStream.close();
	       
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
