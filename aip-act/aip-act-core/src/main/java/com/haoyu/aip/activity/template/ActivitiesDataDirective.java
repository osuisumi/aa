package com.haoyu.aip.activity.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.aip.activity.service.IActivityResultService;
import com.haoyu.aip.activity.service.IActivityService;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Collections3;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class ActivitiesDataDirective implements TemplateDirectiveModel {

	@Resource
	private IActivityService activityService;
	@Resource
	private IActivityResultService activityResultService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> param = Maps.newHashMap();
		if (params.containsKey("relationId") && StringUtils.isNotEmpty(params.get("relationId").toString()) || params.containsKey("relationIds")) {
			if (params.containsKey("relationId")) {
				param.put("relationId", params.get("relationId").toString());
			}
			if (params.containsKey("relationIds")) {
				SimpleSequence model = (SimpleSequence) params.get("relationIds");
				param.put("relationIds", model.toList());
			}
			if (params.containsKey("type")) {
				param.put("type", params.get("type").toString());
			}
			if(params.containsKey("state")){
				param.put("state", params.get("state").toString());
			}
			PageBounds pageBounds = new PageBounds();
			pageBounds.setLimit(Integer.MAX_VALUE);
			pageBounds.setOrders(Order.formString("SORT_NO,CREATE_TIME"));
			List<Activity> activities = activityService.listActivity(param, true, pageBounds);
			
			//如果是查询多个节下的活动, 按照节的顺序进行排序; 单个节下的活动, 按照活动本身进行排序
			if (params.containsKey("relationIds")) {
				List<Activity> activities2 = Lists.newArrayList();
				SimpleSequence model = (SimpleSequence) params.get("relationIds");
				List<String> relationIds = model.toList();
				for (String relationId : relationIds) {
					for (Activity activity : activities) {
						if (activity.getRelation().getId().equals(relationId)) {
							activities2.add(activity);
						}
					}
				}
				env.setVariable("activities", new DefaultObjectWrapper().wrap(activities2));
			}else{
				env.setVariable("activities", new DefaultObjectWrapper().wrap(activities));
			}
			
			//如果getResult=true, 查询当前登录用户的活动完成情况
			if (Collections3.isNotEmpty(activities) && params.containsKey("getResult")) {
				TemplateBooleanModel model = (TemplateBooleanModel) params.get("getResult");
				if (model != null) {
					boolean getResult = model.getAsBoolean();
					if (getResult) {
						List<String> activityIds = Collections3.extractToList(activities, "id");
						param = Maps.newHashMap();
						param.put("activityIds", activityIds);
						if (params.containsKey("userId") && StringUtils.isNotEmpty(params.get("userId").toString())) {
							param.put("creator", params.get("userId").toString());
						}else{
							param.put("creator", ThreadContext.getUser().getId());
						}
						Map<String, ActivityResult> activityResultMap = activityResultService.mapActivityResult(param, null);
						env.setVariable("activityResultMap", new DefaultObjectWrapper().wrap(activityResultMap));
					}
				}
			}
		}
		body.render(env.getOut());
	}

}
