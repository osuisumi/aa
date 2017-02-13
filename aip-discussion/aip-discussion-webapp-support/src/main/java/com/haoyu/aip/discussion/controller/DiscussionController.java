package com.haoyu.aip.discussion.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.web.AbstractBaseController;
import com.haoyu.sip.core.web.SearchParam;

@Controller
@RequestMapping("**/discussion")
public class DiscussionController extends AbstractBaseController{
	
	@Resource
	private IDiscussionService discussionService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(SearchParam searchParam,Model model){
		model.addAllAttributes(request.getParameterMap());
		model.addAttribute("discussions",discussionService.list(searchParam, getPageBounds(10, true)));
		return "discussion/list_discussion";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Discussion discussion, Model model){
		model.addAttribute("discussion", discussion);
		return "discussion/edit_discussion";
	}
	
	@RequestMapping(value = "{id}/edit", method = RequestMethod.GET)
	public String edit(Discussion discussion, Model model){
		model.addAttribute("discussion",discussionService.get(discussion.getId()));
		return "discussion/edit_discussion";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	@ResponseBody
	public Response save(Discussion discussion, String startTime, String endTime, Model model){
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				discussion.getDiscussionRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
			if (StringUtils.isNotEmpty(endTime)) {
				discussion.getDiscussionRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (StringUtils.isEmpty(discussion.getId())) {
			return discussionService.createDiscussion(discussion);
		}else{
			return discussionService.updateDiscussion(discussion);
		}
	}
	
//	@RequestMapping(value="create", method=RequestMethod.POST)
//	@ResponseBody
//	public Response createDiscussion(Discussion discussion, String startTime, String endTime, Model model){
//		try {
//			if (StringUtils.isNotEmpty(startTime)) {
//				discussion.getDiscussionRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
//			}
//			if (StringUtils.isNotEmpty(endTime)) {
//				discussion.getDiscussionRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return discussionService.createDiscussion(discussion);
//	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response createDiscussion(Discussion discussion, String startTime, String endTime, Model model){
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				discussion.getDiscussionRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
			if (StringUtils.isNotEmpty(endTime)) {
				discussion.getDiscussionRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return discussionService.createDiscussion(discussion);
	}

	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateDiscussion(Discussion discussion, String startTime, String endTime, Model model){
		try {
			if (StringUtils.isNotEmpty(startTime)) {
				discussion.getDiscussionRelations().get(0).getTimePeriod().setStartTime(DateUtils.parseDate(startTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
			if (StringUtils.isNotEmpty(endTime)) {
				discussion.getDiscussionRelations().get(0).getTimePeriod().setEndTime(DateUtils.parseDate(endTime, PropertiesLoader.get("activity.timePeriod.pattern")) );
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return discussionService.updateDiscussion(discussion);
	}
	
	@RequestMapping(value="{id}/view", method = RequestMethod.GET)
	public String view(Discussion discussion, Model model, HttpServletRequest request){
		discussion = discussionService.viewDiscussion(discussion.getId(), false);
		model.addAttribute("discussion", discussion);
		model.addAllAttributes(request.getParameterMap());
		return "discussion/view_discussion";
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Discussion discussion){
		return discussionService.deleteDiscussion(discussion);
	}
	
	@RequestMapping(value="listData",method = RequestMethod.POST)
	@ResponseBody
	public List<Discussion> listData(SearchParam searchParam,Model model){
		return discussionService.list(searchParam, getPageBounds(10, true));
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response updateBatch(Discussion discussion){
		return discussionService.updateDiscussions(discussion);
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response deleteBatch(Discussion discussion){
		return discussionService.deleteDiscussions(discussion);
	}
	
}
