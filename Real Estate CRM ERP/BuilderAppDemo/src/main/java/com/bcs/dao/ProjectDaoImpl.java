package com.bcs.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.bcs.model.Amenities;
import com.bcs.model.BankDetails;
import com.bcs.model.Client;
import com.bcs.model.CountDetails;
import com.bcs.model.DocumentType;
import com.bcs.model.Documents;
import com.bcs.model.FloorDetails;
import com.bcs.model.ProjectDetails;
import com.bcs.model.ProjectDisbursement;
import com.bcs.model.Wing;
import com.bcs.model.WingDetails;
import com.bcs.utility.ConstantsUtil;
import com.bcs.utility.DateTimeUtil;

@Repository
public class ProjectDaoImpl extends JdbcDaoSupport implements ProjectDao {

	 private SessionFactory sessionFactory;
	@org.springframework.beans.factory.annotation.Autowired
	private jakarta.persistence.EntityManagerFactory _entityManagerFactory;
	@jakarta.annotation.PostConstruct
	private void _initSessionFactory() { this.sessionFactory = _entityManagerFactory.unwrap(SessionFactory.class); } 	
	 
	 @Autowired
     public void setDs(DataSource dataSource) {
         setDataSource(dataSource);
     }
	 
	 @Override
		public boolean addAllDetails(ProjectDetails project , int projectTranId, int wingTranId) {
		
		 Session session = null;  
		 Transaction tx = null;	
		 int recordCount = 0;
		 boolean isRecordAdded = false;
			try{
				
				session = sessionFactory.openSession();  
				tx = session.beginTransaction();  
				
				int userId = project.getUserId();
				String fileName = null;
				String documentName = null;
				String extension = null;
				
				if(project.getLetterHeadFile() != null){
					
				    MultipartFile file = project.getLetterHeadFile();	
				    
				    if (file.getOriginalFilename().indexOf(".") >= 0) {
				    	documentName = file.getOriginalFilename().substring(0, (file.getOriginalFilename()).lastIndexOf("."));
				    	 extension = file.getOriginalFilename().substring((file.getOriginalFilename()).lastIndexOf("."),(file.getOriginalFilename()).length());
					} 
					String originalFilename = (documentName.replaceAll("[^a-zA-Z0-9_]", "_")).replaceAll("_+", "_")+extension;
				    
					if(originalFilename != null && originalFilename != ""){
						
						Date date     = new Date();								 	 
						fileName  = date.getTime()+"_"+originalFilename;
					 	
					 	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
			                        new File(ConstantsUtil.FILE_SAVE_LOCATION, fileName)));
	
					 	outputStream.write(file.getBytes());
					 	outputStream.flush();
					 	outputStream.close();
					 	project.setLetterHead(fileName);
					}
				}
						
				
				List<Wing> wings =  project.getWingList();
				project.setProjectTranId(projectTranId);
				project.setNoOfWings(wings.size());
				session.save(project);
							
				for(int i=0; i < wings.size(); i++){
					
					Wing  wing = wings.get(i);
					
					wing.setCreatedDatetime(project.getCreatedDatetime());
					wing.setUpdatedDatetime(project.getUpdatedDatetime());
					wing.setProjectId(projectTranId);
					wing.setWingTranId(wingTranId);
					wing.setUserId(userId);
					session.save(wing);
					
					recordCount++;
					
					List<FloorDetails> floorList =  wing.getFloorList();
					
					for(int k=0; k < floorList.size(); k++){
						
						FloorDetails  floorDetails = floorList.get(k);
						
						floorDetails.setWingId(wingTranId);
						floorDetails.setCreatedDatetime(project.getCreatedDatetime());
						floorDetails.setUpdatedDatetime(project.getUpdatedDatetime());
						floorDetails.setUserId(userId);
						session.save(floorDetails);
						
						recordCount++;
						if(recordCount % 20 == 0 ){
							
							session.flush();
							session.clear();
						}
					}
					
					
					List<WingDetails> wingdetailsList =  wing.getWingdetailsList();
					
					for(int j=0; j < wingdetailsList.size(); j++){
						
						
							WingDetails  wingdetails = wingdetailsList.get(j);
							
							wingdetails.setWingId(wingTranId);
							wingdetails.setCreatedDatetime(project.getCreatedDatetime());
							wingdetails.setUpdatedDatetime(project.getUpdatedDatetime());
							wingdetails.setUserId(userId);
							session.save(wingdetails);
							
							recordCount++;
							
							recordCount += wingdetails.getNoOfFlats();
							
							if(recordCount % 20 == 0 ){
								
								//System.out.println("wing details recordCount: "+recordCount);
								session.flush();
								session.clear();
							}
					}
					
					wingTranId = wingTranId + 1;				
				}
				
