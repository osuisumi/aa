package com.haoyu.aip.survey.mobile.service;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;

public interface IMSurveyResultService {
	public Response updateResultFirstTime(String surveyId,String relationId);
	
	public Response getTextEntryQuestionSubmissions(String questionId,PageBounds pageBounds);

}
