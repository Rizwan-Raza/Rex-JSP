package com.rex.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rex.bean.UserBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.util.DBConnector;

public class AdminLoginModal {
	private Connection conn;
	private PreparedStatement stmt;

	public AdminLoginModal() {
		conn = (new DBConnector()).getConnection();
	}

	public Object auth(LoginBean user) {
		try {
			stmt = conn.prepareStatement("SELECT * FROM admins WHERE email=? AND password=?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.next()) {
					return new ErrorBean("A-A-2", "Email exist more than once", "AdminLoginModal");
				} else {
					rs.previous();
				}
				return new UserBean(rs.getString("aid"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("email"), rs.getString("password"), rs.getString("gender"),
						rs.getString("contact"), rs.getString("street_no"), rs.getString("town"), rs.getString("city"),
						rs.getString("state"), null, rs.getString("src"), rs.getString("time"));
			} else {
				return new ErrorBean("A-A-1", "Incorrect Password!", "AdminLoginModal");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
