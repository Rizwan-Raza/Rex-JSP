package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	private PreparedStatement gpg = null;
	private PreparedStatement gpb = null;
	private PreparedStatement gpm = null;
	private PreparedStatement gpa = null;
	private PreparedStatement gpi = null;
	private PreparedStatement gvw = null;
	private PreparedStatement prl = null;
	private PreparedStatement pru = null;
	private PreparedStatement prr = null;

	private ResultSet rs = null;
	private ResultSet rs_temp = null;

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
			gpg = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id ORDER BY properties.time DESC");
			gpb = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id AND users.user_id!=? ORDER BY properties.time DESC");
			gpm = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id AND users.user_id=? ORDER BY properties.time DESC");
			gpa = conn.prepareStatement("SELECT * FROM property_amenities WHERE pid=?");
			gpi = conn.prepareStatement("SELECT * FROM property_images WHERE pid=?");
			gvw = conn.prepareStatement("SELECT firstname FROM users WHERE email=?");
			prl = conn.prepareStatement("INSERT INTO wishlist(pid, cid) VALUES(?, ?)");
			pru = conn.prepareStatement("DELETE FROM wishlist WHERE wid=?");
			prr = conn.prepareStatement(
					"INSERT INTO post_requirement (cid, type, city, state, bhk, bath, area, budget) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	public ResponseBean auth(LoginBean user) {
		try { // 1
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
		} catch (SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-L-A-1", e.toString(), this.getClass().toGenericString());
		} catch (NullPointerException e) {
			e.printStackTrace();
			return new ErrorBean("C-L-A-3", e.toString(), this.getClass().toGenericString());
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-S-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<PropBean> getProps(int uid, String type) {
		try {
			PreparedStatement gpd;
			if (type == "BUY") {
				gpd = gpb;
			} else if (type == "MY") {
				gpd = gpm;
			} else {
				gpd = gpg;
			}
			if (type != "ALL")
				gpd.setInt(1, uid);
			ResultSet rs = gpd.executeQuery();
			ArrayList<PropBean> al = new ArrayList<PropBean>();
			while (rs.next()) {
				int pid = rs.getInt("properties.pid");
				gpa.setInt(1, pid);
				rs_temp = gpa.executeQuery();
				List<String> amens = new ArrayList<String>();
				while (rs_temp.next()) {
					amens.add(rs_temp.getString("amenity"));
				}
				gpi.setInt(1, pid);
				rs_temp = gpi.executeQuery();
				List<String> images = new ArrayList<String>();
				while (rs_temp.next()) {
					images.add(rs_temp.getString("src"));
				}
				UserBean seller = new UserBean(rs.getInt("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), null, rs.getString("users.gender"),
						rs.getString("users.contact"), rs.getInt("users.auth"), rs.getString("users.src"),
						rs.getDate("users.time"), null);
				AddressBean address = new AddressBean(rs.getInt("addresses.add_id"),
						rs.getString("addresses.street_no"), rs.getString("addresses.town"),
						rs.getString("addresses.city"), rs.getString("addresses.state"));
				PropBean prop = new PropBean(seller, pid, rs.getString("properties.type"),
						rs.getString("properties.t_type"), rs.getString("properties.title"),
						rs.getInt("properties.bhk"), rs.getInt("properties.bathrooms"), rs.getInt("properties.age"),
						rs.getInt("properties.furnished"), rs.getInt("properties.area"), rs.getInt("properties.l_area"),
						rs.getInt("properties.price"), rs.getInt("properties.d_price"),
						rs.getInt("properties.availability"), amens, images, rs.getInt("properties.hospital"),
						rs.getInt("properties.school"), rs.getInt("properties.rail"), rs.getInt("properties.units"),
						rs.getInt("properties.floor"), rs.getInt("properties.t_floors"),
						rs.getString("properties.b_desc"), rs.getString("properties.tnc"),
						rs.getTimestamp("properties.time"), rs.getTimestamp("properties.edit"), address);
				al.add(prop);
			}
			return al;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getNameViaEmail(String email) {
		try {
			gvw.setString(1, email);
			rs = gvw.executeQuery();
			rs.next();

			return rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return "User";
		}
	}

	public ResponseBean like(String pid, String uid) {
		try {
			prl.setString(1, pid);
			prl.setString(2, uid);
			prl.executeUpdate();
			return new SuccessBean("C-P-L-1", "Property Added to Wishlist", "prop-like", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-L-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean like(String wid) {
		try {
			pru.setString(1, wid);
			pru.executeUpdate();

			return new SuccessBean("C-P-U-1", "Property Removed from Wishlist", "prop-unlike", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean postRequirement(ReqProp prop) {
		try {
			prr.setInt(1, prop.getCid());
			prr.setString(2, prop.getType());
			prr.setString(3, prop.getCity());
			prr.setString(4, prop.getState());
			prr.setInt(5, prop.getBhk());
			prr.setInt(6, prop.getBath());
			prr.setString(7, prop.getArea());
			prr.setString(8, prop.getBudget());
			prr.executeUpdate();

			return new SuccessBean("C-P-U-1", "Property Removed from Wishlist", "prop-unlike", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}
}
