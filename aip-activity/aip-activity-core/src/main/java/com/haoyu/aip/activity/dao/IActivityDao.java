package com.haoyu.aip.activity.dao;

import com.haoyu.aip.activity.entity.Activity;

public interface IActivityDao {

	Activity selectByEntityId(String entityId);

}
