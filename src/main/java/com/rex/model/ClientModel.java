package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.rex.bean.AddressBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.MemberBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class ClientModel {
	private Connection conn;
	private PreparedStatement stmt;
	private PreparedStatement upd;

	public ClientModel() {
		conn = (new DBConnector()).getConnection();
		try {
			stmt = conn.prepareStatement(
					"SELECT * FROM users, addresses WHERE users.email=? AND users.password=? AND addresses.add_id=users.add_id");
			upd = conn.prepareStatement(
					"UPDATE users, addresses SET users.firstname=?, users.lastname=?, users.gender=?, users.contact=?, addresses.street_no=?, addresses.town=?, addresses.city=?, addresses.state=? WHERE users.user_id=? AND users.add_id=addresses.add_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Activation
	}

	public Object auth(LoginBean user) {
		try { // 1
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.next()) {
					return new ErrorBean("C-A-3", "Email exist more than once", this.getClass().toGenericString());
				} else {
					rs.previous();
				}
				if (rs.getString("auth").equals("0")) {
					return new ErrorBean("C-A-2", "Not Activated Yet!", this.getClass().toGenericString());
				}
				return new MemberBean(new UserBean(rs.getString("user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getString("users.auth"),
						rs.getString("users.src"), rs.getString("users.time")),
						new AddressBean(rs.getString("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.town"), rs.getString("addresses.city"),
								rs.getString("addresses.state")));
			} else {
				return new ErrorBean("C-A-1", "Incorrect Password!", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("C-A-1", e.toString(), this.getClass().toGenericString());
		}
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
				return new SuccessBean("A-U-1", "Profile Updated Successfully", "client-update", "success");
			} else {
				return new SuccessBean("A-U-1", "User Existance not Found (" + mem.getUser().getUid() + ")",
						"client-update", "failed");
			}
		} catch (SQLException e) {
			return new ErrorBean("A-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

}
