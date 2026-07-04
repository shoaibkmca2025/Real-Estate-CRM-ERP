package com.bcs.webservice;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcs.model.CompanyProfile;
import com.bcs.model.LoginDetails;
import com.bcs.model.Status;
import com.bcs.model.User;
import com.bcs.service.CompanyProfileService;
import com.bcs.service.LoginService;
import com.bcs.service.UserService;
import com.bcs.utility.CommonUtil;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.RandomPasswordGenerator;
import com.bcs.utility.SendEMail;
import com.bcs.utility.PasswordEncoder;

@Controller
public class LoginWebController {
	
	@Autowired 
	LoginService loginservice;
	
	@Autowired 
	CompanyProfileService companyService;
	
	@Autowired
	RandomPasswordGenerator randomPasswordGenerator;
	
	@Autowired
	SendEMail sendMail;	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;

	@Autowired
	com.bcs.security.JwtService jwtService;

	final static Logger logger = LoggerFactory.getLogger(LoginWebController.class);
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	
/*	@RequestMapping(value = "/loginWebService", method = RequestMethod.POST)					
	public @ResponseBody
	Status loginUser(@RequestBody User user, HttpSession session) throws Exception {
		
		String emailOrMobile = user.getUserEmail();
		String password      = user.getUserPassword();
		 
		 		String storedPassword = loginservice.getPasswordByEmailOrMobile(emailOrMobile);
					
					if(storedPassword != null && storedPassword.equals(password)){
						
						 int userId = loginservice.getUserIdByEmailAndPassword(emailOrMobile, storedPassword);
						 
						 user = loginservice.getUserById(userId);
						 String userName  =  user.getUserName();
						 
						 session.setAttribute("userId", userId);
						 session.setAttribute("userName", userName);
						
						 return new Status(200, "Login Successful!",userName);  
					 }else{
						 
						 return new Status(404, "Invalid email or password. Try Again. !");  								
						
					 }
	}*/
	
