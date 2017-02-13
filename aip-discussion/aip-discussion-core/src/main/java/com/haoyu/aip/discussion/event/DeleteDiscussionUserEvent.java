package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;

public class DeleteDiscussionUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3077728562489439681L;

	public DeleteDiscussionUserEvent(Object source) {
		super(source);
	}

}
