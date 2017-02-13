package com.haoyu.aip.assignment.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IAssignmentRelationService {

	Response create(AssignmentRelation obj);

	Response update(AssignmentRelation obj);

	Response delete(String id);

	AssignmentRelation get(String id);
	
	Response updateByIdNotSelective(AssignmentRelation obj);

	List<AssignmentRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateParticipateNum(AssignmentRelation obj);

	Response updateBrowseNum(AssignmentRelation obj);

}
