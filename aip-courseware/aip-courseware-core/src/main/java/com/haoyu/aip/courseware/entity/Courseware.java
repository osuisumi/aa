package com.haoyu.aip.courseware.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.haoyu.base.entity.BaseEntity;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.file.entity.FileInfo;

public class Courseware extends BaseEntity {
	private static final long serialVersionUID = -6634532056315781899L;
	private String title;
	private String content;
	private Date teachTime;
	private User teacher;
	private String stage;
	private String grade;
	private String subject;
	private String textbook;
	private String description;
	private FileInfo video;
	private String videoJson;
	private List<CoursewareRelation> coursewareRelations = new ArrayList<CoursewareRelation>();
	private List<FileInfo> fileInfos;
	private String type;
	private String state;

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}

	public void setFileInfos(List<FileInfo> fileInfos) {
		this.fileInfos = fileInfos;
	}

	public Date getTeachTime() {
		return teachTime;
	}

	public void setTeachTime(Date teachTime) {
		this.teachTime = teachTime;
	}


	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTextbook() {
		return textbook;
	}

	public void setTextbook(String textbook) {
		this.textbook = textbook;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public FileInfo getVideo() {
		if(this.videoJson!=null &&!this.videoJson.trim().equals("")){
			return new JsonMapper().fromJson(this.videoJson, FileInfo.class);
		}
		return this.video;
	}

	public void setVideo(FileInfo video) {
		this.video = video;
	}

	public List<CoursewareRelation> getCoursewareRelations() {
		return coursewareRelations;
	}

	public void setCoursewareRelations(List<CoursewareRelation> coursewareRelations) {
		this.coursewareRelations = coursewareRelations;
	}

	public String getVideoJson() {
		if(StringUtils.isEmpty(videoJson)){
			if(this.video != null){
				videoJson = "{\"id\":\""+video.getId()+"\",\"fileName\":\""+video.getFileName()+"\",\"url\":\""+video.getUrl()+"\"}";
			}
		}
		return videoJson == null?"":videoJson;
	}

	public void setVideoJson(String videoJson) {
		this.videoJson = videoJson;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
	

}
