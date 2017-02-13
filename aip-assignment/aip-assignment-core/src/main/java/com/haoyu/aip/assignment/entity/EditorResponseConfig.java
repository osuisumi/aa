package com.haoyu.aip.assignment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class EditorResponseConfig implements Serializable{

	private static final long serialVersionUID = 8690627106781384853L;

	private String allowImage;
	
	private String allowVideo;
	
	private BigDecimal minWords;
	
	private BigDecimal maxWords;

	public String getAllowImage() {
		return allowImage;
	}

	public void setAllowImage(String allowImage) {
		this.allowImage = allowImage;
	}

	public String getAllowVideo() {
		return allowVideo;
	}

	public void setAllowVideo(String allowVideo) {
		this.allowVideo = allowVideo;
	}

	public BigDecimal getMinWords() {
		return minWords;
	}

	public void setMinWords(BigDecimal minWords) {
		this.minWords = minWords;
	}

	public BigDecimal getMaxWords() {
		return maxWords;
	}

	public void setMaxWords(BigDecimal maxWords) {
		this.maxWords = maxWords;
	}
	
}
