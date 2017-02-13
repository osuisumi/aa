package com.haoyu.aip.courseware.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.courseware.entity.Courseware;
import com.haoyu.aip.courseware.entity.CoursewareRelation;
import com.haoyu.sip.core.service.Response;

public interface ICoursewareRelationService{
	
	Response create(CoursewareRelation listenCourseRelation);

	Response update(CoursewareRelation listenCourseRelation);

	Response deleteByLogic(CoursewareRelation coursewareRelation);

	CoursewareRelation get(String id);

	List<Courseware> list(CoursewareRelation listenCourseRelation,PageBounds pageBounds);

}
