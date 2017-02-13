package com.haoyu.aip.assignment.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.dao.IAssignmentMarkDao;
import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.sip.core.jdbc.MybatisDao;

@Repository
public class AssignmentMarkDao extends MybatisDao implements IAssignmentMarkDao{

	@Override
	public int getCount(Map<String, Object> param) {
		return selectOne("getCount", param);
	}

	@Override
	public int insertBatch(Map<String, Object> param) {
		return insert("insertBatch", param);
	}

	@Override
	public List<AssignmentMark> select(Map<String, Object> param, PageBounds pageBounds) {
		return selectList("select", param, pageBounds);
	}

	@Override
	public AssignmentMark selectByPrimaryKey(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public int insert(AssignmentMark assignmentMark) {
		return super.insert(assignmentMark);
	}

	@Override
	public int update(AssignmentMark assignmentMark) {
		return super.update(assignmentMark);
	}

	@Override
	public int deleteByParam(Map<String, Object> param) {
		return delete("deleteByParam", param);
	}

}
