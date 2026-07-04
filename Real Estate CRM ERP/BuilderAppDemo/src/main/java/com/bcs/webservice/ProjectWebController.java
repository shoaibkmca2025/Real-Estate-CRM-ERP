
package com.bcs.webservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.CountDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.Status;
import com.bcs.model.Wing;
import com.bcs.service.AmenitiesDocumentsService;
import com.bcs.service.BankDetailsService;
import com.bcs.service.DisbursementService;
import com.bcs.service.ProjectService;
import com.bcs.service.UtilityService;
import com.bcs.service.WingService;
import com.bcs.utility.CommonUtil;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.SendEMail;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class ProjectWebController {
	
	final static Logger logger = LoggerFactory.getLogger(ProjectWebController.class);	
	
	@Autowired  
	ProjectService projectService; 	
	
	@Autowired  
	WingService wingService;
	
	@Autowired  
	AmenitiesDocumentsService amenitiesService; 	
	
	@Autowired  
	BankDetailsService bankService; 
	
	@Autowired  
	DisbursementService disbursementService; 
	
	@Autowired  
	SendEMail sendMail;
	
	@Autowired
	UtilityService utilityService;
	
	 @Autowired
	 private ObjectMapper mapper;
	
	@RequestMapping(value = "/addProjectDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE) 
	 public @ResponseBody Status addProjectDetails(@RequestParam("projectDetails") String jsondata, 
			 @RequestParam(value="file",required=false) MultipartFile file) { 
		
	  try {
		  		ProjectDetails  projectdetails = mapper.readValue(jsondata, ProjectDetails.class);
		  		projectdetails.setLetterHeadFile(file);
		  		
			 	int projectTranId   =   projectService.getRecentProjectId();
			  	int wingTranId      =   projectService.getRecentWingId();
			  			
			    boolean isRecordAdded = projectService.addAllDetails(projectdetails, projectTranId, wingTranId);
			    
			    if(isRecordAdded){
			    	//projectService.generateProjectDetailsPdf(projectDetails);
			    	logger.info("Project Details added Successfully !");
				  	return new Status(200, "Project Details added Successfully !");
				  	
			    }else{
			    	
			    	logger.info("Project Details could not added!");
				  	return new Status(400, "Project Details could not added!");			    	
			    }
			    	
	     }catch(Exception e){
	    	 
			    e.printStackTrace();
			   return new Status(404, e.toString());
	     }   
	}
		
	@RequestMapping(value = "/getProjectDetails/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status  getProjectById(@PathVariable("projectId") int projectId) throws Exception{
		
		 ProjectDetails  projectdetails = null;
		 try {							 projectdetails = projectService.getProjectDetailsById(projectId); 	
					 int result = projectService.checkIsAnyPropertyBookedByProjectId(projectId);
					 					 return new Status(200, projectdetails, result); 
				
		 }catch(Exception e){ 	
					 e.printStackTrace();	
					 return new Status(500, e.toString());		
		 }  
	
	} 
	
	
	@RequestMapping(value="/getAllProjectDetails/{userType}/{userId}/{companyId}/{projectStatus}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllProjectDetails(@PathVariable("userId") int userId, @PathVariable("companyId") int companyId, 
			@PathVariable("userType") int userType, @PathVariable("projectStatus") int projectStatus) throws IOException {	
		
		List<ProjectDetails> projectDetailsList = null;		
		try{
				 int Id = 0;
				 if(userType == 2 || userType == 1){					 
					 Id  = 	companyId;	
				 }else{					 
					 Id  = 	userId;			
				 }
				 projectDetailsList = projectService.getAllProjectDetails(userType, Id, projectStatus);		
				 
				 return new Status(projectDetailsList,200, "Get Project Details list successfully");
				 
		}catch(Exception e){
			
			   e.printStackTrace();
			   return new Status(500, e.toString());
	    }
	
    }
	
	/*@RequestMapping(value="/getAllProjectDetailsWS",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllProjectDetailsWS(HttpSession session) throws IOException {	
		
		int userId = 0;
		int userType = 0;
		List<ProjectDetails> ProjectDetailsList = null;
		
		try{
			if(session.getAttribute("userId") != null){
				 
				 userId    =  (int) session.getAttribute("userId");
				 userType  =  (int) session.getAttribute("userType");
				 
				 if(userType == 2){					 
						ProjectDetailsList = projectService.getAllProjectDetails(userId);						
				 }else{					 
						ProjectDetailsList = projectService.getAllProjectDetailsByUserType(userId);						
				 }
				return new Status(ProjectDetailsList,200, " Get Project Details list successfully ");
				 
			 }else{
				 
				 logger.info("Session invalidated due to timeout");
				 return new Status(100, "Session Invalidated");
			 }
			
	    	
		}catch(Exception e){
		   e.printStackTrace();
		   return new Status(400, e.toString());
	    }
	
    }
	*/
	
	
	@RequestMapping(value = "/getProjectDetailsByStatus/{projectStatus}/{userType}/{userId}/{companyId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getProjectDetailsByStatus(@PathVariable("projectStatus") int projectStatus, @PathVariable("userType") int userType,
			@PathVariable("userId") int userId,@PathVariable("companyId") int companyId) throws Exception{
		
		List<ProjectDetails>  projectdetails = null;
		
		 try {	
			 		int Id = 0;
				 	if(userType == 2){
				 		Id = companyId;
				 	}else if(userType == 3){
				 		Id = userId;
				 	}
				 	projectdetails = projectService.getAllProjectsByStatus(projectStatus,userType, Id); 	
				return new Status(200,projectdetails, ConstantsUtil.SERVER_IMG_LOCATION);				
			 
		 }catch(Exception e){ 				 
				 e.printStackTrace();		
				 return new Status(500, e.toString());			 
		 }		
	} 
	
	
	@RequestMapping(value = "/getProjectStructureByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getProjectStructureByProjectId(@PathVariable("projectId") int projectId) throws Exception{
		
		 try{			
				 List<Wing>  flatBookingDetails = projectService.getProjectStructureByProjectId(projectId);	
				 return new Status(200, flatBookingDetails);
			 
		 }catch(Exception ex){ 	
			 
				 ex.printStackTrace();
				 return new Status(500, ex.toString());	
		 }  
		 
	} 
	
	@RequestMapping(value = "/getFlatBookingDetailsByWingTranId/{wingTranId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status  getFlatBookingDetailsByWingTranId(@PathVariable("wingTranId") int wingTranId) throws Exception{
		
		 try{			
			 	List<Wing>  flatBookingDetailsByWing = projectService.getBookingDetailsByWingTranId(wingTranId);	
			 	return new Status(200, flatBookingDetailsByWing);
			 	
		 }catch(Exception ex){ 	
			 
				ex.printStackTrace();	
				return new Status(500, ex.toString());	
		 }  
		
	} 
	
	@RequestMapping(value = "/getProjectStatusList", method = RequestMethod.GET)  
	public @ResponseBody Status  getProjectStatusList() throws Exception{
		
		HashMap<Integer, String>  projectStatusList = null;
		
		 try{			
				 projectStatusList = CommonUtil.getProjectStatus();
				 return new Status(200, projectStatusList);
				
			 
		 } catch (Exception e) { 	
				 e.printStackTrace();
				 return new Status(500, e.toString());
		 }
	}  
	
	
	/*
	 * Project Status Update
	 */
	@PostMapping("updateProjectStatus")	
	public @ResponseBody Status updateProjectStatus(@RequestBody ProjectDetails projectDetails) {
		
		 try {	
					    int   projectId        = projectDetails.getProjectTranId();
					  	int   projectStatus    = projectDetails.getProjectStatus();
					  	int   userId           = projectDetails.getUserId();
					 
					  	boolean isUpdated = projectService.updateProjectStatus(projectId, projectStatus, userId);
					  	if(isUpdated){
					  		
					  		logger.info("Status of Project "+projectId+" Updated Successfuly");						
							return new Status(projectId, 200, "Project Status Updated successfully.");
					  	}else{
					  		
					  		logger.info("Project status updation failed");						
							return new Status(projectId, 400, "Project status updation failed");
					  	}
						
					
		 } catch (Exception ex) {		    	 
			       logger.error("Exceptions while Project Status Updation : "+ex.toString());
			       return new Status(500, "Exceptions while Project Status Updation : "+ex.toString());
		 }	
		
	}
	
	
	/*
	 * Delete Project Details
	 */
	
	@RequestMapping(value = "/deleteProjectDetails/{projectId}/{userId}", method = RequestMethod.DELETE)  
	public @ResponseBody Status deleteProjectDetails(@PathVariable("projectId") int projectId, @PathVariable("userId") int userId) {
		
		 try {	
				 	boolean isDeleted = projectService.deleteProjectDetails(projectId,userId);
				 	
				 	if(isDeleted){
				 		
				 		logger.info("Project "+projectId+ " Deleted Successfuly ");						
						return new Status(projectId, 200," Project Deleted successfully.");	
				 	}else{
				 		
				 		logger.info("Project could not Deleted");						
						return new Status(projectId, 400," Project could not Deleted");	
				 	}
									
			  
		 }catch(Exception ex){		    	 
			 
			       logger.error("Exceptions while Project deletion : "+ex.toString());	
			       return new Status(500,"NOT_FOUND Or Already Deleted"); 
		 }	
	}
	
	/*
	 * Update Project Basic Details
	 */	
	@RequestMapping(value = "/updateProjectDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE) 
	 public @ResponseBody Status updateProjectDetails(@RequestParam("projectDetails") String jsondata, 
			 @RequestParam(value="file",required=false) MultipartFile file) {
		
	  try { 
		  
			  	ProjectDetails  projectDetails = mapper.readValue(jsondata, ProjectDetails.class);
			 	projectDetails.setLetterHeadFile(file);
	
		  		boolean isUpdated = projectService.updateProjectBasicDetails(projectDetails); 
		  		if(isUpdated){
		  			
		  			logger.info("Project Basic Details Updated Successfully");
		  			return new Status(200, "Project Basic Details Updated Successfully"); 
		  		}else{
		  			
		  			logger.info("Project Basic Details Updation failed");
		  			return new Status(400, "Project Basic Details Updation Failed"); 
		  		}
		  		
	     }catch(Exception e){ 
	    	 
			    e.printStackTrace();  
			    return new Status(500, e.toString());  
	     }   
	}
	
	@PostMapping("allocateProjectsToUsers")	
	public @ResponseBody Status allocateProjectsToUsers(@RequestBody ProjectDetails projectDetails)  {
		
		try {			
			  		int projectId	= projectDetails.getProjectTranId();
				  	
					projectService.allocateProjectsToUsers(projectDetails);
					logger.info("Project allocated to users successfully.");
					return new Status( projectId,200,"Project allocated to users successfully.");
				
		 }catch(Exception ex){		
			 
			       logger.error("Exceptions while Project Status Updation : "+ex.toString());	
			       return new Status(500, "project allocation Failed");
		 }	
		
	}
	
	@RequestMapping(value = "/getAllUsersByProjectId/{projectId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllUsersByProjectId(@PathVariable("projectId") int projectId)throws Exception {		
		 
		 try{  
			 	List<ProjectDetails> usersListByProject = projectService.getAllUsersByProjectId(projectId);
			 	return new Status(200, usersListByProject);
		 
		 }catch(Exception ex){  
		      	ex.printStackTrace(); 
		      	return new Status(500, ex.toString());	
		 }
	}
	
	
	@RequestMapping(value="/sendProjectDetailsEmail",method=RequestMethod.POST)
	public @ResponseBody  
	Status sendProjectDetailsEmail(@RequestBody ProjectDetails projectDetails) throws IOException {		
	
		try{
			
			    List<String> documentsList = projectDetails.getDocuments();
			   // String projectPdf          = ConstantsUtil.FILE_SAVE_LOCATION+projectDetails.getProjectName()+"/"+projectDetails.getProjectTranId()+"_"+projectDetails.getProjectName()+".pdf";
			    String From             = utilityService.getSettingsByCompanyId(projectDetails.getCompanyId()).getSendersEmail();
			    String companyName		= projectDetails.getCompanyName();
			    String[] documentPathArray=new String[documentsList.size()];
			    
			    //documentPathArray[0] =projectPdf;
			    for(int i=0;i<documentsList.size();i++){
			    	
				   String documentPath = ConstantsUtil.FILE_SAVE_LOCATION+documentsList.get(i);				   
				   documentPathArray[i] = documentPath;
			    }

		        String to          		=  projectDetails.getEmail();
				String subject    		=  "Project Details and Documents";
				String messageBody		=  "";
			
				boolean isMailSent = sendMail.sendMultipleAttachments(From, to, subject, messageBody, documentPathArray, companyName);
				if(isMailSent==true){
					logger.info("Project Details and Documents send successfully ");
					return new Status(200,"Project Details and Documents send successfully");
				}else{
					logger.info("Error in Sending Mail Project Details pdf and Documents ");
					return new Status(404,"Error in Sending Mail Project Details pdf and Documents");
				}
				 
		}catch(Exception ex){
			ex.printStackTrace();
			return new Status(500, ex.toString());	
		}
	}
	
	@RequestMapping(value = "/getAllCountDetails", method = RequestMethod.POST) 
	 public @ResponseBody Status getAllCountDetails(@RequestBody ProjectDetails projectDetails) { 
		
		 try{ 	
			 	int userId   		= projectDetails.getUserId();			 	
			 	int userType 		= projectDetails.getUserType();
			 	int projectStatus 	= projectDetails.getProjectStatus();
			 	int projectTranId 	= projectDetails.getProjectTranId();
			 	int companyId		= projectDetails.getCompanyId();
			 	
			    CountDetails countDetails = projectService.getAllCountDetails(userId, userType, projectStatus, projectTranId, companyId);
			   
			  	return new Status(200,countDetails);  		
			
	     }catch(Exception e) {  
	    	 
			    e.printStackTrace();  
			    return new Status(500, e.toString());  
	     }   
	}
	
	@PostMapping("updateProjectApprovedStatus")	
	public @ResponseBody Status updateProjectApprovedStatus(@RequestBody ProjectDetails projectDetails) {
		
		 try {
			 int   projectId        = projectDetails.getProjectTranId();
			 int   isApproved    = projectDetails.getIsApproved();
			  	
			  	boolean isUpdated = projectService.updateProjectApprovedStatus(projectId, isApproved);
			  	if(isUpdated){
			  		
			  		logger.info("Approved Status of Project "+projectId+" Updated Successfuly");						
					return new Status(projectId, 200, "Project Approved Status Updated successfully.");
			  	}else{
			  		
			  		logger.info("Project approved status updation failed");						
					return new Status(projectId, 400, "Project approved status updation failed");
			  	}
				
		} catch (Exception ex) {		    	 
			       logger.error("Exceptions while Project Approved Status Updation : "+ex.toString());
			       return new Status(500, "Exceptions while Project Approved Status Updation : "+ex.toString());
		}	
		
	}
	
	@RequestMapping(value = "/getAllUnapprovedProjectDetails", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllUnapprovedProjectDetails()throws Exception {
		
		List<ProjectDetails> projectNotificationList = null;  
		int projectNotificationCount=0;
		 
		 try{  
			 projectNotificationList  = projectService.getAllUnapprovedProjectDetails();
			 projectNotificationCount = projectNotificationList.size();				
				return new Status(projectNotificationList,projectNotificationCount);
				
		 }catch(Exception ex){ 
			 
		     	ex.printStackTrace();
		     	return new Status(500, ex.toString());	
		 }
	}
	
	@RequestMapping(value="/getInprogressAndCompletedProjects/{userType}/{companyId}/{userId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getInprogressAndCompletedProjects(@PathVariable("userType") int userType, @PathVariable("companyId") int companyId, @PathVariable("userId") int userId ) throws IOException {	
		
		List<ProjectDetails> projectDetailsList = null;		
		try{
				int Id = 0;
			 	if(userType == 2){
			 		Id = companyId;
			 	}else if(userType == 3){
			 		Id = userId;
			 	}
				projectDetailsList = projectService.getInprogressAndCompletedProjectList(userType, Id);		
				 
				return new Status(projectDetailsList,200, "Get Project Details list successfully");
				 
		}catch(Exception e){
			
			   e.printStackTrace();
			   return new Status(500, e.toString());
	    }
	
    }
	
}