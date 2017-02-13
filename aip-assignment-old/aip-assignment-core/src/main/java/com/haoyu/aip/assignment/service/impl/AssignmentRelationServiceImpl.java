package com.haoyu.aip.assignment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.service.IAssignmentRelationService;
import com.haoyu.aip.assignment.dao.IAssignmentRelationDao;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.core.web.SearchParam;

@Service
public class AssignmentRelationServiceImpl implements IAssignmentRelationService{
	
	@Resource
	private IAssignmentRelationDao assignmentRelationDao;
	
	@Override
	public Response create(AssignmentRelation obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)assignmentRelationDao);
	}

	@Override
	public Response update(AssignmentRelation obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)assignmentRelationDao);
	}
	
	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)assignmentRelationDao);
	}

	@Override
	public AssignmentRelation get(String id) {
		return (AssignmentRelation) BaseServiceUtils.get(id, (MybatisDao)assignmentRelationDao);
	}

	@Override
	public List<AssignmentRelation> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)assignmentRelationDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}

	@Override
	public Response updateByIdNotSelective(AssignmentRelation obj) {
		obj.setUpdatedby(ThreadContext.getUser());
		obj.setUpdateTime(System.currentTimeMillis());
		int count = assignmentRelationDao.updateByIdNotSelective(obj);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public Response updateParticipateNum(AssignmentRelation obj) {
		obj.setParticipateNum(1);
		return this.update(obj);
	}
	
	@Override
	public Response updateBrowseNum(AssignmentRelation obj) {
		obj.setBrowseNum(1);
		return this.update(obj);
	}

}
