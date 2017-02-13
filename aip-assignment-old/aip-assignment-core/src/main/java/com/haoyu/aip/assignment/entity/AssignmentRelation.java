package com.haoyu.aip.assignment.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.TimePeriod;

public class AssignmentRelation extends BaseEntity{
	
	private static final long serialVersionUID = 6764397434333930132L;

	private Relation relation;
	
	private Assignment assignment;
	
	private TimePeriod timePeriod = new TimePeriod();
	
	private int participateNum;
	
	private int browseNum;	

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

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}
	
	public static String getId(String assignmentId,String relationId){
		return DigestUtils.md5Hex(assignmentId+relationId);
	}

}
