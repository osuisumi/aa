package com.haoyu.aip.evaluate.event;

import org.springframework.context.ApplicationEvent;

public class UpdateEvaluateStatusEvent extends ApplicationEvent {

	private static final long serialVersionUID = 3666722786385782133L;

	public UpdateEvaluateStatusEvent(Object source) {
		super(source);
	}

}
