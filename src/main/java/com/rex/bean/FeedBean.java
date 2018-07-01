package com.rex.bean;

public class FeedBean {
	int fid;
	int uid;
	String name;
	String email;
	String mobile;
	String feedType;
	String message;
	int rating;

	public FeedBean(int fid, int uid, String name, String email, String mobile, String feedType, String message,
			int rating) {
		super();
		this.fid = fid;
		this.uid = uid;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.feedType = feedType;
		this.message = message;
		this.rating = rating;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
