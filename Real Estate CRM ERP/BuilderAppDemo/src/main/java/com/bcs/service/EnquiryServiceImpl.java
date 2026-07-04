package com.bcs.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.CompanyProfileDao;
import com.bcs.dao.EnquiryDao;
import com.bcs.dao.FollowupDao;
import com.bcs.model.ActivityLog;
import com.bcs.model.CloseEnquiry;
import com.bcs.model.EnquiryDetails;
import com.bcs.model.FollowupDetails;
import com.bcs.model.WingDetails;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	
	@Autowired
	EnquiryDao dao;
	
	@Autowired
	FollowupDao fdao;
	
	@Autowired
	CompanyProfileDao companyDao;
	
	@Autowired  
	private ActivityDao activityDao;
	
	@Autowired
	UtilityService utilityService;
	
		
	@Override
	public boolean addEnquirydetails(EnquiryDetails enquiryDetails) {
		
		//int builderId  =  enquiryDetails.getBuilderId();
		int companyId  =  enquiryDetails.getCompanyId();
		
		int firstFollowupDuration = utilityService.getSettingsByCompanyId(companyId).getFollowupNotificationDuration();
		
		String dateTime = DateTimeUtil.getSysDateTime();
		boolean isRecordAdded = false;
		enquiryDetails.setUpdatedDatetime(dateTime);	
		enquiryDetails.setCreatedDatetime(dateTime);	
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, firstFollowupDuration); // add 8 days-
		date = cal.getTime();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");   /*   "dd/MM/yyyy hh:mm:ss a"*/
		
		String followupDate = dateFormat.format(date);
		enquiryDetails.setFollowupDate(followupDate);
		enquiryDetails.setFollowupStatus(1);
		
		isRecordAdded = dao.addEnquiryDetails(enquiryDetails);
		
		 if(isRecordAdded==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(enquiryDetails.getUserId()); 
			 activityLog.setActivityDescription("EnquiryId "+enquiryDetails.getEnquiryId()+" added ");
			 activityLog.setProjectTranId(enquiryDetails.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
			 
			 int enquiryId = dao.getRecentEnquiryId();
				
			FollowupDetails followupDetails=new FollowupDetails();
			
			followupDetails.setEnquiryId(enquiryId);		
			followupDetails.setFollowupDescription("First Visit");	
			followupDetails.setCreatedDatetime(dateTime);
			followupDetails.setUpdatedDatetime(dateTime);
			followupDetails.setUserId(enquiryDetails.getUserId());	
			fdao.addFollowupDetails(followupDetails);
		}
		return isRecordAdded;
	}

	@Override
	public List<EnquiryDetails> getAllEnquiryDetails() {
		// TODO Auto-generated method stub
		return dao.getAllEnquiryDetails();
	}

	@Override
	public EnquiryDetails getEnquiryDetailsById(int id) {
		// TODO Auto-generated method stub
		return dao.getEnquiryDetailsById(id);
	}

	@Override
	public boolean updateEnquiryDetails(EnquiryDetails enquiryDetails) {
		boolean isRecordUpdated = false;
		String dateTime = DateTimeUtil.getSysDateTime();	
		
		isRecordUpdated=dao.updateEnquiryDetails(enquiryDetails);
		
		 if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(enquiryDetails.getUserId()); 
			 activityLog.setActivityDescription("EnquiryId "+enquiryDetails.getEnquiryId()+" updated ");
			 activityLog.setProjectTranId(enquiryDetails.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		 return isRecordUpdated;
	}

	@Override
	public boolean deleteEnquiryDetails(Integer enquiryId, int userId, int projectId) {
		boolean isRecordDeleted = false;
		String dateTime = DateTimeUtil.getSysDateTime();	
		
		isRecordDeleted=dao.deleteEnquiryDetails(enquiryId);
		
		if(isRecordDeleted==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("EnquiryId "+enquiryId+" Deleted ");
			 activityLog.setProjectTranId(projectId);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordDeleted;
	}

	@Override
	public List<WingDetails> getPropertyAreaByPropertyId(int projectId,int propertyTypeId, int propertyId) {
		
		return dao.getPropertyAreaByPropertyId(projectId,propertyTypeId,propertyId);
	}

	@Override
	public List<EnquiryDetails> getEnquiryDetailsByProjectId(int projectId) {
	
		return dao.getEnquiryDetailsByProjectId(projectId);
	}

	public int getRecentEnquiryId() {		
		return dao.getRecentEnquiryId();
	}

	@Override
	public boolean addClosedEnquiry(CloseEnquiry closeEnquiry) {
		
		boolean isRecordAdded = false;
		String dateTime = DateTimeUtil.getSysDateTime();	
		closeEnquiry.setCreatedDatetime(dateTime);
		closeEnquiry.setUpdatedDatetime(dateTime);
		
		isRecordAdded=dao.addClosedEnquiry(closeEnquiry);
		
		if(isRecordAdded==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(closeEnquiry.getUserId()); 
			 activityLog.setActivityDescription("Enquiry closed of enquiryId "+closeEnquiry.getEnquiryId()+"");
			 activityLog.setProjectTranId(closeEnquiry.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordAdded;
	}

	@Override
	public List<CloseEnquiry> getAllClosedEnquiryByProjectId(int projectId, int followupStatus, int companyId) {
		
		return dao.getAllClosedEnquiryByProjectId(projectId, followupStatus, companyId);
	}
	
	/*@Override
	public List<WingDetails> getPropertyUnitByProjectIdAndPropertyTypeId(int projectId, int propertyTypeId) {
		
		return dao.getPropertyUnitByProjectIdAndPropertyTypeId(projectId, propertyTypeId);
	}
*/
	@Override
	public List<EnquiryDetails> getAllEnquiryAndClientListByProjectId(int projectId) {
		
		return dao.getAllEnquiryAndClientListByProjectId(projectId);
	}

	@Override
	public void genarateClosedEnquiryExcelReport(int projectId, int followupStatus, int companyId, HttpServletResponse response) {
		String excelFileName = null;
		String projectName = null;
		//String datetime = (DateTimeUtil.getSysDate()).replaceAll(" ", "_").replaceAll(":", "_");	
		//CompanyProfile companyDetails = companyDao.getCompanyDetailsByCompanyId(companyId);
		List<CloseEnquiry> closedEnquiryList = getAllClosedEnquiryByProjectId(projectId, followupStatus, companyId);
		if(projectId==0){
			projectName = "ALL Projects";
		}else{
			projectName = closedEnquiryList.get(0).getProjectName();
		}
		excelFileName    = ConstantsUtil.FILE_SAVE_LOCATION +"ClosedEnquiryExcelReport.xls";
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(); 
			HSSFSheet spreadsheet = workbook .createSheet(projectName+" Closed Enquiry List");				    
			HSSFRow row=spreadsheet.createRow(0);
			HSSFCell cell;	
			
			HSSFCellStyle style = workbook.createCellStyle();
			HSSFCellStyle style1 = workbook.createCellStyle();
			style = workbook.createCellStyle();	
			style1 = workbook.createCellStyle();	
			   
			HSSFFont font = workbook.createFont();					   
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setBold(true);
			font.setColor(IndexedColors.BLACK.getIndex());
			style.setFont(font);
			style.setAlignment(HorizontalAlignment.CENTER);
			style1.setAlignment(HorizontalAlignment.RIGHT);
			   
			spreadsheet.setColumnWidth(1, 5000);
			spreadsheet.setColumnWidth(2, 5000);
			spreadsheet.setColumnWidth(3, 5000);
			spreadsheet.setColumnWidth(4, 8000);
			   
			cell=row.createCell(1);			      			    
		    cell.setCellValue("Sr.No.");
		    cell.setCellStyle(style); 
		      
		    cell=row.createCell(2);
		    cell.setCellValue("Name "); 
		    cell.setCellStyle(style); 
		      
		    cell=row.createCell(3);
		    cell.setCellValue("Mobile Number");
		    cell.setCellStyle(style);
		      
		    cell=row.createCell(4);			      
		    cell.setCellValue("Reason"); 
		    cell.setCellStyle(style); 
		    
		    int i=2;
		    int j=0;
		      int noOfColumns = 0;	  
			  Iterator<CloseEnquiry> itr = closedEnquiryList.iterator();
			 
			  while (itr.hasNext()) {	    	
				  CloseEnquiry closeEnquiry = itr.next();
				  
			        String fullname = closeEnquiry.getFullName();
			        String mobileNo = closeEnquiry.getMobileNo();
			        String reason   = closeEnquiry.getReason();
			      
		         row=spreadsheet.createRow(i);
		         
		         cell=row.createCell(1);
		         cell.setCellValue(j=j+1);
		         cell=row.createCell(2);
		         cell.setCellValue(fullname);
		         cell=row.createCell(3);
		         cell.setCellValue(mobileNo);
		         cell=row.createCell(4);
		         cell.setCellValue(reason);
		        
		         i++;
			  }
		         FileOutputStream out = new FileOutputStream( new File(excelFileName));
		         ServletOutputStream outputStream = response.getOutputStream(); 
			      noOfColumns = spreadsheet.getPhysicalNumberOfRows();
			      int lastRow = noOfColumns+1;
			      row=spreadsheet.createRow(lastRow);
			      workbook.write(out);
				  workbook.write(outputStream); 
				  out.flush();
				  out.close();
			      outputStream.flush();
			      outputStream.close();	
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public boolean addRemark(EnquiryDetails enquiryDetails) {
		boolean isRecordAdded = false;
		
		String dateTime = DateTimeUtil.getSysDateTime();	
		
		enquiryDetails.setRemarkDate(dateTime);
		isRecordAdded=dao.addRemark(enquiryDetails);
		
		if(isRecordAdded==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(enquiryDetails.getUserId()); 
			 activityLog.setActivityDescription("Added remark of enquiryId "+enquiryDetails.getEnquiryId());
			 activityLog.setProjectTranId(enquiryDetails.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordAdded;
	}
	
	
	
}
