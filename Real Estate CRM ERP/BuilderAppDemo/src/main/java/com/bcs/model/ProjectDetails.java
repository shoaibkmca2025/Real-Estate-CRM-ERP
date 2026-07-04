package com.bcs.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="project_details")
public class ProjectDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="address")
	private String address;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="expected_completion_date")
	private String expectedCompletionDate;
	
	@Column(name="no_of_wings")
	private int noOfWings;
	
	@Column(name="maharera_no")
	private String mahareraNo;

	@Column(name="project_tran_id")
	private int projectTranId;
	
	@Column(name="created_datetime")
	private String createdDatetime;
	
	@Column(name="updated_datetime")
	private String updatedDatetime;
	
	@Column(name="is_remove")
	private int isRemove;	
	
	@Column(name="project_status")
	private int projectStatus;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="subuser_id")
	private String subUserId;
	
	@Column(name="company_id")
	private int companyId;
	
	@Column(name="is_approved")
	private int isApproved;	
	
	@Column(name="letter_head")
	private String letterHead;	
	
	@Transient
	private String projectStatusName;
	
	@Transient
	private List<String> documents;
	
	@Transient
	private List<Wing> wingList;
	
	@Transient
	private List<BankDetails> bankDetailsList;
	
	@Transient
	private List<Amenities> amenitiesList;		
	
	@Transient
	private List<Documents> documentsList;
	
	@Transient
	private List<ProjectDisbursement> disbursementList;

	@Transient
	private String userName;
	
	@Transient
	private String email;
	
	@Transient
	private int userType;	
	
	@Transient
	private String companyName;
	
	@Transient
	private Date startDate1;
	
	@Transient
	private Date expectedCompletionDate1;
	
	@Transient
	private MultipartFile letterHeadFile;
	
    public ProjectDetails(){
		
	}

	public ProjectDetails(int projectId, String projectName, String address, String startDate,
			String expectedCompletionDate, int noOfWings, String mahareraNo, int projectTranId, String createdDatetime,
			String updatedDatetime, int isRemove, int projectStatus, int userId, String subUserId, int companyId,
			int isApproved, String projectStatusName, List<String> documents, List<Wing> wingList,
			List<BankDetails> bankDetailsList, List<Amenities> amenitiesList, List<Documents> documentsList,
			List<ProjectDisbursement> disbursementList, String userName, String email, int userType) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.address = address;
		this.startDate = startDate;
		this.expectedCompletionDate = expectedCompletionDate;
		this.noOfWings = noOfWings;
		this.mahareraNo = mahareraNo;
		this.projectTranId = projectTranId;
		this.createdDatetime = createdDatetime;
		this.updatedDatetime = updatedDatetime;
		this.isRemove = isRemove;
		this.projectStatus = projectStatus;
		this.userId = userId;
		this.subUserId = subUserId;
		this.companyId = companyId;
		this.isApproved = isApproved;
		this.projectStatusName = projectStatusName;
		this.documents = documents;
		this.wingList = wingList;
		this.bankDetailsList = bankDetailsList;
		this.amenitiesList = amenitiesList;
		this.documentsList = documentsList;
		this.disbursementList = disbursementList;
		this.userName = userName;
		this.email = email;
		this.userType = userType;
	}



	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		if(projectName == null){
			return "";
		}
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getAddress() {
		if(address == null){
			return "";
		}
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStartDate() {
		if(startDate == null){
			return "";
		}
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getExpectedCompletionDate() {
		if(expectedCompletionDate == null){
			return "";
		}
		return expectedCompletionDate;
	}

	public void setExpectedCompletionDate(String expectedCompletionDate) {
		this.expectedCompletionDate = expectedCompletionDate;
	}

	public int getNoOfWings() {
		return noOfWings;
	}

	public void setNoOfWings(int noOfWings) {
		this.noOfWings = noOfWings;
	}

	public String getMahareraNo() {
		if(mahareraNo == null){
			return "";
		}
		return mahareraNo;
	}

	public void setMahareraNo(String mahareraNo) {
		this.mahareraNo = mahareraNo;
	}
	
	public int getProjectTranId() {
		return projectTranId;
	}

	public void setProjectTranId(int projectTranId) {
		this.projectTranId = projectTranId;
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
	
	public int getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(int isRemove) {
		this.isRemove = isRemove;
	}

	public int getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}

	public String getProjectStatusName() {
		if(projectStatusName == null){
			return "";
		}
		return projectStatusName;
	}

	public void setProjectStatusName(String projectStatusName) {
		this.projectStatusName = projectStatusName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSubUserId() {
		if(subUserId == null){
			return "";
		}
		return subUserId;
	}

	public void setSubUserId(String subUserId) {
		this.subUserId = subUserId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public String getLetterHead() {
		if(letterHead == null){
			return "";
		}
		return letterHead;
	}

	public void setLetterHead(String letterHead) {
		this.letterHead = letterHead;
	}

	public List<String> getDocuments() {
		return documents;
	}

	public void setDocuments(List<String> documents) {
		this.documents = documents;
	}
		
	public List<Wing> getWingList() {
		return wingList;
	}

	public void setWingList(List<Wing> wingList) {
		this.wingList = wingList;
	}

	public List<BankDetails> getBankDetailsList() {
		return bankDetailsList;
	}

	public void setBankDetailsList(List<BankDetails> bankDetailsList) {
		this.bankDetailsList = bankDetailsList;
	}

	public List<Amenities> getAmenitiesList() {
		return amenitiesList;
	}

	public void setAmenitiesList(List<Amenities> amenitiesList) {
		this.amenitiesList = amenitiesList;
	}
	
	public List<Documents> getDocumentsList() {
		return documentsList;
	}

	public void setDocumentsList(List<Documents> documentsList) {
		this.documentsList = documentsList;
	}
	
	public List<ProjectDisbursement> getDisbursementList() {
		return disbursementList;
	}

	public void setDisbursementList(List<ProjectDisbursement> disbursementList) {
		this.disbursementList = disbursementList;
	}

	public String getUserName() {
		if(userName == null){
			return "";
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		if(email == null){
			return "";
		}
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}


	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getStartDate1() {
		return startDate1;
	}

	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}

	public Date getExpectedCompletionDate1() {
		return expectedCompletionDate1;
	}

	public void setExpectedCompletionDate1(Date expectedCompletionDate1) {
		this.expectedCompletionDate1 = expectedCompletionDate1;
	}

	public MultipartFile getLetterHeadFile() {
		return letterHeadFile;
	}

	public void setLetterHeadFile(MultipartFile letterHeadFile) {
		this.letterHeadFile = letterHeadFile;
	}

	@Override
	public String toString() {
		return "ProjectDetails [projectId=" + projectId + ", projectName=" + projectName + ", address=" + address
				+ ", startDate=" + startDate + ", expectedCompletionDate=" + expectedCompletionDate + ", noOfWings="
				+ noOfWings + ", mahareraNo=" + mahareraNo + ", projectTranId=" + projectTranId + ", createdDatetime="
				+ createdDatetime + ", updatedDatetime=" + updatedDatetime + ", isRemove=" + isRemove+ ", companyId=" + companyId+ ", isApproved=" + isApproved
				+ ", projectStatus=" + projectStatus + ", projectStatusName=" + projectStatusName + ", documents=" + documents + "]";
	}


}
