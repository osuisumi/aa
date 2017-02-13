package com.haoyu.aip.debate.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.service.IDebateArgumentStatService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class DebateArgumentStatsDataDirective extends AbstractTemplateDirectiveModel {

	@Resource
	private IDebateArgumentStatService debateArgumentStatService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> parameter = getSelectParam(params);
		if (parameter.containsKey("debateRelationId")) {
			env.setVariable("debateArgumentStats", new DefaultObjectWrapper().wrap(debateArgumentStatService.findDebateArgumentStatByDebateRelation(new DebateRelation(parameter.get("debateRelationId").toString()))));
		}

		body.render(env.getOut());

	}

}
