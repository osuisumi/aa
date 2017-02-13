package com.haoyu.aip.discussion.mobile.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.mobile.file.entity.MFileInfo;
import com.haoyu.sip.user.mobile.entity.MUser;

public class MDiscussion implements Serializable{

	private static final long serialVersionUID = -1306893584431638264L;

	private String id;
	
	private String title;
	
	private String content;
	
	private MUser creator;
	
	private long createTime;
	
	private List<MDiscussionRelation> mDiscussionRelations = Lists.newArrayList();
		
	private int mainPostNum;
	
	private int subPostNum;
	
	private List<MFileInfo> mFileInfos = Lists.newArrayList();
	
	public List<MFileInfo> getmFileInfos() {
		return mFileInfos;
	}

	public void setmFileInfos(List<MFileInfo> mFileInfos) {
		this.mFileInfos = mFileInfos;
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

	public MDiscussion() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<MDiscussionRelation> getmDiscussionRelations() {
		return mDiscussionRelations;
	}

	public void setmDiscussionRelations(List<MDiscussionRelation> mDiscussionRelations) {
		this.mDiscussionRelations = mDiscussionRelations;
	}

	public MUser getCreator() {
		return creator;
	}

	public void setCreator(MUser creator) {
		this.creator = creator;
	}
	
}