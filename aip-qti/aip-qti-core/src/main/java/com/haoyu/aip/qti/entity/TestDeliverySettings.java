/**
 * 
 */
package com.haoyu.aip.qti.entity;

import com.haoyu.sip.core.entity.BaseEntity;
import com.haoyu.sip.core.entity.TimePeriod;

/**
 * @author lianghuahuang
 *
 */
public class TestDeliverySettings extends BaseEntity {
	
	private String id;
	
	private TimePeriod timePeriod;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}
}
