/**
 * 
 */
package com.haoyu.aip.debate.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haoyu.aip.debate.dao.IDebateArgumentDao;
import com.haoyu.aip.debate.dao.IDebateArgumentStatDao;
import com.haoyu.aip.debate.dao.impl.mybatis.DebateArgumentDao;
import com.haoyu.aip.debate.entity.Debate;
import com.haoyu.aip.debate.entity.DebateArgument;
import com.haoyu.aip.debate.entity.DebateArgumentStat;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.service.IDebateArgumentService;
import com.haoyu.sip.core.mapper.JsonMapper;
import com.haoyu.sip.core.service.Response;
import com.haoyu.sip.utils.Identities;

/**
 * @author lianghuahuang
 *
 */
@Service
public class DebateArgumentServiceImpl implements IDebateArgumentService {
	@Autowired
	private IDebateArgumentDao debateArgumentDao;
	@Autowired
	private IDebateArgumentStatDao debateArgumentStatDao;
	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateArgumentService#createDebateArgument(com.haoyu.aip.debate.entity.DebateArgument)
	 */
	@Override
	public Response createDebateArgument(DebateArgument debateArgument) {
		if(debateArgument==null||debateArgument.getDebate()==null){
			return Response.failInstance().responseMsg("debateArgument is null or debateArgument.debate is null");
		}
		if(StringUtils.isEmpty(debateArgument.getId())){
			debateArgument.setId(Identities.uuid2());
		}
		int count = debateArgumentDao.insertDebateArgument(debateArgument);
		if(count>0){
			DebateArgumentStat das = new DebateArgumentStat();
			Debate debate = debateArgument.getDebate();
			if(debate.getDebateRelations()!=null&&!debate.getDebateRelations().isEmpty()){
				for(DebateRelation dr:debate.getDebateRelations()){
					das.setId(DebateArgumentStat.getId(debateArgument.getId(),dr.getId()));
					das.setArgument(debateArgument);
					das.setDebateRelation(dr);
					das.setViewsNum(0);
					count = debateArgumentStatDao.insertDebateArgumentStat(das);
				}
			}
		}
		return count>0?Response.successInstance().responseData(debateArgument):Response.failInstance().responseMsg("createDebateArgument failed!");
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateArgumentService#updateDebateArgument(com.haoyu.aip.debate.entity.DebateArgument)
	 */
	@Override
	public Response updateDebateArgument(DebateArgument debateArgument) {
		if(debateArgument==null||debateArgument.getId()==null){
			return Response.failInstance().responseMsg("debateArgument is null or id is null");
		}
		int count =  debateArgumentDao.updateDebateArgument(debateArgument);
		return count>0?Response.successInstance().responseData(debateArgument):Response.failInstance();
	}

	/* (non-Javadoc)
	 * @see com.haoyu.aip.debate.service.IDebateArgumentService#deleteDebateArgument(java.lang.String)
	 */
	@Override
	public Response deleteDebateArgument(DebateArgument debateArgument) {
		int count =  debateArgumentDao.deleteDebateArgumentByLogic(debateArgument);
		//删除debateArgumentStat
		debateArgumentStatDao.deleteByLogic(debateArgument);
		return count>0?Response.successInstance():Response.failInstance();
	}

}
