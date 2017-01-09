package com.dm.client;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataComponentProduction implements DataServiceProduction{
	final private DataServiceProduction production;
	
	
	@Autowired 
	 public DataComponentProduction(DataServiceProduction production){
		 this.production = production;
	 }
	 
	 public Connection getConnection() throws Exception{	
		 return this.production.getConnection();
	 }
}
