package com.haoyu.aip.activity.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.aip.activity.dao.IActivityAttributeDao;
import com.haoyu.aip.activity.entity.ActivityAttribute;
import com.haoyu.aip.activity.service.IActivityAttributeService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class ActivityAttributeServiceImpl implements IActivityAttributeService{
	
	@Resource
	private IActivityAttributeDao activityAttributeDao;

	@Override
	public Response createActivityAttribute(ActivityAttribute activityAttribute) {
		if (StringUtils.isEmpty(activityAttribute.getId())) {
			activityAttribute.setId(Identities.uuid2());
		}
		activityAttribute.setDefaultValue();
		int count = activityAttributeDao.insert(activityAttribute);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateActivityAttribute(ActivityAttribute activityAttribute) {
		activityAttribute.setUpdatedby(ThreadContext.getUser());
		activityAttribute.setUpdateTime(System.currentTimeMillis());
		int count = activityAttributeDao.update(activityAttribute);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Map<String, ActivityAttribute> mapActivityAttribute(String activityId) {
		return activityAttributeDao.selectForMap(activityId);
	}

	@Override
	public List<ActivityAttribute> listActivityAttribute(List<String> activities) {
		return activityAttributeDao.select(activities);
	}

}
