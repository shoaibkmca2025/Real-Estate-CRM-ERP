/*package com.bcs.utility;

public class MailTest {
	
	
	

	public static void main(String[] args) {
		 String[] attachFiles = new String[3];
	        attachFiles[0] = "C:/ysmvts/sms_images/BuilderAppUat/4_longi.r.pdf";
	        attachFiles[1] = "C:/ysmvts/sms_images/BuilderAppUat/1527500371790_Invoice-26-07-Jul-2017.pdf";
	        attachFiles[2] = "C:/ysmvts/sms_images/BuilderAppUat/Invoice-26-07-Jul-2017.pdf";
	        
	        SendEMail sendMail=new SendEMail();
	        String to          		=  "swapnil002ahire@gmail.com";
			String subject    		=  "Project Details and Documents";
			String messageBody		=  "";
		
				
				boolean isMailSent = sendMail.sendMultipleAttachments(ConstantsUtil.FROM, to, subject, messageBody, attachFiles);
				System.out.println("isMailSent"+isMailSent);
	}

}
*/