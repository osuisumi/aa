package com.haoyu.aip.text.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.text.utils.TextInfoType;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.file.entity.FileInfo;

public class TextInfo extends BaseEntity{
	
	private static final long serialVersionUID = 980886922488007686L;
	
	private String id;

	private String title;
	
	private String content;
	
	private String type;
	
	private String state;
	
	//以下为非数据库字段
	private List<TextInfoRelation> textInfoRelations = Lists.newArrayList();
	
	private List<FileInfo> fileInfos = Lists.newArrayList();
	
	private Map<String, Object> contentMap = Maps.newHashMap();
	
	private List<TextInfoFile> textInfoFiles = Lists.newArrayList();

	public Map<String, Object> getContentMap() {
		if (contentMap.isEmpty() && TextInfoType.FILE.equals(this.type)) {
			contentMap = new JsonMapper().fromJson(content, HashMap.class);
		}
		return contentMap;
	}

	public List<TextInfoFile> getTextInfoFiles() {
		return textInfoFiles;
	}

	public void setTextInfoFiles(List<TextInfoFile> textInfoFiles) {
		this.textInfoFiles = textInfoFiles;
	}

	public void setContentMap(Map<String, Object> contentMap) {
		this.contentMap = contentMap;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public List<TextInfoRelation> getTextInfoRelations() {
		return textInfoRelations;
	}

	public void setTextInfoRelations(List<TextInfoRelation> textInfoRelations) {
		this.textInfoRelations = textInfoRelations;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
