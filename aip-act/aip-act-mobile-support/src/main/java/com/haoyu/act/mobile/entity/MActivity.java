package com.haoyu.act.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.core.entity.TimePeriod;

public class MActivity implements Serializable{
	
	private static final long serialVersionUID = 7121771255593552717L;

	private String id;
	
	private String title;
	
	private String type;
	
	private String completeState;
	
	private TimePeriod timePeriod;
	
	private boolean inCurrentDate;
	
	public boolean isInCurrentDate() {
		return inCurrentDate;
	}

	public void setInCurrentDate(boolean inCurrentDate) {
		this.inCurrentDate = inCurrentDate;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getCompleteState() {
		return completeState;
	}

	public void setCompleteState(String completeState) {
		this.completeState = completeState;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
