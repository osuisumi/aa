package com.haoyu.aip.assignment.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.dao.IAssignmentMarkDao;
import com.haoyu.aip.assignment.entity.Assignment;
import com.haoyu.aip.assignment.entity.AssignmentMark;
import com.haoyu.aip.assignment.entity.AssignmentRelation;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.entity.EachOtherMarkConfig;
import com.haoyu.aip.assignment.event.MarkAssignmentUserEvent;
import com.haoyu.aip.assignment.service.IAssignmentMarkService;
import com.haoyu.aip.assignment.service.IAssignmentService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.aip.assignment.utils.AssignmentMarkState;
import com.haoyu.aip.assignment.utils.AssignmentMarkType;
import com.haoyu.aip.assignment.utils.AssignmentUserState;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.PropertiesLoader;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.utils.Collections3;

@Service
public class AssignmentMarkServiceImpl implements IAssignmentMarkService{

	@Resource
	private IAssignmentService assignmentService;
	@Resource
	private IAssignmentUserService assignmentUserService;
	@Resource
	private IAssignmentMarkDao assignmentMarkDao;
	@Resource
	private ApplicationContext applicationContext;
	
	@Override
	public Response createAssignmentMark(String assignmentId, String assignmentRelationId) {
		Assignment assignment = assignmentService.getAssignment(assignmentId);
		if (AssignmentMarkType.EACH_OTHER.equals(assignment.getMarkType())) {
			String id = AssignmentUser.getId(assignmentRelationId, ThreadContext.getUser().getId());
			AssignmentUser myAu = assignmentUserService.getAssignmentUser(id);
			//获取作业设置的批阅任务数
			EachOtherMarkConfig eachOtherMarkConfig = assignment.getEachOtherMarkConfig();
			BigDecimal markNum = eachOtherMarkConfig.getMarkNum();
			if (markNum.intValue() > 0) {
				//减去已领取的份数
				int oldCount = myAu.getReceiveNum();
				int getNum = markNum.intValue() - oldCount;
				
				if (getNum > 0) {
					//抽取提交最早的N份作业
					PageBounds pageBounds = new PageBounds();
					pageBounds.setLimit(getNum);
					pageBounds.setOrders(Order.formString("RESPONSE_TIME"));
					Map<String, Object> param1 = Maps.newHashMap();
					param1.put("assignmentRelationId", assignmentRelationId);
					param1.put("receivedNumLessThan", markNum);
					param1.put("creatorNotEquals", ThreadContext.getUser().getId());
					param1.put("userIdNotExists", ThreadContext.getUser().getId());
					param1.put("state", AssignmentUserState.COMMIT + "," + AssignmentUserState.COMPLETE);
					param1.put("assignmentMarkType", AssignmentMarkType.EACH_OTHER);
					List<AssignmentUser> assignmentUsers = assignmentUserService.listAssignmentUser(param1, pageBounds);
					
					if (Collections3.isNotEmpty(assignmentUsers)) {
						//创建assignmentMark
						for (AssignmentUser assignmentUser : assignmentUsers) {
							AssignmentMark assignmentMark = new AssignmentMark();
							String amid = AssignmentMark.getId(assignmentUser.getId(), ThreadContext.getUser().getId());
							assignmentMark.setId(amid);
							assignmentMark.setUser(ThreadContext.getUser());
							assignmentMark.setAssignmentUser(assignmentUser);
							assignmentMark.setState(AssignmentMarkState.MARKING);
							assignmentMark.setDefaultValue();
							int count = assignmentMarkDao.insert(assignmentMark);
							if (count > 0) {
								AssignmentUser au = new AssignmentUser();
								au.setId(assignmentUser.getId());
								au.setReceivedNum(1);
								assignmentUserService.updateAssignmentUser(au);
							}
						}
						AssignmentUser au = new AssignmentUser();
						au.setId(myAu.getId());
						au.setReceiveNum(1);
						return assignmentUserService.updateAssignmentUser(au);
					}else{
						return Response.failInstance().responseMsg("assigment is not enough");
					}
				}
			}
		}
		return Response.failInstance();
	}

