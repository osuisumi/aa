package com.haoyu.aip.discussion.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.aip.discussion.event.CreateDiscussionPostEvent;
import com.haoyu.aip.discussion.service.IDiscussionRelationService;
import com.haoyu.sip.core.utils.PropertiesLoader;

@Component
public class UpdateDiscussionRelation_DeleteDiscussionPostListener implements ApplicationListener<CreateDiscussionPostEvent>{
	
	@Resource
	private RedisTemplate redisTemplate;
	@Resource
	private IDiscussionRelationService discussionRelationService;
	
	@Override
	public void onApplicationEvent(CreateDiscussionPostEvent event) {
		DiscussionPost discussionPost = (DiscussionPost) event.getSource();
		//更新统计
		DiscussionRelation discussionRelation = discussionPost.getDiscussionUser().getDiscussionRelation();
		String key = PropertiesLoader.get("redis.app.key") + ":disp:" + discussionRelation.getId(); 
		ValueOperations<String,Long> valueOper = redisTemplate.opsForValue();
		boolean updateFlag = false;
		long now = System.currentTimeMillis();
		if(redisTemplate.hasKey(key)){
			Long lastUpdateTime = valueOper.get(key);				
			if(now - lastUpdateTime > 5 * 1000){
				updateFlag = true;
			}
		}else{
			valueOper.set(key, now);
			updateFlag = true;
		}
		if(updateFlag){
			valueOper.set(key, now);
			discussionRelation.setLastPost(new DiscussionPost());	
			discussionRelation.setReplyNum(1);
			discussionRelation.setParticipateNum(1);
			discussionRelationService.update(discussionRelation);
		}
	}
}
