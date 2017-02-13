/**
 * 
 */
package com.haoyu.aip.debate.event;

import org.springframework.context.ApplicationEvent;

import com.haoyu.aip.debate.entity.DebateUser;

/**
 * @author lianghuahuang
 *
 */
public class CreateDebateUserEvent extends ApplicationEvent {

	public CreateDebateUserEvent(DebateUser debateUser) {
		super(debateUser);
	}

}
