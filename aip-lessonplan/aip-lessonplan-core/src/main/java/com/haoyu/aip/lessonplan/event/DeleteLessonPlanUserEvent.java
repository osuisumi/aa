package com.haoyu.aip.lessonplan.event;

import org.springframework.context.ApplicationEvent;

public class DeleteLessonPlanUserEvent extends ApplicationEvent {

	private static final long serialVersionUID = -7426538879037748675L;

	public DeleteLessonPlanUserEvent(Object source) {
		super(source);
	}

}
