package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.FloorDetails;

@Component
public interface FloorDetailsDao {
	
	public boolean addFloorDetails(FloorDetails floordetails);
	
	public List<FloorDetails> getFloorDetailsByWingId(int wingId);
	
	
	
}
