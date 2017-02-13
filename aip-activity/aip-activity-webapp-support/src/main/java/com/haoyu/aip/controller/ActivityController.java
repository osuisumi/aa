package com.haoyu.aip.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.activity.entity.Activity;
import com.haoyu.aip.activity.service.IActivityService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;

@Controller
@RequestMapping("**/activity")
public class ActivityController extends AbstractBaseController{
	
	@Resource
	private IActivityService activityService;

	@RequestMapping(value="create", method=RequestMethod.GET)
	public String create(Activity activity, Model model){
		model.addAttribute("activity", activity);
		return "activity/add_activity";
	}
	
	@RequestMapping(value="{id}/edit", method=RequestMethod.GET)
	public String edit(Activity activity, Model model){
		model.addAttribute("activity", activityService.get(activity.getId()));
		return "activity/edit_activity";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String list(SearchParam searchParam, Model model){
		model.addAttribute("activities", activityService.list(searchParam, getPageBounds(10, true)));
		return "activity/list_activity";
	}
	
	@RequestMapping(value="{id}/view", method=RequestMethod.GET)
	public String view(Activity activity, Model model){
		model.addAttribute("activity", activityService.get(activity.getId()));
		return "activity/view_activity";
	}
	
	@RequestMapping(value="more", method=RequestMethod.GET)
	public String more(SearchParam searchParam, Model model){
		model.addAttribute("activities", activityService.list(searchParam, getPageBounds(10, true)));
		return "activity/list_more_activity";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Activity activity){
		return activityService.updateActivity(activity);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Activity activity){
		return activityService.deleteActivity(activity);
	}
}
