/**
 * 
 */
package com.haoyu.aip.debate.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.event.CreateDebateUserEvent;

/**
 * 创建辩论用户事件参与人数监听器
 * @author lianghuahuang
 *
 */
@Component  
public class PartiNumCreateDebateUserEventListener implements
		ApplicationListener<CreateDebateUserEvent> {

	@Override
	public void onApplicationEvent(CreateDebateUserEvent createDebateUserEvent) {
		DebateUser debateUser = (DebateUser)createDebateUserEvent.getSource();
		//更新参与人数
	}

}
