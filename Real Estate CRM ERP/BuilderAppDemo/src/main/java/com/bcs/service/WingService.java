package com.bcs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.Wing;
import com.bcs.model.WingDetails;

@Component
public interface WingService {

	public List<Wing> getWingListByProjectId(int projectId);
	
	public List<WingDetails> getWingDetailsByProjectId(int projectId);
	
	public List<WingDetails> getAllWingDetailsByWingId(int wingId);
	
	public Wing getWingById(int wingId);
		
	public boolean updateWing(Wing wing);
	
	public boolean addWing(Wing wing);
	
	public boolean deleteWing(int wingId, int projectId, int userId);

	public List<WingDetails> getAllWingDetailsList();

	public List<Wing> getAllWing();
	
	WingDetails getUnbookedPropertyDetails(int wingId, int flatNumber, int projectId, int floorNumber, String floorName);
}
