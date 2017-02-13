package com.haoyu.aip.discussion.mobile.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.mobile.service.IMDiscussionPostService;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@Controller
@RequestMapping("**/m/discussion/post")
public class MDiscussionPostController extends AbstractBaseMobileController{

	@Resource
	private IDiscussionPostService discussionPostService;
	@Resource
	private IMDiscussionPostService discussionPostMobileService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Response list(DiscussionPost discussionPost){
		return discussionPostMobileService.listDiscussionPost(discussionPost,getPageBounds(10,true));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Response create(DiscussionPost discussionPost){
		return discussionPostMobileService.createDiscussionPost(discussionPost);
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Response update(DiscussionPost discussionPost){
		return discussionPostMobileService.updateDiscussionPost(discussionPost);
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.DELETE)
	@ResponseBody
	public Response delete(DiscussionPost discussionPost){
		return discussionPostService.deleteDiscussionPost(discussionPost);
	}
	
}