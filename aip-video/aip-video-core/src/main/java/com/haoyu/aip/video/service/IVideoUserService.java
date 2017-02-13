package com.haoyu.aip.video.service;

import com.haoyu.aip.video.entity.VideoUser;
import com.haoyu.sip.core.service.Response;

public interface IVideoUserService {
	
	Response create(VideoUser videoUser);
	
	Response update(VideoUser videoUser);
	
	Response deleteByLogic(VideoUser videoUser);

	VideoUser get(String id);

	VideoUser createVideoUserIfNotExists(String videoRelationId);

	Response updateViewTime(VideoUser videoUser, boolean isLimit);

	Response updateVideoStatus(VideoUser videoUser);

	Response removeVideoStatus();

}
