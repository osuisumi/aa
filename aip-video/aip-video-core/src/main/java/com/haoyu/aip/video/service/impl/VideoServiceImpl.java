package com.haoyu.aip.video.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.video.entity.VideoRelation;
import com.haoyu.aip.video.entity.VideoUser;
import com.haoyu.aip.video.dao.IVideoDao;
import com.haoyu.aip.video.entity.Video;
import com.haoyu.aip.video.service.IVideoRecordService;
import com.haoyu.aip.video.service.IVideoRelationService;
import com.haoyu.aip.video.service.IVideoService;
import com.haoyu.aip.video.service.IVideoUserService;
import com.haoyu.aip.video.utils.VideoType;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class VideoServiceImpl implements IVideoService{
	
	@Resource
	private IVideoDao videoDao;
	@Resource
	private IFileService fileService;
	@Resource
	private IVideoRelationService videoRelationService;
	@Resource
	private IVideoUserService videoUserService;
	@Resource
	private IVideoRecordService videoRecordService;

	@Override
	public List<Video> list(Map<String, Object> params, PageBounds pageBounds) {
		return videoDao.select(params, pageBounds);
	}

	@Override
	public Response create(Video video) {
		if (StringUtils.isEmpty(video.getId())) {
			video.setId(Identities.uuid2());
		}
		video.setDefaultValue();
		int count = videoDao.insert(video);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(Video video) {
		video.setUpdatedby(ThreadContext.getUser());
		video.setUpdateTime(System.currentTimeMillis());
		int count = videoDao.update(video);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response deleteByLogic(Video video) {
		String[] array = video.getId().split(",");
		List<String> ids = Arrays.asList(array);
		video.setUpdatedby(ThreadContext.getUser());
		video.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", video);
		int count = videoDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Video get(String id) {
		return videoDao.selectByPrimaryKey(id);
	}

	@Override
	public Response createVideo(Video video) {
		if (VideoType.RECORD.equals(video.getType())) {
			if (StringUtils.isNotEmpty(video.getRecordId())) {
				video.setUrls(videoRecordService.getVideoUrls(video.getRecordId()));
			}
		}
		Response response = this.create(video);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(video.getVideoRelations())) {
				for (VideoRelation videoRelation : video.getVideoRelations()) {
					if (videoRelation.getRelation() != null && StringUtils.isNotEmpty(videoRelation.getRelation().getId())) {
						if (videoRelation.getVideo() == null || StringUtils.isEmpty(videoRelation.getVideo().getId())) {
							videoRelation.setVideo(video);
						}
						String id = VideoRelation.getId(videoRelation.getVideo().getId(), videoRelation.getRelation().getId());
						videoRelation.setId(id);
						videoRelationService.createVideoRelation(videoRelation);
						videoRelation.setVideo(null);
					}
				}
			}
			if (VideoType.FILE.equals(video.getType())) {
				fileService.createFileList(video.getVideoFiles(), video.getId(), "video");
			}
			fileService.createFileList(video.getFileInfos(), video.getId(), "video_file");
		}
		return response;
	}

	@Override
	public Response updateVideo(Video video) {
		if (VideoType.RECORD.equals(video.getType())) {
			if (StringUtils.isNotEmpty(video.getRecordId())) {
				video.setUrls(videoRecordService.getVideoUrls(video.getRecordId()));
			}
		}
		Response response = this.update(video);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(video.getVideoRelations())) {
				for (VideoRelation videoRelation : video.getVideoRelations()) {
					if (StringUtils.isNotEmpty(videoRelation.getId())) {
						videoRelationService.updateVideoRelation(videoRelation);
					}
				}
			}
			
			Relation relation = new Relation(video.getId());
			if (VideoType.FILE.equals(video.getType())) {
				relation.setType("video");
				List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(relation);
				fileService.updateFileList(video.getVideoFiles(), oldFileInfos, video.getId(), "video");
			}
			relation.setType("video_file");
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelation(relation);
			fileService.updateFileList(video.getFileInfos(), oldFileInfos, video.getId(), "video_file");
		}
		return response;
	}

	@Override
	public Video getVideo(String id) {
		Video video = this.get(id);
		if (video != null) {
			Relation relation = new Relation(video.getId());
			if (VideoType.FILE.equals(video.getType())) {
				relation.setId(video.getId());
				relation.setType("video");
				video.setVideoFiles(fileService.listFileInfoByRelation(relation));
			}
			relation.setType("video_file");
			video.setFileInfos(fileService.listFileInfoByRelation(relation));
		}
		return video;
	}

	@Override
	public Video viewVideo(String videoId, String relationId) {
		Video video = this.getVideo(videoId);
		if (video != null) {
			String videoRelationId = VideoRelation.getId(videoId, relationId);
			VideoRelation videoRelation = new VideoRelation();
			videoRelation.setId(videoRelationId);
			videoRelation.setViewNum(1);
//			videoRelationService.updateVideoRelation(videoRelation);
			
			String videoUserId = VideoUser.getId(videoRelationId, ThreadContext.getUser().getId());
			VideoUser videoUser = new VideoUser();
			videoUser.setId(videoUserId);
			videoUser.setViewNum(1);
			videoUserService.update(videoUser);
		}
		return video;
	}

}
