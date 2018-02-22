package com.rex.bean;

import java.io.Serializable;

public class AddressBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516125172897035140L;
	private int add_id;
	private String street;
	private String town;
	private String city;
	private String state;

	public AddressBean(int add_id, String street, String town, String city, String state) {
		super();
		this.add_id = add_id;
		this.street = street;
		this.town = town;
		this.city = city;
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getAdd_id() {
		return add_id;
	}

	public String getStreet() {
		return street;
	}

	public String getTown() {
		return town;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

}
