package com.haoyu.aip.discussion.template;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.aip.discussion.service.IDiscussionRelationService;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.utils.Collections3;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component
public class DiscussionDirective extends AbstractTemplateDirectiveModel {

	@Resource
	private IDiscussionService discussionService;
	@Resource
	private IDiscussionRelationService discussionRelationService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		Discussion discussion = null;
		if(parameter.containsKey("id")){
			String id = (String) parameter.get("id");
			if (parameter.containsKey("op")) {
				PageBounds pageBoundsForOP = new PageBounds(1);
				discussion = discussionService.getDiscussionByOp(parameter,pageBoundsForOP);
			}else{
				discussion = discussionService.get(id);
			}
			if (discussion != null) {
				if (parameter.containsKey("updateBrowseNum")) {
					boolean updateBrowseNum = (boolean) parameter.get("updateBrowseNum");
					if (updateBrowseNum) {
						if (Collections3.isNotEmpty(discussion.getDiscussionRelations())) {
							for(DiscussionRelation discussionRelation : discussion.getDiscussionRelations()){
								DiscussionRelation dr = new DiscussionRelation();
								dr.setId(discussionRelation.getId());
								Runnable r = ()->{
									try {
										Thread.sleep(RandomUtils.nextInt(5000, 15000));
										discussionRelationService.updateBrowseNum(dr);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								};
								new Thread(r).start();
							}
						}
					}
				}
			}
			env.setVariable("discussionModel", new DefaultObjectWrapper().wrap(discussion));
		}
		body.render(env.getOut());
	}

}
