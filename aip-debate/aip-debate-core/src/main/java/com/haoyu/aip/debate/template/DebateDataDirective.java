package com.haoyu.aip.debate.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.service.IDebateService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class DebateDataDirective extends AbstractTemplateDirectiveModel{
	@Resource
	private IDebateService debateService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = getId(params);
		if(StringUtils.isNotEmpty(id)){
			Debate debate = debateService.findDebateById(id);
			env.setVariable("debate", new DefaultObjectWrapper().wrap(debate));
		}
		body.render(env.getOut());
		
	}

}
