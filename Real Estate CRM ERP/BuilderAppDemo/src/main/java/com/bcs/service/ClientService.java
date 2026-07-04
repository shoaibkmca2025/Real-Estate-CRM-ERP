package com.bcs.service;

import java.io.FileNotFoundException;
import java.util.List;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.model.ClientDocuments;
import com.bcs.model.PaidDisbursementDetails;

@Component
public interface ClientService {

	public boolean addClient(Client client);
	
	public List<Client> getAllClientsByProjectId(int projectId);
	
	public Client getClientDetailsById(int id);
	
	public Client getBookedFlatDetails(int wingId, int FlatNumber, int floorNumber, String floorName);
		
	public String getClientNameByFlatNumberAndWingId(int wingId, int flatNumber, int floorNum, String floorName);
	
	public String getUnbookedPropertyType(int wingId, int flatNumber, int floorNum, String floorName);
	
	public boolean updateClientDetailsByClientTranId(Client client);
	
	public boolean cancelPropertyBooking(Client client);
	
	public List<PaidDisbursementDetails> getClientsDisbursementRemaingAmountByProjectId(int projectId);
	
	public PaidDisbursementDetails getAllDisbursmentDetailsByClient(int clientId);

	public boolean addClientDocuments(ClientDocuments documents);

	public List<ClientDocuments> getClientDocumentsListByClientId(int clientId);

	public void downloadClientDocument(String fileName, HttpServletResponse response) throws FileNotFoundException;

	public String getDocumentNameByDocumentId(int documentId);

	public boolean deleteClientDocumentById(int documentId, int userId, int projectId);
	
}
