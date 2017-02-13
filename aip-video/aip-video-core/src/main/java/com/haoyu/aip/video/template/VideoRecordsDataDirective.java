package com.haoyu.aip.video.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.tempuri.MessageResultOfListOfAppVideo;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Maps;
import com.haoyu.aip.video.service.IVideoRecordService;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class VideoRecordsDataDirective implements TemplateDirectiveModel {

	@Resource
	private IVideoRecordService videoRecordService;
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		PageBounds pageBounds = new PageBounds();
		if (params.containsKey("page")  && params.get("page") != null) {
			String page = params.get("page").toString();
			pageBounds.setPage(Integer.valueOf(page));
		}
		if (params.containsKey("limit")  && params.get("limit") != null) {
			String limit = params.get("limit").toString();
			pageBounds.setLimit(Integer.valueOf(limit));
			pageBounds.setContainsTotalCount(true);
		}else{
			pageBounds.setLimit(Integer.MAX_VALUE);
		}
		if (params.containsKey("orders")  && params.get("orders") != null) {
			String orders = params.get("orders").toString();
			pageBounds.setOrders(Order.formString(orders));
		}
		
		Map<String, Object> param = Maps.newHashMap();
		if (params.containsKey("name") && StringUtils.isNotEmpty(params.get("name").toString())) {
			param.put("Name", params.get("name").toString());
		}
		if (params.containsKey("author") && StringUtils.isNotEmpty(params.get("author").toString())) {
			param.put("Author", params.get("author").toString());
		}
		if (params.containsKey("parentCategory") && StringUtils.isNotEmpty(params.get("parentCategory").toString())) {
			param.put("ParentCategory", params.get("parentCategory").toString());
		}
		if (params.containsKey("parentGrade") && StringUtils.isNotEmpty(params.get("parentGrade").toString())) {
			param.put("ParentGrade", params.get("parentGrade").toString());
		}
		if (params.containsKey("recTimeGe") && StringUtils.isNotEmpty(params.get("recTimeGe").toString())) {
			param.put("RecTimeGe", params.get("recTimeGe").toString());
		}
		if (params.containsKey("recTimeLe") && StringUtils.isNotEmpty(params.get("recTimeLe").toString())) {
			param.put("RecTimeLe", params.get("recTimeLe").toString());
		}
		MessageResultOfListOfAppVideo result = videoRecordService.getMessageResultOfListOfAppVideo(param, pageBounds);
		env.setVariable("videoRecords", new DefaultObjectWrapper().wrap(result.getData()));
		if (pageBounds.isContainsTotalCount() && result.getData() != null) {
			Paginator paginator = new Paginator(result.getQuery().getPageIndex(), result.getQuery().getPageSize(), result.getQuery().getRecordCount());
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(paginator));
		}
		body.render(env.getOut());
	}

}