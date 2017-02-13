package com.haoyu.aip.activity.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.haoyu.aip.activity.dao.IActivityAttributeDao;
import com.haoyu.aip.activity.entity.ActivityAttribute;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class ActivityAttributeDao extends MybatisDao implements IActivityAttributeDao{

	@Override
	public int insert(ActivityAttribute activityAttribute) {
		return super.insert(activityAttribute);
	}

	@Override
	public int update(ActivityAttribute activityAttribute) {
		return super.update(activityAttribute);
	}

	@Override
	public Map<String, ActivityAttribute> selectForMap(String activityId) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("activityId", activityId);
		return selectMap("select", param, "attrName");
	}

	@Override
	public List<ActivityAttribute> select(List<String> activities) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("activities", activities);
		return selectList("select", param);
	}

}
