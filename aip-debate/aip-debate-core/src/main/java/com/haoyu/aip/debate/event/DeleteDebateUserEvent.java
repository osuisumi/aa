/**
 * 
 */
package com.haoyu.aip.debate.event;

import org.springframework.context.ApplicationEvent;

/**
 * 删除辩论用户事件
 * @author lianghuahuang
 *
 */
public class DeleteDebateUserEvent extends ApplicationEvent {

	public DeleteDebateUserEvent(String id) {
		super(id);
	}

}
