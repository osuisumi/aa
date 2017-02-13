package com.haoyu.aip.activity.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.Activity;

public interface IActivityDao {

	List<Activity> select(Map<String, Object> params, PageBounds pageBounds);

	Activity selectByPrimaryKey(String id);

	int insert(Activity activity);

	int update(Activity activity);

	int deleteByLogic(Map<String, Object> param);

}
