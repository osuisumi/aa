package com.haoyu.aip.video.entity;

import org.apache.commons.codec.digest.DigestUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class VideoRelation extends BaseEntity{
	
	private static final long serialVersionUID = -5917068738084216516L;

	private String id;
	
	private Video video;
	
	private Relation relation;
	
	private int viewNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	
	public static String getId(String videoId, String relationId){
		return DigestUtils.md5Hex(videoId+relationId);
	}
}