	@Override
	public int getCount(Map<String, Object> param) {
		return assignmentMarkDao.getCount(param);
	}

	@Override
	public List<AssignmentMark> listAssignmentMark(Map<String, Object> param, PageBounds pageBounds) {
		return assignmentMarkDao.select(param, pageBounds);
	}

	@Override
	public AssignmentMark getAssignmentMark(String id) {
		return assignmentMarkDao.selectByPrimaryKey(id);
	}

	@Override
	public Response updateAssignmentMark(AssignmentMark assignmentMark) {
		assignmentMark.setUpdatedby(ThreadContext.getUser());
		assignmentMark.setUpdateTime(System.currentTimeMillis());
		int count = assignmentMarkDao.update(assignmentMark);
		if (count > 0) {
			Response response = Response.failInstance();
			AssignmentUser assignmentUser = assignmentMark.getAssignmentUser();
			Assignment assignment = assignmentUser.getAssignmentRelation().getAssignment();
			if (assignmentUser != null && StringUtils.isNotEmpty(assignmentUser.getId())) {
				AssignmentUser markAu = new AssignmentUser();
				markAu.setId(assignmentUser.getId());
				markAu.setMarkedNum(1);
				markAu.setResponseScore(BigDecimal.valueOf(-1));
				if (assignment.getMarkType().equals(AssignmentMarkType.TEACHER)) {
					markAu.setState(AssignmentUserState.COMPLETE);
				}
				response = assignmentUserService.updateAssignmentUser(markAu);
				if (response.isSuccess()) {
					if (assignment.getMarkType().equals(AssignmentMarkType.EACH_OTHER)) {
						AssignmentRelation assignmentRelation = assignmentUser.getAssignmentRelation();
						if (assignmentRelation != null && StringUtils.isNotEmpty(assignmentRelation.getId())) {
							String id = AssignmentUser.getId(assignmentRelation.getId(), ThreadContext.getUser().getId());
							AssignmentUser myAu = assignmentUserService.getAssignmentUser(id);
							int myMarkNum = myAu.getMarkNum();
							String way = assignment.getEachOtherMarkConfig().getGetMarkScoreWay();
							BigDecimal markScorePct = assignment.getEachOtherMarkConfig().getMarkScorePct()==null?BigDecimal.valueOf(0):assignment.getEachOtherMarkConfig().getMarkScorePct();
							BigDecimal markNum = assignment.getEachOtherMarkConfig().getMarkNum();
							myAu.setMarkNum(1);
							if (EachOtherMarkConfig.GET_MARK_SCORE_WAY_IF_ONNY_ONE.equals(way)) {
								BigDecimal score = BigDecimal.valueOf(myMarkNum)
										.multiply(markScorePct)
										.multiply(assignment.getScore()==null?BigDecimal.valueOf(100):assignment.getScore())
										.divide(markNum, 1, BigDecimal.ROUND_HALF_UP)
										.divide(BigDecimal.valueOf(100), 1, BigDecimal.ROUND_HALF_UP);
								myAu.setMarkScore(score);
								if (BigDecimal.valueOf(myMarkNum + 1).compareTo(markNum) >= 0) {
									myAu.setState(AssignmentUserState.COMPLETE);
								}
								response = assignmentUserService.updateAssignmentUser(myAu);
								if (response.isSuccess()) {
									applicationContext.publishEvent(new MarkAssignmentUserEvent(myAu));
								}
							}else{
								if(BigDecimal.valueOf(myMarkNum + 1).compareTo(markNum) >= 0){
									BigDecimal score = markScorePct
											.multiply(assignment.getScore()==null?BigDecimal.valueOf(100):assignment.getScore())
											.divide(BigDecimal.valueOf(100), 1, BigDecimal.ROUND_HALF_UP);
									myAu.setMarkScore(score);
									myAu.setState(AssignmentUserState.COMPLETE);
								}
								response = assignmentUserService.updateAssignmentUser(myAu);
								if (response.isSuccess() && AssignmentUserState.COMPLETE.equals(myAu.getState())) {
									applicationContext.publishEvent(new MarkAssignmentUserEvent(myAu));
								}
							}
						}
					}
					applicationContext.publishEvent(new MarkAssignmentUserEvent(markAu));
					return response;
				}
				return response;
			}
			return response;
		}
		return Response.failInstance();
	}

