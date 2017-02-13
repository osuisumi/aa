package com.haoyu.aip.assignment.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.dao.IAssignmentDao;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.event.CreateAssignmentEvent;
import com.haoyu.aip.assignment.event.UpdateAssignmentEvent;
import com.haoyu.aip.assignment.service.IAssignmentRelationService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.base.utils.BaseServiceUtils;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.web.SearchParam;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.tag.service.ITagRelationService;
import com.haoyu.sip.utils.Collections3;

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
	public Response create(Assignment obj) {
		return BaseServiceUtils.create(obj, (MybatisDao)assignmentDao);
	}

	@Override
	public Response update(Assignment obj) {
		return BaseServiceUtils.update(obj, (MybatisDao)assignmentDao);
	}

	@Override
	public Response delete(String id) {
		return BaseServiceUtils.delete(id, (MybatisDao)assignmentDao);
	}

	@Override
	public Assignment get(String id) {
		return (Assignment) BaseServiceUtils.get(id, (MybatisDao)assignmentDao);
	}

	@Override
	public List<Assignment> list(SearchParam searchParam, PageBounds pageBounds) {
		return ((MybatisDao)assignmentDao).selectList("select", searchParam.getParamMap(), pageBounds);
	}
	
	@Override
	public Response createAssignment(Assignment assignment) {
		Response response = this.create(assignment);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(assignment.getAssignmentRelations())) {
				for (AssignmentRelation assignmentRelation : assignment.getAssignmentRelations()) {
					if (assignmentRelation.getRelation() != null && StringUtils.isNotEmpty(assignmentRelation.getRelation().getId())) {
						if (assignmentRelation.getAssignment() == null || StringUtils.isEmpty(assignmentRelation.getAssignment().getId())) {
							assignmentRelation.setAssignment(assignment);
						}
						String id = AssignmentRelation.getId(assignmentRelation.getAssignment().getId(), assignmentRelation.getRelation().getId());
						assignmentRelation.setId(id);
						assignmentRelationService.create(assignmentRelation);
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
			response.setResponseData(assignment);
		}
		return response;
	}

	@Override
	public Response updateAssignment(Assignment assignment) {
		Response response = this.update(assignment);
		if (response.isSuccess()) {
			if (Collections3.isNotEmpty(assignment.getAssignmentRelations())) {
				for (AssignmentRelation assignmentRelation : assignment.getAssignmentRelations()) {
					if (StringUtils.isNotEmpty(assignmentRelation.getId())) {
						assignmentRelationService.updateByIdNotSelective(assignmentRelation);
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
			List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(assignment.getId());
			fileService.updateFileList(assignment.getFileInfos(), oldFileInfos, assignment.getId(), "assignment");
			applicationContext.publishEvent(new UpdateAssignmentEvent(assignment));
			response.setResponseData(assignment);
		}
		return response;
	}

}
