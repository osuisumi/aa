package com.haoyu.aip.lessonplan.event;

import org.springframework.context.ApplicationEvent;

public class DeleteLessonPlanEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3131426075772497920L;

	public DeleteLessonPlanEvent(Object source) {
		super(source);
	}

}
