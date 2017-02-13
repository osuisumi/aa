package com.haoyu.aip.activity.event;

import org.springframework.context.ApplicationEvent;

public class UpdateActivityEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8551443029360083394L;

	public UpdateActivityEvent(Object source) {
		super(source);
	}

}
