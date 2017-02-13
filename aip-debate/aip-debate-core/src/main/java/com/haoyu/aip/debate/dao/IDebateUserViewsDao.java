/**
 * 
 */
package com.haoyu.aip.debate.dao;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.entity.ViewComment;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateUserViewsDao {
	
	int insertDebateUserViews(DebateUserViews debateUserViews);
	
	int updateDebateUserViews(DebateUserViews debateUserViews);
	
	List<DebateUserViews> selectDebateUserViewsByDebateUser(DebateUser debaterUser,PageBounds pageBounds);

	List<ViewComment> selectViewComment(ViewComment viewComment,PageBounds pageBounds);
	
	int deleteDebateUserViewsByLogic(DebateUserViews debateUserViews);
	
	int updateBestViews(DebateUserViews debateUserViews);
	
	int updateSupportNum(DebateUserViews debateUserViews);
	
	int updateCommentsNum(DebateUserViews debateUserViews);
	
	int getCount(Map<String,Object> parameter);
	
}
