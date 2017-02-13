/**
 * 
 */
package com.haoyu.aip.debate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.debate.dao.IDebateArgumentStatDao;
import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.aip.debate.entity.DebateArgumentStat;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class DebateArgumentStatDao extends MybatisDao implements
		IDebateArgumentStatDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentStatDao#insertDebateArgumentStat(com.haoyu.aip.debate.entity.DebateArgumentStat)
	 */
	@Override
	public int insertDebateArgumentStat(DebateArgumentStat debateArgumentStat) {
		debateArgumentStat.setDefaultValue();
		return super.insert(debateArgumentStat);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentStatDao#updateBestViews(com.haoyu.aip.debate.entity.DebateUserViews)
	 */
	@Override
	public int updateBestViews(DebateUserViews debateUserViews) {
		debateUserViews.setUpdateValue();
		return super.update("updateBestViews", debateUserViews);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentStatDao#updateViewsNum(com.haoyu.aip.debate.entity.DebateUser)
	 */
	@Override
	public int updateViewsNum(DebateUser debateUser) {
		return super.update("updateViewsNum", debateUser);
	}
	
	@Override
	public int updateParticipateNum(DebateUser debateUser) {
		return super.update("updateParticipateNum", debateUser);
	}
	
	@Override
	public int deleteByLogic(DebateArgument da) {
		Map<String,Object> param = Maps.newHashMap();
		param.put("updatedby", da.getUpdatedby()!=null?da.getUpdatedby().getId():null);
		param.put("argumentId",  Lists.newArrayList(da.getId()));
		param.put("updateTime", System.currentTimeMillis());
		return super.deleteByLogic(param);
	}

	@Override
	public int deleteByLogic(List<DebateArgument> das, String updatedby) {
		Map<String,Object> param = Maps.newHashMap();
		param.put("updatedby", updatedby);
		List<String> argumentIds = Lists.newArrayList();
		for(DebateArgument da:das){
			argumentIds.add(da.getId());
		}
		param.put("argumentIds", argumentIds);
		param.put("updateTime", System.currentTimeMillis());
		return super.deleteByLogic(param);
	}

	@Override
	public DebateArgumentStat selectDebateArgumentStatById(String id) {
		return super.selectByPrimaryKey(id);
	}

	@Override
	public List<DebateArgumentStat> selectDebateArgumentStatByDebateRelation(
			DebateRelation debateRelation) {
		return super.selectList("selectDebateArgumentStatByDebateRelation", debateRelation);
	}

	

}
