package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;

public class DeleteDiscussionPostEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3077728562489439681L;

	public DeleteDiscussionPostEvent(Object source) {
		super(source);
	}

}
