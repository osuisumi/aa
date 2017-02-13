package com.haoyu.aip.discussion.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.sip.core.service.Response;

public interface IMDiscussionPostService {

	Response listDiscussionPost(DiscussionPost discussionPost,PageBounds pageBounds);

	Response createDiscussionPost(DiscussionPost discussionPost);

	Response updateDiscussionPost(DiscussionPost discussionPost);

}
