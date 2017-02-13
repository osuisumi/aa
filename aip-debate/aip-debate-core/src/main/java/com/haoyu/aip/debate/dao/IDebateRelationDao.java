/**
 * 
 */
package com.haoyu.aip.debate.dao;

import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateRelationDao {
	
	DebateRelation selectDebateRelationStatByDebateUser(DebateUser debateUser);
	
	int insertDebateRelation(DebateRelation debateRelation);
	
	int updateParticipateNum(String id);
	
	int updateBrowseNum(String id);
	
	/**
	 * 更新关注数，必须提供id和followNum的属性值
	 * @param debateRelation 
	 * @return
	 */
	int updateFollowNum(DebateRelation debateRelation);
	
	int updateCollectNum(String id);
	
	int deleteDebateRelationByPhysics(String id);
}
