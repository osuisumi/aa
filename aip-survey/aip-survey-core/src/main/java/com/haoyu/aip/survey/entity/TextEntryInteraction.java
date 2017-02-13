package com.haoyu.aip.survey.entity;

import java.io.Serializable;

import com.haoyu.sip.core.mapper.JsonMapper;


/**
 *
 *
 *@author 梁华璜
 */
public class TextEntryInteraction implements Serializable {

	private static final long serialVersionUID = 1595299590538525076L;

	private int minWords;
	
	private int maxWords;

	public int getMinWords() {
		return minWords;
	}

	public void setMinWords(int minWords) {
		this.minWords = minWords;
	}

	public int getMaxWords() {
		return maxWords;
	}

	public void setMaxWords(int maxWords) {
		this.maxWords = maxWords;
	}
	
	public String toString(){
		return new JsonMapper().toJson(this);
	}
}
