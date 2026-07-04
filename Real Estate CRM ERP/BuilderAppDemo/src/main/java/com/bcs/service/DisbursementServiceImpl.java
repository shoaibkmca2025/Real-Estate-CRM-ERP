package com.bcs.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.DisbursementDao;
import com.bcs.dao.OtherPaymentsDao;
import com.bcs.dao.PaymentFollowupDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.Client;
import com.bcs.model.ClientDemandLetterDetails;
import com.bcs.model.Disbursement;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.model.PaymentFollowup;
import com.bcs.model.ProjectDetails;
import com.bcs.model.ProjectDisbursement;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;
import com.bcs.utility.NumToWords;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

@Service
public class DisbursementServiceImpl implements DisbursementService{

	@Autowired
	DisbursementDao disbursementDao;
	
	@Autowired  
	private ActivityDao activityDao;
	
	@Autowired  
	private PaymentFollowupDao paymentFollowupDao;
	
	@Autowired
	OtherPaymentsDao otherPaymentDetailsDao;
	
	@Autowired
	UtilityService utilityService;
	
	final static Logger logger = LoggerFactory.getLogger(DisbursementServiceImpl.class);	
	
	String dateTime = DateTimeUtil.getSysDateTime();
	
	@Override
	public List<ProjectDisbursement> getProjectDisbursementListByProjectId(int projectId) {
		
		return disbursementDao.getProjectDisbursementListByProjectId(projectId);
	}

