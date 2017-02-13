package com.haoyu.aip.text.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.aip.text.dao.ITextInfoRelationDao;
import com.haoyu.aip.text.entity.TextInfoRelation;
import com.haoyu.aip.text.service.ITextInfoRelationService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class TextInfoRelationServiceImpl implements ITextInfoRelationService{
	
	@Resource
	private ITextInfoRelationDao textInfoRelationDao;

	@Override
	public Response createTextInfoRelation(TextInfoRelation entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		entity.setDefaultValue();
		int count = textInfoRelationDao.insert(entity);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateTextInfoRelation(TextInfoRelation textInfoRelation) {
		textInfoRelation.setUpdatedby(ThreadContext.getUser());
		textInfoRelation.setUpdateTime(System.currentTimeMillis());
		int count = textInfoRelationDao.update(textInfoRelation);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
