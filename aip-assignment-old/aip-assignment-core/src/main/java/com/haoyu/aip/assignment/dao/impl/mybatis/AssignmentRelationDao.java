package com.haoyu.aip.assignment.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.assignment.dao.IAssignmentRelationDao;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class AssignmentRelationDao extends MybatisDao implements IAssignmentRelationDao{

	@Override
	public int updateByIdNotSelective(AssignmentRelation obj) {
		return this.update("updateByIdNotSelective", obj);
	}

}
