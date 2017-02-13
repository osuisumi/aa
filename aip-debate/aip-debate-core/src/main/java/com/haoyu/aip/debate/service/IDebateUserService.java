/**
 * 
 */
package com.haoyu.aip.debate.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateUserService {
	
	DebateUser findDebateUserById(String id);
	
	/**
	 * 根据用户id和 debateRelationId查询相关的辩论信息和用户参与信息 
	 * @param debateUser creator属性中的id和debateRelation属性中  的id必须有值 
	 * @return
	 */
	DebateUser findDebateUser(DebateUser debateUser);
	
	List<DebateUser> findDebateUserByDebateRelation(DebateRelation debateRelation,PageBounds pageBounds);
	
	Response createDebateUser(DebateUser debateUser);	
	
	Response updateViewsNum(String id);
	
	Response updateCommentsNum(String id);

	Response deleteDebateUser(DebateUser debateUser);
	
}
