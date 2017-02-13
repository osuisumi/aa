package com.haoyu.aip.discussion.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.discussion.dao.IDiscussionDao;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.aip.discussion.event.CreateDiscussionEvent;
import com.haoyu.aip.discussion.event.DeleteDiscussionEvent;
import com.haoyu.aip.discussion.event.UpdateDiscussionEvent;
import com.haoyu.aip.discussion.service.IDiscussionRelationService;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.aip.discussion.service.IDiscussionUserService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class DiscussionServiceImpl implements IDiscussionService {

	@Resource
	private IDiscussionDao discussionDao;
	@Resource
	private IDiscussionRelationService discussionRelationService;
	@Resource
	private IDiscussionUserService discussionUserService;
	@Resource
	private IFileService fileService;
	@Resource
	private ITagRelationService tagRelationService;
	@Resource  
	private ApplicationContext applicationContext;  
	
	@Override
	public Response create(Discussion entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		entity.setDefaultValue();
		int count = discussionDao.insert(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(Discussion entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionDao.update(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response delete(String id) {
		Discussion entity = new Discussion();
		entity.setId(id);
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = discussionDao.deleteByLogic(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Discussion get(String id) {
		return discussionDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Discussion> list(SearchParam searchParam, PageBounds pageBounds) {
		return discussionDao.select(searchParam.getParamMap(), pageBounds);
	}
	
	@Override
	public Response createDiscussion(Discussion discussion) {
		Response response = this.create(discussion);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(discussion.getDiscussionRelations())) {
				for (DiscussionRelation discussionRelation : discussion.getDiscussionRelations()) {
					if (discussionRelation.getRelation() != null && StringUtils.isNotEmpty(discussionRelation.getRelation().getId())) {
						if (discussionRelation.getDiscussion() == null || StringUtils.isEmpty(discussionRelation.getDiscussion().getId())) {
							discussionRelation.setDiscussion(discussion);
						}
						String id = DiscussionRelation.getId(discussionRelation.getDiscussion().getId(), discussionRelation.getRelation().getId());
						discussionRelation.setId(id);
						discussionRelationService.create(discussionRelation);
					}
					discussionRelation.setDiscussion(null);
				}
			}
			fileService.createFileList(discussion.getFileInfos(), discussion.getId(), "discussion");
			tagRelationService.createTagRelation(discussion.getTags(), new Relation(discussion.getId(),"discussion"), true);
			applicationContext.publishEvent(new CreateDiscussionEvent(discussion));
			response.setResponseData(discussion);
		}
		return response;
	}

	@Override
	public Response updateDiscussion(Discussion discussion) {
		Response response = this.update(discussion);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(discussion.getDiscussionRelations())) {
				for (DiscussionRelation discussionRelation : discussion.getDiscussionRelations()) {
					if (StringUtils.isNotEmpty(discussionRelation.getId())) {
						discussionRelationService.updateByIdNotSelective(discussionRelation);
					}
				}
			}
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(discussion.getId());
			fileService.updateFileList(discussion.getFileInfos(), oldFileInfos, discussion.getId(), "discussion");
			tagRelationService.createTagRelation(discussion.getTags(), new Relation(discussion.getId(),"discussion"), true);
			applicationContext.publishEvent(new UpdateDiscussionEvent(discussion));
			response.setResponseData(discussion);
		}
		return response;
	}

	@Override
	public Discussion viewDiscussion(String id) {
		return this.viewDiscussion(id, true);
	}
	
	@Override
	public Discussion viewDiscussion(String id, boolean updateBrowseNum) {
		Discussion discussion = this.get(id);
		if (discussion != null) {
			discussion.setFileInfos(fileService.listFileInfoByRelationId(discussion.getId()));
			/*if (updateBrowseNum) {
				if (Collections3.isNotEmpty(discussion.getDiscussionRelations())) {
					for(DiscussionRelation discussionRelation : discussion.getDiscussionRelations()){
						DiscussionRelation dr = new DiscussionRelation();
						dr.setId(discussionRelation.getId());
						Runnable r = ()->{
							try {
								Thread.sleep(RandomUtils.nextInt(5000, 15000));
								discussionRelationService.updateBrowseNum(dr);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						};
						new Thread(r).start();
					}
				}
			}*/
		}
		return discussion;
	}

	@Override
	public Response deleteDiscussion(Discussion discussion) {
		Response response = this.delete(discussion.getId());
		if (response.isSuccess()) {
			applicationContext.publishEvent(new DeleteDiscussionEvent(discussion));
		}
		return response;
	}

	@Override
	public List<Discussion> list(Discussion entity, PageBounds pageBounds) {
		Map<String,Object> param = Maps.newHashMap();
		if (StringUtils.isNotEmpty(entity.getId())) {
			List<String> ids = Arrays.asList(entity.getId().split(","));
			param.put("ids", ids);
		}
		if (entity.getDiscussionRelations() != null && entity.getDiscussionRelations().size() > 0 && entity.getDiscussionRelations().get(0) != null ) {
			if(entity.getDiscussionRelations().get(0).getRelation() != null){
				Relation relation = entity.getDiscussionRelations().get(0).getRelation();
				if(StringUtils.isNotEmpty(relation.getId())){
					param.put("relationId", relation.getId());
				}
				if(StringUtils.isNotEmpty(relation.getType())){
					param.put("relationType", relation.getType());
				}
			}
			if (StringUtils.isNotEmpty(entity.getDiscussionRelations().get(0).getIsEssence())) {
				param.put("isEssence",entity.getDiscussionRelations().get(0).getIsEssence());
			}
		}
		if (entity.getCreator() != null && StringUtils.isNotEmpty(entity.getCreator().getId())) {
			param.put("creator", entity.getCreator().getId());
		}
		return discussionDao.select(param, pageBounds);
	}
	
	@Override
	public List<Discussion> list(Map<String, Object> param, PageBounds pageBounds) {
		return discussionDao.select(param, pageBounds);
	}

	@Override
	public Discussion getDiscussionByOp(Map<String, Object> param,PageBounds pageBounds) {
		return discussionDao.selectByOp(param,pageBounds);
	}

	@Override
	public Response updateDiscussions(Discussion discussion) {
		discussion.setUpdatedby(ThreadContext.getUser());
		discussion.setUpdateTime(System.currentTimeMillis());
		String[] array = discussion.getId().split(",");
		List<String> ids = Arrays.asList(array);
		Map<String, Object> map = Maps.newHashMap();
		map.put("entity", discussion);
		map.put("ids", ids);
		int count = discussionDao.updateByIds(map);
		if (count > 0) {
			for (String id : ids) {
				Discussion dis = new Discussion();
				dis.setId(id);
				applicationContext.publishEvent(new UpdateDiscussionEvent(dis));
			}
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteDiscussions(Discussion discussion) {
		discussion.setUpdatedby(ThreadContext.getUser());
		discussion.setUpdateTime(System.currentTimeMillis());
		String[] array = discussion.getId().split(",");
		List<String> ids = Arrays.asList(array);
		Map<String, Object> map = Maps.newHashMap();
		map.put("entity", discussion);
		map.put("ids", ids);
		int count = discussionDao.deleteByIds(map);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return discussionDao.getCount(param);
	}
	
}
