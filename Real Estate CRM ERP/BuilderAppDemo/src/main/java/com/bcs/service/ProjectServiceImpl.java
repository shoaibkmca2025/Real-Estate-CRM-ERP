package com.bcs.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.AmenitiesDocumentsDao;
import com.bcs.dao.BankDetailsDao;
import com.bcs.dao.DisbursementDao;
import com.bcs.dao.ProjectDao;
import com.bcs.dao.WingDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.Amenities;

import com.bcs.model.CountDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.Wing;
import com.bcs.model.WingDetails;
import com.bcs.utility.CommonUtil;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectDao projectdao;
	
	@Autowired
	private WingDao wingDao;
	
	@Autowired  
	private AmenitiesDocumentsDao amenitiesdao; 	
	
	@Autowired  
	private BankDetailsDao bankdao; 

	@Autowired  
	private DisbursementDao disbursementDao;
	
	@Autowired  
	private ActivityDao activityDao;
	
	@Autowired
	UtilityService utilityService;
	
	@Override
	public boolean addAllDetails(ProjectDetails project, int projectTranId,int wingTranId) throws ParseException {	
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		 boolean isRecordAdded = false;
		 
		// SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		 
		// Date date = sdf.parse(project.getStartDate());
		 String startDate = output.format(project.getStartDate1());
		 
		// Date date1 = sdf.parse(project.getExpectedCompletionDate());
		 String expectedCompletionDate = output.format(project.getExpectedCompletionDate1());
			
		 project.setStartDate(startDate);
		 project.setExpectedCompletionDate(expectedCompletionDate);
		 project.setCreatedDatetime(dateTime);
		 project.setUpdatedDatetime(dateTime);
		 project.setIsApproved(1);
		 
		 isRecordAdded = projectdao.addAllDetails(project, projectTranId, wingTranId);	
		 
		 if(isRecordAdded==true){
				 ActivityLog activityLog = new ActivityLog();
				 
				 if(project.getSubUserId() == null || project.getSubUserId() == ""){
					 activityLog.setUserId(project.getUserId()); 
				 }else{
					 activityLog.setUserId(Integer.parseInt(project.getSubUserId()));
				 }
				 
				 activityLog.setActivityDescription("Project added");
				 activityLog.setProjectTranId(project.getProjectTranId());
				 activityLog.setActivityDatetime(dateTime);
				 activityDao.addActivityDetails(activityLog);
			}
		 
		 return isRecordAdded;
	}

	@Override
	public int getRecentProjectId() {		
		return projectdao.getRecentProjectId();
	}

	@Override
	public List<ProjectDetails> getAllProjectDetails(int userType, int companyId, int projectStatus) {		
		return projectdao.getAllProjectDetails(userType, companyId, projectStatus);
	}
	
	@Override
    public int getRecentWingId() {		
		return projectdao.getRecentWingId();
	}

	@Override
	public ProjectDetails getProjectDetailsById(int projectId) {
	
		 ProjectDetails project = projectdao.getProjectDetailsById(projectId);
		 int status = project.getProjectStatus();
         
         HashMap<Integer, String> projectStatusHashMap  = CommonUtil.getProjectStatus();

         String projectStatus = projectStatusHashMap.get(status);
         project.setProjectStatusName(projectStatus);
         
         project.setWingList(wingDao.getWingListByProjectId(projectId));
         project.setAmenitiesList(amenitiesdao.getAmenitiesById(projectId));
         project.setDocumentsList(amenitiesdao.getDocumentsById(projectId));
         project.setBankDetailsList(bankdao.getBankDetailsListById(projectId));
         project.setDisbursementList(disbursementDao.getProjectDisbursementListByProjectId(projectId));
		 return project;
	}
	
	@Override
	public ProjectDetails getProjectDetailsByProjectTransId(int projectId) {
	
		 ProjectDetails project = projectdao.getProjectDetailsById(projectId);
		 int status = project.getProjectStatus();
         
         HashMap<Integer, String> projectStatusHashMap  = CommonUtil.getProjectStatus();

         String projectStatus = projectStatusHashMap.get(status);
         project.setProjectStatusName(projectStatus);
         
         project.setWingList(wingDao.getWingListByProjectId(projectId));
         project.setAmenitiesList(amenitiesdao.getAmenitiesById(projectId));
		 return project;
	}

	@Override
	public boolean updateProjectBasicDetails(ProjectDetails project) throws ParseException {	
		
		String dateTime = DateTimeUtil.getSysDateTime();
		boolean isRecordUpdated = false;
		project.setUpdatedDatetime(dateTime);
		int status = project.getProjectStatus();
		
		 if(status == 0){
			 project.setProjectStatus(3);
		 }
		 
		 //SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		 SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		 
		 //Date date = sdf.parse(project.getStartDate());
		 String startDate = output.format(project.getStartDate1());
		
		// Date date1 = sdf.parse(project.getExpectedCompletionDate());
		 String expectedCompletionDate = output.format(project.getExpectedCompletionDate1());		
			
		 project.setStartDate(startDate);
		 project.setExpectedCompletionDate(expectedCompletionDate);
		 
		 isRecordUpdated = projectdao.updateProjectBasicDetails(project);	
		
		if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			
			 activityLog.setUserId(project.getUserId()); 
			 activityLog.setActivityDescription("Project Basic Details Updated");
			 activityLog.setActivityDatetime(dateTime);
			 activityLog.setProjectTranId(project.getProjectTranId());
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordUpdated;
	}

