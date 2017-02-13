package com.haoyu.aip.discussion.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.web.SearchParam;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class DiscussionsDataDirective implements TemplateDirectiveModel{
	@Resource
	private IDiscussionService discussionService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if(params.containsKey("searchParam")){
			BeanModel discussionBeanModel = (BeanModel) params.get("searchParam");
			SearchParam searchParam = (SearchParam) discussionBeanModel.getWrappedObject();
			PageBounds pageBounds = null;
			if (params.containsKey("pageBounds")) {
				BeanModel beanModel = (BeanModel) params.get("pageBounds");
				pageBounds = (PageBounds) beanModel.getWrappedObject();
			}
			List<Discussion> discussions = discussionService.list(searchParam, pageBounds);
			if(pageBounds != null && pageBounds.isContainsTotalCount()){
				PageList pageList = (PageList)discussions;
				env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
			env.setVariable("discussions", new DefaultObjectWrapper().wrap(discussions));
		}
		body.render(env.getOut());
		
	}

}
