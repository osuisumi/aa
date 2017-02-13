package com.haoyu.aip.discussion.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.DiscussionUser;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

public interface IDiscussionUserService {

	Response create(DiscussionUser entity);
	
	Response update(DiscussionUser entity);
	
	Response delete(String id);
	
	DiscussionUser get(String id);
	
	List<DiscussionUser> list(SearchParam searchParam, PageBounds pageBounds);

	Response deleteDiscussionUser(String discussionUserId);

	Response recoverDiscussionUser(String id);

}
