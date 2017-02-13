/**
 * 
 */
package com.haoyu.aip.debate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.debate.dao.IDebateArgumentDao;
import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class DebateArgumentDao extends MybatisDao implements IDebateArgumentDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentDao#selectDebateArgumentById(java.lang.String)
	 */
	@Override
	public DebateArgument selectDebateArgumentById(String id) {
		return super.selectByPrimaryKey(id);
	}
	
	@Override
	public List<DebateArgument> selectDebateArgumentByDebate(
			Debate debate) {
		return super.selectList("selectDebateArgumentByDebate", debate);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentDao#selectDebateArgumentByDebateRelation(com.haoyu.aip.debate.entity.DebateRelation)
	 */
	@Override
	public List<DebateArgument> selectDebateArgumentByDebateRelation(
			DebateRelation debateRelation) {
		return super.selectList("selectDebateArgumentByDebateRelation", debateRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentDao#deleteDebateArgumentById(java.lang.String)
	 */
	@Override
	public int deleteDebateArgumentByLogic(DebateArgument debateArgument) {
		Map<String,Object> param = Maps.newHashMap();
		param.put("updatedby",ThreadContext.getUser()!=null?ThreadContext.getUser().getId():null);
		param.put("ids", Lists.newArrayList(debateArgument.getId()));
		param.put("updateTime", System.currentTimeMillis());
		debateArgument.setUpdateTime(System.currentTimeMillis());
		return super.deleteByLogic(param);
	}
	
	@Override
	public int deleteDebateArgumentByLogic(List<DebateArgument> debateArguments,String updatedby) {
		Map<String,Object> param = Maps.newHashMap();
		List<String> ids = Lists.newArrayList();
		for(DebateArgument da:debateArguments){
			ids.add(da.getId());
		}
		param.put("ids", ids);
		param.put("updatedby", ThreadContext.getUser()!=null?ThreadContext.getUser().getId():null);
		param.put("updateTime", System.currentTimeMillis());
		return super.deleteByLogic(param);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentDao#insertDebateArgument(com.haoyu.aip.debate.entity.DebateArgument)
	 */
	@Override
	public int insertDebateArgument(DebateArgument debateArgument) {
		debateArgument.setDefaultValue();
		return super.insert(debateArgument);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateArgumentDao#updateDebateArgument(com.haoyu.aip.debate.entity.DebateArgument)
	 */
	@Override
	public int updateDebateArgument(DebateArgument debateArgument) {
		debateArgument.setUpdateValue();
		return super.update(debateArgument);
	}



}
