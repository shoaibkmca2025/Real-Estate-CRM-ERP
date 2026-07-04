package com.bcs.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CommonUtil {

	/*private static FileInputStream fileInputStreamReader;

	public static String encodeFileToBase64Binary(File file){
	    String encodedfile = null;
	    try {
	        fileInputStreamReader = new FileInputStream(file);
	        byte[] bytes = new byte[(int)file.length()];
	        fileInputStreamReader.read(bytes);
	        encodedfile = new String(Base64.getEncoder().encode(bytes), "UTF-8");
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	
	    return encodedfile;
	}
	

	public static void decodeFileToBase64Binary(String binarySourceData, String fileName) {		
		
		byte[] byteArray      = null;

		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byteArray = decoder.decodeBuffer(binarySourceData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		
		try {
			bis.read(byteArray);
			//image1 = ImageIO.read(bis);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// write the byteArray to a file
		String filePath = ConstantsUtil.FILE_SAVE_LOCATION + fileName ; 
		
	     try {
	    	 File outputfile = new File(filePath);
	    	 FileOutputStream fileOuputStream = new FileOutputStream(outputfile);
			 fileOuputStream.write(byteArray);
			 fileOuputStream.close();
	    	 
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
	}*/

	public static HashMap<Integer, String> getProjectStatus(){
			
		HashMap<Integer, String> projectStatus = new HashMap<Integer, String>();
			 
		projectStatus.put(1, "Upcoming");
		projectStatus.put(2, "Ongoing");
		projectStatus.put(3, "Completed");
			
		return projectStatus;
	}
	
	public static int checkEmailOrMobile(String inputstr){
    	
   	 int result = 0;
   	 if(Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(inputstr).matches()){
       	 result = 2;             
        } else if(inputstr.chars().allMatch(Character::isDigit) && inputstr.trim().length() == 10){
        	 result = 1;         
        }          
        return result;
   }
	
    public static LinkedHashMap<Integer, String> getInstallmentsTenure(){
		
		LinkedHashMap<Integer, String> months = new LinkedHashMap<Integer, String>();
		 
			months.put(1, "1 Slab");
			months.put(2, "2 Slabs");
			months.put(3, "3 Slabs");
			months.put(4, "4 Slabs");
			months.put(5, "5 Slabs");
			months.put(6, "6 Slabs");
			months.put(7, "7 Slabs");
			months.put(8, "8 Slabs");
			months.put(9, "9 Slabs");
			months.put(10, "10 Slabs");
			months.put(11, "11 Slabs");
			months.put(12, "12 Slabs");
		/*	months.put(13, "13 Month");
			months.put(14, "14 Months");
			months.put(15, "15 Months");
			months.put(16, "16 Months");
			months.put(17, "17 Months");
			months.put(18, "18 Months");
			months.put(19, "19 Months");
			months.put(20, "20 Months");
			months.put(21, "21 Months");
			months.put(22, "22 Months");
			months.put(23, "23 Months");
			months.put(24, "24 Months");*/
		
		return months;
	}
    

	public static String formatIndianCommaSeparated(double rupee){
	    // remove sign if present
	    String raw = String.valueOf(Double.valueOf(rupee).intValue());
	    
	    int numDigits = raw.length();
	    StringBuilder sb = new StringBuilder(raw);
	    // Reverse the string to start from right most digits
	    sb = sb.reverse();
	    // Counter to keep track of number of commas placed
	    
	    int commas = 0;
	    for (int i=0; i<numDigits; i++){
	        // Insert a comma if i is in the range [3, 5, 7, 9, ...)
	        if (i % 2 == 1 && i != 1 ){	        	
	            sb.insert(i+commas, ",");
	            commas++;
	        }
	    }
	    // Reverse the string back to get original number
	    String sign = (rupee < 0) ? "-" : "";
	    return sign + sb.reverse().toString();
	}
	
}