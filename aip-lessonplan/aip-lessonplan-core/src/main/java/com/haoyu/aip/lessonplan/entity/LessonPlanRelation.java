package com.haoyu.aip.lessonplan.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class LessonPlanRelation extends BaseEntity{
	
	private static final long serialVersionUID = 4098778437438309538L;
	
	private Relation relation;
	
	private LessonPlan lessonPlan;
	
	private TimePeriod timePeriod = new TimePeriod();
		
	private int participateNum;
	
	private int browseNum;
	
	private int followNum;
	
	private int collectNum;
	
	private int replyNum;
	
	private int fileNum;
	
	private long lastReplyTime;
	
	private String isTop;
	
	private String isEssence;
	
	public long getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(long lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
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

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
	
	public LessonPlan getLessonPlan() {
		return lessonPlan;
	}

	public void setLessonPlan(LessonPlan lessonPlan) {
		this.lessonPlan = lessonPlan;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
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

	public int getFollowNum() {
		return followNum;
	}

	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}

	public int getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}

	public static String getId(String lessonPlanId,String relationId){
		return DigestUtils.md5Hex(lessonPlanId+relationId);
	}

}
