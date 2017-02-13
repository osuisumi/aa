package com.haoyu.aip.assignment.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.core.entity.TimePeriod;
import com.haoyu.sip.mobile.file.entity.MFileInfo;

public class MAssignment implements Serializable{

	private static final long serialVersionUID = 5433882344651170280L;

	private String id;
	
	private String markType;
	
	private double markScorePct;
	
	private int markNum;
	
	private String content;
	
	private List<MFileInfo> mFileInfos = Lists.newArrayList();
	
	private String fileTypes;
	
	private double fileSize;
	
	private boolean inResponseTime;
	
	private boolean inMarkTime;
	
	private TimePeriod responseTime;
	
	private TimePeriod markTime;

	public boolean isInResponseTime() {
		return inResponseTime;
	}

	public void setInResponseTime(boolean inResponseTime) {
		this.inResponseTime = inResponseTime;
	}

	public boolean isInMarkTime() {
		return inMarkTime;
	}

	public void setInMarkTime(boolean inMarkTime) {
		this.inMarkTime = inMarkTime;
	}

	public TimePeriod getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(TimePeriod responseTime) {
		this.responseTime = responseTime;
	}

	public TimePeriod getMarkTime() {
		return markTime;
	}

	public void setMarkTime(TimePeriod markTime) {
		this.markTime = markTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMarkType() {
		return markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}

	public double getMarkScorePct() {
		return markScorePct;
	}

	public void setMarkScorePct(double markScorePct) {
		this.markScorePct = markScorePct;
	}

	public int getMarkNum() {
		return markNum;
	}

	public void setMarkNum(int markNum) {
		this.markNum = markNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<MFileInfo> getmFileInfos() {
		return mFileInfos;
	}

	public void setmFileInfos(List<MFileInfo> mFileInfos) {
		this.mFileInfos = mFileInfos;
	}

	public String getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

}
