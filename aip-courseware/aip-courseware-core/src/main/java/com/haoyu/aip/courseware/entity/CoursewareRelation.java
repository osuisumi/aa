package com.haoyu.aip.courseware.entity;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class CoursewareRelation extends BaseEntity {
	private static final long serialVersionUID = 6587954194056768514L;
	private String coursewareId;
	private Relation relation;
	private int participateNum;
	private int browseNum;
	private int collectNum;
	private int followNum;
	private int replyNum;
	private String isTop;
	private String isEssence;
	private TimePeriod timePeriod = new TimePeriod();
	
	public CoursewareRelation(){}

	public CoursewareRelation(String coursewareId,Relation relation){
		this.coursewareId = coursewareId;
		this.relation = relation;
	}
	public String getCoursewareId() {
		return coursewareId;
	}

	public void setCoursewareId(String coursewareId) {
		this.coursewareId = coursewareId;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}

	public int getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(int browseNum) {
		this.browseNum = browseNum;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public String getIsTop() {
		return isTop;
	}

	public void setIsTop(String isTop) {
		this.isTop = isTop;
	}

	public String getIsEssence() {
		return isEssence;
	}

	public void setIsEssence(String isEssence) {
		this.isEssence = isEssence;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}
	

}
