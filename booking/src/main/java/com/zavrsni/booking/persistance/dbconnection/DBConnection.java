package com.zavrsni.booking.persistance.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static Connection Conn() {
		String dbUrl = "jdbc:mysql//localhost:3306/booking";
		String user = "root";
		String password = "";
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl,user,password);
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}