				List<BankDetails> banks =  project.getBankDetailsList();				
				
				for(int i=0; i < banks.size(); i++){
					
					BankDetails bank   = banks.get(i);
				
					bank.setCreatedDatetime(project.getCreatedDatetime());
					bank.setUpdatedDatetime(project.getUpdatedDatetime());
					bank.setProjectId(projectTranId);
					bank.setUserId(userId);
					session.save(bank);
					
					recordCount++;
					
					if(recordCount % 20 == 0 ){
						
						//System.out.println("bank details recordCount: "+recordCount);
						session.flush();
						session.clear();
					}
				}				
				List<Amenities> amenities =  project.getAmenitiesList();
				
				for(int i=0; i < amenities.size(); i++){
					
					Amenities amenity  = amenities.get(i);
					
					amenity.setCreatedDatetime(project.getCreatedDatetime());
					amenity.setUpdatedDatetime(project.getUpdatedDatetime());
					amenity.setProjectId(projectTranId);
					amenity.setUserId(userId);
					session.save(amenity);
					
					recordCount++;
					
					if(recordCount % 20 == 0 ){
						
						//System.out.println("amenity details recordCount: "+recordCount);
						session.flush();
						session.clear();
					}
				}
				
				List<Documents> documents =  project.getDocumentsList();
				
				for(int i=0; i < documents.size(); i++){
					
					Documents document   = documents.get(i);
					
					int docTypeId = document.getDocumentTypeId();
					
					if(docTypeId == -1){
						
						List<Integer> docTypeIds = session.createQuery(
								"select d.documentTypeId from DocumentType d where d.documentType = :documentType", Integer.class)
								.setParameter("documentType", document.getDocumentType())
								.list();

						if(docTypeIds.isEmpty()){

							DocumentType docType  = new DocumentType();
							docType.setDocumentType(document.documentType);
							session.save(docType);

							docTypeId = docType.getDocumentTypeId();
				        }else{
				        	docTypeId = docTypeIds.get(0);
				        }
						document.setDocumentTypeId(docTypeId);						
					}
							
					document.setCreatedDatetime(project.getCreatedDatetime());
					document.setUpdatedDatetime(project.getUpdatedDatetime());
					document.setProjectId(projectTranId);
					document.setUserId(userId);
					session.save(document);
					
					recordCount++;
					
					if(recordCount % 20 == 0 ){
						
						//System.out.println("document details recordCount: "+recordCount);
						session.flush();
						session.clear();
					}
				}
				
				List<ProjectDisbursement> disbursementList =  project.getDisbursementList();
				
