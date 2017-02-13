package com.haoyu.aip.survey.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.haoyu.aip.survey.entity.SurveyUser;
import com.haoyu.aip.survey.event.SubmitSurveyUserEvent;
import com.haoyu.aip.survey.service.ISurveyRelationService;

@Component
@Async
public class SubmitSurveyUserListener implements ApplicationListener<SubmitSurveyUserEvent>{
	
	@Resource
	private ISurveyRelationService surveyRelationService;

	@Override
	public void onApplicationEvent(SubmitSurveyUserEvent event) {
		SurveyUser surveyUser = (SurveyUser) event.getSource();
		surveyRelationService.updateChoiceResults(surveyUser.getSurvey().getId(), surveyUser.getRelation().getId());
	}

}
