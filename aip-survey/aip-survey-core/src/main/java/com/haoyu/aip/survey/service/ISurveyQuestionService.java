package com.haoyu.aip.survey.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.sip.core.service.Response;

public interface ISurveyQuestionService {
	Response createSurveyQuestion(SurveyQuestion surveyQuestion);
	
	Response createSurveyQuestions(Survey survey);
	
	Response updateSurveyQuestion(SurveyQuestion surveyQuestion);
	
	Response deleteSurveyQuestion(SurveyQuestion surveyQuestion);
	
	SurveyQuestion findSurveyQuestionById(String id);
	
	List<SurveyQuestion> findSurveyQuestions(SurveyQuestion surveyQuestion);
	
	List<SurveyQuestion> findSurveyQuestions(SurveyQuestion surveyQuestion,PageBounds pageBounds);
	
	List<SurveyQuestion> findSurveyQuestions(Map<String,Object> parameter);
	
	List<SurveyQuestion> findSurveyQuestions(Map<String,Object> parameter,PageBounds pageBounds);
	
	Response importSurveyQuestionsFromString(String input,String surveyId);
}
