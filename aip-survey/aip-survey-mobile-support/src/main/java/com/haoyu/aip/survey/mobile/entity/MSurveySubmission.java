package com.haoyu.aip.survey.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.core.entity.User;

public class MSurveySubmission implements Serializable{

	private static final long serialVersionUID = 3388226201045147531L;
	
	private String response;
	
	private User user;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
