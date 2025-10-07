package com.edo.factories;

import java.sql.Connection;

public class ConnectionFactory {

	private static String host = "jdbc:postgresql://localhost:5434/bd_productApi";
	private static String user = "user";
	private static String password = "password";
	
	
	public static Connection getConnection() throws Exception {

		return java.sql.DriverManager.getConnection(host, user, password);
	}
	
}
