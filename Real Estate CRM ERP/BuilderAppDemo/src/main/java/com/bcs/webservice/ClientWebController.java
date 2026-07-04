package com.bcs.webservice;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.Client;
import com.bcs.model.ClientDocuments;
import com.bcs.model.Documents;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.model.Status;
import com.bcs.service.ClientService;
import com.bcs.service.UserService;
import com.bcs.service.UtilityService;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.PushNotification;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class ClientWebController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PushNotification pushNotification;
	
	@Autowired
	private ObjectMapper mapper;	
	
	final static Logger logger = LoggerFactory.getLogger(ClientWebController.class);		
	
	@RequestMapping(value = "/getAllClientsByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllClientsByProjectId(@PathVariable("projectId") int projectId)throws Exception {		
		  
		 try{  
			 
			 List<Client> clientList = clientService.getAllClientsByProjectId(projectId);
			 return new Status(200, clientList);
		 
		 }catch(Exception ex){  
			 
		     ex.printStackTrace(); 
		     return new Status(500, ex.toString());  
		 }
	}
	
	
	@RequestMapping(value="/getClientDetailsById/{clientId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status  getClientDetailsById(@PathVariable("clientId") int clientId) throws IOException {	
		
		try{
			Client client = clientService.getClientDetailsById(clientId);
			return new Status(200, client, ConstantsUtil.SERVER_IMG_LOCATION);
	    	
		}catch(Exception e){
			
		   e.printStackTrace();
		   return new Status(500, e.toString());
	    }
		
    }
	
	@RequestMapping(value="/getBookedFlatDetailsByWingAndFlatNumber",method=RequestMethod.POST)
	public @ResponseBody  
	 Status getBookedFlatDetailsByWingAndFlatNumber(@RequestBody Client client, BindingResult result) throws IOException {	
				
		try{
				int wingId 		 = client.getWingId();
				int flatNumber   = client.getFlatNumber();
				int floorNumber  = client.getFloorNumber();
				String floorName = client.getFloorName();
			
				Client propertyDetails = clientService.getBookedFlatDetails(wingId, flatNumber,floorNumber, floorName);
				return new Status(200, propertyDetails, ConstantsUtil.SERVER_IMG_LOCATION);
			
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500, ex.toString()); 
		}
    }
	
	@RequestMapping(value = "/addClient", method = RequestMethod.POST) 
	 public @ResponseBody Status addEnquiryDetails(@RequestBody Client client, BindingResult result) { 
		
	  try { 
		  		if(client.getClientTranId() == 0){
		  			
		  			    boolean isRecordAdded = clientService.addClient(client);  
		  			    
		  			    String[] arrayList    = userService.getAllAdminList();					    
					    int clientId               = utilityService.getRecentId(Client.class, "clientTranId");	
					    
		  			    if(isRecordAdded){	
		  			    	
		  			    	if(client.getUserType() == 3){	
					    		
						    	 pushNotification.pushFCMNotification(arrayList, "Convert to client", "Tap here to see record", "sub-user", 3, clientId);
						    	}
		  			    	
		  			    	logger.info("Property No."+client.getFlatNumber()+" Booked Successfully");
						    return new Status(200, "Property Booked Successfully");  
		  			    }else{
		  			    	
		  			    	logger.info("Property No."+client.getFlatNumber()+" Booking Failed");
						    return new Status(400, "Property Booking Failed");  
		  			    }
					    
		  		}else{
		  		
		  				boolean isRecordUpdated =clientService.updateClientDetailsByClientTranId(client);
		  				if(isRecordUpdated){
		  					
		  					logger.info("Client Id "+client.getClientTranId()+" Updated Successfully");
			  				return new Status(200, "Client Details Updated Successfully");  
		  				}else{
		  					
		  					logger.info("Client Id "+client.getClientTranId()+" could not Updated");
			  				return new Status(400, "Client Details Could Not Updated");  
		  				}
		  				
		  		}
			   
	     }catch(Exception ex) {  
	    	 
			    ex.printStackTrace();  
			    return new Status(500, ex.toString());  
	     }   
	}
	
	@RequestMapping(value="/getPropertyByFlatNumberAndWingId/{wingId}/{flatNumber}/{floorNum}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getPropertyByFlatNumberAndWingId(@PathVariable("wingId") int wingId, @PathVariable("flatNumber") int flatNumber, 
			@PathVariable("floorNum") int floorNum, @PathVariable("floorName") String floorName) throws IOException {	
	
		String propertyType =  clientService.getUnbookedPropertyType(wingId, flatNumber, floorNum,floorName);
		return new Status(propertyType);		
    }

	@RequestMapping(value="/getClientNameByFlatNumberAndWingId/{wingId}/{flatNumber}/{floorNum}/{floorName}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getClientNameByFlatNumberAndWingId(@PathVariable("wingId") int wingId, @PathVariable("flatNumber") int flatNumber,
			@PathVariable("floorNum") int floorNum,@PathVariable("floorName") String floorName) throws IOException {	
	
		String clientName =  clientService.getClientNameByFlatNumberAndWingId(wingId, flatNumber, floorNum, floorName);
		return new Status(clientName);		
    }
	
	@RequestMapping(value="/cancelPropertyBooking",method=RequestMethod.POST)
	public @ResponseBody  
	Status cancelBooking(@RequestBody Client client, BindingResult result) throws IOException {	
		
		try{
			boolean isCancel =  clientService.cancelPropertyBooking(client);
			
			if(isCancel){				
				return new Status(200, "Property Booking cancelled Successfully");
			}else{				
				return new Status(400, "Property Booking cancellation failed");
			}
		
		}catch(Exception ex){
			
		   ex.printStackTrace();
		   return new Status(500, ex.toString());
	    }
    }
	
	
	@RequestMapping(value = "/getClientsDisbursementRemaingAmountByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getClientsDisbursementRemaingAmountByProjectId(@PathVariable("projectId") int projectId)throws Exception {		
		  
		 try{  
			 
			 List<PaidDisbursementDetails> clientList = clientService.getClientsDisbursementRemaingAmountByProjectId(projectId);
			 return new Status(200, clientList);
		 
		 }catch(Exception ex){  
			 
		     ex.printStackTrace(); 
		     return new Status(500, ex.toString());  
		 }
	}
	
	
	@RequestMapping(value = "/getAllPaidAndRemainingDisbursementByClientId/{clientId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllDisbursementByClientId(@PathVariable("clientId") int clientId)throws Exception {		
		  
		 try{  
			 
			 PaidDisbursementDetails clientDetails = clientService.getAllDisbursmentDetailsByClient(clientId);
			 return new Status(200, clientDetails);
		 
		 }catch(Exception ex){  
			 
		     ex.printStackTrace(); 
		     return new Status(500, ex.toString());  
		 }
	}
	
	@RequestMapping(value = "/addClientDocuments", method = RequestMethod.POST) 
	 public @ResponseBody Status addClientDocuments(@RequestParam("documentDetails") String jsondata, @RequestParam(value="file",required=true) MultipartFile file) { 
		
		try{	
				ClientDocuments documents = mapper.readValue(jsondata, ClientDocuments.class);
		        
		        documents.setDocumentFile(file);
		        clientService.addClientDocuments(documents);	
				logger.info("Client Document Uploaded Successfully !");	  		 
				return new Status(200, "Client Document Uploaded Successfully !");
	  		 	
		}catch(Exception ex){	
			
	 			return new Status(500, "Client Document could not uploaded due to "+ex.toString());
		}		
	}
	
	@RequestMapping(value = "/getClientDocumentsListByClientId/{clientId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getClientDocumentsListByClientId(@PathVariable("clientId") int clientId) throws Exception{
		
		 try{			
			 List<ClientDocuments>  documentLists = clientService.getClientDocumentsListByClientId(clientId);
			 return new Status(200, documentLists);
			
		 }catch(Exception ex){ 
			 
			 ex.printStackTrace();	
			 return new Status(500,ex.toString());			
		 } 
	} 
	
