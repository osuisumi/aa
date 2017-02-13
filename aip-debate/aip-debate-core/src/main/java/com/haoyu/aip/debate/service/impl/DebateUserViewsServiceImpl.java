/**
 * 
 */
package com.haoyu.aip.debate.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.haoyu.aip.debate.dao.IDebateArgumentStatDao;
import com.haoyu.aip.debate.dao.IDebateUserDao;
import com.haoyu.aip.debate.dao.IDebateUserViewsDao;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.entity.DebateUserViews;
import com.haoyu.aip.debate.entity.ViewComment;
import com.haoyu.aip.debate.event.CreateDebateUserViewsEvent;
import com.haoyu.aip.debate.event.DeleteDebateUserViewsEvent;
import com.haoyu.aip.debate.service.IDebateUserViewsService;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class DebateUserViewsServiceImpl implements IDebateUserViewsService {
	@Autowired
	private IDebateUserViewsDao debateUserViewsDao;
	@Autowired
	private IDebateArgumentStatDao debateArgumentStatDao;
	@Autowired
	private IDebateUserDao debateUserDao;
	@Autowired  
	private ApplicationContext applicationContext;  
	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserViewsService#createDebateUserViews(com.haoyu.aip.debate.entity.DebateUserViews)
	 */
	@Override
	public Response createDebateUserViews(DebateUserViews debateUserViews) {
		if(debateUserViews==null||StringUtils.isEmpty(debateUserViews.getCreator().getId())){
			return Response.failInstance().responseMsg("debateUserViews is null or creator is null");
		}
		if(StringUtils.isEmpty(debateUserViews.getId())){
			debateUserViews.setId(Identities.uuid2());
		}
		int count = debateUserViewsDao.insertDebateUserViews(debateUserViews);
		//更新debateUser的viewsNum以及debateArgumentStat的viewsNum
		debateUserDao.updateViewsNum(debateUserViews.getDebateUser().getId());
		debateArgumentStatDao.updateViewsNum(debateUserViews.getDebateUser());
		applicationContext.publishEvent(new CreateDebateUserViewsEvent(debateUserViews));
		return count>0?Response.successInstance().responseData(debateUserViews):Response.failInstance().responseMsg("createDebateUserViews is failed!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserViewsService#updateDebateUserViews(com.haoyu.aip.debate.entity.DebateUserViews)
	 */
	@Override
	public Response updateDebateUserViews(DebateUserViews debateUserViews) {
		int count = debateUserViewsDao.updateDebateUserViews(debateUserViews);
		return count>0?Response.successInstance().responseData(debateUserViews):Response.failInstance().responseMsg("updateDebateUserViews is failed!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserViewsService#deleteDebateUserViews(java.lang.String)
	 */
	@Override
	public Response deleteDebateUserViews(DebateUserViews debateUserViews) {
		int count = debateUserViewsDao.deleteDebateUserViewsByLogic(debateUserViews);
		if(count>0){
			applicationContext.publishEvent(new DeleteDebateUserViewsEvent(debateUserViews));
		}
		return count>0?Response.successInstance():Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserViewsService#udpateDebateUserViewsSupportNum(java.lang.String)
	 */
	@Override
	public Response updateDebateUserViewsSupportNum(String id,int supportNum) {
		DebateUserViews duv = new DebateUserViews();
		duv.setId(id);
		duv.setSupportNum(supportNum);
		int count = debateUserViewsDao.updateSupportNum(duv);
		return count>0?Response.successInstance():Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserViewsService#updateDebateUserViewsCommentNum(java.lang.String)
	 */
	@Override
	public Response updateDebateUserViewsCommentsNum(String id,int commentsNum) {
		DebateUserViews duv = new DebateUserViews();
		duv.setId(id);
		duv.setCommentsNum(commentsNum);
		int count = debateUserViewsDao.updateCommentsNum(duv);
		return count>0?Response.successInstance():Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserViewsService#updateDebateUserViewsBestView(DebateUserViews)
	 */
	@Override
	public Response updateDebateUserViewsBestView(DebateUserViews debateUserViews) {
		int count = debateUserViewsDao.updateBestViews(debateUserViews);
		if(count>0){
			count = debateArgumentStatDao.updateBestViews(debateUserViews);
		}
		return count>0?Response.successInstance():Response.failInstance().responseMsg("updateDebateUserViewsBestView is failed!");
	}

	@Override
	public List<DebateUserViews> findDebateUserViewsByDebateUser(
			DebateUser debateUser,PageBounds pageBounds) {
		if(pageBounds!=null&&(pageBounds.getOrders()==null||pageBounds.getOrders().isEmpty())){
			pageBounds.setOrders(Order.formString("CREATE_TIME.DESC"));
		}
		return debateUserViewsDao.selectDebateUserViewsByDebateUser(debateUser, pageBounds);
	}

	@Override
	public List<ViewComment> findViewComment(ViewComment viewComment,
			PageBounds pageBounds) {
		return debateUserViewsDao.selectViewComment(viewComment, pageBounds);
	}

	@Override
	public int getCount(Map<String, Object> parameter) {
		return debateUserViewsDao.getCount(parameter);
	}


}
