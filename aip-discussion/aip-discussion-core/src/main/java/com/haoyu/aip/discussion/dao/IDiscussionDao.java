package com.haoyu.aip.discussion.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.Discussion;

public interface IDiscussionDao{

	int insert(Discussion entity);

	int update(Discussion entity);

	int deleteByLogic(Discussion entity);

	Discussion selectByPrimaryKey(String id);

	List<Discussion> select(Map<String, Object> paramMap, PageBounds pageBounds);

	Discussion selectByOp(Map<String, Object> param,PageBounds pageBounds);

	int updateByIds(Map<String, Object> map);

	int deleteByIds(Map<String, Object> map);

	int getCount(Map<String, Object> param);
	
}
