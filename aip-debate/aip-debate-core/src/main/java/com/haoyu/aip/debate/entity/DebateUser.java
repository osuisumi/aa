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
public class DebateUser extends BaseEntity {
	
	private String id;
	
	private DebateRelation debateRelation;
	
	/**
	 * 支持的论点
	 */
	private DebateArgument argument;
	
	/**
	 * 观点数
	 */
	private int viewsNum;
	
	/**
	 * 评论数
	 */
	private int commentsNum;
	
	public DebateUser(){}
	
	public DebateUser(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public DebateRelation getDebateRelation() {
		return debateRelation;
	}

	public void setDebateRelation(DebateRelation debateRelation) {
		this.debateRelation = debateRelation;
	}

	public DebateArgument getArgument() {
		return argument;
	}

	public void setArgument(DebateArgument argument) {
		this.argument = argument;
	}

	public int getViewsNum() {
		return viewsNum;
	}

	public void setViewsNum(int viewsNum) {
		this.viewsNum = viewsNum;
	}

	public int getCommentsNum() {
		return commentsNum;
	}

	public void setCommentsNum(int commentsNum) {
		this.commentsNum = commentsNum;
	}
	
	public static String getId(String debateRelationId,String creator){
		return DigestUtils.md5Hex(debateRelationId+creator);
	}
}
