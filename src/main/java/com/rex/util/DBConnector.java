package com.rex.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
	private Connection conn = null;

	public DBConnector() {

		Properties db = new Properties();
		try {
			db.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(db.getProperty("driver"));
			conn = DriverManager.getConnection(db.getProperty("url"), db);
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// System.out.println("IO");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}
}
