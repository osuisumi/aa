package com.haoyu.aip.video.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.video.entity.Video;

public interface IVideoDao {

	List<Video> select(Map<String, Object> params, PageBounds pageBounds);

	int insert(Video section);

	int update(Video section);

	int deleteByLogic(Map<String, Object> param);

	Video selectByPrimaryKey(String id);
}
