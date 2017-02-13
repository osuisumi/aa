package com.haoyu.aip.text.mobile.entity;

import java.io.Serializable;

import com.haoyu.sip.file.utils.FileUtils;

public class MTextInfo implements Serializable{
	
	private static final long serialVersionUID = 1708100041014851599L;

	private String id;
	
	private String title;
	
	private String type;
	
	private String content;
	
	private String pdfUrl;
	
	private int interval;
	
	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	private int viewNum;

	public int getViewNum() {
		return viewNum;
	}

	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}

	public String getPdfUrl() {
		String prefix = FileUtils.getHttpHost();
		if (pdfUrl != null && !pdfUrl.contains(prefix)) {
			pdfUrl = prefix + pdfUrl;
		}
		return pdfUrl;
	}

	public void setPdfUrl(String pdfUrl) {
		this.pdfUrl = pdfUrl;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
