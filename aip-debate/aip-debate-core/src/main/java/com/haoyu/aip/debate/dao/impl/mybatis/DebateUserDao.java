/**
 * 
 */
package com.haoyu.aip.debate.dao.impl.mybatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.dao.IDebateUserDao;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class DebateUserDao extends MybatisDao implements IDebateUserDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserDao#insertDebateUser(com.haoyu.aip.debate.entity.DebateUser)
	 */
	@Override
	public int insertDebateUser(DebateUser debateUser) {
		debateUser.setDefaultValue();
		return super.insert(debateUser);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserDao#selectDebateUserById(java.lang.String)
	 */
	@Override
	public DebateUser selectDebateUserById(String id) {
		return super.selectByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserDao#selectDebateUserByDebateRelation(com.haoyu.aip.debate.entity.DebateRelation)
	 */
	@Override
	public List<DebateUser> selectDebateUserByDebateRelation(
			DebateRelation debateRelation,PageBounds pageBounds) {
		return super.selectList("selectDebateUserByDebateRelation", debateRelation, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserDao#updateViewsNum(java.lang.String)
	 */
	@Override
	public int updateViewsNum(String id) {		
		return super.update("updateViewsNum", id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserDao#updateCommentNum(java.lang.String)
	 */
	@Override
	public int updateCommentsNum(String id) {
		//TODO
		return 0;
	}

	@Override
	public int deleteDebateUserByLogic(DebateUser debateUser) {
		debateUser.setUpdateValue();
		return super.deleteByLogic(debateUser);
	}

	@Override
	public DebateUser selectDebateUser(DebateUser debateUser) {
		return super.selectOne("selectDebateUser", debateUser);
	}

}
