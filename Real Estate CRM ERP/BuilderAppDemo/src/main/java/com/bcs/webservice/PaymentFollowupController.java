package com.bcs.webservice;

import java.io.IOException;
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

import com.bcs.model.Client;
import com.bcs.model.PaymentFollowup;
import com.bcs.model.Status;
import com.bcs.service.PaymentFollowupService;
import com.bcs.service.UserService;
import com.bcs.service.UtilityService;
import com.bcs.utility.PushNotification;

@Controller
public class PaymentFollowupController {

	
	@Autowired
	PaymentFollowupService paymentfollowupService;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PushNotification pushNotification;
	
	final static Logger logger = LoggerFactory.getLogger(PaymentFollowupController.class);	
	
	@RequestMapping(value = "/addPaymentFollowupDetails", method = RequestMethod.POST) 
	 public @ResponseBody Status addFollowupDetails(@RequestBody PaymentFollowup paymentFollowup, BindingResult result) { 
		
	     try {			
	    	  	  boolean isRecordAdded = paymentfollowupService.addPaymentFollowupDetails(paymentFollowup);
	    	  	  
	    	  	  String[] arrayList    = userService.getAllAdminList();					    
				    int id              = paymentFollowup.getDisbursementId();		
	    	  	  if(isRecordAdded){
	    	  		if(paymentFollowup.getUserType() == 3){
						
				    	 pushNotification.pushFCMNotification(arrayList, "Payment followup taken", "Tap here to see record", "sub-user", 5, id);
				    	 
						}
	    	  		  
	    	  		  logger.info("Payment Followup Details added Successfully !");
	    	  		  return new Status(200, "Payment Followup Details added Successfully !");  
	    	  		  
	    	  	  }else{
	    	  		  
	    	  		  logger.info("Payment Followup Details Could not added!");
	    	  		  return new Status(400, "Payment Followup Details Could not added!");  
	    	  	  }
	    	  	  
	     }catch(Exception e){  
	    	 
		    	 e.printStackTrace();  
		    	 return new Status(500, e.toString());  
	     }   
	}
	
	/*@RequestMapping(value = "/getAllPaymentFollowupDetailByDisbursementId/{disbursementId}/{companyId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getFollowupDetailsByProjectId(@PathVariable("disbursementId") int disbursementId, @PathVariable("companyId") int companyId)throws Exception {		
		 
		 try{  
			 	 
			 	 List<PaymentFollowup> followupdetailsList = paymentfollowupService.getPaymentFollowupDetailsByDisbursementId(disbursementId);	
			 	 int paymentDueDateDuration = utilityService.getSettingsByCompanyId(companyId).getPaymentDuedateDuration();	
			     return new Status(200, followupdetailsList, paymentDueDateDuration);  
		 
		 }catch(Exception ex) { 
			 
		     	ex.printStackTrace();  
		     	return new Status(500, ex.toString());  
		 }
		
	}*/
	
	@RequestMapping(value="/getTotalAndTodaysPaymentFollowUpDetails/{userId}/{userType}/{companyId}/{projectId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getTodaysReceivedPaymentDetails(@PathVariable("userId") int userId, @PathVariable("userType") int userType, 
			@PathVariable("companyId") int companyId, @PathVariable("projectId") int projectId) throws IOException {	
		try{
				
				 List<Client> totalPaymentFollowup  =  paymentfollowupService.getTotalAndTodaysFollowup(userId, userType, companyId, projectId);
				
				 return new Status(200, totalPaymentFollowup);	
				
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());	
		}
	}
	
	@RequestMapping(value="/getAllPaymentFollowupDetailByDisbursementId/{disbursementId}/{companyId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status  getClientDetailsById(@PathVariable("disbursementId") int disbursementId, @PathVariable("companyId") int companyId) throws IOException {	
		
		try{
			PaymentFollowup payment     = paymentfollowupService.getClientDetailsByDisbursementId(disbursementId);
			 int paymentDueDateDuration = utilityService.getSettingsByCompanyId(companyId).getPaymentDuedateDuration();	
			 return new Status(200, payment, paymentDueDateDuration);
	    	
		}catch(Exception e){
			
		   e.printStackTrace();
		   return new Status(500, e.toString());
	    }
		
    }
}
