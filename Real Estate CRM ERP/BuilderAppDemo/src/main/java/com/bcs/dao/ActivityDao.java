package com.bcs.dao;

import org.springframework.stereotype.Component;

import com.bcs.model.ActivityLog;

@Component
public interface ActivityDao {

	void addActivityDetails(ActivityLog activity);
	
}
