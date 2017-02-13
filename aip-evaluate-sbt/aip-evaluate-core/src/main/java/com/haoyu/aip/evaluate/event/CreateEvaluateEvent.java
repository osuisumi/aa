package com.haoyu.aip.evaluate.event;


import org.springframework.context.ApplicationEvent;
import com.haoyu.aip.evaluate.entity.Evaluate;


public class CreateEvaluateEvent extends ApplicationEvent{
	private static final long serialVersionUID = 610711380969631557L;

	public CreateEvaluateEvent(Evaluate evaluate) {
		super(evaluate);
	}

}
