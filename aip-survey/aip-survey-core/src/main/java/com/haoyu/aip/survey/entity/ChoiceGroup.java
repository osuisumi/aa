package com.haoyu.aip.survey.entity;

import java.util.List;

public class ChoiceGroup {

	private String id;

	private Integer maxChoose;

	private Integer minChoose;

	private List<Choice> choices;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getMaxChoose() {
		return maxChoose;
	}

	public void setMaxChoose(Integer maxChoose) {
		this.maxChoose = maxChoose;
	}

	public Integer getMinChoose() {
		return minChoose;
	}

	public void setMinChoose(Integer minChoose) {
		this.minChoose = minChoose;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

}