				for(int i=0; i < disbursementList.size(); i++){
					
					ProjectDisbursement disbursement  = disbursementList.get(i);
					
					disbursement.setCreatedDatetime(project.getCreatedDatetime());
					disbursement.setUpdatedDatetime(project.getUpdatedDatetime());
					disbursement.setProjectId(projectTranId);
					disbursement.setUserId(userId);
					session.save(disbursement);
					
					recordCount++;
					
					if(recordCount % 20 == 0 ){
						
						//System.out.println("disbursement recordCount: "+recordCount);
						session.flush();
						session.clear();
					}
				}
			 tx.commit();
			 isRecordAdded = true;
			}catch(Exception ex){
				
				ex.printStackTrace();
				 if(tx!= null){
					  tx.rollback();
			    }	
			}finally{
				
				session.close();
			}
			return isRecordAdded;		
		}
	
	@Override
	public int getRecentProjectId(){
		
		Session session = null;  
		Transaction tx = null;	
		int projectId = 0;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction(); 
			
			  List<Integer> maxIdList = session.createQuery(
					  "select p.projectId from ProjectDetails p order by p.projectId desc", Integer.class)
					  .setMaxResults(1)
					  .list();

		     if(maxIdList.isEmpty()){

		    	 projectId = 1;

		     }else{
		    	 projectId = maxIdList.get(0);
		    	 projectId = projectId+1;
		     }
		     
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return projectId;
	}
	
	@Override
	public int getRecentWingId(){
		
		Session session = null;  
		Transaction tx = null;	
		int wingId = 0;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction(); 
			
			  List<Integer> maxIdList = session.createQuery(
					  "select w.wingId from Wing w order by w.wingId desc", Integer.class)
					  .setMaxResults(1)
					  .list();

		     if(maxIdList.isEmpty()){

		    	 wingId = 1;

		     }else{
		    	 wingId = maxIdList.get(0);
		    	 wingId = wingId+1;
		     }
		     
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return wingId;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getAllProjectDetails(int userType, int Id, int projectStatus) {
		
		Session session = null;  
		Transaction tx  = null;
		List<ProjectDetails> projectList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		/*resultQuery =   " select projectId as projectId, projectTranId as projectTranId, projectName as projectName, projectStatus as projectStatus, "
					  + " startDate as startDate, expectedCompletionDate as expectedCompletionDate, subUserId as subUserId "
					  + " from ProjectDetails where isRemove = 0 and companyId = :companyId "
					  + " order by projectStatus asc";	*/	
		
		resultQuery = " select pd.project_id as projectId, pd.project_tran_id as projectTranId, pd.project_name as projectName, "
					+ " pd.project_status as projectStatus, pd.start_date as startDate, pd.expected_completion_date as expectedCompletionDate, "
				    + " pd.subuser_id as subUserId, pd.is_approved as isApproved,pd.company_id as companyId from project_details as pd "
				    + " where pd.is_remove = 0 and case when :userType = 2 or :userType = 1 Then pd.company_id = :Id Else FIND_IN_SET(:Id, pd.subuser_id ) End "
				    + " and case when :projectStatus != 0 Then pd.is_approved = 1 and pd.project_status = :projectStatus Else true End "
				    + " order by pd.project_status asc ";		

		Query query = session.createNativeQuery(resultQuery).setParameter("Id", Id).setParameter("userType", userType).setParameter("projectStatus", projectStatus);
		projectList = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();  
		
		tx.commit();
		session.close();
		return projectList;
	}
	

	@Override
	public ProjectDetails getProjectDetailsById(int projectId) {
		
		Session session        = null;  
		Transaction tx         = null;
		ProjectDetails project = null;
		try{
			
			session = sessionFactory.openSession();
			tx      = session.beginTransaction();		
			
			
				project = session.createQuery(
						"from ProjectDetails p where p.projectTranId = :projectId", ProjectDetails.class)
						.setParameter("projectId", projectId)
						.uniqueResult();
				         
			tx.commit();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			
			session.close();
		}
		return project;
	}

	@Override
	public boolean updateProjectBasicDetails(ProjectDetails project) {
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
			String previousLetterHead = project.getLetterHead(); 
			
			String fileName 	= null;
			String documentName = null;
			String extension 	= null;
			
				if(project.getLetterHeadFile() != null){
					
				    MultipartFile file = project.getLetterHeadFile();	
				    
				    if (file.getOriginalFilename().indexOf(".") >= 0) {
				    	documentName = file.getOriginalFilename().substring(0, (file.getOriginalFilename()).lastIndexOf("."));
				    	 extension = file.getOriginalFilename().substring((file.getOriginalFilename()).lastIndexOf("."),(file.getOriginalFilename()).length());
					} 
					String originalFilename = (documentName.replaceAll("[^a-zA-Z0-9_]", "_")).replaceAll("_+", "_")+extension;
				   
					if(originalFilename != null && originalFilename != ""){
						
						Date date     = new Date();								 	 
						fileName  = date.getTime()+"_"+originalFilename;
					 	
					 	BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(
			                        new File(ConstantsUtil.FILE_SAVE_LOCATION, fileName)));
	
					 	outputStream.write(file.getBytes());
					 	outputStream.flush();
					 	outputStream.close();
					 	project.setLetterHead(fileName);
					 	
					 	if(previousLetterHead != null && previousLetterHead != ""){
					 		
					 		File file1          =  new File(ConstantsUtil.FILE_SAVE_LOCATION+previousLetterHead);		
							file1.delete();
					 	}
					}
				}else if(previousLetterHead != null && previousLetterHead != ""){
					project.setLetterHead(project.getLetterHead());
				}
			
				String hqlUpdate =  "update ProjectDetails set projectName = :projectName, Address = :address, startDate = :startDate,"+ 
									"expectedCompletionDate = :CompletionDate, mahareraNo = :mahareraNo, updatedDatetime = :updatedDatetime, "+
									"projectStatus = :projectStatus, letterHead = :letterHead where projectTranId = :projectTranId";
				
				 session.createQuery(hqlUpdate)
						.setParameter("projectName", project.getProjectName())
						.setParameter("address", project.getAddress())
						.setParameter("startDate", project.getStartDate())	
						.setParameter("CompletionDate", project.getExpectedCompletionDate())	
						.setParameter("mahareraNo", project.getMahareraNo())
						.setParameter("updatedDatetime", project.getUpdatedDatetime())
						.setParameter("letterHead", project.getLetterHead())
						.setParameter("projectStatus", project.getProjectStatus())
						.setParameter("projectTranId", project.getProjectTranId())
						.executeUpdate();
			
			tx.commit();
			isRecordUpdated = true;
		}catch(Exception e){	        
	          e.printStackTrace();
		}finally {
		      session.close();
		}
		return isRecordUpdated;
		
	}

	/*@Override
	public void deleteProjectdetails(int projectId) {
		 Session session = null;  
		 Transaction tx  = null;

		  session = sessionFactory.openSession();  
		  Object o = session.load(ProjectDetails.class, projectId);  
		  tx = session.getTransaction();  
		  session.beginTransaction();  
		  session.delete(o);  
		  tx.commit();		 
	      session.close();	    
		
		 return null;	
		
	}*/

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getAllProjectsByStatus(int projectStatus, int userType, int Id) {
	
		 Session session = null;  
		 Transaction tx = null;
		 List<ProjectDetails> projectLists = null;
	     try{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();	
			
				String resultQuery = " select pd.project_id as projectId, pd.project_tran_id as projectTranId, pd.project_name as projectName, "
									+" pd.project_status as projectStatus, pd.start_date as startDate, pd.expected_completion_date as expectedCompletionDate, "
									+" pd.subuser_id as subUserId,pd.is_approved as isApproved from project_details as pd where pd.is_remove = 0 and pd.project_status =:projectStatus "
									+" and case when :userType = 2 Then pd.company_id = :Id Else FIND_IN_SET(:Id, pd.subuser_id ) End "
									+" order by pd.project_tran_id desc";		
						 	 
				Query query = session.createNativeQuery(resultQuery)		
							 		 .setParameter("projectStatus", projectStatus)
							 		 .setParameter("Id", Id)
							 		 .setParameter("userType", userType); 
				
				projectLists = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();   
				tx.commit();
				
	     }catch(Exception e){	        
	         e.printStackTrace();
	    }finally {
	    if(session.isOpen())
	    session.close();
    }			
   return projectLists;	
 }
	@Override
	public boolean deleteProjectDetails(int projectId){
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordDeleted = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdateProject = "update ProjectDetails set isRemove = 1 where projectTranId = :projectTranId";									
				session.createQuery(hqlUpdateProject).setParameter("projectTranId", projectId)
					.executeUpdate();
				
				String hqlUpdateWing = "update Wing set isRemove = 1 where projectId = :projectId";				
				session.createQuery(hqlUpdateWing).setParameter("projectId", projectId)
					   .executeUpdate();
			
				String hqlUpdateWingdetails =	"update wing_details wd, wing w, project_details p set wd.is_remove = 1 "+ 
												"where wd.wing_id = w.wing_tran_id and p.project_tran_id = w.project_id "+
												"and p.project_tran_id ="+projectId;
				
				session.createNativeQuery(hqlUpdateWingdetails).executeUpdate();
				
				String hqlUpdateBank = "update BankDetails set isRemove = 1 where projectId = :projectId";				
				session.createQuery(hqlUpdateBank).setParameter("projectId", projectId)
					   .executeUpdate();
				
				String hqlUpdateAmenity = "update Amenities set isRemove = 1 where projectId = :projectId";				
				session.createQuery(hqlUpdateAmenity).setParameter("projectId", projectId)
					   .executeUpdate();
				
				String hqlUpdateDoc = "update Documents set isRemove = 1 where projectId = :projectId";				
				session.createQuery(hqlUpdateDoc).setParameter("projectId", projectId)
					   .executeUpdate();
				
				tx.commit();
				isRecordDeleted = true;
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		    }	
		}finally {
		      session.close();
		}
		return isRecordDeleted;
		
	}

	@Override
	public boolean updateProjectStatus(int projectId, int projectStatus, int userId){
		
		Session session = null;  
		Transaction tx = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdateProject = "update ProjectDetails set projectStatus = :projectStatus,  updatedDatetime = :updatedDatetime "+
										  "where projectTranId = :projectTranId";	
				
				session.createQuery(hqlUpdateProject)
				   .setParameter("projectStatus", projectStatus)
				   .setParameter("updatedDatetime", DateTimeUtil.getSysDateTime())
				   .setParameter("projectTranId", projectId)
				   .executeUpdate();
				
			/*	if(projectStatus == 3){
					
					String hql = "SELECT GROUP_CONCAT(CAST(ed.enquiry_id AS CHAR)) from enquiry_details ed "+                   
								 "INNER JOIN  project_details pd ON ed.project_id = pd.project_tran_id "+
								 "WHERE pd.project_tran_id = :projectId and pd.project_status = 3 and ed.followup_status = 1";
					
					Query query       = session.createNativeQuery(hql).setParameter("projectId", projectId);
					String enquiryIdList  = (String) query.uniqueResult();
					
					if(enquiryIdList != null ){
						
						String[] enquiryIdArr = enquiryIdList.split(",");
					
						for(int i = 0 ; i < enquiryIdArr.length ; i++){
							
							String updateEnquiry = "Update EnquiryDetails set followupStatus = 4 where enquiryId = :enquiryId";
							
							 session.createQuery(updateEnquiry)
									.setParameter("enquiryId", Integer.valueOf(enquiryIdArr[i]))
									.executeUpdate();
						}
					}
					
				} */
				
				tx.commit();
				isRecordUpdated = true;
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
		      session.close();
		}
		return isRecordUpdated;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Wing> getProjectStructureByProjectId(int projectId) {
		
		List<Wing> bookingDetailsList = null;
		Session session = null;  
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				Query query = session.createNativeQuery("CALL proc_getProjectStructureByProjectId(:projectId)")
									 .setParameter("projectId", projectId);		
				
				bookingDetailsList = query.setTupleTransformer(Transformers.aliasToBean(Wing.class)).list();   
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	         
		}finally{
		      session.close();
		}
		
		return bookingDetailsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Wing> getBookingDetailsByWingTranId(int wingTranId) {
		

		List<Wing> bookingDetailsListByWing = null;
		Session session = null;  
		Transaction tx = null;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
			String hqlBookingDetails =  " select dummy.flatNumbers as flatNumbers, dummy.bookedFlatNumbers as bookedFlatNumbers, dummy.wingTranId as wingTranId, "
					  + " dummy.floorNumber as floorNumber, dummy.wingName as wingName FROM "
					  + " (SELECT GROUP_CONCAT(distinct ((CAST(f.flat_number AS CHAR))) order by f.flat_number) as flatNumbers,"
					  + " GROUP_CONCAT(distinct (CAST( c.flat_number AS CHAR))) as bookedFlatNumbers, f.wing_id as wingTranId,"
					  + " f.floor_number as floorNumber, concat(f.wing_id,f.floor_number) as test, w.wing_name as wingName "
					  + " from flats as f, client as c, wing as w where f.wing_id = :wingTranId and c.floor_number = f.floor_number "
					  + " and c.wing_id = f.wing_id and w.wing_tran_id = f.wing_id group by f.floor_number, f.wing_id "									  
					  + " UNION "
					  + " SELECT GROUP_CONCAT(distinct ((CAST(f.flat_number AS CHAR))) order by f.flat_number) as flatNumbers, "
					  + " '0' as bookedFlatNumbers, f.wing_id as wingTranId, f.floor_number as floorNumber, " 
					  + " concat(f.wing_id,f.floor_number) as test , w.wing_name as wingName "
					  + " from flats as f, wing as w where f.wing_id = :wingTranId and w.wing_tran_id = f.wing_id " 
					  + " group by f.floor_number,f.wing_id )dummy group by dummy.test order by dummy.wingTranId asc, dummy.floorNumber desc";

				
				Query query = session.createNativeQuery(hqlBookingDetails).setParameter("wingTranId", wingTranId);		
				
				bookingDetailsListByWing = query.setTupleTransformer(Transformers.aliasToBean(Wing.class)).list();   
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
		      session.close();
		}
		
		return bookingDetailsListByWing;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getAllProjectDetailsByUserType(int userId) {
		Session session = null;  
		Transaction tx  = null;
		List<ProjectDetails> projectList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String sql = null;
		
		sql = "select pd.project_id as projectId, pd.project_tran_id as projectTranId, pd.project_name as projectName, pd.project_status as projectStatus, "+
			  "pd.start_date as startDate, pd.expected_completion_date as expectedCompletionDate, pd.subuser_id as subUserId from project_details as pd where is_remove = 0 and FIND_IN_SET("+userId+", subuser_id )"+
			  "order by pd.project_status asc";			

		
		Query query = session.createNativeQuery(sql);
		projectList = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();  
		
		tx.commit();
		session.close();
		return projectList;
	}*/

