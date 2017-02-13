/**
 * 
 */
package com.haoyu.aip.debate.service;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.entity.ViewComment;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateUserViewsService {
	
	List<DebateUserViews> findDebateUserViewsByDebateUser(DebateUser debateUser,PageBounds pageBounds);
	
	List<ViewComment> findViewComment(ViewComment viewComment,PageBounds pageBounds);
	
	/**
	 * 发表观点
	 * @param debateUserViews
	 * @return
	 */
	Response createDebateUserViews(DebateUserViews debateUserViews);
	
	/**
	 * 修改观点
	 * @param debateUserViews
	 * @return
	 */
	Response updateDebateUserViews(DebateUserViews debateUserViews);
	
	/**
	 * 删除观点 
	 * @param debateUserViews
	 * @return
	 */
	Response deleteDebateUserViews(DebateUserViews debateUserViews);
	
	
	/**
	 * 更新观点点赞数
	 * @param id
	 * @return
	 */
	Response updateDebateUserViewsSupportNum(String id,int supportNum);
	
	/**
	 * 更新观点评论数
	 * @param id
	 * @return
	 */
	Response updateDebateUserViewsCommentsNum(String id,int commentsNum);
	
	/**
	 * 更新最佳观点
	 * @param debateUserViews
	 * @return
	 */
	Response updateDebateUserViewsBestView(DebateUserViews debateUserViews);
	
	int getCount(Map<String,Object> parameter);
	
	
}
