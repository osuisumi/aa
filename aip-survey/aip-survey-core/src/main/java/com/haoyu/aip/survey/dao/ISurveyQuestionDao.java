package com.haoyu.aip.survey.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.entity.SurveyQuestion;

public interface ISurveyQuestionDao{

	SurveyQuestion selectSurveyQuestionById(String id);

	int insertSurveyQuestion(SurveyQuestion surveyQuestion);
	
	int insertSurveyQuestions(List<SurveyQuestion> surveyQuestions);

	int updateSurveyQuestion(SurveyQuestion surveyQuestion);

	int deleteSurveyQuestionByLogic(SurveyQuestion surveyQuestion);

	int deleteSurveyQuestionByPhysics(SurveyQuestion surveyQuestion);
	
	List<SurveyQuestion> findAll(Map<String, Object> parameter);

	List<SurveyQuestion> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
