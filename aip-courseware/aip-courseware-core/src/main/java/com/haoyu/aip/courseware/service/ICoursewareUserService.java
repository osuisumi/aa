package com.haoyu.aip.courseware.service;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.courseware.entity.CoursewareUser;
import com.haoyu.sip.core.service.Response;

public interface ICoursewareUserService {
	Response create(CoursewareUser coursewareUser);

	Response update(CoursewareUser coursewareUser);

	Response deleteByLogic(CoursewareUser CoursewareUser);

	CoursewareUser get(String id);

	List<CoursewareUser> list(CoursewareUser coursewareUser,PageBounds pageBounds);

}
