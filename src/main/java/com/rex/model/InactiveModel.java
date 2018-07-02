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
import com.rex.bean.FeedBean;
import com.rex.bean.PropBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class InactiveModel {
	private Connection conn;
	private PreparedStatement addr = null;
	private PreparedStatement user = null;
	private PreparedStatement addf = null;
	private PreparedStatement getf = null;
	private PreparedStatement delf = null;
	private PreparedStatement srch = null;
	private PreparedStatement home = null;
	private PreparedStatement geti = null;
	private PreparedStatement geta = null;
	private PreparedStatement getl = null;

	private ResultSet rs = null;
	private ResultSet rs_temp = null;

	public InactiveModel() {
		conn = (new DBConnector()).getConnection();
		try {
			addf = conn.prepareStatement(
					"INSERT INTO feeds (uid, name, email, mobile, ftype, msg, rate) VALUES (?, ?, ?, ?, ?, ?, ?)");
			getf = conn.prepareStatement("SELECT * FROM feeds");
			delf = conn.prepareStatement("DELETE FROM feeds WHERE fid=?");
			srch = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id AND properties.type LIKE ? AND properties.price BETWEEN ? AND ? AND (addresses.city LIKE ? OR addresses.state LIKE ?) ORDER BY properties.time DESC");
			home = conn.prepareStatement(
					"SELECT properties.pid, properties.title, properties.bhk, properties.price FROM properties LIMIT 4");
			geti = conn.prepareStatement("SELECT * FROM property_images WHERE pid=?");
			geta = conn.prepareStatement("SELECT * FROM property_amenities WHERE pid=?");
			getl = conn.prepareStatement(
					"SELECT * FROM users, wishlist WHERE wishlist.pid=? AND wishlist.cid=users.user_id");

			addr = conn.prepareStatement("INSERT INTO addresses (street_no, town, city, state) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			user = conn.prepareStatement(
					"INSERT INTO users (firstname, lastname, email, password, gender, contact, add_id) VALUES (?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
	}

	public String add(UserBean mem) {
		if (conn == null || addr == null || user == null)
			return "Can't connect with Database";
		try {
			addr.setString(1, mem.getAddress().getStreet());
			addr.setString(2, mem.getAddress().getTown());
			addr.setString(3, mem.getAddress().getCity());
			addr.setString(4, mem.getAddress().getState());
			addr.executeUpdate();
			rs = addr.getGeneratedKeys();
			rs.next();

			user.setString(1, mem.getFname());
			user.setString(2, mem.getLname());
			user.setString(3, mem.getEmail());
			user.setString(4, mem.getPassword());
			user.setString(5, mem.getGender());
			user.setString(6, mem.getContact());
			user.setString(7, rs.getString(1));

			user.executeUpdate();
			rs = user.getGeneratedKeys();
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}

	public ResponseBean add(FeedBean fb) {
		if (conn == null || addf == null)
			return new ErrorBean("U-C-F-2", "Connection Problem!", "feed-add");
		try {
			addf.setInt(1, fb.getUid());
			addf.setString(2, fb.getName());
			addf.setString(3, fb.getEmail());
			addf.setString(4, fb.getMobile());
			addf.setString(5, fb.getFeedType());
			addf.setString(6, fb.getMessage());
			addf.setInt(7, fb.getRating());

			if (addf.executeUpdate() != 0) {
				return new SuccessBean("U-C-F-1", "Thank you, we will reply you soon", "feed-add", "success");
			} else {
				return new ErrorBean("U-C-F-2", "Sorry, try again", "feed-add");
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("U-C-F-1", e.toString(), this.getClass().toGenericString());
		}
	}

	public ArrayList<FeedBean> getFeeds() {
		try {
			rs = getf.executeQuery();
			ArrayList<FeedBean> al = new ArrayList<FeedBean>();
			while (rs.next()) {
				FeedBean fb = new FeedBean(rs.getInt("fid"), rs.getInt("uid"), rs.getString("name"),
						rs.getString("email"), rs.getString("mobile"), rs.getString("ftype"), rs.getString("msg"),
						rs.getInt("rate"));
				al.add(fb);
			}
			return al;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ResponseBean deleteFeed(int fid) {
		try {
			delf.setInt(1, fid);
			if (delf.executeUpdate() != 0) {
				return new SuccessBean("U-F-D-1", "Feed Deleted Successfully", "feed-delete", "success");
			} else {
				return new ErrorBean("U-F-D-2", "Can't Delete Feed, try again", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("U-F-D-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<PropBean> getProps() {
		try {
			rs = home.executeQuery();
			ArrayList<PropBean> al = new ArrayList<PropBean>();
			while (rs.next()) {
				int pid = rs.getInt("properties.pid");
				geti.setInt(1, pid);
				rs_temp = geti.executeQuery();
				List<String> images = new ArrayList<String>();
				while (rs_temp.next()) {
					images.add(rs_temp.getString("src"));
				}
				PropBean prop = new PropBean(null, pid, null, null, rs.getString("properties.title"),
						rs.getInt("properties.bhk"), 0, 0, 0, 0, 0, rs.getInt("properties.price"), 0, 0, null, images,
						0, 0, 0, 0, 0, 0, null, null, null, null, null, null);
				al.add(prop);
			}
			return al;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<PropBean> search(String type, int low, int high, String q) {
		try {
			srch.setString(1, type);
			srch.setInt(2, low);
			srch.setInt(3, high);
			srch.setString(4, q);
			srch.setString(5, q);
			System.out.println(srch.toString());
			rs = srch.executeQuery();
			ArrayList<PropBean> al = new ArrayList<PropBean>();
			while (rs.next()) {
				int pid = rs.getInt("properties.pid");
				geta.setInt(1, pid);
				rs_temp = geta.executeQuery();
				List<String> amens = new ArrayList<String>();
				while (rs_temp.next()) {
					amens.add(rs_temp.getString("amenity"));
				}
				geti.setInt(1, pid);
				rs_temp = geti.executeQuery();
				List<String> images = new ArrayList<String>();
				while (rs_temp.next()) {
					images.add(rs_temp.getString("src"));
				}
				getl.setInt(1, pid);
				rs_temp = getl.executeQuery();
				List<UserBean> likers = new ArrayList<UserBean>();
				while (rs_temp.next()) {
					likers.add(new UserBean(rs_temp.getInt("user_id"), rs_temp.getString("firstname"),
							rs_temp.getString("lastname"), rs_temp.getString("email"), null,
							rs_temp.getString("gender"), rs_temp.getString("contact"), 0, rs_temp.getString("src"),
							rs_temp.getDate("wishlist.time"), null));
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
						rs.getTimestamp("properties.time"), rs.getTimestamp("properties.edit"), address, likers);
				al.add(prop);
			}
			return al;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
