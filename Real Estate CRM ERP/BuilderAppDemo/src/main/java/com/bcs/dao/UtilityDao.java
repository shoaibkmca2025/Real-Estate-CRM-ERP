package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.DocumentType;
import com.bcs.model.Property;
import com.bcs.model.PropertyType;
import com.bcs.model.Reference;
import com.bcs.model.Settings;
import com.bcs.model.WingDetails;

@Component
public interface UtilityDao {

	public List<Reference> getAllReferences();
	
	public List<PropertyType> getAllPropertyType();
	
	public List<Property> getPropertyByPropertyType(int propertyTypeId);
	
	public List<WingDetails> getPropertyByPropertyTypeAndProjectId(int projectId, int propertyTypeId);
	
	public List<DocumentType> getAllDocumentType();

	public List<Property> getAllProperty();
	
	public boolean updateSettings(Settings settings);
	
	public boolean addSettings(Settings settings);
	
	public Settings getSettingsByCompanyId(int companyId);	
	
	@SuppressWarnings("rawtypes")
	public int getRecentId(Class objclass, String pid);
	
} 
