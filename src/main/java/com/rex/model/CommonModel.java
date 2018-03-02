package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rex.bean.AddressBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.PropBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.bean.UserBean;
import com.rex.util.DBConnector;

public class CommonModel {
	private Connection conn = null;

	private PreparedStatement cadd;
	private PreparedStatement cprf;
	private PreparedStatement cpri;
	private PreparedStatement cpir;
	private PreparedStatement cpia;
	private PreparedStatement pupd;
	private PreparedStatement pdel;
	private PreparedStatement amni = null;
	private PreparedStatement amnd = null;
	private PreparedStatement gpg = null;
	private PreparedStatement gpb = null;
	private PreparedStatement gpm = null;
	private PreparedStatement gph = null;
	private PreparedStatement gpa = null;
	private PreparedStatement gpi = null;
	private PreparedStatement gps = null;

	private ResultSet rs_temp = null;

	public CommonModel() {
		conn = (new DBConnector()).getConnection();
		try {
			cadd = conn.prepareStatement(
					"UPDATE addresses SET addresses.street_no=?, addresses.town=?, addresses.city=?, addresses.state=? WHERE addresses.add_id=?");
			cprf = conn.prepareStatement(
					"UPDATE properties SET bhk=?, bathrooms=?, age=?, furnished=?, hospital=?, school=?, rail=?, area=?, l_area=?, edit=CURRENT_TIMESTAMP WHERE pid=?");
			cpri = conn.prepareStatement(
					"UPDATE properties SET units=?, floor=?, t_floors=?, b_desc=?, tnc=?, edit=CURRENT_TIMESTAMP WHERE pid=?");
			cpir = conn.prepareStatement("DELETE FROM property_images WHERE src=? AND pid=?");
			cpia = conn.prepareStatement("INSERT INTO property_images(pid, src) VALUES(?, ?)");
			pupd = conn.prepareStatement(
					"UPDATE properties SET type=?, t_type=?, title=?, price=?, d_price=?, availability=?, edit=CURRENT_TIMESTAMP WHERE pid=?");
			pdel = conn.prepareStatement(
					"DELETE properties, addresses, property_images, property_amenities FROM properties INNER JOIN addresses ON properties.add_id=addresses.add_id INNER JOIN property_images ON properties.pid=property_images.pid INNER JOIN property_amenities ON properties.pid=property_amenities.pid WHERE properties.pid=?");
			amni = conn.prepareStatement("INSERT INTO property_amenities(pid, amenity) VALUES(?, ?)");
			amnd = conn.prepareStatement("DELETE FROM property_amenities WHERE pid=?");
			gpg = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id ORDER BY properties.time DESC");
			gps = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id AND properties.pid=?");
			gpb = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id AND users.user_id!=? ORDER BY properties.time DESC");
			gpm = conn.prepareStatement(
					"SELECT * FROM properties, users, addresses WHERE properties.add_id=addresses.add_id AND properties.sid=users.user_id AND users.user_id=? ORDER BY properties.time DESC");
			gph = conn.prepareStatement(
					"SELECT properties.pid, properties.title, properties.bhk, properties.price FROM properties LIMIT 4");
			gpa = conn.prepareStatement("SELECT * FROM property_amenities WHERE pid=?");
			gpi = conn.prepareStatement("SELECT * FROM property_images WHERE pid=?");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ResponseBean changeAddress(AddressBean address) {
		try {
			cadd.setString(1, address.getStreet());
			cadd.setString(2, address.getTown());
			cadd.setString(3, address.getCity());
			cadd.setString(4, address.getState());
			cadd.setInt(5, address.getAdd_id());

			if (cadd.executeUpdate() == 1) {
				return new SuccessBean("U-C-A-1", "Address Changed Successfully", "change-address", "success");
			} else {
				return new ErrorBean("U-C-A-1", "Can't Change Address, try again", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("U-C-A-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean changeFeatures(PropBean prop) {
		try {
			cprf.setInt(1, prop.getBhk());
			cprf.setInt(2, prop.getBath());
			cprf.setInt(3, prop.getAge());
			cprf.setInt(4, prop.getFurnished());
			cprf.setInt(5, prop.getHospital());
			cprf.setInt(6, prop.getSchool());
			cprf.setInt(7, prop.getRail());
			cprf.setInt(8, prop.getPropArea());
			cprf.setInt(9, prop.getLand());
			cprf.setInt(10, prop.getPropID());

			if (cprf.executeUpdate() == 1) {
				return new SuccessBean("C-P-F-1", "Features Changed Successfully", "change-features", "success");
			} else {
				return new ErrorBean("C-P-F-1", "Can't Change Features, try again", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-F-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean changeInfo(PropBean prop) {
		try {
			cpri.setInt(1, prop.getUnits());
			cpri.setInt(2, prop.getFloor());
			cpri.setInt(3, prop.getTotalFloors());
			cpri.setString(4, prop.getDesc());
			cpri.setString(5, prop.getTnc());
			cpri.setInt(6, prop.getPropID());

			if (cpri.executeUpdate() == 1) {
				amnd.setInt(1, prop.getPropID());
				amnd.executeUpdate();
				for (String amenity : prop.getAmens()) {
					amni.setInt(1, prop.getPropID());
					amni.setString(2, amenity);
					amni.executeUpdate();
				}
				return new SuccessBean("C-P-I-1", "Informations Changed Successfully", "change-info", "success");
			} else {
				return new ErrorBean("C-P-I-1", "Can't Change Informations, try again",
						this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("C-P-I-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean addImage(int pid, String src) {
		try {
			cpia.setInt(1, pid);
			cpia.setString(2, src);

			if (cpia.executeUpdate() == 1) {
				return new SuccessBean("P-A-I-1", "Image Added Successfully", "add-image", "success");
			} else {
				return new ErrorBean("P-A-I-1", "Can't Add Image, try again", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("P-A-I-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean removeImage(int pid, String src) {
		try {
			cpir.setString(1, src);
			cpir.setInt(2, pid);
			if (cpir.executeUpdate() == 1) {
				return new SuccessBean("P-R-I-1", "Image Removed Successfully", "remove-image", "success");
			} else {
				return new ErrorBean("P-R-I-2", "Can't Remove Image, try again", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("P-R-I-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean edit(PropBean prop) {
		try {
			pupd.setString(1, prop.getPropType());
			pupd.setString(2, prop.getTranType());
			pupd.setString(3, prop.getTitle());
			pupd.setInt(4, prop.getPrice());
			pupd.setInt(5, prop.getPriceDisplay());
			pupd.setInt(6, prop.getAvailable());
			pupd.setInt(7, prop.getPropID());
			if (pupd.executeUpdate() == 1) {
				return new SuccessBean("U-P-E-1", "Property Edited Successfully", "prop-edit", "success");
			} else {
				return new ErrorBean("U-P-E-2", "Can't Edit Property, try again", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("U-P-E-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ResponseBean delete(int pid) {
		try {
			pdel.setInt(1, pid);
			if (pdel.executeUpdate() != 0) {
				return new SuccessBean("U-P-D-1", "Property Deleted Successfully", "prop-delete", "success");
			} else {
				return new ErrorBean("U-P-D-2", "Can't Delet Property, try again", this.getClass().toGenericString());
			}
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
			return new ErrorBean("U-P-D-1", e.getMessage(), this.getClass().toGenericString());
		}
	}

	public ArrayList<PropBean> getProps(int uid, String type) {
		try {
			PreparedStatement gpd;
			if (type == "BUY") {
				gpd = gpb;
			} else if (type == "MY") {
				gpd = gpm;
			} else if (type == "SINGLE") {
				gpd = gps;
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
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<PropBean> getProps() {
		try {
			ResultSet rs = gph.executeQuery();
			ArrayList<PropBean> al = new ArrayList<PropBean>();
			while (rs.next()) {
				int pid = rs.getInt("properties.pid");
				gpi.setInt(1, pid);
				rs_temp = gpi.executeQuery();
				List<String> images = new ArrayList<String>();
				while (rs_temp.next()) {
					images.add(rs_temp.getString("src"));
				}
				PropBean prop = new PropBean(null, pid, null, null, rs.getString("properties.title"),
						rs.getInt("properties.bhk"), 0, 0, 0, 0, 0, rs.getInt("properties.price"), 0, 0, null, images,
						0, 0, 0, 0, 0, 0, null, null, null, null, null);
				al.add(prop);
			}
			return al;
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
