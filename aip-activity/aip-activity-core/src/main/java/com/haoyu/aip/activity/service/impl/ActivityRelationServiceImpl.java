package com.haoyu.aip.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.dao.IActivityRelationDao;
import com.haoyu.aip.activity.entity.ActivityRelation;
import com.haoyu.aip.activity.service.IActivityRelationService;
import com.haoyu.aip.activity.service.IActivityResultService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;

@Service
public class ActivityRelationServiceImpl implements IActivityRelationService{

	@Resource
	private IActivityRelationDao activityRelationDao;
	@Resource
	private IActivityResultService activityResultService;
	
	@Override
	public Response create(ActivityRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)activityRelationDao);
	}

	@Override
	public Response update(ActivityRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)activityRelationDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)activityRelationDao);
	}

	@Override
	public ActivityRelation get(String id) {
		return (ActivityRelation) BaseServiceUtils.get(id, (MybatisDao)activityRelationDao);
	}

	@Override
	public List<ActivityRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)activityRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateBrowseNum(ActivityRelation obj) {
		obj.setBrowseNum(1);
		return this.update(obj);
	}

	@Override
	public Response updateParticipateNum(ActivityRelation obj) {
		obj.setParticipateNum(1);
		return this.update(obj);
	}

	@Override
	public Response updateReplyNum(ActivityRelation obj) {
		obj.setReplyNum(1);
		return this.update(obj);
	}

	@Override
	public Response updateByIdNotSelective(ActivityRelation obj) {
		obj.setUpdatedby(ThreadContext.getUser());
		obj.setUpdateTime(System.currentTimeMillis());
		int count = activityRelationDao.updateByIdNotSelective(obj);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
