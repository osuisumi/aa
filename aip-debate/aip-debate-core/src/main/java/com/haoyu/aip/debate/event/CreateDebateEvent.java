/**
 * 
 */
package com.haoyu.aip.debate.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.debate.entity.Debate;

/**
 * 创建辩论事件
 * @author lianghuahuang
 *
 */
public class CreateDebateEvent extends ApplicationEvent {

	public CreateDebateEvent(Debate debate) {
			super(debate);
	}

}
