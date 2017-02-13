package com.haoyu.aip.assignment.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping("assignment")
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
		model.addAttribute("assignment",assignmentService.get(id));
		return "assignment/edit_assignment";
	}
	
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Response save(Assignment assignment, String startTime, String endTime, Model model){
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				assignment.getAssignmentRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
			if (StringUtils.isNotEmpty(endTime)) {
				assignment.getAssignmentRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(assignment.getId())) {
			return assignmentService.createAssignment(assignment);
		}else{
			return assignmentService.updateAssignment(assignment);
		}
	}
	
	@RequestMapping(value="{id}/view", method = RequestMethod.GET)
	public String view(Assignment assignment, Model model, HttpServletRequest request){
		AssignmentUser assignmentUser = new AssignmentUser();
		AssignmentRelation assignmentRelation = new AssignmentRelation();
		assignmentRelation.setAssignment(assignment);
		assignmentUser.setAssignmentRelation(assignmentRelation);
		assignmentUser.setCreator(ThreadContext.getUser());
		model.addAttribute("assignmentUser", assignmentUserService.viewAssignmentUser(assignmentUser));
		model.addAllAttributes(request.getParameterMap());
		return "assignment/view_assignment";
	}

}
