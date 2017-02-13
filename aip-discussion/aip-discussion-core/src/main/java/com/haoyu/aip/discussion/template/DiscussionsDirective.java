package com.haoyu.aip.discussion.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.status.entity.Status;
import com.haoyu.sip.status.service.IStatusService;
import com.haoyu.sip.utils.Collections3;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class DiscussionsDirective extends AbstractTemplateDirectiveModel{
	
	@Resource
	private IDiscussionService discussionService;
	@Resource
	private IStatusService statusService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		List<Discussion> discussions = discussionService.list(parameter, pageBounds);
		env.setVariable("discussions", new DefaultObjectWrapper().wrap(discussions));
		if(pageBounds != null && pageBounds.isContainsTotalCount()){
			PageList pageList = (PageList)discussions;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		if (Collections3.isNotEmpty(discussions) && parameter.containsKey("getStatus")) {
			boolean status = (boolean) parameter.get("getStatus");
			if (status) {
				List<String> relationIds = Collections3.extractToList(discussions, "id");
				Map<String, Map<String, Status>> statusMap = statusService.mapStatus(relationIds);
				env.setVariable("statusMap", new DefaultObjectWrapper().wrap(statusMap));
			}
		}
		body.render(env.getOut());
	}

}