/*	@Override
	public ProjectDetails deleteProjectdetailse(int projectId) {	
		
		return projectdao.deleteProjectdetailse(projectId);	
		
	}*/
	
	@Override
	public List<ProjectDetails> getAllProjectsByStatus(int projectStatus, int userType, int id) {	
		return projectdao.getAllProjectsByStatus(projectStatus, userType, id);
	}

	@Override
	public boolean deleteProjectDetails(int projectId, int userId) {	
		boolean isRecordDeleted = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordDeleted = projectdao.deleteProjectDetails(projectId);
		
		if(isRecordDeleted==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("Project Deleted");
			 activityLog.setProjectTranId(projectId);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordDeleted;
	}

	@Override
	public boolean updateProjectStatus(int projectId, int projectStatus, int userId) {		
		
		boolean isRecordUpdated = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordUpdated =projectdao.updateProjectStatus(projectId, projectStatus, userId);
		
		if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("Project Status Updated");
			 activityLog.setProjectTranId(projectId);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordUpdated;
	}

	@Override
	public List<Wing> getProjectStructureByProjectId(int projectId) {
		
		return projectdao.getProjectStructureByProjectId(projectId);
	}

	@Override
	public List<Wing> getBookingDetailsByWingTranId(int wingTranId) {
		
		return projectdao.getBookingDetailsByWingTranId(wingTranId);
	}

/*	@Override
	public List<ProjectDetails> getAllProjectDetailsByUserType(int userId) {
		// TODO Auto-generated method stub
		return projectdao.getAllProjectDetailsByUserType(userId);
	}

	@Override
	public List<ProjectDetails> getAllProjectsByStatusAndUserType(int projectStatus, int userId) {
		// TODO Auto-generated method stub
		return projectdao.getAllProjectsByStatusAndUserType(projectStatus, userId);
	}*/

	@Override
	public void allocateProjectsToUsers(ProjectDetails projectDetails) {
		
		 String dateTime = DateTimeUtil.getSysDateTime();
		 projectDetails.setUpdatedDatetime(dateTime);
		 boolean isRecordAllocated = false;
		 
		 isRecordAllocated =projectdao.allocateProjectsToUsers(projectDetails);	
		 
		 if(isRecordAllocated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(projectDetails.getUserId()); 
			 activityLog.setActivityDescription("ProjectId allocated to Subusers "+projectDetails.getSubUserId());
			 activityLog.setProjectTranId(projectDetails.getProjectTranId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
	}

	@Override
	public List<ProjectDetails> getAllUsersByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return projectdao.getAllUsersByProjectId(projectId);
	}


	@Override
	public String generateProjectDetailsPdf(ProjectDetails projectDetails) {
		// TODO Auto-generated method stub

		   String pdfName = null;
		   String pdfPath = null;
		   String title   = null;
		   List<WingDetails>  wingDetails = null;
		   
		   try {
			   			projectDetails     = getProjectDetailsByProjectTransId(projectDetails.getProjectTranId());

						Document document  = new Document(PageSize.A4,12, 12, 12, 24);
												
						String currentDate = DateTimeUtil.getSysDate();
						title       	   = projectDetails.getProjectTranId()+"_"+projectDetails.getProjectName();
								
						File theDir = new File(ConstantsUtil.FILE_SAVE_LOCATION+projectDetails.getProjectName()); 
						
						if (!theDir.exists()){
							
							 theDir.mkdirs();
						}
						
						pdfName = theDir+"\\"+title+".pdf";		
						pdfPath = projectDetails.getProjectTranId()+"\\"+projectDetails.getProjectName()+".pdf";
						 
						PdfWriter .getInstance(document, new FileOutputStream(pdfName));
						
						document.open();
											
						document.addAuthor("YSM Software");
				        document.addCreationDate();
				        document.addCreator("BuilderApp");
				        document.addTitle("Project Details");
				       	       	        	       			
					    Font font1 = new Font(FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
					    //Font font2 = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
					    Font font3 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD+Font.UNDERLINE, BaseColor.BLACK);
					    Font font4 = new Font(FontFamily.TIMES_ROMAN, 11, Font.NORMAL, BaseColor.BLACK);
					    Font font5 = new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);
					    Font font6 = new Font(FontFamily.TIMES_ROMAN, 11, Font.BOLD, BaseColor.BLACK);
					    Font font7 = new Font(FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
					    Font zapfdingbats = new Font(FontFamily.ZAPFDINGBATS, 6);
					    //Font font7 = new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
					    
					    
					   // Chunk date  = new Chunk("Date: ", font4);
					   // Chunk datevalue = new Chunk(currentDate, font5);
					    String ProjectStatus=null;
					    int projStatus=projectDetails.getProjectStatus();
					    if(projStatus==1){
					    	ProjectStatus="Upcoming";
					    }else{
					    	ProjectStatus="In Progress";
					    }
					    
					    
					    Paragraph p1 = new Paragraph();
					    p1.setAlignment(Element.ALIGN_RIGHT);
					   // p1.add(date);
					   // p1.add(datevalue);	
					    document.add(p1);
					    
					    Paragraph p2 = new Paragraph("Project Brochure", font1);
					    p2.setAlignment(Element.ALIGN_CENTER);
					    document.add(p2);
					  
					    Paragraph p3 = new Paragraph("  ", font6);
					    p3.setAlignment(Element.ALIGN_LEFT);
					    document.add(p3);	
					    
					    /*Paragraph p4 = new Paragraph("Project Status : "+ProjectStatus, font6);
					    p4.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p4);	
					    
					    Paragraph p5 = new Paragraph("Project Name : "+projectDetails.getProjectName(), font6);
					    p5.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p5);
					    
					    Paragraph p6 = new Paragraph("Address: "+projectDetails.getAddress(), font6);
					    p6.setAlignment(Element.ALIGN_LEFT);				   
					    document.add(p6);
					    
					    Paragraph p7 = new Paragraph("Start Date: "+projectDetails.getStartDate(), font6);
					    p7.setAlignment(Element.ALIGN_LEFT);
					    document.add(p7);
					    
					    Paragraph p8 = new Paragraph("Completion  Date: "+projectDetails.getExpectedCompletionDate(), font6);
					    p8.setAlignment(Element.ALIGN_LEFT);
					    p8.add(new Paragraph(" "));
					    document.add(p8);*/
					    
					   
					    
					    float widthvalheader3 = 4;	
				         
			            PdfPTable pdfTable1 = new PdfPTable(2);
			            
					    
					    pdfTable1.setWidthPercentage(50);
			             float[] columnWidths3 = new float[] {15,20};
			             pdfTable1.setWidths(columnWidths3);
			             pdfTable1.getDefaultCell().setUseAscender(true);
			             pdfTable1.getDefaultCell().setUseDescender(true);
			             pdfTable1.setHorizontalAlignment(Element.ALIGN_LEFT);
			             
			             PdfPCell cell01 = new PdfPCell(new Phrase("Project Status ",font6));
			             cell01.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell01.setPadding(widthvalheader3);
			             cell01.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell01);
			             
			             PdfPCell cell02 = new PdfPCell(new Phrase(": "+ProjectStatus,font6));
			             cell02.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell02.setPadding(widthvalheader3);
			             cell02.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell02);
			            
			             PdfPCell cell03 = new PdfPCell(new Phrase("Project Name",font6));
			             cell03.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell03.setPadding(widthvalheader3);
			             cell03.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell03);
			             
			             PdfPCell cell04 = new PdfPCell(new Phrase(": "+projectDetails.getProjectName(),font6));
			             cell04.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell04.setPadding(widthvalheader3);
			             cell04.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell04);
			             
			             PdfPCell cell05 = new PdfPCell(new Phrase("Address",font6));
			             cell05.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell05.setPadding(widthvalheader3);
			             cell05.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell05);
			             
			             PdfPCell cell06 = new PdfPCell(new Phrase(": "+projectDetails.getAddress(),font6));
			             cell06.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell06.setPadding(widthvalheader3);
			             cell06.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell06);
			             
			             PdfPCell cell07 = new PdfPCell(new Phrase("Start Date",font6));
			             cell07.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell07.setPadding(widthvalheader3);
			             cell07.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell07);
			             
			             PdfPCell cell08 = new PdfPCell(new Phrase(": "+projectDetails.getStartDate(),font6));
			             cell08.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell08.setPadding(widthvalheader3);
			             cell08.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell08);
			             
			             PdfPCell cell09 = new PdfPCell(new Phrase("Completion  Date",font6));
			             cell09.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell09.setPadding(widthvalheader3);
			             cell09.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell09);
			             
			             PdfPCell cell10 = new PdfPCell(new Phrase(": "+projectDetails.getExpectedCompletionDate(),font6));
			             cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
			             cell10.setPadding(widthvalheader3);
			             cell10.setBorder(Rectangle.NO_BORDER);
			             pdfTable1.addCell(cell10);
			             
			             document.add(pdfTable1);
			             
			             Paragraph p8 = new Paragraph(" ");
						 document.add(p8);
					    
					    Chunk chunk1 = new Chunk("Wing Info: ", font3);
					   
					   // Chunk chunk2 = new Chunk("Payment towards the purchase of Flat No.", font2);
					  //  Chunk chunk3 = new Chunk(projectDetails.getWingList(), font6);
					 
					    Paragraph p9 = new Paragraph();
					    p9.add(chunk1);
					    //p9.add(chunk2);
					   // p9.add(chunk3);
					    p9.setAlignment(Element.ALIGN_LEFT);
					    p9.add(new Paragraph(" "));
					    document.add(p9);
					    
					  
					    
					    float widthvalheader = 4;	
			         
			            PdfPTable pdfTable = new PdfPTable(5);
			            
			            //int row = 0;		  
			           
		            	 Iterator<Wing> itr     = projectDetails.getWingList().iterator();
			            	
			   			  while (itr.hasNext()) {
			   				
			   				Wing wing           = (Wing)itr.next();	 
			   			    int wingId   = wing.getWingTranId(); 
			   				wingDetails = wingDao.getAllWingDetailsByWingId(wingId);
			   		        String wingName     = wing.getWingName();       
			   		       // int noOfFloors      = wing.getNoOfFloors();
			   		        int totalFlats      = wing.getTotalFlats();
			   		          	
			            //	row++; 
			   		     
			            	
			             pdfTable.setWidthPercentage(90);
			             float[] columnWidths = new float[] {12,15,12,12,12};
			             pdfTable.setWidths(columnWidths);
			             pdfTable.getDefaultCell().setUseAscender(true);
			             pdfTable.getDefaultCell().setUseDescender(true);
			             pdfTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			             
			             PdfPCell cell1 = new PdfPCell(new Phrase(wingName,font6));
			             cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell1.setPadding(widthvalheader);
			             cell1.setColspan(5);
			             pdfTable.addCell(cell1);
			             
			             PdfPCell cell2 = new PdfPCell(new Phrase("Floor No.",font6));
			             cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			            
			             cell2 = new PdfPCell(new Phrase("Property Type",font6));
			             cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			             
			             cell2 = new PdfPCell(new Phrase("Property",font6));
			             cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			             
			             cell2 = new PdfPCell(new Phrase("Area",font6));
			             cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			             
			             cell2 = new PdfPCell(new Phrase("Quantity",font6));
			             cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell2.setPadding(widthvalheader);
			             pdfTable.addCell(cell2);
			             
			            /* int row = 0;		            	
		            	 Iterator<Wing> itr     = projectDetails.getWingList().iterator();
			            	
			   			  while (itr.hasNext()) {
			   				
			   				Wing wing           = (Wing)itr.next();	          
			   		        String wingName     = wing.getWingName();       
			   		        int noOfFloors      = wing.getNoOfFloors();
			   		        int totalFlats      = wing.getTotalFlats();
			   		          	
			            	row++;*/
			             Iterator<WingDetails> itr3     = wingDetails.iterator();
			            	
			   			  while (itr3.hasNext()) {
			   				
			   				WingDetails wingDetailsInfo           = (WingDetails)itr3.next();	          
			   		        int floorNo     = wingDetailsInfo.getFloorNumber();     
			   		        String propertyType      = wingDetailsInfo.getPropertyTypeDescr();
			   		        String property      = wingDetailsInfo.getPropertyName();
			   		        double area      = wingDetailsInfo.getPropertyArea();
			   		        int qty      = wingDetailsInfo.getNoOfFlats();
			            	
			   		        
			   		        String floor=null;
			   		        if(floorNo == 0){
			   		        	floor = "Ground Floor";
			   		        }else if(floorNo == -1){
			   		        	floor = "Basement";
			   		        }else{
			   		        	floor = "Floor No "+floorNo;
			   		        }
			   		        
					       PdfPCell table_cell2 = new PdfPCell(new Phrase(floor,font4));
					        table_cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
					        table_cell2.setPadding(widthvalheader);
					        pdfTable.addCell(table_cell2); 
			            	
			            	PdfPCell table_cell3 = new PdfPCell(new Phrase(propertyType,font4));
					        table_cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
					        table_cell3.setPadding(widthvalheader);
					        pdfTable.addCell(table_cell3); 
			            				       			        
					        PdfPCell table_cell4 = new PdfPCell(new Phrase(property,font4));
					        table_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
					        table_cell4.setPadding(widthvalheader);
					        pdfTable.addCell(table_cell4); 
					        				  
					        PdfPCell table_cell5 = new PdfPCell(new Phrase(""+area+"",font4));
					        table_cell5.setHorizontalAlignment(Element.ALIGN_RIGHT);
					        table_cell5.setPadding(widthvalheader);
					        pdfTable.addCell(table_cell5); 
					        
					        PdfPCell table_cell6 = new PdfPCell(new Phrase(""+qty+"",font4));
					        table_cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);
					        table_cell6.setPadding(widthvalheader);
					        pdfTable.addCell(table_cell6); 
					        
					        if (!itr3.hasNext()) {
					        	
					        	PdfPCell table_cell7 = new PdfPCell(new Phrase("Total ",font6));
					        	table_cell7.setHorizontalAlignment(Element.ALIGN_RIGHT);
					        	table_cell7.setPadding(widthvalheader);
					        	table_cell7.setColspan(4);
					             pdfTable.addCell(table_cell7);
					        	
					        	PdfPCell table_cell8 = new PdfPCell(new Phrase(""+totalFlats+"",font6));
						        table_cell8.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        table_cell8.setPadding(widthvalheader);
						        pdfTable.addCell(table_cell8); 
						        
						        PdfPCell table_cell9 = new PdfPCell(new Phrase(" "));
						        table_cell9.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        table_cell9.setPadding(widthvalheader);
						        table_cell9.setColspan(5);
						        table_cell9.setBorderWidth(0);
						        pdfTable.addCell(table_cell9); 
						        
					        }
					        
			   			  }
			   			  //document.add(pdfTable);
				   		  
			   			  }
			   			document.add(pdfTable);
			   			/*Paragraph p12 = new Paragraph();
						  p12.add(new Paragraph(" "));
					      document.add(p12);*/
			   		 Chunk chunk2 = new Chunk("Amenities: ", font3);
			   		 Paragraph p10 = new Paragraph();
				     p10.add(chunk2);
				     p10.setAlignment(Element.ALIGN_LEFT);
					 p10.add(new Paragraph(" "));
				     document.add(p10);
			   			
			   		   /*float widthvalheader1 = 2;	
				         
			            PdfPTable pdfTable2 = new PdfPTable(2);
			            
			             pdfTable2.setWidthPercentage(70);
			             float[] columnWidths2 = new float[] {8,18};
			             pdfTable2.setWidths(columnWidths2);
			             pdfTable2.getDefaultCell().setUseAscender(true);
			             pdfTable2.getDefaultCell().setUseDescender(true);
			             pdfTable2.setHorizontalAlignment(Element.ALIGN_LEFT);
			             
			             PdfPCell cell11 = new PdfPCell(new Phrase("Sr.No.",font6));
			             cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell11.setPadding(widthvalheader1);
			             pdfTable2.addCell(cell11);
			            
			             cell11 = new PdfPCell(new Phrase("Amenities",font6));
			             cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			             cell11.setPadding(widthvalheader1);
			             pdfTable2.addCell(cell11);*/
			   			  
			   			int row1 = 0;		            	
		            	 Iterator<Amenities> itr1     = projectDetails.getAmenitiesList().iterator();
			            	
			   			  while (itr1.hasNext()) {
			   				
			   				Amenities aminities = (Amenities)itr1.next();	          
			   		        String aminity     = aminities.getAminities();
			   		          	
			            	row1++;
			            	
			            	//Chunk chunk4 = new Chunk("Sub: ", font6);
						    Chunk bullet = new Chunk(String.valueOf((char) 108), zapfdingbats);
						    Chunk chunk5 = new Chunk(aminity, font7);
						 
						    Paragraph p11 = new Paragraph();
						   // p11.add(chunk4);
						    p11.add(bullet);
						    p11.add("    "+chunk5);
						    p11.setAlignment(Element.ALIGN_LEFT);
						    p11.setIndentationLeft(50);
						   //p11.add(new Paragraph(" "));
						    document.add(p11);
					       /* PdfPCell table_cell2 = new PdfPCell(new Phrase(row1+"",font4));
					        table_cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
					        table_cell2.setPadding(widthvalheader);
					        pdfTable2.addCell(table_cell2); 
			            	
			            	PdfPCell table_cell3 = new PdfPCell(new Phrase(aminity,font4));
					        table_cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
					        table_cell3.setPadding(widthvalheader);
					        pdfTable2.addCell(table_cell3); */
					        
			            }
			   			//document.add(pdfTable2);
			            document.close();
			   			
			   	
		            }catch (Exception i){
					
					    i.printStackTrace();
				}
		   return pdfPath;
	}

	@Override
	public CountDetails getAllCountDetails(int userId, int userType, int projectStatus, int projectTranId, int companyId ) {
	
		return projectdao.getAllCountDetails(userId, userType, projectStatus, projectTranId,companyId);
	}

	@Override
	public boolean updateProjectApprovedStatus(int projectId, int isApproved) {
		
		return projectdao.updateProjectApprovedStatus(projectId, isApproved);
	}

	@Override
	public List<ProjectDetails> getAllUnapprovedProjectDetails() {
		List<ProjectDetails> projectNotificationsList = projectdao.getAllUnapprovedProjectDetails();
		
		return projectNotificationsList;
	}

	@Override
	public int checkIsAnyPropertyBookedByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return projectdao.checkIsAnyPropertyBookedByProjectId(projectId);
	}

