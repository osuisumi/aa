package com.haoyu.aip.activity.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.ActivityResult;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IActivityResultService {
	
	Response create(ActivityResult obj);
	
	Response update(ActivityResult obj);
	
	Response delete(String id);
	
	ActivityResult get(String id);
	
	List<ActivityResult> list(SearchParam searchParam, PageBounds pageBounds);

	int getCount(Map<String, Object> paramMap);

	Response deleteActivityResult(String activityResultId);

}
