package com.haoyu.aip.text.mobile.entity;

import java.io.Serializable;

public class MTextInfoUser implements Serializable{
	
	private static final long serialVersionUID = 2836679365632630841L;

	private String id;

	private MTextInfo mTextInfo;

	private int viewNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MTextInfo getmTextInfo() {
		return mTextInfo;
	}

	public void setmTextInfo(MTextInfo mTextInfo) {
		this.mTextInfo = mTextInfo;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

}
