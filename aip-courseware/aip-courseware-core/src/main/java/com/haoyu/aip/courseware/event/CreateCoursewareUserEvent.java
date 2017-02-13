package com.haoyu.aip.courseware.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.courseware.entity.CoursewareUser;

public class CreateCoursewareUserEvent extends ApplicationEvent{
	private static final long serialVersionUID = 3906444068107584687L;

	public CreateCoursewareUserEvent(CoursewareUser coursewareUser) {
		super(coursewareUser);
	}

}
