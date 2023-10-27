package com.utility;

import java.sql.*;

public class DbConnection {

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/bankdb";
	static String username = "root";
	static String userpass = "root";
	static Connection conn;

	public static Connection getConnect() {

		try {
			Class.forName(driver);
			System.out.println("Driver Loaded");
			conn = DriverManager.getConnection(url, username, userpass);
			System.out.println("Connected to DB...");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}
}
