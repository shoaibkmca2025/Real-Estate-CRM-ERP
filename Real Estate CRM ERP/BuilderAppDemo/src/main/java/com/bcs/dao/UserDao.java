package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.User;

@Component
public interface UserDao {

	public List<User> getAllUserDetails();
	
	public boolean addUser(User user);
	
	public boolean updateUser(User user);
	
	public boolean updateUserStatus(int userId, int userStatus);

	//public List<User> getAllUserDetailsByBuilderId(int builderId);
	public List<User> getAllUserDetailsByCompanyId(int companyId);
	
	//public void updateAllSubusersStatus(int builderId, int userStatus);

	public boolean updateUserProfileDetailsByUserId(User user);
	
	public int getRecentUserId();

	//public void updateAllSubusersDetailsByBuilderId(int builderId, User user);

	public boolean updateUserStatusByScheduledTasks(List<String> companyIdsList);
	
	public void updateAllUsersStatusAfterAddPayment(int companyId);

	//public void updateAllSubusersStatusByScheduledTasks(List<String> userList);

	//public String updateCompanyProfileDetailsByCompanyId(CompanyProfile companyProfile);
	
	public boolean updateUserTokenAndDeviceId(User user);
	
	public List<User> getAllAdminList();

	

}
