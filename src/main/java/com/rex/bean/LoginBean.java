package com.rex.bean;

public class LoginBean {
	private String username;
	private String password;

	public LoginBean() {
		username = null;
		password = null;
	}

	public LoginBean(String username, String password) {
		setUsername(username);
		setPassword(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
