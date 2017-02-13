package com.haoyu.aip.assignment.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.service.IAssignmentMarkService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping("**/assignment/mark")
public class AssignmentMarkController extends AbstractBaseController{
	
	@Resource
	private IAssignmentMarkService assignmentMarkService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createAssignmentMark(AssignmentRelation assignmentRelation){
		return assignmentMarkService.createAssignmentMark(assignmentRelation.getAssignment().getId(), assignmentRelation.getId());
	}
	
	@RequestMapping(value="{relation.id}", method=RequestMethod.POST)
	@ResponseBody
	public Response createAssignmentMarkByTeacher(AssignmentRelation assignmentRelation, int limit){
		return assignmentMarkService.createAssignmentMark(assignmentRelation.getRelation().getId(), limit);
	}
	
}