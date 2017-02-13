package com.haoyu.aip.video.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;

public class VideoUser extends BaseEntity{
	
	private static final long serialVersionUID = 8953998489011037462L;

	private String id;
	
	private VideoRelation videoRelation;
	
	private int viewNum;
	
	private double viewTime;
	
	private double lastViewTime;
	
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VideoRelation getVideoRelation() {
		return videoRelation;
	}

	public void setVideoRelation(VideoRelation videoRelation) {
		this.videoRelation = videoRelation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
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

	public static String getId(String videoRelationId, String userId){
		return DigestUtils.md5Hex(videoRelationId+userId);
	}
	
}
