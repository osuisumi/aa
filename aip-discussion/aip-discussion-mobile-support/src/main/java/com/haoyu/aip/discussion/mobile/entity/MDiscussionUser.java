package com.haoyu.aip.discussion.mobile.entity;

import java.io.Serializable;

public class MDiscussionUser implements Serializable{
	
	private static final long serialVersionUID = -4297783195205473359L;

	private MDiscussion mDiscussion;
	
	private int mainPostNum;
	
	private int subPostNum;

	public MDiscussion getmDiscussion() {
		return mDiscussion;
	}

	public void setmDiscussion(MDiscussion mDiscussion) {
		this.mDiscussion = mDiscussion;
	}

	public int getMainPostNum() {
		return mainPostNum;
	}

	public void setMainPostNum(int mainPostNum) {
		this.mainPostNum = mainPostNum;
	}

	public int getSubPostNum() {
		return subPostNum;
	}

	public void setSubPostNum(int subPostNum) {
		this.subPostNum = subPostNum;
	}

}
