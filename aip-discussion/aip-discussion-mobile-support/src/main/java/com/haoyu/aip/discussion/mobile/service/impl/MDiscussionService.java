package com.haoyu.aip.discussion.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.aip.discussion.mobile.entity.MDiscussion;
import com.haoyu.aip.discussion.mobile.entity.MDiscussionRelation;
import com.haoyu.aip.discussion.mobile.service.IMDiscussionService;
import com.haoyu.aip.discussion.service.IDiscussionService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.user.mobile.entity.MUser;
import com.haoyu.sip.utils.Collections3;

@Service
public class MDiscussionService implements IMDiscussionService{

	@Resource
	private IDiscussionService discussionService;
	
	@Override
	public Response listDiscission(Discussion discussion, PageBounds pageBounds) {
		List<MDiscussion> mDiscussions = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		
		if (Collections3.isNotEmpty(discussion.getDiscussionRelations()) && discussion.getDiscussionRelations().get(0) != null
				&& discussion.getDiscussionRelations().get(0).getRelation() != null) {
			param.put("relationId",discussion.getDiscussionRelations().get(0).getRelation().getId());
			param.put("relationType",discussion.getDiscussionRelations().get(0).getRelation().getType());
		}
		List<Discussion> discussions = discussionService.list(param, pageBounds);
		
		PageList pageList = (PageList)discussions;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(discussions)) {
			
			for (Discussion d : discussions) {
				MDiscussion mDiscussion = new MDiscussion();
				
				BeanUtils.copyProperties(d,mDiscussion);
				
				if (d.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(d.getCreator(),mUser);
					mDiscussion.setCreator(mUser);
				}
				
				if (Collections3.isNotEmpty(d.getDiscussionRelations())) {
					List<MDiscussionRelation> mDiscussionRelations = Lists.newArrayList();
					for (DiscussionRelation dr : d.getDiscussionRelations()) {
						MDiscussionRelation mDiscussionRelation = new MDiscussionRelation();
						BeanUtils.copyProperties(dr,mDiscussionRelation);
						mDiscussionRelations.add(mDiscussionRelation);
					}
					mDiscussion.setmDiscussionRelations(mDiscussionRelations);
				}
			
				mDiscussions.add(mDiscussion);
			}
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("mDiscussions",mDiscussions);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response getDiscussion(Discussion discussion) {
		if (StringUtils.isNotEmpty(discussion.getId())) {
			MDiscussion mDiscussion = new MDiscussion();
			discussion = discussionService.get(discussion.getId());
			BeanUtils.copyProperties(discussion,mDiscussion);
			
			if (discussion.getCreator() != null) {
				MUser mUser = new MUser();
				BeanUtils.copyProperties(discussion.getCreator(),mUser);
				mDiscussion.setCreator(mUser);
			}
			
			if (Collections3.isNotEmpty(discussion.getDiscussionRelations())) {
				List<MDiscussionRelation> mDiscussionRelations = Lists.newArrayList();
				for (DiscussionRelation dr : discussion.getDiscussionRelations()) {
					MDiscussionRelation mDiscussionRelation = new MDiscussionRelation();
					BeanUtils.copyProperties(dr,mDiscussionRelation);
					mDiscussionRelations.add(mDiscussionRelation);
				}
				mDiscussion.setmDiscussionRelations(mDiscussionRelations);
			}
			
			return Response.successInstance().responseData(mDiscussion);
		}
		return Response.failInstance();
	}

	@Override
	public Response createDiscussion(Discussion discussion) {
		Response response = discussionService.createDiscussion(discussion);
		if (response.isSuccess()) {
			MDiscussion mDiscussion = new MDiscussion();
			if (response.getResponseData() != null) {
				discussion = (Discussion) response.getResponseData();
				BeanUtils.copyProperties(discussion,mDiscussion);
				
				if (discussion.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(discussion.getCreator(),mUser);
					mDiscussion.setCreator(mUser);
				}
				
				if (Collections3.isNotEmpty(discussion.getDiscussionRelations())) {
					List<MDiscussionRelation> mDiscussionRelations = Lists.newArrayList();
					for (DiscussionRelation dr : discussion.getDiscussionRelations()) {
						MDiscussionRelation mDiscussionRelation = new MDiscussionRelation();
						BeanUtils.copyProperties(dr,mDiscussionRelation);
						mDiscussionRelations.add(mDiscussionRelation);
					}
					mDiscussion.setmDiscussionRelations(mDiscussionRelations);
				}
			}
			return Response.successInstance().responseData(mDiscussion);
		}
		return Response.failInstance();
	}

	@Override
	public Response updateDiscussion(Discussion discussion) {
		Response response = discussionService.updateDiscussion(discussion);
		if (response.isSuccess()) {
			MDiscussion mDiscussion = new MDiscussion();
			if (response.getResponseData() != null) {
				discussion = (Discussion) response.getResponseData();
				BeanUtils.copyProperties(discussion,mDiscussion);
				
				if (discussion.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(discussion.getCreator(),mUser);
					mDiscussion.setCreator(mUser);
				}
				
				if (Collections3.isNotEmpty(discussion.getDiscussionRelations())) {
					List<MDiscussionRelation> mDiscussionRelations = Lists.newArrayList();
					for (DiscussionRelation dr : discussion.getDiscussionRelations()) {
						MDiscussionRelation mDiscussionRelation = new MDiscussionRelation();
						BeanUtils.copyProperties(dr,mDiscussionRelation);
						mDiscussionRelations.add(mDiscussionRelation);
					}
					mDiscussion.setmDiscussionRelations(mDiscussionRelations);
				}
			}
			return Response.successInstance().responseData(mDiscussion);
		}
		return Response.failInstance();
	}

}
