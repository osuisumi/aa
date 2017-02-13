package com.haoyu.aip.assignment.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.service.IAssignmentService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AssignmentsDataDirective implements TemplateDirectiveModel {

	@Resource
	private IAssignmentService assignmentService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> param = Maps.newHashMap();
		if (params.containsKey("relationId") && params.get("relationId") != null) {
			String relationId = params.get("relationId").toString();
			param.put("relationId", relationId);
		}
		if (params.containsKey("markType") && params.get("markType") != null) {
			String markType = params.get("markType").toString();
			param.put("markType", markType);
		}
		List<Assignment> assignments = assignmentService.listAssignment(param, null);
		env.setVariable("assignments", new DefaultObjectWrapper().wrap(assignments));
		body.render(env.getOut());
	}
	
}