/*	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getAllProjectsByStatusAndUserType(int projectStatus, int userId) {
		
			 Session session = null;  
			 Transaction tx = null;
			 List<ProjectDetails> projectLists = null;
			 
		    try{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();	
				
					String resultQuery ="select projectId as projectId, projectName as projectName, address as address, startDate as startDate,"
							+ "  expectedCompletionDate as expectedCompletionDate, noOfWings as noOfWings, mahareraNo as mahareraNo, projectTranId as projectTranId,"
							+ "  createdDatetime as createdDatetime, updatedDatetime as updatedDatetime, isRemove as isRemove, projectStatus as projectStatus "
							+ "  from ProjectDetails where projectStatus =:projectStatus and subUserId = :userId and isRemove = 0"; 
							 	 
					Query query = session.createQuery(resultQuery)		
								 .setParameter("projectStatus", projectStatus)
								 .setParameter("userId", userId);  
					projectLists = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();   
					tx.commit();
					
		     }catch(Exception e){	        
		         e.printStackTrace();
		    }finally {
		    if(session.isOpen())
		    session.close();
	    }			
	   return projectLists;	
	}*/
	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getAllProjectsByStatusAndUserType(int projectStatus, int userId) {
		
			 Session session = null;  
			 Transaction tx = null;
			 List<ProjectDetails> projectLists = null;
			 
		    try{
					session = sessionFactory.openSession();
					tx = session.beginTransaction();	
				
					String sql ="select pd.project_id as projectId, pd.project_name as projectName, pd.address as address, pd.start_date as startDate,"
							+ "  pd.expected_completion_date as expectedCompletionDate, pd.no_of_wings as noOfWings, pd.maharera_no as mahareraNo, pd.project_tran_id as projectTranId,"
							+ "  pd.created_datetime as createdDatetime, pd.updated_datetime as updatedDatetime, pd.is_remove as isRemove, pd.project_status as projectStatus "
							+ "  from project_details as pd where FIND_IN_SET("+userId+", subuser_id ) and pd.project_status ="+projectStatus+" and is_remove = 0"; 
					
					Query query = session.createNativeQuery(sql);  
					projectLists = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();   
					tx.commit();
					
		     }catch(Exception e){	        
		         e.printStackTrace();
		    }finally {
		    if(session.isOpen())
		    session.close();
	    }			
	   return projectLists;	
	}*/

	@Override
	public boolean allocateProjectsToUsers(ProjectDetails projectDetails) {
		Session session = null;  
		Transaction tx = null;
		boolean isRecordAllocated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update ProjectDetails set subUserId = :subUserId where projectTranId = :projectTranId";
				
				session.createQuery(hqlUpdate)
					.setParameter("subUserId", projectDetails.getSubUserId())
					.setParameter("projectTranId", projectDetails.getProjectTranId())
					.executeUpdate();
			
			tx.commit();
			isRecordAllocated = true;
		}catch(Exception e){	        
	          e.printStackTrace();
		}finally {
		      session.close();
		}
		return isRecordAllocated;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getAllUsersByProjectId(int projectId) {
		// TODO Auto-generated method stub
		 Session session = sessionFactory.openSession();
		 Transaction tx  = session.beginTransaction();
		 
		 String sql = " select u.user_id as userId, u.user_name as userName ,pd.subuser_id as subUserId"
				+ " from user as u, project_details as pd"
				+ " where FIND_IN_SET(u.user_id, pd.subuser_id )"
				+ " and pd.project_tran_id ="+projectId;
				
		 Query query = session.createNativeQuery(sql);
		 List<ProjectDetails> userList = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();  
		
		 tx.commit();
		 session.close();
		
		 return userList;
	}


	@Override
	public CountDetails getAllCountDetails(int userId, int userType, int projectStatus, int projectTranId, int companyId) {
		
		Session session   =   null;  
		Transaction tx    =   null;
		CountDetails countDetails = null;
		
		try{
			
			  session = sessionFactory.openSession();
			  tx      = session.beginTransaction();
			
			  Query query     =  session.createNativeQuery("CALL proc_getAllCountsOfProjectDetails(:userId, :userType, :projectStatus, :projectId, :companyId)")
					  			.setParameter("userId", userId).setParameter("userType", userType)
					  			.setParameter("projectStatus", projectStatus).setParameter("projectId", projectTranId)
					  			.setParameter("companyId", companyId);
			 
			  countDetails  =  (CountDetails) query.setTupleTransformer(Transformers.aliasToBean(CountDetails.class)).uniqueResult();
			     
			tx.commit();
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			
			session.close();
		}
		return countDetails;
	}

	@Override
	public boolean updateProjectApprovedStatus(int projectId, int isApproved) {
		Session session = null;  
		Transaction tx  = null;
		boolean isRecordUpdated = false;
		try{
			session = sessionFactory.openSession();
			tx = session.beginTransaction();	
			
				String hqlUpdate = "update ProjectDetails set isApproved = :isApproved, updatedDatetime = :updatedDatetime "+
										  "where projectTranId =:projectId";	
				
				int result = session.createQuery(hqlUpdate)
								    .setParameter("isApproved", isApproved)
								    .setParameter("updatedDatetime", DateTimeUtil.getSysDateTime())
								    .setParameter("projectId", projectId)
								    .executeUpdate();
				
				if(result > 0){
					isRecordUpdated = true;
				}
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	          if(tx!= null){
				  tx.rollback();
		      }	
		}finally{
		      session.close();
		}
		return isRecordUpdated;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getAllUnapprovedProjectDetails() {
		Session session = null;  
		Transaction tx  = null;
		List<ProjectDetails> projectNotificationsList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		
		String sqlQuery = "select pd.project_tran_id as projectId, pd.project_name as projectName, pd.project_status as projectStatus, "
				+ " pd.created_datetime as createdDatetime, pd.company_id as companyId,cp.company_name as companyName from project_details as pd,company_profile as cp "
				+ " where pd.is_remove = 0 and pd.is_approved = 0 and pd.company_id = cp.company_id";

		Query query = session.createNativeQuery(sqlQuery);
		projectNotificationsList = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();  
		
		tx.commit();
		session.close();
		return projectNotificationsList;
	}

	@Override
	public int checkIsAnyPropertyBookedByProjectId(int projectId) {
		
		Session session = null;  
		Transaction tx = null;	
		int result = 0;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction(); 
			
			Long count = session.createQuery(
					"select count(*) from Client c where c.projectId = :projectId and c.bookingStatus = 1", Long.class)
					.setParameter("projectId", projectId)
					.uniqueResult();

	        if(count != null && count > 0) result = 1;
		     
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return result;
	}


	@Override
	public boolean updateProjectStatusByScheduledTasks(List<String> projectIdsList) {
		Session session        = null;  
		Transaction tx         = null;
		boolean isUpdate       = false;
		try{
			
			session = sessionFactory.openSession();  
			tx = session.beginTransaction();  
			
			//List<String> userIdList = userIdList
			
			String query = "update project_details set is_approved = 0, updated_datetime =? where project_tran_id = ? ";
	        List<Object[]> inputList = new ArrayList<Object[]>();			        
	        
	        for(int i = 0; i < projectIdsList.size(); i++){
	        	
	            Object[] tmp = new Object[]{ DateTimeUtil.getSysDateTime(), projectIdsList.get(i)};
	            inputList.add(tmp);
	        }
	        
		    getJdbcTemplate().batchUpdate(query, inputList);
		    isUpdate       = true;
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			try { tx.commit(); } catch (Exception ignore) { }
			try { if (session != null && session.isOpen()) session.close(); } catch (Exception ignore) { }
		}
		return isUpdate;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectDetails> getInprogressAndCompletedProjectList(int userType, int userId) {
		
		Session session = null;  
		Transaction tx  = null;
		List<ProjectDetails> projectList = null;
		
		session = sessionFactory.openSession();
		tx      = session.beginTransaction();
		String resultQuery = null;
		
		resultQuery = " select pd.project_id as projectId, pd.project_tran_id as projectTranId, pd.project_name as projectName, "
					+ " pd.project_status as projectStatus from project_details as pd where pd.is_remove = 0 "
				    + " and case when :userType = 2 Then pd.company_id = :userId Else FIND_IN_SET(:userId, pd.subuser_id ) End "
				    + " and (pd.project_status = 2 or pd.project_status = 3) and pd.is_approved = 1 order by pd.project_status asc ";		

		Query query = session.createNativeQuery(resultQuery).setParameter("userId", userId).setParameter("userType", userType);
		projectList = query.setTupleTransformer(Transformers.aliasToBean(ProjectDetails.class)).list();  
		
		tx.commit();
		session.close();
		return projectList;
	}

/*	@SuppressWarnings("unchecked")
	@Override
	public List<MonthWiseTotalVisits> getDashboardDetails(int projectTranId) {
		List<MonthWiseTotalVisits> dashboardDetails = null;
		Session session = null;  
		Transaction tx = null;
		try{
				session = sessionFactory.openSession();
				tx = session.beginTransaction();	
			
				Query query = session.createNativeQuery("CALL proc_getDashboardDetailsByProjectId(:projectId)")
									 .setParameter("projectId", projectTranId);		
				
				dashboardDetails = query.setTupleTransformer(Transformers.aliasToBean(MonthWiseTotalVisits.class)).list();   
				
				tx.commit();
		}catch(Exception e){	        
	          e.printStackTrace();
	         
		}finally{
		      session.close();
		}
		
		return dashboardDetails;


	}*/


	
	
	
}


