package com.bcs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="bank_details")
public class BankDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bank_id")
	private int bankId;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="branch_name")
	private String branchName;
	
	@Column(name="account_name")
	private String accountName;
	
	@Column(name="ifsc_code")
	private String iFSCCode;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="account_number")
	private String accountNumber;
	
	@Column(name="attachment")
	private String attachment;
	
	@Column(name="contact_person")
	private String contactPerson;
	
	@Column(name="contact_no")
	private String contactNo;
	
	@Column(name="bank_type")
	private int bankType;	
	
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="user_id")
	private int userId;	
	
	@Column(name="is_remove")
	private int isRemove;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Transient
	public MultipartFile bankAttachment;
	
	public BankDetails(){
		
	}
	public BankDetails(int bankId, String bankName, String branchName, String iFSCCode, String accountType,
			String accountNumber, String attachment, String contactPerson, String contactNo, int bankType,
			int projectId, String createdDatetime, String updatedDatetime, int isRemove, MultipartFile bankAttachment) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.branchName = branchName;
		this.iFSCCode = iFSCCode;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.attachment = attachment;
		this.contactPerson = contactPerson;
		this.contactNo = contactNo;
		this.bankType = bankType;
		this.projectId = projectId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.isRemove = isRemove;
		this.bankAttachment = bankAttachment;
	}

	public int getBankId() {
		return bankId;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		
		if(bankName == null){
			return "";
		}
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchName() {
		
		if(branchName == null){
			return "";
		}
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public String getAccountName() {
		
		if(accountName == null){
			return "";
		}
		return accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public String getiFSCCode() {
		
		if(iFSCCode == null){
			return "";
		}
		return iFSCCode;
	}
	
	public void setiFSCCode(String iFSCCode) {
		this.iFSCCode = iFSCCode;
	}
	
	public String getAccountType() {
		
		if(accountType == null){
			return "";
		}
		return accountType;
	}
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	public String getAccountNumber() {
		
		if(accountNumber == null){
			return "";
		}
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {		
		this.accountNumber = accountNumber;
	}
	
	public String getAttachment() {
		if(attachment == null){
			return "";
		}
		return attachment;
	}
	
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	public String getContactPerson() {
		if(contactPerson == null){
			return "";
		}
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactNo() {
		if(contactNo == null){
			return "";
		}
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public int getBankType() {
		return bankType;
	}

	public void setBankType(int bankType) {
		this.bankType = bankType;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsRemove() {
		return isRemove;
	}
	
	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
	}
	
	public String getCreatedDatetime() {
		
		if(createdDatetime == null){
			return "";
		}
		return createdDatetime;
	}

	public void setCreatedDatetime(String createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdatedDatetime() {
		if(updatedDatetime == null){
			return "";
		}
		return updatedDatetime;
	}

	public void setUpdatedDatetime(String updatedDatetime) {
		this.updatedDatetime = updatedDatetime;
	}

	public MultipartFile getBankAttachment() {
		return bankAttachment;
	}

	public void setBankAttachment(MultipartFile bankAttachment) {
		this.bankAttachment = bankAttachment;
	}

	@Override
	public String toString() {
		return "BankDetails [bankId=" + bankId + ", bankName=" + bankName + ", branchName=" + branchName + ", IFSCCode="
				+ iFSCCode + ", accountType=" + accountType + ", accountNumber=" + accountNumber + ", attachment="
				+ attachment + ", contactPerson=" + contactPerson + ", contactNo=" + contactNo + ", bankType="
				+ bankType + ", projectId=" + projectId + ", createdDatetime=" + createdDatetime + ", updatedDatetime="
				+ updatedDatetime + ", isRemove=" + isRemove + "]";
	}


}
