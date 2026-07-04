package com.bcs.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.LoginDetails;
import com.bcs.model.User;

@Component
public interface LoginService {
	
	public boolean checkEmail(String userEmail);

	boolean checkMobile(String userMobile);
	
	public String getPasswordByEmailOrMobile(String emailOrMobile);
	
	User getUserById(int userId) throws Exception;
	
	int getUserIdByEmailAndPassword(String username, String password) throws Exception;
	
	public String getPasswordByUserId(int userId);
	
	public void changeUserPassword(User user);
	
	void addLoginDetails(LoginDetails loginDetails);

	public void addLoginDetails(LoginDetails loginDetails, int userId);
	
	List<LoginDetails> getLoginReportsBySelectedDate(String createdDateTime);
	
	int getuserTypeByEmailOrMobile(String userName);
	
	public int checkEmailOrMobile(String inputstr);
	
	int getUserIdByEmailOrMobile(String userEmailOrMobile, int flag) throws Exception;
	
	void updateRandomPassword(String userPassword, int userId) throws Exception;
	
}
