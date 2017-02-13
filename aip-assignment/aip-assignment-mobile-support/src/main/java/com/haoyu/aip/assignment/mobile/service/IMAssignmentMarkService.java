package com.haoyu.aip.assignment.mobile.service;

import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.sip.core.service.Response;

public interface IMAssignmentMarkService {

	Response listAssignmentMark(String assignmentRelationId);

	Response markAssignmentUser(AssignmentMark assignmentMark);

}
