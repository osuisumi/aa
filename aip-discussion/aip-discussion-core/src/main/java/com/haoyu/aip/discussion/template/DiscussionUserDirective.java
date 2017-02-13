package com.haoyu.aip.discussion.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class DiscussionUserDirective extends AbstractTemplateDirectiveModel {

	@Resource
	private IDiscussionService discussionService;
	@Resource
	private IDiscussionPostService discussionPostService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		Discussion discussion = null;
		if(parameter.containsKey("discussionId")){
			String discussionId = (String) parameter.get("discussionId");
			if (parameter.containsKey("op")) {
				PageBounds pageBoundsForOP = new PageBounds(1);
				discussion = discussionService.getDiscussionByOp(parameter,pageBoundsForOP);
			}
			if (discussion == null) {
				discussion = discussionService.get(discussionId);
			}
			env.setVariable("discussionModel", new DefaultObjectWrapper().wrap(discussion));
			
			List<DiscussionPost> discussionPosts = discussionPostService.list(parameter, pageBounds);
			env.setVariable("discussionPostsModel", new DefaultObjectWrapper().wrap(discussionPosts));
			if (pageBounds != null && pageBounds.isContainsTotalCount()) {
				PageList pageList = (PageList)discussionPosts;
				env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}
		}
		body.render(env.getOut());
	}

}
