package com.haoyu.aip.discussion.template;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.sip.core.freemarker.AbstractTemplateDirectiveModel;
import com.haoyu.sip.core.utils.PropertiesLoader;

import freemarker.core.Environment;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class DiscussionPostsDirective extends AbstractTemplateDirectiveModel{
	@Resource
	private IDiscussionPostService discussionPostService;
	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Map<String,Object> parameter = getSelectParam(params);
		PageBounds pageBounds = getPageBounds(params);
		
		String discussionRelationId = (String) parameter.get("discussionRelationId");
		String key = PropertiesLoader.get("redis.app.key") + ":list_discussion_post:" + discussionRelationId + ":" + DigestUtils.md5Hex(parameter.toString() + pageBounds.toString());
		ValueOperations<String, List<DiscussionPost>> valueOper = redisTemplate.opsForValue();
		List<DiscussionPost> discussionPosts = Lists.newArrayList();
		if(redisTemplate.hasKey(key)){
			discussionPosts = valueOper.get(key);
			env.setVariable("discussionPosts", new DefaultObjectWrapper().wrap(discussionPosts));
		}else{
			discussionPosts = discussionPostService.list(parameter, pageBounds);
			env.setVariable("discussionPosts", new DefaultObjectWrapper().wrap(discussionPosts));
			valueOper.set(key, discussionPosts);
			redisTemplate.expire(key, 2, TimeUnit.HOURS);
		}
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)discussionPosts;
			env.setVariable("paginator" , new DefaultObjectWrapper().wrap(pageList.getPaginator()));
		}
		body.render(env.getOut());
	}

}
