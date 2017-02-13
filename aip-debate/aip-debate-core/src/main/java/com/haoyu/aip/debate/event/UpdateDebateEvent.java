/**
 * 
 */
package com.haoyu.aip.debate.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.debate.entity.Debate;

/**
 * 更新辩论事件
 * @author lianghuahuang
 *
 */
public class UpdateDebateEvent extends ApplicationEvent {

	public UpdateDebateEvent(Debate debate) {
		super(debate);
	}

}
