package com.haoyu.aip.assignment.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.service.IAssignmentService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AssignmentDataDirective implements TemplateDirectiveModel {

	@Resource
	private IAssignmentService assignmentService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String id = params.get("id").toString();
		Assignment assignment = assignmentService.getAssignment(id);
		if (assignment != null) {
			env.setVariable("assignment", new DefaultObjectWrapper().wrap(assignment));
		}else{
			env.setVariable("assignment", new DefaultObjectWrapper().wrap(new Assignment()));
		}
		body.render(env.getOut());
	}
	
}
