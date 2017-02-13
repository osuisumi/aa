package com.haoyu.aip.courseware.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.courseware.dao.ICoursewareUserDao;
import com.haoyu.aip.courseware.entity.CoursewareUser;
import com.haoyu.aip.courseware.event.CreateCoursewareUserEvent;
import com.haoyu.aip.courseware.service.ICoursewareUserService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
@Service
public class CoursewareUserService extends BaseServiceUtils implements ICoursewareUserService{

	@Resource
	private ICoursewareUserDao coursewareUserDao;
	@Resource
	private ApplicationContext applicationContext;
	@Override
	public Response create(CoursewareUser coursewareUser) {
		coursewareUser.setDefaultValue();
		int count = ((MybatisDao)coursewareUserDao).insert("insert", coursewareUser);
		if(count>0){
			applicationContext.publishEvent(new CreateCoursewareUserEvent(coursewareUser));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response update(CoursewareUser coursewareUser) {
		return BaseServiceUtils.update(coursewareUser, (MybatisDao)coursewareUserDao);
	}

	@Override
	public Response deleteByLogic(CoursewareUser coursewareUser) {
		return BaseServiceUtils.delete(coursewareUser.getId(), (MybatisDao)coursewareUserDao);
	}

	@Override
	public CoursewareUser get(String id) {
		return (CoursewareUser) BaseServiceUtils.get(id, (MybatisDao)coursewareUserDao);
	}

	@Override
	public List<CoursewareUser> list(CoursewareUser coursewareUser, PageBounds pageBounds) {
		return ((MybatisDao)coursewareUserDao).selectList("select", coursewareUser, pageBounds);
	}

}
