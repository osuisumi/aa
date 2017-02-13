package com.haoyu.aip.survey.mobile.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.survey.entity.ChoiceGroup;
import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.aip.survey.entity.SurveyRelation;
import com.haoyu.aip.survey.entity.SurveySubmission;
import com.haoyu.aip.survey.entity.SurveyUser;
import com.haoyu.aip.survey.mobile.entity.MChoice;
import com.haoyu.aip.survey.mobile.entity.MSurveyQuestion;
import com.haoyu.aip.survey.mobile.entity.MSurveyResult;
import com.haoyu.aip.survey.mobile.entity.MSurveySubmission;
import com.haoyu.aip.survey.mobile.service.IMSurveyResultService;
import com.haoyu.aip.survey.service.ISurveyRelationService;
import com.haoyu.aip.survey.service.ISurveyService;
import com.haoyu.aip.survey.service.ISurveySubmissionService;
import com.haoyu.aip.survey.service.ISurveyUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.utils.Collections3;

@Service
public class MSurveyResultService implements IMSurveyResultService{
	@Resource
	private ISurveyService surveyService;
	@Resource
	private ISurveySubmissionService surveySubmissionService;
	@Resource
	private ISurveyRelationService surveyRelationService;
	@Resource
	private ISurveyUserService surveyUserService;
	@Override
	public Response updateResultFirstTime(String surveyId,String relationId) {
		Survey survey = surveyService.findSurveyById(surveyId);
		MSurveyResult result = new MSurveyResult();
		SurveyRelation sr = surveyRelationService.generateChoiceResultsFirstTimeGet(surveyId, relationId);
		if(survey!=null && sr!=null){
			BeanUtils.copyProperties(sr, result);
			
			result.setChoiceInteractionResults(sr.getChoiceInteractionResults());
			
			List<SurveyQuestion> questions = survey.getQuestions();
			LinkedList<MSurveyQuestion> mquestions = Lists.newLinkedList();
			for(SurveyQuestion sq:questions){
				MSurveyQuestion msq = new MSurveyQuestion();
				BeanUtils.copyProperties(sq, msq);
				//设置选项
				if(CollectionUtils.isNotEmpty(sq.getChoiceGroups())){
					ChoiceGroup cg = sq.getChoiceGroups().get(0);
					List<MChoice> mChoices = mChoices = BeanUtils.getCopyList(cg.getChoices(), MChoice.class);
					msq.setmChoices(mChoices);
				}
				mquestions.addLast(msq);
			}
			result.setmSurveyQuestions(mquestions);
		}
		
		SurveyUser surveyUser = surveyUserService.createFirstTimeGetSurveyUser(surveyId, relationId);
		if(surveyUser != null){
			Map map = Collections3.extractToMap(surveyUser.getSurveySubmissions(), "question.id", "response");
			result.setMySubmission(map);
		}
		return Response.successInstance().responseData(result);
	}
	@Override
	public Response getTextEntryQuestionSubmissions(String questionId,PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("questionId", questionId);
		List<SurveySubmission> surveySubmissions = surveySubmissionService.findSurveySubmissions(parameter, pageBounds);
		List<MSurveySubmission> msubmissions = Lists.newArrayList();
		for(SurveySubmission ss:surveySubmissions){
			MSurveySubmission mss = new MSurveySubmission();
			mss.setResponse(ss.getResponse());
			mss.setUser(ss.getCreator());
			msubmissions.add(mss);
		}
		
		Map<String,Object> result = Maps.newHashMap();
		
		PageList pageList = (PageList) surveySubmissions;
		
		result.put("mSubmissions", msubmissions);
		result.put("paginator", pageList.getPaginator());
		
		return Response.successInstance().responseData(result);
	}
	
	
}
