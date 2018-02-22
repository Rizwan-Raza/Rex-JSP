package com.rex.bean;

import java.io.Serializable;

public class ErrorBean implements Serializable, ResponseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String occurAt;

	private String message;

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
