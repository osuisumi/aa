package com.haoyu.aip.assignment.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.dao.IAssignmentDao;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class AssignmentDao extends MybatisDao implements IAssignmentDao{

	@Override
	public int insert(Assignment entity) {
		return super.insert(entity);
	}

	@Override
	public int update(Assignment entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return update("deleteByLogic", param);
	}

	@Override
	public Assignment selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<Assignment> select(Map<String, Object> params, PageBounds pageBounds) {
		return selectList("select", params, pageBounds);
	}

}
