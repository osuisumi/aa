package com.haoyu.aip.survey.template;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.haoyu.aip.survey.entity.Survey;
import com.haoyu.aip.survey.entity.SurveyQuestion;
import com.haoyu.aip.survey.entity.SurveyRelation;
import com.haoyu.aip.survey.service.ISurveyRelationService;
import com.haoyu.aip.survey.service.ISurveyService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class SurveyDataDirective implements TemplateDirectiveModel {

	@Resource
	private ISurveyService surveyService;
	
	@Resource
	private ISurveyRelationService surveyRelationService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if (params.containsKey("id")) {
			String id = params.get("id").toString();
			Survey survey = surveyService.findSurveyById(id);
			if (survey != null) {
				if(params.containsKey("relationId")){
					String relationId = params.get("relationId").toString();
					SurveyRelation surveyRelation = surveyRelationService.generateChoiceResultsFirstTimeGet(id, relationId);
					if (surveyRelation != null) {
						env.setVariable("surveyRelation", new DefaultObjectWrapper().wrap(surveyRelation));
					} else {
						env.setVariable("surveyRelation", new DefaultObjectWrapper().wrap(new SurveyRelation()));
					}
					
				}
				env.setVariable("survey", new DefaultObjectWrapper().wrap(survey));
			} else {
				env.setVariable("survey", new DefaultObjectWrapper().wrap(new Survey()));
			}
		}
		body.render(env.getOut());
	}

}
