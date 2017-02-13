package com.haoyu.aip.assignment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class UploadResponseConfig implements Serializable{
	
	private static final long serialVersionUID = 5353057809841194174L;

	private String fileTypes;
	
	private BigDecimal fileSize;

	public String getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}

	public BigDecimal getFileSize() {
		return fileSize;
	}

	public void setFileSize(BigDecimal fileSize) {
		this.fileSize = fileSize;
	}


}
