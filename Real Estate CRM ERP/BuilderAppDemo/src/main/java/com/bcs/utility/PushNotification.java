package com.bcs.utility;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

	@Component
	public class PushNotification {
	
	public final static String AUTH_KEY_FCM  = "AAAAfYrruGc:APA91bEIqKxb_ZjAqQW-0GQ409pkwAxNClOSRDLZmUHrxN3UgW3YRpVlqo0dQWhlXsrxj1cHZ-NZDSJVlBaZyRKSzTD7Qy1SCYQJbO7XU5luQG0IMuMSbJV8aFaRBCg2IxevEBuOCboM"; //sms key
	public final static String API_URL_FCM   = "https://fcm.googleapis.com/fcm/send";
	
	public void pushFCMNotification(String[] userDeviceIdKey, String subject, String body, String creator, int notifyType, int recentNotifyId) throws Exception {
	
	String authKey = AUTH_KEY_FCM;     // You FCM AUTH key
	String FMCurl   = API_URL_FCM; 
	
/*	System.out.println("Keys : "+userDeviceIdKey);
	for(int i=0; i<userDeviceIdKey.length; i++){
	System.out.println(" i: " +userDeviceIdKey[i]);
	}*/
	
	URL url = new URL(FMCurl);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	conn.setUseCaches(false);
	conn.setDoInput(true);
	conn.setDoOutput(true);
	
	conn.setRequestMethod("POST");
	conn.setRequestProperty("Authorization","key="+authKey);
	conn.setRequestProperty("Content-Type","application/json;charset=UTF-8");
	
	JSONObject json = new JSONObject(); 
	json.put("registration_ids",userDeviceIdKey); //registration_ids
	JSONObject info = new JSONObject();
	info.put("title", subject);   
	//info.put("subject", subject); 
	info.put("body", body); 
	info.put("creator", creator);
	info.put("notifyType", notifyType);	
	info.put("recentNotifyId", recentNotifyId);	
	json.put("data", info);
	//json.put("notification", info);
	
	OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	System.out.println(json.toString());

	wr.write(json.toString());   //json.toString()
	wr.flush();
	
	conn.getInputStream();
	
	//System.out.println("completed");
	}
	
/*	public static void main(String args[]) throws Exception {
	
	String[] userDevice = {"esZuGftZ45Y:APA91bF8sIftW70PRw-bBFtHLE1f0eR8xBANF7gqVITS3sQZuqRgHxXB5Qs3kqVjgixQM76dL9cuBOeCD3NoZUnHK2LhC2wXpWmeBo8mQk11W2a5K0S2n0Pvhuhqy7orpUwLBScA8CY_esZuGftZ45Y:APA91bF8sIftW70PRw-bBFtHLE1f0eR8xBANF7gqVITS3sQZuqRgHxXB5Qs3kqVjgixQM76dL9cuBOeCD3NoZUnHK2LhC2wXpWmeBo8mQk11W2a5K0S2n0Pvhuhqy7orpUwLBScA8CY_"};
	
	PushNotification PushNotification = new PushNotification();
	PushNotification.pushFCMNotification(userDevice, null, null, null, 0);
	}*/
	}
	
	