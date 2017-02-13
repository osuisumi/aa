package com.haoyu.aip.lessonplan.event;

import org.springframework.context.ApplicationEvent;

public class CreateLessonPlanUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1394438385717291256L;

	public CreateLessonPlanUserEvent(Object source) {
		super(source);
	}

}
