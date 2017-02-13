package com.haoyu.aip.survey.service;


import com.haoyu.aip.survey.entity.SurveyRelation;
import com.haoyu.sip.core.service.Response;

public interface ISurveyRelationService {

	Response create(SurveyRelation surveyRelation);
	
	SurveyRelation generateChoiceResultsFirstTimeGet(String id);
	
	SurveyRelation generateChoiceResultsFirstTimeGet(String surveyId,String relationId);

	Response delete(String surveyId,String relationId);
	
	Response update(SurveyRelation surveyRelation);
	
	Response updateChoiceResults(String surveyId, String relationId);
	
}
