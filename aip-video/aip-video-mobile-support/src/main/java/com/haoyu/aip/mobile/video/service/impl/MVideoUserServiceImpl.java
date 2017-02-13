package com.haoyu.aip.mobile.video.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.haoyu.aip.mobile.video.service.IMVideoUserService;
import com.haoyu.aip.video.entity.VideoUser;
import com.haoyu.aip.video.service.IVideoUserService;
import com.haoyu.sip.core.service.Response;

@Service
public class MVideoUserServiceImpl implements IMVideoUserService{

	@Resource
	private IVideoUserService videoUserService;
	
	public Response updateViewTime(VideoUser videoUser, boolean isLimit) {
		return videoUserService.updateViewTime(videoUser, isLimit);
	}

	public Response updateVideoStatus(VideoUser videoUser) {
		return videoUserService.updateVideoStatus(videoUser);
	}

	public Response removeVideoStatus() {
		return videoUserService.removeVideoStatus();
	}

}
