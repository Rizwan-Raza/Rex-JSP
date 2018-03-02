package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class SignUpModel {
	private Connection conn;
	private PreparedStatement add = null;
	private PreparedStatement stmt = null;

	private ResultSet rs = null;

	public SignUpModel() {
		conn = (new DBConnector()).getConnection();
		try {
			add = conn.prepareStatement("INSERT INTO addresses (street_no, town, city, state) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt = conn.prepareStatement(
					"INSERT INTO users (firstname, lastname, email, password, gender, contact, add_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
	}

	public String add(UserBean mem) {
		if (conn == null || add == null || stmt == null)
			return "Can't connect with Database";
		try {
			add.setString(1, mem.getAddress().getStreet());
			add.setString(2, mem.getAddress().getTown());
			add.setString(3, mem.getAddress().getCity());
			add.setString(4, mem.getAddress().getState());
			add.executeUpdate();
			rs = add.getGeneratedKeys();
			rs.next();

			stmt.setString(1, mem.getFname());
			stmt.setString(2, mem.getLname());
			stmt.setString(3, mem.getEmail());
			stmt.setString(4, mem.getPassword());
			stmt.setString(5, mem.getGender());
			stmt.setString(6, mem.getContact());
			stmt.setString(7, rs.getString(1));

			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
