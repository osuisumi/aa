package com.haoyu.aip.activity.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.sip.core.service.Response;

public interface IActivityResultService {

	/**
	 * 创建活动成绩, 默认首次进入活动时创建
	 * @param activityResult
	 * @return
	 */
	Response createActivityResult(ActivityResult activityResult);
	
	/**
	 * 编辑活动成绩
	 * @param activityResult
	 * @return
	 */
	Response updateActivityResult(ActivityResult activityResult);

	ActivityResult createIfNotExists(String activityId, String relationId);
	
	ActivityResult createIfNotExists(String activityId,String relationId,String userId);

	ActivityResult getActivityResult(String id);

	Map<String, ActivityResult> mapActivityResult(Map<String, Object> param, PageBounds pageBounds);

	List<ActivityResult> listActivityResult(Map<String, Object> param, PageBounds pageBounds);
	
}
