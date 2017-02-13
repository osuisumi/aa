/**
 * 
 */
package com.haoyu.aip.debate.dao;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateUserDao {
	
	int insertDebateUser(DebateUser debateUser);
	
	DebateUser selectDebateUserById(String id);
	
	DebateUser selectDebateUser(DebateUser debateUser);
	
	List<DebateUser> selectDebateUserByDebateRelation(DebateRelation debateRelation,PageBounds pageBounds);
	
	int updateViewsNum(String id);
	
	int updateCommentsNum(String id);
	
	int deleteDebateUserByLogic(DebateUser debateUser);
}
