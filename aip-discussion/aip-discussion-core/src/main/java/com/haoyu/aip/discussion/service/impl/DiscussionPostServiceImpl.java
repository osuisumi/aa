package com.haoyu.aip.discussion.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.discussion.dao.IDiscussionPostDao;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.entity.DiscussionUser;
import com.haoyu.aip.discussion.event.CreateDiscussionPostEvent;
import com.haoyu.aip.discussion.event.CreateDiscussionUserEvent;
import com.haoyu.aip.discussion.event.DeleteDiscussionPostEvent;
import com.haoyu.aip.discussion.event.DeleteDiscussionUserEvent;
import com.haoyu.aip.discussion.event.UpdateDiscussionPostEvent;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.aip.discussion.service.IDiscussionRelationService;
import com.haoyu.aip.discussion.service.IDiscussionUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Identities;

@Service
public class DiscussionPostServiceImpl implements IDiscussionPostService {

	@Resource
	private IDiscussionPostDao discussionPostDao;
	@Resource
	private IDiscussionUserService discussionUserService;
	@Resource
	private IDiscussionRelationService discussionRelationService;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(DiscussionPost entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		entity.setDefaultValue();
		int count = discussionPostDao.insert(entity);
		if (count > 0) {
			return Response.successInstance().responseData(entity);
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(DiscussionPost entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionPostDao.update(entity);
		if (count>0) { 
			applicationContext.publishEvent(new UpdateDiscussionPostEvent(entity));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response delete(String id) {
		DiscussionPost entity = new DiscussionPost();
		entity.setId(id);
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionPostDao.deleteByLogic(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public DiscussionPost get(String id) {
		return discussionPostDao.selectByPrimaryKey(id);
	}

	@Override
	public List<DiscussionPost> list(SearchParam searchParam, PageBounds pageBounds) {
		return discussionPostDao.select(searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response createDiscussionPost(DiscussionPost post) {
		DiscussionUser discussionUser = post.getDiscussionUser();
		String id = DiscussionUser.getId(discussionUser.getDiscussionRelation().getId(), ThreadContext.getUser().getId());
		discussionUser.setId(id);
		//创建帖子
		Response response = this.create(post);
		if (response.isSuccess()) {			
			//如果是子帖, 更新主帖的子帖数
			if (StringUtils.isNotEmpty(post.getMainPostId()) && !"null".equals(post.getMainPostId())) {
				DiscussionPost mainPost = new DiscussionPost();
				mainPost.setId(post.getMainPostId());
				this.updateChildPostCount(mainPost);  
			}
			//添加参与记录
			discussionUser.setDiscussionRelation(discussionUser.getDiscussionRelation());
			try {
				if (!discussionUserService.recoverDiscussionUser(id).isSuccess()) {
					discussionUserService.create(discussionUser);
				}
				//参与事件
				applicationContext.publishEvent(new CreateDiscussionUserEvent(discussionUser));
			} catch (DuplicateKeyException e) {
				
			}
			/* 事件  回复帖子 */
			applicationContext.publishEvent(new CreateDiscussionPostEvent(post));
			response.setResponseData(post);
		}
		return response;
	}

	@Override
	public Response updateChildPostCount(DiscussionPost post) {
		post.setChildPostCount(1);
		return this.update(post);
	}

	@Override
	public Response deleteDiscussionPost(DiscussionPost post) {
		Response response = this.delete(post.getId());
		if (response.isSuccess()) {
			if (StringUtils.isNotEmpty(post.getMainPostId()) && !"null".equals(post.getMainPostId())) {
				DiscussionPost mainPost = new DiscussionPost();
				mainPost.setId(post.getMainPostId());
				this.updateChildPostCount(mainPost);
			}
			DiscussionUser discussionUser = post.getDiscussionUser();
			String discussionUserId = DiscussionUser.getId(discussionUser.getDiscussionRelation().getId(), ThreadContext.getUser().getId());
			discussionUser.setId(discussionUserId);
			int count = this.getCount(post);
			if (count == 0) {
				discussionUserService.deleteDiscussionUser(discussionUserId);
				applicationContext.publishEvent(new DeleteDiscussionUserEvent(post));
			}
			applicationContext.publishEvent(new DeleteDiscussionPostEvent(post));
		}
		return response;
	}

	@Override
	public Response updateDiscussionPost(DiscussionPost post) {
		return this.update(post);
	}

	@Override
	public int getCount(DiscussionPost post) {
		Map<String, Object> param = Maps.newHashMap();
		if (post != null) {
			if (post.getDiscussionUser() != null && StringUtils.isNotEmpty(post.getDiscussionUser().getId())) {
				param.put("discussionUserId", post.getDiscussionUser().getId());
			}
			if (post.getCreator() != null && StringUtils.isNotEmpty(post.getCreator().getId())) {
				param.put("creator", post.getCreator().getId());
			}
		}
		return discussionPostDao.getCount(param);
	}
	
	@Override
	public int getCount(Map<String, Object> param) {
		return discussionPostDao.getCount(param);
	}

	@Override
	public List<DiscussionPost> list(Map<String, Object> param, PageBounds pageBounds) {
		return discussionPostDao.select(param, pageBounds);
	}
	
	@Override
	public List<DiscussionPost> list(DiscussionPost post, PageBounds pageBounds) {
		SearchParam searchParam = new SearchParam();
		Map<String, Object> param = Maps.newHashMap();
		if (post.getDiscussionUser() != null && post.getDiscussionUser().getDiscussionRelation() != null) {
			param.put("discussionRelationId",post.getDiscussionUser().getDiscussionRelation().getId());
		}
		param.put("mainPostId",post.getMainPostId());
		searchParam.setParamMap(param);
		return this.list(searchParam, pageBounds);
	}

}
