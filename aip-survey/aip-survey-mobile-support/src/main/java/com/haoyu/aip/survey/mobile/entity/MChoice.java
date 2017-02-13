package com.haoyu.aip.survey.mobile.entity;

import java.io.Serializable;

public class MChoice implements Serializable{
	
	private static final long serialVersionUID = 1778907566895580523L;

	private String id;
	
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
