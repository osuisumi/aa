package com.haoyu.aip.lessonplan.event;

import org.springframework.context.ApplicationEvent;

public class DeleteLessonPlanRecordEvent extends ApplicationEvent {

	private static final long serialVersionUID = -3222812377474562605L;

	public DeleteLessonPlanRecordEvent(Object source) {
		super(source);
	}

}
