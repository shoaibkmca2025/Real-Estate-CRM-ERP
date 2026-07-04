package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="document_type")
public class DocumentType {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="document_type_id")
	private int documentTypeId;	
	
	@Column(name="document_type")
	private String documentType;

	public int getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
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

	
}
