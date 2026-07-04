package com.bcs.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bcs.model.Client;
import com.bcs.model.Disbursement;
import com.bcs.model.PaidDisbursementDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.ProjectDisbursement;

@Component
public interface DisbursementService {

	public List<ProjectDisbursement> getProjectDisbursementListByProjectId(int projectId);
	
	public void updateProjectDisbursementDetails(ProjectDetails project);
	
	void payDisbursementAmountByDisbursementId(Disbursement disbursement) throws ParseException;
	
	public void updateCompletionDateByProjectDisbursementId(ProjectDisbursement projectDisbursement) throws ParseException;
	
	public String createDemandLetterPdf(int clientId, int disbursementId);
	
	public List<Client> getPaymentDueDateNotifications(int userId, int suserType, int paymentNotifDuration, int companyId, int projectId);
	
	void updatePdfSendDate(Disbursement disbursement);
	
	public List<Client> getPendingDemandLetterNotifications(int userId, int userType, int companyId, int projectId);
	
	public List<Disbursement> getDisbursementDetailsListByClientId(int clientTranId);
	
	public List<Client> getTodaysReceivedPaymentClientsList(int userId, int userType, int companyId, int projectId);
	
	public List<PaidDisbursementDetails> getAllPaidDisbursementDetailsByClientId(int clientId);
	
	public Client getPropertyDetails(int clientTranId);
	
}
