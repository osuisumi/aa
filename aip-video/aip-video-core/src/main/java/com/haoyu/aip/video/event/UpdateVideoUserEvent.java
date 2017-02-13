package com.haoyu.aip.video.event;

import org.springframework.context.ApplicationEvent;

public class UpdateVideoUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = 829688363191117L;

	public UpdateVideoUserEvent(Object source) {
		super(source);
	}

}
