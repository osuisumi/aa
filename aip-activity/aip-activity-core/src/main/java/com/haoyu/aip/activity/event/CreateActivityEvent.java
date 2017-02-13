package com.haoyu.aip.activity.event;

import org.springframework.context.ApplicationEvent;

public class CreateActivityEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4542309047436307507L;

	public CreateActivityEvent(Object source) {
		super(source);
	}

}
