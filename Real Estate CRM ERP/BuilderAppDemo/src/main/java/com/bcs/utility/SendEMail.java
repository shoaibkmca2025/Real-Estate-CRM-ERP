package com.bcs.utility;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.service.DisbursementService;

@Component
	public class SendEMail {
		   
	    private String host;	
	    private Properties properties;	
	    private MimeMessage message;
	    private BodyPart messageBodyPart;
	    private Multipart multipart;
	
	    private Authenticator authenticator;
	    
	    @Autowired
		DisbursementService disbursementService;
	    
	    final static Logger logger = LoggerFactory.getLogger(SendEMail.class);	
	         
	    public SendEMail () {
	    	   
	        host = "smtpout.secureserver.net";
	
	        authenticator = new SMTPAuthenticator ();
	        properties = System.getProperties ();
	        properties.put ( "mail.smtp.host", host );
	        properties.put ( "mail.smtp.starttls.enable", "true" );
	      //SendEMai properties.put ( "mail.smtp.port", "465" );
	        properties.put ( "mail.smtp.auth", "true" );
	      //  properties.put( "mail.smtp.socketFactory.port","465");
	       // properties.put( "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	       // properties.put("mail.smtp.starttls.enable", "true");                //added new
	      //  properties.put( "mail.smtp.socketFactory.fallback", "false");

	   
	    }
	
	    public void sendMail (String from, String to, String subject, String messageBody) {
	        try {
	            Session session = Session.getDefaultInstance ( properties, authenticator );
	            message = new MimeMessage ( session );
	            message.setFrom ( new InternetAddress ( from ) );
	            message.addRecipient ( Message.RecipientType.TO,  new InternetAddress ( to ) );
	            message.setSubject ( subject );
	
	            multipart = new MimeMultipart ();
	            messageBodyPart = new MimeBodyPart ();
	            messageBodyPart.setContent ( messageBody, "text/html" );
	            multipart.addBodyPart ( messageBodyPart );
	            message.setContent ( multipart );
	
	            Transport.send (message);	          
	           
	        } catch ( Exception me ) {
	            me.printStackTrace ();
	        }
	    } 
	    
	    public boolean sendDemandLetter(String from, String to, String filename, int clientId, String recipientEmailsCc,String recipientEmailsBcc) {
		 	 
	    	boolean isMailSent = false;
	    	try{
		 		   
	    			Client client = disbursementService.getPropertyDetails(clientId);
	    		
		 		    Session session = Session.getDefaultInstance (properties, authenticator );
		            message = new MimeMessage (session);
		         
		            message.setFrom ( new InternetAddress ( from , client.getCompany()) );
		            
		            message.addRecipient( Message.RecipientType.TO,  new InternetAddress (to));
		            
		            if(recipientEmailsCc != null && recipientEmailsCc != ""){
		            	
		            	String[] recipientEmailsArr = recipientEmailsCc.split("[, ] ?");
				 		   
			 		    InternetAddress[] recipientAddress_Cc = new InternetAddress[recipientEmailsArr.length];
			 		    for (int i = 0; i < recipientEmailsArr.length; i++)
			 		    {			 		    	
			 		    	recipientAddress_Cc[i] = new InternetAddress(recipientEmailsArr[i]);
			 		    }
			            message.setRecipients( Message.RecipientType.CC,  recipientAddress_Cc );
		            }
		            
		            if(recipientEmailsBcc != null && recipientEmailsBcc != ""){
		            	
		            	String[] recipientEmailsArr1 = recipientEmailsBcc.split("[, ] ?");
				 		  
			 		    InternetAddress[] recipientAddress_Bcc = new InternetAddress[recipientEmailsArr1.length];
			 		    for (int i = 0; i < recipientEmailsArr1.length; i++)
			 		    {			 		    	
			 		    	recipientAddress_Bcc[i] = new InternetAddress(recipientEmailsArr1[i]);
			 		    }
			            
			            message.setRecipients( Message.RecipientType.BCC,  recipientAddress_Bcc );
		            }
		            
		            message.setSubject ("Demand Letter");
		                   
		            BodyPart messageBodyPart1 = new MimeBodyPart();
		            
		            String messagebody = "  <html>Dear Sir/Madam, <br/>"+
		            					 "	&nbsp;&nbsp;&nbsp; Referring to the booking for the purchase of the flat no. <b>"+client.getWingName()+"-"+
		            					 	client.getFlatNumber()+"</b>  in our project <b>"+client.getProjectName()+"</b>."+
										 "  <br/> As per the terms of payment mentioned in the agreement, the following amount has become due till date. <br/>"+
										 "	If you have already paid the above mention due amount, kindly ignore this letter. <br/><br/>"+
										 "	Thanks & Regards, <br/>"+client.getCompany()+
										 "	<html>";	
		            
		           
		            messageBodyPart1.setContent(messagebody, "text/html");
		           // messageBodyPart1.setText(messagebody); 
		                
		            MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
		        
		            DataSource source = new FileDataSource(filename); 
		            		            
		            messageBodyPart2.setDataHandler(new DataHandler(source));    
		           // messageBodyPart2.setFileName(filename);  
		            messageBodyPart2.setFileName(new File(filename).getName());
		                  
		            Multipart multipart = new MimeMultipart();    
		            
		            multipart.addBodyPart(messageBodyPart1);     
		            multipart.addBodyPart(messageBodyPart2); 
		             
		            message.setContent(multipart);
		            Transport.send(message); 
		            isMailSent = true;
		          
		        } catch ( Exception ex ) {
		        	
		        	isMailSent = false;
		        	  logger.info("Exception in send mail : "+ex.toString());
		        	  ex.printStackTrace ();
		            
		     }
			return isMailSent;
		 }
	    
	    public boolean sendInvoiceDetails (String from, String to, String subject, String messageBody, String attachFiles) {
		 	 
	    	boolean isMailSent = false;
	    	try{
		 		   
		 		    Session session = Session.getDefaultInstance (properties, authenticator );
		            message = new MimeMessage (session);
		         
		            message.setFrom ( new InternetAddress ( from , "YSM Software" ) );
		            
		            message.addRecipient( Message.RecipientType.TO,  new InternetAddress (to));
		            
		            message.setSubject ( subject );
		                   
		            BodyPart messageBodyPart1 = new MimeBodyPart();     
		            messageBodyPart1.setText("This is message body"); 
		                
		            // creates multi-part
		            Multipart multipart = new MimeMultipart();
		            
		            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		     
		            DataSource source = new FileDataSource(attachFiles); 
		            
		            messageBodyPart2.setDataHandler(new DataHandler(source));    
		           // messageBodyPart2.setFileName(attachFiles);  
		            messageBodyPart2.setFileName(new File(attachFiles).getName());
		            
		            
		            multipart.addBodyPart(messageBodyPart1);
		            multipart.addBodyPart(messageBodyPart2); 
		     
		            message.setContent(multipart);
		     
		            // sends the e-mail
		            Transport.send(message);
		            
		            isMailSent = true;
		          
		        } catch ( Exception me ) {
		        	
		        	isMailSent = false;
		            me.printStackTrace ();
		            
		        }
			return isMailSent;
		 }
	    
	    public boolean sendMultipleAttachments (String from, String to, String subject, String messageBody, String[] attachFiles, String companyName) {
		 	 
	    	boolean isMailSent = false;
	    	try{
		 		   
		 		    Session session = Session.getDefaultInstance (properties, authenticator );
		            message = new MimeMessage (session);
		         
		            message.setFrom ( new InternetAddress ( from , companyName ) );
		            
		            message.addRecipient( Message.RecipientType.TO,  new InternetAddress (to));
		            
		            message.setSubject ( subject );
		                   
		            BodyPart messageBodyPart1 = new MimeBodyPart();     
		            messageBodyPart1.setText("This is message body"); 
		                
		            // creates multi-part
		            Multipart multipart = new MimeMultipart();
		            multipart.addBodyPart(messageBodyPart1);
		     
		            // adds attachments
		            if (attachFiles != null && attachFiles.length > 0) {
		                for (String filePath : attachFiles) {
		                    MimeBodyPart attachPart = new MimeBodyPart();
		     
		                    try {
		                        attachPart.attachFile(filePath);
		                    } catch (IOException ex) {
		                        ex.printStackTrace();
		                    }
		     
		                    multipart.addBodyPart(attachPart);
		                }
		            }
		     
		            // sets the multi-part as e-mail's content
		            message.setContent(multipart);
		     
		            // sends the e-mail
		            Transport.send(message);
		            
		            isMailSent = true;
		          
		        } catch ( Exception me ) {
		        	
		        	isMailSent = false;
		            me.printStackTrace ();
		            
		        }
			return isMailSent;
		 }
	    
	}// main class 


	class SMTPAuthenticator extends Authenticator {

    private static final String SMTP_AUTH_USER = "savita@ysmsoftware.com";
    private static final String SMTP_AUTH_PASSWORD = "savita123";

    public PasswordAuthentication getPasswordAuthentication () {
    		String username = SMTP_AUTH_USER;
    		String password = SMTP_AUTH_PASSWORD;

        	return new PasswordAuthentication(username,  password);
    }
    
}
