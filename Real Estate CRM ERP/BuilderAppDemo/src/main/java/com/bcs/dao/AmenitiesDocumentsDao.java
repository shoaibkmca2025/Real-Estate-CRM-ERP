package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.Amenities;
import com.bcs.model.Documents;

@Component
public interface AmenitiesDocumentsDao {

	public List<Amenities> getAmenitiesById(int projectId);
	
	public List<Documents> getDocumentsById(int projectId);
	
	public boolean addDocument(Documents document);
	
	public boolean addAmenities(Amenities amenity);
	
	public boolean deleteAmenities(int amenityId);
	
	public boolean deleteDocument(int documentId);

	String getDocumentNameByDocumentId(int documentId);
	
	public boolean updateAmenities(Amenities amenity);

	boolean checkDocument(String docName);

	public boolean addDocument(Documents documents, MultipartFile file);
		
	
}
