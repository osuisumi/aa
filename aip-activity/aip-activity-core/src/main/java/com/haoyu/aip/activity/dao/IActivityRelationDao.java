package com.haoyu.aip.activity.dao;

import com.haoyu.aip.activity.entity.ActivityRelation;

public interface IActivityRelationDao {

	int update(ActivityRelation activityRelation);

	int updateByIdNotSelective(ActivityRelation activityRelation);

}
