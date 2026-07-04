package com.bcs.webservice;

import java.io.File;
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
import com.bcs.model.Disbursement;
import com.bcs.model.OtherPayments;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.ProjectDisbursement;
import com.bcs.model.Settings;
import com.bcs.model.Status;
import com.bcs.service.BankDetailsService;
import com.bcs.service.DisbursementService;
import com.bcs.service.OtherPaymentsService;
import com.bcs.service.UserService;
import com.bcs.service.UtilityService;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;
import com.bcs.utility.PushNotification;
import com.bcs.utility.SendEMail;


@Controller
public class DisbursementController {

	final static Logger logger = LoggerFactory.getLogger(DisbursementController.class);	
	
	@Autowired
	DisbursementService disbursementService;
	
	@Autowired
	BankDetailsService bankDetailsService;
	
	@Autowired
	OtherPaymentsService otherPayment;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
	SendEMail sendMail;	
	
	@Autowired
	UserService userService;
	
	@Autowired
	PushNotification pushNotification;
	
	/*
	 * Get all Project Disbusement List By Project Id
	 */
	@RequestMapping(value = "/getProjectDisbursementListByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getProjectDisbursementListByProjectId(@PathVariable("projectId") int projectId) throws Exception{
		
		 try{			
			 	List<ProjectDisbursement>  projectDisbursementList = disbursementService.getProjectDisbursementListByProjectId(projectId);
			 	return new Status(200, projectDisbursementList);
			
		 }catch(Exception ex){ 	
			 
			 	ex.printStackTrace();
			 	return new Status(500, ex.toString()); 			
		 }  	
	} 

	/*
	 * Update Project Disbursement Details
	 */
	@RequestMapping(value = "/updateDisbursementDetails", method = RequestMethod.POST) 
	 public @ResponseBody Status updateProjectDisbursementDetails(@RequestBody ProjectDetails projectDetails, BindingResult result) { 
		
		 try{ 
				 disbursementService.updateProjectDisbursementDetails(projectDetails);
			     return new Status(200, "Disbursement Details Updated Successfully"); 	 
					
	     }catch(Exception e){  
	    	 
			    e.printStackTrace();  
			    return new Status(500, e.toString());  
		 }   
	}	
	
