package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rex.bean.AddressBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.MemberBean;
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
					"UPDATE users SET users.firstname=?, users.lastname=?, users.gender=?, users.contact=? WHERE users.user_id=?");
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
				return new MemberBean(new UserBean(rs.getString("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getString("users.auth"),
						rs.getString("users.src"), rs.getString("users.time")),
						new AddressBean(rs.getString("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.town"), rs.getString("addresses.city"),
								rs.getString("addresses.state")));
			} else {
				return new ErrorBean("A-A-1", "Incorrect Password!", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("A-A-3", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<MemberBean> getClients() {
		try {
			ResultSet rs = gcl.executeQuery();
			ArrayList<MemberBean> al = new ArrayList<MemberBean>();
			while (rs.next()) {
				al.add(new MemberBean(new UserBean(rs.getString("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getString("users.auth"),
						rs.getString("users.src"), rs.getString("users.time")),
						new AddressBean(rs.getString("addresses.add_id"), rs.getString("addresses.street_no"), rs.getString("addresses.city"),
								rs.getString("addresses.town"), rs.getString("addresses.state"))));
			}
			return al;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object update(MemberBean mem) {
		try {
			upd.setString(1, mem.getUser().getFname());
			upd.setString(2, mem.getUser().getLname());
			upd.setString(3, mem.getUser().getGender());
			upd.setString(4, mem.getUser().getContact());
			upd.setString(5, mem.getAddress().getStreet());
			upd.setString(6, mem.getAddress().getTown());
			upd.setString(7, mem.getAddress().getCity());
			upd.setString(8, mem.getAddress().getState());
			upd.setString(9, mem.getUser().getUid());

			if (upd.executeUpdate() == 2) {
				return new SuccessBean("A-U-1", "Profile Updated Successfully", "admin-update", "success");
			} else {
				return new SuccessBean("A-U-1", "User Existance not Found (" + mem.getUser().getUid() + ")",
						"admin-update", "failed");
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
			ck = conn
					.prepareStatement("DELETE FROM users, addresses WHERE user_id=? AND users.add_id=addresses.add_id");
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
