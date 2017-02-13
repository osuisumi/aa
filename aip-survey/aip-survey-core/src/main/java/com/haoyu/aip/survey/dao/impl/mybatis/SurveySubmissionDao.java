package com.haoyu.aip.survey.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.dao.ISurveySubmissionDao;
import com.haoyu.aip.survey.entity.SurveySubmission;
import com.haoyu.sip.core.jdbc.MybatisDao;
@Repository
public class SurveySubmissionDao extends MybatisDao implements ISurveySubmissionDao{

	@Override
	public int insert(SurveySubmission surveySubmission) {
		surveySubmission.setDefaultValue();
		return super.insert(surveySubmission);
	}

	@Override
	public int deleteByPhysics(SurveySubmission surveySubmission) {
		return super.deleteByPhysics(surveySubmission);
	}

	@Override
	public int batchInsert(List<SurveySubmission> surveySubmissions) {
		return super.insert("batchInsert", surveySubmissions);
	}

	@Override
	public List<SurveySubmission> selectByParam(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("selectByParameter", param,pageBounds);
	}


}
