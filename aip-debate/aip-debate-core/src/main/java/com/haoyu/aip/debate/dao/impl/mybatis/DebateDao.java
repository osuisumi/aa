/**
 * 
 */
package com.haoyu.aip.debate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Maps;
import com.haoyu.aip.debate.dao.IDebateDao;
import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.sip.core.entity.Relation;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;
import com.haoyu.sip.tag.entity.Tag;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class DebateDao extends MybatisDao implements IDebateDao {

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.dao.IDebateDao#selectDebateById(java.lang.String)
	 */
	@Override
	public Debate selectDebateById(String id) {
		return super.selectByPrimaryKey(id);
	}
	
	@Override
	public Debate selectDebateByDebateRelation(DebateRelation debateRelation) {
		return super.selectOne("selectDebateByDebateRelation", debateRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.dao.IDebateDao#selectDebateByTagAndRelation(java.util.Set, java.util.Set, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Debate> selectDebateByTagAndRelation(Set<Tag> tags,
			Set<Relation> relations, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(tags!=null&&!tags.isEmpty()){
			parameter.put("tags", tags);
		}
		if(relations!=null&&!relations.isEmpty()){
			parameter.put("relations", relations);
		}
		return super.selectList("selectDebateByParameterMap", parameter, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.dao.IDebateDao#selectDebateByRelationAndTitle(java.util.Set, java.lang.String, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<Debate> selectDebateByRelationAndTitle(Set<Relation> relations,
			String title, PageBounds pageBounds) {
		Map<String,Object> parameter = Maps.newHashMap();
		if(StringUtils.isNotEmpty(title)){
			parameter.put("title", title);
		}
		if(relations!=null&&!relations.isEmpty()){
			parameter.put("relations", relations);
		}
		return super.selectList("selectDebateByParameterMap", parameter, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.dao.IDebateDao#insertDebate(com.haoyu.tip.debate.entity.Debate)
	 */
	@Override
	public int insertDebate(Debate debate) {
		debate.setDefaultValue();
		return super.insert(debate);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.dao.IDebateDao#updateDebate(com.haoyu.tip.debate.entity.Debate)
	 */
	@Override
	public int updateDebate(Debate debate) {
		debate.setUpdateValue();
		return super.update(debate);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.dao.IDebateDao#deleteDebateByLogic(com.haoyu.tip.debate.entity.Debate)
	 */
	@Override
	public int deleteDebateByLogic(Debate debate) {
		debate.setUpdateValue();
		return super.deleteByLogic(debate);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.tip.debate.dao.IDebateDao#deleteDebateByPhysics(java.lang.String)
	 */
	@Override
	public int deleteDebateByPhysics(String id) {
		return super.deleteByPhysics(id);
	}



}
