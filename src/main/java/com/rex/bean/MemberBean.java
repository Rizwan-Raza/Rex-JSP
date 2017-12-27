package com.rex.bean;

public class MemberBean {
	private AddressBean address;
	private UserBean user;

	public MemberBean() {
		setUser(new UserBean());
		setAddress(new AddressBean());
	}

	public MemberBean(String uid, String fname, String lname, String email, String psw, String gender, String cont,
			String street, String town, String city, String state, String auth, String src, String time) {
		setUser(new UserBean(uid, fname, lname, email, psw, gender, cont, auth, src, time));
		setAddress(new AddressBean(street, town, city, state));
	}

	public MemberBean(UserBean user, AddressBean address) {
		setUser(user);
		setAddress(address);
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

}
