package com.rex.bean;

import java.io.Serializable;

public class ErrorBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String occurAt;

	private String message;

	public ErrorBean() {
		message = null;
	}

	public ErrorBean(String message) {
		setMessage(message);
	}

	public ErrorBean(String message, String occurAt) {
		setMessage(message);
		setOccurAt(occurAt);
	}

	public ErrorBean(String code, String message, String occurAt) {
		setCode(code);
		setMessage(message);
		setOccurAt(occurAt);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOccurAt() {
		return occurAt;
	}

	public void setOccurAt(String occurAt) {
		this.occurAt = occurAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
