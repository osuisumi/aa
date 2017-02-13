package com.haoyu.aip.assignment.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.sip.core.service.Response;

public interface IAssignmentUserService {
	
	Response createAssignmentUser(AssignmentUser obj);

	Response updateAssignmentUser(AssignmentUser obj);

	List<AssignmentUser> listAssignmentUser(Map<String, Object> param, PageBounds pageBounds);

	AssignmentUser createAssignmentIfNotExists(String assignmentRelationId);

	AssignmentUser getAssignmentUser(String id);
	
	AssignmentUser getAssignmentUser(String id, boolean getFile);
	
	int getCount(Map<String, Object> param);

	Response updateAssignmentUserEnd(String assignmentId);

	Response updateAssignmentUserBack(AssignmentUser assignmentUser);

}
