package com.bcs.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bcs.model.ActivityLog;

@Repository
public class ActivityDaoImpl implements ActivityDao{
	
	private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 
	
	Session session = null;  
	Transaction tx = null;
	
	@Override
	public void addActivityDetails(ActivityLog activity) {
		
		Session session = null;  
		Transaction tx = null;
		
		session = sessionFactory.openSession();  
		tx = session.beginTransaction();  
		
		session.save(activity);		
		
		tx.commit();
		session.close();		
	}
}
