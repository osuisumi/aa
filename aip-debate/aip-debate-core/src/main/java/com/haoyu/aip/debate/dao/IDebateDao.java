/**
 * 
 */
package com.haoyu.aip.debate.dao;

import java.util.List;
import java.util.Set;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.tag.entity.Tag;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateDao {
	
	Debate selectDebateById(String id);
	
	/**
	 * 基于debateRelation查询
	 * @param debateRelation :id或者relation,debate必须有值
	 * @return
	 */
	Debate selectDebateByDebateRelation(DebateRelation debateRelation);
	
	List<Debate> selectDebateByTagAndRelation(Set<Tag> tags,Set<Relation> relations,PageBounds pageBounds);
	
	List<Debate> selectDebateByRelationAndTitle(Set<Relation> relations,String title,PageBounds pageBounds);
	
	int insertDebate(Debate debate);
	
	int updateDebate(Debate debate);
	
	int deleteDebateByLogic(Debate debate);
	
	int deleteDebateByPhysics(String id);
}
