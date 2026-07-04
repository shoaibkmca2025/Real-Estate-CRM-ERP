package com.bcs.webservice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.BankDetails;
import com.bcs.model.Status;
import com.bcs.service.BankDetailsService;
import com.bcs.utility.ConstantsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class BankWebController {

	@Autowired
	BankDetailsService bankDetailsService;
	
	 @Autowired
	 private ObjectMapper mapper;
	 
	 final static Logger logger = LoggerFactory.getLogger(BankWebController.class);	
	 

	@RequestMapping(value = "/getBankDetailsByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getBankDetailsByProjectId(@PathVariable("projectId") int projectId) throws Exception{
		
		 try{	
			 
			 List<BankDetails>  bankLists = bankDetailsService.getBankDetailsListById(projectId); 
			 return new Status(200, bankLists);
			
		 }catch(Exception ex){ 	
			 
			 ex.printStackTrace();
			 return new Status(500, ex.toString());  
			
		 }  
	} 
	
	@RequestMapping(value = "/getBankDetailsById/{bankId}", method = RequestMethod.GET) 
	public @ResponseBody  
	Status getBankDetailsById(@PathVariable("bankId") int bankId) throws IOException {	
			
		try{
			 BankDetails bankDetails = bankDetailsService.getBankDetailsById(bankId);
			 return new Status(200, bankDetails);
			 
		}catch(Exception ex){
			
			ex.printStackTrace();
			return new Status(500, ex.toString());  
	    }
		
	}
	
	 @RequestMapping(value = "/addBankDetails", method = RequestMethod.POST,  produces = MediaType.APPLICATION_JSON_VALUE ) 
	 public @ResponseBody Status addBankDetails(@RequestParam("bankDetails") String jsondata, @RequestParam(value="file",required=false) MultipartFile file){ 
		
	  try { 
				  	BankDetails bankDetails = mapper.readValue(jsondata, BankDetails.class);
				  
				    bankDetails.setBankAttachment(file);
				    
				  	bankDetailsService.addBankDetails(bankDetails); 
				  	logger.info("Bank Details added Successfully ");
				  	return new Status(200, "Bank Details added Successfully !");  
				
	     }catch(Exception e){  
	    	 
	    	 e.printStackTrace();  
	    	 return new Status(500, e.toString());  
	     }   
	}
	
	@RequestMapping(value="/updateBankDetails",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
	public @ResponseBody  
	Status updateBankDetails(@RequestParam("bankDetails") String jsondata, @RequestParam(value="file",required=false) MultipartFile file) throws IOException {	
		
		try {	
					BankDetails bankDetails = mapper.readValue(jsondata, BankDetails.class);	
					
					bankDetails.setBankAttachment(file);
					
					bankDetailsService.updateBankDetails(bankDetails);
					logger.info("Bank Details Updated Successfully !");
					return new Status(200,"Bank Details Updated Successfully !");
			
				
		}catch(Exception ex){
				  ex.printStackTrace();
				  return new Status(500,ex.toString());
		}
    }
	

	@RequestMapping(value="/deleteBankDetailsById/{bankId}/{projectId}/{userId}",method=RequestMethod.DELETE)
	public @ResponseBody  
	Status deleteWingById(@PathVariable("bankId") int bankId,@PathVariable("projectId") int projectId,@PathVariable("userId") int userId){	
		
		try {
			
					String docName     =  bankDetailsService.getAttachmentByBankId(bankId);			
					boolean result     = bankDetailsService.deleteBankDetails(bankId,userId,projectId);		
					
					if(result){	
						
						File file             =  new File(ConstantsUtil.FILE_SAVE_LOCATION+docName);		
						file.delete();
						logger.info("BankDetails of Id "+bankId+" deleted Successfully");
						return new Status(200,"Bank Details Deleted Successfully !", bankId);
						
					}else{
						
						logger.info("BankDetails of Id "+bankId+" could not deleted");
						return new Status(400,"Bank Details could not deleted!", bankId);
					}
			
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString(),bankId);
		}
    }
	
	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST) 
	 public @ResponseBody Status uploadDocument(@RequestParam(value="file") MultipartFile file) { 
		
		String documentName = null;
		String fileName 	= null;
		String extension 	= null;
		try{
			
						Date date  = new Date();
						if (file.getOriginalFilename().indexOf(".") >= 0) {
							  fileName  = file.getOriginalFilename().substring(0, (file.getOriginalFilename()).lastIndexOf("."));
							  extension = file.getOriginalFilename().substring((file.getOriginalFilename()).lastIndexOf("."),(file.getOriginalFilename()).length());
							} 
						String originalFilename = (fileName.replaceAll("[^a-zA-Z0-9_]", "_")).replaceAll("_+", "_")+extension;
						
					    if(originalFilename != ""  && originalFilename != null){
					    	
					    	documentName   = date.getTime()+"_"+originalFilename;
					    	
							BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
					                       new File(ConstantsUtil.FILE_SAVE_LOCATION, documentName)));
			
					        outputStream.write(file.getBytes());
					        outputStream.flush();
					        outputStream.close();
					    }
					    return new Status(200, documentName);
			
		    
		}catch(Exception ex){	
			 ex.printStackTrace();
			 return new Status(500, "File not uploaded due to "+ex.toString());
		}
		
	}
	
	/*@Autowired
	 ServletContext context;

	 @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	 public String upload(@RequestParam("file") MultipartFile file) {
	     
	
		 String name=file.getOriginalFilename();
		 
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(ConstantsUtil.FILE_SAVE_LOCATION, name)));
	                stream.write(bytes);
	                stream.flush();
	                stream.close();
	                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
	 }
	 
	 @RequestMapping(value="/upload", method=RequestMethod.POST)
	    public @ResponseBody String handleFileUpload( 
	            @RequestParam("file") MultipartFile file){
	            String name = file.getName();
	            
	           System.out.println("name : "+name);
	            
	        if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream = 
	                        new BufferedOutputStream(new FileOutputStream(new File(ConstantsUtil.FILE_SAVE_LOCATION, name)));
	                stream.write(bytes);
	                stream.close();
	                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
	            } catch (Exception e) {
	                return "You failed to upload " + name + " => " + e.getMessage();
	            }
	        } else {
	            return "You failed to upload " + name + " because the file was empty.";
	        }
	    }*/
	
	/*@Autowired
	 ServletContext context;

	 @RequestMapping(value = "/fileupload", consumes="multipart/form-data", method = RequestMethod.POST)
	 public Status upload(@RequestParam("file") MultipartFile inputFile) {
	  //FileInfo fileInfo = new FileInfo();
	  HttpHeaders headers = new HttpHeaders();
	  if (!inputFile.isEmpty()) {
	   try {
	    String originalFilename = inputFile.getOriginalFilename();
	    File destinationFile = new File("C:/YSM/Desktop/"+  File.separator + originalFilename);
	    inputFile.transferTo(destinationFile);
	  //  fileInfo.setFileName(destinationFile.getPath());
	 //   fileInfo.setFileSize(inputFile.getSize());
	    headers.add("File Uploaded Successfully - ", originalFilename);
	    return new Status(200, "File Uploaded Successfully !");
	   } catch (Exception ex) {    
		   return new Status(404, "File not uploaded due to "+ex.toString());
	   }
	  }else{
		  return new Status(404, "File not selected");
	  }
	 }*/
}
