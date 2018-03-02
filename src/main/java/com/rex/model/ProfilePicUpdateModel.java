package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rex.bean.ErrorBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.util.DBConnector;

public class ProfilePicUpdateModel {
	private Connection conn;
	private PreparedStatement stmt;

	public ProfilePicUpdateModel() {
		conn = (new DBConnector()).getConnection();
		try {
			stmt = conn.prepareStatement("UPDATE users SET users.src=? WHERE users.user_id=?");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ResponseBean update(String src, int i, SuccessBean process) {
		try {
			src = src.replaceAll("\\\\", "/");
			stmt.setString(1, src);
			stmt.setInt(2, i);
			stmt.execute();
			process.setQuery("true");
			return process;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("G-P-U-1", e.toString(), this.getClass().toGenericString());
		}
	}
}
