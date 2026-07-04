package com.bcs.webservice;


import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcs.model.CloseEnquiry;
import com.bcs.model.EnquiryDetails;
import com.bcs.model.Property;
import com.bcs.model.PropertyType;
import com.bcs.model.Reference;
import com.bcs.model.Status;
import com.bcs.model.WingDetails;
import com.bcs.service.EnquiryService;
import com.bcs.service.UserService;
import com.bcs.service.UtilityService;
import com.bcs.utility.PushNotification;


@Controller
public class EnquiryWebController {
	
	final static Logger logger = LoggerFactory.getLogger(EnquiryWebController.class);	
	
	@Autowired
	EnquiryService enquiryService;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PushNotification pushNotification;
	
	
	@RequestMapping(value = "/getEnquiryListByProject/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getEnquryListByProject(@PathVariable("projectId") int projectId)throws Exception {		
		
		 try{
			 	List<EnquiryDetails> enquirydetailsList = enquiryService.getEnquiryDetailsByProjectId(projectId);
			 	return new Status(200, enquirydetailsList);
		 
		 }catch(Exception ex){  
			 	ex.printStackTrace(); 
			 	return new Status(500, ex.toString());
		 }
		
	}

	@RequestMapping(value = "/addEnquiryDetailsWS", method = RequestMethod.POST) 
	 public @ResponseBody Status addEnquiry(@RequestBody EnquiryDetails enquiryDetails, BindingResult result) { 
		

		  try{	 
		
				    boolean isRecordAdded = enquiryService.addEnquirydetails(enquiryDetails); 
				    
				    String[] arrayList = userService.getAllAdminList();	

				    
				    int enquiryId = utilityService.getRecentId(EnquiryDetails.class, "enquiryId");				   
				    
				    if(isRecordAdded){				    	
				    	if(enquiryDetails.getUserType() == 3){	
				    		
				    	 pushNotification.pushFCMNotification(arrayList, "New enquiry generated", "Tap here to see record", "sub-user", 1, enquiryId);
				    	}
				    	
				    	logger.info("Enquiry Details added Successfully");
					  	return new Status(200, "Enquiry Details added Successfully !");  	
				    }else{
				    	
				    	logger.info("Enquiry Details could not added");
					  	return new Status(400, "Enquiry Details could not added!");  	
				    }
				   
		  }catch(Exception ex) {  
		    	 
				    ex.printStackTrace();  
				    logger.info("Enquiry Details could not added");
				    return new Status(500,"Enquiry Details could not added due to "+ex.toString());  
				   
		 }   
	}

	@RequestMapping(value="/updateEnquiryDetailsWS",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updateEnquiryDetails(@RequestBody EnquiryDetails enquiryDetails) throws IOException {	
		
		try {
				boolean result = enquiryService.updateEnquiryDetails(enquiryDetails);
				if(result){
					logger.info("Enquiry Details Updated Successfully !");
					return new Status(200,"Enquiry Details Updated Successfully !",enquiryDetails.getEnquiryId());
				}else{
					logger.info("Enquiry Details could not Updated !");
					return new Status(400,"Enquiry Details could not Updated !",enquiryDetails.getEnquiryId());
				}
				
			
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500,ex.toString(),enquiryDetails.getEnquiryId());
		}
    }
	
