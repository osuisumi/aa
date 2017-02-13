package com.haoyu.aip.survey.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.aip.survey.dao.ISurveyRelationDao;
import com.haoyu.aip.survey.entity.Choice;
import com.haoyu.aip.survey.entity.ChoiceGroup;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.aip.survey.entity.SurveyRelation;
import com.haoyu.aip.survey.entity.SurveySubmission;
import com.haoyu.aip.survey.entity.SurveyUser;
import com.haoyu.aip.survey.service.ISurveyRelationService;
import com.haoyu.aip.survey.service.ISurveyService;
import com.haoyu.aip.survey.service.ISurveyUserService;
import com.haoyu.aip.survey.utils.CompletionStatus;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;


@Service
public class SurveyRelationServiceImpl implements ISurveyRelationService{
	@Resource
	private ISurveyRelationDao surveyRelationDao;
	
	@Resource
	private ISurveyUserService surveyUserService;
	
	@Resource
	private ISurveyService surveyService;

	@Override
	public Response create(SurveyRelation surveyRelation) {
		int count =  surveyRelationDao.insert(surveyRelation);
		if(StringUtils.isEmpty(surveyRelation.getId())){
			surveyRelation.setId(Identities.uuid2());
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response delete(String surveyId,String relationId) {
		int count = surveyRelationDao.deleteByPhysics(SurveyRelation.getId(surveyId, relationId));
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(SurveyRelation surveyRelation) {
		return surveyRelationDao.update(surveyRelation)>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public SurveyRelation generateChoiceResultsFirstTimeGet(String id) {
		//没有统计信息，先生成统计信息
		SurveyRelation surveyRelation = surveyRelationDao.get(id);
		if(surveyRelation != null){
			if(MapUtils.isEmpty(surveyRelation.getChoiceInteractionResults())){
				generateChoiceResults(surveyRelation);
			}
		}
		return surveyRelation;
	}
	

	@Override
	public Response updateChoiceResults(String surveyId, String relationId) {
		SurveyUser su = new SurveyUser();
		Relation relation = new Relation(relationId);
		Survey survey = new Survey(surveyId);
		su.setRelation(relation);
		su.setSurvey(survey);
		su.setState(CompletionStatus.COMPLETE.toString());
		List<SurveyUser> surveyUsers = surveyUserService.querySurveyUser(su);
		SurveyRelation surveyRelation = this.generateChoiceResultsFirstTimeGet(SurveyRelation.getId(surveyId, relationId));
		if(surveyRelation!= null){
			//更新统计信息
			for (Map<String, Integer> choiceResult : surveyRelation.getChoiceInteractionResults().values()) {
				for (String choiceId : choiceResult.keySet()) {
					choiceResult.put(choiceId, 0);
				}
			}
			surveyRelation.setParticipateNum(surveyUsers.size());
			for (SurveyUser surveyUser : surveyUsers) {
				List<SurveySubmission> surveySubmissions = surveyUser.getSurveySubmissions();
				if (surveySubmissions != null && !surveySubmissions.isEmpty()) {
					for (SurveySubmission ss : surveySubmissions) {
						if (surveyRelation.getChoiceInteractionResults().containsKey(ss.getQuestion().getId())) {
							if (StringUtils.isNotEmpty(ss.getResponse())) {
								String[] responses = StringUtils.split(ss.getResponse(), ",");
								Map<String, Integer> choiceResult = surveyRelation.getChoiceInteractionResults().get(ss.getQuestion().getId());
								for (String response : responses) {
									choiceResult.put(response, choiceResult.get(response) + 1);
								}
							}
						}
					}
				}
			}
			surveyRelation.setChoiceResultJson();
			return this.update(surveyRelation);
		}else{
			return Response.failInstance().responseMsg("update choiceResult fail! surveyRelation not exist");
		}
	}

	@Override
	public SurveyRelation generateChoiceResultsFirstTimeGet(String surveyId, String relationId) {
		return this.generateChoiceResultsFirstTimeGet(SurveyRelation.getId(surveyId, relationId));
	}
	
	private Response generateChoiceResults(SurveyRelation surveyRelation){
		Survey survey = surveyService.findSurveyById(surveyRelation.getSurvey().getId());
		if(survey== null ||CollectionUtils.isEmpty(survey.getQuestions())){
			return Response.failInstance();
		}
		Map<String, Map<String, Integer>> choiceInteractionResults = new HashMap<String, Map<String, Integer>>();
		surveyRelation.setChoiceInteractionResults(choiceInteractionResults);
		for (SurveyQuestion sq : survey.getQuestions()) {
			if (sq.getType().equals(SurveyQuestion.QuesType.MULTIPLECHOICE.toString()) || sq.getType().equals(SurveyQuestion.QuesType.SINGLECHOICE.toString()) || sq.getType().equals(SurveyQuestion.QuesType.TRUEORFALSE.toString())) {
				List<ChoiceGroup> choiceGroups = sq.getChoiceGroups();
				HashMap<String, Integer> choiceResults = new HashMap<String, Integer>();
				for (ChoiceGroup cg : choiceGroups) {
					for (Choice c : cg.getChoices()) {
						choiceResults.put(c.getId(), 0);
					}
				}
				choiceInteractionResults.put(sq.getId(), choiceResults);
			}
		}
		surveyRelation.setChoiceResultJson();
		Response response = this.update(surveyRelation);
		if(response.isSuccess()){
			response.responseData(surveyRelation);
		}
		return response;
	}

}
