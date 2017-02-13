/**
 * 
 */
package com.haoyu.aip.debate.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.dao.IDebateArgumentStatDao;
import com.haoyu.aip.debate.dao.IDebateRelationDao;
import com.haoyu.aip.debate.dao.IDebateUserDao;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.service.IDebateUserService;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Service
public class DebateUserServiceImpl implements IDebateUserService {
	@Autowired
	private IDebateUserDao debateUserDao;
	@Autowired
	private IDebateRelationDao debateRelationDao;
	@Autowired
	private IDebateArgumentStatDao debateArgumentStatDao;
	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserService#findDebateUserById(java.lang.String)
	 */
	@Override
	public DebateUser findDebateUserById(String id) {
		return debateUserDao.selectDebateUserById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserService#findDebateUserByDebateRelation(com.haoyu.aip.debate.entity.DebateRelation, com.github.miemiedev.mybatis.paginator.domain.PageBounds)
	 */
	@Override
	public List<DebateUser> findDebateUserByDebateRelation(
			DebateRelation debateRelation, PageBounds pageBounds) {
		return debateUserDao.selectDebateUserByDebateRelation(debateRelation, pageBounds);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserService#createDebateUser(com.haoyu.aip.debate.entity.DebateUser)
	 */
	@Override
	public Response createDebateUser(DebateUser debateUser) {
		if(debateUser==null){
			return Response.failInstance().responseMsg("debateUser  is null!");
		}
		if(debateUser.getDebateRelation()==null){
			return Response.failInstance().responseMsg("debateRelation  is null!");
		}
		
		if(debateUser.getCreator()==null){
			return Response.failInstance().responseMsg("creator  is null!");
		}
		if(StringUtils.isEmpty(debateUser.getId())){
			debateUser.setId(DebateUser.getId(debateUser.getDebateRelation().getId(), debateUser.getCreator().getId()));
		}
		int count = debateUserDao.insertDebateUser(debateUser);
		//更新debateRelation 中的参与数以及debateArgumentStat中的参与数
		debateRelationDao.updateParticipateNum(debateUser.getDebateRelation().getId());
		debateArgumentStatDao.updateParticipateNum(debateUser);
		return count>0?Response.successInstance().responseData(debateUser):Response.failInstance().responseMsg("insert debateUser failed!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserService#updateViewsNum(java.lang.String)
	 */
	@Override
	public Response updateViewsNum(String id) {
		int count =  debateUserDao.updateViewsNum(id);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("update viewsNum failed!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserService#updateCommentsNum(java.lang.String)
	 */
	@Override
	public Response updateCommentsNum(String id) {
		int count = debateUserDao.updateCommentsNum(id);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("update commentsNum failed!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateUserService#deleteDebateUser(com.haoyu.aip.debate.entity.DebateUser)
	 */
	@Override
	public Response deleteDebateUser(DebateUser debateUser) {
		int count = debateUserDao.deleteDebateUserByLogic(debateUser);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("update deleteDebateUser failed!");
	}

	@Override
	public DebateUser findDebateUser(DebateUser debateUser) {
		if(debateUser.getCreator().getId()==null||debateUser.getDebateRelation()==null||debateUser.getDebateRelation().getId()==null){
			return null;
		}
		return debateUserDao.selectDebateUser(debateUser);
	}

}
