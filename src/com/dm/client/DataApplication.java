package com.dm.client;

import com.dm.client.DataServiceStaging;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class DataApplication {

	@Bean
	DataServiceStaging mockDataServiceStaging() {
		return new DataServiceStaging() {
			public Connection getConnection() throws Exception {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/stdb");
				return ds.getConnection();
			}
		};
	}
	
	@Bean
	DataServiceProduction mockDataServiceProduction() {
		return new DataServiceProduction() {
			public Connection getConnection() throws Exception {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/prdb");
				return ds.getConnection();
			}
		};
	}
}