	@RequestMapping(value = "/deleteEnquiryDetails/{enquiryId}/{projectId}/{userId}", method = RequestMethod.DELETE)  
	public @ResponseBody Status deleteEnquiryDetails(@PathVariable("enquiryId") int enquiryId, @PathVariable("projectId") int projectId,  @PathVariable("userId") int userId) {
		
		 try {
					boolean result = enquiryService.deleteEnquiryDetails(enquiryId, userId, projectId);
					if(result){
						logger.info("Enquiry Details "+enquiryId+ " Deleted Successfuly ");					
						return new Status(enquiryId, 200," Enquiry Details Deleted successfully.");
					}else{
						logger.info("Enquiry Details Could not Deleted ");					
						return new Status(enquiryId, 400," Enquiry Details Could not Deleted ");
					}
			
		 }catch(Exception ex){		    	 
			 
			       logger.error("Exceptions while Enquiry Details deletion : "+ex.toString());	
			       return new Status(500,"NOT_FOUND Or Already Deleted"); 
		 }	
	}

	
	@RequestMapping(value="/getPropertyArea/{projectId}/{propertytypeId}/{propertyId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getPropertyAreaByPropertyId(@PathVariable("projectId") int projectId, @PathVariable("propertytypeId") int propertyTypeId, 
			@PathVariable("propertyId") int propertyId) throws IOException {	
				
		List<WingDetails> propertyArealist = null;
		try{
			 	propertyArealist = enquiryService.getPropertyAreaByPropertyId(projectId, propertyTypeId, propertyId);	
			 	return new Status(200,propertyArealist); 
			 	
		}catch(Exception e){
				 e.printStackTrace();
				 return new Status(500,e.toString()); 
	    }		
    }

	
	@RequestMapping(value = "/addEnquiryDetailsLocalToServer", method = RequestMethod.POST) 
	 public @ResponseBody Status addEnquiryDetailsLocalToServer(@RequestBody List<EnquiryDetails> enquiryDetailsList, BindingResult result) { 

	  try { 
			   for (int i = 0; i < enquiryDetailsList.size(); i++) {
			    	
				   
				  	enquiryService.addEnquirydetails(enquiryDetailsList.get(i));
				}
			  	return new Status(1, "Enquiry Details added Successfully !");  
			 
		   
	     }catch(Exception e){  
	    	 
	    e.printStackTrace(); 	  
	   return new Status(0, e.toString());  
	 }   
	  
	}
	
