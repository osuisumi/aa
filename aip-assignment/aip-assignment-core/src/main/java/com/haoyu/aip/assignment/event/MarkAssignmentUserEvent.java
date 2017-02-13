package com.haoyu.aip.assignment.event;

import org.springframework.context.ApplicationEvent;

public class MarkAssignmentUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3498292271074138725L;

	public MarkAssignmentUserEvent(Object source) {
		super(source);
	}

}
