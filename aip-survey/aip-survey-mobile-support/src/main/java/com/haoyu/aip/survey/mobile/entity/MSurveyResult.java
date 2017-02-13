package com.haoyu.aip.survey.mobile.entity;

import java.util.List;
import java.util.Map;

public class MSurveyResult {

	private List<MSurveyQuestion> mSurveyQuestions;

	private int participateNum;

	private Map<String, Map<String, Integer>> choiceInteractionResults;
	
	private Map<String,String> mySubmission;

	public List<MSurveyQuestion> getmSurveyQuestions() {
		return mSurveyQuestions;
	}

	public void setmSurveyQuestions(List<MSurveyQuestion> mSurveyQuestions) {
		this.mSurveyQuestions = mSurveyQuestions;
	}

	public int getParticipateNum() {
		return participateNum;
	}

	public void setParticipateNum(int participateNum) {
		this.participateNum = participateNum;
	}

	public Map<String, Map<String, Integer>> getChoiceInteractionResults() {
		return choiceInteractionResults;
	}

	public void setChoiceInteractionResults(Map<String, Map<String, Integer>> choiceInteractionResults) {
		this.choiceInteractionResults = choiceInteractionResults;
	}

	public Map<String, String> getMySubmission() {
		return mySubmission;
	}

	public void setMySubmission(Map<String, String> mySubmission) {
		this.mySubmission = mySubmission;
	}
	
	

}
