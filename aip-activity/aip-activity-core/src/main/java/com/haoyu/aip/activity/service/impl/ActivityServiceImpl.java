package com.haoyu.aip.activity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.dao.IActivityDao;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.entity.ActivityRelation;
import com.haoyu.aip.activity.event.CreateActivityEvent;
import com.haoyu.aip.activity.event.DeleteActivityEvent;
import com.haoyu.aip.activity.event.UpdateActivityEvent;
import com.haoyu.aip.activity.service.IActivityRelationService;
import com.haoyu.aip.activity.service.IActivityService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Collections3;

@Service
public class ActivityServiceImpl implements IActivityService{
	
	@Resource
	private IActivityDao activityDao;
	@Resource
	private IActivityRelationService activityRelationService;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(Activity obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)activityDao);
	}

	@Override
	public Response update(Activity obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)activityDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)activityDao);
	}

	@Override
	public Activity get(String id) {
		return (Activity) BaseServiceUtils.get(id, (MybatisDao)activityDao);
	}

	@Override
	public List<Activity> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)activityDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
	
	@Override
	public Response createActivity(Activity activity){
		Response response = this.create(activity);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(activity.getActivityRelations())) {
				for (ActivityRelation activityRelation : activity.getActivityRelations()) {
					if (activityRelation.getRelation() != null && StringUtils.isNotEmpty(activityRelation.getRelation().getId())) {
						if (activityRelation.getActivity() == null || StringUtils.isEmpty(activityRelation.getActivity().getId())) {
							activityRelation.setActivity(activity);
						}
						String id = ActivityRelation.getId(activityRelation.getActivity().getId(), activityRelation.getRelation().getId());
						activityRelation.setId(id);
						activityRelation.setIsTop("N");
						activityRelation.setIsEssence("N");
						activityRelationService.create(activityRelation);
					}
					activityRelation.setActivity(null);
				}
			}
			applicationContext.publishEvent(new CreateActivityEvent(activity));
			response.setResponseData(activity);
		}
		return response;
	}

	@Override
	public Response updateActivity(Activity activity) {
		Response response = this.update(activity);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(activity.getActivityRelations())) {
				for (ActivityRelation activityRelation : activity.getActivityRelations()) {
					if (StringUtils.isNotEmpty(activityRelation.getId())) {
						activityRelationService.updateByIdNotSelective(activityRelation);
					}
					activityRelation.setActivity(null);
				}
			}
			response.setResponseData(activity);
			applicationContext.publishEvent(new UpdateActivityEvent(activity));
		}
		return response;
	}

	@Override
	public Activity viewActivity(Activity activity, String relationId) {
		ActivityRelation activityRelation = new ActivityRelation();
		activityRelation.setId(ActivityRelation.getId(activity.getId(), relationId));
		activityRelation.setBrowseNum(1);
		activityRelationService.updateBrowseNum(activityRelation);
		return this.get(activity.getId());
	}
	
	@Override
	public Activity getByEntityId(String entityId) {
		return activityDao.selectByEntityId(entityId);
	}

	@Override
	public Response deleteActivity(Activity activity) {
		Response response = this.delete(activity.getId());
		if (response.isSuccess()) {
			applicationContext.publishEvent(new DeleteActivityEvent(activity));
		}
		return response;
	}
}
