package com.haoyu.aip.assignment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EachOtherMarkConfig implements Serializable{
	
	private static final long serialVersionUID = -603978132751348378L;

	private BigDecimal markScorePct;
	
	private BigDecimal markNum;
	
	private String getMarkScoreWay;
	
	public static final String GET_MARK_SCORE_WAY_FINISH_ALL = "finish_all";
	
	public static final String GET_MARK_SCORE_WAY_IF_ONNY_ONE = "if_only_one";

	public String getGetMarkScoreWay() {
		return getMarkScoreWay;
	}

	public void setGetMarkScoreWay(String getMarkScoreWay) {
		this.getMarkScoreWay = getMarkScoreWay;
	}

	public BigDecimal getMarkScorePct() {
		return markScorePct;
	}

	public void setMarkScorePct(BigDecimal markScorePct) {
		this.markScorePct = markScorePct;
	}

	public BigDecimal getMarkNum() {
		return markNum;
	}

	public void setMarkNum(BigDecimal markNum) {
		this.markNum = markNum;
	}

}
