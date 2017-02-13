package com.haoyu.aip.survey.mobile.service.impl;


import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.survey.entity.ChoiceGroup;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.aip.survey.mobile.entity.MChoice;
import com.haoyu.aip.survey.mobile.entity.MSurveyQuestion;
import com.haoyu.aip.survey.mobile.service.IMSurveyQuestionService;
import com.haoyu.aip.survey.service.ISurveyQuestionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;

@Service
public class MSurveyQuestionService implements IMSurveyQuestionService{
	
	@Resource
	private ISurveyQuestionService surveyQuestionService;

	@Override
	public Response listBySurveyId(String surveyId) {
		Map<String,Object> parameter = Maps.newHashMap();
		parameter.put("surveyId", surveyId);
		List<SurveyQuestion> surveyQuestions = surveyQuestionService.findSurveyQuestions(parameter);
		LinkedList<MSurveyQuestion> mSurveyQuestions = Lists.newLinkedList();
		for(SurveyQuestion sq:surveyQuestions){
			MSurveyQuestion msq = new MSurveyQuestion();
			
			BeanUtils.copyProperties(sq, msq);
			
			//设置选项
			if(CollectionUtils.isNotEmpty(sq.getChoiceGroups())){
				ChoiceGroup cg = sq.getChoiceGroups().get(0);
				msq.setMaxChoose(cg.getMaxChoose()==null?0:cg.getMaxChoose());
				msq.setMinChoose(cg.getMinChoose()==null?0:cg.getMinChoose());
				List<MChoice> mChoices = mChoices = BeanUtils.getCopyList(cg.getChoices(), MChoice.class);
				msq.setmChoices(mChoices);
				
			}
			
			if(sq.getTextEntryInteraction()!=null){
				msq.setMinWords(sq.getTextEntryInteraction().getMinWords());
				msq.setMaxWords(sq.getTextEntryInteraction().getMaxWords());
			}
			
			mSurveyQuestions.addLast(msq);
			
		}
		return Response.successInstance().responseData(mSurveyQuestions);
	}

}
