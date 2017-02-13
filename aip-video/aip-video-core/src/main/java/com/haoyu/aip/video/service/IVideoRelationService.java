package com.haoyu.aip.video.service;

import com.haoyu.aip.video.entity.VideoRelation;
import com.haoyu.sip.core.service.Response;

public interface IVideoRelationService {

	Response createVideoRelation(VideoRelation videoRelation);

	Response updateVideoRelation(VideoRelation videoRelation);

}
