package com.haoyu.aip.video.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.video.dao.IVideoDao;
import com.haoyu.aip.video.entity.Video;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class VideoDao extends MybatisDao implements IVideoDao{
	
	@Override
	public List<Video> select(Map<String, Object> params, PageBounds pageBounds) {
		return selectList("select", params, pageBounds);
	}

	@Override
	public int insert(Video video) {
		return super.insert(video);
	}

	@Override
	public int update(Video video) {
		return super.update(video);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return update("deleteByLogic", param);
	}

	@Override
	public Video selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

}
