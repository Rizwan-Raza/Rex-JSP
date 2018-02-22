package com.rex.bean;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable, ResponseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uid;
	private String fname;
	private String lname;
	private String fullname;
	private String email;
	private String password;
	private String gender;
	private String contact;
	private int auth;
	private String src;
	private Date time;
	private AddressBean address;

	public UserBean(int uid, String fname, String lname, String email, String psw, String gender, String cont, int auth,
			String src, Date time, AddressBean address) {
		this.uid = uid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = psw;
		this.gender = gender;
		this.contact = cont;
		this.auth = auth;
		this.src = src;
		this.time = time;
		this.fullname = fname + " " + lname;
		this.address = address;
	}

	public int getUid() {
		return uid;
	}

	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}

	public String getFullname() {
		return fullname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getContact() {
		return contact;
	}

	public int getAuth() {
		return auth;
	}

	public String getSrc() {
		return src;
	}

	public Date getTime() {
		return time;
	}

	public AddressBean getAddress() {
		return address;
	}

}
