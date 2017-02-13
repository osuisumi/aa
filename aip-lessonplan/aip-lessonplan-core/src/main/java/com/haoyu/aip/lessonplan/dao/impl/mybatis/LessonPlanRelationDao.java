package com.haoyu.aip.lessonplan.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.lessonplan.dao.ILessonPlanRelationDao;
import com.haoyu.aip.lessonplan.entity.LessonPlanRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class LessonPlanRelationDao extends MybatisDao implements ILessonPlanRelationDao{

	@Override
	public int updateByIdNotSelective(LessonPlanRelation obj) {
		return this.update("updateByIdNotSelective", obj);
	}

}
