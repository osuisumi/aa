package com.haoyu.aip.lessonplan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.lessonplan.dao.ILessonPlanRelationDao;
import com.haoyu.aip.lessonplan.entity.LessonPlanRelation;
import com.haoyu.aip.lessonplan.service.ILessonPlanRelationService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;

@Service
public class LessonPlanRelationServiceImpl implements ILessonPlanRelationService{

	@Resource
	private ILessonPlanRelationDao lessonPlanRelationDao;
	
	@Override
	public Response create(LessonPlanRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)lessonPlanRelationDao);
	}

	@Override
	public Response update(LessonPlanRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)lessonPlanRelationDao);
	}
	
	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)lessonPlanRelationDao);
	}

	@Override
	public LessonPlanRelation get(String id) {
		return (LessonPlanRelation) BaseServiceUtils.get(id, (MybatisDao)lessonPlanRelationDao);
	}

	@Override
	public List<LessonPlanRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)lessonPlanRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateBrowseNum(LessonPlanRelation obj) {
		obj.setBrowseNum(1);
		return this.update(obj);
	}

	@Override
	public Response updateReplyNum(LessonPlanRelation obj) {
		obj.setReplyNum(1);
		return this.update(obj);
	}

	@Override
	public Response updateParticipateNum(LessonPlanRelation obj) {
		obj.setParticipateNum(1);
		return this.update(obj);
	}

	@Override
	public Response updateByIdNotSelective(LessonPlanRelation obj) {
		obj.setUpdatedby(ThreadContext.getUser());
		obj.setUpdateTime(System.currentTimeMillis());
		int count = lessonPlanRelationDao.updateByIdNotSelective(obj);
		return count>0?Response.successInstance():Response.failInstance();
	}
	
}
