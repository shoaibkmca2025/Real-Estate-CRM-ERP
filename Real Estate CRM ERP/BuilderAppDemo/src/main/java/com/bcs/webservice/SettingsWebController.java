package com.bcs.webservice;

import java.io.IOException;

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

import com.bcs.model.Settings;
import com.bcs.model.Status;
import com.bcs.service.UtilityService;

@Controller
public class SettingsWebController {
	
	@Autowired
	UtilityService utilityService;
	
	final static Logger logger = LoggerFactory.getLogger(SettingsWebController.class);	
	
	
	@RequestMapping(value = "/updateSettings", method = RequestMethod.POST) 
	 public @ResponseBody Status updateSettings(@RequestBody Settings settings,BindingResult result) throws Exception{ 
		
	  try {		
	  		 	if(settings.getSettingsId() != 0){
	  		 		
		  		 	boolean isUpdated = utilityService.updateSettings(settings);
		  		 	if(isUpdated){
		  		 		
		  		 		logger.info("Settings updated Successfully ");
					  	return new Status(200, "Settings updated Successfully !"); 
		  		 	}else{
		  		 		
		  		 		logger.info("Settings could not updated");
					  	return new Status(400, "Settings could not updated"); 
		  		 	}
	  		 		
	  		 	}else{
	  		 		
		  		 	boolean isAdded = utilityService.addSettings(settings);
		  		 	if(isAdded){
		  		 		
			  		 		logger.info("Settings added Successfully ");
						  	return new Status(200, "Settings added Successfully !"); 
		  		 	}else{
		  		 		
			  		 		logger.info("Settings could not added");
						  	return new Status(400, "Settings could not added"); 
		  		 	}
	  		 	}
		  		 	
	     }catch(Exception e){  
	    	 
			    e.printStackTrace();  
			    return new Status(500, e.toString());  
	     }   
	}
	
	@RequestMapping(value = "/getSettingsByCompanyId/{companyId}", method = RequestMethod.GET) 
	public @ResponseBody  
	Status getSettingsByCompanyId(@PathVariable("companyId") int companyId) throws IOException {	
				
		Settings settings = null;
		try{
			
	  		 	 settings = utilityService.getSettingsByCompanyId(companyId);		  		 	
	  		 	 return new Status(200, settings);
			
		}catch(Exception e){
			    e.printStackTrace();
			    return new Status(500, e.toString());
	    }
		
	}

}
