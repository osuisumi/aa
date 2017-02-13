package com.haoyu.aip.video.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.haoyu.aip.video.dao.IVideoUserDao;
import com.haoyu.aip.video.entity.VideoRelation;
import com.haoyu.aip.video.entity.VideoUser;
import com.haoyu.aip.video.event.UpdateVideoUserEvent;
import com.haoyu.aip.video.service.IVideoUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class VideoUserServiceImpl implements IVideoUserService{
	
	@Resource
	private IVideoUserDao videoUserDao;
	@Resource
	private ApplicationContext applicationContext;
	@Resource
	private RedisTemplate redisTemplate;

	@Override
	public Response create(VideoUser videoUser) {
		if (StringUtils.isEmpty(videoUser.getId())) {
			videoUser.setId(Identities.uuid2());
		}
		videoUser.setDefaultValue();
		int count = videoUserDao.insert(videoUser);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(VideoUser videoUser) {
		videoUser.setUpdatedby(ThreadContext.getUser());
		videoUser.setUpdateTime(System.currentTimeMillis());
		int count = videoUserDao.update(videoUser);
		if (count > 0) {
			applicationContext.publishEvent(new UpdateVideoUserEvent(videoUser));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteByLogic(VideoUser videoUser) {
		String[] array = videoUser.getId().split(",");
		List<String> ids = Arrays.asList(array);
		videoUser.setUpdatedby(ThreadContext.getUser());
		videoUser.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", videoUser);
		int count = videoUserDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public VideoUser get(String id) {
		return videoUserDao.selectByPrimaryKey(id);
	}

	@Override
	public VideoUser createVideoUserIfNotExists(String videoRelationId) {
		String id = VideoUser.getId(videoRelationId, ThreadContext.getUser().getId() );
		VideoUser videoUser = videoUserDao.selectByPrimaryKey(id);
		if (videoUser == null) {
			videoUser = new VideoUser();
			VideoRelation videoRelation = new VideoRelation();
			videoRelation.setId(videoRelationId);
			videoUser.setVideoRelation(videoRelation);
			videoUser.setId(id);
			videoUser.setDefaultValue();
			videoUserDao.insert(videoUser);
		}
		return videoUser;
	}

	@Override
	public Response updateViewTime(VideoUser videoUser, boolean isLimit) {
		double lastViewTime = videoUser.getLastViewTime();
		String lvtString = String.valueOf(lastViewTime);
		if (lvtString.contains(".")) {
			int index = StringUtils.indexOf(lvtString, ".");
			lvtString = StringUtils.substring(lvtString, 0, index + 4);
		}
		videoUser.setLastViewTime(Double.valueOf(lvtString));   
		String userId = ThreadContext.getUser().getId();
		String key = PropertiesLoader.get("redis.app.key") + ":viewVideo:" + userId;
		ValueOperations<String,Map<String, Object>> valueOper = redisTemplate.opsForValue();
		if(redisTemplate.hasKey(key)){
			Map<String, Object> map = valueOper.get(key);
			if (map != null) {
				long lastUpdateTime = (long) map.get("lastUpdateTime");
				long now = new Date().getTime();
				String videoUserId = (String) map.get("videoUserId");
				if (!videoUser.getId().equals(videoUserId)) {
					return Response.failInstance().responseMsg("more video is open");
				}
				if (isLimit && now - lastUpdateTime < 10 * 1000) {
					return Response.failInstance().responseMsg("too soon");
				}
				if (now <= lastUpdateTime) {
					return Response.failInstance().responseMsg("-");
				}
				videoUser.setViewTime((now - lastUpdateTime)/1000);
				Response response = this.update(videoUser);
				if (response.isSuccess()) {
					map.put("lastUpdateTime", new Date().getTime());
					map.put("videoUserId", videoUser.getId());
					valueOper.set(key, map);
					redisTemplate.expire(key, 3, TimeUnit.MINUTES);
					
					videoUser = this.get(videoUser.getId());
					response.setResponseData(videoUser.getViewTime());
				}
				return response;
			}
		}
		Map<String, Object> map = Maps.newHashMap();
		int viewTime = Integer.parseInt(PropertiesLoader.get("video.lastTime.update.interval"));
		videoUser.setViewTime(viewTime);
		Response response = this.update(videoUser);
		if (response.isSuccess()) {
			map.put("lastUpdateTime", new Date().getTime());
			map.put("videoUserId", videoUser.getId());
			valueOper.set(key, map);
			redisTemplate.expire(key, 3, TimeUnit.MINUTES);
			
			videoUser = this.get(videoUser.getId());
			response.setResponseData(videoUser.getViewTime());
		}
		return response;
	}

	@Override
	public Response updateVideoStatus(VideoUser videoUser) {
		String userId = ThreadContext.getUser().getId();
		String key = PropertiesLoader.get("redis.app.key") + ":viewVideo:" + userId;
		ValueOperations<String,Map<String, Object>> valueOper = redisTemplate.opsForValue();
		Map<String, Object> map = Maps.newHashMap();
		map.put("lastUpdateTime", new Date().getTime());
		map.put("videoUserId", videoUser.getId());
		valueOper.set(key, map);
		redisTemplate.expire(key, 3, TimeUnit.MINUTES);
		return Response.successInstance();
	}

	@Override
	public Response removeVideoStatus() {
		String userId = ThreadContext.getUser().getId();
		String key = PropertiesLoader.get("redis.app.key") + ":viewVideo:" + userId;
		redisTemplate.delete(key);
		return Response.successInstance();
	}

}
