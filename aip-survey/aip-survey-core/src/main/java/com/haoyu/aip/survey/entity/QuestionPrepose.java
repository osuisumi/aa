package com.haoyu.aip.survey.entity;

import java.util.List;

public class QuestionPrepose {

	private String questionId;
	
	private String type;

	private List<String> choiceIds;
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public List<String> getChoiceIds() {
		return choiceIds;
	}

	public void setChoiceIds(List<String> choiceIds) {
		this.choiceIds = choiceIds;
	}

}
