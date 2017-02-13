package com.haoyu.aip.video.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.video.entity.VideoUser;
import com.haoyu.aip.video.service.IVideoUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseController;

@Controller
@RequestMapping("**/video/user")
public class VideoUserController extends AbstractBaseController{
	
	@Resource
	private IVideoUserService videoUserService;
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	@ResponseBody
	public Response update(VideoUser videoUser){
		return videoUserService.update(videoUser);
	}
	
	@RequestMapping(value="{id}/updateViewTime", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateViewTime(VideoUser videoUser, boolean isLimit){
		return videoUserService.updateViewTime(videoUser, isLimit);
	}
	
	@RequestMapping(value="updateVideoStatus")
	@ResponseBody
	public Response updateVideoStatus(VideoUser videoUser){
		return videoUserService.updateVideoStatus(videoUser);
	}

	@RequestMapping(value="removeVideoStatus")
	@ResponseBody
	public Response removeVideoStatus(){
		return videoUserService.removeVideoStatus();
	}
	
}
