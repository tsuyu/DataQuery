package com.dm.client;

import com.dm.client.DataService;
import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
public class Application {

	@Bean
	DataService mockDataService() {
		return new DataService() {
			public Connection getConnection() throws Exception {
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup("jdbc/dmdb");
				return ds.getConnection();
			}
		};
	}
}