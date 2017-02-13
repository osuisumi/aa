/**
 * 
 */
package com.haoyu.aip.debate.dao;

import java.util.List;

import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.aip.debate.entity.DebateArgumentStat;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateArgumentStatDao {
	
	DebateArgumentStat selectDebateArgumentStatById(String id);
	
	List<DebateArgumentStat> selectDebateArgumentStatByDebateRelation(DebateRelation debateRelation);
	
	int insertDebateArgumentStat(DebateArgumentStat debateArgumentStat);
	
	int updateBestViews(DebateUserViews debateUserViews);
	
	int updateViewsNum(DebateUser debateUser);
	
	int updateParticipateNum(DebateUser debateUser);
	
	int deleteByLogic(DebateArgument da);
	
	int deleteByLogic(List<DebateArgument> das,String updatedby);
}
