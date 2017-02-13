package com.haoyu.aip.lessonplan.entity;

import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.tag.entity.Tag;

public class LessonPlan extends BaseEntity{

	private static final long serialVersionUID = -3405747301715550932L;

    private String title;

    private String content;
    
    private String state;
    
	private List<LessonPlanRelation> lessonPlanRelations = Lists.newArrayList();
	
	private List<FileInfo> fileInfos = Lists.newArrayList();
	
	private List<Tag> tags = Lists.newArrayList();	

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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
        this.title = title == null ? null : title.trim();
    }

   
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setLessonPlanRelations(List<LessonPlanRelation> lessonPlanRelations) {
		this.lessonPlanRelations = lessonPlanRelations;
	}

	public List<LessonPlanRelation> getLessonPlanRelations() {
		return lessonPlanRelations;
	}

	public void setDlessonPlanRelations(List<LessonPlanRelation> lessonPlanRelations) {
		this.lessonPlanRelations = lessonPlanRelations;
	}
    
    
}