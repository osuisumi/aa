package com.haoyu.aip.assignment.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.assignment.entity.Assignment;

public class CreateAssignmentEvent extends ApplicationEvent{

	private static final long serialVersionUID = 4184056964747695540L;

	public CreateAssignmentEvent(Assignment assignment) {
		super(assignment);
	}

}
