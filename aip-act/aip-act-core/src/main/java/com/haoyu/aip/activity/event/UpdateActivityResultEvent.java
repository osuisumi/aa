package com.haoyu.aip.activity.event;

import org.springframework.context.ApplicationEvent;

public class UpdateActivityResultEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4784238741600702716L;

	public UpdateActivityResultEvent(Object source) {
		super(source);
	}

}
