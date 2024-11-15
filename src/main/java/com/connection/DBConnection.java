package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection opcon = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "Kd#8140849670");
		return opcon;

	}

	public void ConnectionClose(Connection connection) throws SQLException {
		connection.close();
	}
}
