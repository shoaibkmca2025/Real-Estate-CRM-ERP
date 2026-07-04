package com.bcs.webservice;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcs.model.Status;
import com.bcs.model.Wing;
import com.bcs.model.WingDetails;
import com.bcs.service.WingService;


@Controller
public class WingWebController {
	
	final static Logger logger = LoggerFactory.getLogger(WingWebController.class);	
	
	@Autowired
	WingService wingService;
	
	@RequestMapping(value = "/getAllWingDetails", method = RequestMethod.GET) 
	public @ResponseBody  
	Status getAllWingDetails() throws IOException {	
			
		try{
				List<WingDetails> wingDetailsList = wingService.getAllWingDetailsList();
				return new Status(200, wingDetailsList);
				
		}catch(Exception e){
				e.printStackTrace();
				return new Status(500, e.toString());
	    }
	}
	
	@RequestMapping(value = "/getAllWingData", method = RequestMethod.GET) 
	public @ResponseBody  
	Status getAllWingData() throws IOException {	
			
		try{
				List<Wing> wingList = wingService.getAllWing();
				return new Status(200, wingList);
				
		}catch(Exception e){
				e.printStackTrace();
				return new Status(500, e.toString());
	    }
		
	}

	@RequestMapping(value = "/getAllWingsByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllWingsByProjectId(@PathVariable("projectId") int projectId) throws Exception{
		
		 try{			
			 	List<Wing>  wingLists = wingService.getWingListByProjectId(projectId); 	
			 	return new Status(200, wingLists);
			
		 }catch(Exception ex){ 
			 
			 	ex.printStackTrace();		
			 	return new Status(500, ex.toString());	
		 }  
		
	} 
	
	@RequestMapping(value = "/getWingDetailsByWingId/{wingId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getWingDetailsByWingId(@PathVariable("wingId") int wingId) throws Exception{
		
		 try {			
			 	List<WingDetails>  wingDetails = wingService.getAllWingDetailsByWingId(wingId);
			 	return new Status(200, wingDetails);
			
		 }catch(Exception ex){ 	
			 	ex.printStackTrace();
			 	return new Status(500, ex.toString());	
			
		 } 		
	} 
	
	@RequestMapping(value = "/getPropertyDetailsByFlatNumber/{wingId}/{flatNumber}/{projectId}/{floorNumber}/{floorName}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getFlatDetailsByFlatNumber(@PathVariable("wingId") int wingId, @PathVariable("flatNumber") int flatNumber, @PathVariable("projectId") int projectId,
			@PathVariable("floorNumber") int floorNumber, @PathVariable("floorName") String floorName) throws Exception{
		
		 try {	
			 
			 	WingDetails  wingDetails  = wingService.getUnbookedPropertyDetails(wingId, flatNumber, projectId, floorNumber, floorName);
			 	return new Status(200, wingDetails);
			 	
		 }catch(Exception ex){ 	
			 	ex.printStackTrace();
			 	return new Status(500, ex.toString());				
		 }  
	} 
	
	@RequestMapping(value="/getWingById/{wingId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getWingById(@PathVariable("wingId") int wingId) throws IOException {	
		
		try{		
			 		
				Wing wing = wingService.getWingById(wingId);
				return new Status(200, wing);
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());		
		}
		
    }
	
	@RequestMapping(value="/updateWingById",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updateWingById(@RequestBody Wing wing){	
		
		try {	
		
				boolean isUpdated = wingService.updateWing(wing);
				if(isUpdated){
					
					logger.info("Wing Id "+wing.getWingTranId()+" Updated Successfully !");
					return new Status(200,"Wing Updated Successfully !",wing.getWingTranId());
					
				}else{
					logger.info("Wing Id "+wing.getWingTranId()+" Could not Updated!");
					return new Status(400,"Wing Could not Updated!",wing.getWingTranId());
				}
				
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString(),wing.getWingTranId());
		}
    }
	
	@RequestMapping(value="/addWing",method=RequestMethod.POST)
	public @ResponseBody  
	Status addWing(@RequestBody Wing wing){	
		try {	
				
				boolean isAdded = wingService.addWing(wing);
				if(isAdded){
					
					logger.info("Wing Added Successfully !");
					return new Status(200,"Wing Added Successfully !");
				}else{
					
					logger.info("Wing Could not Added!");
					return new Status(400,"Wing Could not Added!");
				}	
				
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString());
		}
    }
	
	@RequestMapping(value="/deleteWingById/{wingId}/{projectId}/{userId}",method=RequestMethod.DELETE)
	public @ResponseBody  
	Status deleteWingById(@PathVariable("wingId") int wingId, @PathVariable("projectId") int projectId, @PathVariable("userId") int userId){	
		try {
				
				boolean isDeleted = wingService.deleteWing(wingId, projectId, userId);
				if(isDeleted){
					
					logger.info("Wing Deleted Successfully !");
					return new Status(200,"Wing Deleted Successfully !");
				}else{
					
					logger.info("Wing Id "+wingId+" could not Deleted!");
					return new Status(400,"Wing could not Deleted !");
				}
				
				
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500,ex.toString());
		}
    }
	
	
}
