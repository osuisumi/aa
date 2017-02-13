package com.haoyu.aip.assignment.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.sip.core.service.Response;

public interface IAssignmentMarkService {

	int getCount(Map<String, Object> param);

	Response createAssignmentMark(String assignmentId, String assignmentRelationId);
	
	List<AssignmentMark> listAssignmentMark(Map<String, Object> param, PageBounds pageBounds);

	AssignmentMark getAssignmentMark(String id);

	Response updateAssignmentMark(AssignmentMark assignmentMark);

	AssignmentMark createAssignmentMarkIfNotExists(String assigmnetUserId);
	
	Response updateAssignmentMarkExpired();

	Response createAssignmentMark(String relationId, int limit);

	Response deleteAssignmentMarkByParam(Map<String, Object> param);
	
}
