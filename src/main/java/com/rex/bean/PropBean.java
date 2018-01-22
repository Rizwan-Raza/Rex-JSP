package com.rex.bean;

import java.util.Date;
import java.util.List;

public class PropBean {
	private String propID;
	private String propType;
	private String tranType;
	private String title;
	private String bhk;
	private String bath;
	private String age;
	private String furnished;
	private String propArea;
	private String land;
	private String price;
	private String priceDisplay;
	private String available;
	private List<String> amens;
	private List<String> images;
	private String hospital;
	private String school;
	private String rail;
	private String units;
	private String floor;
	private String totalFloors;
	private String desc;
	private String tnc;
	private Date time;
	private Date edit;
	private AddressBean address;
	private UserBean seller;

	public PropBean() {
		this.propID = this.propType = this.tranType = this.title = this.bhk = this.bath = this.age = this.furnished = this.propArea = this.land = this.price = this.priceDisplay = this.available = this.hospital = this.school = this.rail = this.units = this.floor = this.totalFloors = this.desc = this.tnc = null;
		this.amens = this.images = null;
		this.address = null;
		this.time = this.edit = null;
	}

	public PropBean(UserBean seller, String propID, String propType, String tranType, String title, String bhk,
			String bath, String age, String furnished, String propArea, String land, String price, String priceDisplay,
			String available, List<String> amens, String hospital, String school, String rail, String units,
			String floor, String totalFloors, String desc, String tnc, AddressBean address, Date time, Date edit,
			List<String> images) {
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
		this.hospital = hospital;
		this.school = school;
		this.rail = rail;
		this.units = units;
		this.floor = floor;
		this.totalFloors = totalFloors;
		this.desc = desc;
		this.tnc = tnc;
		this.address = address;
		this.time = time;
		this.edit = edit;
		this.images = images;
	}

	public String getPropID() {
		return propID;
	}

	public void setPropID(String propID) {
		this.propID = propID;
	}

	public String getPropType() {
		return propType;
	}

	public void setPropType(String propType) {
		this.propType = propType;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBhk() {
		return bhk;
	}

	public void setBhk(String bhk) {
		this.bhk = bhk;
	}

	public String getBath() {
		return bath;
	}

	public void setBath(String bath) {
		this.bath = bath;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFurnished() {
		return furnished;
	}

	public void setFurnished(String furnished) {
		this.furnished = furnished;
	}

	public String getPropArea() {
		return propArea;
	}

	public void setPropArea(String propArea) {
		this.propArea = propArea;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceDisplay() {
		return priceDisplay;
	}

	public void setPriceDisplay(String priceDisplay) {
		this.priceDisplay = priceDisplay;
	}

	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public List<String> getAmens() {
		return amens;
	}

	public void setAmens(List<String> amens) {
		this.amens = amens;
	}

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getRail() {
		return rail;
	}

	public void setRail(String rail) {
		this.rail = rail;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(String totalFloors) {
		this.totalFloors = totalFloors;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getEdit() {
		return edit;
	}

	public void setEdit(Date edit) {
		this.edit = edit;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTnc() {
		return tnc;
	}

	public void setTnc(String tnc) {
		this.tnc = tnc;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public UserBean getSeller() {
		return seller;
	}

	public void setSeller(UserBean seller) {
		this.seller = seller;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

}
