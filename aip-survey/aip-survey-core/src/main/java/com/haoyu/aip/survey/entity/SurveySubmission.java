package com.haoyu.aip.survey.entity;

import com.haoyu.sip.core.entity.BaseEntity;

public class SurveySubmission extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	private SurveyQuestion question;

	private String response;

	private float score;

	private SurveyUser surveyUser;

	public SurveySubmission() {

	}

	public SurveySubmission(String id, String response) {
		this.id = id;
		this.response = response;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public SurveyUser getSurveyUser() {
		return surveyUser;
	}

	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}

	public SurveyQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SurveyQuestion question) {
		this.question = question;
	}

}
