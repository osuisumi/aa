/**
 * 
 */
package com.haoyu.aip.debate.service;

import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateRelationService {
	
	DebateRelation findDebateRelationStatByDebateUser(DebateUser debateUser);
	
	Response updateBrowseNum(String id);
	
	Response updateFollowNum(String id,int followNum);
}