	@Override
	public AssignmentMark createAssignmentMarkIfNotExists(String assigmnetUserId) {
		String id = AssignmentUser.getId(assigmnetUserId, ThreadContext.getUser().getId() );
		AssignmentMark assignmentMark = assignmentMarkDao.selectByPrimaryKey(id);
		if (assignmentMark == null) {
			assignmentMark = new AssignmentMark();
			AssignmentUser assignmentUser = new AssignmentUser();
			assignmentUser.setId(assigmnetUserId);
			assignmentMark.setAssignmentUser(assignmentUser);
			assignmentMark.setId(id);
			assignmentMark.setState(AssignmentMarkState.MARKING);
			assignmentMark.setDefaultValue();
			assignmentMarkDao.insert(assignmentMark);
		}
		return assignmentMark;
	}

	@Override
	public Response updateAssignmentMarkExpired() {
		int days = Integer.parseInt(PropertiesLoader.get("assignment.mark.expired.days"));
		long date = System.currentTimeMillis() - 24*60*60*1000*days;
		Map<String, Object> param = Maps.newHashMap();
		param.put("createTimeLessThan", date);
		param.put("state", AssignmentMarkState.MARKING);
		List<AssignmentMark> assignmentMarks = this.listAssignmentMark(param, null);
		if (Collections3.isNotEmpty(assignmentMarks)) {
			assignmentMarkDao.deleteByParam(param);
			for (AssignmentMark assignmentMark : assignmentMarks) {
				String assignmentUserId = assignmentMark.getAssignmentUser().getId();
				AssignmentUser au = new AssignmentUser();
				au.setId(assignmentUserId);
				au.setReceivedNum(1);
				assignmentUserService.updateAssignmentUser(au);
			}
		}
		return Response.successInstance();
	}

	@Override
	public Response createAssignmentMark(String relationId, int limit) {
		//助学批改, 每次默认领取10份作业
		PageBounds pageBounds = new PageBounds();
		pageBounds.setLimit(limit);
		pageBounds.setOrders(Order.formString("RESPONSE_TIME"));
		Map<String, Object> param = Maps.newHashMap();
		param.put("relationId", relationId);
		param.put("markedNumLessThan", 1);
		param.put("receivedNumLessThan", 1);
		param.put("creatorNotEquals", ThreadContext.getUser().getId());
		param.put("userIdNotExists", ThreadContext.getUser().getId());
		param.put("state", AssignmentUserState.COMMIT);
		param.put("assignmentMarkType", AssignmentMarkType.TEACHER);
		List<AssignmentUser> assignmentUsers = assignmentUserService.listAssignmentUser(param, pageBounds);
		
		if (Collections3.isNotEmpty(assignmentUsers)) {
			//创建assignmentMark
			for (AssignmentUser assignmentUser : assignmentUsers) {
				AssignmentMark assignmentMark = new AssignmentMark();
				String amid = AssignmentMark.getId(assignmentUser.getId(), ThreadContext.getUser().getId());
				assignmentMark.setId(amid);
				assignmentMark.setUser(ThreadContext.getUser());
				assignmentMark.setAssignmentUser(assignmentUser);
				assignmentMark.setState(AssignmentMarkState.MARKING);
				assignmentMark.setDefaultValue();
				int count = assignmentMarkDao.insert(assignmentMark);
				if (count > 0) {
					AssignmentUser au = new AssignmentUser();
					au.setId(assignmentUser.getId());
					au.setReceivedNum(1);
					assignmentUserService.updateAssignmentUser(au);
				}
			}
		}else{
			return Response.failInstance().responseMsg("assigment is not enough");
		}
		return Response.successInstance().responseData(assignmentUsers.size());
	}

	@Override
	public Response deleteAssignmentMarkByParam(Map<String, Object> param) {
		int count = assignmentMarkDao.deleteByParam(param);
		return count>0?Response.successInstance():Response.failInstance();
	}
}
