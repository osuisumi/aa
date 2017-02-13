package com.haoyu.aip.discussion.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;

public class DiscussionPost extends BaseEntity {
	
	private static final long serialVersionUID = 762184443894639222L;

	private DiscussionUser discussionUser;
	
	private String targetId;

	private String mainPostId;

	private String parentId;

	private String title;

	private String content;
	
	private String isEssence = "N";
	
	private String isTop = "N";
	
	private float score = -1;	
	
	private int supportNum;
	
	private int childPostCount; 
	
	private List<DiscussionPost> childPostList = Lists.newArrayList();
	
	public DiscussionPost(String id) {
		this.setId(id);
	}

	public DiscussionPost() {
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getMainPostId() {
		return mainPostId;
	}

	public void setMainPostId(String mainPostId) {
		this.mainPostId = mainPostId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsEssence() {
		return isEssence;
	}

	public void setIsEssence(String isEssence) {
		this.isEssence = isEssence;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public int getChildPostCount() {
		return childPostCount;
	}

	public void setChildPostCount(int childPostCount) {
		this.childPostCount = childPostCount;
	}

	public List<DiscussionPost> getChildPostList() {
		return childPostList;
	}

	public void setChildPostList(List<DiscussionPost> childPostList) {
		this.childPostList = childPostList;
	}

	public DiscussionUser getDiscussionUser() {
		return discussionUser;
	}

	public void setDiscussionUser(DiscussionUser discussionUser) {
		this.discussionUser = discussionUser;
	}

}
