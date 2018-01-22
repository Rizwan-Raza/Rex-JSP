package com.rex.bean;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable, ResponseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uid;
	private String fname;
	private String lname;
	@SuppressWarnings("unused")
	private String fullname;
	private String email;
	private String password;
	private String gender;
	private String contact;
	private String auth;
	private String src;
	private Date time;
	private AddressBean address;

	public UserBean() {
		this.uid = this.fullname = this.fname = this.lname = this.email = this.password = this.gender = this.contact = this.auth = this.src = null;
		this.address = null;
		this.time = null;
	}

	public UserBean(String uid, String fname, String lname, String email, String psw, String gender, String cont,
			String auth, String src, Date time, AddressBean address) {
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

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public String getFullname() {
		return getFname() + " " + getLname();
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
}
