package com.haoyu.aip.survey.entity;

import java.util.ArrayList;
import java.util.List;


import com.haoyu.sip.core.entity.BaseEntity;

public class Survey extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	private String title;

	private String description;

	private String state;

	private List<SurveyRelation> surveyRelations = new ArrayList<SurveyRelation>();

	private List<SurveyQuestion> questions = new ArrayList<SurveyQuestion>();

	public Survey() {

	}

	public Survey(String id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<SurveyRelation> getSurveyRelations() {
		return surveyRelations;
	}

	public void setSurveyRelations(List<SurveyRelation> surveyRelations) {
		this.surveyRelations = surveyRelations;
	}

	public List<SurveyQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SurveyQuestion> questions) {
		this.questions = questions;
	}
	

}
