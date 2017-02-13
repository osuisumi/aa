package com.haoyu.aip.survey.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.entity.SurveyUser;

public interface ISurveyUserDao {
	

	public List<SurveyUser> select(Map<String,Object> surveyUser);
	
	public List<SurveyUser> select(Map<String,Object> surveyUser,PageBounds pageBounds);

	public int insert(SurveyUser surveyUser);

	public SurveyUser selectById(String id);

	public int update(SurveyUser surveyUser);
	
}
