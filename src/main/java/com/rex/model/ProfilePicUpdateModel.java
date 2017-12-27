package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rex.bean.ErrorBean;
import com.rex.bean.SuccessBean;
import com.rex.util.DBConnector;

public class ProfilePicUpdateModel {
	private Connection conn;
	private PreparedStatement stmt;

	public ProfilePicUpdateModel() {
		conn = (new DBConnector()).getConnection();
		try {
			stmt = conn.prepareStatement("UPDATE users SET users.src=? WHERE users.user_id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object update(String src, String id, SuccessBean process) {
		try {
			src = src.replaceAll("\\\\", "/");
			stmt.setString(1, src);
			stmt.setString(2, id);
			stmt.execute();
			process.setQuery("true");
			return process;
		} catch (SQLException e) {
			return new ErrorBean("P-U-1", e.toString(), this.getClass().toGenericString());
		}
	}
}
