package com.haoyu.aip.discussion.listener;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.aip.discussion.service.IDiscussionRelationService;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.event.CreateFollowEvent;
import com.haoyu.sip.follow.service.IFollowService;

@Async
@Component
public class CreateFollowForDiscussionListener implements ApplicationListener<CreateFollowEvent> {

	@Resource
	private IFollowService followService;
	@Resource
	private IDiscussionRelationService discussionRelationService;
	@Resource
	private IDiscussionService discussionService;

	@Async
	@Override
	public void onApplicationEvent(CreateFollowEvent createFollowEvent) {
		Follow follow = (Follow) createFollowEvent.getSource();
		if (follow != null) {
			Relation followEntity = follow.getFollowEntity();
			if (followEntity != null && StringUtils.isNotEmpty(followEntity.getType()) && followEntity.getType().equals("discussion")) {
				Discussion discussion = discussionService.get(followEntity.getId());
				DiscussionRelation discussionRelation = discussion.getDiscussionRelations().get(0);
				DiscussionRelation dr = new DiscussionRelation();
				dr.setId(discussionRelation.getId());
				dr.setFollowNum(1);
				discussionRelationService.update(dr);
			}
		}
	}

}
