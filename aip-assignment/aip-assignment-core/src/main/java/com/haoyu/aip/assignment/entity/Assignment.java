package com.haoyu.aip.assignment.entity;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.haoyu.aip.assignment.utils.AssignmentMarkType;
import com.haoyu.aip.assignment.utils.AssignmentResponseType;
import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.tag.entity.Tag;

public class Assignment extends BaseEntity{
	
	private static final long serialVersionUID = 7543959634845567003L;
	
	private String id;

	private String title;
	
	private String content;
	
	private BigDecimal score;
	
	private String responseType;
	
	private String responseConfig;
	
	private String markType;
	
	private String markConfig;
	
	private String state;
	
	//以下非数据库字段
	private List<AssignmentRelation> assignmentRelations = Lists.newArrayList();
	
	private List<Tag> tags = Lists.newArrayList();
	
	private List<FileInfo> fileInfos = Lists.newArrayList();
	
	private EditorResponseConfig editorResponseConfig;
	
	private UploadResponseConfig uploadResponseConfig;
	
	private EachOtherMarkConfig eachOtherMarkConfig;
	
	public String getResponseConfig() {
		if (StringUtils.isNotEmpty(responseConfig)) {
			return responseConfig;
		}
		if (AssignmentResponseType.EDITOR.equals(responseType) && editorResponseConfig != null) {
			responseConfig = new JsonMapper().toJson(editorResponseConfig);
		}else if (AssignmentResponseType.UPLOAD.equals(responseType) && uploadResponseConfig != null) {
			responseConfig = new JsonMapper().toJson(uploadResponseConfig);
		}
		return responseConfig;
	}

	public void setResponseConfig(String responseConfig) {
		this.responseConfig = responseConfig;
		if(StringUtils.isNotEmpty(responseConfig)){
			if (AssignmentResponseType.EDITOR.equals(responseType)) {
				editorResponseConfig = new JsonMapper().fromJson(responseConfig, EditorResponseConfig.class);
			}else if (AssignmentResponseType.UPLOAD.equals(responseType)) {
				uploadResponseConfig = new JsonMapper().fromJson(responseConfig, UploadResponseConfig.class);
			}
		}
	}
	
	public String getMarkConfig() {
		if (StringUtils.isNotEmpty(markConfig)) {
			return markConfig;
		}
		if (AssignmentMarkType.EACH_OTHER.equals(markType) && eachOtherMarkConfig != null) {
			markConfig = new JsonMapper().toJson(eachOtherMarkConfig);
		}
		return markConfig;
	}

	public void setMarkConfig(String markConfig) {
		this.markConfig = markConfig;
		if (AssignmentMarkType.EACH_OTHER.equals(markType) && StringUtils.isNotEmpty(markConfig)) {
			eachOtherMarkConfig = new JsonMapper().fromJson(markConfig, EachOtherMarkConfig.class);
		}
	}
	
	public EachOtherMarkConfig getEachOtherMarkConfig() {
		return eachOtherMarkConfig;
	}

	public void setEachOtherMarkConfig(EachOtherMarkConfig eachOtherMarkConfig) {
		this.eachOtherMarkConfig = eachOtherMarkConfig;
	}

	public EditorResponseConfig getEditorResponseConfig() {
		return editorResponseConfig;
	}

	public void setEditorResponseConfig(EditorResponseConfig editorResponseConfig) {
		this.editorResponseConfig = editorResponseConfig;
	}

	public UploadResponseConfig getUploadResponseConfig() {
		return uploadResponseConfig;
	}

	public void setUploadResponseConfig(UploadResponseConfig uploadResponseConfig) {
		this.uploadResponseConfig = uploadResponseConfig;
	}

	public List<AssignmentRelation> getAssignmentRelations() {
		return assignmentRelations;
	}

	public void setAssignmentRelations(List<AssignmentRelation> assignmentRelations) {
		this.assignmentRelations = assignmentRelations;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getMarkType() {
		return markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}

}
