package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;

public class UpdateDiscussionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1896779202176661208L;

	public UpdateDiscussionEvent(Object source) {
		super(source);
	}

}
