package com.haoyu.aip.video.dao.impl.mybatis;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.haoyu.aip.video.dao.IVideoUserDao;
import com.haoyu.aip.video.entity.VideoUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class VideoUserDao extends MybatisDao implements IVideoUserDao{

	@Override
	public int insert(VideoUser videoUser) {
		return super.insert(videoUser);
	}

	@Override
	public int update(VideoUser videoUser) {
		try{
			String lastViewTime = String.valueOf(videoUser.getLastViewTime());
			if(!StringUtils.isNumeric(lastViewTime)){
				videoUser.setLastViewTime(0);
			}
			return super.update(videoUser);
		} catch (Exception e) {
			System.out.println("viewNum:"+videoUser.getViewNum());
			System.out.println("lastViewTime:"+ videoUser.getLastViewTime());
			System.out.println("viewTime:"+ videoUser.getViewTime());
			System.out.println("id:"+ videoUser.getId());
			System.out.println("creator"+ videoUser.getCreator());
			throw e;
		}
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return super.deleteByLogic(param);
	}

	@Override
	public VideoUser selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

}
