package com.bcs.webservice;

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

import com.bcs.model.SMS;
import com.bcs.model.SMSCreditDetails;
import com.bcs.model.Status;
import com.bcs.service.SMSService;

@Controller
public class SMSController {
	
	@Autowired
	SMSService smsService;
	
	final static Logger logger = LoggerFactory.getLogger(SMSController.class);	
	
	@RequestMapping(value="/sendSMS",method=RequestMethod.POST)
	public @ResponseBody  
	Status sendSMS(@RequestBody SMS sms){	
		try {	
			
			if(sms.getIsScheduledSMS()){
				boolean isAdded = smsService.scheduleSms(sms);
				
				if(isAdded){
					
					SMSCreditDetails availableCreditDetails = smsService.getAvailableSMSCreditsByCompanyId(sms.getCompanyId());
					logger.info("Message schedule Successfully !");
					return new Status(200,"Message schedule Successfully !",availableCreditDetails,"");
					
				}else{
					
					logger.info("Message schedule failed!");
					return new Status(400,"Message schedule failed!");
				}	
			}else{
				
				boolean isAdded = smsService.sendSms(sms);
				if(isAdded){
					
					SMSCreditDetails availableCreditDetails = smsService.getAvailableSMSCreditsByCompanyId(sms.getCompanyId());
					logger.info("Message sent Successfully !");
					return new Status(200,"Message sent Successfully !",availableCreditDetails,"");
					
				}else{
					
					logger.info("Message sending failed!");
					return new Status(400,"Message sending failed!");
				}	
			}
				
				
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString());
		}
    }
	
	@RequestMapping(value = "/getAllSMSListByDate", method = RequestMethod.POST)  
	public @ResponseBody  
	Status getAllSMSListByDate(@RequestBody SMS sms) throws Exception{
		
		 try{			
			 List<SMS>  smsLists  = smsService.getAllSMS(sms.getCompanyId(), sms.getDate());
			 SMSCreditDetails availableCreditDetails = smsService.getAvailableSMSCreditsByCompanyId(sms.getCompanyId());
			 return new Status(200, smsLists, availableCreditDetails);
			
		 }catch(Exception ex){ 	
			 ex.printStackTrace();
			 return new Status(500, ex.toString());  
			
		 }  
	} 

	@RequestMapping(value = "/getAllMobileNumbersByType/{userId}/{type}/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllMobileNumbersByType(@PathVariable("userId") int userId, @PathVariable("type") int type, @PathVariable("projectId") int projectId) throws Exception{
		
		 String  mobileNos = null;		
		 try{			
			
			// System.out.println("userId: "+userId+" *** type: "+type+" ***projectId: "+projectId);
			 mobileNos = smsService.getAllMobileNosByType(userId, type, projectId);
			 return new Status(200, mobileNos);
			
		 }catch(Exception ex){ 	
			 ex.printStackTrace();
			 return new Status(500, ex.toString());  
			
		 }  
	} 
	
	@RequestMapping(value = "/getAllScheduledSMSListByCompanyId/{companyId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllScheduledSMSListByUserId(@PathVariable("companyId") int companyId) throws Exception{
		
		List<SMS>  smsLists = null;
		 try{			
			 smsLists = smsService.getAllScheduledSMSListByCompanyId(companyId);
			 return new Status(200, smsLists);
			
		 }catch(Exception ex){ 	
			 ex.printStackTrace();
			 return new Status(500, ex.toString());  
			
		 }  
	} 

	@RequestMapping(value="/addSmsCreditDetails",method=RequestMethod.POST)
	public @ResponseBody  
	Status addSmsCreditDetails(@RequestBody SMSCreditDetails smsCreditDetails){	
		try {	
			
				boolean isAdded = smsService.addSmsCreditDetails(smsCreditDetails);
				if(isAdded){
					
					logger.info("SMS credits added Successfully !");
					return new Status(200,"SMS credits added Successfully !");
					
				}else{
					
					logger.info("Message schedule failed!");
					return new Status(400,"Message schedule failed!");
				}	
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString());
		}
    }
	
	@RequestMapping(value = "/getAllSmsCreditDetailsByCompanyId/{companyId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllSmsCreditDetailsByCompanyId(@PathVariable("companyId") int companyId) throws Exception{
		
		List<SMSCreditDetails> smsCreditDetails = null;		
		 try{			
			
			 smsCreditDetails = smsService.getAllSmsCreditDetailsByCompanyId(companyId);
			 return new Status(200, smsCreditDetails);
			
		 }catch(Exception ex){ 	
			 ex.printStackTrace();
			 return new Status(500, ex.toString());  
			
		 }  
	} 
	

}
