package com.haoyu.aip.qti.mobile.entity;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

public class MTestUser implements Serializable{
	
	private static final long serialVersionUID = 7095322720150175927L;

	private String id;
	
	private String completionStatus;
	
	private int attempts;
	
	private double sumScore;
	
	private Map<String, MTestSubmission> mTestSubmissionMap = Maps.newHashMap();
	
	private MTest mTest;

	public MTest getmTest() {
		return mTest;
	}

	public void setmTest(MTest mTest) {
		this.mTest = mTest;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public double getSumScore() {
		return sumScore;
	}

	public void setSumScore(double sumScore) {
		this.sumScore = sumScore;
	}

	public Map<String, MTestSubmission> getmTestSubmissionMap() {
		return mTestSubmissionMap;
	}

	public void setmTestSubmissionMap(Map<String, MTestSubmission> mTestSubmissionMap) {
		this.mTestSubmissionMap = mTestSubmissionMap;
	}
	
	
	
	

}
