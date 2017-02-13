package com.haoyu.aip.text.entity;

import java.math.BigDecimal;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.file.entity.FileInfo;

public class TextInfoFile extends BaseEntity{
	
	private static final long serialVersionUID = 980886922488007686L;
	
	private String id;

	private String name;
	
	private String summary;
	
	private String url;
	
	private BigDecimal fileNum;
	
	private TextInfo textInfo;
	
	//以下非数据库字段
	private FileInfo fileInfo;

	public FileInfo getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(FileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}

	public TextInfo getTextInfo() {
		return textInfo;
	}

	public void setTextInfo(TextInfo textInfo) {
		this.textInfo = textInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getFileNum() {
		return fileNum;
	}

	public void setFileNum(BigDecimal fileNum) {
		this.fileNum = fileNum;
	}
	
	@Override
    public boolean equals(Object obj) {
    	if (obj instanceof TextInfoFile) {
			obj = (TextInfoFile)obj;
			if (((TextInfoFile) obj).getId() == null) {
				return false;
			}
			if (((TextInfoFile) obj).getId().equals(this.getId())) {
				return true;
			}
		}
    	return false;
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}
	
}
