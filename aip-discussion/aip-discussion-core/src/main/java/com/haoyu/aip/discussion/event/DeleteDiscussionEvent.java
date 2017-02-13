package com.haoyu.aip.discussion.event;

import org.springframework.context.ApplicationEvent;

public class DeleteDiscussionEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1225947330044359574L;

	public DeleteDiscussionEvent(Object source) {
		super(source);
	}

}
