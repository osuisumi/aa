package com.haoyu.aip.activity.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.dao.IActivityResultDao;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.aip.activity.service.IActivityResultService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

@Service
public class ActivityResultserviceImpl implements IActivityResultService{

	@Resource
	private IActivityResultDao activityResultDao;
	
	@Override
	public Response create(ActivityResult obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)activityResultDao);
	}

	@Override
	public Response update(ActivityResult obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)activityResultDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)activityResultDao);
	}

	@Override
	public ActivityResult get(String id) {
		return (ActivityResult) BaseServiceUtils.get(id, (MybatisDao)activityResultDao);
	}

	@Override
	public List<ActivityResult> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)activityResultDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> paramMap) {
		return activityResultDao.getCount(paramMap);
	}

	@Override
	public Response deleteActivityResult(String id) {
		int count = activityResultDao.deleteByPhysics(id);
		return count>0?Response.successInstance():Response.failInstance();
	}
}
