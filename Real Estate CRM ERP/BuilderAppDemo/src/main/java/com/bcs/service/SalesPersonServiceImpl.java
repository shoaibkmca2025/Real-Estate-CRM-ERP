package com.bcs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.SalesPersonDaoImpl;
import com.bcs.model.SalesPerson;

@Service
public class SalesPersonServiceImpl implements SalesPersonService {

	@Autowired
	SalesPersonDaoImpl dao; 
	
	@Override
	public void addSalesPerson(SalesPerson salePerson) {		
		dao.addSalesPerson(salePerson);		
	}

	@Override
	public List<SalesPerson> getAllSalesPerson() {		
		return dao.getAllSalesPerson();
	}

	@Override
	public SalesPerson getSalesPersonById(int id) {		
		return dao.getSalesPersonById(id);
	}

	@Override
	public void updateSalesPerson(SalesPerson salesPerson) {		
		dao.updateSalesPerson(salesPerson);		
	}

	@Override
	public void deleteSalesPerson(int id) {		
		dao.deleteSalesPerson(id);		
	}

	@Override
	public List<SalesPerson> getSalesPersonByProjectId(int projectId) {		
		return dao.getSalesPersonByProjectId(projectId);
	}
}
