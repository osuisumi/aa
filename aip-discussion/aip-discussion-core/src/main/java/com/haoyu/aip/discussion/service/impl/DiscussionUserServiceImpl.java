package com.haoyu.aip.discussion.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.dao.IDiscussionUserDao;
import com.haoyu.aip.discussion.entity.DiscussionUser;
import com.haoyu.aip.discussion.service.IDiscussionUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Identities;

@Service
public class DiscussionUserServiceImpl implements IDiscussionUserService{

	@Resource
	private IDiscussionUserDao discussionUserDao;
	
	@Override
	public Response create(DiscussionUser entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		entity.setDefaultValue();
		int count = discussionUserDao.insert(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(DiscussionUser entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionUserDao.update(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response delete(String id) {
		DiscussionUser entity = new DiscussionUser();
		entity.setId(id);
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionUserDao.deleteByLogic(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public DiscussionUser get(String id) {
		return discussionUserDao.selectByPrimaryKey(id);
	}

	@Override
	public List<DiscussionUser> list(SearchParam searchParam, PageBounds pageBounds) {
		return discussionUserDao.select(searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response deleteDiscussionUser(String id) {
		DiscussionUser discussionUser = new DiscussionUser();
		discussionUser.setId(id);
		discussionUser.setUpdatedby(ThreadContext.getUser());
		discussionUser.setUpdateTime(System.currentTimeMillis());
		int count = discussionUserDao.deleteByLogic(discussionUser);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response recoverDiscussionUser(String id) {
		DiscussionUser discussionUser = new DiscussionUser();
		discussionUser.setId(id);
		discussionUser.setUpdatedby(ThreadContext.getUser());
		discussionUser.setUpdateTime(System.currentTimeMillis());
		int count = discussionUserDao.recover(discussionUser);
		return count>0?Response.successInstance():Response.failInstance();
	}
}
