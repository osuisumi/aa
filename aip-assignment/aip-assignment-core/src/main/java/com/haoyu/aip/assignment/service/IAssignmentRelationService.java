package com.haoyu.aip.assignment.service;

import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.sip.core.service.Response;

public interface IAssignmentRelationService {

	Response createAssignmentRelation(AssignmentRelation obj);

	Response updateAssignmentRelation(AssignmentRelation obj);

	AssignmentRelation getAssignmentRelation(String id);

}
