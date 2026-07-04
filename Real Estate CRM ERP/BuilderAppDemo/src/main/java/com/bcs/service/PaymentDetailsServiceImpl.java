package com.bcs.service;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.bcs.dao.CompanyProfileDao;
import com.bcs.dao.PaymentDetailsDao;
import com.bcs.dao.ProjectDao;
import com.bcs.dao.UserDao;
import com.bcs.model.CompanyProfile;
import com.bcs.model.PaymentDetails;
import com.bcs.model.User;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

	@Autowired
	PaymentDetailsDao paymentDao;
	
	@Autowired
	ProjectDao projectdao;

	@Autowired
	UserDao userdao; 
	
	@Autowired
	CompanyProfileDao companyDao;
	
	
	@Autowired
	ServletContext context;

	@Bean
	@Primary
	  public ServletContext getServletContext() {
	   // System.out.println("*** Context path: *** " + context.getRealPath("/"));
	    return context;
	  }
	
	 public static final String IMAGE = "/images/ysm_header.png";

	@Override
	public void addPaymentDetails(PaymentDetails paymentDetails) throws ParseException {
		String dateTime = DateTimeUtil.getSysDateTime();
		boolean isAdded = false;
		paymentDetails.setCreatedDatetime(dateTime); 
		paymentDetails.setUpdatedDatetime(dateTime);
		
		String startDate="";	
		String endDate="";
		String paidDate="";
		// SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		 Date date,date1,date2;
		 date = paymentDetails.getStartDate1();
		 date1 = paymentDetails.getEndDate1();
		 date2 = paymentDetails.getPaidDate1();
		 startDate	 = output.format(date);
		 endDate	 = output.format(date1);
		 paidDate	 = output.format(date2);
			
		 paymentDetails.setStartDate(startDate);
		 paymentDetails.setEndDate(endDate);
		 paymentDetails.setPaidDate(paidDate);
		 
		 isAdded = paymentDao.addPaymentDetails(paymentDetails);
		 if(isAdded==true){
			 projectdao.updateProjectApprovedStatus(paymentDetails.getProjectId(), 1);
			 //userdao.updateAllUsersStatusAfterAddPayment(paymentDetails.getCompanyId());
			// String pdfPath = createInvoiceLetterPdf(paymentDetails.getPaymentId(),paymentDetails.getCompanyId());
			// System.out.println("******"+pdfPath);
		 }
	}

	@Override
	public List<PaymentDetails> getAllPaymentDetails() {
		List<PaymentDetails> paymentList = paymentDao.getAllPaymentDetails();
		
		for(int i= 0; i< paymentList.size() ; i++){
			
			if(paymentList.get(i).getPaymentType() == 1){
				
				paymentList.get(i).setPaymentTypeName("Demo Version");
				
			}else if(paymentList.get(i).getPaymentType() == 2){
				
				paymentList.get(i).setPaymentTypeName("Monthly");
				
			}else if(paymentList.get(i).getPaymentType() == 3){
				
				paymentList.get(i).setPaymentTypeName("Quarterly");
				
			}else if(paymentList.get(i).getPaymentType() == 4){
				
				paymentList.get(i).setPaymentTypeName("Semi-Annual");
				
			}else if(paymentList.get(i).getPaymentType() == 5){
				
				paymentList.get(i).setPaymentTypeName("Annual");
			}
			
		}
		return paymentList;
	}

	@Override
	public PaymentDetails getPaymentDetailsByPaymentId(int paymentId) {
		
		return paymentDao.getPaymentDetailsByPaymentId(paymentId);
	}

	@Override
	public void updatePaymentDetailsByPaymentId(PaymentDetails paymentDetails) throws ParseException {
		String dateTime = DateTimeUtil.getSysDateTime();
		paymentDetails.setUpdatedDatetime(dateTime);
		
		String startDate="";	
		String endDate="";
		String paidDate="";
		 //SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		 Date date,date1,date2;
		 date = paymentDetails.getStartDate1();
		 date1 = paymentDetails.getEndDate1();
		 date2 = paymentDetails.getPaidDate1();
		 startDate	 = output.format(date);
		 endDate	 = output.format(date1);
		 paidDate	 = output.format(date2);
			
		 paymentDetails.setStartDate(startDate);
		 paymentDetails.setEndDate(endDate);
		 paymentDetails.setPaidDate(paidDate);
		 
		paymentDao.updatePaymentDetailsByPaymentId(paymentDetails);
	}

	@Override
	public List<PaymentDetails> getAllPaymentNotifications() {
		List<PaymentDetails> paymentNotificationsList = paymentDao.getAllPaymentNotifications();
			for(int i= 0; i< paymentNotificationsList.size() ; i++){
					
					if(paymentNotificationsList.get(i).getPaymentType() == 1){
						
						paymentNotificationsList.get(i).setPaymentTypeName("Demo Version");
						
					}else if(paymentNotificationsList.get(i).getPaymentType() == 2){
						
						paymentNotificationsList.get(i).setPaymentTypeName("Monthly");
						
					}else if(paymentNotificationsList.get(i).getPaymentType() == 3){
						
						paymentNotificationsList.get(i).setPaymentTypeName("Quarterly");
						
					}else if(paymentNotificationsList.get(i).getPaymentType() == 4){
						
						paymentNotificationsList.get(i).setPaymentTypeName("Semi-Annual");
						
					}else if(paymentNotificationsList.get(i).getPaymentType() == 5){
						
						paymentNotificationsList.get(i).setPaymentTypeName("Annual");
					}
					
				}
		return paymentNotificationsList;
	}


	@Override
	public List<String> getTodaysEndDateProjectIdList() {
		List<String> projectList = paymentDao.getTodaysEndDateProjectIdList();
		return projectList;
	}



	@Override
	public List<PaymentDetails> getPaymentDetailsByProjectId(int projectId) {
		List<PaymentDetails> paymentList = paymentDao.getPaymentDetailsByProjectId(projectId);
			for(int i= 0; i< paymentList.size() ; i++){
				
				if(paymentList.get(i).getPaymentType() == 1){
					
					paymentList.get(i).setPaymentTypeName("Demo Version");
					
				}else if(paymentList.get(i).getPaymentType() == 2){
					
					paymentList.get(i).setPaymentTypeName("Monthly");
					
				}else if(paymentList.get(i).getPaymentType() == 3){
					
					paymentList.get(i).setPaymentTypeName("Quarterly");
					
				}else if(paymentList.get(i).getPaymentType() == 4){
					
					paymentList.get(i).setPaymentTypeName("Semi-Annual");
					
				}else if(paymentList.get(i).getPaymentType() == 5){
					
					paymentList.get(i).setPaymentTypeName("Annual");
				}
				
			}
		return paymentList;
	}
	
	@Override
	public void updateSendInvoiceDate(PaymentDetails paymentDetails) {
		String dateTime = DateTimeUtil.getSysDateTime();
		paymentDetails.setUpdatedDatetime(dateTime);
		
		paymentDao.updateSendInvoiceDate(paymentDetails);
	}
	
	@Override
	public String createInvoiceLetterPdf(int paymentId,int companyId) {
		
		   String pdfName = null;
		   String pdfPath = null;
		   String title   = null;
		   
		   try {
						   		
			   PaymentDetails paymentDetails = paymentDao.getPaymentDetailsByPaymentId(paymentId);
			   CompanyProfile companyDetails = companyDao.getCompanyDetailsByCompanyId(companyId);
			   //List<User> userDetailsList = userdao.getAllUserDetailsByCompanyId(companyId);
			   		
			   			//double totalAmount 	= paymentDetails.getTotalAmount();
			   			//double gstAmount 	= 0.00;
			   					
						Document document  = new Document(PageSize.A5,20, 20, 20, 24);
												
						String currentDate = DateTimeUtil.getSysDate();
						String companyName = companyDetails.getCompanyName().replaceAll("[^a-zA-Z0-9_]", "_");
						
						title       	   = companyId+"_"+companyName+"_"+paymentId;
								
						File theDir = new File(ConstantsUtil.FILE_SAVE_LOCATION +companyName.replaceAll("_+", "_")); 
						
						if (!theDir.exists()){ 
							
							 theDir.mkdirs();
						}
						pdfName = (theDir+"\\"+title).replaceAll("_+", "_")+".pdf";		
						pdfPath = (companyName+"\\"+title).replaceAll("_+", "_")+".pdf";
						
						PdfWriter .getInstance(document, new FileOutputStream(pdfName));
						
						document.open();
											
						document.addAuthor("YSM Software");
				        document.addCreationDate();
				        document.addCreator("BuilderApp");
				        document.addTitle("Invoice Details");
				       	       	        	       			
					    Font font1 = new Font(FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
					    Font font2 = new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
					    Font font22 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.DARK_GRAY);
					    Font font3 = new Font(FontFamily.HELVETICA, 10, Font.BOLD+Font.UNDERLINE, BaseColor.DARK_GRAY);
					    Font font4 = new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.DARK_GRAY);
					    Font font5 = new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.DARK_GRAY);
					    Font font6 = new Font(FontFamily.HELVETICA, 11, Font.BOLD, BaseColor.DARK_GRAY);
					    Font font7 = new Font(FontFamily.HELVETICA, 9, Font.BOLD, BaseColor.BLACK);
					    Font font8 = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
					    
					    LineSeparator ls = new LineSeparator(1, 100, new BaseColor(220, 220, 220), Element.ALIGN_CENTER, -2);
					    Chunk glue = new Chunk(new VerticalPositionMark());
					    NumberFormat format = new DecimalFormat("#0.00");
					    
					    ServletContext context = getServletContext();
					    URL resourceUrl = context.getResource("/images/ysm_header1.png");
					    Image img = Image.getInstance(resourceUrl);
					   // Image img = Image.getInstance("src/main/resources/static/images/ysm_header1.png");
					    img.scaleToFit(380.6F, 250.0F);
					    
					    
					    document.add(img);
					    Paragraph p20 = new Paragraph(" ");
					    document.add(p20);
					    
					    Chunk invoice  = new Chunk("Invoice/Receipt", font1);
					    Chunk date  = new Chunk("Date: ", font4);
					    Chunk datevalue = new Chunk(paymentDetails.getPaidDate(), font5);
					    Chunk addrYsm1 = new Chunk("Office No. 2, 1st Floor,", font4);
					    Chunk addrYsm2 = new Chunk("Dhandai Apt., Near Sahyadri Nagar,", font4);
					    Chunk addrYsm3 = new Chunk("Ambad Police Station Road,", font4);
					    Chunk addrYsm4 = new Chunk("Nashik - 422 009", font4);
					  
					   /* Paragraph p1 = new Paragraph("Invoice/Receipt", font1);
					    p1.setAlignment(Element.ALIGN_LEFT);
					    document.add(p1);
					    
					    Paragraph p2 = new Paragraph();
					    p2.setAlignment(Element.ALIGN_LEFT);
					    p2.add(date);
					    p2.add(datevalue);	
					    p2.add(new Paragraph(" "));
					    document.add(p2);*/
					    
					    Paragraph p18 = new Paragraph("YSM Software, ", font2);
					    p18.setAlignment(Element.ALIGN_LEFT);
					    p18.add(glue);
					    p18.add(invoice);
					    document.add(p18);	
					    
					    Paragraph p19 = new Paragraph(addrYsm1);
					    p19.setAlignment(Element.ALIGN_LEFT);
					    p19.add(glue);
					    p19.add(date);
					    p19.add(datevalue);
					    document.add(p19);	
					    
					    Paragraph p21 = new Paragraph(addrYsm2);
					    p21.setAlignment(Element.ALIGN_LEFT);
					    document.add(p21);
					    
					    Paragraph p22 = new Paragraph(addrYsm3);
					    p22.setAlignment(Element.ALIGN_LEFT);
					    document.add(p22);
					    
					    Paragraph p23 = new Paragraph(addrYsm4);
					    p23.setAlignment(Element.ALIGN_LEFT);
					    p23.add(new Paragraph(" "));
					    document.add(p23);
					  
					    Paragraph p3 = new Paragraph("Bill To, ", font7);
					    p3.setAlignment(Element.ALIGN_LEFT);
					    document.add(p3);	
					    
					    Paragraph p4 = new Paragraph(companyDetails.getCompanyName(), font2);
					    p4.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p4);	
					    
					    
					    Paragraph p5 = new Paragraph(companyDetails.getAddress(), font4);					 
					    p5.setIndentationRight(170);
					    p5.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p5);
					    
					    String mobileNo = "";
					    if(!companyDetails.getLandline().isEmpty() && !companyDetails.getMobile().isEmpty()){
					    	mobileNo = "+91."+companyDetails.getMobile()+"/ "+companyDetails.getLandline();
					    }else if (companyDetails.getLandline().isEmpty() && !companyDetails.getMobile().isEmpty()){
					    	mobileNo = "+91."+companyDetails.getMobile();
					    }else if (!companyDetails.getLandline().isEmpty() && companyDetails.getMobile().isEmpty()){
					    	mobileNo = companyDetails.getLandline();
					    }else {
					    	mobileNo = "";
					    }
					    
					    Paragraph p6 = new Paragraph(mobileNo, font4);
					    p6.setAlignment(Element.ALIGN_LEFT);	
					    p6.add(new Paragraph(" "));
					    document.add(p6);
					    
					    /*float widthvalheader = 2;	
				         
			            PdfPTable pdfTable = new PdfPTable(4);
			            
					    
					    pdfTable.setWidthPercentage(100);
			             float[] columnWidths = new float[] {20,20,25,35};
			             pdfTable.setWidths(columnWidths);
			             pdfTable.getDefaultCell().setUseAscender(true);
			             pdfTable.getDefaultCell().setUseDescender(true);
			             pdfTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			             
			             PdfPCell cell01 = new PdfPCell(new Phrase("Bill To, ", font7));
			             cell01.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell01.setPadding(widthvalheader);
			             cell01.setBorder(Rectangle.NO_BORDER);
			             cell01.setColspan(4);
			             pdfTable.addCell(cell01);
			             
			             PdfPCell cell02 = new PdfPCell(new Phrase(companyDetails.getCompanyName(), font2));
			             cell02.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell02.setPadding(widthvalheader);
			             cell02.setBorder(Rectangle.NO_BORDER);
			             cell02.setColspan(2);
			             pdfTable.addCell(cell02);
			             
			             PdfPCell cellB1 = new PdfPCell(new Phrase());
			             cellB1.setPadding(widthvalheader);
			             cellB1.setBorder(Rectangle.NO_BORDER);
			             pdfTable.addCell(cellB1);
			            
			             PdfPCell cell03 = new PdfPCell(new Phrase("YSM Software",font2));
			             cell03.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell03.setPadding(widthvalheader);
			             cell03.setBorder(Rectangle.NO_BORDER);
			             pdfTable.addCell(cell03);
			             
			             PdfPCell cell04 = new PdfPCell(new Phrase(companyDetails.getAddress(), font4));
			             cell04.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell04.setPadding(widthvalheader);
			             cell04.setBorder(Rectangle.NO_BORDER);
			             cell04.setColspan(2);
			             pdfTable.addCell(cell04);
			             
			             PdfPCell cellB2 = new PdfPCell(new Phrase());
			             cellB2.setPadding(widthvalheader);
			             cellB2.setBorder(Rectangle.NO_BORDER);
			             pdfTable.addCell(cellB2);
			             
			             PdfPCell cell05 = new PdfPCell(new Phrase(addrYsm));
			             cell05.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell05.setPadding(widthvalheader);
			             cell05.setBorder(Rectangle.NO_BORDER);
			             cell05.setRowspan(2);
			             pdfTable.addCell(cell05);
			             
			             PdfPCell cell06 = new PdfPCell(new Phrase(mobileNo, font4));
			             cell06.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell06.setPadding(widthvalheader);
			             cell06.setBorder(Rectangle.NO_BORDER);
			             cell06.setColspan(3);
			             pdfTable.addCell(cell06);
			             
			             document.add(pdfTable);*/
					    
					    /*Paragraph p7 = new Paragraph("Mail Id: "+paymentDetails.getCompanyEmail(), font5);
					    p7.setAlignment(Element.ALIGN_LEFT);
					    p7.add(new Paragraph(" "));
					    document.add(p7);*/
					    
					    Chunk chunk2 = new Chunk("Payment Details: ", font22);
					    
					    Paragraph p8 = new Paragraph();
					    p8.add(chunk2);
					    p8.setAlignment(Element.ALIGN_LEFT);
					    p8.add(ls);
					    p8.setSpacingAfter(7f);
					    document.add(p8);
					    
					   // document.add(new Chunk(ls));
					    
					    /*
					    float widthvalheader3 = 4;	
				         
			            PdfPTable pdfTable1 = new PdfPTable(2);
			            
					    
					    pdfTable1.setWidthPercentage(90);
			             float[] columnWidths = new float[] {90};
			             pdfTable1.setWidths(columnWidths);
			             pdfTable1.getDefaultCell().setUseAscender(true);
			             pdfTable1.getDefaultCell().setUseDescender(true);
			             pdfTable1.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             
			             PdfPCell cell01 = new PdfPCell(new Phrase("Payment Type : "+paymentDetails.getPaymentTypeName(),font6));
			             cell01.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell01.setPadding(widthvalheader3);
			             cell01.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell01);
			             
			             PdfPCell cell02 = new PdfPCell(new Phrase("Valid From Date : "+paymentDetails.getStartDate(),font6));
			             cell02.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell02.setPadding(widthvalheader3);
			             cell02.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell02);
			             
			             PdfPCell cell03 = new PdfPCell(new Phrase("Valid Till Date : "+paymentDetails.getStartDate(),font6));
			             cell03.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell03.setPadding(widthvalheader3);
			             cell03.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell03);
			             
			             
			             PdfPCell cell04 = new PdfPCell(new Phrase("Paid Date : "+paymentDetails.getStartDate(),font6));
			             cell04.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell04.setPadding(widthvalheader3);
			             cell04.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell04);
			             
			             PdfPCell cell05 = new PdfPCell(new Phrase("Gross Amount : "+paymentDetails.getAmount(),font6));
			             cell05.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell05.setPadding(widthvalheader3);
			             cell05.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell05);
			             
			             PdfPCell cell06 = new PdfPCell(new Phrase("Discount : "+paymentDetails.getDiscount(),font6));
			             cell06.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell06.setPadding(widthvalheader3);
			             cell06.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell06);
			             
			             PdfPCell cell07 = new PdfPCell(new Phrase("GST Amount : 0.00",font6));
			             cell07.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell07.setPadding(widthvalheader3);
			             cell07.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell07);
			             
			             PdfPCell cell08 = new PdfPCell(new Phrase("Total : "+paymentDetails.getTotalAmount() +" INR",font6));
			             cell08.setHorizontalAlignment(Element.ALIGN_RIGHT);
			             cell08.setPadding(widthvalheader3);
			             cell08.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell08);*/
					    String PaymentType	= null;
					    double amount 		= paymentDetails.getAmount();
					    double dicount 		= paymentDetails.getDiscount();
					    double gst  		= paymentDetails.getGst();
					    double priceAmount	= 0.00;
					    double discountAmount = 0.00;
					    double gstAmount = 0.00;
					    double grossAmount = 0.00;
					    if(paymentDetails.getPaymentType()==1){
					    	PaymentType = "Demo Version";
					    	priceAmount		= 0.00;
					    	discountAmount	= 0.00;
					    	gstAmount = 0.00;
					    	grossAmount = 0.00;
					    }else if(paymentDetails.getPaymentType()==2){
					    	PaymentType = "Monthly";
					    	priceAmount		= amount;
					    	discountAmount	= priceAmount*dicount/100;
					    	grossAmount     = priceAmount-discountAmount;
					    	gstAmount		= grossAmount*gst/100;
					    }else if(paymentDetails.getPaymentType()==3){
					    	PaymentType = "Quarterly";
					    	priceAmount		= amount*3;
					    	discountAmount	= priceAmount*dicount/100;
					    	grossAmount     = priceAmount-discountAmount;
					    	gstAmount		= grossAmount*gst/100;
					    }else if(paymentDetails.getPaymentType()==4){
					    	PaymentType = "Semi-Annual";
					    	priceAmount		= amount*6;
					    	discountAmount	= priceAmount*dicount/100;
					    	grossAmount     = priceAmount-discountAmount;
					    	gstAmount		= grossAmount*gst/100;
					    }else if(paymentDetails.getPaymentType()==5){
					    	PaymentType = "Annual";
					    	priceAmount		= amount*12;
					    	discountAmount	= priceAmount*dicount/100;
					    	grossAmount     = priceAmount-discountAmount;
					    	gstAmount		= grossAmount*gst/100;
					    }
					    
					    //Chunk chunkPtype = new Chunk(PaymentType, font4);
					    Chunk chunkApp	 = new Chunk("CRM App", font2);
					    //Chunk chunkUsers	 = new Chunk("No. Of users:", font4);
					    //Chunk chunkTerm	 = new Chunk("Term:"+PaymentType, font4);
					   
					    Paragraph p9 = new Paragraph();
					    p9.add(chunkApp);
					    document.add(p9);	
					    
					    Chunk chunkSdate	 = new Chunk("Valid From Date :"+paymentDetails.getStartDate(), font4);
					    Chunk chunk1 = new Chunk("Project : "+paymentDetails.getProjectName(), font5);
					    Paragraph p10 = new Paragraph();
					    //Paragraph p10 = new Paragraph("No. Of users : "+userDetailsList.size(), font4);
					    p10.add(chunk1);
					    p10.add(glue);
					    p10.add(chunkSdate);
					    p10.setSpacingBefore(7f);
					    document.add(p10);
					    
					   // Chunk chunkSdate = new Chunk(, font4);
					    Chunk chunkEdate = new Chunk("Valid Till Date : "+paymentDetails.getEndDate(), font4);
					    Paragraph p11 = new Paragraph("Term : "+PaymentType, font4);
					    p11.add(glue);
					    p11.add(chunkEdate);			   
					    document.add(p11);
					    
					    /*Paragraph p12 = new Paragraph("Term:"+PaymentType, font4);
					    p12.setAlignment(Element.ALIGN_RIGHT);
					    document.add(p12);
					    
					    Chunk chunkPdate = new Chunk(paymentDetails.getPaidDate(), font4);
					    Paragraph p12 = new Paragraph("Paid Date : ", font6);
					    p12.add(chunkPdate);
					    p12.setAlignment(Element.ALIGN_RIGHT);
					    document.add(p12);*/
					    
					    document.add(new Chunk(ls));
					    
					    Paragraph p13 = new Paragraph("Purchase Price : "+format.format(priceAmount), font4);
					    p13.setAlignment(Element.ALIGN_RIGHT);
					    p13.setSpacingBefore(0f);
					    document.add(p13);
					    
					    Paragraph p14 = new Paragraph("Discount("+format.format(paymentDetails.getDiscount())+"%) : "+format.format(discountAmount), font4);
					    p14.setAlignment(Element.ALIGN_RIGHT);
					    document.add(p14);
					    
					    Paragraph p17 = new Paragraph("Gross Amount : "+format.format(grossAmount), font4);
					    p17.setAlignment(Element.ALIGN_RIGHT);
					    document.add(p17);
					    
					    Paragraph p15 = new Paragraph("GST("+format.format(paymentDetails.getGst())+"%) : "+format.format(gstAmount), font4);
					    p15.setAlignment(Element.ALIGN_RIGHT);
					    document.add(p15);
					    
					    document.add(new Chunk(ls));
					    
					    Chunk chunkRupee = new Chunk(" Rs. "+format.format(paymentDetails.getTotalAmount()), font1);
					    Chunk chunkInr = new Chunk(" INR", font4);
					    
					    Paragraph p16 = new Paragraph("Total : ", font1);
					    p16.add(chunkRupee);
					    p16.add(chunkInr);
					    p16.setAlignment(Element.ALIGN_RIGHT);
					    p16.setSpacingBefore(2f);
					    document.add(p16);
					   
			            document.close();
			   			
		            }catch (Exception i){
					
					    i.printStackTrace();
				}
		   return pdfPath;
		
	}

	
}
