package com.haoyu.aip.discussion.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.service.IDiscussionService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class DiscussionDataDirective implements TemplateDirectiveModel {

	@Resource
	private IDiscussionService discussionService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("id")){
			String id = params.get("id").toString();
			Discussion discussion = discussionService.get(id);
			if (discussion != null) {
				env.setVariable("discussion", new DefaultObjectWrapper().wrap(discussion));
			}else{
				env.setVariable("discussion", new DefaultObjectWrapper().wrap(new Discussion()));
			}
		}
		body.render(env.getOut());
	}

}
