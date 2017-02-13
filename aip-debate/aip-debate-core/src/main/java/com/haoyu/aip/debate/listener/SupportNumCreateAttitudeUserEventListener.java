/**
 * 
 */
package com.haoyu.aip.debate.listener;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.service.IDebateUserViewsService;
import com.haoyu.aip.debate.utils.AttitudeConstants;
import com.haoyu.aip.debate.utils.RelationTypeConstants;
import com.haoyu.sip.attitude.entity.AttitudeStat;
import com.haoyu.sip.attitude.entity.AttitudeUser;
import com.haoyu.sip.attitude.event.CreateAttitudeUserEvent;
import com.haoyu.sip.attitude.service.IAttitudeService;

/**
 * @author lianghuahuang
 *
 */
@Component
public class SupportNumCreateAttitudeUserEventListener implements
		ApplicationListener<CreateAttitudeUserEvent> {
	@Autowired
	private IDebateUserViewsService debateUserViewsService;
	@Autowired
	private IAttitudeService attitudeService;
	@Override
	public void onApplicationEvent(CreateAttitudeUserEvent createAttitudeUserEvent) {
		AttitudeUser au = (AttitudeUser)createAttitudeUserEvent.getSource();
		//更新观点的支持数
		if(au.getRelation().getType()!=null&&au.getRelation().getType().equals(RelationTypeConstants.DEBATE_USER_VIEWS)){
			//获取点赞数
			Map<String,AttitudeStat> attitudeStat = attitudeService.getAttitudeStatByAttitudeAndRelation(AttitudeConstants.SUPPORT, au.getRelation());
			if(attitudeStat.containsKey(AttitudeConstants.SUPPORT)){
				debateUserViewsService.updateDebateUserViewsSupportNum(au.getRelation().getId(),attitudeStat.get(AttitudeConstants.SUPPORT).getParticipateNum());
			}
			
		}
	}

}
