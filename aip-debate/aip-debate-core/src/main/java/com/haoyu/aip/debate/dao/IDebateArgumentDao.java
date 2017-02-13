/**
 * 
 */
package com.haoyu.aip.debate.dao;

import java.util.List;
import java.util.Set;

import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.aip.debate.entity.DebateRelation;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateArgumentDao {
	
	DebateArgument selectDebateArgumentById(String id);
	
	List<DebateArgument> selectDebateArgumentByDebate(Debate debate);
	
	List<DebateArgument> selectDebateArgumentByDebateRelation(DebateRelation debateRelation);
	
	int deleteDebateArgumentByLogic(DebateArgument debateArgument);
	
	int deleteDebateArgumentByLogic(List<DebateArgument> debateArguments,String updatedby);
	
	int insertDebateArgument(DebateArgument debateArgument);
	
	int updateDebateArgument(DebateArgument debateArgument);
}
