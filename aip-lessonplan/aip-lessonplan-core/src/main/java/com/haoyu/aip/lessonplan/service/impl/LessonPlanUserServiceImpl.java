package com.haoyu.aip.lessonplan.service.impl;


import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.haoyu.aip.lessonplan.dao.ILessonPlanUserDao;
import com.haoyu.aip.lessonplan.entity.LessonPlanUser;
import com.haoyu.aip.lessonplan.service.ILessonPlanUserService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;

@Service
public class LessonPlanUserServiceImpl implements ILessonPlanUserService{

	@Resource
	private ILessonPlanUserDao lessonPlanUserDao;


	@Override
	public Response create(LessonPlanUser obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)lessonPlanUserDao);
	}

	@Override
	public Response deleteLessonPlanUser(String id) {
		int count = lessonPlanUserDao.deleteByPhysics(id);
		return count>0?Response.successInstance():Response.failInstance();
	}
}
