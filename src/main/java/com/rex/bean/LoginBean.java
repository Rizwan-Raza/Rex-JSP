package com.rex.bean;

public class LoginBean {
	private String username;
	private String password;

	public LoginBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
