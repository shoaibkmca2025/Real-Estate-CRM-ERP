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

import com.bcs.model.PaymentDetails;
import com.bcs.model.Status;
import com.bcs.service.PaymentDetailsService;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.SendEMail;

@Controller
public class PaymentController {
	
	@Autowired
	PaymentDetailsService paymentService;
	
	@Autowired  
	SendEMail sendMail;
	
	final static Logger logger = LoggerFactory.getLogger(SettingsWebController.class);	
	
	@RequestMapping(value = "/addPaymentdetails", method = RequestMethod.POST) 
	 public @ResponseBody Status addUser(@RequestBody PaymentDetails paymentDetails, BindingResult result) throws Exception{ 
		
	  try{ 
		 
		  	paymentService.addPaymentDetails(paymentDetails);
		    logger.info("Payment Details added Successfully !");
		  	return new Status(200, "Payment Details added Successfully !");  

	  }catch(Exception e){  
	    	 
		    e.printStackTrace();  
		    return new Status(500, e.toString());  
	 }   
	}
	
	@RequestMapping(value="/getAllPaymentDetails",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllPaymentDetails() throws IOException {
		
		try{			
				List<PaymentDetails> paymentList  = paymentService.getAllPaymentDetails();
				return new Status(paymentList,200);
	
		}catch(Exception ex){
			
			   ex.printStackTrace();
			   return new Status(500, ex.toString());		
	    }
		 
    }
	
	@RequestMapping(value="/getPaymentDetailsByProjectId/{projectId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getPaymentDetailsByProjectId(@PathVariable("projectId") int projectId) throws IOException {
		 
		try{
				List<PaymentDetails> paymentList = paymentService.getPaymentDetailsByProjectId(projectId);
				return new Status(paymentList,200);
			
		}catch(Exception ex){
			
			   ex.printStackTrace();
			   return new Status(500, ex.toString());	
	    }
		 
    }
	
	@RequestMapping(value="/getPaymentDetailsByPaymentId/{paymentId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getUserDetailsByUserId(@PathVariable("paymentId") int paymentId) throws IOException {
			
		try{
				PaymentDetails paymentDetails = paymentService.getPaymentDetailsByPaymentId(paymentId);
				return new Status(200, paymentDetails);
	    	
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());	
	    }
    }
	
	@RequestMapping(value="/updatePaymentDetailsByPaymentId",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updatePaymentDetailsByPaymentId(@RequestBody PaymentDetails paymentDetails) throws IOException {
		
		try {	
			
				paymentService.updatePaymentDetailsByPaymentId(paymentDetails);	
				logger.info("Payment Details Updated Successfully of PaymentId :"+paymentDetails.getPaymentId());
				return new Status(200,"Payment Details Updated Successfully !",paymentDetails.getPaymentId());
				
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500,ex.toString());
			
		}
   }
	
	@RequestMapping(value = "/getAllPaymentDetailsNotification", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllPaymentDetailsNotification()throws Exception {	
		
		List<PaymentDetails> paymentNotificationList = null;  
		int paymentNotificationCount=0;
		 
		 try{  
			 	paymentNotificationList	= paymentService.getAllPaymentNotifications();
				paymentNotificationCount= paymentNotificationList.size();				
				return new Status(paymentNotificationList,paymentNotificationCount);
				
		 }catch(Exception ex){ 
			 
		     	ex.printStackTrace();
		     	return new Status(500, ex.toString());	
		 }
		 
	}
	
	@RequestMapping(value="/createInvoiceLetterPdf/{paymentId}/{companyId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status createInvoiceLetterPdf(@PathVariable("paymentId") int paymentId,@PathVariable("companyId") int companyId) throws IOException {
		
		try{
				String invoicePath	= paymentService.createInvoiceLetterPdf(paymentId,companyId);
				return new Status(200,invoicePath,ConstantsUtil.SERVER_IMG_LOCATION);
			
		}catch(Exception ex){
			   ex.printStackTrace();
			   return new Status(500, ex.toString());	
	    }
    }
	
	@RequestMapping(value="/sendInvoiceDetailsToClient",method=RequestMethod.POST)
	public @ResponseBody  
	Status sendProjectDetailsEmail(@RequestBody PaymentDetails paymentDetails) throws IOException {
	
		try{
			    String invoicePdf = ConstantsUtil.FILE_SAVE_LOCATION+paymentDetails.getCompanyName()+"/"+paymentDetails.getCompanyId()+"_"+paymentDetails.getCompanyName()+"_"+paymentDetails.getPaymentId()+".pdf";
			   
		        String to          		=  paymentDetails.getCompanyEmail();
				String subject    		=  "Invoice Details";
				String messageBody		=  "";
			
				boolean isMailSent = sendMail.sendInvoiceDetails(ConstantsUtil.FROM, to, subject, messageBody, invoicePdf);
				
				if(isMailSent==true){
					paymentService.updateSendInvoiceDate(paymentDetails);	
					logger.info("Invoice Details PDF Send Successfully");
					return new Status(200,"Invoice Details PDF Send Successfully");
				}else{
					logger.info("Error in Sending Mail Invoice Details PDF");
					return new Status(404,"Error in Sending Mail Invoice Details PDF ");
				}
				 
		}catch(Exception ex){
			ex.printStackTrace();
			return new Status(500, ex.toString());	
		}
	}

}
