package com.haoyu.aip.assignment.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.service.IAssignmentRelationService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AssignmentRelationDataDirective implements TemplateDirectiveModel {

	@Resource
	private IAssignmentRelationService assignmentRelationService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		String assignmentId = params.get("assignmentId").toString();
		String relationId = params.get("relationId").toString();
		String id = AssignmentRelation.getId(assignmentId, relationId);
		AssignmentRelation assignmentRelation = assignmentRelationService.getAssignmentRelation(id);
		if (assignmentRelation != null) {
			env.setVariable("assignmentRelation", new DefaultObjectWrapper().wrap(assignmentRelation));
		}else{
			env.setVariable("assignmentRelation", new DefaultObjectWrapper().wrap(new AssignmentRelation()));
		}
		body.render(env.getOut());
	}
	
}
