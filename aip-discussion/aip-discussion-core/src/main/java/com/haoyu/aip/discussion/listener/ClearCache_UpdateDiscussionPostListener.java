package com.haoyu.aip.discussion.listener;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.entity.DiscussionUser;
import com.haoyu.aip.discussion.event.UpdateDiscussionPostEvent;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.aip.discussion.service.IDiscussionUserService;
import com.haoyu.sip.core.utils.PropertiesLoader;

@Component
public class ClearCache_UpdateDiscussionPostListener implements ApplicationListener<UpdateDiscussionPostEvent>{
	
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private IDiscussionPostService discussionPostService;
	@Resource
	private IDiscussionUserService discussionUserService;
	
	@Override
	public void onApplicationEvent(UpdateDiscussionPostEvent event) {
		DiscussionPost discussionPost = (DiscussionPost) event.getSource();
		discussionPost = discussionPostService.get(discussionPost.getId());
		DiscussionUser discussionUser = discussionUserService.get(discussionPost.getDiscussionUser().getId());
		String discussionRelationId = discussionUser.getDiscussionRelation().getId();
		String key = PropertiesLoader.get("redis.app.key") + ":list_discussion_post:" + discussionRelationId + ":*";
		if (StringUtils.isNotEmpty(discussionRelationId)) {
			Set<String> keys = redisTemplate.keys(key);
			redisTemplate.delete(keys);
		}
	}
}
