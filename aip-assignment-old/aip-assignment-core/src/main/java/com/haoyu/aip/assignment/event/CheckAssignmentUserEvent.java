package com.haoyu.aip.assignment.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.assignment.entity.AssignmentUser;

public class CheckAssignmentUserEvent extends ApplicationEvent{

	private static final long serialVersionUID = 3267836727199712181L;

	public CheckAssignmentUserEvent(AssignmentUser assignmentUser) {
		super(assignmentUser);
	}

}
