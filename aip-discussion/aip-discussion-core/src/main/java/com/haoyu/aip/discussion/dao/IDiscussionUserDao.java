package com.haoyu.aip.discussion.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.DiscussionUser;

public interface IDiscussionUserDao {
	
	int insert(DiscussionUser entity);

	int update(DiscussionUser entity);

	int deleteByLogic(DiscussionUser entity);

	DiscussionUser selectByPrimaryKey(String id);

	List<DiscussionUser> select(Map<String, Object> paramMap, PageBounds pageBounds);

	int deleteByPhysics(String id);

	int recover(DiscussionUser discussionUser);

}
