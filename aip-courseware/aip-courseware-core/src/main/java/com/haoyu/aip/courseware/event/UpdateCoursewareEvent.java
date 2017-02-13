package com.haoyu.aip.courseware.event;

import org.springframework.context.ApplicationEvent;

public class UpdateCoursewareEvent extends ApplicationEvent{
	private static final long serialVersionUID = 8624985818116589079L;
	public UpdateCoursewareEvent(Object source) {
		super(source);
	}

}
