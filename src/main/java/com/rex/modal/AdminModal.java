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

	private PreparedStatement auth;
	private PreparedStatement gcl;
	private PreparedStatement upd;
	private PreparedStatement cap;
	private PreparedStatement ck;
	private PreparedStatement cu;

	public AdminModal() {
		conn = (new DBConnector()).getConnection();
		try {
			auth = conn.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
			gcl = conn.prepareStatement("SELECT * FROM users");
			upd = conn.prepareStatement(
					"UPDATE users SET firstname=?, lastname=?, gender=?, contact=?, street_no=?, town=?, city=?, state=? WHERE user_id=?");
			cap = conn.prepareStatement("UPDATE users SET auth=? WHERE user_id=?");
			cu = conn.prepareStatement("UPDATE users SET firstname=?, lastname=?, gender=?, contact=? WHERE user_id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object auth(LoginBean user) {
		try {
			auth.setString(1, user.getUsername());
			auth.setString(2, user.getPassword());

			ResultSet rs = auth.executeQuery();
			if (rs.next()) {
				if (rs.next()) {
					return new ErrorBean("A-A-2", "Email exist more than once", this.getClass().toGenericString());
				} else {
					rs.previous();
				}
				if (!rs.getString("auth").equals("2")) {
					return new ErrorBean("A-A-4", "Are you an Admin ? Try to Login as a Client",
							this.getClass().toGenericString());
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
			ResultSet rs = gcl.executeQuery();
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
			upd.setString(1, user.getFname());
			upd.setString(2, user.getLname());
			upd.setString(3, user.getGender());
			upd.setString(4, user.getContact());
			upd.setString(5, user.getStreet());
			upd.setString(6, user.getTown());
			upd.setString(7, user.getCity());
			upd.setString(8, user.getState());
			upd.setString(9, user.getUid());

			if (upd.executeUpdate() == 1) {
				return new SuccessBean("A-U-1", "Profile Updated Successfully", "admin-update", "success");
			} else {
				return new SuccessBean("A-U-1", "User Existance not Found (" + user.getUid() + ")", "admin-update",
						"failed");
			}
		} catch (SQLException e) {
			return new ErrorBean("A-U-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public Object activate(String act, String id) {
		try {
			cap.setString(1, act);
			cap.setString(2, id);
			if (cap.executeUpdate() == 1) {
				return "Client " + ((act.equals("1")) ? "A" : "Dea") + "ctivated Successfully!";
			} else {
				return new ErrorBean("A-C-2", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public Object delete(String id) {
		try {
			ck = conn.prepareStatement("DELETE FROM users WHERE user_id=?");
			ck.setString(1, id);
			if (ck.executeUpdate() == 1) {
				return "Client Kicked out Successfully!";
			} else {
				return new ErrorBean("A-C-4", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-3", e.toString(), this.getClass().toGenericString());
		}
	}

	public Object promote(String act, String id) {
		try {
			cap.setString(1, act);
			cap.setString(2, id);
			if (cap.executeUpdate() == 1) {
				if (act.equals("2"))
					return "Client is now an Admin, Login with Client Data as Admin also.";
				else
					return "Admin is no longer an Admin, Login with Admin Data as a Client.";
			} else {
				return new ErrorBean("A-C-6", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-5", e.toString(), this.getClass().toGenericString());
		}
	}

	public Object clientUpdate(UserBean user) {
		try {
			cu.setString(1, user.getFname());
			cu.setString(2, user.getLname());
			cu.setString(3, user.getGender());
			cu.setString(4, user.getContact());
			cu.setString(5, user.getUid());

			if (cu.executeUpdate() == 1) {
				return new SuccessBean("A-C-7", "Client Updated Successfully", "admin-client-update", "success");
			} else {
				return new SuccessBean("A-C-8", "User Existance not Found (" + user.getUid() + ")",
						"admin-client-update", "failed");
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-7", e.toString(), this.getClass().toGenericString());
		}
	}
}
