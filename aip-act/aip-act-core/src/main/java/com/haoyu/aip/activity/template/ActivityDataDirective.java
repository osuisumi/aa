package com.haoyu.aip.activity.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.service.IActivityService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class ActivityDataDirective implements TemplateDirectiveModel {

	@Resource
	private IActivityService activityService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if (params.containsKey("id")) {
			String id = params.get("id").toString();
			Activity activity = activityService.getActivity(id);
			if (activity != null) {
				env.setVariable("activity", new DefaultObjectWrapper().wrap(activity));
			}else{
				env.setVariable("activity", new DefaultObjectWrapper().wrap(new Activity()));
			}
		}
		body.render(env.getOut());
	}

}
