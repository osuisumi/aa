package com.haoyu.aip.assignment.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.file.service.IFileService;

@Controller
@RequestMapping("**/assignment/user")
public class AssignmentUserController extends AbstractBaseController{
	
	@Resource
	private IAssignmentUserService assignmentUserService;
	@Resource
	private IAssignmentService assignmentService;
	@Resource
	private IFileService fileService;
	
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
}
