package com.haoyu.aip.survey.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.survey.dao.ISurveyDao;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.aip.survey.entity.SurveyRelation;
import com.haoyu.aip.survey.service.ISurveyQuestionService;
import com.haoyu.aip.survey.service.ISurveyRelationService;
import com.haoyu.aip.survey.service.ISurveyService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

@Service("surveyService")
public class SurveyServiceImpl implements ISurveyService {
	
	@Resource
	private ISurveyDao surveyDao;
	@Resource
	private ISurveyRelationService surveyRelationService;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private ISurveyQuestionService surveyQuestionService;

	@Override
	public Response createSurvey(Survey survey) {
		if(survey == null){
			return Response.failInstance().responseMsg("createSurvey fail!survey is null!");
		}
		String id = StringUtils.isEmpty(survey.getId())?Identities.uuid2():survey.getId();
		survey.setId(id);
		int count = surveyDao.insertSurvey(survey);
		Response response = Response.failInstance();
		if(count>0){
			response = Response.successInstance();
			if(CollectionUtils.isNotEmpty(survey.getSurveyRelations())){
				for(SurveyRelation sr:survey.getSurveyRelations()){
					sr.setSurvey(survey);
					sr.setId(SurveyRelation.getId(id, sr.getRelation().getId()));
					response = surveyRelationService.create(sr);
				}
			}
		}
		return response;
	}

	@Override
	public Response updateSurvey(Survey survey) {
		return surveyDao.updateSurvey(survey)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteSurvey(Survey survey) {
		int count = surveyDao.deleteSurveyByLogic(survey);
		if(count>0){
			return Response.successInstance();
		}
		return Response.failInstance();
	}

	@Override
	public Survey findSurveyById(String id) {
		Survey survey = surveyDao.selectSurveyById(id);
		if (survey != null) {
			if (CollectionUtils.isNotEmpty(survey.getQuestions())) {
				Collections.sort(survey.getQuestions(),new Comparator<SurveyQuestion>() {
					@Override
					public int compare(SurveyQuestion o1, SurveyQuestion o2) {
						if (o1.getSortNo() == null && o2.getSortNo() == null || o1.getSortNo().compareTo(o2.getSortNo()) == 0) {
							return (int) (o1.getCreateTime() - o2.getCreateTime());
						}else{
							if (o1.getSortNo() == null) {
								return -1;
							}else if(o2.getSortNo() == null){
								return 1;
							}else{
								return o1.getSortNo().compareTo(o2.getSortNo());
							}
						}
					}
				});
			}
		}
		return survey;
	}

	@Override
	public List<Survey> findSurveys(Survey survey) {
		return findSurveys(survey,null);
	}

	@Override
	public List<Survey> findSurveys(Survey survey, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(StringUtils.isNotEmpty(survey.getTitle())){
			parameter.put("title", survey.getTitle());
		}
		if(StringUtils.isNotBlank(survey.getState())){
			parameter.put("state", survey.getState());
		}
		return findSurveys(parameter, pageBounds);
	}

	@Override
	public List<Survey> findSurveys(Map<String, Object> parameter) {
		return surveyDao.findAll(parameter);
	}

	@Override
	public List<Survey> findSurveys(Map<String, Object> parameter, PageBounds pageBounds) {
		return surveyDao.findAll(parameter,pageBounds);
	}
	

}
