package com.haoyu.aip.lessonplan.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.lessonplan.dao.ILessonPlanRecordDao;
import com.haoyu.aip.lessonplan.entity.LessonPlanRecord;
import com.haoyu.aip.lessonplan.event.CreateLessonPlanRecordEvent;
import com.haoyu.aip.lessonplan.event.DeleteLessonPlanRecordEvent;
import com.haoyu.aip.lessonplan.service.ILessonPlanRecordService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;

@Service
public class LessonPlanRecordServiceImpl implements ILessonPlanRecordService{

	@Resource
	private ILessonPlanRecordDao lessonPlanRecordDao;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(LessonPlanRecord obj) {
		Response response = BaseServiceUtils.create(obj, (MybatisDao)lessonPlanRecordDao);
		if (response.isSuccess()) {
			applicationContext.publishEvent(new CreateLessonPlanRecordEvent(obj));
		}
		return response;
	}

	@Override
	public Response update(LessonPlanRecord obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)lessonPlanRecordDao);
	}

	@Override
	public Response delete(String id) {
		Response response = BaseServiceUtils.delete(id, (MybatisDao)lessonPlanRecordDao);
		if (response.isSuccess()) {
			applicationContext.publishEvent(new DeleteLessonPlanRecordEvent(id));
		}
		return response;
	}

	@Override
	public LessonPlanRecord get(String id) {
		return (LessonPlanRecord) BaseServiceUtils.get(id, (MybatisDao)lessonPlanRecordDao);
	}

	@Override
	public List<LessonPlanRecord> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)lessonPlanRecordDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
}
