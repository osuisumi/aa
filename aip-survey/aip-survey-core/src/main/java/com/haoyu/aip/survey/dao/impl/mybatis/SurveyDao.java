package com.haoyu.aip.survey.dao.impl.mybatis;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.dao.ISurveyDao;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class SurveyDao extends MybatisDao implements ISurveyDao {

	@Override
	public Survey selectSurveyById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insertSurvey(Survey survey) {
		survey.setDefaultValue();
		return super.insert(survey);
	}

	@Override
	public int updateSurvey(Survey survey) {
		survey.setUpdateValue();
		return super.update(survey);
	}

	@Override
	public int deleteSurveyByLogic(Survey survey) {
		survey.setUpdateValue();
		return super.deleteByLogic(survey);
	}

	@Override
	public int deleteSurveyByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public List<Survey> findAll(Map<String, Object> parameter) {
		return super.selectList("selectByParameter", parameter);
	}

	@Override
	public List<Survey> findAll(Map<String, Object> parameter, PageBounds pageBounds) {
		return super.selectList("selectByParameter", parameter, pageBounds);
	}

	
}
