package com.haoyu.aip.courseware.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.courseware.dao.ICoursewareRelationDao;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class CoursewareRelationDao extends MybatisDao implements ICoursewareRelationDao{

	@Override
	public int updateParticipateNum(String listenCourseId) {
		return this.update("updateParticipateNum", listenCourseId);
	}

}
