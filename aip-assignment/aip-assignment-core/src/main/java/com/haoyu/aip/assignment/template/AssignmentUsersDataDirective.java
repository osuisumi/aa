package com.haoyu.aip.assignment.template;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.SimpleSequence;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class AssignmentUsersDataDirective implements TemplateDirectiveModel {

	@Resource
	private IAssignmentService assignmentService;
	@Resource
	private IAssignmentUserService assignmentUserService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = null;
		if (params.containsKey("page")  && params.get("page") != null) {
			pageBounds = new PageBounds();
			String page = params.get("page").toString();
			pageBounds.setPage(Integer.valueOf(page));
		}
		if (params.containsKey("limit")  && params.get("limit") != null) {
			String limit = params.get("limit").toString();
			pageBounds.setLimit(Integer.valueOf(limit));
			pageBounds.setContainsTotalCount(true);
		}
		if (params.containsKey("orders")  && params.get("orders") != null) {
			String orders = params.get("orders").toString();
			pageBounds.setOrders(Order.formString(orders));
		}
		
		Map<String, Object> param = Maps.newHashMap();
		if (params.containsKey("relationId") && StringUtils.isNotEmpty(params.get("relationId").toString())) {
			param.put("relationId", params.get("relationId").toString());
			if(params.containsKey("assignmentId") && StringUtils.isNotEmpty(params.get("assignmentId").toString())){
				param.put("assignmentId", params.get("assignmentId").toString());
			}else if(params.containsKey("assignmentIds")){
				SimpleSequence model = (SimpleSequence) params.get("assignmentIds");
				List<String> assignmentIds = model.toList();
				param.put("assignmentIds", assignmentIds);
			}
			if (params.containsKey("assignmentMarkType") && StringUtils.isNotEmpty(params.get("assignmentMarkType").toString())) {
				param.put("assignmentMarkType", params.get("assignmentMarkType").toString());
			}
			if (params.containsKey("state") && StringUtils.isNotEmpty(params.get("state").toString())) {
				param.put("state", params.get("state").toString());
			}
			if (params.containsKey("marker") && StringUtils.isNotEmpty(params.get("marker").toString())) {
				param.put("marker", params.get("marker").toString());
			}
			if (params.containsKey("responseStartTime") && StringUtils.isNotEmpty(params.get("responseStartTime").toString())) {
				String date = params.get("responseStartTime").toString();
				try {
					param.put("responseStartTime", DateUtils.parseDate(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (params.containsKey("responseEndTime") && StringUtils.isNotEmpty(params.get("responseEndTime").toString())) {
				String date = params.get("responseEndTime").toString();
				try {
					param.put("responseEndTime", DateUtils.parseDate(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (params.containsKey("realName") && StringUtils.isNotEmpty(params.get("realName").toString())) {
				param.put("realName", params.get("realName").toString());
			}
			List<AssignmentUser> assignmentUsers = assignmentUserService.listAssignmentUser(param, pageBounds);
			env.setVariable("assignmentUsers", new DefaultObjectWrapper().wrap(assignmentUsers));
			if (pageBounds != null && pageBounds.isContainsTotalCount()) {
				PageList pageList = (PageList)assignmentUsers;
				env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
		}
		body.render(env.getOut());
	}
	
}
