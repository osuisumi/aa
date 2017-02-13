package com.haoyu.aip.assignment.mobile.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.mobile.file.entity.MFileInfo;
import com.haoyu.sip.user.mobile.entity.MUser;

public class MAssignmentUser implements Serializable{

	private static final long serialVersionUID = -2777119430766137353L;

	private String id;
	
	private MAssignment mAssignment;
	
	private String state;
	
	private Date responseTime;
	
	private double responseScore;
	
	private int markNum;
	
	private double markScore;
	
	private int markedNum;
	
	private String assignmentRelationId;
	
	private List<MFileInfo> mFileInfos = Lists.newArrayList();
	
	private MUser mUser;

	public MUser getmUser() {
		return mUser;
	}

	public void setmUser(MUser mUser) {
		this.mUser = mUser;
	}

	public List<MFileInfo> getmFileInfos() {
		return mFileInfos;
	}

	public void setmFileInfos(List<MFileInfo> mFileInfos) {
		this.mFileInfos = mFileInfos;
	}

	public String getAssignmentRelationId() {
		return assignmentRelationId;
	}

	public void setAssignmentRelationId(String assignmentRelationId) {
		this.assignmentRelationId = assignmentRelationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MAssignment getmAssignment() {
		return mAssignment;
	}

	public void setmAssignment(MAssignment mAssignment) {
		this.mAssignment = mAssignment;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public double getResponseScore() {
		return responseScore;
	}

	public void setResponseScore(double responseScore) {
		this.responseScore = responseScore;
	}

	public int getMarkNum() {
		return markNum;
	}

	public void setMarkNum(int markNum) {
		this.markNum = markNum;
	}

	public double getMarkScore() {
		return markScore;
	}

	public void setMarkScore(double markScore) {
		this.markScore = markScore;
	}

	public int getMarkedNum() {
		return markedNum;
	}

	public void setMarkedNum(int markedNum) {
		this.markedNum = markedNum;
	}

}
