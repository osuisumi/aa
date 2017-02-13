package com.haoyu.aip.survey.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.entity.SurveySubmission;
import com.haoyu.sip.core.service.Response;

public interface ISurveySubmissionService {

	Response create(SurveySubmission surveySubmission);

	Response createSurveySubmissions(List<SurveySubmission> surveySubmissions);
	//根据surveySubmission.surveyUser.id删除
	Response deleteByPhysics(SurveySubmission surveySubmission);
	
	List<SurveySubmission> findSurveySubmissions(Map<String,Object> param,PageBounds pageBounds);
	
}
