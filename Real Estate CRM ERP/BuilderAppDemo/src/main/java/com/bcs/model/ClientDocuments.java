package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="client_documents")
public class ClientDocuments {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_id")
	private int documentId;

	@Column(name="client_id")
	private int clientId;

	@Column(name="document_title")
	private String documentTitle;	
	
	@Column(name="document_path")
	private String documentPath;	

	@Column(name="project_id")
	private int projectId;
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="is_remove")
	private int isRemove;	
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	public MultipartFile documentFile;
	
	@Transient
	private String projectName;
	
	@Transient
	private String wingName;
	
	@Transient
	private String floorName;
	
	@Transient
	private int flatNumber;
	
	public ClientDocuments(){
		
	}

	public ClientDocuments(int documentId, int clientId, String documentTitle, String documentPath, int projectId,
			int userId, int isRemove, String createdDatetime, String updatedDatetime, MultipartFile documentFile) {
		super();
		this.documentId = documentId;
		this.clientId = clientId;
		this.documentTitle = documentTitle;
		this.documentPath = documentPath;
		this.projectId = projectId;
		this.userId = userId;
		this.isRemove = isRemove;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.documentFile = documentFile;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getDocumentTitle() {
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
	}

	public String getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedDatetime() {
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public MultipartFile getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(MultipartFile documentFile) {
		this.documentFile = documentFile;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getWingName() {
		return wingName;
	}

	public void setWingName(String wingName) {
		this.wingName = wingName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public int getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}

	@Override
	public String toString() {
		return "ClientDocuments [documentId=" + documentId + ", clientId=" + clientId + ", documentTitle="
				+ documentTitle + ", documentPath=" + documentPath + ", projectId=" + projectId + ", userId=" + userId
				+ ", isRemove=" + isRemove + ", createdDatetime=" + createdDatetime + ", updatedDatetime="
				+ updatedDatetime + ", documentFile=" + documentFile + "]";
	}

}
