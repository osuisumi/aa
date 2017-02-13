package com.haoyu.aip.video.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.video.dao.IVideoRelationDao;
import com.haoyu.aip.video.entity.VideoRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class VideoRelationDao extends MybatisDao implements IVideoRelationDao{

	@Override
	public int insert(VideoRelation videoRelation) {
		return super.insert(videoRelation);
	}

	@Override
	public int update(VideoRelation videoRelation) {
		return super.update(videoRelation);
	}

}
