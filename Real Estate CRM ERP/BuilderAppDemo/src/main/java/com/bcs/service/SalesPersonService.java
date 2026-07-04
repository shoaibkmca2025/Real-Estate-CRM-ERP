package com.bcs.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.bcs.model.SalesPerson;

@Component
public interface SalesPersonService {
	
	public void addSalesPerson(SalesPerson salePerson);
	
	public List<SalesPerson> getAllSalesPerson();
	
	public SalesPerson getSalesPersonById(int id);
	
	public void updateSalesPerson(SalesPerson salesPerson);
	
	public void deleteSalesPerson(int id);
	
	public List<SalesPerson> getSalesPersonByProjectId(int projectId);	
}