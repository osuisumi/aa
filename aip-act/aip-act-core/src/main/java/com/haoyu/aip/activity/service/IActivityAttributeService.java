package com.haoyu.aip.activity.service;

import java.util.List;
import java.util.Map;

import com.haoyu.aip.activity.entity.ActivityAttribute;
import com.haoyu.sip.core.service.Response;

public interface IActivityAttributeService {
	
	/**
	 * 创建活动属性
	 * @param activityAttribute
	 * @return
	 */
	Response createActivityAttribute(ActivityAttribute activityAttribute);
	
	/**
	 * 编辑活动属性
	 * @param activityAttribute
	 * @return
	 */
	Response updateActivityAttribute(ActivityAttribute activityAttribute);

	List<ActivityAttribute> listActivityAttribute(List<String> activities);

	Map<String, ActivityAttribute> mapActivityAttribute(String activityId);

}
