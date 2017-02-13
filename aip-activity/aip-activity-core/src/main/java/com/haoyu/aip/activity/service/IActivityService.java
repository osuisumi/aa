package com.haoyu.aip.activity.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IActivityService{
	
	Response create(Activity obj);
	
	Response update(Activity obj);
	
	Response delete(String id);
	
	Activity get(String id);
	
	List<Activity> list(SearchParam searchParam, PageBounds pageBounds);

	Response createActivity(Activity activity);
	
	Response updateActivity(Activity activity);

	Activity viewActivity(Activity activity, String relationId);
	
	Activity getByEntityId(String entityId);

	Response deleteActivity(Activity activity);

}
