package com.haoyu.aip.courseware.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.courseware.entity.Courseware;

public class CreateCoursewareEvent extends ApplicationEvent{
	private static final long serialVersionUID = 6551756283390976008L;
	public CreateCoursewareEvent(Courseware source) {
		super(source);
	}


}
