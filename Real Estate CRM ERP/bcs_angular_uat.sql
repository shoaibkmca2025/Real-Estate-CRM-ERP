# SQL Manager 2007 Lite for MySQL 4.0.0.3
# ---------------------------------------
# Host     : localhost
# Port     : 3306
# Database : bcs_angular_uat


SET FOREIGN_KEY_CHECKS=0;


USE `bcs_angular_uat`;

#
# Structure for the `activity_log` table : 
#

CREATE TABLE `activity_log` (
  `activity_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `project_tran_id` int(11) NOT NULL,
  `activity_description` varchar(150) NOT NULL,
  `activity_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`activity_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=619 DEFAULT CHARSET=latin1;

#
# Structure for the `aminities` table : 
#

CREATE TABLE `aminities` (
  `amenity_id` int(11) NOT NULL AUTO_INCREMENT,
  `aminities` varchar(100) NOT NULL,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`amenity_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=348 COMMENT='InnoDB free: 10240 kB';

#
# Structure for the `bank_details` table : 
#

CREATE TABLE `bank_details` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(50) NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  `account_name` varchar(50) DEFAULT NULL,
  `ifsc_code` varchar(30) DEFAULT NULL,
  `account_type` varchar(30) DEFAULT NULL,
  `attachment` varchar(70) DEFAULT NULL,
  `account_number` varchar(50) DEFAULT NULL,
  `contact_person` varchar(50) DEFAULT NULL,
  `contact_no` varchar(50) DEFAULT NULL,
  `bank_type` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`bank_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

#
# Structure for the `cal` table : 
#

CREATE TABLE `cal` (
  `month` int(11) DEFAULT NULL,
  `monthname` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `calendar_tbl` table : 
#

CREATE TABLE `calendar_tbl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `month` int(11) DEFAULT NULL,
  `monthname` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

#
# Structure for the `client` table : 
#

CREATE TABLE `client` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_name` varchar(100) NOT NULL,
  `date_of_birth` varchar(50) NOT NULL,
  `pan_number` varchar(50) NOT NULL,
  `aadhar_number` varchar(50) NOT NULL,
  `enquiry_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `wing_id` int(11) NOT NULL,
  `property_type_id` int(11) NOT NULL,
  `property_id` int(11) NOT NULL,
  `property_area` double(15,2) NOT NULL,
  `floor_number` int(11) NOT NULL,
  `flat_number` int(11) NOT NULL,
  `floor_name` varchar(50) NOT NULL,
  `rate` double(15,2) NOT NULL,
  `infrastructure_amount` double(15,2) NOT NULL,
  `agreement_amount` double(12,2) NOT NULL,
  `gst_percentage` double(5,2) DEFAULT NULL,
  `client_tran_id` int(11) NOT NULL,
  `is_paid` int(11) DEFAULT '0',
  `user_id` int(11) NOT NULL,
  `booking_date` varchar(50) DEFAULT NULL,
  `registration_no` varchar(100) DEFAULT NULL,
  `registration_date` varchar(50) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `booking_status` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`client_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=16384 COMMENT='InnoDB free: 9216 kB';

#
# Structure for the `client_documents` table : 
#

CREATE TABLE `client_documents` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `document_title` varchar(200) NOT NULL,
  `document_path` varchar(200) NOT NULL,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `close_enquiry` table : 
#

CREATE TABLE `close_enquiry` (
  `closed_enquiry_id` int(11) NOT NULL AUTO_INCREMENT,
  `reason` varchar(70) NOT NULL,
  `enquiry_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`closed_enquiry_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

#
# Structure for the `company_payment_details` table : 
#

CREATE TABLE `company_payment_details` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `company_id` int(11) NOT NULL,
  `payment_type` int(11) NOT NULL,
  `start_date` varchar(100) NOT NULL,
  `end_date` varchar(100) NOT NULL,
  `amount` double(15,2) NOT NULL,
  `paid_date` varchar(100) DEFAULT NULL,
  `discount` double(15,2) NOT NULL,
  `gst` double(15,2) NOT NULL,
  `total_amount` double(15,2) NOT NULL,
  `send_invoice_date` varchar(50) DEFAULT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=4096 COMMENT='InnoDB free: 1342464 kB';

#
# Structure for the `company_profile` table : 
#

CREATE TABLE `company_profile` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(100) NOT NULL,
  `website` varchar(100) DEFAULT NULL,
  `company_email` varchar(50) NOT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `landline` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `logo_path` varchar(100) DEFAULT NULL,
  `marketed_by` varchar(100) DEFAULT NULL,
  `marketed_by_website` varchar(100) DEFAULT NULL,
  `is_updated` int(11) NOT NULL DEFAULT '0',
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=2730 COMMENT='InnoDB free: 1342464 kB';

#
# Structure for the `custom_events` table : 
#

CREATE TABLE `custom_events` (
  `custom_event_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(50) NOT NULL,
  `event_date` varchar(50) NOT NULL,
  `sms_id` int(11) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`custom_event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

#
# Structure for the `disbursement` table : 
#

CREATE TABLE `disbursement` (
  `disbursement_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) NOT NULL,
  `percentage_value` double(12,2) NOT NULL,
  `disbursement_description` varchar(50) DEFAULT NULL,
  `disbursement_amount` double(12,2) NOT NULL,
  `gst_amount` double(12,2) NOT NULL,
  `prev_remaining_amount` double(12,2) NOT NULL DEFAULT '0.00',
  `total_amount` double(12,2) NOT NULL,
  `paid_amount` double(12,2) NOT NULL DEFAULT '0.00',
  `remaining_amount` double(12,2) NOT NULL,
  `send_pdf_date` varchar(30) DEFAULT NULL,
  `paid_date` varchar(100) DEFAULT NULL,
  `demand_letter_pdf_path` varchar(200) DEFAULT NULL,
  `disbursement_followup_date` varchar(50) DEFAULT NULL,
  `project_disbursement_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`disbursement_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=744 COMMENT='InnoDB free: 10240 kB';

#
# Structure for the `disbursement_partial_payments` table : 
#

CREATE TABLE `disbursement_partial_payments` (
  `partial_payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `partial_payment_description` varchar(200) NOT NULL,
  `partial_gross_amount` double(15,2) NOT NULL,
  `partial_gst_amount` double(15,2) NOT NULL,
  `partial_payment_paid_date` varchar(50) NOT NULL,
  `total_partial_amount` double(15,2) NOT NULL,
  `disbursement_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`partial_payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=5461 COMMENT='InnoDB free: 9216 kB';

#
# Structure for the `document` table : 
#

CREATE TABLE `document` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_type_id` int(11) NOT NULL,
  `document_title` varchar(50) DEFAULT NULL,
  `document_path` varchar(100) NOT NULL,
  `project_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`document_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

#
# Structure for the `document_type` table : 
#

CREATE TABLE `document_type` (
  `document_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `document_type` varchar(50) NOT NULL,
  PRIMARY KEY (`document_type_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

#
# Structure for the `enquiry_details` table : 
#

CREATE TABLE `enquiry_details` (
  `enquiry_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `mobile_no` varchar(20) NOT NULL,
  `landline_no` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `occupation` varchar(50) NOT NULL,
  `company` varchar(50) DEFAULT NULL,
  `sales_person` int(11) DEFAULT '0',
  `project_id` int(11) NOT NULL,
  `property_type` int(11) NOT NULL,
  `property` int(11) NOT NULL,
  `property_area` double(15,2) NOT NULL,
  `budget_min` int(11) NOT NULL DEFAULT '0',
  `budget_max` int(11) NOT NULL,
  `reference` int(11) NOT NULL,
  `reference_name` varchar(50) DEFAULT NULL,
  `followup_date` varchar(50) NOT NULL,
  `followup_status` int(11) NOT NULL DEFAULT '1',
  `remark` varchar(200) DEFAULT NULL,
  `remark_date` varchar(50) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`enquiry_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=5461 COMMENT='InnoDB free: 1342464 kB';

#
# Structure for the `flats` table : 
#

CREATE TABLE `flats` (
  `flat_id` int(20) NOT NULL AUTO_INCREMENT,
  `flat_number` int(30) NOT NULL,
  `floor_number` int(20) NOT NULL,
  `floor_name` varchar(50) NOT NULL,
  `project_id` int(20) NOT NULL,
  `wing_id` int(20) NOT NULL,
  `wing_details_id` int(11) NOT NULL,
  PRIMARY KEY (`flat_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1960 DEFAULT CHARSET=latin1;

#
# Structure for the `floor_details` table : 
#

CREATE TABLE `floor_details` (
  `floor_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_number` int(11) NOT NULL,
  `floor_name` varchar(50) NOT NULL,
  `wing_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`floor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

#
# Structure for the `followup_details` table : 
#

CREATE TABLE `followup_details` (
  `followup_id` int(11) NOT NULL AUTO_INCREMENT,
  `followup_descr` varchar(200) NOT NULL,
  `enquiry_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`followup_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=2048 COMMENT='InnoDB free: 1342464 kB';

#
# Structure for the `login_details` table : 
#

CREATE TABLE `login_details` (
  `login_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) NOT NULL,
  `login_datetime` varchar(100) NOT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `location` varchar(100) NOT NULL,
  `device_name` varchar(50) DEFAULT NULL,
  `ip_address` varchar(50) NOT NULL,
  `created_datetime` varchar(100) DEFAULT NULL,
  `update_datetime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_details_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=775 DEFAULT CHARSET=utf8;

#
# Structure for the `mobilenumbers` table : 
#

CREATE TABLE `mobilenumbers` (
  `mobilenumber_id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile_number` varchar(40) NOT NULL,
  `sms_id` int(11) NOT NULL,
  `send_status` int(11) NOT NULL DEFAULT '0',
  `created_datetime` varchar(50) NOT NULL,
  `custom_event_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`mobilenumber_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;

#
# Structure for the `other_payments` table : 
#

CREATE TABLE `other_payments` (
  `other_payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `amount` double(12,2) NOT NULL,
  `cheque_number` varchar(50) DEFAULT NULL,
  `cheque_date` varchar(50) DEFAULT NULL,
  `due_date` varchar(50) DEFAULT NULL,
  `paid_date` varchar(50) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`other_payment_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

#
# Structure for the `payment_followup` table : 
#

CREATE TABLE `payment_followup` (
  `payment_followup_id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_followup_descr` varchar(200) NOT NULL,
  `disbursement_id` int(11) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`payment_followup_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;

#
# Structure for the `project_details` table : 
#

CREATE TABLE `project_details` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  `start_date` varchar(20) NOT NULL,
  `expected_completion_date` varchar(20) NOT NULL,
  `no_of_wings` int(11) NOT NULL,
  `maharera_no` varchar(20) NOT NULL,
  `project_tran_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `subuser_id` varchar(100) DEFAULT NULL,
  `project_status` int(11) NOT NULL DEFAULT '1',
  `company_id` int(11) NOT NULL,
  `is_approved` int(11) NOT NULL DEFAULT '0',
  `is_remove` int(11) NOT NULL DEFAULT '0',
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `letter_head` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`project_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=1092 COMMENT='InnoDB free: 10240 kB';

CREATE TRIGGER `project_details_after_upd_tr` AFTER UPDATE ON `project_details`
  FOR EACH ROW
BEGIN

DECLARE monthCount INTEGER ; 
DECLARE monthVal INTEGER ; 
DECLARE yearVal INTEGER ; 
DECLARE projectCreationDate VARCHAR(50);
DECLARE created_datetime VARCHAR(50);

	DECLARE projectId INTEGER;      
    SET projectId   =  OLD.project_tran_id; 

	SELECT pd.created_datetime  from project_details as pd 
    WHERE pd.project_tran_id = projectId AND pd.project_status =2 AND pd.is_remove = 0
    AND pd.is_approved = 1 INTO projectCreationDate; 
      
    IF(projectCreationDate IS NOT NULL)THEN
    
    INSERT INTO temp() VALUES(projectId,projectCreationDate) ;   
    
    SET created_datetime = concat(01,'/',
     						extract(month from STR_TO_DATE(projectCreationDate, '%d/%m/%Y %h:%i:%s %p')),
                            '/',
                            extract(year from STR_TO_DATE(projectCreationDate, '%d/%m/%Y %h:%i:%s %p')));
       
    
	SELECT TIMESTAMPDIFF(MONTH, DATE_FORMAT(STR_TO_DATE(created_datetime, '%d/%m/%Y'),'%Y-%m-%d '),
	DATE_FORMAT(NOW(), '%Y-%m-%d ')) into monthCount; 
    
    SET @counter := -1;
	WHILE (DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL @counter MONTH))) < DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL monthCount MONTH)))) DO 
				
          
            SET monthVal := DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL monthCount MONTH), INTERVAL @counter:=@counter + 1 MONTH),'%m');
            SET yearVal  := DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL monthCount MONTH), INTERVAL @counter  MONTH),'%Y');
    		SET @monthname  := DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL monthCount MONTH), INTERVAL @counter MONTH),'%b');
        
   			SET @record_exists := EXISTS(SELECT `month` FROM `calendar_tbl` WHERE `month` = monthVal  
            									 and `year` = yearVal  
            					  				 and `project_id` = projectId); 
            IF(@record_exists = 0) THEN       
     
    			INSERT INTO calendar_tbl(month, monthname, year, project_id) SELECT monthVal,@monthname, yearVal,  projectId ;
			end IF; 			
	END WHILE; 
    
	END IF; 	  
END;

#
# Structure for the `project_disbursement` table : 
#

CREATE TABLE `project_disbursement` (
  `project_disbursement_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `disbursement_title` varchar(50) NOT NULL,
  `disbursement_description` varchar(200) DEFAULT NULL,
  `disbursement_percentage` double(15,2) NOT NULL,
  `completion_date` varchar(50) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`project_disbursement_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=latin1;

#
# Structure for the `property` table : 
#

CREATE TABLE `property` (
  `property_id` int(11) NOT NULL AUTO_INCREMENT,
  `property_name` varchar(50) NOT NULL,
  `property_type_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_disabled` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`property_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

#
# Structure for the `property_type` table : 
#

CREATE TABLE `property_type` (
  `property_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `property_type_descr` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`property_type_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Structure for the `reference` table : 
#

CREATE TABLE `reference` (
  `reference_id` int(11) NOT NULL AUTO_INCREMENT,
  `reference_type` varchar(100) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`reference_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

#
# Structure for the `sales_person` table : 
#

CREATE TABLE `sales_person` (
  `sales_person_id` int(11) NOT NULL AUTO_INCREMENT,
  `sales_person_name` varchar(50) NOT NULL,
  `sales_person_email` varchar(50) NOT NULL,
  `sales_person_address` varchar(100) NOT NULL,
  `sales_person_mobile` varchar(20) NOT NULL,
  `project_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_disabled` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`sales_person_id`) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `settings` table : 
#

CREATE TABLE `settings` (
  `settings_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_email` varchar(50) NOT NULL,
  `followup_notification_duration` int(11) NOT NULL DEFAULT '0',
  `payment_notification_duration` int(11) NOT NULL DEFAULT '0',
  `payment_duedate_duration` int(11) NOT NULL DEFAULT '0',
  `company_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`settings_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Structure for the `sms` table : 
#

CREATE TABLE `sms` (
  `sms_id` int(11) NOT NULL AUTO_INCREMENT,
  `sms_text` varchar(200) NOT NULL,
  `sms_send_time` varchar(50) DEFAULT NULL,
  `company_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`sms_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=1260;

#
# Structure for the `sms_credit_details` table : 
#

CREATE TABLE `sms_credit_details` (
  `sms_credit_id` int(11) NOT NULL AUTO_INCREMENT,
  `total_credits` int(50) NOT NULL DEFAULT '0',
  `total_sent` int(50) NOT NULL DEFAULT '0',
  `available_credits` int(50) NOT NULL DEFAULT '0',
  `company_id` int(11) NOT NULL,
  `insertion_date` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`sms_credit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Structure for the `temp` table : 
#

CREATE TABLE `temp` (
  `id` int(11) DEFAULT NULL,
  `creation_date` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Structure for the `user` table : 
#

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `company_id` int(11) NOT NULL DEFAULT '0',
  `user_name` varchar(30) NOT NULL,
  `user_email` varchar(50) NOT NULL,
  `user_mobile` varchar(50) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `user_type` int(11) NOT NULL,
  `user_status` int(11) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  `token_name` varchar(300) DEFAULT NULL,
  `user_device_id` varchar(50) DEFAULT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

#
# Structure for the `wing` table : 
#

CREATE TABLE `wing` (
  `wing_id` int(11) NOT NULL AUTO_INCREMENT,
  `wing_name` varchar(50) NOT NULL,
  `no_of_floors` int(20) NOT NULL,
  `total_flats` int(20) NOT NULL,
  `project_id` int(11) NOT NULL,
  `wing_tran_id` int(11) NOT NULL,
  `is_manual_floors` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`wing_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=latin1 AVG_ROW_LENGTH=8192 COMMENT='InnoDB free: 9216 kB';

#
# Structure for the `wing_details` table : 
#

CREATE TABLE `wing_details` (
  `wing_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `floor_number` int(11) NOT NULL,
  `floor_name` varchar(50) NOT NULL,
  `property_id` int(11) NOT NULL,
  `property_area` double(15,2) NOT NULL,
  `no_of_flats` int(11) NOT NULL,
  `property_type_id` int(11) NOT NULL,
  `wing_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_datetime` varchar(50) NOT NULL,
  `updated_datetime` varchar(50) NOT NULL,
  `is_remove` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`wing_details_id`) 
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=latin1;

CREATE TRIGGER `wing_details_after_ins_tr` AFTER INSERT ON `wing_details`
  FOR EACH ROW
BEGIN

DECLARE wingId INTEGER ;
DECLARE wingDetailsId INTEGER ;
DECLARE projectId INTEGER ;
DECLARE floorNumber INTEGER ;
DECLARE floorName VARCHAR(50) ;
DECLARE totalFlats INTEGER ;
DECLARE x INTEGER ;

DECLARE flatNum VARCHAR(50) ;
DECLARE prevFlatNum INTEGER ;

DECLARE existingFlatNo INTEGER ;  
SET  existingFlatNo = 0;

 SELECT wd.wing_details_id, wd.wing_id, w.project_id, wd.floor_number, wd.no_of_flats, wd.floor_name
 	 	INTO wingDetailsId,wingId,projectId,floorNumber,totalFlats, floorName 
 FROM wing_details wd, wing w WHERE w.wing_tran_id = wd.wing_id ORDER BY wing_details_id DESC LIMIT 1 ;
               
 SELECT f.flat_number into existingFlatNo from flats f 
 		 where f.floor_number = floorNumber and f.floor_name = floorName
 		 and f.wing_id = wingId order by f.flat_id desc limit 1;

IF(existingFlatNo > 0 )THEN  
      set x = 1 ;
   	  while x <= totalFlats do    
      
         set prevFlatNum = existingFlatNo+X ;
         
         INSERT INTO flats(flat_number, floor_name,  floor_number, wing_id, project_id, wing_details_id) 
         VALUES(prevFlatNum, floorName, floorNumber, wingId, projectId,wingDetailsId);  
         
         set x=x+1; 
         
      end while ;  

ELSE 
 IF(totalFlats >  0 ) THEN
   set x = 1 ;
   while x <= totalFlats do    
      
   	   IF(floorNumber = -1) THEN
            set flatNum = x ; 
       ELSE 
       	   IF(x < 10) THEN
        		set flatNum =CONCAT(floorNumber,"0",x);    
            ELSE  
                set flatNum = CONCAT(floorNumber,"",x) ;    
            end IF;   
       end IF ;   
       INSERT INTO flats(flat_number,floor_name, floor_number, wing_id, project_id, wing_details_id)
       		  VALUES(flatNum, floorName, floorNumber, wingId, projectId,wingDetailsId);  
        
     set x=x+1;
    end while ;
  end IF ;  
 end IF ;         
END;

CREATE TRIGGER `wing_details_after_del_tr` AFTER DELETE ON `wing_details`
  FOR EACH ROW
BEGIN

	DECLARE wingId INTEGER;      
    SET wingId   =  OLD.wing_id; 

	DELETE FROM flats where wing_id = wingId; 

END;

#
# Definition for the `demo` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `demo`()
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

DECLARE projectsCount INTEGER ;
DECLARE projectId INTEGER ;
DECLARE projectIdList VARCHAR(50);
DECLARE x INTEGER;
DECLARE record_exists  INTEGER;

SET x = 1; 

	  select count(project_tran_id), GROUP_CONCAT(project_tran_id) into projectsCount, projectIdList  
      from project_details where is_approved = 1 and project_status = 2;    
      
 	  while x <= projectsCount do 
      
      		SELECT substring_index( SUBSTRING_INDEX(projectIdList, ',', x),',', -1) INTO projectId; 
            
            SET x = x+1; 
            
      		INSERT INTO calendar_tbl(month, monthname, year, project_id) VALUES(12,
						                'Dec',
										2018,
           								projectId);
           
      end while ;

END;

#
# Definition for the `new_proc` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `new_proc`()
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

DECLARE projectsCount INTEGER ;
DECLARE projectId INTEGER ;
DECLARE projectIdList VARCHAR(50);
DECLARE x INTEGER;
DECLARE record_exists  INTEGER;

SET x = 1; 

	  select count(project_tran_id), GROUP_CONCAT(project_tran_id) into projectsCount, projectIdList  
      from project_details where is_approved = 1 and project_status = 2;    
      
 	  while x <= projectsCount do 
      
      		SELECT substring_index( SUBSTRING_INDEX(projectIdList, ',', x),',', -1) INTO projectId; 
            
            SET x = x+1; 
            SET record_exists = EXISTS(SELECT `month` FROM `calendar_tbl` WHERE `month` = DATE_FORMAT(NOW(),'%m') 
            															 and `year` = DATE_FORMAT(NOW(),'%Y')
            															 and `project_id` = projectId); 
            IF(record_exists = 0) THEN
      		 INSERT INTO calendar_tbl(month, monthname, year, project_id) SELECT DATE_FORMAT(NOW(),'%m'),
						                DATE_FORMAT(NOW(),'%b'),
										DATE_FORMAT(NOW(),'%Y'),
           								projectId;
           end IF;  
      end while ;

END;

#
# Definition for the `proc_calendar` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_calendar`()
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

CREATE TABLE IF NOT EXISTS `Cal` (
`month` INT(11) ,
`monthname` VARCHAR(50) ,
`year` INT(11)
) ;    

TRUNCATE TABLE Cal;               

SET @counter := -1;
WHILE (DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL @counter MONTH))) < DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL 5 MONTH)))) DO 
INSERT INTO Cal SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter:=@counter + 1 MONTH),'%m'),
						DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter MONTH),'%b'),
						DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter + 1 MONTH),'%Y');
END WHILE;
END;

#
# Definition for the `proc_insert_flat_details` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_insert_flat_details`(
        IN `wing_id` INTEGER(11)
    )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

DECLARE wingId INTEGER ;
DECLARE projectId INTEGER ;
DECLARE noOfFloors INTEGER ;
DECLARE totalFlats INTEGER ;
DECLARE x INTEGER ;
DECLARE y INTEGER ;
DECLARE z INTEGER ;
DECLARE flatsPerFloor INTEGER ;
DECLARE flatNum INTEGER ;
DECLARE noOfShops INTEGER ; 
DECLARE flatsCount INTEGER ;   
      
 
SELECT w.wing_tran_id, w.project_id,w.no_of_floors, w.total_flats INTO wingId,projectId,noOfFloors,totalFlats
 FROM wing w WHERE w.project_id = project_id and w.wing_tran_id = wing_id ; 
 
set noOfShops = 0 ;         
SELECT SUM(wd.no_of_flats) into noOfShops from wing_details as wd where
 wd.property_type_id = 2 and property_id = 5
and wd.wing_id = wing_id;

 IF (noOfShops IS NULL)  THEN 
     set noOfShops = 0 ;
  end IF ;   
   
set flatsCount = totalFlats -  noOfShops;

 IF(noOfFloors >  0 ) THEN
   set x = 1 ;
   while x <= noOfFloors do    
        
    SET flatsPerFloor =  FLOOR(flatsCount /  noOfFloors ); 
     IF( flatsPerFloor >  0 ) THEN
   			set y = 1 ;   
           
   			while y <= flatsPerFloor do    
             set flatNum = 100*x+y ;
    		INSERT INTO flats(flat_number, floor_number, wing_id, project_id) VALUES(flatNum, x, wingId, projectId);  
    
    		set y=y+1;
    		end while ;
        END IF;  
    
     set x=x+1;
    end while ;
  end IF ;    
  
  IF( noOfShops >  0 ) THEN     
  
         set z = 1;
         while z <= noOfShops do    
             set flatNum = z ;
    		INSERT INTO flats(flat_number, floor_number, wing_id, project_id) VALUES(flatNum, 0, wingId, projectId);  
                 
    		set z=z+1;
    	end while ;             
 end IF ; 
   

END;

#
# Definition for the `getAllClientDetailsByDisbursementId` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `getAllClientDetailsByDisbursementId`(
        IN `proj_disbursement_id` INTEGER(11)
    )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
select c.booked_amount as bookedAmount,sum(d.disbursement_amount) as totalDisbursementAmount, 
   sum(d.percentage_value) as totalPercentage,
    Sum(Case When d.paid_date IS NOT NULL 
      Then d.disbursement_amount Else 0 End) as totalPaidAmount ,
     c.client_tran_id as clientTranId, c.floor_number  
	as floorNumber, c.flat_number as flatNumber, e.first_name as firstName, e.last_name as lastName, 
	e.mobile_no as mobileNo, e.email as email, e.address as address, e.city as city, w.wing_name as wingName,
	p.project_name as projectName, p.address as projectAddress, p.project_tran_id as projectId
	from project_disbursement  pd, client c, disbursement d, enquiry_details e, project_details p, wing w
	where pd.project_disbursement_id = d.project_disbursement_id 
	and c.client_tran_id = d.client_id      	
	and c.enquiry_id = e.enquiry_id
	and c.project_id = p.project_tran_id
	and c.wing_id = w.wing_tran_id
    and pd.completion_date IS NOT NULL 
	and c.project_id = (select pd.project_id from project_disbursement  pd 
    					where pd.project_disbursement_id = proj_disbursement_id)
	group by c.client_tran_id;

#
# Definition for the `new_monthwiseTotalVisitsProcBackup` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `new_monthwiseTotalVisitsProcBackup`(IN userId INTEGER(11), IN userType INTEGER(11), IN companyId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

	CREATE TEMPORARY TABLE IF NOT EXISTS `Cal` (
		`month` INT(11) ,
		`year` INT(11) 
	) ;                     

	SET @counter := -1;
	WHILE (DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL @counter MONTH))) < DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL 5 MONTH)))) DO 
	INSERT INTO Cal SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter:=@counter + 1 MONTH),'%m'),DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter + 1 MONTH),'%Y');
	END WHILE; 
    
    SELECT  MONTHNAME(STR_TO_DATE(`Cal`.`Month`, '%m')) as monthName ,
    IFNULL(tbl.totalVisit,0) AS totalVisit, tbl.projectName
	FROM `Cal`
	LEFT JOIN (  
	SELECT extract(month from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) as mon,
	   SUM(case When p.project_tran_id = ed.project_id
           	 	Then 1 Else 0 End) as totalVisit, p.project_tran_id, p.project_name as projectName
	FROM project_details p, enquiry_details ed 
	WHERE p.is_remove = 0 
	AND ed.is_remove  = 0
	AND p.is_approved = 1 
	AND p.project_status = 2 
    AND (Case When userType = 2 
            Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)
	AND STR_TO_DATE(ed.created_datetime, '%d/%m/%Y') >=  DATE_SUB(CURRENT_DATE(), INTERVAL 6 MONTH) 
	GROUP BY p.project_tran_id, extract(month from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) ) as tbl
    ON tbl.mon =`Cal`.`Month`
    order by tbl.project_tran_id ASC, `Cal`.`Year` ASC, `Cal`.`Month` asc ;
END;

#
# Definition for the `proc_calendarNew` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_calendarNew`(IN monthCount INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN


CREATE TEMPORARY TABLE IF NOT EXISTS `Cal1` (
`month` INT(11) ,
`year` INT(11) 

) ;    

SET @counter := -1;
WHILE (DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL @counter MONTH))) < DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL monthCount MONTH)))) DO 
INSERT INTO Cal1 SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL monthCount MONTH), INTERVAL @counter:=@counter + 1 MONTH),'%m'),
						
						DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL monthCount MONTH), INTERVAL @counter  MONTH),'%Y');
END WHILE;
END;

#
# Definition for the `proc_getAllCountsOfProjectDetails` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getAllCountsOfProjectDetails`(IN userId INTEGER(11), IN userType INTEGER(11), IN projectStatus INTEGER(11), IN projectId INTEGER(11), IN companyId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY INVOKER
    COMMENT ''
BEGIN
	SELECT 	tbl1.totalVisit as totalVisit ,tbl1.todaysVisit as todaysVisit,
     		tbl2.totalClosedVisits as totalClosedVisits, tbl2.todaysClosedVisits as todaysClosedVisits,
    		tbl3.totalDemandLetters as totalDemandLetters, tbl3.todaysSendDemandLetters as todaysSendDemandLetters, 
    		tbl4.totalClient as totalClient, tbl4.todaysClients as todaysClients,
        	/*tbl5.totalFollowup, tbl5.todaysFollowups,*/
            tbl6.totalPaymentFollowupCount as totalPaymentFollowupCount, tbl7.todaysPaymentFollowupCount as todaysPaymentFollowupCount,
            tbl8.todaysReceivedPaymentCount as todaysReceivedPaymentCount, tbl9.todaysFollowups as todaysFollowups,
            tbl10.totalFollowup as totalFollowup
	FROM  
    	/* get enquiry counts */
	   (SELECT count(ed.enquiry_id) as totalVisit,
       		   count(case When STR_TO_DATE(ed.created_datetime, '%d/%m/%Y') = CURDATE() 
       					    Then ed.enquiry_id end) as todaysVisit 
				from project_details p, enquiry_details ed
	    WHERE p.is_remove = 0 
		AND p.project_tran_id = ed.project_id 
		AND ed.is_remove     = 0
        /* and ed.followup_status = 1   */   
        AND p.is_approved = 1
        AND Case When projectId != 0
            Then p.project_tran_id = projectId else p.project_status  = projectStatus End 
        AND (Case When userType = 2 
            Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)  
		) as tbl1, 
        
        /* get closed enquiry counts */
       ( SELECT COUNT(*) as totalClosedVisits, 
       			COUNT(case When STR_TO_DATE(ce.created_datetime, '%d/%m/%Y') = CURDATE() 
       					    Then ed.enquiry_id end) as todaysClosedVisits 
	    FROM project_details p, enquiry_details ed, close_enquiry ce       
		WHERE p.is_remove = 0 and ed.is_remove = 0 
        AND p.project_tran_id = ed.project_id 
        AND ed.enquiry_id = ce.enquiry_id 
        AND p.is_approved = 1
        AND Case When projectId != 0 
            Then p.project_tran_id = projectId else p.project_status = projectStatus End 
        AND (Case When userType = 2 
            Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)) as tbl2, 
        
         /* get pending Demand letter counts */   
         
       (SELECT COUNT(case When dummy.send_pdf_date IS NULL 
       					    Then dummy.client_tran_id End) as totalDemandLetters, 
            	COUNT(case When STR_TO_DATE(dummy.send_pdf_date, '%d/%m/%Y')= CURDATE()
       		           Then dummy.client_tran_id End) as todaysSendDemandLetters 
 		FROM (select  tbl.project_id, tbl.send_pdf_date, tbl.client_tran_id  
       		 From    
        		(SELECT  cl.project_id , d.send_pdf_date, cl.client_tran_id 			
   		 	 	 FROM project_disbursement pd, disbursement d, client cl, project_details p  
   		 		 WHERE pd.project_disbursement_id = d.project_disbursement_id 
   		 		 AND d.client_id  = cl.client_tran_id
                 AND cl.is_paid = 0 
                 AND p.project_tran_id = cl.project_id 
   		 		 AND pd.completion_date Is NOT NULL 
                 AND p.is_approved = 1 
                 and p.is_remove = 0
                 AND Case When 0 != 0
         		  	 Then p.project_tran_id = 0 else p.project_status = 2 End 
         		 AND Case When 2 = 2 
            	      Then p.company_id = 1  Else FIND_IN_SET(0, p.subuser_id ) End
        		 ORDER BY d.disbursement_id desc ) as tbl 
  			GROUP BY tbl.client_tran_id ) as dummy
       	 ) as tbl3, 
             
         /* get total clients count */
      ( SELECT COUNT(*) as totalClient, 
               COUNT(case When STR_TO_DATE(cl.created_datetime, '%d/%m/%Y') = CURDATE() 
       					    Then cl.client_tran_id end) as todaysClients 
 		FROM project_details p, client cl
		WHERE p.project_tran_id = cl.project_id
        AND p.is_remove = 0 
        AND p.is_approved = 1
        AND Case When projectId != 0
           		  Then p.project_tran_id = projectId else p.project_status = projectStatus  End 
        AND (Case When userType = 2 
            Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)  
       ) as tbl4,    
        
        /* get followup counts */
        /*
       ( SELECT count(distinct (case When STR_TO_DATE(ed.followup_date, '%d/%m/%Y') <= CURDATE() 
       								 Then ed.enquiry_id end))as totalFollowup, 
       			SUM(case When fdd.followup_id NOT IN( SELECT Max(fdd.followup_id) as followup_id 
 								  FROM followup_details as fdd 
 								  Group By fdd.enquiry_id) 
 							and STR_TO_DATE(ed.updated_datetime, '%d/%m/%Y') = CURDATE()
       					 Then 1 Else 0 End) as todaysFollowups
		FROM enquiry_details ed, project_details p , followup_details as fdd
		WHERE p.is_remove = 0
        AND p.is_approved = 1 
        AND p.project_tran_id = ed.project_id 
        AND ed.enquiry_id = fdd.enquiry_id
    	AND ed.is_remove = 0	
    	AND ed.followup_status = 1  
        AND Case When projectId != 0
        		  Then p.project_tran_id = projectId else p.project_status = projectStatus  End
        AND (Case When userType = 2 
            Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)   
       ) as tbl5 ,*/
         
          /* get Pending Payment counts */   
      ( SELECT COUNT(c.client_id) as totalPaymentFollowupCount
		FROM client c , project_details p,  disbursement d
		WHERE c.client_tran_id = d.client_id 
		AND p.project_tran_id = c.project_id
		AND c.is_paid = 0
        AND p.is_remove = 0
        AND p.is_approved = 1  
		AND d.disbursement_id IN (select MAX(disbursement_id)           
						  from disbursement d
						  where send_pdf_date IS NOT NULL
	 					  and remaining_amount > 0
                          and STR_TO_DATE(d.disbursement_followup_date , '%d/%m/%Y') <= CURRENT_DATE()
	 					  group by client_id ) 
      	AND Case When projectId != 0
        		  Then p.project_tran_id = projectId else p.project_status = projectStatus  End
        AND (Case When userType = 2 
            Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)
        ) as tbl6,
       
       /* get Todays Payment Followup counts */                      
      ( SELECT COUNT(distinct(c.client_id)) as todaysPaymentFollowupCount
		FROM client c , project_details p, disbursement d,payment_followup pf
		WHERE c.client_tran_id = d.client_id 
		AND p.project_tran_id = c.project_id
		AND pf.disbursement_id = d.disbursement_id
		AND STR_TO_DATE(pf.updated_datetime , '%d/%m/%Y') = CURRENT_DATE()
        AND p.is_remove = 0
        AND p.is_approved = 1 
        AND Case When projectId != 0
        		  Then p.project_tran_id = projectId else p.project_status = projectStatus  End
        AND (Case When userType = 2 
            Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)  
        ) as tbl7 ,
                     
        /*** get todays Received Payment Count ****/
        
        ( SELECT COUNT(distinct d.client_id) as todaysReceivedPaymentCount
		  FROM project_details as p, client as cl, disbursement as d 
		  LEFT JOIN
		       disbursement_partial_payments as dpp
		  ON d.disbursement_id = dpp.disbursement_id AND STR_TO_DATE(dpp.updated_datetime, '%d/%m/%Y') = CURDATE()
		  WHERE p.project_tran_id = cl.project_id 
		  AND cl.client_tran_id = d.client_id
		  AND d.send_pdf_date IS NOT NULL 
		  AND d.paid_date IS NOT NULL
          AND p.is_remove = 0
          AND p.is_approved = 1  
		  AND STR_TO_DATE(d.updated_datetime, '%d/%m/%Y') = CURDATE()
		  AND Case When projectId != 0 Then p.project_tran_id = projectId else true end
		  AND (Case When userType = 2
		  Then p.company_id = companyId  Else FIND_IN_SET(userId, p.subuser_id )  End) 
        ) as tbl8 ,
        
        
        /* get todays followup counts */
        (select count(ed.enquiry_id) as todaysFollowups
		from enquiry_details as ed, project_details as pd ,followup_details fd,
		(
  			select MAX(fdd.`followup_id`) followup_id, fdd.`enquiry_id`
  			from `followup_details` as fdd
  			where fdd.`followup_id` NOT IN( SELECT Max(fdd.`followup_id`)as followup_id 
  											FROM followup_details as fdd 
 											Group By fdd.`enquiry_id`)
  			and fdd.`enquiry_id` IN ( select enquiry_id 
      								  from enquiry_details 
      								  where is_remove = 0 and followup_status = 1 
      								  and STR_TO_DATE(updated_datetime, '%d/%m/%Y')=CURDATE()
  									)Group By fdd.`enquiry_id`
		)  as dummy
		where ed.`enquiry_id`  = fd.enquiry_id 
		and ed.project_id  = pd.project_tran_id 
		and fd.followup_id   = dummy.followup_id
		and fd.enquiry_id    = dummy.`enquiry_id`
		and pd.is_remove = 0
		and pd.is_approved = 1 
		and ed.is_remove = 0
		and ed.followup_status = 1  
		and Case When projectId != 0
        		  Then pd.project_tran_id = projectId else pd.project_status = projectStatus  End
		and (Case When userType = 2 
            Then pd.company_id = companyId Else FIND_IN_SET(userId, pd.subuser_id ) End)   
       ) as tbl9,
       
       
        /* get Total Followup counts */
       (select count(ed.enquiry_id) as totalFollowup
		from enquiry_details as ed, project_details as pd,followup_details fd,
		(
  		select MAX(fdd.`followup_id`) followup_id, fdd.`enquiry_id`
  		from `followup_details` as fdd
  		where fdd.`enquiry_id` IN (
      		select enquiry_id 
      		from enquiry_details 
      		where is_remove = 0 and followup_status = 1 
      		and STR_TO_DATE(followup_date, '%d/%m/%Y')<=CURDATE()
  		)
  		Group By fdd.`enquiry_id`
		)  as dummy
		where ed.`enquiry_id`  = fd.enquiry_id 
		and ed.project_id  = pd.project_tran_id 
		and fd.followup_id   = dummy.followup_id
		and fd.enquiry_id    = dummy.`enquiry_id`
		and pd.is_remove = 0
		and pd.is_approved = 1 
		and ed.is_remove = 0
		and ed.followup_status = 1  
		and Case When projectId != 0
        		  Then pd.project_tran_id = projectId else pd.project_status = projectStatus  End
		and (Case When userType = 2 
            Then pd.company_id = companyId Else FIND_IN_SET(userId, pd.subuser_id ) End)   
       ) as tbl10;   
     
    
END;

#
# Definition for the `proc_getAllDashboardDetails` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getAllDashboardDetails`(IN companyId INTEGER(11), IN userType INTEGER(11), IN userId INTEGER(11), IN projectStatus INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
	SELECT 
    IFNULL(p.project_tran_id, 0) projectId,p.project_name as projectName,
	IFNULL(countFlatsByProject.totalUnits, 0) totalUnits,
	IFNULL(bookedProperty.totalClient, 0) totalBooked,
	IFNULL(countFlatsByProject.totalUnits-bookedProperty.totalClient, 0) totalUnbooked,
	IFNULL(enquiryDetails.totalEnquiry, 0) totalEnquiry,
	IFNULL(closedEnquiryDetails.totalClosed, 0) closedEnquiry,
	IFNULL(TRUNCATE(((enquiryDetails.totalEnquiry-IFNULL(closedEnquiryDetails.totalClosed, 0))/enquiryDetails.totalEnquiry)*100,2), 0) response,
	IFNULL(projectCompletionStatus.projectCompletion, 0) projectCompletion
	FROM  project_details p
	LEFT JOIN(
		SELECT SUM(w.total_flats) as totalUnits,p.project_tran_id as projectId
    	FROM wing as w,project_details p
		WHERE w.project_id = p.project_tran_id
		AND w.is_remove = 0
		AND p.is_approved = 1 
        AND p.project_status = projectStatus
		GROUP BY p.project_tran_id
	) countFlatsByProject on countFlatsByProject.projectId = p.project_tran_id
	LEFT JOIN
	(
		SELECT COUNT(client_id) as totalClient,p.project_tran_id as projectId
		FROM project_details p, client cl
		WHERE p.project_tran_id = cl.project_id
		AND p.is_remove = 0
		AND p.is_approved = 1 
        AND  cl.booking_status != 3
        AND p.project_status = projectStatus
		GROUP BY p.project_tran_id
	) bookedProperty ON bookedProperty.projectId = p.project_tran_id
	LEFT JOIN
	(
		SELECT COUNT(ed.enquiry_id) as totalEnquiry,ed.project_id as projectId
		FROM project_details p, enquiry_details ed
	    WHERE p.is_remove = 0
		AND p.project_tran_id = ed.project_id
		AND ed.is_remove    = 0
        AND p.is_approved = 1
        AND p.project_status = projectStatus
        GROUP BY p.project_tran_id
	) enquiryDetails ON enquiryDetails.projectId = p.project_tran_id
	LEFT JOIN
	(
		SELECT COUNT(ce.closed_enquiry_id) as totalClosed,ed.project_id as projectId
		FROM project_details p, enquiry_details ed, close_enquiry ce
		WHERE p.is_remove = 0 and ed.is_remove = 0
		AND p.project_tran_id = ed.project_id
		AND ed.enquiry_id = ce.enquiry_id
		AND p.is_approved = 1
        AND p.project_status = projectStatus
		GROUP BY p.project_tran_id
	) closedEnquiryDetails ON closedEnquiryDetails.projectId = p.project_tran_id
	LEFT JOIN
    (
		SELECT SUM(pd.disbursement_percentage) as projectCompletion,pd.project_id as projectId
		FROM project_disbursement as pd,project_details as p
		WHERE pd.project_id = p.project_tran_id
		AND pd.completion_date IS NOT NULL
		AND p.is_remove = 0
		AND p.is_approved = 1  
        AND p.project_status = projectStatus
		GROUP BY p.project_tran_id
	)projectCompletionStatus on projectCompletionStatus.projectId = p.project_tran_id
	WHERE (Case When userType = 2 
          Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)
	AND p.is_remove = 0
	AND p.is_approved = 1
    AND p.project_status = projectStatus
	GROUP BY p.project_tran_id;
END;

#
# Definition for the `proc_getAllFollowupDetails` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getAllFollowupDetails`(IN `userId` INTEGER(11), IN `userType` INTEGER(11), IN `companyId` INTEGER(11), IN projectId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
select ed.enquiry_id as enquiryId, ed.first_name as firstName, ed.last_name as lastName, 
	ed.mobile_no as mobileNo, ed.email as email,ed.landline_no as landlineNo, ed.city as city, 
	ed.address as address, ed.occupation as occupation, ed.company as company, 
	ed.budget_min as budgetMin, ed.budget_max as budgetMax, pd.project_tran_id as projectTranId, pd.project_name as projectName,
	ed.property_area as propertyArea, r.reference_type as referenceDescr, 
	pt.property_type_descr as propertyTypeDescr, p.property_name as propertyName,
	fd.followup_descr as followupDescr, 
	ed.followup_date as followupDate,ed.updated_datetime as updatedDatetime
	from enquiry_details as ed, project_details as pd, reference as r, 
	property_type as pt, property as p ,followup_details fd,
	(
  		select MAX(fdd.`followup_id`) followup_id, fdd.`enquiry_id`
  		from `followup_details` as fdd
  		where fdd.`enquiry_id` IN (
      		select enquiry_id 
      		from enquiry_details 
      		where is_remove = 0 and followup_status = 1 
      		and STR_TO_DATE(followup_date, '%d/%m/%Y')<=CURDATE()
  		)
  		Group By fdd.`enquiry_id`
	)  as dummy
	where ed.`enquiry_id`  = fd.enquiry_id 
	and ed.project_id  = pd.project_tran_id
  	and (Case When userType = 2
            Then pd.company_id = companyId  Else FIND_IN_SET(userId, pd.subuser_id )  End) 
	and Case When projectId != 0 Then pd.project_tran_id = projectId else true end  
	and ed.property_type = pt.property_type_id 
	and ed.property      = p.property_id 
	and ed.reference     = r.reference_id 
	and fd.followup_id   = dummy.followup_id
	and fd.enquiry_id    = dummy.`enquiry_id`
    and pd.is_remove = 0
	and pd.is_approved = 1
    and pd.project_status != 3
    order by STR_TO_DATE(ed.followup_date, '%d/%m/%Y') desc;

#
# Definition for the `proc_getAllFollwup_NotificationCount` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getAllFollwup_NotificationCount`(
        IN `userId` INTEGER(11),
        IN `userType` INTEGER(11)
    )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

	SELECT count(enquiryId) followupNotification_count
 	FROM ( 
		select ed.enquiry_id as enquiryId, ed.first_name as firstName, ed.last_name as lastName, 
		ed.mobile_no as mobileNo, ed.email as email,ed.landline_no as landlineNo, ed.city as city, 
		ed.address as address, ed.occupation as occupation, ed.company as company, 
		ed.budget_min as budgetMin, ed.budget_max as budgetMax, pd.project_name as projectName, 
		ed.property_area as propertyArea, r.reference_type as referenceDescr, 
		pt.property_type_descr as propertyTypeDescr, p.property_name as propertyName,
		fd.followup_descr as followupDescr, fd.enquiry_id as enquiryTransId, ed.followup_date as followupDate
		from enquiry_details as ed, project_details as pd, reference as r, 
		property_type as pt, property as p ,followup_details fd,
		(
		  select MAX(fdd.`followup_id`) followup_id, fdd.`enquiry_id`
		  from `followup_details` as fdd
		  where fdd.`enquiry_id` IN (
		      select enquiry_id 
		      from enquiry_details 
		      where is_remove = 0 and followup_status = 1 
		      and STR_TO_DATE(followup_date, '%d/%m/%Y')<=CURDATE()
		  )
		  Group By fdd.`enquiry_id`
		)  as dummy
		where ed.`enquiry_id`  = fd.enquiry_id 
		and ed.project_id  = pd.project_tran_id 
		and (Case When userType = 2
            Then pd.user_id = userId  Else FIND_IN_SET(userId, pd.subuser_id )  End) 
		and ed.property_type = pt.property_type_id 
		and ed.property      = p.property_id 
		and ed.reference     = r.reference_id 
		and fd.followup_id   = dummy.followup_id
		and fd.enquiry_id    = dummy.`enquiry_id`)followupNotification;
        
END;

#
# Definition for the `proc_getBookedFlatDetails` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getBookedFlatDetails`(
              IN `wingId` INTEGER(11),
              IN `floorName` VARCHAR(50),
              IN `floorNumber` INTEGER(11),
              IN `flatNumber` INTEGER(11)
      )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
select c.owner_name as ownerName, pd.project_name as projectName, ed.mobile_no as mobileNo, ed.email as email, 
ed.landline_no as landlineNo, ed.city as city, ed.address as address, ed.occupation as occupation, ed.company as company,
pt.property_type_descr as propertyTypeDescr, p.property_name as propertyName, w.wing_name as wingName, c.property_area 
as propertyArea, c.client_tran_id as clientTranId, c.flat_number as flatNumber, c.agreement_amount as agreementAmount,   
c.gst_percentage as gstPercentage, c.date_of_birth as dateOfBirth, c.pan_number as panNumber, c.aadhar_number as aadharNumber, 
c.booking_status as bookingStatus, pd.project_tran_id as projectId, c.rate as rate, c.infrastructure_amount as infrastructureAmount, 
c.booking_date as bookingDate, c.registration_no as registrationNo, c.registration_date as registrationDate, op.cheque_number as 
chequeNo, op.cheque_date as chequeDateString, IFNULL(op.amount, 0.0) as bookingAmount, c.floor_name as floorName 
from wing as w, enquiry_details as ed, property_type as pt, property as p, project_details as pd, client as c 
LEFT JOIN other_payments as op ON op.client_id = c.client_tran_id 
where c.wing_id             = w.wing_tran_id 
and ed.enquiry_id         = c.enquiry_id 
and c.project_id           = pd.project_tran_id 
and c.property_type_id = pt.property_type_id 
and c.property_id         = p.property_id and c.booking_status != 3
and c.wing_id = wingId and c.flat_number = flatNumber and c.floor_number =floorNumber and c.floor_name = floorName
ORDER BY op.other_payment_id ASC LIMIT 1;
END;

#
# Definition for the `proc_getClientDetailsById` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getClientDetailsById`(
              IN `clientId` INTEGER(11)
      )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

select c.owner_name as ownerName, ed.mobile_no as mobileNo, ed.email as email, c.flat_number as flatNumber,
ed.landline_no as landlineNo, ed.city as city, ed.address as address, ed.occupation as occupation, ed.company as company,
ed.budget_max as budgetMax, pd.project_tran_id as projectId, pd.project_name as projectName, c.property_area as propertyArea, 
r.reference_type as reference, pt.property_type_descr as propertyTypeDescr, c.floor_number as floorNumber, 
p.property_name as propertyName, ed.reference_name as referenceName, c.client_tran_id as clientTranId, c.gst_percentage 
as gstPercentage, c.agreement_amount as agreementAmount, temp.enqPropertyTypeName, temp.enqPropertyName, temp.enqPropertyArea,
w.wing_name as wingName, c.booking_date as bookingDate, c.registration_date as registrationDate, c.booking_status as bookingStatus,
c.date_of_birth as dateOfBirth, c.pan_number as panNumber, c.aadhar_number as aadharNumber, c.registration_no as registrationNo,
c.rate as rate, c.infrastructure_amount as infrastructureAmount, c.remark as remark, c.floor_name as floorName 
from enquiry_details as ed, project_details as pd, reference as r, property_type as pt, property as p, client as c, wing as w,
(select pt.`property_type_descr` as enqPropertyTypeName, p.`property_name` as enqPropertyName, ed.`property_area` as enqPropertyArea 
from `enquiry_details` as ed, `property_type` as pt, `property` as p,`client` as c where ed.`enquiry_id`   = c.`enquiry_id` 
and ed.`property_type` = pt.`property_type_id` and ed.`property` = p.`property_id` and c.`client_tran_id` = clientId) as temp 
where c.project_id         = pd.project_tran_id 
and c.property_type_id = pt.property_type_id 
and c.property_id         = p.property_id 
and ed.reference           = r.reference_id
and ed.enquiry_id         = c.enquiry_id 
and w.wing_tran_id       = c.wing_id 
and w.project_id           = pd.project_tran_id 
and c.client_tran_id   = clientId ;
END;

#
# Definition for the `proc_getClientDisbursementDetailsByClientId` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getClientDisbursementDetailsByClientId`(IN `clientId` INTEGER(11), IN disbursementId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN       
	                  
SELECT c.agreement_amount as agreementAmount ,sum(d.percentage_value) as totalPercentage,
           SUM(case when d.disbursement_id != disbursementId
           		    Then d.remaining_amount Else 0 END) AS prevRemainingAmount,
   		   Sum(d.paid_amount) as totalPaidAmount ,dummy.disbursement_amount as outStandingAmount,
    	   dummy.gst_amount as gstAmount, dummy.disbursement_title as disbursementTitle,
   		   c.floor_number as floorNumber, c.flat_number as flatNumber, c.floor_name as floorName,
           c.gst_percentage as gstPercentage, c.owner_name as ownerName, e.mobile_no as mobileNo, 
           e.email as email, e.address as address, e.city as city, w.wing_name as wingName, 
		   p.project_name as projectName, p.address as projectAddress, b.account_name as accountName, 
           b.account_number as accountNumber, b.ifsc_code as ifscCode, b.bank_name as bankName, 	
    	   b.branch_name as branchName, p.letter_head as letterHeadName,
           cp.company_name as companyName, cp.logo_path as companyLogo,
           cp.address as companyAddress           
	FROM project_disbursement  pd, client c, disbursement d, enquiry_details e, project_details p,
    	 wing w, bank_details b, company_profile cp, 
    	( SELECT d.`disbursement_amount`, d.`gst_amount`, pd.`disbursement_title`,
              d.`client_id` , d.remaining_amount, d.prev_remaining_amount 
     		 FROM  `project_disbursement` pd, `disbursement` d 
     		 WHERE pd.`project_disbursement_id` = d.`project_disbursement_id`
     	     AND d.`disbursement_id` = disbursementId
     	) as dummy 
	WHERE pd.project_disbursement_id = d.project_disbursement_id 
    AND p.company_id = cp.company_id
	AND c.client_tran_id = d.client_id      	
	AND c.enquiry_id = e.enquiry_id
	AND c.project_id = p.project_tran_id
    AND dummy.client_id =  c.client_tran_id
	AND c.wing_id = w.wing_tran_id
    AND pd.completion_date IS NOT NULL 
    AND b.project_id = p.project_tran_id
    AND b.bank_type = 2
	AND d.client_id = clientId 
	GROUP BY c.client_tran_id ;
END;

#
# Definition for the `proc_getDashboardDetailsByProjectId` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getDashboardDetailsByProjectId`(IN projectId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN   		
		select tbl1.yr AS year, CAST(tbl1.mon AS CHAR(100)) as month,  tbl1.totalVisit, tbl2.totalClosedVisits, tbl3.totalClient
		FROM 
		( select extract(year from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) as yr,
	   			 MONTHNAME(STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) as mon,
       			 count(ed.enquiry_id) as totalVisit
		 FROM project_details p, enquiry_details ed 
		 WHERE p.is_remove = 0 
		 AND p.project_tran_id = ed.project_id 
		 AND ed.is_remove  = 0
		 AND p.is_approved = 1	AND p.project_tran_id = projectId
		 AND STR_TO_DATE(ed.created_datetime, '%d/%m/%Y') >=  DATE_SUB(CURRENT_DATE(), INTERVAL 6 MONTH) 
		 GROUP BY  extract(month from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y'))
         
         ) as tbl1
	LEFT JOIN
		( select extract(year from STR_TO_DATE(ce.created_datetime, '%d/%m/%Y')) as yr,
	   	 MONTHNAME(STR_TO_DATE(ce.created_datetime, '%d/%m/%Y')) as mon,
       	 count(ce.enquiry_id) as totalClosedVisits
		 FROM project_details p, enquiry_details ed, close_enquiry ce
		 WHERE p.is_remove = 0 
		 AND p.project_tran_id = ed.project_id 
		 AND ed.enquiry_id = ce.enquiry_id
		 AND ed.is_remove  = 0
		 AND p.is_approved = 1
		 AND p.project_tran_id = projectId
		 AND STR_TO_DATE(ce.created_datetime, '%d/%m/%Y') >=  DATE_SUB(CURRENT_DATE(), INTERVAL 6 MONTH) 
		 GROUP BY /* extract(year from STR_TO_DATE(ce.created_datetime, '%d/%m/%Y')), */
		 extract(month from STR_TO_DATE(ce.created_datetime, '%d/%m/%Y')) ) as tbl2
		 ON tbl1.yr = tbl2.yr and tbl1.mon = tbl2.mon
	LEFT JOIN
		( SELECT  extract(year from STR_TO_DATE(cl.created_datetime, '%d/%m/%Y')) as yr,
	     MONTHNAME(STR_TO_DATE(cl.created_datetime, '%d/%m/%Y')) as mon,
         COUNT(cl.client_tran_id) as totalClient
 		 FROM project_details p, client cl
		 WHERE p.project_tran_id = cl.project_id
         AND p.is_remove = 0 
         AND p.is_approved = 1
         AND p.project_tran_id = projectId
         AND STR_TO_DATE(cl.created_datetime, '%d/%m/%Y') >=  DATE_SUB(CURRENT_DATE(), INTERVAL 6 MONTH) 
		GROUP BY /* extract(year from STR_TO_DATE(cl.created_datetime, '%d/%m/%Y')), */	
        		 extract(month from STR_TO_DATE(cl.created_datetime, '%d/%m/%Y'))) as tbl3
		ON tbl1.yr = tbl3.yr and tbl1.mon = tbl3.mon ;

END;

#
# Definition for the `proc_getDisbursmentListByClientId` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getDisbursmentListByClientId`(IN clientId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

	DECLARE intValue INTEGER;  
    SET  intValue = 0 ; 
          select * from (
      		 select d.disbursement_id as disbursementId, cl.client_tran_id as clientId, pd.disbursement_title as disbursementTitle,
			 d.disbursement_description as disbursementDescription, d.percentage_value as percentageValue, 
			 d.disbursement_amount as disbursementAmount, d.paid_date as paidDate, pd.completion_date as completionDate, 
			 d.gst_amount as gstAmount, d.send_pdf_date as sendPdfDate, d.demand_letter_pdf_path as demandLetterPdfPath, 
			 d.total_amount as totalAmount, d.disbursement_followup_date as disbursementFollowupDate, 
			 d.remaining_amount as remainingAmount, d.paid_amount as paidAmount, intValue as partialPaymentId,
             d.prev_remaining_amount as prevRemainingAmount, 0.0 as partialGrossAmount, 0.0 partialGstAmount
			 from disbursement d, project_disbursement as pd, client as cl            
			 where pd.project_disbursement_id = d.project_disbursement_id 
			 and d.client_id = cl.client_tran_id and cl.client_tran_id = clientId 
			 UNION 
			 select dpp.disbursement_id as disbursementId, intValue as clientId, null as disbursementTitle,
			 dpp.partial_payment_description as disbursementDescription, 0.0 as percentageValue,
			 0.0 as disbursementAmount, dpp.partial_payment_paid_date as paidDate, null as completionDate, 
			 0.0 as gstAmount, null as sendPdfDate, null as demandLetterPdfPath, 0.0 as totalAmount, 
			 null as disbursementFollowupDate, 0.0 as remainingAmount,
              dpp.total_partial_amount as paidAmount, dpp.partial_payment_id as partialPaymentId, 0.0 as prevRemainingAmount,
              dpp.partial_gross_amount as partialGrossAmount, dpp.partial_gst_amount as partialGstAmount
			 from disbursement d, disbursement_partial_payments dpp, client as cl
			 where d.disbursement_id = dpp.disbursement_id 
             and d.client_id = cl.client_tran_id 
             and cl.client_tran_id = clientId  
          ) as subtbl
          order by subtbl.disbursementId ASC, subtbl.clientId desc, subtbl.partialPaymentId asc ;
          

END;

#
# Definition for the `proc_getFollowupCountDetailsByProject` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getFollowupCountDetailsByProject`(IN userId INTEGER(11), IN userType INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

select pd.project_tran_id as projectTranId , pd.project_name as projectName, pd.project_status as projectStatus ,
	SUM(case 
		When pd.project_tran_id = ed.project_id 
        	 and STR_TO_DATE(ed.followup_date, '%d/%m/%Y') <= CURDATE()
             and ed.is_remove = 0	
             and ed.followup_status = 1 
             and ed.enquiry_id = fdd.enquiry_id
		Then 1 Else 0 End) as totalCount, dummy.todaysFollowupCount as todaysFollowupCount
	from enquiry_details ed, project_details pd , followup_details as fdd,
    (  select pd.project_tran_id, 
 		SUM(case 
			When fdd.`followup_id` NOT IN( SELECT Max(fdd.`followup_id`)as followup_id 
 								  FROM followup_details as fdd 
 								  Group By fdd.`enquiry_id`)
  				 and ed.`is_remove` = 0 and ed.`followup_status` = 1 
 			 	 and ed.`enquiry_id` = fdd.`enquiry_id`
  				 and pd.`project_tran_id` = ed.`project_id` 
 				 and STR_TO_DATE(ed.`updated_datetime`, '%d/%m/%Y') = CURDATE()
       		 Then 1 Else 0 End) as todaysFollowupCount
 		from `followup_details` as fdd, `enquiry_details` ed, `project_details` pd 
 		Group By pd.`project_id` ) as dummy
	where pd.is_remove = 0 
    and dummy.project_tran_id = pd.project_tran_id 
    and (Case When userType = 2
            Then pd.user_id = userId  Else FIND_IN_SET(userId, pd.subuser_id )  End) 
	group by pd.project_tran_id ; 
   
END;

#
# Definition for the `proc_getFollowupDetailsByProjectId` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getFollowupDetailsByProjectId`(
        IN `projectId` INTEGER(11)
    )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
select ed.enquiry_id as enquiryId, ed.first_name as firstName, ed.last_name as lastName, 
ed.mobile_no as mobileNo, pd.project_name as projectName, p.property_name as propertyName,
fd.followup_descr as followupDescr, /*fd.enquiry_id as enquiryTransId,*/ ed.followup_date as followupDate, '0' as followupFlag
from enquiry_details as ed, project_details as pd, reference as r, 
property_type as pt, property as p ,followup_details fd,
(
  select MAX(fdd.`followup_id`) followup_id, fdd.`enquiry_id`
  from `followup_details` as fdd
  where fdd.`enquiry_id` IN (
      select enquiry_id 
      from enquiry_details 
      where is_remove = 0 and followup_status = 1 
      and STR_TO_DATE(followup_date, '%d/%m/%Y')<=CURDATE()
  )
  Group By fdd.`enquiry_id`
)  as dummy
where ed.`enquiry_id`  = fd.enquiry_id 
and ed.project_id  = pd.project_tran_id 
and ed.property_type = pt.property_type_id 
and ed.property      = p.property_id 
and ed.reference     = r.reference_id 
and fd.followup_id   = dummy.followup_id
and fd.enquiry_id    = dummy.`enquiry_id`
and ed.project_id    = projectId

UNION

select ed.enquiry_id as enquiryId, ed.first_name as firstName, ed.last_name as lastName, 
ed.mobile_no as mobileNo, pd.project_name as projectName, p.property_name as propertyName,
fd.followup_descr as followupDescr, /* fd.enquiry_id as enquiryTransId,*/ ed.followup_date as followupDate, '1' as followupFlag
from enquiry_details as ed, project_details as pd, reference as r, 
property_type as pt, property as p ,followup_details fd,
(
  select MAX(fdd.`followup_id`) followup_id, fdd.`enquiry_id`
  from `followup_details` as fdd
  where fdd.`followup_id` NOT IN( SELECT Max(fdd.`followup_id`)as followup_id 
  								  FROM followup_details as fdd 
								  Group By fdd.`enquiry_id`)
  and fdd.`enquiry_id` IN (  select enquiry_id 
      						 from enquiry_details 
      						 where is_remove = 0 and followup_status = 1 
      						 and STR_TO_DATE(updated_datetime, '%d/%m/%Y')=CURDATE()
						  )
  Group By fdd.`enquiry_id`
)  as dummy
where ed.`enquiry_id`  = fd.enquiry_id 
and ed.project_id  = pd.project_tran_id 
and ed.property_type = pt.property_type_id 
and ed.property      = p.property_id 
and ed.reference     = r.reference_id 
and fd.followup_id   = dummy.followup_id
and fd.enquiry_id    = dummy.`enquiry_id`
and ed.project_id    = projectId;
END;

#
# Definition for the `proc_getMonthwiseProjectDashboardDetails` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getMonthwiseProjectDashboardDetails`(IN projectId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

        SELECT IFNULL(tblTotalUnits.totalUnits,0) AS totalUnits, 
        CONCAT(calendar.monthname,'-',calendar.year) as monthName, IFNULL(enqTbl.totalEnquiry, 0) AS totalEnquiry,
        IFNULL(clientTbl.totalBooked, 0) AS totalBooked, IFNULL(closedEnqTbl.totalClosed, 0) AS closedEnquiry,
        IFNULL(TRUNCATE(((enqTbl.totalEnquiry-IFNULL(closedEnqTbl.totalClosed, 0))/enqTbl.totalEnquiry)*100,2), 0) response    
		FROM 
    	( 
    		SELECT SUM(w.total_flats) as totalUnits
    		FROM wing as w
			WHERE w.project_id = projectId
			AND w.is_remove = 0
			GROUP BY w.project_id
    	) as  tblTotalUnits ,
    	calendar_tbl as calendar LEFT JOIN
		(	SELECT  count(ed.enquiry_id) as totalEnquiry,
				extract(month from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) AS mon,
				extract(year from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) as year
			FROM enquiry_details ed 
			WHERE ed.project_id = projectId
			AND  ed.is_remove = 0
			GROUP by extract(year from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')),
					 extract(month from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) 
        ) AS enqTbl
		ON enqTbl.mon = calendar.month
		AND enqTbl.year = calendar.year        
        LEFT JOIN 
        ( 	SELECT COUNT(cl.client_id) as totalBooked ,
                   extract(month from STR_TO_DATE(cl.created_datetime, '%d/%m/%Y')) AS mon,
				   extract(year from STR_TO_DATE(cl.created_datetime, '%d/%m/%Y')) as year
			FROM  client cl
			WHERE cl.project_id  = projectId
			AND  cl.booking_status != 3
			GROUP BY extract(year from STR_TO_DATE(cl.created_datetime, '%d/%m/%Y')),
            		 extract(month from STR_TO_DATE(cl.created_datetime, '%d/%m/%Y'))
        ) as clientTbl
        ON clientTbl.mon = calendar.month
		AND clientTbl.year = calendar.year 
        LEFT JOIN
		(	SELECT COUNT(ce.closed_enquiry_id) as totalClosed,
        		   extract(month from STR_TO_DATE(ce.created_datetime, '%d/%m/%Y')) AS mon,
				   extract(year from STR_TO_DATE(ce.created_datetime, '%d/%m/%Y')) as year
			FROM enquiry_details ed, close_enquiry ce
			WHERE ed.is_remove = 0
        	AND ed.project_id = projectId
			AND ed.enquiry_id = ce.enquiry_id
			GROUP by extract(year from STR_TO_DATE(ce.created_datetime, '%d/%m/%Y')),
			extract(month from STR_TO_DATE(ce.created_datetime, '%d/%m/%Y'))
        ) AS closedEnqTbl
		ON closedEnqTbl.mon = calendar.month
		AND closedEnqTbl.year = calendar.year 
        WHERE  calendar.project_id = projectId
        ORDER BY calendar.year, calendar.month;
    
END;

#
# Definition for the `proc_getMonthwiseTotalEnquiries` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getMonthwiseTotalEnquiries`(IN userId INTEGER(11), IN userType INTEGER(11), IN companyId INTEGER(11), IN projectStatus INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

    select temp1.projectName,
	GROUP_CONCAT(CONCAT(temp1.monthname,'-',temp1.year)
	 	order by temp1.year,temp1.month) as monthName,
	GROUP_CONCAT(CAST( temp1.totalVisit AS CHAR) order by temp1.year,temp1.month) AS totalVisit
	FROM
   ( SELECT temp.project_name as projectName, temp.project_tran_id, 
	temp.month, temp.monthname, temp.year, count(ed.enquiry_id) as totalVisit		
	FROM
	(select c.month, c.monthname, c.year, p.project_name, p.project_tran_id 
	from cal as c, project_details p
	WHERE p.is_approved = 1 
    AND p.is_remove = 0
	AND p.project_status = projectStatus 
	AND (Case When userType = 2 
          	  Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)
	) as temp LEFT JOIN enquiry_details ed 
	ON temp.month = extract(month from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y')) 
    AND temp.year = extract(year from STR_TO_DATE(ed.created_datetime, '%d/%m/%Y'))
	AND temp.project_tran_id = ed.project_id
	group by temp.project_tran_id, temp.month ) AS temp1
	group by temp1.project_tran_id, temp1.projectName; 
    
END;

#
# Definition for the `proc_getPaymentDueDateNotifications` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getPaymentDueDateNotifications`(IN `userId` INTEGER(11), IN `userType` INTEGER(11), IN `paymentNotifDuration` INTEGER(11), IN `companyId` INTEGER(11), IN projectId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

 Select * from 
   (SELECT cl.client_tran_id as clientTranId , cl.owner_name as ownerName, 
  		   ed.mobile_no as mobileNo,  dummy.remaining_amount as remainingAmount ,         	
           p.project_tran_id as projectId, p.project_name as projectName, w.wing_tran_id as wingId, 
           cl.flat_number as flatNumber, cl.floor_number as floorNumber,w.wing_name as wingName,           
           dummy.disbursement_followup_date as dueDate, null as title, cl.floor_name as floorName
    FROM   client cl, enquiry_details ed, project_details p, wing w,        
           (select  subtbl.client_tran_id as clientId, subtbl.remaining_amount,				 
				    subtbl.disbursement_followup_date, subtbl.disbursement_id           
 		    from ( select d.remaining_amount, c.client_tran_id, d.disbursement_followup_date,
                   d.disbursement_id                
                   from `disbursement` d, `client` c  
          		   where c.`client_tran_id` = d.`client_id` 
          		   and d.`send_pdf_date` IS NOT NULL 
          		   and c.`is_paid` = 0 order by d.disbursement_id DESC ) as subtbl
  			group by subtbl.`client_tran_id`) as dummy 
    where cl.enquiry_id     = ed.enquiry_id 
    and p.project_tran_id = cl.project_id
    and p.is_approved = 1
    and cl.wing_id = w.wing_tran_id    
    and dummy.clientId = cl.client_tran_id 
    and cl.is_paid = 0
    and STR_TO_DATE(dummy.disbursement_followup_date , '%d/%m/%Y') <= DATE_ADD(CURRENT_DATE(), INTERVAL paymentNotifDuration DAY)   
    and Case When projectId != 0 Then p.project_tran_id = projectId else true END
    and (Case When userType = 2
            Then p.company_id = companyId  Else FIND_IN_SET(userId, p.subuser_id )  End) 
    and dummy.remaining_amount > 0 
    UNION 
    SELECT cl.client_tran_id as clientTranId , cl.owner_name as ownerName, 
  		   ed.mobile_no as mobileNo,  op.amount as remainingAmount ,         	
           p.project_tran_id as projectId, p.project_name as projectName, w.wing_tran_id as wingId, 
           cl.flat_number as flatNumber, cl.floor_number as floorNumber,w.wing_name as wingName,           
           op.due_date as dueDate, op.title as title, cl.floor_name as floorName
    FROM   client cl, enquiry_details ed, project_details p, wing w, other_payments op
    WHERE p.project_tran_id = cl.project_id
    and p.is_approved  = 1
    and cl.wing_id 	   = w.wing_tran_id 
    and cl.enquiry_id  = ed.enquiry_id 
    and op.client_id = cl.client_tran_id   
    and cl.booking_status != 3
    and STR_TO_DATE(op.due_date , '%d/%m/%Y') <= DATE_ADD(CURRENT_DATE(), INTERVAL paymentNotifDuration DAY)   
    and Case When projectId != 0 Then p.project_tran_id = projectId else true END
    and (Case When userType = 2
            Then p.company_id = companyId  Else FIND_IN_SET(userId, p.subuser_id )  End) 
    and op.amount > 0
    and (op.paid_date IS NULL OR op.paid_date = '')
    ) as dummytbl order by STR_TO_DATE(dummytbl.dueDate , '%d/%m/%Y') desc;     
    
END;

#
# Definition for the `proc_getPendingDemandLetterNotifications` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getPendingDemandLetterNotifications`(
              IN `companyId` INTEGER(11),
              IN `userId` INTEGER(11),
              IN `userType` INTEGER(11),
              IN `projectId` INTEGER(11)
      )
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

SELECT dummytbl.clientTranId, dummytbl.ownerName, dummytbl.mobileNo, dummytbl.projectName, dummytbl.projectId, 
dummytbl.wingName, dummytbl.wingId, dummytbl.floorNumber, dummytbl.flatNumber, dummytbl.floorName, dummytbl.sendPdfDate 
FROM (SELECT cl.client_tran_id as clientTranId, cl.owner_name as ownerName , pd.completion_date, 
ed.mobile_no as mobileNo, p.project_name as projectName, p.project_tran_id as projectId, cl.floor_name as floorName, 
w.wing_name as wingName,cl.wing_id as wingId, cl.floor_number as floorNumber, cl.flat_number as flatNumber, d.send_pdf_date as sendPdfDate 
FROM disbursement d, client cl, enquiry_details ed, wing w, project_details p, project_disbursement pd, 
(SELECT MAX(d.`disbursement_id`) as disbursementId FROM `project_disbursement` pd, `disbursement` d 
where pd.`project_disbursement_id` = d.`project_disbursement_id` and pd.completion_date Is NOT NULL 
group by d.`client_id` ) as tbl 
WHERE d.client_id = cl.client_tran_id 
AND p.project_tran_id = cl.project_id 
AND w.wing_tran_id = cl.wing_id 
AND ed.enquiry_id = cl.enquiry_id 
AND d.disbursement_id = tbl.disbursementId 
AND pd.project_disbursement_id = d.project_disbursement_id 
AND cl.is_paid = 0 and cl.booking_status != 3
AND (d.send_pdf_date IS NULL OR   STR_TO_DATE(d.send_pdf_date, '%d/%m/%Y') = CURRENT_DATE())
AND p.is_approved = 1
AND Case When projectId != 0 Then p.project_tran_id = projectId else true end 
AND (Case When userType = 2 Then p.company_id = companyId   Else FIND_IN_SET(userId,p.subuser_id)   End) ) as dummytbl
ORDER BY STR_TO_DATE(dummytbl.completion_date , '%d/%m/%Y') asc;

END;

#
# Definition for the `proc_getProjectStructureByProjectId` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getProjectStructureByProjectId`(IN projectId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN

 	SELECT GROUP_CONCAT(distinct ((CAST(f.flat_number AS CHAR))) order by f.flat_number) as flatNumbers,
		   GROUP_CONCAT( DISTINCT CASE WHEN c.booking_status = 2 THEN CAST(c.flat_number AS CHAR) ELSE 0 END 
           			    ) AS bookedFlatNumbers  , 
           GROUP_CONCAT( DISTINCT CASE WHEN c.booking_status = 1 THEN CAST(c.flat_number AS CHAR) ELSE 0 END )
           AS registeredFlatNumbers, f.wing_id as wingTranId, f.floor_number as floorNumber, 
           f.floor_name as floorName, w.wing_name as wingName 
	FROM wing as w, flats as f LEFT JOIN client as c
    ON  c.floor_number = f.floor_number and c.floor_name = f.floor_name
	AND c.wing_id = f.wing_id  AND c.booking_status != 3
	WHERE f.project_id = projectId   
    AND w.wing_tran_id = f.wing_id
	GROUP BY f.floor_name, f.wing_id
	ORDER BY f.wing_id asc, f.floor_number desc/*, f.floor_name desc*/ ;

END;

#
# Definition for the `proc_getReferencewiseDashboardDetails` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getReferencewiseDashboardDetails`(IN companyId INTEGER(11), IN userType INTEGER(11), IN userId INTEGER(11), IN projectStatus INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
       	
	 SELECT GROUP_CONCAT(Cast(refDetails.referenceType AS CHAR)) AS reference,
     GROUP_CONCAT(Cast(IFNULL(refDetails.totalEnquiry,0) AS CHAR)) AS totalVisit,
     GROUP_CONCAT(Cast(IFNULL(refDetails.totalClient,0) AS CHAR)) AS  totalClient
     FROM
       ( SELECT rf.reference_type as referenceType,  totalEnquiryTbl.totalEnquiry,
				totalClientTbl.totalClient
		 FROM reference as rf
		 LEFT JOIN
		 ( SELECT COUNT(ed.enquiry_id) as totalEnquiry, ed.reference
			FROM enquiry_details as ed , project_details as p
			WHERE p.is_remove = 0 
			AND ed.project_id = p.project_tran_id
			AND ed.is_remove  = 0
			AND p.is_approved = 1              
			AND p.project_status = projectStatus 
    		AND (Case When userType = 2 
            			Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)
			GROUP BY ed.reference ) as totalEnquiryTbl
	ON rf.reference_id = totalEnquiryTbl.reference 
	LEFT JOIN
 	( SELECT  ed.reference, COUNT(cl.enquiry_id) as totalClient
		FROM client as cl, enquiry_details as ed, project_details as p
		WHERE ed.is_remove = 0
		AND ed.project_id = p.project_tran_id
        AND cl.enquiry_id = ed.enquiry_id
		AND ed.followup_status = 2
		AND cl.booking_status != 3 
        AND p.is_approved = 1              
		AND p.project_status = projectStatus   
        AND p.is_remove = 0 
		AND (Case When userType = 2 
          		  Then p.company_id = companyId Else FIND_IN_SET(userId, p.subuser_id ) End)
		GROUP BY ed.reference ) as totalClientTbl
 	ON rf.reference_id = totalClientTbl.reference) AS refDetails ;
END;

#
# Definition for the `proc_getReferencewiseDashboardDetailsByProjectId` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_getReferencewiseDashboardDetailsByProjectId`(IN projectId INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN
 SELECT refDetailsTbl.reference,refDetailsTbl.totalVisit,refDetailsTbl.totalClient,
    areaTbl.areaCount,areaTbl.areaDetails
 	from (
     select GROUP_CONCAT(Cast(refDetails.referenceType AS CHAR)) AS reference,
     GROUP_CONCAT(Cast(IFNULL(refDetails.totalEnquiry,0) AS CHAR)) AS totalVisit,
     GROUP_CONCAT(Cast(IFNULL(refDetails.totalClient,0) AS CHAR)) AS  totalClient   
     FROM
       ( SELECT rf.reference_type as referenceType,  totalEnquiryTbl.totalEnquiry,
				totalClientTbl.totalClient
		 FROM reference as rf
		 LEFT JOIN
		 ( SELECT COUNT(ed.enquiry_id) as totalEnquiry, ed.reference
			FROM enquiry_details as ed 
			WHERE ed.is_remove  = 0
    		AND ed.project_id = projectId
			GROUP BY ed.reference ) as totalEnquiryTbl
		ON rf.reference_id = totalEnquiryTbl.reference 
		LEFT JOIN
 		( SELECT  ed.reference, COUNT(cl.enquiry_id) as totalClient
			FROM client as cl, enquiry_details as ed
			WHERE ed.is_remove = 0
        	AND cl.enquiry_id = ed.enquiry_id
			AND cl.booking_status != 3
			AND ed.project_id = projectId 
			GROUP BY ed.reference ) as totalClientTbl
 		ON rf.reference_id = totalClientTbl.reference) AS refDetails) as refDetailsTbl,
    
   (select 
    IFNULL(GROUP_CONCAT( Cast(tbl.totals AS CHAR)),0) AS areaCount,
    IFNULL(GROUP_CONCAT(Cast(tbl.property_area AS CHAR),' sq.ft'),0) AS areaDetails
   	FROM  (select COUNT( ed.enquiry_id) as totals,ed.property_area from enquiry_details ed
	where ed.project_id = projectId 
	and ed.is_remove = 0
	group by ed.property_area
	order by totals DESC limit 3) as tbl) as areaTbl ;
END;

#
# Definition for the `proc_paymentTotalAndTodaysFollowupDetails` procedure : 
#

CREATE DEFINER = 'root'@'localhost' PROCEDURE `proc_paymentTotalAndTodaysFollowupDetails`(IN `userId` INTEGER(11), IN `userType` INTEGER(11), IN `companyId` INTEGER(11), IN `projectId` INTEGER(11))
    NOT DETERMINISTIC
    CONTAINS SQL
    SQL SECURITY DEFINER
    COMMENT ''
BEGIN


SELECT cl.client_tran_id as clientTranId , cl.owner_name as ownerName, 
ed.mobile_no as mobileNo,  d.remaining_amount as remainingAmount ,         	
p.project_tran_id as projectId, p.project_name as projectName, w.wing_tran_id as wingId, 
cl.flat_number as flatNumber, cl.floor_number as floorNumber,w.wing_name as wingName,           
d.disbursement_followup_date as dueDate, d.disbursement_id as disbursementId, "0" as followupFlag
from client cl , project_details p,  disbursement d, enquiry_details ed, wing w
WHERE cl.client_tran_id = d.client_id 
AND p.project_tran_id = cl.project_id
AND ed.enquiry_id = cl.enquiry_id
AND w.project_id = p.project_tran_id
AND cl.is_paid = 0 AND p.is_remove = 0
AND p.is_approved = 1  
AND d.disbursement_id IN (select MAX(disbursement_id) as disbursement_id           
 						  from disbursement d
 						  where send_pdf_date IS NOT NULL
 						  AND remaining_amount > 0
 						  AND STR_TO_DATE(disbursement_followup_date , '%d/%m/%Y') <= CURRENT_DATE()
 						  group by client_id ) 
AND Case When projectId != 0 Then p.project_tran_id = projectId else true END
AND (Case When userType = 2
Then p.company_id = companyId  Else FIND_IN_SET(userId, p.subuser_id )  End)   
group by d.disbursement_id
UNION 
SELECT c.client_tran_id as clientTranId , c.owner_name as ownerName, 
ed.mobile_no as mobileNo,  d.remaining_amount as remainingAmount ,         	
p.project_tran_id as projectId, p.project_name as projectName, w.wing_tran_id as wingId, 
c.flat_number as flatNumber, c.floor_number as floorNumber,w.wing_name as wingName,           
d.disbursement_followup_date as dueDate, d.disbursement_id as disbursementId, 
"1" as followupFlag
FROM client c, project_details p, disbursement d, payment_followup pf, 
enquiry_details ed, wing w 
WHERE c.client_tran_id = d.client_id 
AND p.project_tran_id = c.project_id
AND ed.enquiry_id = c.enquiry_id
AND w.project_id = p.project_tran_id
AND pf.disbursement_id = d.disbursement_id
AND STR_TO_DATE(pf.updated_datetime , '%d/%m/%Y') = CURRENT_DATE()  
AND p.is_remove = 0 AND p.is_approved = 1 
AND Case When projectId != 0 Then p.project_tran_id = projectId else true END
    AND (Case When userType = 2
            Then p.company_id = companyId  Else FIND_IN_SET(userId, p.subuser_id )  End) 
GROUP BY d.disbursement_id;

END;

#
# Definition for the `monthly_calendar_tbl_for_dashboard` Event : 
#

CREATE EVENT `monthly_calendar_tbl_for_dashboard`
  ON SCHEDULE EVERY 1 MONTH STARTS '2018-11-01 00:00:01'
  ON COMPLETION NOT PRESERVE
  ENABLE
  COMMENT ''  DO
BEGIN
  
CREATE TABLE IF NOT EXISTS `cal` (
`month` INT(11) ,
`monthname` VARCHAR(50) ,
`year` INT(11)
) ;    

TRUNCATE TABLE cal;               

SET @counter := -1;
WHILE (DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL @counter MONTH))) < DATEDIFF(DATE(NOW()), DATE(DATE_SUB(NOW(),INTERVAL 5 MONTH)))) DO 
INSERT INTO cal SELECT DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter:=@counter + 1 MONTH),'%m'),
						DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter MONTH),'%b'),
						DATE_FORMAT(DATE_ADD(DATE_SUB(NOW(),INTERVAL 5 MONTH), INTERVAL @counter + 1 MONTH),'%Y');
END WHILE;
END;

#
# Definition for the `monthly_calendar_tbl_for_project_dashboard` Event : 
#

CREATE EVENT `monthly_calendar_tbl_for_project_dashboard`
  ON SCHEDULE EVERY 1 MONTH STARTS '2018-12-01 00:05:00'
  ON COMPLETION NOT PRESERVE
  ENABLE
  COMMENT ''  DO
BEGIN

DECLARE projectsCount INTEGER ;
DECLARE projectId INTEGER ;
DECLARE projectIdList VARCHAR(50);
DECLARE x INTEGER;
DECLARE record_exists  INTEGER;

SET x = 1; 

	  select count(project_tran_id), GROUP_CONCAT(project_tran_id) into projectsCount, projectIdList  
      from project_details where is_approved = 1 and project_status = 2;    
      
 	  while x <= projectsCount do 
      
      		SELECT substring_index( SUBSTRING_INDEX(projectIdList, ',', x),',', -1) INTO projectId; 
            
            SET x = x+1; 
            SET record_exists = EXISTS(SELECT `month` FROM `calendar_tbl` WHERE `month` = DATE_FORMAT(NOW(),'%m') 
            															 and `year` = DATE_FORMAT(NOW(),'%Y')
            															 and `project_id` = projectId); 
            IF(record_exists = 0) THEN
      		 INSERT INTO calendar_tbl(month, monthname, year, project_id) SELECT DATE_FORMAT(NOW(),'%m'),
						                DATE_FORMAT(NOW(),'%b'),
										DATE_FORMAT(NOW(),'%Y'),
           								projectId;
           end IF;  
      end while ;

END;

#
# Data for the `activity_log` table  (LIMIT 0,500)
#

INSERT INTO `activity_log` (`activity_id`, `user_id`, `project_tran_id`, `activity_description`, `activity_datetime`) VALUES 
  (1,2,0,'Paid Date Updated of disbursementId [19, 20, 21, 22]','06/07/2018 11:44:11 AM'),
  (2,2,2,'BankId 4 Updated','07/07/2018 09:43:54 AM'),
  (3,2,2,'BankId 4 Updated','07/07/2018 09:44:07 AM'),
  (4,2,8,'Project added','07/07/2018 12:09:07 PM'),
  (5,2,8,'Project Basic Details Updated','07/07/2018 12:09:37 PM'),
  (6,2,8,'Project Status Updated','07/07/2018 12:10:21 PM'),
  (7,2,8,'EnquiryId 12 added ','07/07/2018 12:14:18 PM'),
  (8,2,8,'Client Added of clientId 11','07/07/2018 12:15:10 PM'),
  (9,2,0,'Completion date updated of disbursementId 48','06/07/2018 04:56:08 PM'),
  (10,2,0,'Completion date updated of disbursementId 49','07/07/2018 01:09:07 PM'),
  (11,2,8,'Client Added of clientId 12','07/07/2018 01:13:34 PM'),
  (12,2,0,'Paid Date Updated of disbursementId [90]','07/07/2018 01:09:07 PM'),
  (13,2,0,'Profile Details Updated','07/07/2018 01:28:10 PM'),
  (14,2,0,'Profile Details Updated','07/07/2018 02:54:50 PM'),
  (15,2,0,'Profile Details Updated','07/07/2018 02:55:41 PM'),
  (16,2,0,'Paid Date Updated of disbursementId [29]','07/07/2018 04:31:31 PM'),
  (17,2,0,'Paid Date Updated of disbursementId [9, 10]','07/07/2018 04:39:07 PM'),
  (18,2,0,'Paid Date Updated of disbursementId [60, 61]','07/07/2018 04:45:51 PM'),
  (19,2,0,'Completion date updated of disbursementId 10','07/07/2018 04:52:37 PM'),
  (20,2,0,'Paid Date Updated of disbursementId [5]','07/07/2018 04:52:37 PM'),
  (21,2,0,'Completion date updated of disbursementId 11','07/07/2018 04:52:37 PM'),
  (22,2,0,'Paid Date Updated of disbursementId [6]','07/07/2018 04:52:37 PM'),
  (23,2,0,'Paid Date Updated of disbursementId [23, 24]','07/07/2018 04:56:00 PM'),
  (24,2,0,'Paid Date Updated of disbursementId [16, 17, 18]','07/07/2018 05:07:19 PM'),
  (25,2,3,'Project Disbursement Updated','09/07/2018 09:09:24 AM'),
  (26,2,4,'Project Disbursement Updated','09/07/2018 09:09:24 AM'),
  (27,2,0,'Completion date updated of disbursementId 26','09/07/2018 09:34:21 AM'),
  (28,0,0,'Settings of BuilderId[0] Updated','09/07/2018 01:16:47 PM'),
  (29,2,0,'Settings of BuilderId[2] added','09/07/2018 03:02:20 PM'),
  (30,2,0,'Settings of BuilderId[2] Updated','09/07/2018 03:08:00 PM'),
  (31,2,0,'Settings of BuilderId[2] Updated','09/07/2018 03:27:14 PM'),
  (32,2,0,'Settings of BuilderId[2] Updated','09/07/2018 04:33:27 PM'),
  (33,2,0,'Settings of BuilderId[2] Updated','10/07/2018 09:08:02 AM'),
  (34,8,0,'Settings of BuilderId[8] Updated','10/07/2018 11:03:12 AM'),
  (35,2,0,'Settings of BuilderId[2] Updated','10/07/2018 11:45:38 AM'),
  (36,2,0,'Settings of BuilderId[2] Updated','10/07/2018 11:46:36 AM'),
  (37,2,0,'Settings of BuilderId[2] Updated','11/07/2018 09:27:42 AM'),
  (38,2,0,'Settings of BuilderId[2] Updated','11/07/2018 09:29:06 AM'),
  (39,2,0,'Settings of BuilderId[2] Updated','11/07/2018 09:29:16 AM'),
  (40,2,0,'Password changed','11/07/2018 11:24:08 AM'),
  (41,2,0,'Profile Details Updated','11/07/2018 12:15:23 PM'),
  (42,2,0,'Profile Details Updated','11/07/2018 12:19:45 PM'),
  (43,2,0,'Profile Details Updated','11/07/2018 12:31:16 PM'),
  (44,2,2,'Project Basic Details Updated','12/07/2018 02:13:49 PM'),
  (45,2,2,'AmenityId 3 Updated','12/07/2018 02:14:29 PM'),
  (46,2,9,'Project added','12/07/2018 02:26:12 PM'),
  (47,2,9,'AmenityId 48 added','12/07/2018 02:28:03 PM'),
  (48,2,9,'AmenityId 43 Updated','12/07/2018 02:28:30 PM'),
  (49,2,9,'WingId 21 Added','12/07/2018 02:29:25 PM'),
  (50,2,9,'WingId 21 Updated','12/07/2018 02:30:20 PM'),
  (51,2,9,'WingId 22 Added','12/07/2018 02:30:50 PM'),
  (52,2,9,'WingId 22 Deleted','12/07/2018 02:30:53 PM'),
  (53,2,9,'AmenityId 49 added','12/07/2018 02:30:59 PM'),
  (54,2,9,'AmenityId 49 Deleted','12/07/2018 02:34:11 PM'),
  (55,2,9,'AmenityId 50 added','12/07/2018 02:35:20 PM'),
  (56,2,9,'AmenityId 50 Deleted','12/07/2018 02:35:26 PM'),
  (57,2,9,'AmenityId 51 added','12/07/2018 02:36:51 PM'),
  (58,2,9,'AmenityId 51 Deleted','12/07/2018 02:36:55 PM'),
  (59,2,9,'BankId 20 Updated','12/07/2018 02:37:09 PM'),
  (60,2,9,'BankId 21 added','12/07/2018 02:37:38 PM'),
  (61,2,9,'BankId 21 Deleted','12/07/2018 02:37:53 PM'),
  (62,2,9,'BankId 19 Updated','12/07/2018 02:39:27 PM'),
  (63,2,9,'BankId 19 Updated','12/07/2018 02:39:45 PM'),
  (64,2,9,'Project Disbursement Updated','12/07/2018 02:11:22 PM'),
  (65,2,0,'Completion date updated of disbursementId 80','12/07/2018 02:11:22 PM'),
  (66,2,9,'ProjectId allocated to Subusers 5,6','12/07/2018 02:53:17 PM'),
  (67,2,2,'AmenityId 5 Updated','12/07/2018 02:53:33 PM'),
  (68,5,9,'Project Status Updated','12/07/2018 02:55:03 PM'),
  (69,5,9,'EnquiryId 13 added ','12/07/2018 02:56:28 PM'),
  (70,2,0,'Settings of BuilderId[2] Updated','12/07/2018 03:06:08 PM'),
  (71,2,0,'Password changed','12/07/2018 03:32:00 PM'),
  (72,2,9,'Client Added of clientId 13','12/07/2018 03:33:13 PM'),
  (73,2,0,'Settings of BuilderId[2] Updated','12/07/2018 03:38:05 PM'),
  (74,2,0,'Settings of BuilderId[2] Updated','12/07/2018 03:38:21 PM'),
  (75,2,0,'Settings of BuilderId[2] Updated','12/07/2018 03:38:32 PM'),
  (76,5,0,'Paid Date Updated of disbursementId [110]','12/07/2018 03:31:05 PM'),
  (77,2,5,'EnquiryId 6 updated ','12/07/2018 04:09:27 PM'),
  (78,0,0,'Paid Date Updated of disbursementId [11]','12/07/2018 04:07:10 PM'),
  (79,2,9,'WingId 22 Added','12/07/2018 04:28:52 PM'),
  (80,2,9,'WingId 24 Added','12/07/2018 04:31:04 PM'),
  (81,2,9,'AmenityId 52 added','12/07/2018 04:31:30 PM'),
  (82,2,0,'Paid Date Updated of disbursementId [91]','12/07/2018 04:41:48 PM'),
  (83,2,0,'Paid Date Updated of disbursementId [46]','13/07/2018 10:33:59 AM'),
  (84,2,0,'Completion date updated of disbursementId 27','13/07/2018 10:33:59 AM'),
  (85,4,0,'Profile Details Updated','13/07/2018 10:37:24 AM'),
  (86,2,2,'EnquiryId 14 added ','13/07/2018 10:59:15 AM'),
  (87,2,2,'EnquiryId 15 added ','13/07/2018 11:24:08 AM'),
  (88,2,5,'EnquiryId 16 added ','13/07/2018 11:25:30 AM'),
  (89,2,5,'EnquiryId 6 updated ','13/07/2018 11:26:28 AM'),
  (90,2,8,'EnquiryId 17 added ','13/07/2018 11:51:52 AM'),
  (91,2,0,'Settings of BuilderId[2] Updated','14/07/2018 10:58:41 AM'),
  (92,2,0,'Settings of BuilderId[2] Updated','14/07/2018 11:02:14 AM'),
  (93,2,2,'Enquiry closed of enquiryId 15','14/07/2018 04:25:00 PM'),
  (94,2,2,'Enquiry closed of enquiryId 15','14/07/2018 04:27:05 PM'),
  (95,2,5,'EnquiryId 18 added ','14/07/2018 04:44:06 PM'),
  (96,2,5,'Enquiry closed of enquiryId 18','14/07/2018 04:44:18 PM'),
  (97,2,9,'EnquiryId 19 added ','14/07/2018 04:47:02 PM'),
  (98,2,9,'Enquiry closed of enquiryId 19','14/07/2018 04:47:07 PM'),
  (99,2,9,'EnquiryId 20 added ','14/07/2018 05:00:14 PM'),
  (100,2,9,'Enquiry closed of enquiryId 20','14/07/2018 05:00:18 PM'),
  (101,2,2,'EnquiryId 21 added ','14/07/2018 05:01:47 PM'),
  (102,2,2,'Enquiry closed of enquiryId 21','14/07/2018 05:01:53 PM'),
  (103,2,8,'EnquiryId 22 added ','14/07/2018 05:04:15 PM'),
  (104,2,8,'Enquiry closed of enquiryId 22','14/07/2018 05:04:31 PM'),
  (105,2,5,'Enquiry closed of enquiryId 16','16/07/2018 11:30:53 AM'),
  (106,2,5,'Enquiry closed of enquiryId 8','16/07/2018 11:31:17 AM'),
  (107,2,2,'Client Added of clientId 14','16/07/2018 11:40:56 AM'),
  (108,2,5,'EnquiryId 23 added ','16/07/2018 03:19:37 PM'),
  (109,2,2,'EnquiryId 24 added ','16/07/2018 03:21:48 PM'),
  (110,2,0,'Settings of BuilderId[2] Updated','16/07/2018 03:22:10 PM'),
  (111,2,0,'Settings of BuilderId[2] Updated','16/07/2018 03:22:19 PM'),
  (112,2,2,'EnquiryId 25 added ','16/07/2018 03:23:59 PM'),
  (113,2,2,'EnquiryId 26 added ','16/07/2018 03:26:46 PM'),
  (114,2,9,'EnquiryId 27 added ','16/07/2018 03:36:29 PM'),
  (115,2,2,'EnquiryId 28 added ','16/07/2018 03:41:38 PM'),
  (116,2,2,'Enquiry closed of enquiryId 28','16/07/2018 03:42:09 PM'),
  (117,2,9,'EnquiryId 29 added ','16/07/2018 03:49:11 PM'),
  (118,2,9,'Client Added of clientId 15','16/07/2018 03:49:38 PM'),
  (119,1,0,'Password changed','16/07/2018 04:22:45 PM'),
  (120,2,0,'Password changed','16/07/2018 04:35:13 PM'),
  (121,2,0,'Settings of BuilderId[2] Updated','16/07/2018 04:48:44 PM'),
  (122,2,0,'Password changed','17/07/2018 10:59:56 AM'),
  (123,2,0,'Password changed','17/07/2018 11:00:46 AM'),
  (124,2,9,'Client Added of clientId 16','18/07/2018 09:47:10 AM'),
  (125,2,9,'Client Added of clientId 17','18/07/2018 12:35:51 PM'),
  (126,2,0,'Paid Date Updated of disbursementId [155]','18/07/2018 12:18:25 PM'),
  (127,2,0,'Completion date updated of disbursementId 81','18/07/2018 12:18:25 PM'),
  (128,2,0,'Completion date updated of disbursementId 6','18/07/2018 03:04:17 PM'),
  (129,2,2,'Client Added of clientId 1','18/07/2018 03:09:36 PM'),
  (130,2,0,'Settings of BuilderId[2] Updated','18/07/2018 03:22:30 PM'),
  (131,2,2,'Client Added of clientId 2','18/07/2018 03:25:47 PM'),
  (132,2,5,'Client Added of clientId 3','18/07/2018 03:29:26 PM'),
  (133,2,5,'Client Added of clientId 4','18/07/2018 03:30:09 PM'),
  (134,2,5,'Client Added of clientId 5','18/07/2018 03:31:39 PM'),
  (135,2,0,'Completion date updated of disbursementId 21','18/07/2018 03:04:17 PM'),
  (136,2,8,'Client Added of clientId 6','18/07/2018 03:51:38 PM'),
  (137,2,9,'Client Added of clientId 7','18/07/2018 03:52:05 PM'),
  (138,2,0,'Completion date updated of disbursementId 80','18/07/2018 03:04:17 PM'),
  (139,2,0,'Completion date updated of disbursementId 48','18/07/2018 03:04:17 PM'),
  (140,2,0,'Completion date updated of disbursementId 7','18/07/2018 03:04:17 PM'),
  (141,2,0,'Paid Date Updated of disbursementId [7]','18/07/2018 03:04:17 PM'),
  (142,2,0,'Settings of BuilderId[2] Updated','18/07/2018 04:03:57 PM'),
  (143,2,0,'Settings of BuilderId[2] Updated','19/07/2018 08:54:50 AM'),
  (144,2,0,'Paid Date Updated of disbursementId [1]','19/07/2018 08:50:21 AM'),
  (145,2,0,'Paid Date Updated of disbursementId [45]','19/07/2018 08:50:21 AM'),
  (146,2,0,'Paid Date Updated of disbursementId [29]','19/07/2018 08:50:21 AM'),
  (147,2,0,'Paid Date Updated of disbursementId [13]','19/07/2018 08:50:21 AM'),
  (148,2,0,'Completion date updated of disbursementId 22','19/07/2018 08:50:21 AM'),
  (149,2,2,'Client Added of clientId 8','19/07/2018 10:09:36 AM'),
  (150,2,2,'Client Added of clientId 9','19/07/2018 10:18:46 AM'),
  (151,2,2,'Client Added of clientId 8','19/07/2018 10:29:11 AM'),
  (152,2,2,'Client Added of clientId 9','19/07/2018 10:29:52 AM'),
  (153,2,1,'ProjectId allocated to Subusers 5','19/07/2018 10:57:31 AM'),
  (154,2,5,'ProjectId allocated to Subusers 5','19/07/2018 10:57:50 AM'),
  (155,2,2,'ProjectId allocated to Subusers 5','19/07/2018 10:58:01 AM'),
  (156,2,0,'Paid Date Updated of disbursementId [102, 103]','19/07/2018 10:25:19 AM'),
  (157,2,0,'Completion date updated of disbursementId 8','19/07/2018 10:25:19 AM'),
  (158,2,0,'Completion date updated of disbursementId 23','19/07/2018 10:25:19 AM'),
  (159,2,0,'Paid Date Updated of disbursementId [30, 31]','19/07/2018 10:25:19 AM'),
  (160,2,9,'Client Added of clientId 10','19/07/2018 01:26:44 PM'),
  (161,2,2,'EnquiryId 30 added ','20/07/2018 10:11:33 AM'),
  (162,2,2,'Enquiry closed of enquiryId 25','20/07/2018 10:13:44 AM'),
  (163,2,6,'EnquiryId 31 added ','20/07/2018 04:39:15 PM'),
  (164,8,0,'Profile Details Updated','21/07/2018 09:06:57 AM'),
  (165,8,0,'Profile Details Updated','21/07/2018 09:07:34 AM'),
  (166,8,0,'Profile Details Updated','21/07/2018 09:08:30 AM'),
  (167,2,2,'EnquiryId 32 added ','21/07/2018 10:30:43 AM'),
  (168,2,2,'EnquiryId 33 added ','21/07/2018 10:31:15 AM'),
  (169,2,2,'Enquiry closed of enquiryId 33','21/07/2018 10:31:31 AM'),
  (170,5,10,'Project added','23/07/2018 09:21:28 AM'),
  (171,2,5,'Client Added of clientId 11','23/07/2018 12:08:11 PM'),
  (172,2,2,'Client Added of clientId 12','23/07/2018 12:16:53 PM'),
  (173,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (174,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (175,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (176,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (177,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (178,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (179,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (180,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (181,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (182,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 09:11:11 AM'),
  (183,2,0,'Paid Date Updated of disbursementId [104]','24/07/2018 11:46:00 AM'),
  (184,2,0,'Settings of BuilderId[2] Updated','24/07/2018 02:33:21 PM'),
  (185,10,0,'Profile Details Updated','24/07/2018 02:36:03 PM'),
  (186,3,0,'Profile Details Updated','24/07/2018 02:37:52 PM'),
  (187,0,0,'Settings of BuilderId[0] added','24/07/2018 02:38:29 PM'),
  (188,2,5,'Client Added of clientId 1','25/07/2018 09:59:38 AM'),
  (189,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 09:50:42 AM'),
  (190,2,5,'Client Added of clientId 1','25/07/2018 11:17:02 AM'),
  (191,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 01:12:58 PM'),
  (192,2,5,'Client Added of clientId 1','25/07/2018 01:35:01 PM'),
  (193,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 01:33:48 PM'),
  (194,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 01:33:48 PM'),
  (195,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 02:37:11 PM'),
  (196,2,5,'Client Added of clientId 1','25/07/2018 02:39:48 PM'),
  (197,2,5,'Client Added of clientId 2','25/07/2018 02:39:49 PM'),
  (198,2,5,'Client Added of clientId 1','25/07/2018 02:42:34 PM'),
  (199,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 02:38:55 PM'),
  (200,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 02:38:55 PM'),
  (201,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 02:38:55 PM'),
  (202,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','25/07/2018 02:38:55 PM'),
  (203,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','26/07/2018 01:28:29 PM'),
  (204,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','26/07/2018 01:35:58 PM'),
  (205,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','26/07/2018 02:35:01 PM'),
  (206,2,0,'Paid Date Updated of disbursementId [1, 2, 3]','26/07/2018 02:35:01 PM'),
  (207,2,5,'Client Added of clientId 1','26/07/2018 03:48:29 PM'),
  (208,2,0,'Paid Date Updated of disbursementId [1]','26/07/2018 03:53:53 PM'),
  (209,2,0,'Completion date updated of disbursementId 22','26/07/2018 03:53:53 PM'),
  (210,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 09:01:37 AM'),
  (211,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 09:01:37 AM'),
  (212,2,5,'Client Added of clientId 2','27/07/2018 09:10:25 AM'),
  (213,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 09:01:37 AM'),
  (214,2,6,'Client Added of clientId 3','27/07/2018 09:37:31 AM'),
  (215,2,0,'Completion date updated of disbursementId 37','27/07/2018 09:01:37 AM'),
  (216,2,0,'Paid Date Updated of disbursementId [33]','27/07/2018 09:38:25 AM'),
  (217,2,0,'Paid Date Updated of disbursementId [33]','27/07/2018 10:02:51 AM'),
  (218,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 10:02:51 AM'),
  (219,2,5,'Client Added of clientId 4','27/07/2018 10:41:04 AM'),
  (220,2,5,'Client Added of clientId 5','27/07/2018 10:41:05 AM'),
  (221,2,0,'Paid Date Updated of disbursementId [39, 40]','27/07/2018 10:39:24 AM'),
  (222,2,2,'Client Added of clientId 5','27/07/2018 10:45:10 AM'),
  (223,2,2,'Client Added of clientId 6','27/07/2018 10:45:10 AM'),
  (224,2,0,'Completion date updated of disbursementId 7','27/07/2018 10:39:24 AM'),
  (225,2,0,'Paid Date Updated of disbursementId [77, 78]','27/07/2018 11:11:48 AM'),
  (226,2,0,'Paid Date Updated of disbursementId [77, 78]','27/07/2018 11:11:48 AM'),
  (227,2,2,'Client Added of clientId 7','27/07/2018 11:18:30 AM'),
  (228,2,0,'Paid Date Updated of disbursementId [83, 84]','27/07/2018 11:11:48 AM'),
  (229,2,5,'Client Added of clientId 1','27/07/2018 11:51:47 AM'),
  (230,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 11:42:06 AM'),
  (231,2,5,'Client Added of clientId 1','27/07/2018 12:01:37 PM'),
  (232,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 12:00:46 PM'),
  (233,2,5,'Client Added of clientId 1','27/07/2018 12:10:53 PM'),
  (234,2,5,'Client Added of clientId 1','27/07/2018 12:19:53 PM'),
  (235,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 12:22:08 PM'),
  (236,2,5,'Client Added of clientId 2','27/07/2018 12:24:02 PM'),
  (237,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 12:22:08 PM'),
  (238,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 12:22:08 PM'),
  (239,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 12:22:08 PM'),
  (240,2,5,'Client Added of clientId 1','27/07/2018 02:08:22 PM'),
  (241,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 02:06:16 PM'),
  (242,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 02:06:16 PM'),
  (243,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 02:06:16 PM'),
  (244,2,5,'Client Added of clientId 1','27/07/2018 02:24:43 PM'),
  (245,2,0,'Paid Date Updated of disbursementId [1, 2]','27/07/2018 02:23:39 PM'),
  (246,2,5,'Client Added of clientId 2','27/07/2018 02:26:16 PM'),
  (247,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 02:23:39 PM'),
  (248,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 02:23:39 PM'),
  (249,2,0,'Paid Date Updated of disbursementId [17, 18]','27/07/2018 02:23:39 PM'),
  (250,2,5,'Client Added of clientId 3','27/07/2018 02:52:00 PM'),
  (251,2,0,'Paid Date Updated of disbursementId null','27/07/2018 02:50:20 PM'),
  (252,2,0,'Paid Date Updated of disbursementId null','27/07/2018 02:50:20 PM'),
  (253,2,0,'Paid Date Updated of disbursementId null','27/07/2018 02:50:20 PM'),
  (254,2,0,'Paid Date Updated of disbursementId null','27/07/2018 02:50:20 PM'),
  (255,2,0,'Completion date updated of disbursementId 23','27/07/2018 02:50:20 PM'),
  (256,2,0,'Paid Date Updated of disbursementId null','27/07/2018 02:50:20 PM'),
  (257,2,5,'Client Added of clientId 1','27/07/2018 03:15:06 PM'),
  (258,2,5,'Client Added of clientId 2','27/07/2018 03:15:34 PM'),
  (259,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (260,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (261,2,0,'Completion date updated of disbursementId 22','27/07/2018 03:12:17 PM'),
  (262,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (263,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (264,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (265,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (266,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (267,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (268,2,0,'Completion date updated of disbursementId 23','27/07/2018 03:12:17 PM'),
  (269,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (270,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (271,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (272,2,0,'Completion date updated of disbursementId 24','27/07/2018 03:12:17 PM'),
  (273,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (274,2,2,'Client Added of clientId 3','27/07/2018 03:35:29 PM'),
  (275,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (276,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (277,2,0,'Completion date updated of disbursementId 8','27/07/2018 03:12:17 PM'),
  (278,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (279,2,0,'Completion date updated of disbursementId 9','27/07/2018 03:12:17 PM'),
  (280,2,0,'Paid Date Updated of disbursementId null','27/07/2018 03:12:17 PM'),
  (281,2,2,'Client Added of clientId 4','28/07/2018 10:16:29 AM'),
  (282,2,5,'Client Added of clientId 1','28/07/2018 10:23:49 AM'),
  (283,2,5,'Client Added of clientId 2','28/07/2018 11:39:31 AM'),
  (284,2,5,'Client Added of clientId 1','28/07/2018 11:42:53 AM'),
  (285,2,0,'Paid Date Updated of disbursementId null','28/07/2018 12:22:43 PM'),
  (286,2,2,'Client Added of clientId 2','28/07/2018 12:58:26 PM'),
  (287,2,0,'Paid Date Updated of disbursementId null','28/07/2018 12:22:43 PM'),
  (288,2,0,'Paid Date Updated of disbursementId null','28/07/2018 12:22:43 PM'),
  (289,2,0,'Completion date updated of disbursementId 7','28/07/2018 12:22:43 PM'),
  (290,2,2,'Client Added of clientId 3','28/07/2018 02:05:05 PM'),
  (291,2,0,'Paid Date Updated of disbursementId null','28/07/2018 12:22:43 PM'),
  (292,2,0,'Paid Date Updated of disbursementId null','28/07/2018 12:22:43 PM'),
  (293,2,5,'Client Added of clientId 1','28/07/2018 03:16:55 PM'),
  (294,2,0,'Paid Date Updated of disbursementId null','28/07/2018 04:07:40 PM'),
  (295,2,0,'Paid Date Updated of disbursementId null','28/07/2018 04:07:40 PM'),
  (296,2,0,'Completion date updated of disbursementId 22','28/07/2018 04:07:40 PM'),
  (297,2,0,'Paid Date Updated of disbursementId null','28/07/2018 04:40:05 PM'),
  (298,2,0,'Paid Date Updated of disbursementId null','28/07/2018 04:40:05 PM'),
  (299,2,5,'Client Added of clientId 2','28/07/2018 04:47:39 PM'),
  (300,2,2,'Client Added of clientId 3','30/07/2018 08:59:22 AM'),
  (301,2,0,'Completion date updated of disbursementId 7','30/07/2018 08:56:07 AM'),
  (302,2,5,'Client Added of clientId 1','30/07/2018 09:04:27 AM'),
  (303,2,5,'Client Added of clientId 2','30/07/2018 09:04:56 AM'),
  (304,2,0,'Paid Date Updated of disbursementId null','30/07/2018 08:56:07 AM'),
  (305,2,0,'Completion date updated of disbursementId 22','30/07/2018 08:56:07 AM'),
  (306,2,5,'Client Added of clientId 1','30/07/2018 09:12:48 AM'),
  (307,2,0,'Completion date updated of disbursementId 22','30/07/2018 09:16:28 AM'),
  (308,2,5,'Client Added of clientId 1','30/07/2018 09:32:40 AM'),
  (309,2,0,'Paid Date Updated of disbursementId null','30/07/2018 09:31:22 AM'),
  (310,2,0,'Completion date updated of disbursementId 22','30/07/2018 09:31:22 AM'),
  (311,2,5,'Client Added of clientId 1','30/07/2018 09:37:07 AM'),
  (312,2,0,'Paid Date Updated of disbursementId null','30/07/2018 09:37:47 AM'),
  (313,2,0,'Completion date updated of disbursementId 22','30/07/2018 09:37:47 AM'),
  (314,2,5,'Client Added of clientId 2','30/07/2018 09:43:31 AM'),
  (315,2,5,'Client Added of clientId 1','30/07/2018 10:30:59 AM'),
  (316,2,0,'Completion date updated of disbursementId 22','30/07/2018 10:33:32 AM'),
  (317,2,0,'Paid Date Updated of disbursementId null','30/07/2018 10:42:05 AM'),
  (318,2,0,'Paid Date Updated of disbursementId null','30/07/2018 10:42:05 AM'),
  (319,2,0,'Paid Date Updated of disbursementId null','30/07/2018 10:42:05 AM'),
  (320,2,5,'Client Added of clientId 2','30/07/2018 10:58:16 AM'),
  (321,2,2,'Client Added of clientId 3','30/07/2018 12:13:14 PM'),
  (322,2,0,'Completion date updated of disbursementId 7','30/07/2018 10:42:05 AM'),
  (323,2,0,'Paid Date Updated of disbursementId null','30/07/2018 10:42:05 AM'),
  (324,2,0,'Paid Date Updated of disbursementId null','30/07/2018 10:42:05 AM'),
  (325,2,0,'Completion date updated of disbursementId 7','30/07/2018 10:42:05 AM'),
  (326,2,2,'Client Added of clientId 4','30/07/2018 01:05:08 PM'),
  (327,2,0,'Completion date updated of disbursementId 23','30/07/2018 12:52:03 PM'),
  (328,2,0,'Paid Date Updated of disbursementId null','30/07/2018 12:52:03 PM'),
  (329,2,0,'Paid Date Updated of disbursementId null','30/07/2018 12:52:03 PM'),
  (330,2,0,'Paid Date Updated of disbursementId null','30/07/2018 12:52:03 PM'),
  (331,2,0,'Paid Date Updated of disbursementId null','30/07/2018 12:52:03 PM'),
  (332,2,0,'Paid Date Updated of disbursementId null','30/07/2018 12:52:03 PM'),
  (333,2,0,'Paid Date Updated of disbursementId null','30/07/2018 12:52:03 PM'),
  (334,2,0,'Settings of BuilderId[2] Updated','30/07/2018 02:50:14 PM'),
  (335,2,2,'Client Added of clientId 5','30/07/2018 04:37:39 PM'),
  (336,2,0,'Paid Date Updated of disbursementId null','31/07/2018 09:02:34 AM'),
  (337,2,0,'Paid Date Updated of disbursementId null','31/07/2018 09:02:34 AM'),
  (338,2,8,'Client Added of clientId 6','31/07/2018 09:06:28 AM'),
  (339,2,0,'Paid Date Updated of disbursementId null','31/07/2018 09:02:34 AM'),
  (340,2,0,'Paid Date Updated of disbursementId null','31/07/2018 09:02:34 AM'),
  (341,2,0,'Completion date updated of disbursementId 49','31/07/2018 09:02:34 AM'),
  (342,0,0,'CompanyId1 Details Updated','31/07/2018 03:23:54 PM'),
  (343,2,5,'Client Added of clientId 7','01/08/2018 10:27:30 AM'),
  (344,2,2,'Enquiry closed of enquiryId 26','01/08/2018 01:19:53 PM'),
  (345,2,0,'Paid Date Updated of disbursementId null','01/08/2018 02:33:04 PM'),
  (346,2,0,'Paid Date Updated of disbursementId null','01/08/2018 02:33:04 PM'),
  (347,2,0,'Paid Date Updated of disbursementId null','01/08/2018 02:44:53 PM'),
  (348,2,9,'Client Added of clientId 8','01/08/2018 04:27:52 PM'),
  (349,2,9,'Client Added of clientId 9','01/08/2018 04:28:31 PM'),
  (350,2,6,'Client Added of clientId 10','01/08/2018 04:33:23 PM'),
  (351,2,6,'Client Added of clientId 11','01/08/2018 04:34:13 PM'),
  (352,2,0,'Settings of CompanyId[1] Updated','02/08/2018 10:10:32 AM'),
  (353,2,5,'Client Added of clientId 12','02/08/2018 10:14:07 AM'),
  (354,2,0,'Paid Date Updated of disbursementId null','02/08/2018 10:37:18 AM'),
  (355,2,0,'Paid Date Updated of disbursementId null','02/08/2018 10:37:18 AM'),
  (356,2,7,'Client Added of clientId 13','02/08/2018 01:42:32 PM'),
  (357,2,0,'Paid Date Updated of disbursementId null','03/08/2018 10:22:16 AM'),
  (358,2,9,'Client Added of clientId 14','03/08/2018 02:12:19 PM'),
  (359,1,0,'Password changed','03/08/2018 02:55:11 PM'),
  (360,2,11,'Project added','03/08/2018 03:15:07 PM'),
  (361,2,11,'WingId 29 Added','03/08/2018 03:16:04 PM'),
  (362,2,0,'Completion date updated of disbursementId 43','03/08/2018 02:21:47 PM'),
  (363,2,0,'Completion date updated of disbursementId 50','03/08/2018 02:21:47 PM'),
  (364,2,2,'Client Added of clientId 15','04/08/2018 11:51:32 AM'),
  (365,2,5,'Client Added of clientId 16','04/08/2018 04:27:14 PM'),
  (366,2,5,'Client Added of clientId 17','07/08/2018 11:25:40 AM'),
  (367,2,0,'Paid Date Updated of disbursementId null','10/08/2018 09:02:28 AM'),
  (368,2,0,'Paid Date Updated of disbursementId null','10/08/2018 09:02:28 AM'),
  (369,2,0,'Paid Date Updated of disbursementId null','10/08/2018 09:02:28 AM'),
  (370,1,1,'AmenityId 66 Deleted','10/08/2018 10:00:58 AM'),
  (371,2,12,'Project added','10/08/2018 01:11:00 PM'),
  (372,2,7,'WingId 32 Added','10/08/2018 01:14:42 PM'),
  (373,2,7,'AmenityId 63 added','10/08/2018 01:14:57 PM'),
  (374,2,7,'AmenityId 27 Updated','10/08/2018 01:15:07 PM'),
  (375,2,7,'BankId 27 added','10/08/2018 01:16:37 PM'),
  (376,2,7,'BankId 27 Updated','10/08/2018 01:16:44 PM'),
  (377,2,7,'BankId 27 Updated','10/08/2018 01:16:50 PM'),
  (378,2,7,'BankId 28 added','10/08/2018 01:16:57 PM'),
  (379,2,7,'BankId 28 Deleted','10/08/2018 01:17:00 PM'),
  (380,2,7,'BankId 29 added','10/08/2018 01:17:23 PM'),
  (381,2,7,'BankId 29 Deleted','10/08/2018 01:17:40 PM'),
  (382,2,7,'BankId 14 Updated','10/08/2018 01:19:05 PM'),
  (383,2,7,'BankId 14 Updated','10/08/2018 01:19:33 PM'),
  (384,2,7,'BankId 14 Updated','10/08/2018 01:19:40 PM'),
  (385,2,7,'DocumentId 13 Added','10/08/2018 01:20:23 PM'),
  (386,2,2,'EnquiryId 30 updated ','10/08/2018 01:20:41 PM'),
  (387,2,2,'EnquiryId 30 updated ','10/08/2018 01:20:48 PM'),
  (388,2,0,'Settings of CompanyId[1] Updated','10/08/2018 01:29:11 PM'),
  (389,2,2,'Paid Date Updated of disbursementId 34','10/08/2018 03:11:58 PM'),
  (390,2,7,'Completion date updated of disbursementId 44','10/08/2018 03:11:58 PM'),
  (391,2,7,'AmenityId 26 Updated','10/08/2018 03:14:58 PM'),
  (392,2,5,'Paid Date Updated of disbursementId 3','11/08/2018 09:03:28 AM'),
  (393,2,7,'WingId 33 Added','11/08/2018 03:09:55 PM'),
  (394,2,7,'AmenityId 63 Updated','11/08/2018 03:10:16 PM'),
  (395,2,5,'Paid Date Updated of disbursementId 117','13/08/2018 09:04:32 AM'),
  (396,2,5,'Paid Date Updated of disbursementId 117','13/08/2018 09:04:32 AM'),
  (397,2,2,'Paid Date Updated of disbursementId 46','13/08/2018 12:27:51 PM'),
  (398,2,2,'Paid Date Updated of disbursementId 46','13/08/2018 12:27:51 PM'),
  (399,2,2,'Paid Date Updated of disbursementId 46','13/08/2018 12:27:51 PM'),
  (400,2,6,'Paid Date Updated of disbursementId 103','13/08/2018 02:45:22 PM'),
  (401,2,8,'Demand letter send date updated of disbursement Id 53','13/08/2018 04:37:03 PM'),
  (402,2,9,'Demand letter send date updated of disbursement Id 131','13/08/2018 04:56:54 PM'),
  (403,2,9,'Completion date updated of project disbursement Id 81','13/08/2018 05:00:15 PM'),
  (404,2,9,'Demand letter send date updated of disbursement Id 91','13/08/2018 05:00:15 PM'),
  (405,2,6,'BankId 12 Updated','14/08/2018 02:40:12 PM'),
  (406,2,2,'Paid Date Updated of disbursementId 46','16/08/2018 11:31:04 AM'),
  (407,2,6,'Paid Date Updated of disbursementId 109','16/08/2018 11:31:04 AM'),
  (408,2,2,'Paid Date Updated of disbursementId 34','16/08/2018 11:31:04 AM'),
  (409,2,2,'Paid Date Updated of disbursementId 46','16/08/2018 11:31:04 AM'),
  (410,2,2,'Paid Date Updated of disbursementId 46','16/08/2018 04:02:47 PM'),
  (411,2,5,'Paid Date Updated of disbursementId 117','17/08/2018 11:50:50 AM'),
  (412,2,8,'Paid Date Updated of disbursementId 53','17/08/2018 11:50:50 AM'),
  (413,2,8,'Paid Date Updated of disbursementId 53','17/08/2018 11:50:50 AM'),
  (414,2,9,'Paid Date Updated of disbursementId 131','17/08/2018 11:50:50 AM'),
  (415,0,0,'CompanyId3 Details Updated','17/08/2018 04:48:36 PM'),
  (416,2,13,'Project added','18/08/2018 05:04:33 PM'),
  (417,2,10,'Project Status Updated','18/08/2018 05:05:02 PM'),
  (418,2,10,'EnquiryId 34 added ','18/08/2018 05:06:45 PM'),
  (419,2,10,'Client Added of clientId 18','18/08/2018 05:07:22 PM'),
  (420,2,10,'Client Details updated of Client Id 18','18/08/2018 05:07:35 PM'),
  (421,2,9,'Paid Date Updated of disbursementId 91','20/08/2018 12:59:06 PM'),
  (422,2,9,'Paid Date Updated of disbursementId 77','20/08/2018 12:59:06 PM'),
  (423,2,5,'Paid Date Updated of disbursementId 117','20/08/2018 12:59:06 PM'),
  (424,2,2,'Paid Date Updated of disbursementId 46','20/08/2018 12:59:06 PM'),
  (425,2,7,'Completion date updated of project disbursement Id 45','22/08/2018 09:02:57 AM'),
  (426,2,5,'Paid Date Updated of disbursementId 3','22/08/2018 09:05:44 AM'),
  (427,2,9,'Paid Date Updated of disbursementId 77','22/08/2018 09:05:44 AM'),
  (428,2,2,'Project Basic Details Updated','22/08/2018 09:18:15 AM'),
  (429,2,14,'Project added','22/08/2018 03:55:46 PM'),
  (430,2,14,'Project Basic Details Updated','22/08/2018 04:15:39 PM'),
  (431,0,0,'CompanyId1 Details Updated','23/08/2018 09:29:40 AM'),
  (432,0,0,'CompanyId1 Details Updated','23/08/2018 11:03:00 AM'),
  (433,0,0,'CompanyId1 Details Updated','23/08/2018 11:06:44 AM'),
  (434,0,0,'CompanyId1 Details Updated','23/08/2018 11:07:20 AM'),
  (435,0,0,'CompanyId1 Details Updated','23/08/2018 11:08:04 AM'),
  (436,0,0,'CompanyId1 Details Updated','23/08/2018 11:08:50 AM'),
  (437,0,0,'CompanyId1 Details Updated','23/08/2018 11:10:10 AM'),
  (438,0,0,'CompanyId1 Details Updated','23/08/2018 11:11:08 AM'),
  (439,0,0,'CompanyId1 Details Updated','23/08/2018 11:11:46 AM'),
  (440,0,0,'CompanyId1 Details Updated','23/08/2018 11:12:18 AM'),
  (441,0,0,'CompanyId1 Details Updated','23/08/2018 11:28:27 AM'),
  (442,0,0,'CompanyId1 Details Updated','23/08/2018 11:30:04 AM'),
  (443,0,0,'CompanyId1 Details Updated','23/08/2018 11:42:25 AM'),
  (444,0,0,'CompanyId1 Details Updated','23/08/2018 11:43:21 AM'),
  (445,0,0,'CompanyId1 Details Updated','23/08/2018 11:44:06 AM'),
  (446,0,0,'CompanyId1 Details Updated','23/08/2018 11:46:19 AM'),
  (447,0,0,'CompanyId1 Details Updated','23/08/2018 11:47:28 AM'),
  (448,0,0,'CompanyId1 Details Updated','23/08/2018 11:56:39 AM'),
  (449,0,0,'CompanyId1 Details Updated','23/08/2018 11:58:13 AM'),
  (450,0,0,'CompanyId1 Details Updated','23/08/2018 12:41:15 PM'),
  (451,2,8,'Completion date updated of project disbursement Id 51','23/08/2018 02:30:47 PM'),
  (452,2,8,'DocumentId 16 Added','23/08/2018 03:36:13 PM'),
  (453,2,8,'DocumentId 17 Added','23/08/2018 03:42:22 PM'),
  (454,2,8,'DocumentId 18 Added','23/08/2018 03:43:47 PM'),
  (455,2,8,'DocumentId 19 Added','23/08/2018 03:44:15 PM'),
  (456,0,0,'CompanyId1 Details Updated','23/08/2018 04:23:18 PM'),
  (457,0,0,'CompanyId1 Details Updated','24/08/2018 09:03:48 AM'),
  (458,0,0,'CompanyId1 Details Updated','24/08/2018 10:36:07 AM'),
  (459,2,15,'Project added','24/08/2018 10:52:43 AM'),
  (460,2,16,'Project added','29/08/2018 09:30:34 AM'),
  (461,2,16,'DocumentId 22 Added','29/08/2018 09:34:37 AM'),
  (462,2,16,'DocumentId 21 Deleted','29/08/2018 09:34:39 AM'),
  (463,2,7,'Completion date updated of project disbursement Id 46','29/08/2018 09:27:59 AM'),
  (464,2,6,'Completion date updated of project disbursement Id 38','29/08/2018 09:27:59 AM'),
  (465,0,0,'CompanyId1 Details Updated','29/08/2018 11:17:46 AM'),
  (466,0,0,'CompanyId1 Details Updated','29/08/2018 11:26:34 AM'),
  (467,0,0,'CompanyId1 Details Updated','29/08/2018 11:27:35 AM'),
  (468,0,0,'CompanyId1 Details Updated','29/08/2018 11:28:02 AM'),
  (469,0,0,'CompanyId1 Details Updated','29/08/2018 11:29:18 AM'),
  (470,0,0,'CompanyId1 Details Updated','29/08/2018 11:29:43 AM'),
  (471,0,0,'CompanyId1 Details Updated','29/08/2018 11:30:30 AM'),
  (472,0,0,'CompanyId1 Details Updated','29/08/2018 11:32:57 AM'),
  (473,0,0,'CompanyId1 Details Updated','29/08/2018 11:34:02 AM'),
  (474,0,0,'CompanyId1 Details Updated','29/08/2018 11:34:28 AM'),
  (475,0,0,'CompanyId1 Details Updated','29/08/2018 11:34:55 AM'),
  (476,0,0,'CompanyId1 Details Updated','29/08/2018 11:35:01 AM'),
  (477,0,0,'CompanyId1 Details Updated','29/08/2018 11:35:45 AM'),
  (478,0,0,'CompanyId1 Details Updated','29/08/2018 11:35:55 AM'),
  (479,0,0,'CompanyId1 Details Updated','29/08/2018 11:36:59 AM'),
  (480,2,6,'Demand letter send date updated of disbursement Id 104','29/08/2018 04:05:30 PM'),
  (481,2,2,'Client Added of clientId 19','30/08/2018 04:30:57 PM'),
  (482,2,2,'Client Added of clientId 19','31/08/2018 09:27:15 AM'),
  (483,2,2,'Client Added of clientId 19','31/08/2018 09:28:55 AM'),
  (484,2,2,'Client Details updated of Client Id 19','31/08/2018 10:09:05 AM'),
  (485,2,2,'Client Details updated of Client Id 15','31/08/2018 04:59:43 PM'),
  (486,2,2,'Client Details updated of Client Id 19','01/09/2018 12:14:55 PM'),
  (487,2,2,'Client Details updated of Client Id 19','01/09/2018 12:16:30 PM'),
  (488,2,2,'Client Details updated of Client Id 19','01/09/2018 12:20:26 PM'),
  (489,2,5,'Client Added of clientId 22','01/09/2018 12:22:38 PM'),
  (490,2,9,'Demand letter send date updated of disbursement Id 78','01/09/2018 03:23:53 PM'),
  (491,2,10,'Completion date updated of project disbursement Id 93','02/09/2018 01:10:29 PM'),
  (492,2,10,'Demand letter send date updated of disbursement Id 144','02/09/2018 01:10:29 PM'),
  (493,2,10,'Paid Date Updated of disbursementId 144','02/09/2018 01:10:29 PM'),
  (494,2,10,'Paid Date Updated of disbursementId 144','02/09/2018 01:10:29 PM'),
  (495,2,10,'Paid Date Updated of disbursementId 144','02/09/2018 01:10:29 PM'),
  (496,2,10,'Completion date updated of project disbursement Id 94','02/09/2018 01:10:29 PM'),
  (497,2,10,'Completion date updated of project disbursement Id 95','02/09/2018 01:10:29 PM'),
  (498,2,10,'Completion date updated of project disbursement Id 96','02/09/2018 01:10:29 PM'),
  (499,2,10,'Completion date updated of project disbursement Id 97','02/09/2018 01:10:29 PM'),
  (500,2,10,'Demand letter send date updated of disbursement Id 148','02/09/2018 01:10:29 PM');

COMMIT;

#
# Data for the `activity_log` table  (LIMIT 500,500)
#

INSERT INTO `activity_log` (`activity_id`, `user_id`, `project_tran_id`, `activity_description`, `activity_datetime`) VALUES 
  (501,2,10,'Paid Date Updated of disbursementId 148','02/09/2018 01:10:29 PM'),
  (502,2,10,'Paid Date Updated of disbursementId 148','02/09/2018 01:10:29 PM'),
  (503,2,10,'Paid Date Updated of disbursementId 148','02/09/2018 01:10:29 PM'),
  (504,2,2,'Project Basic Details Updated','03/09/2018 02:32:00 PM'),
  (505,2,2,'Project Basic Details Updated','03/09/2018 02:35:49 PM'),
  (506,2,2,'Project Basic Details Updated','03/09/2018 02:41:20 PM'),
  (507,2,2,'Project Basic Details Updated','03/09/2018 02:42:40 PM'),
  (508,2,2,'Project Basic Details Updated','03/09/2018 02:46:28 PM'),
  (509,2,2,'Project Basic Details Updated','03/09/2018 02:52:18 PM'),
  (510,2,2,'Project Basic Details Updated','03/09/2018 02:52:57 PM'),
  (511,2,2,'Project Basic Details Updated','03/09/2018 02:53:19 PM'),
  (512,2,0,'Settings of CompanyId[1] Updated','04/09/2018 09:57:26 AM'),
  (513,2,0,'Added remark of enquiryId 30','04/09/2018 11:40:37 AM'),
  (514,2,2,'Added remark of enquiryId 30','04/09/2018 11:43:48 AM'),
  (515,2,2,'Added remark of enquiryId 30','04/09/2018 11:46:09 AM'),
  (516,2,2,'Added remark of enquiryId 30','04/09/2018 11:48:46 AM'),
  (517,2,2,'Added remark of enquiryId 30','04/09/2018 12:26:43 PM'),
  (518,2,2,'Added remark of enquiryId 30','04/09/2018 12:30:03 PM'),
  (519,2,2,'EnquiryId 35 added ','04/09/2018 12:52:11 PM'),
  (520,2,2,'EnquiryId 36 added ','04/09/2018 12:53:12 PM'),
  (521,2,2,'EnquiryId 37 added ','04/09/2018 12:54:03 PM'),
  (522,2,2,'EnquiryId 38 added ','04/09/2018 12:55:05 PM'),
  (523,2,2,'EnquiryId 39 added ','04/09/2018 12:56:15 PM'),
  (524,2,2,'EnquiryId 40 added ','04/09/2018 12:56:56 PM'),
  (525,2,2,'EnquiryId 41 added ','04/09/2018 12:57:47 PM'),
  (526,2,2,'EnquiryId 42 added ','04/09/2018 12:58:34 PM'),
  (527,2,2,'EnquiryId 43 added ','04/09/2018 12:59:15 PM'),
  (528,2,2,'EnquiryId 44 added ','04/09/2018 01:00:19 PM'),
  (529,2,2,'EnquiryId 40 updated ','04/09/2018 02:20:44 PM'),
  (530,2,2,'Client Added of clientId 23','04/09/2018 04:24:08 PM'),
  (531,2,2,'EnquiryId 40 updated ','05/09/2018 09:28:26 AM'),
  (532,2,2,'EnquiryId 43 updated ','05/09/2018 09:28:38 AM'),
  (533,2,2,'EnquiryId 45 added ','05/09/2018 09:38:05 AM'),
  (534,2,5,'Client Details updated of Client Id 16','05/09/2018 01:29:41 PM'),
  (535,2,5,'Client Details updated of Client Id 16','05/09/2018 02:41:18 PM'),
  (536,2,2,'Added remark of enquiryId 30','05/09/2018 03:55:39 PM'),
  (537,2,17,'Project added','07/09/2018 12:56:38 PM'),
  (538,2,17,'Project Status Updated','07/09/2018 01:14:07 PM'),
  (539,2,17,'Project Basic Details Updated','07/09/2018 01:16:14 PM'),
  (540,2,18,'Project added','07/09/2018 02:40:24 PM'),
  (541,2,19,'Project added','07/09/2018 02:51:38 PM'),
  (542,2,20,'Project added','07/09/2018 03:12:54 PM'),
  (543,2,20,'Project Status Updated','07/09/2018 03:16:22 PM'),
  (544,2,20,'Project Basic Details Updated','07/09/2018 03:17:06 PM'),
  (545,2,5,'WingId 47 Added','08/09/2018 11:27:43 AM'),
  (546,2,18,'WingId 41 Updated','08/09/2018 01:10:46 PM'),
  (547,2,18,'WingId 42 Updated','08/09/2018 01:11:03 PM'),
  (548,2,2,'Client Added of clientId 24','08/09/2018 03:20:33 PM'),
  (549,2,2,'Client Details updated of Client Id 24','08/09/2018 03:20:45 PM'),
  (550,2,2,'Client Details updated of Client Id 24','08/09/2018 03:21:01 PM'),
  (551,2,20,'WingId 45 Updated','10/09/2018 11:01:29 AM'),
  (552,2,2,'Demand letter send date updated of disbursement Id 150','10/09/2018 11:58:47 AM'),
  (553,2,2,'Paid Date Updated of disbursementId 150','10/09/2018 11:58:47 AM'),
  (554,2,2,'Paid Date Updated of disbursementId 46','10/09/2018 11:58:47 AM'),
  (555,2,5,'Client Added of clientId 25','10/09/2018 04:22:58 PM'),
  (556,2,5,'Client Added of clientId 26','10/09/2018 04:30:51 PM'),
  (557,2,2,'Paid Date Updated of disbursementId 46','14/09/2018 09:23:55 AM'),
  (558,2,9,'Demand letter send date updated of disbursement Id 132','14/09/2018 01:00:56 PM'),
  (559,2,5,'EnquiryId 46 added ','15/09/2018 09:10:34 AM'),
  (560,2,2,'EnquiryId 47 added ','17/09/2018 03:50:15 PM'),
  (561,2,2,'EnquiryId 48 added ','17/09/2018 03:50:56 PM'),
  (562,2,2,'EnquiryId 49 added ','17/09/2018 03:52:25 PM'),
  (563,2,2,'Project Basic Details Updated','25/09/2018 02:48:48 PM'),
  (564,2,2,'Project Basic Details Updated','12/10/2018 12:32:40 PM'),
  (565,2,2,'Project Basic Details Updated','12/10/2018 12:41:01 PM'),
  (566,2,2,'Project Basic Details Updated','12/10/2018 12:56:35 PM'),
  (567,2,2,'Project Basic Details Updated','12/10/2018 12:57:47 PM'),
  (568,2,2,'Project Basic Details Updated','12/10/2018 01:15:14 PM'),
  (569,2,2,'Project Basic Details Updated','12/10/2018 01:18:05 PM'),
  (570,2,5,'Project Basic Details Updated','12/10/2018 01:19:16 PM'),
  (571,2,8,'Project Basic Details Updated','12/10/2018 01:19:54 PM'),
  (572,2,8,'Project Basic Details Updated','12/10/2018 01:20:23 PM'),
  (573,2,8,'Project Basic Details Updated','12/10/2018 02:14:28 PM'),
  (574,2,8,'Project Basic Details Updated','12/10/2018 02:16:42 PM'),
  (575,2,8,'Project Basic Details Updated','12/10/2018 02:17:51 PM'),
  (576,2,8,'Project Basic Details Updated','12/10/2018 02:39:11 PM'),
  (577,2,8,'Project Basic Details Updated','12/10/2018 02:50:51 PM'),
  (578,2,8,'Project Basic Details Updated','12/10/2018 02:51:08 PM'),
  (579,2,8,'Project Basic Details Updated','12/10/2018 03:07:42 PM'),
  (580,2,8,'Project Basic Details Updated','12/10/2018 03:08:40 PM'),
  (581,2,2,'Project Basic Details Updated','12/10/2018 03:09:23 PM'),
  (582,2,2,'Project Basic Details Updated','12/10/2018 03:11:05 PM'),
  (583,2,2,'Project Basic Details Updated','12/10/2018 03:16:51 PM'),
  (584,2,2,'Project Basic Details Updated','12/10/2018 03:35:59 PM'),
  (585,2,2,'Project Basic Details Updated','12/10/2018 03:42:25 PM'),
  (586,2,8,'Project Basic Details Updated','12/10/2018 03:48:07 PM'),
  (587,2,5,'Project Basic Details Updated','12/10/2018 03:48:26 PM'),
  (588,2,5,'Project Basic Details Updated','12/10/2018 03:49:09 PM'),
  (589,2,5,'Project Basic Details Updated','12/10/2018 03:50:00 PM'),
  (590,2,5,'Project Basic Details Updated','12/10/2018 03:51:25 PM'),
  (591,2,8,'Project Basic Details Updated','12/10/2018 03:51:53 PM'),
  (592,2,8,'Project Basic Details Updated','12/10/2018 03:52:28 PM'),
  (593,2,8,'Project Basic Details Updated','12/10/2018 03:52:47 PM'),
  (594,2,5,'Project Basic Details Updated','12/10/2018 03:56:45 PM'),
  (595,2,2,'Project Basic Details Updated','12/10/2018 03:59:36 PM'),
  (596,2,2,'Project Basic Details Updated','12/10/2018 03:59:59 PM'),
  (597,2,5,'Project Basic Details Updated','12/10/2018 04:00:23 PM'),
  (598,2,20,'Project Basic Details Updated','12/10/2018 04:00:56 PM'),
  (599,2,2,'Project Basic Details Updated','12/10/2018 04:01:38 PM'),
  (600,2,8,'Project Basic Details Updated','12/10/2018 04:02:18 PM'),
  (601,2,5,'Project Basic Details Updated','12/10/2018 04:36:17 PM'),
  (602,2,8,'Project Basic Details Updated','12/10/2018 04:36:41 PM'),
  (603,2,20,'Project Basic Details Updated','12/10/2018 04:36:51 PM'),
  (604,2,10,'Project Basic Details Updated','12/10/2018 04:37:00 PM'),
  (605,3,9,'Project Basic Details Updated','13/10/2018 03:16:21 PM'),
  (606,3,4,'Project Basic Details Updated','13/10/2018 03:28:16 PM'),
  (607,3,7,'Project Basic Details Updated','13/10/2018 03:28:46 PM'),
  (608,2,2,'Project Basic Details Updated','15/10/2018 09:24:39 AM'),
  (609,2,5,'EnquiryId 50 added ','17/11/2018 10:11:10 AM'),
  (610,2,20,'BankId 44 added','02/01/2019 04:04:24 PM'),
  (611,0,1,'BankId 45 added','02/01/2019 04:13:30 PM'),
  (612,2,16,'BankId 34 Deleted','03/01/2019 03:17:56 PM'),
  (613,2,16,'BankId 34 Deleted','03/01/2019 04:09:15 PM'),
  (614,2,16,'BankId 34 Deleted','03/01/2019 04:10:12 PM'),
  (615,2,8,'Project Status Updated','04/01/2019 12:39:30 PM'),
  (616,2,2,'EnquiryId 43 updated ','04/01/2019 02:33:45 PM'),
  (617,2,13,'Project Status Updated','08/01/2019 01:20:50 PM'),
  (618,2,20,'DocumentId 27 Added','30/01/2019 09:22:14 AM');

COMMIT;

#
# Data for the `aminities` table  (LIMIT 0,500)
#

INSERT INTO `aminities` (`amenity_id`, `aminities`, `project_id`, `user_id`, `created_datetime`, `updated_datetime`, `is_remove`) VALUES 
  (1,'Swimming Pool',1,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (2,'Play Ground',1,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (3,'Parking',2,2,'26/06/2018 10:34:02 AM','12/07/2018 02:14:29 PM',0),
  (4,'Gym',2,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (5,'Playground',2,2,'26/06/2018 10:34:02 AM','12/07/2018 02:53:33 PM',0),
  (6,'Play Ground',3,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (7,'Swimming Pool',3,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (8,'HeliPad',3,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (9,'Amphy Theater',4,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (10,'Club House',4,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (11,'Parking',4,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (12,'Play Ground',4,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (13,'Power backup',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (14,'Lifts',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (15,'Security services',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (16,'Parking space',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (17,'Recreational facilities',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (18,'Access to common spaces',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (19,'Waste disposal',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (20,'Gym',6,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (21,'Play Ground',6,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (22,'Garden',6,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (23,'Gym',7,2,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM',0),
  (24,'Garden',7,2,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM',0),
  (26,'Spa',7,2,'05/07/2018 06:28:11 PM','10/08/2018 03:14:58 PM',0),
  (27,'aminity 11',7,2,'05/07/2018 06:28:40 PM','10/08/2018 01:15:07 PM',0),
  (28,'24 Hour Water Supply1',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (29,'Club House',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (30,'Food Court',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (31,'Landscape Garden Park',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (32,'Open Space',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (33,'Paved Compound',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (34,'Power Backup',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (35,'Wi-Fi Connectivity',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (36,'24 Hours Security',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (37,'Jogging Track',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (38,'Billiards & Snooker',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (39,'Basketball Court',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (40,'Tennis Court',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (41,'Conceirge Service',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (42,'Spa',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (43,'Parking space',9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:28:30 PM',0),
  (44,'Gym',9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (45,'Spa',9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (46,'Playground',9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (47,'Garden',9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (48,'Security services',9,2,'12/07/2018 02:28:03 PM','12/07/2018 02:28:03 PM',0),
  (52,'hhhhh',9,2,'12/07/2018 04:31:30 PM','12/07/2018 04:31:30 PM',0),
  (53,'Parking',10,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (54,'Gym',10,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (55,'Parking',11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (56,'Gym',11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (57,'Swimming Pool',11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (58,'Terrace',11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (59,'Lift',11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (60,'Parking',12,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (61,'Gym',12,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (62,'Swimming Pool',12,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (63,'Parking',7,2,'10/08/2018 01:14:57 PM','11/08/2018 03:10:16 PM',0),
  (64,'hhh',13,2,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM',0),
  (65,'Parking',14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (66,'Swimming Pool',14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (67,'Gym',14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (68,'Spa',14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (69,'Playground',14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (70,'Parking',15,2,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM',0),
  (71,'hhh',16,2,'29/08/2018 09:30:34 AM','29/08/2018 09:30:34 AM',0),
  (72,'Parking',17,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (73,'hgfhf',18,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM',0),
  (74,'JGHJ',19,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (75,'KKUI',20,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0);

COMMIT;

#
# Data for the `bank_details` table  (LIMIT 0,500)
#

INSERT INTO `bank_details` (`bank_id`, `bank_name`, `branch_name`, `account_name`, `ifsc_code`, `account_type`, `attachment`, `account_number`, `contact_person`, `contact_no`, `bank_type`, `project_id`, `user_id`, `created_datetime`, `updated_datetime`, `is_remove`) VALUES 
  (1,'SBI','Panchavati',NULL,NULL,NULL,NULL,NULL,'Hemant Gaidhani','9789798708',1,1,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (2,'HDFC','Konark Nagar','Amol Mahale','7897978','Saving',NULL,'907897987',NULL,NULL,2,1,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (3,'SBI','Makhmalabad, Panchvati',NULL,NULL,NULL,NULL,NULL,'Aashish Rakibe','8969686867',1,2,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (4,'HDFC','Makhmalabad Naka, Nashik','Dream Castle Developers','5FHJ3573985944','Saving','1530936834568_Welcome.docx','75757574678',NULL,NULL,2,2,2,'26/06/2018 10:34:02 AM','07/07/2018 09:44:07 AM',0),
  (5,'HDFC','Panchavati',NULL,NULL,NULL,NULL,NULL,'Kiran Kadam','8978979798',1,3,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (6,'Axis','Nimani','Shubham','09890898890','Saving',NULL,'890908908908',NULL,NULL,2,3,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (7,'SBI','Panchavati',NULL,NULL,NULL,NULL,NULL,'Hemant','6869869868',1,4,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (8,'HDFC','Panchavati','ABC','8969696','Saving',NULL,'986986689',NULL,NULL,2,4,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (9,'Bank Of India','Lekha Nagar',NULL,NULL,NULL,NULL,NULL,'Darshan Pingle','8939783973',1,5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (10,'Janata Sahakari Bank','Lekha Nagar','Amrut Villa Developers','JSBP0000043SD','Saving',NULL,'45789236871',NULL,NULL,2,5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (11,'HDFC Bank','Shivaji Nagar',NULL,NULL,NULL,NULL,NULL,'Gaurav Joshi','9970659640',1,6,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (12,'PDCC Bank','Punawale','Western Hills Developers','PDCC000349583','Saving','1534237812944_logo-AACT.png','5349500069594',NULL,NULL,2,6,2,'30/06/2018 05:53:14 PM','14/08/2018 02:40:12 PM',0),
  (13,'HDFC Bank','SHivaji Nagar',NULL,NULL,NULL,NULL,NULL,'Ramesh Shaha','9098790050',1,7,2,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM',0),
  (14,'PDCC Bank1','Shivaji nagar','A B Developers','PDCC007066','Saving','1533887373755_test.docx','6960594939000',NULL,NULL,2,7,2,'02/07/2018 05:05:07 PM','10/08/2018 01:19:40 PM',0),
  (15,'Canara Bank','Gangapur Road',NULL,NULL,NULL,NULL,NULL,'Mr. Paresh Dube','8923423423',1,8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (16,'ICICI Bank','Gangapur Road',NULL,NULL,NULL,NULL,NULL,'Mr. Vikas Mohan','9823492349',1,8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (17,'HDFC Bank','Sharanpur Road',NULL,NULL,NULL,NULL,NULL,'Mr. Sachin Mali','9382349294',1,8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (18,'Axis Bank','College Road','Creston Plaza Developers','AX000032421','Current','1530945256054_samplepdf.txt','686786786866',NULL,NULL,2,8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (19,'HDFC','ShivaJi Nagar, Saptpur, Nashik','Galaxy Developers','JGH954854JKTJJKKKK','Saving','1531386585057_TestWordDoc.doc','94564634536546666',NULL,NULL,2,9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:39:45 PM',0),
  (20,'SBI','Satpur Colony',NULL,NULL,NULL,NULL,NULL,'Prasad Nalawade','9853975398',1,9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:37:09 PM',0),
  (21,'SBI','College Road, nashik',NULL,NULL,NULL,NULL,NULL,'snehal pingle','9680456980',1,10,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (22,'Indian Bank','College Road, Nashik','Suyash Developers','48JJ6L5K496ML','Saving',NULL,'785678354785',NULL,NULL,2,10,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (23,'ICICI Bank','CBS',NULL,NULL,NULL,NULL,NULL,'Mr. Avinash Jadhav','9792835792',1,11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (24,'ICICI Bank','CBS','ABC Developers','5HKSJF4579567','Saving',NULL,'265465432132',NULL,NULL,2,11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (25,'Canara Bank','Makhmalabad, Nashik',NULL,NULL,NULL,NULL,NULL,'Avinash Mahale','8539039839',1,12,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (26,'Canara Bank','Makhmalabad, Nashik','XYZ Developers','JKJSF9583B343','Saving',NULL,'74984653212',NULL,NULL,2,12,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (27,'Axis Bank','Gangapur Road, Nashik',NULL,NULL,NULL,NULL,NULL,'Anvish Abhang','9347039570',1,7,2,'10/08/2018 01:16:37 PM','10/08/2018 01:16:50 PM',0),
  (28,'yy','yy','yy','55555','Saving',NULL,'5555',NULL,NULL,2,13,2,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM',0),
  (29,'kk','kk',NULL,NULL,NULL,NULL,NULL,'kk','7777777777',1,13,2,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM',0),
  (30,'SBI','CBS, Nashik',NULL,NULL,NULL,NULL,NULL,'Swapnil Ahire','8984923749',1,14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (31,'SBI','CBS, Nashik','Sai Developers','3872KJFSDKJ453489','Saving',NULL,'94538750297453',NULL,NULL,2,14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (32,'HDFC','Pavan Nagar, Nashik',NULL,NULL,NULL,NULL,NULL,'Prajwal Wadh','9854652321',1,15,2,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM',0),
  (33,'HDFC','Pavan Nagar, Nashik','Mayur Sanap','BN00034783','Saving',NULL,'36784763828975',NULL,NULL,2,15,2,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM',0),
  (35,'hh','hh','hh','hh','Saving',NULL,'444444',NULL,NULL,2,16,2,'29/08/2018 09:30:34 AM','29/08/2018 09:30:34 AM',0),
  (36,'aa','aaa',NULL,NULL,NULL,NULL,NULL,'djshlf','5555555555',1,17,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (37,'abc','abc','ABC','5987-089','Saving',NULL,'867858957897',NULL,NULL,2,17,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (38,'HGFH','FGHFGH',NULL,NULL,NULL,NULL,NULL,'FH','6575756756',1,18,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM',0),
  (39,'UTYUTY','UYTU','TYUTU','57858JT','Saving',NULL,'75675',NULL,NULL,2,18,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM',0),
  (40,'FJGH','JGHJ',NULL,NULL,NULL,NULL,NULL,'GHJG','7897897897',1,19,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (41,'FGH','FH','FGH','HFGHFG','Saving',NULL,'678678',NULL,NULL,2,19,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (42,'RTYTU','TYUTYU',NULL,NULL,NULL,NULL,NULL,'TYUYT','7868678686',1,20,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (43,'KKY','KYUK','UYKYUIK','YUIY766','Saving',NULL,'6767',NULL,NULL,2,20,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (44,'boi','abc',NULL,NULL,NULL,NULL,NULL,'abc','9898989757',1,20,2,'02/01/2019 04:04:24 PM','02/01/2019 04:04:24 PM',0),
  (45,'Bank Of India','Makhmalabad','Tanvi Kothule','','','','','','9596325142',1,1,0,'02/01/2019 04:13:30 PM','02/01/2019 04:13:30 PM',0);

COMMIT;

#
# Data for the `cal` table  (LIMIT 0,500)
#

INSERT INTO `cal` (`month`, `monthname`, `year`) VALUES 
  (8,'Aug',2018),
  (9,'Sep',2018),
  (10,'Oct',2018),
  (11,'Nov',2018),
  (12,'Dec',2018),
  (1,'Jan',2019);

COMMIT;

#
# Data for the `calendar_tbl` table  (LIMIT 0,500)
#

INSERT INTO `calendar_tbl` (`id`, `month`, `monthname`, `year`, `project_id`) VALUES 
  (1,10,'Oct',2018,2),
  (2,10,'Oct',2018,5),
  (3,10,'Oct',2018,6),
  (4,10,'Oct',2018,7),
  (5,10,'Oct',2018,8),
  (6,10,'Oct',2018,9),
  (7,10,'Oct',2018,20),
  (8,6,'Jun',2018,2),
  (9,7,'Jul',2018,2),
  (10,8,'Aug',2018,2),
  (11,9,'Sep',2018,2),
  (12,1,'Jan',2019,2),
  (19,1,'Jan',2019,5),
  (20,1,'Jan',2019,6),
  (21,1,'Jan',2019,7),
  (22,1,'Jan',2019,8),
  (23,1,'Jan',2019,9),
  (24,1,'Jan',2019,20),
  (32,12,'Dec',2018,2),
  (33,12,'Dec',2018,5),
  (34,12,'Dec',2018,6),
  (35,12,'Dec',2018,7),
  (36,12,'Dec',2018,8),
  (37,12,'Dec',2018,9),
  (38,12,'Dec',2018,20),
  (39,12,'Dec',2018,2),
  (41,12,'Dec',2018,6),
  (42,12,'Dec',2018,7),
  (44,12,'Dec',2018,9),
  (46,7,'Jul',2018,8),
  (47,8,'Aug',2018,8),
  (48,9,'Sep',2018,8),
  (49,11,'Nov',2018,8),
  (50,8,'Aug',2018,5),
  (51,7,'Jul',2018,5),
  (52,6,'Jun',2018,5),
  (53,9,'Sep',2018,5),
  (54,11,'Nov',2018,5);

COMMIT;

#
# Data for the `client` table  (LIMIT 0,500)
#

INSERT INTO `client` (`client_id`, `owner_name`, `date_of_birth`, `pan_number`, `aadhar_number`, `enquiry_id`, `project_id`, `wing_id`, `property_type_id`, `property_id`, `property_area`, `floor_number`, `flat_number`, `floor_name`, `rate`, `infrastructure_amount`, `agreement_amount`, `gst_percentage`, `client_tran_id`, `is_paid`, `user_id`, `booking_date`, `registration_no`, `registration_date`, `remark`, `booking_status`, `created_datetime`, `updated_datetime`) VALUES 
  (1,' Pallavi Pawar','12/05/1987','6476','021649846546',5,5,13,1,1,370.57,2,201,'Floor 2',0,0,3000000,12,1,0,2,NULL,NULL,NULL,NULL,1,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (2,'Pooja Sangle','10/06/1987','65032','021321546548',6,5,13,1,1,370.57,2,202,'Floor 2',0,0,2850000,12,2,0,2,NULL,NULL,NULL,NULL,1,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (3,'Kiran Kadam','27/04/1982','2652','013264560254',3,2,4,1,1,346,1,101,'Floor 1',0,0,3150000,12,3,0,2,NULL,NULL,NULL,NULL,1,'30/07/2018 12:13:14 PM','30/07/2018 12:13:14 PM'),
  (4,'Swapnil Ahire','04/05/1991','123456','002154984654',2,2,4,1,1,346,1,103,'Floor 1',0,0,3250000,13,4,0,2,NULL,NULL,NULL,NULL,1,'30/07/2018 01:05:08 PM','30/07/2018 01:05:08 PM'),
  (5,'Shital Patil','17/02/1988','32453','012134534345',4,2,4,1,1,346,1,105,'Floor 1',0,0,3200000,14,5,0,2,NULL,NULL,NULL,NULL,1,'30/07/2018 04:37:39 PM','30/07/2018 04:37:39 PM'),
  (6,'Anushka Varma','31/07/2018','32453','023165464899',17,8,18,1,1,554,2,201,'Floor 2',0,0,2980000,10,6,0,2,NULL,NULL,NULL,NULL,1,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (7,'Snehal Kedare','04/02/1987','32654','321654968749',23,5,13,1,1,370.57,2,204,'Floor 2',0,0,3400000,9,7,0,2,NULL,NULL,NULL,NULL,1,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (8,'Kajal Barve','30/03/1988','32453','365498465431',29,9,20,1,2,453,4,401,'Floor 4',0,0,3850000,14,8,0,2,NULL,NULL,NULL,NULL,1,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (9,'Mrunmayi Ghayal','07/02/1979','5565432','321301456460',27,9,20,1,2,453,4,403,'Floor 4',0,0,3975000,12,9,0,2,NULL,NULL,NULL,NULL,1,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (10,'Sandip Aware','05/05/1981','365101','323104614595',31,6,15,1,2,1000,3,301,'Floor 3',0,0,3280000,13,10,0,2,NULL,NULL,NULL,NULL,1,'01/08/2018 04:33:23 PM','01/08/2018 04:33:23 PM'),
  (11,'Laxmikant Wagh','15/04/1986','341232','041321341342',9,6,15,1,1,500,3,303,'Floor 3',0,0,3070000,14,11,0,2,NULL,NULL,NULL,NULL,1,'01/08/2018 04:34:13 PM','01/08/2018 04:34:13 PM'),
  (12,'Tejas Kale','31/07/2018','32654','765756834243',7,5,13,1,1,370.57,2,207,'Floor 2',0,0,2980000,10,12,0,2,NULL,NULL,NULL,NULL,1,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (13,'Ramesh Pawar','21/05/1987','32654','032165484545',10,7,17,1,1,500,2,202,'Floor 2',0,0,2935000,14,13,0,2,NULL,NULL,NULL,NULL,1,'02/08/2018 01:42:32 PM','02/08/2018 01:42:32 PM'),
  (14,'Pratiksha Pingle','07/05/1981','21546','120654874984',13,9,20,1,2,453,4,405,'Floor 4',0,0,3250000,15,14,0,2,NULL,NULL,NULL,NULL,1,'03/08/2018 02:12:19 PM','03/08/2018 02:12:19 PM'),
  (15,'Vrushali Nikam','05/04/1982','365465','011498654651',32,2,4,1,1,346,1,107,'Floor 1',5500,80000,1983000,12,15,0,2,'31/08/2018',NULL,NULL,NULL,2,'04/08/2018 11:51:32 AM','07/08/2018 10:38:22 AM'),
  (16,'Tejas Kale','30/12/1990','32654','328545123123',7,5,13,1,1,347.7,1,108,'Floor 1',0,0,3200000,14,16,0,2,NULL,NULL,NULL,NULL,2,'04/08/2018 04:27:14 PM','05/09/2018 02:41:18 PM'),
  (17,'Pooja Sangle','26/05/1981','2352132','023135484652',6,5,13,1,1,426.78,1,102,'Floor 1',0,0,2975000,14,17,0,2,NULL,NULL,NULL,NULL,3,'07/08/2018 11:25:40 AM','07/08/2018 11:25:45 AM'),
  (18,'Avdhut Chandwadkar','06/01/1981','9856564','231654531321',34,10,25,1,3,400,3,302,'Floor 3',0,0,4000000,14,18,1,2,NULL,NULL,NULL,NULL,1,'18/08/2018 05:07:22 PM','02/09/2018 01:31:07 PM'),
  (21,'Yogita Pawar','15/08/1979','65645','878465132131',14,2,4,1,1,380,2,204,'Floor 2',6000,70000,2350000,18,19,0,2,'31/08/2018',NULL,NULL,NULL,2,'31/08/2018 09:28:55 AM','31/08/2018 09:28:55 AM'),
  (22,'Snehal Kedare','05/02/1981','9686385','635389698635',23,5,13,1,1,426.78,1,102,'Floor 1',5000,40000,2173900,17,22,0,2,'01/09/2018',NULL,NULL,NULL,2,'01/09/2018 12:22:38 PM','01/09/2018 12:22:38 PM'),
  (23,'Shubham Gare','02/02/1983','3656746','435420343567',24,2,4,2,5,546,0,11,'Ground Floor ',5000,70000,2800000,18,23,0,2,'04/09/2018',NULL,NULL,NULL,2,'04/09/2018 04:24:08 PM','04/09/2018 04:24:08 PM'),
  (24,'Samiksha Daware','10/02/1983','8976546','456631321854',41,2,5,1,1,400,2,204,'Floor 2',6000,70000,2470000,18,24,0,2,'08/09/2018','14141414','08/09/2018',NULL,1,'08/09/2018 03:20:33 PM','08/09/2018 03:20:33 PM'),
  (25,'Snehal Kedare','15/04/1986','6834563','468797654643',23,5,47,1,1,785,1,101,'Ground Residential',4000,70000,3210000,18,25,0,2,'10/09/2018',NULL,NULL,NULL,2,'10/09/2018 04:22:58 PM','10/09/2018 04:22:58 PM'),
  (26,'Pooja Sangle','07/04/1981','6789798','343543457487',6,5,47,1,1,574,1,101,'Floor 1',4500,70000,2653000,18,26,0,2,'10/09/2018',NULL,NULL,NULL,2,'10/09/2018 04:30:51 PM','10/09/2018 04:30:51 PM');

COMMIT;

#
# Data for the `close_enquiry` table  (LIMIT 0,500)
#

INSERT INTO `close_enquiry` (`closed_enquiry_id`, `reason`, `enquiry_id`, `user_id`, `created_datetime`, `updated_datetime`) VALUES 
  (2,'Not  Interested',15,2,'14/07/2018 04:27:05 PM','14/07/2018 04:27:05 PM'),
  (3,'abcd',18,2,'14/07/2018 04:44:18 PM','14/07/2018 04:44:18 PM'),
  (4,'abcd',19,2,'14/07/2018 04:47:07 PM','14/07/2018 04:47:07 PM'),
  (5,'abcd',20,2,'14/07/2018 05:00:18 PM','14/07/2018 05:00:18 PM'),
  (6,'Not  Interested',21,2,'14/07/2018 05:01:53 PM','14/07/2018 05:01:53 PM'),
  (7,'Financial Issues',22,2,'14/07/2018 05:04:31 PM','14/07/2018 05:04:31 PM'),
  (8,'Not  Interested',16,2,'16/07/2018 11:30:53 AM','16/07/2018 11:30:53 AM'),
  (9,'Financial Issues',8,2,'16/07/2018 11:31:17 AM','16/07/2018 11:31:17 AM'),
  (10,'abcd',28,2,'16/07/2018 03:42:09 PM','16/07/2018 03:42:09 PM'),
  (11,'aaaa',25,2,'20/07/2018 10:13:44 AM','20/07/2018 10:13:44 AM'),
  (12,'mhgg ggjgj',33,2,'21/07/2018 10:31:31 AM','21/07/2018 10:31:31 AM'),
  (13,'bxbhd fgdgffdb',26,2,'01/08/2018 01:19:53 PM','01/08/2018 01:19:53 PM');

COMMIT;

#
# Data for the `company_payment_details` table  (LIMIT 0,500)
#

INSERT INTO `company_payment_details` (`payment_id`, `project_id`, `company_id`, `payment_type`, `start_date`, `end_date`, `amount`, `paid_date`, `discount`, `gst`, `total_amount`, `send_invoice_date`, `created_datetime`, `updated_datetime`) VALUES 
  (9,0,1,1,'30/07/2018','30/08/2018',0,'',0,0,0,NULL,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (10,0,3,1,'04/08/2018','04/09/2018',0,NULL,0,0,0,NULL,'04/08/2018 09:12:53 AM','04/08/2018 09:12:53 AM'),
  (11,1,1,5,'16/03/2019','15/03/2020',100000,'30/03/2019',20,12,1075200,NULL,'16/03/2019 11:06:04 AM','16/03/2019 11:06:04 AM');

COMMIT;

#
# Data for the `company_profile` table  (LIMIT 0,500)
#

INSERT INTO `company_profile` (`company_id`, `company_name`, `website`, `company_email`, `mobile`, `landline`, `address`, `logo_path`, `marketed_by`, `marketed_by_website`, `is_updated`, `created_datetime`, `updated_datetime`) VALUES 
  (1,'YSM Software','http://www.ysmsoftware.com','info@ysmsoftware.com','8985465132','0321165454','Dhandai Apartment, Pelican Park Rd, Swami Vivekanand Nagar, Shri Ram Colony, Nashik, Maharashtra 422010, India','1535522819174_james-grant-logo-ret.png','Sai Marketing',NULL,1,'30/07/2018 10:30:59 AM','29/08/2018 11:36:59 AM'),
  (3,'G D Square Developers','http://www.gdsquare.in','gdsquare@gmail.com','9873857398','0206060448','G D Square Developers Ujwal Serene, Office B 401,  Near Mauli Garden, Baner Road,  Baner, Pune - 411 045.','1534504716839_assoLogo.png','Sai Marketing',NULL,1,'04/08/2018 09:12:53 AM','17/08/2018 04:48:36 PM');

COMMIT;

#
# Data for the `custom_events` table  (LIMIT 0,500)
#

INSERT INTO `custom_events` (`custom_event_id`, `event_name`, `event_date`, `sms_id`, `is_remove`) VALUES 
  (1,'Holi','12/03/2019',16,0),
  (2,'abc','12/03/2019',17,0),
  (3,'pppp','12/03/2019',18,0),
  (4,'aaaaa','17/03/2019',19,0),
  (5,'Dussehra','28/03/2019',20,0),
  (6,'Diwali','18/03/2019',21,0),
  (7,'mmmm','21/03/2019',24,0),
  (8,'abcd','22/03/2019',25,0),
  (9,'nvbnvbn','24/03/2019',26,0),
  (10,'hggfhfhfhf','03/04/2019',27,0),
  (11,'mhmhjm','20/03/2019',28,0);

COMMIT;

#
# Data for the `disbursement` table  (LIMIT 0,500)
#

INSERT INTO `disbursement` (`disbursement_id`, `client_id`, `percentage_value`, `disbursement_description`, `disbursement_amount`, `gst_amount`, `prev_remaining_amount`, `total_amount`, `paid_amount`, `remaining_amount`, `send_pdf_date`, `paid_date`, `demand_letter_pdf_path`, `disbursement_followup_date`, `project_disbursement_id`, `user_id`, `created_datetime`, `updated_datetime`) VALUES 
  (1,1,5,NULL,150000,18000,0,168000,0,0,'30/07/2018',NULL,'Amrut Villa Apartment\\Wing-P\\201\\Pallavi_Pawar_5.0_1.pdf','04/08/2018',21,2,'30/07/2018 10:30:59 AM','30/07/2018 10:36:40 AM'),
  (2,1,5,'received by cheque',150000,18000,168000,336000,336000,0,'30/07/2018','30/07/2018','Amrut Villa Apartment\\Wing-P\\201\\Pallavi_Pawar_10.0_2.pdf','03/08/2018',22,2,'30/07/2018 10:30:59 AM','30/07/2018 10:47:42 AM'),
  (3,1,5,'paid',150000,18000,0,168000,140000,28000,'30/07/2018','21/08/2018','Amrut Villa Apartment\\Wing-P\\201\\Pallavi_Pawar_15.0_3.pdf','29/08/2018',23,2,'30/07/2018 10:30:59 AM','22/08/2018 09:08:15 AM'),
  (4,1,4.99,NULL,149700,17964,0,167664,0,167664,NULL,NULL,NULL,NULL,24,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (5,1,10.01,NULL,300300,36036,0,336336,0,336336,NULL,NULL,NULL,NULL,25,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (6,1,7.5,NULL,225000,27000,0,252000,0,252000,NULL,NULL,NULL,NULL,26,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (7,1,7.5,NULL,225000,27000,0,252000,0,252000,NULL,NULL,NULL,NULL,27,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (8,1,7.5,NULL,225000,27000,0,252000,0,252000,NULL,NULL,NULL,NULL,28,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (9,1,7.5,NULL,225000,27000,0,252000,0,252000,NULL,NULL,NULL,NULL,29,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (10,1,7.5,NULL,225000,27000,0,252000,0,252000,NULL,NULL,NULL,NULL,30,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (11,1,7.5,NULL,225000,27000,0,252000,0,252000,NULL,NULL,NULL,NULL,31,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (12,1,5,NULL,150000,18000,0,168000,0,168000,NULL,NULL,NULL,NULL,32,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (13,1,5,NULL,150000,18000,0,168000,0,168000,NULL,NULL,NULL,NULL,33,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (14,1,5,NULL,150000,18000,0,168000,0,168000,NULL,NULL,NULL,NULL,34,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (15,1,5,NULL,150000,18000,0,168000,0,168000,NULL,NULL,NULL,NULL,35,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (16,1,5,NULL,150000,18000,0,168000,0,168000,NULL,NULL,NULL,NULL,36,2,'30/07/2018 10:30:59 AM','30/07/2018 10:30:59 AM'),
  (17,2,5,NULL,142500,17100,0,159600,0,0,NULL,NULL,NULL,NULL,21,2,'30/07/2018 10:58:16 AM','30/07/2018 11:00:17 AM'),
  (18,2,5,'received',142500,17100,159600,319200,230000,0,'30/07/2018','30/07/2018','Amrut Villa Apartment\\Wing-P\\202\\Pooja_Sangle_10.0_18.pdf','05/08/2018',22,2,'30/07/2018 10:58:16 AM','30/07/2018 02:13:53 PM'),
  (19,2,5,'received',142500,17100,89200,248800,248800,0,'30/07/2018','01/08/2018','Amrut Villa Apartment\\Wing-P\\202\\Pooja_Sangle_15.0_19.pdf','05/08/2018',23,2,'30/07/2018 10:58:16 AM','01/08/2018 02:36:07 PM'),
  (20,2,4.99,NULL,142215,17065.8,0,159280.8,0,159280.8,NULL,NULL,NULL,NULL,24,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (21,2,10.01,NULL,285285,34234.2,0,319519.2,0,319519.2,NULL,NULL,NULL,NULL,25,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (22,2,7.5,NULL,213750,25650,0,239400,0,239400,NULL,NULL,NULL,NULL,26,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (23,2,7.5,NULL,213750,25650,0,239400,0,239400,NULL,NULL,NULL,NULL,27,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (24,2,7.5,NULL,213750,25650,0,239400,0,239400,NULL,NULL,NULL,NULL,28,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (25,2,7.5,NULL,213750,25650,0,239400,0,239400,NULL,NULL,NULL,NULL,29,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (26,2,7.5,NULL,213750,25650,0,239400,0,239400,NULL,NULL,NULL,NULL,30,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (27,2,7.5,NULL,213750,25650,0,239400,0,239400,NULL,NULL,NULL,NULL,31,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (28,2,5,NULL,142500,17100,0,159600,0,159600,NULL,NULL,NULL,NULL,32,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (29,2,5,NULL,142500,17100,0,159600,0,159600,NULL,NULL,NULL,NULL,33,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (30,2,5,NULL,142500,17100,0,159600,0,159600,NULL,NULL,NULL,NULL,34,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (31,2,5,NULL,142500,17100,0,159600,0,159600,NULL,NULL,NULL,NULL,35,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (32,2,5,NULL,142500,17100,0,159600,0,159600,NULL,NULL,NULL,NULL,36,2,'30/07/2018 10:58:16 AM','30/07/2018 10:58:16 AM'),
  (33,3,12,'received by cheque',378000,45360,0,423360,200000,0,'30/07/2018','30/07/2018','Dream Castle\\Wing-A\\101\\Kiran_Kadam_12.0_33.pdf','04/08/2018',6,2,'30/07/2018 12:13:14 PM','30/07/2018 01:04:17 PM'),
  (34,3,23,'received',724500,86940,223360,1034800,1034800,0,'30/07/2018','16/08/2018','Dream Castle\\Wing-A\\101\\Kiran_Kadam_35.0_34.pdf','16/08/2018',7,2,'30/07/2018 12:13:14 PM','16/08/2018 02:56:00 PM'),
  (35,3,15,NULL,472500,56700,0,529200,0,529200,NULL,NULL,NULL,NULL,8,2,'30/07/2018 12:13:14 PM','30/07/2018 12:13:14 PM'),
  (36,3,18,NULL,567000,68040,0,635040,0,635040,NULL,NULL,NULL,NULL,9,2,'30/07/2018 12:13:14 PM','30/07/2018 12:13:14 PM'),
  (37,3,22,NULL,693000,83160,0,776160,0,776160,NULL,NULL,NULL,NULL,10,2,'30/07/2018 12:13:14 PM','30/07/2018 12:13:14 PM'),
  (38,3,10,NULL,315000,37800,0,352800,0,352800,NULL,NULL,NULL,NULL,11,2,'30/07/2018 12:13:14 PM','30/07/2018 12:13:14 PM'),
  (39,4,12,NULL,390000,50700,0,440700,0,0,NULL,NULL,NULL,NULL,6,2,'30/07/2018 01:05:08 PM','30/07/2018 01:05:25 PM'),
  (40,4,23,'received',747500,97175,440700,1285375,1285375,0,'30/07/2018','10/08/2018','Dream Castle\\Wing-A\\103\\Swapnil_Ahire_35.0_40.pdf','08/08/2018',7,2,'30/07/2018 01:05:08 PM','10/08/2018 09:12:29 AM'),
  (41,4,15,NULL,487500,63375,0,550875,0,550875,NULL,NULL,NULL,NULL,8,2,'30/07/2018 01:05:08 PM','30/07/2018 01:05:08 PM'),
  (42,4,18,NULL,585000,76050,0,661050,0,661050,NULL,NULL,NULL,NULL,9,2,'30/07/2018 01:05:08 PM','30/07/2018 01:05:08 PM'),
  (43,4,22,NULL,715000,92950,0,807950,0,807950,NULL,NULL,NULL,NULL,10,2,'30/07/2018 01:05:08 PM','30/07/2018 01:05:08 PM'),
  (44,4,10,NULL,325000,42250,0,367250,0,367250,NULL,NULL,NULL,NULL,11,2,'30/07/2018 01:05:08 PM','30/07/2018 01:05:08 PM'),
  (45,5,12,NULL,384000,53760,0,437760,0,0,NULL,NULL,NULL,NULL,6,2,'30/07/2018 04:37:39 PM','30/07/2018 04:42:29 PM'),
  (46,5,23,'paid',736000,103040,437760,1276800,1250850,25950,'30/07/2018','14/09/2018','Dream Castle\\Wing-A\\105\\Shital_Patil_35.0_46.pdf','22/08/2018',7,2,'30/07/2018 04:37:39 PM','14/09/2018 11:33:53 AM'),
  (47,5,15,NULL,480000,67200,0,547200,0,547200,NULL,NULL,NULL,NULL,8,2,'30/07/2018 04:37:39 PM','30/07/2018 04:37:39 PM'),
  (48,5,18,NULL,576000,80640,0,656640,0,656640,NULL,NULL,NULL,NULL,9,2,'30/07/2018 04:37:39 PM','30/07/2018 04:37:39 PM'),
  (49,5,22,NULL,704000,98560,0,802560,0,802560,NULL,NULL,NULL,NULL,10,2,'30/07/2018 04:37:39 PM','30/07/2018 04:37:39 PM'),
  (50,5,10,NULL,320000,44800,0,364800,0,364800,NULL,NULL,NULL,NULL,11,2,'30/07/2018 04:37:39 PM','30/07/2018 04:37:39 PM'),
  (51,6,9.9,'received',295020,29502,0,324522,200000,0,'31/07/2018','31/07/2018','Creston Plaza\\Wing-A\\201\\Anushka_Varma_9.9_51.pdf','05/08/2018',48,2,'31/07/2018 09:06:28 AM','31/07/2018 09:10:19 AM'),
  (52,6,10.1,NULL,300980,30098,124522,455600,0,0,'31/07/2018',NULL,'Creston Plaza\\Wing-A\\201\\Anushka_Varma_20.0_52.pdf','05/08/2018',49,2,'31/07/2018 09:06:28 AM','13/08/2018 04:51:07 PM'),
  (53,6,10,'paid',298000,29800,455600,783400,170000,613400,'13/08/2018','17/08/2018','Creston Plaza\\Wing-A\\201\\Anushka Varma_30.0_53.pdf','24/08/2018',50,2,'31/07/2018 09:06:28 AM','17/08/2018 12:32:14 PM'),
  (54,6,8.57,NULL,255386,25538.6,0,280924.6,0,280924.6,NULL,NULL,NULL,NULL,51,2,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (55,6,7.41,NULL,220818,22081.8,0,242899.8,0,242899.8,NULL,NULL,NULL,NULL,52,2,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (56,6,8.5,NULL,253300,25330,0,278630,0,278630,NULL,NULL,NULL,NULL,53,2,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (57,6,12.63,NULL,376374,37637.4,0,414011.4,0,414011.4,NULL,NULL,NULL,NULL,54,2,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (58,6,10.76,NULL,320648,32064.8,0,352712.8,0,352712.8,NULL,NULL,NULL,NULL,55,2,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (59,6,11.89,NULL,354322,35432.2,0,389754.2,0,389754.2,NULL,NULL,NULL,NULL,56,2,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (60,6,10.24,NULL,305152,30515.2,0,335667.2,0,335667.2,NULL,NULL,NULL,NULL,57,2,'31/07/2018 09:06:28 AM','31/07/2018 09:06:28 AM'),
  (61,7,5,NULL,170000,15300,0,185300,0,0,NULL,NULL,NULL,NULL,21,2,'01/08/2018 10:27:30 AM','01/08/2018 04:01:54 PM'),
  (62,7,5,NULL,170000,15300,0,185300,0,0,NULL,NULL,NULL,NULL,22,2,'01/08/2018 10:27:30 AM','01/08/2018 04:01:54 PM'),
  (63,7,5,'received by cheque',170000,15300,370600,555900,555900,0,'01/08/2018','01/08/2018','Amrut Villa Apartment\\Wing-P\\204\\Snehal_Kedare_15.0_63.pdf','06/08/2018',23,2,'01/08/2018 10:27:30 AM','01/08/2018 04:02:55 PM'),
  (64,7,4.99,NULL,169660,15269.4,0,184929.4,0,184929.4,NULL,NULL,NULL,NULL,24,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (65,7,10.01,NULL,340340,30630.6,0,370970.6,0,370970.6,NULL,NULL,NULL,NULL,25,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (66,7,7.5,NULL,255000,22950,0,277950,0,277950,NULL,NULL,NULL,NULL,26,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (67,7,7.5,NULL,255000,22950,0,277950,0,277950,NULL,NULL,NULL,NULL,27,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (68,7,7.5,NULL,255000,22950,0,277950,0,277950,NULL,NULL,NULL,NULL,28,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (69,7,7.5,NULL,255000,22950,0,277950,0,277950,NULL,NULL,NULL,NULL,29,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (70,7,7.5,NULL,255000,22950,0,277950,0,277950,NULL,NULL,NULL,NULL,30,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (71,7,7.5,NULL,255000,22950,0,277950,0,277950,NULL,NULL,NULL,NULL,31,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (72,7,5,NULL,170000,15300,0,185300,0,185300,NULL,NULL,NULL,NULL,32,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (73,7,5,NULL,170000,15300,0,185300,0,185300,NULL,NULL,NULL,NULL,33,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (74,7,5,NULL,170000,15300,0,185300,0,185300,NULL,NULL,NULL,NULL,34,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (75,7,5,NULL,170000,15300,0,185300,0,185300,NULL,NULL,NULL,NULL,35,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (76,7,5,NULL,170000,15300,0,185300,0,185300,NULL,NULL,NULL,NULL,36,2,'01/08/2018 10:27:30 AM','01/08/2018 10:27:30 AM'),
  (77,8,5,'paid',192500,26950,0,219450,169450,0,'01/08/2018','22/08/2018','Galaxy Apartment\\Wing-A\\401\\Kajal_Barve_5.0_77.pdf','29/08/2018',80,2,'01/08/2018 04:27:52 PM','01/09/2018 03:25:45 PM'),
  (78,8,5,NULL,192500,26950,50000,269450,0,269450,'01/09/2018',NULL,'Galaxy Apartment\\Wing-A\\401\\Kajal Barve_10.0_78.pdf','22/08/2018',81,2,'01/08/2018 04:27:52 PM','01/09/2018 03:25:45 PM'),
  (79,8,7,NULL,269500,37730,0,307230,0,307230,NULL,NULL,NULL,NULL,82,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (80,8,5.97,NULL,229845,32178.3,0,262023.3,0,262023.3,NULL,NULL,NULL,NULL,83,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (81,8,6.83,NULL,262955,36813.7,0,299768.7,0,299768.7,NULL,NULL,NULL,NULL,84,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (82,8,9.46,NULL,364210,50989.4,0,415199.4,0,415199.4,NULL,NULL,NULL,NULL,85,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (83,8,10.58,NULL,407330,57026.2,0,464356.2,0,464356.2,NULL,NULL,NULL,NULL,86,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (84,8,12.45,NULL,479325,67105.5,0,546430.5,0,546430.5,NULL,NULL,NULL,NULL,87,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (85,8,11.27,NULL,433895,60745.3,0,494640.3,0,494640.3,NULL,NULL,NULL,NULL,88,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (86,8,8.68,NULL,334180,46785.2,0,380965.2,0,380965.2,NULL,NULL,NULL,NULL,89,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (87,8,6.47,NULL,249095,34873.3,0,283968.3,0,283968.3,NULL,NULL,NULL,NULL,90,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (88,8,5.25,NULL,202125,28297.5,0,230422.5,0,230422.5,NULL,NULL,NULL,NULL,91,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (89,8,6.04,NULL,232540,32555.6,0,265095.6,0,265095.6,NULL,NULL,NULL,NULL,92,2,'01/08/2018 04:27:52 PM','01/08/2018 04:27:52 PM'),
  (90,9,5,NULL,198750,23850,0,222600,0,0,'01/08/2018',NULL,'Galaxy Apartment\\Wing-A\\403\\Mrunmayi_Ghayal_5.0_90.pdf','06/08/2018',80,2,'01/08/2018 04:28:31 PM','13/08/2018 05:03:09 PM'),
  (91,9,5,'paid',198750,23850,222600,445200,300000,145200,'13/08/2018','20/08/2018','Galaxy Apartment\\Wing-A\\403\\Mrunmayi Ghayal_10.0_91.pdf','27/08/2018',81,2,'01/08/2018 04:28:31 PM','20/08/2018 03:15:06 PM'),
  (92,9,7,NULL,278250,33390,0,311640,0,311640,NULL,NULL,NULL,NULL,82,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (93,9,5.97,NULL,237307.5,28476.9,0,265784.4,0,265784.4,NULL,NULL,NULL,NULL,83,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (94,9,6.83,NULL,271492.5,32579.1,0,304071.6,0,304071.6,NULL,NULL,NULL,NULL,84,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (95,9,9.46,NULL,376035,45124.2,0,421159.2,0,421159.2,NULL,NULL,NULL,NULL,85,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (96,9,10.58,NULL,420555,50466.6,0,471021.6,0,471021.6,NULL,NULL,NULL,NULL,86,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (97,9,12.45,NULL,494887.5,59386.5,0,554274,0,554274,NULL,NULL,NULL,NULL,87,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (98,9,11.27,NULL,447982.5,53757.9,0,501740.4,0,501740.4,NULL,NULL,NULL,NULL,88,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (99,9,8.68,NULL,345030,41403.6,0,386433.6,0,386433.6,NULL,NULL,NULL,NULL,89,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (100,9,6.47,NULL,257182.5,30861.9,0,288044.4,0,288044.4,NULL,NULL,NULL,NULL,90,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (101,9,5.25,NULL,208687.5,25042.5,0,233730,0,233730,NULL,NULL,NULL,NULL,91,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (102,9,6.04,NULL,240090,28810.8,0,268900.8,0,268900.8,NULL,NULL,NULL,NULL,92,2,'01/08/2018 04:28:31 PM','01/08/2018 04:28:31 PM'),
  (103,10,20,'received',656000,85280,0,741280,320000,0,'01/08/2018','13/08/2018','Western Hills\\A\\301\\Sandip_Aware_20.0_103.pdf','24/08/2018',37,2,'01/08/2018 04:33:23 PM','29/08/2018 04:07:36 PM'),
  (104,10,20,NULL,656000,85280,421280,1162560,0,1162560,'29/08/2018',NULL,'Western Hills\\A\\301\\Sandip Aware_40.0_104.pdf','05/09/2018',38,2,'01/08/2018 04:33:23 PM','29/08/2018 04:07:36 PM'),
  (105,10,20,NULL,656000,85280,0,741280,0,741280,NULL,NULL,NULL,NULL,39,2,'01/08/2018 04:33:23 PM','01/08/2018 04:33:23 PM'),
  (106,10,20,NULL,656000,85280,0,741280,0,741280,NULL,NULL,NULL,NULL,40,2,'01/08/2018 04:33:23 PM','01/08/2018 04:33:23 PM'),
  (107,10,10,NULL,328000,42640,0,370640,0,370640,NULL,NULL,NULL,NULL,41,2,'01/08/2018 04:33:23 PM','01/08/2018 04:33:23 PM'),
  (108,10,10,NULL,328000,42640,0,370640,0,370640,NULL,NULL,NULL,NULL,42,2,'01/08/2018 04:33:23 PM','01/08/2018 04:33:23 PM'),
  (109,11,20,'received',614000,85960,0,699960,699960,0,'01/08/2018','16/08/2018','Western Hills\\A\\303\\Laxmikant_Wagh_20.0_109.pdf','06/08/2018',37,2,'01/08/2018 04:34:13 PM','16/08/2018 02:54:36 PM'),
  (110,11,20,NULL,614000,85960,0,699960,0,699960,NULL,NULL,NULL,NULL,38,2,'01/08/2018 04:34:13 PM','01/08/2018 04:34:13 PM'),
  (111,11,20,NULL,614000,85960,0,699960,0,699960,NULL,NULL,NULL,NULL,39,2,'01/08/2018 04:34:13 PM','01/08/2018 04:34:13 PM'),
  (112,11,20,NULL,614000,85960,0,699960,0,699960,NULL,NULL,NULL,NULL,40,2,'01/08/2018 04:34:13 PM','01/08/2018 04:34:13 PM'),
  (113,11,10,NULL,307000,42980,0,349980,0,349980,NULL,NULL,NULL,NULL,41,2,'01/08/2018 04:34:13 PM','01/08/2018 04:34:13 PM'),
  (114,11,10,NULL,307000,42980,0,349980,0,349980,NULL,NULL,NULL,NULL,42,2,'01/08/2018 04:34:13 PM','01/08/2018 04:34:13 PM'),
  (115,12,5,NULL,149000,14900,0,163900,0,0,NULL,NULL,NULL,NULL,21,2,'02/08/2018 10:14:07 AM','02/08/2018 10:18:49 AM'),
  (116,12,5,NULL,149000,14900,0,163900,0,0,NULL,NULL,NULL,NULL,22,2,'02/08/2018 10:14:07 AM','02/08/2018 10:18:49 AM'),
  (117,12,5,'paid',149000,14900,327800,491700,491700,0,'02/08/2018','20/08/2018','Amrut Villa Apartment\\Wing-P\\207\\Tejas_Kale_15.0_117.pdf','22/08/2018',23,2,'02/08/2018 10:14:07 AM','20/08/2018 03:17:20 PM'),
  (118,12,4.99,NULL,148702,14870.2,0,163572.2,0,163572.2,NULL,NULL,NULL,NULL,24,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (119,12,10.01,NULL,298298,29829.8,0,328127.8,0,328127.8,NULL,NULL,NULL,NULL,25,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (120,12,7.5,NULL,223500,22350,0,245850,0,245850,NULL,NULL,NULL,NULL,26,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (121,12,7.5,NULL,223500,22350,0,245850,0,245850,NULL,NULL,NULL,NULL,27,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (122,12,7.5,NULL,223500,22350,0,245850,0,245850,NULL,NULL,NULL,NULL,28,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (123,12,7.5,NULL,223500,22350,0,245850,0,245850,NULL,NULL,NULL,NULL,29,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (124,12,7.5,NULL,223500,22350,0,245850,0,245850,NULL,NULL,NULL,NULL,30,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (125,12,7.5,NULL,223500,22350,0,245850,0,245850,NULL,NULL,NULL,NULL,31,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (126,12,5,NULL,149000,14900,0,163900,0,163900,NULL,NULL,NULL,NULL,32,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (127,12,5,NULL,149000,14900,0,163900,0,163900,NULL,NULL,NULL,NULL,33,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (128,12,5,NULL,149000,14900,0,163900,0,163900,NULL,NULL,NULL,NULL,34,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (129,12,5,NULL,149000,14900,0,163900,0,163900,NULL,NULL,NULL,NULL,35,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (130,12,5,NULL,149000,14900,0,163900,0,163900,NULL,NULL,NULL,NULL,36,2,'02/08/2018 10:14:07 AM','02/08/2018 10:14:07 AM'),
  (131,14,5,'paid',162500,24375,0,186875,70000,0,'13/08/2018','17/08/2018','Galaxy Apartment\\Wing-A\\405\\Pratiksha Pingle_5.0_131.pdf','24/08/2018',80,2,'04/08/2018 11:34:55 AM','14/09/2018 01:19:16 PM'),
  (132,14,5,NULL,162500,24375,116875,303750,0,303750,'14/09/2018',NULL,'Galaxy Apartment\\Wing-A\\Floor 4\\405\\Pratiksha Pingle_10.0_132.pdf','21/09/2018',81,2,'04/08/2018 11:34:55 AM','14/09/2018 01:19:16 PM'),
  (133,14,7,NULL,227500,34125,0,261625,0,261625,NULL,NULL,NULL,NULL,82,2,'04/08/2018 11:34:55 AM','04/08/2018 11:34:55 AM'),
  (134,14,5.97,NULL,194025,29103.75,0,223128.75,0,223128.75,NULL,NULL,NULL,NULL,83,2,'04/08/2018 11:34:55 AM','04/08/2018 11:34:55 AM'),
  (135,14,6.83,NULL,221975,33296.25,0,255271.25,0,255271.25,NULL,NULL,NULL,NULL,84,2,'04/08/2018 11:34:55 AM','04/08/2018 11:34:55 AM'),
  (136,14,9.46,NULL,307450,46117.5,0,353567.5,0,353567.5,NULL,NULL,NULL,NULL,85,2,'04/08/2018 11:34:55 AM','04/08/2018 11:34:55 AM'),
  (137,14,10.58,NULL,343850,51577.5,0,395427.5,0,395427.5,NULL,NULL,NULL,NULL,86,2,'04/08/2018 11:34:55 AM','04/08/2018 11:34:55 AM'),
  (138,14,12.45,NULL,404625,60693.75,0,465318.75,0,465318.75,NULL,NULL,NULL,NULL,87,2,'04/08/2018 11:34:55 AM','04/08/2018 11:34:55 AM'),
  (139,14,11.27,NULL,366275,54941.25,0,421216.25,0,421216.25,NULL,NULL,NULL,NULL,88,2,'04/08/2018 11:34:56 AM','04/08/2018 11:34:56 AM'),
  (140,14,8.68,NULL,282100,42315,0,324415,0,324415,NULL,NULL,NULL,NULL,89,2,'04/08/2018 11:34:56 AM','04/08/2018 11:34:56 AM'),
  (141,14,6.47,NULL,210275,31541.25,0,241816.25,0,241816.25,NULL,NULL,NULL,NULL,90,2,'04/08/2018 11:34:56 AM','04/08/2018 11:34:56 AM'),
  (142,14,5.25,NULL,170625,25593.75,0,196218.75,0,196218.75,NULL,NULL,NULL,NULL,91,2,'04/08/2018 11:34:56 AM','04/08/2018 11:34:56 AM'),
  (143,14,6.04,NULL,196300,29445,0,225745,0,225745,NULL,NULL,NULL,NULL,92,2,'04/08/2018 11:34:56 AM','04/08/2018 11:34:56 AM'),
  (144,18,23,'received',920000,128800,0,1048800,1048800,0,'02/09/2018','02/09/2018','Diamond Villa\\Wing-1\\302\\Avdhut Chandwadkar_23.0_144.pdf','07/09/2018',93,2,'18/08/2018 05:07:35 PM','02/09/2018 01:27:00 PM'),
  (145,18,20,NULL,800000,112000,0,912000,0,0,NULL,NULL,NULL,NULL,94,2,'18/08/2018 05:07:35 PM','02/09/2018 01:30:07 PM'),
  (146,18,17,NULL,680000,95200,0,775200,0,0,NULL,NULL,NULL,NULL,95,2,'18/08/2018 05:07:35 PM','02/09/2018 01:30:07 PM'),
  (147,18,25,NULL,1000000,140000,0,1140000,0,0,NULL,NULL,NULL,NULL,96,2,'18/08/2018 05:07:35 PM','02/09/2018 01:30:07 PM'),
  (148,18,15,'paid',600000,84000,2827200,3511200,2830000,681200,'02/09/2018','02/09/2018','Diamond Villa\\Wing-1\\302\\Avdhut Chandwadkar_100.0_148.pdf','08/09/2018',97,2,'18/08/2018 05:07:35 PM','02/09/2018 01:31:07 PM'),
  (149,24,12,NULL,296400,53352,0,349752,0,0,NULL,NULL,NULL,NULL,6,2,'08/09/2018 03:21:01 PM','10/09/2018 12:37:00 PM'),
  (150,24,23,'received',568100,102258,349752,1020110,400000,620110,'10/09/2018','10/09/2018','Dream Castle\\Wing-B\\204\\Samiksha Daware_35.0_150.pdf','17/09/2018',7,2,'08/09/2018 03:21:01 PM','10/09/2018 12:37:51 PM'),
  (151,24,15,NULL,370500,66690,0,437190,0,437190,NULL,NULL,NULL,NULL,8,2,'08/09/2018 03:21:01 PM','08/09/2018 03:21:01 PM'),
  (152,24,18,NULL,444600,80028,0,524628,0,524628,NULL,NULL,NULL,NULL,9,2,'08/09/2018 03:21:01 PM','08/09/2018 03:21:01 PM'),
  (153,24,22,NULL,543400,97812,0,641212,0,641212,NULL,NULL,NULL,NULL,10,2,'08/09/2018 03:21:01 PM','08/09/2018 03:21:01 PM'),
  (154,24,10,NULL,247000,44460,0,291460,0,291460,NULL,NULL,NULL,NULL,11,2,'08/09/2018 03:21:01 PM','08/09/2018 03:21:01 PM');

COMMIT;

#
# Data for the `disbursement_partial_payments` table  (LIMIT 0,500)
#

INSERT INTO `disbursement_partial_payments` (`partial_payment_id`, `partial_payment_description`, `partial_gross_amount`, `partial_gst_amount`, `partial_payment_paid_date`, `total_partial_amount`, `disbursement_id`, `created_datetime`, `updated_datetime`) VALUES 
  (1,'received by cash',0,0,'30/07/2018',100000,2,'30/07/2018 10:46:49 AM','30/07/2018 10:46:49 AM'),
  (2,'received by cheque',0,0,'30/07/2018',70000,2,'30/07/2018 10:47:23 AM','30/07/2018 10:47:23 AM'),
  (3,'received by cheque',0,0,'30/07/2018',166000,2,'30/07/2018 10:47:42 AM','30/07/2018 10:47:42 AM'),
  (4,'received by cash',0,0,'30/07/2018',80000,33,'30/07/2018 12:22:34 PM','30/07/2018 12:22:34 PM'),
  (5,'received by cheque',0,0,'30/07/2018',120000,33,'30/07/2018 12:23:00 PM','30/07/2018 12:23:00 PM'),
  (6,'received by cash',0,0,'30/07/2018',70000,3,'30/07/2018 02:11:23 PM','30/07/2018 02:11:23 PM'),
  (7,'received',0,0,'30/07/2018',150000,18,'30/07/2018 02:12:05 PM','30/07/2018 02:12:05 PM'),
  (8,'received by cheque',0,0,'30/07/2018',60000,18,'30/07/2018 02:12:52 PM','30/07/2018 02:12:52 PM'),
  (9,'received',0,0,'30/07/2018',20000,18,'30/07/2018 02:13:34 PM','30/07/2018 02:13:34 PM'),
  (10,'received',0,0,'30/07/2018',300000,34,'30/07/2018 02:18:52 PM','30/07/2018 02:18:52 PM'),
  (11,'received by cheque',0,0,'30/07/2018',150000,34,'30/07/2018 02:19:07 PM','30/07/2018 02:19:07 PM'),
  (12,'received',0,0,'31/07/2018',30000,40,'31/07/2018 09:04:45 AM','31/07/2018 09:04:45 AM'),
  (13,'received by cheque',0,0,'31/07/2018',580000,40,'31/07/2018 09:05:21 AM','31/07/2018 09:05:21 AM'),
  (14,'received by cheque',0,0,'31/07/2018',160000,51,'31/07/2018 09:09:08 AM','31/07/2018 09:09:08 AM'),
  (15,'received',0,0,'31/07/2018',40000,51,'31/07/2018 09:09:31 AM','31/07/2018 09:09:31 AM'),
  (16,'received',0,0,'01/08/2018',80000,19,'01/08/2018 02:34:08 PM','01/08/2018 02:34:08 PM'),
  (17,'received',0,0,'01/08/2018',168800,19,'01/08/2018 02:36:07 PM','01/08/2018 02:36:07 PM'),
  (18,'received by cheque',0,0,'02/08/2018',175000,34,'02/08/2018 10:39:49 AM','02/08/2018 10:39:49 AM'),
  (19,'received',0,0,'02/08/2018',255000,34,'02/08/2018 10:40:50 AM','02/08/2018 10:40:50 AM'),
  (20,'received by cheque',0,0,'03/08/2018',385000,40,'03/08/2018 10:37:02 AM','03/08/2018 10:37:02 AM'),
  (21,'received',0,0,'10/08/2018',290375,40,'10/08/2018 09:12:29 AM','10/08/2018 09:12:29 AM'),
  (22,'received',0,0,'10/08/2018',250000,46,'10/08/2018 09:45:38 AM','10/08/2018 09:45:38 AM'),
  (23,'received',0,0,'10/08/2018',300000,46,'10/08/2018 09:47:30 AM','10/08/2018 09:47:30 AM'),
  (24,'received',0,0,'10/08/2018',60000,34,'10/08/2018 03:13:12 PM','10/08/2018 03:13:12 PM'),
  (25,'received',0,0,'11/08/2018',40000,3,'11/08/2018 09:10:30 AM','11/08/2018 09:10:30 AM'),
  (26,'received',0,0,'12/08/2018',175000,117,'13/08/2018 10:34:00 AM','13/08/2018 10:34:00 AM'),
  (27,'received',0,0,'13/08/2018',40000,117,'13/08/2018 10:50:59 AM','13/08/2018 10:50:59 AM'),
  (28,'received',51600,8400,'12/08/2018',60000,46,'13/08/2018 12:29:37 PM','13/08/2018 12:29:37 PM'),
  (29,'received',326800,53200,'13/08/2018',380000,46,'13/08/2018 12:31:12 PM','13/08/2018 12:31:12 PM'),
  (30,'received',35131,5719,'13/08/2018',40850,46,'13/08/2018 12:32:05 PM','13/08/2018 12:32:05 PM'),
  (31,'received',278400,41600,'13/08/2018',320000,103,'13/08/2018 04:28:47 PM','13/08/2018 04:28:47 PM'),
  (32,'received',60200,9800,'16/08/2018',70000,46,'16/08/2018 02:45:23 PM','16/08/2018 02:45:23 PM'),
  (33,'received',83424,11376,'16/08/2018',94800,34,'16/08/2018 02:56:00 PM','16/08/2018 02:56:00 PM'),
  (35,'paid',43000,7000,'16/08/2018',50000,46,'16/08/2018 04:04:18 PM','16/08/2018 04:04:18 PM'),
  (36,'paid',36000,4000,'17/08/2018',40000,117,'17/08/2018 11:51:31 AM','17/08/2018 11:51:31 AM'),
  (37,'paid',117000,13000,'17/08/2018',130000,53,'17/08/2018 12:31:50 PM','17/08/2018 12:31:50 PM'),
  (38,'paid',36000,4000,'17/08/2018',40000,53,'17/08/2018 12:32:14 PM','17/08/2018 12:32:14 PM'),
  (39,'paid',59500,10500,'17/08/2018',70000,131,'17/08/2018 12:35:01 PM','17/08/2018 12:35:01 PM'),
  (40,'paid',264000,36000,'20/08/2018',300000,91,'20/08/2018 03:15:06 PM','20/08/2018 03:15:06 PM'),
  (41,'paid',102727,16723,'20/08/2018',119450,77,'20/08/2018 03:16:03 PM','20/08/2018 03:16:03 PM'),
  (42,'paid',213030,23670,'20/08/2018',236700,117,'20/08/2018 03:17:20 PM','20/08/2018 03:17:20 PM'),
  (43,'paid',43000,7000,'20/08/2018',50000,46,'20/08/2018 03:18:03 PM','20/08/2018 03:18:03 PM'),
  (44,'paid',26400,3600,'21/08/2018',30000,3,'22/08/2018 09:08:15 AM','22/08/2018 09:08:15 AM'),
  (45,'paid',43000,7000,'22/08/2018',50000,77,'22/08/2018 09:10:26 AM','22/08/2018 09:10:26 AM'),
  (46,'received',430000.86,70000.14,'01/09/2018',500001,144,'02/09/2018 01:26:27 PM','02/09/2018 01:26:27 PM'),
  (47,'received',154800,25200,'01/09/2018',180000,144,'02/09/2018 01:26:52 PM','02/09/2018 01:26:52 PM'),
  (48,'received',317167.14,51631.86,'02/09/2018',368799,144,'02/09/2018 01:27:00 PM','02/09/2018 01:27:00 PM'),
  (49,'paid',498800,81200,'01/09/2018',580000,148,'02/09/2018 01:30:24 PM','02/09/2018 01:30:24 PM'),
  (50,'paid',1290000,210000,'02/09/2018',1500000,148,'02/09/2018 01:30:51 PM','02/09/2018 01:30:51 PM'),
  (51,'paid',645000,105000,'02/09/2018',750000,148,'02/09/2018 01:31:07 PM','02/09/2018 01:31:07 PM'),
  (52,'received',328000,72000,'10/09/2018',400000,150,'10/09/2018 12:37:51 PM','10/09/2018 12:37:51 PM'),
  (53,'received by cheque',25800,4200,'10/09/2018',30000,46,'10/09/2018 12:45:38 PM','10/09/2018 12:45:38 PM'),
  (54,'paid',17200,2800,'14/09/2018',20000,46,'14/09/2018 11:33:53 AM','14/09/2018 11:33:53 AM');

COMMIT;

#
# Data for the `document` table  (LIMIT 0,500)
#

INSERT INTO `document` (`document_id`, `document_type_id`, `document_title`, `document_path`, `project_id`, `user_id`, `created_datetime`, `updated_datetime`, `is_remove`) VALUES 
  (1,1,NULL,'1529989259450_Invoice-26-07-Jul-2017.pdf',1,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (2,1,'Ground Floor','1529989326574_IMG_2426.jpg',2,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (3,2,NULL,'1529989618181_Invoice-26-07-Jul-2017.pdf',3,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (4,1,NULL,'1530079940096_Invoice-26-07-Jul-2017.pdf',4,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (5,1,'Ground Floor Plan','1530264869648_IMG_2426.jpg',5,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (6,1,'First floor plan','1530361052260_419392.jpg',6,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (7,1,'Floor plan','1530531194117_419392.jpg',7,2,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM',0),
  (8,1,'Third Floor Plan','1530945395897_third_Floor_plan.jpg',8,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (9,1,NULL,'1531385520475_third_Floor_plan.jpg',9,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (10,2,NULL,'1532317772633_Apartment-Floor-Plans-Download.pdf',10,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (11,1,NULL,'1533289171496_wing-b-parking-floor.jpg',11,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (12,1,'First Floor','1533886709438_wing-b-parking-floor.jpg',12,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (13,2,NULL,'1533887423420_peninsula-land-ltd-ashok-meadows-payment-plan-521129.jpeg',7,2,'10/08/2018 01:20:23 PM','10/08/2018 01:20:23 PM',0),
  (14,1,NULL,'1534592052227_wing-b-parking-floor.jpg',13,2,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM',0),
  (15,5,NULL,'1534933352443_samplepptx.pptx',14,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (16,4,NULL,'1535018773335_test.docx',8,2,'23/08/2018 03:36:13 PM','23/08/2018 03:36:13 PM',0),
  (17,7,'','1535019142810_18 Latitude PDF.pdf',8,2,'23/08/2018 03:42:22 PM','23/08/2018 03:42:22 PM',0),
  (18,4,NULL,'1535019227159_test.docx',8,2,'23/08/2018 03:43:47 PM','23/08/2018 03:43:47 PM',0),
  (19,4,NULL,'1535019255968_samplepptx.pptx',8,2,'23/08/2018 03:44:15 PM','23/08/2018 03:44:15 PM',0),
  (20,1,NULL,'1535088146420_wing-b-parking-floor.jpg',15,2,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM',0),
  (22,1,NULL,'1535515477026_disbursement amount.xlsx',16,2,'29/08/2018 09:34:37 AM','29/08/2018 09:34:37 AM',0),
  (23,1,NULL,'1536305181002_letterhead5.pdf',17,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (24,1,NULL,'1536311413672_letterhead4.pdf',18,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM',0),
  (25,1,NULL,'1536312028860_111.pdf',19,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (26,2,NULL,'1536313345343_letterhead1.pdf',20,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (27,1,'abc','1548820334301_18_Longitude_P.pdf',20,2,'30/01/2019 09:22:14 AM','30/01/2019 09:22:14 AM',0);

COMMIT;

#
# Data for the `document_type` table  (LIMIT 0,500)
#

INSERT INTO `document_type` (`document_type_id`, `document_type`) VALUES 
  (1,'Floor Plan'),
  (2,'Brochure'),
  (3,'Maha Rera Certificate'),
  (4,'abc'),
  (5,'Interior Design'),
  (7,'mmm');

COMMIT;

#
# Data for the `enquiry_details` table  (LIMIT 0,500)
#

INSERT INTO `enquiry_details` (`enquiry_id`, `first_name`, `last_name`, `mobile_no`, `landline_no`, `email`, `city`, `address`, `occupation`, `company`, `sales_person`, `project_id`, `property_type`, `property`, `property_area`, `budget_min`, `budget_max`, `reference`, `reference_name`, `followup_date`, `followup_status`, `remark`, `remark_date`, `user_id`, `is_remove`, `created_datetime`, `updated_datetime`) VALUES 
  (1,'Gauri','Kokate','9853958379','0234253534','gaurikokate2017@gmail.com','Nashik','Flat No.13, Shakambari Residency, Behind Neva mall, Ramkrishna Nagar, Makhmalabad Rd, Panchvati','Software Developer','YSM Software',0,2,1,1,346,0,30,1,NULL,'04/07/2018',1,NULL,NULL,0,0,'26/06/2018 10:35:31 AM','26/06/2018 10:35:31 AM'),
  (2,'Swapnil','Ahire','7935739857','0274272898','swapnil002ahire@gmail.com','Nashik','Rajmanya Society-1, A-wing, Peth Road, Panchvati','Software Developer','YSM Software',0,2,2,5,546,0,28,2,NULL,'04/07/2018',2,NULL,NULL,0,0,'26/06/2018 10:36:36 AM','26/06/2018 10:36:36 AM'),
  (3,'Kiran','Kadam','9857395739','0237478924','kirannkadam95@gmail.com','Nashik','Flat No.1 , Nandvihar Hou., so., Koushalya Nagar, Ramwadi, Panchvati','Software Developer','YSM Software',0,2,1,2,450,0,38,2,NULL,'04/07/2018',2,NULL,NULL,0,0,'26/06/2018 10:37:42 AM','26/06/2018 10:37:42 AM'),
  (4,'Shital','Patil','9874986479','0253487356','kalesavitav@gmail.com','Nashik','Jadhav Sankul, Ambad Satpur Link Rd, Chundale, Near Jadhav Sankul Township, Medage Nagar','Software Tester',NULL,0,2,1,2,450,0,32,2,NULL,'07/07/2018',2,NULL,NULL,2,0,'29/06/2018 11:01:57 AM','29/06/2018 11:01:57 AM'),
  (5,'Pallavi','Pawar','9478298428','0274367938','swapnil002ahire@gmail.com','Nashik','Gut No.23/1, Behind Indoline Furniture, Ambad,, Ambad Gaon Rd, Flora Town, MIDC Ambad','Employee','Indoline Industries Pvt Ltd',0,5,1,1,426.78,0,36,2,NULL,'27/07/2018',2,NULL,NULL,2,0,'29/06/2018 03:26:38 PM','20/07/2018 09:53:59 AM'),
  (6,'Pooja','Sangle','9385936593','0424254534','kalesavitav@gmail.com','Nashik','Gala No 5, Rajhans Apartment, Vikrikar Bhavan Rd, Girdhar Nagar, Prashant Nagar, Pathardi Phata','Manager','P.M.Electro Auto Pvt.Ltd',0,5,1,3,578.89,0,50,2,NULL,'20/07/2018',2,NULL,NULL,2,0,'29/06/2018 03:29:07 PM','13/07/2018 04:18:29 PM'),
  (7,'Tejas','Kale','9879824892','0274287426','kalesavitav@gmail.com','Nashik','Near Wasan Toyoto, NH 3, Wasan Nagar, Pathardi Phata','Employee','EPCOS India Private Ltd.',0,5,2,4,352.38,0,31,3,NULL,'07/07/2018',2,NULL,NULL,2,0,'29/06/2018 03:31:00 PM','29/06/2018 03:31:00 PM'),
  (8,'Gaurav','Kokate','9696868678','0324234353','savita@ysmsoftware.com','Nashik','No. 32, Asara, Vanvihar Colony, Vanvihar Colony, MIDC, Satpur Colony','Employee','ABB India Limited',0,5,2,4,346.89,0,37,2,NULL,'20/07/2018',3,NULL,NULL,2,0,'29/06/2018 03:33:35 PM','16/07/2018 11:31:17 AM'),
  (9,'Laxmikant','Wagh','9970435055',NULL,'laxmikantw@gmail.com','pune','Pune','Software Engineer','Volkswagen',0,6,1,1,500,0,50,3,'Laxmikant','07/07/2018',2,NULL,NULL,2,0,'30/06/2018 06:18:28 PM','30/06/2018 06:19:40 PM'),
  (10,'Ramesh','Pawar','6669696969',NULL,'lax020@yahoo.co.in','Pune','Kothrud','Doctor','Rubi Clinic',0,7,1,1,500,0,50,3,'Rahul','10/07/2018',2,NULL,NULL,2,0,'02/07/2018 05:07:09 PM','02/07/2018 05:07:09 PM'),
  (11,'Nilesh','Sonawane','7875073698',NULL,'nilson4588@gmail.com','Nashik','Nashik','Business',NULL,0,7,1,1,500,0,30,2,NULL,'13/07/2018',1,NULL,NULL,2,1,'05/07/2018 06:31:05 PM','05/07/2018 06:31:05 PM'),
  (12,'Priyanka','Pardeshi','8593845938',NULL,'kalesavitav@gmail.com','Nashik','Hari Krishna Plaza, 3rd & 4th Floor, Old Adgaon Naka, Opp Panchavati Bus, Depot, Abhang Nagar','Employee',NULL,0,8,1,1,555,0,30,2,NULL,'15/07/2018',1,NULL,NULL,2,0,'07/07/2018 12:14:18 PM','07/07/2018 12:14:18 PM'),
  (13,'Pratiksha','Pingle','9859357983',NULL,'kalesavitav@gmail.com','Nashik','Makhmalabad, Panchvati, Nashik','abc',NULL,0,9,1,1,380,0,35,2,NULL,'19/07/2018',2,NULL,NULL,2,0,'12/07/2018 02:56:28 PM','12/07/2018 02:56:28 PM'),
  (14,'Yogita','Pawar','8937539857',NULL,'kalesavitav@gmail.com','Dhule','Devpur','Teacher',NULL,0,2,1,2,500,0,30,2,NULL,'18/07/2018',2,NULL,NULL,2,0,'13/07/2018 10:59:15 AM','13/07/2018 10:59:15 AM'),
  (15,'Rupali','Kalankar','9865645213',NULL,'kalesavitav@gmail.com','Nashik','Bhairavnath Nagar, Nashik Road','Manager','BOI',0,2,2,5,546,0,32,3,NULL,'18/07/2018',3,NULL,NULL,2,0,'13/07/2018 11:24:08 AM','14/07/2018 04:27:05 PM'),
  (16,'Sagar','Kale','8958304990',NULL,'kalesavitav@gmail.com','Nashik','Makhmalabad','Mahindra',NULL,0,5,1,2,476.43,0,35,4,NULL,'18/07/2018',3,NULL,NULL,2,0,'13/07/2018 11:25:30 AM','16/07/2018 11:30:53 AM'),
  (17,'Anushka','Varma','9847923847','0324890249','kalesavitav@gmail.com','Nashik','Gangapur Road, Nashik','abc',NULL,0,8,1,1,563,0,33,4,NULL,'18/07/2018',2,NULL,NULL,2,0,'13/07/2018 11:51:52 AM','13/07/2018 11:51:52 AM'),
  (18,'Gayatri','Deshmukh','9374958379',NULL,'kalesavitav@gmail.com','Chalisgaon','Ashok Nagar','abc',NULL,0,5,2,4,352.38,0,22,4,NULL,'19/07/2018',3,NULL,NULL,2,0,'14/07/2018 04:44:06 PM','14/07/2018 04:44:18 PM'),
  (19,'abc','abc','8888888888',NULL,'kalesavitav@gmail.com','abc','abc','abc',NULL,0,9,1,1,380,0,25,2,NULL,'19/07/2018',3,NULL,NULL,2,0,'14/07/2018 04:47:02 PM','14/07/2018 04:47:07 PM'),
  (20,'Prajwal','Wagh','8593570395',NULL,'kalesavitav@gmail.com','Nashik','Vidya Nagar, M.Bad Road, Nashik','abc',NULL,0,9,2,4,444,0,25,4,NULL,'19/07/2018',3,NULL,NULL,2,0,'14/07/2018 05:00:14 PM','14/07/2018 05:00:18 PM'),
  (21,'Tanvi','Kothule','9847589037',NULL,'kalesavitav@gmail.com','Nashik','Meri Link Road','abc',NULL,0,2,1,2,500,0,24,3,NULL,'19/07/2018',3,NULL,NULL,2,0,'14/07/2018 05:01:47 PM','14/07/2018 05:01:53 PM'),
  (22,'Tanmay','Patil','9834573875',NULL,'kalesavitav@gmail.com','Nashik','Shinde Nagar, Nashik','abc',NULL,0,8,2,4,654,0,23,4,NULL,'19/07/2018',3,NULL,NULL,2,0,'14/07/2018 05:04:15 PM','14/07/2018 05:04:31 PM'),
  (23,'Snehal','Kedare','9789797979',NULL,'kalesavitav@gmail.com','Chandwad','Opp. Palod petrol pump, Mumbai Agra Highway, Bajartal','abc',NULL,0,5,2,4,352.38,0,30,3,NULL,'21/07/2018',2,NULL,NULL,2,0,'16/07/2018 03:19:37 PM','16/07/2018 03:19:37 PM'),
  (24,'Shubham','Gare','9789686856',NULL,'kalesavitav@gmail.com','Nashik','Dindori, Nashik District, Khedgaon','abc',NULL,0,2,1,2,450,0,39,2,NULL,'21/07/2018',2,NULL,NULL,2,0,'16/07/2018 03:21:48 PM','16/07/2018 03:21:48 PM'),
  (25,'Jyoti','Jadhav','9784938739',NULL,'kalesavitav@gmail.com','Nashik','Mumbai - Agra National Hwy, Panchwad Nagar, Ojhar','Employee',NULL,0,2,1,2,450,0,36,2,NULL,'24/07/2018',3,NULL,NULL,2,0,'16/07/2018 03:23:59 PM','20/07/2018 10:13:44 AM'),
  (26,'Hrishikesh','Gare','8447583495',NULL,'kalesavitav@gmail.com','Nashik','V/72, HAL Township, HAL, Ojhar','abc',NULL,0,2,1,1,450,0,24,4,NULL,'17/07/2018',3,NULL,NULL,2,0,'16/07/2018 03:26:46 PM','01/08/2018 01:19:53 PM'),
  (27,'Mrunmayi','Ghayal','9787895767',NULL,'kalesavitav@gmail.com','Nashik','Jail Rd, Teachers Colony, Dhara Society, Sant Dynaneshwar Nagar, Ashtvinayak Nagar','Designer',NULL,0,9,1,1,380,0,36,2,NULL,'17/07/2018',2,NULL,NULL,2,0,'16/07/2018 03:36:29 PM','16/07/2018 03:36:29 PM'),
  (28,'Pradnya','Nirbhavne','8987967867',NULL,'kalesavitav@gmail.com','Nashik','new station wadi, Deolali Camp, Deolali','Employee',NULL,0,2,2,5,546,0,33,1,NULL,'17/07/2018',3,NULL,NULL,2,0,'16/07/2018 03:41:38 PM','16/07/2018 03:42:09 PM'),
  (29,'Kajal','Barve','8678679678',NULL,'kalesavitav@gmail.com','Mumbai','Church Pakhadi Road No-1, Sahar Village, Near Nalanda Buddha Vihar, Andheri East','Manager',NULL,0,9,1,2,410,0,37,4,NULL,'17/07/2018',2,NULL,NULL,2,0,'16/07/2018 03:49:11 PM','16/07/2018 03:49:11 PM'),
  (30,'Abhishek','Kothule','9875398579','','swapnil002ahire@gmail.com','Nashik','Peth Road','Employee','',0,2,2,5,546,0,36,3,'','18/09/2018',1,'abcd abcd','05/09/2018 03:55:39 PM',2,0,'20/07/2018 10:11:33 AM','11/09/2018 01:17:19 PM'),
  (31,'Sandip','Aware','8789347539',NULL,'kalesavitav@gmail.com','Nashik','Amrutdham, Nashik','abc',NULL,0,6,1,1,500,0,35,2,NULL,'21/07/2018',2,NULL,NULL,2,0,'20/07/2018 04:39:15 PM','20/07/2018 04:39:15 PM'),
  (32,'Vrushali','Nikam','9879686867',NULL,'savita@ysmsoftware.com','Nashik','Gangapur Road, Pumping Station, Opp. Shalimar Society Garden, Sahdev Nagar','Lawyer',NULL,0,2,1,2,500,0,29,2,NULL,'22/07/2018',2,NULL,NULL,2,0,'21/07/2018 10:30:43 AM','21/07/2018 10:30:43 AM'),
  (33,'Sayali','Kakad','9588586786',NULL,'gauri@ysmsoftware.com','Nashik','Jail Road, Samrudha Nagari, Borade Nagar','Fashion Designer',NULL,0,2,1,2,450,0,42,2,NULL,'22/07/2018',3,NULL,NULL,2,0,'21/07/2018 10:31:15 AM','21/07/2018 10:31:31 AM'),
  (34,'Avdhut','Chandwadkar','9374982749','0131283429','avi@gmail.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','Doctor',NULL,0,10,1,2,444,0,36,1,NULL,'23/08/2018',2,NULL,NULL,2,0,'18/08/2018 05:06:45 PM','18/08/2018 05:06:45 PM'),
  (35,'Ajinkya','Rakibe','8476490560',NULL,'savita@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','Doctor',NULL,0,2,1,1,450,0,36,2,NULL,'09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:52:11 PM','04/09/2018 12:52:11 PM'),
  (36,'Sapna','Gaikwad','5739570397',NULL,'savita@ysmsoftware.com','Nashik','Shanti nagar, Makhmalabad Road, Pamchvati, Nashik','Lawyer',NULL,0,2,1,2,500,0,40,1,NULL,'09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:53:12 PM','04/09/2018 12:53:12 PM'),
  (37,'Anjali','Kashyap','8345206894',NULL,'savita@ysmsoftware.com','Nashik','Shanti nagar, Makhmalabad Road, Pamchvati, Nashik','CA',NULL,0,2,1,2,500,0,48,3,NULL,'09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:54:03 PM','04/09/2018 12:54:03 PM'),
  (38,'Aarohi','Agnihotri','9475934857',NULL,'savita@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','Software Developer','',0,2,1,2,450,0,42,3,NULL,'09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:55:05 PM','04/09/2018 12:55:05 PM'),
  (39,'Tanaya','Jadhav','6742959287',NULL,'savita@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','Software Tester',NULL,0,2,1,2,500,0,45,4,'abc','09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:56:15 PM','04/09/2018 12:56:15 PM'),
  (40,'Virat','Deshmukh','9259263598','','gauri@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','CA','',0,2,2,5,546,0,36,3,'','09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:56:56 PM','04/09/2018 12:56:56 PM'),
  (41,'Samiksha','Daware','7894547640',NULL,'savita@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','Doctor',NULL,0,2,2,5,546,0,32,3,NULL,'09/09/2018',2,NULL,NULL,2,0,'04/09/2018 12:57:47 PM','04/09/2018 12:57:47 PM'),
  (42,'Priti','Wagh','6453984579',NULL,'savita@ysmsoftware.com','Nashik','Shanti nagar, Makhmalabad Road, Pamchvati, Nashik','Software Developer',NULL,0,2,2,5,546,0,33,2,NULL,'09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:58:34 PM','04/09/2018 12:58:34 PM'),
  (43,'Suraj','Patil','9573947593','','gauri@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','CA','',0,2,2,5,546,0,34,2,'','09/09/2018',1,NULL,NULL,2,0,'04/09/2018 12:59:15 PM','04/09/2018 12:59:15 PM'),
  (44,'Sanskruti','Rakibe','9365936985',NULL,'savita@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','Software Developer',NULL,0,2,2,5,546,0,31,2,NULL,'09/09/2018',1,NULL,NULL,2,0,'04/09/2018 01:00:19 PM','04/09/2018 01:00:19 PM'),
  (45,'Gayatri','Karpe','9346375893',NULL,'savita@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','abc','',0,2,1,2,500,0,37,3,NULL,'10/09/2018',1,NULL,NULL,2,0,'05/09/2018 09:38:05 AM','05/09/2018 09:38:05 AM'),
  (46,'Naina','Sandhan','9789675757','','savita@ysmsoftware.com','Nashik','Shanti nagar, Makhmalabad Road, Pamchvati, Nashik','Software Developer',NULL,0,5,1,1,370.57,0,40,3,NULL,'20/09/2018',1,NULL,NULL,2,0,'15/09/2018 09:10:34 AM','15/09/2018 09:10:34 AM'),
  (47,'Ashwini','Jadhav','4353785735','9865431312','savita@yssoftware.com','Nashik','Shanti nagar, Makhmalabad Road, Pamchvati, Nashik','Software Developer',NULL,0,2,1,1,380,0,45,3,NULL,'22/09/2018',1,NULL,NULL,2,0,'17/04/2018 03:50:15 PM','17/09/2018 03:50:15 PM'),
  (48,'Shubhangi','Kakad','7983957834',NULL,'savita@ysmsoftware.com','Nashik','Shanti nagar, Makhmalabad Road, Pamchvati, Nashik','Lawyer',NULL,0,2,1,1,450,0,32,2,NULL,'22/09/2018',1,NULL,NULL,2,0,'17/08/2018 03:50:56 PM','17/09/2018 03:50:56 PM'),
  (49,'Sagar','Kale','8586786786',NULL,'savita@ysmsoftware.com','Nashik','Model Colony, Shivaji Nagar, Pune, Maharashtra 411016','CA',NULL,0,2,2,5,546,0,40,3,NULL,'22/09/2018',1,NULL,NULL,2,0,'17/05/2018 03:52:25 PM','17/09/2018 03:52:25 PM'),
  (50,'Avnish','Rakibe','8546989684','8465421321','gauri@ysmsoftware.com','Nashik','Nashik','Software Tester','',0,5,1,2,434.65,0,32,3,NULL,'22/11/2018',1,NULL,NULL,2,0,'17/11/2018 10:11:10 AM','17/11/2018 10:11:10 AM');

COMMIT;

#
# Data for the `flats` table  (LIMIT 0,500)
#

INSERT INTO `flats` (`flat_id`, `flat_number`, `floor_number`, `floor_name`, `project_id`, `wing_id`, `wing_details_id`) VALUES 
  (13,101,1,'Floor 1',1,2,4),
  (14,102,1,'Floor 1',1,2,4),
  (15,103,1,'Floor 1',1,2,4),
  (16,104,1,'Floor 1',1,2,4),
  (17,105,1,'Floor 1',1,2,4),
  (18,106,1,'Floor 1',1,2,4),
  (19,201,2,'Floor 2',1,2,5),
  (20,202,2,'Floor 2',1,2,5),
  (21,203,2,'Floor 2',1,2,5),
  (22,204,2,'Floor 2',1,2,5),
  (23,205,2,'Floor 2',1,2,5),
  (24,206,2,'Floor 2',1,2,5),
  (25,301,3,'Floor 3',1,2,6),
  (26,302,3,'Floor 3',1,2,6),
  (27,303,3,'Floor 3',1,2,6),
  (28,304,3,'Floor 3',1,2,6),
  (29,1,-1,'Basement',1,3,7),
  (30,2,-1,'Basement',1,3,7),
  (31,3,-1,'Basement',1,3,7),
  (32,4,-1,'Basement',1,3,7),
  (33,5,-1,'Basement',1,3,7),
  (34,6,-1,'Basement',1,3,7),
  (35,1,0,'Ground Floor',1,3,8),
  (36,2,0,'Ground Floor',1,3,8),
  (37,3,0,'Ground Floor',1,3,8),
  (38,4,0,'Ground Floor',1,3,8),
  (39,101,1,'Floor 1',1,3,9),
  (40,102,1,'Floor 1',1,3,9),
  (41,103,1,'Floor 1',1,3,9),
  (42,104,1,'Floor 1',1,3,9),
  (43,201,2,'Floor 2',1,3,10),
  (44,202,2,'Floor 2',1,3,10),
  (45,203,2,'Floor 2',1,3,10),
  (46,204,2,'Floor 2',1,3,10),
  (47,205,2,'Floor 2',1,3,10),
  (48,206,2,'Floor 2',1,3,10),
  (49,301,3,'Floor 3',1,3,11),
  (50,302,3,'Floor 3',1,3,11),
  (51,303,3,'Floor 3',1,3,11),
  (52,304,3,'Floor 3',1,3,11),
  (53,305,3,'Floor 3',1,3,11),
  (54,306,3,'Floor 3',1,3,11),
  (55,11,0,'Ground Floor',2,4,12),
  (56,12,0,'Ground Floor',2,4,12),
  (57,13,0,'Ground Floor',2,4,12),
  (58,4,0,'Ground Floor',2,4,12),
  (59,5,0,'Ground Floor',2,4,12),
  (60,6,0,'Ground Floor',2,4,12),
  (61,7,0,'Ground Floor',2,4,12),
  (62,8,0,'Ground Floor',2,4,12),
  (63,101,1,'Floor 1',2,4,13),
  (64,102,1,'Floor 1',2,4,13),
  (65,103,1,'Floor 1',2,4,13),
  (66,104,1,'Floor 1',2,4,13),
  (67,105,1,'Floor 1',2,4,13),
  (68,106,1,'Floor 1',2,4,13),
  (69,107,1,'Floor 1',2,4,13),
  (70,108,1,'Floor 1',2,4,13),
  (71,109,1,'Floor 1',2,4,13),
  (72,110,1,'Floor 1',2,4,13),
  (73,201,2,'Floor 2',2,4,14),
  (74,202,2,'Floor 2',2,4,14),
  (75,203,2,'Floor 2',2,4,14),
  (76,204,2,'Floor 2',2,4,14),
  (77,205,2,'Floor 2',2,4,14),
  (78,206,2,'Floor 2',2,4,14),
  (79,207,2,'Floor 2',2,4,14),
  (80,208,2,'Floor 2',2,4,14),
  (81,101,1,'Floor 1',2,5,15),
  (82,102,1,'Floor 1',2,5,15),
  (83,103,1,'Floor 1',2,5,15),
  (84,104,1,'Floor 1',2,5,15),
  (85,105,1,'Floor 1',2,5,15),
  (86,106,1,'Floor 1',2,5,15),
  (87,107,1,'Floor 1',2,5,15),
  (88,108,1,'Floor 1',2,5,15),
  (89,201,2,'Floor 2',2,5,16),
  (90,202,2,'Floor 2',2,5,16),
  (91,203,2,'Floor 2',2,5,16),
  (92,204,2,'Floor 2',2,5,16),
  (93,205,2,'Floor 2',2,5,16),
  (94,206,2,'Floor 2',2,5,16),
  (95,207,2,'Floor 2',2,5,16),
  (96,208,2,'Floor 2',2,5,16),
  (97,209,2,'Floor 2',2,5,16),
  (98,210,2,'Floor 2',2,5,16),
  (99,301,3,'Floor 3',2,5,17),
  (100,302,3,'Floor 3',2,5,17),
  (101,303,3,'Floor 3',2,5,17),
  (102,304,3,'Floor 3',2,5,17),
  (103,305,3,'Floor 3',2,5,17),
  (104,306,3,'Floor 3',2,5,17),
  (105,101,1,'Floor 1',2,6,18),
  (106,102,1,'Floor 1',2,6,18),
  (107,103,1,'Floor 1',2,6,18),
  (108,104,1,'Floor 1',2,6,18),
  (109,105,1,'Floor 1',2,6,18),
  (110,106,1,'Floor 1',2,6,18),
  (111,107,1,'Floor 1',2,6,18),
  (112,108,1,'Floor 1',2,6,18),
  (113,109,1,'Floor 1',2,6,18),
  (114,110,1,'Floor 1',2,6,18),
  (115,201,2,'Floor 2',2,6,19),
  (116,202,2,'Floor 2',2,6,19),
  (117,203,2,'Floor 2',2,6,19),
  (118,204,2,'Floor 2',2,6,19),
  (119,205,2,'Floor 2',2,6,19),
  (120,206,2,'Floor 2',2,6,19),
  (121,207,2,'Floor 2',2,6,19),
  (122,208,2,'Floor 2',2,6,19),
  (123,301,3,'Floor 3',2,6,20),
  (124,302,3,'Floor 3',2,6,20),
  (125,303,3,'Floor 3',2,6,20),
  (126,304,3,'Floor 3',2,6,20),
  (127,305,3,'Floor 3',2,6,20),
  (128,1,0,'Ground Floor',3,7,21),
  (129,2,0,'Ground Floor',3,7,21),
  (130,3,0,'Ground Floor',3,7,21),
  (131,4,0,'Ground Floor',3,7,21),
  (132,101,1,'Floor 1',3,7,22),
  (133,102,1,'Floor 1',3,7,22),
  (134,103,1,'Floor 1',3,7,22),
  (135,104,1,'Floor 1',3,7,22),
  (136,105,1,'Floor 1',3,7,22),
  (137,106,1,'Floor 1',3,7,22),
  (138,107,1,'Floor 1',3,7,22),
  (139,108,1,'Floor 1',3,7,22),
  (140,201,2,'Floor 2',3,7,23),
  (141,202,2,'Floor 2',3,7,23),
  (142,203,2,'Floor 2',3,7,23),
  (143,204,2,'Floor 2',3,7,23),
  (144,205,2,'Floor 2',3,7,23),
  (145,206,2,'Floor 2',3,7,23),
  (146,101,1,'Floor 1',3,8,24),
  (147,102,1,'Floor 1',3,8,24),
  (148,103,1,'Floor 1',3,8,24),
  (149,104,1,'Floor 1',3,8,24),
  (150,105,1,'Floor 1',3,8,24),
  (151,106,1,'Floor 1',3,8,24),
  (152,107,1,'Floor 1',3,8,24),
  (153,108,1,'Floor 1',3,8,24),
  (154,201,2,'Floor 2',3,8,25),
  (155,202,2,'Floor 2',3,8,25),
  (156,203,2,'Floor 2',3,8,25),
  (157,204,2,'Floor 2',3,8,25),
  (158,205,2,'Floor 2',3,8,25),
  (159,206,2,'Floor 2',3,8,25),
  (160,207,2,'Floor 2',3,8,25),
  (161,208,2,'Floor 2',3,8,25),
  (162,301,3,'Floor 3',3,8,26),
  (163,302,3,'Floor 3',3,8,26),
  (164,303,3,'Floor 3',3,8,26),
  (165,304,3,'Floor 3',3,8,26),
  (166,305,3,'Floor 3',3,8,26),
  (167,306,3,'Floor 3',3,8,26),
  (168,307,3,'Floor 3',3,8,26),
  (169,308,3,'Floor 3',3,8,26),
  (170,101,1,'Floor 1',3,9,27),
  (171,102,1,'Floor 1',3,9,27),
  (172,103,1,'Floor 1',3,9,27),
  (173,104,1,'Floor 1',3,9,27),
  (174,105,1,'Floor 1',3,9,27),
  (175,106,1,'Floor 1',3,9,27),
  (176,107,1,'Floor 1',3,9,27),
  (177,108,1,'Floor 1',3,9,27),
  (178,109,1,'Floor 1',3,9,27),
  (179,110,1,'Floor 1',3,9,27),
  (180,111,1,'Floor 1',3,9,27),
  (181,112,1,'Floor 1',3,9,27),
  (182,201,2,'Floor 2',3,9,28),
  (183,202,2,'Floor 2',3,9,28),
  (184,203,2,'Floor 2',3,9,28),
  (185,204,2,'Floor 2',3,9,28),
  (186,205,2,'Floor 2',3,9,28),
  (187,206,2,'Floor 2',3,9,28),
  (188,207,2,'Floor 2',3,9,28),
  (189,208,2,'Floor 2',3,9,28),
  (190,209,2,'Floor 2',3,9,28),
  (191,210,2,'Floor 2',3,9,28),
  (192,211,2,'Floor 2',3,9,28),
  (193,212,2,'Floor 2',3,9,28),
  (194,101,1,'Floor 1',1,1,29),
  (195,102,1,'Floor 1',1,1,29),
  (196,103,1,'Floor 1',1,1,29),
  (197,104,1,'Floor 1',1,1,29),
  (198,201,2,'Floor 2',1,1,30),
  (199,202,2,'Floor 2',1,1,30),
  (200,203,2,'Floor 2',1,1,30),
  (201,204,2,'Floor 2',1,1,30),
  (202,301,3,'Floor 3',1,1,31),
  (203,302,3,'Floor 3',1,1,31),
  (204,303,3,'Floor 3',1,1,31),
  (205,304,3,'Floor 3',1,1,31),
  (206,1,0,'Ground Floor',1,1,32),
  (207,2,0,'Ground Floor',1,1,32),
  (208,3,0,'Ground Floor',1,1,32),
  (209,4,0,'Ground Floor',1,1,32),
  (210,5,0,'Ground Floor',1,1,32),
  (211,6,0,'Ground Floor',1,1,32),
  (212,7,0,'Ground Floor',1,1,32),
  (213,1,-1,'Basement',4,10,33),
  (214,2,-1,'Basement',4,10,33),
  (215,3,-1,'Basement',4,10,33),
  (216,4,-1,'Basement',4,10,33),
  (217,1,0,'Ground Floor',4,10,34),
  (218,2,0,'Ground Floor',4,10,34),
  (219,3,0,'Ground Floor',4,10,34),
  (220,4,0,'Ground Floor',4,10,34),
  (221,101,1,'Floor 1',4,10,35),
  (222,102,1,'Floor 1',4,10,35),
  (223,103,1,'Floor 1',4,10,35),
  (224,104,1,'Floor 1',4,10,35),
  (225,105,1,'Floor 1',4,10,35),
  (226,106,1,'Floor 1',4,10,35),
  (227,201,2,'Floor 2',4,10,36),
  (228,202,2,'Floor 2',4,10,36),
  (229,203,2,'Floor 2',4,10,36),
  (230,204,2,'Floor 2',4,10,36),
  (231,101,1,'Floor 1',4,11,37),
  (232,102,1,'Floor 1',4,11,37),
  (233,103,1,'Floor 1',4,11,37),
  (234,104,1,'Floor 1',4,11,37),
  (235,105,1,'Floor 1',4,11,37),
  (236,106,1,'Floor 1',4,11,37),
  (237,201,2,'Floor 2',4,11,38),
  (238,202,2,'Floor 2',4,11,38),
  (239,203,2,'Floor 2',4,11,38),
  (240,204,2,'Floor 2',4,11,38),
  (241,205,2,'Floor 2',4,11,38),
  (242,206,2,'Floor 2',4,11,38),
  (243,301,3,'Floor 3',4,11,39),
  (244,302,3,'Floor 3',4,11,39),
  (245,303,3,'Floor 3',4,11,39),
  (246,304,3,'Floor 3',4,11,39),
  (247,1,0,'Ground Floor',4,12,40),
  (248,2,0,'Ground Floor',4,12,40),
  (249,101,1,'Floor 1',4,12,41),
  (250,102,1,'Floor 1',4,12,41),
  (251,103,1,'Floor 1',4,12,41),
  (252,104,1,'Floor 1',4,12,41),
  (253,105,1,'Floor 1',4,12,41),
  (254,106,1,'Floor 1',4,12,41),
  (255,201,2,'Floor 2',4,12,42),
  (256,202,2,'Floor 2',4,12,42),
  (257,203,2,'Floor 2',4,12,42),
  (258,204,2,'Floor 2',4,12,42),
  (259,1,0,'Ground Floor',5,13,43),
  (260,2,0,'Ground Floor',5,13,43),
  (261,3,0,'Ground Floor',5,13,43),
  (262,4,0,'Ground Floor',5,13,44),
  (263,5,0,'Ground Floor',5,13,44),
  (264,6,0,'Ground Floor',5,13,44),
  (265,7,0,'Ground Floor',5,13,44),
  (266,8,0,'Ground Floor',5,13,44),
  (267,9,0,'Ground Floor',5,13,45),
  (268,10,0,'Ground Floor',5,13,45),
  (269,11,0,'Ground Floor',5,13,45),
  (270,12,0,'Ground Floor',5,13,45),
  (271,13,0,'Ground Floor',5,13,46),
  (272,14,0,'Ground Floor',5,13,46),
  (273,101,1,'Floor 1',5,13,47),
  (274,102,1,'Floor 1',5,13,47),
  (275,103,1,'Floor 1',5,13,47),
  (276,104,1,'Floor 1',5,13,47),
  (277,105,1,'Floor 1',5,13,48),
  (278,106,1,'Floor 1',5,13,48),
  (279,107,1,'Floor 1',5,13,49),
  (280,108,1,'Floor 1',5,13,49),
  (281,109,1,'Floor 1',5,13,49),
  (282,110,1,'Floor 1',5,13,49),
  (283,201,2,'Floor 2',5,13,50),
  (284,202,2,'Floor 2',5,13,50),
  (285,203,2,'Floor 2',5,13,50),
  (286,204,2,'Floor 2',5,13,50),
  (287,205,2,'Floor 2',5,13,50),
  (288,206,2,'Floor 2',5,13,50),
  (289,207,2,'Floor 2',5,13,50),
  (290,208,2,'Floor 2',5,13,51),
  (291,209,2,'Floor 2',5,13,51),
  (292,210,2,'Floor 2',5,13,51),
  (293,211,2,'Floor 2',5,13,51),
  (294,212,2,'Floor 2',5,13,51),
  (295,301,3,'Floor 3',5,13,52),
  (296,302,3,'Floor 3',5,13,52),
  (297,303,3,'Floor 3',5,13,52),
  (298,304,3,'Floor 3',5,13,52),
  (299,305,3,'Floor 3',5,13,52),
  (300,401,4,'Floor 4',5,13,53),
  (301,402,4,'Floor 4',5,13,53),
  (302,403,4,'Floor 4',5,13,53),
  (303,404,4,'Floor 4',5,13,53),
  (304,405,4,'Floor 4',5,13,53),
  (305,406,4,'Floor 4',5,13,53),
  (306,1,0,'Ground Floor',5,14,54),
  (307,2,0,'Ground Floor',5,14,54),
  (308,3,0,'Ground Floor',5,14,54),
  (309,4,0,'Ground Floor',5,14,54),
  (310,5,0,'Ground Floor',5,14,54),
  (311,6,0,'Ground Floor',5,14,54),
  (312,7,0,'Ground Floor',5,14,54),
  (313,8,0,'Ground Floor',5,14,54),
  (314,101,1,'Floor 1',5,14,55),
  (315,102,1,'Floor 1',5,14,55),
  (316,103,1,'Floor 1',5,14,55),
  (317,104,1,'Floor 1',5,14,55),
  (318,105,1,'Floor 1',5,14,55),
  (319,106,1,'Floor 1',5,14,55),
  (320,107,1,'Floor 1',5,14,56),
  (321,108,1,'Floor 1',5,14,56),
  (322,109,1,'Floor 1',5,14,56),
  (323,110,1,'Floor 1',5,14,56),
  (324,111,1,'Floor 1',5,14,56),
  (325,201,2,'Floor 2',5,14,57),
  (326,202,2,'Floor 2',5,14,57),
  (327,203,2,'Floor 2',5,14,57),
  (328,204,2,'Floor 2',5,14,57),
  (329,205,2,'Floor 2',5,14,57),
  (330,206,2,'Floor 2',5,14,57),
  (331,207,2,'Floor 2',5,14,57),
  (332,208,2,'Floor 2',5,14,58),
  (333,209,2,'Floor 2',5,14,58),
  (334,210,2,'Floor 2',5,14,58),
  (335,211,2,'Floor 2',5,14,58),
  (336,301,3,'Floor 3',5,14,59),
  (337,302,3,'Floor 3',5,14,59),
  (338,303,3,'Floor 3',5,14,59),
  (339,304,3,'Floor 3',5,14,59),
  (340,305,3,'Floor 3',5,14,59),
  (341,306,3,'Floor 3',5,14,59),
  (342,307,3,'Floor 3',5,14,60),
  (343,308,3,'Floor 3',5,14,60),
  (344,309,3,'Floor 3',5,14,60),
  (345,310,3,'Floor 3',5,14,60),
  (358,101,1,'Floor 1',6,16,67),
  (359,102,1,'Floor 1',6,16,67),
  (360,103,1,'Floor 1',6,16,67),
  (361,104,1,'Floor 1',6,16,67),
  (362,201,2,'Floor 2',6,16,68),
  (363,202,2,'Floor 2',6,16,68),
  (364,203,2,'Floor 2',6,16,68),
  (365,204,2,'Floor 2',6,16,68),
  (366,301,3,'Floor 3',6,16,69),
  (367,302,3,'Floor 3',6,16,69),
  (368,303,3,'Floor 3',6,16,69),
  (369,304,3,'Floor 3',6,16,69),
  (370,101,1,'Floor 1',6,15,70),
  (371,102,1,'Floor 1',6,15,70),
  (372,103,1,'Floor 1',6,15,71),
  (373,104,1,'Floor 1',6,15,71),
  (374,201,2,'Floor 2',6,15,72),
  (375,202,2,'Floor 2',6,15,72),
  (376,203,2,'Floor 2',6,15,73),
  (377,204,2,'Floor 2',6,15,73),
  (378,301,3,'Floor 3',6,15,74),
  (379,302,3,'Floor 3',6,15,74),
  (380,303,3,'Floor 3',6,15,75),
  (381,304,3,'Floor 3',6,15,75),
  (382,101,1,'Floor 1',7,17,76),
  (383,102,1,'Floor 1',7,17,76),
  (384,103,1,'Floor 1',7,17,76),
  (385,104,1,'Floor 1',7,17,76),
  (386,201,2,'Floor 2',7,17,77),
  (387,202,2,'Floor 2',7,17,77),
  (388,203,2,'Floor 2',7,17,77),
  (389,204,2,'Floor 2',7,17,77),
  (390,101,1,'Floor 1',8,18,78),
  (391,102,1,'Floor 1',8,18,78),
  (392,103,1,'Floor 1',8,18,78),
  (393,104,1,'Floor 1',8,18,78),
  (394,105,1,'Floor 1',8,18,78),
  (395,106,1,'Floor 1',8,18,78),
  (396,107,1,'Floor 1',8,18,78),
  (397,108,1,'Floor 1',8,18,78),
  (398,109,1,'Floor 1',8,18,78),
  (399,201,2,'Floor 2',8,18,79),
  (400,202,2,'Floor 2',8,18,79),
  (401,203,2,'Floor 2',8,18,79),
  (402,204,2,'Floor 2',8,18,79),
  (403,205,2,'Floor 2',8,18,79),
  (404,206,2,'Floor 2',8,18,79),
  (405,207,2,'Floor 2',8,18,79),
  (406,208,2,'Floor 2',8,18,79),
  (407,209,2,'Floor 2',8,18,79),
  (408,301,3,'Floor 3',8,18,80),
  (409,302,3,'Floor 3',8,18,80),
  (410,303,3,'Floor 3',8,18,80),
  (411,304,3,'Floor 3',8,18,80),
  (412,305,3,'Floor 3',8,18,80),
  (413,306,3,'Floor 3',8,18,80),
  (414,307,3,'Floor 3',8,18,80),
  (415,1,0,'Ground Floor',8,19,81),
  (416,2,0,'Ground Floor',8,19,81),
  (417,3,0,'Ground Floor',8,19,81),
  (418,4,0,'Ground Floor',8,19,81),
  (419,5,0,'Ground Floor',8,19,81),
  (420,6,0,'Ground Floor',8,19,81),
  (421,7,0,'Ground Floor',8,19,81),
  (422,8,0,'Ground Floor',8,19,81),
  (423,9,0,'Ground Floor',8,19,81),
  (424,101,1,'Floor 1',8,19,82),
  (425,102,1,'Floor 1',8,19,82),
  (426,103,1,'Floor 1',8,19,82),
  (427,104,1,'Floor 1',8,19,82),
  (428,105,1,'Floor 1',8,19,82),
  (429,106,1,'Floor 1',8,19,82),
  (430,107,1,'Floor 1',8,19,82),
  (431,201,2,'Floor 2',8,19,83),
  (432,202,2,'Floor 2',8,19,83),
  (433,203,2,'Floor 2',8,19,83),
  (434,204,2,'Floor 2',8,19,83),
  (435,205,2,'Floor 2',8,19,83),
  (436,206,2,'Floor 2',8,19,83),
  (437,207,2,'Floor 2',8,19,83),
  (438,101,1,'Floor 1',9,20,84),
  (439,102,1,'Floor 1',9,20,84),
  (440,103,1,'Floor 1',9,20,84),
  (441,104,1,'Floor 1',9,20,84),
  (442,105,1,'Floor 1',9,20,84),
  (443,106,1,'Floor 1',9,20,84),
  (444,107,1,'Floor 1',9,20,84),
  (445,108,1,'Floor 1',9,20,84),
  (446,109,1,'Floor 1',9,20,84),
  (447,110,1,'Floor 1',9,20,84),
  (448,201,2,'Floor 2',9,20,85),
  (449,202,2,'Floor 2',9,20,85),
  (450,203,2,'Floor 2',9,20,85),
  (451,204,2,'Floor 2',9,20,85),
  (452,205,2,'Floor 2',9,20,85),
  (453,206,2,'Floor 2',9,20,85),
  (454,207,2,'Floor 2',9,20,85),
  (455,208,2,'Floor 2',9,20,85),
  (456,301,3,'Floor 3',9,20,86),
  (457,302,3,'Floor 3',9,20,86),
  (458,303,3,'Floor 3',9,20,86),
  (459,304,3,'Floor 3',9,20,86),
  (460,305,3,'Floor 3',9,20,86),
  (461,306,3,'Floor 3',9,20,86),
  (462,307,3,'Floor 3',9,20,86),
  (463,308,3,'Floor 3',9,20,86),
  (464,309,3,'Floor 3',9,20,86),
  (465,401,4,'Floor 4',9,20,87),
  (466,402,4,'Floor 4',9,20,87),
  (467,403,4,'Floor 4',9,20,87),
  (468,404,4,'Floor 4',9,20,87),
  (469,405,4,'Floor 4',9,20,87),
  (470,406,4,'Floor 4',9,20,87),
  (471,407,4,'Floor 4',9,20,87),
  (472,501,5,'Floor 5',9,20,88),
  (473,502,5,'Floor 5',9,20,88),
  (474,503,5,'Floor 5',9,20,88),
  (475,504,5,'Floor 5',9,20,88),
  (476,505,5,'Floor 5',9,20,88),
  (477,506,5,'Floor 5',9,20,88),
  (478,507,5,'Floor 5',9,20,88),
  (479,508,5,'Floor 5',9,20,88),
  (500,1,0,'Ground Floor',9,21,92),
  (501,2,0,'Ground Floor',9,21,92),
  (502,3,0,'Ground Floor',9,21,92),
  (503,4,0,'Ground Floor',9,21,92),
  (504,5,0,'Ground Floor',9,21,92),
  (505,6,0,'Ground Floor',9,21,92),
  (506,101,1,'Floor 1',9,21,93),
  (507,102,1,'Floor 1',9,21,93),
  (508,103,1,'Floor 1',9,21,93),
  (509,104,1,'Floor 1',9,21,93),
  (510,105,1,'Floor 1',9,21,93),
  (511,106,1,'Floor 1',9,21,93),
  (512,201,2,'Floor 2',9,21,94),
  (513,202,2,'Floor 2',9,21,94),
  (514,203,2,'Floor 2',9,21,94),
  (515,204,2,'Floor 2',9,21,94),
  (516,205,2,'Floor 2',9,21,94),
  (517,206,2,'Floor 2',9,21,94),
  (518,207,2,'Floor 2',9,21,94),
  (523,1,0,'Ground Floor',9,22,96),
  (524,2,0,'Ground Floor',9,22,96),
  (525,3,0,'Ground Floor',9,22,96),
  (526,4,0,'Ground Floor',9,22,96),
  (527,101,1,'Floor 1',9,24,97),
  (528,102,1,'Floor 1',9,24,97),
  (529,103,1,'Floor 1',9,24,97),
  (530,101,1,'Floor 1',10,25,98),
  (531,102,1,'Floor 1',10,25,98),
  (532,103,1,'Floor 1',10,25,98),
  (533,104,1,'Floor 1',10,25,98),
  (534,105,1,'Floor 1',10,25,98),
  (535,106,1,'Floor 1',10,25,98),
  (536,201,2,'Floor 2',10,25,99),
  (537,202,2,'Floor 2',10,25,99),
  (538,203,2,'Floor 2',10,25,99),
  (539,204,2,'Floor 2',10,25,99),
  (540,205,2,'Floor 2',10,25,99),
  (541,206,2,'Floor 2',10,25,99),
  (542,207,2,'Floor 2',10,25,99),
  (543,208,2,'Floor 2',10,25,99),
  (544,301,3,'Floor 3',10,25,100),
  (545,302,3,'Floor 3',10,25,100),
  (546,303,3,'Floor 3',10,25,100),
  (547,304,3,'Floor 3',10,25,100),
  (548,305,3,'Floor 3',10,25,100);

COMMIT;

#
# Data for the `flats` table  (LIMIT 500,500)
#

INSERT INTO `flats` (`flat_id`, `flat_number`, `floor_number`, `floor_name`, `project_id`, `wing_id`, `wing_details_id`) VALUES 
  (549,306,3,'Floor 3',10,25,100),
  (550,307,3,'Floor 3',10,25,100),
  (551,101,1,'Floor 1',10,26,101),
  (552,102,1,'Floor 1',10,26,101),
  (553,103,1,'Floor 1',10,26,101),
  (554,104,1,'Floor 1',10,26,101),
  (555,105,1,'Floor 1',10,26,101),
  (556,201,2,'Floor 2',10,26,102),
  (557,202,2,'Floor 2',10,26,102),
  (558,203,2,'Floor 2',10,26,102),
  (559,204,2,'Floor 2',10,26,102),
  (560,205,2,'Floor 2',10,26,102),
  (561,301,3,'Floor 3',10,26,103),
  (562,302,3,'Floor 3',10,26,103),
  (563,303,3,'Floor 3',10,26,103),
  (564,304,3,'Floor 3',10,26,103),
  (565,1,0,'Ground Floor',11,27,104),
  (566,2,0,'Ground Floor',11,27,104),
  (567,3,0,'Ground Floor',11,27,104),
  (568,101,1,'Floor 1',11,27,105),
  (569,102,1,'Floor 1',11,27,105),
  (570,103,1,'Floor 1',11,27,105),
  (571,104,1,'Floor 1',11,27,105),
  (572,201,2,'Floor 2',11,27,106),
  (573,202,2,'Floor 2',11,27,106),
  (574,203,2,'Floor 2',11,27,106),
  (575,204,2,'Floor 2',11,27,106),
  (576,301,3,'Floor 3',11,27,107),
  (577,302,3,'Floor 3',11,27,107),
  (578,303,3,'Floor 3',11,27,107),
  (579,304,3,'Floor 3',11,27,107),
  (580,101,1,'Floor 1',11,28,108),
  (581,102,1,'Floor 1',11,28,108),
  (582,103,1,'Floor 1',11,28,108),
  (583,104,1,'Floor 1',11,28,108),
  (584,105,1,'Floor 1',11,28,108),
  (585,201,2,'Floor 2',11,28,109),
  (586,202,2,'Floor 2',11,28,109),
  (587,203,2,'Floor 2',11,28,109),
  (588,204,2,'Floor 2',11,28,109),
  (589,205,2,'Floor 2',11,28,109),
  (590,301,3,'Floor 3',11,28,110),
  (591,302,3,'Floor 3',11,28,110),
  (592,303,3,'Floor 3',11,28,110),
  (593,304,3,'Floor 3',11,28,110),
  (594,305,3,'Floor 3',11,28,110),
  (595,401,4,'Floor 4',11,28,111),
  (596,402,4,'Floor 4',11,28,111),
  (597,403,4,'Floor 4',11,28,111),
  (598,404,4,'Floor 4',11,28,111),
  (599,101,1,'Floor 1',11,29,112),
  (600,102,1,'Floor 1',11,29,112),
  (601,103,1,'Floor 1',11,29,112),
  (602,104,1,'Floor 1',11,29,112),
  (603,105,1,'Floor 1',11,29,112),
  (604,1,0,'Ground Floor',11,29,113),
  (605,2,0,'Ground Floor',11,29,113),
  (606,201,2,'Floor 2',11,29,114),
  (607,202,2,'Floor 2',11,29,114),
  (608,203,2,'Floor 2',11,29,114),
  (609,204,2,'Floor 2',11,29,114),
  (610,101,1,'Floor 1',12,30,115),
  (611,102,1,'Floor 1',12,30,115),
  (612,103,1,'Floor 1',12,30,115),
  (613,104,1,'Floor 1',12,30,115),
  (614,105,1,'Floor 1',12,30,115),
  (615,106,1,'Floor 1',12,30,115),
  (616,107,1,'Floor 1',12,30,115),
  (617,201,2,'Floor 2',12,30,116),
  (618,202,2,'Floor 2',12,30,116),
  (619,203,2,'Floor 2',12,30,116),
  (620,204,2,'Floor 2',12,30,116),
  (621,205,2,'Floor 2',12,30,116),
  (622,206,2,'Floor 2',12,30,116),
  (623,301,3,'Floor 3',12,30,117),
  (624,302,3,'Floor 3',12,30,117),
  (625,303,3,'Floor 3',12,30,117),
  (626,304,3,'Floor 3',12,30,117),
  (627,305,3,'Floor 3',12,30,117),
  (628,101,1,'Floor 1',12,31,118),
  (629,102,1,'Floor 1',12,31,118),
  (630,103,1,'Floor 1',12,31,118),
  (631,104,1,'Floor 1',12,31,118),
  (632,105,1,'Floor 1',12,31,118),
  (633,106,1,'Floor 1',12,31,118),
  (634,107,1,'Floor 1',12,31,118),
  (635,108,1,'Floor 1',12,31,118),
  (636,201,2,'Floor 2',12,31,119),
  (637,202,2,'Floor 2',12,31,119),
  (638,203,2,'Floor 2',12,31,119),
  (639,204,2,'Floor 2',12,31,119),
  (640,205,2,'Floor 2',12,31,119),
  (641,206,2,'Floor 2',12,31,119),
  (642,207,2,'Floor 2',12,31,119),
  (643,208,2,'Floor 2',12,31,119),
  (644,301,3,'Floor 3',12,31,120),
  (645,302,3,'Floor 3',12,31,120),
  (646,303,3,'Floor 3',12,31,120),
  (647,304,3,'Floor 3',12,31,120),
  (648,305,3,'Floor 3',12,31,120),
  (649,306,3,'Floor 3',12,31,120),
  (650,307,3,'Floor 3',12,31,120),
  (651,308,3,'Floor 3',12,31,120),
  (652,401,4,'Floor 4',12,31,121),
  (653,402,4,'Floor 4',12,31,121),
  (654,403,4,'Floor 4',12,31,121),
  (655,404,4,'Floor 4',12,31,121),
  (656,405,4,'Floor 4',12,31,121),
  (657,406,4,'Floor 4',12,31,121),
  (658,407,4,'Floor 4',12,31,121),
  (659,408,4,'Floor 4',12,31,121),
  (660,101,1,'Floor 1',7,32,122),
  (661,102,1,'Floor 1',7,32,122),
  (662,201,2,'Floor 2',7,32,123),
  (663,202,2,'Floor 2',7,32,123),
  (664,203,2,'Floor 2',7,32,123),
  (665,204,2,'Floor 2',7,32,123),
  (666,205,2,'Floor 2',7,32,123),
  (667,101,1,'Floor 1',7,33,124),
  (668,102,1,'Floor 1',7,33,124),
  (669,201,2,'Floor 2',7,33,125),
  (670,202,2,'Floor 2',7,33,125),
  (671,203,2,'Floor 2',7,33,125),
  (672,204,2,'Floor 2',7,33,125),
  (673,1,-1,'Basement',13,34,126),
  (674,2,-1,'Basement',13,34,126),
  (675,101,1,'Floor 1',14,35,127),
  (676,102,1,'Floor 1',14,35,127),
  (677,103,1,'Floor 1',14,35,127),
  (678,104,1,'Floor 1',14,35,127),
  (679,105,1,'Floor 1',14,35,127),
  (680,106,1,'Floor 1',14,35,127),
  (681,201,2,'Floor 2',14,35,128),
  (682,202,2,'Floor 2',14,35,128),
  (683,203,2,'Floor 2',14,35,128),
  (684,204,2,'Floor 2',14,35,128),
  (685,205,2,'Floor 2',14,35,128),
  (686,206,2,'Floor 2',14,35,128),
  (687,207,2,'Floor 2',14,35,128),
  (688,208,2,'Floor 2',14,35,128),
  (689,301,3,'Floor 3',14,35,129),
  (690,302,3,'Floor 3',14,35,129),
  (691,303,3,'Floor 3',14,35,129),
  (692,304,3,'Floor 3',14,35,129),
  (693,305,3,'Floor 3',14,35,129),
  (694,306,3,'Floor 3',14,35,129),
  (695,307,3,'Floor 3',14,35,129),
  (696,308,3,'Floor 3',14,35,129),
  (697,101,1,'Floor 1',14,36,130),
  (698,102,1,'Floor 1',14,36,130),
  (699,103,1,'Floor 1',14,36,130),
  (700,104,1,'Floor 1',14,36,130),
  (701,105,1,'Floor 1',14,36,130),
  (702,201,2,'Floor 2',14,36,131),
  (703,202,2,'Floor 2',14,36,131),
  (704,203,2,'Floor 2',14,36,131),
  (705,204,2,'Floor 2',14,36,131),
  (706,205,2,'Floor 2',14,36,131),
  (707,1,0,'Ground Floor',14,36,132),
  (708,2,0,'Ground Floor',14,36,132),
  (709,3,0,'Ground Floor',14,36,132),
  (710,201,2,'Floor 2',15,37,133),
  (711,202,2,'Floor 2',15,37,133),
  (712,203,2,'Floor 2',15,37,133),
  (713,1,0,'Ground Floor',16,38,134),
  (714,2,0,'Ground Floor',16,38,134),
  (715,1,0,'Ground Commercial',17,39,135),
  (716,2,0,'Ground Commercial',17,39,135),
  (717,3,0,'Ground Commercial',17,39,135),
  (718,4,0,'Ground Commercial',17,39,135),
  (719,5,0,'Ground Commercial',17,39,135),
  (720,101,1,'Floor 1',17,39,136),
  (721,102,1,'Floor 1',17,39,136),
  (722,103,1,'Floor 1',17,39,136),
  (723,104,1,'Floor 1',17,39,136),
  (724,1,0,'Ground Residential',17,39,137),
  (725,2,0,'Ground Residential',17,39,137),
  (726,3,0,'Ground Residential',17,39,137),
  (727,201,2,'Floor 2',17,39,138),
  (728,202,2,'Floor 2',17,39,138),
  (729,203,2,'Floor 2',17,39,138),
  (730,204,2,'Floor 2',17,39,138),
  (731,205,2,'Floor 2',17,39,138),
  (732,206,2,'Floor 2',17,39,138),
  (733,207,2,'Floor 2',17,39,138),
  (734,208,2,'Floor 2',17,39,138),
  (735,1,0,'Ground Floor',17,40,139),
  (736,2,0,'Ground Floor',17,40,139),
  (737,3,0,'Ground Floor',17,40,139),
  (738,101,1,'Floor 1',17,40,140),
  (739,102,1,'Floor 1',17,40,140),
  (740,103,1,'Floor 1',17,40,140),
  (741,104,1,'Floor 1',17,40,140),
  (742,105,1,'Floor 1',17,40,140),
  (743,106,1,'Floor 1',17,40,140),
  (744,107,1,'Floor 1',17,40,140),
  (745,108,1,'Floor 1',17,40,140),
  (746,201,2,'Floor 2',17,40,141),
  (747,202,2,'Floor 2',17,40,141),
  (748,203,2,'Floor 2',17,40,141),
  (749,204,2,'Floor 2',17,40,141),
  (750,205,2,'Floor 2',17,40,141),
  (751,206,2,'Floor 2',17,40,141),
  (752,301,3,'Floor 3',17,40,142),
  (753,302,3,'Floor 3',17,40,142),
  (754,303,3,'Floor 3',17,40,142),
  (755,304,3,'Floor 3',17,40,142),
  (756,305,3,'Floor 3',17,40,142),
  (757,306,3,'Floor 3',17,40,142),
  (825,1,0,'Ground Commercial',19,43,152),
  (826,2,0,'Ground Commercial',19,43,152),
  (827,3,0,'Ground Commercial',19,43,152),
  (828,4,0,'Ground Commercial',19,43,152),
  (829,5,0,'Ground Commercial',19,43,152),
  (830,6,0,'Ground Commercial',19,43,152),
  (831,2,1,'Ground Residential',19,43,153),
  (832,3,1,'Ground Residential',19,43,153),
  (833,4,1,'Ground Residential',19,43,153),
  (834,5,1,'Ground Residential',19,43,153),
  (835,6,1,'Ground Residential',19,43,153),
  (836,7,1,'Ground Residential',19,43,153),
  (837,8,1,'Ground Residential',19,43,153),
  (838,9,1,'Ground Residential',19,43,153),
  (839,10,1,'Ground Residential',19,43,153),
  (840,2,1,'Floor 1',19,43,154),
  (841,3,1,'Floor 1',19,43,154),
  (842,4,1,'Floor 1',19,43,154),
  (843,5,1,'Floor 1',19,43,154),
  (844,6,1,'Floor 1',19,43,154),
  (845,7,1,'Floor 1',19,43,154),
  (846,8,1,'Floor 1',19,43,154),
  (847,3,2,'Floor 2',19,43,155),
  (848,4,2,'Floor 2',19,43,155),
  (849,5,2,'Floor 2',19,43,155),
  (850,6,2,'Floor 2',19,43,155),
  (851,7,2,'Floor 2',19,43,155),
  (852,8,2,'Floor 2',19,43,155),
  (853,9,2,'Floor 2',19,43,155),
  (854,10,2,'Floor 2',19,43,155),
  (855,4,3,'Floor 3',19,43,156),
  (856,5,3,'Floor 3',19,43,156),
  (857,6,3,'Floor 3',19,43,156),
  (858,7,3,'Floor 3',19,43,156),
  (859,8,3,'Floor 3',19,43,156),
  (860,9,3,'Floor 3',19,43,156),
  (861,1,0,'Ground Floor',19,44,157),
  (862,2,0,'Ground Floor',19,44,157),
  (863,3,0,'Ground Floor',19,44,157),
  (864,4,0,'Ground Floor',19,44,157),
  (865,5,0,'Ground Floor',19,44,157),
  (866,6,0,'Ground Floor',19,44,157),
  (867,7,0,'Ground Floor',19,44,157),
  (868,2,1,'Floor 1',19,44,158),
  (869,3,1,'Floor 1',19,44,158),
  (870,4,1,'Floor 1',19,44,158),
  (871,5,1,'Floor 1',19,44,158),
  (872,6,1,'Floor 1',19,44,158),
  (873,7,1,'Floor 1',19,44,158),
  (874,8,1,'Floor 1',19,44,159),
  (875,9,1,'Floor 1',19,44,159),
  (876,10,1,'Floor 1',19,44,159),
  (877,11,1,'Floor 1',19,44,159),
  (878,3,2,'Floor 2',19,44,160),
  (879,4,2,'Floor 2',19,44,160),
  (880,5,2,'Floor 2',19,44,160),
  (881,6,2,'Floor 2',19,44,160),
  (904,1,0,'Ground Floor',20,46,165),
  (905,2,0,'Ground Floor',20,46,165),
  (906,3,0,'Ground Floor',20,46,165),
  (907,4,0,'Ground Floor',20,46,165),
  (908,5,0,'Ground Floor',20,46,165),
  (909,6,0,'Ground Floor',20,46,165),
  (910,101,1,'Floor 1',20,46,166),
  (911,102,1,'Floor 1',20,46,166),
  (912,103,1,'Floor 1',20,46,166),
  (913,104,1,'Floor 1',20,46,166),
  (914,105,1,'Floor 1',20,46,166),
  (915,201,2,'Floor 2',20,46,167),
  (916,202,2,'Floor 2',20,46,167),
  (917,203,2,'Floor 2',20,46,167),
  (918,204,2,'Floor 2',20,46,167),
  (919,205,2,'Floor 2',20,46,167),
  (920,206,2,'Floor 2',20,46,167),
  (921,207,2,'Floor 2',20,46,167),
  (922,106,1,'Floor 1',20,46,168),
  (923,107,1,'Floor 1',20,46,168),
  (924,108,1,'Floor 1',20,46,168),
  (925,109,1,'Floor 1',20,46,168),
  (926,110,1,'Floor 1',20,46,168),
  (927,111,1,'Floor 1',20,46,168),
  (928,208,2,'Floor 2',20,46,169),
  (929,209,2,'Floor 2',20,46,169),
  (930,210,2,'Floor 2',20,46,169),
  (931,211,2,'Floor 2',20,46,169),
  (932,212,2,'Floor 2',20,46,169),
  (933,213,2,'Floor 2',20,46,169),
  (934,1,0,'Ground Comm.',5,47,170),
  (935,2,0,'Ground Comm.',5,47,170),
  (936,3,0,'Ground Comm.',5,47,170),
  (937,101,1,'Ground Residential',5,47,171),
  (938,102,1,'Ground Residential',5,47,171),
  (939,103,1,'Ground Residential',5,47,171),
  (940,101,1,'Floor 1',5,47,172),
  (941,102,1,'Floor 1',5,47,172),
  (942,103,1,'Floor 1',5,47,172),
  (943,104,1,'Floor 1',5,47,172),
  (944,105,1,'Floor 1',5,47,172),
  (945,201,2,'Floor 2',5,47,173),
  (946,202,2,'Floor 2',5,47,173),
  (947,203,2,'Floor 2',5,47,173),
  (948,204,2,'Floor 2',5,47,173),
  (949,205,2,'Floor 2',5,47,173),
  (950,301,3,'Floor 3',5,47,174),
  (951,302,3,'Floor 3',5,47,174),
  (952,303,3,'Floor 3',5,47,174),
  (953,304,3,'Floor 3',5,47,174),
  (954,305,3,'Floor 3',5,47,174),
  (955,1,0,'Ground Commercial',18,41,175),
  (956,2,0,'Ground Commercial',18,41,175),
  (957,3,0,'Ground Commercial',18,41,175),
  (958,4,0,'Ground Commercial',18,41,175),
  (959,5,0,'Ground Commercial',18,41,175),
  (960,6,0,'Ground Commercial',18,41,175),
  (961,101,1,'Ground Residential',18,41,176),
  (962,102,1,'Ground Residential',18,41,176),
  (963,103,1,'Ground Residential',18,41,176),
  (964,104,1,'Ground Residential',18,41,176),
  (965,105,1,'Ground Residential',18,41,176),
  (966,106,1,'Ground Residential',18,41,176),
  (967,107,1,'Ground Residential',18,41,176),
  (968,108,1,'Ground Residential',18,41,176),
  (969,101,1,'Floor 1',18,41,177),
  (970,102,1,'Floor 1',18,41,177),
  (971,103,1,'Floor 1',18,41,177),
  (972,104,1,'Floor 1',18,41,177),
  (973,105,1,'Floor 1',18,41,177),
  (974,106,1,'Floor 1',18,41,177),
  (975,107,1,'Floor 1',18,41,177),
  (976,108,1,'Floor 1',18,41,177),
  (977,109,1,'Floor 1',18,41,177),
  (978,110,1,'Floor 1',18,41,177),
  (979,201,2,'Floor 2',18,41,178),
  (980,202,2,'Floor 2',18,41,178),
  (981,203,2,'Floor 2',18,41,178),
  (982,204,2,'Floor 2',18,41,178),
  (983,205,2,'Floor 2',18,41,178),
  (984,206,2,'Floor 2',18,41,178),
  (985,207,2,'Floor 2',18,41,178),
  (986,301,3,'Floor 3',18,41,179),
  (987,302,3,'Floor 3',18,41,179),
  (988,303,3,'Floor 3',18,41,179),
  (989,304,3,'Floor 3',18,41,179),
  (990,305,3,'Floor 3',18,41,179),
  (991,306,3,'Floor 3',18,41,179),
  (992,307,3,'Floor 3',18,41,179),
  (993,308,3,'Floor 3',18,41,179),
  (994,1,0,'Ground Floor',18,42,180),
  (995,2,0,'Ground Floor',18,42,180),
  (996,3,0,'Ground Floor',18,42,180),
  (997,4,0,'Ground Floor',18,42,180),
  (998,5,0,'Ground Floor',18,42,180),
  (999,6,0,'Ground Floor',18,42,180),
  (1000,7,0,'Ground Floor',18,42,180),
  (1001,8,0,'Ground Floor',18,42,180),
  (1002,101,1,'Floor 1',18,42,181),
  (1003,102,1,'Floor 1',18,42,181),
  (1004,103,1,'Floor 1',18,42,181),
  (1005,104,1,'Floor 1',18,42,181),
  (1006,105,1,'Floor 1',18,42,181),
  (1007,106,1,'Floor 1',18,42,181),
  (1008,107,1,'Floor 1',18,42,181),
  (1009,108,1,'Floor 1',18,42,181),
  (1010,109,1,'Floor 1',18,42,181),
  (1011,201,2,'Floor 2',18,42,182),
  (1012,202,2,'Floor 2',18,42,182),
  (1013,203,2,'Floor 2',18,42,182),
  (1014,204,2,'Floor 2',18,42,182),
  (1015,205,2,'Floor 2',18,42,182),
  (1016,206,2,'Floor 2',18,42,182),
  (1017,301,3,'Floor 3',18,42,183),
  (1018,302,3,'Floor 3',18,42,183),
  (1019,303,3,'Floor 3',18,42,183),
  (1020,304,3,'Floor 3',18,42,183),
  (1021,305,3,'Floor 3',18,42,183),
  (1022,1,0,'Ground Commercial',20,45,184),
  (1023,2,0,'Ground Commercial',20,45,184),
  (1024,3,0,'Ground Commercial',20,45,184),
  (1025,4,0,'Ground Commercial',20,45,184),
  (1026,5,0,'Ground Commercial',20,45,184),
  (1027,6,0,'Ground Commercial',20,45,184),
  (1028,101,1,'Ground Residential',20,45,185),
  (1029,102,1,'Ground Residential',20,45,185),
  (1030,103,1,'Ground Residential',20,45,185),
  (1031,104,1,'Ground Residential',20,45,185),
  (1032,105,1,'Ground Residential',20,45,185),
  (1033,106,1,'Ground Residential',20,45,185),
  (1034,101,1,'Floor 1',20,45,186),
  (1035,102,1,'Floor 1',20,45,186),
  (1036,103,1,'Floor 1',20,45,186),
  (1037,104,1,'Floor 1',20,45,186),
  (1038,105,1,'Floor 1',20,45,186),
  (1039,106,1,'Floor 1',20,45,186),
  (1040,107,1,'Floor 1',20,45,186),
  (1041,201,2,'Floor 2',20,45,187),
  (1042,202,2,'Floor 2',20,45,187),
  (1043,203,2,'Floor 2',20,45,187),
  (1044,301,3,'Floor 3',20,45,188),
  (1045,302,3,'Floor 3',20,45,188),
  (1046,303,3,'Floor 3',20,45,188),
  (1047,304,3,'Floor 3',20,45,188),
  (1048,305,3,'Floor 3',20,45,188),
  (1049,107,1,'Floor 1',1,2,4),
  (1050,108,1,'Floor 1',1,2,4),
  (1051,109,1,'Floor 1',1,2,4),
  (1052,110,1,'Floor 1',1,2,4),
  (1053,111,1,'Floor 1',1,2,4),
  (1054,112,1,'Floor 1',1,2,4),
  (1055,207,2,'Floor 2',1,2,5),
  (1056,208,2,'Floor 2',1,2,5),
  (1057,209,2,'Floor 2',1,2,5),
  (1058,210,2,'Floor 2',1,2,5),
  (1059,211,2,'Floor 2',1,2,5),
  (1060,212,2,'Floor 2',1,2,5),
  (1061,305,3,'Floor 3',1,2,6),
  (1062,306,3,'Floor 3',1,2,6),
  (1063,307,3,'Floor 3',1,2,6),
  (1064,308,3,'Floor 3',1,2,6),
  (1065,7,-1,'Basement',1,3,7),
  (1066,8,-1,'Basement',1,3,7),
  (1067,9,-1,'Basement',1,3,7),
  (1068,10,-1,'Basement',1,3,7),
  (1069,11,-1,'Basement',1,3,7),
  (1070,12,-1,'Basement',1,3,7),
  (1071,5,0,'Ground Floor',1,3,8),
  (1072,6,0,'Ground Floor',1,3,8),
  (1073,7,0,'Ground Floor',1,3,8),
  (1074,8,0,'Ground Floor',1,3,8),
  (1075,105,1,'Floor 1',1,3,9),
  (1076,106,1,'Floor 1',1,3,9),
  (1077,107,1,'Floor 1',1,3,9),
  (1078,108,1,'Floor 1',1,3,9),
  (1079,207,2,'Floor 2',1,3,10),
  (1080,208,2,'Floor 2',1,3,10),
  (1081,209,2,'Floor 2',1,3,10),
  (1082,210,2,'Floor 2',1,3,10),
  (1083,211,2,'Floor 2',1,3,10),
  (1084,212,2,'Floor 2',1,3,10),
  (1085,307,3,'Floor 3',1,3,11),
  (1086,308,3,'Floor 3',1,3,11),
  (1087,309,3,'Floor 3',1,3,11),
  (1088,310,3,'Floor 3',1,3,11),
  (1089,311,3,'Floor 3',1,3,11),
  (1090,312,3,'Floor 3',1,3,11),
  (1091,9,0,'Ground Floor',2,4,12),
  (1092,10,0,'Ground Floor',2,4,12),
  (1093,11,0,'Ground Floor',2,4,12),
  (1094,12,0,'Ground Floor',2,4,12),
  (1095,13,0,'Ground Floor',2,4,12),
  (1096,14,0,'Ground Floor',2,4,12),
  (1097,15,0,'Ground Floor',2,4,12),
  (1098,16,0,'Ground Floor',2,4,12),
  (1099,111,1,'Floor 1',2,4,13),
  (1100,112,1,'Floor 1',2,4,13),
  (1101,113,1,'Floor 1',2,4,13),
  (1102,114,1,'Floor 1',2,4,13),
  (1103,115,1,'Floor 1',2,4,13),
  (1104,116,1,'Floor 1',2,4,13),
  (1105,117,1,'Floor 1',2,4,13),
  (1106,118,1,'Floor 1',2,4,13),
  (1107,119,1,'Floor 1',2,4,13),
  (1108,120,1,'Floor 1',2,4,13),
  (1109,209,2,'Floor 2',2,4,14),
  (1110,210,2,'Floor 2',2,4,14),
  (1111,211,2,'Floor 2',2,4,14),
  (1112,212,2,'Floor 2',2,4,14),
  (1113,213,2,'Floor 2',2,4,14),
  (1114,214,2,'Floor 2',2,4,14),
  (1115,215,2,'Floor 2',2,4,14),
  (1116,216,2,'Floor 2',2,4,14),
  (1117,109,1,'Floor 1',2,5,15),
  (1118,110,1,'Floor 1',2,5,15),
  (1119,111,1,'Floor 1',2,5,15),
  (1120,112,1,'Floor 1',2,5,15),
  (1121,113,1,'Floor 1',2,5,15),
  (1122,114,1,'Floor 1',2,5,15),
  (1123,115,1,'Floor 1',2,5,15),
  (1124,116,1,'Floor 1',2,5,15),
  (1125,211,2,'Floor 2',2,5,16),
  (1126,212,2,'Floor 2',2,5,16),
  (1127,213,2,'Floor 2',2,5,16),
  (1128,214,2,'Floor 2',2,5,16),
  (1129,215,2,'Floor 2',2,5,16),
  (1130,216,2,'Floor 2',2,5,16),
  (1131,217,2,'Floor 2',2,5,16),
  (1132,218,2,'Floor 2',2,5,16),
  (1133,219,2,'Floor 2',2,5,16),
  (1134,220,2,'Floor 2',2,5,16),
  (1135,307,3,'Floor 3',2,5,17),
  (1136,308,3,'Floor 3',2,5,17),
  (1137,309,3,'Floor 3',2,5,17);

COMMIT;

#
# Data for the `flats` table  (LIMIT 1000,500)
#

INSERT INTO `flats` (`flat_id`, `flat_number`, `floor_number`, `floor_name`, `project_id`, `wing_id`, `wing_details_id`) VALUES 
  (1138,310,3,'Floor 3',2,5,17),
  (1139,311,3,'Floor 3',2,5,17),
  (1140,312,3,'Floor 3',2,5,17),
  (1141,111,1,'Floor 1',2,6,18),
  (1142,112,1,'Floor 1',2,6,18),
  (1143,113,1,'Floor 1',2,6,18),
  (1144,114,1,'Floor 1',2,6,18),
  (1145,115,1,'Floor 1',2,6,18),
  (1146,116,1,'Floor 1',2,6,18),
  (1147,117,1,'Floor 1',2,6,18),
  (1148,118,1,'Floor 1',2,6,18),
  (1149,119,1,'Floor 1',2,6,18),
  (1150,120,1,'Floor 1',2,6,18),
  (1151,209,2,'Floor 2',2,6,19),
  (1152,210,2,'Floor 2',2,6,19),
  (1153,211,2,'Floor 2',2,6,19),
  (1154,212,2,'Floor 2',2,6,19),
  (1155,213,2,'Floor 2',2,6,19),
  (1156,214,2,'Floor 2',2,6,19),
  (1157,215,2,'Floor 2',2,6,19),
  (1158,216,2,'Floor 2',2,6,19),
  (1159,306,3,'Floor 3',2,6,20),
  (1160,307,3,'Floor 3',2,6,20),
  (1161,308,3,'Floor 3',2,6,20),
  (1162,309,3,'Floor 3',2,6,20),
  (1163,310,3,'Floor 3',2,6,20),
  (1164,5,0,'Ground Floor',3,7,21),
  (1165,6,0,'Ground Floor',3,7,21),
  (1166,7,0,'Ground Floor',3,7,21),
  (1167,8,0,'Ground Floor',3,7,21),
  (1168,109,1,'Floor 1',3,7,22),
  (1169,110,1,'Floor 1',3,7,22),
  (1170,111,1,'Floor 1',3,7,22),
  (1171,112,1,'Floor 1',3,7,22),
  (1172,113,1,'Floor 1',3,7,22),
  (1173,114,1,'Floor 1',3,7,22),
  (1174,115,1,'Floor 1',3,7,22),
  (1175,116,1,'Floor 1',3,7,22),
  (1176,207,2,'Floor 2',3,7,23),
  (1177,208,2,'Floor 2',3,7,23),
  (1178,209,2,'Floor 2',3,7,23),
  (1179,210,2,'Floor 2',3,7,23),
  (1180,211,2,'Floor 2',3,7,23),
  (1181,212,2,'Floor 2',3,7,23),
  (1182,109,1,'Floor 1',3,8,24),
  (1183,110,1,'Floor 1',3,8,24),
  (1184,111,1,'Floor 1',3,8,24),
  (1185,112,1,'Floor 1',3,8,24),
  (1186,113,1,'Floor 1',3,8,24),
  (1187,114,1,'Floor 1',3,8,24),
  (1188,115,1,'Floor 1',3,8,24),
  (1189,116,1,'Floor 1',3,8,24),
  (1190,209,2,'Floor 2',3,8,25),
  (1191,210,2,'Floor 2',3,8,25),
  (1192,211,2,'Floor 2',3,8,25),
  (1193,212,2,'Floor 2',3,8,25),
  (1194,213,2,'Floor 2',3,8,25),
  (1195,214,2,'Floor 2',3,8,25),
  (1196,215,2,'Floor 2',3,8,25),
  (1197,216,2,'Floor 2',3,8,25),
  (1198,309,3,'Floor 3',3,8,26),
  (1199,310,3,'Floor 3',3,8,26),
  (1200,311,3,'Floor 3',3,8,26),
  (1201,312,3,'Floor 3',3,8,26),
  (1202,313,3,'Floor 3',3,8,26),
  (1203,314,3,'Floor 3',3,8,26),
  (1204,315,3,'Floor 3',3,8,26),
  (1205,316,3,'Floor 3',3,8,26),
  (1206,113,1,'Floor 1',3,9,27),
  (1207,114,1,'Floor 1',3,9,27),
  (1208,115,1,'Floor 1',3,9,27),
  (1209,116,1,'Floor 1',3,9,27),
  (1210,117,1,'Floor 1',3,9,27),
  (1211,118,1,'Floor 1',3,9,27),
  (1212,119,1,'Floor 1',3,9,27),
  (1213,120,1,'Floor 1',3,9,27),
  (1214,121,1,'Floor 1',3,9,27),
  (1215,122,1,'Floor 1',3,9,27),
  (1216,123,1,'Floor 1',3,9,27),
  (1217,124,1,'Floor 1',3,9,27),
  (1218,213,2,'Floor 2',3,9,28),
  (1219,214,2,'Floor 2',3,9,28),
  (1220,215,2,'Floor 2',3,9,28),
  (1221,216,2,'Floor 2',3,9,28),
  (1222,217,2,'Floor 2',3,9,28),
  (1223,218,2,'Floor 2',3,9,28),
  (1224,219,2,'Floor 2',3,9,28),
  (1225,220,2,'Floor 2',3,9,28),
  (1226,221,2,'Floor 2',3,9,28),
  (1227,222,2,'Floor 2',3,9,28),
  (1228,223,2,'Floor 2',3,9,28),
  (1229,224,2,'Floor 2',3,9,28),
  (1230,105,1,'Floor 1',1,1,29),
  (1231,106,1,'Floor 1',1,1,29),
  (1232,107,1,'Floor 1',1,1,29),
  (1233,108,1,'Floor 1',1,1,29),
  (1234,205,2,'Floor 2',1,1,30),
  (1235,206,2,'Floor 2',1,1,30),
  (1236,207,2,'Floor 2',1,1,30),
  (1237,208,2,'Floor 2',1,1,30),
  (1238,305,3,'Floor 3',1,1,31),
  (1239,306,3,'Floor 3',1,1,31),
  (1240,307,3,'Floor 3',1,1,31),
  (1241,308,3,'Floor 3',1,1,31),
  (1242,8,0,'Ground Floor',1,1,32),
  (1243,9,0,'Ground Floor',1,1,32),
  (1244,10,0,'Ground Floor',1,1,32),
  (1245,11,0,'Ground Floor',1,1,32),
  (1246,12,0,'Ground Floor',1,1,32),
  (1247,13,0,'Ground Floor',1,1,32),
  (1248,14,0,'Ground Floor',1,1,32),
  (1249,5,-1,'Basement',4,10,33),
  (1250,6,-1,'Basement',4,10,33),
  (1251,7,-1,'Basement',4,10,33),
  (1252,8,-1,'Basement',4,10,33),
  (1253,5,0,'Ground Floor',4,10,34),
  (1254,6,0,'Ground Floor',4,10,34),
  (1255,7,0,'Ground Floor',4,10,34),
  (1256,8,0,'Ground Floor',4,10,34),
  (1257,107,1,'Floor 1',4,10,35),
  (1258,108,1,'Floor 1',4,10,35),
  (1259,109,1,'Floor 1',4,10,35),
  (1260,110,1,'Floor 1',4,10,35),
  (1261,111,1,'Floor 1',4,10,35),
  (1262,112,1,'Floor 1',4,10,35),
  (1263,205,2,'Floor 2',4,10,36),
  (1264,206,2,'Floor 2',4,10,36),
  (1265,207,2,'Floor 2',4,10,36),
  (1266,208,2,'Floor 2',4,10,36),
  (1267,107,1,'Floor 1',4,11,37),
  (1268,108,1,'Floor 1',4,11,37),
  (1269,109,1,'Floor 1',4,11,37),
  (1270,110,1,'Floor 1',4,11,37),
  (1271,111,1,'Floor 1',4,11,37),
  (1272,112,1,'Floor 1',4,11,37),
  (1273,207,2,'Floor 2',4,11,38),
  (1274,208,2,'Floor 2',4,11,38),
  (1275,209,2,'Floor 2',4,11,38),
  (1276,210,2,'Floor 2',4,11,38),
  (1277,211,2,'Floor 2',4,11,38),
  (1278,212,2,'Floor 2',4,11,38),
  (1279,305,3,'Floor 3',4,11,39),
  (1280,306,3,'Floor 3',4,11,39),
  (1281,307,3,'Floor 3',4,11,39),
  (1282,308,3,'Floor 3',4,11,39),
  (1283,3,0,'Ground Floor',4,12,40),
  (1284,4,0,'Ground Floor',4,12,40),
  (1285,107,1,'Floor 1',4,12,41),
  (1286,108,1,'Floor 1',4,12,41),
  (1287,109,1,'Floor 1',4,12,41),
  (1288,110,1,'Floor 1',4,12,41),
  (1289,111,1,'Floor 1',4,12,41),
  (1290,112,1,'Floor 1',4,12,41),
  (1291,205,2,'Floor 2',4,12,42),
  (1292,206,2,'Floor 2',4,12,42),
  (1293,207,2,'Floor 2',4,12,42),
  (1294,208,2,'Floor 2',4,12,42),
  (1295,15,0,'Ground Floor',5,13,43),
  (1296,16,0,'Ground Floor',5,13,43),
  (1297,17,0,'Ground Floor',5,13,43),
  (1298,18,0,'Ground Floor',5,13,44),
  (1299,19,0,'Ground Floor',5,13,44),
  (1300,20,0,'Ground Floor',5,13,44),
  (1301,21,0,'Ground Floor',5,13,44),
  (1302,22,0,'Ground Floor',5,13,44),
  (1303,23,0,'Ground Floor',5,13,45),
  (1304,24,0,'Ground Floor',5,13,45),
  (1305,25,0,'Ground Floor',5,13,45),
  (1306,26,0,'Ground Floor',5,13,45),
  (1307,27,0,'Ground Floor',5,13,46),
  (1308,28,0,'Ground Floor',5,13,46),
  (1309,111,1,'Floor 1',5,13,47),
  (1310,112,1,'Floor 1',5,13,47),
  (1311,113,1,'Floor 1',5,13,47),
  (1312,114,1,'Floor 1',5,13,47),
  (1313,115,1,'Floor 1',5,13,48),
  (1314,116,1,'Floor 1',5,13,48),
  (1315,117,1,'Floor 1',5,13,49),
  (1316,118,1,'Floor 1',5,13,49),
  (1317,119,1,'Floor 1',5,13,49),
  (1318,120,1,'Floor 1',5,13,49),
  (1319,213,2,'Floor 2',5,13,50),
  (1320,214,2,'Floor 2',5,13,50),
  (1321,215,2,'Floor 2',5,13,50),
  (1322,216,2,'Floor 2',5,13,50),
  (1323,217,2,'Floor 2',5,13,50),
  (1324,218,2,'Floor 2',5,13,50),
  (1325,219,2,'Floor 2',5,13,50),
  (1326,220,2,'Floor 2',5,13,51),
  (1327,221,2,'Floor 2',5,13,51),
  (1328,222,2,'Floor 2',5,13,51),
  (1329,223,2,'Floor 2',5,13,51),
  (1330,224,2,'Floor 2',5,13,51),
  (1331,306,3,'Floor 3',5,13,52),
  (1332,307,3,'Floor 3',5,13,52),
  (1333,308,3,'Floor 3',5,13,52),
  (1334,309,3,'Floor 3',5,13,52),
  (1335,310,3,'Floor 3',5,13,52),
  (1336,407,4,'Floor 4',5,13,53),
  (1337,408,4,'Floor 4',5,13,53),
  (1338,409,4,'Floor 4',5,13,53),
  (1339,410,4,'Floor 4',5,13,53),
  (1340,411,4,'Floor 4',5,13,53),
  (1341,412,4,'Floor 4',5,13,53),
  (1342,9,0,'Ground Floor',5,14,54),
  (1343,10,0,'Ground Floor',5,14,54),
  (1344,11,0,'Ground Floor',5,14,54),
  (1345,12,0,'Ground Floor',5,14,54),
  (1346,13,0,'Ground Floor',5,14,54),
  (1347,14,0,'Ground Floor',5,14,54),
  (1348,15,0,'Ground Floor',5,14,54),
  (1349,16,0,'Ground Floor',5,14,54),
  (1350,112,1,'Floor 1',5,14,55),
  (1351,113,1,'Floor 1',5,14,55),
  (1352,114,1,'Floor 1',5,14,55),
  (1353,115,1,'Floor 1',5,14,55),
  (1354,116,1,'Floor 1',5,14,55),
  (1355,117,1,'Floor 1',5,14,55),
  (1356,118,1,'Floor 1',5,14,56),
  (1357,119,1,'Floor 1',5,14,56),
  (1358,120,1,'Floor 1',5,14,56),
  (1359,121,1,'Floor 1',5,14,56),
  (1360,122,1,'Floor 1',5,14,56),
  (1361,212,2,'Floor 2',5,14,57),
  (1362,213,2,'Floor 2',5,14,57),
  (1363,214,2,'Floor 2',5,14,57),
  (1364,215,2,'Floor 2',5,14,57),
  (1365,216,2,'Floor 2',5,14,57),
  (1366,217,2,'Floor 2',5,14,57),
  (1367,218,2,'Floor 2',5,14,57),
  (1368,219,2,'Floor 2',5,14,58),
  (1369,220,2,'Floor 2',5,14,58),
  (1370,221,2,'Floor 2',5,14,58),
  (1371,222,2,'Floor 2',5,14,58),
  (1372,311,3,'Floor 3',5,14,59),
  (1373,312,3,'Floor 3',5,14,59),
  (1374,313,3,'Floor 3',5,14,59),
  (1375,314,3,'Floor 3',5,14,59),
  (1376,315,3,'Floor 3',5,14,59),
  (1377,316,3,'Floor 3',5,14,59),
  (1378,317,3,'Floor 3',5,14,60),
  (1379,318,3,'Floor 3',5,14,60),
  (1380,319,3,'Floor 3',5,14,60),
  (1381,320,3,'Floor 3',5,14,60),
  (1382,105,1,'Floor 1',6,16,67),
  (1383,106,1,'Floor 1',6,16,67),
  (1384,107,1,'Floor 1',6,16,67),
  (1385,108,1,'Floor 1',6,16,67),
  (1386,205,2,'Floor 2',6,16,68),
  (1387,206,2,'Floor 2',6,16,68),
  (1388,207,2,'Floor 2',6,16,68),
  (1389,208,2,'Floor 2',6,16,68),
  (1390,305,3,'Floor 3',6,16,69),
  (1391,306,3,'Floor 3',6,16,69),
  (1392,307,3,'Floor 3',6,16,69),
  (1393,308,3,'Floor 3',6,16,69),
  (1394,105,1,'Floor 1',6,15,70),
  (1395,106,1,'Floor 1',6,15,70),
  (1396,107,1,'Floor 1',6,15,71),
  (1397,108,1,'Floor 1',6,15,71),
  (1398,205,2,'Floor 2',6,15,72),
  (1399,206,2,'Floor 2',6,15,72),
  (1400,207,2,'Floor 2',6,15,73),
  (1401,208,2,'Floor 2',6,15,73),
  (1402,305,3,'Floor 3',6,15,74),
  (1403,306,3,'Floor 3',6,15,74),
  (1404,307,3,'Floor 3',6,15,75),
  (1405,308,3,'Floor 3',6,15,75),
  (1406,105,1,'Floor 1',7,17,76),
  (1407,106,1,'Floor 1',7,17,76),
  (1408,107,1,'Floor 1',7,17,76),
  (1409,108,1,'Floor 1',7,17,76),
  (1410,205,2,'Floor 2',7,17,77),
  (1411,206,2,'Floor 2',7,17,77),
  (1412,207,2,'Floor 2',7,17,77),
  (1413,208,2,'Floor 2',7,17,77),
  (1414,110,1,'Floor 1',8,18,78),
  (1415,111,1,'Floor 1',8,18,78),
  (1416,112,1,'Floor 1',8,18,78),
  (1417,113,1,'Floor 1',8,18,78),
  (1418,114,1,'Floor 1',8,18,78),
  (1419,115,1,'Floor 1',8,18,78),
  (1420,116,1,'Floor 1',8,18,78),
  (1421,117,1,'Floor 1',8,18,78),
  (1422,118,1,'Floor 1',8,18,78),
  (1423,210,2,'Floor 2',8,18,79),
  (1424,211,2,'Floor 2',8,18,79),
  (1425,212,2,'Floor 2',8,18,79),
  (1426,213,2,'Floor 2',8,18,79),
  (1427,214,2,'Floor 2',8,18,79),
  (1428,215,2,'Floor 2',8,18,79),
  (1429,216,2,'Floor 2',8,18,79),
  (1430,217,2,'Floor 2',8,18,79),
  (1431,218,2,'Floor 2',8,18,79),
  (1432,308,3,'Floor 3',8,18,80),
  (1433,309,3,'Floor 3',8,18,80),
  (1434,310,3,'Floor 3',8,18,80),
  (1435,311,3,'Floor 3',8,18,80),
  (1436,312,3,'Floor 3',8,18,80),
  (1437,313,3,'Floor 3',8,18,80),
  (1438,314,3,'Floor 3',8,18,80),
  (1439,10,0,'Ground Floor',8,19,81),
  (1440,11,0,'Ground Floor',8,19,81),
  (1441,12,0,'Ground Floor',8,19,81),
  (1442,13,0,'Ground Floor',8,19,81),
  (1443,14,0,'Ground Floor',8,19,81),
  (1444,15,0,'Ground Floor',8,19,81),
  (1445,16,0,'Ground Floor',8,19,81),
  (1446,17,0,'Ground Floor',8,19,81),
  (1447,18,0,'Ground Floor',8,19,81),
  (1448,108,1,'Floor 1',8,19,82),
  (1449,109,1,'Floor 1',8,19,82),
  (1450,110,1,'Floor 1',8,19,82),
  (1451,111,1,'Floor 1',8,19,82),
  (1452,112,1,'Floor 1',8,19,82),
  (1453,113,1,'Floor 1',8,19,82),
  (1454,114,1,'Floor 1',8,19,82),
  (1455,208,2,'Floor 2',8,19,83),
  (1456,209,2,'Floor 2',8,19,83),
  (1457,210,2,'Floor 2',8,19,83),
  (1458,211,2,'Floor 2',8,19,83),
  (1459,212,2,'Floor 2',8,19,83),
  (1460,213,2,'Floor 2',8,19,83),
  (1461,214,2,'Floor 2',8,19,83),
  (1462,111,1,'Floor 1',9,20,84),
  (1463,112,1,'Floor 1',9,20,84),
  (1464,113,1,'Floor 1',9,20,84),
  (1465,114,1,'Floor 1',9,20,84),
  (1466,115,1,'Floor 1',9,20,84),
  (1467,116,1,'Floor 1',9,20,84),
  (1468,117,1,'Floor 1',9,20,84),
  (1469,118,1,'Floor 1',9,20,84),
  (1470,119,1,'Floor 1',9,20,84),
  (1471,120,1,'Floor 1',9,20,84),
  (1472,209,2,'Floor 2',9,20,85),
  (1473,210,2,'Floor 2',9,20,85),
  (1474,211,2,'Floor 2',9,20,85),
  (1475,212,2,'Floor 2',9,20,85),
  (1476,213,2,'Floor 2',9,20,85),
  (1477,214,2,'Floor 2',9,20,85),
  (1478,215,2,'Floor 2',9,20,85),
  (1479,216,2,'Floor 2',9,20,85),
  (1480,310,3,'Floor 3',9,20,86),
  (1481,311,3,'Floor 3',9,20,86),
  (1482,312,3,'Floor 3',9,20,86),
  (1483,313,3,'Floor 3',9,20,86),
  (1484,314,3,'Floor 3',9,20,86),
  (1485,315,3,'Floor 3',9,20,86),
  (1486,316,3,'Floor 3',9,20,86),
  (1487,317,3,'Floor 3',9,20,86),
  (1488,318,3,'Floor 3',9,20,86),
  (1489,408,4,'Floor 4',9,20,87),
  (1490,409,4,'Floor 4',9,20,87),
  (1491,410,4,'Floor 4',9,20,87),
  (1492,411,4,'Floor 4',9,20,87),
  (1493,412,4,'Floor 4',9,20,87),
  (1494,413,4,'Floor 4',9,20,87),
  (1495,414,4,'Floor 4',9,20,87),
  (1496,509,5,'Floor 5',9,20,88),
  (1497,510,5,'Floor 5',9,20,88),
  (1498,511,5,'Floor 5',9,20,88),
  (1499,512,5,'Floor 5',9,20,88),
  (1500,513,5,'Floor 5',9,20,88),
  (1501,514,5,'Floor 5',9,20,88),
  (1502,515,5,'Floor 5',9,20,88),
  (1503,516,5,'Floor 5',9,20,88),
  (1504,7,0,'Ground Floor',9,21,92),
  (1505,8,0,'Ground Floor',9,21,92),
  (1506,9,0,'Ground Floor',9,21,92),
  (1507,10,0,'Ground Floor',9,21,92),
  (1508,11,0,'Ground Floor',9,21,92),
  (1509,12,0,'Ground Floor',9,21,92),
  (1510,107,1,'Floor 1',9,21,93),
  (1511,108,1,'Floor 1',9,21,93),
  (1512,109,1,'Floor 1',9,21,93),
  (1513,110,1,'Floor 1',9,21,93),
  (1514,111,1,'Floor 1',9,21,93),
  (1515,112,1,'Floor 1',9,21,93),
  (1516,208,2,'Floor 2',9,21,94),
  (1517,209,2,'Floor 2',9,21,94),
  (1518,210,2,'Floor 2',9,21,94),
  (1519,211,2,'Floor 2',9,21,94),
  (1520,212,2,'Floor 2',9,21,94),
  (1521,213,2,'Floor 2',9,21,94),
  (1522,214,2,'Floor 2',9,21,94),
  (1523,5,0,'Ground Floor',9,22,96),
  (1524,6,0,'Ground Floor',9,22,96),
  (1525,7,0,'Ground Floor',9,22,96),
  (1526,8,0,'Ground Floor',9,22,96),
  (1527,104,1,'Floor 1',9,24,97),
  (1528,105,1,'Floor 1',9,24,97),
  (1529,106,1,'Floor 1',9,24,97),
  (1530,107,1,'Floor 1',10,25,98),
  (1531,108,1,'Floor 1',10,25,98),
  (1532,109,1,'Floor 1',10,25,98),
  (1533,110,1,'Floor 1',10,25,98),
  (1534,111,1,'Floor 1',10,25,98),
  (1535,112,1,'Floor 1',10,25,98),
  (1536,209,2,'Floor 2',10,25,99),
  (1537,210,2,'Floor 2',10,25,99),
  (1538,211,2,'Floor 2',10,25,99),
  (1539,212,2,'Floor 2',10,25,99),
  (1540,213,2,'Floor 2',10,25,99),
  (1541,214,2,'Floor 2',10,25,99),
  (1542,215,2,'Floor 2',10,25,99),
  (1543,216,2,'Floor 2',10,25,99),
  (1544,308,3,'Floor 3',10,25,100),
  (1545,309,3,'Floor 3',10,25,100),
  (1546,310,3,'Floor 3',10,25,100),
  (1547,311,3,'Floor 3',10,25,100),
  (1548,312,3,'Floor 3',10,25,100),
  (1549,313,3,'Floor 3',10,25,100),
  (1550,314,3,'Floor 3',10,25,100),
  (1551,106,1,'Floor 1',10,26,101),
  (1552,107,1,'Floor 1',10,26,101),
  (1553,108,1,'Floor 1',10,26,101),
  (1554,109,1,'Floor 1',10,26,101),
  (1555,110,1,'Floor 1',10,26,101),
  (1556,206,2,'Floor 2',10,26,102),
  (1557,207,2,'Floor 2',10,26,102),
  (1558,208,2,'Floor 2',10,26,102),
  (1559,209,2,'Floor 2',10,26,102),
  (1560,210,2,'Floor 2',10,26,102),
  (1561,305,3,'Floor 3',10,26,103),
  (1562,306,3,'Floor 3',10,26,103),
  (1563,307,3,'Floor 3',10,26,103),
  (1564,308,3,'Floor 3',10,26,103),
  (1565,4,0,'Ground Floor',11,27,104),
  (1566,5,0,'Ground Floor',11,27,104),
  (1567,6,0,'Ground Floor',11,27,104),
  (1568,105,1,'Floor 1',11,27,105),
  (1569,106,1,'Floor 1',11,27,105),
  (1570,107,1,'Floor 1',11,27,105),
  (1571,108,1,'Floor 1',11,27,105),
  (1572,205,2,'Floor 2',11,27,106),
  (1573,206,2,'Floor 2',11,27,106),
  (1574,207,2,'Floor 2',11,27,106),
  (1575,208,2,'Floor 2',11,27,106),
  (1576,305,3,'Floor 3',11,27,107),
  (1577,306,3,'Floor 3',11,27,107),
  (1578,307,3,'Floor 3',11,27,107),
  (1579,308,3,'Floor 3',11,27,107),
  (1580,106,1,'Floor 1',11,28,108),
  (1581,107,1,'Floor 1',11,28,108),
  (1582,108,1,'Floor 1',11,28,108),
  (1583,109,1,'Floor 1',11,28,108),
  (1584,110,1,'Floor 1',11,28,108),
  (1585,206,2,'Floor 2',11,28,109),
  (1586,207,2,'Floor 2',11,28,109),
  (1587,208,2,'Floor 2',11,28,109),
  (1588,209,2,'Floor 2',11,28,109),
  (1589,210,2,'Floor 2',11,28,109),
  (1590,306,3,'Floor 3',11,28,110),
  (1591,307,3,'Floor 3',11,28,110),
  (1592,308,3,'Floor 3',11,28,110),
  (1593,309,3,'Floor 3',11,28,110),
  (1594,310,3,'Floor 3',11,28,110),
  (1595,405,4,'Floor 4',11,28,111),
  (1596,406,4,'Floor 4',11,28,111),
  (1597,407,4,'Floor 4',11,28,111),
  (1598,408,4,'Floor 4',11,28,111),
  (1599,106,1,'Floor 1',11,29,112),
  (1600,107,1,'Floor 1',11,29,112),
  (1601,108,1,'Floor 1',11,29,112),
  (1602,109,1,'Floor 1',11,29,112),
  (1603,110,1,'Floor 1',11,29,112),
  (1604,3,0,'Ground Floor',11,29,113),
  (1605,4,0,'Ground Floor',11,29,113),
  (1606,205,2,'Floor 2',11,29,114),
  (1607,206,2,'Floor 2',11,29,114),
  (1608,207,2,'Floor 2',11,29,114),
  (1609,208,2,'Floor 2',11,29,114),
  (1610,108,1,'Floor 1',12,30,115),
  (1611,109,1,'Floor 1',12,30,115),
  (1612,110,1,'Floor 1',12,30,115),
  (1613,111,1,'Floor 1',12,30,115),
  (1614,112,1,'Floor 1',12,30,115),
  (1615,113,1,'Floor 1',12,30,115),
  (1616,114,1,'Floor 1',12,30,115),
  (1617,207,2,'Floor 2',12,30,116),
  (1618,208,2,'Floor 2',12,30,116),
  (1619,209,2,'Floor 2',12,30,116),
  (1620,210,2,'Floor 2',12,30,116),
  (1621,211,2,'Floor 2',12,30,116),
  (1622,212,2,'Floor 2',12,30,116),
  (1623,306,3,'Floor 3',12,30,117),
  (1624,307,3,'Floor 3',12,30,117),
  (1625,308,3,'Floor 3',12,30,117),
  (1626,309,3,'Floor 3',12,30,117),
  (1627,310,3,'Floor 3',12,30,117),
  (1628,109,1,'Floor 1',12,31,118),
  (1629,110,1,'Floor 1',12,31,118),
  (1630,111,1,'Floor 1',12,31,118),
  (1631,112,1,'Floor 1',12,31,118),
  (1632,113,1,'Floor 1',12,31,118),
  (1633,114,1,'Floor 1',12,31,118),
  (1634,115,1,'Floor 1',12,31,118),
  (1635,116,1,'Floor 1',12,31,118),
  (1636,209,2,'Floor 2',12,31,119),
  (1637,210,2,'Floor 2',12,31,119);

COMMIT;

#
# Data for the `flats` table  (LIMIT 1500,500)
#

INSERT INTO `flats` (`flat_id`, `flat_number`, `floor_number`, `floor_name`, `project_id`, `wing_id`, `wing_details_id`) VALUES 
  (1638,211,2,'Floor 2',12,31,119),
  (1639,212,2,'Floor 2',12,31,119),
  (1640,213,2,'Floor 2',12,31,119),
  (1641,214,2,'Floor 2',12,31,119),
  (1642,215,2,'Floor 2',12,31,119),
  (1643,216,2,'Floor 2',12,31,119),
  (1644,309,3,'Floor 3',12,31,120),
  (1645,310,3,'Floor 3',12,31,120),
  (1646,311,3,'Floor 3',12,31,120),
  (1647,312,3,'Floor 3',12,31,120),
  (1648,313,3,'Floor 3',12,31,120),
  (1649,314,3,'Floor 3',12,31,120),
  (1650,315,3,'Floor 3',12,31,120),
  (1651,316,3,'Floor 3',12,31,120),
  (1652,409,4,'Floor 4',12,31,121),
  (1653,410,4,'Floor 4',12,31,121),
  (1654,411,4,'Floor 4',12,31,121),
  (1655,412,4,'Floor 4',12,31,121),
  (1656,413,4,'Floor 4',12,31,121),
  (1657,414,4,'Floor 4',12,31,121),
  (1658,415,4,'Floor 4',12,31,121),
  (1659,416,4,'Floor 4',12,31,121),
  (1660,103,1,'Floor 1',7,32,122),
  (1661,104,1,'Floor 1',7,32,122),
  (1662,206,2,'Floor 2',7,32,123),
  (1663,207,2,'Floor 2',7,32,123),
  (1664,208,2,'Floor 2',7,32,123),
  (1665,209,2,'Floor 2',7,32,123),
  (1666,210,2,'Floor 2',7,32,123),
  (1667,103,1,'Floor 1',7,33,124),
  (1668,104,1,'Floor 1',7,33,124),
  (1669,205,2,'Floor 2',7,33,125),
  (1670,206,2,'Floor 2',7,33,125),
  (1671,207,2,'Floor 2',7,33,125),
  (1672,208,2,'Floor 2',7,33,125),
  (1673,3,-1,'Basement',13,34,126),
  (1674,4,-1,'Basement',13,34,126),
  (1675,107,1,'Floor 1',14,35,127),
  (1676,108,1,'Floor 1',14,35,127),
  (1677,109,1,'Floor 1',14,35,127),
  (1678,110,1,'Floor 1',14,35,127),
  (1679,111,1,'Floor 1',14,35,127),
  (1680,112,1,'Floor 1',14,35,127),
  (1681,209,2,'Floor 2',14,35,128),
  (1682,210,2,'Floor 2',14,35,128),
  (1683,211,2,'Floor 2',14,35,128),
  (1684,212,2,'Floor 2',14,35,128),
  (1685,213,2,'Floor 2',14,35,128),
  (1686,214,2,'Floor 2',14,35,128),
  (1687,215,2,'Floor 2',14,35,128),
  (1688,216,2,'Floor 2',14,35,128),
  (1689,309,3,'Floor 3',14,35,129),
  (1690,310,3,'Floor 3',14,35,129),
  (1691,311,3,'Floor 3',14,35,129),
  (1692,312,3,'Floor 3',14,35,129),
  (1693,313,3,'Floor 3',14,35,129),
  (1694,314,3,'Floor 3',14,35,129),
  (1695,315,3,'Floor 3',14,35,129),
  (1696,316,3,'Floor 3',14,35,129),
  (1697,106,1,'Floor 1',14,36,130),
  (1698,107,1,'Floor 1',14,36,130),
  (1699,108,1,'Floor 1',14,36,130),
  (1700,109,1,'Floor 1',14,36,130),
  (1701,110,1,'Floor 1',14,36,130),
  (1702,206,2,'Floor 2',14,36,131),
  (1703,207,2,'Floor 2',14,36,131),
  (1704,208,2,'Floor 2',14,36,131),
  (1705,209,2,'Floor 2',14,36,131),
  (1706,210,2,'Floor 2',14,36,131),
  (1707,4,0,'Ground Floor',14,36,132),
  (1708,5,0,'Ground Floor',14,36,132),
  (1709,6,0,'Ground Floor',14,36,132),
  (1710,204,2,'Floor 2',15,37,133),
  (1711,205,2,'Floor 2',15,37,133),
  (1712,206,2,'Floor 2',15,37,133),
  (1713,3,0,'Ground Floor',16,38,134),
  (1714,4,0,'Ground Floor',16,38,134),
  (1715,6,0,'Ground Commercial',17,39,135),
  (1716,7,0,'Ground Commercial',17,39,135),
  (1717,8,0,'Ground Commercial',17,39,135),
  (1718,9,0,'Ground Commercial',17,39,135),
  (1719,10,0,'Ground Commercial',17,39,135),
  (1720,105,1,'Floor 1',17,39,136),
  (1721,106,1,'Floor 1',17,39,136),
  (1722,107,1,'Floor 1',17,39,136),
  (1723,108,1,'Floor 1',17,39,136),
  (1724,4,0,'Ground Residential',17,39,137),
  (1725,5,0,'Ground Residential',17,39,137),
  (1726,6,0,'Ground Residential',17,39,137),
  (1727,209,2,'Floor 2',17,39,138),
  (1728,210,2,'Floor 2',17,39,138),
  (1729,211,2,'Floor 2',17,39,138),
  (1730,212,2,'Floor 2',17,39,138),
  (1731,213,2,'Floor 2',17,39,138),
  (1732,214,2,'Floor 2',17,39,138),
  (1733,215,2,'Floor 2',17,39,138),
  (1734,216,2,'Floor 2',17,39,138),
  (1735,4,0,'Ground Floor',17,40,139),
  (1736,5,0,'Ground Floor',17,40,139),
  (1737,6,0,'Ground Floor',17,40,139),
  (1738,109,1,'Floor 1',17,40,140),
  (1739,110,1,'Floor 1',17,40,140),
  (1740,111,1,'Floor 1',17,40,140),
  (1741,112,1,'Floor 1',17,40,140),
  (1742,113,1,'Floor 1',17,40,140),
  (1743,114,1,'Floor 1',17,40,140),
  (1744,115,1,'Floor 1',17,40,140),
  (1745,116,1,'Floor 1',17,40,140),
  (1746,207,2,'Floor 2',17,40,141),
  (1747,208,2,'Floor 2',17,40,141),
  (1748,209,2,'Floor 2',17,40,141),
  (1749,210,2,'Floor 2',17,40,141),
  (1750,211,2,'Floor 2',17,40,141),
  (1751,212,2,'Floor 2',17,40,141),
  (1752,307,3,'Floor 3',17,40,142),
  (1753,308,3,'Floor 3',17,40,142),
  (1754,309,3,'Floor 3',17,40,142),
  (1755,310,3,'Floor 3',17,40,142),
  (1756,311,3,'Floor 3',17,40,142),
  (1757,312,3,'Floor 3',17,40,142),
  (1758,7,0,'Ground Commercial',19,43,152),
  (1759,8,0,'Ground Commercial',19,43,152),
  (1760,9,0,'Ground Commercial',19,43,152),
  (1761,10,0,'Ground Commercial',19,43,152),
  (1762,11,0,'Ground Commercial',19,43,152),
  (1763,12,0,'Ground Commercial',19,43,152),
  (1764,11,1,'Ground Residential',19,43,153),
  (1765,12,1,'Ground Residential',19,43,153),
  (1766,13,1,'Ground Residential',19,43,153),
  (1767,14,1,'Ground Residential',19,43,153),
  (1768,15,1,'Ground Residential',19,43,153),
  (1769,16,1,'Ground Residential',19,43,153),
  (1770,17,1,'Ground Residential',19,43,153),
  (1771,18,1,'Ground Residential',19,43,153),
  (1772,19,1,'Ground Residential',19,43,153),
  (1773,9,1,'Floor 1',19,43,154),
  (1774,10,1,'Floor 1',19,43,154),
  (1775,11,1,'Floor 1',19,43,154),
  (1776,12,1,'Floor 1',19,43,154),
  (1777,13,1,'Floor 1',19,43,154),
  (1778,14,1,'Floor 1',19,43,154),
  (1779,15,1,'Floor 1',19,43,154),
  (1780,11,2,'Floor 2',19,43,155),
  (1781,12,2,'Floor 2',19,43,155),
  (1782,13,2,'Floor 2',19,43,155),
  (1783,14,2,'Floor 2',19,43,155),
  (1784,15,2,'Floor 2',19,43,155),
  (1785,16,2,'Floor 2',19,43,155),
  (1786,17,2,'Floor 2',19,43,155),
  (1787,18,2,'Floor 2',19,43,155),
  (1788,10,3,'Floor 3',19,43,156),
  (1789,11,3,'Floor 3',19,43,156),
  (1790,12,3,'Floor 3',19,43,156),
  (1791,13,3,'Floor 3',19,43,156),
  (1792,14,3,'Floor 3',19,43,156),
  (1793,15,3,'Floor 3',19,43,156),
  (1794,8,0,'Ground Floor',19,44,157),
  (1795,9,0,'Ground Floor',19,44,157),
  (1796,10,0,'Ground Floor',19,44,157),
  (1797,11,0,'Ground Floor',19,44,157),
  (1798,12,0,'Ground Floor',19,44,157),
  (1799,13,0,'Ground Floor',19,44,157),
  (1800,14,0,'Ground Floor',19,44,157),
  (1801,12,1,'Floor 1',19,44,158),
  (1802,13,1,'Floor 1',19,44,158),
  (1803,14,1,'Floor 1',19,44,158),
  (1804,15,1,'Floor 1',19,44,158),
  (1805,16,1,'Floor 1',19,44,158),
  (1806,17,1,'Floor 1',19,44,158),
  (1807,18,1,'Floor 1',19,44,159),
  (1808,19,1,'Floor 1',19,44,159),
  (1809,20,1,'Floor 1',19,44,159),
  (1810,21,1,'Floor 1',19,44,159),
  (1811,7,2,'Floor 2',19,44,160),
  (1812,8,2,'Floor 2',19,44,160),
  (1813,9,2,'Floor 2',19,44,160),
  (1814,10,2,'Floor 2',19,44,160),
  (1815,7,0,'Ground Floor',20,46,165),
  (1816,8,0,'Ground Floor',20,46,165),
  (1817,9,0,'Ground Floor',20,46,165),
  (1818,10,0,'Ground Floor',20,46,165),
  (1819,11,0,'Ground Floor',20,46,165),
  (1820,12,0,'Ground Floor',20,46,165),
  (1821,112,1,'Floor 1',20,46,166),
  (1822,113,1,'Floor 1',20,46,166),
  (1823,114,1,'Floor 1',20,46,166),
  (1824,115,1,'Floor 1',20,46,166),
  (1825,116,1,'Floor 1',20,46,166),
  (1826,214,2,'Floor 2',20,46,167),
  (1827,215,2,'Floor 2',20,46,167),
  (1828,216,2,'Floor 2',20,46,167),
  (1829,217,2,'Floor 2',20,46,167),
  (1830,218,2,'Floor 2',20,46,167),
  (1831,219,2,'Floor 2',20,46,167),
  (1832,220,2,'Floor 2',20,46,167),
  (1833,117,1,'Floor 1',20,46,168),
  (1834,118,1,'Floor 1',20,46,168),
  (1835,119,1,'Floor 1',20,46,168),
  (1836,120,1,'Floor 1',20,46,168),
  (1837,121,1,'Floor 1',20,46,168),
  (1838,122,1,'Floor 1',20,46,168),
  (1839,221,2,'Floor 2',20,46,169),
  (1840,222,2,'Floor 2',20,46,169),
  (1841,223,2,'Floor 2',20,46,169),
  (1842,224,2,'Floor 2',20,46,169),
  (1843,225,2,'Floor 2',20,46,169),
  (1844,226,2,'Floor 2',20,46,169),
  (1845,4,0,'Ground Comm.',5,47,170),
  (1846,5,0,'Ground Comm.',5,47,170),
  (1847,6,0,'Ground Comm.',5,47,170),
  (1848,104,1,'Ground Residential',5,47,171),
  (1849,105,1,'Ground Residential',5,47,171),
  (1850,106,1,'Ground Residential',5,47,171),
  (1851,106,1,'Floor 1',5,47,172),
  (1852,107,1,'Floor 1',5,47,172),
  (1853,108,1,'Floor 1',5,47,172),
  (1854,109,1,'Floor 1',5,47,172),
  (1855,110,1,'Floor 1',5,47,172),
  (1856,206,2,'Floor 2',5,47,173),
  (1857,207,2,'Floor 2',5,47,173),
  (1858,208,2,'Floor 2',5,47,173),
  (1859,209,2,'Floor 2',5,47,173),
  (1860,210,2,'Floor 2',5,47,173),
  (1861,306,3,'Floor 3',5,47,174),
  (1862,307,3,'Floor 3',5,47,174),
  (1863,308,3,'Floor 3',5,47,174),
  (1864,309,3,'Floor 3',5,47,174),
  (1865,310,3,'Floor 3',5,47,174),
  (1866,7,0,'Ground Commercial',18,41,175),
  (1867,8,0,'Ground Commercial',18,41,175),
  (1868,9,0,'Ground Commercial',18,41,175),
  (1869,10,0,'Ground Commercial',18,41,175),
  (1870,11,0,'Ground Commercial',18,41,175),
  (1871,12,0,'Ground Commercial',18,41,175),
  (1872,109,1,'Ground Residential',18,41,176),
  (1873,110,1,'Ground Residential',18,41,176),
  (1874,111,1,'Ground Residential',18,41,176),
  (1875,112,1,'Ground Residential',18,41,176),
  (1876,113,1,'Ground Residential',18,41,176),
  (1877,114,1,'Ground Residential',18,41,176),
  (1878,115,1,'Ground Residential',18,41,176),
  (1879,116,1,'Ground Residential',18,41,176),
  (1880,111,1,'Floor 1',18,41,177),
  (1881,112,1,'Floor 1',18,41,177),
  (1882,113,1,'Floor 1',18,41,177),
  (1883,114,1,'Floor 1',18,41,177),
  (1884,115,1,'Floor 1',18,41,177),
  (1885,116,1,'Floor 1',18,41,177),
  (1886,117,1,'Floor 1',18,41,177),
  (1887,118,1,'Floor 1',18,41,177),
  (1888,119,1,'Floor 1',18,41,177),
  (1889,120,1,'Floor 1',18,41,177),
  (1890,208,2,'Floor 2',18,41,178),
  (1891,209,2,'Floor 2',18,41,178),
  (1892,210,2,'Floor 2',18,41,178),
  (1893,211,2,'Floor 2',18,41,178),
  (1894,212,2,'Floor 2',18,41,178),
  (1895,213,2,'Floor 2',18,41,178),
  (1896,214,2,'Floor 2',18,41,178),
  (1897,309,3,'Floor 3',18,41,179),
  (1898,310,3,'Floor 3',18,41,179),
  (1899,311,3,'Floor 3',18,41,179),
  (1900,312,3,'Floor 3',18,41,179),
  (1901,313,3,'Floor 3',18,41,179),
  (1902,314,3,'Floor 3',18,41,179),
  (1903,315,3,'Floor 3',18,41,179),
  (1904,316,3,'Floor 3',18,41,179),
  (1905,9,0,'Ground Floor',18,42,180),
  (1906,10,0,'Ground Floor',18,42,180),
  (1907,11,0,'Ground Floor',18,42,180),
  (1908,12,0,'Ground Floor',18,42,180),
  (1909,13,0,'Ground Floor',18,42,180),
  (1910,14,0,'Ground Floor',18,42,180),
  (1911,15,0,'Ground Floor',18,42,180),
  (1912,16,0,'Ground Floor',18,42,180),
  (1913,110,1,'Floor 1',18,42,181),
  (1914,111,1,'Floor 1',18,42,181),
  (1915,112,1,'Floor 1',18,42,181),
  (1916,113,1,'Floor 1',18,42,181),
  (1917,114,1,'Floor 1',18,42,181),
  (1918,115,1,'Floor 1',18,42,181),
  (1919,116,1,'Floor 1',18,42,181),
  (1920,117,1,'Floor 1',18,42,181),
  (1921,118,1,'Floor 1',18,42,181),
  (1922,207,2,'Floor 2',18,42,182),
  (1923,208,2,'Floor 2',18,42,182),
  (1924,209,2,'Floor 2',18,42,182),
  (1925,210,2,'Floor 2',18,42,182),
  (1926,211,2,'Floor 2',18,42,182),
  (1927,212,2,'Floor 2',18,42,182),
  (1928,306,3,'Floor 3',18,42,183),
  (1929,307,3,'Floor 3',18,42,183),
  (1930,308,3,'Floor 3',18,42,183),
  (1931,309,3,'Floor 3',18,42,183),
  (1932,310,3,'Floor 3',18,42,183),
  (1933,7,0,'Ground Commercial',20,45,184),
  (1934,8,0,'Ground Commercial',20,45,184),
  (1935,9,0,'Ground Commercial',20,45,184),
  (1936,10,0,'Ground Commercial',20,45,184),
  (1937,11,0,'Ground Commercial',20,45,184),
  (1938,12,0,'Ground Commercial',20,45,184),
  (1939,107,1,'Ground Residential',20,45,185),
  (1940,108,1,'Ground Residential',20,45,185),
  (1941,109,1,'Ground Residential',20,45,185),
  (1942,110,1,'Ground Residential',20,45,185),
  (1943,111,1,'Ground Residential',20,45,185),
  (1944,112,1,'Ground Residential',20,45,185),
  (1945,108,1,'Floor 1',20,45,186),
  (1946,109,1,'Floor 1',20,45,186),
  (1947,110,1,'Floor 1',20,45,186),
  (1948,111,1,'Floor 1',20,45,186),
  (1949,112,1,'Floor 1',20,45,186),
  (1950,113,1,'Floor 1',20,45,186),
  (1951,114,1,'Floor 1',20,45,186),
  (1952,204,2,'Floor 2',20,45,187),
  (1953,205,2,'Floor 2',20,45,187),
  (1954,206,2,'Floor 2',20,45,187),
  (1955,306,3,'Floor 3',20,45,188),
  (1956,307,3,'Floor 3',20,45,188),
  (1957,308,3,'Floor 3',20,45,188),
  (1958,309,3,'Floor 3',20,45,188),
  (1959,310,3,'Floor 3',20,45,188);

COMMIT;

#
# Data for the `floor_details` table  (LIMIT 0,500)
#

INSERT INTO `floor_details` (`floor_id`, `floor_number`, `floor_name`, `wing_id`, `user_id`, `created_datetime`, `updated_datetime`) VALUES 
  (1,0,'Ground Residential',39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (2,0,'Ground Commercial',39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (3,1,'Floor 1',39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (4,2,'Floor 2',39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (5,-1,'Basement',40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (6,0,'Ground Floor',40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (7,1,'Floor 1',40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (8,2,'Floor 2',40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (9,3,'Floor 3',40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (10,0,'Ground Commercial',41,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (11,1,'Ground Residential',41,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (12,1,'Floor 1',41,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (13,2,'Floor 2',41,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (14,3,'Floor 3',41,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (15,-1,'Basement',42,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (16,0,'Ground Floor',42,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (17,1,'Floor 1',42,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (18,2,'Floor 2',42,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (19,3,'Floor 3',42,2,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (20,0,'Ground Commercial',43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (21,1,'Ground Residential',43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (22,1,'Floor 1',43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (23,2,'Floor 2',43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (24,3,'Floor 3',43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (25,-1,'Basement',44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (26,0,'Ground Floor',44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (27,1,'Floor 1',44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (28,2,'Floor 2',44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (29,0,'Ground Commercial',45,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (30,1,'Ground Residential',45,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (31,1,'Floor 1',45,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (32,2,'Floor 2',45,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (33,-1,'Basement',46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (34,0,'Ground Floor',46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (35,1,'Floor 1',46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (36,2,'Floor 2',46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (37,3,'Floor 3',46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM'),
  (38,0,'Ground Comm.',47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM'),
  (39,1,'Ground Residential',47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM'),
  (40,1,'Floor 1',47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM'),
  (41,2,'Floor 2',47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM'),
  (42,3,'Floor 3',47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM'),
  (43,3,'Floor 3',45,2,'10/09/2018 11:01:29 AM','10/09/2018 11:01:29 AM');

COMMIT;

#
# Data for the `followup_details` table  (LIMIT 0,500)
#

INSERT INTO `followup_details` (`followup_id`, `followup_descr`, `enquiry_id`, `user_id`, `is_remove`, `created_datetime`, `updated_datetime`) VALUES 
  (1,'First Followup',1,0,0,'26/06/2018 10:35:31 AM','26/06/2018 10:35:31 AM'),
  (2,'First Followup',2,0,0,'26/06/2018 10:36:36 AM','26/06/2018 10:36:36 AM'),
  (3,'First Followup',3,0,0,'26/06/2018 10:37:42 AM','26/06/2018 10:37:42 AM'),
  (4,'First Followup',4,2,0,'29/06/2018 11:01:57 AM','29/06/2018 11:01:57 AM'),
  (5,'First Followup',5,2,0,'29/06/2018 03:26:38 PM','29/06/2018 03:26:38 PM'),
  (6,'First Followup',6,2,0,'29/06/2018 03:29:07 PM','29/06/2018 03:29:07 PM'),
  (7,'First Followup',7,2,0,'29/06/2018 03:31:00 PM','29/06/2018 03:31:00 PM'),
  (8,'First Followup',8,2,0,'29/06/2018 03:33:35 PM','29/06/2018 03:33:35 PM'),
  (9,'First Followup',9,2,0,'30/06/2018 06:18:28 PM','30/06/2018 06:18:28 PM'),
  (10,'Again visited with family',9,2,0,'30/06/2018 06:19:40 PM','30/06/2018 06:19:40 PM'),
  (11,'First Followup',10,2,0,'02/07/2018 05:07:09 PM','02/07/2018 05:07:09 PM'),
  (12,'First Followup',12,2,0,'07/07/2018 12:14:18 PM','07/07/2018 12:14:18 PM'),
  (13,'First Followup',13,5,0,'12/07/2018 02:56:28 PM','12/07/2018 02:56:28 PM'),
  (14,'First Followup',14,2,0,'13/07/2018 10:59:15 AM','13/07/2018 10:59:15 AM'),
  (15,'First Followup',15,2,0,'13/07/2018 11:24:08 AM','13/07/2018 11:24:08 AM'),
  (16,'First Followup',16,2,0,'13/07/2018 11:25:30 AM','13/07/2018 11:25:30 AM'),
  (17,'First Followup',17,2,0,'13/07/2018 11:51:52 AM','13/07/2018 11:51:52 AM'),
  (18,'second followup',8,2,0,'13/07/2018 04:17:20 PM','13/07/2018 04:17:20 PM'),
  (19,'abcd',6,2,0,'13/07/2018 04:18:29 PM','13/07/2018 04:18:29 PM'),
  (20,'First Followup',18,2,0,'14/07/2018 04:44:06 PM','14/07/2018 04:44:06 PM'),
  (21,'First Followup',19,2,0,'14/07/2018 04:47:02 PM','14/07/2018 04:47:02 PM'),
  (22,'First Followup',20,2,0,'14/07/2018 05:00:14 PM','14/07/2018 05:00:14 PM'),
  (23,'First Followup',21,2,0,'14/07/2018 05:01:47 PM','14/07/2018 05:01:47 PM'),
  (24,'First Followup',22,2,0,'14/07/2018 05:04:15 PM','14/07/2018 05:04:15 PM'),
  (25,'First Followup',23,2,0,'16/07/2018 03:19:37 PM','16/07/2018 03:19:37 PM'),
  (26,'First Followup',24,2,0,'16/07/2018 03:21:48 PM','16/07/2018 03:21:48 PM'),
  (27,'First Followup',25,2,0,'16/07/2018 03:23:59 PM','16/07/2018 03:23:59 PM'),
  (28,'First Followup',26,2,0,'16/07/2018 03:26:46 PM','16/07/2018 03:26:46 PM'),
  (29,'First Followup',27,2,0,'16/07/2018 03:36:29 PM','16/07/2018 03:36:29 PM'),
  (30,'First Followup',28,2,0,'16/07/2018 03:41:38 PM','16/07/2018 03:41:38 PM'),
  (31,'First Followup',29,2,0,'16/07/2018 03:49:11 PM','16/07/2018 03:49:11 PM'),
  (32,'Second followup',25,2,0,'17/07/2018 09:27:05 AM','17/07/2018 09:27:05 AM'),
  (33,'coming within 10 days',5,2,0,'20/07/2018 09:53:59 AM','20/07/2018 09:53:59 AM'),
  (34,'First Followup',30,2,0,'20/07/2018 10:11:33 AM','20/07/2018 10:11:33 AM'),
  (35,'First Followup',31,2,0,'20/07/2018 04:39:15 PM','20/07/2018 04:39:15 PM'),
  (36,'First Followup',32,2,0,'21/07/2018 10:30:43 AM','21/07/2018 10:30:43 AM'),
  (37,'First Followup',33,2,0,'21/07/2018 10:31:15 AM','21/07/2018 10:31:15 AM'),
  (38,'Second Followup',30,2,0,'10/08/2018 01:21:11 PM','10/08/2018 01:21:11 PM'),
  (39,'abcs',30,2,0,'17/08/2018 10:26:52 AM','17/08/2018 10:26:52 AM'),
  (40,'aaa bbbbb',30,2,0,'17/08/2018 10:33:39 AM','17/08/2018 10:33:39 AM'),
  (41,'First Followup',34,2,0,'18/08/2018 05:06:45 PM','18/08/2018 05:06:45 PM'),
  (42,'First Visit',35,2,0,'04/09/2018 12:52:11 PM','04/09/2018 12:52:11 PM'),
  (43,'First Visit',36,2,0,'04/09/2018 12:53:12 PM','04/09/2018 12:53:12 PM'),
  (44,'First Visit',37,2,0,'04/09/2018 12:54:03 PM','04/09/2018 12:54:03 PM'),
  (45,'First Visit',38,2,0,'04/09/2018 12:55:05 PM','04/09/2018 12:55:05 PM'),
  (46,'First Visit',39,2,0,'04/09/2018 12:56:15 PM','04/09/2018 12:56:15 PM'),
  (47,'First Visit',40,2,0,'04/09/2018 12:56:56 PM','04/09/2018 12:56:56 PM'),
  (48,'First Visit',41,2,0,'04/09/2018 12:57:47 PM','04/09/2018 12:57:47 PM'),
  (49,'First Visit',42,2,0,'04/09/2018 12:58:34 PM','04/09/2018 12:58:34 PM'),
  (50,'First Visit',43,2,0,'04/09/2018 12:59:15 PM','04/09/2018 12:59:15 PM'),
  (51,'First Visit',44,2,0,'04/09/2018 01:00:19 PM','04/09/2018 01:00:19 PM'),
  (52,'First Visit',45,2,0,'05/09/2018 09:38:05 AM','05/09/2018 09:38:05 AM'),
  (53,'dddd',30,2,0,'11/09/2018 01:17:19 PM','11/09/2018 01:17:19 PM'),
  (54,'First Visit',46,2,0,'15/09/2018 09:10:34 AM','15/09/2018 09:10:34 AM'),
  (55,'First Visit',47,2,0,'17/09/2018 03:50:15 PM','17/09/2018 03:50:15 PM'),
  (56,'First Visit',48,2,0,'17/09/2018 03:50:56 PM','17/09/2018 03:50:56 PM'),
  (57,'First Visit',49,2,0,'17/09/2018 03:52:25 PM','17/09/2018 03:52:25 PM'),
  (58,'First Visit',50,2,0,'17/11/2018 10:11:10 AM','17/11/2018 10:11:10 AM');

COMMIT;

#
# Data for the `login_details` table  (LIMIT 0,500)
#

INSERT INTO `login_details` (`login_details_id`, `login_id`, `login_datetime`, `latitude`, `longitude`, `location`, `device_name`, `ip_address`, `created_datetime`, `update_datetime`) VALUES 
  (1,1,'17/04/2018 01:17:42 PM','19.9833','73.8','Nashik',NULL,'1.186.138.227','17/04/2018 01:17:42 PM','17/04/2018 01:17:42 PM'),
  (2,1,'17/04/2018 01:29:26 PM','19.9833','73.8','Nashik',NULL,'1.186.138.227','17/04/2018 01:29:26 PM','17/04/2018 01:29:26 PM'),
  (3,1,'17/04/2018 04:30:04 PM','19.9833','73.8','Nashik,Maharashtra,India',NULL,'1.186.138.227','17/04/2018 04:30:04 PM','17/04/2018 04:30:04 PM'),
  (4,1,'17/04/2018 05:04:03 PM','19.9833','73.8','Nashik,Maharashtra,India',NULL,'1.186.138.227','17/04/2018 05:04:03 PM','17/04/2018 05:04:03 PM'),
  (5,1,'18/04/2018 08:56:01 AM',' ',' ','geolocation not supported',NULL,' ','18/04/2018 08:56:01 AM','18/04/2018 08:56:01 AM'),
  (6,1,'18/04/2018 12:46:59 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','18/04/2018 12:46:59 PM','18/04/2018 12:46:59 PM'),
  (7,1,'18/04/2018 02:23:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','18/04/2018 02:23:52 PM','18/04/2018 02:23:52 PM'),
  (8,1,'18/04/2018 03:18:37 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','18/04/2018 03:18:37 PM','18/04/2018 03:18:37 PM'),
  (9,1,'19/04/2018 09:00:24 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','19/04/2018 09:00:24 AM','19/04/2018 09:00:24 AM'),
  (10,1,'19/04/2018 12:38:19 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','19/04/2018 12:38:19 PM','19/04/2018 12:38:19 PM'),
  (11,1,'19/04/2018 04:57:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','19/04/2018 04:57:56 PM','19/04/2018 04:57:56 PM'),
  (12,1,'20/04/2018 08:59:22 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','20/04/2018 08:59:22 AM','20/04/2018 08:59:22 AM'),
  (13,1,'20/04/2018 10:49:54 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','20/04/2018 10:49:54 AM','20/04/2018 10:49:54 AM'),
  (14,2,'20/04/2018 10:50:37 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','20/04/2018 10:50:37 AM','20/04/2018 10:50:37 AM'),
  (15,1,'20/04/2018 11:04:20 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','20/04/2018 11:04:20 AM','20/04/2018 11:04:20 AM'),
  (16,1,'20/04/2018 11:15:51 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','20/04/2018 11:15:51 AM','20/04/2018 11:15:51 AM'),
  (17,1,'21/04/2018 09:09:16 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','21/04/2018 09:09:16 AM','21/04/2018 09:09:16 AM'),
  (18,1,'21/04/2018 01:15:16 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','21/04/2018 01:15:16 PM','21/04/2018 01:15:16 PM'),
  (19,1,'23/04/2018 08:56:38 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','23/04/2018 08:56:38 AM','23/04/2018 08:56:38 AM'),
  (20,1,'23/04/2018 10:15:40 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','23/04/2018 10:15:40 AM','23/04/2018 10:15:40 AM'),
  (21,1,'23/04/2018 11:00:39 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','23/04/2018 11:00:39 AM','23/04/2018 11:00:39 AM'),
  (22,1,'23/04/2018 03:03:10 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','23/04/2018 03:03:10 PM','23/04/2018 03:03:10 PM'),
  (23,1,'24/04/2018 08:57:16 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','24/04/2018 08:57:16 AM','24/04/2018 08:57:16 AM'),
  (24,1,'24/04/2018 09:58:42 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','24/04/2018 09:58:42 AM','24/04/2018 09:58:42 AM'),
  (25,1,'24/04/2018 11:07:41 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','24/04/2018 11:07:41 AM','24/04/2018 11:07:41 AM'),
  (26,1,'24/04/2018 11:35:11 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','24/04/2018 11:35:11 AM','24/04/2018 11:35:11 AM'),
  (27,1,'24/04/2018 11:45:06 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','24/04/2018 11:45:06 AM','24/04/2018 11:45:06 AM'),
  (28,1,'24/04/2018 02:26:54 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','24/04/2018 02:26:54 PM','24/04/2018 02:26:54 PM'),
  (29,1,'24/04/2018 02:32:31 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','24/04/2018 02:32:31 PM','24/04/2018 02:32:31 PM'),
  (30,1,'25/04/2018 09:07:35 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','25/04/2018 09:07:35 AM','25/04/2018 09:07:35 AM'),
  (31,1,'25/04/2018 09:24:18 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','25/04/2018 09:24:18 AM','25/04/2018 09:24:18 AM'),
  (32,1,'25/04/2018 12:43:54 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','25/04/2018 12:43:54 PM','25/04/2018 12:43:54 PM'),
  (33,1,'25-04-2018 04:54:06 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','25-04-2018 04:54:06 PM','25-04-2018 04:54:06 PM'),
  (34,1,'26-04-2018 08:58:34 AM',' ',' ','geolocation not supported','Firefox 59( Windows 7 )',' ','26-04-2018 08:58:34 AM','26-04-2018 08:58:34 AM'),
  (35,1,'26-04-2018 11:06:07 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','26-04-2018 11:06:07 AM','26-04-2018 11:06:07 AM'),
  (36,1,'27-04-2018 08:57:16 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','27-04-2018 08:57:16 AM','27-04-2018 08:57:16 AM'),
  (37,1,'30-04-2018 05:24:21 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','30-04-2018 05:24:21 PM','30-04-2018 05:24:21 PM'),
  (38,1,'03-05-2018 09:12:07 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 59( Windows 7 )','1.186.138.227','03-05-2018 09:12:07 AM','03-05-2018 09:12:07 AM'),
  (39,2,'14-06-2018 12:30:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:30:49 PM','14-06-2018 12:30:49 PM'),
  (40,2,'14-06-2018 12:33:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:33:52 PM','14-06-2018 12:33:52 PM'),
  (41,2,'14-06-2018 12:37:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:37:49 PM','14-06-2018 12:37:49 PM'),
  (42,2,'14-06-2018 12:40:51 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:40:51 PM','14-06-2018 12:40:51 PM'),
  (43,2,'14-06-2018 12:51:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:51:01 PM','14-06-2018 12:51:01 PM'),
  (44,2,'14-06-2018 12:52:04 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:52:04 PM','14-06-2018 12:52:04 PM'),
  (45,2,'14-06-2018 12:53:38 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:53:38 PM','14-06-2018 12:53:38 PM'),
  (46,2,'14-06-2018 12:59:31 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 12:59:31 PM','14-06-2018 12:59:31 PM'),
  (47,2,'14-06-2018 01:12:13 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 01:12:13 PM','14-06-2018 01:12:13 PM'),
  (48,2,'14-06-2018 01:15:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 01:15:12 PM','14-06-2018 01:15:12 PM'),
  (49,2,'14-06-2018 03:21:24 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 03:21:24 PM','14-06-2018 03:21:24 PM'),
  (50,2,'14-06-2018 03:26:21 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 03:26:21 PM','14-06-2018 03:26:21 PM'),
  (51,2,'14-06-2018 03:28:03 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 03:28:03 PM','14-06-2018 03:28:03 PM'),
  (52,2,'14-06-2018 03:51:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 03:51:15 PM','14-06-2018 03:51:15 PM'),
  (53,2,'14-06-2018 04:59:59 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 04:59:59 PM','14-06-2018 04:59:59 PM'),
  (54,2,'14-06-2018 05:04:11 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 05:04:11 PM','14-06-2018 05:04:11 PM'),
  (55,2,'14-06-2018 05:07:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 05:07:12 PM','14-06-2018 05:07:12 PM'),
  (56,5,'14-06-2018 05:09:10 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 05:09:10 PM','14-06-2018 05:09:10 PM'),
  (57,6,'14-06-2018 05:09:31 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 05:09:31 PM','14-06-2018 05:09:31 PM'),
  (58,5,'14-06-2018 05:09:40 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','14-06-2018 05:09:40 PM','14-06-2018 05:09:40 PM'),
  (59,5,'15-06-2018 08:58:31 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 08:58:31 AM','15-06-2018 08:58:31 AM'),
  (60,2,'15-06-2018 08:59:26 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 08:59:26 AM','15-06-2018 08:59:26 AM'),
  (61,2,'15-06-2018 09:09:24 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 09:09:24 AM','15-06-2018 09:09:24 AM'),
  (62,2,'15-06-2018 09:20:59 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 09:20:59 AM','15-06-2018 09:20:59 AM'),
  (63,2,'15-06-2018 09:24:41 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 09:24:41 AM','15-06-2018 09:24:41 AM'),
  (64,2,'15-06-2018 09:41:41 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 09:41:41 AM','15-06-2018 09:41:41 AM'),
  (65,2,'15-06-2018 09:54:13 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 09:54:13 AM','15-06-2018 09:54:13 AM'),
  (66,2,'15-06-2018 11:36:34 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 11:36:34 AM','15-06-2018 11:36:34 AM'),
  (67,2,'15-06-2018 11:41:39 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 11:41:39 AM','15-06-2018 11:41:39 AM'),
  (68,2,'15-06-2018 11:44:05 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 11:44:05 AM','15-06-2018 11:44:05 AM'),
  (69,2,'15-06-2018 11:50:58 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 11:50:58 AM','15-06-2018 11:50:58 AM'),
  (70,2,'15-06-2018 11:55:33 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 11:55:33 AM','15-06-2018 11:55:33 AM'),
  (71,2,'15-06-2018 11:59:48 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 11:59:48 AM','15-06-2018 11:59:48 AM'),
  (72,2,'15-06-2018 12:49:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 12:49:52 PM','15-06-2018 12:49:52 PM'),
  (73,2,'15-06-2018 01:34:47 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 01:34:47 PM','15-06-2018 01:34:47 PM'),
  (74,2,'15-06-2018 02:18:51 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 02:18:51 PM','15-06-2018 02:18:51 PM'),
  (75,2,'15-06-2018 02:22:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 02:22:15 PM','15-06-2018 02:22:15 PM'),
  (76,2,'15-06-2018 02:25:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 02:25:01 PM','15-06-2018 02:25:01 PM'),
  (77,2,'15-06-2018 02:27:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 02:27:49 PM','15-06-2018 02:27:49 PM'),
  (78,2,'15-06-2018 02:56:41 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 02:56:41 PM','15-06-2018 02:56:41 PM'),
  (79,2,'15-06-2018 04:08:46 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','15-06-2018 04:08:46 PM','15-06-2018 04:08:46 PM'),
  (80,2,'16-06-2018 08:57:56 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 08:57:56 AM','16-06-2018 08:57:56 AM'),
  (81,2,'16-06-2018 09:26:52 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:26:52 AM','16-06-2018 09:26:52 AM'),
  (82,2,'16-06-2018 09:27:54 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:27:54 AM','16-06-2018 09:27:54 AM'),
  (83,2,'16-06-2018 09:30:11 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:30:11 AM','16-06-2018 09:30:11 AM'),
  (84,2,'16-06-2018 09:32:18 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:32:18 AM','16-06-2018 09:32:18 AM'),
  (85,2,'16-06-2018 09:36:18 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:36:18 AM','16-06-2018 09:36:18 AM'),
  (86,2,'16-06-2018 09:37:51 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:37:51 AM','16-06-2018 09:37:51 AM'),
  (87,2,'16-06-2018 09:42:24 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:42:24 AM','16-06-2018 09:42:24 AM'),
  (88,2,'16-06-2018 09:43:56 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:43:56 AM','16-06-2018 09:43:56 AM'),
  (89,2,'16-06-2018 09:46:56 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:46:56 AM','16-06-2018 09:46:56 AM'),
  (90,2,'16-06-2018 09:49:09 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:49:09 AM','16-06-2018 09:49:09 AM'),
  (91,2,'16-06-2018 09:51:22 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:51:22 AM','16-06-2018 09:51:22 AM'),
  (92,2,'16-06-2018 09:54:23 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 09:54:23 AM','16-06-2018 09:54:23 AM'),
  (93,2,'16-06-2018 10:04:54 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 10:04:54 AM','16-06-2018 10:04:54 AM'),
  (94,2,'16-06-2018 11:13:42 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 11:13:42 AM','16-06-2018 11:13:42 AM'),
  (95,2,'16-06-2018 11:28:48 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 11:28:48 AM','16-06-2018 11:28:48 AM'),
  (96,2,'16-06-2018 12:58:19 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 12:58:19 PM','16-06-2018 12:58:19 PM'),
  (97,1,'16-06-2018 02:23:25 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Windows 7 )','1.186.138.227','16-06-2018 02:23:25 PM','16-06-2018 02:23:25 PM'),
  (98,1,'16-06-2018 02:45:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 02:45:56 PM','16-06-2018 02:45:56 PM'),
  (99,2,'16-06-2018 02:46:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 02:46:56 PM','16-06-2018 02:46:56 PM'),
  (100,2,'16-06-2018 03:30:51 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 03:30:51 PM','16-06-2018 03:30:51 PM'),
  (101,2,'16-06-2018 03:39:50 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 03:39:50 PM','16-06-2018 03:39:50 PM'),
  (102,2,'16-06-2018 03:50:33 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 03:50:33 PM','16-06-2018 03:50:33 PM'),
  (103,2,'16-06-2018 05:04:02 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','16-06-2018 05:04:02 PM','16-06-2018 05:04:02 PM'),
  (104,2,'18-06-2018 08:55:32 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 08:55:32 AM','18-06-2018 08:55:32 AM'),
  (105,2,'18-06-2018 09:04:49 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:04:49 AM','18-06-2018 09:04:49 AM'),
  (106,2,'18-06-2018 09:08:59 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:08:59 AM','18-06-2018 09:08:59 AM'),
  (107,1,'18-06-2018 09:13:53 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:13:53 AM','18-06-2018 09:13:53 AM'),
  (108,2,'18-06-2018 09:14:07 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:14:07 AM','18-06-2018 09:14:07 AM'),
  (109,2,'18-06-2018 09:15:32 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:15:32 AM','18-06-2018 09:15:32 AM'),
  (110,2,'18-06-2018 09:18:24 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:18:24 AM','18-06-2018 09:18:24 AM'),
  (111,2,'18-06-2018 09:21:30 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:21:30 AM','18-06-2018 09:21:30 AM'),
  (112,2,'18-06-2018 09:25:58 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:25:58 AM','18-06-2018 09:25:58 AM'),
  (113,2,'18-06-2018 09:29:44 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 09:29:44 AM','18-06-2018 09:29:44 AM'),
  (114,2,'18-06-2018 11:00:15 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 11:00:15 AM','18-06-2018 11:00:15 AM'),
  (115,2,'18-06-2018 11:13:57 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 11:13:57 AM','18-06-2018 11:13:57 AM'),
  (116,2,'18-06-2018 11:17:12 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 11:17:12 AM','18-06-2018 11:17:12 AM'),
  (117,2,'18-06-2018 11:19:37 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 11:19:37 AM','18-06-2018 11:19:37 AM'),
  (118,2,'18-06-2018 11:21:55 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 11:21:55 AM','18-06-2018 11:21:55 AM'),
  (119,2,'18-06-2018 11:35:42 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 11:35:42 AM','18-06-2018 11:35:42 AM'),
  (120,2,'18-06-2018 12:16:41 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 12:16:41 PM','18-06-2018 12:16:41 PM'),
  (121,2,'18-06-2018 01:09:09 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 01:09:09 PM','18-06-2018 01:09:09 PM'),
  (122,2,'18-06-2018 01:17:51 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 01:17:51 PM','18-06-2018 01:17:51 PM'),
  (123,2,'18-06-2018 02:29:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 02:29:01 PM','18-06-2018 02:29:01 PM'),
  (124,2,'18-06-2018 02:31:36 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 02:31:36 PM','18-06-2018 02:31:36 PM'),
  (125,2,'18-06-2018 02:37:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 02:37:32 PM','18-06-2018 02:37:32 PM'),
  (126,2,'18-06-2018 02:44:57 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 02:44:57 PM','18-06-2018 02:44:57 PM'),
  (127,2,'18-06-2018 02:50:26 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 02:50:26 PM','18-06-2018 02:50:26 PM'),
  (128,2,'18-06-2018 03:18:44 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:18:44 PM','18-06-2018 03:18:44 PM'),
  (129,2,'18-06-2018 03:23:25 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:23:25 PM','18-06-2018 03:23:25 PM'),
  (130,2,'18-06-2018 03:26:06 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:26:06 PM','18-06-2018 03:26:06 PM'),
  (131,2,'18-06-2018 03:38:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:38:52 PM','18-06-2018 03:38:52 PM'),
  (132,2,'18-06-2018 03:41:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:41:05 PM','18-06-2018 03:41:05 PM'),
  (133,2,'18-06-2018 03:42:41 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:42:41 PM','18-06-2018 03:42:41 PM'),
  (134,2,'18-06-2018 03:45:29 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:45:29 PM','18-06-2018 03:45:29 PM'),
  (135,2,'18-06-2018 03:46:55 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:46:55 PM','18-06-2018 03:46:55 PM'),
  (136,2,'18-06-2018 03:59:04 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 03:59:04 PM','18-06-2018 03:59:04 PM'),
  (137,2,'18-06-2018 04:09:57 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:09:57 PM','18-06-2018 04:09:57 PM'),
  (138,2,'18-06-2018 04:16:16 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:16:16 PM','18-06-2018 04:16:16 PM'),
  (139,2,'18-06-2018 04:19:23 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:19:23 PM','18-06-2018 04:19:23 PM'),
  (140,2,'18-06-2018 04:20:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:20:15 PM','18-06-2018 04:20:15 PM'),
  (141,2,'18-06-2018 04:24:47 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:24:47 PM','18-06-2018 04:24:47 PM'),
  (142,2,'18-06-2018 04:26:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:26:17 PM','18-06-2018 04:26:17 PM'),
  (143,2,'18-06-2018 04:27:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:27:32 PM','18-06-2018 04:27:32 PM'),
  (144,2,'18-06-2018 04:29:38 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:29:38 PM','18-06-2018 04:29:38 PM'),
  (145,2,'18-06-2018 04:39:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:39:05 PM','18-06-2018 04:39:05 PM'),
  (146,2,'18-06-2018 04:39:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:39:52 PM','18-06-2018 04:39:52 PM'),
  (147,2,'18-06-2018 04:41:08 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:41:08 PM','18-06-2018 04:41:08 PM'),
  (148,2,'18-06-2018 04:41:58 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:41:58 PM','18-06-2018 04:41:58 PM'),
  (149,2,'18-06-2018 04:44:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:44:15 PM','18-06-2018 04:44:15 PM'),
  (150,2,'18-06-2018 04:45:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:45:56 PM','18-06-2018 04:45:56 PM'),
  (151,2,'18-06-2018 04:47:26 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:47:26 PM','18-06-2018 04:47:26 PM'),
  (152,2,'18-06-2018 04:50:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:50:45 PM','18-06-2018 04:50:45 PM'),
  (153,2,'18-06-2018 04:58:11 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','18-06-2018 04:58:11 PM','18-06-2018 04:58:11 PM'),
  (154,2,'19-06-2018 09:09:16 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','19-06-2018 09:09:16 AM','19-06-2018 09:09:16 AM'),
  (155,2,'19-06-2018 09:29:39 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','19-06-2018 09:29:39 AM','19-06-2018 09:29:39 AM'),
  (156,2,'19-06-2018 09:44:35 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','19-06-2018 09:44:35 AM','19-06-2018 09:44:35 AM'),
  (157,2,'19-06-2018 10:07:17 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','19-06-2018 10:07:17 AM','19-06-2018 10:07:17 AM'),
  (158,2,'19-06-2018 10:17:46 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','19-06-2018 10:17:46 AM','19-06-2018 10:17:46 AM'),
  (159,2,'20-06-2018 08:54:57 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 08:54:57 AM','20-06-2018 08:54:57 AM'),
  (160,2,'20-06-2018 09:03:54 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 09:03:54 AM','20-06-2018 09:03:54 AM'),
  (161,2,'20-06-2018 09:10:07 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 09:10:07 AM','20-06-2018 09:10:07 AM'),
  (162,2,'20-06-2018 09:35:46 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 09:35:46 AM','20-06-2018 09:35:46 AM'),
  (163,2,'20-06-2018 09:49:02 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 09:49:02 AM','20-06-2018 09:49:02 AM'),
  (164,2,'20-06-2018 10:16:21 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 10:16:21 AM','20-06-2018 10:16:21 AM'),
  (165,2,'20-06-2018 10:26:50 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 10:26:50 AM','20-06-2018 10:26:50 AM'),
  (166,2,'20-06-2018 10:37:20 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 10:37:20 AM','20-06-2018 10:37:20 AM'),
  (167,2,'20-06-2018 10:39:19 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 10:39:19 AM','20-06-2018 10:39:19 AM'),
  (168,2,'20-06-2018 10:57:15 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 10:57:15 AM','20-06-2018 10:57:15 AM'),
  (169,2,'20-06-2018 11:24:50 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 11:24:50 AM','20-06-2018 11:24:50 AM'),
  (170,2,'20-06-2018 11:45:15 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 11:45:15 AM','20-06-2018 11:45:15 AM'),
  (171,2,'20-06-2018 12:07:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 12:07:12 PM','20-06-2018 12:07:12 PM'),
  (172,2,'20-06-2018 12:15:43 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 12:15:43 PM','20-06-2018 12:15:43 PM'),
  (173,2,'20-06-2018 12:23:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 12:23:20 PM','20-06-2018 12:23:20 PM'),
  (174,2,'20-06-2018 12:45:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 12:45:52 PM','20-06-2018 12:45:52 PM'),
  (175,2,'20-06-2018 12:50:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 12:50:12 PM','20-06-2018 12:50:12 PM'),
  (176,2,'20-06-2018 12:55:58 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 12:55:58 PM','20-06-2018 12:55:58 PM'),
  (177,2,'20-06-2018 01:07:35 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 01:07:35 PM','20-06-2018 01:07:35 PM'),
  (178,2,'20-06-2018 01:10:36 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 01:10:36 PM','20-06-2018 01:10:36 PM'),
  (179,2,'20-06-2018 01:19:58 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 01:19:58 PM','20-06-2018 01:19:58 PM'),
  (180,2,'20-06-2018 02:21:25 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 02:21:25 PM','20-06-2018 02:21:25 PM'),
  (181,2,'20-06-2018 02:26:38 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 02:26:38 PM','20-06-2018 02:26:38 PM'),
  (182,2,'20-06-2018 05:04:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','20-06-2018 05:04:32 PM','20-06-2018 05:04:32 PM'),
  (183,2,'21-06-2018 08:58:01 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 08:58:01 AM','21-06-2018 08:58:01 AM'),
  (184,2,'21-06-2018 09:02:50 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 09:02:50 AM','21-06-2018 09:02:50 AM'),
  (185,2,'21-06-2018 09:31:29 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 09:31:29 AM','21-06-2018 09:31:29 AM'),
  (186,2,'21-06-2018 10:18:36 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 10:18:36 AM','21-06-2018 10:18:36 AM'),
  (187,2,'21-06-2018 10:19:26 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 10:19:26 AM','21-06-2018 10:19:26 AM'),
  (188,2,'21-06-2018 10:28:14 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 10:28:14 AM','21-06-2018 10:28:14 AM'),
  (189,2,'21-06-2018 11:11:49 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 11:11:49 AM','21-06-2018 11:11:49 AM'),
  (190,2,'21-06-2018 11:49:02 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 11:49:02 AM','21-06-2018 11:49:02 AM'),
  (191,2,'21-06-2018 11:52:03 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 11:52:03 AM','21-06-2018 11:52:03 AM'),
  (192,2,'21-06-2018 11:57:40 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 11:57:40 AM','21-06-2018 11:57:40 AM'),
  (193,2,'21-06-2018 12:00:03 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 12:00:03 PM','21-06-2018 12:00:03 PM'),
  (194,2,'21-06-2018 12:03:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 12:03:20 PM','21-06-2018 12:03:20 PM'),
  (195,2,'21-06-2018 12:05:11 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 12:05:11 PM','21-06-2018 12:05:11 PM'),
  (196,2,'21-06-2018 02:44:10 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 02:44:10 PM','21-06-2018 02:44:10 PM'),
  (197,2,'21-06-2018 02:44:10 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 02:44:10 PM','21-06-2018 02:44:10 PM'),
  (198,2,'21-06-2018 02:49:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 02:49:32 PM','21-06-2018 02:49:32 PM'),
  (199,2,'21-06-2018 02:53:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 02:53:17 PM','21-06-2018 02:53:17 PM'),
  (200,2,'21-06-2018 02:56:58 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 02:56:58 PM','21-06-2018 02:56:58 PM'),
  (201,2,'21-06-2018 02:59:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 02:59:12 PM','21-06-2018 02:59:12 PM'),
  (202,2,'21-06-2018 03:01:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:01:45 PM','21-06-2018 03:01:45 PM'),
  (203,2,'21-06-2018 03:06:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:06:05 PM','21-06-2018 03:06:05 PM'),
  (204,2,'21-06-2018 03:09:42 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:09:42 PM','21-06-2018 03:09:42 PM'),
  (205,2,'21-06-2018 03:17:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:17:32 PM','21-06-2018 03:17:32 PM'),
  (206,2,'21-06-2018 03:18:54 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:18:54 PM','21-06-2018 03:18:54 PM'),
  (207,2,'21-06-2018 03:25:39 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:25:39 PM','21-06-2018 03:25:39 PM'),
  (208,2,'21-06-2018 03:27:46 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:27:46 PM','21-06-2018 03:27:46 PM'),
  (209,2,'21-06-2018 03:32:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:32:49 PM','21-06-2018 03:32:49 PM'),
  (210,2,'21-06-2018 03:39:18 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:39:18 PM','21-06-2018 03:39:18 PM'),
  (211,2,'21-06-2018 03:41:14 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:41:14 PM','21-06-2018 03:41:14 PM'),
  (212,2,'21-06-2018 03:46:16 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:46:16 PM','21-06-2018 03:46:16 PM'),
  (213,2,'21-06-2018 03:49:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 03:49:05 PM','21-06-2018 03:49:05 PM'),
  (214,2,'21-06-2018 04:08:11 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 04:08:11 PM','21-06-2018 04:08:11 PM'),
  (215,2,'21-06-2018 04:14:44 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 04:14:44 PM','21-06-2018 04:14:44 PM'),
  (216,2,'21-06-2018 04:19:04 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 04:19:04 PM','21-06-2018 04:19:04 PM'),
  (217,2,'21-06-2018 04:22:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 04:22:45 PM','21-06-2018 04:22:45 PM'),
  (218,2,'21-06-2018 04:46:19 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 04:46:19 PM','21-06-2018 04:46:19 PM'),
  (219,2,'21-06-2018 05:05:43 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','21-06-2018 05:05:43 PM','21-06-2018 05:05:43 PM'),
  (220,2,'22-06-2018 09:00:08 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:00:08 AM','22-06-2018 09:00:08 AM'),
  (221,2,'22-06-2018 09:07:32 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:07:32 AM','22-06-2018 09:07:32 AM'),
  (222,2,'22-06-2018 09:12:52 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:12:52 AM','22-06-2018 09:12:52 AM'),
  (223,2,'22-06-2018 09:15:19 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:15:19 AM','22-06-2018 09:15:19 AM'),
  (224,2,'22-06-2018 09:21:55 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:21:55 AM','22-06-2018 09:21:55 AM'),
  (225,2,'22-06-2018 09:24:45 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:24:45 AM','22-06-2018 09:24:45 AM'),
  (226,2,'22-06-2018 09:34:59 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:34:59 AM','22-06-2018 09:34:59 AM'),
  (227,2,'22-06-2018 09:37:57 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:37:57 AM','22-06-2018 09:37:57 AM'),
  (228,2,'22-06-2018 09:54:04 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:54:04 AM','22-06-2018 09:54:04 AM'),
  (229,2,'22-06-2018 09:55:12 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:55:12 AM','22-06-2018 09:55:12 AM'),
  (230,2,'22-06-2018 09:57:47 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 09:57:47 AM','22-06-2018 09:57:47 AM'),
  (231,2,'22-06-2018 10:04:18 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 10:04:18 AM','22-06-2018 10:04:18 AM'),
  (232,2,'22-06-2018 10:07:36 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 10:07:36 AM','22-06-2018 10:07:36 AM'),
  (233,2,'22-06-2018 10:09:58 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 10:09:58 AM','22-06-2018 10:09:58 AM'),
  (234,2,'22-06-2018 10:11:51 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 10:11:51 AM','22-06-2018 10:11:51 AM'),
  (235,2,'22-06-2018 10:20:41 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 10:20:41 AM','22-06-2018 10:20:41 AM'),
  (236,2,'22-06-2018 11:15:41 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 11:15:41 AM','22-06-2018 11:15:41 AM'),
  (237,2,'22-06-2018 11:17:04 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 11:17:04 AM','22-06-2018 11:17:04 AM'),
  (238,2,'22-06-2018 11:31:55 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 11:31:55 AM','22-06-2018 11:31:55 AM'),
  (239,2,'22-06-2018 11:38:00 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 11:38:00 AM','22-06-2018 11:38:00 AM'),
  (240,2,'22-06-2018 11:39:49 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 11:39:49 AM','22-06-2018 11:39:49 AM'),
  (241,2,'22-06-2018 12:34:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 12:34:05 PM','22-06-2018 12:34:05 PM'),
  (242,2,'22-06-2018 12:35:36 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 12:35:36 PM','22-06-2018 12:35:36 PM'),
  (243,2,'22-06-2018 12:37:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 12:37:15 PM','22-06-2018 12:37:15 PM'),
  (244,2,'22-06-2018 12:48:38 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 12:48:38 PM','22-06-2018 12:48:38 PM'),
  (245,2,'22-06-2018 12:51:16 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 12:51:16 PM','22-06-2018 12:51:16 PM'),
  (246,2,'22-06-2018 12:56:21 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 12:56:21 PM','22-06-2018 12:56:21 PM'),
  (247,2,'22-06-2018 12:58:54 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 12:58:54 PM','22-06-2018 12:58:54 PM'),
  (248,2,'22-06-2018 01:00:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 01:00:15 PM','22-06-2018 01:00:15 PM'),
  (249,2,'22-06-2018 01:12:43 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 01:12:43 PM','22-06-2018 01:12:43 PM'),
  (250,2,'22-06-2018 01:19:14 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 01:19:14 PM','22-06-2018 01:19:14 PM'),
  (251,2,'22-06-2018 01:30:23 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 01:30:23 PM','22-06-2018 01:30:23 PM'),
  (252,2,'22-06-2018 01:33:11 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 01:33:11 PM','22-06-2018 01:33:11 PM'),
  (253,2,'22-06-2018 01:34:27 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 01:34:27 PM','22-06-2018 01:34:27 PM'),
  (254,2,'22-06-2018 02:34:11 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 02:34:11 PM','22-06-2018 02:34:11 PM'),
  (255,2,'22-06-2018 02:36:08 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 02:36:08 PM','22-06-2018 02:36:08 PM'),
  (256,2,'22-06-2018 02:52:22 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 02:52:22 PM','22-06-2018 02:52:22 PM'),
  (257,2,'22-06-2018 02:55:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 02:55:12 PM','22-06-2018 02:55:12 PM'),
  (258,2,'22-06-2018 03:06:16 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 03:06:16 PM','22-06-2018 03:06:16 PM'),
  (259,2,'22-06-2018 03:07:31 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 03:07:31 PM','22-06-2018 03:07:31 PM'),
  (260,2,'22-06-2018 04:09:40 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 04:09:40 PM','22-06-2018 04:09:40 PM'),
  (261,2,'22-06-2018 04:10:43 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 04:10:43 PM','22-06-2018 04:10:43 PM'),
  (262,2,'22-06-2018 04:14:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 04:14:01 PM','22-06-2018 04:14:01 PM'),
  (263,2,'22-06-2018 05:05:33 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 05:05:33 PM','22-06-2018 05:05:33 PM'),
  (264,2,'22-06-2018 05:10:44 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','22-06-2018 05:10:44 PM','22-06-2018 05:10:44 PM'),
  (265,2,'23-06-2018 09:06:01 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:06:01 AM','23-06-2018 09:06:01 AM'),
  (266,2,'23-06-2018 09:10:22 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:10:22 AM','23-06-2018 09:10:22 AM'),
  (267,2,'23-06-2018 09:16:09 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:16:09 AM','23-06-2018 09:16:09 AM'),
  (268,2,'23-06-2018 09:17:50 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:17:50 AM','23-06-2018 09:17:50 AM'),
  (269,2,'23-06-2018 09:20:02 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:20:02 AM','23-06-2018 09:20:02 AM'),
  (270,2,'23-06-2018 09:20:48 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:20:48 AM','23-06-2018 09:20:48 AM'),
  (271,2,'23-06-2018 09:24:06 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:24:06 AM','23-06-2018 09:24:06 AM'),
  (272,2,'23-06-2018 09:27:31 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:27:31 AM','23-06-2018 09:27:31 AM'),
  (273,2,'23-06-2018 09:29:21 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:29:21 AM','23-06-2018 09:29:21 AM'),
  (274,2,'23-06-2018 09:31:14 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:31:14 AM','23-06-2018 09:31:14 AM'),
  (275,2,'23-06-2018 09:33:56 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:33:56 AM','23-06-2018 09:33:56 AM'),
  (276,2,'23-06-2018 09:37:12 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:37:12 AM','23-06-2018 09:37:12 AM'),
  (277,2,'23-06-2018 09:38:26 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:38:26 AM','23-06-2018 09:38:26 AM'),
  (278,2,'23-06-2018 09:50:35 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:50:35 AM','23-06-2018 09:50:35 AM'),
  (279,2,'23-06-2018 09:54:16 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:54:16 AM','23-06-2018 09:54:16 AM'),
  (280,2,'23-06-2018 09:55:04 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:55:04 AM','23-06-2018 09:55:04 AM'),
  (281,2,'23-06-2018 09:57:29 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:57:29 AM','23-06-2018 09:57:29 AM'),
  (282,2,'23-06-2018 09:58:17 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:58:17 AM','23-06-2018 09:58:17 AM'),
  (283,2,'23-06-2018 09:59:53 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 09:59:53 AM','23-06-2018 09:59:53 AM'),
  (284,2,'23-06-2018 10:00:41 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 10:00:41 AM','23-06-2018 10:00:41 AM'),
  (285,2,'23-06-2018 12:10:23 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:10:23 PM','23-06-2018 12:10:23 PM'),
  (286,2,'23-06-2018 12:11:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:11:17 PM','23-06-2018 12:11:17 PM'),
  (287,2,'23-06-2018 12:13:14 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:13:14 PM','23-06-2018 12:13:14 PM'),
  (288,2,'23-06-2018 12:19:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:19:15 PM','23-06-2018 12:19:15 PM'),
  (289,2,'23-06-2018 12:21:03 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:21:03 PM','23-06-2018 12:21:03 PM'),
  (290,2,'23-06-2018 12:27:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:27:20 PM','23-06-2018 12:27:20 PM'),
  (291,2,'23-06-2018 12:29:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:29:01 PM','23-06-2018 12:29:01 PM'),
  (292,2,'23-06-2018 12:33:22 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:33:22 PM','23-06-2018 12:33:22 PM'),
  (293,2,'23-06-2018 12:54:19 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:54:19 PM','23-06-2018 12:54:19 PM'),
  (294,2,'23-06-2018 12:57:03 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 12:57:03 PM','23-06-2018 12:57:03 PM'),
  (295,2,'23-06-2018 01:04:36 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 01:04:36 PM','23-06-2018 01:04:36 PM'),
  (296,2,'23-06-2018 01:08:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 01:08:01 PM','23-06-2018 01:08:01 PM'),
  (297,2,'23-06-2018 02:24:14 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:24:14 PM','23-06-2018 02:24:14 PM'),
  (298,2,'23-06-2018 02:33:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:33:07 PM','23-06-2018 02:33:07 PM'),
  (299,2,'23-06-2018 02:47:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:47:17 PM','23-06-2018 02:47:17 PM'),
  (300,2,'23-06-2018 02:48:29 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:48:29 PM','23-06-2018 02:48:29 PM'),
  (301,2,'23-06-2018 02:49:41 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:49:41 PM','23-06-2018 02:49:41 PM'),
  (302,2,'23-06-2018 02:50:43 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:50:43 PM','23-06-2018 02:50:43 PM'),
  (303,2,'23-06-2018 02:51:50 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:51:50 PM','23-06-2018 02:51:50 PM'),
  (304,2,'23-06-2018 02:54:08 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:54:08 PM','23-06-2018 02:54:08 PM'),
  (305,2,'23-06-2018 02:55:00 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:55:00 PM','23-06-2018 02:55:00 PM'),
  (306,2,'23-06-2018 02:57:37 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 02:57:37 PM','23-06-2018 02:57:37 PM'),
  (307,2,'23-06-2018 03:01:40 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:01:40 PM','23-06-2018 03:01:40 PM'),
  (308,2,'23-06-2018 03:03:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:03:45 PM','23-06-2018 03:03:45 PM'),
  (309,2,'23-06-2018 03:04:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:04:45 PM','23-06-2018 03:04:45 PM'),
  (310,2,'23-06-2018 03:13:46 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:13:46 PM','23-06-2018 03:13:46 PM'),
  (311,2,'23-06-2018 03:15:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:15:07 PM','23-06-2018 03:15:07 PM'),
  (312,2,'23-06-2018 03:15:40 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:15:40 PM','23-06-2018 03:15:40 PM'),
  (313,2,'23-06-2018 03:18:28 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:18:28 PM','23-06-2018 03:18:28 PM'),
  (314,2,'23-06-2018 03:20:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:20:12 PM','23-06-2018 03:20:12 PM'),
  (315,2,'23-06-2018 03:25:48 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:25:48 PM','23-06-2018 03:25:48 PM'),
  (316,2,'23-06-2018 03:48:02 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:48:02 PM','23-06-2018 03:48:02 PM'),
  (317,2,'23-06-2018 03:49:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:49:01 PM','23-06-2018 03:49:01 PM'),
  (318,2,'23-06-2018 03:50:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:50:20 PM','23-06-2018 03:50:20 PM'),
  (319,2,'23-06-2018 03:54:59 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:54:59 PM','23-06-2018 03:54:59 PM'),
  (320,2,'23-06-2018 03:58:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 03:58:07 PM','23-06-2018 03:58:07 PM'),
  (321,2,'23-06-2018 04:01:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:01:56 PM','23-06-2018 04:01:56 PM'),
  (322,2,'23-06-2018 04:06:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:06:49 PM','23-06-2018 04:06:49 PM'),
  (323,2,'23-06-2018 04:07:13 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:07:13 PM','23-06-2018 04:07:13 PM'),
  (324,2,'23-06-2018 04:19:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:19:07 PM','23-06-2018 04:19:07 PM'),
  (325,2,'23-06-2018 04:24:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:24:01 PM','23-06-2018 04:24:01 PM'),
  (326,2,'23-06-2018 04:25:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:25:07 PM','23-06-2018 04:25:07 PM'),
  (327,2,'23-06-2018 04:27:53 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:27:53 PM','23-06-2018 04:27:53 PM'),
  (328,2,'23-06-2018 04:39:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:39:52 PM','23-06-2018 04:39:52 PM'),
  (329,2,'23-06-2018 04:40:29 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:40:29 PM','23-06-2018 04:40:29 PM'),
  (330,2,'23-06-2018 04:41:39 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:41:39 PM','23-06-2018 04:41:39 PM'),
  (331,2,'23-06-2018 04:47:13 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:47:13 PM','23-06-2018 04:47:13 PM'),
  (332,2,'23-06-2018 04:48:53 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:48:53 PM','23-06-2018 04:48:53 PM'),
  (333,2,'23-06-2018 04:51:03 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:51:03 PM','23-06-2018 04:51:03 PM'),
  (334,2,'23-06-2018 04:54:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 04:54:17 PM','23-06-2018 04:54:17 PM'),
  (335,2,'23-06-2018 05:01:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 05:01:17 PM','23-06-2018 05:01:17 PM'),
  (336,2,'23-06-2018 05:02:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','23-06-2018 05:02:17 PM','23-06-2018 05:02:17 PM'),
  (337,2,'25-06-2018 09:01:31 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 09:01:31 AM','25-06-2018 09:01:31 AM'),
  (338,2,'25-06-2018 09:19:26 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 09:19:26 AM','25-06-2018 09:19:26 AM'),
  (339,2,'25-06-2018 09:41:22 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 09:41:22 AM','25-06-2018 09:41:22 AM'),
  (340,2,'25-06-2018 09:41:57 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 09:41:57 AM','25-06-2018 09:41:57 AM'),
  (341,2,'25-06-2018 09:43:33 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 09:43:33 AM','25-06-2018 09:43:33 AM'),
  (342,2,'25-06-2018 09:46:00 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 09:46:00 AM','25-06-2018 09:46:00 AM'),
  (343,2,'25-06-2018 10:11:36 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:11:36 AM','25-06-2018 10:11:36 AM'),
  (344,2,'25-06-2018 10:15:46 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:15:46 AM','25-06-2018 10:15:46 AM'),
  (345,2,'25-06-2018 10:18:38 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:18:38 AM','25-06-2018 10:18:38 AM'),
  (346,2,'25-06-2018 10:21:06 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:21:06 AM','25-06-2018 10:21:06 AM'),
  (347,2,'25-06-2018 10:22:15 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:22:15 AM','25-06-2018 10:22:15 AM'),
  (348,2,'25-06-2018 10:24:31 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:24:31 AM','25-06-2018 10:24:31 AM'),
  (349,2,'25-06-2018 10:27:54 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:27:54 AM','25-06-2018 10:27:54 AM'),
  (350,2,'25-06-2018 10:31:46 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:31:46 AM','25-06-2018 10:31:46 AM'),
  (351,2,'25-06-2018 10:37:09 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:37:09 AM','25-06-2018 10:37:09 AM'),
  (352,2,'25-06-2018 10:42:51 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:42:51 AM','25-06-2018 10:42:51 AM'),
  (353,2,'25-06-2018 10:55:41 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:55:41 AM','25-06-2018 10:55:41 AM'),
  (354,2,'25-06-2018 10:56:32 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:56:32 AM','25-06-2018 10:56:32 AM'),
  (355,2,'25-06-2018 10:57:21 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 10:57:21 AM','25-06-2018 10:57:21 AM'),
  (356,2,'25-06-2018 11:01:40 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 11:01:40 AM','25-06-2018 11:01:40 AM'),
  (357,2,'25-06-2018 11:07:01 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 11:07:01 AM','25-06-2018 11:07:01 AM'),
  (358,2,'25-06-2018 11:09:37 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 11:09:37 AM','25-06-2018 11:09:37 AM'),
  (359,2,'25-06-2018 11:13:09 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 11:13:09 AM','25-06-2018 11:13:09 AM'),
  (360,2,'25-06-2018 11:15:43 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 11:15:43 AM','25-06-2018 11:15:43 AM'),
  (361,5,'25-06-2018 11:38:23 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 11:38:23 AM','25-06-2018 11:38:23 AM'),
  (362,2,'25-06-2018 11:40:06 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 11:40:06 AM','25-06-2018 11:40:06 AM'),
  (363,2,'25-06-2018 12:10:53 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:10:53 PM','25-06-2018 12:10:53 PM'),
  (364,2,'25-06-2018 12:11:41 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:11:41 PM','25-06-2018 12:11:41 PM'),
  (365,2,'25-06-2018 12:12:30 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:12:30 PM','25-06-2018 12:12:30 PM'),
  (366,2,'25-06-2018 12:15:53 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:15:53 PM','25-06-2018 12:15:53 PM'),
  (367,2,'25-06-2018 12:23:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:23:07 PM','25-06-2018 12:23:07 PM'),
  (368,2,'25-06-2018 12:30:42 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:30:42 PM','25-06-2018 12:30:42 PM'),
  (369,2,'25-06-2018 12:32:26 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:32:26 PM','25-06-2018 12:32:26 PM'),
  (370,2,'25-06-2018 12:34:39 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:34:39 PM','25-06-2018 12:34:39 PM'),
  (371,2,'25-06-2018 12:37:10 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:37:10 PM','25-06-2018 12:37:10 PM'),
  (372,2,'25-06-2018 12:41:27 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:41:27 PM','25-06-2018 12:41:27 PM'),
  (373,2,'25-06-2018 12:44:15 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:44:15 PM','25-06-2018 12:44:15 PM'),
  (374,2,'25-06-2018 12:45:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:45:07 PM','25-06-2018 12:45:07 PM'),
  (375,2,'25-06-2018 12:55:04 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:55:04 PM','25-06-2018 12:55:04 PM'),
  (376,2,'25-06-2018 12:55:51 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:55:51 PM','25-06-2018 12:55:51 PM'),
  (377,2,'25-06-2018 12:57:29 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 12:57:29 PM','25-06-2018 12:57:29 PM'),
  (378,2,'25-06-2018 01:03:13 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 01:03:13 PM','25-06-2018 01:03:13 PM'),
  (379,2,'25-06-2018 01:14:10 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 01:14:10 PM','25-06-2018 01:14:10 PM'),
  (380,2,'25-06-2018 01:18:43 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 01:18:43 PM','25-06-2018 01:18:43 PM'),
  (381,2,'25-06-2018 01:21:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 01:21:56 PM','25-06-2018 01:21:56 PM'),
  (382,2,'25-06-2018 01:28:12 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 01:28:12 PM','25-06-2018 01:28:12 PM'),
  (383,2,'25-06-2018 01:30:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 01:30:32 PM','25-06-2018 01:30:32 PM'),
  (384,2,'25-06-2018 01:33:06 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 01:33:06 PM','25-06-2018 01:33:06 PM'),
  (385,2,'25-06-2018 02:15:19 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:15:19 PM','25-06-2018 02:15:19 PM'),
  (386,2,'25-06-2018 02:16:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:16:56 PM','25-06-2018 02:16:56 PM'),
  (387,2,'25-06-2018 02:19:28 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:19:28 PM','25-06-2018 02:19:28 PM'),
  (388,2,'25-06-2018 02:23:04 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:23:04 PM','25-06-2018 02:23:04 PM'),
  (389,2,'25-06-2018 02:24:48 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:24:48 PM','25-06-2018 02:24:48 PM'),
  (390,2,'25-06-2018 02:26:29 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:26:29 PM','25-06-2018 02:26:29 PM'),
  (391,2,'25-06-2018 02:29:22 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:29:22 PM','25-06-2018 02:29:22 PM'),
  (392,2,'25-06-2018 02:33:18 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:33:18 PM','25-06-2018 02:33:18 PM'),
  (393,2,'25-06-2018 02:34:30 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:34:30 PM','25-06-2018 02:34:30 PM'),
  (394,2,'25-06-2018 02:35:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:35:05 PM','25-06-2018 02:35:05 PM'),
  (395,2,'25-06-2018 02:36:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:36:32 PM','25-06-2018 02:36:32 PM'),
  (396,2,'25-06-2018 02:38:00 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:38:00 PM','25-06-2018 02:38:00 PM'),
  (397,2,'25-06-2018 02:39:53 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:39:53 PM','25-06-2018 02:39:53 PM'),
  (398,2,'25-06-2018 02:42:34 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:42:34 PM','25-06-2018 02:42:34 PM'),
  (399,2,'25-06-2018 02:45:40 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:45:40 PM','25-06-2018 02:45:40 PM'),
  (400,2,'25-06-2018 02:48:44 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:48:44 PM','25-06-2018 02:48:44 PM'),
  (401,2,'25-06-2018 02:49:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:49:49 PM','25-06-2018 02:49:49 PM'),
  (402,2,'25-06-2018 02:52:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:52:20 PM','25-06-2018 02:52:20 PM'),
  (403,2,'25-06-2018 02:53:41 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 02:53:41 PM','25-06-2018 02:53:41 PM'),
  (404,2,'25-06-2018 03:06:30 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:06:30 PM','25-06-2018 03:06:30 PM'),
  (405,2,'25-06-2018 03:13:29 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:13:29 PM','25-06-2018 03:13:29 PM'),
  (406,2,'25-06-2018 03:16:48 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:16:48 PM','25-06-2018 03:16:48 PM'),
  (407,2,'25-06-2018 03:18:26 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:18:26 PM','25-06-2018 03:18:26 PM'),
  (408,2,'25-06-2018 03:23:00 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:23:00 PM','25-06-2018 03:23:00 PM'),
  (409,2,'25-06-2018 03:43:21 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:43:21 PM','25-06-2018 03:43:21 PM'),
  (410,2,'25-06-2018 03:45:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:45:45 PM','25-06-2018 03:45:45 PM'),
  (411,2,'25-06-2018 03:46:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:46:32 PM','25-06-2018 03:46:32 PM'),
  (412,2,'25-06-2018 03:47:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:47:20 PM','25-06-2018 03:47:20 PM'),
  (413,2,'25-06-2018 03:50:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:50:01 PM','25-06-2018 03:50:01 PM'),
  (414,2,'25-06-2018 03:50:29 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:50:29 PM','25-06-2018 03:50:29 PM'),
  (415,2,'25-06-2018 03:52:34 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:52:34 PM','25-06-2018 03:52:34 PM'),
  (416,2,'25-06-2018 03:53:18 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:53:18 PM','25-06-2018 03:53:18 PM'),
  (417,2,'25-06-2018 03:53:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:53:49 PM','25-06-2018 03:53:49 PM'),
  (418,2,'25-06-2018 03:54:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:54:49 PM','25-06-2018 03:54:49 PM'),
  (419,2,'25-06-2018 03:55:50 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:55:50 PM','25-06-2018 03:55:50 PM'),
  (420,2,'25-06-2018 03:57:30 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 03:57:30 PM','25-06-2018 03:57:30 PM'),
  (421,2,'25-06-2018 04:00:00 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 04:00:00 PM','25-06-2018 04:00:00 PM'),
  (422,2,'25-06-2018 04:02:07 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 04:02:07 PM','25-06-2018 04:02:07 PM'),
  (423,2,'25-06-2018 04:04:56 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 04:04:56 PM','25-06-2018 04:04:56 PM'),
  (424,2,'25-06-2018 04:05:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 04:05:20 PM','25-06-2018 04:05:20 PM'),
  (425,2,'25-06-2018 04:07:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 04:07:05 PM','25-06-2018 04:07:05 PM'),
  (426,2,'25-06-2018 04:07:48 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','25-06-2018 04:07:48 PM','25-06-2018 04:07:48 PM'),
  (427,1,'26-06-2018 10:25:22 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','26-06-2018 10:25:22 AM','26-06-2018 10:25:22 AM'),
  (428,2,'26-06-2018 10:25:39 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 10:25:39 AM','26-06-2018 10:25:39 AM'),
  (429,2,'26-06-2018 10:25:51 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','26-06-2018 10:25:51 AM','26-06-2018 10:25:51 AM'),
  (430,1,'26-06-2018 10:38:21 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','26-06-2018 10:38:21 AM','26-06-2018 10:38:21 AM'),
  (431,2,'26-06-2018 10:49:44 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 10:49:44 AM','26-06-2018 10:49:44 AM'),
  (432,1,'26-06-2018 10:50:23 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','26-06-2018 10:50:23 AM','26-06-2018 10:50:23 AM'),
  (433,1,'26-06-2018 11:01:56 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','26-06-2018 11:01:56 AM','26-06-2018 11:01:56 AM'),
  (434,2,'26-06-2018 11:03:52 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','26-06-2018 11:03:52 AM','26-06-2018 11:03:52 AM'),
  (435,2,'26-06-2018 11:16:55 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','26-06-2018 11:16:55 AM','26-06-2018 11:16:55 AM'),
  (436,2,'26-06-2018 11:21:03 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 11:21:03 AM','26-06-2018 11:21:03 AM'),
  (437,2,'26-06-2018 11:25:20 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 11:25:20 AM','26-06-2018 11:25:20 AM'),
  (438,2,'26-06-2018 12:06:50 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 12:06:50 PM','26-06-2018 12:06:50 PM'),
  (439,2,'26-06-2018 01:19:32 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 01:19:32 PM','26-06-2018 01:19:32 PM'),
  (440,2,'26-06-2018 02:17:22 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 02:17:22 PM','26-06-2018 02:17:22 PM'),
  (441,2,'26-06-2018 02:26:03 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 02:26:03 PM','26-06-2018 02:26:03 PM'),
  (442,2,'26-06-2018 03:34:01 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 03:34:01 PM','26-06-2018 03:34:01 PM'),
  (443,2,'26-06-2018 04:15:04 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 04:15:04 PM','26-06-2018 04:15:04 PM'),
  (444,2,'26-06-2018 04:22:05 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 04:22:05 PM','26-06-2018 04:22:05 PM'),
  (445,2,'26-06-2018 04:50:54 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','26-06-2018 04:50:54 PM','26-06-2018 04:50:54 PM'),
  (446,2,'27-06-2018 10:42:30 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','27-06-2018 10:42:30 AM','27-06-2018 10:42:30 AM'),
  (447,2,'27-06-2018 11:30:53 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','27-06-2018 11:30:53 AM','27-06-2018 11:30:53 AM'),
  (448,2,'27-06-2018 11:36:14 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','27-06-2018 11:36:14 AM','27-06-2018 11:36:14 AM'),
  (449,1,'27-06-2018 11:37:11 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','27-06-2018 11:37:11 AM','27-06-2018 11:37:11 AM'),
  (450,2,'27-06-2018 11:37:29 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','27-06-2018 11:37:29 AM','27-06-2018 11:37:29 AM'),
  (451,2,'27-06-2018 11:54:32 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','27-06-2018 11:54:32 AM','27-06-2018 11:54:32 AM'),
  (452,1,'27-06-2018 02:41:44 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','27-06-2018 02:41:44 PM','27-06-2018 02:41:44 PM'),
  (453,2,'27-06-2018 02:43:50 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','27-06-2018 02:43:50 PM','27-06-2018 02:43:50 PM'),
  (454,1,'27-06-2018 03:22:42 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','27-06-2018 03:22:42 PM','27-06-2018 03:22:42 PM'),
  (455,2,'27-06-2018 03:22:46 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','27-06-2018 03:22:46 PM','27-06-2018 03:22:46 PM'),
  (456,2,'27-06-2018 03:22:59 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','27-06-2018 03:22:59 PM','27-06-2018 03:22:59 PM'),
  (457,2,'27-06-2018 03:23:00 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','27-06-2018 03:23:00 PM','27-06-2018 03:23:00 PM'),
  (458,5,'27-06-2018 03:59:02 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','27-06-2018 03:59:02 PM','27-06-2018 03:59:02 PM'),
  (459,2,'27-06-2018 03:59:26 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','27-06-2018 03:59:26 PM','27-06-2018 03:59:26 PM'),
  (460,2,'27-06-2018 05:26:21 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','27-06-2018 05:26:21 PM','27-06-2018 05:26:21 PM'),
  (461,2,'27-06-2018 07:00:11 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','27-06-2018 07:00:11 PM','27-06-2018 07:00:11 PM'),
  (462,2,'28-06-2018 08:59:05 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','28-06-2018 08:59:05 AM','28-06-2018 08:59:05 AM'),
  (463,2,'28-06-2018 09:17:09 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','28-06-2018 09:17:09 AM','28-06-2018 09:17:09 AM'),
  (464,2,'28-06-2018 09:20:53 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','28-06-2018 09:20:53 AM','28-06-2018 09:20:53 AM'),
  (465,2,'28-06-2018 09:53:54 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','28-06-2018 09:53:54 AM','28-06-2018 09:53:54 AM'),
  (466,2,'28-06-2018 10:18:04 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','28-06-2018 10:18:04 AM','28-06-2018 10:18:04 AM'),
  (467,2,'28-06-2018 10:22:39 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 10:22:39 AM','28-06-2018 10:22:39 AM'),
  (468,2,'28-06-2018 10:58:32 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','28-06-2018 10:58:32 AM','28-06-2018 10:58:32 AM'),
  (469,2,'28-06-2018 11:15:50 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 11:15:50 AM','28-06-2018 11:15:50 AM'),
  (470,2,'28-06-2018 11:39:06 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 11:39:06 AM','28-06-2018 11:39:06 AM'),
  (471,2,'28-06-2018 11:46:45 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','28-06-2018 11:46:45 AM','28-06-2018 11:46:45 AM'),
  (472,2,'28-06-2018 12:09:24 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Windows 7 )','1.186.138.227','28-06-2018 12:09:24 PM','28-06-2018 12:09:24 PM'),
  (473,2,'28-06-2018 12:18:17 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','28-06-2018 12:18:17 PM','28-06-2018 12:18:17 PM'),
  (474,2,'28-06-2018 01:03:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 01:03:20 PM','28-06-2018 01:03:20 PM'),
  (475,2,'28-06-2018 02:15:59 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Windows 7 )','1.186.138.227','28-06-2018 02:15:59 PM','28-06-2018 02:15:59 PM'),
  (476,2,'28-06-2018 02:23:38 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Windows 7 )','1.186.138.227','28-06-2018 02:23:38 PM','28-06-2018 02:23:38 PM'),
  (477,2,'28-06-2018 02:26:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 02:26:52 PM','28-06-2018 02:26:52 PM'),
  (478,2,'28-06-2018 02:43:35 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 02:43:35 PM','28-06-2018 02:43:35 PM'),
  (479,2,'28-06-2018 02:58:57 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Windows 7 )','1.186.138.227','28-06-2018 02:58:57 PM','28-06-2018 02:58:57 PM'),
  (480,2,'28-06-2018 03:04:34 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 03:04:34 PM','28-06-2018 03:04:34 PM'),
  (481,2,'28-06-2018 03:26:00 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Windows 7 )','1.186.138.227','28-06-2018 03:26:00 PM','28-06-2018 03:26:00 PM'),
  (482,2,'28-06-2018 03:42:43 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 03:42:43 PM','28-06-2018 03:42:43 PM'),
  (483,2,'28-06-2018 04:21:25 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 04:21:25 PM','28-06-2018 04:21:25 PM'),
  (484,2,'28-06-2018 04:48:21 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 04:48:21 PM','28-06-2018 04:48:21 PM'),
  (485,2,'28-06-2018 05:02:40 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','28-06-2018 05:02:40 PM','28-06-2018 05:02:40 PM'),
  (486,2,'29-06-2018 10:52:08 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','29-06-2018 10:52:08 AM','29-06-2018 10:52:08 AM'),
  (487,2,'29-06-2018 10:54:20 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','29-06-2018 10:54:20 AM','29-06-2018 10:54:20 AM'),
  (488,2,'29-06-2018 02:28:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','29-06-2018 02:28:20 PM','29-06-2018 02:28:20 PM'),
  (489,1,'29-06-2018 03:31:49 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','29-06-2018 03:31:49 PM','29-06-2018 03:31:49 PM'),
  (490,2,'29-06-2018 03:32:20 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','29-06-2018 03:32:20 PM','29-06-2018 03:32:20 PM'),
  (491,2,'29-06-2018 05:09:08 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','29-06-2018 05:09:08 PM','29-06-2018 05:09:08 PM'),
  (492,2,'29-06-2018 05:53:34 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','29-06-2018 05:53:34 PM','29-06-2018 05:53:34 PM'),
  (493,2,'29-06-2018 06:29:55 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','29-06-2018 06:29:55 PM','29-06-2018 06:29:55 PM'),
  (494,2,'29-06-2018 07:03:55 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','29-06-2018 07:03:55 PM','29-06-2018 07:03:55 PM'),
  (495,2,'29-06-2018 07:18:18 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','29-06-2018 07:18:18 PM','29-06-2018 07:18:18 PM'),
  (496,2,'29-06-2018 07:39:08 PM','20','77',',,India','Firefox 60( Mac OS X 10.13 )','103.210.201.230','29-06-2018 07:39:08 PM','29-06-2018 07:39:08 PM'),
  (497,2,'30-06-2018 08:54:13 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','30-06-2018 08:54:13 AM','30-06-2018 08:54:13 AM'),
  (498,2,'30-06-2018 09:15:29 AM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','30-06-2018 09:15:29 AM','30-06-2018 09:15:29 AM'),
  (499,2,'30-06-2018 05:35:00 PM','20','77',',,India','Firefox 60( Mac OS X 10.13 )','103.210.201.230','30-06-2018 05:35:00 PM','30-06-2018 05:35:00 PM'),
  (500,2,'30-06-2018 06:37:52 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','30-06-2018 06:37:52 PM','30-06-2018 06:37:52 PM');

COMMIT;

#
# Data for the `login_details` table  (LIMIT 500,500)
#

INSERT INTO `login_details` (`login_details_id`, `login_id`, `login_datetime`, `latitude`, `longitude`, `location`, `device_name`, `ip_address`, `created_datetime`, `update_datetime`) VALUES 
  (501,2,'30-06-2018 07:33:16 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Linux - )','1.186.138.227','30-06-2018 07:33:16 PM','30-06-2018 07:33:16 PM'),
  (502,2,'02-07-2018 10:35:13 AM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 68( Windows 7 )','1.186.138.227','02-07-2018 10:35:13 AM','02-07-2018 10:35:13 AM'),
  (503,2,'02-07-2018 01:03:58 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','02-07-2018 01:03:58 PM','02-07-2018 01:03:58 PM'),
  (504,2,'02-07-2018 02:04:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','02-07-2018 02:04:45 PM','02-07-2018 02:04:45 PM'),
  (505,2,'02-07-2018 02:11:47 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','02-07-2018 02:11:47 PM','02-07-2018 02:11:47 PM'),
  (506,2,'02-07-2018 02:18:10 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','02-07-2018 02:18:10 PM','02-07-2018 02:18:10 PM'),
  (507,2,'02-07-2018 02:42:35 PM','19.9833','73.8','Nashik,Maharashtra,India','Chrome 67( Windows 7 )','1.186.138.227','02-07-2018 02:42:35 PM','02-07-2018 02:42:35 PM'),
  (508,2,'02-07-2018 03:07:45 PM','19.9833','73.8','Nashik,Maharashtra,India','Firefox 60( Windows 7 )','1.186.138.227','02-07-2018 03:07:45 PM','02-07-2018 03:07:45 PM'),
  (509,2,'02-07-2018 03:43:45 PM','20','77',',,India','Firefox 61( Mac OS X 10.13 )','103.210.201.230','02-07-2018 03:43:45 PM','02-07-2018 03:43:45 PM'),
  (510,0,'15-10-2018 10:11:59 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 69( Windows 7 )','1.186.138.227','15-10-2018 10:11:59 AM','15-10-2018 10:11:59 AM'),
  (511,0,'15-10-2018 10:22:32 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 69( Windows 7 )','1.186.138.227','15-10-2018 10:22:32 AM','15-10-2018 10:22:32 AM'),
  (512,0,'15-10-2018 10:27:08 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 69( Windows 7 )','1.186.138.227','15-10-2018 10:27:08 AM','15-10-2018 10:27:08 AM'),
  (513,0,'15-10-2018 11:25:35 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 69( Windows 7 )','1.186.138.227','15-10-2018 11:25:35 AM','15-10-2018 11:25:35 AM'),
  (514,0,'15-10-2018 12:18:09 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 69( Windows 7 )','1.186.138.227','15-10-2018 12:18:09 PM','15-10-2018 12:18:09 PM'),
  (515,0,'17-10-2018 12:14:22 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 69( Windows 7 )','1.186.138.227','17-10-2018 12:14:22 PM','17-10-2018 12:14:22 PM'),
  (516,0,'17-10-2018 01:13:37 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 69( Windows 7 )','1.186.138.227','17-10-2018 01:13:37 PM','17-10-2018 01:13:37 PM'),
  (517,0,'19-12-2018 10:01:10 AM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.95','19-12-2018 10:01:10 AM','19-12-2018 10:01:10 AM'),
  (518,0,'19-12-2018 10:14:29 AM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.95','19-12-2018 10:14:29 AM','19-12-2018 10:14:29 AM'),
  (519,0,'19-12-2018 11:41:44 AM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.95','19-12-2018 11:41:44 AM','19-12-2018 11:41:44 AM'),
  (520,0,'19-12-2018 12:07:25 PM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.95','19-12-2018 12:07:25 PM','19-12-2018 12:07:25 PM'),
  (521,0,'19-12-2018 02:25:52 PM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.197.19','19-12-2018 02:25:52 PM','19-12-2018 02:25:52 PM'),
  (522,0,'19-12-2018 02:34:01 PM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.197.19','19-12-2018 02:34:01 PM','19-12-2018 02:34:01 PM'),
  (523,0,'19-12-2018 04:43:38 PM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.161','19-12-2018 04:43:38 PM','19-12-2018 04:43:38 PM'),
  (524,0,'19-12-2018 05:04:39 PM','19.0165','73.0413','Marathi (Sector 11),Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.161','19-12-2018 05:04:39 PM','19-12-2018 05:04:39 PM'),
  (525,0,'02-01-2019 04:03:43 PM','19.0165','73.0413','Marathi,Maharashtra,India','Firefox 64( Windows 7 )','1.186.198.126','02-01-2019 04:03:43 PM','02-01-2019 04:03:43 PM'),
  (526,0,'04-01-2019 10:55:57 AM','19.9973','73.791','Nashik,Maharashtra,India','Firefox 64( Windows 7 )','1.186.197.153','04-01-2019 10:55:57 AM','04-01-2019 10:55:57 AM'),
  (527,0,'05-01-2019 12:00:35 PM','19.9973','73.791','Nashik,Maharashtra,India','Firefox 64( Windows 7 )','1.186.199.71','05-01-2019 12:00:35 PM','05-01-2019 12:00:35 PM'),
  (528,0,'05-01-2019 12:33:03 PM','19.9973','73.791','Nashik,Maharashtra,India','Firefox 64( Windows 7 )','1.186.199.71','05-01-2019 12:33:03 PM','05-01-2019 12:33:03 PM'),
  (529,0,'05-01-2019 12:39:15 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.199.71','05-01-2019 12:39:15 PM','05-01-2019 12:39:15 PM'),
  (530,0,'05-01-2019 01:08:40 PM','19.9973','73.791','Nashik,Maharashtra,India','Firefox 64( Windows 7 )','1.186.199.71','05-01-2019 01:08:40 PM','05-01-2019 01:08:40 PM'),
  (531,0,'05-01-2019 02:10:28 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.199.71','05-01-2019 02:10:28 PM','05-01-2019 02:10:28 PM'),
  (532,0,'05-01-2019 02:13:31 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.199.71','05-01-2019 02:13:31 PM','05-01-2019 02:13:31 PM'),
  (533,0,'05-01-2019 02:17:15 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.199.71','05-01-2019 02:17:15 PM','05-01-2019 02:17:15 PM'),
  (534,0,'05-01-2019 02:35:27 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.199.71','05-01-2019 02:35:27 PM','05-01-2019 02:35:27 PM'),
  (535,0,'05-01-2019 03:37:03 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.199.71','05-01-2019 03:37:03 PM','05-01-2019 03:37:03 PM'),
  (536,0,'05-01-2019 04:30:36 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.199.71','05-01-2019 04:30:36 PM','05-01-2019 04:30:36 PM'),
  (537,0,'07-01-2019 11:01:04 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.215','07-01-2019 11:01:04 AM','07-01-2019 11:01:04 AM'),
  (538,0,'07-01-2019 11:15:21 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.215','07-01-2019 11:15:21 AM','07-01-2019 11:15:21 AM'),
  (539,0,'08-01-2019 09:07:31 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 09:07:31 AM','08-01-2019 09:07:31 AM'),
  (540,0,'08-01-2019 10:03:15 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 10:03:15 AM','08-01-2019 10:03:15 AM'),
  (541,0,'08-01-2019 10:21:43 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 10:21:43 AM','08-01-2019 10:21:43 AM'),
  (542,0,'08-01-2019 11:13:39 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 11:13:39 AM','08-01-2019 11:13:39 AM'),
  (543,0,'08-01-2019 01:09:22 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 01:09:22 PM','08-01-2019 01:09:22 PM'),
  (544,0,'08-01-2019 01:15:21 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 01:15:21 PM','08-01-2019 01:15:21 PM'),
  (545,0,'08-01-2019 01:15:56 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 01:15:56 PM','08-01-2019 01:15:56 PM'),
  (546,0,'08-01-2019 01:17:26 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 01:17:26 PM','08-01-2019 01:17:26 PM'),
  (547,0,'08-01-2019 01:20:20 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 01:20:20 PM','08-01-2019 01:20:20 PM'),
  (548,0,'08-01-2019 01:29:36 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 01:29:36 PM','08-01-2019 01:29:36 PM'),
  (549,0,'08-01-2019 03:41:02 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.196.42','08-01-2019 03:41:02 PM','08-01-2019 03:41:02 PM'),
  (550,0,'09-01-2019 10:38:42 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 10:38:42 AM','09-01-2019 10:38:42 AM'),
  (551,0,'09-01-2019 11:27:08 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 11:27:08 AM','09-01-2019 11:27:08 AM'),
  (552,0,'09-01-2019 11:36:54 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 11:36:54 AM','09-01-2019 11:36:54 AM'),
  (553,0,'09-01-2019 11:41:03 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 11:41:03 AM','09-01-2019 11:41:03 AM'),
  (554,0,'09-01-2019 11:43:07 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 11:43:07 AM','09-01-2019 11:43:07 AM'),
  (555,0,'09-01-2019 11:43:49 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 11:43:49 AM','09-01-2019 11:43:49 AM'),
  (556,0,'09-01-2019 11:46:54 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 11:46:54 AM','09-01-2019 11:46:54 AM'),
  (557,0,'09-01-2019 11:47:44 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.198.57','09-01-2019 11:47:44 AM','09-01-2019 11:47:44 AM'),
  (558,0,'10-01-2019 10:15:04 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.197.14','10-01-2019 10:15:04 AM','10-01-2019 10:15:04 AM'),
  (559,0,'10-01-2019 10:40:05 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 71( Windows 7 )','1.186.197.14','10-01-2019 10:40:05 AM','10-01-2019 10:40:05 AM'),
  (560,0,'05-03-2019 10:07:53 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.81','05-03-2019 10:07:53 AM','05-03-2019 10:07:53 AM'),
  (561,0,'05-03-2019 12:23:28 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.81','05-03-2019 12:23:28 PM','05-03-2019 12:23:28 PM'),
  (562,0,'05-03-2019 12:40:21 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.81','05-03-2019 12:40:21 PM','05-03-2019 12:40:21 PM'),
  (563,0,'05-03-2019 12:45:34 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.81','05-03-2019 12:45:34 PM','05-03-2019 12:45:34 PM'),
  (564,0,'05-03-2019 12:52:33 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.81','05-03-2019 12:52:33 PM','05-03-2019 12:52:33 PM'),
  (565,0,'07-03-2019 10:13:11 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.123','07-03-2019 10:13:11 AM','07-03-2019 10:13:11 AM'),
  (566,0,'08-03-2019 09:37:52 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 09:37:52 AM','08-03-2019 09:37:52 AM'),
  (567,0,'08-03-2019 09:48:48 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 09:48:48 AM','08-03-2019 09:48:48 AM'),
  (568,0,'08-03-2019 09:50:08 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 09:50:08 AM','08-03-2019 09:50:08 AM'),
  (569,0,'08-03-2019 09:52:42 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 09:52:42 AM','08-03-2019 09:52:42 AM'),
  (570,0,'08-03-2019 10:14:53 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:14:53 AM','08-03-2019 10:14:53 AM'),
  (571,0,'08-03-2019 10:18:40 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:18:40 AM','08-03-2019 10:18:40 AM'),
  (572,0,'08-03-2019 10:28:03 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:28:03 AM','08-03-2019 10:28:03 AM'),
  (573,0,'08-03-2019 10:31:22 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:31:22 AM','08-03-2019 10:31:22 AM'),
  (574,0,'08-03-2019 10:33:28 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:33:28 AM','08-03-2019 10:33:28 AM'),
  (575,0,'08-03-2019 10:35:59 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:35:59 AM','08-03-2019 10:35:59 AM'),
  (576,0,'08-03-2019 10:38:20 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:38:20 AM','08-03-2019 10:38:20 AM'),
  (577,0,'08-03-2019 10:40:39 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:40:39 AM','08-03-2019 10:40:39 AM'),
  (578,0,'08-03-2019 10:42:35 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:42:35 AM','08-03-2019 10:42:35 AM'),
  (579,0,'08-03-2019 10:49:44 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 10:49:44 AM','08-03-2019 10:49:44 AM'),
  (580,0,'08-03-2019 11:09:54 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:09:54 AM','08-03-2019 11:09:54 AM'),
  (581,0,'08-03-2019 11:14:19 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:14:19 AM','08-03-2019 11:14:19 AM'),
  (582,0,'08-03-2019 11:25:02 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:25:02 AM','08-03-2019 11:25:02 AM'),
  (583,0,'08-03-2019 11:26:13 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:26:13 AM','08-03-2019 11:26:13 AM'),
  (584,0,'08-03-2019 11:28:25 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:28:25 AM','08-03-2019 11:28:25 AM'),
  (585,0,'08-03-2019 11:31:03 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:31:03 AM','08-03-2019 11:31:03 AM'),
  (586,0,'08-03-2019 11:51:51 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:51:51 AM','08-03-2019 11:51:51 AM'),
  (587,0,'08-03-2019 11:57:54 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 11:57:54 AM','08-03-2019 11:57:54 AM'),
  (588,0,'08-03-2019 12:06:35 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:06:35 PM','08-03-2019 12:06:35 PM'),
  (589,0,'08-03-2019 12:07:50 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:07:50 PM','08-03-2019 12:07:50 PM'),
  (590,0,'08-03-2019 12:17:33 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:17:33 PM','08-03-2019 12:17:33 PM'),
  (591,0,'08-03-2019 12:23:40 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:23:40 PM','08-03-2019 12:23:40 PM'),
  (592,0,'08-03-2019 12:26:30 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:26:30 PM','08-03-2019 12:26:30 PM'),
  (593,0,'08-03-2019 12:29:10 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:29:10 PM','08-03-2019 12:29:10 PM'),
  (594,0,'08-03-2019 12:42:19 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:42:19 PM','08-03-2019 12:42:19 PM'),
  (595,0,'08-03-2019 12:51:53 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:51:53 PM','08-03-2019 12:51:53 PM'),
  (596,0,'08-03-2019 12:53:26 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:53:26 PM','08-03-2019 12:53:26 PM'),
  (597,0,'08-03-2019 12:56:30 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 12:56:30 PM','08-03-2019 12:56:30 PM'),
  (598,0,'08-03-2019 01:05:41 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 01:05:41 PM','08-03-2019 01:05:41 PM'),
  (599,0,'08-03-2019 01:07:18 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 01:07:18 PM','08-03-2019 01:07:18 PM'),
  (600,0,'08-03-2019 01:08:45 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 01:08:45 PM','08-03-2019 01:08:45 PM'),
  (601,0,'08-03-2019 01:10:39 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 01:10:39 PM','08-03-2019 01:10:39 PM'),
  (602,0,'08-03-2019 01:13:44 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 01:13:44 PM','08-03-2019 01:13:44 PM'),
  (603,0,'08-03-2019 03:27:25 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 03:27:25 PM','08-03-2019 03:27:25 PM'),
  (604,0,'08-03-2019 04:17:12 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 04:17:12 PM','08-03-2019 04:17:12 PM'),
  (605,0,'08-03-2019 04:53:55 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.231','08-03-2019 04:53:55 PM','08-03-2019 04:53:55 PM'),
  (606,0,'09-03-2019 09:49:03 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 09:49:03 AM','09-03-2019 09:49:03 AM'),
  (607,0,'09-03-2019 10:04:16 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 10:04:16 AM','09-03-2019 10:04:16 AM'),
  (608,0,'09-03-2019 10:07:26 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 10:07:26 AM','09-03-2019 10:07:26 AM'),
  (609,0,'09-03-2019 10:27:10 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 10:27:10 AM','09-03-2019 10:27:10 AM'),
  (610,0,'09-03-2019 10:51:13 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 10:51:13 AM','09-03-2019 10:51:13 AM'),
  (611,0,'09-03-2019 10:56:10 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 10:56:10 AM','09-03-2019 10:56:10 AM'),
  (612,0,'09-03-2019 11:12:25 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:12:25 AM','09-03-2019 11:12:25 AM'),
  (613,0,'09-03-2019 11:14:17 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:14:17 AM','09-03-2019 11:14:17 AM'),
  (614,0,'09-03-2019 11:30:13 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:30:13 AM','09-03-2019 11:30:13 AM'),
  (615,0,'09-03-2019 11:33:46 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:33:46 AM','09-03-2019 11:33:46 AM'),
  (616,0,'09-03-2019 11:35:25 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:35:25 AM','09-03-2019 11:35:25 AM'),
  (617,0,'09-03-2019 11:40:27 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:40:27 AM','09-03-2019 11:40:27 AM'),
  (618,0,'09-03-2019 11:45:44 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:45:44 AM','09-03-2019 11:45:44 AM'),
  (619,0,'09-03-2019 11:54:07 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 11:54:07 AM','09-03-2019 11:54:07 AM'),
  (620,0,'09-03-2019 12:04:44 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 12:04:44 PM','09-03-2019 12:04:44 PM'),
  (621,0,'09-03-2019 12:11:31 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 12:11:31 PM','09-03-2019 12:11:31 PM'),
  (622,0,'09-03-2019 12:15:18 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 12:15:18 PM','09-03-2019 12:15:18 PM'),
  (623,0,'09-03-2019 12:17:39 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 12:17:39 PM','09-03-2019 12:17:39 PM'),
  (624,0,'09-03-2019 12:27:06 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 12:27:06 PM','09-03-2019 12:27:06 PM'),
  (625,0,'09-03-2019 01:23:46 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 01:23:46 PM','09-03-2019 01:23:46 PM'),
  (626,0,'09-03-2019 02:37:21 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 02:37:21 PM','09-03-2019 02:37:21 PM'),
  (627,0,'09-03-2019 02:46:16 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 02:46:16 PM','09-03-2019 02:46:16 PM'),
  (628,0,'09-03-2019 02:48:57 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 02:48:57 PM','09-03-2019 02:48:57 PM'),
  (629,0,'09-03-2019 03:02:41 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 03:02:41 PM','09-03-2019 03:02:41 PM'),
  (630,0,'09-03-2019 03:22:16 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 03:22:16 PM','09-03-2019 03:22:16 PM'),
  (631,0,'09-03-2019 03:27:17 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 03:27:17 PM','09-03-2019 03:27:17 PM'),
  (632,0,'09-03-2019 03:29:18 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 03:29:18 PM','09-03-2019 03:29:18 PM'),
  (633,0,'09-03-2019 03:44:21 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 03:44:21 PM','09-03-2019 03:44:21 PM'),
  (634,0,'09-03-2019 03:56:36 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 03:56:36 PM','09-03-2019 03:56:36 PM'),
  (635,0,'09-03-2019 04:02:00 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 04:02:00 PM','09-03-2019 04:02:00 PM'),
  (636,0,'09-03-2019 04:05:55 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 04:05:55 PM','09-03-2019 04:05:55 PM'),
  (637,0,'09-03-2019 04:16:11 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 04:16:11 PM','09-03-2019 04:16:11 PM'),
  (638,0,'09-03-2019 04:27:08 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.25','09-03-2019 04:27:08 PM','09-03-2019 04:27:08 PM'),
  (639,0,'11-03-2019 09:27:05 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:27:05 AM','11-03-2019 09:27:05 AM'),
  (640,0,'11-03-2019 09:30:32 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:30:32 AM','11-03-2019 09:30:32 AM'),
  (641,0,'11-03-2019 09:31:50 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:31:50 AM','11-03-2019 09:31:50 AM'),
  (642,0,'11-03-2019 09:33:24 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:33:24 AM','11-03-2019 09:33:24 AM'),
  (643,0,'11-03-2019 09:35:16 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:35:16 AM','11-03-2019 09:35:16 AM'),
  (644,0,'11-03-2019 09:36:51 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:36:51 AM','11-03-2019 09:36:51 AM'),
  (645,0,'11-03-2019 09:38:16 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:38:16 AM','11-03-2019 09:38:16 AM'),
  (646,0,'11-03-2019 09:46:05 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:46:05 AM','11-03-2019 09:46:05 AM'),
  (647,0,'11-03-2019 09:52:07 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 09:52:07 AM','11-03-2019 09:52:07 AM'),
  (648,0,'11-03-2019 04:15:07 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 04:15:07 PM','11-03-2019 04:15:07 PM'),
  (649,0,'11-03-2019 04:16:54 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 04:16:54 PM','11-03-2019 04:16:54 PM'),
  (650,0,'11-03-2019 04:18:34 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 04:18:34 PM','11-03-2019 04:18:34 PM'),
  (651,0,'11-03-2019 04:29:23 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 04:29:23 PM','11-03-2019 04:29:23 PM'),
  (652,0,'11-03-2019 04:36:44 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 04:36:44 PM','11-03-2019 04:36:44 PM'),
  (653,0,'11-03-2019 04:48:36 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.229','11-03-2019 04:48:36 PM','11-03-2019 04:48:36 PM'),
  (654,0,'12-03-2019 12:05:50 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:05:50 PM','12-03-2019 12:05:50 PM'),
  (655,0,'12-03-2019 12:09:30 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:09:30 PM','12-03-2019 12:09:30 PM'),
  (656,0,'12-03-2019 12:13:24 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:13:24 PM','12-03-2019 12:13:24 PM'),
  (657,0,'12-03-2019 12:16:44 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:16:44 PM','12-03-2019 12:16:44 PM'),
  (658,0,'12-03-2019 12:18:22 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:18:22 PM','12-03-2019 12:18:22 PM'),
  (659,0,'12-03-2019 12:21:21 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:21:21 PM','12-03-2019 12:21:21 PM'),
  (660,0,'12-03-2019 12:23:42 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:23:42 PM','12-03-2019 12:23:42 PM'),
  (661,0,'12-03-2019 12:30:35 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:30:35 PM','12-03-2019 12:30:35 PM'),
  (662,0,'12-03-2019 12:34:13 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:34:13 PM','12-03-2019 12:34:13 PM'),
  (663,0,'12-03-2019 12:36:17 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:36:17 PM','12-03-2019 12:36:17 PM'),
  (664,0,'12-03-2019 12:37:47 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:37:47 PM','12-03-2019 12:37:47 PM'),
  (665,0,'12-03-2019 12:41:26 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:41:26 PM','12-03-2019 12:41:26 PM'),
  (666,0,'12-03-2019 12:42:33 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:42:33 PM','12-03-2019 12:42:33 PM'),
  (667,0,'12-03-2019 12:44:41 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:44:41 PM','12-03-2019 12:44:41 PM'),
  (668,0,'12-03-2019 12:47:17 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:47:17 PM','12-03-2019 12:47:17 PM'),
  (669,0,'12-03-2019 12:48:31 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:48:31 PM','12-03-2019 12:48:31 PM'),
  (670,0,'12-03-2019 12:57:53 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:57:53 PM','12-03-2019 12:57:53 PM'),
  (671,0,'12-03-2019 12:59:26 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 12:59:26 PM','12-03-2019 12:59:26 PM'),
  (672,0,'12-03-2019 01:00:52 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:00:52 PM','12-03-2019 01:00:52 PM'),
  (673,0,'12-03-2019 01:04:04 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:04:04 PM','12-03-2019 01:04:04 PM'),
  (674,0,'12-03-2019 01:11:17 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:11:17 PM','12-03-2019 01:11:17 PM'),
  (675,0,'12-03-2019 01:12:59 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:12:59 PM','12-03-2019 01:12:59 PM'),
  (676,0,'12-03-2019 01:23:39 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:23:39 PM','12-03-2019 01:23:39 PM'),
  (677,0,'12-03-2019 01:24:43 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:24:43 PM','12-03-2019 01:24:43 PM'),
  (678,0,'12-03-2019 01:26:04 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:26:04 PM','12-03-2019 01:26:04 PM'),
  (679,0,'12-03-2019 01:59:32 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 01:59:32 PM','12-03-2019 01:59:32 PM'),
  (680,0,'12-03-2019 02:02:15 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 02:02:15 PM','12-03-2019 02:02:15 PM'),
  (681,0,'12-03-2019 02:05:43 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 02:05:43 PM','12-03-2019 02:05:43 PM'),
  (682,0,'12-03-2019 02:53:01 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 02:53:01 PM','12-03-2019 02:53:01 PM'),
  (683,0,'12-03-2019 03:09:04 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 03:09:04 PM','12-03-2019 03:09:04 PM'),
  (684,0,'12-03-2019 03:15:36 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 03:15:36 PM','12-03-2019 03:15:36 PM'),
  (685,0,'12-03-2019 03:23:56 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.198.29','12-03-2019 03:23:56 PM','12-03-2019 03:23:56 PM'),
  (686,0,'15-03-2019 10:20:49 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 10:20:49 AM','15-03-2019 10:20:49 AM'),
  (687,0,'15-03-2019 11:59:53 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 11:59:53 AM','15-03-2019 11:59:53 AM'),
  (688,0,'15-03-2019 12:24:20 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:24:20 PM','15-03-2019 12:24:20 PM'),
  (689,0,'15-03-2019 12:37:47 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:37:47 PM','15-03-2019 12:37:47 PM'),
  (690,0,'15-03-2019 12:41:27 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:41:27 PM','15-03-2019 12:41:27 PM'),
  (691,0,'15-03-2019 12:43:03 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:43:03 PM','15-03-2019 12:43:03 PM'),
  (692,0,'15-03-2019 12:43:53 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:43:53 PM','15-03-2019 12:43:53 PM'),
  (693,0,'15-03-2019 12:45:50 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:45:50 PM','15-03-2019 12:45:50 PM'),
  (694,0,'15-03-2019 12:47:02 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:47:02 PM','15-03-2019 12:47:02 PM'),
  (695,0,'15-03-2019 12:52:17 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:52:17 PM','15-03-2019 12:52:17 PM'),
  (696,0,'15-03-2019 12:54:47 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:54:47 PM','15-03-2019 12:54:47 PM'),
  (697,0,'15-03-2019 12:57:20 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 12:57:20 PM','15-03-2019 12:57:20 PM'),
  (698,0,'15-03-2019 01:02:53 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 01:02:53 PM','15-03-2019 01:02:53 PM'),
  (699,0,'15-03-2019 01:07:12 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 01:07:12 PM','15-03-2019 01:07:12 PM'),
  (700,0,'15-03-2019 01:09:24 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 01:09:24 PM','15-03-2019 01:09:24 PM'),
  (701,0,'15-03-2019 01:12:06 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 01:12:06 PM','15-03-2019 01:12:06 PM'),
  (702,0,'15-03-2019 01:13:51 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.50','15-03-2019 01:13:51 PM','15-03-2019 01:13:51 PM'),
  (703,0,'16-03-2019 09:31:47 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 09:31:47 AM','16-03-2019 09:31:47 AM'),
  (704,0,'16-03-2019 09:44:34 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 09:44:34 AM','16-03-2019 09:44:34 AM'),
  (705,0,'16-03-2019 09:50:11 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 09:50:11 AM','16-03-2019 09:50:11 AM'),
  (706,0,'16-03-2019 10:17:44 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 10:17:44 AM','16-03-2019 10:17:44 AM'),
  (707,0,'16-03-2019 10:57:42 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 10:57:42 AM','16-03-2019 10:57:42 AM'),
  (708,0,'16-03-2019 11:37:27 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 11:37:27 AM','16-03-2019 11:37:27 AM'),
  (709,0,'16-03-2019 11:37:37 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 11:37:37 AM','16-03-2019 11:37:37 AM'),
  (710,0,'16-03-2019 11:50:53 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 11:50:53 AM','16-03-2019 11:50:53 AM'),
  (711,0,'16-03-2019 11:51:07 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 11:51:07 AM','16-03-2019 11:51:07 AM'),
  (712,0,'16-03-2019 01:04:22 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 01:04:22 PM','16-03-2019 01:04:22 PM'),
  (713,0,'16-03-2019 01:27:37 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 01:27:37 PM','16-03-2019 01:27:37 PM'),
  (714,0,'16-03-2019 01:27:49 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 01:27:49 PM','16-03-2019 01:27:49 PM'),
  (715,0,'16-03-2019 01:31:52 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 01:31:52 PM','16-03-2019 01:31:52 PM'),
  (716,0,'16-03-2019 01:37:20 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 01:37:20 PM','16-03-2019 01:37:20 PM'),
  (717,0,'16-03-2019 02:30:56 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 02:30:56 PM','16-03-2019 02:30:56 PM'),
  (718,0,'16-03-2019 02:34:45 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 02:34:45 PM','16-03-2019 02:34:45 PM'),
  (719,0,'16-03-2019 02:41:00 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 02:41:00 PM','16-03-2019 02:41:00 PM'),
  (720,0,'16-03-2019 02:45:49 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 02:45:49 PM','16-03-2019 02:45:49 PM'),
  (721,0,'16-03-2019 02:52:12 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 02:52:12 PM','16-03-2019 02:52:12 PM'),
  (722,0,'16-03-2019 03:02:13 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:02:13 PM','16-03-2019 03:02:13 PM'),
  (723,0,'16-03-2019 03:04:08 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:04:08 PM','16-03-2019 03:04:08 PM'),
  (724,0,'16-03-2019 03:05:36 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:05:36 PM','16-03-2019 03:05:36 PM'),
  (725,0,'16-03-2019 03:07:13 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:07:13 PM','16-03-2019 03:07:13 PM'),
  (726,0,'16-03-2019 03:10:00 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:10:00 PM','16-03-2019 03:10:00 PM'),
  (727,0,'16-03-2019 03:11:03 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:11:03 PM','16-03-2019 03:11:03 PM'),
  (728,0,'16-03-2019 03:14:11 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:14:11 PM','16-03-2019 03:14:11 PM'),
  (729,0,'16-03-2019 03:19:34 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:19:34 PM','16-03-2019 03:19:34 PM'),
  (730,0,'16-03-2019 03:26:21 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:26:21 PM','16-03-2019 03:26:21 PM'),
  (731,0,'16-03-2019 03:34:16 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:34:16 PM','16-03-2019 03:34:16 PM'),
  (732,0,'16-03-2019 03:42:00 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:42:00 PM','16-03-2019 03:42:00 PM'),
  (733,0,'16-03-2019 03:51:47 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:51:47 PM','16-03-2019 03:51:47 PM'),
  (734,0,'16-03-2019 03:53:17 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 03:53:17 PM','16-03-2019 03:53:17 PM'),
  (735,0,'16-03-2019 04:22:28 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 04:22:28 PM','16-03-2019 04:22:28 PM'),
  (736,0,'16-03-2019 04:38:36 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 04:38:36 PM','16-03-2019 04:38:36 PM'),
  (737,0,'16-03-2019 04:38:53 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 04:38:53 PM','16-03-2019 04:38:53 PM'),
  (738,0,'16-03-2019 04:56:17 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 04:56:17 PM','16-03-2019 04:56:17 PM'),
  (739,0,'16-03-2019 04:58:29 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 04:58:29 PM','16-03-2019 04:58:29 PM'),
  (740,0,'16-03-2019 05:12:31 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 05:12:31 PM','16-03-2019 05:12:31 PM'),
  (741,0,'16-03-2019 05:23:22 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 05:23:22 PM','16-03-2019 05:23:22 PM'),
  (742,0,'16-03-2019 05:27:03 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.197.39','16-03-2019 05:27:03 PM','16-03-2019 05:27:03 PM'),
  (743,0,'18-03-2019 09:18:52 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:18:52 AM','18-03-2019 09:18:52 AM'),
  (744,0,'18-03-2019 09:21:23 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:21:23 AM','18-03-2019 09:21:23 AM'),
  (745,0,'18-03-2019 09:23:42 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:23:42 AM','18-03-2019 09:23:42 AM'),
  (746,0,'18-03-2019 09:26:54 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:26:54 AM','18-03-2019 09:26:54 AM'),
  (747,0,'18-03-2019 09:28:40 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:28:40 AM','18-03-2019 09:28:40 AM'),
  (748,0,'18-03-2019 09:43:48 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:43:48 AM','18-03-2019 09:43:48 AM'),
  (749,0,'18-03-2019 09:58:48 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:58:48 AM','18-03-2019 09:58:48 AM'),
  (750,0,'18-03-2019 09:59:02 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 09:59:02 AM','18-03-2019 09:59:02 AM'),
  (751,0,'18-03-2019 10:00:30 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 10:00:30 AM','18-03-2019 10:00:30 AM'),
  (752,0,'18-03-2019 10:11:17 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 10:11:17 AM','18-03-2019 10:11:17 AM'),
  (753,0,'18-03-2019 10:13:50 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 10:13:50 AM','18-03-2019 10:13:50 AM'),
  (754,0,'18-03-2019 01:28:34 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 01:28:34 PM','18-03-2019 01:28:34 PM'),
  (755,0,'18-03-2019 02:31:03 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 02:31:03 PM','18-03-2019 02:31:03 PM'),
  (756,0,'18-03-2019 02:33:45 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 02:33:45 PM','18-03-2019 02:33:45 PM'),
  (757,0,'18-03-2019 02:38:45 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 02:38:45 PM','18-03-2019 02:38:45 PM'),
  (758,0,'18-03-2019 02:40:57 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 02:40:57 PM','18-03-2019 02:40:57 PM'),
  (759,0,'18-03-2019 02:53:11 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 02:53:11 PM','18-03-2019 02:53:11 PM'),
  (760,0,'18-03-2019 03:03:20 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 03:03:20 PM','18-03-2019 03:03:20 PM'),
  (761,0,'18-03-2019 03:26:38 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 03:26:38 PM','18-03-2019 03:26:38 PM'),
  (762,0,'18-03-2019 03:39:58 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 03:39:58 PM','18-03-2019 03:39:58 PM'),
  (763,0,'18-03-2019 03:57:32 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 03:57:32 PM','18-03-2019 03:57:32 PM'),
  (764,0,'18-03-2019 03:59:44 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 03:59:44 PM','18-03-2019 03:59:44 PM'),
  (765,0,'18-03-2019 04:22:56 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.139','18-03-2019 04:22:56 PM','18-03-2019 04:22:56 PM'),
  (766,0,'19-03-2019 10:22:44 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.209','19-03-2019 10:22:44 AM','19-03-2019 10:22:44 AM'),
  (767,0,'19-03-2019 10:34:24 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.209','19-03-2019 10:34:24 AM','19-03-2019 10:34:24 AM'),
  (768,0,'19-03-2019 10:39:06 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.209','19-03-2019 10:39:06 AM','19-03-2019 10:39:06 AM'),
  (769,0,'19-03-2019 10:42:57 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.209','19-03-2019 10:42:57 AM','19-03-2019 10:42:57 AM'),
  (770,0,'19-03-2019 10:45:27 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.209','19-03-2019 10:45:27 AM','19-03-2019 10:45:27 AM'),
  (771,0,'19-03-2019 10:48:51 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.209','19-03-2019 10:48:51 AM','19-03-2019 10:48:51 AM'),
  (772,0,'19-03-2019 10:52:15 AM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.199.209','19-03-2019 10:52:15 AM','19-03-2019 10:52:15 AM'),
  (773,0,'19-03-2019 12:35:11 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.196.55','19-03-2019 12:35:11 PM','19-03-2019 12:35:11 PM'),
  (774,0,'19-03-2019 01:13:32 PM','19.9973','73.791','Nashik,Maharashtra,India','Chrome 72( Windows 7 )','1.186.196.55','19-03-2019 01:13:32 PM','19-03-2019 01:13:32 PM');

COMMIT;

#
# Data for the `mobilenumbers` table  (LIMIT 0,500)
#

INSERT INTO `mobilenumbers` (`mobilenumber_id`, `mobile_number`, `sms_id`, `send_status`, `created_datetime`, `custom_event_id`) VALUES 
  (1,'9552981371',1,0,'05/03/2019 02:47:51 PM',0),
  (2,'9403060654',1,0,'05/03/2019 02:47:51 PM',0),
  (3,'9865324152',2,0,'05/03/2019 04:21:39 PM',0),
  (4,'7485245152',2,0,'05/03/2019 04:21:39 PM',0),
  (5,'9637321930',3,0,'05/03/2019 04:24:53 PM',0),
  (6,'9403060654',3,0,'05/03/2019 04:24:53 PM',0),
  (7,'8605421036',4,0,'05/03/2019 04:33:28 PM',0),
  (8,'9552981371',5,0,'07/03/2019 10:45:31 AM',0),
  (9,'8605421036',5,0,'07/03/2019 10:45:31 AM',0),
  (10,'8605421036',6,0,'07/03/2019 10:52:16 AM',0),
  (11,'9637321930',6,0,'07/03/2019 10:52:16 AM',0),
  (12,'9175690643',6,0,'07/03/2019 10:52:16 AM',0),
  (13,'9552981371',7,0,'09/03/2019 03:30:00 PM',0),
  (14,'8055981712',7,0,'09/03/2019 03:30:00 PM',0),
  (15,'9637321930',7,0,'09/03/2019 03:30:00 PM',0),
  (16,'9596959695',8,0,'09/03/2019 03:31:02 PM',0),
  (17,'9876145236',9,0,'09/03/2019 03:31:46 PM',0),
  (18,'9865325263',10,0,'09/03/2019 04:18:13 PM',0),
  (19,'9685748596',11,0,'09/03/2019 04:19:33 PM',0),
  (20,'9876145236',12,0,'09/03/2019 04:27:30 PM',0),
  (21,'9876145236',13,0,'11/03/2019 09:48:34 AM',0),
  (22,'9876145236',14,0,'12/03/2019 03:02:28 PM',0),
  (23,'9854785968',14,0,'12/03/2019 03:02:28 PM',0),
  (24,'9865326598',16,0,'12/03/2019 03:24:40 PM',1),
  (25,'9999999999',17,0,'12/03/2019 04:34:01 PM',1),
  (26,'8888888888',17,0,'12/03/2019 04:34:01 PM',1),
  (27,'7777777777',17,0,'12/03/2019 04:34:01 PM',1),
  (28,'9595959595',18,0,'12/03/2019 04:42:41 PM',1),
  (29,'7575757575',18,0,'12/03/2019 04:42:41 PM',1),
  (30,'8585858585',18,0,'12/03/2019 04:42:41 PM',1),
  (31,'9696969696',19,0,'15/03/2019 12:25:03 PM',4),
  (32,'9393939393',19,0,'15/03/2019 12:25:03 PM',4),
  (33,'9875398579',20,0,'15/03/2019 01:16:28 PM',5),
  (34,'8476490560',20,0,'15/03/2019 01:16:28 PM',5),
  (35,'5739570397',20,0,'15/03/2019 01:16:28 PM',5),
  (36,'8345206894',20,0,'15/03/2019 01:16:28 PM',5),
  (37,'9475934857',20,0,'15/03/2019 01:16:28 PM',5),
  (38,'6742959287',20,0,'15/03/2019 01:16:28 PM',5),
  (39,'9259263598',20,0,'15/03/2019 01:16:28 PM',5),
  (40,'6453984579',20,0,'15/03/2019 01:16:28 PM',5),
  (41,'9573947593',20,0,'15/03/2019 01:16:28 PM',5),
  (42,'9365936985',20,0,'15/03/2019 01:16:28 PM',5),
  (43,'9346375893',20,0,'15/03/2019 01:16:28 PM',5),
  (44,'4353785735',20,0,'15/03/2019 01:16:28 PM',5),
  (45,'7983957834',20,0,'15/03/2019 01:16:28 PM',5),
  (46,'8586786786',20,0,'15/03/2019 01:16:28 PM',5),
  (47,'9552981371',21,0,'15/03/2019 01:25:05 PM',6),
  (48,'9175690643',21,0,'15/03/2019 01:25:05 PM',6),
  (49,'9896587485',21,1,'15/03/2019 01:25:05 PM',6),
  (50,'9637321930',22,0,'16/03/2019 09:56:26 AM',0),
  (51,'1245635241',23,0,'16/03/2019 05:27:41 PM',0),
  (52,'9552981371',24,0,'19/03/2019 10:35:06 AM',7),
  (53,'9637321930',24,0,'19/03/2019 10:35:06 AM',7),
  (54,'8605431036',24,0,'19/03/2019 10:35:06 AM',7),
  (55,'9552981371',25,0,'19/03/2019 10:43:30 AM',8),
  (56,'9637321930',25,0,'19/03/2019 10:43:30 AM',8),
  (57,'9552981371',26,0,'19/03/2019 10:47:30 AM',9),
  (58,'9850010654',26,0,'19/03/2019 10:47:30 AM',9),
  (59,'9552981371',27,0,'19/03/2019 10:49:32 AM',10),
  (60,'9850010654',27,0,'19/03/2019 10:49:32 AM',10),
  (61,'8055981712',27,0,'19/03/2019 10:49:32 AM',10),
  (62,'9552981371',28,0,'19/03/2019 01:14:02 PM',11);

COMMIT;

#
# Data for the `other_payments` table  (LIMIT 0,500)
#

INSERT INTO `other_payments` (`other_payment_id`, `title`, `amount`, `cheque_number`, `cheque_date`, `due_date`, `paid_date`, `client_id`, `user_id`, `created_datetime`, `updated_datetime`) VALUES 
  (10,'Registration charges',15000,NULL,NULL,NULL,'02/07/2018',3,2,'02/07/2018 05:10:05 PM','02/07/2018 05:10:05 PM'),
  (11,'Stamp Duty',300000,NULL,NULL,NULL,'07/02/2018',10,2,'02/07/2018 05:18:32 PM','02/07/2018 05:18:32 PM'),
  (12,'Stamp Duty',10000,NULL,NULL,NULL,'03/07/2018',2,2,'03/07/2018 09:01:39 AM','03/07/2018 09:01:39 AM'),
  (13,'Registration Charges',40000,NULL,NULL,NULL,'19/07/2018',7,2,'19/07/2018 09:11:06 AM','19/07/2018 09:11:06 AM'),
  (14,'Registration Charges',50000,NULL,NULL,NULL,'19/07/2018',1,2,'19/07/2018 12:06:47 PM','19/07/2018 12:06:47 PM'),
  (15,'Stamp Duty',30000,NULL,NULL,NULL,'19/07/2018',1,2,'19/07/2018 12:07:02 PM','19/07/2018 12:07:02 PM'),
  (16,'Registration Charges',50000,NULL,NULL,NULL,'10/08/2018',4,2,'10/08/2018 09:21:18 AM','10/08/2018 09:21:18 AM'),
  (17,'abc',40000,NULL,NULL,NULL,'21/08/2018',15,2,'21/08/2018 03:42:42 PM','21/08/2018 03:42:42 PM'),
  (18,'Legal Charges',50000,NULL,NULL,NULL,'20/08/2018',4,2,'21/08/2018 04:05:51 PM','22/08/2018 09:07:25 AM'),
  (19,'Stamp Duty',20000,NULL,NULL,NULL,'21/08/2018',4,2,'21/08/2018 04:06:57 PM','22/08/2018 09:06:47 AM'),
  (20,'Registration Charges',40000,NULL,NULL,NULL,'21/08/2018',8,2,'22/08/2018 09:11:01 AM','22/08/2018 09:11:08 AM'),
  (21,'Booking Amount',50000,NULL,NULL,NULL,'31/08/2018',19,2,'31/08/2018 09:28:55 AM','31/08/2018 09:28:55 AM'),
  (22,'Reg. Charges',40000,'','','01/09/2018','',19,2,'31/08/2018 01:19:44 PM','31/08/2018 04:52:25 PM'),
  (23,'Stamp Duty',70000,NULL,NULL,'05/09/2018',NULL,19,2,'31/08/2018 04:55:28 PM','31/08/2018 04:55:28 PM'),
  (25,'Booking Amount',80000,NULL,NULL,NULL,'01/09/2018',22,2,'01/09/2018 12:22:38 PM','01/09/2018 12:22:38 PM'),
  (26,'Registration amount',50000,NULL,NULL,'03/09/2018',NULL,5,2,'03/09/2018 11:49:16 AM','03/09/2018 11:49:16 AM'),
  (27,'Other Charges',40000,NULL,NULL,'05/09/2018',NULL,5,2,'03/09/2018 11:52:36 AM','03/09/2018 11:52:36 AM'),
  (28,'Registration Amount',20000,'','','06/09/2018','',4,2,'03/09/2018 04:53:06 PM','04/09/2018 02:55:45 PM'),
  (29,'Booking Amount',60000,NULL,NULL,NULL,'04/09/2018',23,2,'04/09/2018 04:24:08 PM','04/09/2018 04:24:08 PM'),
  (30,'Booking Amount',50000,'645647567','08/09/2018',NULL,'08/09/2018',24,2,'08/09/2018 03:20:33 PM','08/09/2018 03:20:33 PM'),
  (31,'Booking Amount',70000,'54342112','10/09/2018',NULL,'10/09/2018',25,2,'10/09/2018 04:22:58 PM','10/09/2018 04:22:58 PM'),
  (32,'Booking Amount',60000,'768979','10/09/2018',NULL,'10/09/2018',26,2,'10/09/2018 04:30:51 PM','10/09/2018 04:30:51 PM'),
  (33,'abcd',20000,NULL,NULL,'21/09/2018',NULL,3,2,'14/09/2018 11:11:44 AM','14/09/2018 11:11:44 AM'),
  (34,'kjhk',20000,NULL,NULL,'21/09/2018',NULL,3,2,'14/09/2018 11:11:58 AM','14/09/2018 11:11:58 AM'),
  (35,'mm',20000,NULL,NULL,'14/09/2018',NULL,3,2,'14/09/2018 11:13:16 AM','14/09/2018 11:13:16 AM'),
  (36,'hh',20000,NULL,NULL,'20/09/2018',NULL,3,2,'14/09/2018 11:28:34 AM','14/09/2018 11:28:34 AM');

COMMIT;

#
# Data for the `payment_followup` table  (LIMIT 0,500)
#

INSERT INTO `payment_followup` (`payment_followup_id`, `payment_followup_descr`, `disbursement_id`, `is_remove`, `created_datetime`, `updated_datetime`) VALUES 
  (1,'Demand Letter send : 5.0% (Booking Amount) project Completion  having amount Rs.  1,68,000.00',1,0,'30/07/2018 10:34:24 AM','30/07/2018 10:34:24 AM'),
  (2,'Demand Letter send : 10.0% (Within 30 days of the booking date) project Completion  having amount Rs.  3,36,000.00',2,0,'30/07/2018 10:36:40 AM','30/07/2018 10:36:40 AM'),
  (3,'Demand Letter send : 10.0% (Within 30 days of the booking date) project Completion  having amount Rs.  3,19,200.00',18,0,'30/07/2018 11:00:18 AM','30/07/2018 11:00:18 AM'),
  (4,'Demand Letter send : 12.0% (Disbursement-1) project Completion  having amount Rs.  4,23,360.00',33,0,'30/07/2018 12:21:23 PM','30/07/2018 12:21:23 PM'),
  (5,'Demand Letter send : 35.0% (Disbursement-2) project Completion  having amount Rs.  10,34,800.00',34,0,'30/07/2018 01:04:17 PM','30/07/2018 01:04:17 PM'),
  (6,'Demand Letter send : 35.0% (Disbursement-2) project Completion  having amount Rs.  12,85,375.00',40,0,'30/07/2018 01:05:25 PM','30/07/2018 01:05:25 PM'),
  (7,'Demand Letter send : 15.0% (Within 45 days of the booking date) project Completion  having amount Rs.  1,68,000.00',3,0,'30/07/2018 02:10:44 PM','30/07/2018 02:10:44 PM'),
  (8,'Demand Letter send : 15.0% (Within 45 days of the booking date) project Completion  having amount Rs.  2,48,800.00',19,0,'30/07/2018 02:13:53 PM','30/07/2018 02:13:53 PM'),
  (9,'Demand Letter send : 35.0% (Disbursement-2) project Completion  having amount Rs.  12,76,800.00',46,0,'30/07/2018 04:42:29 PM','30/07/2018 04:42:29 PM'),
  (10,'Demand Letter send : 9.9% (Booking) project Completion  having amount Rs.  3,24,522.00',51,0,'31/07/2018 09:06:47 AM','31/07/2018 09:06:47 AM'),
  (11,'Demand Letter send : 20.0% (Post Agreement) project Completion  having amount Rs.  4,55,600.00',52,0,'31/07/2018 09:10:20 AM','31/07/2018 09:10:20 AM'),
  (12,'Demand Letter send : 15.0% (Within 45 days of the booking date) Project Completion having amount Rs. 5,55,900.00',63,0,'01/08/2018 04:01:54 PM','01/08/2018 04:01:54 PM'),
  (13,'Demand Letter send : 5.0% (Booking Amount) Project Completion having amount Rs. 2,19,450.00',77,0,'01/08/2018 04:28:57 PM','01/08/2018 04:28:57 PM'),
  (14,'Demand Letter send : 5.0% (Booking Amount) Project Completion having amount Rs. 2,22,600.00',90,0,'01/08/2018 04:29:16 PM','01/08/2018 04:29:16 PM'),
  (15,'Demand Letter send : 20.0% (Plinth Level) Project Completion having amount Rs. 6,99,960.00',109,0,'01/08/2018 04:34:29 PM','01/08/2018 04:34:29 PM'),
  (16,'Demand Letter send : 20.0% (Plinth Level) Project Completion having amount Rs. 7,41,280.00',103,0,'01/08/2018 04:35:56 PM','01/08/2018 04:35:56 PM'),
  (17,'Demand Letter send : 15.0% (Within 45 days of the booking date) Project Completion having amount Rs. 4,91,700.00',117,0,'02/08/2018 10:18:49 AM','02/08/2018 10:18:49 AM'),
  (18,'Demand Letter send : 30.0% (Foundation) Project Completion having amount Rs. 7,83,400.00',53,0,'13/08/2018 04:51:07 PM','13/08/2018 04:51:07 PM'),
  (19,'Demand Letter send : 5.0% (Booking Amount) Project Completion having amount Rs. 1,86,875.00',131,0,'13/08/2018 04:59:16 PM','13/08/2018 04:59:16 PM'),
  (20,'Demand Letter send : 10.0% (Within 30 days of the booking date) Project Completion having amount Rs. 4,45,200.00',91,0,'13/08/2018 05:03:09 PM','13/08/2018 05:03:09 PM'),
  (21,'paid within next 2 days',3,0,'16/08/2018 04:40:46 PM','16/08/2018 04:40:46 PM'),
  (22,'aaaa bbbbb',117,0,'17/08/2018 11:23:57 AM','17/08/2018 11:23:57 AM'),
  (23,'paid within 10 days',103,0,'18/08/2018 12:35:07 PM','18/08/2018 12:35:07 PM'),
  (24,'paid within 10 days',53,0,'22/08/2018 09:09:35 AM','22/08/2018 09:09:35 AM'),
  (25,'Demand Letter send : 40.0% (First floor) Project Completion having amount Rs. 11,62,560.00',104,0,'29/08/2018 04:07:36 PM','29/08/2018 04:07:36 PM'),
  (26,'Demand Letter send : 10.0% (Within 30 days of the booking date) Project Completion having amount Rs. 2,69,450.00',78,0,'01/09/2018 03:25:45 PM','01/09/2018 03:25:45 PM'),
  (27,'Demand Letter send : 23.0% (Plinth Level) Project Completion having amount Rs. 10,48,800.00',144,0,'02/09/2018 01:26:00 PM','02/09/2018 01:26:00 PM'),
  (28,'Demand Letter send : 100.0% (Possession) Project Completion having amount Rs. 35,11,200.00',148,0,'02/09/2018 01:30:08 PM','02/09/2018 01:30:08 PM'),
  (29,'paid within 10 days',46,0,'02/09/2018 02:35:40 PM','02/09/2018 02:35:40 PM'),
  (30,'Demand Letter send : 35.0% (Disbursement-2) Project Completion having amount Rs. 10,20,110.00',150,0,'10/09/2018 12:37:00 PM','10/09/2018 12:37:00 PM'),
  (31,'Demand Letter send : 10.0% (Within 30 days of the booking date) Project Completion having amount Rs. 3,03,750.00',132,0,'14/09/2018 01:19:16 PM','14/09/2018 01:19:16 PM'),
  (32,'paid within next 2 days',46,0,'15/09/2018 09:42:33 AM','15/09/2018 09:42:33 AM'),
  (33,'aaaa bbbbb',46,0,'15/09/2018 09:42:43 AM','15/09/2018 09:42:43 AM'),
  (34,'paid within next 8 days',78,0,'15/09/2018 10:39:08 AM','15/09/2018 10:39:08 AM');

COMMIT;

#
# Data for the `project_details` table  (LIMIT 0,500)
#

INSERT INTO `project_details` (`project_id`, `project_name`, `address`, `start_date`, `expected_completion_date`, `no_of_wings`, `maharera_no`, `project_tran_id`, `user_id`, `subuser_id`, `project_status`, `company_id`, `is_approved`, `is_remove`, `created_datetime`, `updated_datetime`, `letter_head`) VALUES 
  (1,'Parkside Residency','Aurangabad Road,Nashik','31-05-2018','05-07-2018',3,'786876',1,2,'5',1,1,1,0,'26/06/2018 10:32:04 AM','16/03/2019 11:06:05 AM',NULL),
  (2,'Dream Castle','Makhmalabad Rd, Hanumanwadi, Janata Raja Colony, Nashik, Maharashtra 422003','01/12/2017','31/12/2018',3,'AVC123',2,2,'5',2,1,1,0,'26/06/2018 10:34:02 AM','15/10/2018 09:24:39 AM','1535966577318_letterhead2.pdf'),
  (3,'Anmol Heights','Panchavati,Nashik','03-05-2018','06-06-2018',3,'8098908',3,2,NULL,1,1,0,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',NULL),
  (4,'Divakar Heights','Nashik road,Nashik','07/06/2018','29/06/2018',3,'234324',4,2,NULL,1,3,1,0,'27/06/2018 11:42:53 AM','13/10/2018 03:28:16 PM',''),
  (5,'Amrut Villa Apartment','Swami Vivekanand Nagar, Shri Ram Colony, Nashik, Maharashtra 422010','01/04/2018','31/01/2019',2,'ABC123',5,2,'5',2,1,1,0,'29/06/2018 03:14:39 PM','12/10/2018 04:36:17 PM','1535515234920_letterhead2.pdf'),
  (6,'Western Hills','Kate Wasti ,Punawale','02/07/2018','06/12/2022',2,'PUP-098E-0984',6,2,NULL,2,3,1,0,'30/06/2018 05:53:14 PM','30/06/2018 06:43:01 PM',NULL),
  (7,'TwinTree','Shivaji Nagar,Pune','07/02/2018','12/06/2022',3,'PUP-098E-0984',7,2,NULL,2,3,1,0,'02/07/2018 05:05:07 PM','13/10/2018 03:28:46 PM','1535515223998_letterhead1.pdf'),
  (8,'Creston Plaza','L-6, Pundalik Nagar, Alto Porvorim , North Goa 1','01/05/2018','22/05/2019',2,'KHS22',8,2,NULL,3,1,1,0,'07/07/2018 12:09:07 PM','04/01/2019 12:39:30 PM',''),
  (9,'Galaxy Apartment','Opp. Motiwala College, Druvnagar, Gangapur-Satpur Link Rd, Shivaji Nagar, Satpur Colony, Nashik','05/07/2018','10/12/2019',4,'SDF121',9,2,'5,6',2,3,1,0,'12/07/2018 02:26:12 PM','13/10/2018 03:16:21 PM','1534933546793_18 Latitude PDF.pdf'),
  (10,'Diamond Villa','College Road','01/07/2018','30/05/2019',2,'ABC123',10,2,'5',2,1,0,0,'23/07/2018 09:21:28 AM','12/10/2018 04:37:00 PM',''),
  (11,'Shakambari Residency','Pathardi Rd, Siemens Colony, Rane Nagar, Nashik, Maharashtra 422009','01/05/2018','30/04/2019',3,'KBC444',11,2,NULL,1,1,0,0,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',NULL),
  (12,'Suyash Residency','Shanti Nagar, Makhmalabad Road, Panchavati, Nashik-422003','01/01/2018','30/12/2018',2,'BVN148',12,2,NULL,1,1,0,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',NULL),
  (13,'abc','aaa','15/08/2018','30/08/2018',1,'111',13,2,NULL,2,1,0,0,'18/08/2018 05:04:33 PM','08/01/2019 01:20:50 PM',NULL),
  (14,'Dhandai Apartment','Dhandai Apartment, Pelican Park Rd, Swami Vivekanand Nagar, Shri Ram Colony, Nashik, Maharashtra 422010, India','01/08/2018','15/12/2019',2,'AMCF435',14,2,NULL,1,1,0,0,'22/08/2018 03:55:46 PM','22/08/2018 04:15:39 PM','1534933546793_18 Latitude PDF.pdf'),
  (15,'Lambodar Park','Lokmanya Nagar, Pawan Nagar, Nashik, Maharashtra 422008','01/06/2018','30/01/2020',1,'AA1233',15,2,NULL,1,1,0,0,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM',NULL),
  (16,'abc','aaa','13/08/2018','04/09/2018',1,'333',16,2,NULL,1,1,0,0,'29/08/2018 09:30:34 AM','29/08/2018 09:30:34 AM','1535515234920_letterhead2.pdf'),
  (17,'pqr','abc','29/08/2018','12/09/2018',2,'111',17,2,NULL,2,1,0,0,'07/09/2018 12:56:38 PM','07/09/2018 01:16:14 PM',''),
  (18,'Suyash Residency','adffd','07/09/2018','28/09/2018',2,'657',18,2,NULL,1,1,1,0,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM',NULL),
  (19,'fhhk','khhlw','06/09/2018','04/10/2018',2,'654',19,2,NULL,1,1,0,0,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',NULL),
  (20,'Dhaval Residency-B','ryrty','31/08/2018','28/09/2018',2,'675',20,2,NULL,2,1,1,0,'07/09/2018 03:12:54 PM','12/10/2018 04:36:51 PM','');

COMMIT;

#
# Data for the `project_disbursement` table  (LIMIT 0,500)
#

INSERT INTO `project_disbursement` (`project_disbursement_id`, `project_id`, `disbursement_title`, `disbursement_description`, `disbursement_percentage`, `completion_date`, `user_id`, `created_datetime`, `updated_datetime`) VALUES 
  (1,1,'Disbursement 1','Disbursement 1',22,NULL,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM'),
  (2,1,'Disbursement 2','Disbursement 2',24,NULL,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM'),
  (3,1,'Disbursement 3','Disbursement 3',18,NULL,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM'),
  (4,1,'Disbursement 4','Disbursement 4',15,NULL,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM'),
  (5,1,'Disbursement 5','Disbursement 5',21,NULL,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM'),
  (6,2,'Disbursement-1','d1',12,'18/07/2018',2,'26/06/2018 10:34:02 AM','18/07/2018 03:08:44 PM'),
  (7,2,'Disbursement-2','d2',23,'30/07/2018',2,'26/06/2018 10:34:02 AM','30/07/2018 12:23:12 PM'),
  (8,2,'Disbursement-3','d3',15,NULL,2,'26/06/2018 10:34:02 AM','27/07/2018 03:39:29 PM'),
  (9,2,'Disbursement-4','d4',18,NULL,2,'26/06/2018 10:34:02 AM','27/07/2018 03:42:30 PM'),
  (10,2,'Disbursement-5','d5',22,NULL,2,'26/06/2018 10:34:02 AM','07/07/2018 04:53:16 PM'),
  (11,2,'Disbursement-6','d6',10,NULL,2,'26/06/2018 10:34:02 AM','07/07/2018 04:54:15 PM'),
  (21,5,'Booking Amount','Booking Amount',5,'18/07/2018',2,'29/06/2018 03:14:39 PM','18/07/2018 03:32:00 PM'),
  (22,5,'Within 30 days of the booking date','Within 30 days of the booking date',5,'30/07/2018',2,'29/06/2018 03:14:39 PM','30/07/2018 10:34:55 AM'),
  (23,5,'Within 45 days of the booking date','Within 45 days of the booking date',5,'30/07/2018',2,'29/06/2018 03:14:39 PM','30/07/2018 01:29:54 PM'),
  (24,5,'Within 60 days of the booking date','Within 60 days of the booking date',4.99,NULL,2,'29/06/2018 03:14:39 PM','27/07/2018 03:24:51 PM'),
  (25,5,'On Casting of Plinth Slab','On Casting of Plinth Slab',10.01,NULL,2,'29/06/2018 03:14:39 PM','03/07/2018 11:37:57 AM'),
  (26,5,'On Casting of 1st Slab','On Casting of 1st Slab',7.5,NULL,2,'29/06/2018 03:14:39 PM','09/07/2018 09:36:00 AM'),
  (27,5,'On Casting of 2nd Slab','On Casting of 2nd Slab',7.5,NULL,2,'29/06/2018 03:14:39 PM','13/07/2018 10:35:31 AM'),
  (28,5,'On Casting of 3rd Slab','On Casting of 3rd Slab',7.5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (29,5,'On Casting of 4thSlab','On Casting of 4thSlab',7.5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (30,5,'On Casting of 5thSlab','On Casting of 5thSlab',7.5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (31,5,'On Casting of 6th Slab','On Casting of 6th Slab',7.5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (32,5,'On Casting of Roof Slab','On Casting of Roof Slab',5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (33,5,'On completion of brickwork','On completion of brickwork',5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (34,5,'On completion of Internal Plaster','On completion of Internal Plaster',5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (35,5,'On completion of Flooring','On completion of Flooring',5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (36,5,'On possession','On possession',5,NULL,0,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM'),
  (37,6,'Plinth Level','Plinth level work completion',20,'27/07/2018',2,'30/06/2018 05:53:14 PM','27/07/2018 09:37:53 AM'),
  (38,6,'First floor','First floor completion',20,'29/08/2018',2,'30/06/2018 05:53:14 PM','29/08/2018 09:37:49 AM'),
  (39,6,'Third floor','Third floor completion',20,NULL,2,'30/06/2018 05:53:14 PM','02/07/2018 04:44:10 PM'),
  (40,6,'Forth floor','Forth floor completion',20,NULL,0,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM'),
  (41,6,'Flooring','Flooring completion',10,NULL,0,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM'),
  (42,6,'Possesion','Possesion time',10,NULL,0,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM'),
  (43,7,'Plint Level','Plinth Level completion',20,'03/08/2018',2,'02/07/2018 05:05:07 PM','03/08/2018 03:51:52 PM'),
  (44,7,'First Floor','First floor completion',20,'10/08/2018',2,'02/07/2018 05:05:07 PM','10/08/2018 03:13:57 PM'),
  (45,7,'Third floor','Third floor completion',20,'22/08/2018',2,'02/07/2018 05:05:07 PM','22/08/2018 09:03:46 AM'),
  (46,7,'Flooring','Flooring completion',20,'29/08/2018',2,'02/07/2018 05:05:07 PM','29/08/2018 09:37:15 AM'),
  (47,7,'Possesion','Possesion',20,NULL,0,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM'),
  (48,8,'Booking','',9.9,'18/07/2018',2,'07/07/2018 12:09:07 PM','18/07/2018 03:52:30 PM'),
  (49,8,'Post Agreement',NULL,10.1,'31/07/2018',2,'07/07/2018 12:09:07 PM','31/07/2018 09:09:43 AM'),
  (50,8,'Foundation',NULL,10,'03/08/2018',2,'07/07/2018 12:09:07 PM','03/08/2018 03:52:37 PM'),
  (51,8,'Plinth',NULL,8.57,'23/08/2018',2,'07/07/2018 12:09:07 PM','23/08/2018 02:36:09 PM'),
  (52,8,'1st slab',NULL,7.41,NULL,0,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM'),
  (53,8,'2rd slab',NULL,8.5,NULL,0,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM'),
  (54,8,'3rd slab',NULL,12.63,NULL,0,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM'),
  (55,8,'Bric Work','',10.76,NULL,0,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM'),
  (56,8,'Flooring',NULL,11.89,NULL,0,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM'),
  (57,8,'Possession',NULL,10.24,NULL,0,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM'),
  (58,3,'Disbursement phase 1','Disbursement phase 1',15,NULL,2,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM'),
  (59,3,'Disbursement phase 2','Disbursement phase 2',18,NULL,2,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM'),
  (60,3,'Disbursement phase 3','Disbursement phase 3',14,NULL,2,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM'),
  (61,3,'Disbursement phase 4','Disbursement phase 4',16,NULL,2,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM'),
  (62,3,'Disbursement phase 5','Disbursement phase 5',20,NULL,2,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM'),
  (63,3,'Disbursement phase 6','Disbursement phase 6',17,NULL,2,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM'),
  (64,4,'d1','d1',30,NULL,2,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM'),
  (65,4,'d2','d2',30,NULL,2,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM'),
  (66,4,'d3','d3',40,NULL,2,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM'),
  (80,9,'Booking Amount',NULL,5,'18/07/2018',2,'12/07/2018 02:26:12 PM','18/07/2018 03:52:21 PM'),
  (81,9,'Within 30 days of the booking date',NULL,5,'13/08/2018',2,'12/07/2018 02:26:12 PM','13/08/2018 05:01:42 PM'),
  (82,9,'Within 45 days of the booking date',NULL,7,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (83,9,'Within 60 days of the booking date',NULL,5.97,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (84,9,'On Casting of Plinth Slab',NULL,6.83,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (85,9,'On Casting of 1st Slab',NULL,9.46,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (86,9,'On Casting of 2nd Slab',NULL,10.58,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (87,9,'On Casting of 3rd Slab',NULL,12.45,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (88,9,'On Casting of Roof Slab',NULL,11.27,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (89,9,'On completion of brickwork',NULL,8.68,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (90,9,'On completion of Internal Plaster',NULL,6.47,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (91,9,'On completion of Flooring',NULL,5.25,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (92,9,'On possession',NULL,6.04,NULL,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM'),
  (93,10,'Plinth Level','',23,'02/09/2018',2,'23/07/2018 09:21:28 AM','02/09/2018 01:25:37 PM'),
  (94,10,'First floor',NULL,20,'02/09/2018',2,'23/07/2018 09:21:28 AM','02/09/2018 01:27:11 PM'),
  (95,10,'Third Floor',NULL,17,'02/09/2018',2,'23/07/2018 09:21:28 AM','02/09/2018 01:27:36 PM'),
  (96,10,'Flooring',NULL,25,'02/09/2018',2,'23/07/2018 09:21:28 AM','02/09/2018 01:29:41 PM'),
  (97,10,'Possession',NULL,15,'02/09/2018',2,'23/07/2018 09:21:28 AM','02/09/2018 01:29:50 PM'),
  (98,11,'Booking Amount',NULL,20,NULL,0,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM'),
  (99,11,'Registration Charges','',12,NULL,0,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM'),
  (100,11,'Plinth level work completion',NULL,19.88,NULL,0,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM'),
  (101,11,'First Slab Completion',NULL,22.48,NULL,0,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM'),
  (102,11,'Flooring completion',NULL,17.29,NULL,0,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM'),
  (103,11,'On Possession',NULL,8.35,NULL,0,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM'),
  (104,12,'Booking','',10.26,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (105,12,'Post Agreement',NULL,4.58,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (106,12,'Foundation',NULL,7.85,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (107,12,'Plinth',NULL,11.29,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (108,12,'Slab Completion',NULL,18.95,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (109,12,'Bric Work',NULL,17.28,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (110,12,'Flooring',NULL,21.45,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (111,12,'Possession',NULL,8.34,NULL,0,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM'),
  (112,13,'kk',NULL,8.34,NULL,0,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM'),
  (113,13,'ss',NULL,91.66,NULL,0,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM'),
  (114,14,'Possession','',30,NULL,0,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM'),
  (115,14,'Booking Amount',NULL,12.75,NULL,0,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM'),
  (116,14,'Within 60 days',NULL,32.14,NULL,0,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM'),
  (117,14,'On Casting Of Plinth cab',NULL,8.34,NULL,0,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM'),
  (118,14,'On Completion of Internal Plaster',NULL,16.77,NULL,0,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM'),
  (119,15,'Possession',NULL,50,NULL,0,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM'),
  (120,15,'On Completion of Internal Plaster',NULL,50,NULL,0,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM'),
  (121,16,'On Completion of Internal Plaster',NULL,100,NULL,0,'29/08/2018 09:30:34 AM','29/08/2018 09:30:34 AM'),
  (122,17,'Possession',NULL,100,NULL,0,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM'),
  (123,18,'On Completion of Internal Plaster',NULL,100,NULL,0,'07/09/2018 02:40:24 PM','07/09/2018 02:40:24 PM'),
  (124,19,'On Completion of Internal Plaster',NULL,100,NULL,0,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM'),
  (125,20,'On Completion of Internal Plaster',NULL,100,NULL,0,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM');

COMMIT;

#
# Data for the `property` table  (LIMIT 0,500)
#

INSERT INTO `property` (`property_id`, `property_name`, `property_type_id`, `created_datetime`, `updated_datetime`, `is_disabled`) VALUES 
  (1,'1 BHK',1,'20/02/2018 11:32:27 PM','20/02/2018 11:32:47 PM',0),
  (2,'2 BHK',1,'20/02/2018 11:32:40 PM','20/02/2018 11:32:40 PM',0),
  (3,'3 BHK',1,'20/02/2018 11:32:27 PM','20/02/2018 11:32:27 PM',0),
  (4,'Offices',2,'20/02/2018 11:32:27 PM','20/02/2018 11:32:27 PM',0),
  (5,'Shops',2,'20/02/2018 11:32:27 PM','20/02/2018 11:32:27 PM',0);

COMMIT;

#
# Data for the `property_type` table  (LIMIT 0,500)
#

INSERT INTO `property_type` (`property_type_id`, `property_type_descr`) VALUES 
  (1,'Residential'),
  (2,'Commercial');

COMMIT;

#
# Data for the `reference` table  (LIMIT 0,500)
#

INSERT INTO `reference` (`reference_id`, `reference_type`) VALUES 
  (1,'Newspaper'),
  (2,'Website'),
  (3,'Friends'),
  (4,'Other');

COMMIT;

#
# Data for the `settings` table  (LIMIT 0,500)
#

INSERT INTO `settings` (`settings_id`, `sender_email`, `followup_notification_duration`, `payment_notification_duration`, `payment_duedate_duration`, `company_id`, `created_datetime`, `updated_datetime`) VALUES 
  (1,'no-replay@ysmsoftware.com',5,3,7,1,'09/07/2018 03:02:20 PM','04/09/2018 09:57:26 AM'),
  (2,'noreply@ysmsoftware.com',8,6,8,2,'04/08/2018 09:11:25 AM','04/08/2018 09:11:25 AM'),
  (3,'noreply@ysmsoftware.com',8,6,8,3,'04/08/2018 09:12:53 AM','04/08/2018 09:12:53 AM');

COMMIT;

#
# Data for the `sms` table  (LIMIT 0,500)
#

INSERT INTO `sms` (`sms_id`, `sms_text`, `sms_send_time`, `company_id`, `created_datetime`, `updated_datetime`) VALUES 
  (1,'Susan from Private Real Estate will show you the property on Thursday at 2pm. Please reply to this number if you need to change the time.','05/03/2019 02:47:51 PM',1,'',''),
  (2,'Hi...This is Anushka of Real Estate Group. Thanks for signing up for a home valuation. I''m putting it together now. ','05/03/2019 04:21:39 PM',1,'',''),
  (3,'Hope you''re doing well John! You contacted me recently about a home. There are a few available foreclosures in your market available ','05/03/2019 04:24:53 PM',1,'',''),
  (4,'Thanks for signing up for a home valuation. I''m putting it together now. I''ll actually be in and around your neighbourhood on Saturday.','05/03/2019 04:33:28 PM',1,'',''),
  (5,' Are you still interested in the 44 Main St. property? Feel free to replay to this text with any question you may have.','07/03/2019 10:45:31 AM',1,'',''),
  (6,'Hi...,it''s Andrew here! Are you still interested in searching for home in the Weak Forest Area?','07/03/2019 10:52:16 AM',1,'',''),
  (7,'Hiiiiiiiiiiiiiiiiiiiiiiiiiiiiii','09/03/2019 03:30:00 PM',1,'',''),
  (8,'aaaaaaaaaa','09/03/2019 03:31:02 PM',1,'',''),
  (9,'bmbmbm','09/03/2019 03:31:46 PM',1,'',''),
  (10,'kkkkkkkkkkkkk','09/03/2019 04:18:13 PM',1,'',''),
  (11,'hfghfhf','09/03/2019 04:19:33 PM',1,'',''),
  (12,'mhgmgmhnbnvbn','09/03/2019 04:27:30 PM',1,'',''),
  (13,'22','11/03/2019 09:48:34 AM',1,'',''),
  (14,'abc','12/03/2019 03:02:28 PM',1,'',''),
  (16,'ghfjghjg',NULL,1,'',''),
  (17,'asdf hfgh fghgfh fhfghf fgh fgh  fhfgh fhfgh',NULL,1,'',''),
  (18,'iuiuouoyiouyouyiouyiouyioiuyo',NULL,1,'',''),
  (19,'assdfadadadasd',NULL,1,'15/03/2019 12:25:03 PM','15/03/2019 12:25:03 PM'),
  (20,'Happy Dussehra.....',NULL,1,'15/03/2019 01:16:28 PM','15/03/2019 01:16:28 PM'),
  (21,'Happy Holi in advance.....','18/03/2019 03:35:01 PM',1,'15/03/2019 01:25:05 PM','18/03/2019 03:35:01 PM'),
  (22,'abcd abcd abcd abcd','16/03/2019 09:56:26 AM',1,'16/03/2019 09:56:26 AM','16/03/2019 09:56:26 AM'),
  (23,'mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm','16/03/2019 05:27:41 PM',1,'16/03/2019 05:27:41 PM','16/03/2019 05:27:41 PM'),
  (24,'dfdfgdfg',NULL,0,'19/03/2019 10:35:06 AM','19/03/2019 10:35:06 AM'),
  (25,'gfdgfdgdg',NULL,1,'19/03/2019 10:43:30 AM','19/03/2019 10:43:30 AM'),
  (26,'mnmnmnmn',NULL,1,'19/03/2019 10:47:30 AM','19/03/2019 10:47:30 AM'),
  (27,'aaaa bbbbb cccc ddddd',NULL,1,'19/03/2019 10:49:32 AM','19/03/2019 10:49:32 AM'),
  (28,'hiiii',NULL,1,'19/03/2019 01:14:02 PM','19/03/2019 01:14:02 PM');

COMMIT;

#
# Data for the `sms_credit_details` table  (LIMIT 0,500)
#

INSERT INTO `sms_credit_details` (`sms_credit_id`, `total_credits`, `total_sent`, `available_credits`, `company_id`, `insertion_date`, `updated_datetime`) VALUES 
  (1,15,0,15,1,'18/03/2019','18/03/2019 09:59:37 AM'),
  (2,5,11,9,1,'18/03/2019','19/03/2019 01:14:02 PM');

COMMIT;

#
# Data for the `temp` table  (LIMIT 0,500)
#

INSERT INTO `temp` (`id`, `creation_date`) VALUES 
  (2,'26/06/2018 10:34:02 AM'),
  (8,'07/07/2018 12:09:07 PM'),
  (7,'02/07/2018 05:05:07 PM'),
  (2,'26/06/2018 10:34:02 AM'),
  (8,'07/07/2018 12:09:07 PM'),
  (8,'07/07/2018 12:09:07 PM');

COMMIT;

#
# Data for the `user` table  (LIMIT 0,500)
#

INSERT INTO `user` (`user_id`, `company_id`, `user_name`, `user_email`, `user_mobile`, `user_password`, `user_type`, `user_status`, `is_remove`, `token_name`, `user_device_id`, `created_datetime`, `updated_datetime`) VALUES 
  (1,0,'savita','admin@gmail.com','1234567890','$2a$10$sKx43VFGa04uNz3Q9lRWsOHS1bqpKTg6.l7Ttd/bM33AxlbEcd2KC',1,1,0,'','','07/04/2018 10:56:39 PM','03/08/2018 02:55:11 PM'),
  (2,1,'Gauri Kokate','demo@gmail.com','8976546541','$2a$10$sKx43VFGa04uNz3Q9lRWsOHS1bqpKTg6.l7Ttd/bM33AxlbEcd2KC',2,1,0,'','','09/04/2018 02:35:39 PM','29/08/2018 11:36:59 AM'),
  (3,3,'Sonali','savita@ysmsoftware.com','8888888888','$2a$10$sKx43VFGa04uNz3Q9lRWsOHS1bqpKTg6.l7Ttd/bM33AxlbEcd2KC',2,1,0,'','','09/04/2018 03:01:00 PM','10/08/2018 01:37:29 PM'),
  (4,1,'Sapna G','sapna@ysmsoftware.com','9999999999','$2a$10$Eny9PlWh9sJDMiBdI/2rpeMj5ogTLGtr02YVqNlNHMwcw4gyPGPD.',2,1,0,'','','09/04/2018 3:03:23 PM','16/08/2018 11:36:14 AM'),
  (5,1,'Priyanka','gauri@ysmsoftware.com','6796048604','$2a$10$Eny9PlWh9sJDMiBdI/2rpeMj5ogTLGtr02YVqNlNHMwcw4gyPGPD.',3,1,0,'','','14/06/2018 03:54:47 PM','10/08/2018 01:38:47 PM'),
  (6,1,'Sonal','savita10@ysmsoftware.com','9774967498','$2a$10$Eny9PlWh9sJDMiBdI/2rpeMj5ogTLGtr02YVqNlNHMwcw4gyPGPD.',3,1,0,'','','14/06/2018 03:55:29 PM','19/07/2018 09:13:37 AM'),
  (7,1,'Manisha','savita1@ysmsoftware.com','8976546513','$2a$10$Eny9PlWh9sJDMiBdI/2rpeMj5ogTLGtr02YVqNlNHMwcw4gyPGPD.',3,0,0,'','','14/06/2018 04:04:08 PM','10/08/2018 01:38:31 PM'),
  (8,1,'Sanskruti Rakibe','savita11@ysmsoftware.com','9349853985','$2a$10$Eny9PlWh9sJDMiBdI/2rpeMj5ogTLGtr02YVqNlNHMwcw4gyPGPD.',2,1,0,'','','10/07/2018 10:57:01 AM','10/08/2018 01:38:19 PM'),
  (9,1,'Pratiksha','savita111@ysmsoftware.com','7777777777','$2a$10$Eny9PlWh9sJDMiBdI/2rpeMj5ogTLGtr02YVqNlNHMwcw4gyPGPD.',3,1,0,'','','12/07/2018 03:26:20 PM','10/08/2018 01:38:08 PM'),
  (10,1,'Swapnil Ahire','swapnil002ahire@gmail.com','7359083950','$2a$10$sKx43VFGa04uNz3Q9lRWsOHS1bqpKTg6.l7Ttd/bM33AxlbEcd2KC',2,1,0,'','','13/07/2018 10:38:53 AM','16/08/2018 11:36:10 AM'),
  (11,1,'Ananya','anu@gmail.com','9893759847','$2a$10$Eny9PlWh9sJDMiBdI/2rpeMj5ogTLGtr02YVqNlNHMwcw4gyPGPD.',3,1,0,'','','13/07/2018 10:39:51 AM','10/08/2018 01:37:02 PM'),
  (12,3,'Tanvi Kothule','tanu@gmail.com','9465432132','$2a$10$88L/lsfhaSqUAMVbYcglT.m1ZwzfDY24aXrdqnOI8A6nlnFaydS.S',2,1,0,'','','17/08/2018 04:46:34 PM','17/08/2018 04:48:36 PM'),
  (13,1,'Pranjal Pingle','savita1111@ysmsoftware.com','9769789797','$2a$10$Qageytq6iEQXCB.y/.xrdeySSjseK0gzb9Dl3oMwjJbZ871B5BGza',3,1,0,'','','04/09/2018 01:07:23 PM','04/09/2018 01:07:23 PM');

COMMIT;

#
# Data for the `wing` table  (LIMIT 0,500)
#

INSERT INTO `wing` (`wing_id`, `wing_name`, `no_of_floors`, `total_flats`, `project_id`, `wing_tran_id`, `is_manual_floors`, `user_id`, `created_datetime`, `updated_datetime`, `is_remove`) VALUES 
  (1,'A-wing',3,19,1,1,0,0,'26/06/2018 10:32:04 AM','26/06/2018 10:38:41 AM',0),
  (2,'B-wing',3,16,1,2,0,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (3,'C-wing',3,26,1,3,0,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (4,'Wing-A',2,26,2,4,0,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (5,'Wing-B',3,24,2,5,0,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (6,'Wing-C',4,23,2,6,0,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (7,'A1',2,18,3,7,0,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (8,'A2',3,24,3,8,0,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (9,'A3',2,24,3,9,0,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (10,'A-wing',2,18,4,10,0,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (11,'B-wing',3,16,4,11,0,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (12,'C-wing',2,12,4,12,0,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (13,'Wing-P',4,47,5,13,0,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (14,'Wing-Q',3,40,5,14,0,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (15,'A',3,12,6,15,0,2,'30/06/2018 05:53:14 PM','30/06/2018 06:10:14 PM',0),
  (16,'B',3,12,6,16,0,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (17,'A',2,8,7,17,0,2,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM',0),
  (18,'Wing-A',3,25,8,18,0,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (19,'Wing B',2,23,8,19,0,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (20,'Wing-A',5,42,9,20,0,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (21,'Wing-B',2,19,9,21,0,2,'12/07/2018 02:29:25 PM','12/07/2018 02:30:20 PM',0),
  (23,'hhh',4,4,9,22,0,2,'12/07/2018 04:28:52 PM','12/07/2018 04:28:52 PM',0),
  (24,'gg',4,3,9,24,0,2,'12/07/2018 04:31:04 PM','12/07/2018 04:31:04 PM',0),
  (25,'Wing-1',3,21,10,25,0,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (26,'Wing-2',3,14,10,26,0,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (27,'Shakambari Residency-A',5,15,11,27,0,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (28,'Shakambari Residency-B',4,19,11,28,0,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (29,'Wing-C',2,11,11,29,0,2,'03/08/2018 03:16:04 PM','03/08/2018 03:16:04 PM',0),
  (30,'Wing-1',3,18,12,30,0,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (31,'Wing-2',4,32,12,31,0,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (32,'B',2,7,7,32,0,2,'10/08/2018 01:14:42 PM','10/08/2018 01:14:42 PM',0),
  (33,'C',2,6,7,33,0,2,'11/08/2018 03:09:55 PM','11/08/2018 03:09:55 PM',0),
  (34,'dd',1,2,13,34,0,2,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM',0),
  (35,'Wing-1',3,22,14,35,0,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (36,'Wing-2',2,13,14,36,0,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (37,'Wing-1',3,3,15,37,0,2,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM',0),
  (38,'a',2,2,16,38,0,2,'29/08/2018 09:30:34 AM','29/08/2018 09:30:34 AM',0),
  (39,'ABC',5,20,17,39,1,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (40,'PQR',3,23,17,40,0,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (41,'ABC',5,39,18,41,1,2,'07/09/2018 02:40:24 PM','08/09/2018 01:10:46 PM',0),
  (42,'PQR',3,28,18,42,0,2,'07/09/2018 02:40:24 PM','08/09/2018 01:11:03 PM',0),
  (43,'ABC',5,36,19,43,1,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (44,'PQR',2,21,19,44,0,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (45,'ABC',5,27,20,45,1,2,'07/09/2018 03:12:54 PM','10/09/2018 11:01:29 AM',0),
  (46,'XYZ',3,30,20,46,0,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (48,'Wing-ABC',3,21,5,47,0,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM',0);

COMMIT;

#
# Data for the `wing_details` table  (LIMIT 0,500)
#

INSERT INTO `wing_details` (`wing_details_id`, `floor_number`, `floor_name`, `property_id`, `property_area`, `no_of_flats`, `property_type_id`, `wing_id`, `user_id`, `created_datetime`, `updated_datetime`, `is_remove`) VALUES 
  (4,1,'Floor 1',1,432,6,1,2,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (5,2,'Floor 2',2,517,6,1,2,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (6,3,'Floor 3',3,845,4,1,2,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (7,-1,'Basement',5,324,6,2,3,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (8,0,'Ground Floor',4,482,4,2,3,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (9,1,'Floor 1',1,422,4,1,3,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (10,2,'Floor 2',2,634,6,1,3,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (11,3,'Floor 3',3,743,6,1,3,0,'26/06/2018 10:32:04 AM','26/06/2018 10:32:04 AM',0),
  (12,0,'Ground Floor',5,546,8,2,4,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (13,1,'Floor 1',1,346,10,1,4,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (14,2,'Floor 2',1,380,8,1,4,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (15,1,'Floor 1',1,450,8,1,5,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (16,2,'Floor 2',1,400,10,1,5,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (17,3,'Floor 3',2,500,6,1,5,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (18,1,'Floor 1',1,300,10,1,6,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (19,2,'Floor 2',1,350,8,1,6,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (20,3,'Floor 3',2,450,5,1,6,0,'26/06/2018 10:34:02 AM','26/06/2018 10:34:02 AM',0),
  (21,0,'Ground Floor',4,423,4,2,7,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (22,1,'Floor 1',2,632,8,1,7,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (23,2,'Floor 2',3,834,6,1,7,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (24,1,'Floor 1',1,423,8,1,8,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (25,2,'Floor 2',2,632,8,1,8,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (26,3,'Floor 3',3,734,8,1,8,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (27,1,'Floor 1',2,624,12,1,9,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (28,2,'Floor 2',3,834,12,1,9,0,'26/06/2018 10:38:16 AM','26/06/2018 10:38:16 AM',0),
  (29,1,'Floor 1',1,462,4,1,1,0,'26/06/2018 10:38:41 AM','26/06/2018 10:38:41 AM',0),
  (30,2,'Floor 2',2,563,4,1,1,0,'26/06/2018 10:38:41 AM','26/06/2018 10:38:41 AM',0),
  (31,3,'Floor 3',3,753,4,1,1,0,'26/06/2018 10:38:41 AM','26/06/2018 10:38:41 AM',0),
  (32,0,'Ground Floor',4,350,7,2,1,0,'26/06/2018 10:38:41 AM','26/06/2018 10:38:41 AM',0),
  (33,-1,'Basement',5,333,4,2,10,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (34,0,'Ground Floor',4,432,4,2,10,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (35,1,'Floor 1',1,423,6,1,10,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (36,2,'Floor 2',3,634,4,1,10,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (37,1,'Floor 1',1,234,6,1,11,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (38,2,'Floor 2',2,523,6,1,11,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (39,3,'Floor 3',3,632,4,1,11,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (40,0,'Ground Floor',4,523,2,2,12,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (41,1,'Floor 1',2,523,6,1,12,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (42,2,'Floor 2',3,734,4,1,12,0,'27/06/2018 11:42:53 AM','27/06/2018 11:42:53 AM',0),
  (43,0,'Ground Floor',4,364.67,3,2,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (44,0,'Ground Floor',4,352.38,5,2,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (45,0,'Ground Floor',4,346.89,4,2,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (46,0,'Ground Floor',5,434.5,2,2,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (47,1,'Floor 1',1,426.78,4,1,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (48,1,'Floor 1',1,345.76,2,1,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (49,1,'Floor 1',1,347.7,4,1,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (50,2,'Floor 2',1,370.57,7,1,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (51,2,'Floor 2',1,432.87,5,1,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (52,3,'Floor 3',3,578.89,5,1,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (53,4,'Floor 4',3,537.7,6,1,13,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (54,0,'Ground Floor',5,346,8,2,14,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (55,1,'Floor 1',2,434.65,6,1,14,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (56,1,'Floor 1',2,476.43,5,1,14,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (57,2,'Floor 2',2,367.78,7,1,14,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (58,2,'Floor 2',2,370.88,4,1,14,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (59,3,'Floor 3',2,567,6,1,14,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (60,3,'Floor 3',2,456.77,4,1,14,2,'29/06/2018 03:14:39 PM','29/06/2018 03:14:39 PM',0),
  (67,1,'Floor 1',2,1000,4,1,16,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (68,2,'Floor 2',2,1000,4,1,16,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (69,3,'Floor 3',2,1000,4,1,16,2,'30/06/2018 05:53:14 PM','30/06/2018 05:53:14 PM',0),
  (70,1,'Floor 1',2,1000,2,1,15,2,'30/06/2018 06:10:14 PM','30/06/2018 06:10:14 PM',0),
  (71,1,'Floor 1',1,500,2,1,15,2,'30/06/2018 06:10:14 PM','30/06/2018 06:10:14 PM',0),
  (72,2,'Floor 2',1,500,2,1,15,2,'30/06/2018 06:10:14 PM','30/06/2018 06:10:14 PM',0),
  (73,2,'Floor 2',2,1000,2,1,15,2,'30/06/2018 06:10:14 PM','30/06/2018 06:10:14 PM',0),
  (74,3,'Floor 3',2,1000,2,1,15,2,'30/06/2018 06:10:14 PM','30/06/2018 06:10:14 PM',0),
  (75,3,'Floor 3',1,500,2,1,15,2,'30/06/2018 06:10:14 PM','30/06/2018 06:10:14 PM',0),
  (76,1,'Floor 1',1,500,4,1,17,2,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM',0),
  (77,2,'Floor 2',1,500,4,1,17,2,'02/07/2018 05:05:07 PM','02/07/2018 05:05:07 PM',0),
  (78,1,'Floor 1',1,555,9,1,18,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (79,2,'Floor 2',1,554,9,1,18,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (80,3,'Floor 3',1,563,7,1,18,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (81,0,'Ground Floor',4,654,9,2,19,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (82,1,'Floor 1',2,657,7,1,19,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (83,2,'Floor 2',2,757,7,1,19,2,'07/07/2018 12:09:07 PM','07/07/2018 12:09:07 PM',0),
  (84,1,'Floor 1',1,344,10,1,20,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (85,2,'Floor 2',2,357,8,1,20,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (86,3,'Floor 3',1,380,9,1,20,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (87,4,'Floor 4',2,453,7,1,20,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (88,5,'Floor 5',3,445,8,1,20,2,'12/07/2018 02:26:12 PM','12/07/2018 02:26:12 PM',0),
  (92,0,'Ground Floor',4,533,6,2,21,2,'12/07/2018 02:30:20 PM','12/07/2018 02:30:20 PM',0),
  (93,1,'Floor 1',2,345,6,1,21,2,'12/07/2018 02:30:20 PM','12/07/2018 02:30:20 PM',0),
  (94,2,'Floor 2',2,410,7,1,21,2,'12/07/2018 02:30:20 PM','12/07/2018 02:30:20 PM',0),
  (96,0,'Ground Floor',4,444,4,2,22,2,'12/07/2018 04:28:52 PM','12/07/2018 04:28:52 PM',0),
  (97,1,'Floor 1',2,333,3,1,24,2,'12/07/2018 04:31:04 PM','12/07/2018 04:31:04 PM',0),
  (98,1,'Floor 1',1,342,6,1,25,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (99,2,'Floor 2',2,430,8,1,25,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (100,3,'Floor 3',3,400,7,1,25,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (101,1,'Floor 1',1,333,5,1,26,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (102,2,'Floor 2',2,444,5,1,26,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (103,3,'Floor 3',3,550,4,1,26,2,'23/07/2018 09:21:28 AM','23/07/2018 09:21:28 AM',0),
  (104,0,'Ground Floor',1,340,3,1,27,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (105,1,'Floor 1',1,340,4,1,27,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (106,2,'Floor 2',1,340,4,1,27,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (107,3,'Floor 3',1,340,4,1,27,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (108,1,'Floor 1',2,450,5,1,28,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (109,2,'Floor 2',2,450,5,1,28,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (110,3,'Floor 3',2,450,5,1,28,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (111,4,'Floor 4',2,450,4,1,28,2,'03/08/2018 03:15:07 PM','03/08/2018 03:15:07 PM',0),
  (112,1,'Floor 1',1,222,5,1,29,2,'03/08/2018 03:16:04 PM','03/08/2018 03:16:04 PM',0),
  (113,0,'Ground Floor',5,546,2,2,29,2,'03/08/2018 03:16:04 PM','03/08/2018 03:16:04 PM',0),
  (114,2,'Floor 2',1,350,4,1,29,2,'03/08/2018 03:16:04 PM','03/08/2018 03:16:04 PM',0),
  (115,1,'Floor 1',2,420,7,1,30,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (116,2,'Floor 2',2,430,6,1,30,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (117,3,'Floor 3',2,430,5,1,30,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (118,1,'Floor 1',1,340,8,1,31,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (119,2,'Floor 2',1,340,8,1,31,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (120,3,'Floor 3',1,340,8,1,31,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (121,4,'Floor 4',1,340,8,1,31,2,'10/08/2018 01:11:00 PM','10/08/2018 01:11:00 PM',0),
  (122,1,'Floor 1',1,222,2,1,32,2,'10/08/2018 01:14:42 PM','10/08/2018 01:14:42 PM',0),
  (123,2,'Floor 2',2,320,5,1,32,2,'10/08/2018 01:14:42 PM','10/08/2018 01:14:42 PM',0),
  (124,1,'Floor 1',1,222,2,1,33,2,'11/08/2018 03:09:55 PM','11/08/2018 03:09:55 PM',0),
  (125,2,'Floor 2',4,333,4,2,33,2,'11/08/2018 03:09:55 PM','11/08/2018 03:09:55 PM',0),
  (126,-1,'Basement',1,222,2,1,34,2,'18/08/2018 05:04:33 PM','18/08/2018 05:04:33 PM',0),
  (127,1,'Floor 1',1,350,6,1,35,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (128,2,'Floor 2',2,450,8,1,35,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (129,3,'Floor 3',2,450,8,1,35,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (130,1,'Floor 1',3,400,5,1,36,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (131,2,'Floor 2',3,400,5,1,36,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (132,0,'Ground Floor',4,370,3,2,36,2,'22/08/2018 03:55:46 PM','22/08/2018 03:55:46 PM',0),
  (133,2,'Floor 2',1,333,3,1,37,2,'24/08/2018 10:52:43 AM','24/08/2018 10:52:43 AM',0),
  (134,0,'Ground Floor',1,222,2,1,38,2,'29/08/2018 09:30:34 AM','29/08/2018 09:30:34 AM',0),
  (135,0,'Ground Commercial',4,555,5,2,39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (136,1,'Floor 1',1,888,4,1,39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (137,0,'Ground Residential',1,600,3,1,39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (138,2,'Floor 2',2,760,8,1,39,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (139,0,'Ground Floor',1,879,3,1,40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (140,1,'Floor 1',1,768,8,1,40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (141,2,'Floor 2',2,676,6,1,40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (142,3,'Floor 3',1,785,6,1,40,2,'07/09/2018 12:56:38 PM','07/09/2018 12:56:38 PM',0),
  (152,0,'Ground Commercial',4,756,6,2,43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (153,1,'Ground Residential',1,547,9,1,43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (154,1,'Floor 1',1,789,7,1,43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (155,2,'Floor 2',2,768,8,1,43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (156,3,'Floor 3',1,867,6,1,43,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (157,0,'Ground Floor',2,888,7,1,44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (158,1,'Floor 1',1,786,6,1,44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (159,1,'Floor 1',1,756,4,1,44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (160,2,'Floor 2',2,678,4,1,44,2,'07/09/2018 02:51:38 PM','07/09/2018 02:51:38 PM',0),
  (165,0,'Ground Floor',4,865,6,2,46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (166,1,'Floor 1',1,875,5,1,46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (167,2,'Floor 2',1,787,7,1,46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (168,1,'Floor 1',1,875,6,1,46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (169,2,'Floor 2',1,756,6,1,46,2,'07/09/2018 03:12:54 PM','07/09/2018 03:12:54 PM',0),
  (170,0,'Ground Comm.',4,876,3,2,47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM',0),
  (171,1,'Ground Residential',1,785,3,1,47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM',0),
  (172,1,'Floor 1',1,574,5,1,47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM',0),
  (173,2,'Floor 2',1,787,5,1,47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM',0),
  (174,3,'Floor 3',1,798,5,1,47,2,'08/09/2018 11:27:43 AM','08/09/2018 11:27:43 AM',0),
  (175,0,'Ground Commercial',4,867,6,2,41,2,'08/09/2018 01:10:46 PM','08/09/2018 01:10:46 PM',0),
  (176,1,'Ground Residential',2,657,8,1,41,2,'08/09/2018 01:10:46 PM','08/09/2018 01:10:46 PM',0),
  (177,1,'Floor 1',1,678,10,1,41,2,'08/09/2018 01:10:46 PM','08/09/2018 01:10:46 PM',0),
  (178,2,'Floor 2',3,897,7,1,41,2,'08/09/2018 01:10:46 PM','08/09/2018 01:10:46 PM',0),
  (179,3,'Floor 3',2,789,8,1,41,2,'08/09/2018 01:10:46 PM','08/09/2018 01:10:46 PM',0),
  (180,0,'Ground Floor',4,768,8,2,42,2,'08/09/2018 01:11:03 PM','08/09/2018 01:11:03 PM',0),
  (181,1,'Floor 1',1,686,9,1,42,2,'08/09/2018 01:11:03 PM','08/09/2018 01:11:03 PM',0),
  (182,2,'Floor 2',1,876,6,1,42,2,'08/09/2018 01:11:03 PM','08/09/2018 01:11:03 PM',0),
  (183,3,'Floor 3',2,978,5,1,42,2,'08/09/2018 01:11:03 PM','08/09/2018 01:11:03 PM',0),
  (184,0,'Ground Commercial',4,567,6,2,45,2,'10/09/2018 11:01:29 AM','10/09/2018 11:01:29 AM',0),
  (185,1,'Ground Residential',1,666,6,1,45,2,'10/09/2018 11:01:29 AM','10/09/2018 11:01:29 AM',0),
  (186,1,'Floor 1',1,566,7,1,45,2,'10/09/2018 11:01:29 AM','10/09/2018 11:01:29 AM',0),
  (187,2,'Floor 2',1,657,3,1,45,2,'10/09/2018 11:01:29 AM','10/09/2018 11:01:29 AM',0),
  (188,3,'Floor 3',2,978,5,1,45,2,'10/09/2018 11:01:29 AM','10/09/2018 11:01:29 AM',0);

COMMIT;

