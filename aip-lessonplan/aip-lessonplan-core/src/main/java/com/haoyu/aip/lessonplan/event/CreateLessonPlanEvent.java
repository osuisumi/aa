package com.haoyu.aip.lessonplan.event;

import org.springframework.context.ApplicationEvent;

public class CreateLessonPlanEvent extends ApplicationEvent {

	private static final long serialVersionUID = 8564287151763537786L;

	public CreateLessonPlanEvent(Object source) {
		super(source);
	}

}
