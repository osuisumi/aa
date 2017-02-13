package com.haoyu.aip.discussion.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.dao.IDiscussionPostDao;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class DiscussionPostDao extends MybatisDao implements IDiscussionPostDao{
	
	@Override
	public int insert(DiscussionPost entity) {
		return super.insert(entity);
	}

	@Override
	public int update(DiscussionPost entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByLogic(DiscussionPost entity) {
		return super.deleteByLogic(entity);
	}

	@Override
	public DiscussionPost selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<DiscussionPost> select(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

}
