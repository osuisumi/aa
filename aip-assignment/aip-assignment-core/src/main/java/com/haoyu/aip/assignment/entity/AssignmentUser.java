package com.haoyu.aip.assignment.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;

public class AssignmentUser extends BaseEntity{

	private static final long serialVersionUID = 4016174902021585987L;

	private String id;
	
	private AssignmentRelation assignmentRelation;
	
	private String response;
	
	private String state;
	
	//调用dao默认的update方法时, 如果<0则更新为平均值, =0则更新为0, >0则更新为参数值
	private BigDecimal responseScore;
	
	private BigDecimal markScore;
	
	private String comment;
	
	private Date responseTime;
	
	private int receivedNum;
	
	private int markedNum;
	
	private int receiveNum;
	
	private int markNum;
	
	private String isEnd;
	
	//非数据库字段
	private List<FileInfo> fileInfos = Lists.newArrayList();

	public String getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(String isEnd) {
		this.isEnd = isEnd;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public AssignmentRelation getAssignmentRelation() {
		return assignmentRelation;
	}

	public void setAssignmentRelation(AssignmentRelation assignmentRelation) {
		this.assignmentRelation = assignmentRelation;
	}

	public BigDecimal getResponseScore() {
		return responseScore;
	}

	public void setResponseScore(BigDecimal responseScore) {
		this.responseScore = responseScore;
	}

	public BigDecimal getMarkScore() {
		return markScore;
	}

	public void setMarkScore(BigDecimal markScore) {
		this.markScore = markScore;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
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

	public int getReceivedNum() {
		return receivedNum;
	}

	public void setReceivedNum(int receivedNum) {
		this.receivedNum = receivedNum;
	}

	public int getMarkedNum() {
		return markedNum;
	}

	public void setMarkedNum(int markedNum) {
		this.markedNum = markedNum;
	}

	public int getMarkNum() {
		return markNum;
	}

	public void setMarkNum(int markNum) {
		this.markNum = markNum;
	}

	public int getReceiveNum() {
		return receiveNum;
	}

	public void setReceiveNum(int receiveNum) {
		this.receiveNum = receiveNum;
	}

	public static String getId(String assignmentRelationId, String userId){
		return DigestUtils.md5Hex(assignmentRelationId+userId);
	}
	
}
