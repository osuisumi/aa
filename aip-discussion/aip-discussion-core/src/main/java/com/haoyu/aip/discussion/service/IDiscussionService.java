package com.haoyu.aip.discussion.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IDiscussionService {
	
	Response create(Discussion entity);
	
	Response update(Discussion entity);
	
	Response delete(String id);
	
	Discussion get(String id);
	
	List<Discussion> list(SearchParam searchParam, PageBounds pageBounds);
	
	List<Discussion> list(Map<String, Object> param, PageBounds pageBounds);
	
	/**
	 * 创建研讨
	 * discussion参数用来创建研讨
	 * discussion.discussionRelations参数用来创建关系
	 */
	Response createDiscussion(Discussion discussion);
	
	/**
	 * 更新研讨
	 * discussion参数用来更新研讨表的数据
	 * discussion.discussionRelations参数用来更新关系表的数据
	 */
	Response updateDiscussion(Discussion discussion);

	/**
	 * 查询研讨
	 */
	Discussion viewDiscussion(String id);

	Response deleteDiscussion(Discussion discussion);

	Discussion viewDiscussion(String id, boolean updateBrowseNum);

	Discussion getDiscussionByOp(Map<String, Object> param,PageBounds pageBounds);

	Response updateDiscussions(Discussion discussion);

	Response deleteDiscussions(Discussion discussion);
	
	List<Discussion> list(Discussion entity, PageBounds pageBounds);

	int getCount(Map<String, Object> param);
	
}
