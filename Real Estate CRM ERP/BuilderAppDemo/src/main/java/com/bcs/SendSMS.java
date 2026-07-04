package com.bcs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
public class SendSMS {
	
	public static String sendSms(String mobileNos, String textMessage) {
		try {
			
			System.out.println("mobileNos: "+mobileNos);
			System.out.println("textMessage: "+textMessage);
			// Construct data
			String apiKey = "apikey=" + "nxx7z0o2zTU-3LQ6A5bfqGBhb46RQ6hRCjYSEKzv6C";
			String message = "&message=" +textMessage;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + mobileNos;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	
	/*public static String checkBalance() {
		try {
			// Construct data
			String apiKey = "apikey=" + "nxx7z0o2zTU-3LQ6A5bfqGBhb46RQ6hRCjYSEKzv6C";
			
			// Send data
			String data = apiKey;
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/balance/?").openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	
	 public static void main(String[] args){
		
		String msg = SendSMS.sendSms("8605421036,9403060654", "Susan from Private Real Estate will show you the property on Thursday at 2pm. Please reply to this number if you need to change the time.");
		System.out.println("Message: "+msg);
		System.out.println(SendSMS.checkBalance());
	} */
}
