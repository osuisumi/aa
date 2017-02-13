/**
 * 
 */
package com.haoyu.aip.debate.dao.impl.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.dao.IDebateUserViewsDao;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.entity.ViewComment;
import com.haoyu.sip.core.entity.User;
import com.haoyu.sip.core.jdbc.MybatisDao;
import com.haoyu.sip.core.utils.ThreadContext;

/**
 * @author lianghuahuang
 *
 */
@Repository
public class DebateUserViewsDao extends MybatisDao implements
		IDebateUserViewsDao {

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserViewsDao#insertDebateUserViews(com.haoyu.aip.debate.entity.DebateUserViews)
	 */
	@Override
	public int insertDebateUserViews(DebateUserViews debateUserViews) {
		debateUserViews.setDefaultValue();
		return super.insert(debateUserViews);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserViewsDao#updateDebateUserViews(com.haoyu.aip.debate.entity.DebateUserViews)
	 */
	@Override
	public int updateDebateUserViews(DebateUserViews debateUserViews) {
		debateUserViews.setUpdateValue();
		return super.update(debateUserViews);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserViewsDao#selectDebateUserViewsByDebateUser(com.haoyu.aip.debate.entity.DebateUser, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<DebateUserViews> selectDebateUserViewsByDebateUser(
			DebateUser debateUser, PageBounds pageBounds) {
		return super.selectList("selectDebateUserViewsByDebateUser", debateUser, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.dao.IDebateUserViewsDao#deleteDebateUserViewsByLogic(com.haoyu.aip.debate.entity.DebateUserViews)
	 */
	@Override
	public int deleteDebateUserViewsByLogic(DebateUserViews debateUserViews) {
		debateUserViews.setUpdateValue();
		return super.deleteByLogic(debateUserViews);
	}

	@Override
	public int updateBestViews(DebateUserViews debateUserViews) {
		debateUserViews.setUpdateValue();
		super.update("updateBestViewsIsNull", debateUserViews.getDebateUser().getId());
		return super.update("updateBestViews",debateUserViews);
	}

	@Override
	public int updateSupportNum(DebateUserViews debateUserViews) {
		return super.update("updateSupportNum", debateUserViews);
	}

	@Override
	public List<ViewComment> selectViewComment(ViewComment viewComment,
			PageBounds pageBounds) {
		return super.selectList("selectViewComment", viewComment, pageBounds);
	}

	@Override
	public int updateCommentsNum(DebateUserViews debateUserViews) {
		return super.update("updateCommentNum", debateUserViews);
	}

	@Override
	public int getCount(Map<String, Object> parameter) {
		return super.selectOne("getCount", parameter);
	}
}
