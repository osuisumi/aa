package com.haoyu.aip.assignment.event;

import org.springframework.context.ApplicationEvent;

public class CreateAssignmentUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = -2848917272970468523L;

	public CreateAssignmentUserEvent(Object source) {
		super(source);
	}

}
