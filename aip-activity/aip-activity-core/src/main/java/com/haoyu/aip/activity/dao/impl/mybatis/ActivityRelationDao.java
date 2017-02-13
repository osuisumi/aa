package com.haoyu.aip.activity.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.activity.dao.IActivityRelationDao;
import com.haoyu.aip.activity.entity.ActivityRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class ActivityRelationDao extends MybatisDao implements IActivityRelationDao{

	@Override
	public int update(ActivityRelation activityStat) {
		return this.update(activityStat);
	}

	@Override
	public int updateByIdNotSelective(ActivityRelation activityRelation) {
		return this.update("updateByIdNotSelective", activityRelation);
	}

}
