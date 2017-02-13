package com.haoyu.aip.activity.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.dao.IActivityResultDao;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.aip.activity.event.UpdateActivityResultEvent;
import com.haoyu.aip.activity.service.IActivityResultService;
import com.haoyu.aip.activity.utils.ActivityResultState;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class ActivityResultServiceImpl implements IActivityResultService{
	
	@Resource
	private IActivityResultDao activityResultDao;
	@Resource
	private ApplicationContext applicationContext;

	@Override
	public Response createActivityResult(ActivityResult activityResult) {
		if (StringUtils.isEmpty(activityResult.getId())) {
			activityResult.setId(Identities.uuid2());
		}
		activityResult.setDefaultValue();
		int count = activityResultDao.insert(activityResult);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateActivityResult(ActivityResult activityResult) {
		activityResult.setUpdatedby(ThreadContext.getUser());
		activityResult.setUpdateTime(System.currentTimeMillis());
		int count = activityResultDao.update(activityResult);
		if (count > 0) {
			applicationContext.publishEvent(new UpdateActivityResultEvent(activityResult));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public ActivityResult createIfNotExists(String activityId, String relationId) {
		return this.createIfNotExists(activityId, relationId, null);
	}

	@Override
	public ActivityResult getActivityResult(String id) {
		return activityResultDao.selectByPrimaryKey(id);
	}

	@Override
	public Map<String, ActivityResult> mapActivityResult(Map<String, Object> param, PageBounds pageBounds) {
		return activityResultDao.selectForMap(param, pageBounds);
	}

	@Override
	public List<ActivityResult> listActivityResult(Map<String, Object> param, PageBounds pageBounds) {
		return activityResultDao.select(param, pageBounds);
	}

	@Override
	public ActivityResult createIfNotExists(String activityId, String relationId, String userId) {
		String id = "";
		if(StringUtils.isNotEmpty(userId)){
			id = ActivityResult.getId(activityId, relationId, userId);
		}else{
			id = ActivityResult.getId(activityId, relationId, ThreadContext.getUser().getId());
		}
		ActivityResult activityResult = activityResultDao.selectByPrimaryKey(id);
		if (activityResult == null) {
			activityResult = new ActivityResult();
			activityResult.setId(id);
			Activity activity = new Activity();
			activity.setId(activityId);
			activityResult.setActivity(activity);
			activityResult.setRelation(new Relation(relationId));
			activityResult.setState(ActivityResultState.NOT_ATTEMPT);
			this.createActivityResult(activityResult);
		}
		return activityResult;
	}

}
