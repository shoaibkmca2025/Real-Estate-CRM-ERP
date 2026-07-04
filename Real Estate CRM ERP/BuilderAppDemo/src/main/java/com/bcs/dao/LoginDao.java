package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.LoginDetails;
import com.bcs.model.User;

@Component
public interface LoginDao {

	public boolean checkEmail(String userEmail);

	boolean checkMobile(String userMobile);
	
	public String getPasswordByEmailOrMobile(String emailOrMobile);

	User getUserById(int userId) throws Exception;

	int getUserIdByEmailAndPassword(String username, String password) throws Exception;
	
	public String getPasswordByUserId(int userId);
	
	public boolean changeUserPassword(User user);

	void addLoginDetails(LoginDetails loginDetails);

	List<LoginDetails> getLoginReportsBySelectedDate(String createdDateTime);

	List<LoginDetails> getLoginDataByIdAndDate(int loginId, String createdDateTime);

	int getuserTypeByEmailOrMobile(String userName);
	
	int checkEmailOrMobile(String inputstr);
	
	int getUserIdByEmailOrMobile(String userEmailOrMobile, int flag) throws Exception;

	void updateRandomPassword(String userPassword, int userId) throws Exception;
	
	
}
