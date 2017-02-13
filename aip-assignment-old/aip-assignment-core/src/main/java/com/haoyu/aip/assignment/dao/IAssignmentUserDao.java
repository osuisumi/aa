package com.haoyu.aip.assignment.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.entity.AssignmentUser;

public interface IAssignmentUserDao {

	AssignmentUser selectOne(AssignmentUser au);

	List<AssignmentUser> select(AssignmentUser assignmentUser, PageBounds pageBounds);

}
