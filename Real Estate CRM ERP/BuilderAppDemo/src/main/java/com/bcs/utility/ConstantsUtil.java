package com.bcs.utility;

public class ConstantsUtil {
	
	/***********	for localhost		 ***********/

	//public static final String FILE_SAVE_LOCATION   =   "C:\\ysmvts\\sms_images\\BuilderAppUat\\";	
	//public static final String FILE_SAVE_LOCATION   =   "//home//nilson//1-Work//Real Estate CRM//Builder App//BuilderAppUat//";	
	//public static final String SERVER_IMG_LOCATION  =   "http://localhost:8080/images/BuilderAppUat/";

	/***********	for uat       		***********/
	public static final String FILE_SAVE_LOCATION     =  "C:\\Users\\ysmvts\\sms_images\\BuilderAppUat\\";
	public static final String SERVER_IMG_LOCATION    =  "http://132.148.21.45:8080/images/BuilderAppUat/";
	
	/***********   for Live Application 	***********/
	//public static final String FILE_SAVE_LOCATION   =  "C:\\Users\\ysmvts\\sms_images\\BuilderApp\\";
	//public static final String SERVER_IMG_LOCATION  =  "http://132.148.21.45:8080/images/BuilderApp/";
	
	public static final String FROM                 =  "savita@ysmsoftware.com";
	 
	public static final int sessionInvalidateDuration = 86400; // in ms (24 hrs )
	
	public static final int    MIN_LEN             =  6;				
	public static final int    MAX_LEN             =  8;
    public static final int    NO_OF_CAPS_ALPHA    =  2;						 
    
    public static final int    NO_OF_DIGITS        =  2;					
    public static final int    NO_OF_SPL_CHARS     =  2;	
    public static final String LINE_CHART_URL = "http://132.148.21.45:8080/BuilderAppAngular/getAllDashboadDetails/";
	
}