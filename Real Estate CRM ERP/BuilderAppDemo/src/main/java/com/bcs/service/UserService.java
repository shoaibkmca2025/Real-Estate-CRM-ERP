package com.bcs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.CompanyProfile;
import com.bcs.model.User;

@Component
public interface UserService {

	public List<User> getAllUserDetails();
	
	public boolean addUser(User user) throws ParseException;
	
	public boolean updateUser(User user);
	
	public boolean updateUserStatus(int userId, int userStatus);
	
	//public List<User> getAllUserDetailsByBuilderId(int builderId);
	public List<User> getAllUserDetailsByCompanyId(int companyId);

	//public void updateAllSubusersStatus(int builderId, int userStatus);

	public boolean updateUserProfileDetailsByUserId(User user);

	//public void updateAllSubusersDetailsByBuilderId(int builderId, User user);
	
	public boolean updateUserStatusByScheduledTasks(List<String> companyIdsList);

	
	//public void updateAllSubusersStatusByScheduledTasks(List<String> userList);

	public boolean updateUserTokenAndDeviceId(User user); 
	
	public String[] getAllAdminList();
	
}
