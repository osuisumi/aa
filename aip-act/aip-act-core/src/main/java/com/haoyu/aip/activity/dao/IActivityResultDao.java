package com.haoyu.aip.activity.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.ActivityResult;

public interface IActivityResultDao {

	ActivityResult selectByPrimaryKey(String id);

	int insert(ActivityResult activityResult);

	int update(ActivityResult activityResult);

	Map<String, ActivityResult> selectForMap(Map<String, Object> param, PageBounds pageBounds);

	List<ActivityResult> select(Map<String, Object> param, PageBounds pageBounds);

}
