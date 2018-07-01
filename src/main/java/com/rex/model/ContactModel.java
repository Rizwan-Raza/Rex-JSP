package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rex.bean.ErrorBean;
import com.rex.bean.FeedBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.util.DBConnector;

public class ContactModel {
	private Connection conn;
	private PreparedStatement addf = null;
	private PreparedStatement getf = null;
	private PreparedStatement delf = null;

	public ContactModel() {
		conn = (new DBConnector()).getConnection();
		try {
			addf = conn.prepareStatement(
					"INSERT INTO feeds (uid, name, email, mobile, ftype, msg, rate) VALUES (?, ?, ?, ?, ?, ?, ?)");
			getf = conn.prepareStatement("SELECT * FROM feeds");
			delf = conn.prepareStatement("DELETE FROM feeds WHERE fid=?");
		} catch (NullPointerException | SQLException e) {
			e.printStackTrace();
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
			ResultSet rs = getf.executeQuery();
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
}
