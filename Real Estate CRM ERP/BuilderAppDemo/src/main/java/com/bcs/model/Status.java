package com.bcs.model;


import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Status {    	

		 private int code;  
	     private String message; 
	     private List list;
	     private List list1;
	     private int id;
	     private int statusCode;
	     private int count;
	     private int type;
	     private HashMap<Integer, String> statusList;
	     private Object object;
	     private String constantFilePath; 	
	     private int result;
	     private Object jsonObject;
	     private String token;   // JWT issued on successful login (Spring Boot 3 / stateless auth)

	     public Status() {}

	     public Status(String message) {
			super();
			this.message = message;
		 }
	     
	     public Status(int code, HashMap<Integer, String> statusList) {
				super();
				this.code = code;
				this.statusList = statusList;
		}
	   
		 public Status(int code, String message) {
			super();
			this.code = code;
			this.message = message;
		 }
		 
		 public Status(int code, Object object) {
				super();
				this.code = code;
				this.object = object;
		}
		 
		 public Status(int code, List list) {
		 		super();
		 		this.code       = code;
		 		this.list       = list;				
		 }

		 
		 public Status(int code, Object object, String constantFilePath) {
				super();
				this.code 			   = code;
				this.object 		   = object;
				this.constantFilePath  = constantFilePath;
				
		}
		 
		 public Status(int code, Object object, Object jsonObject) {
				super();
				this.code 			   = code;
				this.object 		   = object;
				this.jsonObject        = jsonObject;
				//this.setJsonObject(jsonObject);
				
		}
		 
		 public Status(int code, String message, String constantFilePath) {
				super();
				this.code 			   = code;
				this.message 		   = message;
				this.constantFilePath  = constantFilePath;
				
		}
		
		 public Status(int code, String message, int id) {
				super();
				this.code = code;
				this.message = message;
				this.id = id;
		 }
		
		 public Status(List list, int count) {
		 		super();
		 		this.list       = list;
				this.count       = count;
		 }

		 
		 public Status(List list,List list1, int count) {
		 		super();
		 		this.list       = list;
		 		this.list1       = list1;
				this.count       = count;
		 }		 
	
			
	/*	 public Status(List list, int code) {
		 		super();
		 		this.list       = list;
				this.code       = code;			
		}*/
		 
		 public Status( int code, Object object, int result) {
		 		super();
				this.code     = code;
				this.object   = object;
				this.result    = result;
		}
		 
		 public Status( int code, List list, int result) {
		 		super();
				this.code     = code;
				this.list     = list;
				this.result   = result;
		}
		

		public Status(List list, int code, int count) {
		 		super();
		 		this.list     = list;
				this.code     = code;
				this.count    = count;
		}
		
		public Status( int code, List list,  Object object) {
	 		super();
			this.code     = code;
			this.list     = list;
			this.object   = object;
	}
		
		public Status(List list, int code, String message) {
	 		super();
	 		this.list       = list;
			this.code       = code;
			this.message = message;
		}
		//---------------------------------------------------------------------))))))))))))))))))))))))))))---------------------
		public Status(int code, List list , String constantFilePath) {
	 		super();
	 		this.list       = list;
			this.code       = code;
			this.constantFilePath  = constantFilePath;
		}
		 
		public Status(int id, int statusCode, String message) {  
			 super();
			 this.id = id;  
			 this.message = message;  
			 this.statusCode = statusCode; 
		}  
		 
		public Status(int id, int type,int statusCode, String message) {  
			 super();
			 this.id = id;  
			 this.type = type;
			 this.message = message;  
			 this.statusCode = statusCode;			
		}  
		 
		
		public Status(int code,String message,Object object,String constantFilePath) {  
			 super();
			 this.code = code;  
			 this.message = message;
			 this.object = object;  
			 this.constantFilePath =  constantFilePath;
			
		}  
		 
		public int getCode() {
			return code;
		}
	
		public void setCode(int code) {
			this.code = code;
		}
	
		public String getMessage() {
			return message;
		}
	
		public void setMessage(String message) {
			this.message = message;
		}
	
		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
		
		public int getStatusCode() {
			return statusCode;
		}
		
		public void setStatusCode(int statusCode) {
			this.statusCode = statusCode;
		}

		public int getCount() {
			return count;
		}
	
		public void setCount(int count) {
			this.count = count;
		}
		
		public List getList() {
			return list;
		}
	
		public void setList(List list) {
			this.list = list;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public HashMap<Integer, String> getStatusList() {
			return statusList;
		}

		public void setStatusList(HashMap<Integer, String> statusList) {
			this.statusList = statusList;
		}

		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}

		public String getConstantFilePath() {
			return constantFilePath;
		}

		public void setConstantFilePath(String constantFilePath) {
			this.constantFilePath = constantFilePath;
		}

		public int getResult() {
			return result;
		}

		public void setResult(int result) {
			this.result = result;
		}

		public List getList1() {
			return list1;
		}

		public void setList1(List list1) {
			this.list1 = list1;
		}

		public Object getJsonObject() {
			return jsonObject;
		}

		public void setJsonObject(Object jsonObject) {
			this.jsonObject = jsonObject;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

	 
}  

