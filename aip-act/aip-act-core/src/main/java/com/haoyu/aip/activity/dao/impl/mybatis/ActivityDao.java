package com.haoyu.aip.activity.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.dao.IActivityDao;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class ActivityDao extends MybatisDao implements IActivityDao{

	@Override
	public List<Activity> select(Map<String, Object> params, PageBounds pageBounds) {
		return selectList("select", params, pageBounds);
	}

	@Override
	public Activity selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insert(Activity activity) {
		return super.insert(activity);
	}

	@Override
	public int update(Activity activity) {
		return super.update(activity);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}

}
