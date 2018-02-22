package com.rex.bean;

import java.io.Serializable;

public class SuccessBean implements Serializable, ResponseBean {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String message;
	private String process;
	private String completion;
	private String query;
	private String session;
	private String cleanUp;

	public SuccessBean(String code, String message) {
		setCode(code);
		setMessage(message);
	}

	public SuccessBean(String code, String message, String process, String completion) {
		setCode(code);
		setMessage(message);
		setProcess(process);
		setCompletion(completion);
	}

	public SuccessBean(String code, String message, String process, String completion, String query, String session,
			String cleanUp) {
		setCode(code);
		setMessage(message);
		setProcess(process);
		setCompletion(completion);
		setQuery(query);
		setSession(session);
		setCleanUp(cleanUp);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getCleanUp() {
		return cleanUp;
	}

	public void setCleanUp(String cleanUp) {
		this.cleanUp = cleanUp;
	}
}
