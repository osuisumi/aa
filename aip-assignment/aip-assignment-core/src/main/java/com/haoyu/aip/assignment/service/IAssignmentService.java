package com.haoyu.aip.assignment.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.sip.core.service.Response;

public interface IAssignmentService {

	Response deleteAssignmentByLogic(Assignment assignment);

	List<Assignment> listAssignment(Map<String, Object> param, PageBounds pageBounds);
	
	Response createAssignment(Assignment assignment);
	
	Response updateAssignment(Assignment assignment);

	Assignment getAssignment(String id);

	Response updateAssignment(Assignment assignment, boolean updateFile);

}
