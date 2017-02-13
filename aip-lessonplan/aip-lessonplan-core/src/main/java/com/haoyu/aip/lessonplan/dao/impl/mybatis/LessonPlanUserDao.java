package com.haoyu.aip.lessonplan.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.lessonplan.dao.ILessonPlanUserDao;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class LessonPlanUserDao extends MybatisDao implements ILessonPlanUserDao{

	@Override
	public int deleteByPhysics(String id) {
		return delete("deleteByPhysics", id);
	}

}
