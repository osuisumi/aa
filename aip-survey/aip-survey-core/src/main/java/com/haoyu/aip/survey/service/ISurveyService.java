package com.haoyu.aip.survey.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.sip.core.service.Response;

public interface ISurveyService {
	
	Response createSurvey(Survey survey);
	
	Response updateSurvey(Survey survey);
	
	Response deleteSurvey(Survey survey);
	
	Survey findSurveyById(String id);
	
	List<Survey> findSurveys(Survey survey);
	
	List<Survey> findSurveys(Survey survey,PageBounds pageBounds);
	
	List<Survey> findSurveys(Map<String,Object> parameter);
	
	List<Survey> findSurveys(Map<String,Object> parameter,PageBounds pageBounds);
}
