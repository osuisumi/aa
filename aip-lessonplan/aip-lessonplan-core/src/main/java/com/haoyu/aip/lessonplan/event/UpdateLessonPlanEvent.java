package com.haoyu.aip.lessonplan.event;

import org.springframework.context.ApplicationEvent;

public class UpdateLessonPlanEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4862515228889156915L;

	public UpdateLessonPlanEvent(Object source) {
		super(source);
	}

}
