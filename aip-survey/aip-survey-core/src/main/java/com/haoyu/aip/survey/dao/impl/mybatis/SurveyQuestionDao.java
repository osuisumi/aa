package com.haoyu.aip.survey.dao.impl.mybatis;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.dao.ISurveyQuestionDao;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class SurveyQuestionDao extends MybatisDao implements ISurveyQuestionDao {

	@Override
	public SurveyQuestion selectSurveyQuestionById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertSurveyQuestion(SurveyQuestion surveyQuestion) {
		surveyQuestion.setDefaultValue();
		return super.insert(surveyQuestion);
	}

	@Override
	public int updateSurveyQuestion(SurveyQuestion surveyQuestion) {
		surveyQuestion.setUpdateValue();
		return super.update(surveyQuestion);
	}

	@Override
	public int deleteSurveyQuestionByLogic(SurveyQuestion surveyQuestion) {
		surveyQuestion.setUpdateValue();
		return super.deleteByLogic(surveyQuestion);
	}

	@Override
	public int deleteSurveyQuestionByPhysics(SurveyQuestion surveyQuestion) {
		return super.deleteByPhysics(surveyQuestion);
	}

	@Override
	public List<SurveyQuestion> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<SurveyQuestion> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	@Override
	public int insertSurveyQuestions(List<SurveyQuestion> surveyQuestions) {
		return super.insert("batchInsert",surveyQuestions);
	}



}
