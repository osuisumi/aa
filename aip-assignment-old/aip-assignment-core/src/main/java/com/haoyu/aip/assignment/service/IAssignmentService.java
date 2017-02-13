package com.haoyu.aip.assignment.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IAssignmentService {

	Response create(Assignment obj);

	Response update(Assignment obj);

	Response delete(String id);

	Assignment get(String id);

	List<Assignment> list(SearchParam searchParam, PageBounds pageBounds);

	Response createAssignment(Assignment assignment);

	Response updateAssignment(Assignment assignment);

}
