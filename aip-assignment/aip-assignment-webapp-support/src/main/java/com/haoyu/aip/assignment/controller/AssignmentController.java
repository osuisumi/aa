package com.haoyu.aip.assignment.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping("**/assignment")
public class AssignmentController extends AbstractBaseController{
	
	@Resource
	private IAssignmentService assignmentService;
	@Resource
	private IAssignmentUserService assignmentUserService;
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Assignment assignment){
		return "assignment/edit_assignment";
	}
	
	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String edit(@PathVariable String id, Model model){
		model.addAttribute("assignment",assignmentService.getAssignment(id));
		return "assignment/edit_assignment";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Response createAssignment(Assignment assignment){
		return assignmentService.createAssignment(assignment);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Response updateAssignment(Assignment assignment, boolean isUpdateFile){
		return assignmentService.updateAssignment(assignment, isUpdateFile);
	}

}
