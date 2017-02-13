package com.haoyu.aip.discussion.listener;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.event.DeleteDiscussionPostEvent;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.sip.core.utils.PropertiesLoader;

@Component
public class ClearCache_DeleteDiscussionPostListener implements ApplicationListener<DeleteDiscussionPostEvent>{
	
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private IDiscussionPostService discussionPostService;
	
	@Override
	public void onApplicationEvent(DeleteDiscussionPostEvent event) {
		DiscussionPost discussionPost = (DiscussionPost) event.getSource();
		String discussionRelationId = discussionPost.getDiscussionUser().getDiscussionRelation().getId();
		String key = PropertiesLoader.get("redis.app.key") + ":list_discussion_post:" + discussionRelationId + ":*";
		if (StringUtils.isNotEmpty(discussionRelationId)) {
			Set<String> keys = redisTemplate.keys(key);
			redisTemplate.delete(keys);
		}
	}
}
