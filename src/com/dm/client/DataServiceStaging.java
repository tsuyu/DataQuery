package com.dm.client;

import java.sql.Connection;

public interface DataServiceStaging {
	Connection getConnection() throws Exception;
}
