package com.haoyu.aip.survey.entity;

import java.util.ArrayList;
import java.util.List;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.Relation;

public class SurveyUser extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;

	private Survey survey;

	private Relation relation = new Relation();

	private String state;

	private List<SurveySubmission> surveySubmissions = new ArrayList<SurveySubmission>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<SurveySubmission> getSurveySubmissions() {
		return surveySubmissions;
	}

	public void setSurveySubmissions(List<SurveySubmission> surveySubmissions) {
		this.surveySubmissions = surveySubmissions;
	}

}
