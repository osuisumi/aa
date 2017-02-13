package com.haoyu.aip.video.dao;

import java.util.Map;

import com.haoyu.aip.video.entity.VideoUser;

public interface IVideoUserDao {

	int insert(VideoUser videoUser);

	int update(VideoUser videoUser);

	int deleteByLogic(Map<String, Object> param);

	VideoUser selectByPrimaryKey(String id);

}