/*	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public MonthWiseTotalVisits getDashboardDetails(int projectTranId) {
		
		List<MonthWiseTotalVisits> dashboardDetailsList = projectdao.getDashboardDetails(projectTranId);
		MonthWiseTotalVisits dashboardDetailsList1 = new MonthWiseTotalVisits();
		
		ArrayList<String> monthArray = new ArrayList<String>();
		ArrayList<String> yearArray  = new ArrayList<String>();
		
		ArrayList<String> totalVisitsArray 		 = new ArrayList<String>();
		ArrayList<String> totalClosedVisitsArray = new ArrayList<String>();
		ArrayList<String> totalClientArray 		 = new ArrayList<String>();
		
		for(int i = 0; i<dashboardDetailsList.size(); i++){
			
			monthArray.add(dashboardDetailsList.get(i).getMonth());
			yearArray.add(String.valueOf(dashboardDetailsList.get(i).getYear()));
			totalVisitsArray.add(dashboardDetailsList.get(i).getTotalVisit().toString());
			totalClosedVisitsArray.add(dashboardDetailsList.get(i).getTotalClosedVisits().toString());
			totalClientArray.add(dashboardDetailsList.get(i).getTotalClient().toString());
			
			//((ArrayList)allCountsArray.get(i)).add(new ArrayList().add(totalCountArray));
			//((ArrayList)allCountsArray.get(i)).set(i, new ArrayList().add(totalCountArray));
		}
		
		ArrayList allCountsArray = new ArrayList();
		allCountsArray.add(totalVisitsArray);
		allCountsArray.add(totalClosedVisitsArray);
		allCountsArray.add(totalClientArray);
		
		dashboardDetailsList1.setMonthArray(monthArray);
		dashboardDetailsList1.setTotalCountArray(allCountsArray);
		dashboardDetailsList1.setYearArray(yearArray);
		
		System.out.println(dashboardDetailsList1.toString());
		return dashboardDetailsList1;

	}*/

	@Override
	public boolean updateProjectStatusByScheduledTasks(List<String> projectIdsList) {
		return projectdao.updateProjectStatusByScheduledTasks(projectIdsList);
	}

	@Override
	public List<ProjectDetails> getInprogressAndCompletedProjectList(int userType, int userId) {
		// TODO Auto-generated method stub
		return projectdao.getInprogressAndCompletedProjectList(userType, userId);
	}

	
	
}   