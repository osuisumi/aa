package com.haoyu.aip.activity.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.aip.activity.service.IActivityResultService;
import com.haoyu.sip.core.utils.ThreadContext;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class ActivityResultsDataDirective implements TemplateDirectiveModel {

	@Resource
	private IActivityResultService activityResultService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> param = Maps.newHashMap();
		if (params.containsKey("relationId")) {
			param.put("relationId", params.get("relationId").toString());
		}
		param.put("creator", ThreadContext.getUser().getId());
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(Integer.MAX_VALUE);
		pageBounds.setOrders(Order.formString("ACTIVITY_CREATE_TIME"));
		List<ActivityResult> activityResults = activityResultService.listActivityResult(param, pageBounds);
		env.setVariable("activityResults", new DefaultObjectWrapper().wrap(activityResults));
		body.render(env.getOut());
	}

}
