package com.haoyu.aip.survey.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.entity.Survey;

public interface ISurveyDao {

	Survey selectSurveyById(String id);

	int insertSurvey(Survey survey);

	int updateSurvey(Survey survey);

	int deleteSurveyByLogic(Survey survey);

	int deleteSurveyByPhysics(String id);

	List<Survey> findAll(Map<String, Object> parameter);

	List<Survey> findAll(Map<String, Object> parameter, PageBounds pageBounds);

}
