package com.haoyu.aip.activity.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.dao.IActivityResultDao;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class ActivityResultDao extends MybatisDao implements IActivityResultDao{

	@Override
	public ActivityResult selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insert(ActivityResult activityResult) {
		return super.insert(activityResult);
	}

	@Override
	public int update(ActivityResult activityResult) {
		return super.update(activityResult);
	}

	@Override
	public Map<String, ActivityResult> selectForMap(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectMap("select", param, "activity.id", pageBounds);
	}

	@Override
	public List<ActivityResult> select(Map<String, Object> param, PageBounds pageBounds) {
		return super.selectList("select", param, pageBounds);
	}

}
