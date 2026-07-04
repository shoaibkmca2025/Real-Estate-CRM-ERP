package com.bcs.webservice;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcs.model.CompanyProfile;
import com.bcs.model.Status;
import com.bcs.model.User;
import com.bcs.service.CompanyProfileService;
import com.bcs.service.LoginService;
import com.bcs.service.UserService;
import com.bcs.utility.PasswordEncoder;

@Controller
public class UserController {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired  
	UserService userService;
	
	@Autowired 
	LoginService loginservice;
	
	@Autowired 
	CompanyProfileService companyService;	
	 
	@Autowired
	PasswordEncoder passwordEncoder;	
	
	@RequestMapping(value="/getAllUserDetails",method=RequestMethod.GET)
	public @ResponseBody  
	Status getAllUserDetails() throws IOException {
			
		try{
				List<User> usersList = userService.getAllUserDetails();
				return new Status(200, usersList);
	    	
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());			
	    }
    }
	
	@RequestMapping(value="/getUserDetailsByUserId/{userId}",method=RequestMethod.GET)
	public @ResponseBody  
	Status getUserDetailsByUserId(@PathVariable("userId") int userId) throws IOException {
				
		 User user = null;
		try{
				user = loginservice.getUserById(userId);
				
				CompanyProfile companyProfile = companyService.getCompanyDetailsByUserId(userId);	
				user.setCompanyId(companyProfile.getCompanyId());
				user.setCompanyName(companyProfile.getCompanyName());
				user.setCompanyEmail(companyProfile.getCompanyEmail());
				user.setCompanyMobile(companyProfile.getMobile());
				user.setWebsite(companyProfile.getWebsite());
				user.setLandline(companyProfile.getLandline());
				user.setAddress(companyProfile.getAddress());
				user.setLogoPath(companyProfile.getLogoPath());
				
				return new Status(200, user);
	    	
		}catch(Exception ex){
				ex.printStackTrace();
				return new Status(500, ex.toString());			
	    }
		
    }
	
	@RequestMapping(value = "/addUserWs", method = RequestMethod.POST) 
	 public @ResponseBody Status addUser(@RequestBody User user, BindingResult result) throws Exception{
		
	  try { 		 
			  String newPassword = user.getUserPassword();
			  
			  if(loginservice.checkMobile(user.getUserMobile()) || loginservice.checkEmail(user.getUserEmail())) {	
					
					logger.warn("Mobile number "+user.getUserMobile()+" or Email Id "+ user.getUserEmail() +" already registered.");
					return new Status(400, "Mobile number or Email id already registered.");
					
			  }
			  
			  String userPassword = passwordEncoder.getHashPassword(newPassword);			 
			  user.setUserPassword(userPassword);
			  
		      boolean isAdded = userService.addUser(user);
		      if(isAdded){
		    	  
			    	  logger.info("User Details added Successfully !");
				  	  return new Status(200, "User Details added Successfully !");  		    	  
		      }else{
		    	  
			    	  logger.info("User Details could not added!");
				  	  return new Status(400, "User Details could not added!");  	
		      }
		      

	     }catch (Exception e) {
			    e.printStackTrace();  
			    return new Status(500, e.toString());  
	     }   
	}
	
	@RequestMapping(value="/updateUserById",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updateUserById(@RequestBody User user) throws IOException {
		
		try {	
			
				boolean isUpdated = userService.updateUser(user);
				if(isUpdated){
					
						logger.info("User Details Updated Successfully of userId :"+user.getUserId());
						return new Status(200,"User Details Updated Successfully !",user.getUserId());
				}else{
					
						logger.info("User Details could not Updated :"+user.getUserId());
						return new Status(400,"User Details could not Updated");
				}
				
				
		}catch(Exception ex){
			
			ex.printStackTrace();
			return new Status(500,ex.toString());
			
		}
   }
	
	/*
	 * User Status Update
	 */
	@PostMapping("updateUserStatus")	
	public @ResponseBody Status updateUserStatus(@RequestBody User user) {
		
		 try {	
			    int   userId        = user.getUserId();
			  	int   userStatus    = user.getUserStatus();
			  	
			  	boolean isUpdated = userService.updateUserStatus(userId, userStatus);
			  	if(isUpdated){
			  		
				  		logger.info("Status of User "+userId+" Updated Successfuly");			 
						return new Status(userId, 200,"User Status Updated successfully.");
			  	}else{
			  		
				  		logger.info("User status updation failed");			 
						return new Status(userId, 400,"User status updation failed");
			  	}
			  	
			  
		 } catch (Exception ex) {		    	 
			       logger.error("Exceptions while User Status Updation : "+ex.toString());			       
		 }	
		 return new Status(500, "User Status Updation Failed");
	}
	
/*	@RequestMapping(value = "/getAllUserDetailsByBuilderId/{userId}", method = RequestMethod.GET)  
	public @ResponseBody  
	Status getAllUserDetailsByBuilderId(@PathVariable("userId") int userId)throws Exception {		
		 List<User> userDetailsList = null;  
		 
		 try {			 
				 userDetailsList = userService.getAllUserDetailsByBuilderId(userId);
				 return new Status(userDetailsList, 200 , "");
		 
		     }catch(Exception e) {  
		    	 
			     e.printStackTrace();  
			     return new Status(100, e.toString());
		     }
	}*/
	
	@RequestMapping(value = "/getAllUserDetailsByCompanyId/{companyId}", method = RequestMethod.GET) 
	public @ResponseBody  
	Status getAllUserDetailsByCompanyId(@PathVariable("companyId") int companyId)throws Exception {		
		 List<User> userDetailsList = null;  
		 
		 try {			 
				 userDetailsList = userService.getAllUserDetailsByCompanyId(companyId);
				 return new Status(200 ,userDetailsList);
		 
		     }catch(Exception e) {  
		    	 
			     e.printStackTrace();  
			     return new Status(500, e.toString());
		     }
	}
	
	@RequestMapping(value="/updateUserProfileDetailsByUserId",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updateUserProfileDetailsByUserId(@RequestBody User user) throws IOException {
		try {	
			
				boolean isUpdated = userService.updateUserProfileDetailsByUserId(user);
				if(isUpdated){
					
					return new Status(200,"User Profile Details Updated Successfully !",user.getUserId());
				}else{
					
					return new Status(200,"User Profile Details Could Not Updated !",user.getUserId());
				}
							
		}catch(Exception ex){
			
				ex.printStackTrace();
				return new Status(500,ex.toString(),user.getUserId());
		}
    }
	
	
	@RequestMapping(value="/updateUserTokenAndDeviceId",method=RequestMethod.PUT)
	public @ResponseBody  
	Status updateUserTokenAndDeviceId(@RequestBody User user) throws IOException {
		
		try {	
			
				boolean isUpdated = userService.updateUserTokenAndDeviceId(user);
				if(isUpdated){
					
						logger.info("User Details Updated Successfully of userId ");
						return new Status(200,"User Details Updated Successfully ");
				}else{
					
						logger.info("User Details could not Updated ");
						return new Status(400,"User Details could not Updated");
				}
				
				
		}catch(Exception ex){
			
			ex.printStackTrace();
			return new Status(500,ex.toString());
			
		}
   }
	
}
