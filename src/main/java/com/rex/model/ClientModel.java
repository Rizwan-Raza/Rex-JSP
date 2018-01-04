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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Activation
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
				return new UserBean(rs.getString("user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), rs.getString("users.password"),
						rs.getString("users.gender"), rs.getString("users.contact"), rs.getString("users.auth"),
						rs.getString("users.src"), rs.getString("users.time"),
						new AddressBean(rs.getString("addresses.add_id"), rs.getString("addresses.street_no"),
								rs.getString("addresses.town"), rs.getString("addresses.city"),
								rs.getString("addresses.state")));
			} else {
				return new ErrorBean("C-L-A-1", "Incorrect Password!", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
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
			upd.setString(9, mem.getUid());

			if (upd.executeUpdate() == 2) {
				return new SuccessBean("C-D-U-1", "Profile Updated Successfully", "client-update", "success");
			} else {
				return new SuccessBean("C-D-U-2", "User Existance not Found (" + mem.getUid() + ")", "client-update",
						"failed");
			}
		} catch (SQLException e) {
			return new ErrorBean("C-D-U-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean sell(PropBean prop) {
		try {
			add.setString(1, prop.getAddress().getStreet());
			add.setString(2, prop.getAddress().getCity());
			add.setString(3, prop.getAddress().getTown());
			add.setString(4, prop.getAddress().getState());
			add.executeUpdate();
			rs = add.getGeneratedKeys();
			rs.next();

			pro.setString(1, rs.getString(1));
			pro.setString(2, prop.getSeller().getUid());
			pro.setString(3, prop.getPropType());
			pro.setString(4, prop.getTranType());
			pro.setString(5, prop.getTitle());
			pro.setString(6, prop.getBhk());
			pro.setString(7, prop.getBath());
			pro.setString(8, prop.getAge());
			pro.setString(9, prop.getFurnished());
			pro.setString(10, prop.getPropArea());
			pro.setString(11, prop.getLand());
			pro.setString(12, prop.getPrice());
			pro.setString(13, prop.getPriceDisplay());
			pro.setString(14, prop.getTnc());
			pro.setString(15, prop.getDesc());
			pro.setString(16, prop.getHospital());
			pro.setString(17, prop.getSchool());
			pro.setString(18, prop.getRail());
			pro.setString(19, prop.getUnits());
			pro.setString(20, prop.getFloor());
			pro.setString(21, prop.getTotalFloors());
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
			return new ErrorBean("C-P-S-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<PropBean> getProps(String uid, String type) {
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
				gpd.setString(1, uid);
			ResultSet rs = gpd.executeQuery();
			ArrayList<PropBean> al = new ArrayList<PropBean>();
			while (rs.next()) {
				String add_id = rs.getString("addresses.add_id");
				gpa.setString(1, add_id);
				rs_temp = gpa.executeQuery();
				List<String> amens = new ArrayList<String>();
				while (rs_temp.next()) {
					amens.add(rs_temp.getString("amenity"));
				}
				gpi.setString(1, add_id);
				rs_temp = gpi.executeQuery();
				List<String> images = new ArrayList<String>();
				while (rs_temp.next()) {
					images.add(rs_temp.getString("src"));
				}
				UserBean seller = new UserBean(rs.getString("users.user_id"), rs.getString("users.firstname"),
						rs.getString("users.lastname"), rs.getString("users.email"), null, rs.getString("users.gender"),
						rs.getString("users.contact"), rs.getString("users.auth"), rs.getString("users.src"),
						rs.getString("users.time"), null);
				AddressBean address = new AddressBean(add_id, rs.getString("addresses.street_no"),
						rs.getString("addresses.town"), rs.getString("addresses.city"),
						rs.getString("addresses.state"));
				PropBean prop = new PropBean(seller, null, rs.getString("properties.type"),
						rs.getString("properties.t_type"), rs.getString("properties.title"),
						rs.getString("properties.bhk"), rs.getString("properties.bathrooms"),
						rs.getString("properties.age"), rs.getString("properties.furnished"),
						rs.getString("properties.area"), rs.getString("properties.l_area"),
						rs.getString("properties.price"), rs.getString("properties.d_price"),
						rs.getString("properties.availability"), amens, rs.getString("properties.hospital"),
						rs.getString("properties.school"), rs.getString("properties.rail"),
						rs.getString("properties.units"), rs.getString("properties.floor"),
						rs.getString("properties.t_floors"), rs.getString("properties.b_desc"),
						rs.getString("properties.tnc"), address, rs.getString("properties.time"),
						rs.getString("properties.edit"), images);
				al.add(prop);
			}
			return al;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
