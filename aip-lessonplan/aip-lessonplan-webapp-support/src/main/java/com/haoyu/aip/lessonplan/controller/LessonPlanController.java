package com.haoyu.aip.lessonplan.controller;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.lessonplan.entity.LessonPlan;
import com.haoyu.aip.lessonplan.service.ILessonPlanService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping("lesson_plan")
public class LessonPlanController extends AbstractBaseController{
	
	@Resource
	private ILessonPlanService lessonPlanService;

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(LessonPlan lessonPlan, Model model){
		model.addAttribute("lessonPlan", lessonPlan);
		return "lessonplan/edit_lesson_plan";
	}
	
	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String edit(LessonPlan lessonPlan, Model model){
		model.addAttribute("lessonPlan",lessonPlanService.get(lessonPlan.getId()));
		return "lessonplan/edit_lesson_plan";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Response save(LessonPlan lessonPlan, String startTime, String endTime, Model model){
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				lessonPlan.getLessonPlanRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
			if (StringUtils.isNotEmpty(endTime)) {
				lessonPlan.getLessonPlanRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(lessonPlan.getId())) {
			return lessonPlanService.createLessonPlan(lessonPlan);
		}else{
			return lessonPlanService.updateLessonPlan(lessonPlan);
		}
	}
	
	@RequestMapping(value="{id}/view", method = RequestMethod.GET)
	public String view(LessonPlan lessonPlan, Model model, HttpServletRequest request){
		lessonPlan = lessonPlanService.viewLessonPlan(lessonPlan.getId());
		model.addAttribute("lessonPlan", lessonPlan);
		model.addAllAttributes(request.getParameterMap());
		return "lessonplan/view_lesson_plan";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(LessonPlan lessonPlan){
		return lessonPlanService.deleteLessonPlan(lessonPlan);
	}
}
