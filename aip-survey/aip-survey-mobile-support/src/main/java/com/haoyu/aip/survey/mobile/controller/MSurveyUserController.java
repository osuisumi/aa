package com.haoyu.aip.survey.mobile.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.aip.survey.entity.SurveyUser;
import com.haoyu.aip.survey.service.ISurveyUserService;
import com.haoyu.aip.survey.utils.CompletionStatus;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@RestController
@RequestMapping("**/m/survey_user")
public class MSurveyUserController extends AbstractBaseMobileController{
	
	@Resource
	private ISurveyUserService surveyUserService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Response save(SurveyUser surveyUser){
		surveyUserService.createFirstTimeGetSurveyUser(surveyUser.getSurvey().getId(), surveyUser.getRelation().getId());
		surveyUser.setState(CompletionStatus.COMPLETE.toString());
		return surveyUserService.saveSurveyUser(surveyUser);
	}

}
