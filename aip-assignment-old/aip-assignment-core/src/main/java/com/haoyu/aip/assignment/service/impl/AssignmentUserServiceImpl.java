package com.haoyu.aip.assignment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.dao.IAssignmentUserDao;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.event.CheckAssignmentUserEvent;
import com.haoyu.aip.assignment.event.CreateAssignmentUserEvent;
import com.haoyu.aip.assignment.service.IAssignmentRelationService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;

@Service
public class AssignmentUserServiceImpl implements IAssignmentUserService{
	
	@Resource
	private IAssignmentUserDao assignmentUserDao;
	@Resource
	private IFileService fileService;
	@Resource
	private IAssignmentRelationService assignmentRelationService;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response create(AssignmentUser obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)assignmentUserDao);
	}

	@Override
	public Response update(AssignmentUser obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)assignmentUserDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)assignmentUserDao);
	}

	@Override
	public AssignmentUser get(String id) {
		return (AssignmentUser) BaseServiceUtils.get(id, (MybatisDao)assignmentUserDao);
	}

	@Override
	public AssignmentUser viewAssignmentUser(AssignmentUser assignmentUser) {
		assignmentUser = assignmentUserDao.selectOne(assignmentUser);
		if (assignmentUser != null && assignmentUser.getAssignmentRelation() != null && assignmentUser.getAssignmentRelation().getAssignment() != null) {
			Assignment assignment = assignmentUser.getAssignmentRelation().getAssignment();
			assignment.setFileInfos(fileService.listFileInfoByRelationId(assignment.getId()));
			
			AssignmentRelation ar = new AssignmentRelation();
			ar.setId(assignmentUser.getAssignmentRelation().getId());
	 		ar.setBrowseNum(1);
	 		assignmentRelationService.updateBrowseNum(ar);
		}
		return assignmentUser;
	}

	@Override
	public List<AssignmentUser> listAssignmentUser(AssignmentUser assignmentUser, PageBounds pageBounds) {
		return assignmentUserDao.select(assignmentUser, pageBounds);
	}

	@Override
	public Response checkAssignmentUser(AssignmentUser assignmentUser) {
		Response response = this.update(assignmentUser);
		if (response.isSuccess()) {
			applicationContext.publishEvent(new CheckAssignmentUserEvent(assignmentUser));
		}
		return response;
	}

	@Override
	public Response createAssignmentUser(AssignmentUser assignmentUser) {
	 	Response response = this.create(assignmentUser);
	 	if (response.isSuccess()) {
	 		AssignmentRelation assignmentRelation = assignmentUser.getAssignmentRelation();
	 		assignmentRelation.setParticipateNum(1);
	 		assignmentRelationService.updateParticipateNum(assignmentRelation);
	 		
	 		fileService.createFileList(assignmentUser.getFileInfos(), assignmentUser.getId(), "assignment_user");
	 		applicationContext.publishEvent(new CreateAssignmentUserEvent(assignmentUser));
		}
	 	return response;
	}

	@Override
	public Response updateAssignmentUser(AssignmentUser assignmentUser) {
		List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(assignmentUser.getId());
		fileService.updateFileList(assignmentUser.getFileInfos(), oldFileInfos, assignmentUser.getId(), "assignment_user");
		return Response.successInstance();
	}

}
