package com.haoyu.aip.assignment.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.aip.assignment.utils.AssignmentUserState;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AssignmentUserNumDirective extends AbstractTemplateDirectiveModel{

	@Resource
	private IAssignmentUserService assignmentUserService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> parameter = getSelectParam(params);
		
		parameter.put("state", AssignmentUserState.COMMIT + "," + AssignmentUserState.COMPLETE);
		int allNum = assignmentUserService.getCount(parameter);
		env.setVariable("allNum", new DefaultObjectWrapper().wrap(allNum));
		
		if (parameter.containsKey("userId")) {
			parameter.put("marker", parameter.get("userId"));
		}
		parameter.put("state", AssignmentUserState.COMPLETE);
		int markNum = assignmentUserService.getCount(parameter);
		env.setVariable("markNum", new DefaultObjectWrapper().wrap(markNum));
		
		parameter.put("state", AssignmentUserState.COMMIT);
		int notMarkedNum = assignmentUserService.getCount(parameter);
		env.setVariable("notMarkedNum", new DefaultObjectWrapper().wrap(notMarkedNum));
		
		parameter.remove("marker");
		parameter.put("state", AssignmentUserState.COMMIT);
		parameter.put("receivedNumLessThan", 1);
		int notReceivedNum = assignmentUserService.getCount(parameter);
		env.setVariable("notReceivedNum", new DefaultObjectWrapper().wrap(notReceivedNum));
		
		body.render(env.getOut());
	}
	
}
