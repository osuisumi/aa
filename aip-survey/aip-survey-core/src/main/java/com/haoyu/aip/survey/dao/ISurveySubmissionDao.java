package com.haoyu.aip.survey.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.entity.SurveySubmission;


public interface ISurveySubmissionDao {

	int insert(SurveySubmission surveySubmission);
	
	int deleteByPhysics(SurveySubmission surveySubmission);
	
	int batchInsert(List<SurveySubmission> surveySubmissions);
	
	List<SurveySubmission> selectByParam(Map<String,Object> param,PageBounds pageBounds);

}
