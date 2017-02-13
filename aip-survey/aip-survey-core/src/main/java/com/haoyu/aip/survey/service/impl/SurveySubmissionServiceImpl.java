package com.haoyu.aip.survey.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.dao.ISurveySubmissionDao;
import com.haoyu.aip.survey.entity.SurveySubmission;
import com.haoyu.aip.survey.service.ISurveySubmissionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

@Service
public class SurveySubmissionServiceImpl implements ISurveySubmissionService{
	@Resource
	private ISurveySubmissionDao surveySubmissionDao;

	@Override
	public Response create(SurveySubmission surveySubmission) {
		if(StringUtils.isEmpty(surveySubmission.getId())){
			surveySubmission.setId(Identities.uuid2());
		}
		return surveySubmissionDao.insert(surveySubmission)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteByPhysics(SurveySubmission surveySubmission) {
		return surveySubmissionDao.deleteByPhysics(surveySubmission)>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response createSurveySubmissions(List<SurveySubmission> surveySubmissions) {
		Response response = Response.failInstance();
		for(SurveySubmission surveySubmission:surveySubmissions){
			response = this.create(surveySubmission);
		}
		return response;
	}

	@Override
	public List<SurveySubmission> findSurveySubmissions(Map<String,Object> param,PageBounds pageBounds) {
		return surveySubmissionDao.selectByParam(param, pageBounds);
	}


}
