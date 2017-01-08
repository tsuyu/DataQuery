package com.dm.client;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dm.client.DataServiceStaging;

@Component("st")
public class DataComponentStaging implements DataServiceStaging{
	final private DataServiceStaging staging;
	
	
	@Autowired 
	 public DataComponentStaging(DataServiceStaging staging){
		 this.staging = staging;
	 }
	 
	 public Connection getConnection() throws Exception{	
		 return this.staging.getConnection();
	 }
}