	@RequestMapping(value="/getAllPropertyDetails",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllPropertyDetails() throws IOException {	
			
		try{
				List<Property> propertyDetailsList = utilityService.getAllProperty();
				return new Status(200,propertyDetailsList); 
    		
		}catch(Exception e){
			
				e.printStackTrace();
				return new Status(500, e.toString());  				
	    }
    }
	
	@RequestMapping(value="/getAllPropertyTypeDetails",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllPropertyTypeDetails() throws IOException {	
		
		try{
			
				List<PropertyType> propertyTypeDetailsList = utilityService.getAllPropertyType();
				return new Status(200,propertyTypeDetailsList);  
				
		}catch(Exception e){
			
				e.printStackTrace();
				return new Status(500, e.toString());  
	    }
		
    }
	
	@RequestMapping(value="/getAllReferenceDetails",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllReferenceDetails() throws IOException {	
		
		try{
				List<Reference> referenceDetailsList = utilityService.getAllReferences();
				return new Status(200,referenceDetailsList);
    		
		}catch(Exception e){
				e.printStackTrace();
				return new Status(500, e.toString());
	    }
		
    }
	
	
	@RequestMapping(value="/getPropertyByPropertyTypeId/{propertyTypeId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getPropertyByPropertyTypeId(@PathVariable("propertyTypeId") int propertyTypeId) throws IOException {	
		
		try{
				List<Property> propertyList = utilityService.getPropertyByPropertyType(propertyTypeId);
				return new Status(200,propertyList);
    		
		}catch(Exception e){
			
				e.printStackTrace();
				return new Status(500, e.toString());
	    }
		
    }
	
	
	@RequestMapping(value="/getEnquiryDetailsByEnquiryId/{enquiryId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getEnquiryDetailsByEnquiryId(@PathVariable("enquiryId") int enquiryId) throws IOException {
			
		try{
				EnquiryDetails enquiryDetails = enquiryService.getEnquiryDetailsById(enquiryId);
				return new Status(200, enquiryDetails);
	    	
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());  
	    }
	
    }
	
	/*
	@RequestMapping(value = "/getPropertyUnitByProjectIdAndPropertyTypeId/{projectId}/{propertyTypeId}", method = RequestMethod.GET)  
	public @ResponseBody  
	List<WingDetails> getPropertyUnitByProjectIdAndPropertyTypeId(@PathVariable("projectId") int projectId, @PathVariable("propertyTypeId") int propertyTypeId)throws Exception {		
		 List<WingDetails> propertyList = null;  
		 try {  
			 
			 propertyList = enquiryService.getPropertyUnitByProjectIdAndPropertyTypeId(projectId, propertyTypeId);
		 
		     } catch (Exception e) {  
		     e.printStackTrace();  
		  }
		
		
		 return propertyList;  
	}*/
	
	
	@RequestMapping(value = "/getAllClosedEnquiryByProjectId/{projectId}/{followupStatus}/{companyId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllClosedEnquiry(@PathVariable("projectId") int projectId, @PathVariable("followupStatus") int followupStatus,
			 @PathVariable("companyId") int companyId)throws Exception {	
		 
		 try{  

			 	List<CloseEnquiry> enquiryClosedList = enquiryService.getAllClosedEnquiryByProjectId(projectId, followupStatus,companyId);

			 	return new Status(200, enquiryClosedList);
		 
		 }catch(Exception ex){  
		     	ex.printStackTrace();
		     	return new Status(500, ex.toString());			
		 }
		
	}
	
	@RequestMapping(value = "/genarateClosedEnquiryExcelReport/{projectId}/{followupStatus}/{companyId}", method = RequestMethod.GET)  
	public @ResponseBody  
	void genarateClosedEnquiryExcelReport(@PathVariable("projectId") int projectId, @PathVariable("followupStatus") int followupStatus,
			 @PathVariable("companyId") int companyId,HttpServletResponse response)throws Exception {		
		 
		 try{  
			 
			 	enquiryService.genarateClosedEnquiryExcelReport(projectId, followupStatus,companyId,response);
			
		 }catch(Exception ex){  
		     	ex.printStackTrace();
		 }
		
	}

	
/*	@PostMapping("closedEnquiry")	
	public @ResponseBody Status closedEnquiry(@RequestBody CloseEnquiry closeEnquiry) {
		
		 try {	
			    int   enquiryId        = closeEnquiry.getEnquiryId();
			  	String reason          = closeEnquiry.getReason();
			  	System.out.println("enquiryId"+enquiryId);
			  	System.out.println("reason"+reason);
			  	enquiryService.addClosedEnquiry(closeEnquiry);
				logger.info("closed enquiry "+enquiryId+" added Successfuly");
				
				return new Status(enquiryId, 200,"closed enquiry added Successfully !");
			  
		 } catch (Exception ex) {		    	 
			       logger.error("Exceptions while closed enquiry add : "+ex.toString());			       
		 }	
		 return new Status(404, "closed enquiry insert Failed");
	}*/
	
	@PostMapping("closedEnquiry/{projectId}")	
	public @ResponseBody Status closedEnquiry(@RequestBody CloseEnquiry closeEnquiry,@PathVariable("projectId") int projectId) {
		
			try {		
						int   enquiryId        = closeEnquiry.getEnquiryId();
						int   userId           = closeEnquiry.getUserId();
						
						closeEnquiry.setUserId(userId);
						closeEnquiry.setProjectId(projectId);
					    boolean result = enquiryService.addClosedEnquiry(closeEnquiry);
					    
					    String[] arrayList    = userService.getAllAdminList();					    
					    int id                = closeEnquiry.getEnquiryId();	
					    					    
					    if(result){
					    	
					    	if(closeEnquiry.getUserType() == 3){
					    		
						    	 pushNotification.pushFCMNotification(arrayList, "Enquiry closed", "Tap here to see record", "sub-user", 2, id);
						    	 
						    	}
					    	
					    	logger.info("Enquiry ("+enquiryId+") closed Successfuly");
							return new Status(enquiryId, 200,"enquiry closed Successfully !");
							
					    }else{
					    	
					    	logger.info("Enquiry closed Failed !");
							return new Status(enquiryId, 400,"Enquiry closed Failed !");
					    }
					    
		    }catch(Exception ex) {	
		    	
			       ex.printStackTrace();
			       logger.error("Exceptions while closed enquiry : "+ex.toString());
			       return new Status(500, "Exceptions while closed enquiry : "+ex.toString());
		 }	
	}
	
	@PostMapping("addRemark")	
	public @ResponseBody Status addRemark(@RequestBody EnquiryDetails enquiryDetails) {
		
			try {		
						int   enquiryId        = enquiryDetails.getEnquiryId();
						
					    boolean result = enquiryService.addRemark(enquiryDetails);
					    
					    if(result){
					    	
					    	logger.info("Remark of enquiry id ("+enquiryId+") added Successfuly");
							return new Status(200,"Remark added Successfully !");
							
					    }else{
					    	
					    	logger.info("Remark could not added");
							return new Status(400,"Remark could not added ");
					    }
					    
		    }catch(Exception ex) {	
		    	
			       ex.printStackTrace();
			       logger.error("Exceptions while add remark : "+ex.toString());
			       return new Status(500, "Exceptions while add remark : "+ex.toString());
		 }	
		
	}
	
}


