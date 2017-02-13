package com.haoyu.aip.survey.dao;

import com.haoyu.aip.survey.entity.SurveyRelation;

public interface ISurveyRelationDao {
	
	public int insert(SurveyRelation surveyRelation);

	public int deleteByPhysics(String id);
	
	public int update(SurveyRelation surveyRelation);
	
	public SurveyRelation get(String id);
	
}
