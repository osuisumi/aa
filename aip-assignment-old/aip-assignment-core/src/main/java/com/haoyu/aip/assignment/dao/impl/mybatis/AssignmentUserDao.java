package com.haoyu.aip.assignment.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.dao.IAssignmentUserDao;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class AssignmentUserDao extends MybatisDao implements IAssignmentUserDao{

	@Override
	public AssignmentUser selectOne(AssignmentUser au) {
		return selectOne("selectOne", au);
	}

	@Override
	public List<AssignmentUser> select(AssignmentUser assignmentUser, PageBounds pageBounds) {
		return selectList("select", assignmentUser, pageBounds);
	}

}
