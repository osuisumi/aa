package com.haoyu.aip.lessonplan.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.lessonplan.entity.LessonPlanRecord;
import com.haoyu.aip.lessonplan.service.ILessonPlanRecordService;
import com.haoyu.aip.lessonplan.service.ILessonPlanService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;

@Controller
@RequestMapping("lesson_plan/record")
public class LessonPlanRecordController extends AbstractBaseController{
	
	@Resource
	private ILessonPlanRecordService lessonPlanRecordService;
	
	@Resource
	private ILessonPlanService lessonPlanService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(LessonPlanRecord lessonPlanRecord, Model model){
		model.addAttribute("lessonPlanRecord", lessonPlanRecord);
		return "lessonplan/edit_lesson_plan_record";
	}
	
	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String edit(LessonPlanRecord lessonPlanRecord, Model model){
		model.addAttribute("lessonPlanRecord",lessonPlanRecordService.get(lessonPlanRecord.getId()));
		return "lessonplan/edit_lesson_plan_record";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Response save(LessonPlanRecord lessonPlanRecord, Model model){
		if (StringUtils.isEmpty(lessonPlanRecord.getId())) {
			return lessonPlanRecordService.create(lessonPlanRecord);
		}else{
			return lessonPlanRecordService.update(lessonPlanRecord);
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(SearchParam searchParam, Model model, HttpServletRequest request){
		model.addAttribute("lessonPlanRecords", lessonPlanRecordService.list(searchParam, getPageBounds(10, false)));
		model.addAllAttributes(request.getParameterMap());
		return "lessonplan/list_lesson_plan_record";
	}
	
	@RequestMapping(value = "{id}/view", method = RequestMethod.GET)
	public String view(LessonPlanRecord lessonPlanRecord, Model model){
		model.addAttribute("lessonPlanRecord",lessonPlanRecordService.get(lessonPlanRecord.getId()));
		return "lessonplan/view_lesson_plan_record";
	}
	
}
