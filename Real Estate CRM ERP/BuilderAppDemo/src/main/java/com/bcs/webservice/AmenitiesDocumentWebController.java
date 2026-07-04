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

import com.bcs.model.Amenities;
import com.bcs.model.DocumentType;
import com.bcs.model.Documents;
import com.bcs.model.Status;
import com.bcs.service.AmenitiesDocumentsService;
import com.bcs.service.UtilityService;
import com.bcs.utility.ConstantsUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AmenitiesDocumentWebController {

	@Autowired
	AmenitiesDocumentsService amenitiesDocumentsService;
	
	@Autowired
	UtilityService utilityService;
	
	@Autowired
	private ObjectMapper mapper;	
	
	final static Logger logger = LoggerFactory.getLogger(AmenitiesDocumentWebController.class);	
		
	@RequestMapping(value="/getDocumentTypeList",method=RequestMethod.GET)
	public @ResponseBody Status getDocumentTypeList() throws IOException {	
			
		try{
			
			List<DocumentType> documentTypeList = utilityService.getAllDocumentType();
			return new Status( 200, documentTypeList, ConstantsUtil.SERVER_IMG_LOCATION);  
    		
		}catch(Exception ex){
		   ex.printStackTrace();
		   return new Status(500, ex.toString());  
	    }
		
    }
	
	@RequestMapping(value = "/getAllAmenitiesByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllAmenitiesByProjectId(@PathVariable("projectId") int projectId) throws Exception{
		
		List<Amenities>  amenitiesLists = null;
		 try{			
			 amenitiesLists = amenitiesDocumentsService.getAmenitiesById(projectId); 
			 return new Status(200, amenitiesLists);
			
		 }catch(Exception ex){ 	
			 ex.printStackTrace();
			 return new Status(500, ex.toString());  
			
		 }  
	} 
		
	@RequestMapping(value = "/addAmenitiesWs", method = RequestMethod.POST) 
	 public @ResponseBody Status addAmenities(@RequestBody Amenities amenities, BindingResult result) throws Exception{ 
		
	  try { 
			  	boolean isRecordAdded = amenitiesDocumentsService.addAmenities(amenities);
			  	if(isRecordAdded){
			  		return new Status(200, "Amenities Details added Successfully !"); 
			  	}else{
			  		return new Status(400, "Amenities Details can not added !"); 
			  	}
			  	
	     }catch (Exception e){  
	    	 
			    e.printStackTrace();  
			    return new Status(500, e.toString());  
	     }   
	}
	
	@RequestMapping(value="/updateAmenityById",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updateAmenityById(@RequestBody Amenities amenity) throws IOException {	
		try {	
			
				 boolean result = amenitiesDocumentsService.updateAmenities(amenity);
				 if(result){
					 
					 logger.info("Amenity Updated Successfully :"+amenity.getAmenityId());
				     return new Status(200,"Amenity Updated Successfully !",amenity.getAmenityId());
				     
				 }else{
					 
					 logger.info("Amenity Updation failed !"+amenity.getAmenityId());
				     return new Status(400,"Amenity Updation failed !",amenity.getAmenityId()); 
				 }		  		 
			     
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500,ex.toString());
			
		}
    }
	
	@RequestMapping(value="/deleteAmenitiesById/{aminitiesId}/{projectId}/{userId}",method=RequestMethod.DELETE)
	public @ResponseBody  
	Status deleteAmenitiesById(@PathVariable("aminitiesId") int aminitiesId, @PathVariable("projectId") int projectId, @PathVariable("userId") int userId) throws IOException {	
		try {
			
			boolean result = amenitiesDocumentsService.deleteAmenities(aminitiesId,userId,projectId);
			if(result){
				
				logger.info("Amenities Details Deleted Successfully !"+aminitiesId);	  		 	
				return new Status(200,"Amenities Details Deleted Successfully !", aminitiesId);
				
			}else{
				
				logger.info("Amenity Id "+aminitiesId+" does not Found! ");	  		 	
				return new Status(400,"Amenity Id does not Found! ", aminitiesId);
			}
				
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString());
		}
    }
	
	@RequestMapping(value = "/getDocumentDetailsByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getDocumentDetailsByProjectId(@PathVariable("projectId") int projectId) throws Exception{
		
		 try{			
			 List<Documents>  documentLists = amenitiesDocumentsService.getDocumentsById(projectId);
			 return new Status(200, documentLists);
			
		 }catch(Exception ex){ 
			 
			 ex.printStackTrace();	
			 return new Status(500,ex.toString());			
		 } 
	} 
	
	
	 @RequestMapping(value = "/addDocument", method = RequestMethod.POST) 
	 public @ResponseBody Status addDocument(@RequestParam("documentDetails") String jsondata, @RequestParam(value="file",required=true) MultipartFile file) { 
		
		try{	
			 	Documents documents = mapper.readValue(jsondata, Documents.class);
		        
		        documents.setDocumentFile(file);
				amenitiesDocumentsService.addDocument(documents);	
				logger.info("Document Added Successfully !");	  		 
				return new Status(200, "Document Added Successfully !");
	  		 	
		}catch(Exception ex){	
			
	 			return new Status(500, "Document could not added due to "+ex.toString());
		}		
	}
	
	@RequestMapping(value = "/deleteDocumentById/{documentId}/{projectId}/{userId}", method = RequestMethod.DELETE) 
	 public @ResponseBody Status deleteDocumentById(@PathVariable("documentId") int documentId, @PathVariable("projectId") int projectId, @PathVariable("userId") int userId) { 
		
		try{
			
				String docName     =  amenitiesDocumentsService.getDocumentNameByDocumentId(documentId);
				
				amenitiesDocumentsService.deleteDocument(documentId,userId,projectId);
				logger.info("Document of Id "+documentId+" deleted Successfully");
				
				File file          =  new File(ConstantsUtil.FILE_SAVE_LOCATION+docName);		
				file.delete();
				
				return new Status(200, "Document Deleted Successfully !");
  		 		
	
		}catch(Exception ex){		
			
		 		return new Status(200, "Document could not deleted due to "+ex.toString());
		}
	}
	
	
	@RequestMapping(value = "/deleteUploadedDocument/{documentName}", method = RequestMethod.DELETE) 
	public @ResponseBody Status deleteUploadedDocument(@PathVariable("documentName") String documentName)  {		
		
		try{			
				File file   =  new File(ConstantsUtil.FILE_SAVE_LOCATION+documentName);		
				file.delete();
				logger.info("Uploaded Document deleted Successfully: "+documentName);
				return new Status(200, "Uploaded Document deleted Successfully");

		}catch(Exception ex){
			return new Status(500, "Uploaded Document could not deleted due to "+ex.toString());
		}
		
    }
	
	@RequestMapping(value ="/downloadDocument/{fileName}", method = RequestMethod.GET)
    public void downloadDocument(
    		@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
 
			amenitiesDocumentsService.downloadDocument(fileName, response);
	        logger.info("Download Document Successfully: "+fileName);
        
    }
	
/*	@RequestMapping(value ="/downloadDocument/{fileName}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile1(
    		@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
 
		
		String fullPath         =  ConstantsUtil.FILE_SAVE_LOCATION+fileName;
		System.out.println(fileName);
		System.out.println(fullPath);
		
		File file           = new File(fullPath);	
		
        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
      
       //File file = new File(ConstantsUtil.FILE_SAVE_LOCATION + "/" + fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
 
        return ResponseEntity.ok()               
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())               
                .contentType(mediaType)               
                .contentLength(file.length())
                .body(resource);
    }*/

}
