package com.haoyu.aip.discussion.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.DiscussionRelation;

public interface IDiscussionRelationDao {

	int insert(DiscussionRelation entity);

	int update(DiscussionRelation entity);

	int deleteByLogic(DiscussionRelation entity);

	DiscussionRelation selectByPrimaryKey(String id);

	List<DiscussionRelation> select(Map<String, Object> paramMap, PageBounds pageBounds);

	int updateLastPost(DiscussionRelation discussionRelation);

	int updateByIdNotSelective(DiscussionRelation discussionRelation);

}
