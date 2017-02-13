package com.haoyu.aip.assignment.mobile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.assignment.entity.AssignmentUser;
import com.haoyu.aip.assignment.mobile.entity.MAssignmentUser;
import com.haoyu.aip.assignment.mobile.service.IMAssignmentUserService;
import com.haoyu.aip.assignment.service.IAssignmentUserService;
import com.haoyu.aip.assignment.utils.AssignmentUserState;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.core.utils.BeanUtils;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.user.mobile.entity.MUser;

@Service
public class MAssignmentUserServiceImpl implements IMAssignmentUserService{
	
	@Resource
	private IAssignmentUserService assignmentUserService;

	@Override
	public Response listAssignmentUser(String relationId, String assignmentId, String state, PageBounds pageBounds) {
		Map<String, Object> resultMap = Maps.newHashMap();
		pageBounds.setOrders(Order.formString("CREATE_TIME.DESC"));
		Map<String, Object> param = Maps.newHashMap();
		param.put("assignmentId", assignmentId);
		param.put("state", state);
		param.put("relationId", relationId);
		param.put("marker", ThreadContext.getUser().getId());
		param.put("assignmentMarkType", "teacher");
		
		List<AssignmentUser> assignmentUsers = assignmentUserService.listAssignmentUser(param, pageBounds);
		List<MAssignmentUser> mAssignmentUsers = Lists.newArrayList();
		for (AssignmentUser assignmentUser : assignmentUsers) {
			MAssignmentUser mAssignmentUser = new MAssignmentUser();
			MUser mUser = new MUser();
			BeanUtils.copyProperties(assignmentUser, mAssignmentUser);
			BeanUtils.copyProperties(assignmentUser.getCreator(), mUser);
			mAssignmentUser.setmUser(mUser);
			mAssignmentUsers.add(mAssignmentUser);
		}
		resultMap.put("mAssignmentUsers", mAssignmentUsers);
		if (pageBounds != null && pageBounds.isContainsTotalCount()) {
			PageList pageList = (PageList)assignmentUsers;
			resultMap.put("paginator", pageList.getPaginator());
		}
		return Response.successInstance().responseData(resultMap);
	}

	@Override
	public Response getAssignmentUserNum(String relationId) {
		Map<String, Object> resultMap = Maps.newHashMap();
		Map<String, Object> parameter = Maps.newHashMap();
		parameter.put("relationId", relationId);
		parameter.put("userId", ThreadContext.getUser().getId());
		parameter.put("assignmentMarkType", "teacher");
		
		parameter.put("state", AssignmentUserState.COMMIT + "," + AssignmentUserState.COMPLETE);
		int allNum = assignmentUserService.getCount(parameter);
		resultMap.put("allNum", allNum);
		
		if (parameter.containsKey("userId")) {
			parameter.put("marker", parameter.get("userId"));
		}
		parameter.put("state", AssignmentUserState.COMPLETE);
		int markNum = assignmentUserService.getCount(parameter);
		resultMap.put("markNum", markNum);
		
		parameter.put("state", AssignmentUserState.COMMIT);
		int notMarkedNum = assignmentUserService.getCount(parameter);
		resultMap.put("notMarkedNum", notMarkedNum);
		
		parameter.remove("marker");
		parameter.put("state", AssignmentUserState.COMMIT);
		parameter.put("receivedNumLessThan", 1);
		int notReceivedNum = assignmentUserService.getCount(parameter);
		resultMap.put("notReceivedNum", notReceivedNum);
		return Response.successInstance().responseData(resultMap);
	}

}
