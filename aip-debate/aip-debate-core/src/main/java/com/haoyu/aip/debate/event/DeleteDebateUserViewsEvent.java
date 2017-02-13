/**
 * 
 */
package com.haoyu.aip.debate.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.debate.entity.DebateUserViews;

/**
 * 删除辩论观点事件
 * @author lianghuahuang
 *
 */
public class DeleteDebateUserViewsEvent extends ApplicationEvent {

	public DeleteDebateUserViewsEvent(DebateUserViews debateUserViews) {
		super(debateUserViews);
	}

}
