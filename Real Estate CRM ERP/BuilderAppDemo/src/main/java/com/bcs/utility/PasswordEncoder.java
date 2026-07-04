package com.bcs.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

	static final Logger logger = LoggerFactory.getLogger(PasswordEncoder.class);
	
	public String getHashPassword(String password) {  
	
		  String hashedPassword = null;
		  try{				  
		        if(password != ""){
		    
				  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
				  hashedPassword = passwordEncoder.encode(password);  
				    
				  return hashedPassword;
				  
			    }else{
				   logger.error("Password can not be null");				 
			    }
		        
		  } catch(Exception ex) {
				
			  //ex.printStackTrace();
			  logger.error("Error while password encryption.");
		  }
		  return hashedPassword;
	}		
} 