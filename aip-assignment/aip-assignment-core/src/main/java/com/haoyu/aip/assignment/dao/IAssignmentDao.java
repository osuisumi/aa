package com.haoyu.aip.assignment.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.Assignment;

public interface IAssignmentDao {

	int update(Assignment assignment);

	int deleteByLogic(Map<String, Object> param);

	int insert(Assignment entity);

	Assignment selectByPrimaryKey(String id);

	List<Assignment> select(Map<String, Object> param, PageBounds pageBounds);
}
