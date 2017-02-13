package com.haoyu.aip.survey.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.dao.ISurveyUserDao;
import com.haoyu.aip.survey.entity.SurveyUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class SurveyUserDao extends MybatisDao implements ISurveyUserDao {


	@Override
	public int insert(SurveyUser surveyUser) {
		return super.insert(surveyUser);
	}

	@Override
	public SurveyUser selectById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int update(SurveyUser surveyUser) {
		surveyUser.setUpdateValue();
		return super.update(surveyUser);
	}

	@Override
	public List<SurveyUser> select(Map<String, Object> surveyUser) {
		return super.selectList("selectByParameter", surveyUser);
	}

	@Override
	public List<SurveyUser> select(Map<String, Object> surveyUser, PageBounds pageBounds) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
