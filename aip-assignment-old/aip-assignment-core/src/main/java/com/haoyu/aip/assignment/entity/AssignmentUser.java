package com.haoyu.aip.assignment.entity;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.collect.Lists;
import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;

public class AssignmentUser extends BaseEntity{

	private static final long serialVersionUID = 4016174902021585987L;

	private AssignmentRelation assignmentRelation;
	
	private String state;
	
	private BigDecimal score;
	
	private String remark;
	
	private List<FileInfo> fileInfos = Lists.newArrayList();

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public AssignmentRelation getAssignmentRelation() {
		return assignmentRelation;
	}

	public void setAssignmentRelation(AssignmentRelation assignmentRelation) {
		this.assignmentRelation = assignmentRelation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public static String getId(String assignmentRelationId, String userId){
		return DigestUtils.md5Hex(assignmentRelationId+userId);
	}
	
}
