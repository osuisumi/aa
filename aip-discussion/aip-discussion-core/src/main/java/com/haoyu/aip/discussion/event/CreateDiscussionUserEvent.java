package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;

public class CreateDiscussionUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5576996730782810785L;

	public CreateDiscussionUserEvent(Object source) {
		super(source);
	}

}
