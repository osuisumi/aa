package com.haoyu.aip.activity.dao;

import java.util.Map;

public interface IActivityResultDao {

	int getCount(Map<String, Object> paramMap);

	int deleteByPhysics(String id);

}
