package com.bcs.webservice;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.CompanyProfile;
import com.bcs.model.Status;
import com.bcs.service.CompanyProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CompanyProfileController {
	
	@Autowired
	CompanyProfileService companyService;
	
	@Autowired
	private ObjectMapper mapper;
	
	final static Logger logger = LoggerFactory.getLogger(CompanyProfileController.class);	
	
	@RequestMapping(value="/getAllCompanyDetails",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllCompanyDetails() throws IOException {
			
		try{
				List<CompanyProfile> companyList = companyService.getAllCompanyDetails();
				return new Status(200, companyList);
	    	
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500, ex.toString());  
	    }		
    }
	
	@RequestMapping(value = "/addCompanyDetails", method = RequestMethod.POST) 
	 public @ResponseBody Status addUser(@RequestBody CompanyProfile companyProfile, BindingResult result) throws Exception{
		
		try { 
				companyService.addCompanyDetails(companyProfile);
				logger.info("Company Details added Successfully !");
				return new Status(200, "Company Details added Successfully !");  

	     }catch(Exception ex){  
	    	 
			    ex.printStackTrace();  
			    return new Status(500, ex.toString());  
	     }   
	}
	 
	 @RequestMapping(value="/getCompanyDetailsByCompanyId/{companyId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getCompanyDetailsByCompanyId(@PathVariable("companyId") int companyId) throws IOException {
			
		try{
				CompanyProfile companyProfile = companyService.getCompanyDetailsByCompanyId(companyId);
				return new Status(200, companyProfile);
	    	
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());  
	    }		
    }
	 
		@RequestMapping(value="/updateCompanyByCompanyId",method=RequestMethod.PUT)
		public @ResponseBody  
		Status updateCompanyByCompanyId(@RequestBody CompanyProfile companyProfile) throws IOException {
			
			try {
					companyService.updateCompanyByCompanyId(companyProfile);	
					logger.info("Company Details Updated Successfully of companyId :"+companyProfile.getCompanyId());
					return new Status(200,"Company Details Updated Successfully !",companyProfile.getCompanyId());
					
			}catch(Exception ex){
				
					ex.printStackTrace();
					return new Status(500,ex.toString());
				
			}
	   }
		
		@RequestMapping(value="/updateCompanyProfileDetailsByCompanyId",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
		public @ResponseBody  
		Status updateCompanyProfileDetailsByCompanyId(@RequestParam("profileDetails") String jsondata, @RequestParam(value="file",required=false) MultipartFile file) throws IOException {	
			
			try {	

						CompanyProfile companyProfile = mapper.readValue(jsondata, CompanyProfile.class);	
						
						int userId = companyProfile.getCompanyId();
						
						companyProfile.setLogoAttachment(file);
						
						String LogoAttachment = companyService.updateCompanyProfileDetailsByCompanyId(companyProfile);
						
						logger.info("Profile Details of userId "+userId+" Updated Successfully !");
						return new Status(200,"Profile Details Updated Successfully !",LogoAttachment);
			
			}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500,ex.toString());
			}
	    }

}
