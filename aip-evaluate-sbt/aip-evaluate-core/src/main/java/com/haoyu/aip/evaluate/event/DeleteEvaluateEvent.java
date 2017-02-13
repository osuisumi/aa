package com.haoyu.aip.evaluate.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.evaluate.entity.Evaluate;


public class DeleteEvaluateEvent extends ApplicationEvent{
	private static final long serialVersionUID = 5113822129353790084L;

	public DeleteEvaluateEvent(Evaluate source) {
		super(source);
	}

}
