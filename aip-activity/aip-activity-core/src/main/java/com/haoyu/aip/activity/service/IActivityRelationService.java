package com.haoyu.aip.activity.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.activity.entity.ActivityRelation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IActivityRelationService {
	
	Response create(ActivityRelation obj);
	
	Response update(ActivityRelation obj);
	
	Response delete(String id);
	
	ActivityRelation get(String id);
	
	List<ActivityRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateBrowseNum(ActivityRelation obj);

	Response updateParticipateNum(ActivityRelation obj);
	
	Response updateReplyNum(ActivityRelation obj);

	Response updateByIdNotSelective(ActivityRelation obj);

}
