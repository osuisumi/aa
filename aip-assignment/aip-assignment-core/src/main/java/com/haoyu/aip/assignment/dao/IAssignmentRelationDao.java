package com.haoyu.aip.assignment.dao;

import com.haoyu.aip.assignment.entity.AssignmentRelation;

public interface IAssignmentRelationDao {

	int insert(AssignmentRelation entity);

	int update(AssignmentRelation entity);

	AssignmentRelation selectByPrimaryKey(String id);

}
