package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rex.bean.AddressBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class AdminModel {
	private Connection conn = null;

	private PreparedStatement auth;
	private PreparedStatement gcl;
	private PreparedStatement upd;
	private PreparedStatement cap;
	private PreparedStatement ck;
	private PreparedStatement cu;

	public AdminModel() {
		conn = (new DBConnector()).getConnection();
		try {
			auth = conn.prepareStatement(
					"SELECT * FROM users, addresses WHERE users.email=? AND users.password=? AND addresses.add_id=users.add_id");
			gcl = conn.prepareStatement("SELECT * FROM users, addresses WHERE addresses.add_id=users.add_id");
			upd = conn.prepareStatement(
					"UPDATE users, addresses SET users.firstname=?, users.lastname=?, users.gender=?, users.contact=?, addresses.street_no=?, addresses.town=?, addresses.city=?, addresses.state=? WHERE users.user_id=? AND users.add_id=addresses.add_id");
			cap = conn.prepareStatement("UPDATE users SET users.auth=? WHERE users.user_id=?");
			cu = conn.prepareStatement(
					"UPDATE users SET users.firstname=?, users.lastname=?, users.gender=?, users.email=?, users.contact=? WHERE users.user_id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResponseBean auth(LoginBean user) {
		try {
			auth.setString(1, user.getUsername());
			auth.setString(2, user.getPassword());

			ResultSet rs = auth.executeQuery();
			if (rs.next()) {
				if (rs.next()) {
					return new ErrorBean("A-L-A-2", "Email exist more than once", this.getClass().toGenericString());
				} else {
					rs.previous();
				}
				if (!rs.getString("auth").equals("2")) {
					return new ErrorBean("A-L-A-4", "Are you an Admin ? Try to Login as a Client",
							this.getClass().toGenericString());
				}
				return new UserBean(rs.getString("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getString("users.auth"),
						rs.getString("users.src"), rs.getDate("users.time"),
						new AddressBean(rs.getString("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.town"), rs.getString("addresses.city"),
								rs.getString("addresses.state")));
			} else {
				return new ErrorBean("A-L-A-1", "Incorrect Password!", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-L-A-3", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<UserBean> getClients() {
		try {
			ResultSet rs = gcl.executeQuery();
			ArrayList<UserBean> al = new ArrayList<UserBean>();
			while (rs.next()) {
				al.add(new UserBean(rs.getString("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getString("users.auth"),
						rs.getString("users.src"), rs.getDate("users.time"),
						new AddressBean(rs.getString("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.city"), rs.getString("addresses.town"),
								rs.getString("addresses.state"))));
			}
			return al;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ResponseBean update(UserBean mem) {
		try {
			upd.setString(1, mem.getFname());
			upd.setString(2, mem.getLname());
			upd.setString(3, mem.getGender());
			upd.setString(4, mem.getContact());
			upd.setString(5, mem.getAddress().getStreet());
			upd.setString(6, mem.getAddress().getTown());
			upd.setString(7, mem.getAddress().getCity());
			upd.setString(8, mem.getAddress().getState());
			upd.setString(9, mem.getUid());

			if (upd.executeUpdate() == 2) {
				return new SuccessBean("A-D-U-1", "Profile Updated Successfully", "admin-update", "success");
			} else {
				return new SuccessBean("A-D-U-2", "User Existance not Found (" + mem.getUid() + ")", "admin-update",
						"failed");
			}
		} catch (SQLException e) {
			return new ErrorBean("A-D-U-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean activate(String act, String id) {
		try {
			cap.setString(1, act);
			cap.setString(2, id);
			if (cap.executeUpdate() == 1) {
				return new SuccessBean("A-C-A-1",
						"Client " + ((act.equals("1")) ? "A" : "Dea") + "ctivated Successfully!", "client-active",
						"success");
			} else {
				return new ErrorBean("A-C-A-2", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-A-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean delete(String id) {
		try {
			ck = conn
					.prepareStatement("DELETE FROM users, addresses WHERE user_id=? AND users.add_id=addresses.add_id");
			ck.setString(1, id);
			if (ck.executeUpdate() == 1) {
				return new SuccessBean("A-C-K-1", "Client Kicked out Successfully!", "client-delete", "success");
			} else {
				return new ErrorBean("A-C-K-2", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-K-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean promote(String act, String id) {
		try {
			cap.setString(1, act);
			cap.setString(2, id);
			if (cap.executeUpdate() == 1) {
				if (act.equals("2"))
					return new SuccessBean("A-C-P-1", "Client is now an Admin, Login with Client Data as Admin also.",
							"client-delete", "success");
				else
					return new SuccessBean("A-C-P-1", "Admin is no longer an Admin, Login with Admin Data as a Client.",
							"client-delete", "success");
			} else {
				return new ErrorBean("A-C-P-2", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-P-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean clientUpdate(UserBean user) {
		try {
			cu.setString(1, user.getFname());
			cu.setString(2, user.getLname());
			cu.setString(3, user.getGender());
			cu.setString(4, user.getEmail());
			cu.setString(5, user.getContact());
			cu.setString(6, user.getUid());

			if (cu.executeUpdate() == 1) {
				return new SuccessBean("A-C-U-2", "Client Updated Successfully", "admin-client-update", "success");
			} else {
				return new SuccessBean("A-C-U-1", "User Existance not Found (" + user.getUid() + ")",
						"admin-client-update", "failed");
			}
		} catch (SQLException e) {
			return new ErrorBean("A-C-U-1", e.toString(), this.getClass().toGenericString());
		}
	}
}
