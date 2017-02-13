package com.haoyu.aip.courseware.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.courseware.entity.Courseware;

public class DeleteCoursewareEvent extends ApplicationEvent{
	private static final long serialVersionUID = -9193291966249453597L;

	public DeleteCoursewareEvent(Courseware courseware) {
		super(courseware);
	}

}
