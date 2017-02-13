package com.haoyu.aip.discussion.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.dao.IDiscussionRelationDao;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.aip.discussion.service.IDiscussionRelationService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.utils.Identities;

@Service
public class DiscussionRelationServiceImpl implements IDiscussionRelationService{

	@Resource
	private IDiscussionRelationDao discussionRelationDao;
	
	@Override
	public Response create(DiscussionRelation entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		entity.setDefaultValue();
		int count = discussionRelationDao.insert(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(DiscussionRelation entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionRelationDao.update(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
	@Override
	public Response delete(String id) {
		DiscussionRelation entity = new DiscussionRelation();
		entity.setId(id);
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionRelationDao.deleteByLogic(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public DiscussionRelation get(String id) {
		return discussionRelationDao.selectByPrimaryKey(id);
	}

	@Override
	public List<DiscussionRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return discussionRelationDao.select(searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateBrowseNum(DiscussionRelation entity) {
		entity.setBrowseNum(1);
		return this.update(entity);
	}

	@Override
	public Response updateReplyNum(DiscussionRelation entity) {
		entity.setReplyNum(1);
		return this.update(entity);
	}

	@Override
	public Response updateParticipateNum(DiscussionRelation entity) {
		entity.setParticipateNum(1);
		return this.update(entity);
	}

	@Override
	public Response updateLastPost(DiscussionRelation discussionRelation) {
		int count = discussionRelationDao.updateLastPost(discussionRelation);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateByIdNotSelective(DiscussionRelation entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionRelationDao.updateByIdNotSelective(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateSupportNum(DiscussionRelation obj) {
		obj.setSupportNum(1);
		return this.update(obj);
	}

}
