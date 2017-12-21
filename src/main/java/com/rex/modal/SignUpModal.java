package com.rex.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class SignUpModal {
	private Connection conn;
	private PreparedStatement stmt;

	public SignUpModal() {
		conn = (new DBConnector()).getConnection();
		try {
			stmt = conn.prepareStatement(
					"INSERT INTO users (firstname, lastname, email, password, gender, contact, street_no, town, city, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String add(UserBean user) {
		try {
			stmt.setString(1, user.getFname());
			stmt.setString(2, user.getLname());
			stmt.setString(3, user.getEmail());
			stmt.setString(4, user.getPassword());
			stmt.setString(5, user.getGender());
			stmt.setString(6, user.getContact());
			stmt.setString(7, user.getStreet());
			stmt.setString(8, user.getTown());
			stmt.setString(9, user.getCity());
			stmt.setString(10, user.getState());

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
