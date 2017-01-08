package com.dm.client;

import java.sql.Connection;

public interface DataService {
	Connection getConnection() throws Exception;
}
