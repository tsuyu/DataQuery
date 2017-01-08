package com.dm.client;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dm.client.DataService;

@Component
public class DataComponent {
	final private DataService service;
	
	@Autowired 
	 public DataComponent(DataService service){
		 this.service = service;
	 }
	 
	 public Connection getConnection() throws Exception{	
		 return this.service.getConnection();
	 }
}
