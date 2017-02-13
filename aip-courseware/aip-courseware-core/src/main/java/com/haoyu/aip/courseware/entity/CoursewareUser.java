package com.haoyu.aip.courseware.entity;

import com.haoyu.base.entity.BaseEntity;

public class CoursewareUser extends BaseEntity{
	private static final long serialVersionUID = -8951768167433390976L;
	private CoursewareRelation coursewareRelation;
	private String state;
	public CoursewareRelation getCoursewareRelation() {
		return coursewareRelation;
	}
	public void setCoursewareRelation(CoursewareRelation coursewareRelation) {
		this.coursewareRelation = coursewareRelation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	

}
