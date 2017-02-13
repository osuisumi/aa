package com.haoyu.aip.survey.mobile.entity;

import java.io.Serializable;
import java.util.List;

public class MSurveyQuestion implements Serializable{

	private static final long serialVersionUID = -1005373900343711518L;
	
	private String id;

	private String title;
	
	private String type;
	
	private int minWords;
	
	private int maxWords;
	
	private int minChoose;
	
	private int maxChoose;
	
	private List<MChoice> mChoices;

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

	public int getMinChoose() {
		return minChoose;
	}

	public void setMinChoose(int minChoose) {
		this.minChoose = minChoose;
	}

	public int getMaxChoose() {
		return maxChoose;
	}

	public void setMaxChoose(int maxChoose) {
		this.maxChoose = maxChoose;
	}

	public List<MChoice> getmChoices() {
		return mChoices;
	}

	public void setmChoices(List<MChoice> mChoices) {
		this.mChoices = mChoices;
	}
	
	

}
