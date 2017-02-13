package com.haoyu.aip.survey.mobile.controller;



import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.haoyu.aip.survey.mobile.service.IMSurveyQuestionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@RestController
@RequestMapping("**/m/survey_question")
public class MSurveyQuestionController extends AbstractBaseMobileController{
	
	@Resource
	private IMSurveyQuestionService mSurveyQuestionService;
	
	
	@RequestMapping(value="{surveyId}",method=RequestMethod.GET)
	public Response questions(@PathVariable String surveyId){
		return mSurveyQuestionService.listBySurveyId(surveyId);
	}

}
