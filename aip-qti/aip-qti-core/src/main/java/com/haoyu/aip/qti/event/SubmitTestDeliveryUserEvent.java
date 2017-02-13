/**
 * 
 */
package com.haoyu.aip.qti.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author lianghuahuang
 *
 */
public class SubmitTestDeliveryUserEvent extends ApplicationEvent {

	public SubmitTestDeliveryUserEvent(Object source) {
		super(source);
	}

}
