package com.haoyu.aip.discussion.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.tag.entity.Tag;

public class Discussion extends BaseEntity {

	private static final long serialVersionUID = 8267884160051220913L;

	private String title;

	private String content;

	private String state;

	private List<DiscussionRelation> discussionRelations = Lists.newArrayList();
	
	private List<FileInfo> fileInfos = Lists.newArrayList();
	
	private List<Tag> tags = Lists.newArrayList();
	
	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<DiscussionRelation> getDiscussionRelations() {
		return discussionRelations;
	}

	public void setDiscussionRelations(List<DiscussionRelation> discussionRelations) {
		this.discussionRelations = discussionRelations;
	}

}
