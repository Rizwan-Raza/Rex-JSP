package com.rex.bean;

public class PropBean {
	private String propID;
	private String propType;
	private String tranType;
	private String title;
	private String street;
	private String town;
	private String city;
	private String state;
	private String bhk;
	private String bath;
	private String age;
	private String furnished;
	private String propArea;
	private String land;
	private String price;
	private String priceDisplay;
	private String available;
	private String amens;
	private String hospital;
	private String school;
	private String rail;
	private String units;
	private String floor;
	private String totalFloors;
	private String desc;
	private String tnc;

	public PropBean() {
		this.propID = this.propType = this.tranType = this.title = this.street = this.town = this.city = this.state = this.bhk = this.bath =this.age = this.furnished = this.propArea = this.land = this.price = this.priceDisplay = this.available = this.amens = this.hospital = this.school = this.rail = this.units = this.floor = this.totalFloors = this.desc = this.tnc = null;
	}

	public PropBean(String propID, String propType, String tranType, String title, String street, String town,
			String city, String state, String bhk, String bath, String age, String furnished, String propArea,
			String land, String price, String priceDisplay, String available, String amens, String hospital,
			String school, String rail, String units, String floor, String totalFloors, String desc, String tnc) {
		this.propID = propID;
		this.propType = propType;
		this.tranType = tranType;
		this.title = title;
		this.street = street;
		this.town = town;
		this.city = city;
		this.state = state;
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
	}

	public PropBean(String propType, String tranType, String title, String street, String town, String city,
			String state, String bhk, String bath, String age, String furnished, String propArea, String land,
			String price, String priceDisplay, String available, String amens, String hospital, String school,
			String rail, String units, String floor, String totalFloors, String desc, String tnc) {
		super();
		this.propType = propType;
		this.tranType = tranType;
		this.title = title;
		this.street = street;
		this.town = town;
		this.city = city;
		this.state = state;
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

	public String getAmens() {
		return amens;
	}

	public void setAmens(String amens) {
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
}
