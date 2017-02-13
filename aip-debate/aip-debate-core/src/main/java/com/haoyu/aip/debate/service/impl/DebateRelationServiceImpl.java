/**
 * 
 */
package com.haoyu.aip.debate.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haoyu.aip.debate.dao.IDebateRelationDao;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.service.IDebateRelationService;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Service
public class DebateRelationServiceImpl implements IDebateRelationService {
	@Autowired
	private IDebateRelationDao debateRelationDao;
	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateRelationService#findDebateRelationStatByDebateUser(com.haoyu.aip.debate.entity.DebateUser)
	 */
	@Override
	public DebateRelation findDebateRelationStatByDebateUser(
			DebateUser debateUser) {
		DebateRelation dr =  debateRelationDao.selectDebateRelationStatByDebateUser(debateUser);
		if(dr!=null&&dr.getDebate()!=null&&dr.getDebate().getArguments()!=null){
			Collections.sort(dr.getDebate().getArguments());
		}
		return dr;
	}
	@Override
	public Response updateBrowseNum(String id) {
		int count =  debateRelationDao.updateBrowseNum(id);
		return count>0?Response.successInstance():Response.failInstance();
	}
	@Override
	public Response updateFollowNum(String id, int followNum) {
		DebateRelation dr = new DebateRelation(id);
		dr.setFollowNum(followNum);
		int count  = debateRelationDao.updateFollowNum(dr);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
