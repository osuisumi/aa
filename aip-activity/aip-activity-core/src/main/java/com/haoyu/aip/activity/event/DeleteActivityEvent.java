package com.haoyu.aip.activity.event;

import org.springframework.context.ApplicationEvent;

public class DeleteActivityEvent extends ApplicationEvent {

	private static final long serialVersionUID = -1919152337102221779L;

	public DeleteActivityEvent(Object source) {
		super(source);
	}

}
