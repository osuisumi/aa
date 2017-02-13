package com.haoyu.aip.discussion.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.dao.IDiscussionUserDao;
import com.haoyu.aip.discussion.entity.DiscussionUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class DiscussionUserDao extends MybatisDao implements IDiscussionUserDao{
	
	@Override
	public int insert(DiscussionUser entity) {
		return super.insert(entity);
	}

	@Override
	public int update(DiscussionUser entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByLogic(DiscussionUser entity) {
		return super.deleteByLogic(entity);
	}

	@Override
	public DiscussionUser selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<DiscussionUser> select(Map<String, Object> paramMap, PageBounds pageBounds) {
		return selectList("select", paramMap, pageBounds);
	}

	@Override
	public int deleteByPhysics(String id) {
		return delete("deleteByPhysics", id);
	}

	@Override
	public int recover(DiscussionUser discussionUser) {
		return super.update("recover", discussionUser);
	}

}
