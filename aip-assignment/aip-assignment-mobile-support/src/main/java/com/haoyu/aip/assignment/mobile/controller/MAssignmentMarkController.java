package com.haoyu.aip.assignment.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.mobile.service.IMAssignmentMarkService;
import com.haoyu.aip.assignment.service.IAssignmentMarkService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@Controller
@RequestMapping("**/m/assignment/mark")
public class MAssignmentMarkController extends AbstractBaseMobileController{
	
	@Resource
	private IAssignmentMarkService assignmentMarkService;
	@Resource
	private IMAssignmentMarkService mAssignmentMarkService;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response listAssignmentMark(String assignmentRelationId){
		return mAssignmentMarkService.listAssignmentMark(assignmentRelationId);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createAssignmentMark(AssignmentRelation assignmentRelation){
		return assignmentMarkService.createAssignmentMark(assignmentRelation.getAssignment().getId(), assignmentRelation.getId());
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	@ResponseBody
	public Response markAssignmentUser(AssignmentMark assignmentMark){
		return mAssignmentMarkService.markAssignmentUser(assignmentMark);
	}
	
	@RequestMapping(value="{id}/markByTeacher", method=RequestMethod.GET)
	@ResponseBody
	public Response markAssignmentUser(AssignmentUser assignmentUser){
		AssignmentMark assignmentMark = assignmentMarkService.createAssignmentMarkIfNotExists(assignmentUser.getId());
		return mAssignmentMarkService.markAssignmentUser(assignmentMark);
	}
	
	@RequestMapping(value="{relation.id}", method=RequestMethod.POST)
	@ResponseBody
	public Response createAssignmentMarkByTeacher(AssignmentRelation assignmentRelation){
		int limit = 10;
		return assignmentMarkService.createAssignmentMark(assignmentRelation.getRelation().getId(), limit);
	}
}
