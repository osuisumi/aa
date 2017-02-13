/**
 * 
 */
package com.haoyu.aip.debate.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.event.DeleteDebateUserViewsEvent;
import com.haoyu.aip.debate.service.IDebateArgumentStatService;

/**
 * @author lianghuahuang
 *
 */
@Component
public class DeleteDebateUserViewsListener implements
		ApplicationListener<DeleteDebateUserViewsEvent> {
	@Autowired
	private IDebateArgumentStatService debateArgumentStatService;
	
	@Async
	@Override
	public void onApplicationEvent(DeleteDebateUserViewsEvent source) {
		DebateUserViews duv = (DebateUserViews)source.getSource();
		//更新DebateArugmentStat的观点数
		debateArgumentStatService.updateViewsNum(duv.getDebateUser());
	}

}
