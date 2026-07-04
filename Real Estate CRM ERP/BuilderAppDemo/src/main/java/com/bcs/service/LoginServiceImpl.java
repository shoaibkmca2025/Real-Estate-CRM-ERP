package com.bcs.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.LoginDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.LoginDetails;
import com.bcs.model.User;
import com.bcs.utility.DateTimeUtil;

@Service
public class LoginServiceImpl implements LoginService {

	 @Autowired  
	 LoginDao logindao;
	 
	 @Autowired  
		private ActivityDao activityDao;
	 
	@Override
	public boolean checkEmail(String userEmail) {
		
		return logindao.checkEmail(userEmail);
	}

	@Override
	public boolean checkMobile(String userMobile) {
		
		return logindao.checkMobile(userMobile);
	}

	@Override
	public String getPasswordByEmailOrMobile(String emailOrMobile) {
	
		return logindao.getPasswordByEmailOrMobile(emailOrMobile);
	}

	@Override
	public User getUserById(int userId) throws Exception {
		
		return logindao.getUserById(userId);
	}

	@Override
	public int getUserIdByEmailAndPassword(String username, String password) throws Exception {
		
		return logindao.getUserIdByEmailAndPassword(username, password);
	}

	@Override
	public String getPasswordByUserId(int userId) {
		
		return logindao.getPasswordByUserId(userId);
	}

	@Override
	public void changeUserPassword(User user) {
		boolean isRecordUpdated = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordUpdated = logindao.changeUserPassword(user);
		
		if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(user.getUserId()); 
			 activityLog.setActivityDescription("Password changed");
			 activityLog.setProjectTranId(0);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
	}

	@Override
	public void addLoginDetails(LoginDetails loginDetails) {
		DateFormat	dateFormat = new SimpleDateFormat("dd-MM-yyy hh:mm:ss a");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);
		//String dateTime = DateTimeUtil.getSysDateTime();	
		loginDetails.setLoginDateTime(sysDate);
		loginDetails.setCreatedDateTime(sysDate);
		loginDetails.setUpdatedDateTime(sysDate);
		
		logindao.addLoginDetails(loginDetails);
	}
	
	@Override
	public void addLoginDetails(LoginDetails loginDetails, int userId) {
		// TODO Auto-generated method stub
		DateFormat	dateFormat = new SimpleDateFormat("dd-MM-yyy hh:mm:ss a");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);
		//String dateTime = DateTimeUtil.getSysDateTime();	
		loginDetails.setLoginDateTime(sysDate);
		loginDetails.setCreatedDateTime(sysDate);
		loginDetails.setUpdatedDateTime(sysDate);
		loginDetails.setLoginId(userId);
		
		logindao.addLoginDetails(loginDetails);
	}

	@Override
	public List<LoginDetails> getLoginReportsBySelectedDate(String createdDateTime) {

		List<LoginDetails> loginDetailsList = logindao.getLoginReportsBySelectedDate(createdDateTime);
		Iterator<LoginDetails> loginDetailsItr = loginDetailsList.iterator();
		List<LoginDetails> loginDetailsList1 = new ArrayList<>();
		while(loginDetailsItr.hasNext()){
			
			LoginDetails loginData = loginDetailsItr.next();
			
			int loginId = loginData.getLoginId();
			String createdDateTime1 = loginData.getCreatedDateTime();
			String[] dateTimeArray = createdDateTime1.split(" ");
			
			loginData.setLoginDataList(logindao.getLoginDataByIdAndDate(loginId, dateTimeArray[0]));
			
			loginDetailsList1.add(loginData);
			
		}
		 
		return loginDetailsList1;
	}

	@Override
	public int getuserTypeByEmailOrMobile(String userName) {
		// TODO Auto-generated method stub
		return logindao.getuserTypeByEmailOrMobile(userName);
	}

	@Override
	public int checkEmailOrMobile(String inputstr) {
	
		return logindao.checkEmailOrMobile(inputstr);
	}

	@Override
	public int getUserIdByEmailOrMobile(String userEmailOrMobile, int flag) throws Exception {
		// TODO Auto-generated method stub
		return logindao.getUserIdByEmailOrMobile(userEmailOrMobile, flag);
	}

	@Override
	public void updateRandomPassword(String userPassword, int userId) throws Exception {
		
		logindao.updateRandomPassword(userPassword, userId);
	}
	
}
