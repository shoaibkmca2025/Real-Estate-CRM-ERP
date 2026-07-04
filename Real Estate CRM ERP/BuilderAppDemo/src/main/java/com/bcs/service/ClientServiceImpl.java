package com.bcs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcs.dao.ActivityDao;
import com.bcs.dao.ClientDao;
import com.bcs.model.ActivityLog;
import com.bcs.dao.DisbursementDao;
import com.bcs.dao.OtherPaymentsDao;

import com.bcs.model.Client;
import com.bcs.model.ClientDocuments;
import com.bcs.model.Documents;
import com.bcs.model.OtherPayments;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientDao clientDao;

	@Autowired  
	private ActivityDao activityDao;

	@Autowired
	OtherPaymentsDao otherPaymentDetailsDao;
	
	@Autowired
	DisbursementDao disbursementDao;
	
	@Override
	public boolean addClient(Client client) {
		
		boolean isRecordAdded = false;
		
	/*	try {
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		
			date = sdf.parse(client.getDateOfBirth());
			client.setDateOfBirth(output.format(date));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		client.setDateOfBirth(output.format(client.getBirthDate()));
		
		/*if(client.getRegDate() != null){			
			client.setRegistrationDate(output.format(client.getRegDate()));			
		}*/
		
		String dateTime = DateTimeUtil.getSysDateTime();
		client.setClientTranId(clientDao.getRecentClientTranId());
		client.setBookingDate(output.format(client.getBookingDate1()));			
		client.setCreatedDatetime(dateTime);
		client.setUpdatedDatetime(dateTime);

		isRecordAdded = clientDao.addClient(client);
		
		if(isRecordAdded==true){
			
			 OtherPayments otherPayments = new OtherPayments();
			 
			 otherPayments.setTitle("Booking Amount");
			 otherPayments.setAmount(client.getBookingAmount());
			 otherPayments.setPaidDate(output.format(client.getBookingDate1()));
			 otherPayments.setChequeNumber(client.getChequeNo());
			 
			 if(client.getChequeDate() != null){
				 otherPayments.setChequeDate(output.format(client.getChequeDate()));
			 }
			 otherPayments.setClientId(client.getClientTranId());
			 otherPayments.setUserId(client.getUserId());
			 otherPayments.setCreatedDatetime(dateTime);
			 otherPayments.setUpdatedDatetime(dateTime);
			 
			 otherPaymentDetailsDao.addOtherPayments(otherPayments);
			
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(client.getUserId()); 
			 activityLog.setActivityDescription("Client Added of clientId "+client.getClientTranId());
			 activityLog.setProjectTranId(client.getProjectId());
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordAdded;		
	}
	
	@Override
	public List<Client> getAllClientsByProjectId(int projectId) {
		
		return clientDao.getAllClientsByProjectId(projectId);
	}
	
	@Override
	public Client getClientDetailsById(int id) {
		
		Client clientInfo = clientDao.getClientDetailsById(id); 
		clientInfo.setDisbursementList(disbursementDao.getDisbursementDetailsListByClientId(id));
		
		clientInfo.setOtherPaymentList(otherPaymentDetailsDao.getOtherPaymentsListByClientId(id));
		
		return clientInfo;
	}

	@Override
	public Client getBookedFlatDetails(int wingId, int FlatNumber, int floorNumber, String floorName) {
		
		Client bookedPropertyDetails = clientDao.getBookedFlatDetails(wingId, FlatNumber, floorNumber,floorName);
		
		int clientId = bookedPropertyDetails.getClientTranId();
		bookedPropertyDetails.setDisbursementList(disbursementDao.getDisbursementDetailsListByClientId(clientId));
		
		bookedPropertyDetails.setOtherPaymentList(otherPaymentDetailsDao.getOtherPaymentsListByClientId(clientId));
		
		return bookedPropertyDetails;
	}

	@Override
	public String getClientNameByFlatNumberAndWingId(int wingId, int flatNumber, int floorNum, String floorName) {
	
		return clientDao.getClientNameByFlatNumberAndWingId(wingId, flatNumber,floorNum, floorName);
	}

	@Override
	public String getUnbookedPropertyType(int wingId, int flatNumber,int floorNum, String floorName) {
		
		return clientDao.getUnbookedPropertyType(wingId, flatNumber, floorNum, floorName);
	}

	@Override
	public boolean updateClientDetailsByClientTranId(Client client) {
		
		boolean isRecordUpdated = false;
		/*try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
			SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
			
			Date date = sdf.parse(client.getDateOfBirth());
				client.setDateOfBirth(output.format(date));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		SimpleDateFormat output = new SimpleDateFormat("dd/MM/yyyy");
		client.setDateOfBirth(output.format(client.getBirthDate()));
		if(client.getRegDate() != null){
			client.setRegistrationDate(output.format(client.getRegDate()));
		}
		isRecordUpdated = clientDao.updateClientDetailsByClientTranId(client);
		String datetime = DateTimeUtil.getSysDateTime();
		
		if(isRecordUpdated==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(client.getUserId()); 
			 activityLog.setActivityDescription("Client Details updated of Client Id "+client.getClientTranId());
			 activityLog.setProjectTranId(client.getProjectId());
			 activityLog.setActivityDatetime(datetime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordUpdated;
	}

	@Override
	public boolean cancelPropertyBooking(Client client) {
		
		boolean isRecordUpdated = false;
		String datetime = DateTimeUtil.getSysDateTime();
		
		isRecordUpdated = clientDao.cancelPropertyBooking(client);
		if(isRecordUpdated){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(client.getUserId()); 
			 activityLog.setActivityDescription("Client Details updated of Client Id "+client.getClientTranId());
			 activityLog.setProjectTranId(client.getProjectId());
			 activityLog.setActivityDatetime(datetime);
			 activityDao.addActivityDetails(activityLog);
		}
		
		return isRecordUpdated;
	}

	@Override
	public List<PaidDisbursementDetails> getClientsDisbursementRemaingAmountByProjectId(int projectId) {
		// TODO Auto-generated method stub
		return clientDao.getClientsDisbursementRemaingAmountByProjectId(projectId);
	}

	@Override
	public PaidDisbursementDetails getAllDisbursmentDetailsByClient(int clientId) {
		// TODO Auto-generated method stub
		return clientDao.getAllDisbursmentDetailsByClient(clientId);
	}
	
	@Override
	public boolean addClientDocuments(ClientDocuments document) {
		
		  String dateTime = DateTimeUtil.getSysDateTime();
		  boolean isRecordAdded = false;
		  document.setUpdatedDatetime(dateTime);
		  document.setCreatedDatetime(dateTime);
				
		  isRecordAdded = clientDao.addClientDocuments(document);
		  
		  if(isRecordAdded==true){
				 ActivityLog activityLog = new ActivityLog();
				 
				 activityLog.setUserId(document.getUserId()); 
				 activityLog.setActivityDescription("Client Document Id: "+document.getDocumentId()+" Uploaded");
				 activityLog.setProjectTranId(document.getProjectId());
				 activityLog.setActivityDatetime(dateTime);
				 activityDao.addActivityDetails(activityLog);
			}
		return isRecordAdded;
	}
	
	@Override
	public List<ClientDocuments> getClientDocumentsListByClientId(int clientId) {
		
		return clientDao.getClientDocumentsListByClientId(clientId);
	}
	
	@Override
	public void downloadClientDocument(String documentName, HttpServletResponse response) throws FileNotFoundException {
		
		try{
			
			String fullPath         =  ConstantsUtil.FILE_SAVE_LOCATION+documentName;
			File downloadFile           = new File(fullPath);		
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
		    ServletOutputStream out = response.getOutputStream();
		   
	        byte[] buffer = new byte[4096];
	        int bytesRead = -1;
	 
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	        	out.write(buffer, 0, bytesRead);
	        }
			
			out.flush();	 
	        inputStream.close();
	       
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@Override
	public String getDocumentNameByDocumentId(int documentId) {
		
		return clientDao.getDocumentNameByDocumentId(documentId);
	}
	
	@Override
	public boolean deleteClientDocumentById(int documentId, int userId, int projectId) {
		boolean isRecordDeleted = false;
		String dateTime = DateTimeUtil.getSysDateTime();
		
		isRecordDeleted = clientDao.deleteClientDocumentById(documentId);
		
		String documentName    =  clientDao.getDocumentNameByDocumentId(documentId);
		File file              =  new File(ConstantsUtil.FILE_SAVE_LOCATION+documentName);		
		file.delete();
		
		if(isRecordDeleted==true){
			 ActivityLog activityLog = new ActivityLog();
			 
			 activityLog.setUserId(userId); 
			 activityLog.setActivityDescription("Client Document Id "+documentId+" Deleted");
			 activityLog.setProjectTranId(projectId);
			 activityLog.setActivityDatetime(dateTime);
			 activityDao.addActivityDetails(activityLog);
		}
		return isRecordDeleted;
	}

}
