package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;

public class CreateDiscussionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -6123413024993745550L;

	public CreateDiscussionEvent(Object source) {
		super(source);
	}

}
