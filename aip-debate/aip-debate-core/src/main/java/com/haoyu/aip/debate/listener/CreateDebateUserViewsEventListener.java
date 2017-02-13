/**
 * 
 */
package com.haoyu.aip.debate.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.event.CreateDebateUserViewsEvent;
import com.haoyu.aip.debate.service.IDebateArgumentStatService;
import com.haoyu.aip.debate.service.IDebateUserService;

/**
 * @author lianghuahuang
 *
 */
@Component  
public class CreateDebateUserViewsEventListener implements
		ApplicationListener<CreateDebateUserViewsEvent> {
	@Autowired
	private IDebateUserService debateUserService;
	@Autowired
	private IDebateArgumentStatService debateArgumentStatService;
	
	@Async
	@Override
	public void onApplicationEvent(CreateDebateUserViewsEvent createDebateUserViewsEvent) {
		DebateUserViews debateUserViews = (DebateUserViews)createDebateUserViewsEvent.getSource();
		//更新debateUser的用户的观点数
		debateUserService.updateViewsNum(debateUserViews.getDebateUser().getId());
		//更新DebateArgumentStat中的观点数
		debateArgumentStatService.updateViewsNum(debateUserViews.getDebateUser());
	}

}
