package com.haoyu.aip.discussion.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;

public class DiscussionUser extends BaseEntity{

	private static final long serialVersionUID = -9084564034521206204L;

	private DiscussionRelation discussionRelation;
	
	private String state;

	public DiscussionRelation getDiscussionRelation() {
		return discussionRelation;
	}

	public void setDiscussionRelation(DiscussionRelation discussionRelation) {
		this.discussionRelation = discussionRelation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static String getId(String discussionRelationId, String creator){
		return DigestUtils.md5Hex(discussionRelationId+creator);
	}
	
}