	@PostMapping("loginWebService")	
	public @ResponseBody Status login(@RequestBody User user) throws Exception {
		
		String newEmailOrMobile = user.getNewEmailOrMobile();
    	logger.info("newEmailOrMobile : "+newEmailOrMobile);
    	String password         = user.getUserPassword();
    	logger.info("password : "+password);
    	 boolean flag   = false; 
    	 int    userId  = 0;
    	
    	int    changeType       = CommonUtil.checkEmailOrMobile(newEmailOrMobile);
    	logger.error("changeType : "+changeType);
    	
    	/* if(changeType == 1) {	
        	
			if(loginservice.checkMobile(newEmailOrMobile)) {	
	    		  flag = true;
			}
			
	    } else*/  if(changeType == 2) { 
	    	
	    	if(loginservice.checkEmail(newEmailOrMobile)) {
	    		  flag   = true;
			}		
	    	
	   }  else if(changeType == 0) {
		    logger.error("Invalid email or mobile number. Try Again.");
	        return new Status(1004, "Invalid email or mobile number.");
	    } 
        
        	
        	String storedPassword   = loginservice.getPasswordByEmailOrMobile(newEmailOrMobile);			
			logger.error("storedPassword : "+storedPassword);
			
			if(flag == true){
				
				    userId           = loginservice.getUserIdByEmailAndPassword(newEmailOrMobile,storedPassword);
			}
			
			if(userId == 0){
				logger.error("userId == 0");
				return new Status(404, "Wrong email/mobile or password.");
			}
			
			//int usertype   = loginservice.getuserTypeByEmailOrMobile(newEmailOrMobile);
			//logger.info("usertype =="+ usertype);
			
			if(storedPassword == null || storedPassword.equals("")){
				 logger.error("null storedPassword");
				 return new Status(404, "Wrong email/mobile or password.");
			}
			
			 try {  
				    if(flag == true){
				    	
				    	if(storedPassword != null && bCryptPasswordEncoder.matches(password, storedPassword)){   /* && storedPassword.equals(password) && bCryptPasswordEncoder.matches(password, storedPassword)*/ 
			    	         							
				    		User users      = loginservice.getUserById(userId);	
				    		
							logger.info("Users: "+users);
							int status      = users.getUserStatus();
							
								if(status == 1){
									
									logger.info("Login Successful"); 
									if(users.getUserType()==2 || users.getUserType()==3){ 
										CompanyProfile companyProfile = companyService.getCompanyDetailsByUserId(userId);	
										users.setCompanyId(companyProfile.getCompanyId());
										users.setCompanyName(companyProfile.getCompanyName());
										users.setWebsite(companyProfile.getWebsite());
										users.setLogoPath(companyProfile.getLogoPath());
										users.setIsUpdated(companyProfile.getIsUpdated());
										users.setMarketedBy(companyProfile.getMarketedBy());
										users.setMarketedByWebsite(companyProfile.getMarketedByWebsite());
									}
									Status loginStatus = new Status( 200, "Login Successful",users,ConstantsUtil.SERVER_IMG_LOCATION);
									java.util.Map<String, Object> claims = new java.util.HashMap<>();
									claims.put("userType", users.getUserType());
									claims.put("companyId", users.getCompanyId());
									loginStatus.setToken(jwtService.generateToken(users.getUserId(),
											users.getUserEmail() != null ? users.getUserEmail() : newEmailOrMobile, claims));
									return loginStatus;
											
								}else{	
									
									logger.error(String.valueOf(status));
									logger.error("Inactive Member");
									return new Status(userId, 401, "You are inactive member.");  
								}		
						} else {
								logger.error("Invalid Username or password. Try Again.");
								return new Status(userId, 401, "Invalid Username or password. Try Again.");  
						}		    	
				    }
				    		    
			   }catch(Exception ex){
				   
				   	 ex.printStackTrace();
				   	 logger.error("Exception @Login"+ex.toString());
				   	 return new Status( 404, "Exception @Login"+ex.toString());
			   }
			 
			 return new Status(404, "Wrong email/mobile or password.");
	     }
	
	 @RequestMapping(value = "/addLoginDetails", method = RequestMethod.POST) 
	 public @ResponseBody Status addLoginDetails(@RequestBody LoginDetails loginDetails, BindingResult result) { 
		 
		
		 try { 
		
		  	loginservice.addLoginDetails(loginDetails);  
		  	return new Status(200, "Login Details added Successfully !");  

	     } catch (Exception e) {  
	    	 		e.printStackTrace();  
	    	 		return new Status(0, e.toString());  
	     }   
	}
	
	@RequestMapping(value = "/getLoginReportsBySelectedDate", method = RequestMethod.POST)  
	public @ResponseBody  
	List<LoginDetails> getLoginReportsBySelectedDate(@RequestBody LoginDetails loginDetails, BindingResult result)throws Exception {		
		 List<LoginDetails> loginDetailsList = null;  
		 try {  
			
			 loginDetailsList = loginservice.getLoginReportsBySelectedDate(loginDetails.getCreatedDateTime());
		 
		     } catch (Exception e) {  
		     e.printStackTrace();  
		  }

		 return loginDetailsList;  
	}
	
	/*@RequestMapping(value="logout",method=RequestMethod.GET)
	public @ResponseBody Status logout (HttpSession session) {	
			
		session.invalidate();
			
		return new Status(200,"Logout Successfully");

    }*/
	
