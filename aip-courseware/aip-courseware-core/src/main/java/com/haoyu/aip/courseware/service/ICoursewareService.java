package com.haoyu.aip.courseware.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.courseware.entity.Courseware;
import com.haoyu.sip.core.service.Response;

public interface ICoursewareService {
	Response create(Courseware listenCourse);

	Response update(Courseware listenCourse);

	Response deleteByLogic(Courseware courseware);

	Courseware get(String id);

	List<Courseware> list(Courseware listenCourse,PageBounds pageBounds);

}
