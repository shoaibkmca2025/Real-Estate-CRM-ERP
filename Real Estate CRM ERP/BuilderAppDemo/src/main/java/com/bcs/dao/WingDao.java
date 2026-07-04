package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.FloorDetails;
import com.bcs.model.Wing;
import com.bcs.model.WingDetails;

@Component
public interface WingDao {

	public List<Wing> getWingListByProjectId(int projectId);
	
	public List<WingDetails> getWingDetailsByProjectId(int projectId);
	
	public List<WingDetails> getAllWingDetailsByWingId(int wingId);
	
	public Wing getWingById(int wingId);
			
	public boolean updateWing(Wing wing);
	
	public boolean addWing(Wing wing);
	
	public boolean deleteWing(int wingId, int projectId);

	public List<WingDetails> getAllWingDetailsList();

	public List<Wing> getAllWing();
	
	WingDetails getUnbookedPropertyDetails(int wingId, int flatNumber, int floorNumber, String floorName);
	
	
}
