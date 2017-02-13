package com.haoyu.aip.debate.event;

import org.springframework.context.ApplicationEvent;

public class DeleteDebateEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3568254567345946645L;

	public DeleteDebateEvent(Object source) {
		super(source);
	}

}
