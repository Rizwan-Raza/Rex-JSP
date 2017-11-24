package com.rex.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rex.bean.UserBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.util.DBConnector;

public class ClientLoginModal {
	private Connection conn;
	private PreparedStatement stmt;

	public ClientLoginModal() {
		conn = (new DBConnector()).getConnection();
	}

	public Object auth(LoginBean user) {
		try {
			stmt = conn.prepareStatement("SELECT * FROM clients WHERE email=? AND password=?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.next()) {
					return new ErrorBean("C-A-3", "Email exist more than once", "ClientLoginModal");
				} else {
					rs.previous();
				}
				if (rs.getString("active") == "0") {
					return new ErrorBean("C-A-2", "Not Activated Yet!", "ClientLoginModal");
				}
				return new UserBean(rs.getString("cid"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("email"), rs.getString("password"), rs.getString("gender"),
						rs.getString("contact"), rs.getString("street_no"), rs.getString("town"), rs.getString("city"),
						rs.getString("state"), rs.getString("active"), rs.getString("src"), rs.getString("time"));
			} else {
				return new ErrorBean("C-A-1", "Incorrect Password!", "ClientLoginModal");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
