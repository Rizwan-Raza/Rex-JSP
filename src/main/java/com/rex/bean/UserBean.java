package com.rex.bean;

import java.io.Serializable;

public class UserBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uid;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private String gender;
	private String contact;
	private String street;
	private String town;
	private String city;
	private String state;
	private String auth;
	private String src;
	private String time;

	public UserBean() {
		uid = fname = lname = email = password = gender = contact = street = town = city = state = auth = src = time = null;
	}

	public UserBean(String fname, String lname, String email, String psw, String gender, String cont, String street,
			String town, String city, String state) {
		setFname(fname);
		setLname(lname);
		setEmail(email);
		setPassword(psw);
		setGender(gender);
		setContact(cont);
		setStreet(street);
		setTown(town);
		setCity(city);
		setState(state);

	}

	public UserBean(String uid, String fname, String lname, String email, String psw, String gender, String cont,
			String street, String town, String city, String state, String auth, String src, String time) {
		setUid(uid);
		setFname(fname);
		setLname(lname);
		setEmail(email);
		setPassword(psw);
		setGender(gender);
		setContact(cont);
		setStreet(street);
		setTown(town);
		setCity(city);
		setState(state);
		setAuth(auth);
		setSrc(src);
		setTime(time);

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getFullname() {
		return getFname() + " " + getLname();
	}
}
