package com.haoyu.aip.discussion.mobile.service.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.discussion.entity.DiscussionPost;
import com.haoyu.aip.discussion.mobile.entity.MDiscussionPost;
import com.haoyu.aip.discussion.mobile.service.IMDiscussionPostService;
import com.haoyu.aip.discussion.service.IDiscussionPostService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.user.mobile.entity.MUser;
import com.haoyu.sip.utils.Collections3;

@Service
public class MDiscussionPostService implements IMDiscussionPostService{

	@Resource
	private IDiscussionPostService discussionPostService;
	@Resource
	private RedisTemplate redisTemplate;
	
	@Override
	public Response listDiscussionPost(DiscussionPost discussionPost,PageBounds pageBounds) {
		List<MDiscussionPost> mDiscussionPosts = Lists.newArrayList();
		Map<String, Object> param = Maps.newHashMap();
		
		if (Collections3.isEmpty(pageBounds.getOrders())) {
			pageBounds.setOrders(Order.formString("CREATE_TIME.DESC"));
		}
		
		if (discussionPost.getDiscussionUser() != null && discussionPost.getDiscussionUser().getDiscussionRelation() != null) {			
			param.put("discussionRelationId",discussionPost.getDiscussionUser().getDiscussionRelation().getId());
		}
		
		if (StringUtils.isNotEmpty(discussionPost.getMainPostId())) {			
			param.put("mainPostId",discussionPost.getMainPostId());
		}
		
		String discussionRelationId = (String) param.get("discussionRelationId");
		String key = PropertiesLoader.get("redis.app.key") + ":list_discussion_post:" + discussionRelationId + ":" + DigestUtils.md5Hex(param.toString() + pageBounds.toString());
		ValueOperations<String, List<DiscussionPost>> valueOper = redisTemplate.opsForValue();
		List<DiscussionPost> discussionPosts = Lists.newArrayList();
		
		if(redisTemplate.hasKey(key)){
			discussionPosts = valueOper.get(key);
		}else{
			discussionPosts = discussionPostService.list(param, pageBounds);
			valueOper.set(key, discussionPosts);
			redisTemplate.expire(key, 2, TimeUnit.HOURS);
		}
		
		PageList pageList = (PageList)discussionPosts;
		Paginator paginator = pageList.getPaginator();
		
		if (Collections3.isNotEmpty(discussionPosts)) {
			for (DiscussionPost dp : discussionPosts) {
				MDiscussionPost mDiscussionPost = new MDiscussionPost();
				BeanUtils.copyProperties(dp,mDiscussionPost);
				
				if (dp.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(dp.getCreator(),mUser);
					mDiscussionPost.setCreator(mUser);
				}
				
				mDiscussionPosts.add(mDiscussionPost);
			}
		}
		
		Map<String, Object> resultMap = Maps.newHashMap();
		resultMap.put("mDiscussionPosts",mDiscussionPosts);
		resultMap.put("paginator",paginator);
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response createDiscussionPost(DiscussionPost discussionPost) {
		Response response = discussionPostService.createDiscussionPost(discussionPost);
		if (response.isSuccess()) {
			MDiscussionPost mDiscussionPost = new MDiscussionPost();
			if (response.getResponseData() != null) {
				discussionPost = (DiscussionPost) response.getResponseData();
				BeanUtils.copyProperties(discussionPost,mDiscussionPost);
				
				if (discussionPost.getCreator() != null ) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(discussionPost.getCreator(),mUser);
					mDiscussionPost.setCreator(mUser);
				}
			}
			return Response.successInstance().responseData(mDiscussionPost);
		}
		return Response.failInstance();
	}

	@Override
	public Response updateDiscussionPost(DiscussionPost discussionPost) {
		Response response = discussionPostService.updateDiscussionPost(discussionPost);
		if (response.isSuccess()) {
			MDiscussionPost mDiscussionPost = new MDiscussionPost();
			if (response.getResponseData() != null) {
				discussionPost = (DiscussionPost) response.getResponseData();
				BeanUtils.copyProperties(discussionPost,mDiscussionPost);
				
				if (discussionPost.getCreator() != null) {
					MUser mUser = new MUser();
					BeanUtils.copyProperties(discussionPost.getCreator(),mUser);
					mDiscussionPost.setCreator(mUser);
				}
			}
			return Response.successInstance().responseData(mDiscussionPost);
		}
		return Response.failInstance();
	}

}
