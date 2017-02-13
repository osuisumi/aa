/**
 * 
 */
package com.haoyu.aip.qti.entity;

import java.util.Set;

import com.haoyu.sip.core.entity.BaseEntity;

/**
 * 题目文件包
 * @author lianghuahuang
 *
 */
public class QuestionPackage extends BaseEntity {
	
	private  String id;
	
	/**
	 * 文件存储相对路径
	 */
	private String questionHref;
	
	private String fileName;
	
	/**
	 * 文件存储根路径
	 */
	private String sandboxPath;
	
	//题目包中关联到的文件路径，如图片，视频,音频等
	private Set<String> safeFileHrefs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionHref() {
		return questionHref;
	}

	public void setQuestionHref(String questionHref) {
		this.questionHref = questionHref;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSandboxPath() {
		return sandboxPath;
	}

	public void setSandboxPath(String sandboxPath) {
		this.sandboxPath = sandboxPath;
	}

	public Set<String> getSafeFileHrefs() {
		return safeFileHrefs;
	}

	public void setSafeFileHrefs(Set<String> safeFileHrefs) {
		this.safeFileHrefs = safeFileHrefs;
	}
}
