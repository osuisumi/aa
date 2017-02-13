package com.haoyu.aip.text.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.haoyu.aip.text.dao.ITextInfoUserDao;
import com.haoyu.aip.text.entity.TextInfoRelation;
import com.haoyu.aip.text.entity.TextInfoUser;
import com.haoyu.aip.text.event.ViewTextInfoEvent;
import com.haoyu.aip.text.service.ITextInfoUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class TextInfoUserServiceImpl implements ITextInfoUserService{
	
	@Resource
	private ITextInfoUserDao textInfoUserDao;
	@Resource
	private ApplicationContext applicationContext;

	@Override
	public Response create(TextInfoUser textInfoUser) {
		if (StringUtils.isEmpty(textInfoUser.getId())) {
			textInfoUser.setId(Identities.uuid2());
		}
		textInfoUser.setDefaultValue();
		int count = textInfoUserDao.insert(textInfoUser);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(TextInfoUser textInfoUser) {
		textInfoUser.setUpdatedby(ThreadContext.getUser());
		textInfoUser.setUpdateTime(System.currentTimeMillis());
		int count = textInfoUserDao.update(textInfoUser);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response deleteByLogic(TextInfoUser textInfoUser) {
		String[] array = textInfoUser.getId().split(",");
		List<String> ids = Arrays.asList(array);
		textInfoUser.setUpdatedby(ThreadContext.getUser());
		textInfoUser.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", textInfoUser);
		int count = textInfoUserDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public TextInfoUser get(String id) {
		return textInfoUserDao.selectByPrimaryKey(id);
	}

	@Override
	public TextInfoUser createTextInfoUserIfNotExists(String textInfoRelationId) {
		String id = TextInfoUser.getId(textInfoRelationId, ThreadContext.getUser().getId() );
		TextInfoUser textInfoUser = textInfoUserDao.selectByPrimaryKey(id);
		if (textInfoUser == null) {
			textInfoUser = new TextInfoUser();
			TextInfoRelation textInfoRelation = new TextInfoRelation();
			textInfoRelation.setId(textInfoRelationId);
			textInfoUser.setTextInfoRelation(textInfoRelation);
			textInfoUser.setId(id);
			textInfoUser.setDefaultValue();
			try {
				textInfoUserDao.insert(textInfoUser);
			} catch (Exception e) {
				
			}
		}
		return textInfoUser;
	}

	@Override
	public Response updateAttempt(TextInfoUser textInfoUser) {
		textInfoUser.setViewNum(1);
		Response response = this.update(textInfoUser);
		if (response.isSuccess()) {
			applicationContext.publishEvent(new ViewTextInfoEvent(textInfoUser));
		}
		return response;
	}

}
