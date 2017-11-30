package com.rex.modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class AdminModal {
	private Connection conn = null;
	private PreparedStatement stmt;

	public AdminModal() {
		conn = (new DBConnector()).getConnection();
	}

	public Object auth(LoginBean user) {
		try {
			stmt = conn.prepareStatement("SELECT * FROM users WHERE email=? AND password=? AND auth=2");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.next()) {
					return new ErrorBean("A-A-2", "Email exist more than once", this.getClass().toGenericString());
				} else {
					rs.previous();
				}
				return new UserBean(rs.getString("user_id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("email"), rs.getString("password"), rs.getString("gender"),
						rs.getString("contact"), rs.getString("street_no"), rs.getString("town"), rs.getString("city"),
						rs.getString("state"), rs.getString("auth"), rs.getString("src"), rs.getString("time"));
			} else {
				return new ErrorBean("A-A-1", "Incorrect Password!", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-A-3", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<UserBean> getClients() {
		try {
			stmt = conn.prepareStatement("SELECT * FROM users");
			ResultSet rs = stmt.executeQuery();
			ArrayList<UserBean> al = new ArrayList<UserBean>();
			while (rs.next()) {
				al.add(new UserBean(rs.getString("user_id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("email"), rs.getString("password"), rs.getString("gender"),
						rs.getString("contact"), rs.getString("street_no"), rs.getString("city"), rs.getString("town"),
						rs.getString("state"), rs.getString("auth"), rs.getString("src"), rs.getString("time")));
			}
			return al;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object update(UserBean user) {
		try {
			stmt = conn.prepareStatement(
					"UPDATE users SET firstname=?, lastname=?, gender=?, contact=?, street_no=?, town=?, city=?, state=? WHERE user_id=?");
			stmt.setString(1, user.getFname());
			stmt.setString(2, user.getLname());
			stmt.setString(3, user.getGender());
			stmt.setString(4, user.getContact());
			stmt.setString(5, user.getStreet());
			stmt.setString(6, user.getTown());
			stmt.setString(7, user.getCity());
			stmt.setString(8, user.getState());
			stmt.setString(9, user.getUid());

			if (stmt.executeUpdate() == 1) {
				return new SuccessBean("A-U-1", "Profile Updated Successfully", "admin-update", "success");
			} else {
				return new SuccessBean("A-U-1", "User Existance not Found (" + user.getUid() + ")", "admin-update",
						"failed");
			}
		} catch (SQLException e) {
			return new ErrorBean("A-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}
}
