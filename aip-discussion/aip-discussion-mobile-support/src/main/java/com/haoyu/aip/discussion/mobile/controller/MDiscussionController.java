package com.haoyu.aip.discussion.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.mobile.service.IMDiscussionService;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@Controller
@RequestMapping("**/m/discussion")
public class MDiscussionController extends AbstractBaseMobileController{
	
	@Resource
	private IDiscussionService discussionService;
	@Resource
	private IMDiscussionService discussionMobileService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Response list(Discussion discussion){
		return discussionMobileService.listDiscission(discussion,getPageBounds(10, true));
	}

	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ResponseBody
	public Response get(Discussion discussion){
		return discussionMobileService.getDiscussion(discussion);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(Discussion discussion){
		return discussionMobileService.createDiscussion(discussion);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(Discussion discussion){
		return discussionMobileService.updateDiscussion(discussion);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(Discussion discussion){
		return discussionService.deleteDiscussion(discussion);
	}
	
}
