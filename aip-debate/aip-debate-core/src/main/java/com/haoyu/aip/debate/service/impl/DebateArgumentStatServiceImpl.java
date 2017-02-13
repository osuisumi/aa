/**
 * 
 */
package com.haoyu.aip.debate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haoyu.aip.debate.dao.IDebateArgumentStatDao;
import com.haoyu.aip.debate.entity.DebateArgumentStat;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.aip.debate.service.IDebateArgumentStatService;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
@Service
public class DebateArgumentStatServiceImpl implements
		IDebateArgumentStatService {
	@Autowired
	private IDebateArgumentStatDao debateArgumentStatDao;
	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateArgumentStatService#findDebateArgumentStatById(java.lang.String)
	 */
	@Override
	public DebateArgumentStat findDebateArgumentStatById(String id) {
		return debateArgumentStatDao.selectDebateArgumentStatById(id);
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateArgumentStatService#findDebateArgumentStatByDebateRelation(com.haoyu.aip.debate.entity.DebateRelation)
	 */
	@Override
	public List<DebateArgumentStat> findDebateArgumentStatByDebateRelation(
			DebateRelation debateRelation) {
		return debateArgumentStatDao.selectDebateArgumentStatByDebateRelation(debateRelation);
	}

	@Override
	public Response updateViewsNum(DebateUser debateUser) {
		int count =  debateArgumentStatDao.updateViewsNum(debateUser);
		return count>0?Response.successInstance():Response.failInstance().responseMsg("updateViewsNum is failed!");
	}

}
