package com.haoyu.aip.assignment.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoyu.aip.assignment.service.IAssignmentBizService;
import com.haoyu.aip.assignment.service.IAssignmentService;

@Service
public class AssignmentBizServiceImpl implements IAssignmentBizService{
	
	@Resource
	private IAssignmentService assignmentService;
	
	
}
