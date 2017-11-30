package com.rex.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rex.bean.ErrorBean;
import com.rex.bean.SuccessBean;
import com.rex.util.DBConnector;

public class ProfilePicUpdateModal {
	private Connection conn;
	private PreparedStatement stmt;

	public ProfilePicUpdateModal() {
		conn = (new DBConnector()).getConnection();
		try {
			stmt = conn.prepareStatement("UPDATE users SET src=? WHERE user_id=?");
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
			return new ErrorBean("P-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}
}
