package com.haoyu.aip.assignment.entity;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;

public class AssignmentMark extends BaseEntity{

	private static final long serialVersionUID = 4016174902021585987L;
	
	private String id;

	private AssignmentUser assignmentUser;
	
	private BigDecimal score;
	
	private String state;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AssignmentUser getAssignmentUser() {
		return assignmentUser;
	}

	public void setAssignmentUser(AssignmentUser assignmentUser) {
		this.assignmentUser = assignmentUser;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public static String getId(String assignmentUserId, String userId){
		return DigestUtils.md5Hex(assignmentUserId+userId);
	}
	
}
