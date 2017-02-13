package com.haoyu.aip.lessonplan.entity;

import com.haoyu.base.entity.BaseEntity;

public class LessonPlanRecord extends BaseEntity{
	
	private static final long serialVersionUID = -4093965716430111469L;

    private String title;

    private String lessonPlanId;

    private String content;
    
    private String relationId;
    
    //以下非数据库字段
    private String realName;

    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLessonPlanId() {
		return lessonPlanId;
	}

	public void setLessonPlanId(String lessonPlanId) {
		this.lessonPlanId = lessonPlanId;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}