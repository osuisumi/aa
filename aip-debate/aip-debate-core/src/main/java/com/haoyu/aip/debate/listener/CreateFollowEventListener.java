/**
 * 
 */
package com.haoyu.aip.debate.listener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.service.IDebateRelationService;
import com.haoyu.aip.debate.utils.RelationTypeConstants;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.follow.entity.Follow;
import com.haoyu.sip.follow.entity.FollowStat;
import com.haoyu.sip.follow.event.CreateFollowEvent;
import com.haoyu.sip.follow.service.IFollowService;

/**
 * @author lianghuahuang
 *
 */
@Component
public class CreateFollowEventListener implements
		ApplicationListener<CreateFollowEvent> {
	@Autowired
	private IFollowService followService;
	@Autowired
	private IDebateRelationService debateRelationService;
	
	@Async
	@Override
	public void onApplicationEvent(CreateFollowEvent createFollowEvent) {
		Follow follow = (Follow)createFollowEvent.getSource();
		//如果关注的是辩论则更新辩论对象的关注数
		if(follow!=null){
			Relation followEntity = follow.getFollowEntity();
			if(followEntity!=null&&StringUtils.isNotEmpty(followEntity.getType())&&followEntity.getType().equals(RelationTypeConstants.DEBATE_RELATION)){
				FollowStat fs = followService.getFollowStatByFollowEntity(followEntity);
				debateRelationService.updateFollowNum(followEntity.getId(), fs.getFollowNum());
			}
		}		
	}

}
