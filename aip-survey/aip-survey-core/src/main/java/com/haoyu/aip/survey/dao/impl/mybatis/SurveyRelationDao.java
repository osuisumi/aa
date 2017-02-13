package com.haoyu.aip.survey.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.survey.dao.ISurveyRelationDao;
import com.haoyu.aip.survey.entity.SurveyRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class SurveyRelationDao extends MybatisDao implements ISurveyRelationDao{

	@Override
	public int insert(SurveyRelation surveyRelation) {
		surveyRelation.setDefaultValue();
		return super.insert(surveyRelation);
	}

	@Override
	public int deleteByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public int update(SurveyRelation surveyRelation) {
		return super.update("update", surveyRelation);
	}

	@Override
	public SurveyRelation get(String id) {
		return super.selectByPrimaryKey(id);
	}

	

}
