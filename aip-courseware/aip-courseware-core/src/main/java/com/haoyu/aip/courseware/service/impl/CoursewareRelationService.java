package com.haoyu.aip.courseware.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.aip.courseware.dao.ICoursewareRelationDao;
import com.haoyu.aip.courseware.entity.Courseware;
import com.haoyu.aip.courseware.entity.CoursewareRelation;
import com.haoyu.aip.courseware.service.ICoursewareRelationService;
import com.haoyu.base.utils.BaseServiceUtils;

@Service("listenCourseRelationService")
public class CoursewareRelationService implements ICoursewareRelationService {
	@Resource
	private ICoursewareRelationDao listenCourseRelationDao;

	@Override
	public Response deleteByLogic(CoursewareRelation coursewareRelation) {
		return BaseServiceUtils.delete(coursewareRelation.getId(), (MybatisDao)this.listenCourseRelationDao);
	}

	@Override
	public Response update(CoursewareRelation coursewareRelation) {
		return BaseServiceUtils.update(coursewareRelation, (MybatisDao)this.listenCourseRelationDao);
	}


	@Override
	public Response create(CoursewareRelation coursewareRelation) {
		return BaseServiceUtils.create(coursewareRelation, (MybatisDao)this.listenCourseRelationDao);
	}

	@Override
	public CoursewareRelation get(String id) {
		return (CoursewareRelation) BaseServiceUtils.get(id, (MybatisDao)this.listenCourseRelationDao);
	}

	@Override
	public List<Courseware> list(CoursewareRelation coursewareRelation, PageBounds pageBounds) {
		return ((MybatisDao)this.listenCourseRelationDao).selectList("select",coursewareRelation,pageBounds);
	}

}
