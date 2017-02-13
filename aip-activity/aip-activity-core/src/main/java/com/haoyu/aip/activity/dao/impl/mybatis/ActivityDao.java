package com.haoyu.aip.activity.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.activity.dao.IActivityDao;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class ActivityDao extends MybatisDao implements IActivityDao{

	public int deleteByLogic(Activity object) {
		return delete("deleteByLogic", object);
	}

	@Override
	public Activity selectByEntityId(String entityId) {
		return selectOne("selectByEntityId",entityId);
	}

}
