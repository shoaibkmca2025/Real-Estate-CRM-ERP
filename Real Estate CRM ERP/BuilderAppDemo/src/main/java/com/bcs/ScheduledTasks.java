package com.bcs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bcs.model.SMS;
import com.bcs.service.PaymentDetailsService;
import com.bcs.service.ProjectService;
import com.bcs.service.SMSService;
import com.bcs.service.UserService;
import com.bcs.webservice.UserController;

@Component
public class ScheduledTasks {
	
	final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired  
	UserService userService;
	
	@Autowired  
	ProjectService projectService; 	
	
	@Autowired
	PaymentDetailsService paymentService;
	
	@Autowired  
	SMSService smsService; 	

	@Scheduled(cron = "59 59 23 * * *")
	public void performTaskUsingCron() {
		
		 List<String> projectIdsList = paymentService.getTodaysEndDateProjectIdList();
		 
		 if(projectIdsList.size()>0){
			 projectService.updateProjectStatusByScheduledTasks(projectIdsList);
			 //boolean isUpdate = userService.updateUserStatusByScheduledTasks(projectIdsList);
			/* if(isUpdate==true){
				 userService.updateAllSubusersStatusByScheduledTasks(companyIdsList);
			 }*/
		 }
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 logger.info("Todays EndDate CompanyIdsList "+projectIdsList);
		 logger.info("Task Scheduler performed using Cron at "+ output.format(new Date()));

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	@Scheduled(cron="0 0 8 * * *")
	public void eventScheduleUsingCron() {
		
		try{
				List<SMS> smsList = smsService.getAllScheduleSMSDetails();
				
				for(int i = 0; i< smsList.size(); i++){
					
						SMS sms = smsList.get(i);			
						String response = SendSMS.sendSms(sms.getMobileNos(), sms.getSmsText());
						JSONParser parser = new JSONParser();
						JSONObject json = (JSONObject) parser.parse(response);
						logger.info("Response: "+ response);
						
						String[] mobileNoArray = sms.getMobileNos().split(",");
						int dndMobileNoCount = 0;
						
						List<Object> obj =  (List<Object>) json.get("warnings");
						for(int j=0 ;j< obj.size(); j++){
							
							JSONObject row = (JSONObject) obj.get(j);
							String mobileNo = row.get("numbers").toString().substring(2, 12);
							dndMobileNoCount++;
							List list = new ArrayList(Arrays.asList(mobileNoArray));
							list.remove(mobileNo);
							
							mobileNoArray = (String[]) list.toArray(new String[list.size()]);
						}

						sms.setMobileNos(String.join(",", mobileNoArray));
						
						int msgLength = sms.getSmsText().length();
						int failedMessageCost = (int) (dndMobileNoCount*(Math.ceil(msgLength/160)));
						if(json.get("status").equals("success")){					
							smsService.updateSendTimeAndSendStatusByScheduler(sms, failedMessageCost);					
						}
				}
				
				 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				// logger.info("Todays EndDate CompanyIdsList "+projectIdsList);
				 logger.info("Task Scheduler performed using Cron at "+ output.format(new Date()));
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
