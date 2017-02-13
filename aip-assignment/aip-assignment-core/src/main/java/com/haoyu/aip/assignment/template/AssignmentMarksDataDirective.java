package com.haoyu.aip.assignment.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.aip.assignment.service.IAssignmentMarkService;
import com.haoyu.sip.core.utils.ThreadContext;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AssignmentMarksDataDirective implements TemplateDirectiveModel {


	@Resource
	private IAssignmentMarkService assignmentMarkService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> param = Maps.newHashMap();
		if(params.containsKey("assignmentRelationId")){
			String assignmentRelationId = params.get("assignmentRelationId").toString();
			param.put("assignmentRelationId", assignmentRelationId);
		}
		param.put("userId", ThreadContext.getUser().getId());
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(Integer.MAX_VALUE);
		pageBounds.setOrders(Order.formString("STATE"));
		List<AssignmentMark> assignmentMarks = assignmentMarkService.listAssignmentMark(param, pageBounds);
		env.setVariable("assignmentMarks", new DefaultObjectWrapper().wrap(assignmentMarks));
		body.render(env.getOut());
	}
}
