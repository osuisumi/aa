package com.haoyu.aip.text.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.sip.core.entity.BaseEntity;

public class TextInfoUser extends BaseEntity{

	private static final long serialVersionUID = -2052149537771674413L;

	private String id;
	
	private TextInfoRelation textInfoRelation;
	
	private int viewNum;
	
	private double viewTime;
	
	private String state;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public double getViewTime() {
		return viewTime;
	}

	public void setViewTime(double viewTime) {
		this.viewTime = viewTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public TextInfoRelation getTextInfoRelation() {
		return textInfoRelation;
	}

	public void setTextInfoRelation(TextInfoRelation textInfoRelation) {
		this.textInfoRelation = textInfoRelation;
	}

	public static String getId(String textInfoRelationId, String userId) {
		return DigestUtils.md5Hex(textInfoRelationId+userId);
	}

}
