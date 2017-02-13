package com.haoyu.aip.assignment.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.tag.entity.Tag;

public class Assignment extends BaseEntity{
	
	private static final long serialVersionUID = 7543959634845567003L;

	private String title;
	
	private String content;
	
	private String state;
	
	private List<AssignmentRelation> assignmentRelations = Lists.newArrayList();
	
	private List<Tag> tags = Lists.newArrayList();
	
	private List<FileInfo> fileInfos = Lists.newArrayList();

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public List<AssignmentRelation> getAssignmentRelations() {
		return assignmentRelations;
	}

	public void setAssignmentRelations(List<AssignmentRelation> assignmentRelations) {
		this.assignmentRelations = assignmentRelations;
	}

}
