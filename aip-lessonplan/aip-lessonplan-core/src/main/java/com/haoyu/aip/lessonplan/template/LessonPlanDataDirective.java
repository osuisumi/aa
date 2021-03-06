package com.haoyu.aip.lessonplan.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.aip.lessonplan.entity.LessonPlan;
import com.haoyu.aip.lessonplan.service.ILessonPlanService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class LessonPlanDataDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private ILessonPlanService lessonPlanService;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = getId(params);
		if(StringUtils.isNotEmpty(id)){
			LessonPlan lessonPlan = lessonPlanService.get(id);
			env.setVariable("lessonPlan", new DefaultObjectWrapper().wrap(lessonPlan));
		}
		body.render(env.getOut());
	}

}
