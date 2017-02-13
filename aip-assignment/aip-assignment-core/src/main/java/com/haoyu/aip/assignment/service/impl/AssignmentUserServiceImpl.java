package com.haoyu.aip.assignment.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.dao.IAssignmentUserDao;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.event.MarkAssignmentUserEvent;
import com.haoyu.aip.assignment.event.SubmitAssignmentUserEvent;
import com.haoyu.aip.assignment.service.IAssignmentMarkService;
import com.haoyu.aip.assignment.service.IAssignmentRelationService;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.aip.assignment.utils.AssignmentMarkState;
import com.haoyu.aip.assignment.utils.AssignmentMarkType;
import com.haoyu.aip.assignment.utils.AssignmentResponseType;
import com.haoyu.aip.assignment.utils.AssignmentUserState;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.file.entity.FileInfo;
import com.haoyu.sip.file.service.IFileService;
import com.haoyu.sip.utils.Collections3;
import com.haoyu.sip.utils.Identities;

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
	@Resource
	private IAssignmentMarkService assignmentMarkService;
	@Resource
	private IAssignmentService assignmentService;

	@Override
	public List<AssignmentUser> listAssignmentUser(Map<String, Object> param, PageBounds pageBounds) {
		return assignmentUserDao.select(param, pageBounds);
	}

	@Override
	public Response createAssignmentUser(AssignmentUser assignmentUser) {
		if (StringUtils.isEmpty(assignmentUser.getId())) {
			assignmentUser.setId(Identities.uuid2());
		}
		assignmentUser.setDefaultValue();
		int count = assignmentUserDao.insert(assignmentUser);
		if (count > 0) {
			fileService.createFileList(assignmentUser.getFileInfos(), assignmentUser.getId(), "assignment_user");
		}
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public Response updateAssignmentUser(AssignmentUser assignmentUser) {
		if (AssignmentUserState.COMMIT.equals(assignmentUser.getState())) {
			assignmentUser.setResponseTime(new Date());
		}
		if (AssignmentUserState.RETURN.equals(assignmentUser.getState())) {
			assignmentUser.setResponseScore(BigDecimal.valueOf(0));
		}
		assignmentUser.setUpdatedby(ThreadContext.getUser());
		assignmentUser.setUpdateTime(System.currentTimeMillis());
		int count = assignmentUserDao.update(assignmentUser);
		if (count > 0) {
			if (AssignmentUserState.COMMIT.equals(assignmentUser.getState())) {
				if (Collections3.isNotEmpty(assignmentUser.getFileInfos())) {
					List<FileInfo> oldFileInfos = fileService.listFileInfoByRelationId(assignmentUser.getId());
					fileService.updateFileList(assignmentUser.getFileInfos(), oldFileInfos, assignmentUser.getId(), "assignment_user");
				}
				applicationContext.publishEvent(new SubmitAssignmentUserEvent(assignmentUser));
			}else if(AssignmentUserState.RETURN.equals(assignmentUser.getState())){
				applicationContext.publishEvent(new MarkAssignmentUserEvent(assignmentUser));
			}
		}
		return count > 0 ? Response.successInstance() : Response.failInstance();
	}

	@Override
	public AssignmentUser getAssignmentUser(String id) {
		return getAssignmentUser(id, true);
	}
	

	@Override
	public AssignmentUser getAssignmentUser(String id, boolean getFile) {
		AssignmentUser assignmentUser = assignmentUserDao.selectByPrimaryKey(id);
		if (assignmentUser != null && getFile) {
			if (AssignmentResponseType.UPLOAD.equals(assignmentUser.getAssignmentRelation().getAssignment().getResponseType())) {
				assignmentUser.setFileInfos(fileService.listFileInfoByRelationId(assignmentUser.getId()));
			}
		}
		return assignmentUser;
	}
	
	@Override
	public AssignmentUser createAssignmentIfNotExists(String assignmentRelationId) {
		String id = AssignmentUser.getId(assignmentRelationId, ThreadContext.getUser().getId() );
		AssignmentUser assignmentUser = assignmentUserDao.selectByPrimaryKey(id);
		if (assignmentUser != null) {
			if (AssignmentResponseType.UPLOAD.equals(assignmentUser.getAssignmentRelation().getAssignment().getResponseType())) {
				assignmentUser.setFileInfos(fileService.listFileInfoByRelationId(assignmentUser.getId()));
			}
		}else{
			assignmentUser = new AssignmentUser();
			AssignmentRelation assignmentRelation = new AssignmentRelation();
			assignmentRelation.setId(assignmentRelationId);
			assignmentUser.setAssignmentRelation(assignmentRelation);
			assignmentUser.setId(id);
			assignmentUser.setState(AssignmentUserState.NOT_ATTEMPT);
			assignmentUser.setDefaultValue();
			assignmentUserDao.insert(assignmentUser);
		}
		return assignmentUser;
	}

	@Override
	public Response updateAssignmentUserEnd(String assignmentId) {
		if (StringUtils.isEmpty(assignmentId)) {
			return Response.failInstance().responseMsg("assignmentId is not allow to be null");
		}
		Assignment assignment = assignmentService.getAssignment(assignmentId);
		if (assignment.getMarkType().equals(AssignmentMarkType.EACH_OTHER)) {
			int markNum = assignment.getEachOtherMarkConfig().getMarkNum().intValue();
			BigDecimal markScorePct = assignment.getEachOtherMarkConfig().getMarkScorePct();
			
			Map<String, Object> param = Maps.newHashMap();
			param.put("assignmentId", assignment.getId());
			param.put("state", AssignmentUserState.COMMIT + "," + AssignmentUserState.COMPLETE);
			param.put("markedNumLessThan", markNum);
			//查出已提交, 并且被批阅次数未满的作业, 默认将未满次数算作满分, 再平均计算作业得分
			List<AssignmentUser> assignmentUsers = this.listAssignmentUser(param, null);
			if (Collections3.isNotEmpty(assignmentUsers)) {
				for (AssignmentUser assignmentUser : assignmentUsers) {
					Map<String, Object> param1 = Maps.newHashMap();
					param1.put("assignmentUserId", assignmentUser.getId());
					param1.put("state", AssignmentMarkState.MARKED);
					List<AssignmentMark> assignmentMarks = assignmentMarkService.listAssignmentMark(param1, null);
					int num = 0;
					BigDecimal score = BigDecimal.valueOf(0);
					if (Collections3.isNotEmpty(assignmentMarks)) {
						for (AssignmentMark assignmentMark : assignmentMarks) {
							num++;
							score = score.add(assignmentMark.getScore());
						}
					}
					if (num < markNum) {
						BigDecimal fullScore = BigDecimal.valueOf(100);
						if (markScorePct != null && markScorePct.floatValue() > 0) {
							fullScore = BigDecimal.valueOf(100).subtract(markScorePct);
						}
						for (int i = 0; i < markNum - num; i++) {
							num++;
							score = score.add(fullScore);
						}
					}
					BigDecimal responseScore = score.divide(BigDecimal.valueOf(markNum), 1, BigDecimal.ROUND_HALF_UP);
					AssignmentUser au = new AssignmentUser();
					au.setId(assignmentUser.getId());
					au.setResponseScore(responseScore);
					Response response = this.updateAssignmentUser(au);
					if (response.isSuccess()) {
						applicationContext.publishEvent(new MarkAssignmentUserEvent(au));
					}
				}
			}
			//查出已提交, 并且领取作业次数未满的
			param.remove("markedNumLessThan");
			param.put("receiveNumLessThan", markNum);
			assignmentUsers = this.listAssignmentUser(param, null);
			if (Collections3.isNotEmpty(assignmentUsers)) {
				//查询是否存在未领取的作业 
				param.remove("receiveNumLessThan");
				param.put("receivedNumLessThan", markNum);
				for (AssignmentUser assignmentUser : assignmentUsers) {
					param.put("creatorNotEquals", assignmentUser.getCreator().getId());
					param.put("userIdNotExists", assignmentUser.getCreator().getId());
					int count = this.getCount(param);
					AssignmentUser au = new AssignmentUser();
					au.setId(assignmentUser.getId());
					if (count == 0) {
						au.setMarkScore(markScorePct);
					}
					au.setState(AssignmentUserState.COMPLETE);
					Response response = this.updateAssignmentUser(au);
					if (response.isSuccess()) {
						applicationContext.publishEvent(new MarkAssignmentUserEvent(au));
					}
				}
			}
		}
		return Response.successInstance();
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return assignmentUserDao.getCount(param);
	}

	@Override
	public Response updateAssignmentUserBack(AssignmentUser assignmentUser) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("assignmentUserId", assignmentUser.getId());
		param.put("state", "marked");
		int count = assignmentMarkService.getCount(param);
		if (count > 0) {
			return Response.failInstance().responseMsg("assignment user is marked");
		}else{
			param = Maps.newHashMap();
			param.put("assignmentUserId", assignmentUser.getId());
			assignmentMarkService.deleteAssignmentMarkByParam(param);
			
			assignmentUser.setState(AssignmentUserState.RETURN);
			assignmentUser.setReceivedNum(1);
			assignmentUser.setMarkedNum(1);
			return this.updateAssignmentUser(assignmentUser);
		}
	}

}
