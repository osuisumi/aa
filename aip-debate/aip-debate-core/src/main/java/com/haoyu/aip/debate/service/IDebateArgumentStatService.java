/**
 * 
 */
package com.haoyu.aip.debate.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.haoyu.aip.debate.entity.DebateArgumentStat;
import com.haoyu.aip.debate.entity.DebateRelation;
import com.haoyu.aip.debate.entity.DebateUser;
import com.haoyu.sip.core.service.Response;

/**
 * @author lianghuahuang
 *
 */
public interface IDebateArgumentStatService {
	
	DebateArgumentStat findDebateArgumentStatById(String id);
	
	List<DebateArgumentStat> findDebateArgumentStatByDebateRelation(DebateRelation debateRelation);
	
	/**
	 * 更新论点对应的观点数
	 * @param debateUser 
	 * id required
	 * argument.id required
	 * debateRelation.id required
	 * @return
	 */
	Response updateViewsNum(DebateUser debateUser);
}
