package com.haoyu.aip.qti.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class MTest implements Serializable{
	
	private static final long serialVersionUID = 4664268581068887295L;

	private String id;
	
	private String title;
	
	private String description;
	
	private int maxAttempts;
	
	private List<MQuestion> mQuestions = Lists.newArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}

	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}

	public List<MQuestion> getmQuestions() {
		return mQuestions;
	}

	public void setmQuestions(List<MQuestion> mQuestions) {
		this.mQuestions = mQuestions;
	}

}
