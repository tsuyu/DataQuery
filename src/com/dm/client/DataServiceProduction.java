package com.dm.client;

import java.sql.Connection;

public interface DataServiceProduction {
	Connection getConnection() throws Exception;
}
