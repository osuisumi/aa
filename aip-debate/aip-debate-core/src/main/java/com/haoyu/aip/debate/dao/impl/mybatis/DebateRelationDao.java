/**
 * 
 */
package com.haoyu.aip.debate.dao.impl.mybatis;

import org.springframework.stereotype.Repository;

import com.haoyu.aip.debate.dao.IDebateRelationDao;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.sip.core.jdbc.MybatisDao;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class DebateRelationDao extends MybatisDao implements IDebateRelationDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateRelationDao#insertDebateRelation(com.haoyu.aip.debate.entity.DebateRelation)
	 */
	@Override
	public int insertDebateRelation(DebateRelation debateRelation) {
		debateRelation.setDefaultValue();
		return super.insert(debateRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateRelationDao#updateParticipateNum(java.lang.String)
	 */
	@Override
	public int updateParticipateNum(String id) {
		return super.update("updateParticipateNum", id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateRelationDao#updateBrowseNum(java.lang.String)
	 */
	@Override
	public int updateBrowseNum(String id) {
		return super.update("updateBrowseNum", id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateRelationDao#updateFollowNum(java.lang.String)
	 */
	@Override
	public int updateFollowNum(DebateRelation debateRelation) {
		return super.update("updateFollowNum", debateRelation);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateRelationDao#updateCollectNum(java.lang.String)
	 */
	@Override
	public int updateCollectNum(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateRelationDao#deleteDebateRelationByPhysics(java.lang.String)
	 */
	@Override
	public int deleteDebateRelationByPhysics(String id) {
		return super.deleteByPhysics(id);
	}

	@Override
	public DebateRelation selectDebateRelationStatByDebateUser(
			DebateUser debateUser) {
		return super.selectOne("selectDebateRelationStatByDebateUser", debateUser);
	}

}
