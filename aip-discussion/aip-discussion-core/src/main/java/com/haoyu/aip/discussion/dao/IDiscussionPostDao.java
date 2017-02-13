package com.haoyu.aip.discussion.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.DiscussionPost;

public interface IDiscussionPostDao {
	
	int insert(DiscussionPost entity);

	int update(DiscussionPost entity);

	int deleteByLogic(DiscussionPost entity);

	DiscussionPost selectByPrimaryKey(String id);

	List<DiscussionPost> select(Map<String, Object> paramMap, PageBounds pageBounds);

	int getCount(Map<String, Object> param);

}
