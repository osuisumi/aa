package com.haoyu.aip.lessonplan.event;

import org.springframework.context.ApplicationEvent;

public class CreateLessonPlanRecordEvent extends ApplicationEvent {

	private static final long serialVersionUID = -8222010762066733648L;

	public CreateLessonPlanRecordEvent(Object source) {
		super(source);
	}

}
