package com.haoyu.aip.survey.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.dao.ISurveyDao;
import com.haoyu.aip.survey.dao.ISurveyUserDao;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.aip.survey.entity.SurveySubmission;
import com.haoyu.aip.survey.entity.SurveyUser;
import com.haoyu.aip.survey.event.SubmitSurveyUserEvent;
import com.haoyu.aip.survey.service.ISurveyRelationService;
import com.haoyu.aip.survey.service.ISurveySubmissionService;
import com.haoyu.aip.survey.service.ISurveyUserService;
import com.haoyu.aip.survey.utils.CompletionStatus;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service("surveyUserService")
public class SurveyUserServiceImpl implements ISurveyUserService {

	@Resource
	private ISurveyUserDao surveyUserDao;

	@Resource
	private ISurveyDao surveyDao;

	@Resource
	private ISurveySubmissionService surveySubmissionService;
	
	@Resource
	private ISurveyRelationService surveyRelationService;
	
	@Resource
	private ApplicationContext applicationContext;


	@Override
	//获取surveyUser，获取不到时根据参数创建surveyUser
	public SurveyUser createFirstTimeGetSurveyUser(String surveyId, String relationId) {
		if (StringUtils.isEmpty(surveyId) || StringUtils.isEmpty(relationId) ||ThreadContext.getUser()==null) {
			return null;
		}
		String id = DigestUtils.md5Hex(surveyId + relationId + ThreadContext.getUser().getId());
		SurveyUser surveyUser = surveyUserDao.selectById(id);
		if(surveyUser == null){
			surveyUser = new SurveyUser();
			Survey survey = surveyDao.selectSurveyById(surveyId);
			if (survey != null) {
				surveyUser.setSurvey(survey);
				surveyUser.getRelation().setId(relationId);
				surveyUser.setSurvey(survey);
				surveyUser.setId(id);
				surveyUser.setState(CompletionStatus.NOTATTEMPED.toString());
				surveyUser.setDefaultValue();
				surveyUserDao.insert(surveyUser);
			}
		}
		return surveyUser;
	}

	@Override
	public SurveyUser getSurveyUser(String id) {
		return surveyUserDao.selectById(id);
	}

	@Override
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		if (StringUtils.isNoneEmpty(surveyUser.getSurvey().getId())) {
			parameter.put("surveyId", surveyUser.getSurvey().getId());
		}
		if (StringUtils.isNotEmpty(surveyUser.getRelation().getId())) {
			parameter.put("relationId", surveyUser.getRelation().getId());
		}
		if (StringUtils.isNotEmpty(surveyUser.getState())) {
			parameter.put("completionStatus", surveyUser.getState());
		}
		return surveyUserDao.select(parameter);
	}

	@Override
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser, List<String> users) {
		return this.querySurveyUser(surveyUser, users, null);
	}

	@Override
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser, PageBounds pageBounds) {
		return this.querySurveyUser(surveyUser, null, pageBounds);
	}

	@Override
	public List<SurveyUser> querySurveyUser(SurveyUser surveyUser, List<String> users, PageBounds pageBounds) {
		Map<String,Object> parameter = new HashMap<String,Object>();
		if (StringUtils.isNoneEmpty(surveyUser.getSurvey().getId())) {
			parameter.put("surveyId", surveyUser.getSurvey().getId());
		}
		if (StringUtils.isNotEmpty(surveyUser.getRelation().getId())) {
			parameter.put("relationId", surveyUser.getRelation().getId());
		}
		if (StringUtils.isNotEmpty(surveyUser.getState())) {
			parameter.put("completionStatus", surveyUser.getState());
		}
		if(CollectionUtils.isNotEmpty(users)){
			parameter.put("userIds", users);
		}
		return surveyUserDao.select(parameter, pageBounds);
	}

	@Override
	//更新surveyUser，重新插入surveySubmission，更新统计信息
	public Response saveSurveyUser(SurveyUser surveyUser) {
		if (StringUtils.isEmpty(surveyUser.getId())) {
			surveyUser.setId(DigestUtils.md5Hex(surveyUser.getSurvey().getId() + surveyUser.getRelation().getId() + surveyUser.getCreator().getId()));
		}
		int count = surveyUserDao.update(surveyUser);
		if (count > 0) {
			if (surveyUser.getSurveySubmissions() != null && !surveyUser.getSurveySubmissions().isEmpty()) {
				SurveySubmission surveySubmission = new SurveySubmission();
				surveySubmission.setSurveyUser(surveyUser);
				surveySubmissionService.deleteByPhysics(surveySubmission);
				for (SurveySubmission ss : surveyUser.getSurveySubmissions()) {
					if(StringUtils.isEmpty(ss.getId())){
						ss.setId(Identities.uuid2());
						ss.setDefaultValue();
					}
					ss.setSurveyUser(surveyUser);
				}
				surveySubmissionService.createSurveySubmissions(surveyUser.getSurveySubmissions());
			}
			applicationContext.publishEvent(new SubmitSurveyUserEvent(surveyUser));
			return Response.successInstance();
		}
		return Response.failInstance();
	}

}
