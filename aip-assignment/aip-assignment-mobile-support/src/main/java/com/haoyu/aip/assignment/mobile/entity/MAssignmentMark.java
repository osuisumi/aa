package com.haoyu.aip.assignment.mobile.entity;

import java.io.Serializable;
import java.util.Date;

import com.haoyu.sip.core.entity.BaseEntity;

public class MAssignmentMark implements Serializable{

	private static final long serialVersionUID = 4016174902021585987L;
	
	private String id;

	private Date responseTime;
	
	private double score;
	
	private String state;
	
	private int expiredDays;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getExpiredDays() {
		return expiredDays;
	}

	public void setExpiredDays(int expiredDays) {
		this.expiredDays = expiredDays;
	}
	
}
