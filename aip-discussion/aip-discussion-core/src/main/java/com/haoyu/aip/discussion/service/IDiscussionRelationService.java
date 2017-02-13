package com.haoyu.aip.discussion.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.DiscussionRelation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IDiscussionRelationService{
	
	Response create(DiscussionRelation entity);
	
	Response update(DiscussionRelation entity);
	
	Response delete(String id);
	
	DiscussionRelation get(String id);
	
	List<DiscussionRelation> list(SearchParam searchParam, PageBounds pageBounds);

	Response updateBrowseNum(DiscussionRelation entity);
	
	Response updateReplyNum(DiscussionRelation entity);
	
	Response updateParticipateNum(DiscussionRelation entity);
	
	Response updateSupportNum(DiscussionRelation obj);

	Response updateLastPost(DiscussionRelation discussionRelation);

	Response updateByIdNotSelective(DiscussionRelation entity);

}
