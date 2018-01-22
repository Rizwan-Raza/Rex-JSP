package com.rex.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rex.bean.AddressBean;
import com.rex.bean.ErrorBean;
import com.rex.bean.ResponseBean;
import com.rex.bean.SuccessBean;
import com.rex.util.DBConnector;

public class CommonModel {
	private Connection conn = null;

	private PreparedStatement cadd;

	public CommonModel() {
		conn = (new DBConnector()).getConnection();
		try {
			cadd = conn.prepareStatement(
					"UPDATE addresses SET addresses.street_no=?, addresses.town=?, addresses.city=?, addresses.state=? WHERE addresses.add_id=?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ResponseBean change(AddressBean address) {
		try {
			cadd.setString(1, address.getStreet());
			cadd.setString(2, address.getTown());
			cadd.setString(3, address.getCity());
			cadd.setString(4, address.getState());
			cadd.setString(5, address.getAdd_id());

			if (cadd.executeUpdate() == 1) {
				return new SuccessBean("U-C-A-1", "Address Changed Successfully", "change-address", "success");
			} else {
				return new ErrorBean("U-C-A-1", "Can't Change Address, try again", this.getClass().toGenericString());
			}
		} catch (SQLException e) {
			return new ErrorBean("U-C-A-1", e.getMessage(), this.getClass().toGenericString());
		}
	}
}
