package com.haoyu.aip.activity.dao;

import java.util.List;
import java.util.Map;

import com.haoyu.aip.activity.entity.ActivityAttribute;

public interface IActivityAttributeDao {

	int insert(ActivityAttribute activityAttribute);

	int update(ActivityAttribute activityAttribute);

	Map<String, ActivityAttribute> selectForMap(String activityId);

	List<ActivityAttribute> select(List<String> activities);

}
