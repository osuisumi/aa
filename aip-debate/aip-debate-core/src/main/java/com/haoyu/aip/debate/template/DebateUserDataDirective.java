package com.haoyu.aip.debate.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.service.IDebateUserService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class DebateUserDataDirective extends AbstractTemplateDirectiveModel{
	@Resource
	private IDebateUserService debateUserService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = getId(params);
		if(StringUtils.isEmpty(id)&&params.containsKey("debateRelationId")&&params.containsKey("userId")){
			id = DebateUser.getId(params.get("debateRelationId").toString(), params.get("userId").toString());
		}
		if(StringUtils.isNotEmpty(id)){
			DebateUser debateUser = debateUserService.findDebateUserById(id);
			env.setVariable("debateUser", new DefaultObjectWrapper().wrap(debateUser));
		}
		body.render(env.getOut());
		
	}

}
