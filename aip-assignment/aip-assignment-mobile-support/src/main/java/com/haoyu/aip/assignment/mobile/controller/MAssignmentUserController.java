package com.haoyu.aip.assignment.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.mobile.service.IMAssignmentUserService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@Controller
@RequestMapping("**/m/assignment/user")
public class MAssignmentUserController extends AbstractBaseMobileController{
	
	@Resource
	private IAssignmentUserService assignmentUserService;
	@Resource
	private IMAssignmentUserService mAssignmentUserService;
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateAssignmentUser(AssignmentUser assignmentUser){
		return assignmentUserService.updateAssignmentUser(assignmentUser);
	}
	
	@RequestMapping(value="{id}/back", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateAssignmentUserBack(AssignmentUser assignmentUser){
		return assignmentUserService.updateAssignmentUserBack(assignmentUser);
	}
	
	@RequestMapping(value="getAssignmentUserNum" ,method=RequestMethod.GET)
	@ResponseBody
	public Response getAssignmentUserNum(String relationId){
		return mAssignmentUserService.getAssignmentUserNum(relationId);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response listAssignmentUser(String relationId, String assignmentId, String state){
		return mAssignmentUserService.listAssignmentUser(relationId, assignmentId, state, getPageBounds(10, true));
	}
}
