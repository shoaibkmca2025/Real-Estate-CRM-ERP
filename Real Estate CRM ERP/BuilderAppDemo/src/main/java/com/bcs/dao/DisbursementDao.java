package com.bcs.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.model.ClientDemandLetterDetails;
import com.bcs.model.Disbursement;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.ProjectDisbursement;

@Component
public interface DisbursementDao {

	public List<ProjectDisbursement> getProjectDisbursementListByProjectId(int projectId);
	
	public boolean updateProjectDisbursementDetails(ProjectDetails project);

	boolean payDisbursementAmountByDisbursementId(Disbursement disbursement);
	
	public boolean updateCompletionDateByProjectDisbursementId(ProjectDisbursement projectDisbursement);
		
	public List<Client> getPaymentDueDateNotifications(int userId, int userType, int paymentNotifDuration, int companyId, int projectId);
	
	public ClientDemandLetterDetails getClientDisbursementDetailsByClientId(int clientId, int disbursementId);
	
	public boolean updatePdfSendDate(Disbursement disbursement);
	
	public List<Client> getPendingDemandLetterNotifications(int userId, int userType, int companyId, int projectId);
	
	public List<Disbursement> getDisbursementDetailsListByClientId(int clientId);
	
	public void updateDisbursementFollowupDate(Disbursement disbursement);
	
	public Disbursement getDisbursementFirstFollowupDetails(int clientId);
	
	public List<Client> getTodaysReceivedPaymentClientsList(int userId, int userType, int companyId, int projectId);
	
	public List<PaidDisbursementDetails> getAllPaidDisbursementDetailsByClientId(int clientId);
	
	public Client getPropertyDetails(int clientTranId);
	
}
