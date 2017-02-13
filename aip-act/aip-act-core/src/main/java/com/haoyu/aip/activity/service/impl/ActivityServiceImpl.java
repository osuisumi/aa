package com.haoyu.aip.activity.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.activity.dao.IActivityDao;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.entity.ActivityAttribute;
import com.haoyu.aip.activity.service.IActivityAttributeService;
import com.haoyu.aip.activity.service.IActivityService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Collections3;

@Service
public class ActivityServiceImpl implements IActivityService{
	
	@Resource
	private IActivityDao activityDao;
	@Resource
	private IActivityAttributeService activityAttributeService;
	@Resource
	private ITagRelationService tagRelationService;


	@Override
	@Cacheable(value="listActivity",condition="#params[courseId] != null and (#pageBounds == null or #pageBounds.page == 1)", key="#params[courseId]+':getAttribute:'+#getAttribute")
	public List<Activity> listActivity(Map<String, Object> params, boolean getAttribute, PageBounds pageBounds) {
		List<Activity> activities = activityDao.select(params, pageBounds);
		if (Collections3.isNotEmpty(activities) && getAttribute) {
			List<String> ids = Collections3.extractToList(activities, "id");
			List<ActivityAttribute> activityAttributes = activityAttributeService.listActivityAttribute(ids);
			Map<String, Activity> activityMap = Collections3.extractToMap(activities, "id", null);
			for (ActivityAttribute activityAttribute : activityAttributes) {
				activityMap.get(activityAttribute.getActivity().getId()).getAttributeMap().put(activityAttribute.getAttrName(), activityAttribute);
			}
		}
		return activities;
	}

	@Override
	public Activity getActivity(String id) {
		Activity activity = activityDao.selectByPrimaryKey(id);
		if (activity != null) {
			Map<String, ActivityAttribute> activityAttributeMap = activityAttributeService.mapActivityAttribute(id);
			activity.setAttributeMap(activityAttributeMap);
		}
		return activity;
	}

	@Override
	@CacheEvict(value="listActivity", allEntries=true)
	public Response createActivity(Activity activity) {
		if (StringUtils.isEmpty(activity.getId())) {
			activity.setId(Activity.generateId());
		}
		activity.setDefaultValue();
		int count = activityDao.insert(activity);
		if (count > 0) {
			tagRelationService.createTagRelation(activity.getTags(), new Relation(activity.getId(),"activity"), true);
			if (!activity.getAttributeMap().isEmpty()) {
				for (String key : activity.getAttributeMap().keySet()) {
					ActivityAttribute activityAttribute = activity.getAttributeMap().get(key);
					String id = ActivityAttribute.getId(activity.getId(), key);
					activityAttribute.setId(id);
					activityAttribute.setActivity(activity);
					activityAttribute.setAttrName(key);
					activityAttributeService.createActivityAttribute(activityAttribute);
				}
			}
		}
		return count>0?Response.successInstance().responseData(activity):Response.failInstance();
	}

	@Override
	@CacheEvict(value="listActivity", allEntries=true)
	public Response updateActivity(Activity activity, boolean updateTag){
		activity.setUpdatedby(ThreadContext.getUser());
		activity.setUpdateTime(System.currentTimeMillis());
		int count = activityDao.update(activity);
		if (count > 0) {
			if (updateTag) {
				tagRelationService.createTagRelation(activity.getTags(), new Relation(activity.getId(),"activity"), true);
			}
			if (!activity.getAttributeMap().isEmpty()) {
				for (String key : activity.getAttributeMap().keySet()) {
					ActivityAttribute activityAttribute = activity.getAttributeMap().get(key);
					String id = ActivityAttribute.getId(activity.getId(), key);
					activityAttribute.setId(id);
					activityAttribute.setActivity(activity);
					activityAttribute.setAttrName(key);
					Response response = activityAttributeService.updateActivityAttribute(activityAttribute);
					if (!response.isSuccess()) {
						activityAttributeService.createActivityAttribute(activityAttribute);
					}
				}
			}
		}
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response updateActivity(Activity activity){
		return updateActivity(activity, true);
	}

	@Override
	@CacheEvict(value="listActivity", allEntries=true)
	public Response deleteActivityByLogic(Activity activity) {
		String[] array = activity.getId().split(",");
		List<String> ids = Arrays.asList(array);
		activity.setUpdatedby(ThreadContext.getUser());
		activity.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", activity);
		int count = activityDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Activity getActivityByEntityId(String entityId) {
		Map<String, Object> params = Maps.newHashMap();
		params.put("entityId", entityId);
		List<Activity> activities = activityDao.select(params, null);
		if (Collections3.isNotEmpty(activities)) {
			Activity activity = activities.get(0);
			Map<String, ActivityAttribute> activityAttributeMap = activityAttributeService.mapActivityAttribute(activity.getId());
			activity.setAttributeMap(activityAttributeMap);
			return activity;
		}
		return null;
	}

}
