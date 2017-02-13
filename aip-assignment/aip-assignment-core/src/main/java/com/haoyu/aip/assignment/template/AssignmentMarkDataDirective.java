package com.haoyu.aip.assignment.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.aip.assignment.service.IAssignmentMarkService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AssignmentMarkDataDirective implements TemplateDirectiveModel {
	
	@Resource
	private IAssignmentMarkService assignmentMarkService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("id") && StringUtils.isNotEmpty(params.get("id").toString())){
			String id = params.get("id").toString();
			AssignmentMark assignmentMark = assignmentMarkService.getAssignmentMark(id);
			if (assignmentMark != null) {
				env.setVariable("assignmentMark", new DefaultObjectWrapper().wrap(assignmentMark));
			}else{
				env.setVariable("assignmentMark", new DefaultObjectWrapper().wrap(new AssignmentMark()));
			}
		}else if (params.containsKey("assignmentUserId") && StringUtils.isNotEmpty(params.get("assignmentUserId").toString())) {
			String assignmentUserId = params.get("assignmentUserId").toString();
			AssignmentMark assignmentMark = assignmentMarkService.createAssignmentMarkIfNotExists(assignmentUserId);
			env.setVariable("assignmentMark", new DefaultObjectWrapper().wrap(assignmentMark));
		}
		body.render(env.getOut());
	}
	
}
