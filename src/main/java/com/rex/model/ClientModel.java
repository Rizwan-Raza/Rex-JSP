package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.rex.bean.AddressBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.LoginBean;
import com.rex.bean.PropBean;
import com.rex.bean.ReqProp;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class ClientModel {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private PreparedStatement upd = null;
	private PreparedStatement add = null;
	private PreparedStatement pro = null;
	private PreparedStatement amn = null;
	private PreparedStatement img = null;
	private PreparedStatement gvw = null;
	private PreparedStatement prl = null;
	private PreparedStatement pru = null;
	private PreparedStatement prr = null;
	private PreparedStatement grp = null;
	private PreparedStatement gor = null;

	private ResultSet rs = null;

	public ClientModel() {
		conn = (new DBConnector()).getConnection();
		try {
			stmt = conn.prepareStatement(
					"SELECT * FROM users, addresses WHERE users.email=? AND users.password=? AND addresses.add_id=users.add_id");
			upd = conn.prepareStatement(
					"UPDATE users, addresses SET users.firstname=?, users.lastname=?, users.gender=?, users.contact=?, addresses.street_no=?, addresses.town=?, addresses.city=?, addresses.state=? WHERE users.user_id=? AND users.add_id=addresses.add_id");
			add = conn.prepareStatement("INSERT INTO addresses(street_no, town, city, state) VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			pro = conn.prepareStatement(
					"INSERT INTO properties(add_id, sid, type, t_type, title, bhk, bathrooms, age, furnished, area, l_area, price, d_price, tnc, b_desc, hospital, school, rail, units, floor, t_floors) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			amn = conn.prepareStatement("INSERT INTO property_amenities(pid, amenity) VALUES(?, ?)");
			img = conn.prepareStatement("INSERT INTO property_images(pid, src) VALUES(?, ?)");
			gvw = conn.prepareStatement("SELECT firstname FROM users WHERE email=?");
			prl = conn.prepareStatement("INSERT INTO wishlist(pid, cid) VALUES(?, ?)");
			pru = conn.prepareStatement("DELETE FROM wishlist WHERE pid=? AND cid=?");
			prr = conn.prepareStatement(
					"INSERT INTO post_requirement (cid, type, city, state, bhk, bath, area, budget) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			grp = conn.prepareStatement("SELECT * FROM post_requirement WHERE cid=?");
			gor = conn.prepareStatement(
					"SELECT * FROM post_requirement, users, addresses WHERE users.user_id=post_requirement.cid AND addresses.add_id=users.add_id AND post_requirement.cid!=?");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Object auth(LoginBean user) {
		try {
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.next()) {
					return new ErrorBean("C-L-A-3", "Email exist more than once", this.getClass().toGenericString());
				} else {
					rs.previous();
				}
				if (rs.getString("auth").equals("0")) {
					return new ErrorBean("C-L-A-2", "Not Activated Yet!", this.getClass().toGenericString());
				}
				return new UserBean(rs.getInt("user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getInt("users.auth"),
						rs.getString("users.src"), rs.getDate("users.time"),
						new AddressBean(rs.getInt("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.town"), rs.getString("addresses.city"),
								rs.getString("addresses.state")));
			} else {
				return new ErrorBean("C-L-A-1", "Incorrect Password!", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-L-A-1", e.toString(), this.getClass().toGenericString());
		}
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
				return new SuccessBean("C-D-U-1", "Profile Updated Successfully", "client-update", "success");
			} else {
				return new SuccessBean("C-D-U-2", "User Existance not Found (" + mem.getUid() + ")", "client-update",
						"failed");
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-D-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean sell(PropBean prop) {
		try {
			add.setString(1, prop.getAddress().getStreet());
			add.setString(2, prop.getAddress().getTown());
			add.setString(3, prop.getAddress().getCity());
			add.setString(4, prop.getAddress().getState());
			add.executeUpdate();
			rs = add.getGeneratedKeys();
			rs.next();

			pro.setInt(1, rs.getInt(1));
			pro.setInt(2, prop.getSeller().getUid());
			pro.setString(3, prop.getPropType());
			pro.setString(4, prop.getTranType());
			pro.setString(5, prop.getTitle());
			pro.setInt(6, prop.getBhk());
			pro.setInt(7, prop.getBath());
			pro.setInt(8, prop.getAge());
			pro.setInt(9, prop.getFurnished());
			pro.setInt(10, prop.getPropArea());
			pro.setInt(11, prop.getLand());
			pro.setInt(12, prop.getPrice());
			pro.setInt(13, prop.getPriceDisplay());
			pro.setString(14, prop.getTnc());
			pro.setString(15, prop.getDesc());
			pro.setInt(16, prop.getHospital());
			pro.setInt(17, prop.getSchool());
			pro.setInt(18, prop.getRail());
			pro.setInt(19, prop.getUnits());
			pro.setInt(20, prop.getFloor());
			pro.setInt(21, prop.getTotalFloors());
			pro.executeUpdate();
			rs = pro.getGeneratedKeys();
			rs.next();
			String pid = rs.getString(1);
			for (String amenity : prop.getAmens()) {
				amn.setString(1, pid);
				amn.setString(2, amenity);
				amn.executeUpdate();
			}
			for (String image : prop.getImages()) {
				img.setString(1, pid);
				img.setString(2, image);
				img.executeUpdate();
			}

			return new SuccessBean("C-P-S-1", "Property Added Successfully", "client-prop-sell", "success");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-S-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public String getNameViaEmail(String email) {
		try {
			gvw.setString(1, email);
			rs = gvw.executeQuery();
			rs.next();

			return rs.getString(1);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return "User";
		}
	}

	public ResponseBean like(int pid, int uid) {
		try {
			prl.setInt(1, pid);
			prl.setInt(2, uid);
			prl.executeUpdate();
			return new SuccessBean("C-P-L-1", "Property Added to Wishlist", "prop-like", "success");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-L-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean unlike(int pid, int uid) {
		try {
			pru.setInt(1, pid);
			pru.setInt(2, uid);
			pru.executeUpdate();

			return new SuccessBean("C-P-U-1", "Property Removed from Wishlist", "prop-unlike", "success");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean postRequirement(ReqProp prop) {
		try {
			prr.setInt(1, prop.getClient().getUid());
			prr.setString(2, prop.getType());
			prr.setString(3, prop.getCity());
			prr.setString(4, prop.getState());
			prr.setInt(5, prop.getBhk());
			prr.setInt(6, prop.getBath());
			prr.setString(7, prop.getArea());
			prr.setString(8, prop.getBudget());
			prr.executeUpdate();

			return new SuccessBean("C-P-U-1", "Property Request Added", "prop-req", "success");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-R-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<ReqProp> getReqProps(int uid) {
		try {
			grp.setInt(1, uid);
			rs = grp.executeQuery();
			ArrayList<ReqProp> al = new ArrayList<ReqProp>();
			while (rs.next()) {
				ReqProp rp = new ReqProp(rs.getInt("pr_id"), null, rs.getString("type"), rs.getString("city"),
						rs.getString("state"), rs.getInt("bhk"), rs.getInt("bath"), rs.getString("area"),
						rs.getString("budget"), rs.getTimestamp("time"), rs.getTimestamp("edit"));
				// System.out.println(rs.getTimestamp("time"));
				al.add(rp);
			}
			return al;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<ReqProp> getOReqProps(int uid) {
		try {
			gor.setInt(1, uid);
			rs = gor.executeQuery();
			ArrayList<ReqProp> al = new ArrayList<ReqProp>();
			while (rs.next()) {
				ReqProp rp = new ReqProp(rs.getInt("pr_id"),
						new UserBean(rs.getInt("user_id"), rs.getString("firstname"), rs.getString("lastname"),
								rs.getString("email"), rs.getString("password"), rs.getString("gender"),
								rs.getString("contact"), rs.getInt("auth"), rs.getString("src"),
								rs.getTimestamp("time"),
								new AddressBean(rs.getInt("add_id"), rs.getString("street_no"), rs.getString("town"),
										rs.getString("city"), rs.getString("state"))),
						rs.getString("type"), rs.getString("city"), rs.getString("state"), rs.getInt("bhk"),
						rs.getInt("bath"), rs.getString("area"), rs.getString("budget"), rs.getTimestamp("time"),
						rs.getTimestamp("edit"));
				// System.out.println(rs.getTimestamp("time"));
				al.add(rp);
			}
			return al;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
