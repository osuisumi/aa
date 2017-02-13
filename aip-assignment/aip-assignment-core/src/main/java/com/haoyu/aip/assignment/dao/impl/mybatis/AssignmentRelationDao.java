package com.haoyu.aip.assignment.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.assignment.dao.IAssignmentRelationDao;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class AssignmentRelationDao extends MybatisDao implements IAssignmentRelationDao{

	@Override
	public int insert(AssignmentRelation entity) {
		return super.insert(entity);
	}

	@Override
	public int update(AssignmentRelation entity) {
		return super.update(entity);
	}

	@Override
	public AssignmentRelation selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

}
