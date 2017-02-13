package com.haoyu.aip.video.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.video.utils.VideoType;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.file.entity.FileInfo;

public class Video extends BaseEntity{
	
	private static final long serialVersionUID = 1403238822503620691L;

	private String id;
	
	private String title;
	
	private String urls;
	
	private String type;
	
	private String allowDownload;
	
	private String startTime;
	
	private String endTime;
	
	private String recordId;
	
	private String summary;
	
	//以下非数据库字段
	private List<FileInfo> videoFiles = Lists.newArrayList();	//视频列表
	
	private List<FileInfo> fileInfos = Lists.newArrayList();	//讲义列表
	
	private List<VideoRelation> videoRelations = Lists.newArrayList();
	
	private Map<String, Object> urlsMap = Maps.newHashMap();
	
	public Map<String, Object> getUrlsMap() {
		if (!urlsMap.isEmpty()) {
			return urlsMap;
		}
		if (StringUtils.isNotEmpty(urls) && type == VideoType.RECORD) {
			urlsMap = new JsonMapper().fromJson(urls, HashMap.class);
		}
		return urlsMap;
	}

	public void setUrlsMap(Map<String, Object> urlsMap) {
		this.urlsMap = urlsMap;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public List<VideoRelation> getVideoRelations() {
		return videoRelations;
	}

	public void setVideoRelations(List<VideoRelation> videoRelations) {
		this.videoRelations = videoRelations;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrls() {
		if (StringUtils.isNotEmpty(urls)) {
			return urls;
		}
		if (!urlsMap.isEmpty()) {
			urls = new JsonMapper().toJson(urlsMap);
		}
		return urls;
	}

	public void setUrls(String urls) {
		this.urls = urls;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAllowDownload() {
		return allowDownload;
	}

	public void setAllowDownload(String allowDownload) {
		this.allowDownload = allowDownload;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public List<FileInfo> getVideoFiles() {
		return videoFiles;
	}

	public void setVideoFiles(List<FileInfo> videoFiles) {
		this.videoFiles = videoFiles;
	}

}
