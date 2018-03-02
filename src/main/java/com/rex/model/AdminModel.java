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
	private PreparedStatement ccp;

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
			ccp = conn.prepareStatement("UPDATE users SET users.password=? WHERE users.user_id=?");
			ck = conn.prepareStatement(
					"DELETE users, addresses FROM users LEFT JOIN addresses ON addresses.add_id= users.add_id WHERE users.user_id=?");
		} catch (NullPointerException | SQLException e) {
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
				return new UserBean(rs.getInt("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getInt("users.auth"),
						rs.getString("users.src"), rs.getDate("users.time"),
						new AddressBean(rs.getInt("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.town"), rs.getString("addresses.city"),
								rs.getString("addresses.state")));
			} else {
				return new ErrorBean("A-L-A-1", "Incorrect Password!", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("A-L-A-3", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<UserBean> getClients() {
		try {
			ResultSet rs = gcl.executeQuery();
			ArrayList<UserBean> al = new ArrayList<UserBean>();
			while (rs.next()) {
				al.add(new UserBean(rs.getInt("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getInt("users.auth"),
						rs.getString("users.src"), rs.getDate("users.time"),
						new AddressBean(rs.getInt("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.city"), rs.getString("addresses.town"),
								rs.getString("addresses.state"))));
			}
			return al;
		} catch (NullPointerException | SQLException e) {
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
			upd.setInt(9, mem.getUid());

			if (upd.executeUpdate() == 2) {
				return new SuccessBean("A-D-U-1", "Profile Updated Successfully", "admin-update", "success");
			} else {
				return new SuccessBean("A-D-U-2", "User Existance not Found (" + mem.getUid() + ")", "admin-update",
						"failed");
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("A-D-U-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean activate(int act, int id) {
		try {
			cap.setInt(1, act);
			cap.setInt(2, id);
			if (cap.executeUpdate() == 1) {
				return new SuccessBean("A-C-A-1", "Client " + ((act == 1) ? "A" : "Dea") + "ctivated Successfully!",
						"client-active", "success");
			} else {
				return new ErrorBean("A-C-A-2", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("A-C-A-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean delete(int id) {
		try {
			ck.setInt(1, id);
			if (ck.executeUpdate() == 1) {
				return new SuccessBean("A-C-K-1", "Client Kicked out Successfully!", "client-delete", "success");
			} else {
				return new ErrorBean("A-C-K-2", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("A-C-K-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean promote(int act, int id) {
		try {
			cap.setInt(1, act);
			cap.setInt(2, id);
			if (cap.executeUpdate() == 1) {
				if (act == 2)
					return new SuccessBean("A-C-P-1", "Client is now an Admin also.", "client-delete", "success");
				else
					return new SuccessBean("A-C-P-1", "Admin is no longer an Admin.", "client-delete", "success");
			} else {
				return new ErrorBean("A-C-P-2", "User Existance not Found", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
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
			cu.setInt(6, user.getUid());

			if (cu.executeUpdate() == 1) {
				return new SuccessBean("A-C-U-2", "Client Updated Successfully", "admin-client-update", "success");
			} else {
				return new SuccessBean("A-C-U-1", "User Existance not Found (" + user.getUid() + ")",
						"admin-client-update", "failed");
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("A-C-U-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ResponseBean clientPassword(String password, int uid) {
		try {
			ccp.setString(1, password);
			ccp.setInt(2, uid);

			if (ccp.executeUpdate() == 1) {
				return new SuccessBean("A-C-P-2", "Client's Password Changed Successfully", "admin-client-password",
						"success");
			} else {
				return new SuccessBean("A-C-P-1", "User Existance not Found (" + uid + ")", "admin-client-update",
						"failed");
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("A-C-P-1", e.toString(), this.getClass().toGenericString());
		}
	}
}
