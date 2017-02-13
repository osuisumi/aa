package com.haoyu.aip.activity.dao.impl.mybatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.activity.dao.IActivityResultDao;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class ActivityResultDao extends MybatisDao implements IActivityResultDao{

	@Override
	public int getCount(Map<String, Object> paramMap) {
		return selectOne("getCount", paramMap);
	}

	@Override
	public int deleteByPhysics(String id) {
		return delete("deleteByPhysics", id);
	}

}
