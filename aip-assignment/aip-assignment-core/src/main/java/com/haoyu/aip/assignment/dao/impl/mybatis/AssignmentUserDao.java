package com.haoyu.aip.assignment.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.dao.IAssignmentUserDao;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class AssignmentUserDao extends MybatisDao implements IAssignmentUserDao{

	@Override
	public int insert(AssignmentUser entity) {
		return super.insert(entity);
	}

	@Override
	public int update(AssignmentUser entity) {
		return super.update(entity);
	}

	@Override
	public int deleteByLogic(Map<String, Object> param) {
		return update("deleteByLogic", param);
	}

	@Override
	public AssignmentUser selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<AssignmentUser> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

}