	@Override
	public void updateProjectDisbursementDetails(ProjectDetails project) {
		boolean isRecordUpdated = false;
		
		isRecordUpdated =disbursementDao.updateProjectDisbursementDetails(project);		
		 
		 if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(project.getUserId()); 
			 activityLog.setActivityDescription("Project Disbursement Updated");
			 activityLog.setActivityDatetime(dateTime);
			 activityLog.setProjectTranId(project.getProjectTranId());
			 activityDao.addActivityDetails(activityLog);
		}
	}

	@Override
	public void payDisbursementAmountByDisbursementId(Disbursement disbursement) throws ParseException {
		
		 //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		// Date date;
		 
		 //logger.info("Disbursement paid date: "+disbursement.getDate());
		// logger.info("Disbursement followup date: "+disbursement.getFollowupDateAsDate());
		// date = sdf.parse(disbursement.getPaidDate());
		
		 disbursement.setPaidDate(output.format(disbursement.getDate()));
		 
		 if(disbursement.getPaymentType() == 2){
			 
			// Date date1 = sdf.parse(disbursement.getDisbursementFollowupDate());
			 disbursement.setDisbursementFollowupDate(output.format(disbursement.getFollowupDateAsDate()));
		 }
		 
		 boolean isRecordUpdated = false;
		
		 isRecordUpdated = disbursementDao.payDisbursementAmountByDisbursementId(disbursement); 
		 
		 if(isRecordUpdated==true){
			 
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(disbursement.getUserId()); 
			 activityLog.setActivityDescription("Paid Date Updated of disbursementId "+disbursement.getDisbursementId());
			 activityLog.setProjectTranId(disbursement.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
	}

	@Override
	public void updateCompletionDateByProjectDisbursementId(ProjectDisbursement projectDisbursement) throws ParseException {
		
		boolean isRecordUpdated = false;
		 
		// SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		// Date date = sdf.parse(projectDisbursement.getCompletionDate());
		 projectDisbursement.setCompletionDate(output.format(projectDisbursement.getDateOfCompletion()));
		
		 isRecordUpdated = disbursementDao.updateCompletionDateByProjectDisbursementId(projectDisbursement);
		
		 if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(projectDisbursement.getUserId()); 
			 activityLog.setActivityDescription("Completion date updated of project disbursement Id "+projectDisbursement.getProjectDisbursementId());
			 activityLog.setProjectTranId(projectDisbursement.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
	}

	@Override
	public String createDemandLetterPdf(int clientId, int disbursementId) {
		
		   String pdfName = null;
		   String pdfPath = null;
		   String title   = null;
		   
		   try {
						   		
			       ClientDemandLetterDetails clientDetails = disbursementDao.getClientDisbursementDetailsByClientId(clientId, disbursementId);
			   		
			   			double totalAmount 			   = clientDetails.getOutStandingAmount() + clientDetails.getGstAmount() + clientDetails.getPrevRemainingAmount();
			   			double totalDisbursementAmount = clientDetails.getTotalPaidAmount()+totalAmount;
			   				
			   			Document document ;
												
						String currentDate = DateTimeUtil.getSysDate();
						title       	   = clientDetails.getOwnerName()+"_"+clientDetails.getTotalPercentage()+"_"+disbursementId;
								
						File theDir = new File(ConstantsUtil.FILE_SAVE_LOCATION +clientDetails.getProjectName()+"\\"+clientDetails.getWingName()+"\\"+clientDetails.getFloorName()+
									"\\"+clientDetails.getFlatNumber()); 
						
						if (!theDir.exists()){ 							
							 theDir.mkdirs();
						}
						
						pdfName = theDir+"\\"+title+".pdf";		
						pdfPath = clientDetails.getProjectName()+"\\"+clientDetails.getWingName()+"\\"+clientDetails.getFloorName()+"\\"+clientDetails.getFlatNumber()+"\\"+title+".pdf";
						 
						
						 if(clientDetails.getLetterHeadName().isEmpty() || clientDetails.getLetterHeadName() == null){
							 
							  document  = new Document(PageSize.A4,15, 15, 15, 15);
							  PdfWriter .getInstance(document, new FileOutputStream(pdfName));
						 }else{
							 
							  document  = new Document(PageSize.A4,12, 12, 12, 15);
							  PdfWriter .getInstance(document, new FileOutputStream(theDir+"\\"+"clientDemandLetter.pdf"));
						 }
						document.open();
											
						document.addAuthor("YSM Software");
				        document.addCreationDate();
				        document.addCreator("BuilderApp");
				        document.addTitle("Demand Letter");
				       	       	        	       			
					    Font font1 = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
					    Font font2 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
					    Font font3 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD+Font.UNDERLINE, BaseColor.BLACK);
					    Font font4 = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
					    Font font5 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
					    Font font6 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
					    Font font7 = new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
					    Font font8 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
					    Font font9 = new Font(FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
					    Font font10 = new Font(FontFamily.TIMES_ROMAN, 14, Font.NORMAL, new BaseColor(77, 77, 77));
					    Font font11 = new Font(FontFamily.TIMES_ROMAN, 9, Font.NORMAL, new BaseColor(128, 128, 128));
					   
					    float spacingAfter ;
					    
					    if(clientDetails.getLetterHeadName().isEmpty() || clientDetails.getLetterHeadName() == null){
					    	
					    		spacingAfter = 10f;
					    		
					    		Paragraph companyName = new Paragraph(clientDetails.getCompanyName(), font10);
					    		companyName.setAlignment(Element.ALIGN_LEFT);
							    document.add(companyName);
							  
							    Paragraph address = new Paragraph(clientDetails.getCompanyAddress(), font11);
							    address.setAlignment(Element.ALIGN_LEFT);
							    address.setIndentationRight(150f);
							    if(clientDetails.getCompanyLogo() == ""){
							    	address.setSpacingAfter(6f);
							    }
							    document.add(address);
							   
							    if(clientDetails.getCompanyLogo() != ""){
								    Image img = Image.getInstance(ConstantsUtil.FILE_SAVE_LOCATION+clientDetails.getCompanyLogo());
								    img.setAbsolutePosition(490f, 788f);
								    //img.scaleToFit(115, 45);
								    img.scaleAbsolute(80f, 41f);
							        document.add(img);

							        Paragraph p = new Paragraph("");
							        p.setSpacingAfter(8f);
							        document.add(p);
							    }
						        
						        LineSeparator ls = new LineSeparator(1, 100, new BaseColor(145, 145, 145), Element.ALIGN_CENTER, -2);
						        document.add(ls);
						        
					    }else{
					    	spacingAfter = 7f;					    	
					    }
					    
					    Chunk glue = new Chunk(new VerticalPositionMark());
					    
					    Chunk date  = new Chunk("Date: ", font4);
					    Chunk datevalue = new Chunk(currentDate, font5);
					    
					    if(clientDetails.getLetterHeadName() == "" || clientDetails.getLetterHeadName() == null){ 
							    Paragraph p1 = new Paragraph();
							    p1.setAlignment(Element.ALIGN_RIGHT);
							    p1.setSpacingBefore(5f);
							    p1.add(date);
							    p1.add(datevalue);	
							    document.add(p1);
					    }
					    
					    Paragraph p2 = new Paragraph("Demand Letter", font1);
					    p2.setAlignment(Element.ALIGN_CENTER);
					    if(clientDetails.getLetterHeadName() != ""){ 
					    	p2.setIndentationLeft(220);
						    p2.add(glue);
						    p2.add(date);
						    p2.add(datevalue);	
					    }
					    document.add(p2);
					  
					    Paragraph p3 = new Paragraph(" To, ", font6);
					    p3.setAlignment(Element.ALIGN_LEFT);
					    document.add(p3);	
					    
					    Paragraph p4 = new Paragraph(" Mr./Mrs. "+clientDetails.getOwnerName(), font5);
					    p4.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p4);	
					    
					    Paragraph p5 = new Paragraph("Address:   "+clientDetails.getAddress()+", "+clientDetails.getCity(), font5);					 
					   // p5.setIndentationRight(170);
					    p5.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p5);
					    
					    String mobileNo = "";
					    if(clientDetails.getLandlineNo() != null){
					    	 mobileNo = clientDetails.getMobileNo()+"/ "+clientDetails.getLandlineNo();
					    }else{
					    	 mobileNo = clientDetails.getMobileNo();
					    }
					    
					    Paragraph p6 = new Paragraph("Mobile No: "+mobileNo, font5);
					    p6.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p6);
					    
					    Paragraph p7 = new Paragraph("Mail Id: "+clientDetails.getEmail(), font5);
					    p7.setAlignment(Element.ALIGN_LEFT);
						p7.setSpacingAfter(spacingAfter);
					    document.add(p7);
					    
					    Chunk chunk1 = new Chunk("Sub: ", font6);
					    Chunk chunk2 = new Chunk("Payment towards the purchase of Flat No.", font2);
					    Chunk chunk3 = new Chunk(clientDetails.getWingName()+"-"+clientDetails.getFlatNumber()+".", font6);
					 
					    Paragraph p8 = new Paragraph();
					    p8.add(chunk1);
					    p8.add(chunk2);
					    p8.add(chunk3);
					    p8.setAlignment(Element.ALIGN_LEFT);
					    document.add(p8);
					    
					    Chunk chunk4  = new Chunk("In the scheme: ", font2);
					    Chunk chunk5 = new Chunk(clientDetails.getProjectName(), font5);
					    Chunk chunk6 = new Chunk(" Situated at "+clientDetails.getProjectAddress(), font2);
					    
					    Paragraph p9 = new Paragraph();
					    p9.add(chunk4);
					    p9.add(chunk5);
					    p9.add(chunk6);
					    p9.setAlignment(Element.ALIGN_LEFT);	
					    p9.setSpacingAfter(spacingAfter);
					    document.add(p9);
					    
					    Paragraph p10 = new Paragraph("Dear Sir/Madam, ",font2);
					    p10.setAlignment(Element.ALIGN_LEFT);
					    document.add(p10);
					    
					    Chunk chunk7  = new Chunk("Please refer to the Booking for the purchase of above Flat No.", font2);
					    Chunk chunk8  = new Chunk(clientDetails.getWingName()+"-"+clientDetails.getFlatNumber(), font5);
					    Chunk chunk9  = new Chunk(" As per the terms of payment mentioned in the Agreement, the following amount has become due till date for ", font2);
					    Chunk chunk10 = new Chunk(clientDetails.getTotalPercentage()+" % ( "+clientDetails.getDisbursementTitle()+" )", font5);
					    Chunk chunk11 = new Chunk(" Completion.", font2);
					    
					    Paragraph p11 = new Paragraph();
					    p11.setTabSettings(new TabSettings(15f));
					    p11.add(Chunk.TABBING);
					    p11.add(chunk7);
					    p11.add(chunk8);
					    p11.add(chunk9);
					    p11.add(chunk10);
					    p11.add(chunk11);
					    p11.setAlignment(Element.ALIGN_LEFT);
					    p11.setSpacingAfter(spacingAfter);
					    document.add(p11);
					    
					    float widthvalheader = 3;	
			         
			            PdfPTable pdfTable = new PdfPTable(3);
			            
			             pdfTable.setWidthPercentage(80);
			             float[] columnWidths = new float[] {10,15,8};
			             pdfTable.setWidths(columnWidths);
			             pdfTable.getDefaultCell().setUseAscender(true);
			             pdfTable.getDefaultCell().setUseDescender(true);
			             pdfTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			             
			             PdfPCell cell1 = new PdfPCell(new Phrase("Particular",font5));
			             cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell1.setPadding(widthvalheader);
			             cell1.setColspan(2);
			             pdfTable.addCell(cell1);
			            
			             cell1 = new PdfPCell(new Phrase("Amount",font5));
			             cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell1.setPadding(widthvalheader);
			             pdfTable.addCell(cell1);
			             
			             PdfPCell cell2 = new PdfPCell(new Phrase(""));
			             cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			             
			             cell2 = new PdfPCell(new Phrase("Agreement Amount:",font2));
			             cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			             
			             Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
			           
			             cell2 = new PdfPCell(new Phrase(format.format(clientDetails.getAgreementAmount()),font2));
			             cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			             
			             PdfPCell cell3= new PdfPCell(new Phrase("DUE", font5));
			             cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell3.setPadding(widthvalheader);
			             pdfTable.addCell(cell3);
			             
			             cell3 = new PdfPCell(new Phrase(clientDetails.getTotalPercentage()+"% Due (Incl. GST)",font2));
			             cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell3.setPadding(widthvalheader);
			             pdfTable.addCell(cell3);
			             
			             cell3 = new PdfPCell(new Phrase(format.format(totalDisbursementAmount),font2));
			             cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell3.setPadding(widthvalheader);
			             pdfTable.addCell(cell3);
			             
			             PdfPCell cell4= new PdfPCell(new Phrase("Received", font5));
			             cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell4.setPadding(widthvalheader);
			             pdfTable.addCell(cell4);
			             
			             cell4 = new PdfPCell(new Phrase("Amount Received Till Date",font2));
			             cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell4.setPadding(widthvalheader);
			             pdfTable.addCell(cell4);
			             
			             cell4 = new PdfPCell(new Phrase(format.format(clientDetails.getTotalPaidAmount()),font2));
			             cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell4.setPadding(widthvalheader);
			             pdfTable.addCell(cell4);
			             
			             PdfPCell cell5= new PdfPCell(new Phrase("Balance",font5));
			             cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell5.setPadding(widthvalheader);
			             pdfTable.addCell(cell5);
			             
			             cell5 = new PdfPCell(new Phrase("Outstanding Amount",font2));
			             cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell5.setPadding(widthvalheader);
			             pdfTable.addCell(cell5);
			             
			             cell5 = new PdfPCell(new Phrase(format.format(clientDetails.getOutStandingAmount()),font2));
			             cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell5.setPadding(widthvalheader);
			             pdfTable.addCell(cell5);
			             
			             PdfPCell cell6= new PdfPCell(new Phrase("GST",font5));
			             cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell6.setPadding(widthvalheader);
			             pdfTable.addCell(cell6);
			         
			             cell6 = new PdfPCell(new Phrase(clientDetails.getGstPercentage()+" %",font2));
			             cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell6.setPadding(widthvalheader);
			             pdfTable.addCell(cell6);
			             
			             cell6 = new PdfPCell(new Phrase(format.format(clientDetails.getGstAmount()),font2));
			             cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell6.setPadding(widthvalheader);
			             pdfTable.addCell(cell6);
			             
			             PdfPCell cell7= new PdfPCell(new Phrase("",font5));
			             cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell7.setPadding(widthvalheader);
			             pdfTable.addCell(cell7);
			         
			             cell7 = new PdfPCell(new Phrase("Previous Remaining (Incl. GST)",font2));
			             cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell7.setPadding(widthvalheader);
			             pdfTable.addCell(cell7);
			             
			             cell7 = new PdfPCell(new Phrase(format.format(clientDetails.getPrevRemainingAmount()),font2));
			             cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell7.setPadding(widthvalheader);
			             pdfTable.addCell(cell7);
			             
			             PdfPCell cell8= new PdfPCell(new Phrase("Total Due",font5));
			             cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell8.setPadding(widthvalheader);
			             pdfTable.addCell(cell8);
			             
			             cell8 = new PdfPCell(new Phrase("Outstanding+GST+Previous Remaining",font5)); 
			             cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell8.setPadding(widthvalheader);
			             pdfTable.addCell(cell8);
			             
			            
			            // double totalOutstandingAmount  = outstandingAmount+;
			             cell8 = new PdfPCell(new Phrase(format.format(totalAmount),font5));
			             cell8.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell8.setPadding(widthvalheader);
			             pdfTable.addCell(cell8);
			             
			             document.add(pdfTable);  
			             
			             NumToWords w = new NumToWords();
						 int rupees = Integer.parseInt(Double.toString(totalAmount).split("\\.")[0]);
						
						 	String amountInWords = "";
						 	String str1 = w.convert(rupees);								
						//	str1 += " Rupees ";
							amountInWords = str1;
							
							int paise = Integer.parseInt(format.format(totalAmount).split("\\.")[1]);
						    if (paise != 0) {
						    	
								  amountInWords += " and ";
								  amountInWords += w.convert(paise)+" Paise";
						    }  
						    
			                Chunk chunk12  = new Chunk("In Words: ", font4);
						    Chunk chunk13 = new Chunk(amountInWords+" Only", font3);
						  
						    Paragraph p12 = new Paragraph();
						    p12.add(chunk12);
						    p12.add(chunk13);
						    p12.setAlignment(Element.ALIGN_LEFT);	
						    p12.add(new Paragraph(" "));
						    document.add(p12);
						    
						    PdfPTable pdfTable2 = new PdfPTable(3);
				            
						     pdfTable2.setWidthPercentage(90);
				             float[] columnWidth = new float[] {13,8,11};
				             pdfTable2.setWidths(columnWidth);
				             pdfTable2.getDefaultCell().setUseAscender(true);
				             pdfTable2.getDefaultCell().setUseDescender(true);
				             pdfTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
				             
				             PdfPCell cell9 = new PdfPCell(new Phrase("Cheque Detail Break up & Chqeue Favoring",font5));
				             cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell9.setPadding(widthvalheader);
				             pdfTable2.addCell(cell9);
				            
				             cell9 = new PdfPCell(new Phrase("Amount",font5));
				             cell9.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell9.setPadding(widthvalheader);
				             pdfTable2.addCell(cell9);
				             
				             cell8 = new PdfPCell(new Phrase("Towards",font5));
				             cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell8.setPadding(widthvalheader);
				             pdfTable2.addCell(cell8);
				             
				             PdfPCell cell10 = new PdfPCell(new Phrase("1) "+clientDetails.getAccountName()+" A/c No. "+clientDetails.getAccountNumber(),font7));
				             cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
				             cell10.setPadding(widthvalheader);
				             cell10.setRowspan(3);
				             pdfTable2.addCell(cell10);
				            
				             cell10 = new PdfPCell(new Phrase(format.format(clientDetails.getOutStandingAmount()),font2));
				             cell10.setHorizontalAlignment(Element.ALIGN_RIGHT);
				             cell10.setPadding(widthvalheader);
				             pdfTable2.addCell(cell10);
				             
				             cell10 = new PdfPCell(new Phrase("Own Contribution",font5));
				             cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell10.setPadding(widthvalheader);
				             pdfTable2.addCell(cell10);
				             
				             PdfPCell cell11 = new PdfPCell();
				            
				             cell11 = new PdfPCell(new Phrase(format.format(clientDetails.getGstAmount()),font2));
				             cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
				             cell11.setPadding(widthvalheader);
				             pdfTable2.addCell(cell11);
				             
				             cell11 = new PdfPCell(new Phrase("GST",font5));
				             cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell11.setPadding(widthvalheader);
				             pdfTable2.addCell(cell11);
				             
				             PdfPCell cell111 = new PdfPCell();
					            
				             cell111 = new PdfPCell(new Phrase(format.format(clientDetails.getPrevRemainingAmount()),font2));
				             cell111.setHorizontalAlignment(Element.ALIGN_RIGHT);
				             cell111.setPadding(widthvalheader);
				             pdfTable2.addCell(cell111);
				             
				             cell111 = new PdfPCell(new Phrase("Prev. Remaining (Incl. GST)",font5));
				             cell111.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell111.setPadding(widthvalheader);
				             pdfTable2.addCell(cell111);
				             
				             PdfPCell cell12 = new PdfPCell(new Phrase("Grand Total",font5));
				             cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell12.setPadding(widthvalheader);
				             pdfTable2.addCell(cell12);
				            
				             cell12 = new PdfPCell(new Phrase(format.format(totalAmount),font6));
				             cell12.setHorizontalAlignment(Element.ALIGN_RIGHT);
				             cell12.setPadding(widthvalheader);
				             pdfTable2.addCell(cell12);
				             
				             cell12 = new PdfPCell(new Phrase("",font6));
				             cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
				             cell12.setPadding(widthvalheader);
				             pdfTable2.addCell(cell12);
				             
				             document.add(pdfTable2);  
						    
						    Chunk chunk14  = new Chunk("We hereby request you send us Cheque / D.D / NEFT transfer in favour of ", font2);
						    Chunk chunk15 = new Chunk(clientDetails.getAccountName()+" A/c ", font5);
						  
						    Paragraph p13 = new Paragraph();
						    p13.add(chunk14);
						    p13.add(chunk15);
						    p13.setAlignment(Element.ALIGN_LEFT);						    
						    document.add(p13);
						    
						    Paragraph p14 = new Paragraph("Cheque favoring details of our Project "+clientDetails.getProjectName()+", "+clientDetails.getCity()+" are as follows:", font2);					    
						    p14.setAlignment(Element.ALIGN_LEFT);
						    document.add(p14);
						    
							     Paragraph p15 = new Paragraph("1. In case of NEFT transfer-  ", font5);
							     p15.setAlignment(Element.ALIGN_LEFT);							   
							     document.add(p15);
							    
							     PdfPTable bankDetailsTbl = new PdfPTable(3);
					            
							     bankDetailsTbl.setWidthPercentage(100);
					             float[] columnWidths1 = new float[] {28,33,33};
					             bankDetailsTbl.setWidths(columnWidths1);
					             bankDetailsTbl.getDefaultCell().setUseAscender(true);
					             bankDetailsTbl.getDefaultCell().setUseDescender(true);
					             bankDetailsTbl.setHorizontalAlignment(Element.ALIGN_LEFT);
					             
					             Chunk bankNameTitle  = new Chunk("Bank Name   : ", font7);
								 Chunk bankNameText = new Chunk(clientDetails.getBankName(), font9);
								 
								 Phrase phrase = new Phrase();
								 phrase.add(bankNameTitle);
								 phrase.add(bankNameText);
					             
					             PdfPCell bankName = new PdfPCell(phrase);
					             bankName.setHorizontalAlignment(Element.ALIGN_LEFT);
					             bankName.setPadding(widthvalheader);
					             bankName.setBorder(Rectangle.NO_BORDER);
					             bankDetailsTbl.addCell(bankName);
					             
					             Chunk branchNameTitle  = new Chunk("Branch   : ", font7);
								 Chunk branchNameText = new Chunk(clientDetails.getBranchName(), font9);
								 
								 phrase = new Phrase();
								 phrase.add(branchNameTitle);
								 phrase.add(branchNameText);
					             
					             PdfPCell branchName = new PdfPCell(phrase);
					             branchName.setHorizontalAlignment(Element.ALIGN_LEFT);
					             branchName.setPadding(widthvalheader);
					             branchName.setBorder(Rectangle.NO_BORDER);
					             bankDetailsTbl.addCell(branchName);
					             
					             Chunk ifscCodeTitle = new Chunk("IFSC Code  : ",font7);
					             Chunk ifscCode1 = new Chunk(clientDetails.getIfscCode(),font9);
					             
					             phrase = new Phrase();
								 phrase.add(ifscCodeTitle);
								 phrase.add(ifscCode1);
					             
					             PdfPCell ifscCode = new PdfPCell(phrase);
					             ifscCode.setHorizontalAlignment(Element.ALIGN_LEFT);
					             ifscCode.setPadding(widthvalheader);
					             ifscCode.setBorder(Rectangle.NO_BORDER);
					             bankDetailsTbl.addCell(ifscCode);
					             
					             Chunk accNameTitle = new Chunk("A/c Name      : ",font7);
					             Chunk accName      = new Chunk(clientDetails.getAccountName(),font9);
					             
					             phrase = new Phrase();
								 phrase.add(accNameTitle);
								 phrase.add(accName);
					             
					             PdfPCell accountName = new PdfPCell(phrase);
					             accountName.setHorizontalAlignment(Element.ALIGN_LEFT);
					             accountName.setPadding(widthvalheader);
					             accountName.setBorder(Rectangle.NO_BORDER);
					             bankDetailsTbl.addCell(accountName);
					             
					             Chunk accNoTitle = new Chunk("A/c No.   : ",font7);
					             Chunk accNo = new Chunk(clientDetails.getAccountNumber(),font9);
					             
					             phrase = new Phrase();
								 phrase.add(accNoTitle);
								 phrase.add(accNo);
					             
					             PdfPCell accountNo = new PdfPCell(phrase);
					             accountNo.setHorizontalAlignment(Element.ALIGN_LEFT);
					             accountNo.setPadding(widthvalheader);
					             accountNo.setColspan(2);
					             accountNo.setBorder(Rectangle.NO_BORDER);
					             bankDetailsTbl.addCell(accountNo);
					             
					             Paragraph p = new Paragraph();
					             p.setIndentationLeft(20);
					             p.add(bankDetailsTbl);
					             
					             document.add(p);
					             
							    /*
							    Chunk chunk17 = new Chunk("A/c Name          :  ", font7);
							    Chunk chunk18 = new Chunk(clientDetails.getAccountName(), font7);
							    
							    Paragraph para1 = new Paragraph();
							    para1.setAlignment(Element.ALIGN_LEFT);
							    para1.setTabSettings(new TabSettings(50f));
							    para1.add(Chunk.TABBING);
							    para1.add(chunk17);
							    para1.add(chunk18);
							    document.add(para1);
							    
							    Chunk chunk19 = new Chunk("Bank Name       :  ", font7);
							    Chunk chunk20 = new Chunk(clientDetails.getBankName(), font7);
							    
							    Paragraph para2 = new Paragraph();
							    para2.setAlignment(Element.ALIGN_LEFT);
							    para2.setTabSettings(new TabSettings(50f));
							    para2.add(Chunk.TABBING);
							    para2.add(chunk19);
							    para2.add(chunk20);
							    document.add(para2);
							    
							    Chunk chunk21 = new Chunk("Branch Name   :  ", font7);
							    Chunk chunk22 = new Chunk(clientDetails.getBranchName(), font7);
							    
							    Paragraph para3 = new Paragraph();
							    para3.setAlignment(Element.ALIGN_LEFT);
							    para3.setTabSettings(new TabSettings(50f));
							    para3.add(Chunk.TABBING);
							    para3.add(chunk21);
							    para3.add(chunk22);
							    document.add(para3);
							    
							    Chunk chunk23 = new Chunk("A/c No.              :  ", font7);
							    Chunk chunk24 = new Chunk(clientDetails.getAccountNumber(), font7);
							    
							    Paragraph para4 = new Paragraph();
							    para4.setAlignment(Element.ALIGN_LEFT);
							    para4.setTabSettings(new TabSettings(50f));
							    para4.add(Chunk.TABBING);
							    para4.add(chunk23);
							    para4.add(chunk24);
							    document.add(para4);
							    
							    Chunk chunk25 = new Chunk("IFSC Code       :  ", font7);
							    Chunk chunk26 = new Chunk(clientDetails.getIfscCode(), font7);
							    
							    Paragraph para5 = new Paragraph();
							    para5.setAlignment(Element.ALIGN_LEFT);
							    para5.setTabSettings(new TabSettings(50f));
							    para5.add(Chunk.TABBING);
							    para5.add(chunk25);
							    para5.add(chunk26);
							    para5.add(new Paragraph(" "));
							    document.add(para5);*/
							    						    
						    Paragraph p17 = new Paragraph("Due Amount to be received within seven days from the receipt of this letter and oblige otherwise "+
						    							  "interest at the rate of 18% p.a shall be charged from due date.",font2);					    
						    p17.setAlignment(Element.ALIGN_LEFT);	
							document.add(p17);
							
							Paragraph p18 = new Paragraph("If you have already paid the above mention due amount, kindly ignore this letter.",font2);					    
							p18.setAlignment(Element.ALIGN_LEFT);
							p18.setTabSettings(new TabSettings(20f));
							p18.add(Chunk.TABBING);
							p18.setSpacingAfter(spacingAfter);
							document.add(p18);
							
							Paragraph p19 = new Paragraph("Thanking You,",font2);					    
							p19.setAlignment(Element.ALIGN_LEFT);	
							p19.setSpacingAfter(spacingAfter);
							document.add(p19);
							
							Paragraph p20 = new Paragraph("Yours faithfully  ",font2);	
							p20.setAlignment(Element.ALIGN_LEFT);
							p20.setSpacingAfter(spacingAfter);
							document.add(p20);
						    
							Chunk note = new Chunk("( Note: This is a system generated demand letter. No signature is required )", font8);
							
							Paragraph p21 = new Paragraph();
							p21.setAlignment(Element.ALIGN_RIGHT);
							//p21.setTabSettings(new TabSettings(20f));	
							p21.add(note);	
							document.add(p21);
			             
			            document.close();
			           
			            if(clientDetails.getLetterHeadName() != "" && clientDetails.getLetterHeadName() != null){
			            
						    	PdfReader reader = new PdfReader(ConstantsUtil.FILE_SAVE_LOCATION+clientDetails.getLetterHeadName());
						        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(pdfName));
						        PdfContentByte canvas = stamper.getOverContent(1);
						        PdfReader r = new PdfReader(theDir+"\\"+"clientDemandLetter.pdf");
						        PdfImportedPage page = stamper.getImportedPage(r, 1);
					            canvas.addTemplate(page, 10,-125);
					            stamper.getWriter().freeReader(r);
					            r.close();
						        stamper.close();
						        
						        File file          =  new File(theDir+"\\"+"clientDemandLetter.pdf");		
								file.delete();
						  }
			            
			   			
		            }catch (Exception i){
					
					    i.printStackTrace();
		            }
		   return pdfPath;
	}

	@Override
	public List<Client> getPaymentDueDateNotifications(int userId, int userType, int paymentNotifDuration, int companyId, int projectId) {
		
		List<Client> clientList = disbursementDao.getPaymentDueDateNotifications(userId, userType,paymentNotifDuration,companyId, projectId);
	
		return clientList;
	}

	@Override
	public void updatePdfSendDate(Disbursement disbursement) {
		
		boolean isRecordUpdated    = disbursementDao.updatePdfSendDate(disbursement);
		Disbursement disb = disbursementDao.getDisbursementFirstFollowupDetails(disbursement.getClientId());
		Format format     = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		
		if(isRecordUpdated){
			
			String datetime = DateTimeUtil.getSysDateTime();
			PaymentFollowup paymentFollowup = new PaymentFollowup();
			String amount = format.format(disb.getRemainingAmount());
			
			String followupDescription = "Demand Letter send : "+disb.getPercentageValue()+"% ("+disb.getDisbursementTitle()+") Project Completion"+" having amount Rs."+amount.substring(1);
			paymentFollowup.setDisbursementId(disbursement.getDisbursementId());
			
			paymentFollowup.setPaymentFollowupDescription(followupDescription);
			paymentFollowup.setCreatedDatetime(datetime);
			paymentFollowup.setUpdatedDatetime(datetime);
			
			paymentFollowupDao.addPaymentFollowupDetails(paymentFollowup);
			
			int firstFollowupDuration = utilityService.getSettingsByCompanyId(disbursement.getCompanyId()).getPaymentDuedateDuration();
			
			Date date = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, firstFollowupDuration); // add 8 days-
			date = cal.getTime();
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");   /*   "dd/MM/yyyy hh:mm:ss a"*/
			
			String paymentFollowupDate = dateFormat.format(date);
			
			disbursement.setDisbursementFollowupDate(paymentFollowupDate);
			disbursement.setUpdatedDatetime(datetime);
			
			disbursementDao.updateDisbursementFollowupDate(disbursement);	
			
			 if(isRecordUpdated==true){
				 ActivityLog activityLog = new ActivityLog();
				 
				 activityLog.setUserId(disbursement.getUserId()); 
				 activityLog.setActivityDescription("Demand letter send date updated of disbursement Id "+disbursement.getDisbursementId());
				 activityLog.setProjectTranId(disbursement.getProjectId());
				 activityLog.setActivityDatetime(dateTime);
				 activityDao.addActivityDetails(activityLog);
			}
		}		
	}

	@Override
	public List<Client> getPendingDemandLetterNotifications(int userId, int userType, int companyId, int projectId) {
		
		return disbursementDao.getPendingDemandLetterNotifications(userId, userType, companyId, projectId);
	}

	@Override
	public List<Disbursement> getDisbursementDetailsListByClientId(int clientTranId) {
		
		return disbursementDao.getDisbursementDetailsListByClientId(clientTranId);
	}

	@Override
	public List<Client> getTodaysReceivedPaymentClientsList(int userId, int userType, int companyId, int projectId) {
		// TODO Auto-generated method stub
		return disbursementDao.getTodaysReceivedPaymentClientsList(userId, userType, companyId, projectId);
	}


	@Override
	public List<PaidDisbursementDetails> getAllPaidDisbursementDetailsByClientId(int clientId) {		
		
		return disbursementDao.getAllPaidDisbursementDetailsByClientId(clientId);
	}

	@Override
	public Client getPropertyDetails(int clientTranId) {
		
		return disbursementDao.getPropertyDetails(clientTranId);
	}

}
