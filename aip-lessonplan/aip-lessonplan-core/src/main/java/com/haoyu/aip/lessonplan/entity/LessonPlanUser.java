package com.haoyu.aip.lessonplan.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;

public class LessonPlanUser extends BaseEntity{

	private static final long serialVersionUID = -2043870198867934860L;

	private LessonPlanRelation lessonPlanRelation;
	
	private String state;
	
	public LessonPlanRelation getLessonPlanRelation() {
		return lessonPlanRelation;
	}

	public void setLessonPlanRelation(LessonPlanRelation lessonPlanRelation) {
		this.lessonPlanRelation = lessonPlanRelation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
    
	public static String getId(String lessonPlanRelationId, String creator){
		return DigestUtils.md5Hex(lessonPlanRelationId+creator);
    }
}