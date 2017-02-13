package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;
/** 
 * 回复帖子事件
 */
public class CreateDiscussionPostEvent extends ApplicationEvent {

	private static final long serialVersionUID = -6123413024993745550L;

	public CreateDiscussionPostEvent(Object source) {
		super(source);
	}

}
