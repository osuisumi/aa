package com.haoyu.aip.assignment.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.service.Response;

public interface IMAssignmentUserService {

	Response listAssignmentUser(String relationId, String assignmentId, String state, PageBounds pageBounds);

	Response getAssignmentUserNum(String relationId);

}
