package com.haoyu.aip.discussion.mobile.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.discussion.entity.Discussion;
import com.haoyu.sip.core.service.Response;

public interface IMDiscussionService {

	Response listDiscission(Discussion discussion, PageBounds pageBounds);

	Response getDiscussion(Discussion discussion);

	Response createDiscussion(Discussion discussion);

	Response updateDiscussion(Discussion discussion);

}
