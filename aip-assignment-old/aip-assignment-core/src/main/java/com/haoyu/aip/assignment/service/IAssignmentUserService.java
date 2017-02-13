package com.haoyu.aip.assignment.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.sip.core.service.Response;

public interface IAssignmentUserService {
	
	Response create(AssignmentUser obj);

	Response update(AssignmentUser obj);

	Response delete(String id);

	AssignmentUser get(String id);

	List<AssignmentUser> listAssignmentUser(AssignmentUser assignmentUser, PageBounds pageBounds);

	Response checkAssignmentUser(AssignmentUser assignmentUser);

	AssignmentUser viewAssignmentUser(AssignmentUser assignmentUser);

	Response createAssignmentUser(AssignmentUser assignmentUser);

	Response updateAssignmentUser(AssignmentUser assignmentUser);

}