/*	@RequestMapping(value ="/downloadClientDocument/{fileName}", method = RequestMethod.GET)
    public void downloadClientDocument(
    		@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
		System.out.println("fileName: "+fileName);
		clientService.downloadClientDocument(fileName, response);
	    logger.info("Download Document Successfully: "+fileName);
        
    }*/
	

	@RequestMapping(value ="/downloadClientDocument", method = RequestMethod.POST)
    public @ResponseBody void downloadClientDocument(
	@RequestParam("documentName") String fileName, HttpServletResponse response) throws IOException {
 
			clientService.downloadClientDocument(fileName, response);
	        logger.info("Download Document Successfully: "+fileName);
        
    }
	
	@RequestMapping(value = "/deleteClientDocumentById/{documentId}/{projectId}/{userId}", method = RequestMethod.DELETE) 
	 public @ResponseBody Status deleteClientDocumentById(@PathVariable("documentId") int documentId, @PathVariable("projectId") int projectId, @PathVariable("userId") int userId) { 
		
		try{
			
				String docName     =  clientService.getDocumentNameByDocumentId(documentId);
				
				clientService.deleteClientDocumentById(documentId,userId,projectId);
				logger.info("Client Document of Id "+documentId+" deleted Successfully");
				
				File file          =  new File(ConstantsUtil.FILE_SAVE_LOCATION+docName);		
				file.delete();
				
				return new Status(200, "Client Document Deleted Successfully !");
 		 		
	
		}catch(Exception ex){		
			
		 		return new Status(200, "Client Document could not deleted due to "+ex.toString());
		}
	}
	
	
}
