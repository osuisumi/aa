package com.haoyu.aip.assignment.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.AssignmentMark;

public interface IAssignmentMarkDao {

	int getCount(Map<String, Object> param);

	int insertBatch(Map<String, Object> param);

	List<AssignmentMark> select(Map<String, Object> param, PageBounds pageBounds);

	AssignmentMark selectByPrimaryKey(String id);

	int insert(AssignmentMark assignmentMark);

	int update(AssignmentMark assignmentMark);

	int deleteByParam(Map<String, Object> param);

}
