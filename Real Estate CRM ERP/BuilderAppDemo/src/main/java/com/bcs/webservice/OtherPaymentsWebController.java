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

import com.bcs.model.OtherPayments;
import com.bcs.model.Status;
import com.bcs.service.OtherPaymentsService;

@Controller
public class OtherPaymentsWebController {
	
	final static Logger logger = LoggerFactory.getLogger(OtherPaymentsWebController.class);	
	
	@Autowired
	OtherPaymentsService otherPaymentsService;
	
	
	@RequestMapping(value = "/getOtherPaymentsListByClientId/{clientId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getOtherPaymentsListByClientId(@PathVariable("clientId") int clientId) throws Exception{
		
		 try{			
				 List<OtherPayments>  otherPaymentsList = otherPaymentsService.getOtherPaymentsListByClientId(clientId);
				 return new Status(200, otherPaymentsList);
			
		 }catch(Exception ex){ 	
			 
			 	ex.printStackTrace();	
			 	return new Status(500, ex.toString());		
			
		 }
	} 
	
	@RequestMapping(value = "/getOtherPaymentDetailsById/{otherPaymentId}", method = RequestMethod.GET) 
	public @ResponseBody  
	Status getBankDetailsById(@PathVariable("otherPaymentId") int otherPaymentId) throws IOException {	
			
		try{
				OtherPayments otherPaymentDetails = otherPaymentsService.getOtherPaymentsById(otherPaymentId);
				return new Status(200, otherPaymentDetails);		
    		
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());		
	    }		
	}
	
		
	@RequestMapping(value = "/addOtherPayments", method = RequestMethod.POST) 
	 public @ResponseBody Status addOtherPayments(@RequestBody OtherPayments otherPayment, BindingResult result) throws Exception{ 
		
	  try { 		  
		  	
				boolean isRecordAdded = otherPaymentsService.addOtherPayments(otherPayment);
				if(isRecordAdded){
					
					logger.info("Other Payment Details added Successfully ");
				  	return new Status(200, "Other Payment Details added Successfully !"); 
				}else{
					
					logger.info("Other Payment Details could not added!");
				  	return new Status(400, "Other Payment Details could not added!"); 
				}
				
			
	     }catch(Exception e){  
	    	 
			    e.printStackTrace();  
			    return new Status(500, e.toString());  
	     }   
	}
	
	@RequestMapping(value="/updateOtherPayments",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updateOtherPayments(@RequestBody OtherPayments otherPayment) throws IOException {	
		
		try {		  		 	
	  		 	boolean isRecordUpdated = otherPaymentsService.updateOtherPayments(otherPayment);
	  		 	if(isRecordUpdated){
	  		 		
	  		 		logger.info("Other Payment Details Updated Successfully :");
					return new Status(200,"Other Payment Details Updated Successfully !");							
	  		 	}else{
	  		 		
	  		 		logger.info("Other Payment Details could not Updated!");
					return new Status(400,"Other Payment Details could not Updated!");		
	  		 	}
		}catch(Exception ex){			
					ex.printStackTrace();
					return new Status(500,ex.toString());			
		}
    }
	
	@RequestMapping(value="/deleteOtherPaymentsById/{otherPaymentId}",method=RequestMethod.DELETE)
	public @ResponseBody  
	Status deleteAmenitiesById(@PathVariable("otherPaymentId") int otherPaymentId) throws IOException {	
		try {
			
				boolean isRecordDeleted = otherPaymentsService.deleteOtherPayments(otherPaymentId);
				if(isRecordDeleted){
					
					logger.info("Other Payment Details Deleted Successfully !");	  		 	
					return new Status(200,"Other Payment Details Deleted Successfully !");
					
				}else{
					
					logger.info("Other Payment Details Could not Deleted !");	  		 	
					return new Status(400,"Other Payment Details Could not Deleted !");
				}
		
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString());
		}
    }

}
