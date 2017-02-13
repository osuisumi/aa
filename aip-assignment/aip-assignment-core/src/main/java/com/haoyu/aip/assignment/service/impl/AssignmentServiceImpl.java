package com.haoyu.aip.assignment.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.dao.IAssignmentDao;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.event.CreateAssignmentEvent;
import com.haoyu.aip.assignment.event.UpdateAssignmentEvent;
import com.haoyu.aip.assignment.service.IAssignmentRelationService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

@Service
public class AssignmentServiceImpl implements IAssignmentService{
	
	@Resource
	private IAssignmentDao assignmentDao;
	@Resource
	private IAssignmentRelationService assignmentRelationService;
	@Resource
	private IAssignmentUserService assignmentUserService;
	@Resource  
	private ApplicationContext applicationContext;  
	@Resource
	private ITagRelationService tagRelationService;
	@Resource
	private IFileService fileService;
	
	@Override
	public Response deleteAssignmentByLogic(Assignment assignment) {
		String[] array = assignment.getId().split(",");
		List<String> ids = Arrays.asList(array);
		assignment.setUpdatedby(ThreadContext.getUser());
		assignment.setUpdateTime(System.currentTimeMillis());
		Map<String, Object> param = Maps.newHashMap();
		param.put("ids", ids);
		param.put("entity", assignment);
		int count = assignmentDao.deleteByLogic(param);
		return count>0?Response.successInstance():Response.failInstance();
	}

	@Override
	public List<Assignment> listAssignment(Map<String, Object> param, PageBounds pageBounds) {
		return assignmentDao.select(param, pageBounds);
	}
	
	@Override
	public Assignment getAssignment(String id) {
		Assignment assignment = assignmentDao.selectByPrimaryKey(id);
		if (assignment != null) {
			assignment.setFileInfos(fileService.listFileInfoByRelationId(assignment.getId()));
		}
		return assignment;
	}
	
	@Override
	public Response createAssignment(Assignment assignment) {
		if (StringUtils.isEmpty(assignment.getId())) {
			assignment.setId(Identities.uuid2());
		}
		assignment.setDefaultValue();
		int count = assignmentDao.insert(assignment);
		if (count > 0) {
			if (Collections3.isNotEmpty(assignment.getAssignmentRelations())) {
				for (AssignmentRelation assignmentRelation : assignment.getAssignmentRelations()) {
					if (assignmentRelation.getRelation() != null && StringUtils.isNotEmpty(assignmentRelation.getRelation().getId())) {
						if (assignmentRelation.getAssignment() == null || StringUtils.isEmpty(assignmentRelation.getAssignment().getId())) {
							assignmentRelation.setAssignment(assignment);
						}
						String id = AssignmentRelation.getId(assignmentRelation.getAssignment().getId(), assignmentRelation.getRelation().getId());
						assignmentRelation.setId(id);
						assignmentRelationService.createAssignmentRelation(assignmentRelation);
						assignmentRelation.setAssignment(null);
					}
				}
			}
			//插入标签信息
			if(Collections3.isNotEmpty(assignment.getTags())){
				tagRelationService.createTagRelation(assignment.getTags(), new Relation(assignment.getId(),"assignment"), true);
			}
			//添加附件
			fileService.createFileList(assignment.getFileInfos(), assignment.getId(), "assignment");
			applicationContext.publishEvent(new CreateAssignmentEvent(assignment));
			return Response.successInstance().responseData(assignment);
		}
		return Response.failInstance();
	}

	@Override
	public Response updateAssignment(Assignment assignment, boolean isUpdateFile) {
		assignment.setUpdatedby(ThreadContext.getUser());
		assignment.setUpdateTime(System.currentTimeMillis());
		int count = assignmentDao.update(assignment);
		if (count > 0) {
			if (Collections3.isNotEmpty(assignment.getAssignmentRelations())) {
				for (AssignmentRelation assignmentRelation : assignment.getAssignmentRelations()) {
					if (StringUtils.isNotEmpty(assignmentRelation.getId())) {
						assignmentRelationService.updateAssignmentRelation(assignmentRelation);
					}
				}
			}
			if(Collections3.isNotEmpty(assignment.getTags())){
				//添加标签信息
				tagRelationService.createTagRelation(assignment.getTags(), new Relation(assignment.getId(),"assignment"),  true);
			}else{
				tagRelationService.deleteTagRelationByRelation(new Relation(assignment.getId()));
			}
			//更新附件
			if (isUpdateFile) {
				List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(assignment.getId());
				fileService.updateFileList(assignment.getFileInfos(), oldFileInfos, assignment.getId(), "assignment");
			}
			applicationContext.publishEvent(new UpdateAssignmentEvent(assignment));
			return Response.successInstance().responseData(assignment);
		}
		return Response.failInstance();
	}
	
	@Override
	public Response updateAssignment(Assignment assignment) {
		return updateAssignment(assignment, true);
	}

}
