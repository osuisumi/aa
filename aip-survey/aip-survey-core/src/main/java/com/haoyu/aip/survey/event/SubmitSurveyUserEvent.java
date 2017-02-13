package com.haoyu.aip.survey.event;

import org.springframework.context.ApplicationEvent;

public class SubmitSurveyUserEvent extends ApplicationEvent{
	private static final long serialVersionUID = 2518072141091644467L;
	public SubmitSurveyUserEvent(Object source) {
		super(source);
	}
}
