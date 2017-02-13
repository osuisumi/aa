package com.haoyu.aip.video.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.video.entity.Video;
import com.haoyu.sip.core.service.Response;

public interface IVideoService {
	
	List<Video> list(Map<String, Object> params, PageBounds pageBounds);
	
	Response create(Video video);
	
	Response update(Video video);
	
	Response deleteByLogic(Video video);

	Video get(String id);
	
	Response createVideo(Video video);
	
	Response updateVideo(Video video);
	
	Video getVideo(String id);

	Video viewVideo(String videoId, String relationId);

}
