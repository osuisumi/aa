package com.haoyu.aip.assignment.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.file.service.IFileService;

@Controller
@RequestMapping("assignment/user")
public class AssignmentUserController extends AbstractBaseController{
	
	@Resource
	private IAssignmentUserService assignmentUserService;
	@Resource
	private IAssignmentService assignmentService;
	@Resource
	private IFileService fileService;
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Response save(AssignmentUser assignmentUser){
		if (StringUtils.isEmpty(assignmentUser.getId())) {
			return assignmentUserService.createAssignmentUser(assignmentUser);
		}else{
			return assignmentUserService.updateAssignmentUser(assignmentUser);
		}
	}
	
	@RequestMapping(method={RequestMethod.GET})
	public String list(AssignmentRelation assignmentRelation, Model model){
		String assignmentRelaitonId = AssignmentRelation.getId(assignmentRelation.getAssignment().getId(), assignmentRelation.getRelation().getId());
		AssignmentUser assignmentUser = new AssignmentUser();
		assignmentRelation.setId(assignmentRelaitonId);
		assignmentUser.setAssignmentRelation(assignmentRelation);
		List<AssignmentUser> assignmentUsers = assignmentUserService.listAssignmentUser(assignmentUser, getPageBounds(10, true));
		Assignment assignment = assignmentService.get(assignmentRelation.getAssignment().getId());
		assignment.setFileInfos(fileService.listFileInfoByRelationId(assignment.getId()));
		model.addAttribute("assignment", assignment);
		model.addAttribute("assignmentUsers",assignmentUsers);
		return "assignment/list_assignment_user";
	}
	
	@RequestMapping(value = "{id}/check", method = RequestMethod.GET)
	public String check(@PathVariable String id, Model model){
		AssignmentUser assignmentUser = assignmentUserService.get(id);
		assignmentUser.setFileInfos(fileService.listFileInfoByRelationId(assignmentUser.getId()));
		model.addAttribute("assignmentUser", assignmentUser);
		return "assignment/check_assignment_user";
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Response checkAssignmentUser(AssignmentUser assignmentUser){
		return assignmentUserService.checkAssignmentUser(assignmentUser);
	}
}
