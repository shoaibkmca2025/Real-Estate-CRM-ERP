package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="reference")
public class Reference {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reference_id")
	private int referenceId;	

	@Column(name="reference_type")
	private String referenceTypeDescr;

	public int getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceTypeDescr() {
		if(referenceTypeDescr == null){
			return "";
		}
		return referenceTypeDescr;
	}

	public void setReferenceTypeDescr(String referenceTypeDescr) {
		this.referenceTypeDescr = referenceTypeDescr;
	}
}
