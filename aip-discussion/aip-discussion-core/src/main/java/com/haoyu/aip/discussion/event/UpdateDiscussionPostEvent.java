package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;

public class UpdateDiscussionPostEvent extends ApplicationEvent {

	private static final long serialVersionUID = 7317317829118790543L;

	public UpdateDiscussionPostEvent(Object source) {
		super(source);
	}

}
