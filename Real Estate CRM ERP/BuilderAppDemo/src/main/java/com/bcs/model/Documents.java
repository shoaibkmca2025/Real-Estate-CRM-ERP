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
@Table(name="document")
public class Documents {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_id")
	private int documentId;

	@Column(name="document_type_id")
	private int documentTypeId;

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
	public String documentType;
	
	@Transient
	public MultipartFile documentFile;
	
	public Documents(){
		
	}

	public Documents(int documentId, int documentTypeId, String documentTitle, String documentPath, int projectId,
			String createdDatetime, String updatedDatetime, int isRemove, String documentType,
			MultipartFile documentFile) {
		super();
		this.documentId = documentId;
		this.documentTypeId = documentTypeId;
		this.documentTitle = documentTitle;
		this.documentPath = documentPath;
		this.projectId = projectId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.isRemove = isRemove;
		this.documentType = documentType;
		this.documentFile = documentFile;
	}

	public int getDocumentId() {
		return documentId;
	}

	public void setDocumentId(int documentId) {
		this.documentId = documentId;
	}
	
	public int getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentPath() {
		if(documentPath == null){
			return "";
		}
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
		if(createdDatetime == null){
			return "";
		}
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedDatetime() {
		if(updatedDatetime == null){
			return "";
		}
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public String getDocumentType() {
		if(documentType == null){
			return "";
		}
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentTitle() {
		if(documentTitle == null){
			return "";
		}
		return documentTitle;
	}

	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	
	public MultipartFile getDocumentFile() {
		return documentFile;
	}

	public void setDocumentFile(MultipartFile documentFile) {
		this.documentFile = documentFile;
	}

	@Override
	public String toString() {
		return "Documents [documentId=" + documentId + ", documentTypeId=" + documentTypeId + ", documentTitle="
				+ documentTitle + ", documentPath=" + documentPath + ", projectId=" + projectId + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + ", isRemove=" + isRemove
				+ ", documentType=" + documentType + ", documentFile=" + documentFile + "]";
	}

}
