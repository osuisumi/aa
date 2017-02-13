package com.haoyu.aip.assignment.event;

import org.springframework.context.ApplicationEvent;

public class SubmitAssignmentUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4333561610729447617L;

	public SubmitAssignmentUserEvent(Object source) {
		super(source);
	}

}
