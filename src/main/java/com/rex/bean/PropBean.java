package com.rex.bean;

import java.util.Date;
import java.util.List;

public class PropBean {
	private UserBean seller;
	private int propID;
	private String propType;
	private String tranType;
	private String title;
	private int bhk;
	private int bath;
	private int age;
	private int furnished;
	private int propArea;
	private int land;
	private int price;
	private int priceDisplay;
	private int available;
	private List<String> amens;
	private List<String> images;
	private int hospital;
	private int school;
	private int rail;
	private int units;
	private int floor;
	private int totalFloors;
	private String desc;
	private String tnc;
	private Date time;
	private Date edit;
	private AddressBean address;

	private List<UserBean> likers;

	public PropBean(UserBean seller, int propID, String propType, String tranType, String title, int bhk, int bath,
			int age, int furnished, int propArea, int land, int price, int priceDisplay, int available,
			List<String> amens, List<String> images, int hospital, int school, int rail, int units, int floor,
			int totalFloors, String desc, String tnc, Date time, Date edit, AddressBean address,
			List<UserBean> likers) {
		super();
		this.seller = seller;
		this.propID = propID;
		this.propType = propType;
		this.tranType = tranType;
		this.title = title;
		this.bhk = bhk;
		this.bath = bath;
		this.age = age;
		this.furnished = furnished;
		this.propArea = propArea;
		this.land = land;
		this.price = price;
		this.priceDisplay = priceDisplay;
		this.available = available;
		this.amens = amens;
		this.images = images;
		this.hospital = hospital;
		this.school = school;
		this.rail = rail;
		this.units = units;
		this.floor = floor;
		this.totalFloors = totalFloors;
		this.desc = desc;
		this.tnc = tnc;
		this.time = time;
		this.edit = edit;
		this.address = address;
		this.likers = likers;
	}

	public UserBean getSeller() {
		return seller;
	}

	public int getPropID() {
		return propID;
	}

	public String getPropType() {
		return propType;
	}

	public String getTranType() {
		return tranType;
	}

	public String getTitle() {
		return title;
	}

	public int getBhk() {
		return bhk;
	}

	public int getBath() {
		return bath;
	}

	public int getAge() {
		return age;
	}

	public int getFurnished() {
		return furnished;
	}

	public int getPropArea() {
		return propArea;
	}

	public int getLand() {
		return land;
	}

	public int getPrice() {
		return price;
	}

	public int getPriceDisplay() {
		return priceDisplay;
	}

	public int getAvailable() {
		return available;
	}

	public List<String> getAmens() {
		return amens;
	}

	public List<String> getImages() {
		return images;
	}

	public int getHospital() {
		return hospital;
	}

	public int getSchool() {
		return school;
	}

	public int getRail() {
		return rail;
	}

	public int getUnits() {
		return units;
	}

	public int getFloor() {
		return floor;
	}

	public int getTotalFloors() {
		return totalFloors;
	}

	public String getDesc() {
		return desc;
	}

	public String getTnc() {
		return tnc;
	}

	public Date getTime() {
		return time;
	}

	public Date getEdit() {
		return edit;
	}

	public AddressBean getAddress() {
		return address;
	}

	public List<UserBean> getLikers() {
		return likers;
	}

}
