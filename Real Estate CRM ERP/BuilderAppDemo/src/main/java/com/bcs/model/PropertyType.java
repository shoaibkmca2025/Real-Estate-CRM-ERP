package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="property_type")
public class PropertyType {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="property_type_id")
	private int propertyTypeId;	

	@Column(name="property_type_descr")
	private String propertyTypeDescr;

	public int getPropertyTypeId() {
		return propertyTypeId;
	}

	public void setPropertyTypeId(int propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

	public String getPropertyTypeDescr() {
		if(propertyTypeDescr == null){
			return "";
		}
		return propertyTypeDescr;
	}

	public void setPropertyTypeDescr(String propertyTypeDescr) {
		this.propertyTypeDescr = propertyTypeDescr;
	}	
}
