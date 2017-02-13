package com.haoyu.aip.video.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.aip.video.dao.IVideoRelationDao;
import com.haoyu.aip.video.entity.VideoRelation;
import com.haoyu.aip.video.service.IVideoRelationService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class VideoRelationServiceImpl implements IVideoRelationService{
	
	@Resource
	private IVideoRelationDao videoRelationDao;

	@Override
	public Response createVideoRelation(VideoRelation videoRelation) {
		if (StringUtils.isEmpty(videoRelation.getId())) {
			videoRelation.setId(Identities.uuid2());
		}
		videoRelation.setDefaultValue();
		int count = videoRelationDao.insert(videoRelation);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateVideoRelation(VideoRelation videoRelation) {
		videoRelation.setUpdatedby(ThreadContext.getUser());
		videoRelation.setUpdateTime(System.currentTimeMillis());
		int count = videoRelationDao.update(videoRelation);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
