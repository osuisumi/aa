package com.haoyu.aip.survey.mobile.controller;

import javax.annotation.Resource;

import com.haoyu.sip.core.service.Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.survey.mobile.service.IMSurveyResultService;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@RestController
@RequestMapping("**/m/survey_result")
public class MSurveyResultController extends AbstractBaseMobileController {
	@Resource
	private IMSurveyResultService mSurveyResultService;

	@RequestMapping(value = "{relationId}/{surveyId}", method = RequestMethod.GET)
	public Response getResult(@PathVariable String surveyId,@PathVariable String relationId) {
		return mSurveyResultService.updateResultFirstTime(surveyId,relationId);
	}

	@RequestMapping(value = "{questionId}/submissions", method = RequestMethod.GET)
	public Response getTextEntryQuestionSubmissions(@PathVariable String questionId) {
		PageBounds pageBounds = getPageBounds(10, true);
		return mSurveyResultService.getTextEntryQuestionSubmissions(questionId, pageBounds);

	}

}
