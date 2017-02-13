package com.haoyu.aip.assignment.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.haoyu.aip.assignment.service.IAssignmentRelationService;
import com.haoyu.aip.assignment.dao.IAssignmentRelationDao;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Identities;

@Service
public class AssignmentRelationServiceImpl implements IAssignmentRelationService{
	
	@Resource
	private IAssignmentRelationDao assignmentRelationDao;

	@Override
	public Response createAssignmentRelation(AssignmentRelation entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setId(Identities.uuid2());
		}
		entity.setDefaultValue();
		int count = assignmentRelationDao.insert(entity);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response updateAssignmentRelation(AssignmentRelation entity) {
		entity.setUpdatedby(ThreadContext.getUser());
		entity.setUpdateTime(System.currentTimeMillis());
		int count = assignmentRelationDao.update(entity);
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public AssignmentRelation getAssignmentRelation(String id) {
		return assignmentRelationDao.selectByPrimaryKey(id);
	}

}
