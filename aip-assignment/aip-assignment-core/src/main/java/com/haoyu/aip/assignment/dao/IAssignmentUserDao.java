package com.haoyu.aip.assignment.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.AssignmentUser;

public interface IAssignmentUserDao {

	int update(AssignmentUser entity);

	int deleteByLogic(Map<String, Object> param);

	int insert(AssignmentUser entity);

	AssignmentUser selectByPrimaryKey(String id);

	List<AssignmentUser> select(Map<String, Object> param, PageBounds pageBounds);

	int getCount(Map<String, Object> param);

}
