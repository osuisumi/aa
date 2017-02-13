package com.haoyu.aip.discussion.listener;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.aip.discussion.service.IDiscussionRelationService;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.attitude.event.CreateAttitudeUserEvent;

@Async
@Component
public class CreateAttitudeForDiscussionListener implements ApplicationListener<CreateAttitudeUserEvent>{
	@Resource
	private IDiscussionService discussionService;
	@Resource
	private IDiscussionRelationService discussionRelationService;
	@Resource
	private IDiscussionPostService discussionPostService;

	@Override
	public void onApplicationEvent(CreateAttitudeUserEvent event) {
		AttitudeUser attitudeUser = (AttitudeUser) event.getSource();
		if(!StringUtils.isEmpty(attitudeUser.getRelation().getType())){
			if(attitudeUser.getRelation().getType().equals("discussion")){
				Discussion discussion = discussionService.get(attitudeUser.getRelation().getId());
				DiscussionRelation discussionRelation = discussion.getDiscussionRelations().get(0);
				DiscussionRelation dr = new DiscussionRelation();
				dr.setId(discussionRelation.getId());
				discussionRelationService.updateSupportNum(dr);
			}else if (attitudeUser.getRelation().getType().equals("discussion_post")) {
				DiscussionPost discussionPost = new DiscussionPost();
				discussionPost.setId(attitudeUser.getRelation().getId());
				discussionPost.setSupportNum(1);
				discussionPostService.update(discussionPost);
			}
		}
	}

}
