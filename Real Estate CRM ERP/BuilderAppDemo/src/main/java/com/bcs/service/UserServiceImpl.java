package com.bcs.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.CompanyProfileDao;
import com.bcs.dao.PaymentDetailsDao;
import com.bcs.dao.UserDao;
import com.bcs.dao.UtilityDao;
import com.bcs.model.User;
import com.bcs.utility.DateTimeUtil;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userdao; 

	@Autowired  
	private ActivityDao activityDao;

	@Autowired
	UtilityDao utilityDao;
	
	@Autowired
	PaymentDetailsDao paymentDao;
	
	@Autowired
	CompanyProfileDao companyDao;

	
	@Override
	public List<User> getAllUserDetails() {
		
		List<User> userList = userdao.getAllUserDetails();
		
		for(int i= 0; i< userList.size() ; i++){
			
			if(userList.get(i).getUserType() == 1){
				
				userList.get(i).setUserTypeName("Admin");
				
			}else if(userList.get(i).getUserType() == 2){
				
				userList.get(i).setUserTypeName("Builder");
				
			}else if(userList.get(i).getUserType() == 3){
				
				userList.get(i).setUserTypeName("User");
			}
			
		}
		return userList;
	}


	@Override
	public boolean addUser(User user) throws ParseException {
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		 user.setCreatedDatetime(dateTime); 
		 user.setUpdatedDatetime(dateTime);
		 user.setUserStatus(1);
		
		return userdao.addUser(user);
		
	}


	@Override
	public boolean updateUser(User user) {
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		// user.setCreatedDatetime(dateTime); 
		 user.setUpdatedDatetime(dateTime);
		 
		 return userdao.updateUser(user);
	}


	@Override
	public boolean updateUserStatus(int userId, int userStatus) {
		
		return userdao.updateUserStatus(userId, userStatus);		
	}


	/*@Override
	public List<User> getAllUserDetailsByBuilderId(int builderId) {
		// TODO Auto-generated method stub
		List<User> userDetailsList = userdao.getAllUserDetailsByBuilderId(builderId);
		return userDetailsList;
	}*/
	
	@Override
	public List<User> getAllUserDetailsByCompanyId(int companyId) {
		
		List<User> userDetailsList = userdao.getAllUserDetailsByCompanyId(companyId);
	
			for(int i= 0; i< userDetailsList.size() ; i++){
				
				if(userDetailsList.get(i).getUserType() == 1){
					
					userDetailsList.get(i).setUserTypeName("Admin");
					
				}else if(userDetailsList.get(i).getUserType() == 2){
					
					userDetailsList.get(i).setUserTypeName("Builder");
					
				}else if(userDetailsList.get(i).getUserType() == 3){
					
					userDetailsList.get(i).setUserTypeName("User");
				}
				
			}
		return userDetailsList;
	}


	/*@Override
	public void updateAllSubusersStatus(int builderId, int userStatus) {
		
		userdao.updateAllSubusersStatus(builderId, userStatus);	
	}*/


	@Override
	public boolean updateUserProfileDetailsByUserId(User user) {
		 String dateTime = DateTimeUtil.getSysDateTime();
		 user.setUpdatedDatetime(dateTime);
		 String LogoAttachment = null;
		 
		return userdao.updateUserProfileDetailsByUserId(user);
		 
		/* LogoAttachment =userdao.updateUserProfileDetailsByUserId(user);
		 
		if(LogoAttachment!=null || LogoAttachment!=""){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(user.getUserId()); 
			 activityLog.setActivityDescription("Profile Details Updated");
			 activityLog.setProjectTranId(0);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return LogoAttachment;*/
	}


/*	@Override
	public void updateAllSubusersDetailsByBuilderId(int builderId, User user) {

		userdao.updateAllSubusersDetailsByBuilderId(builderId,user);		
	}*/


	@Override
	public boolean updateUserStatusByScheduledTasks(List<String> companyIdsList) {
		boolean isUpdate = userdao.updateUserStatusByScheduledTasks(companyIdsList);
		return isUpdate;
	}


	@Override
	public boolean updateUserTokenAndDeviceId(User user) {
		boolean isUpdate = userdao.updateUserTokenAndDeviceId(user);
		return isUpdate;
	}


	@Override
	public String[] getAllAdminList() {			
		
		List<User> adminList = userdao.getAllAdminList();


		Iterator<User> userItr = adminList.iterator();
		List<String> tokenList = new ArrayList<>();
		String[] tokenArray = null;
		
		if(!adminList.isEmpty()){
			while (userItr.hasNext()) {
				User user = userItr.next();
				String tokenName = user.getTokenName();
				tokenList.add(tokenName);		
				}
			
			tokenArray = (String[])tokenList.toArray(new String[tokenList.size()]);
			
		}		
		
		return tokenArray;
	}

	
	/*@Override
	public String[] getAllAdminTokenList() {
		
		List<User> adminList = loginDao.getAllAdminList();
		System.out.println("adminList: "+adminList);
		Iterator<User> userItr = adminList.iterator();
		List<String> tokenList = new ArrayList<>();
		String[] tokenArray = null;
		
		if (!adminList.isEmpty()) {
				while (userItr.hasNext()) {
					User user = userItr.next();
					String tokenName = user.getTokenName();
					tokenList.add(tokenName);
				}
				System.out.println("tokenList: "+tokenList);
				tokenArray = (String[])tokenList.toArray(new String[tokenList.size()]);
				System.out.println("tokenArray: "+tokenArray);
		}
		
		return tokenArray;
	}*/

	/*@Override
	public void updateAllSubusersStatusByScheduledTasks(List<String> userList) {
		
		userdao.updateAllSubusersStatusByScheduledTasks(userList);	
	}*/


}
