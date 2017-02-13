package com.haoyu.aip.discussion.mobile.entity;

import java.io.Serializable;

public class MDiscussionRelation implements Serializable {

	private static final long serialVersionUID = 3261827987077292161L;

	private String id;
	
	private int replyNum;

	private int supportNum;
	
	private int participateNum;
	
	private int followNum;
	
	private int browseNum;
	
	public MDiscussionRelation() {
	}
	
	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}
	
}
