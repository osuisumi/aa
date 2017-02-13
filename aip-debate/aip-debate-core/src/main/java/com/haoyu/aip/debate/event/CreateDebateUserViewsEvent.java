/**
 * 
 */
package com.haoyu.aip.debate.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.debate.entity.DebateUserViews;

/**
 * 创建辩论观点事件
 * @author lianghuahuang
 *
 */
public class CreateDebateUserViewsEvent extends ApplicationEvent {

	public CreateDebateUserViewsEvent(DebateUserViews debateUserViews) {
		super(debateUserViews);
	}

}