	 /* To change Password */
	@PostMapping("changePassword")	
	public @ResponseBody Status changePassword(@RequestBody User user) {
					
				try {	
						 String oldpassword     = user.getUserPassword();
						 String newPassword = user.getNewPassword();
						 
						 logger.info("Old Password: "+oldpassword);
						 
						 int userId      = user.getUserId();
						 String password = loginservice.getPasswordByUserId(userId);
						 
						 if(bCryptPasswordEncoder.matches(oldpassword,password)){	/*(!password.equals(oldpassword) )*/
							 
							 user.setUserId(userId);
							 String userPassword = passwordEncoder.getHashPassword(newPassword);
							 user.setNewPassword(userPassword);
						     loginservice.changeUserPassword(user);

							 return new Status(200,"Password changed successfully.");
							 
						 }else{
							 logger.error("Old Password Does not match... ");
							 return new Status(400, "Old Password Does not match... ");
						 }
						   
				 }catch(Exception ex){		    	 
						 logger.error("Exceptions while Change Password : "+ex.toString());	
						 return new Status(500 , ex.toString());
				 }
				
	}
	
	 /*	Forgot Passowrd */
	 @PostMapping("forgotPassword")	
	 public @ResponseBody Status forgotPassword(@RequestBody User user) throws Exception {

        String newEmailOrMobile   =  user.getNewEmailOrMobile(); 	       
        int    userId             =  0;
        int    emailOrMobileFlag  =  0;
      
        try {     
        	
      	     emailOrMobileFlag    =  loginservice.checkEmailOrMobile(newEmailOrMobile);
      	     
      	     if(emailOrMobileFlag == 0){      	    	     
      	    	 	logger.error("Invalid email address. Try Again.");
			        return new Status(401, "Invalid email address.");
      	     }
      	     
        }catch(Exception ex) {		    	
      	  			logger.error("Exception while"+ex.toString());
      	  			return new Status(401, "Invalid email address."); 
	    }
        
        try{ 	
      	     	userId = loginservice.getUserIdByEmailOrMobile(newEmailOrMobile, emailOrMobileFlag);
      	     	
        }catch(Exception ex) {	
      	 
      	        logger.error("Exception while verifying User registered or not : "+ex.toString());
			    return new Status(401, "User not registered."); 
		}
        
		  try {			  	 
			     String randomPassword      = 	randomPasswordGenerator.generateRandomPassword(ConstantsUtil.MIN_LEN, ConstantsUtil.MAX_LEN, ConstantsUtil.NO_OF_CAPS_ALPHA, ConstantsUtil.NO_OF_DIGITS, ConstantsUtil.NO_OF_SPL_CHARS);
			     String userPassword        = 	passwordEncoder.getHashPassword(randomPassword);
			     if(userId != 0){
				   
				     String to              = 	loginservice.getUserById(userId).getUserEmail();
					 String subject         = 	"Password Reset Successfuly";
					 String messageBody     = 	"Your new Password is: "+randomPassword;
					 
					 loginservice.updateRandomPassword(userPassword, userId);
					 sendMail.sendMail(ConstantsUtil.FROM, to, subject, messageBody);	
					
					 logger.info("Random Password Generated");
					 return new Status(200, "Random password generated Successfully"); 
					 
			     }else{
			    	 
			    	 logger.error("User not registered. ");
			    	 return new Status(401, "User not registered."); 
			     }
			  
		  }catch(Exception ex) {
		    	 ex.printStackTrace();
			     logger.error("Exception @Generating Random Password"+ex.toString());
			     return new Status( 500, "Exception @Generating Random Password"+ex.toString()); 
		  }
	 }
	 
	 
		@RequestMapping(value="/logout",method=RequestMethod.PUT)
		public @ResponseBody  
		Status updateUserTokenAndDeviceId(@RequestBody User user) throws IOException {
			
			try {	
				
					boolean isUpdated = userService.updateUserTokenAndDeviceId(user);
					if(isUpdated){
						
							logger.info("Logout Successfully");
							return new Status(200,"User Logout Successfully");
					}else{
						
							logger.info("User could not Logout");
							return new Status(400,"User could not Logouts");
					}					
					
			}catch(Exception ex){
				
				ex.printStackTrace();
				return new Status(500,ex.toString());
				
			}
	   }
}
