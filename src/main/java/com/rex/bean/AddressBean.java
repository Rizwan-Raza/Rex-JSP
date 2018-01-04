package com.rex.bean;

import java.io.Serializable;

public class AddressBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4516125172897035140L;
	private String add_id;
	private String street;
	private String town;
	private String city;
	private String state;

	public AddressBean() {
		add_id = street = town = city = state = null;
	}

	public AddressBean(String add_id, String street, String town, String city, String state) {
		setAdd_id(add_id);
		setStreet(street);
		setTown(town);
		setCity(city);
		setState(state);
	}

	public String getAdd_id() {
		return add_id;
	}

	public void setAdd_id(String add_id) {
		this.add_id = add_id;
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

}
