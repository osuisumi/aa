/**
 * 
 */
package com.haoyu.aip.debate.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;


/**
 * @author lianghuahuang
 *
 */
public class DebateArgumentStat extends BaseEntity {
	
	private String id;
	
	private DebateArgument argument;
	
	private DebateRelation debateRelation;
	
	/**
	 * 最佳观点
	 */
	private DebateUserViews bestViews;
	
	/**
	 * 论点下发表的观点数
	 */
	private int viewsNum;
	
	/**
	 * 论点支持人数
	 */
	private int participateNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DebateArgument getArgument() {
		return argument;
	}

	public void setArgument(DebateArgument argument) {
		this.argument = argument;
	}

	public DebateRelation getDebateRelation() {
		return debateRelation;
	}

	public void setDebateRelation(DebateRelation debateRelation) {
		this.debateRelation = debateRelation;
	}

	public int getViewsNum() {
		return viewsNum;
	}

	public void setViewsNum(int viewsNum) {
		this.viewsNum = viewsNum;
	}

	public DebateUserViews getBestViews() {
		return bestViews;
	}

	public void setBestViews(DebateUserViews bestViews) {
		this.bestViews = bestViews;
	}
	
	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}

	public static String getId(String argumentId,String debateRelationId){
		return DigestUtils.md5Hex(argumentId+debateRelationId);
	}

}
