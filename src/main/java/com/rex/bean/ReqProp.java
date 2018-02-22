package com.rex.bean;

import java.util.Date;

public class ReqProp {
	int pr_id;
	int cid;
	String type;
	String city;
	String state;
	int bhk;
	int bath;
	String area;
	String budget;
	Date time;
	Date edit;
	public ReqProp(int pr_id, int cid, String type, String city, String state, int bhk, int bath, String area,
			String budget, Date time, Date edit) {
		super();
		this.pr_id = pr_id;
		this.cid = cid;
		this.type = type;
		this.city = city;
		this.state = state;
		this.bhk = bhk;
		this.bath = bath;
		this.area = area;
		this.budget = budget;
		this.time = time;
		this.edit = edit;
	}
	public int getPr_id() {
		return pr_id;
	}
	public int getCid() {
		return cid;
	}
	public String getType() {
		return type;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public int getBhk() {
		return bhk;
	}
	public int getBath() {
		return bath;
	}
	public String getArea() {
		return area;
	}
	public String getBudget() {
		return budget;
	}
	public Date getTime() {
		return time;
	}
	public Date getEdit() {
		return edit;
	}
	
}
