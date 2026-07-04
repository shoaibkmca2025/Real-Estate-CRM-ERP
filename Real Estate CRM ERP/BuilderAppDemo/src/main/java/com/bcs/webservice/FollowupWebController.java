package com.bcs.webservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcs.model.EnquiryDetails;
import com.bcs.model.FollowupDetails;
import com.bcs.model.Status;
import com.bcs.service.FollowupService;
import com.bcs.service.UserService;
import com.bcs.service.UtilityService;
import com.bcs.utility.PushNotification;

@Controller
public class FollowupWebController {
	
	final static Logger logger = LoggerFactory.getLogger(FollowupWebController.class);	

	@Autowired
	FollowupService followupService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PushNotification pushNotification;
	
	@Autowired
	UtilityService utilityService;
	
	@RequestMapping(value = "/getFollowupDetailsListByEnquiryId/{enquiryId}", method = RequestMethod.GET)  
	public @ResponseBody
	Status getFollowupDetailByEnquiryId(@PathVariable("enquiryId") int enquiryId)throws Exception {		
		 
		 try{  
			 	List<FollowupDetails> followupdetailsList = followupService.getFollowupDetailsByEnquiryId(enquiryId);
			 	return new Status(200, followupdetailsList);
		 
		 }catch(Exception ex){ 
			 
		     	ex.printStackTrace();
		     	return new Status(500, ex.toString());	
		 }
		
	}
	
	@RequestMapping(value = "/getAllFollowupDetailByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getFollowupDetailsByProjectId(@PathVariable("projectId") int projectId)throws Exception {		
		
		 try{  
			 	
		 		List<EnquiryDetails> followupdetailsList = followupService.getFollowupDetailsByProjectId(projectId);
		 		return new Status(200, followupdetailsList);			    
		 
		 }catch(Exception ex){  
			 
	     		ex.printStackTrace(); 
	     		return new Status(500, ex.toString());	
		 }
	}
		
	
	@RequestMapping(value = "/getAllFollowupDetailsNotification/{userId}/{userType}/{companyId}/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllFollowupDetails(@PathVariable("userId") int userId, @PathVariable("userType") int userType, 
			@PathVariable("companyId") int companyId, @PathVariable("projectId") int projectId)throws Exception {	
		
		 List<EnquiryDetails> followupDetailsNotificationList = null;  
		 int followupdetailsCount=0;
		 
		 try {  
			 		followupDetailsNotificationList = followupService.getAllFollowupDetails(userId,userType,companyId, projectId);
			 		followupdetailsCount  			= followupDetailsNotificationList.size();
			 		//followupdetailsCount =  followupService.getFollowupNotificationCount(userId,userType);
			 		//System.out.println("followupdetailsCount :"+followupdetailsCount);
		 
		     } catch (Exception e) {  
		     e.printStackTrace();  
		  }
		
		 return new Status(followupDetailsNotificationList,followupdetailsCount);
	}
	
	/*@RequestMapping(value = "/getAllFollowupNotificationCount", method = RequestMethod.GET)  
	public @ResponseBody  
	BigInteger getAllFollowupNotificationCount()throws Exception {		
		BigInteger followupDetailsCount = null;  
		 try {  
			 followupDetailsCount =  followupService.getFollowupNotificationCount();
			 System.out.println("followupDetailsCount :"+followupDetailsCount);
		 
		     } catch (Exception e) {  
		     e.printStackTrace();  
		  }
		 return followupDetailsCount ;  
	}
	*/
	 @RequestMapping(value = "/addFollowupDetails", method = RequestMethod.POST) 
	 public @ResponseBody Status addFollowupDetails(@RequestBody FollowupDetails followupDetails, BindingResult result) { 
		
	      try { 
			
				  followupService.addFollowupDetails(followupDetails);
				  
				  String[] arrayList = userService.getAllAdminList();	
				  
				   int enquiryId = followupDetails.getEnquiryId();
				 
					if(followupDetails.getUserType() == 3){
						
			    	 pushNotification.pushFCMNotification(arrayList, "Followup taken", "Tap here to see record", "sub-user", 4, enquiryId);
			    	 
					}
			    	 
				  return new Status(200, "Followup Details added Successfully !");  
			
	     }catch(Exception e){  
	    	 
		    	 e.printStackTrace();  
		    	 return new Status(500, e.toString());  
	     }   
	}
	
}
