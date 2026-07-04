package com.bcs.service;

import java.io.FileNotFoundException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.Amenities;
import com.bcs.model.Documents;

@Component
public interface AmenitiesDocumentsService {

	public List<Amenities> getAmenitiesById(int projectId);
	
	public List<Documents> getDocumentsById(int projectId);
	
	public boolean addDocument(Documents document);
	
	public boolean addAmenities(Amenities amenity);
	
	boolean deleteAmenities(int amenityId, int userId, int projectId);
	
	boolean deleteDocument(int documentId, int userId, int projectId);
	
	String getDocumentNameByDocumentId(int documentId);
	
	public boolean updateAmenities(Amenities amenity);
	
	boolean checkDocument(String docName);

	public boolean addDocument(Documents documents, MultipartFile file);
	
	public void downloadDocument(String documentName, HttpServletResponse response ) throws FileNotFoundException;
}

