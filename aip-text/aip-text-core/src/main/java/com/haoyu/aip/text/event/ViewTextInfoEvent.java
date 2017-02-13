package com.haoyu.aip.text.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class ViewTextInfoEvent extends ApplicationEvent {

	public ViewTextInfoEvent(Object source) {
		super(source);
	}

}
