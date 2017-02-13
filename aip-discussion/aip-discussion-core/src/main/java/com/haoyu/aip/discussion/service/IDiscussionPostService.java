package com.haoyu.aip.discussion.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IDiscussionPostService {

	Response create(DiscussionPost entity);
	
	Response update(DiscussionPost entity);
	
	Response delete(String id);
	
	DiscussionPost get(String id);
	
	List<DiscussionPost> list(SearchParam searchParam, PageBounds pageBounds);
	
	Response updateChildPostCount(DiscussionPost post);

	/**
	 * 发表回复
	 * 必需参数: post.discussionUser.discussionRelation.id
	 * 可选参数: post.discussionUser.discussionRelation.discussion.id, post.discussionUser.discussionRelation.relation.id		  
	 */
	Response createDiscussionPost(DiscussionPost post);
	
	/**
	 * 删除回复
	 * post.id参数用来删除回复
	 * 若删除子帖,post.mainPostId用来更新主帖的回复数
	 * 若配置了更新回复数的事件监听,则post.discussionUser.discussionRelation.id不能为空
	 * 若配置了更新参与记录的事件监听(如更改参与状态为未完成),则post.discussionUser.id不能为空
	 * 若配置了更新最后回复的事件监听,则post.discussionUser.discussionRelation.id不能为空
	 */
	Response deleteDiscussionPost(DiscussionPost post);

	Response updateDiscussionPost(DiscussionPost post);

	int getCount(DiscussionPost post);

	int getCount(Map<String, Object> param);

	List<DiscussionPost> list(Map<String, Object> param, PageBounds pageBounds);
	
	List<DiscussionPost> list(DiscussionPost post, PageBounds pageBounds);

}
