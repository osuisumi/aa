package com.haoyu.aip.mobile.video.entity;

import java.io.Serializable;

public class MVideoUser implements Serializable{
	
	private static final long serialVersionUID = 8496265361615232728L;

	private String id;
	
	private MVideo mVideo;
	
	private double viewTime;
	
	private double lastViewTime;
	
	private boolean isTiming;

	public boolean isTiming() {
		return isTiming;
	}

	public void setTiming(boolean isTiming) {
		this.isTiming = isTiming;
	}

	public MVideo getmVideo() {
		return mVideo;
	}

	public void setmVideo(MVideo mVideo) {
		this.mVideo = mVideo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getViewTime() {
		return viewTime;
	}

	public void setViewTime(double viewTime) {
		this.viewTime = viewTime;
	}

	public double getLastViewTime() {
		return lastViewTime;
	}

	public void setLastViewTime(double lastViewTime) {
		this.lastViewTime = lastViewTime;
	}

}