	@RequestMapping(value="/payDisbursementAmount",method=RequestMethod.POST)
	public @ResponseBody  
	Status payDisbursementAmount(@RequestBody Disbursement disbursement, BindingResult result) throws IOException {	
	
		try{			
			
			
				disbursementService.payDisbursementAmountByDisbursementId(disbursement);
				
				  String[] arrayList = userService.getAllAdminList();
				  
				  int id = disbursement.getClientId();
				  
				  if(disbursement.getUserType() == 3){
									
			    	 pushNotification.pushFCMNotification(arrayList, "Builder Dashboard", "Payment Received", "sub-user", 3, id);
			    	
				  }
			    	 
				logger.info("Disbursement amount paid successfully");
				return new Status(200,"Disbursement amount paid successfully");		 
				
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500, ex.toString());	
		}
		
    }
	
	@RequestMapping(value="/updateCompletionDateByProjectDisbursementId",method=RequestMethod.POST)
	public @ResponseBody  
	Status updateCompletedDateByProjectDisbursementId(@RequestBody ProjectDisbursement projectDisbursement) throws IOException {	
	
		try{			
				int projectDisbursementId  = projectDisbursement.getProjectDisbursementId();
				
				disbursementService.updateCompletionDateByProjectDisbursementId(projectDisbursement);
				logger.info("Completion date updated successfully: "+projectDisbursementId);
				return new Status(200 , "Completion date updated successfully");		
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());	
		}
    }
	
	@RequestMapping(value="/getPaymentDueDateNotifications/{userId}/{userType}/{companyId}/{projectId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getPaymentDueDateNotifications(@PathVariable("userId") int userId, @PathVariable("userType") int userType, 
			@PathVariable("companyId") int companyId, @PathVariable("projectId") int projectId) throws IOException {
	
		int paymentNotificationCount=0;
		try{
			
				 Settings settings = utilityService.getSettingsByCompanyId(companyId);
				 int paymentNotifDuration = settings.getPaymentNotificationDuration();
				
				 List<Client> dueDateNotificationList  =  disbursementService.getPaymentDueDateNotifications(userId, userType, paymentNotifDuration,companyId, projectId);
				 paymentNotificationCount =  dueDateNotificationList.size();
				 return new Status(dueDateNotificationList, 200, paymentNotificationCount);	
				
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());	
		}
	}
	
	@RequestMapping(value="/createDemandLetter",method=RequestMethod.POST)
	public @ResponseBody  
	Status createDemandLetter(@RequestBody Disbursement disbursement) throws IOException {	
	
		try{
			
				int clientId       = disbursement.getClientId();
				int disbursementId = disbursement.getDisbursementId();
			
				String demandLetterPath  =  disbursementService.createDemandLetterPdf(clientId, disbursementId);
					
				logger.info("Demand letter generated successfully: "+demandLetterPath);
				return new Status(200, demandLetterPath);	
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());	
		}
		
	}
	
	@RequestMapping(value="/DeleteDemandLetter",method=RequestMethod.POST)
	public @ResponseBody  
	Status DeleteDemandLetter(@RequestBody Disbursement disbursement) throws IOException {	
	
		try{	
				String demandLetterPath = disbursement.getDemandLetterPdfPath();
				
				File file             =  new File(ConstantsUtil.FILE_SAVE_LOCATION+demandLetterPath);		
				file.delete();
				
				return new Status(200, "Demand Letter Creation Date and path updated successfully");	
				
			
		}catch(Exception ex){
			ex.printStackTrace();
			return new Status(500, ex.toString());	
		}
		
	}
	
	@RequestMapping(value="/sendDemandLetterToClient",method=RequestMethod.POST)
	public @ResponseBody  
	Status sendDemandLetter(@RequestBody Disbursement disbursement) throws IOException {	
	
		try{
						
					 	String pdfSendDate 	   = DateTimeUtil.getSysDate();
					 	int companyId          = disbursement.getCompanyId();
					 
					 	String From             =  utilityService.getSettingsByCompanyId(companyId).getSendersEmail();
				        String to          		=  disbursement.getClientEmail();
						String demandLetterName = ConstantsUtil.FILE_SAVE_LOCATION+disbursement.getDemandLetterPdfPath();
						int clientId            = disbursement.getClientId();
						String recipientEmailsCc  = disbursement.getRecipientEmailsCc();
						String recipientEmailsBcc = disbursement.getRecipientEmailsBcc();
						
						
						logger.info("demand letter path: "+demandLetterName);
						if(demandLetterName != null){
							
							boolean isMailSent = sendMail.sendDemandLetter(From, to, demandLetterName, clientId, recipientEmailsCc,recipientEmailsBcc);
							logger.info("isMailSent: "+isMailSent);
							
							String[] arrayList    = userService.getAllAdminList();					    
						    int id                = disbursement.getClientId();
							
							if(isMailSent){
								
								if(disbursement.getUserType() == 3){
									
							    	 pushNotification.pushFCMNotification(arrayList, "Demand letter send", "Tap here to see record", "sub-user", 6, id);
							    	 
									}
								
								logger.info("Demand letter sent successfully ");
								
								disbursement.setSendPdfDate(pdfSendDate);
								disbursementService.updatePdfSendDate(disbursement);
								
								logger.info("Demand Letter send date updated successfully ");
								return new Status(200,"Demand letter sent successfully");
							}
						}
					return new Status(404,"Demand letter sending failed ");
				
	
		}catch(Exception ex){
			ex.printStackTrace();
			return new Status(500, ex.toString());	
		}
		
	
	}
	
	@RequestMapping(value="/getPendingDemandLetterNotifications/{userId}/{userType}/{companyId}/{projectId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getPendingDemandLetterNotifications(@PathVariable("userId") int userId, @PathVariable("userType") int userType,
			@PathVariable("companyId") int companyId, @PathVariable("projectId") int projectId) throws IOException {	
	
		int notificationCount = 0;
		try{
		
			List<Client> notificationList  =  disbursementService.getPendingDemandLetterNotifications(userId, userType, companyId, projectId);
			notificationCount =  notificationList.size();
			return new Status(notificationList, 200, notificationCount);		
			
		}catch(Exception ex){
			ex.printStackTrace();
			return new Status(500, ex.toString());	
		}
	}
	
	/*
	 * Get Disbusement List By Client Tran Id
	 */
	@RequestMapping(value = "/getDisbursementListByClientId/{clientTranId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getDisbursementListByClientId(@PathVariable("clientTranId") int clientTranId) throws Exception{
		
		 try{
			 
			 List<Disbursement>  disbursementList = disbursementService.getDisbursementDetailsListByClientId(clientTranId);
			 return new Status(200, disbursementList);
			
		 }catch(Exception ex){ 	
			 
			 ex.printStackTrace();
			 return new Status(500, ex.toString());				
		 }  
	} 
	
	
	@RequestMapping(value="/getTodaysReceivedPaymentDetails/{userId}/{userType}/{companyId}/{projectId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getTodaysReceivedPaymentDetails(@PathVariable("userId") int userId, @PathVariable("userType") int userType, 
			@PathVariable("companyId") int companyId, @PathVariable("projectId") int projectId) throws IOException {
	
		try{
			
				 List<Client> recievedPaymentList  =  disbursementService.getTodaysReceivedPaymentClientsList(userId, userType, companyId, projectId);				 
			    	
				
				 return new Status(200, recievedPaymentList);	
				
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());	
		}
	}
	
	@RequestMapping(value="/getAllPaidDisbursementDetailsByClientId/{clientId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllPaidDisbursementDetailsByClientId(@PathVariable("clientId") int clientId) throws IOException {
	
		try{		
			List<PaidDisbursementDetails> PaidDisbursementList =  disbursementService.getAllPaidDisbursementDetailsByClientId(clientId);	
			List<OtherPayments> otherPaymentList               = otherPayment.getOtherPaymentsListByClientId(clientId);
				 return new Status(PaidDisbursementList, otherPaymentList, 200);	
				
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());	
		}
	}
}
