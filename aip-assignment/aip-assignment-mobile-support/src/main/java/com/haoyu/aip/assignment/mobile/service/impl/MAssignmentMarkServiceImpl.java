package com.haoyu.aip.assignment.mobile.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.mobile.entity.MAssignmentMark;
import com.haoyu.aip.assignment.mobile.entity.MAssignmentUser;
import com.haoyu.aip.assignment.mobile.service.IMAssignmentMarkService;
import com.haoyu.aip.assignment.service.IAssignmentMarkService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.evaluate.entity.Evaluate;
import com.haoyu.sip.evaluate.entity.EvaluateItem;
import com.haoyu.sip.evaluate.entity.EvaluateItemSubmission;
import com.haoyu.sip.evaluate.entity.EvaluateRelation;
import com.haoyu.sip.evaluate.entity.EvaluateSubmission;
import com.haoyu.sip.evaluate.mobile.entity.MEvaluateItemSubmission;
import com.haoyu.sip.evaluate.mobile.entity.MEvaluateSubmission;
import com.haoyu.sip.evaluate.service.IEvaluateRelationService;
import com.haoyu.sip.evaluate.service.IEvaluateService;
import com.haoyu.sip.evaluate.service.IEvaluateSubmissionService;
import com.haoyu.sip.mobile.file.entity.MFileInfo;

@Service
public class MAssignmentMarkServiceImpl implements IMAssignmentMarkService{
	
	@Resource
	private IAssignmentMarkService assignmentMarkService;
	@Resource
	private IAssignmentUserService assignmentUserService;
	@Resource
	private IEvaluateRelationService evaluateRelationService;
	@Resource
	private IEvaluateService evaluateService;
	@Resource
	private IEvaluateSubmissionService evaluateSubmissionService;

	@Override
	public Response listAssignmentMark(String assignmentRelationId) {
		if (StringUtils.isEmpty(assignmentRelationId)) {
			return Response.failInstance().responseMsg("assignmentRelationId is null");
		}
		Map<String, Object> param = Maps.newHashMap();
		param.put("assignmentRelationId", assignmentRelationId);
		param.put("userId", ThreadContext.getUser().getId());
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(Integer.MAX_VALUE);
		pageBounds.setOrders(Order.formString("STATE"));
		List<AssignmentMark> assignmentMarks = assignmentMarkService.listAssignmentMark(param, pageBounds);
		List<MAssignmentMark> mAssignmentMarks = Lists.newArrayList();
		for (AssignmentMark assignmentMark : assignmentMarks) {
			MAssignmentMark mAssignmentMark = new MAssignmentMark();
			BeanUtils.copyProperties(assignmentMark, mAssignmentMark);
			
			int expiredDays = Integer.parseInt((String)PropertiesLoader.get("assignment.mark.expired.days"));
			if (expiredDays - (new Date().getTime() - assignmentMark.getCreateTime())/1000/60/60/24 < 1) {
				expiredDays = 1;
			}else{
				expiredDays = (int) (expiredDays - (new Date().getTime() - assignmentMark.getCreateTime())/1000/60/60/24);
			}
			mAssignmentMark.setExpiredDays(expiredDays);
			mAssignmentMarks.add(mAssignmentMark);
		}
		return Response.successInstance().responseData(mAssignmentMarks);
	}

	@Override
	public Response markAssignmentUser(AssignmentMark assignmentMark) {
		Map<String, Object> resultMap = Maps.newHashMap();
		assignmentMark = assignmentMarkService.getAssignmentMark(assignmentMark.getId());
		if (assignmentMark != null) {
			AssignmentUser assignmentUser = assignmentUserService.getAssignmentUser(assignmentMark.getAssignmentUser().getId(), true);
			if (assignmentUser != null) {
				Assignment assignment = assignmentUser.getAssignmentRelation().getAssignment();
				EvaluateRelation evaluateRelation = evaluateRelationService.getEvaluateRelationByRelationId(assignment.getId());
				if (evaluateRelation != null) {
					//查询评价项列表
					String evaluateId = evaluateRelation.getEvaluate().getId();
					Evaluate evaluate = evaluateService.getEvaluate(evaluateId);
					
					//查询评价项分数, 如果首次进入, 将会创建evaluateSubmission与evaluateRelation
					EvaluateSubmission evaluateSubmission = evaluateSubmissionService.createEvaluateSubmissionIfNotExists(evaluateId, assignmentUser.getId());
					
					MEvaluateSubmission mEvaluateSubmission = new MEvaluateSubmission();
					BeanUtils.copyProperties(evaluateSubmission, mEvaluateSubmission);
					mEvaluateSubmission.setEvaluateRelationId(evaluateSubmission.getEvaluateRelation().getId());
					for (EvaluateItem item : evaluate.getEvaluateItems()) {
						MEvaluateItemSubmission mEvaluateItemSubmission = new MEvaluateItemSubmission();
						mEvaluateItemSubmission.setId(item.getId());
						mEvaluateItemSubmission.setContent(item.getContent());
						if (evaluateSubmission.getEvaluateItemSubmissionMap() != null) {
							Map<String, EvaluateItemSubmission> eisMap = evaluateSubmission.getEvaluateItemSubmissionMap();
							if (eisMap.containsKey(item.getId())) {
								EvaluateItemSubmission eis = eisMap.get(item.getId());
								if (eis.getScore() != null) {
									mEvaluateItemSubmission.setScore(eis.getScore().doubleValue());
								}
							}
						}
						mEvaluateSubmission.getmEvaluateItemSubmissions().add(mEvaluateItemSubmission);
					}
					
					resultMap.put("mEvaluateSubmission", mEvaluateSubmission);
				}
				MAssignmentUser mAssignmentUser = new MAssignmentUser();
				BeanUtils.copyProperties(assignmentUser, mAssignmentUser);
				mAssignmentUser.setmFileInfos(BeanUtils.getCopyList(assignmentUser.getFileInfos(), MFileInfo.class));
				resultMap.put("mAssignmentUser", mAssignmentUser);
			}
		}
		return Response.successInstance().responseData(resultMap);
	}

}
