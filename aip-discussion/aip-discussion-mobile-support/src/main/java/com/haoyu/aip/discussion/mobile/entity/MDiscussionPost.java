package com.haoyu.aip.discussion.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.user.mobile.entity.MUser;

public class MDiscussionPost implements Serializable{

	private static final long serialVersionUID = 8370847848557017482L;

	private String id;
	
	private String content;
	
	private MUser creator;
	
	private long createTime;
	
	private String mainPostId;
	
	private int childPostCount; 
	
	private List<MDiscussionPost> childMDiscussionPosts = Lists.newArrayList();
			
	private int supportNum;
	
	public MDiscussionPost() {
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainPostId() {
		return mainPostId;
	}

	public void setMainPostId(String mainPostId) {
		this.mainPostId = mainPostId;
	}

	public int getChildPostCount() {
		return childPostCount;
	}

	public void setChildPostCount(int childPostCount) {
		this.childPostCount = childPostCount;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public List<MDiscussionPost> getChildMDiscussionPosts() {
		return childMDiscussionPosts;
	}

	public void setChildMDiscussionPosts(List<MDiscussionPost> childMDiscussionPosts) {
		this.childMDiscussionPosts = childMDiscussionPosts;
	}

	public int getSupportNum() {
		return supportNum;
	}

	public void setSupportNum(int supportNum) {
		this.supportNum = supportNum;
	}

	public MUser getCreator() {
		return creator;
	}

	public void setCreator(MUser creator) {
		this.creator = creator;
	}

}