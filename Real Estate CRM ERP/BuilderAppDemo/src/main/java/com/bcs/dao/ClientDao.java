package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.model.ClientDocuments;
import com.bcs.model.PaidDisbursementDetails;

@Component
public interface ClientDao {
	
	public boolean addClient(Client client);
	
	public List<Client> getAllClientsByProjectId(int projectId);
	
	Client getClientDetailsById(int id);
	
	public Client getBookedFlatDetails(int wingId, int FlatNumber, int floorNumber, String floorName);
	
	String getClientNameByFlatNumberAndWingId(int wingId, int flatNumber, int floorNum, String floorName);

	String getUnbookedPropertyType(int wingId, int flatNumber, int floorNum, String floorName);

	int getRecentClientTranId();
	
	public boolean updateClientDetailsByClientTranId(Client client);
	
	public boolean cancelPropertyBooking(Client client);
	
	public List<PaidDisbursementDetails> getClientsDisbursementRemaingAmountByProjectId(int projectId);
	
	public PaidDisbursementDetails getAllDisbursmentDetailsByClient(int clientId);

	public boolean addClientDocuments(ClientDocuments document);

	public List<ClientDocuments> getClientDocumentsListByClientId(int clientId);

	public String getDocumentNameByDocumentId(int documentId);

	public boolean deleteClientDocumentById(int documentId);
	
}
