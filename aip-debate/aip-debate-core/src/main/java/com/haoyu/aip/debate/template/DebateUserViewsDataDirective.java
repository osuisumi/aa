package com.haoyu.aip.debate.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.service.IDebateUserViewsService;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class DebateUserViewsDataDirective extends AbstractTemplateDirectiveModel {
	@Resource
	private IDebateUserViewsService debateUserViewsService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String, Object> parameter = getSelectParam(params);
		if (parameter.containsKey("debateRelationId")) {
			PageBounds pageBounds = getPageBounds(params);
			DebateUser selectParam = new DebateUser();
			if (parameter.containsKey("argumentId")) {
				DebateArgument debateArgument = new DebateArgument();
				debateArgument.setId(parameter.get("argumentId").toString());
				selectParam.setArgument(debateArgument);
			}

			if (parameter.containsKey("creatorId")) {
				selectParam.setCreator(new User(parameter.get("creatorId").toString()));
			}
			selectParam.setDebateRelation(new DebateRelation(parameter.get("debateRelationId").toString()));

			List<DebateUserViews> debateUserViews = debateUserViewsService.findDebateUserViewsByDebateUser(selectParam, pageBounds);
			env.setVariable("debateUserViews", new DefaultObjectWrapper().wrap(debateUserViews));
			if (pageBounds != null && pageBounds.isContainsTotalCount()) {
				PageList pageList = (PageList) debateUserViews;
				env.setVariable("paginator", new DefaultObjectWrapper().wrap(pageList.getPaginator()));
			}

		}
		body.render(env.getOut());

	}

}
