package com.haoyu.aip.mobile.video.entity;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.haoyu.sip.mobile.file.entity.MFileInfo;

public class MVideo implements Serializable{
	
	private static final long serialVersionUID = -2067105235930264022L;

	private String id;
	
	private String tilte;
	
	private double viewTime;
	
	private int interval;
	
	private String allowDownload;
	
	private String urls;
	
	private String summary;
	
	private String type;
	
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	private List<MFileInfo> videoFiles = Lists.newArrayList();
	
	private List<MFileInfo> attchFiles = Lists.newArrayList();

	public String getUrls() {
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getAllowDownload() {
		return allowDownload;
	}

	public void setAllowDownload(String allowDownload) {
		this.allowDownload = allowDownload;
	}

	public List<MFileInfo> getVideoFiles() {
		return videoFiles;
	}

	public void setVideoFiles(List<MFileInfo> videoFiles) {
		this.videoFiles = videoFiles;
	}

	public List<MFileInfo> getAttchFiles() {
		return attchFiles;
	}

	public void setAttchFiles(List<MFileInfo> attchFiles) {
		this.attchFiles = attchFiles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTilte() {
		return tilte;
	}

	public void setTilte(String tilte) {
		this.tilte = tilte;
	}

	public double getViewTime() {
		return viewTime;
	}

	public void setViewTime(double viewTime) {
		this.viewTime = viewTime;
	}

}
