package com.haoyu.aip.mobile.video.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.haoyu.aip.mobile.video.service.impl.MVideoUserServiceImpl;
import com.haoyu.aip.video.entity.VideoUser;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.AbstractBaseMobileController;

@Controller
@RequestMapping("**/m/video/user")
public class MVideoUserController extends AbstractBaseMobileController{
	
	@Resource
	private MVideoUserServiceImpl mVideoUserService;
	
	@RequestMapping(value="{id}/updateViewTime", method=RequestMethod.PUT)
	@ResponseBody
	public Response updateViewTime(VideoUser videoUser, boolean isLimit){
		return mVideoUserService.updateViewTime(videoUser, isLimit);
	}
	
	@RequestMapping(value="{id}/updateVideoStatus")
	@ResponseBody
	public Response updateVideoStatus(VideoUser videoUser){
		return mVideoUserService.updateVideoStatus(videoUser);
	}

	@RequestMapping(value="{id}/removeVideoStatus")
	@ResponseBody
	public Response removeVideoStatus(){
		return mVideoUserService.removeVideoStatus();
	}

}
