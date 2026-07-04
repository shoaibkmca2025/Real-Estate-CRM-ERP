package com.bcs.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class DateTimeUtil {
	
	public static String getSysDateTime(){
		

        LocalDateTime localDate = LocalDateTime.now(ZoneId.of("GMT+05:30"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss a");
		String	sysDate = localDate.format(formatter);

	/*	DateFormat	dateFormat = new SimpleDateFormat("dd/MM/yyy hh:mm:ss a");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);*/
		
		return sysDate;
	}
    
    public static String getSysDate(){
		
    	
    	LocalDateTime localDate = LocalDateTime.now(ZoneId.of("GMT+05:30"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyy");
		String	sysDate = localDate.format(formatter);
		
		/*DateFormat	dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);*/
		
		return sysDate;
	}
        
    public static String convertDateFormat(String dateform) throws ParseException{
		
    	DateFormat	dateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
    	Date datefm = dateFormat1.parse(dateform);
		DateFormat	dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String	sysDate    = dateFormat.format(datefm);
		
		return sysDate;
	}
    
    public static int getCurrentMonth(){
		
    	int month = 0;
		DateFormat	dateFormat = new SimpleDateFormat("MM");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);
		month = Integer.parseInt(sysDate);
		
		return month;
	}
    
    public static String getCurrentMonthYear(){
		
		DateFormat	dateFormat = new SimpleDateFormat("MM/yyyy");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);
		
		return sysDate;
	}
    
    public static String getMonthName(int number){
		String formatedNumber = "";
		switch(number){ 
			 case 1 :
			            formatedNumber = "January"; 
			            break;
	         case 2 :
		        	 	formatedNumber = "February"; 
			            break;
	         case 3 :
		        	 	formatedNumber = "March"; 
			            break;
	         case 4 :
		        	 	formatedNumber = "April"; 
			            break;
	         case 5 :
		        	 	formatedNumber = "May"; 
			            break;
	         case 6 :
		        	 	formatedNumber = "June"; 
			            break;
	         case 7 :
		        	 	formatedNumber = "July"; 
			            break;
	         case 8 :
		        	 	formatedNumber = "August"; 
			            break;
	         case 9 :
		        	 	formatedNumber = "September"; 
			            break;   
			 case 10 :
				   	 	formatedNumber = "October"; 
						break;       
			 case 11 :
					    formatedNumber = "November"; 
						break;     
			 case 12 :
		        	 	formatedNumber = "December"; 
			            break;      			
	        }
		  
		return formatedNumber.trim();  
	}
    
    public static LinkedHashMap<Integer, String> getMonths(){
		
		LinkedHashMap<Integer, String> months = new LinkedHashMap<Integer, String>();
		 
			months.put(1, "January");
			months.put(2, "February");
			months.put(3, "March");
			months.put(4, "April");
			months.put(5, "May");
			months.put(6, "June");
			months.put(7, "July");
			months.put(8, "August");
			months.put(9, "September");
			months.put(10, "October");
			months.put(11, "November");
			months.put(12, "December");
		
		return months;
	}
    
    public static List<Date> getDatesList(String str_date, String end_date) throws ParseException {
    	
    	List<Date> dateList = new ArrayList<Date>();
    	
    	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // hh:mm:ss a
    	
		Date  startDate = (Date)formatter.parse(str_date); 
		Date  endDate   = (Date)formatter.parse(end_date);
		
		long interval = 24*1000 * 60 * 60; // 1 hour in millis
		long endTime  = endDate.getTime() ; // create your endtime here, possibly using Calendar or Date
		long curTime  = startDate.getTime();
		
		while (curTime <= endTime) {
			dateList.add(new Date(curTime));
		    curTime += interval;
		}
		/*for(int i=0;i<dateList.size();i++){
		    Date lDate =(Date)dateList.get(i);
		    String ds = formatter.format(lDate);    
		    System.out.println(" Date is ..." + ds);
		}*/
    	
    	return dateList;
    }

	public static String getSysDateTimeFormat() {
		
		DateFormat	dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);
		
		return sysDate;
	}
	
	public static String getSysDateTimeFormatWithMiliseconds() {
		
		DateFormat	dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS a");
		Date date  = new Date();
		String	sysDate    = dateFormat.format(date);
		
		return sysDate;
	}
}