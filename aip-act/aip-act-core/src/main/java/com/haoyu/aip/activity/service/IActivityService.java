package com.haoyu.aip.activity.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.sip.core.service.Response;

public interface IActivityService {
	
	/**
	 * 获取活动列表
	 * @param activityAttribute 可传参数有relation.id(课程ID或章节ID)
	 * @param getAttribute 是否获取activity的扩展属性, 如为true, 则会获取活动的扩展属性并封装到 attributeMap中
	 * @return
	 */
	List<Activity> listActivity(Map<String, Object> params, boolean getAttribute, PageBounds pageBounds);
	
	/**
	 * 获取单个活动
	 * @param id
	 * @return
	 */
	Activity getActivity(String id);
	
	/**
	 * 创建活动
	 * @param activity, 当attributeMap非空时, 会遍历插入所有activityAttribute, activityAttribute的id由activity.id与attrName MD5生成
	 * @return
	 */
	Response createActivity(Activity activity);
	
	/**
	 * 编辑活动
	 * @param activity, 当attributeMap非空时, 会遍历更新所有activityAttribute, 如更新失败, 则插入
	 * @return
	 */
	Response updateActivity(Activity activity);
	
	/**
	 * 逻辑删除活动
	 * @param activity
	 * @return
	 */
	Response deleteActivityByLogic(Activity activity);

	Activity getActivityByEntityId(String id);

	Response updateActivity(Activity activity, boolean updateTag);

}
